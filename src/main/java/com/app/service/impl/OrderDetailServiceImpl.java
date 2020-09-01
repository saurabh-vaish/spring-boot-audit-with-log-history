package com.app.service.impl;

import com.app.dto.*;
import com.app.models.OrderDetail;
import com.app.models.OrderDetailPK;
import com.app.repository.OrderDetailRepository;
import com.app.service.OrderDetailService;
import com.app.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderDetailRepository orderDetailRepository;

    private String response;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findByDetailPk(String productId, String orderId) {
        Integer prodId = Utilities.isInteger(productId);
        Integer ordId = Utilities.isInteger(orderId);
        return orderDetailRepository.findById(new OrderDetailPK(prodId, ordId)).get();
    }

    @Override
    public String save(OrderDetailsDTO orderDetailsDTO) {
        response = "Order Detail saved!";
        orderDetailRepository.save(new OrderDetail(orderDetailsDTO));
        logger.info(response);
        return response;
    }

    @Override
    public String update(OrderDetailsDTO orderDetailsDTO) {
        response = "Order Detail updated!";
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailsDTO.getId()).get();
        orderDetail = this.updateOrderDetail(orderDetail, orderDetailsDTO);
        orderDetailRepository.save(orderDetail);
        logger.info(response);
        return response;
    }

    @Override
    public String deleteById(OrderDetailPK orderDetailPK) {
        response = "Order Detail deleted!";
        orderDetailRepository.deleteById(orderDetailPK);
        return response;
    }

    private OrderDetail updateOrderDetail(OrderDetail orderDetail,
                                          OrderDetailsDTO orderDetailsDTO) {
        orderDetail.setUnitPrice(orderDetailsDTO.getUnitPrice());
        orderDetail.setDiscount(orderDetailsDTO.getDiscount());
        orderDetail.setQuantity(orderDetailsDTO.getQuantity());
        return orderDetail;
    }

}
