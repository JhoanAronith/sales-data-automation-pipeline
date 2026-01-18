package com.jhoan.data_automation_pipeline.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ExcelService {

    void importSalesFromExcel(MultipartFile file) throws IOException;

}