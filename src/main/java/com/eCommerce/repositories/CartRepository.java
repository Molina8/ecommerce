package com.eCommerce.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eCommerce.dominio.*;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
}
