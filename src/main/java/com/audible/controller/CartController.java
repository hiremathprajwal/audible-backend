package com.audible.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audible.dto.CartDTO;
import com.audible.service.CartService;



@RestController
@RequestMapping("/api/carts")

//@CrossOrigin(origins = "http://localhost:3000")

public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> getCartByCustomerId(@PathVariable Integer customerId) {

        CartDTO cart = cartService.getCartByCustomerId(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/{customerId}/add/{audioId}")
    public ResponseEntity<CartDTO> addToCart(@PathVariable Integer customerId, @PathVariable Integer audioId) {

        CartDTO cart = cartService.addToCart(customerId, audioId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}/remove/{audioId}")

    public ResponseEntity<CartDTO> removeFromCart(@PathVariable Integer customerId, @PathVariable Integer audioId) {
        CartDTO cart = cartService.removeFromCart(customerId, audioId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }



    @DeleteMapping("/{customerId}/clear")

    public ResponseEntity<Void> clearCart(@PathVariable Integer customerId) {
        cartService.clearCart(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}