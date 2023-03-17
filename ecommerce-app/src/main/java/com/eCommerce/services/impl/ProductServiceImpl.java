package com.eCommerce.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.dominio.Product;
import com.eCommerce.repositories.ProductRepository;
import com.eCommerce.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

}
