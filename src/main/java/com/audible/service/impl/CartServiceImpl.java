package com.audible.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.audible.dto.CartDTO;
import com.audible.dto.CartItemDTO;
import com.audible.entity.Audiobook;
import com.audible.entity.Cart;
import com.audible.entity.CartItem;
import com.audible.entity.Customer;
import com.audible.exception.AudibleException;
import com.audible.repository.AudiobookRepository;
import com.audible.repository.CartItemRepository;
import com.audible.repository.CartRepository;
import com.audible.repository.CustomerRepository;
import com.audible.service.CartService;
import com.audible.util.MediaUrlUtil;

@Service
@Transactional

public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AudiobookRepository audiobookRepository;

    @Override
    public CartDTO getCartByCustomerId(Integer customerId) {

        Cart cart = getOrCreateCart(customerId);
        return mapToDTO(cart);

    }

    @Override
    public CartDTO addToCart(Integer customerId, Integer audioId) {
        Cart cart = getOrCreateCart(customerId);
        boolean exists = cartItemRepository.findByCartCartIdAndAudiobookAudioId(cart.getCartId(), audioId).isPresent();

        if (exists) {
            throw new AudibleException("Service.CART_ITEM_ALREADY_EXISTS");
        }

        Audiobook audiobook = audiobookRepository.findById(audioId)
                .orElseThrow(() -> new AudibleException("Service.AUDIOBOOK_ID_NOT_FOUND"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setAudiobook(audiobook);
        cartItemRepository.save(cartItem);
        return mapToDTO(cart);

    }

    @Override
    public CartDTO removeFromCart(Integer customerId, Integer audioId) {
        Cart cart = getOrCreateCart(customerId);

        CartItem cartItem = cartItemRepository.findByCartCartIdAndAudiobookAudioId(cart.getCartId(), audioId)
                .orElseThrow(() -> new AudibleException("Service.CART_ITEM_NOT_FOUND"));
        cartItemRepository.delete(cartItem);

        return mapToDTO(cart);

    }

    @Override

    public void clearCart(Integer customerId) {

        Cart cart = getOrCreateCart(customerId);
        cartItemRepository.deleteByCartCartId(cart.getCartId());

    }

    private Cart getOrCreateCart(Integer customerId) {

        return cartRepository.findByCustomerCustomerId(customerId)
                .orElseGet(() -> {
                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(
                                    () -> new AudibleException("Service.CART_CUSTOMER_NOT_FOUND"));
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepository.save(newCart);
                });

    }

    private CartDTO mapToDTO(Cart cart) {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        List<CartItem> items = cartItemRepository.findByCartCartId(cart.getCartId());
        List<CartItemDTO> itemDTOs = items.stream()

                .map(item -> {
                    CartItemDTO cartItemDTO = modelMapper.map(item, CartItemDTO.class);
                    MediaUrlUtil.applyMediaUrls(cartItemDTO.getAudiobook());
                    return cartItemDTO;
                })
                .toList();

        cartDTO.setCartItems(itemDTOs);
        return cartDTO;

    }
}