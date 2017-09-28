package utils.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TO_ExcelUtils {

    public static XSSFSheet ExcelWSheet;
    public static XSSFWorkbook ExcelWBook;
    public static XSSFCell Cell;
    public static XSSFRow Row;

    // This method is to set the File path and to open the Excel file
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);

            // Access the required testdata sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;

        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required testdata sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int totalRows = ExcelWSheet.getLastRowNum();
            int totalCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells() - 1;

            System.out.println("Raekker: " + totalRows);
            System.out.println("Kolonner: " + totalCols);

            // tabArray indeholder data fra alle raekker og kolonner i Excel
            tabArray = new String[totalRows][totalCols];

            int ci = 0; // ci angiver raekke i array
            for (int i = 1; i <= totalRows; i++, ci++) {
                int cj = 0; // cj angiver kolonne i array
                for (int j = 1; j <= totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        return (tabArray);
    }

    // This method is to read the testdata from the Excel cell
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getTestCaseName(String sTestCase) throws Exception {
        String value = sTestCase;
        try {
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);
            return value;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int getRowContains(String sTestCaseName, int colNum) throws Exception {
        int i;
        try {
            int rowCount = getRowUsed();
            for (i = 0; i < rowCount; i++) {
                if (getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
                    break;
                }
            }
            return i;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int getRowUsed() throws Exception {
        try {
            int RowCount = ExcelWSheet.getLastRowNum();
            return RowCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

}
