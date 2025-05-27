package com.oneplace.dgapiserver.global.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("DGDG")
                    .description("")
                    .version("1.0.0")
            )
            .addSecurityItem(SecurityRequirement().addList("Authorization"))
            .components(
                Components()
                    .addSecuritySchemes(
                        "Authorization",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("Authorization")
                            .`in`(SecurityScheme.In.HEADER)
                            .name("Authorization")
                    )
            )
    }
}