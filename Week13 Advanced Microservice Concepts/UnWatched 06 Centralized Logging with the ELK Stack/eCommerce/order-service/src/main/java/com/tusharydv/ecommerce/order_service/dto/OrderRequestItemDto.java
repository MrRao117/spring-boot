package com.tusharydv.ecommerce.order_service.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OrderRequestItemDto {
    private Long id;
    private Long productId;
    private Integer quantity;

}
