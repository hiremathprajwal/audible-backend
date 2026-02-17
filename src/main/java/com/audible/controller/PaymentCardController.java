package com.audible.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.audible.dto.PaymentCardDTO;
import com.audible.service.PaymentCardService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment-cards")

//@CrossOrigin(origins = "http://localhost:3000")
public class PaymentCardController {
    @Autowired
    private PaymentCardService paymentCardService;

    @PostMapping
    public ResponseEntity<PaymentCardDTO> addPaymentCard(@Valid @RequestBody PaymentCardDTO paymentCardDTO) {

        PaymentCardDTO savedCard = paymentCardService.addPaymentCard(paymentCardDTO);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deletePaymentCard(@PathVariable Integer cardId) {

        paymentCardService.deletePaymentCard(cardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PaymentCardDTO>> getPaymentCardsByCustomerId(@PathVariable Integer customerId) {
        List<PaymentCardDTO> cards = paymentCardService.getPaymentCardsByCustomerId(customerId);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}