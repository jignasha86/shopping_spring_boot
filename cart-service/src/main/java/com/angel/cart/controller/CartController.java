package com.angel.cart.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angel.cart.model.Cart;
import com.angel.cart.model.Product;
import com.angel.cart.repository.CartRepository;
import com.angel.cart.repository.ProductRepository;

@RestController
public class CartController {
	
	@Autowired
    private CartRepository repository;
	
	@Autowired
    private ProductRepository product_repository;

    private final static Logger log = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/add_cart")
    public Cart add_cart(Principal principal, @RequestParam(defaultValue="1") long product_id,
            @RequestParam(required = false, defaultValue="1") int quantity) {
        String username = principal.getName();
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        log.info("claims: " + token.getTokenAttributes());
        List<Product> prod = this.product_repository.findByid(product_id);
        System.out.println(prod.get(0));
        Product prod1 = prod.get(0);
        Cart cart = new Cart(prod1, username);
        return this.repository.save(cart);
    }
}