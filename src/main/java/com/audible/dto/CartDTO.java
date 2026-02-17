package com.audible.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartDTO {

    private Integer cartId;
    private Integer customerId;
    private List<CartItemDTO> cartItems;

}