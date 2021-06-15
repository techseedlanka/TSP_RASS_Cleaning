/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.RPT_All_EMP_Atten.EMP_atten;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sapu
 */
public class RPT_Total_Shifts_loc_wise extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Total_Shifts_loc_wise
     */
    static ArrayList shift_tot;

    public RPT_Total_Shifts_loc_wise() {
        initComponents();

        shift_tot = new ArrayList();
    }

    
    private void ttal_shifts(){
    
    
        
    
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmb_loc_type = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCalendar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCalendar1.setMinimumSize(new java.awt.Dimension(251, 175));
        jCalendar1.setPreferredSize(new java.awt.Dimension(251, 167));
        getContentPane().add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 160, 22));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 270, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Month / Year :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 40));

        cmb_loc_type.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_loc_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Bank Only" }));
        getContentPane().add(cmb_loc_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 120, -1));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Total Shift Summery - Location wise");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 280, 40));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 320, 10));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Location Type:-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, 40));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 320, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (cmb_loc_type.getSelectedItem().equals("All")) {

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

            String Month = (d1);
            String Year = Integer.toString(year);

            System.out.println(Month + " " + Year);

            String Loc = null;
            String Loc_Name = null;
            int guards = 0;
            int agreed_shifts = 0;
            Double worked_shifts = 0.0;
            Double variance = 0.0;

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from location_reg");
                while (rs.next()) {
                    Loc = rs.getString("LocCode");
                    Loc_Name = rs.getString("LocName");

                    Statement st1 = DbConnection.getconnection().createStatement();
                    ResultSet rs1 = st1.executeQuery("select *,SUM(NoOfGuards) from location_carder where LocCode='" + Loc + "'");
                    while (rs1.next()) {

                        if (rs1.getString("SUM(NoOfGuards)") == null) {
                            guards = 0;
                        } else {

                            guards = Integer.parseInt(rs1.getString("SUM(NoOfGuards)"));
                        }

                    }

                    Statement st2 = DbConnection.getconnection().createStatement();
                    ResultSet rs2 = st2.executeQuery("select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) from emp_atten_main where Location='" + Loc + "' and Month='" + Month + "' and Year='" + Year + "'");
                    while (rs2.next()) {

                        if (rs2.getString("Location") == null ) {

                        } else {

                            Double dayShift = Double.parseDouble(rs2.getString("SUM(DayShift)"));
                            Double nightShift = Double.parseDouble(rs2.getString("SUM(NightShift)"));
                            Double half = Double.parseDouble(rs2.getString("SUM(HalfDayShift)"));
                            Double halfShift = half / 2;

                            worked_shifts = dayShift + nightShift + halfShift;
                        }
                    }

                    agreed_shifts = days_in_Month * guards;

                    variance = agreed_shifts - worked_shifts;

                    bean_Total_Shifts bds = new bean_Total_Shifts();

                    bds.setLoc_code(Loc);
                    bds.setLoc_name(Loc_Name);
                    bds.setNo_of_guards(guards);
                    bds.setAgreed_shifts(agreed_shifts);
                    bds.setWorked_shifts(worked_shifts);
                    bds.setVariance(variance);
                    bds.setMonth(Month);
                    bds.setYear(Year);
                    bds.setLoc_type("All Locations");

                    shift_tot.add(bds);

                    worked_shifts = 0.0;

                }

                print();
                shift_tot.clear();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }
        } else if (cmb_loc_type.getSelectedItem().equals("Bank Only")) {

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

            String Month = (d1);
            String Year = Integer.toString(year);

            System.out.println(Month + " " + Year);

            String Loc = null;
            String Loc_Name = null;
            int guards = 0;
            int agreed_shifts = 0;
            Double worked_shifts = 0.0;
            Double variance = 0.0;

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from location_reg where Loctype='Bank'");
                while (rs.next()) {
                    Loc = rs.getString("LocCode");
                    Loc_Name = rs.getString("LocName");

                    Statement st1 = DbConnection.getconnection().createStatement();
                    ResultSet rs1 = st1.executeQuery("select *,SUM(NoOfGuards) from location_carder where LocCode='" + Loc + "'");
                    while (rs1.next()) {

                        if (rs1.getString("SUM(NoOfGuards)") == null) {
                            guards = 0;
                        } else {

                            guards = Integer.parseInt(rs1.getString("SUM(NoOfGuards)"));
                        }

                    }

                    Statement st2 = DbConnection.getconnection().createStatement();
                    ResultSet rs2 = st2.executeQuery("select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) from emp_atten_main where Location='" + Loc + "' and Month='" + Month + "' and Year='" + Year + "'");
                    while (rs2.next()) {

                        if (rs2.getString("Location") == null ) {

                        } else {

                            Double dayShift = Double.parseDouble(rs2.getString("SUM(DayShift)"));
                            Double nightShift = Double.parseDouble(rs2.getString("SUM(NightShift)"));
                            Double half = Double.parseDouble(rs2.getString("SUM(HalfDayShift)"));
                            Double halfShift = half / 2;

                            worked_shifts = dayShift + nightShift + halfShift;
                        }
                    }

                    agreed_shifts = days_in_Month * guards;

                    variance = agreed_shifts - worked_shifts;

                    bean_Total_Shifts bds = new bean_Total_Shifts();

                    bds.setLoc_code(Loc);
                    bds.setLoc_name(Loc_Name);
                    bds.setNo_of_guards(guards);
                    bds.setAgreed_shifts(agreed_shifts);
                    bds.setWorked_shifts(worked_shifts);
                    bds.setVariance(variance);
                    bds.setMonth(Month);
                    bds.setYear(Year);
                    bds.setLoc_type("Location : Banks Only");

                    shift_tot.add(bds);

                    worked_shifts = 0.0;

                }

                print();
                shift_tot.clear();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }

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
            java.util.logging.Logger.getLogger(RPT_Total_Shifts_loc_wise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_Total_Shifts_loc_wise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_Total_Shifts_loc_wise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_Total_Shifts_loc_wise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_Total_Shifts_loc_wise().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_loc_type;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    // End of variables declaration//GEN-END:variables

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(shift_tot);
            String path = "Reports\\total_shift_loc_wise.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
