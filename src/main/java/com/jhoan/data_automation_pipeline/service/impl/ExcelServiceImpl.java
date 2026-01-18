package com.jhoan.data_automation_pipeline.service.impl;

import com.jhoan.data_automation_pipeline.model.*;
import com.jhoan.data_automation_pipeline.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final ProductService productService;
    private final SaleDataService saleDataService;

    @Override
    public void importSalesFromExcel(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            Cell cellInvoice = row.getCell(0);
            Cell cellDate = row.getCell(1);
            Cell cellSku = row.getCell(2);
            Cell cellProduct = row.getCell(3);
            Cell cellCategory = row.getCell(4);
            Cell cellQty = row.getCell(5);
            Cell cellPrice = row.getCell(6);
            Cell cellRegion = row.getCell(7);
            String invoiceNum = formatter.formatCellValue(cellInvoice);
            if (invoiceNum.isEmpty()) continue;
            if (saleDataService.findByInvoiceNumber(invoiceNum).isPresent()) {
                log.info("Saltando factura duplicada: {}", invoiceNum);
                continue;
            }
            String sku = formatter.formatCellValue(cellSku);
            Product product = productService.findBySku(sku)
                    .orElseGet(() -> {
                        Product nuevo = new Product();
                        nuevo.setSku(sku);
                        nuevo.setName(formatter.formatCellValue(cellProduct));
                        nuevo.setCategory(formatter.formatCellValue(cellCategory));
                        return productService.save(nuevo);
                    });
            SaleData venta = new SaleData();
            venta.setInvoiceNumber(invoiceNum);
            venta.setProduct(product);
            if (cellDate != null) {
                try {
                    if (cellDate.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cellDate)) {
                        venta.setSaleDate(cellDate.getLocalDateTimeCellValue().toLocalDate());
                    } else {
                        String dateStr = formatter.formatCellValue(cellDate);
                        venta.setSaleDate(java.time.LocalDate.parse(dateStr));
                    }
                } catch (Exception e) {
                    log.warn("No se pudo procesar la fecha en fila {}: {}", row.getRowNum(), e.getMessage());
                }
            }
            try {
                String cantStr = formatter.formatCellValue(cellQty).trim();
                if (!cantStr.isEmpty()) {
                    double val = Double.parseDouble(cantStr.replace(",", "."));
                    venta.setQuantity((int) val);
                }
                String precioStr = formatter.formatCellValue(cellPrice)
                        .replace(",", ".")
                        .replaceAll("[^\\d.]", "");
                if (!precioStr.isEmpty()) {
                    venta.setUnitPrice(new java.math.BigDecimal(precioStr));
                }
            } catch (NumberFormatException e) {
                log.error("Error de formato numérico en fila {}: {}", row.getRowNum(), e.getMessage());
                continue;
            }
            venta.setRegion(formatter.formatCellValue(cellRegion));
            saleDataService.save(venta);
        }
        workbook.close();
    }
}