package com.xyz.shopping.online.analyticsservice.controller;

import com.xyz.shopping.online.analyticsservice.entity.ProductDetailsLog;
import com.xyz.shopping.online.analyticsservice.entity.ProductSearchLog;
import com.xyz.shopping.online.analyticsservice.service.ProductAnalyticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductAnalyticsController.class)
public class ProductAnalyticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductAnalyticsService productAnalyticsService;

    private ProductDetailsLog productDetailsLog;

    private ProductSearchLog productSearchLog;

    @BeforeEach
    public void setUp(){
        productDetailsLog = new ProductDetailsLog();
        productDetailsLog.setProductId(1l);
        productDetailsLog.setProductName("abc");
        productDetailsLog.setUserId("def");

        productSearchLog = new ProductSearchLog();
        productSearchLog.setUserId("def");
        productSearchLog.setSearchText("name:abc");
    }

    @Test
    public void logProductDetails() throws Exception {
        String productDetailsLogJson = "{\n" +
                "  \"userId\": \"def\",\n" +
                "  \"productId\": 1,\n" +
                "  \"productName\": \"abc\"\n" +
                "}";

        when(productAnalyticsService.logProductDetails(any(ProductDetailsLog.class))).thenReturn(productDetailsLog);

        mockMvc.perform(post("/analytics/log/productDetails")
            .contentType(MediaType.APPLICATION_JSON)
            .content(productDetailsLogJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is(productDetailsLog.getProductName())));

    }

    @Test
    public void logProductSearch() throws Exception {
        String productSearchLogJson = "{\n" +
                "  \"userId\": \"def\",\n" +
                "  \"searchText\": \"name:abc\"\n" +
                "}";

        when(productAnalyticsService.logProductSearch(any(ProductSearchLog.class))).thenReturn(productSearchLog);

        mockMvc.perform(post("/analytics/log/productSearch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productSearchLogJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.searchText", is(productSearchLog.getSearchText())));

    }

}
