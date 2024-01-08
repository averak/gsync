package net.averak.gsync

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.context.annotation.ComponentScan
import java.util.*

@SpringBootApplication
@ComponentScan(basePackages = ["net.averak.gsync"], nameGenerator = Entrypoint.FQCNBeanNameGenerator::class)
open class Entrypoint {

    class FQCNBeanNameGenerator : AnnotationBeanNameGenerator() {

        override fun buildDefaultBeanName(definition: BeanDefinition): String {
            return definition.beanClassName!!
        }
    }
}

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    runApplication<Entrypoint>(*args)
}
