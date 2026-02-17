package com.audible.service.impl;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.util.List;



import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import com.audible.dto.AudiobookDTO;

import com.audible.dto.OrderDTO;

import com.audible.dto.OrderItemDTO;

import com.audible.entity.Cart;

import com.audible.entity.CartItem;

import com.audible.entity.Order;

import com.audible.entity.OrderItem;

import com.audible.entity.PaymentCard;

import com.audible.exception.AudibleException;

import com.audible.repository.CartItemRepository;

import com.audible.repository.CartRepository;

import com.audible.repository.OrderItemRepository;

import com.audible.repository.OrderRepository;

import com.audible.repository.PaymentCardRepository;

import com.audible.service.CartService;

import com.audible.service.LibraryService;

import com.audible.service.OrderService;

import com.audible.util.MediaUrlUtil;



@Service



@Transactional



public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;



    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;



    @Autowired
    private CartService cartService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    private ModelMapper modelMapper=new ModelMapper();



    @Override
    public OrderDTO placeOrder(Integer customerId, String paymentMethod) {

        Cart cart = cartRepository.findByCustomerCustomerId(customerId)
                .orElseThrow(() -> new AudibleException("Service.CART_CUSTOMER_NOT_FOUND"));

        List<CartItem> cartItems = cartItemRepository.findByCartCartId(cart.getCartId());

        if (cartItems.isEmpty()) {
            throw new AudibleException("Service.CART_EMPTY");
        }



        if (paymentMethod != null && (paymentMethod.equalsIgnoreCase("Credit Card") || paymentMethod.equalsIgnoreCase("Debit Card"))) {
            List<PaymentCard> cards = paymentCardRepository.findByCustomerCustomerId(customerId);
            if (cards.isEmpty()) {
                throw new AudibleException("Service.CARDS_NOT_FOUND");
            }
        }

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            totalAmount = totalAmount.add(item.getAudiobook().getPrice());
        }

        double discountRate = 0;

        if(paymentMethod!=null) {
            if(paymentMethod.equalsIgnoreCase("Credit Card")){
                discountRate=0.10;
            }

            else if(paymentMethod.equalsIgnoreCase("Debit Card")){
                discountRate=0.5;
            }

        }



        BigDecimal discountAmount=totalAmount
                .multiply(BigDecimal.valueOf(discountRate))
                .setScale(2,RoundingMode.HALF_UP);

        BigDecimal finalAmount=totalAmount.subtract(discountAmount);

        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discountAmount);
        order.setFinalAmount(finalAmount);
        order.setPaymentStatus("SUCCESS"); // Mock payment success
        Order savedOrder = orderRepository.save(order);

        for (CartItem item : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setAudiobook(item.getAudiobook());
            orderItem.setPrice(item.getAudiobook().getPrice());
            orderItemRepository.save(orderItem);
            libraryService.addToLibrary(customerId, item.getAudiobook().getAudioId());

        }

        cartService.clearCart(customerId);

        return mapToDTO(savedOrder);

    }



    @Override

    public List<OrderDTO> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerCustomerId(customerId).stream()
                .map(this::mapToDTO)
                .toList();

    }



    private OrderDTO mapToDTO(Order order) {

        OrderDTO dto = modelMapper.map(order, OrderDTO.class);
        dto.setCustomerId(order.getCustomer().getCustomerId());
        List<OrderItem> items = orderItemRepository.findByOrderOrderId(order.getOrderId());

        List<OrderItemDTO> itemDTOs = items.stream()
                .map(item -> mapOrderItemToDTO(item, order.getOrderId()))
                .toList();



        dto.setOrderItems(itemDTOs);
        return dto;
    }

    private OrderItemDTO mapOrderItemToDTO(OrderItem item,Integer orderId) {

        OrderItemDTO dto = new OrderItemDTO();

        dto.setOrderItemId(item.getOrderItemId());
        dto.setOrderId(orderId);
        dto.setAudioId(item.getAudiobook().getAudioId());
        dto.setPrice(item.getPrice());
        dto.setAudiobook(modelMapper.map(item.getAudiobook(), AudiobookDTO.class));
        MediaUrlUtil.applyMediaUrls(dto.getAudiobook());
        return dto;

    }



}