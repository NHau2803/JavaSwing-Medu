/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalImp;

import functional.HandlingFileInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Point;
import model.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HAU28
 */
public class HandlingFileService implements HandlingFileInterface {

    private final String[] HeaderVn = {
        "Mã Số", "Họ Và Tên",
        "Lớp", "Giới Tính", "Năm Sinh",
        "Số Điện Thoại", "Quê Quán"};
    private final String[] HeaderPointVn = {
        "Mã Số", "Họ Và Tên",
        "Môn Học", "%Bài Tập",
        "%Giữa Kì", "%Cuối Kì", "Ghi Chú"};
    private final String[] HeaderAccountVn = {
        "Tên đăng nhập", "Mật khẩu", "Lớp"};

    private static ArrayList<Student> LStudentData = new ArrayList<>();
    // private static List<CPoint> LPointData = new ArrayList<>();
    // private static List<CStudent> LStudent = new ArrayList<>();
    private static ArrayList<Point> LPoint = new ArrayList<>();
    private static ArrayList<Account> LAccount = new ArrayList<>();

    private int rowCount = 0;
    private int colCount = 0;
    private String user = "";
    private String pass = "";
    private String yourClass = "";
    private String id = "";
    private String name = "";
    private String phone = "";
    private String birthday = "";
    private String gender = "";
    private String address = "";

    @Override
    public Font createFont(Workbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        return headerFont;
    }

    @Override
    public void exportFileExcel(String nameFile, ArrayList<Student> ListStudent) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheetStudent = workbook.createSheet(nameFile);
            Sheet sheetPoint = workbook.createSheet("Điểm lớp " + nameFile);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(createFont(workbook));
            // Create a Row
            Row headerRow = sheetStudent.createRow(0);
            Row headerRowP = sheetPoint.createRow(0);
            int len = HeaderVn.length;
            for (int i = 0; i < len; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HeaderVn[i]);
                cell.setCellStyle(headerCellStyle);
            }
            len = HeaderPointVn.length;
            for (int i = 0; i < len; i++) {
                Cell cell = headerRowP.createCell(i);
                cell.setCellValue(HeaderPointVn[i]);
                cell.setCellStyle(headerCellStyle);
            }
            //Create Other rows and cells with contacts data
            int rowNum = 1;
            for (Student st : ListStudent) {
                Row row = sheetStudent.createRow(rowNum++);
                row.createCell(0).setCellValue(st.getId());
                row.createCell(1).setCellValue(st.getName());
                row.createCell(2).setCellValue(st.getYourClass());
                row.createCell(3).setCellValue(st.getGender());
                row.createCell(4).setCellValue(st.getBirthday());
                row.createCell(5).setCellValue(st.getPhone());
                row.createCell(6).setCellValue(st.getAddress());
            }
            int rowNumP = 1;
            ArrayList<Point> ListPoint = new ArrayList<>();
            for (Student st : ListStudent) {
                ListPoint = st.getPoint();
                for (Point p : ListPoint) {
                    Row row = sheetPoint.createRow(rowNumP++);
                    row.createCell(0).setCellValue(st.getId());
                    row.createCell(1).setCellValue(st.getName());
                    row.createCell(2).setCellValue(p.getNameSub());
                    row.createCell(3).setCellValue(p.getPoint_HW());
                    row.createCell(4).setCellValue(p.getPoint_Mid());
                    row.createCell(5).setCellValue(p.getPoint_Final());
                    row.createCell(6).setCellValue(p.getNote());
                }
            }
            for (int i = 0; i < HeaderVn.length; i++) {
                sheetStudent.autoSizeColumn(i);
            }
            for (int i = 0; i < HeaderPointVn.length; i++) {
                sheetPoint.autoSizeColumn(i);
            }
            String pathDesktop = System.getProperty("user.home") + "/Desktop\\";
            try (FileOutputStream fileOut = new FileOutputStream(pathDesktop + nameFile + ".xlsx")) {
                workbook.write(fileOut);
            }
            try (
                    FileOutputStream fileOut = new FileOutputStream(nameFile + ".xlsx")) {
                workbook.write(fileOut);
            }
            System.out.println("Import ok!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Student> importFileExcel(String nameFile) {
        try {
            LStudentData = new ArrayList<>();

            Workbook workbook = new XSSFWorkbook(new File(nameFile + ".xlsx"));
            Sheet sheetStudent = workbook.getSheetAt(0);

            rowCount = sheetStudent.getLastRowNum() + 1;
            colCount = sheetStudent.getRow(0).getLastCellNum();

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cellStudent = (XSSFCell) sheetStudent.getRow(i).getCell(j);
                    switch (j) {
                        case 0:
                            id = cellStudent.getStringCellValue();
                            break;
                        case 1:
                            name = cellStudent.getStringCellValue();
                            break;
                        case 2:
                            yourClass = cellStudent.getStringCellValue();
                            break;
                        case 3:
                            gender = cellStudent.getStringCellValue();
                            break;
                        case 4:
                            birthday = cellStudent.getStringCellValue();
                            break;
                        case 5:
                            phone = cellStudent.getStringCellValue();
                            break;
                        case 6:
                            address = cellStudent.getStringCellValue();
                            break;
                    }
                }
                LPoint = new ArrayList<>();
                LPoint = importFileExcelPoint(id, nameFile);
                LStudentData.add(new Student((ArrayList<Point>) LPoint, id,
                        name, phone, birthday, gender, address, yourClass));
            }

        } catch (IOException | InvalidFormatException e) {
            System.out.println(e);
        }
        return (ArrayList<Student>) LStudentData;
    }

    @Override
    public ArrayList<Point> importFileExcelPoint(String id, String nameFile) {
        try {
            String subject = null, note = null;
            double P_Hw = 0, P_Mid = 0, P_Final = 0;
            Workbook workbook = new XSSFWorkbook(new File(nameFile + ".xlsx"));
            Sheet sheetPoint = workbook.getSheetAt(1);
            //
            rowCount = sheetPoint.getLastRowNum() + 1;
            colCount = sheetPoint.getRow(0).getLastCellNum();
            //
            LPoint = new ArrayList<>();
            for (int i = 1; i < rowCount; i++) {
                XSSFCell cellID = (XSSFCell) sheetPoint.getRow(i).getCell(0);
                if (id.equals(cellID.toString())) {
                    for (int j = 0; j < colCount; j++) {
                        XSSFCell cellPoint = null;

                        cellPoint = (XSSFCell) sheetPoint.getRow(i).getCell(j);
                        switch (j) {
                            case 2:
                                subject = cellPoint.getStringCellValue();
                            case 3:
                                P_Hw = Double.valueOf(cellPoint.toString());
                            case 4:
                                P_Mid = Double.valueOf(cellPoint.toString());
                            case 5:
                                P_Final = Double.valueOf(cellPoint.toString());
                            case 6:
                                note = cellPoint.getStringCellValue();
                        }
                    }
                    LPoint.add(new Point(subject, P_Hw, P_Mid, P_Final, note));
                }
            }
        } catch (NumberFormatException | IOException | InvalidFormatException e) {
            System.out.println(e);
            // Logger.getLogger(HandlingFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Point>) LPoint;
    }
//====================================================================================
    @Override
    public void exportAccountFileExcel(String nameFile, ArrayList<Account> LAccount) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheetAccount = workbook.createSheet(nameFile);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(createFont(workbook));

            Row headerRow = sheetAccount.createRow(0);
            int len = HeaderAccountVn.length;
            for (int i = 0; i < len; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HeaderAccountVn[i]);
                cell.setCellStyle(headerCellStyle);
            }
            int rowNum = 1;
            for (Account account : LAccount) {
                Row row = sheetAccount.createRow(rowNum++);
                row.createCell(0).setCellValue(account.getUser());
                row.createCell(1).setCellValue(account.getPass());
                row.createCell(2).setCellValue(account.getYourClass());
            }
            for (int i = 0; i < HeaderVn.length; i++) {
                sheetAccount.autoSizeColumn(i);
            }
            try ( // Write the output to a file
                    FileOutputStream fileOut = new FileOutputStream(nameFile + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Account> importAccountFileExcel(String nameFile) {
        try {
            LAccount = new ArrayList<>();
            Workbook workbook = new XSSFWorkbook(new File(nameFile + ".xlsx"));
            Sheet sheetAccount = workbook.getSheetAt(0);
            rowCount = sheetAccount.getLastRowNum() + 1;
            colCount = sheetAccount.getRow(0).getLastCellNum();
            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cellAccount = (XSSFCell) sheetAccount.getRow(i).getCell(j);
                    switch (j) {
                        case 0:
                            user = cellAccount.getStringCellValue();
                        case 1:
                            pass = cellAccount.getStringCellValue();
                        case 2:
                            yourClass = cellAccount.getStringCellValue();
                    }
                }
                LAccount.add(new Account(user, pass, yourClass));
            }
        } catch (NumberFormatException | IOException | InvalidFormatException e) {
            System.out.println(e);
        }
        return (ArrayList<Account>) LAccount;
    }

}
