package com.angel.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfiguration {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Add Cart Service")
				.description(
						"REST API for centalized documentation using Spring Boot and spring-fox swagger 2 ")
				.termsOfServiceUrl("").version("0.0.1-SNAPSHOT").contact(new Contact("Jignasha", "https://github.com/jignasha86", "https://github.com/jignasha86")).build();
	}

	@Bean
	public Docket configureControllerPackageAndConvertors() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.angel.cart")).build()
	                .apiInfo(apiInfo());
	}


}