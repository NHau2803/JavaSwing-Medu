/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Constant;
import common.SystemArrayText;
import functional.impl.HandlingFileService;
import functional.impl.HandlingTxtService;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Point;
import model.Student;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import serviceImpl.AccountService;
import serviceImpl.CheckError;
import serviceImpl.StudentService;

/**
 *
 * @author HAU28
 */
public class ManagerLearningPoints extends javax.swing.JFrame {

    // ======== TODO : Declare
    private ArrayList<Student> LStudent;
    private ArrayList<String> LSubject;
    private ArrayList<Point> LPoint;
    DefaultTableModel TableStudent;
    DefaultTableModel TablePoint;

    private final AccountService accountService = new AccountService();
    private final StudentService studentService = new StudentService();
    private final CheckError check = new CheckError();
    private final HandlingFileService hfs = new HandlingFileService();
    private final HandlingTxtService txtService = new HandlingTxtService();
    private String language = txtService.getLanguage();
    private String title = "";
    int rowSelectTableStudent = -1;
    int rowSelce;
    private static int numberStudent;

//------------------Variable----------------------------------------------------------------------
    private static String id, name, phone, address, birthday,
            gender, yourClass, dd, mm, yyyy, nameSub = "";
    private static double PointHW, PointMid, PointFinal = 0.0;
    String note = "";

//------------------------------Dialog---------------------------------------------------------------
    private void showDialogError(String messageVn, String message) {
        int dialog = JOptionPane.ERROR_MESSAGE;
        title = Constant.invalid;
        if ("VN".equals(language)) {
            message = messageVn;
            title = Constant.inputError;
        }
        JOptionPane.showMessageDialog(this, message, title, dialog);
    }

    private int showDialogConfirm(String messageVn, String titleVn, String message, String title) {
        int dialog = JOptionPane.YES_NO_OPTION;
        int dialogResult = 0;
        if ("VN".equals(language)) {
            message = messageVn;
            title = titleVn;
        }
        dialogResult = JOptionPane.showConfirmDialog(this, message, title, dialog);
        return dialogResult;
    }

    private void showDialogMessage(String messageVn, String titleVn, String message, String title) {
        int dialog = JOptionPane.UNDEFINED_CONDITION;
        if ("VN".equals(language)) {
            title = titleVn;
            message = messageVn;
        }
        JOptionPane.showMessageDialog(this, title, message, dialog);
    }

//-------------------------Function---------------------------------------------------------------------
    private void reset() {
        jTextFieldID.setEnabled(true);
        jTextFieldID.setText("");
        jTextFieldName.setText("");
        jTextFieldPhone.setText("");
        jTextFieldAddress.setText("");
        buttonGroup1.clearSelection();
        jTextFieldDay.setText("");
        jTextFieldMonth.setText("");
        jTextFieldYear.setText("");
        TablePoint.setRowCount(0);

    }

    private void enabled(boolean bln) {
        jTextFieldID.setEnabled(false);
        jTextFieldName.setEnabled(bln);
        jTextFieldPhone.setEnabled(bln);
        jTextFieldAddress.setEnabled(bln);
        jRadioButtonMale.setEnabled(bln);
        jRadioButtonFemale.setEnabled(bln);
        jTextFieldDay.setEditable(bln);
        jTextFieldMonth.setEditable(bln);
        jTextFieldYear.setEditable(bln);
        jComboBoxClass.setEnabled(bln);
        jButtonSave.setEnabled(bln);
        jButtonDetele.setEnabled(bln);
        jButtonUpdateDataTablePoint.setEnabled(bln);
        jTextFieldSubject.setEnabled(bln);
        jButtonAdd.setEnabled(bln);
        jButtonDeleteSubject.setEnabled(bln);
        //
        jTablePoint.setEnabled(bln);
    }

    private void checkEdit() {
        if (jCheckBoxEdit.isSelected()) {
            enabled(true);
        } else {
            enabled(false);
        }
    }

    private String changeGender(String gender) {
        if ("EN".equals(language)) {
            if ("Nam".equals(gender)) {
                gender = "Male";
            } else {
                gender = "Female";
            }
        }
        return gender;
    }

    private void sentDataToTable(String yourClass) {
        ArrayList<Student> list = new ArrayList<>();
        list = studentService.getList(yourClass);
        LStudent = (ArrayList<Student>) list;
        int size = LStudent.size();
        if (size == 0) {
            showDialogMessage(
                    Constant.notFoundVn,
                    Constant.sorryVn,
                    Constant.notFound,
                    Constant.sorry);
            jLabelNumberStudent.setText("0");
            TableStudent.setRowCount(0);
        } else {
            jLabelNumberStudent.setText(String.valueOf(size));
            TableStudent.setRowCount(0);
            for (int i = 0; i < list.size(); i++) {
                Object obj[] = {
                    list.get(i).getId(), list.get(i).getName(),
                    list.get(i).getYourClass(),
                    changeGender(list.get(i).getGender()),
                    list.get(i).getBirthday(), list.get(i).getPhone(),
                    list.get(i).getAddress()
                };
                TableStudent.addRow(obj);
            }
        }

    }

    private void senDataToTablePoint(ArrayList<Point> point) {
        try {
            LPoint = new ArrayList<>();
            LPoint = point;
            TablePoint.setRowCount(0);
            for (int i = 0; i < point.size(); i++) {
                Object obj[] = {
                    point.get(i).getNameSub(),
                    point.get(i).getPoint_HW(),
                    point.get(i).getPoint_Mid(),
                    point.get(i).getPoint_Final(),
                    point.get(i).getNote()};
                TablePoint.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getGender() {
        gender = "";
        if (jRadioButtonMale.isSelected()) {
            gender = "Nam";
        }
        if (jRadioButtonFemale.isSelected()) {
            gender = "Nữ";
        }
        return gender;
    }

    private String getBirthDay() {
        dd = jTextFieldDay.getText();
        mm = jTextFieldMonth.getText();
        yyyy = jTextFieldYear.getText();
        birthday = dd + "/" + mm + "/" + yyyy;
        return birthday;
    }

    private void deleteRowTablePoint() {
        try {
            int rowTablePoint = jTablePoint.getSelectedRow();
            rowSelectTableStudent = jTableStudent.getSelectedRow();
            if (rowTablePoint == -1) {
                showDialogError(Constant.notSelectedVn, Constant.notSelected);
            } else {
                yourClass = jTableStudent.getValueAt(rowSelectTableStudent, 2).toString();
                id = jTableStudent.getValueAt(rowSelectTableStudent, 0).toString();
                nameSub = jTablePoint.getValueAt(rowTablePoint, 0).toString();
                int dialogResult = showDialogConfirm(
                        Constant.DeleteSubjectVn,
                        Constant.delete,
                        Constant.DeleteSubject,
                        Constant.delete);
                if (dialogResult == 0) {
                    studentService.deleteSubject(id, nameSub, yourClass);
                    LPoint = studentService.findTablePoint(id, yourClass);
                    senDataToTablePoint(LPoint);
                    sentDataToTable(yourClass);
                }
            }
        } catch (Exception e) {
            System.out.println("del sub: " + e);
            showDialogMessage("Xin Lỗi!", "Bạn hãy chọn 1 sinh viên trước khi xóa", "Sorry!", "You need chosse 1 student before remove");
        }

    }

    private void deleteStudent() {
        int row = jTableStudent.getSelectedRow();
        if (row == -1) {
            showDialogError(Constant.notSelectedVn, Constant.notSelected);
        } else {
            yourClass = String.valueOf(jTableStudent.getValueAt(row, 2));
            id = String.valueOf(jTableStudent.getValueAt(row, 0));
            int dialogResult = showDialogConfirm(
                    Constant.DeleteStudentVn,
                    Constant.deleteVn,
                    Constant.DeleteStudent,
                    Constant.delete);
            if (dialogResult == 0) {
                TableStudent.removeRow(row);
                studentService.deleteStudentAndAccount(id, yourClass);
                sentDataToTable(yourClass);
                reset();
            }
        }
    }

    private void addSubject() {

        String subject = jTextFieldSubject.getText();
        id = jTextFieldID.getText();

        if ("".equals(id)) {
            showDialogError(Constant.notSelectedVn, Constant.notSelected);
        } else {
            studentService.addSubject(id, name, subject, yourClass);
            LPoint = studentService.findTablePoint(id, yourClass);
            senDataToTablePoint(LPoint);
            sentDataToTable(yourClass);
            jTextFieldSubject.setText("");
        }

    }

    private ArrayList<Point> getDataOfTablePoint() {
        Student student = LStudent.get(rowSelectTableStudent);
        try {
            int row = jTablePoint.getRowCount();
            for (int i = 0; i < row; i++) {
                nameSub = jTablePoint.getValueAt(i, 0).toString();
                PointHW = Double.valueOf(jTablePoint.getValueAt(i, 1).toString());
                PointMid = Double.valueOf(jTablePoint.getValueAt(i, 2).toString());
                PointFinal = Double.valueOf(jTablePoint.getValueAt(i, 3).toString());
                note = jTablePoint.getValueAt(i, 4).toString();
                //
                student.getPoint().get(i).setNameSub(nameSub);
                student.getPoint().get(i).setPoint_HW(PointHW);
                student.getPoint().get(i).setPoint_Mid(PointMid);
                student.getPoint().get(i).setPoint_Final(PointFinal);
                student.getPoint().get(i).setNote(note);
            }
            System.out.println(student.getPoint().get(0).getNote());

        } catch (NumberFormatException e) {
            showDialogError(Constant.inputError, Constant.invalid);
            System.out.println(e);
        }
        return student.getPoint();
    }

    private void save() {
        id = jTextFieldID.getText();
        name = jTextFieldName.getText();
        phone = jTextFieldPhone.getText();
        address = jTextFieldAddress.getText();
        birthday = getBirthDay();
        gender = getGender();
        yourClass = String.valueOf(jComboBoxClass.getSelectedItem());
        if (!check.checkException(id, name, phone, gender, dd, mm, yyyy)) {
            showDialogError(Constant.inputError, Constant.invalid);
        } else if (!check.checkDuplicateID(id, yourClass)) {
            showDialogError(Constant.DupliacateIDVn, Constant.DupliacateID);
        } else {
            yourClass = String.valueOf(jComboBoxClass.getSelectedItem());
            studentService.hangdlingSave(id, name, phone, address, birthday, gender, yourClass);
            jComboBoxClassChooseItem.setSelectedItem(yourClass);
            sentDataToTable(yourClass);
            reset();
            accountService.createAccout(id, yourClass);
        }
    }

    private void updateStudent() {
        try {
            id = jTextFieldID.getText();
            name = jTextFieldName.getText();
            phone = jTextFieldPhone.getText();
            address = jTextFieldAddress.getText();
            birthday = getBirthDay();
            gender = getGender();
            yourClass = String.valueOf(jComboBoxClass.getSelectedItem());

            if (!check.checkException(id, name, phone, gender, dd, mm, yyyy)) {
                showDialogError(Constant.inputError, Constant.invalid);
            } else {
                LPoint = getDataOfTablePoint();
                studentService.updateAll(LPoint, id, name, phone, address, birthday, gender, yourClass);
                sentDataToTable(yourClass);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void setText() {
        jTextFieldID.setText(id);
        jTextFieldName.setText(name);
        jComboBoxClass.setSelectedItem(yourClass);
        if ("Female".equals(gender) || "Nữ".equals(gender)) {
            jRadioButtonFemale.setSelected(true);
        } else {
            jRadioButtonMale.setSelected(true);
        }
        String B[] = birthday.split("/");
        jTextFieldDay.setText(B[0]);
        jTextFieldMonth.setText(B[1]);
        jTextFieldYear.setText(B[2]);
        jTextFieldPhone.setText(phone);
        jTextFieldAddress.setText(address);
    }

    private void mouseClickTableStudent() {
        try {
            checkEdit();
            jTablePoint.setVisible(true);
            int i = jTableStudent.getSelectedRow();

            rowSelectTableStudent = i;
            TableModel modedClick = jTableStudent.getModel();
            //
            id = modedClick.getValueAt(i, 0).toString();
            name = modedClick.getValueAt(i, 1).toString();
            yourClass = modedClick.getValueAt(i, 2).toString();
            gender = modedClick.getValueAt(i, 3).toString();
            phone = modedClick.getValueAt(i, 5).toString();
            address = modedClick.getValueAt(i, 6).toString();
            //
            birthday = modedClick.getValueAt(i, 4).toString();
            setText();
            senDataToTablePoint(LStudent.get(i).getPoint());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void deleteClass() {
        int dialogResult = showDialogConfirm(
                Constant.DeleteClassVn,
                Constant.deleteVn,
                Constant.DeleteClass,
                Constant.delete);
        if (dialogResult == 0) {
            yourClass = jComboBoxClassChooseItem.getSelectedItem().toString();
            studentService.deleteClass(yourClass);
            accountService.deleteAccoutOfClass(yourClass);
            TableStudent.setRowCount(0);
            TablePoint.setRowCount(0);
            jLabelNumberStudent.setText("0");
        }

    }

    private void showClass() {
        try {
            yourClass = String.valueOf(jComboBoxClassChooseItem.getSelectedItem());
            sentDataToTable(yourClass);
            reset();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addHeaderToTable(String header[], String headerPoint[]) {
        TableStudent = new DefaultTableModel(header, 0);
        jTableStudent.setModel(TableStudent);
        TablePoint = new DefaultTableModel(headerPoint, 0);
        jTablePoint.setModel(TablePoint);
    }

    private void handlingAddHeaderToTable() {

        if ("VN".equals(language)) {
            addHeaderToTable(SystemArrayText.HeaderVn, SystemArrayText.HeaderPointVn);

        } else {
            addHeaderToTable(SystemArrayText.Header, SystemArrayText.HeaderPoint);
        }
    }

    private void setTextAndBorder(String ArrayText[]) {
        java.awt.Font f = new java.awt.Font("Tohoma", 24, 24);
        Border b = new TitledBorder("");
        jPanelManagerStudent.setBorder(new TitledBorder(b, ArrayText[0], 0, 0, f));
        jLabelID.setText(ArrayText[1]);
        jLabelName.setText(ArrayText[2]);
        jLabelPhone.setText(ArrayText[3]);
        jLabelBirthDay.setText(ArrayText[4]);
        jLabelGender.setText(ArrayText[5]);
        jLabelAddress.setText(ArrayText[6]);
        jLabelClass.setText(ArrayText[7]);
        jLabelNewSubject.setText(ArrayText[8]);
        jButtonAdd.setText(ArrayText[9]);
        jButtonDetele.setText(ArrayText[10]);
        jButtonReset.setText(ArrayText[11]);
        jButtonSave.setText(ArrayText[12]);
        jButtonUpdateDataTablePoint.setText(ArrayText[13]);
        jRadioButtonMale.setText(ArrayText[14]);
        jRadioButtonFemale.setText(ArrayText[15]);
        jCheckBoxEdit.setText(ArrayText[16]);
        jMenuFile.setText(ArrayText[17]);
        jMenuEdit.setText(ArrayText[18]);
        jMenuView.setText(ArrayText[19]);
        jMenuHelp.setText(ArrayText[20]);
        jMenuItemSave.setText(ArrayText[21]);
        jMenuItemUpdate.setText(ArrayText[22]);
        jMenuDelete.setText(ArrayText[23]);
        jMenuItemDeleteStudent.setText(ArrayText[24]);
        jMenuItemDeleteSubject.setText(ArrayText[25]);
        jMenuItemDeleteAll.setText(ArrayText[26]);
        jMenuItemRefresh.setText(ArrayText[27]);
        jMenuItemImportFileExcel.setText(ArrayText[28]);
        jMenuItemExportFileExccel.setText(ArrayText[29]);
        jMenuItemBack.setText(ArrayText[30]);
        jMenuItemExit.setText(ArrayText[31]);
        jMenuItemAddTimeTable.setText(ArrayText[32]);
        jMenuItemViewAsFileExcel.setText(ArrayText[33]);
        jMenuItemViewReport.setText(ArrayText[34]);
        jMenuItemViewTimeTable.setText(ArrayText[35]);
        jCheckBoxMenuItemFullScreen.setText(ArrayText[36]);
        jMenuLanguage.setText(ArrayText[37]);
        handlingAddHeaderToTable();
    }

    private void changeLanguage() {
        if (language.equals("VN")) {
            setTextAndBorder(SystemArrayText.textVn);
        } else {
            setTextAndBorder(SystemArrayText.text);

        }
    }

    private void handlingChangeLanguage(String lang) {
        language = lang;
        changeLanguage();
        txtService.setLanguage(language);
        reset();
    }

    private void viewTimeTable() {
        LSubject = new ArrayList<>();
        LSubject = txtService.getTimeTable("TimeTable.txt");
        String subject = "";
        for (String sub : LSubject) {
            subject += sub + "\n";
        }
        showDialogMessage(Constant.timeTableVn, subject, Constant.timeTable, subject);
    }

    private void importExcel() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Please choose file excel!");
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String excelPath = fc.getSelectedFile().getName();
            String[] ExcelPath = excelPath.split("\\.");
            yourClass = ExcelPath[0];
            sentDataToTable(yourClass);
            jComboBoxClassChooseItem.setSelectedItem(yourClass);
            reset();
        }
    }

    private void openFileExcel() {
        try {
            String file = yourClass + ".xlsx";
            if (file.length() > 5) {
                Runtime.getRuntime().exec(
                        "rundll32 url.dll, FileProtocolHandler "
                        + "F:\\S_Java\\Software\\" + file);
            } else {
                showDialogMessage("Chưa chọn lớp", "Thông báo",
                        "not class selected", "Message");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void goToFormAddTimeTable() {
        new AddTimeTable().setVisible(true);
        this.dispose();
    }

    private void search() {
        String keySearch = jTextFieldSearch.getText();
        yourClass = (String) jComboBoxClassChooseItem.getSelectedItem();
        LStudent = studentService.search(keySearch, yourClass);
        numberStudent = LStudent.size();
        if (numberStudent == 0) {
            showDialogMessage(
                    Constant.notFoundVn,
                    Constant.sorryVn,
                    Constant.notFound,
                    Constant.sorry);
        } else {
            numberStudent = LStudent.size();
            String number = String.valueOf(numberStudent);
            jLabelNumberStudent.setText(number);
            TableStudent.setRowCount(0);
            ArrayList<Student> list = LStudent;
            for (int i = 0; i < list.size(); i++) {
                Object obj[] = {
                    list.get(i).getId(), list.get(i).getName(),
                    list.get(i).getYourClass(),
                    changeGender(list.get(i).getGender()),
                    list.get(i).getBirthday(), list.get(i).getPhone(),
                    list.get(i).getAddress()
                };
                TableStudent.addRow(obj);
            }
        }
    }

    private void fullScreen() {
        if (jCheckBoxMenuItemFullScreen.isSelected()) {
            setExtendedState(ManagerLearningPoints.MAXIMIZED_BOTH);
        } else {
            setExtendedState(ManagerLearningPoints.NORMAL);
        }
    }

    private void back() {
        new Items().setVisible(true);
        this.dispose();
    }

    public ManagerLearningPoints() {
        initComponents();
        LStudent = new ArrayList<>();
        this.setLocationRelativeTo(null);
        String timeUpdate = txtService.getTime();
        jLabelUpdateTime.setText(timeUpdate);
        changeLanguage();
        yourClass = (String) jComboBoxClassChooseItem.getSelectedItem();
        sentDataToTable(yourClass);
        fullScreen();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jPanelManagerStudent = new javax.swing.JPanel();
        jTextFieldID = new javax.swing.JTextField();
        jLabelID = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelPhone = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JTextField();
        jLabelAddress = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabelBirthDay = new javax.swing.JLabel();
        jLabelGender = new javax.swing.JLabel();
        jRadioButtonMale = new javax.swing.JRadioButton();
        jRadioButtonFemale = new javax.swing.JRadioButton();
        jLabelClass = new javax.swing.JLabel();
        jComboBoxClass = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePoint = new javax.swing.JTable();
        jLabelNewSubject = new javax.swing.JLabel();
        jTextFieldSubject = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jButtonDeleteSubject = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableStudent = new javax.swing.JTable();
        jComboBoxClassChooseItem = new javax.swing.JComboBox<>();
        jLabelNumberStudent = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxEdit = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jButtonDetele = new javax.swing.JButton();
        jButtonUpdateDataTablePoint = new javax.swing.JButton();
        jLabelUpdateTime = new javax.swing.JLabel();
        jTextFieldDay = new javax.swing.JTextField();
        jTextFieldYear = new javax.swing.JTextField();
        jTextFieldMonth = new javax.swing.JTextField();
        jLabelhyphen1 = new javax.swing.JLabel();
        jLabelhyphen2 = new javax.swing.JLabel();
        jLabelFomatTime = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemUpdate = new javax.swing.JMenuItem();
        jMenuDelete = new javax.swing.JMenu();
        jMenuItemDeleteStudent = new javax.swing.JMenuItem();
        jMenuItemDeleteSubject = new javax.swing.JMenuItem();
        jMenuItemDeleteAll = new javax.swing.JMenuItem();
        jMenuItemRefresh = new javax.swing.JMenuItem();
        jMenuItemImportFileExcel = new javax.swing.JMenuItem();
        jMenuItemExportFileExccel = new javax.swing.JMenuItem();
        jMenuItemBack = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemAddTimeTable = new javax.swing.JMenuItem();
        jMenuView = new javax.swing.JMenu();
        jMenuItemViewAsFileExcel = new javax.swing.JMenuItem();
        jMenuItemViewReport = new javax.swing.JMenuItem();
        jMenuItemViewTimeTable = new javax.swing.JMenuItem();
        jCheckBoxMenuItemFullScreen = new javax.swing.JCheckBoxMenuItem();
        jMenuLanguage = new javax.swing.JMenu();
        jMenuItemVN = new javax.swing.JMenuItem();
        jMenuItemEN = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelManagerStudent.setBackground(new java.awt.Color(255, 255, 255));
        jPanelManagerStudent.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Manager Learning Points", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 120, 160))); // NOI18N

        jTextFieldID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelID.setText("ID");

        jLabelName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelName.setText("Name");

        jTextFieldName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelPhone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPhone.setText("Phone");

        jTextFieldPhone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelAddress.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelAddress.setText("Address");

        jTextFieldAddress.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelBirthDay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelBirthDay.setText("Birtday");

        jLabelGender.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelGender.setText("Gender");

        jRadioButtonMale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonMale);
        jRadioButtonMale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButtonMale.setText("Male");

        jRadioButtonFemale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonFemale);
        jRadioButtonFemale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButtonFemale.setText("Female");

        jLabelClass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelClass.setText("Class ");

        jComboBoxClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT06", "IT07", "IT08", "IT09", "IT10" }));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Point", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 120, 160))); // NOI18N

        jTablePoint.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTablePoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePoint.setColumnSelectionAllowed(true);
        jTablePoint.setRowHeight(25);
        jTablePoint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePointMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePoint);

        jLabelNewSubject.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelNewSubject.setText("NewSubject");

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDeleteSubject.setText("Remove");
        jButtonDeleteSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteSubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabelNewSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDeleteSubject)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNewSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDeleteSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTableStudent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStudent.setRowHeight(25);
        jTableStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableStudentMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableStudent);

        jComboBoxClassChooseItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT06", "IT07", "IT08", "IT09", "IT10" }));
        jComboBoxClassChooseItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClassChooseItemActionPerformed(evt);
            }
        });

        jLabelNumberStudent.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\user.png")); // NOI18N
        jLabelNumberStudent.setText(" ");

        jButtonSearch.setText("Search by ID");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jCheckBoxEdit.setText("Edit");
        jCheckBoxEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBoxClassChooseItem, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelNumberStudent)
                        .addGap(85, 85, 85)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jCheckBoxEdit)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxClassChooseItem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumberStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch)
                    .addComponent(jCheckBoxEdit))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
        );

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jButtonDetele.setText("Detele");
        jButtonDetele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeteleActionPerformed(evt);
            }
        });

        jButtonUpdateDataTablePoint.setText("Update");
        jButtonUpdateDataTablePoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateDataTablePointActionPerformed(evt);
            }
        });

        jLabelUpdateTime.setText("Time Update");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDetele, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdateDataTablePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelUpdateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDetele, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateDataTablePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUpdateTime))
                .addContainerGap())
        );

        jTextFieldDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDayActionPerformed(evt);
            }
        });

        jTextFieldMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMonthActionPerformed(evt);
            }
        });

        jLabelhyphen1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelhyphen1.setText("/");

        jLabelhyphen2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelhyphen2.setText("/");

        jLabelFomatTime.setText("(dd/mm/yyyy)");

        javax.swing.GroupLayout jPanelManagerStudentLayout = new javax.swing.GroupLayout(jPanelManagerStudent);
        jPanelManagerStudent.setLayout(jPanelManagerStudentLayout);
        jPanelManagerStudentLayout.setHorizontalGroup(
            jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPhone)
                            .addComponent(jTextFieldAddress)))
                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                        .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldName))
                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                        .addComponent(jLabelID, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldID))
                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelBirthDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelGender))
                        .addGap(46, 46, 46)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                                .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jRadioButtonMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldDay))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelhyphen1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonFemale)
                                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                                        .addComponent(jTextFieldMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelhyphen2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelFomatTime))))
                            .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelManagerStudentLayout.setVerticalGroup(
            jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelManagerStudentLayout.createSequentialGroup()
                .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelhyphen1)
                            .addComponent(jLabelhyphen2)
                            .addComponent(jLabelFomatTime))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonMale, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonFemale))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelManagerStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelManagerStudentLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(145, 40));

        jMenuFile.setBackground(new java.awt.Color(255, 255, 255));
        jMenuFile.setText("File");

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemSave.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemSave.setText("Save");
        jMenuItemSave.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSave);

        jMenuItemUpdate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemUpdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemUpdate.setText("Update");
        jMenuItemUpdate.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemUpdate);

        jMenuDelete.setBackground(new java.awt.Color(255, 255, 255));
        jMenuDelete.setText("Delete");
        jMenuDelete.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuDelete.setPreferredSize(new java.awt.Dimension(230, 24));

        jMenuItemDeleteStudent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemDeleteStudent.setText("Student");
        jMenuItemDeleteStudent.setPreferredSize(new java.awt.Dimension(170, 24));
        jMenuItemDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteStudentActionPerformed(evt);
            }
        });
        jMenuDelete.add(jMenuItemDeleteStudent);

        jMenuItemDeleteSubject.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemDeleteSubject.setText("Subject");
        jMenuItemDeleteSubject.setPreferredSize(new java.awt.Dimension(170, 24));
        jMenuItemDeleteSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteSubjectActionPerformed(evt);
            }
        });
        jMenuDelete.add(jMenuItemDeleteSubject);

        jMenuItemDeleteAll.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemDeleteAll.setText("All");
        jMenuItemDeleteAll.setPreferredSize(new java.awt.Dimension(170, 24));
        jMenuItemDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteAllActionPerformed(evt);
            }
        });
        jMenuDelete.add(jMenuItemDeleteAll);

        jMenuFile.add(jMenuDelete);

        jMenuItemRefresh.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemRefresh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemRefresh.setText("Refersh");
        jMenuItemRefresh.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRefreshActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemRefresh);

        jMenuItemImportFileExcel.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemImportFileExcel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemImportFileExcel.setText("Import File Excel");
        jMenuItemImportFileExcel.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemImportFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImportFileExcelActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemImportFileExcel);

        jMenuItemExportFileExccel.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemExportFileExccel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemExportFileExccel.setText("Download File Excel");
        jMenuItemExportFileExccel.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemExportFileExccel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExportFileExccelActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExportFileExccel);

        jMenuItemBack.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemBack.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemBack.setText("Back");
        jMenuItemBack.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBackActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemBack);

        jMenuItemExit.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemExit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemExit.setText("Exit");
        jMenuItemExit.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setBackground(new java.awt.Color(255, 255, 255));
        jMenuEdit.setText("Edit");

        jMenuItemAddTimeTable.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemAddTimeTable.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemAddTimeTable.setText("Edit Time Table");
        jMenuItemAddTimeTable.setPreferredSize(new java.awt.Dimension(230, 24));
        jMenuItemAddTimeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddTimeTableActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemAddTimeTable);

        jMenuBar1.add(jMenuEdit);

        jMenuView.setBackground(new java.awt.Color(255, 255, 255));
        jMenuView.setText("View");

        jMenuItemViewAsFileExcel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemViewAsFileExcel.setText("View as File Excel");
        jMenuItemViewAsFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewAsFileExcelActionPerformed(evt);
            }
        });
        jMenuView.add(jMenuItemViewAsFileExcel);

        jMenuItemViewReport.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemViewReport.setText("View Report");
        jMenuItemViewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewReportActionPerformed(evt);
            }
        });
        jMenuView.add(jMenuItemViewReport);

        jMenuItemViewTimeTable.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemViewTimeTable.setText("View Time Table");
        jMenuItemViewTimeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewTimeTableActionPerformed(evt);
            }
        });
        jMenuView.add(jMenuItemViewTimeTable);

        jCheckBoxMenuItemFullScreen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jCheckBoxMenuItemFullScreen.setSelected(true);
        jCheckBoxMenuItemFullScreen.setText("Full Screen");
        jCheckBoxMenuItemFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemFullScreenActionPerformed(evt);
            }
        });
        jMenuView.add(jCheckBoxMenuItemFullScreen);

        jMenuLanguage.setBackground(new java.awt.Color(255, 255, 255));
        jMenuLanguage.setText("Language");
        jMenuLanguage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jMenuItemVN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemVN.setText("Tiếng Việt - VN");
        jMenuItemVN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVNActionPerformed(evt);
            }
        });
        jMenuLanguage.add(jMenuItemVN);

        jMenuItemEN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItemEN.setText("English - EN");
        jMenuItemEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemENActionPerformed(evt);
            }
        });
        jMenuLanguage.add(jMenuItemEN);

        jMenuView.add(jMenuLanguage);

        jMenuBar1.add(jMenuView);

        jMenuHelp.setBackground(new java.awt.Color(255, 255, 255));
        jMenuHelp.setText("Help");
        jMenuHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuHelpMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelManagerStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelManagerStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelManagerStudent.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        //save();
    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jMenuItemUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateActionPerformed
        updateStudent();
    }//GEN-LAST:event_jMenuItemUpdateActionPerformed

    private void jMenuItemAddTimeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddTimeTableActionPerformed
        goToFormAddTimeTable();
    }//GEN-LAST:event_jMenuItemAddTimeTableActionPerformed

    private void jMenuItemImportFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImportFileExcelActionPerformed
        importExcel();
    }//GEN-LAST:event_jMenuItemImportFileExcelActionPerformed

    private void jMenuItemBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBackActionPerformed
        back();
    }//GEN-LAST:event_jMenuItemBackActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemViewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewReportActionPerformed
        new FormReport().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemViewReportActionPerformed

    private void jMenuItemViewTimeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewTimeTableActionPerformed
        viewTimeTable();
    }//GEN-LAST:event_jMenuItemViewTimeTableActionPerformed

    private void jCheckBoxMenuItemFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemFullScreenActionPerformed
        fullScreen();
    }//GEN-LAST:event_jCheckBoxMenuItemFullScreenActionPerformed

    private void jMenuItemVNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVNActionPerformed
        handlingChangeLanguage("VN");
    }//GEN-LAST:event_jMenuItemVNActionPerformed

    private void jMenuItemENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemENActionPerformed
        handlingChangeLanguage("EN");
    }//GEN-LAST:event_jMenuItemENActionPerformed

    private void jComboBoxClassChooseItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClassChooseItemActionPerformed
        showClass();
    }//GEN-LAST:event_jComboBoxClassChooseItemActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        addSubject();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        save();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        reset();
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jButtonDeteleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeteleActionPerformed
        deleteStudent();
    }//GEN-LAST:event_jButtonDeteleActionPerformed

    private void jButtonUpdateDataTablePointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateDataTablePointActionPerformed
        updateStudent();
    }//GEN-LAST:event_jButtonUpdateDataTablePointActionPerformed

    private void jMenuItemViewAsFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewAsFileExcelActionPerformed
        hfs.exportFileExcel(yourClass, LStudent);
        openFileExcel();
    }//GEN-LAST:event_jMenuItemViewAsFileExcelActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        search();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTablePointMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePointMouseClicked

    }//GEN-LAST:event_jTablePointMouseClicked

    private void jMenuItemExportFileExccelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExportFileExccelActionPerformed
        yourClass = JOptionPane.showInputDialog("Name File: ");
        if (yourClass != null) {
            hfs.exportFileExcel(yourClass, LStudent);
        }
    }//GEN-LAST:event_jMenuItemExportFileExccelActionPerformed

    private void jButtonDeleteSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteSubjectActionPerformed
        deleteRowTablePoint();
    }//GEN-LAST:event_jButtonDeleteSubjectActionPerformed

    private void jMenuItemDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteStudentActionPerformed
        deleteStudent();
    }//GEN-LAST:event_jMenuItemDeleteStudentActionPerformed

    private void jMenuItemDeleteSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteSubjectActionPerformed
        deleteRowTablePoint();
    }//GEN-LAST:event_jMenuItemDeleteSubjectActionPerformed

    private void jMenuItemDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteAllActionPerformed
        deleteClass();
    }//GEN-LAST:event_jMenuItemDeleteAllActionPerformed

    private void jMenuItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRefreshActionPerformed
        reset();
        yourClass = (String) jComboBoxClassChooseItem.getSelectedItem();
        sentDataToTable(yourClass);
        TableStudent.setRowCount(0);
        TablePoint.setRowCount(0);
        jLabelNumberStudent.setText("0");
        jComboBoxClassChooseItem.setSelectedIndex(0);

    }//GEN-LAST:event_jMenuItemRefreshActionPerformed

    private void jMenuHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuHelpMouseClicked
        try {
            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler medu.docx");
        } catch (IOException ex) {
            Logger.getLogger(ManagerLearningPoints.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuHelpMouseClicked

    private void jCheckBoxEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxEditMouseClicked
        checkEdit();    }//GEN-LAST:event_jCheckBoxEditMouseClicked

    private void jTableStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStudentMouseClicked
        mouseClickTableStudent();
    }//GEN-LAST:event_jTableStudentMouseClicked

    private void jTextFieldDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDayActionPerformed

    private void jTextFieldMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMonthActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerLearningPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerLearningPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerLearningPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerLearningPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerLearningPoints().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDeleteSubject;
    private javax.swing.JButton jButtonDetele;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdateDataTablePoint;
    private javax.swing.JCheckBox jCheckBoxEdit;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemFullScreen;
    private javax.swing.JComboBox<String> jComboBoxClass;
    private javax.swing.JComboBox<String> jComboBoxClassChooseItem;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelBirthDay;
    private javax.swing.JLabel jLabelClass;
    private javax.swing.JLabel jLabelFomatTime;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNewSubject;
    private javax.swing.JLabel jLabelNumberStudent;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelUpdateTime;
    private javax.swing.JLabel jLabelhyphen1;
    private javax.swing.JLabel jLabelhyphen2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuDelete;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAddTimeTable;
    private javax.swing.JMenuItem jMenuItemBack;
    private javax.swing.JMenuItem jMenuItemDeleteAll;
    private javax.swing.JMenuItem jMenuItemDeleteStudent;
    private javax.swing.JMenuItem jMenuItemDeleteSubject;
    private javax.swing.JMenuItem jMenuItemEN;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemExportFileExccel;
    private javax.swing.JMenuItem jMenuItemImportFileExcel;
    private javax.swing.JMenuItem jMenuItemRefresh;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemUpdate;
    private javax.swing.JMenuItem jMenuItemVN;
    private javax.swing.JMenuItem jMenuItemViewAsFileExcel;
    private javax.swing.JMenuItem jMenuItemViewReport;
    private javax.swing.JMenuItem jMenuItemViewTimeTable;
    private javax.swing.JMenu jMenuLanguage;
    private javax.swing.JMenu jMenuView;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelManagerStudent;
    private javax.swing.JRadioButton jRadioButtonFemale;
    private javax.swing.JRadioButton jRadioButtonMale;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablePoint;
    private javax.swing.JTable jTableStudent;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldDay;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldMonth;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldSubject;
    private javax.swing.JTextField jTextFieldYear;
    // End of variables declaration//GEN-END:variables
}
