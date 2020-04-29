package com.xyz.shopping.online.productservice.controller;

import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

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
        productRepository.save(product);

        mockMvc.perform(get("/product/search").queryParam("search", "name:abc")
                .queryParam("page", "0").queryParam("size", "20")
                .queryParam("userId", "def")
        ).andExpect(status().isOk()).andExpect(jsonPath("$.content[0].name", is(product.getName())));

    }

    @Test
    public void getProductDetails() throws Exception {
        productRepository.save(product);

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

        mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(product.getName())));
    }
}
