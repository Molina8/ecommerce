package com.eCommerce.dominio.converter;

import org.springframework.stereotype.Component;

import com.eCommerce.dominio.Cart;
import com.eCommerce.dominio.Product;
import com.eCommerce.dominio.DTO.CartDTO;
import com.eCommerce.dominio.DTO.ProductDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;

@Component
public class Converter implements IConverter{
private ModelMapper mapper = new ModelMapper();
	
	

	@Override
	public CartDTO ctoCDTO(Cart carro) {
		CartDTO dto = mapper.typeMap(Cart.class, CartDTO.class)
				.map(carro);
		
		return dto;

	}
	
	@Override
	public Cart CartDTOtoCart(CartDTO dto) {
		Cart cart = mapper.typeMap(CartDTO.class, Cart.class).map(dto);
		return cart;
	}

	@Override
	public Product DtoToProduct(ProductDTO dto) {
		Product product = mapper.typeMap(ProductDTO.class, Product.class).map(dto);
		return product;
	}

	@Override
	public ProductDTO ProductToDTo(Product product) {
		ProductDTO dto = mapper.typeMap(Product.class, ProductDTO.class).map(product);
		return dto;
	}
	
	
	
}
