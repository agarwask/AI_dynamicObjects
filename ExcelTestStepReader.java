package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pages.ai.TestStep;
import java.io.FileInputStream;
import java.util.*;

public class ExcelTestStepReader {
    public static Map<String, List<TestStep>> readTestSteps(String excelPath, String sheetName) {
        Map<String, List<TestStep>> methodSteps = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            String currentMethod = null;
            List<TestStep> steps = null;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                String methodName = row.getCell(0).getStringCellValue();
                if (!methodName.isEmpty()) {
                    currentMethod = methodName;
                    // Check if we already have a list for this method
                    steps = methodSteps.computeIfAbsent(currentMethod, k -> new ArrayList<>());
                }

                if (currentMethod != null && steps != null) {
                    TestStep step = new TestStep();
                    step.setStepName(row.getCell(1).getStringCellValue());
                    step.setAction(row.getCell(2).getStringCellValue());
                    step.setLocator(row.getCell(3).getStringCellValue());
                    step.setValue(row.getCell(4).getStringCellValue());
                    steps.add(step);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methodSteps;
    }
}