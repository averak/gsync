package net.averak.gsync.infrastructure.mybatis.plugin

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter

/**
 * MyBatis Generatorで生成されるファイル名をカスタマイズするプラグイン
 */
class RenameGeneratedFilesPlugin : PluginAdapter() {

    override fun validate(warnings: List<String>): Boolean {
        return true
    }

    override fun initialized(introspectedTable: IntrospectedTable) {
        super.initialized(introspectedTable)
        introspectedTable.baseRecordType += "Entity"
        introspectedTable.recordWithBLOBsType += "Entity"

        // 生成されたファイルに直接変更を加えるのを避けるために、生成されるファイルを XxxMapper から XxxBaseMapper に変更する
        // XxxBaseMapper を継承した XxxMapper が手動で作成されるはず
        introspectedTable.myBatis3JavaMapperType = introspectedTable.myBatis3JavaMapperType.replace(
            "Mapper$".toRegex(),
            "BaseMapper",
        )
        introspectedTable.myBatis3XmlMapperFileName = introspectedTable.myBatis3XmlMapperFileName.replace(
            "Mapper\\.xml".toRegex(),
            "BaseMapper.xml",
        )
    }
}
