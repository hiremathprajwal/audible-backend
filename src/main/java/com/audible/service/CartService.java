package com.audible.service;

import com.audible.dto.CartDTO;

public interface CartService {
    CartDTO getCartByCustomerId(Integer customerId);
    CartDTO addToCart(Integer customerId, Integer audioId);
    CartDTO removeFromCart(Integer customerId, Integer audioId);
    void clearCart(Integer customerId);
}
