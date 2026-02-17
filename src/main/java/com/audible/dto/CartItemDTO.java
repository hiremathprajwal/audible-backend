package com.audible.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartItemDTO {

    private Integer audioCartId;
    private Integer cartId;
    private Integer audioId;
    private AudiobookDTO audiobook;

}