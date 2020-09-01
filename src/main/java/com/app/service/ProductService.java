
package com.app.service;

import com.app.dto.ProductDTO;
import com.app.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByText(String id, String name);

    String save(ProductDTO productDTO);

    String update(ProductDTO productDTO);

    String deleteById(int id);

}
