/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalImp;

import functional.HandlingReportInterface;
import functional.impl.HandlingTxtService;
import java.util.ArrayList;
import model.Point;
import model.Report;
import model.Student;
import serviceImpl.StudentService;

/**
 *
 * @author HAU28
 */
public class HandlingReportService implements HandlingReportInterface {

    private Report report;
    private final ArrayList<Report> listReport = new ArrayList<>();
    private final HandlingTxtService hts = new HandlingTxtService();
    private final StudentService ss = new StudentService();
    private ArrayList<String> listLedger = new ArrayList<>();
    private ArrayList<Student> listStudent = new ArrayList<>();

    @Override
    public ArrayList<Report> handlingReport() {
        listLedger = hts.readLedger("Ledger.txt");
        for (String nameClass : listLedger) {
            if (!"".equals(nameClass)) {
                report = new Report();
                listStudent = new ArrayList<>();
                listStudent = ss.getList(nameClass);
                int size = listStudent.size();
                report.setNameClass(nameClass);
                report.setToltalStudent(size);
                //
                int totalMale = totalMale(listStudent);
                report.setTotalMale(totalMale);
                int totalFemale = totalFemale(size, totalMale);
                report.setTotalFemale(totalFemale);
                //
                Double p[] = totalPoint(listStudent);
                report.setTotalPass(p[0].intValue());
                report.setTotalFail(p[1].intValue());
                //
                report.setAverages(Math.round(p[2] * 100.0) / 100.0);
                listReport.add(report);
            }
        }
        return listReport;
    }

    @Override
    public int totalMale(ArrayList<Student> listAll) {
        int totalMale = 0;
        for (Student st : listAll) {
            if ("Nam".equals(st.getGender())) {
                totalMale++;
            }
        }
        return totalMale;
    }

    @Override
    public Double[] totalPoint(ArrayList<Student> listAll) {
        double totalPass = 0;
        double totalFail = 0;
        double point = 0.0;
        double averages = 0.0;
        for (Student st : listAll) {
            for (Point p : st.getPoint()) {
                point = 0;
                point = (p.getPoint_HW() * 2 + p.getPoint_Mid() * 2 + p.getPoint_Final() * 6) / 10;
                averages += point;
                if (point >= 4) {
                    totalPass++;
                }
            }
        }
        int size = listAll.size();
        totalFail = size - totalPass;
        averages = averages / size;
        return new Double[]{totalPass, totalFail, averages};
    }

    @Override
    public int totalFemale(int size, int numMale) {
        return size - numMale;
    }

}
