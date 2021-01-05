/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.SystemArrayText;
import functional.impl.HandlingTxtService;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import model.Point;
import model.Student;
import serviceImpl.StudentService;

/**
 *
 * @author HAU28
 */
public final class ShowProfine extends javax.swing.JFrame {

    static DefaultTableModel TablePoint;
    private final HandlingTxtService hts = new HandlingTxtService();
    private final String language = hts.getLanguage();
    private final StudentService studentService = new StudentService();

    private double Total(double scoreHw, double scoreMid, double scoreFinal) {
        return ((scoreHw * 1) + (scoreMid * 4) + (scoreFinal * 5)) / 10;
    }

    private void LearningPoint() {
        int rowCount = jTablePoint.getRowCount();
        double totalAll = 0;
        for (int i = 0; i < rowCount; i++) {
            totalAll += (Double) jTablePoint.getValueAt(i, 4);
        }

        double average = totalAll / rowCount;
        average = Math.round(average * 100) / 100;
        jLabelPoint.setText(String.valueOf(average));

    }

    private void handlingShow(Student student) {
//        Account account = hts.getInfoAccount("info.txt");
//        String username = account.getUser();
//        String nameTableStudent = account.getYourClass();
//        ArrayList<Student> list = studentService.search(username, nameTableStudent);
//        Student student = list.get(0);

        //  Student student = studentService.findOne(Temp.getId(), Temp.getYourClass());
        //Student student = new Student();
        //System.out.println(student.getId());
        showInformation(student.getStudent());
        showTablePoint(student.getStudent().getPoint());
    }

    private void showTablePoint(ArrayList<Point> point) {
        TablePoint.setRowCount(0);
        for (int i = 0; i < point.size(); i++) {
            String nameSubject = point.get(i).getNameSub();
            Double scoreHw = point.get(i).getPoint_HW();
            Double scoreMid = point.get(i).getPoint_Mid();
            Double scoreFinal = point.get(i).getPoint_Final();
            String note = point.get(i).getNote();
            double total = Total(scoreHw, scoreMid, scoreFinal);
            Object obj[] = {nameSubject, scoreHw, scoreMid, scoreFinal, total, note};
            TablePoint.addRow(obj);
        }
        LearningPoint();
    }

    public void showInformation(Student student) {

        jLabelID.setText(student.getId());
        jLabelName.setText(student.getName());
        jLabelClass.setText(student.getYourClass());
        jLabelBirthday.setText(student.getBirthday());
        jTextAreaShowInfo.append(
                "ID: " + student.getId()
                + "\nName: " + student.getName()
                + "\nBirthday: " + student.getBirthday()
                + "\nGender: " + student.getGender()
                + "\nClass: " + student.getYourClass()
                + "\nAddress: " + student.getAddress()
                + "\nPhone: " + student.getPhone());
    }

    private void addHeaderToTable() {
        if ("VN".equals(language)) {
            TablePoint = new DefaultTableModel(SystemArrayText.HeaderPointVn, 0);
            jTablePoint.setModel(TablePoint);
        } else {
            TablePoint = new DefaultTableModel(SystemArrayText.HeaderPoint, 0);
            jTablePoint.setModel(TablePoint);
        }
    }

    private void changeLanguage() {
        if ("VN".equals(language)) {
            java.awt.Font f = new java.awt.Font("Tohoma", 48, 48);
            Border b = new TitledBorder("");
            jPanelInfo.setBorder(new TitledBorder(b, "Thông Tin", 5, 0, f));
            jButtonBack.setText("Quay lại");
            jLabelC.setText("Ghi Chú");
            jLabelTP.setText("Điểm Rèn Luyện");
            jLabelLP.setText("Điểm Học Tập");

        } else {
            java.awt.Font f = new java.awt.Font("Tohoma", 48, 48);
            Border b = new TitledBorder("");
            jPanelInfo.setBorder(new TitledBorder(b, "Info", 5, 0, f));
            jButtonBack.setText("Back");
            jLabelC.setText("Note");
            jLabelTP.setText("Training Point");
            jLabelLP.setText("Learning Point");

        }
    }

    private void back() {
        this.dispose();
        Login f = new Login();
        f.setVisible(true);
    }
//main

    public ShowProfine() {

    }

    public ShowProfine(Student student) {
        initComponents();
        addHeaderToTable();
        handlingShow(student);
        //showInFormation();
        this.setLocationRelativeTo(null);
        changeLanguage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanelInfo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePoint = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabelLP = new javax.swing.JLabel();
        jLabelTP = new javax.swing.JLabel();
        jLabelC = new javax.swing.JLabel();
        jLabelPoint = new javax.swing.JLabel();
        jLabelTraining = new javax.swing.JLabel();
        jLabelComment = new javax.swing.JLabel();
        jPanelShowInfo = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaShowInfo = new javax.swing.JTextArea();
        jButtonBack = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelClass = new javax.swing.JLabel();
        jLabelBirthday = new javax.swing.JLabel();
        jLabelAuthor = new javax.swing.JLabel();

        jTextField4.setText("jTextField4");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jTablePoint.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(0, 120, 160)));
        jTablePoint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTablePoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePoint.setEnabled(false);
        jTablePoint.setGridColor(new java.awt.Color(255, 255, 255));
        jTablePoint.setRowHeight(30);
        jTablePoint.setRowMargin(10);
        jTablePoint.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTablePoint.setSelectionForeground(new java.awt.Color(0, 120, 160));
        jScrollPane1.setViewportView(jTablePoint);
        jTablePoint.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabelLP.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelLP.setForeground(new java.awt.Color(0, 120, 160));
        jLabelLP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLP.setText("Learning Points: ");

        jLabelTP.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelTP.setForeground(new java.awt.Color(0, 120, 160));
        jLabelTP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTP.setText("Training Point: ");

        jLabelC.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelC.setForeground(new java.awt.Color(0, 120, 160));
        jLabelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelC.setText("Comment: ");

        jLabelPoint.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelPoint.setText("0.0");

        jLabelTraining.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelTraining.setText("0.0");

        jLabelComment.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelComment.setText("0.0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jLabelTP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addComponent(jLabelComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jLabelTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelComment, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(172, 172, 172))
        );

        jPanelShowInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelShowInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Profile", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 36))); // NOI18N

        jTextAreaShowInfo.setColumns(20);
        jTextAreaShowInfo.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextAreaShowInfo.setRows(5);
        jScrollPane3.setViewportView(jTextAreaShowInfo);

        javax.swing.GroupLayout jPanelShowInfoLayout = new javax.swing.GroupLayout(jPanelShowInfo);
        jPanelShowInfo.setLayout(jPanelShowInfoLayout);
        jPanelShowInfoLayout.setHorizontalGroup(
            jPanelShowInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );
        jPanelShowInfoLayout.setVerticalGroup(
            jPanelShowInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelShowInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelShowInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 322, Short.MAX_VALUE)))
        );

        jButtonBack.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\icons8-back-24.png")); // NOI18N
        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 120, 160));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));

        jLabelID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelID.setForeground(new java.awt.Color(255, 255, 255));
        jLabelID.setText("ID");

        jLabelName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName.setText("Name");

        jLabelClass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelClass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClass.setText("Class");

        jLabelBirthday.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelBirthday.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBirthday.setText("Birthday");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabelID)
                .addGap(176, 176, 176)
                .addComponent(jLabelName)
                .addGap(222, 222, 222)
                .addComponent(jLabelBirthday)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelClass)
                .addGap(151, 151, 151))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelID, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabelAuthor.setForeground(new java.awt.Color(0, 120, 160));
        jLabelAuthor.setText("Design by NCH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelAuthor))
            .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAuthor))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        back();
    }//GEN-LAST:event_jButtonBackActionPerformed

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
            java.util.logging.Logger.getLogger(ShowProfine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowProfine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowProfine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowProfine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new ShowProfine().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelC;
    private javax.swing.JLabel jLabelClass;
    private javax.swing.JLabel jLabelComment;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelLP;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPoint;
    private javax.swing.JLabel jLabelTP;
    private javax.swing.JLabel jLabelTraining;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelShowInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTablePoint;
    private javax.swing.JTextArea jTextAreaShowInfo;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
