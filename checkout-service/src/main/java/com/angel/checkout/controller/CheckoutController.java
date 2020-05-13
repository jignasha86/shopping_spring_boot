package com.angel.checkout.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.angel.checkout.model.Cart;
import com.angel.checkout.model.Checkout;
import com.angel.checkout.model.Product;
import com.angel.checkout.repository.CheckoutRepository;
import com.angel.checkout.repository.ProductRepository;
import com.angel.checkout.repository.CartRepository;

@RestController
public class CheckoutController {
	
	@Autowired
    private CheckoutRepository repository;
	
	@Autowired
    private ProductRepository product_repository;
	
	@Autowired
    private CartRepository cart_repository;

    private final static Logger log = LoggerFactory.getLogger(CheckoutController.class);

    @GetMapping("/checkout")
    public String checkout(Principal principal) {
        String username = principal.getName();
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        log.info("claims: " + token.getTokenAttributes());
        Iterable<Cart> items = this.cart_repository.findAll();
        Iterator<Cart> iter = items.iterator();
        int num = 0;
        while(iter.hasNext()) {
        	Cart ct = iter.next();
        	System.out.println(ct.getPids().size());
        	if(ct.getUser_id().equals(username)) {
        		System.out.println(ct.getPids().get(0));
        		List<Product> prod = this.product_repository.findByid(Long.parseLong(ct.getPids().get(0)));
        		Product prod1 = prod.get(0);
        		checkout_func(prod1, username, ct.getId());
        		num = num + 1;
        	}
        }
        if(num == 0) {
           return "Cart empty for "+username;	
        }
        return "Success";
    }
    
    public synchronized void checkout_func(Product prod, String username, Long cart_id) {
        Checkout checkout = new Checkout(prod, username);
        checkout.setStatus("Success");
        
        this.cart_repository.deleteById(cart_id);
        
        this.repository.save(checkout);
        List<Product> item = this.product_repository.findByid(prod.getId());
        Product i1 = item.get(0);
        i1.setQuantity(i1.getQuantity() - 1);
        this.product_repository.save(i1);
    }
}