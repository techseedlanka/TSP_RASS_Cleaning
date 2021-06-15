/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sapu
 */
public class RPT_No_of_Shifts_in_Selected_DateRange extends javax.swing.JFrame {

    /**
     * Creates new form RPT_No_of_Shifts_in_Selected_DateRange
     */
    public RPT_No_of_Shifts_in_Selected_DateRange() {
        initComponents();
        TitleBar();
    }
    
    private void TitleBar() {

        try {
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg where isDefault=1 ");
            while (rs.next()) {
                String ComName = rs.getString("ComName");
                this.setTitle("TechSeed Payroll" + "    -    " + ComName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setResizable(false);
        ImageIcon img = new ImageIcon("Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

    private void search() {

        if (Date_from.getDate() == null | Date_to.getDate() == null) {

        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String FromDate = sdf.format(Date_from.getDate());
            String ToDate = sdf.format(Date_to.getDate());

            String begin = txt_begin.getText();
            String end = txt_end.getText();

            try {

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\RPT_ShiftRateDetails_SingleEmployee.jrxml");
////
//                String sql = "SELECT emp_atten_main.EPFno,SUM(DayShift+NightShift), SUM(DayShift+NightShift) BETWEEN '"+begin+"' AND '"+end+"' , NameWithInitials FROM `emp_atten_main` ,`employee_reg`  "
//                        + "WHERE DATE BETWEEN '"+FromDate+"' AND '"+ToDate+"'  AND emp_atten_main.EPFno = employee_reg.`EPFno` GROUP BY EPFno";

                String sql = "SELECT MAX(DATE) AS max_date ,MIN(DATE) AS min_date , emp_atten_main.*,emp_atten_main.EPFno,SUM(DayShift+NightShift+HalfDayShift/2),NameWithInitials FROM `emp_atten_main` ,`employee_reg` WHERE DATE BETWEEN '" + FromDate + "' AND '" + ToDate + "'  AND emp_atten_main.EPFno = employee_reg.`EPFno` GROUP BY EPFno HAVING SUM(DayShift+NightShift+HalfDayShift/2) BETWEEN '" + begin + "' AND '" + end + "' ;";

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Error!!! Contact System Administrator" + "  " + e);

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Date_to = new com.toedter.calendar.JDateChooser();
        Date_from = new com.toedter.calendar.JDateChooser();
        txt_begin = new javax.swing.JTextField();
        txt_end = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Date_to.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Date_to.setDateFormatString("yyyy-MM-dd");
        Date_to.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(Date_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 110, 23));

        Date_from.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Date_from.setDateFormatString("yyyy-MM-dd");
        Date_from.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(Date_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 110, 23));

        txt_begin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_begin.setText("03");
        getContentPane().add(txt_begin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 50, 23));

        txt_end.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_end.setText("10");
        getContentPane().add(txt_end, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 50, 23));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Employee Total Shifts - Date Range Wise");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 320, 40));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 420, 10));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("To :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, 20));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("No. of Shifts ;      Between :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Date Range ;       From :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText("To :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton1.setText("Veiw Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 190, 40));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 420, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        search();

    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RPT_No_of_Shifts_in_Selected_DateRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_No_of_Shifts_in_Selected_DateRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_No_of_Shifts_in_Selected_DateRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_No_of_Shifts_in_Selected_DateRange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_No_of_Shifts_in_Selected_DateRange().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_from;
    private com.toedter.calendar.JDateChooser Date_to;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JTextField txt_begin;
    private javax.swing.JTextField txt_end;
    // End of variables declaration//GEN-END:variables
}
