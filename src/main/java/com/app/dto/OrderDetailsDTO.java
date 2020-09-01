package com.app.dto;


import com.app.models.OrderDetailPK;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDTO {

    private OrderDetailPK id;
    private double discount;
    private int quantity;
    private double unitPrice;

    @Override
    public String toString() {
        return String.format("OrderDetailDTO{[order=%d, product=%d], discount=%.2f, quantity=%d, unitPrice=%.2f}",
                             id.getOrderId(), id.getProductId(), discount, quantity, unitPrice);
    }

}
