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

    // 상품 수정
    @Modifying
    @Query(value = "UPDATE Product p SET p.prodName = :prodName, p.prodPrice = :prodPrice, p.prodIntro = :prodIntro, p.prodImg = :prodImg, p.prodCat = :prodCat, p.memNum.memNum = :memNum WHERE p.prodNum = :prodNum" , nativeQuery = true)
    void updateProduct(
        @Param("prodNum") String prodNum,
        @Param("prodName") String prodName,
        @Param("prodPrice") int prodPrice,
        @Param("prodIntro") String prodIntro,
        @Param("prodImg") String prodImg,
        @Param("prodCat") int prodCat,
        @Param("memNum") String memNum
    );    

    // main -------------------------------------------------------------------------------------------------------------------------------------------------------

    // main 상품 미리보기 list 출력
    @Query(value = "select * from product order by prod_num desc limit 8", nativeQuery = true)
    public List<Product> productPreview();

    // 상품list 페이지 (category 클릭시 해당 카테고리 해당하는 product만 호출)
    @Query(value = "SELECT * FROM product WHERE prod_cat = :category", nativeQuery = true)
    public List<Product> productAllByCategory(@Param("category") int category);

    // admin -------------------------------------------------------------------------------------------------------------------------------------------------------

    // admin 상품 전체보기
    @Query(value = "select * from product order by prod_num", nativeQuery = true)
    public List<Product> productAll();

    @Modifying
    @Query(value = "delete from product where prod_num = :prodNum", nativeQuery = true)
    public int deleteProd(@Param("prodNum") String prodNum);

    // biz -------------------------------------------------------------------------------------------------------------------------------------------------------

    // biz 상품 전체보기
    @Query(value = "select * from product where mem_num = :memNum order by prod_num", nativeQuery = true)
    public List<Product> productAllBiz(@Param("memNum") String memNum);

    // subscription ----------------------------------------------------------------------------------------------------------------------------------------------
    @Query(value = "select prod_name, prod_price from product where prod_num = :prodNum", nativeQuery = true)
    public List<Object[]> findPayNameAndPayPrice(@Param("prodNum") String prodNum);

    @Query(value = "select prod_name from product p join subscription s on p.prod_num = s.prod_num where s.prod_num = :prodNum and s.sub_num = :subNum", nativeQuery = true)
    public String findProdNameBySubNum(@Param("prodNum") String prodNum, @Param("subNum") String subNum);
}