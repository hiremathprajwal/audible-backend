package com.audible.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audible.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartCartId(Integer cartId);

    Optional<CartItem> findByCartCartIdAndAudiobookAudioId(Integer cartId, Integer audioId);

    void deleteByCartCartId(Integer cartId);
}
