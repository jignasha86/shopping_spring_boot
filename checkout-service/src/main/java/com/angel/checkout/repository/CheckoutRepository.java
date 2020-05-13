package com.angel.checkout.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.angel.checkout.model.Checkout;

@RepositoryRestResource
@Transactional(readOnly = true)
public interface CheckoutRepository extends CrudRepository<Checkout, Long> {
	
	List<Checkout> findByid(long id);
}