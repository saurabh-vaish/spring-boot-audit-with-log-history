package com.app.service;

import com.app.dto.OrderDetailsDTO;
import com.app.models.OrderDetail;
import com.app.models.OrderDetailPK;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> findAll();

    OrderDetail findByDetailPk(String productId, String orderId);

    String save(OrderDetailsDTO orderDetailsDTO);

    String update(OrderDetailsDTO orderDetailsDTO);

    String deleteById(OrderDetailPK orderDetailPK);

}
