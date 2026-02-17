package com.audible.service.impl;

import java.util.List;



import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import com.audible.dto.PaymentCardDTO;

import com.audible.entity.Customer;

import com.audible.entity.PaymentCard;

import com.audible.exception.AudibleException;

import com.audible.repository.CustomerRepository;

import com.audible.repository.PaymentCardRepository;

import com.audible.service.PaymentCardService;



@Service

@Transactional

public class PaymentCardServiceImpl implements PaymentCardService {

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public PaymentCardDTO addPaymentCard(PaymentCardDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new AudibleException("Service.Customer.ID_NOT_FOUND"));

        PaymentCard card = modelMapper.map(dto, PaymentCard.class);
        card.setCustomer(customer);

        PaymentCard savedCard = paymentCardRepository.save(card);
        return modelMapper.map(savedCard, PaymentCardDTO.class);

    }

    @Override
    public void deletePaymentCard(Integer cardId) {
        if (!paymentCardRepository.existsById(cardId)) {
            throw new AudibleException("Service.Payment.CARD_NOT_FOUND");
        }
        paymentCardRepository.deleteById(cardId);
    }

    @Override

    public List<PaymentCardDTO> getPaymentCardsByCustomerId(Integer customerId) {
        return paymentCardRepository.findByCustomerCustomerId(customerId).stream()
                .map(card -> modelMapper.map(card, PaymentCardDTO.class))
                .toList();

    }
}