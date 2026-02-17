package com.audible.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.audible.dto.OrderDTO;
import com.audible.service.OrderService;

@RestController
@RequestMapping("/api/orders")
//@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{customerId}/place")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Integer customerId,
                                               @RequestParam(defaultValue = "Credit Card") String paymentMethod) {

        OrderDTO order = orderService.placeOrder(customerId, paymentMethod);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Integer customerId) {

        List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }
}