/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import daoImpl.AccountDAO;
import daoImpl.StudentDAO;
import common.Constant;
import functional.impl.HandlingTxtService;
import java.util.ArrayList;
import model.Point;
import model.Student;
import service.StudentServiceInterface;

/**
 *
 * @author HAU28
 */
public class StudentService implements StudentServiceInterface {

    public static ArrayList<Student> ListStudent;
    private static ArrayList<String> LSubject;
    private static ArrayList<Point> LPoint;
    private static String nameTable, nameTablePoint = "";
    private final StudentDAO sDAO = new StudentDAO();
    private final AccountDAO aDAO = new AccountDAO();
    private final HandlingTxtService hts = new HandlingTxtService();

    @Override
    public void setNameTable(String yourClass) {
        nameTable = yourClass;
        nameTablePoint = yourClass + "_p";
    }

    @Override
    public void hangdlingSave(String id, String name, String phone, String address, String birthday, String gender, String yourClass) {
        LSubject = new ArrayList<>();
        LPoint = new ArrayList<>();
        LSubject = hts.getTimeTable("TimeTable.txt");
        for (String subject : LSubject) {
            Point row = new Point(subject, 0.0, 0.0, 0.0, "note");
            LPoint.add(row);
        }
        Student student = new Student(
                LPoint, id, name,
                phone, birthday, gender,
                address, yourClass);
        setNameTable(yourClass);
        int check = sDAO.insertStudent(student, Constant.nameDB, nameTable, nameTablePoint);
        if (check == -1) {
            sDAO.createTable(student, Constant.nameDB, nameTable, nameTablePoint);
            sDAO.insertStudent(student, Constant.nameDB, nameTable, nameTablePoint);
        }

    }

    @Override
    public ArrayList<Student> getList(String yourClass) {
        setNameTable(yourClass);
        ListStudent = new ArrayList<>();
        ListStudent = sDAO.findAll(Constant.nameDB, nameTable, nameTablePoint);
        return ListStudent;
    }

    private static int count = 0;

    @Override
    public int getSize() {
        if (count == 0) {
            ListStudent = new ArrayList<>();
            ListStudent = sDAO.findAll(Constant.nameDB, "IT06", "IT06_p");
            count++;
        }
        System.out.println(count);
        System.out.println(ListStudent.size());
        return ListStudent.size();
    }

    @Override
    public void deleteStudentAndAccount(String id, String yourClass) {
        aDAO.deleteAccount(id, Constant.nameDB, "account");
        setNameTable(yourClass);
        sDAO.deleteStudent(id, Constant.nameDB, nameTable, nameTablePoint);
    }

    @Override
    public void deleteSubject(String id, String nameSub, String yourClass) {
        setNameTable(yourClass);
        sDAO.deleteSubjcet(id, nameSub, Constant.nameDB, nameTablePoint);

    }

    @Override
    public ArrayList<Point> findTablePoint(String id, String yourClass) {
        setNameTable(yourClass);
        return sDAO.findTablePoint(id, Constant.nameDB, nameTablePoint);
    }

    @Override
    public void addSubject(String id, String name, String subject, String yourClass) {
        Point point = new Point(subject, 0.0, 0.0, 0.0, "note");
        setNameTable(yourClass);
        sDAO.insertPoint(id, name, point, Constant.nameDB, nameTablePoint);
    }

    @Override
    public void updateAll(ArrayList<Point> LPoint, String id, String name, String phone, String address, String birthday, String gender, String yourClass) {
        Student student = new Student(
                LPoint, id, name,
                phone, birthday, gender,
                address, yourClass);
        setNameTable(yourClass);
        sDAO.updateStudent(student, Constant.nameDB, nameTable, nameTablePoint);
    }

    @Override
    public ArrayList<Student> search(String keySearch, String yourClass) {
        setNameTable(yourClass);
        //ListStudent= new ArrayList<Student>();
        ListStudent = sDAO.findByID(keySearch, Constant.nameDB, nameTable, nameTablePoint);
        return ListStudent;
    }

    @Override
    public void deleteClass(String yourClass) {
        setNameTable(yourClass);
        sDAO.dropTable(Constant.nameDB, nameTable);
        sDAO.dropTable(Constant.nameDB, nameTablePoint);

    }

    @Override
    public Student findOne(String id, String yourClass) {
        setNameTable(yourClass);
        return sDAO.findOne(id, Constant.nameDB, nameTable, nameTablePoint);
    }

    @Override
    public int changeStudentToClassOther(String id, String yourClassFirst, String yourClass) {

        try {
            setNameTable(yourClassFirst);
            Student student = sDAO.findOne(id, Constant.nameDB, nameTable, nameTablePoint);
            student.setYourClass(yourClass);
            sDAO.deleteStudent(id, Constant.nameDB, nameTable, nameTablePoint);
            setNameTable(yourClass);
            sDAO.insertStudent(student, Constant.nameDB, nameTable, nameTablePoint);
            sDAO.updateStudent(student, Constant.nameDB, nameTable, nameTablePoint);
        } catch (Exception e) {
            System.out.println("changeStudentToClassOther" + e);
            return -1;
        }
        return 1;
    }

    @Override
    public void deleteStudent(String id, String yourClass) {
        setNameTable(yourClass);
        sDAO.deleteStudent(id, Constant.nameDB, nameTable, nameTablePoint);
    }

}
