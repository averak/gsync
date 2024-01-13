package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.Interface

/**
 * 生成されたコードで発生する警告を抑制するプラグイン
 */
class SuppressWarningsPlugin : PluginAdapter() {

    override fun validate(warnings: MutableList<String>?): Boolean {
        return true
    }

    override fun clientGenerated(interfaze: Interface, introspectedTable: IntrospectedTable): Boolean {
        interfaze.annotations += "@SuppressWarnings({\"JavadocDeclaration\", \"DuplicatedCode\"})"
        return true
    }
}
