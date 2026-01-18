package com.jhoan.data_automation_pipeline.repository;

import com.jhoan.data_automation_pipeline.model.SaleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleDataRepository extends JpaRepository<SaleData, Long> {

    Optional<SaleData> findByInvoiceNumber(String invoiceNumber);

}
