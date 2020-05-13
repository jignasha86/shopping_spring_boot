package com.angel.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.angel.cart.model.Cart;

@RepositoryRestResource
public interface CartRepository extends CrudRepository<Cart, Long> {
	
	List<Cart> findByid(int id);
}