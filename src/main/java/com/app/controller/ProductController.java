package com.app.controller;

import com.app.dto.ProductDTO;
import com.app.models.Product;
import com.app.service.ProductService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api
@Log4j2
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAllProducts() {
        log.info("Find all products");
        return productService.findAll();
    }

    @GetMapping(path = "/{text}")
    public Product findByText(@PathVariable String text) {
        log.info(String.format("Finding by: %s", text));
        return productService.findByText(text, text);
    }

    @PostMapping
    public String saveProduct(@RequestBody ProductDTO productDTO) {
        Validate.notNull(productDTO, "The product cannot be null");
        log.info(String.format("Saving product: %s", productDTO.toString()));
        return productService.save(productDTO);
    }

    @PutMapping
    public String updateProduct(@RequestBody ProductDTO productDTO) {
        Validate.notNull(productDTO, "The product cannot be null");
        log.info(String.format("Updating product: %s", productDTO.toString()));
        return productService.update(productDTO);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteProduct(@PathVariable int id) {
        log.info(String.format("Deleting product: %s", id));
        return productService.deleteById(id);
    }

}
