package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType
import org.mybatis.generator.api.dom.java.TopLevelClass

/**
 * NULL 許容/非許容なカラムに対して Nullable/Nonnull アノテーションを付与するプラグイン
 */
class ResolveNullPlugin : PluginAdapter() {

    override fun validate(warnings: List<String>): Boolean {
        return true
    }

    override fun modelBaseRecordClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        resolveNullable(topLevelClass, introspectedTable)
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable)
    }

    private fun resolveNullable(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable) {
        topLevelClass.addImportedType(FullyQualifiedJavaType("javax.annotation.Nullable"))
        topLevelClass.addImportedType(FullyQualifiedJavaType("javax.annotation.Nonnull"))

        val columnNullableMap = HashMap<String, Boolean>()
        introspectedTable.allColumns.forEach { column ->
            columnNullableMap[column.javaProperty] = column.isNullable || column.isAutoIncrement
        }

        columnNullableMap.forEach { (columnName, isNullable) ->
            topLevelClass.methods.forEach { method ->
                if (method.name.startsWith(("get")) && method.name.substring(3).equals(columnName, ignoreCase = true)) {
                    method.addAnnotation(getAnnotationName(isNullable))
                }
                method.parameters.forEach { parameter ->
                    if (parameter.name == columnName) {
                        parameter.addAnnotation(getAnnotationName(isNullable))
                    }
                }
            }
        }
    }

    private fun getAnnotationName(isNullable: Boolean): String {
        return if (isNullable) "@Nullable" else "@Nonnull"
    }
}
