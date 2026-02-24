package com.alloy.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;

@Service
public class InteractionMatrixService {

    // Matrix structure:
    // RowElement -> (ColumnElement -> interaction value)
    private final Map<String, Map<String, Double>> matrix = new HashMap<>();

    @PostConstruct
    public void init() {
        loadMatrix();
    }

    private void loadMatrix() {

        try {
            System.out.println("Loading Excel file...");

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("Interaction_matrix.xlsx");

            if (is == null) {
                throw new RuntimeException("Excel file NOT found in resources folder!");
            }

            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);
            int lastColumn = headerRow.getLastCellNum();

            // Store column elements
            List<String> columnElements = new ArrayList<>();

            for (int col = 1; col < lastColumn; col++) {
                String element = headerRow
                        .getCell(col)
                        .getStringCellValue()
                        .trim();
                columnElements.add(element);
            }

            // Read rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                Cell firstCell = row.getCell(0);
                if (firstCell == null) continue;

                String rowElement = firstCell.getStringCellValue().trim();
                Map<String, Double> rowMap = new HashMap<>();

                for (int col = 1; col < lastColumn; col++) {

                    Cell cell = row.getCell(col);
                    Double value = null;

                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        value = cell.getNumericCellValue();
                    }

                    String columnElement = columnElements.get(col - 1);
                    rowMap.put(columnElement, value);
                }

                matrix.put(rowElement, rowMap);
            }

            workbook.close();

            System.out.println("Matrix size loaded: " + matrix.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==========================
    // PUBLIC METHODS
    

    public int getMetalCount() {
        return matrix.size();
    }

    public List<String> getAllMetals() {
        return new ArrayList<>(matrix.keySet());
    }

    public Double getInteractionValue(String element1, String element2) {

        if (!matrix.containsKey(element1)) return null;

        Map<String, Double> row = matrix.get(element1);

        return row.getOrDefault(element2, null);
    }

    public Map<String, Map<String, Double>> getMatrix() {
        return matrix;
    }
}