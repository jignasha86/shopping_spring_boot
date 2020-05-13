package com.angel.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.angel.productservice.model.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByid(int id);
}