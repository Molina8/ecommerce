package com.eCommerce.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable{
   
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6391775866281681825L;

	@Id
	private Long id;

	@Column(name="description")
    private String description;

	@Column(name="stock")
    private int stock;
	
	public Product() {
		
	}

	public Product(Long id, String description, int stock) {
		super();
		this.id = id;
		this.description = description;
		this.stock = stock;
	}

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
	public int hashCode() {
		return Objects.hash(stock, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		return stock == other.stock && Objects.equals(description, other.description) && id == other.id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", stock=" + stock + "]";
	}
    
    
    
}
