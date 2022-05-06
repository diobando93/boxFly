package com.boxFly.boxFly.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boxFly.boxFly.models.Products;
import com.boxFly.boxFly.services.ProductServices;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductServices productService;
	
	@PostMapping()
	public ResponseEntity createProduct(@RequestBody Products product) {
		
		if(product != null) {
			
			productService.createProduct(product);
			
			return (ResponseEntity.status(HttpStatus.OK))
					.body(product);
		}else {
			
			return (ResponseEntity.status(HttpStatus.BAD_REQUEST))
					.body("Falta producto");
		}
		
	}
	
	@GetMapping()
	public ResponseEntity readProducts() {
		
		ArrayList<Products> products;
		products = productService.readProducts();
		
		if(products.size() == 0) {
			return (ResponseEntity.status(HttpStatus.NO_CONTENT)).
					body(products);
		}else {
			return (ResponseEntity.status(HttpStatus.OK)).
					body(products);
		}
		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity updateProduct(@PathVariable("id") Long id, @RequestBody Products productFind) {
		
		Products product;
		product = productService.findByIdProduct(id);
		
		if(product != null) {
			
			if(productFind.getName() != null) {
				
				product.setName(productFind.getName());
				productService.createProduct(product);
				
			}
			return (ResponseEntity.status(HttpStatus.OK))
					.body("actualizado");
		}else {
			return (ResponseEntity.status(HttpStatus.OK))
					.body("No existe el producto");
		}
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
		
		boolean ok = productService.deleteProduct(id);
		if(ok == true) {
			return (ResponseEntity.status(HttpStatus.OK)).
					body("Producto eliminado");
		}else {
			return (ResponseEntity.status(HttpStatus.NO_CONTENT)).
					body("No existen el producto");
		}
	}
	

}
