package com.eCommerce.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.eCommerce.dominio.pk.CartProductId;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cart_product")
public class CartProduct implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3971681390993943559L;

	@EmbeddedId
    private CartProductId id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
    
    

	public CartProduct(CartProductId id, Cart cart, Product product, Integer quantity) {
		super();
		this.id = id;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}
	
	public CartProduct() {
		
	}
	

	public CartProductId getId() {
		return id;
	}




	public void setId(CartProductId id) {
		this.id = id;
	}




	public Cart getCart() {
		return cart;
	}




	public void setCart(Cart cart) {
		this.cart = cart;
	}




	public Product getProduct() {
		return product;
	}




	public void setProduct(Product product) {
		this.product = product;
	}




	public Integer getQuantity() {
		return quantity;
	}




	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}




	@Override
	public int hashCode() {
		return Objects.hash(cart, id, product, quantity);
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
		CartProduct other = (CartProduct) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(id, other.id)
				&& Objects.equals(product, other.product) && Objects.equals(quantity, other.quantity);
	}




	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", cart=" + cart + ", product=" + product + ", quantity=" + quantity + "]";
	}

   
}
