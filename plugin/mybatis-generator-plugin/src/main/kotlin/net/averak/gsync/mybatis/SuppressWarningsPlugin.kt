package net.averak.gsync.mybatis

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.Interface
import org.mybatis.generator.api.dom.java.TopLevelClass

/**
 * 生成されたコードで発生する警告を抑制するプラグイン
 */
@SuppressWarnings("kotlin:S4144")
class SuppressWarningsPlugin : PluginAdapter() {

    private val suppressWarningStr = "@SuppressWarnings({\"all\"})"

    override fun validate(warnings: MutableList<String>): Boolean {
        return true
    }

    override fun modelBaseRecordClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        topLevelClass.annotations += suppressWarningStr
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable)
    }

    override fun modelExampleClassGenerated(topLevelClass: TopLevelClass, introspectedTable: IntrospectedTable): Boolean {
        topLevelClass.annotations += suppressWarningStr
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable)
    }

    override fun clientGenerated(interfaze: Interface, introspectedTable: IntrospectedTable): Boolean {
        interfaze.annotations += suppressWarningStr
        return super.clientGenerated(interfaze, introspectedTable)
    }
}
