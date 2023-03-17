package com.eCommerce.dominio.converter;

import com.eCommerce.dominio.Cart;
import com.eCommerce.dominio.Product;
import com.eCommerce.dominio.DTO.CartDTO;
import com.eCommerce.dominio.DTO.ProductDTO;

public interface IConverter {

	CartDTO ctoCDTO(Cart carro);

	Cart CartDTOtoCart(CartDTO dto);

	Product DtoToProduct(ProductDTO dto);
	
	ProductDTO ProductToDTo(Product product);
}
