/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import daoImpl.AccountDAO;
import common.Constant;
import functional.impl.HandlingTxtService;
import java.util.ArrayList;
import model.Account;
import model.Student;
import service.CheckErrorInterface;

/**
 *
 * @author HAU28
 */
public class CheckError implements CheckErrorInterface {

    private final AccountDAO aDAO = new AccountDAO();
    private final HandlingTxtService hts = new HandlingTxtService();
    private final AccountService as = new AccountService();

    @Override
    public boolean checkDuplicateID(String id, String yourClass) {
        StudentService ss = new StudentService();
        ArrayList<Student> list = ss.getList(yourClass);
        for (Student st : list) {
            if (id.equals(st.getId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkFormatDay(String dd) {
        try {
            if (dd.length() != 2) {
                return false;
            }
            int day = Integer.valueOf(dd);
            if (day > 31 || day < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkFormatMonth(String mm) {
        try {
            if (mm.length() != 2) {
                return false;
            }
            int month = Integer.valueOf(mm);
            if (month > 12 || month < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkFormatYear(String yyyy) {
        try {
            if (yyyy.length() != 4) {
                return false;
            }
            int year = Integer.valueOf(yyyy);
            if (year < 1990 || year > 2020) {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkException(String id, String name, String phone,
            String gender, String dd, String mm, String yyyy) {
        return !(id.length() == 0 || name.length() == 0 || phone.length() == 0
                || gender == "" || !checkFormatDay(dd)
                || !checkFormatMonth(mm) || !checkFormatYear(yyyy));
    }

    @Override
    public String checkLoginAndFindOne(String user, String pass) {
        try {
            Account account = aDAO.checkExist(user, as.encrypt(pass), Constant.nameDB, Constant.nameTableAccount);
            if (user.length() == 0 && pass.length() == 0) {
                return "notEnter";
            }
            if (Constant.user_admin.equals(user) && Constant.pass_admin.equals(pass)) {
                return "admin";
            }
            if (account.getUser() != null) {
                return account.getYourClass();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "notFound";
    }

//    @Override
//    public int checkLogin(String user, String pass) {
//        try {
//            Account account = aDAO.checkExist(user, as.encrypt(pass), Constant.nameDB, Constant.nameTableAccount);
//            if (user.length() == 0 && pass.length() == 0) {
//                return 1;
//            }
//            if (Constant.user_admin.equals(user) && Constant.pass_admin.equals(pass)) {
//                return 2;
//            }
//            if (account.getUser() != null) {
//                hts.saveInfoAccount(account, "info.txt");
//                return 3;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return -1;
//    }
    @Override
    public int checkAccount(String user, String pass, String pass1, String pass2) {
        try {
            Account account = aDAO.checkExist(user, as.encrypt(pass), Constant.nameDB, Constant.nameTableAccount);
            if (user.length() == 0 && pass.length() == 0) {
                return -1;
            }
            if (pass1 != pass2 && pass1.length() == 0) {
                return -1;
            }
            if (account.getUser() == null) {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("BUG " + e);
        }
        return 1;
    }

    @Override
    public boolean checkUserName(String userName) {
        return aDAO.checkExistUserName(userName, Constant.nameDB, Constant.nameTableAccount) == 1;
    }

}
