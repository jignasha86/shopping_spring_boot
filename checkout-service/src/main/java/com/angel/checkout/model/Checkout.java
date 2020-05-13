package com.angel.checkout.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.angel.checkout.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class Checkout {

	public Checkout() {
    }
	
    public Checkout(Product product, String user_id) {
    	this.products.add(product);
        this.user_id = user_id;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	@Id
    @GeneratedValue
    private Long id;
    
    @OneToMany(targetEntity=Product.class, mappedBy="cart", fetch=FetchType.EAGER)
    private List<Product> products = new ArrayList<Product>();
    
    @NonNull
    private String user_id;
    
    @NonNull
    private String status;
    
    @NonNull
    private Integer quantity;
    
    @NonNull
    private String payment_id;
    
}