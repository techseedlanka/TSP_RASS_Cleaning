/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class RPT_Invoice_Estimated_income extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Invoice_Estimated_income
     */
    static ArrayList atten_sum;

    public RPT_Invoice_Estimated_income() {
        initComponents();
        atten_sum = new ArrayList();
    }

    private void shifts() {

        int days_in_Month = jCalendar1.getCalendar().getActualMaximum(Calendar.DATE);
        int month = jCalendar1.getCalendar().get(Calendar.MONTH);
        int year = jCalendar1.getCalendar().get(Calendar.YEAR);

        int d = month + 1;
        String d1 = null;

        if (d == 1) {
            d1 = "January";
        }
        if (d == 2) {
            d1 = "February";
        }
        if (d == 3) {
            d1 = "March";
        }
        if (d == 4) {
            d1 = "April";
        }
        if (d == 5) {
            d1 = "May";
        }
        if (d == 6) {
            d1 = "June";
        }
        if (d == 7) {
            d1 = "July";
        }
        if (d == 8) {
            d1 = "August";
        }
        if (d == 9) {
            d1 = "September";
        }
        if (d == 10) {
            d1 = "October";
        }
        if (d == 11) {
            d1 = "November";
        }
        if (d == 12) {
            d1 = "December";
        }

        try {

            Connection con = DbConnection.getconnection();

            PreparedStatement pst_loc = null;
            ResultSet rs_loc = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            PreparedStatement pst_rate = null;
            ResultSet rs_rate = null;
            PreparedStatement pst1 = null;
            ResultSet rs1 = null;

            String sql_loc = "select * from location_reg where LocStatus='1'";
            pst_loc = con.prepareStatement(sql_loc);
            rs_loc = pst_loc.executeQuery();
            while (rs_loc.next()) {

                String LOCCODE = rs_loc.getString("LocCode");
                String LOCNAME = rs_loc.getString("LocName");

                String sql = "SELECT * , SUM(NoOfGuards) FROM `location_carder` where LocCode ='" + LOCCODE + "' GROUP BY  Rank ";

                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {

                    int guards = rs.getInt("SUM(NoOfGuards)");
                    String rank = rs.getString("Rank");
                    int con_shifts = guards * days_in_Month;

                    String sql_rate = "select * from invoice_shift_rates where InvoShiftLoc='" + LOCCODE + "' and  InvoShiftRank='" + rank + "' ";
                    pst_rate = con.prepareStatement(sql_rate);
                    rs_rate = pst_rate.executeQuery();

                    while (rs_rate.next()) {

                        String Rate = rs_rate.getString("InvoShiftRate");
                        Double D_rate = Double.parseDouble(Rate);

                        Double D_Amount = (Double) (D_rate * con_shifts);
                        String Total_Amount = String.format("%.2f", D_Amount);

                        System.out.println(LOCCODE + "  " + rank + "  " + con_shifts + "  (" + guards + ") People   " + Rate + "   " + Total_Amount);

                        String sql1 = "SELECT  *, SUM(DayShift+NightShift) FROM `emp_atten_main` where Location='" + LOCCODE + "' and EffectiveRank='" + rank + "' ";
                        pst1 = con.prepareStatement(sql1);
                        rs1 = pst1.executeQuery();

                        while (rs1.next()) {

                            int shifts = rs1.getInt("SUM(DayShift+NightShift)");
                            String eff_rank = rs1.getString("EffectiveRank");

                            if (eff_rank == null) {

                                shifts = 0;
                                eff_rank = rank;
                            } else {
                                //shifts = rs1.getString("SUM(DayShift+NightShift)");
                                eff_rank = rs1.getString("EffectiveRank");
                            }

                            System.out.println("****WORKED DETAILS****");
                            System.out.println(LOCCODE + "  " + eff_rank + "  " + shifts);
                            System.out.println("============================================================");

                            bean_estimated_income bds = new bean_estimated_income();

                            bds.setLoc_code(LOCCODE);
                            bds.setLoc_name(LOCNAME);
                            bds.setContracted_shift(con_shifts);
                            bds.setAmount(Total_Amount);
                            bds.setRank(rank);
                            bds.setRate(Rate);
                            bds.setWorked_shift(shifts);

                            atten_sum.add(bds);

                        }

                    }

                }

            }

            print();
            atten_sum.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(atten_sum);
            String path = "Reports\\Estimated_invoice_amount.jrxml";//Attendance_Summery_Single_Loc
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton1.setText("View ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 230, 38));

        jCalendar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCalendar1.setMinimumSize(new java.awt.Dimension(251, 175));
        jCalendar1.setPreferredSize(new java.awt.Dimension(251, 167));
        getContentPane().add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 160, 22));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Invoice Estimated Income");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 310, 10));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel18.setText(" Month / Year :-");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 310, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        shifts();
//        try {
//
//            Connection con = (Connection) DbConnection.getconnection();
//            String sql = "select * from location_reg where LocStatus='1'";
//
//            PreparedStatement pst = con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//
//                String Loc_code = rs.getString("LocCode");
//
//                String sql_2 = "select *, SUM(DayShift+NightShift) from  emp_atten_main where Location='" + Loc_code + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'  group by EffectiveRank ";
//                PreparedStatement pst_2 = con.prepareStatement(sql_2);
//                ResultSet rs_2 = pst_2.executeQuery();
//
//                while (rs_2.next()) {
//
//                    System.out.println(Loc_code);
//                    System.out.println(rs_2.getString("EffectiveRank"));
//                    System.out.println(rs_2.getString("SUM(DayShift+NightShift)"));
//                    System.out.println("===============================");
//
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RPT_Invoice_Estimated_income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_Invoice_Estimated_income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_Invoice_Estimated_income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_Invoice_Estimated_income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_Invoice_Estimated_income().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
