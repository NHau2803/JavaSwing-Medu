/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Point;
import model.Student;

/**
 *
 * @author HAU28
 */
public interface StudentInterface {

    public ArrayList<Point> findTablePoint(String id, String nameDB, String nameTablePoint);

    public ArrayList<Student> findAll(String nameDB, String nameTable, String nameTablePoint);

    public void createTable(Student student, String nameDB, String nameTable, String nameTablePoint);

    public void insertPoint(String id, String name, Point point, String nameDB, String nameTablePoint);

    public int insertStudent(Student student, String nameDB, String nameTable, String nameTablePoint);

    public void updateStudent(Student student, String nameDB, String nameTable, String nameTablePoint);

    public void deleteStudent(String id, String nameDB, String nameTable, String nameTablePoint);

    public void deleteSubjcet(String id, String nameSubject, String nameDB, String nameTablePoint);

    public void dropTable(String nameTable, String nameDB);

    public ArrayList<Student> findByID(String keySearch, String nameDB, String nameTable, String nameTablePoint);
    
    public Student findOne(String id, String nameDB, String nameTable, String nameTablePoint);

}
