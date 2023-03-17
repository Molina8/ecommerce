package com.eCommerce.dominio.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartProductId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1944900975286782408L;


	@Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

    
    public CartProductId() {
    	
    }
    
	public CartProductId(Long cartId, Long productId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
	}
	
	
	

	public Long getCartId() {
		return cartId;
	}




	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}




	public Long getProductId() {
		return productId;
	}




	public void setProductId(Long productId) {
		this.productId = productId;
	}




	@Override
	public int hashCode() {
		return Objects.hash(cartId, productId);
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
		CartProductId other = (CartProductId) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(productId, other.productId);
	}

    
    
}
