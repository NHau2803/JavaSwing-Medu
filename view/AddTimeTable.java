/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import static controler.HandlingUpdate.readTimeTable;
import common.SystemArrayText;
import functional.impl.HandlingTxtService;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HAU28
 */
public class AddTimeTable extends javax.swing.JFrame {

    int row = -1;
    private ArrayList<String> ListSubject;
    private final HandlingTxtService txtService = new HandlingTxtService();
    private final String language = txtService.getLanguage();

    private boolean check() {
        if (ListSubject.isEmpty()) {
            String mes = "Not yet enter";
            String title = "Message";
            int dialog = JOptionPane.ERROR_MESSAGE;
            if ("VN".equals(language)) {
                mes = "Chưa Nhập thời khoá biểu";
                title = "Xin lỗi";
            }
            JOptionPane.showMessageDialog(this, mes, title, dialog);
            return false;
        }
        return true;
    }

    private void save() {
        ListSubject = new ArrayList<>();
        String s = "";
        for (int i = 0; i < 10; i++) {
            try {
                s = (String) jTableTime.getValueAt(i, 1);
                if (!s.isEmpty()) {
                    ListSubject.add(s);
                }
            } catch (Exception e) {
                System.out.println("save time table" + e);
            }
        }
        if (check() == true) {
            txtService.setTimeTable(ListSubject, "TimeTable.txt");
            System.out.println(ListSubject.toString());
            goToFormManager();
        }
    }

    private void setTextAndBorder(String ArrayText[]) {
        java.awt.Font f = new java.awt.Font("Tohoma", 36, 36);
        Border b = new TitledBorder("");
        jPanelTimeTable.setBorder(new TitledBorder(b, ArrayText[0], 0, 0, f));
        jButtonCancel.setText(ArrayText[1]);
        jButtonSave.setText(ArrayText[2]);
        jLabelTutorial.setText(ArrayText[3]);
        jLabelLable.setText(ArrayText[4]);
        jLabelNameSubject.setText(ArrayText[5]);
        jLabelNumber.setText(ArrayText[6]);
    }

    private void changeLanguage() {
        if ("VN".equals(language)) {
            setTextAndBorder(SystemArrayText.textTimeTableVn);
        } else {
            setTextAndBorder(SystemArrayText.textTimeTable);
        }
    }

    private void goToFormManager() {
        new ManagerLearningPoints().setVisible(true);
        this.dispose();
    }

    public AddTimeTable() {
        initComponents();
        this.setLocationRelativeTo(null);
        changeLanguage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTimeTable = new javax.swing.JPanel();
        jLabelLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTime = new javax.swing.JTable();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabelTutorial = new javax.swing.JLabel();
        jLabelNumber = new javax.swing.JLabel();
        jLabelNameSubject = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelTimeTable.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTimeTable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Time Table", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        jLabelLable.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLable.setText("Teacher add time table for student");

        jTableTime.setAutoCreateRowSorter(true);
        jTableTime.setBorder(new javax.swing.border.MatteBorder(null));
        jTableTime.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTableTime.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null},
                {"2", null},
                {"3", null},
                {"4", null},
                {"5", null},
                {"6", null},
                {"7", null},
                {"8", null},
                {"9", null}
            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableTime.setColumnSelectionAllowed(true);
        jTableTime.setRowHeight(30);
        jScrollPane1.setViewportView(jTableTime);
        jTableTime.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabelTutorial.setText("Click row Table and Edit ");

        jLabelNumber.setText("Number");

        jLabelNameSubject.setText("Name Subject");

        javax.swing.GroupLayout jPanelTimeTableLayout = new javax.swing.GroupLayout(jPanelTimeTable);
        jPanelTimeTable.setLayout(jPanelTimeTableLayout);
        jPanelTimeTableLayout.setHorizontalGroup(
            jPanelTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelTimeTableLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabelNumber)
                .addGap(175, 175, 175)
                .addComponent(jLabelNameSubject)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelTimeTableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTimeTableLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanelTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTimeTableLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTutorial)
                        .addContainerGap())))
        );
        jPanelTimeTableLayout.setVerticalGroup(
            jPanelTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTimeTableLayout.createSequentialGroup()
                .addComponent(jLabelLable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumber)
                    .addComponent(jLabelNameSubject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanelTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTimeTableLayout.createSequentialGroup()
                        .addComponent(jLabelTutorial)
                        .addGap(38, 38, 38)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTimeTableLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTimeTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTimeTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        save();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        goToFormManager();

    }//GEN-LAST:event_jButtonCancelActionPerformed

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
            java.util.logging.Logger.getLogger(AddTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AddTimeTable().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabelLable;
    private javax.swing.JLabel jLabelNameSubject;
    private javax.swing.JLabel jLabelNumber;
    private javax.swing.JLabel jLabelTutorial;
    private javax.swing.JPanel jPanelTimeTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTime;
    // End of variables declaration//GEN-END:variables
}
