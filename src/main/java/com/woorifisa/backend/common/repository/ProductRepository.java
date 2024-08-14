package com.woorifisa.backend.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woorifisa.backend.common.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // 상품 추가
    @Modifying
    @Query(value = "INSERT INTO product (prod_name, prod_price, prod_intro, prod_img, prod_cat, mem_num) VALUES (:prodName, :prodPrice, :prodIntro, :prodImg, :prodCat,:memNum)", nativeQuery = true)
    public void insertProduct(
        @Param("prodName") String prodName,
        @Param("prodPrice") int prodPrice,
        @Param("prodIntro") String prodIntro,
        @Param("prodImg") String prodImg,
        @Param("prodCat") int prodCat,
        @Param("memNum") String memNum
    );
    // main 상품 미리보기 list 출력
    @Modifying
    @Query(value= "select * from product order by prod_num desc limit 8", nativeQuery = true)
    public List<Product> preview();
    // 상품 수정
    @Modifying
    @Query("UPDATE Product p SET p.prodName = :prodName, p.prodPrice = :prodPrice, p.prodIntro = :prodIntro, p.prodImg = :prodImg, p.prodCat = :prodCat, p.memNum.memNum = :memNum WHERE p.prodNum = :prodNum")
    void updateProduct(
        @Param("prodNum") String prodNum,
        @Param("prodName") String prodName,
        @Param("prodPrice") int prodPrice,
        @Param("prodIntro") String prodIntro,
        @Param("prodImg") String prodImg,
        @Param("prodCat") int prodCat,
        @Param("memNum") String memNum
    );
    
}
