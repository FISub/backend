package com.woorifisa.backend.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woorifisa.backend.common.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Modifying
    @Query(value = "INSERT INTO product (prod_name, prod_price, prod_intro, prod_img, mem_num) VALUES (:prodName, :prodPrice, :prodIntro, :prodImg, :memNum)", nativeQuery = true)
    void insertProduct(
        @Param("prodName") String prodName,
        @Param("prodPrice") int prodPrice,
        @Param("prodIntro") String prodIntro,
        @Param("prodImg") String prodImg,
        @Param("memNum") String memNum
    );
    
}
