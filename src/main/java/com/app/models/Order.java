package com.app.models;

import com.app.dto.OrderDTO;
import com.app.utils.Utilities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Audited
@Table(name = "order_table")
public class Order extends VersionEntityManager {

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_status")
    private int orderStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "sale_date")
    private Date saleDate;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Person customer;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Person employee;

    public Order(OrderDTO orderDTO, Person customer, Person employee) {
        this.customer = customer;
        this.employee = employee;
        this.saleDate = Utilities.matchDate(orderDTO.getSaleDate());
        this.orderStatus = orderDTO.getOrderStatus();
    }

}