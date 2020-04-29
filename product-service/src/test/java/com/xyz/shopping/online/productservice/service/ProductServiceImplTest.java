package com.xyz.shopping.online.productservice.service;

import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    private Product product;

    @BeforeEach
    public void setUp(){
        product = new Product();
        product.setId(1l);
        product.setName("abc");
        product.setDescription("abc");
        product.setBranch("mumbai");
        product.setPrice(3.20);
        product.setManufacturerName("abc");
        product.setRating(2);
    }

    @Test
    public void createProduct(){
        when(productRepository.save(product)).thenReturn(product);
        Product product1 = productService.createProduct(product);
        assertEquals(product, product1);
    }

    @Test
    public void getProductDetails(){
        Optional<Product> optProduct = Optional.of(product);
        when(productRepository.findById(1l)).thenReturn(optProduct);
        Optional<Product> optProduct1 = productService.getProductDetails(1l);
        assertEquals(optProduct.get(), optProduct1.get());
    }

    @Test
    public void searchProducts(){
        String searchCriteria = "name:abc";
        Pageable pageable = PageRequest.of(1, 10);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Page<Product> products = new PageImpl<>(productList);
        when(productRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(products);
        Page<Product> products1 = productService.searchProducts(searchCriteria, pageable);
        assertEquals(products, products1);
    }


}
