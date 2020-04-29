package com.xyz.shopping.online.orderservice.controller;

import com.xyz.shopping.online.orderservice.entity.CallCenter;
import com.xyz.shopping.online.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

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
        Optional<CallCenter> optCallCenter = Optional.of(callCenter);
        when(orderService.getCallCenterDetails(1l)).thenReturn(optCallCenter);
        mockMvc.perform(get("/order/getCallCenterDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is(callCenter.getCity())));
    }
}
