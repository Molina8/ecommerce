package com.eCommerce.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cart")
public class Cart implements Serializable{
	
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -3303182126535389561L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "last_activity", nullable = false)
    private Date lastActivity;

    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("cartProducts")
    @JsonManagedReference
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
	public int hashCode() {
		return Objects.hash(cartProducts, creationDate, id, lastActivity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cart)) {
			return false;
		}
		Cart other = (Cart) obj;
		return Objects.equals(cartProducts, other.cartProducts) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(id, other.id) && Objects.equals(lastActivity, other.lastActivity);
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", creationDate=" + creationDate + ", lastActivity=" + lastActivity
				+ ", cartProducts=" + cartProducts + "]";
	}

    
}




