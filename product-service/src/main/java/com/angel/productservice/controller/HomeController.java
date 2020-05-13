package com.angel.productservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.angel.productservice.model.Product;
import com.angel.productservice.repository.ProductRepository;

import java.security.Principal;

@RestController
public class HomeController {

    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductRepository repository;
    
    @GetMapping("/")
	public String index() {
		return "redirect:swagger-ui.html";
	}
    
    @GetMapping("/home")
    public String products(Principal principal) {
        String username = principal.getName();
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        log.info("claims: " + token.getTokenAttributes());
        return "Hello, " + username;
    }
    
    @GetMapping("/products")
    public @ResponseBody Iterable<Product> products_details(Principal principal) {
    	log.info("Get Products called");
        return this.repository.findAll();
    }
}