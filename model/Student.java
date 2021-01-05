/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;



/**
 *
 * @author HAU28
 */
public class Student implements Serializable{
    public ArrayList<Point> point;
    //public String[][] point;
    private String id;
    private String name;
    private String phone;
    private String birthday;
    private String gender;
    private String address;
    private String yourClass;
    

    public Student() {
    }

    public Student(ArrayList<Point> point, String id, String name, String phone, 
            String birthday, String gender, String address, String yourClass) {
        this.point = point;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.yourClass = yourClass;
    }
    public Student getStudent(){
        return this;
    }


    public ArrayList<Point> getPoint() {
        return point;
    }

    public void setPoint(ArrayList<Point> point) {
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYourClass() {
        return yourClass;
    }

    public void setYourClass(String yourClass) {
        this.yourClass = yourClass;
    }

    @Override
    public String toString() {
        return "CStudent{" + "point=" + point + ", id=" + id + ", name=" + name + ", phone=" + phone + ", birthday=" + birthday + ", gender=" + gender + ", address=" + address + ", yourClass=" + yourClass + '}';
    }
    


    
    
    
    

    

}
