package com.app.repository;

import com.app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> , RevisionRepository<Product,Integer,Integer> {

    Product findByProductIdOrName(int id, String name);

}
