/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Constant;
import functional.impl.HandlingTxtService;
import javax.swing.JOptionPane;
import model.Student;
import serviceImpl.CheckError;
import serviceImpl.StudentService;

/**
 *
 * @author HAU28
 */
public class Login extends javax.swing.JFrame {

    private String user;
    private String pass;
    private String language = "EN";
    private final CheckError check = new CheckError();
    private final HandlingTxtService hts = new HandlingTxtService();
    private final StudentService studentService = new StudentService();

    private void showDialog(String conditionVN, String tilteVN, String condition, String tilte) {
        int dialog = JOptionPane.UNDEFINED_CONDITION;
        if ("VN".equals(language)) {
            JOptionPane.showMessageDialog(this, conditionVN, tilteVN, dialog);
        } else {
            JOptionPane.showMessageDialog(this, condition, tilte, dialog);
        }
    }

    private void login() {
        try {
            user = jTextFieldUser.getText();
            pass = String.valueOf(jPasswordPass.getPassword());
            String tick = check.checkLoginAndFindOne(user, pass);
            switch (tick) {
                case "notFound":
                    showDialog(Constant.AccountNotFoundVn,
                            Constant.sorryVn,
                            Constant.AccountNotFound,
                            Constant.sorry);
                    break;
                case "notEnter":
                    showDialog(
                            Constant.notEnterVn,
                            Constant.sorryVn,
                            Constant.notEnter,
                            Constant.sorry);
                    break;
                case "admin":
                    goToFormItems();
                    break;
                default:
                    goToFormShow(user, tick);
                    break;
            }
        } catch (Exception e) {
            System.out.println("BUG " + e);
        }
    }

//        private void login() {
//        try {
//            user = jTextFieldUser.getText();
//            pass = String.valueOf(jPasswordPass.getPassword());
//            int tick = check.checkLogin(user, pass);
//            switch (tick) {
//                case -1:
//                    showDialog(Constant.AccountNotFoundVn,
//                            Constant.sorryVn,
//                            Constant.AccountNotFound,
//                            Constant.sorry);
//                    break;
//                case 1:
//                    showDialog(
//                            Constant.notEnterVn,
//                            Constant.sorryVn,
//                            Constant.notEnter,
//                            Constant.sorry);
//                    break;
//                case 2:
//                    goToFormItems();
//                    break;
//                case 3:
//                    goToFormShow();
//                    //goToFormShow();
//                    break;
//            }
//
//        } catch (Exception e) {
//            System.out.println("BUG " + e);
//        }
//    }
    private void showAndHidePass() {
        if (jCheckBoxShowPass.isSelected()) {
            jPasswordPass.setEchoChar((char) 0);
        } else {
            jPasswordPass.setEchoChar('*');
        }
    }

    private void goToFormItems() {
        this.dispose();
        new Items().setVisible(true);
        changeLanguage(language);
    }

    private void goToFormShow(String user, String yourClass) {
        Student student = new Student();
        student = studentService.findOne(user, yourClass);
        new ShowProfine(student).setVisible(true);
        this.dispose();
    }

//    private void goToFormShow() {
//        new ShowProfine().setVisible(true);
//        this.dispose();
//    }
    private void goToFormChangePassword() {
        new ChangePassword().setVisible(true);
        this.dispose();
    }

    private void changeLanguage(String language) {
        if (language.equals("VN")) {
            jButtonLogin.setText("Đăng Nhập");
            jLabelWeelCome.setText("Xin Chào");
            jLabelChangePass.setText("Quên mật khẩu?");
            jCheckBoxShowPass.setText("Hiện");

        } else {
            jRadioButton1.setSelected(true);
            jButtonLogin.setText("Login");
            jLabelWeelCome.setText("Welcom");
            jLabelChangePass.setText("Forgot password?");
            jCheckBoxShowPass.setText("show");
        }
    }

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        language = hts.getLanguage();
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
        jLabelUser = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabelLogin = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();
        jPasswordPass = new javax.swing.JPasswordField();
        jLabelWeelCome = new javax.swing.JLabel();
        jCheckBoxShowPass = new javax.swing.JCheckBox();
        jLabelChangePass = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelFacebook = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MeduSotfware");
        setBackground(new java.awt.Color(0, 120, 160));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        jLabelSlogann.setText("Manager - Education");

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
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 120, 160)));

        jLabelUser.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(0, 120, 160));
        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUser.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\icons8-customer-26.png")); // NOI18N

        jLabelPass.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(0, 120, 160));
        jLabelPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPass.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\icons8-password-26.png")); // NOI18N

        jTextFieldUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldUser.setForeground(new java.awt.Color(0, 120, 160));
        jTextFieldUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelLogin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLogin.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelLogin.setForeground(new java.awt.Color(0, 120, 160));
        jLabelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogin.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\icons8-login-96.png")); // NOI18N

        jButtonLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jPasswordPass.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordPass.setForeground(new java.awt.Color(0, 120, 160));
        jPasswordPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 120, 160)));

        jLabelWeelCome.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWeelCome.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabelWeelCome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWeelCome.setText("WElCOME");

        jCheckBoxShowPass.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxShowPass.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBoxShowPass.setText("Show");
        jCheckBoxShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxShowPassActionPerformed(evt);
            }
        });

        jLabelChangePass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelChangePass.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\key.png")); // NOI18N
        jLabelChangePass.setText("Forgot password?");
        jLabelChangePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChangePassMouseClicked(evt);
            }
        });

        jLabelEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmail.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\email (1).png")); // NOI18N

        jLabelFacebook.setIcon(new javax.swing.ImageIcon("F:\\S_Java\\Software\\icon\\facebook-new (1).png")); // NOI18N

        jRadioButton1.setText("English");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelWeelCome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jCheckBoxShowPass))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelChangePass))
                            .addComponent(jPasswordPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))))
                .addGap(0, 74, Short.MAX_VALUE))
            .addComponent(jLabelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelFacebook)
                .addGap(18, 18, 18)
                .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelWeelCome, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelPass, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordPass)
                        .addComponent(jCheckBoxShowPass)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelChangePass)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelFacebook)))
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        login();
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jCheckBoxShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowPassActionPerformed
        showAndHidePass();
    }//GEN-LAST:event_jCheckBoxShowPassActionPerformed

    private void jLabelChangePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelChangePassMouseClicked
        goToFormChangePassword();
    }//GEN-LAST:event_jLabelChangePassMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            language = "EN";
        } else {
            language = "VN";
        }
        changeLanguage(language);
        hts.setLanguage(language);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JCheckBox jCheckBoxShowPass;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelChangePass;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFacebook;
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelSlogan;
    private javax.swing.JLabel jLabelSlogann;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelWeelCome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordPass;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
