package com.xyz.shopping.online.orderservice.controller;

import com.xyz.shopping.online.orderservice.entity.CallCenter;
import com.xyz.shopping.online.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "/getCallCenterDetails", method = RequestMethod.GET)
    public ResponseEntity<CallCenter> getCallCenterDetails(){
        Optional<CallCenter> callCenter = orderService.getCallCenterDetails(1l);
        if(callCenter.isPresent()){
            return new ResponseEntity<>(callCenter.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
