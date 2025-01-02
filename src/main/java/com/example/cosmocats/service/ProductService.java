package com.example.cosmocats.service;

import com.example.cosmocats.dto.ProductDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService {

     ProductDTO createProduct(ProductDTO productDTO);
     List<ProductDTO> getAllProducts();
     ProductDTO getProductById(Long id);
     ProductDTO updateProduct(Long id, ProductDTO productDTO) ;
     boolean deleteProduct(Long id);
}
