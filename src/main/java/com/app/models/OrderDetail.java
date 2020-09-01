package com.app.models;

import com.app.dto.OrderDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Audited
@Table(name = "order_detail")
public class OrderDetail extends VersionEntityManagerWithoutId {


    @EmbeddedId
    private OrderDetailPK id;

    @Column(name = "discount")
    private double discount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    /**
     * bi-directional many-to-one association to Order entity
     * JoinColumn name must have the same name as the Order entity Id, as well as the same
     * name as defined in the column name of OrderDetailPK entity, because this is part of composite pk
     * check the name of the column annotation @Column(name = "order_id") in Order entity
     */
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    /**
     * bi-directional many-to-one association to Product
     * JoinColumn name must have the same name as the Product entity Id, as well as the same
     * name as defined in the column name of OrderDetailPK entity, because this is part of composite pk
     * check the name of the column annotation @Column(name = "product_id") in Product entity
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;

    public OrderDetail(OrderDetailsDTO orderDetailsDTO) {
        this.id = orderDetailsDTO.getId();
        this.quantity = orderDetailsDTO.getQuantity();
        this.discount = orderDetailsDTO.getDiscount();
        this.unitPrice = orderDetailsDTO.getUnitPrice();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("discount", discount)
                .append("quantity", quantity)
                .append("unitPrice", unitPrice)
                .append("order", order)
                .append("product", product)
                .toString();
    }

}