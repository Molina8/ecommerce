package com.eCommerce.dominio.DTO;

import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.eCommerce.dominio.CartProduct;

public class CartDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6870971406418840539L;
	private Long id;
	private Date creationDate;
	private Date lastActivity;
	private List<CartProduct> cartProducts;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	@Override
	public String toString() {
		return "CartDTO [id=" + id + ", creationDate=" + creationDate + ", lastActivity=" + lastActivity
				+ ", cartProducts=" + cartProducts + "]";
	}

	


}
