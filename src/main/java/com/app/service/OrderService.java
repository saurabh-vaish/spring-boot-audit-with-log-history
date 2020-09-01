package com.app.service;

import com.app.dto.OrderDTO;
import com.app.models.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    List<Order> findByText(String id, String customer, String employee, String date, String orderStatus);

    String save(OrderDTO orderDTO);

    String update(OrderDTO orderDTO);

    String deleteById(int id);

}
