/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import daoImpl.AccountDAO;
import common.Constant;
import functional.impl.Md5;
import java.util.ArrayList;
import model.Account;
import service.AccountServiceInterface;

/**
 *
 * @author HAU28
 */
public class AccountService implements AccountServiceInterface {

    private final AccountDAO aDAO = new AccountDAO();
    private final Md5 md5 = new Md5();

    @Override
    public void createAccout(String id, String yourClass) {
        String firstPass = "@" + id;
        Account account = new Account(id, encrypt(firstPass), yourClass);
        aDAO.insertAccount(account, Constant.nameDB, Constant.nameTableAccount);
    }

    @Override
    public void deleteAccout(String id, String yourClass) {
        aDAO.deleteAccount(id, Constant.nameDB, Constant.nameTableAccount);
    }

    @Override
    public int updateAccoutByPassword(String user, String pass, String passNew) {
        try {
            Account account = aDAO.checkExist(user, md5.getMd5(pass), Constant.nameDB, Constant.nameTableAccount);
            account.setPass(md5.getMd5(passNew));
            aDAO.updateAccount(account, Constant.nameDB, Constant.nameTableAccount);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    @Override
    public ArrayList<Account> findAll() {
        return aDAO.findAll(Constant.nameDB, Constant.nameTableAccount);
    }

    @Override
    public ArrayList<Account> findByID(String keySearch) {
        return aDAO.findByID(keySearch, Constant.nameDB, Constant.nameTableAccount);
    }

    @Override
    public void deleteAccoutOfClass(String yourClass) {

        ArrayList<Account> list = findAll();
        for (Account a : list) {
            if (a.getYourClass().equals(yourClass)) {
                aDAO.deleteAccount(a.getUser(), Constant.nameDB, Constant.nameTableAccount);
            }

        }

    }

    @Override
    public int updateAccout(String user, String pass, String yourClass) {
        try {
            Account a = new Account(user, pass, yourClass);
            aDAO.updateAccount(a, Constant.nameDB, Constant.nameTableAccount);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

    }

    @Override
    public String encrypt(String s) {
        return md5.getMd5(s);
    }

}
