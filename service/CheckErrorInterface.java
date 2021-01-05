/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author HAU28
 */
public interface CheckErrorInterface {

    public boolean checkDuplicateID(String id, String yourClass);

    public boolean checkException(String id, String name, String phone,
            String gender, String dd, String mm, String yyyy);

    public boolean checkFormatDay(String dd);

    public boolean checkFormatMonth(String mm);

    public boolean checkFormatYear(String yyyy);

    public String checkLoginAndFindOne(String user, String pass);

    //public int checkLogin(String user, String pass);

    public int checkAccount(String user, String pass, String pass1, String pass2);

    public boolean checkUserName(String userName);

}
