package net.averak.gsync.testkit

import groovy.sql.Sql
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import java.util.*
import javax.sql.DataSource

@TestConfiguration
@EnableAutoConfiguration
internal open class TestConfig(
    private val randomizers: List<IRandomizer<Any>>,
) {

    @PostConstruct
    open fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        Faker.init(randomizers)
    }

    @Bean
    open fun dataSource(properties: DataSourceProperties): DataSource {
        return TransactionAwareDataSourceProxy(
            DataSourceBuilder.create()
                .driverClassName(properties.driverClassName)
                .url(properties.url)
                .build(),
        )
    }

    @Bean
    open fun sql(dataSource: DataSource): Sql {
        return Sql(dataSource)
    }
}
