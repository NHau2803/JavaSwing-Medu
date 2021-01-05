/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author HAU28
 */
public class Point implements Serializable {
    
    private String nameSub;
    private Double Point_HW;
    private Double Point_Mid;
    private Double Point_Final;
    private String note;

    public Point(String nameSub, Double Point_HW, Double Point_Mid, Double Point_Final, String note) {
        this.nameSub = nameSub;
        this.Point_HW = Point_HW;
        this.Point_Mid = Point_Mid;
        this.Point_Final = Point_Final;
        this.note = note;
    }

    public String getNameSub() {
        return nameSub;
    }

    public void setNameSub(String nameSub) {
        this.nameSub = nameSub;
    }

    public Double getPoint_HW() {
        return Point_HW;
    }

    public void setPoint_HW(Double Point_HW) {
        this.Point_HW = Point_HW;
    }

    public Double getPoint_Mid() {
        return Point_Mid;
    }

    public void setPoint_Mid(Double Point_Mid) {
        this.Point_Mid = Point_Mid;
    }

    public Double getPoint_Final() {
        return Point_Final;
    }

    public void setPoint_Final(Double Point_Final) {
        this.Point_Final = Point_Final;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CPoint{" + "nameSub=" + nameSub + ", Point_HW=" + Point_HW + ", Point_Mid=" + Point_Mid + ", Point_Final=" + Point_Final + ", note=" + note + '}';
    }

    

    
    
    
    

    
}
