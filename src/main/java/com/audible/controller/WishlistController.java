package com.audible.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audible.dto.WishlistDTO;
import com.audible.service.WishlistService;



@RestController
@RequestMapping("/api/wishlist")
//@CrossOrigin(origins = "http://localhost:3000")

public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{customerId}/{audioId}")
    public ResponseEntity<String> addToWishlist(

            @PathVariable Integer customerId,
            @PathVariable Integer audioId) {

        wishlistService.addToWishlist(customerId, audioId);
        return ResponseEntity.ok("Added to wishlist");

    }



    @DeleteMapping("/{customerId}/{audioId}")



    public ResponseEntity<String> removeFromWishlist(

            @PathVariable Integer customerId,
            @PathVariable Integer audioId) {

        wishlistService.removeFromWishlist(customerId, audioId);
        return ResponseEntity.ok("Removed from wishlist");

    }



    @GetMapping("/{customerId}")

    public ResponseEntity<List<WishlistDTO>> getWishlist(@PathVariable Integer customerId) {
        return ResponseEntity.ok(wishlistService.getWishlist(customerId));
    }

}