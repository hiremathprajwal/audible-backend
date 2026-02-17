package com.audible.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDTO {

    private Integer orderItemId;
    private Integer orderId;
    private Integer audioId;
    private AudiobookDTO audiobook;
    private BigDecimal price;

}