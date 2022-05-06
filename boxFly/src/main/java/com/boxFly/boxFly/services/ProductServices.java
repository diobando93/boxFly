package com.boxFly.boxFly.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxFly.boxFly.models.Products;
import com.boxFly.boxFly.repositories.ProductsRepo;


@Service
public class ProductServices {
	
	@Autowired
	ProductsRepo productsRepo;
	
	//Create a new product.
	public Products createProduct(Products product) {
		
		return productsRepo.save(product);
	}
	
	//Read all the products in the DB.
	public ArrayList<Products> readProducts(){
		
		return (ArrayList<Products>) productsRepo.findAll();
	}
	
	//Read product by id
	public Products findByIdProduct(Long id) {
		
		return productsRepo.getById(id);
	}
	
	//delete products
	
	public boolean deleteProduct(Long id) {
		
		try{
			
			Products product;
			product = productsRepo.getById(id);
			productsRepo.delete(product);
			return true;
		}catch(Exception err) {
			
			return false;
		}
		
		
	}
	

}
