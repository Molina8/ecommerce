package com.eCommerce.services;

import java.util.List;
import java.util.Optional;

import com.eCommerce.dominio.Cart;

public interface CartService {
	List<Cart> listAllCarts();
	Cart save(Cart c);
	Optional<Cart> findById(Long id);
	 void deleteCart(Long id);
	 void deleteCart(Cart c);

}
