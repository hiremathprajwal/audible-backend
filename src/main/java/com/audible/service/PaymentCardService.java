package com.audible.service;

import java.util.List;

import com.audible.dto.PaymentCardDTO;

public interface PaymentCardService {
    PaymentCardDTO addPaymentCard(PaymentCardDTO paymentCardDTO);

    void deletePaymentCard(Integer cardId);

    List<PaymentCardDTO> getPaymentCardsByCustomerId(Integer customerId);
}
