package com.eCommerce.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.eCommerce.dominio.CartProduct;
import com.eCommerce.dominio.pk.CartProductId;
import com.eCommerce.repositories.CartProductRepository;
import com.eCommerce.services.CartProductService;

@Service
@Transactional
public class CartProductServiceImpl implements CartProductService{
	@Autowired
	private CartProductRepository cartProductRepository;
	

	@Override
	public List<CartProduct> listAllCartProducts() {
		return cartProductRepository.findAll();
	}

	@Override
	public CartProduct save(CartProduct c) {
		return cartProductRepository.save(c);
	}

	@Override
	public Optional<CartProduct> findById(CartProductId id) {
		return cartProductRepository.findById(id);
	}

	@Override
	public void deleteCartProduct(CartProductId id) {
		cartProductRepository.deleteById(id);
		
	}

	@Override
	public void deleteCartProduct(CartProduct c) {
		cartProductRepository.delete(c);
		
	}
}
