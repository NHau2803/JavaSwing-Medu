/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import model.Account;

/**
 *
 * @author HAU28
 */
public interface AccountServiceInterface {

    public void createAccout(String id, String yourClass);

    public void deleteAccout(String id, String yourClass);

    public int updateAccoutByPassword(String user, String pass, String passNew);

    public int updateAccout(String user, String pass, String yourClass);
    
    public ArrayList<Account> findAll();

    public ArrayList<Account> findByID(String keySearch);

    public void deleteAccoutOfClass(String yourClass);
    
    public String encrypt(String s);
}
