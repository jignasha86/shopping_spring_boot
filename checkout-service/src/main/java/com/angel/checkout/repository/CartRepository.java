package com.angel.checkout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.angel.checkout.model.Cart;

@RepositoryRestResource
@Transactional(readOnly = true)
public interface CartRepository extends CrudRepository<Cart, Long> {
	
	List<Cart> findByid(long id);
}