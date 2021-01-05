/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import model.Point;
import model.Student;

/**
 *
 * @author HAU28
 */
public interface StudentServiceInterface {

    public void hangdlingSave(String id, String name, String phone, String address, String birthday, String gender, String yourClass);

    void setNameTable(String yourClass);

    public ArrayList<Student> getList(String yourClass);

    public int getSize();

    public void deleteStudentAndAccount(String id, String yourClass);

    public void deleteStudent(String id, String yourClass);

    public void deleteClass(String yourClass);

    public void deleteSubject(String id, String nameSub, String yourClass);

    public ArrayList<Point> findTablePoint(String id, String yourClass);

    public void addSubject(String id, String name, String subject, String yourClass);

    public void updateAll(ArrayList<Point> LPoint, String id, String name, String phone, String address, String birthday, String gender, String yourClass);

    public ArrayList<Student> search(String keySearch, String yourClass);

    public Student findOne(String id, String yourClass);

    public int changeStudentToClassOther(String id, String yourClassFirst, String yourClass);

}
