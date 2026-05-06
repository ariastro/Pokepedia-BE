package dev.sws.sample_pokedex.core.config

import dev.sws.sample_pokedex.core.security.ApiKeyInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val apiKeyInterceptor: ApiKeyInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiKeyInterceptor)
            .addPathPatterns("/api/**")
    }
}