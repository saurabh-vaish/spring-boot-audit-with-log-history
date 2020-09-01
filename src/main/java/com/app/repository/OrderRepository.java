package com.app.repository;

import com.app.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> , RevisionRepository<Order,Integer,Integer> {

    List<Order> findByOrderIdOrCustomer_IdOrEmployee_IdOrSaleDateOrOrderStatus(Integer orderId, Integer customer_id, Integer employee_id, Date saleDate, int orderStatus);

    List<Order> findByOrderIdOrCustomer_FirstNameOrCustomer_LastNameOrEmployee_FirstNameOrEmployee_LastNameOrSaleDateOrOrderStatus(Integer orderId, String customerName, String customerLastName, String employee, String employeeLastName, Date date, int orderStatus);

}
