/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

//import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class RPT_rate_change_shifts_summery extends javax.swing.JFrame {

    /**
     * Creates new form RPT_rate_change_shifts_summery
     */
    public RPT_rate_change_shifts_summery() {
        initComponents();

        get_Location_Details();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
   
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txt_locName = new javax.swing.JTextField();
        cmb_defLocation = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 110, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 70, -1));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 120, 50));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Rate Change Attendance Summery Report");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 320, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 360, 10));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 360, 10));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Please Process the Attendance (if not) Before Print this Report.");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 360, -1));

        txt_locName.setEditable(false);
        txt_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_locNameFocusLost(evt);
            }
        });
        getContentPane().add(txt_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 340, 30));

        cmb_defLocation.setEditable(true);
        cmb_defLocation.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_defLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=Location=" }));
        cmb_defLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_defLocationFocusGained(evt);
            }
        });
        cmb_defLocation.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_defLocationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 200, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setText("Month /Year :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 40));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setText("Location :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 70, 40));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton2.setText("Accountant's Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 200, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
        //        int mm = cmb_month.getSelectedIndex();
        //        int int_month = mm + 1;
        //        String month = String.format("%02d", int_month);
        //
        //        String year = cmb_year.getSelectedItem().toString();
        //        int int_year = Integer.parseInt(year);
        //
        //        String d1 = (year + "-" + month + "-" + "01");
        //        String d2 = (year + "-" + month + "-" + "02");
        //        String d3 = (year + "-" + month + "-" + "03");
        //        String d4 = (year + "-" + month + "-" + "04");
        //        String d5 = (year + "-" + month + "-" + "05");
        //        String d6 = (year + "-" + month + "-" + "06");
        //        String d7 = (year + "-" + month + "-" + "07");
        //        String d8 = (year + "-" + month + "-" + "08");
        //        String d9 = (year + "-" + month + "-" + "09");
        //        String d10 = (year + "-" + month + "-" + "10");
        //        String d11 = (year + "-" + month + "-" + "11");
        //        String d12 = (year + "-" + month + "-" + "12");
        //        String d13 = (year + "-" + month + "-" + "13");
        //        String d14 = (year + "-" + month + "-" + "14");
        //        String d15 = (year + "-" + month + "-" + "15");
        //        String d16 = (year + "-" + month + "-" + "16");
        //        String d17 = (year + "-" + month + "-" + "17");
        //        String d18 = (year + "-" + month + "-" + "18");
        //        String d19 = (year + "-" + month + "-" + "19");
        //        String d20 = (year + "-" + month + "-" + "20");
        //        String d21 = (year + "-" + month + "-" + "21");
        //        String d22 = (year + "-" + month + "-" + "22");
        //        String d23 = (year + "-" + month + "-" + "23");
        //        String d24 = (year + "-" + month + "-" + "24");
        //        String d25 = (year + "-" + month + "-" + "25");
        //        String d26 = (year + "-" + month + "-" + "26");
        //        String d27 = (year + "-" + month + "-" + "27");
        //        String d28 = (year + "-" + month + "-" + "28");
        //        String d29 = (year + "-" + month + "-" + "29");
        //        String d30 = (year + "-" + month + "-" + "30");
        //        String d31 = (year + "-" + month + "-" + "31");
        //
        //        String d1d = "0";
        //        String d2d = "0";
        //        String d3d = "0";
        //        String d4d = "0";
        //        String d5d = "0";
        //        String d6d = "0";
        //        String d7d = "0";
        //        String d8d = "0";
        //        String d9d = "0";
        //        String d10d = "0";
        //        String d11d = "0";
        //        String d12d = "0";
        //        String d13d = "0";
        //        String d14d = "0";
        //        String d15d = "0";
        //        String d16d = "0";
        //        String d17d = "0";
        //        String d18d = "0";
        //        String d19d = "0";
        //        String d20d = "0";
        //        String d21d = "0";
        //        String d22d = "0";
        //        String d23d = "0";
        //        String d24d = "0";
        //        String d25d = "0";
        //        String d26d = "0";
        //        String d27d = "0";
        //        String d28d = "0";
        //        String d29d = "0";
        //        String d30d = "0";
        //        String d31d = "0";
        //
        //        String d1n = "0";
        //        String d2n = "0";
        //        String d3n = "0";
        //        String d4n = "0";
        //        String d5n = "0";
        //        String d6n = "0";
        //        String d7n = "0";
        //        String d8n = "0";
        //        String d9n = "0";
        //        String d10n = "0";
        //        String d11n = "0";
        //        String d12n = "0";
        //        String d13n = "0";
        //        String d14n = "0";
        //        String d15n = "0";
        //        String d16n = "0";
        //        String d17n = "0";
        //        String d18n = "0";
        //        String d19n = "0";
        //        String d20n = "0";
        //        String d21n = "0";
        //        String d22n = "0";
        //        String d23n = "0";
        //        String d24n = "0";
        //        String d25n = "0";
        //        String d26n = "0";
        //        String d27n = "0";
        //        String d28n = "0";
        //        String d29n = "0";
        //        String d30n = "0";
        //        String d31n = "0";
        //
        //        String d1h = "0";
        //        String d2h = "0";
        //        String d3h = "0";
        //        String d4h = "0";
        //        String d5h = "0";
        //        String d6h = "0";
        //        String d7h = "0";
        //        String d8h = "0";
        //        String d9h = "0";
        //        String d10h = "0";
        //        String d11h = "0";
        //        String d12h = "0";
        //        String d13h = "0";
        //        String d14h = "0";
        //        String d15h = "0";
        //        String d16h = "0";
        //        String d17h = "0";
        //        String d18h = "0";
        //        String d19h = "0";
        //        String d20h = "0";
        //        String d21h = "0";
        //        String d22h = "0";
        //        String d23h = "0";
        //        String d24h = "0";
        //        String d25h = "0";
        //        String d26h = "0";
        //        String d27h = "0";
        //        String d28h = "0";
        //        String d29h = "0";
        //        String d30h = "0";
        //        String d31h = "0";
        //
        //        try {
        //
        //            Statement st = DbConnection.getconnection().createStatement();
        //
        //            ResultSet rs = st.executeQuery("select LocName,Location, Date, SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) from `location_reg`INNER JOIN `emp_atten_main`  ON location_reg.`LocCode` = emp_atten_main.`Location` where Month='February' group by Date");
        //
        //            //ResultSet rs = st.executeQuery("SELECT Location, Date, SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) FROM `location_reg`INNER JOIN `emp_atten_main`  ON location_reg.`LocCode` = emp_atten_main.`Location`  WHERE MONTH='" + cmb_month.getSelectedItem().toString() + "' GROUP BY Date ");
        //            while (rs.next()) {
        //
        //                String Date = rs.getString("Date");
        //
        //                if (Date.equals(d1)) {
        //
        //                    d1d = rs.getString("SUM(DayShift)");
        //                    d1n = rs.getString("SUM(NightShift)");
        //
        //                    String half = rs.getString("SUM(HalfDayShift)");
        //                    Double halfday = Double.parseDouble(half);
        //
        //                    d1h = Double.toString(halfday / 2);
        //
        //                }
        //                if (Date.equals(d2)) {
        //
        //                    d2d = rs.getString("SUM(DayShift)");
        //                    d2n = rs.getString("SUM(NightShift)");
        //
        //                    String half = rs.getString("SUM(HalfDayShift)");
        //                    Double halfday = Double.parseDouble(half);
        //
        //                    d2h = Double.toString(halfday / 2);
        //
        //                }
        //                if (Date.equals(d3)) {
        //
        //                    d3d = rs.getString("SUM(DayShift)");
        //                    d3n = rs.getString("SUM(NightShift)");
        //
        //                    String half = rs.getString("SUM(HalfDayShift)");
        //                    Double halfday = Double.parseDouble(half);
        //
        //                    d3h = Double.toString(halfday / 2);
        //
        //                }
        //
        //                String LocCode = rs.getString("Location");
        //                String LocName = rs.getString("LocName");
        //
        //                System.out.println(LocCode);
        //                System.out.println(LocName);
        //
        //                bean_ateen_summery bds = new bean_ateen_summery();
        //
        //                bds.setL_code(LocCode);
        //                bds.setL_name(LocName);
        //
        //                bds.setDay1day(d1d);
        //                bds.setDay1night(d1n);
        //                bds.setDay1half(d1h);
        //
        //                bds.setDay2day(d2d);
        //                bds.setDay2night(d2n);
        //                bds.setDay2half(d2h);
        //
        //                atten_sum.add(bds);
        //            }
        //
        //            print();
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (cmb_defLocation.getSelectedIndex() == 0) {

            try {

                String month = cmb_month.getSelectedItem().toString();
                String year = cmb_year.getSelectedItem().toString();

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\EMP_rate_change_summery.jrxml");
                String sql = "SELECT *,employee_reg.`NameWithInitials` ,employee_reg.`Designation`  ,employee_reg.`ShiftRate` ,location_reg.`LocName` FROM `emp_atten_rate_change_shift_summery` emp_atten_rate_change_shift_summery,`employee_reg` employee_reg,`location_reg` location_reg where emp_atten_rate_change_shift_summery.`EPFno`= employee_reg.`EmployeeNo` and emp_atten_rate_change_shift_summery.`LocCode`=  location_reg.`LocCode` and emp_atten_rate_change_shift_summery.`Month`='" + month + "' and emp_atten_rate_change_shift_summery.`Year`='" + year + "' ORDER BY employee_reg.EmployeeNo ASC";

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }

        } else {

            try {

                String month = cmb_month.getSelectedItem().toString();
                String year = cmb_year.getSelectedItem().toString();

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\EMP_rate_change_summery_Loc_Wise.jrxml");
                //  String sql = "SELECT *,employee_reg.`NameWithInitials` ,employee_reg.`Designation`  ,employee_reg.`ShiftRate` ,location_reg.`LocName` FROM `emp_atten_rate_change_shift_summery` emp_atten_rate_change_shift_summery,`employee_reg` employee_reg,`location_reg` location_reg where emp_atten_rate_change_shift_summery.`EPFno`= employee_reg.`EPFno` and emp_atten_rate_change_shift_summery.`LocCode`= '"+cmb_defLocation.getSelectedItem().toString()+"' and emp_atten_rate_change_shift_summery.`Month`='" + month + "' and emp_atten_rate_change_shift_summery.`Year`='" + year + "'";

                String sql = "SELECT *,employee_reg.`NameWithInitials` FROM `emp_atten_rate_change_shift_summery`,`employee_reg` WHERE `emp_atten_rate_change_shift_summery`.LocCode='"+cmb_defLocation.getSelectedItem().toString()+"' AND emp_atten_rate_change_shift_summery.Year = '"+year+"'AND emp_atten_rate_change_shift_summery.MONTH='"+month+"' AND employee_reg.`EPFno`  = `emp_atten_rate_change_shift_summery`.`EPFno` ORDER BY employee_reg.EPFno * 1 ASC";

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_locNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locNameFocusGained

    private void txt_locNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locNameFocusLost

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locName.setText(" All Locations ");
            txt_locName.setForeground(Color.red);

        } else {
            txt_locName.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(code);
                    txt_locName.setText(name);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {

                String month = cmb_month.getSelectedItem().toString();
                String year = cmb_year.getSelectedItem().toString();

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\EMP_rate_change_summery_ACC_RPT.jrxml");
                String sql = "SELECT *,employee_reg.`NameWithInitials` ,employee_reg.`Designation`  ,employee_reg.`ShiftRate` ,location_reg.`LocName` FROM `emp_atten_rate_change_shift_summery` emp_atten_rate_change_shift_summery,`employee_reg` employee_reg,`location_reg` location_reg where emp_atten_rate_change_shift_summery.`EPFno`= employee_reg.`EPFno` and emp_atten_rate_change_shift_summery.`LocCode`=  location_reg.`LocCode` and emp_atten_rate_change_shift_summery.`Month`='" + month + "' and emp_atten_rate_change_shift_summery.`Year`='" + year + "' ORDER BY employee_reg.EPFno * 1 ASC";

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(RPT_rate_change_shifts_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_rate_change_shifts_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_rate_change_shifts_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_rate_change_shifts_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_rate_change_shifts_summery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField txt_locName;
    // End of variables declaration//GEN-END:variables

    private void get_Location_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from location_reg order by LocName");
            while (rs.next()) {

                Object name = rs.getString("LocName");
                //Object code = rs.getString("LocCode");

                //cmb_defLocation.addItem(code);
                cmb_defLocation.addItem(name);
            }

            AutoCompleteDecorator.decorate(cmb_defLocation);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
