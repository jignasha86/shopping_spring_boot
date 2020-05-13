package com.angel.productservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;
import com.angel.productservice.model.Product;
import com.angel.productservice.repository.ProductRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

	@Autowired
    private ProductRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Configuration
    static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
        	http.antMatcher("/v2/api-docs").anonymous();
        	
            http
                .authorizeRequests().
                antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/**").permitAll().                
                anyRequest().                
                authenticated()
                    .and()
                .oauth2ResourceServer().jwt();
            // @formatter:on
        }
    }

    @Bean
    ApplicationRunner init(ProductRepository repository) {
        return args -> {
            Stream.of("Product123").forEach(name -> {
                Product product = new Product(name);
                product.setQuantity(1000);
                repository.save(product);
            });
            repository.findAll().forEach(System.out::println);
        };
    }
}