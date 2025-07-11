package com.perfulandia.usuarioservice.config;

import io.swagger.v3.oas.models.*;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration


public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("👑👑👑 API Perfulandia 👑👑👑")
                        .version("1.0.0")
                        .description("Documento de la API para la tienda de perfumes Perfulandia ubicada en Puerto Montt")
                        .contact(new Contact()
                                .name("Bastian Delgado")
                                .email("ba.delgados@duoc.cl")
                                .url("https://www.duoc.cl")
                        ));
    }




}