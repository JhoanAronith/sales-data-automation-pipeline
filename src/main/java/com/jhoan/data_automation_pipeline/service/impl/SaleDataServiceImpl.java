package com.jhoan.data_automation_pipeline.service.impl;

import com.jhoan.data_automation_pipeline.model.SaleData;
import com.jhoan.data_automation_pipeline.repository.SaleDataRepository;
import com.jhoan.data_automation_pipeline.service.SaleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleDataServiceImpl implements SaleDataService {

    private final SaleDataRepository saleDataRepository;

    @Override
    public SaleData save(SaleData saleData) {
        if (saleData.getUnitPrice() != null && saleData.getQuantity() != null) {
            BigDecimal total = saleData.getUnitPrice().multiply(new BigDecimal(saleData.getQuantity()));
            saleData.setTotalPrice(total);
        } else {
            saleData.setTotalPrice(BigDecimal.ZERO);
        }
        return saleDataRepository.save(saleData);
    }

    @Override
    public List<SaleData> findAll() {
        return saleDataRepository.findAll();
    }

    @Override
    public Optional<SaleData> findByInvoiceNumber(String invoiceNumber) {
        return saleDataRepository.findByInvoiceNumber(invoiceNumber);
    }

}
