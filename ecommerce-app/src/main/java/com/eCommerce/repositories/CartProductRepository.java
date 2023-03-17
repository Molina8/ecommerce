package com.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.dominio.CartProduct;
import com.eCommerce.dominio.pk.CartProductId;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {

}
