package com.audible.service;

import com.audible.dto.PaymentCardDTO;
import com.audible.dto.WishlistDTO;

import java.util.List;

public interface WishlistService {
    void addToWishlist(Integer customerId, Integer audioId);
    List<WishlistDTO> getWishlist(Integer customerId);
    void removeFromWishlist(Integer customerId, Integer audioId);
}
