package net.averak.gsync.adapter.handler.rest.config

import net.averak.gsync.adapter.handler.rest.interceptor.IRequestInterceptor
import net.averak.gsync.adapter.handler.rest.interceptor.InterceptorPriority
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebMvcConfig(
    private val interceptors: List<IRequestInterceptor>,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        interceptors.forEach {
            when (it.getPriority()) {
                InterceptorPriority.HIGH -> registry.addInterceptor(it).order(0)
                InterceptorPriority.MEDIUM -> registry.addInterceptor(it).order(1)
                InterceptorPriority.LOW -> registry.addInterceptor(it).order(2)
            }
        }
    }
}
