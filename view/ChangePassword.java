/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Constant;
import functional.impl.HandlingTxtService;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import serviceImpl.AccountService;
import serviceImpl.CheckError;

/**
 *
 * @author HAU28
 */
public class ChangePassword extends javax.swing.JFrame {

    private final HandlingTxtService txtService = new HandlingTxtService();
    private final String language = txtService.getLanguage();
    private final CheckError check = new CheckError();
    private final AccountService as = new AccountService();

    private void showDialogError(String messageVN, String titleVN, String message, String title) {
        int dialog = JOptionPane.ERROR_MESSAGE;
        if ("VN".equals(language)) {
            message = messageVN;
            title = titleVN;
        }
        JOptionPane.showMessageDialog(this, message, title, dialog);
    }

    private void showDialogMessage(String messageVN, String titleVN, String message, String title) {
        int dialog = JOptionPane.UNDEFINED_CONDITION;
        if ("VN".equals(language)) {
            message = messageVN;
            title = titleVN;
        }
        JOptionPane.showMessageDialog(this, message, title, dialog);
    }

    private void changePass() {
        String id = jTextFieldID.getText();
        String pass = String.copyValueOf(jPasswordPass.getPassword());
        String pass1 = String.valueOf(jPasswordPass1.getPassword());
        String pass2 = String.valueOf(jPasswordPass2.getPassword());
        int tick = check.checkAccount(id, pass, pass1, pass2);
        switch (tick) {
            case -1:
                showDialogError(Constant.invalidVn,
                        Constant.errorMessageVn,
                        Constant.invalid,
                        Constant.errorMessage);
                break;
            case 0:
                showDialogError(
                        Constant.AcountInvalidVn,
                        Constant.errorMessageVn,
                        Constant.AccountInvalid,
                        Constant.errorMessage);
                break;
            case 1:
                int tick2 = as.updateAccoutByPassword(id, pass, pass1);
                if (tick2 == 1) {
                    showDialogMessage(
                            Constant.updateSuccessfullyVn,
                            Constant.CongratulationsVn,
                            Constant.updateSuccessfully,
                            Constant.Congratulations);
                    gotoFormLogin();
                    break;
                } else {
                    showDialogMessage(
                            Constant.updateUnsuccessfullyVn,
                            Constant.sorryVn,
                            Constant.updateUnsuccessfully,
                            Constant.sorry);
                    gotoFormLogin();
                    break;
                }
        }

    }

    private void showAndHidePass() {
        if (jCheckBoxShowPass.isSelected()) {
            jPasswordPass1.setEchoChar((char) 0);
            jPasswordPass2.setEchoChar((char) 0);
        } else {
            jPasswordPass1.setEchoChar('*');
            jPasswordPass2.setEchoChar('*');
        }
    }

    private void gotoFormLogin() {
        this.dispose();
        Login fl = new Login();
        fl.setVisible(true);
    }

    private void changeView(String btnChange, String btnUpdate, String btnCancel) {
        java.awt.Font f = new java.awt.Font("Tohoma", 48, 48);
        Border b = new TitledBorder("");
        jPanelChangePassword.setBorder(new TitledBorder(b, btnChange, 5, 0, f));
        jButtonUpdate.setText(btnUpdate);
        jButtonCancel.setText(btnCancel);

    }

    private void changeLanguage(String language) {
        if ("VN".equals(language)) {
            changeView(
                    Constant.changePassVn,
                    Constant.updateVn,
                    Constant.cancelVn);
        } else {
            changeView(
                    Constant.changePass,
                    Constant.update,
                    Constant.cancel);
        }
    }

    public ChangePassword() {
        initComponents();
        this.setLocationRelativeTo(null);
        changeLanguage(language);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelImg = new javax.swing.JLabel();
        jLabelSlogan = new javax.swing.JLabel();
        jLabelAuthor = new javax.swing.JLabel();
        jLabelSlogann = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanelChangePassword = new javax.swing.JPanel();
        jButtonUpdate = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jLabelPass = new javax.swing.JLabel();
        jPasswordPass = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabelPass1 = new javax.swing.JLabel();
        jPasswordPass1 = new javax.swing.JPasswordField();
        jLabelPass2 = new javax.swing.JLabel();
        jPasswordPass2 = new javax.swing.JPasswordField();
        jCheckBoxShowPass = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 120, 160));

        jLabelImg.setBackground(new java.awt.Color(255, 255, 255));
        jLabelImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImg.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\student (1).png")); // NOI18N

        jLabelSlogan.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelSlogan.setForeground(java.awt.Color.white);
        jLabelSlogan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSlogan.setText("MEDU");

        jLabelAuthor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAuthor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelAuthor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAuthor.setText("Design by NCH");

        jLabelSlogann.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelSlogann.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSlogann.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSlogann.setText("Menager - Education");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addComponent(jLabelSlogan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelSlogann, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelSlogan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSlogann)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelAuthor))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanelChangePassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelChangePassword.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 48))); // NOI18N

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabelUser.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(0, 120, 160));
        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUser.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\login (1).png")); // NOI18N

        jTextFieldID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelPass.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(0, 120, 160));
        jLabelPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPass.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\password.png")); // NOI18N

        jPasswordPass.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordPass.setForeground(new java.awt.Color(0, 120, 160));
        jPasswordPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordPass, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jLabelPass1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelPass1.setForeground(new java.awt.Color(0, 120, 160));
        jLabelPass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPass1.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\password.png")); // NOI18N

        jPasswordPass1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordPass1.setForeground(new java.awt.Color(0, 120, 160));
        jPasswordPass1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelPass2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelPass2.setForeground(new java.awt.Color(0, 120, 160));
        jLabelPass2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPass2.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\login.png")); // NOI18N

        jPasswordPass2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordPass2.setForeground(new java.awt.Color(0, 120, 160));
        jPasswordPass2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jCheckBoxShowPass.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxShowPass.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBoxShowPass.setText("Show");
        jCheckBoxShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxShowPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPasswordPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxShowPass)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jCheckBoxShowPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelChangePasswordLayout = new javax.swing.GroupLayout(jPanelChangePassword);
        jPanelChangePassword.setLayout(jPanelChangePasswordLayout);
        jPanelChangePasswordLayout.setHorizontalGroup(
            jPanelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelChangePasswordLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelChangePasswordLayout.setVerticalGroup(
            jPanelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChangePasswordLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        changePass();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        gotoFormLogin();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jCheckBoxShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowPassActionPerformed
        showAndHidePass();
    }//GEN-LAST:event_jCheckBoxShowPassActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JCheckBox jCheckBoxShowPass;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelPass1;
    private javax.swing.JLabel jLabelPass2;
    private javax.swing.JLabel jLabelSlogan;
    private javax.swing.JLabel jLabelSlogann;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelChangePassword;
    private javax.swing.JPasswordField jPasswordPass;
    private javax.swing.JPasswordField jPasswordPass1;
    private javax.swing.JPasswordField jPasswordPass2;
    private javax.swing.JTextField jTextFieldID;
    // End of variables declaration//GEN-END:variables
}
