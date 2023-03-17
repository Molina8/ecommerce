package com.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.dominio.Cart;
import com.eCommerce.dominio.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
