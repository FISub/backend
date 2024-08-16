package com.woorifisa.backend.product.service;

import java.util.List;

import com.woorifisa.backend.common.dto.ProductDTO;

public interface ProductService {

    String insertProduct(ProductDTO productDTO);

    String updateProduct(String prodNum, ProductDTO productDTO);

	List<ProductDTO> productAll(String memNum);
}
