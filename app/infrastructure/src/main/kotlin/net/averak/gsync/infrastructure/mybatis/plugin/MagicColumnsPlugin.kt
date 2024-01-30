package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.JavaVisibility
import org.mybatis.generator.api.dom.java.Method
import org.mybatis.generator.api.dom.java.Parameter
import org.mybatis.generator.api.dom.java.TopLevelClass

/**
 * マジックカラムを扱うプラグイン
 */
class MagicColumnsPlugin : PluginAdapter() {

    override fun validate(warnings: List<String>): Boolean {
        return true
    }

    override fun modelBaseRecordClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        val method = Method(introspectedTable.baseRecordType.substringAfterLast("."))
        method.isConstructor = true
        method.visibility = JavaVisibility.PUBLIC
        introspectedTable.allColumns
            .filter { it.actualColumnName != "created_at" && it.actualColumnName != "updated_at" }
            .forEach {
                method.addParameter(
                    Parameter(it.fullyQualifiedJavaType, it.javaProperty),
                )
                method.bodyLines += "this.${it.javaProperty} = ${it.javaProperty};"
            }

        if (introspectedTable.allColumns.any { it.actualColumnName == "created_at" }) {
            method.bodyLines += "this.createdAt = LocalDateTime.now();"
        }
        if (introspectedTable.allColumns.any { it.actualColumnName == "updated_at" }) {
            method.bodyLines += "this.updatedAt = LocalDateTime.now();"
        }

        method.javaDocLines += "/**"
        method.javaDocLines += " * マジックカラムにデフォルト値を設定するコンストラクタ"
        method.javaDocLines += " *"
        method.javaDocLines += " * @mbg.generated"
        method.javaDocLines += " */"

        topLevelClass.addMethod(method)

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable)
    }
}
