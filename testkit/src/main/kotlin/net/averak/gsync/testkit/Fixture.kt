package net.averak.gsync.testkit

import com.google.common.base.CaseFormat
import groovy.sql.Sql

class Fixture {

    companion object {

        private lateinit var sql: Sql

        /**
         * 初期化する
         * テストのエントリーポイントから必ず呼び出すこと
         */
        @JvmStatic
        fun init(sql: Sql) {
            Fixture.sql = sql
        }

        /**
         * テストフィクスチャをセットアップする
         */
        @JvmStatic
        fun <T> setup(entity: T): T {
            require(entity != null) {
                "entity must not be null"
            }

            sql.dataSet(extractTableName(entity)).add(extractColumns(entity))
            return entity
        }

        /**
         * テストフィクスチャをセットアップする
         */
        @JvmStatic
        fun <T> setup(vararg entities: T): List<T> {
            entities.forEach { setup(it) }
            return entities.toList()
        }

        private fun extractTableName(entity: Any): String {
            val tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entity.javaClass.simpleName).replace("_entity", "")
            return "`$tableName`"
        }

        private fun extractColumns(entity: Any): Map<String, Any> {
            val result = LinkedHashMap<String, Any>()
            entity.javaClass.declaredFields.forEach {
                val columnName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, it.name)
                it.isAccessible = true
                result["`$columnName`"] = it[entity]
                it.isAccessible = false
            }
            return result
        }
    }
}
