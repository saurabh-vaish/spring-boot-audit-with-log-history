package com.app.models;

import lombok.*;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailPK implements Serializable {

    private static final long serialVersionUID = 1125212530915683308L;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("productId", productId)
                .append("orderId", orderId)
                .toString();
    }

}