package com.app.controller;

import com.app.dto.OrderDTO;
import com.app.models.Order;
import com.app.service.OrderService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(value = "/order")
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAllOrders() {
        log.info("Find all orders");
        return orderService.findAll();
    }

    @GetMapping(path = "/{text}")
    public List<Order> findByTest(@PathVariable String text) {
        log.info(String.format("Finding by: %s", text));
        return orderService.findByText(text, text, text, text, text);
    }

    @PostMapping
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        log.info(String.format("Saving order: %s", orderDTO.toString()));
        return orderService.save(orderDTO);
    }

    @PutMapping
    public String updateOrder(@RequestBody OrderDTO orderDTO) {
        Validate.notNull(orderDTO, "The order cannot be null");
        log.info(String.format("Updating order: %s", orderDTO.toString()));
        return orderService.update(orderDTO);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteOrder(@PathVariable int id) {
        log.info(String.format("Deleting order: %s", id));
        return orderService.deleteById(id);
    }

}
