package com.github.progtechteam.socialnetwork.api.config;

import com.github.progtechteam.socialnetwork.commons.Url;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

/**
 * Class for configuring <a href="https://springdoc.org/">OpenAPI</a>.
 *
 * @author Evgenii Puliaev
 */
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dovecote API")
                        .description("Social network application")
                        .version("v0.0.1")
                        .license(new License().name("MIT License").url("https://github.com/ProgTechTeam/SocialNetwork/blob/master/LICENSE.md")))
                .externalDocs(new ExternalDocumentation()
                        .description("Dovecote Wiki Documentation")
                        .url("https://github.com/ProgTechTeam/SocialNetwork/wiki"));
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("social-network-auth")
                .pathsToMatch(Url.AUTH + Url.WILDCARD)
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("social-network-admin")
                .pathsToMatch(Url.ADMIN + Url.WILDCARD)
                .build();
    }

    @Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder()
                .group("social-network-test")
                .pathsToMatch("/test")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("social-network-public")
                .pathsToMatch(Url.PUBLIC + Url.WILDCARD)
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("social-network-user")
                .pathsToExclude("/test", Url.PUBLIC + Url.WILDCARD, Url.ADMIN + Url.WILDCARD, Url.AUTH + Url.WILDCARD)
                .build();
    }

}
