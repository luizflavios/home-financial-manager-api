package br.com.api.core.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        var server = new Server();
        server.setUrl("http://localhost:8080/");
        server.setDescription("Localhost");

        var contact = new Contact();
        contact.setEmail("home-financial-manager@hotmail.com");
        contact.setName("Home Financial Manager");
        contact.setUrl("www.homefinancialmanger.com.br");

        var info = new Info()
                .title("Home Financial Manager")
                .contact(contact)
                .version("1.0")
                .description("Home Financial Manager Api");


        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .servers(singletonList(server));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
