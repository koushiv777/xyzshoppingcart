package com.xyz.shopping.online.orderservice.controller;

import com.xyz.shopping.online.orderservice.entity.CallCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private CallCenter callCenter;

    @BeforeEach
    public void setUp(){
        callCenter = new CallCenter();
        callCenter.setPhoneNumber("+6581630557");
        callCenter.setCity("Singapore");
        callCenter.setAddressLine1("Block 175D");
        callCenter.setAddressLine2("Punggol Field");
    }

    @Test
    public void getCallCenterDetails() throws Exception {

        mockMvc.perform(get("/order/getCallCenterDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is(callCenter.getCity())));
    }
}
