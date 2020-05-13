package com.angel.cart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.angel.cart.model.Product;

@RepositoryRestResource
@Transactional(readOnly = true)
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByid(long id);	
}