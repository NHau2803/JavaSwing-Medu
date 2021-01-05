/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional.impl;

import functional.HandlingFileTxtInterface;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Student;

/**
 *
 * @author HAU28
 */
public class HandlingTxtService implements HandlingFileTxtInterface {

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    private DataOutputStream dos = null;
    private ObjectInputStream ois = null;
    private FileInputStream fis = null;
    private DataInputStream dis = null;
    private File f = null;

    @Override
    public void setOutputStream() {
        oos = null;
        fos = null;
        dos = null;
    }

    @Override
    public void setInputStream() {
        ois = null;
        fis = null;
        dis = null;
    }

    @Override
    public void setNameFile(String nameFile) {
        f = new File(nameFile);
    }

    @Override
    public void writeInformation(Student student, String nameFile) {
        setNameFile(nameFile);
        setOutputStream();
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fos != null || oos != null) {
                try {
                    fos.close();
                    oos.close();
                    setTime();
                } catch (IOException e) {
                    System.out.println("w txt: " + e);
                }
            }
        }
    }

    @Override
    public Student getInfomation(String nameFile) {
        setNameFile(nameFile);
        Student student = new Student();
        setInputStream();
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                while (true) {
                    student = (Student) ois.readObject();
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }

        } catch (IOException e) {
            System.out.println("get info: " + e);
        }
        return student;
    }

    @Override
    public void setTime() {
        Date date = new Date();
        setNameFile("UpdateTime.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(date.toString());
            fw.flush();
        } catch (IOException e) {
            System.out.println("Update Time Error!" + e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println("set time: " + e);
                }
            }
        }
    }

    @Override
    public String getTime() {
        setNameFile("UpdateTime.txt");
        FileReader fr = null;
        BufferedReader br = null;
        String dateEnd = "";
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            dateEnd = br.readLine();
        } catch (IOException e) {
            System.out.println("Get Time Error!" + e);
        } finally {
            if (fr != null && br != null) {
                try {
                    fr.close();
                    br.close();
                } catch (IOException e) {
                    System.out.println("get time: " + e);
                }
            }
        }
        return dateEnd;
    }

    @Override
    public ArrayList<String> readLedger(String nameFile) {
        setNameFile(nameFile);
        FileReader fr = null;
        BufferedReader br = null;
        String nameClass = "";
        ArrayList<String> ledger = new ArrayList<>();
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while ((nameClass = br.readLine()) != null) {
                ledger.add(nameClass);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fr != null && br != null) {
                try {
                    fr.close();
                    br.close();
                } catch (IOException e) {
                    System.out.println("get ledger: " + e);
                }
            }
        }
        return ledger;
    }

    @Override
    public void setTimeTable(ArrayList<String> LSubject, String nameFile) {
        setNameFile(nameFile);
        setOutputStream();
        try {
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            for (String subject : LSubject) {
                dos.writeUTF(subject);
                System.out.println(subject);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fos != null || oos != null) {
                try {
                    fos.close();
                    dos.close();

                    setTime();
                } catch (IOException e) {
                    System.out.println("set timetable: " + e);
                }
            }
        }
    }

    @Override
    public ArrayList<String> getTimeTable(String nameFile) {
        ArrayList<String> LSubject = new ArrayList<>();
        setNameFile(nameFile);
        setInputStream();
        String s = "";
        try {
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            while (true) {
                s = dis.readUTF();
                LSubject.add(s);
            }
        } catch (IOException e) {
            System.out.println("get timetable: " + e);
        }
        return LSubject;
    }

    @Override
    public void setLanguage(String language) {
        try {
            FileWriter fw = new FileWriter("Language.txt");
            fw.write(language);
            fw.close();
        } catch (IOException e) {
            System.out.println("set lang" + e);
        }
    }

    @Override
    public String getLanguage() {
        String language = "";
        try {
            FileReader fr = new FileReader("Language.txt");
            BufferedReader bf = new BufferedReader(fr);
            language = bf.readLine();
        } catch (FileNotFoundException efn) {
            System.out.println("get lang: " + efn);
        } catch (IOException e) {
            System.out.println("get lang: " + e);
        }
        return language;
    }

    @Override
    public void saveInfoAccount(Account account, String nameFile) {
        setNameFile(nameFile);
        setOutputStream();
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(account);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fos != null || oos != null) {
                try {
                    fos.close();
                    oos.close();
                    setTime();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public Account getInfoAccount(String nameFile) {
        Account account = new Account();
        setNameFile(nameFile);
        setInputStream();
        try {
            fis = new FileInputStream(nameFile);
            ois = new ObjectInputStream(fis);
            try {
                while (true) {
                    account = (Account) ois.readObject();
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return account;
    }

}
