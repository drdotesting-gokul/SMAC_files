package com.alloy.Config;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExcelConfig {

    private final Map<String, Double> meltingPoints = new HashMap<>();

    public ExcelConfig() {
        loadExcel();
    }

    private void loadExcel() {
        try {

            
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("Interaction_matrix.xlsx");

            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String element = row.getCell(0).getStringCellValue();
                double mp = row.getCell(1).getNumericCellValue();
                meltingPoints.put(element, mp);
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double getMeltingPoint(String element) {
        return meltingPoints.get(element);
    }
}