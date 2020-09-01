package com.app.controller;

import com.app.dto.OrderDetailsDTO;
import com.app.models.OrderDetail;
import com.app.service.OrderDetailService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Log4j2
@RestController
@RequestMapping(value = "/order-detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public List<OrderDetail> findAllDetails() {
        log.info("Find all order details");
        return orderDetailService.findAll();
    }

    @GetMapping(path = "/{productId}/{orderId}")
    public OrderDetail findByDetailPk(@PathVariable String productId,
                                      @PathVariable String orderId) {
        log.info(String.format("Finding by: %s, %s", productId, orderId));
        return orderDetailService.findByDetailPk(productId, orderId);
    }

    @PostMapping
    public String saveDetail(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        Validate.notNull(orderDetailsDTO, "The order detail cannot be null");
        log.info(String.format("Saving detail: %s", orderDetailsDTO.toString()));
        return orderDetailService.save(orderDetailsDTO);
    }

    @PutMapping
    public String updateDetail(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        Validate.notNull(orderDetailsDTO, "The order detail cannot be null");
        log.info(String.format("Updating order detail: %s", orderDetailsDTO.toString()));
        return orderDetailService.update(orderDetailsDTO);
    }

    @DeleteMapping
    public String deleteDetail(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        log.info(String.format("Deleting order detail: %s", orderDetailsDTO));
        return orderDetailService.deleteById(orderDetailsDTO.getId());
    }

}
