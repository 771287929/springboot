package com.artbrain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private Date availableFrom;
	private BigDecimal price;
	private String description;
	
	public Product(Date availableFrom, BigDecimal price, String description) {
		super();
		this.availableFrom = availableFrom;
		this.price = price;
		this.description = description;
	}
	public Date getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
