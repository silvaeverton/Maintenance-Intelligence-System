package com.example.maintenance_Intelligence_system.services.serviceImpl;

import com.example.maintenance_Intelligence_system.models.Machine;
import com.example.maintenance_Intelligence_system.models.TechnicalReport;
import com.example.maintenance_Intelligence_system.services.ExcelExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ExcelExportServiceImpl implements ExcelExportService {
    @Override
    public byte[] generateExcel(List<TechnicalReport> resportList) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Relatório Técnicos");

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            String[] headers = {
                    "ID", "Operator", "Machine", "OS Status", "Machine Status", "Category", "Problem", "Solution",
                    "Start Time", "End Time", "Accepted Date", "Technician", "Stop Type"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            CellStyle bodyStyle = workbook.createCellStyle();
            ;
            bodyStyle.setWrapText(true);
            bodyStyle.setVerticalAlignment(VerticalAlignment.TOP);

            int rowNum = 1;
            for (TechnicalReport report : resportList) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(report.getId());
                row.createCell(1).setCellValue(Optional.ofNullable(report.getOperator()).orElse("-"));
                row.createCell(2).setCellValue(Optional.ofNullable(report.getMachine()).map(Machine::getName).orElse("-"));
                row.createCell(3).setCellValue(Optional.ofNullable(report.getStatusOrder()).map(Enum::name).orElse("-"));
                row.createCell(4).setCellValue(Optional.ofNullable(report.getStatusMachine()).map(Enum::name).orElse("-"));
                row.createCell(5).setCellValue(Optional.ofNullable(report.getRequestCategoryOS()).map(Enum::name).orElse("-"));
                row.createCell(6).setCellValue(Optional.ofNullable(report.getProblemFound()).orElse("-"));
                row.createCell(7).setCellValue(Optional.ofNullable(report.getSolutionAdopted()).orElse("-"));
                row.createCell(8).setCellValue(formatDate(report.getStartTime()));
                row.createCell(9).setCellValue(formatDate(report.getCloseTime()));
                row.createCell(10).setCellValue(formatDate(report.getAcceptedCalled()));
                row.createCell(11).setCellValue(Optional.ofNullable(report.getTechnicianResponsible()).map(t -> t.getName()).orElse("-"));
                row.createCell(12).setCellValue(Optional.ofNullable(report.getTypeOfStop()).orElse("-"));

                for (int i = 0; i < headers.length; i++) {
                    row.getCell(i).setCellStyle(bodyStyle);
                }

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error generating Excel ", e);
        }
    }

    private String formatDate(LocalDateTime dateTime) {
        return (dateTime != null) ? dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "-";
    }
}
