package com.harsh.ecommerce.repository;

import com.harsh.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,Long> {

}
