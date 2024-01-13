package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.IntrospectedColumn
import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.*
import java.util.*

/**
 * InsertOrUpdate 関連コードを生成するプラグイン
 */
class InsertOrUpdatePlugin : PluginAdapter() {

    override fun validate(warnings: List<String>): Boolean {
        return true
    }

    override fun modelBaseRecordClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        topLevelClass.setSuperClass("AbstractDto<${topLevelClass.type.shortName}>")
        topLevelClass.addImportedType("java.util.Objects")

        val method = Method("isPrimaryKeyEquals")
        method.visibility = JavaVisibility.PUBLIC
        method.addParameter(Parameter(FullyQualifiedJavaType(introspectedTable.baseRecordType), "other"))
        method.setReturnType(FullyQualifiedJavaType("boolean"))

        val expressions = mutableListOf<String>()
        introspectedTable.primaryKeyColumns.forEach { column ->
            expressions += "Objects.equals(${getterName(column)}(), other.${getterName(column)}())"
        }
        method.bodyLines += "return ${expressions.joinToString(" && ")};"
        topLevelClass.addMethod(method)

        return true
    }

    override fun clientGenerated(interfaze: Interface, introspectedTable: IntrospectedTable): Boolean {
        if (introspectedTable.hasPrimaryKeyColumns()) {
            addSyncOriginalMethod(interfaze, introspectedTable)
            addSyncOriginalMultiMethod(interfaze, introspectedTable)
            addInsertOrUpdateMethod(interfaze, introspectedTable)
            addInsertOrUpdateMultiMethod(interfaze, introspectedTable)
        }
        return true
    }

    private fun addSyncOriginalMethod(interfaze: Interface, introspectedTable: IntrospectedTable) {
        val method = Method("syncOriginal")
        method.visibility = JavaVisibility.PUBLIC
        method.isDefault = true
        method.addParameter(Parameter(FullyQualifiedJavaType(introspectedTable.baseRecordType), "dto"))
        method.setReturnType(FullyQualifiedJavaType("void"))

        val primaryKeyArgs = introspectedTable.primaryKeyColumns.joinToString(", ") {
            "dto.${getterName(it)}()"
        }
        method.bodyLines += "dto.setOriginal(selectByPrimaryKey($primaryKeyArgs));"

        addSyncOriginalJavadoc(method)
        interfaze.addMethod(method)
    }

    private fun addSyncOriginalMultiMethod(interfaze: Interface, introspectedTable: IntrospectedTable) {
        interfaze.addImportedType(FullyQualifiedJavaType("java.util.List"))

        val method = Method("syncOriginal")
        method.visibility = JavaVisibility.PUBLIC
        method.isDefault = true
        method.addParameter(Parameter(FullyQualifiedJavaType("List<${introspectedTable.baseRecordType}>"), "dtos"))
        method.setReturnType(FullyQualifiedJavaType("void"))

        val dtoClassName = introspectedTable.baseRecordType.split(".").last()
        val exampleClassName = introspectedTable.exampleType.split(".").last()
        method.bodyLines += "if (dtos.isEmpty()) {"
        method.bodyLines += "    return;"
        method.bodyLines += "}"
        method.bodyLines += ""
        method.bodyLines += "final var example = new $exampleClassName();"
        introspectedTable.primaryKeyColumns.forEach { column ->
            val getterRef = "$dtoClassName::${getterName(column)}"
            method.bodyLines += "example.createCriteria().and${capitalize(column.javaProperty)}In(dtos.stream().map($getterRef).toList());"
        }
        method.bodyLines += "final var originals = selectByExample(example);"
        method.bodyLines += "originals.forEach(original -> "
        method.bodyLines += "    dtos.forEach(dto -> {"
        method.bodyLines += "        if (dto.isPrimaryKeyEquals(original)) {"
        method.bodyLines += "            dto.setOriginal(original);"
        method.bodyLines += "        }"
        method.bodyLines += "    })"
        method.bodyLines += ");"

        addSyncOriginalJavadoc(method)
        interfaze.addMethod(method)
    }

    private fun addInsertOrUpdateMethod(interfaze: Interface, introspectedTable: IntrospectedTable) {
        val method = Method("insertOrUpdate")
        method.visibility = JavaVisibility.PUBLIC
        method.isDefault = true
        method.addParameter(Parameter(FullyQualifiedJavaType(introspectedTable.baseRecordType), "dto"))
        method.setReturnType(FullyQualifiedJavaType("void"))

        val updateMethodName = if (introspectedTable.hasBLOBColumns()) {
            "updateByPrimaryKeyWithBLOBs"
        } else {
            "updateByPrimaryKey"
        }
        method.bodyLines += "if (dto.getOriginal() == null) {"
        method.bodyLines += "    insert(dto);"
        method.bodyLines += "}"
        method.bodyLines += "else {"
        if (introspectedTable.allColumns.any { it.javaProperty == "createdAt" }) {
            method.bodyLines += "    dto.setCreatedAt(dto.getOriginal().getCreatedAt());"
        }
        method.bodyLines += "    $updateMethodName(dto);"
        method.bodyLines += "}"

        addInsertOrUpdateJavadoc(method)
        interfaze.addMethod(method)
    }

    private fun addInsertOrUpdateMultiMethod(interfaze: Interface, introspectedTable: IntrospectedTable) {
        val method = Method("insertOrUpdate")
        method.visibility = JavaVisibility.PUBLIC
        method.isDefault = true
        method.addParameter(Parameter(FullyQualifiedJavaType("List<${introspectedTable.baseRecordType}>"), "dtos"))
        method.setReturnType(FullyQualifiedJavaType("void"))

        val updateMethodName = if (introspectedTable.hasBLOBColumns()) {
            "updateByPrimaryKeyWithBLOBs"
        } else {
            "updateByPrimaryKey"
        }
        method.bodyLines += "final var inserts = dtos.stream().filter(dto -> dto.getOriginal() == null).toList();"
        method.bodyLines += "if (!inserts.isEmpty()) {"
        method.bodyLines += "    bulkInsert(inserts);"
        method.bodyLines += "}"
        method.bodyLines += ""
        method.bodyLines += "final var updates = dtos.stream().filter(dto -> dto.getOriginal() != null).toList();"
        method.bodyLines += "updates.parallelStream().forEach(dto -> {"
        method.bodyLines += "    if (dto.getOriginal() == null) {"
        method.bodyLines += "        return;"
        method.bodyLines += "    }"
        method.bodyLines += "    dto.setCreatedAt(dto.getOriginal().getCreatedAt());"
        method.bodyLines += "    $updateMethodName(dto);"
        method.bodyLines += "});"

        addInsertOrUpdateJavadoc(method)
        interfaze.addMethod(method)
    }

    private fun capitalize(str: String): String {
        return str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private fun getterName(column: IntrospectedColumn): String {
        return "get${capitalize(column.javaProperty)}"
    }

    private fun addSyncOriginalJavadoc(method: Method) {
        method.javaDocLines += "/**"
        method.javaDocLines += " * PK でレコード検索し、存在する場合は original にセットする"
        method.javaDocLines += " *"
        method.javaDocLines += " * @mbg.generated"
        method.javaDocLines += " */"
    }

    private fun addInsertOrUpdateJavadoc(method: Method) {
        method.javaDocLines += "/**"
        method.javaDocLines += " * original が null の場合は INSERT、そうでない場合は UPDATE を実行する"
        method.javaDocLines += " *"
        method.javaDocLines += " * @mbg.generated"
        method.javaDocLines += " */"
    }
}
