package com.Product.product.repository;

import com.Product.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT * FROM product WHERE id = ?1", nativeQuery = true)
    Product getProductById(Integer productId);

}
