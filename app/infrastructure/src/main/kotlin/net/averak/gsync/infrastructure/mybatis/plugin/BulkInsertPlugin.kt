package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.java.*
import org.mybatis.generator.api.dom.xml.Attribute
import org.mybatis.generator.api.dom.xml.Document
import org.mybatis.generator.api.dom.xml.TextElement
import org.mybatis.generator.api.dom.xml.XmlElement

/**
 * Bulk Insert 関連コードを生成するプラグイン
 */
class BulkInsertPlugin : PluginAdapter() {

    override fun validate(warnings: List<String>): Boolean {
        return true
    }

    override fun clientGenerated(interfaze: Interface, introspectedTable: IntrospectedTable): Boolean {
        interfaze.addImportedType(FullyQualifiedJavaType("java.util.List"))

        val method = Method("bulkInsert")
        method.visibility = JavaVisibility.PUBLIC
        method.isAbstract = true
        method.addParameter(
            Parameter(FullyQualifiedJavaType("List<${introspectedTable.baseRecordType}>"), "dtos").apply {
                addAnnotation("@Param(\"dtos\")")
            },
        )
        method.setReturnType(FullyQualifiedJavaType("void"))

        method.javaDocLines += "/**"
        method.javaDocLines += " * 複数レコードを一括で INSERT する"
        method.javaDocLines += " *"
        method.javaDocLines += " * @mbg.generated"
        method.javaDocLines += " */"

        interfaze.addMethod(method)

        return true
    }

    override fun sqlMapDocumentGenerated(document: Document, introspectedTable: IntrospectedTable): Boolean {
        val element = XmlElement("insert")
        element.addAttribute(Attribute("id", "bulkInsert"))
        element.addElement(TextElement("INSERT INTO " + introspectedTable.fullyQualifiedTableNameAtRuntime))
        element.addElement(TextElement("("))

        val forEachElement = XmlElement("foreach")
        forEachElement.addAttribute(Attribute("collection", "dtos"))
        forEachElement.addAttribute(Attribute("item", "dto"))
        forEachElement.addAttribute(Attribute("separator", ","))
        forEachElement.addAttribute(Attribute("open", "("))
        forEachElement.addAttribute(Attribute("close", ")"))

        introspectedTable.allColumns.forEachIndexed { i, column ->
            if (i == introspectedTable.allColumns.size - 1) {
                element.addElement(TextElement(column.actualColumnName))
                forEachElement.addElement(TextElement("#{dto.${column.javaProperty}, jdbcType=${column.jdbcTypeName}}"))
            } else {
                element.addElement(TextElement(column.actualColumnName + ","))
                forEachElement.addElement(TextElement("#{dto.${column.javaProperty}, jdbcType=${column.jdbcTypeName}},"))
            }
        }

        element.addElement(TextElement(") VALUES"))
        element.addElement(forEachElement)

        document.rootElement.addElement(element)

        return true
    }
}
