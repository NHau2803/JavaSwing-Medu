
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.StudentInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Point;
import model.Student;

/**
 *
 * @author HAU28
 */
public class StudentDAO implements StudentInterface {

    private final ConnectJDBC connectjdbc = new ConnectJDBC();
    private static StringBuilder sql = new StringBuilder();
    private static StringBuilder sql2 = new StringBuilder();

    @Override
    public ArrayList<Point> findTablePoint(String id, String nameDB, String nameTablePoint) {
        ArrayList<Point> pointList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            sql = new StringBuilder();
            sql.append("select * from ");
            sql.append(nameTablePoint);
            sql.append(" where id='");
            sql.append(id).append("'");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(sql));
            while (resultSet.next()) {
                Point p = new Point(resultSet.getString("nameSubject"),
                        resultSet.getDouble("homeWork"), resultSet.getDouble("midExam"),
                        resultSet.getDouble("finalExam"), resultSet.getString("note"));
                pointList.add(p);
            }
        } catch (SQLException e) {
            System.out.println("find table point exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return pointList;
    }

    @Override
    public ArrayList<Student> findAll(String nameDB, String nameTable, String nameTablePoint) {
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Point> pointList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            sql = new StringBuilder();
            sql.append("select * from ");
            sql.append(nameDB);
            sql.append(".");
            sql.append(nameTable);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(sql));
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                pointList = findTablePoint(id, nameDB, nameTablePoint);
                Student std = new Student(pointList,
                        id, resultSet.getString("name"),
                        resultSet.getString("phone"), resultSet.getString("birthday"),
                        resultSet.getString("gender"), resultSet.getString("address"),
                        resultSet.getString("yourClass"));

                studentList.add(std);
            }
        } catch (SQLException e) {
            System.out.println("find all exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return studentList;
    }

    @Override
    public void createTable(Student student, String nameDB, String nameTable, String nameTablePoint) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        try {
            connection = connectjdbc.getConnection(nameDB);

            sql = new StringBuilder();
            sql.append("create table ");
            sql.append(nameDB);
            sql.append(".");
            sql.append(nameTable);
            sql.append("(id VARCHAR(15)  NOT NULL,\n");
            sql.append("name VARCHAR(30) NOT NULL ,\n");
            sql.append("birthday VARCHAR(45) NOT NULL ,\n");
            sql.append("address VARCHAR(45) NOT NULL ,\n");
            sql.append("gender VARCHAR(5) NOT NULL ,\n");
            sql.append("phone VARCHAR(15) NOT NULL ,\n");
            sql.append("yourClass VARCHAR(20) NOT NULL,\n");
            sql.append("PRIMARY KEY(id));");
            statement = connection.prepareStatement(String.valueOf(sql));
            statement.execute();

            sql2 = new StringBuilder();
            sql2.append("create table ");
            sql2.append(nameDB);
            sql2.append(".");
            sql2.append(nameTablePoint);
            sql2.append("(id VARCHAR(15) NOT NULL ,\n");
            sql2.append("name VARCHAR(45) NOT NULL ,\n");
            sql2.append("nameSubject VARCHAR(45) NOT NULL ,\n");
            sql2.append("homeWork DOUBLE NOT NULL ,\n");
            sql2.append("midExam DOUBLE NOT NULL ,\n");
            sql2.append("finalExam DOUBLE NOT NULL,\n");
            sql2.append("note VARCHAR(45) NOT NULL);");
            statement2 = connection.prepareStatement(String.valueOf(sql2));
            statement2.execute();

        } catch (SQLException e) {
            System.out.println("creat table exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
            connectjdbc.closeConnection(statement2, connection);
        }
        insertStudent(student, nameDB, nameTable, nameTablePoint);
    }

    @Override
    public void insertPoint(String id, String name, Point point, String nameDB, String nameTablePoint) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //lay tat ca danh sach sinh vien
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("insert into ");
            sql.append(nameTablePoint);
            sql.append(" (id, name, nameSubject, homeWork, midExam, finalExam, note)");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?);");
            statement = connection.prepareCall(String.valueOf(sql));

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, point.getNameSub());
            statement.setDouble(4, point.getPoint_HW());
            statement.setDouble(5, point.getPoint_Mid());
            statement.setDouble(6, point.getPoint_Final());
            statement.setString(7, point.getNote());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("insert ponit exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
    }

    @Override
    public int insertStudent(Student student, String nameDB, String nameTable, String nameTablePoint) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("insert into ");
            sql.append(nameTable);
            sql.append("(id, name, birthday, address, gender, phone, yourClass)");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?)");
            //
            sql2 = new StringBuilder();
            sql2.append("insert into ");
            sql2.append(nameTablePoint);
            sql2.append("(id, name, nameSubject, homeWork, midExam, finalExam, note)");
            sql2.append("values (?, ?, ?, ?, ?, ?, ?)");

            statement = connection.prepareCall(String.valueOf(sql));
            statement2 = connection.prepareCall(String.valueOf(sql2));

            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getBirthday());
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getGender());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getYourClass());
            statement.execute();
            //table point
            ArrayList<Point> point = student.getPoint();
            for (Point cPoint : point) {
                statement2.setString(1, student.getId());
                statement2.setString(2, student.getName());
                statement2.setString(3, cPoint.getNameSub());
                statement2.setDouble(4, cPoint.getPoint_HW());
                statement2.setDouble(5, cPoint.getPoint_Mid());
                statement2.setDouble(6, cPoint.getPoint_Final());
                statement2.setString(7, cPoint.getNote());
                statement2.execute();
            }

        } catch (SQLException e) {
            System.out.println("insert stundet exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
            connectjdbc.closeConnection(statement2, connection);
        }
        return 1;
    }

    @Override
    public void updateStudent(Student student, String nameDB, String nameTable, String nameTablePoint) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("update ");
            sql.append(nameDB);
            sql.append(".");
            sql.append(nameTable);
            sql.append(" set name=?,birthday=?,address=?,gender=?,phone=?,yourClass=? where id=?");

            sql2 = new StringBuilder();
            sql2.append("update ");
            sql2.append(nameDB);
            sql2.append(".");
            sql2.append(nameTablePoint);
            sql2.append(" set name=?,homeWork=?,midExam=?,finalExam=?,note=?");
            sql2.append(" where id=? and nameSubject=?");

            statement = connection.prepareCall(String.valueOf(sql));
            statement2 = connection.prepareCall(String.valueOf(sql2));

            statement.setString(1, student.getName());
            statement.setString(2, student.getBirthday());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getGender());
            statement.setString(5, student.getPhone());
            statement.setString(6, student.getYourClass());
            statement.setString(7, student.getId());
            statement.execute();
            //table point
            ArrayList<Point> point = student.getPoint();
            for (Point p : point) {
                statement2.setString(1, student.getName());
                statement2.setString(7, p.getNameSub());
                statement2.setDouble(2, p.getPoint_HW());
                statement2.setDouble(3, p.getPoint_Mid());
                statement2.setDouble(4, p.getPoint_Final());
                statement2.setString(5, p.getNote());
                statement2.setString(6, student.getId());
                statement2.execute();
            }

        } catch (SQLException e) {
            System.out.println("update student exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
            connectjdbc.closeConnection(statement2, connection);
        }
    }

    @Override
    public void deleteStudent(String id, String nameDB, String nameTable, String nameTablePoint) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(nameTable);
            sql.append(" where id = ?");
            //
            sql2 = new StringBuilder();
            sql2.append("delete from ");
            sql2.append(nameTablePoint);
            sql2.append(" where id = ?");

            statement = connection.prepareCall(String.valueOf(sql));
            statement2 = connection.prepareCall(String.valueOf(sql2));

            statement.setString(1, id);
            statement2.setString(1, id);

            statement.execute();
            statement2.execute();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            connectjdbc.closeConnection(statement, connection);
            connectjdbc.closeConnection(statement2, connection);
        }
    }

    @Override
    public void deleteSubjcet(String id, String nameSubject, String nameDB, String nameTablePoint) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(nameTablePoint);
            sql.append(" where id =? and nameSubject = ?");
            statement = connection.prepareCall(String.valueOf(sql));
            statement.setString(1, id);
            statement.setString(2, nameSubject);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("del sub exc: " + e);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
    }

    @Override
    public void dropTable(String nameDB, String nameTable) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("drop table ");
            sql.append(nameDB);
            sql.append(".");
            sql.append(nameTable);
            statement = connection.prepareCall(String.valueOf(sql));
            statement.execute();
        } catch (SQLException e) {
            System.out.println("drop exc: " + e);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
    }

    @Override
    public ArrayList<Student> findByID(String keySearch, String nameDB, String nameTable, String nameTablePoint) {
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Point> pointList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            //query
            sql = new StringBuilder();
            sql.append("select * from ");
            sql.append(nameTable);
            sql.append(" where id like ?");
            statement = connection.prepareCall(String.valueOf(sql));
            StringBuilder sqlSearch = new StringBuilder();
            sqlSearch.append("%");
            sqlSearch.append(keySearch);
            sqlSearch.append("%");
            statement.setString(1, String.valueOf(sqlSearch));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                pointList = findTablePoint(id, nameDB, nameTablePoint);
                Student std = new Student(
                        pointList,
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("birthday"),
                        resultSet.getString("gender"),
                        resultSet.getString("address"),
                        resultSet.getString("yourClass"));
                studentList.add(std);
            }
        } catch (SQLException e) {
            System.out.println("find id exc: " + e);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return studentList;
    }

    @Override
    public Student findOne(String id, String nameDB, String nameTable, String nameTablePoint) {
        Student student = new Student();
        ArrayList<Point> pointList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            sql = new StringBuilder();
            sql.append("select * from ");
            sql.append(nameDB);
            sql.append(".");
            sql.append(nameTable);
            sql.append(" where id=");
            sql.append(id);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(sql));
            pointList = findTablePoint(id, nameDB, nameTablePoint);
            while (resultSet.next()) {
                student = new Student(pointList,
                        id, resultSet.getString("name"),
                        resultSet.getString("phone"), resultSet.getString("birthday"),
                        resultSet.getString("gender"), resultSet.getString("address"),
                        resultSet.getString("yourClass"));
            }
        } catch (SQLException e) {
            System.out.println("find one exc: " + e);

        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return student;
    }

}
