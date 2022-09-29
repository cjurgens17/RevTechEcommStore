package com.revature.repositories;

import com.revature.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query(value = "UPDATE product SET description = ?1, image = ?2, more_details = ?3, name = ?4, price = ?5, quantity = ?6 WHERE id = ?7", nativeQuery = true)
    int updateProduct(String description, String image, String more_details, String name, double price, int quantity, int id);

    @Query(value = "SELECT * FROM product WHERE name =?1",nativeQuery = true)
    List<Product> getProductByName(String name);




}
