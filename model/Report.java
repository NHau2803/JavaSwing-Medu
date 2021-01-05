/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HAU28
 */
public class Report {
    private String nameClass;
    private int toltalStudent;
    private int totalMale ;
    private int totalFemale;
    private int totalPass;
    private int totalFail;
    private double averages;
    
    

    public Report() {
    }

    public Report(String nameClass, int toltalStudent, int totalMale, int totalFemale, int totalPass, int totalFail, double averages) {
        this.nameClass = nameClass;
        this.toltalStudent = toltalStudent;
        this.totalMale = totalMale;
        this.totalFemale = totalFemale;
        this.totalPass = totalPass;
        this.totalFail = totalFail;
        this.averages = averages;
    }
    
    

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getToltalStudent() {
        return toltalStudent;
    }

    public void setToltalStudent(int toltalStudent) {
        this.toltalStudent = toltalStudent;
    }

    public int getTotalMale() {
        return totalMale;
    }

    public void setTotalMale(int totalMale) {
        this.totalMale = totalMale;
    }

    public int getTotalFemale() {
        return totalFemale;
    }

    public void setTotalFemale(int totalFemale) {
        this.totalFemale = totalFemale;
    }

    public int getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(int totalPass) {
        this.totalPass = totalPass;
    }

    public int getTotalFail() {
        return totalFail;
    }

    public void setTotalFail(int totalFail) {
        this.totalFail = totalFail;
    }

    public double getAverages() {
        return averages;
    }

    public void setAverages(double averages) {
        this.averages = averages;
    }

    @Override
    public String toString() {
        return "Report{" + "nameClass=" + nameClass + ", toltalStudent=" + toltalStudent + ", totalMale=" + totalMale + ", totalFemale=" + totalFemale + ", totalPass=" + totalPass + ", totalFail=" + totalFail + ", averages=" + averages + '}';
    }

  

    

   
}
