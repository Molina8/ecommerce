package com.eCommerce.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.common.ApplicationConstants;
import com.eCommerce.dominio.Product;
import com.eCommerce.dominio.DTO.ProductDTO;
import com.eCommerce.dominio.DTO.CartDTO;
import com.eCommerce.dominio.DTO.MessageDTO;
import com.eCommerce.dominio.converter.Converter;
import com.eCommerce.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {


	@Autowired
	private ProductService productService;


	@Autowired
	private Converter conv;

	

	@GetMapping("/getAll")
	public ResponseEntity<List<ProductDTO>> getAll() {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		productService.listAllProduct().stream().forEach(ci -> products.add(conv.ProductToDTo(ci)));
		return ResponseEntity.ok(products);

	}


	@GetMapping("/{id}")
	public ResponseEntity<MessageDTO> getProductById(@PathVariable Long id) {
		Optional<Product> product = productService.findById(id);
		if (!product.isPresent())
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		return ResponseEntity.ok(new MessageDTO(200, conv.ProductToDTo(product.get())));
	}

	@PostMapping("/create")
	public ResponseEntity<MessageDTO> createProduct(@RequestBody(required = true) ProductDTO productoDTO) {
		if(productoDTO.getDescription() == null ||(Object) productoDTO.getStock() == null ||(Object) productoDTO.getId() == null) {
			return ResponseEntity.ok(new MessageDTO(400,ApplicationConstants.ERROR_PARAMETROS));
		}
		Product nuevo = conv.DtoToProduct(productoDTO);
		productService.save(nuevo);
		return ResponseEntity.ok(new MessageDTO(200, productoDTO));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> deleteProduct(@PathVariable Long id) {
		Optional<Product> product = productService.findById(id);
		if (!product.isPresent())
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		productService.deleteProduct(id);
		return ResponseEntity.ok(new MessageDTO(200,ApplicationConstants.PRODUCTO_ELIMINADO_OK));
	}
}
