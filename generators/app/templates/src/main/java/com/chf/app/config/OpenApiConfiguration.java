package <%=package%>.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import <%=package%>.config.properties.ConfigProperties;
import <%=package%>.config.properties.ConfigProperties.ApiDocs;
import <%=package%>.constants.SystemConstants;
import <%=package%>.security.jwt.JWTFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

// /swagger-ui/index.html
@EnableOpenApi
@Configuration
@Profile(SystemConstants.PROFILE_DEVELOPMENT)
public class OpenApiConfiguration {

    @Bean
    public Docket apiFirstDocket(ConfigProperties configProperties) {
        ApiDocs swagger = configProperties.getApiDocs();
        ApiInfo apiInfo = new ApiInfoBuilder().title(swagger.getTitle()).description(swagger.getDescription())
                .version(swagger.getVersion()).build();

        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.basePackage(SystemConstants.BASE_PACKAGE)).paths(PathSelectors.any())
                .build().securitySchemes(auth()).securityContexts(securityContexts());
    }

    private List<SecurityScheme> auth() {
        List<SecurityScheme> arrayList = new ArrayList<>();
        arrayList.add(new ApiKey(JWTFilter.AUTHORIZATION_HEADER, JWTFilter.AUTHORIZATION_HEADER, "header"));
        return arrayList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth()).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
