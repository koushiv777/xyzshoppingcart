package com.xyz.shopping.online.productservice.controller;

import com.xyz.shopping.online.productservice.dto.ProductDetailsLog;
import com.xyz.shopping.online.productservice.dto.ProductSearchLog;
import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.service.ProductAnalyticsService;
import com.xyz.shopping.online.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductAnalyticsService productAnalyticsService;

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
    public void searchProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Page<Product> products = new PageImpl<>(productList);
        when(productService.searchProducts(anyString(), any(Pageable.class))).thenReturn(products);
        when(productAnalyticsService.logProductSearch(any(ProductSearchLog.class))).thenReturn(null);
        mockMvc.perform(get("/product/search").queryParam("search", "name:abc")
                .queryParam("page", "0").queryParam("size", "20")
                .queryParam("userId","def")
                ).andExpect(status().isOk()).andExpect(jsonPath("$.content[0].name", is(product.getName())));

    }

    @Test
    public void getProductDetails() throws Exception {
        Optional<Product> optProduct = Optional.of(product);
        when(productService.getProductDetails(anyLong())).thenReturn(optProduct);
        when(productAnalyticsService.logProductDetails(any(ProductDetailsLog.class))).thenReturn(null);
        mockMvc.perform(get("/product/1")
                .queryParam("userId", "def"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product.getName())));
    }

    @Test
    public void createProduct() throws Exception {
        String productJson = "{\n" +
                "  \"name\": \"abc\",\n" +
                "  \"description\": \"abc\",\n" +
                "  \"price\": 3.32,\n" +
                "  \"color\": \"red\",\n" +
                "  \"branch\": \"mumbai\",\n" +
                "  \"manufacturerName\": \"abc\",\n" +
                "  \"rating\": 2\n" +
                "}";
        when(productService.createProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(product.getName())));
    }



}
