package com.eCommerce.services;

import java.util.List;
import java.util.Optional;

import com.eCommerce.dominio.CartProduct;
import com.eCommerce.dominio.pk.CartProductId;

public interface CartProductService {

	List<CartProduct> listAllCartProducts();
	CartProduct save(CartProduct c);
	Optional<CartProduct> findById(CartProductId id);
	 void deleteCartProduct(CartProductId id);
	 void deleteCartProduct(CartProduct c);
}
