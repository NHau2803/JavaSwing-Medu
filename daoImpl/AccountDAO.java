/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.AccountInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Account;
import model.Point;
import model.Student;

/**
 *
 * @author HAU28
 */
public class AccountDAO implements AccountInterface {

    private final ConnectJDBC connectjdbc = new ConnectJDBC();

    @Override
    public ArrayList<Account> findAll(String nameDB, String nameTable) {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Account> listAccounts = new ArrayList<>();
        Account account = new Account();
        try {
            connection = connectjdbc.getConnection(nameDB);
            //
            String sql = "select * from " + nameDB + "." + nameTable;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                account = new Account(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("yourClass"));
                listAccounts.add(account);
            }
        } catch (SQLException e) {
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return listAccounts;
    }

    @Override
    public Account checkExist(String userName, String password, String nameDB, String nameTable) {
        Connection connection = null;
        Statement statement = null;
        Account a = new Account();
        try {
            connection = connectjdbc.getConnection(nameDB);
            String user = " where username='" + userName + "'";
            String pass = " and password='" + password + "'";
            String sql = "select * from " + nameDB + "." + nameTable + user + pass;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                a = new Account(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("yourClass"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return a;
    }

    @Override
    public int checkExistUserName(String userName, String nameDB, String nameTable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student getDataStudent(String userName, String yourClass, String nameDB) {
        Student student = new Student();
        ArrayList<Point> pointList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;

        try {
            connection = connectjdbc.getConnection(nameDB);

            //query
            String nameTable = yourClass;
            String sql = "select * from " + nameTable + " where id='" + userName + "'";
            statement = connection.createStatement();

            String nameTablePoint = yourClass + "_p";
            String sql2 = "select * from " + nameTablePoint + " where id='" + userName + "'";
            statement2 = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            ResultSet resultSet2 = statement2.executeQuery(sql2);

            while (resultSet2.next()) {
                Point p = new Point(resultSet2.getString("nameSubject"),
                        resultSet2.getDouble("homeWork"), resultSet2.getDouble("midExam"),
                        resultSet2.getDouble("finalExam"), resultSet2.getString("note"));

                pointList.add(p);
            }
            while (resultSet.next()) {
                student = new Student(pointList,
                        resultSet.getString("id"), resultSet.getString("name"),
                        resultSet.getString("phone"), resultSet.getString("birthday"),
                        resultSet.getString("gender"), resultSet.getString("address"),
                        resultSet.getString("yourClass"));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectjdbc.closeConnection(statement, connection);
            connectjdbc.closeConnection(statement2, connection);
        }

        return student;
    }

    @Override
    public void insertAccount(Account acount, String nameDB, String nameTable) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            String sql = "insert into " + nameTable + "(username, password, yourClass) values(?, ?, ?)";
            statement = connection.prepareCall(sql);
            statement.setString(1, acount.getUser());
            statement.setString(2, acount.getPass());
            statement.setString(3, acount.getYourClass());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
    }

    @Override
    public void updateAccount(Account acount, String nameDB, String nameTable) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            String sql = "update " + nameTable + " set password=?, yourClass=? where username=?";
            statement = connection.prepareCall(sql);
            statement.setString(1, acount.getPass());
            statement.setString(2, acount.getYourClass());
            statement.setString(3, acount.getUser());
            statement.execute();

        } catch (SQLException ex) {
            //Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
    }

    @Override
    public void deleteAccount(String username, String nameDB, String nameTable) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //lay tat ca danh sach sinh vien
            connection = connectjdbc.getConnection(nameDB);
            //query
            String sql = "delete from " + nameTable + " where username = " + username;
            statement = connection.prepareCall(sql);
            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
    }

    @Override
    public ArrayList<Account> findByID(String keySearch, String nameDB, String nameTable) {
        ArrayList<Account> listAccount = new ArrayList<>();
        Account a = new Account();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectjdbc.getConnection(nameDB);
            String sql = "select * from " + nameTable + " where username like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + keySearch + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                a = new Account(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("yourClass"));
                listAccount.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectjdbc.closeConnection(statement, connection);
        }
        return listAccount;
    }

}
