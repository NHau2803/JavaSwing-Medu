/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import java.util.ArrayList;
import model.Account;
import model.Point;
import model.Student;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author HAU28
 */
public interface HandlingFileInterface {
    
    public Font createFont(Workbook workbook);
   
    public void exportFileExcel(String nameFile, ArrayList<Student> ListStudent);

    public ArrayList<Student> importFileExcel(String nameFile);

    public ArrayList<Point> importFileExcelPoint(String id, String nameFile);

    public void exportAccountFileExcel(String nameFile, ArrayList<Account> LAccount);

    public ArrayList<Account> importAccountFileExcel(String nameFile);

}
