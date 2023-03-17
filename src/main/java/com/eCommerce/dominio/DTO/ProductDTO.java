package com.eCommerce.dominio.DTO;

import javax.persistence.Id;

public class ProductDTO {
	
	private Long id;

    private String description;

    private int stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", description=" + description + ", stock=" + stock + "]";
	}
    
    
}
