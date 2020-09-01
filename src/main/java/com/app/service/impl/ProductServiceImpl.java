package com.app.service.impl;

import com.app.models.Product;
import com.app.dto.ProductDTO;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import com.app.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final ProductRepository productRepository;
    private String response;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByText(String id, String name) {
        Integer productId = Utilities.isInteger(id);
        return productRepository.findByProductIdOrName(productId, name);
    }

    @Override
    public String save(ProductDTO productDTO) {
        response = "Product saved!";
        productRepository.save(new Product(productDTO));
        logger.info(response);
        return response;
    }

    @Override
    public String update(ProductDTO productDTO) {
        response = "Product updated!";
        Product product = productRepository.findById(productDTO.getId()).get();
        product = this.updateProduct(product, productDTO);
        productRepository.save(product);
        logger.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "Product deleted!";
        productRepository.deleteById(id);
        logger.info(response);
        return response;
    }

    private Product updateProduct(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantityPerUnit(productDTO.getQuantityPerUnit());
        product.setUnitPrice(productDTO.getUnitPrice());
        return product;
    }

}
