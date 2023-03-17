package com.eCommerce.controllers;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.common.ApplicationConstants;
import com.eCommerce.dominio.Cart;
import com.eCommerce.dominio.CartProduct;
import com.eCommerce.dominio.Product;
import com.eCommerce.dominio.DTO.CartDTO;
import com.eCommerce.dominio.DTO.MessageDTO;
import com.eCommerce.dominio.converter.*;
import com.eCommerce.dominio.pk.CartProductId;
import com.eCommerce.services.CartProductService;
import com.eCommerce.services.CartService;
import com.eCommerce.services.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartProductService cartProductService;

	@Autowired
	private Converter conv;

	@GetMapping("/getAll")
	public ResponseEntity<List<CartDTO>> getAll() {
		List<CartDTO> carts = new ArrayList<CartDTO>();
		cartService.listAllCarts().stream().forEach(ci -> carts.add(conv.ctoCDTO(ci)));
		return ResponseEntity.ok(carts);

	}

	@GetMapping("/{id}")
	public ResponseEntity<MessageDTO> getCartById(@PathVariable Long id) {
		Optional<Cart> carro = cartService.findById(id);
		if (!carro.isPresent())
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		return ResponseEntity.ok(new MessageDTO(200, conv.ctoCDTO(carro.get())));
	}

	@PostMapping("/create")
	public ResponseEntity<MessageDTO> createCart() {
		Cart nuevo = new Cart();
		nuevo.setCartProducts(new LinkedList<CartProduct>());
		nuevo.setCreationDate(new Date());
		nuevo.setLastActivity(new Date());
		CartDTO respuesta = conv.ctoCDTO(cartService.save(nuevo));
		return ResponseEntity.ok(new MessageDTO(200, respuesta));
	}

	/**
	 * 
	 * Endpoint para agregar un producto al carrito de compras.
	 * 
	 * @param params Mapa que contiene los parametros del request: 
	 * "cartId" (Long): el ID del carrito al cual se desea agregar el producto.
	 * "productId" (Long): el ID del producto a agregar. "cantidad"
	 * "cantidad" (int): la cantidad del producto a agregar.
	 * @return ResponseEntity<MessageDTO>: una respuesta HTTP con un objeto
	 */
	@PutMapping("/add-product/")
	public ResponseEntity<MessageDTO> addProductToCart(@RequestBody Map<String, Object> params) {
		Long cartId = Long.parseLong(params.get("cartId").toString());
		Long productId = Long.parseLong(params.get("productId").toString());
		int cantidad = Integer.parseInt(params.get("cantidad").toString());
		Optional<Cart> cart = cartService.findById(cartId);
		if (!cart.isPresent()) {
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		}
		Optional<Product> product = productService.findById(productId);
		if (!product.isPresent()) {
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		}

		if (cantidad > product.get().getStock()) {
			return ResponseEntity.ok(new MessageDTO(200, ApplicationConstants.NO_STOCK));
		}

		Optional<CartProduct> cartProduct = cartProductService
				.findById(new CartProductId(cart.get().getId(), product.get().getId()));
		if (!cartProduct.isPresent()) {
			CartProduct nuevo = new CartProduct(new CartProductId(cart.get().getId(), product.get().getId()),
					cart.get(), product.get(), cantidad);
			cart.get().getCartProducts().add(nuevo);
			cartProductService.save(nuevo);

		} else {
			int cantidadAnterior = cartProduct.get().getQuantity();

			cart.get().getCartProducts().stream().filter(cp -> cp.getProduct().getId().equals(productId)).findFirst()
					.ifPresent(cp -> {
						cp.setQuantity(cantidad + cantidadAnterior);
						cartProductService.save(cp);
					});

		}
		cart.get().setLastActivity(new Date());
		product.get().setStock(product.get().getStock() - cantidad);
		productService.save(product.get());
		cartService.save(cart.get());
		return ResponseEntity.ok(new MessageDTO(200, conv.ctoCDTO(cart.get())));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> deleteCart(@PathVariable Long id) {
		Optional<Cart> cart = cartService.findById(id);
		if (!cart.isPresent()) {
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		}
		cartService.deleteCart(id);
		return ResponseEntity.ok(new MessageDTO(200, ApplicationConstants.CARRO_ELIMINADO_OK));
	}

	/**
	 * 
	 * A function that adds multiple products to a cart, given a cartId and a list
	 * of products with their respective ids and quantities.
	 * 
	 * @param body A Map<String, Object> containing the request body, with the
	 *             cartId and products to add.
	 * @return A ResponseEntity with a MessageDTO indicating if the operation was
	 *         successful or if an error occurred.
	 */
	@SuppressWarnings("unchecked")
	@PutMapping("/add-products/")
	public ResponseEntity<MessageDTO> addProductsToCart(@RequestBody Map<String, Object> body) {
		Long cartId = Long.parseLong(body.get("cartId").toString());
		List<Map<String, Object>> products = (List<Map<String, Object>>) body.get("products");

		Optional<Cart> cart = cartService.findById(cartId);
		if (!cart.isPresent()) {
			return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
		}

		for (Map<String, Object> productParams : products) {
			Long productId = Long.parseLong(productParams.get("productId").toString());
			int cantidad = Integer.parseInt(productParams.get("cantidad").toString());

			Optional<Product> product = productService.findById(productId);
			if (!product.isPresent()) {
				return ResponseEntity.ok(new MessageDTO(404, ApplicationConstants.ERROR_404));
			}

			if (cantidad > product.get().getStock()) {
				return ResponseEntity.ok(new MessageDTO(200, ApplicationConstants.NO_STOCK));
			}

			Optional<CartProduct> cartProduct = cartProductService
					.findById(new CartProductId(cart.get().getId(), product.get().getId()));
			if (!cartProduct.isPresent()) {
				CartProduct nuevo = new CartProduct(new CartProductId(cart.get().getId(), product.get().getId()),
						cart.get(), product.get(), cantidad);
				cart.get().getCartProducts().add(nuevo);
				cartProductService.save(nuevo);
			} else {
				int cantidadAnterior = cartProduct.get().getQuantity();

				cart.get().getCartProducts().stream().filter(cp -> cp.getProduct().getId().equals(productId))
						.findFirst().ifPresent(cp -> {
							cp.setQuantity(cantidad + cantidadAnterior);
							cartProductService.save(cp);
						});
			}

			cart.get().setLastActivity(new Date());
			product.get().setStock(product.get().getStock() - cantidad);
			productService.save(product.get());
		}

		cartService.save(cart.get());
		return ResponseEntity.ok(new MessageDTO(200, conv.ctoCDTO(cart.get())));
	}

}
