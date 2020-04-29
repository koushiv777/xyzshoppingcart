package com.xyz.shopping.online.orderservice.service;

import com.xyz.shopping.online.orderservice.entity.CallCenter;
import com.xyz.shopping.online.orderservice.repository.CallCenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private CallCenterRepository callCenterRepository;

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

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
    public void getCallCenterDetails(){
        Optional<CallCenter> optCallCenter = Optional.of(callCenter);
        when(callCenterRepository.findById(1l)).thenReturn(optCallCenter);
        Optional<CallCenter> callCenter1 = orderService.getCallCenterDetails(1l);
        assertEquals(callCenter, callCenter1.get());
    }

}
