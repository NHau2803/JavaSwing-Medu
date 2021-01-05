/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Constant;
import common.SystemArrayText;
import functional.impl.HandlingTxtService;
import functional.impl.Md5;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Account;
import serviceImpl.AccountService;
import serviceImpl.CheckError;
import serviceImpl.StudentService;

/**
 *
 * @author HAU28
 */
public final class ManagerAccounts extends javax.swing.JFrame {

    private ArrayList<Account> listAllAccounts;
    DefaultTableModel TableAccounts;
    private static int numberStudent;
    private final HandlingTxtService hts = new HandlingTxtService();
    private final String language = hts.getLanguage();
    private final AccountService accountService = new AccountService();
    private final StudentService studentService = new StudentService();
    private final CheckError check = new CheckError();
    private final Md5 md5 = new Md5();
//-----------------------------------------------------------------------------

    private String user = "";
    private String pass = "";
    private String yourClass = "";
    private String yourClassFirst = "";
    private String note = "";

    private void addHeaderToTable() {
        if ("VN".equals(language)) {
            TableAccounts = new DefaultTableModel(SystemArrayText.HeaderFormAccountVn, 0);
            jTableAccounts.setModel(TableAccounts);
        } else {
            TableAccounts = new DefaultTableModel(SystemArrayText.HeaderFormAccount, 0);
            jTableAccounts.setModel(TableAccounts);
        }
    }

    private void enabled(boolean bln) {
        jTextFieldUser.setEnabled(bln);
        jButtonResetPassword.setEnabled(bln);
        jComboBoxClass.setEnabled(bln);
        jTextFieldNote.setEnabled(bln);

    }

    private void checkEdit() {
        if (jCheckBoxEdit.isSelected()) {
            enabled(true);
        } else {
            enabled(false);
        }
    }

    private void showDialogMessage(String messageVn, String titleVn, String message, String title) {
        int dialog = JOptionPane.UNDEFINED_CONDITION;
        if ("VN".equals(language)) {
            message = messageVn;
            title = titleVn;
        }
        JOptionPane.showMessageDialog(this, message, title, dialog);
    }

    private ArrayList getAllAccount() {
        ArrayList<Account> list = accountService.findAll();
        return list;
    }

    private void sentDataToTable(ArrayList<Account> list) {
        numberStudent = list.size();
        if (numberStudent == 0) {
            showDialogMessage(
                    "Lớp chưa được tạo",
                    Constant.sorryVn,
                    Constant.notFound,
                    Constant.sorry);
        } else {
            for (int i = 0; i < numberStudent; i++) {
                Object obj[] = {
                    list.get(i).getUser(),
                    list.get(i).getPass(),
                    list.get(i).getYourClass(),
                    "none"
                };
                TableAccounts.addRow(obj);
            }
        }
    }

    private void search() {
        String keySearch = jTextFieldSearch.getText();
        ArrayList<Account> listSearch = accountService.findByID(keySearch);
        TableAccounts.setRowCount(0);
        sentDataToTable(listSearch);
    }

    private ArrayList<Account> sortByNameClass(ArrayList<Account> list) {
        Collections.sort(list, (Account a1, Account a2) -> a1.getYourClass().compareTo(a2.getYourClass()));
        return list;
    }

    private ArrayList<Account> findByNameClass(String nameClass) {
        ArrayList<Account> listFind = new ArrayList<>();
        for (Account a : listAllAccounts) {
            if (a.getYourClass().equals(nameClass)) {
                listFind.add(a);
            }
        }
        return listFind;
    }

    private void showClass() {
        String nameClass = (String) jComboBoxClassChooseItem.getSelectedItem();
        sortByNameClass(listAllAccounts);
        TableAccounts.setRowCount(0);
        ArrayList<Account> listFind = findByNameClass(nameClass);
        sentDataToTable(listFind);

    }

    private void showAll() {
        if (jComboChoose.getSelectedItem() == "All") {
            TableAccounts.setRowCount(0);
            sentDataToTable(listAllAccounts);
            jComboBoxClassChooseItem.setEnabled(false);
        } else {
            jComboBoxClassChooseItem.setEnabled(true);
        }
    }

    private void setText() {
        jTextFieldUser.setText(user);
        jTextFieldNote.setText(note);
        jComboBoxClass.setSelectedItem(yourClassFirst);
    }

    private void getText() {
        user = jTextFieldUser.getText();
        yourClass = (String) jComboBoxClass.getSelectedItem();
        note = jTextFieldNote.getText();
    }

    private void mouseClick() {
        checkEdit();
        int i = jTableAccounts.getSelectedRow();
        TableModel modedClick = jTableAccounts.getModel();
        //
        user = modedClick.getValueAt(i, 0).toString();
        pass = modedClick.getValueAt(i, 1).toString();
        yourClassFirst = modedClick.getValueAt(i, 2).toString();
        note = modedClick.getValueAt(i, 3).toString();
        setText();
    }

    private void update() {
        try {
            getText();
            if (check.checkUserName(user)) {
                accountService.updateAccout(user, pass, yourClass);
                if (!yourClassFirst.equals(yourClass)) {
                    //studentService.deleteStudent(user, yourClassFirst);
                    studentService.changeStudentToClassOther(user, yourClassFirst, yourClass);
                }
                getData();
            } else {
                showDialogMessage(
                        Constant.AccountNotFoundVn,
                        Constant.sorryVn,
                        Constant.notFound,
                        Constant.sorry);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void resetPassword() {
        try {
            getText();
            pass = md5.getMd5("@" + user);
            if (check.checkUserName(user)) {
                accountService.updateAccout(user, pass, yourClass);
                getData();
            } else {
                showDialogMessage(
                        Constant.AccountNotFoundVn,
                        Constant.sorryVn,
                        Constant.notFound,
                        Constant.sorry);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getData() {
        TableAccounts.setRowCount(0);
        listAllAccounts = new ArrayList<>();
        listAllAccounts = getAllAccount();
        sentDataToTable(listAllAccounts);
    }

    public ManagerAccounts() {
        initComponents();
        setExtendedState(ManagerLearningPoints.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        addHeaderToTable();
        getData();
        showAll();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAccounts = new javax.swing.JTable();
        jComboChoose = new javax.swing.JComboBox<>();
        jButtonBack = new javax.swing.JButton();
        jComboBoxClassChooseItem = new javax.swing.JComboBox<>();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxEdit = new javax.swing.JCheckBox();
        jButtonUpdate = new javax.swing.JButton();
        jTextFieldUser = new javax.swing.JTextField();
        jLabelUser = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jLabelClass = new javax.swing.JLabel();
        jTextFieldNote = new javax.swing.JTextField();
        jLabelNote = new javax.swing.JLabel();
        jComboBoxClass = new javax.swing.JComboBox<>();
        jButtonResetPassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36))); // NOI18N

        jTableAccounts.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTableAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableAccounts.setRowHeight(27);
        jTableAccounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAccountsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAccounts);

        jComboChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Custom" }));
        jComboChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboChooseActionPerformed(evt);
            }
        });

        jButtonBack.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\icons8-back-24.png")); // NOI18N
        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jComboBoxClassChooseItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT06", "IT07", "IT08", "IT09", "IT10" }));
        jComboBoxClassChooseItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClassChooseItemActionPerformed(evt);
            }
        });

        jButtonSearch.setText("Search by username");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jCheckBoxEdit.setText("Edit Data");
        jCheckBoxEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxEditMouseClicked(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jTextFieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserActionPerformed(evt);
            }
        });

        jLabelUser.setText("Username");

        jLabelPass.setText("Password");

        jLabelClass.setText("Class");

        jTextFieldNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNoteActionPerformed(evt);
            }
        });

        jLabelNote.setText("Note");

        jComboBoxClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT06", "IT07", "IT08", "IT09", "IT10" }));
        jComboBoxClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClassActionPerformed(evt);
            }
        });

        jButtonResetPassword.setText("Reset Passoword");
        jButtonResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSearch)
                        .addGap(18, 18, 18)
                        .addComponent(jComboChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxClassChooseItem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxEdit)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonResetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxClass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNote, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSearch)
                        .addComponent(jComboChoose)
                        .addComponent(jComboBoxClassChooseItem)
                        .addComponent(jCheckBoxEdit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonResetPassword))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxClass))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNote, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        new Items().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jComboBoxClassChooseItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClassChooseItemActionPerformed
        showClass();
    }//GEN-LAST:event_jComboBoxClassChooseItemActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        search();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        update();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jComboChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboChooseActionPerformed
        showAll();
    }//GEN-LAST:event_jComboChooseActionPerformed

    private void jTextFieldUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUserActionPerformed

    private void jTextFieldNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNoteActionPerformed

    private void jTableAccountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAccountsMouseClicked
        mouseClick();
    }//GEN-LAST:event_jTableAccountsMouseClicked

    private void jCheckBoxEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxEditMouseClicked
        checkEdit();
    }//GEN-LAST:event_jCheckBoxEditMouseClicked

    private void jComboBoxClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxClassActionPerformed

    private void jButtonResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetPasswordActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Reset", "Reset Password?", dialog);
        if (dialogResult == 0) {
            resetPassword();
        }
    }//GEN-LAST:event_jButtonResetPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerAccounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerAccounts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonResetPassword;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JCheckBox jCheckBoxEdit;
    private javax.swing.JComboBox<String> jComboBoxClass;
    private javax.swing.JComboBox<String> jComboBoxClassChooseItem;
    private javax.swing.JComboBox<String> jComboChoose;
    private javax.swing.JLabel jLabelClass;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAccounts;
    private javax.swing.JTextField jTextFieldNote;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
