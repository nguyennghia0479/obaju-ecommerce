package cybersoft.javabackend.java18.obajuecommerce.app_product.service;

import cybersoft.javabackend.java18.obajuecommerce.app_product.dto.ProductIncludeSubcategoryDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.exception.FileException;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.FileExceptionMessageUtils;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class ProductToExcel {
    static String[] HEADERS = {"Id", "Product Name", "Product Code", "Price", "Color", "Subcategory"};
    static String SHEET = "Products";

    private CellStyle createCellStyle(Workbook workbook, boolean isBold, int fontSize) {
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(isBold);
        font.setFontHeight(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private Row createHeaderLine(Sheet sheet, Workbook workbook) {
        CellStyle cellStyle = createCellStyle(workbook, true, 16);
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < HEADERS.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(HEADERS[col]);
            cell.setCellStyle(cellStyle);
        }
        return headerRow;
    }

    private void createDataLine(Sheet sheet, Workbook workbook, List<ProductIncludeSubcategoryDTO> products) {
        CellStyle cellStyle = createCellStyle(workbook, true, 13);
        int rowIdx = 1;
        for (ProductIncludeSubcategoryDTO product : products) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(product.getId().toString());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getCode());
            row.createCell(3).setCellValue(product.getPrice());
            row.createCell(4).setCellValue(product.getColor().toString());
            row.createCell(5).setCellValue(product.getSubcategory().getName());
            row.setRowStyle(cellStyle);
        }
    }

    public static ByteArrayInputStream productToExcel(List<ProductIncludeSubcategoryDTO> products) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = createHeaderLine(sheet, workbook);
            createDataLine(sheet, workbook, products);
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new FileException(FileExceptionMessageUtils.EXPORT_FILE_ERROR);
        }
    }
}
