package com.boxFly.boxFly.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.boxFly.boxFly.models.Products;

public interface ProductsRepo extends JpaRepository<Products, Long>{
	

}
