package net.averak.gsync.infrastructure.json

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import java.time.format.DateTimeFormatter

@Configuration
open class JsonConfig {

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    open fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        // LocalDate, LocalDateTime に対して application.yml の設定は適応されないので、ここでシリアライザを定義する必要がある
        return Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder.serializers(
                LocalDateSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                ),
                LocalDateTimeSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"),
                ),
            )
        }
    }
}
