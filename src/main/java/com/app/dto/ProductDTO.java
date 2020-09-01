package com.app.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private int quantityPerUnit;
    private double unitPrice;

    @Override
    public String toString() {
        return String.format("ProductDTO{ id=%d, name='%s', description='%s', quantityPerUnit=%d, unitPrice=%.2f}", id,
                             name, description, quantityPerUnit, unitPrice);
    }

}
