package com.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

    private int id;
    private int orderStatus;
    private String saleDate;
    private int customer;
    private int employee;


    @Override
    public String toString() {
        return String.format("OrderDTO{id=%d, orderStatus=%d, saleDate='%s', customer=%d, employee=%d}", id,
                             orderStatus, saleDate, customer, employee);
    }
}
