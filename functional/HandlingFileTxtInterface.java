/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import java.util.ArrayList;
import model.Account;
import model.Student;

/**
 *
 * @author HAU28
 */
public interface HandlingFileTxtInterface {

    public void setOutputStream();

    public void setInputStream();

    public void setNameFile(String nameFile);

    public void writeInformation(Student student, String nameFile);

    public Student getInfomation(String nameFile);

    public void setTime();

    public String getTime();

    public ArrayList<String> readLedger(String nameFile);

    public void setTimeTable(ArrayList<String> LSubject, String nameFile);

    public ArrayList<String> getTimeTable(String nameFile);

    public void setLanguage(String language);

    public String getLanguage();

    public void saveInfoAccount(Account account, String nameFile);

    public Account getInfoAccount(String nameFile);
}
