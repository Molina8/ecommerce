package com.eCommerce.services;

import java.util.List;
import java.util.Optional;

import com.eCommerce.dominio.Product;

public interface ProductService {
	
	List<Product> listAllProduct();

	Product save(Product c);

	Optional<Product> findById(Long id);

	void deleteProduct(Long id);

	void deleteProduct(Product c);
}
