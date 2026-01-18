package com.jhoan.data_automation_pipeline.service;

import com.jhoan.data_automation_pipeline.model.Product;

import java.util.Optional;

public interface ProductService {

    Product save(Product product);
    Optional<Product> findBySku(String sku);

}
