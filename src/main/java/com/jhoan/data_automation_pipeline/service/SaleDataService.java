package com.jhoan.data_automation_pipeline.service;

import com.jhoan.data_automation_pipeline.model.SaleData;

import java.util.List;
import java.util.Optional;

public interface SaleDataService {

    SaleData save(SaleData saleData);
    List<SaleData> findAll();
    Optional<SaleData> findByInvoiceNumber(String invoiceNumber);

}
