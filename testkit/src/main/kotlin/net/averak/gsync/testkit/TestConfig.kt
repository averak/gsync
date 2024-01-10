package net.averak.gsync.testkit

import groovy.sql.Sql
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
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

    @Bean
    open fun mockMvc(webApplicationContext: WebApplicationContext): MockMvc {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }
}
