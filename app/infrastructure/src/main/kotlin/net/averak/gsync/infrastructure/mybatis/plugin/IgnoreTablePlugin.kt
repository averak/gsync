package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.GeneratedXmlFile
import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.Interface
import org.mybatis.generator.api.dom.java.TopLevelClass

/**
 * MyBatis Generatorで不要なテーブルを無視するプラグイン
 */
class IgnoreTablePlugin : PluginAdapter() {

    private val ignoredTableNames = listOf(
        "flyway_schema_history",
        "oauth2_authorized_client",
        "SPRING_SESSION",
        "SPRING_SESSION_ATTRIBUTES",
    )

    override fun validate(warnings: List<String>): Boolean {
        return true
    }

    private fun checkIsTableToGenerate(introspectedTable: IntrospectedTable): Boolean {
        val tableName = introspectedTable.fullyQualifiedTableNameAtRuntime.replace("`", "")
        return ignoredTableNames.none { tableName == it }
    }

    override fun modelBaseRecordClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        return checkIsTableToGenerate(introspectedTable)
    }

    override fun modelExampleClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        return checkIsTableToGenerate(introspectedTable)
    }

    override fun clientGenerated(interfaze: Interface, introspectedTable: IntrospectedTable): Boolean {
        return checkIsTableToGenerate(introspectedTable)
    }

    override fun sqlMapGenerated(sqlMap: GeneratedXmlFile, introspectedTable: IntrospectedTable): Boolean {
        return checkIsTableToGenerate(introspectedTable)
    }
}
