package com.angel.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableDiscoveryClient
@SpringBootApplication
public class AddCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddCartApplication.class, args);
	}
	
	@Configuration
    static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .authorizeRequests().
                antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/**").permitAll().
                anyRequest().authenticated()
                    .and()
                .oauth2ResourceServer().jwt();
            // @formatter:on
        }
    }

}
