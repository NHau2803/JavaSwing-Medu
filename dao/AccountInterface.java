/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Account;
import model.Student;

/**
 *
 * @author HAU28
 */
public interface AccountInterface {

    public ArrayList<Account> findAll(String nameDB, String nameTable);

    public Account checkExist(String userName, String password, String nameDB, String nameTable);

    public int checkExistUserName(String userName, String nameDB, String nameTable);

    // public Account checkAccount(String userName, String password, String nameDB, String nameTable);
    public Student getDataStudent(String userName, String yourClass, String nameDB);

    public void insertAccount(Account acount, String nameDB, String nameTable);

    public void updateAccount(Account acount, String nameDB, String nameTable);

    public void deleteAccount(String username, String nameDB, String nameTable);

    public ArrayList<Account> findByID(String keySearch, String nameDB, String nameTable);
}
