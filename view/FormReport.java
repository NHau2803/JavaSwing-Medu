/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import functionalImp.HandlingReportService;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import model.Report;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class FormReport extends javax.swing.JFrame {

    private static DefaultTableModel TableReport;
    private static final String Header[] = new String[]{
        "Lớp", "Tổng Sinh Viên",
        "Nam", "Nữ", "Đạt",
        "Rớt", "Tỉ Lệ Điểm", "Học Phí"};

    private ArrayList<Report> LReport;
    private Report report;

    private static String nameClass;
    private static int totalStudent;
    private static int totalMale;
    private static int totalFemale;
    private static int totalPass;
    private static int totalFail;
    private static double averages;

//    private final AccountService accountService = new AccountService();
//    private final StudentService studentService = new StudentService();
    private final HandlingReportService hrs = new HandlingReportService();

//    private static void sumAll(String s, int numStudent, int numMale,
//            int numFemail, int numStudentPass, int numStudentFamail) {
//        if (s.length() > 0) {
//            allClass++;
//        }
//        allTotal = allTotal + numStudent;
//        allMale = allMale + numMale;
//        allFemale = allFemale + numFemail;
//        allPass = allPass + numStudentPass;
//        allFail = allFail + numStudentFamail;
//    }
//
//    private ArrayList<String> getAllClass() {
//        ArrayList<String> classes = new ArrayList<>();
//        ArrayList<Account> listAccount = accountService.findAll();
//        for (Account account : listAccount) {
//            if (!classes.contains(account.getYourClass())) {
//                classes.add(account.getYourClass());
//            }
//        }
//        return classes;
//    }
//
//    private ArrayList<model.Report> getListReport() {
//        ArrayList<String> classes = getAllClass();
//        ArrayList<Student> listStudent = new ArrayList<>();
//        for (String nameClass : classes) {
//            listStudent = (ArrayList<Student>) studentService.getList(nameClass);
//            LReport = HandlingReport.dataCalculation(listStudent, nameClass);
//        }
//        return LReport;
//    }
    private void senDataToTablePoint() {
        try {
            LReport = hrs.handlingReport();
            TableReport.setRowCount(0);
            for (int i = 0; i < LReport.size(); i++) {
                nameClass = LReport.get(i).getNameClass();
                totalStudent = LReport.get(i).getToltalStudent();
                totalMale = LReport.get(i).getTotalMale();
                totalFemale = LReport.get(i).getTotalFemale();
                totalPass = LReport.get(i).getTotalPass();
                totalFail = LReport.get(i).getTotalFail();
                averages = LReport.get(i).getAverages();
                Object obj[] = {nameClass, totalStudent, totalMale, totalFemale, totalPass, totalFail, averages};
                TableReport.addRow(obj);
            }
            //================================================================
            int row = jTableReport.getRowCount();
            int col = jTableReport.getColumnCount();
            int sum = 0;
            int sumCol[] = new int[10];
            int indexOfSumCol = 0;
            for (int c = 1; c < col - 2; c++) {
                sum = 0;
                for (int r = 0; r < row; r++) {
                    sum += Integer.valueOf(jTableReport.getValueAt(r, c).toString());
                }
                sumCol[indexOfSumCol++] = sum;
            }
            totalStudent = sumCol[0];
            totalMale = sumCol[1];
            totalFemale = sumCol[2];
            totalPass = sumCol[3];
            totalFail = sumCol[4];
            averages = sumCol[5];

            Object obj[] = {"Tổng số: ", totalStudent, totalMale, totalFemale, totalPass, totalFail, averages};
            TableReport.addRow(obj);

//            report= new Report("", totalStudent, totalMale, totalFemale, totalPass, totalFail, 100);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private static JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart(
                title, dataset, true, true, true);
        return chart;
    }

    private static PieDataset createDataset(Object obj[], String condition) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        if (condition.equals("SLSV")) {
            dataset.setValue("Tổng Nam", (Number) obj[2]);
            dataset.setValue("Tổng Nữ", (Number) obj[3]);
        }
        if (condition.equals("TLSVDR")) {
            dataset.setValue("Tổng Sinh Viên Đạt", (Number) obj[4]);
            dataset.setValue("Tổng Sinh Viên Rớt", (Number) obj[5]);
        }

        return dataset;
    }

    private static JFrame newFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Báo Cáo");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

    private static void viewChart() {
        Object data[] = {nameClass, totalStudent, totalMale, totalFemale, totalPass, totalFail};

        String condition1 = "SLSV";
        String condition2 = "TLSVDR";

        JFreeChart pieChartPerson = createChart(createDataset(data, condition1), "TỈ LỆ GIỚI TÍNH");
        ChartPanel chartPanel = new ChartPanel(pieChartPerson);
        JFrame frame = newFrame();
        frame.add(chartPanel);
        
        JFreeChart pieChartPassAndFail = createChart(createDataset(data, condition2), "TỈ LỆ SINH VIÊN ĐẠT - RỚT");
        ChartPanel chartPanel2 = new ChartPanel(pieChartPassAndFail);
        JFrame frame2 = newFrame();
        frame2.add(chartPanel2);
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReport = new javax.swing.JTable();
        jLabelTitle = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemViewChart = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(957, 700));

        jTableReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableReport.setRowHeight(30);
        jScrollPane1.setViewportView(jTableReport);

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("MEDU  - REPORT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(55, 50));

        jMenu1.setText("Option");

        jMenuItemViewChart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItemViewChart.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemViewChart.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItemViewChart.setText("View Chart");
        jMenuItemViewChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewChartActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemViewChart);

        jMenuItemExit.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItemExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemViewChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewChartActionPerformed
        viewChart();
    }//GEN-LAST:event_jMenuItemViewChartActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        new ManagerLearningPoints().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemExitActionPerformed

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
            java.util.logging.Logger.getLogger(FormReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormReport().setVisible(true);
            }
        });
    }

    public FormReport() {
        initComponents();
        this.setLocationRelativeTo(null);
        TableReport = new DefaultTableModel(Header, 0);
        jTableReport.setModel(TableReport);
        //
        senDataToTablePoint();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemViewChart;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReport;
    // End of variables declaration//GEN-END:variables

}
