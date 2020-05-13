package com.angel.checkout.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.angel.checkout.model.StringListConverter;
import com.angel.checkout.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class Cart {
	
	public Cart() {
    }

    public Cart(Product product, String user_id) {
        this.products.add(product);
        this.user_id = user_id;
        this.pids.add(product.getId().toString());
    }

    @Id
    @GeneratedValue
    private Long id;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return this.products;
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

	@OneToMany(targetEntity=Product.class, mappedBy="cart", fetch=FetchType.EAGER)
    private List<Product> products = new ArrayList<Product>();
	
	@Convert(converter = StringListConverter.class)
    private List<String> pids = new ArrayList<String>();
    
    public List<String> getPids() {
		return pids;
	}

	public void setPids(List<String> pids) {
		this.pids = pids;
	}

	@NonNull
    private String user_id;
}