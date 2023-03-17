package com.eCommerce.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.dominio.Cart;
import com.eCommerce.repositories.CartRepository;
import com.eCommerce.services.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> listAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart save(Cart c) {
		return cartRepository.save(c);
	}

	@Override
	public Optional<Cart> findById(Long id) {
		return cartRepository.findById(id);
	}

	@Override
	public void deleteCart(Long id) {
		cartRepository.deleteById(id);

	}

	@Override
	public void deleteCart(Cart c) {
		cartRepository.delete(c);

	}

}
