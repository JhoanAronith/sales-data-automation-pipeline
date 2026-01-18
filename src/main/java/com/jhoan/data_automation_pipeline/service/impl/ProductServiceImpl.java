package com.jhoan.data_automation_pipeline.service.impl;

import com.jhoan.data_automation_pipeline.model.Product;
import com.jhoan.data_automation_pipeline.repository.ProductRepository;
import com.jhoan.data_automation_pipeline.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepository.findBySku(sku);
    }

}
