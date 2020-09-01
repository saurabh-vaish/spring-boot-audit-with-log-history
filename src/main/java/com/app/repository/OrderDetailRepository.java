package com.app.repository;

import com.app.models.OrderDetail;
import com.app.models.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> , RevisionRepository<OrderDetail,OrderDetailPK,Integer> {

//    Optional<OrderDetail> findById(OrderDetailPK orderDetailPK);

}
