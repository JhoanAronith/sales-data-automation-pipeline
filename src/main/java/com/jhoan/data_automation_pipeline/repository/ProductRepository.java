package com.jhoan.data_automation_pipeline.repository;

import com.jhoan.data_automation_pipeline.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

}
