package cybersoft.javabackend.java18.obajuecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Obaju E-commerce")
                        .description("Service for Education Purpose")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Nguyễn Trọng Nghĩa")
                                .email("nguyennghia0479@gmail.com")
                                .url("https://www.linkedin.com/in/trong-nghia-nguyen-b95bb6254/"))
                        .license(new License()
                                .name("NO LICENSE")
                                .url("https://github.com/nguyennghia0479")))
                .externalDocs(new ExternalDocumentation()
                        .url("https://docs.spring.io/spring-framework/docs/current/reference/html/")
                        .description("Spring documentation"));
    }
}
