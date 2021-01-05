/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import java.util.ArrayList;
import model.Report;
import model.Student;

/**
 *
 * @author HAU28
 */
public interface HandlingReportInterface {

    public ArrayList<Report> handlingReport();

    public int totalMale(ArrayList<Student> listAll);

    public int totalFemale(int size, int numMale);

    public Double[] totalPoint(ArrayList<Student> listAll);
    

}
