package com.xyz.shopping.online.orderservice.service;

import com.xyz.shopping.online.orderservice.entity.CallCenter;

import java.util.Optional;

public interface OrderService {

    Optional<CallCenter> getCallCenterDetails(long id);
}
