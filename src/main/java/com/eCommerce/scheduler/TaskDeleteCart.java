package com.eCommerce.scheduler;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eCommerce.dominio.Cart;
import com.eCommerce.dominio.Product;
import com.eCommerce.services.CartService;
import com.eCommerce.services.ProductService;

@Component
public class TaskDeleteCart {
	

    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductService productService;

  
    @Scheduled(fixedDelay = 600000) //10 minutos * 60 segundos * 1000 ms
    public void deleteInactiveCarts() {
    	List<Cart> copia = new ArrayList<>(cartService.listAllCarts());
    	Iterator<Cart> iterator = copia.iterator();
    	while (iterator.hasNext()) {
    	    Cart cart = iterator.next();

        	if (Duration.between(cart.getLastActivity().toInstant(), Instant.now(Clock.systemDefaultZone())).toMinutes() >= 10) {
        		cart.getCartProducts().stream()
        	    .forEach(cartProduct -> {
        	        Product product = cartProduct.getProduct(); //Devolver al stock la cantidad del producto que se habia añadido al carro
        	        int currentStock = product.getStock();
        	        int quantityToAdd = cartProduct.getQuantity();
        	        int updatedStock = currentStock + quantityToAdd;
        	        product.setStock(updatedStock);
        	        productService.save(product);
        	    });
        		cartService.deleteCart(cart);
                iterator.remove();
            }
        }
    }
}