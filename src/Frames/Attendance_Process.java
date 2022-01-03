/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Attendance_Process extends javax.swing.JFrame {

    /**
     * Creates new form Attendance_Process
     */
    public Attendance_Process() {
        initComponents();

        lbl_atten.setVisible(false);
        TitleBar();

    }

    private void TitleBar() {

        this.setTitle("Attendance Process");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));

    }

//    public void OLD_ATTEN_PROCESS() {
//        long start = System.currentTimeMillis();
//
////        String startd = lbl_startDate.getText();
////        String end = lbl_endDate.getText();
//        rate_change_shift_summery();
//
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("SELECT EPFno ,  SUM(NightShift+DayShift+(HalfDayShift/2)) FROM emp_atten_main WHERE Status='pending' AND  DATE BETWEEN '" + startd + "' AND '" + end + "'   GROUP BY EPFno ASC");
//
//            while (rs.next()) {
//
//                String epf = rs.getString("EPFno");
//                String total = rs.getString("SUM(NightShift+DayShift+(HalfDayShift/2))");
//
//                Double y = Double.parseDouble(total);
//
//                System.out.println("EPf:" + epf + " " + "total " + y);
//
////// ******************* update Status column as 'processed' in emp_atten_main **********************
//                if (y < 8) {
//
//                    //// ******************* get epfno,name,total worked shifts of emp's who do not worked more than 8 shifts **********************             
//                    Statement st2 = DbConnection.getconnection().createStatement();
//                    ResultSet rs2 = st2.executeQuery("select * from employee_reg where EPFno = '" + epf + "'");
//
//                    while (rs2.next()) {
//
//                        String name = rs2.getString("NameWithInitials");
//
//                     //   DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//                        Vector v = new Vector();
//
//                        v.add(epf);
//                        v.add(name);
//                        v.add(total);
//
//                       // dtm.addRow(v);
//
//                    }
//
//                }
//            }
//
//            Statement st2 = DbConnection.getconnection().createStatement();
//            ResultSet rs2 = st2.executeQuery("SELECT *, count(EPFno) FROM emp_atten_main WHERE Status='pending' and Month = '" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "'  GROUP BY EPFno");
//            while (rs2.next()) {
//
//                String EPFno = rs2.getString("EPFno");
//
//                Statement st1 = DbConnection.getconnection().createStatement();
//                st1.executeUpdate("update emp_atten_main  set Status = 'processed'  where EPFno = '" + EPFno + "' and Month ='" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "' ");
//
//            }
//
//            lbl_atten.setText("Attendance Processed Succesfully...!");
//            Color cl = new Color(0, 153, 51);
//            lbl_atten.setForeground(cl);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        get_no_of_AttenProcessed_epms_();
//        btn_Process.setText("Processed");
//
//        long duration = System.currentTimeMillis() - start;
//
//        DateFormat df = new SimpleDateFormat("HH 'Hours', mm 'Min(s),' ss 'Second(s)'");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//        System.out.println(df.format(new Date(duration)));
//        JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));
//
//    }
    public void NEW_ATTEN_PROCESS() {
        long start = System.currentTimeMillis();
//        long duration = System.currentTimeMillis() - start;
//
//        DateFormat df = new SimpleDateFormat("HH 'Hours', mm 'Min(s),' ss 'Second(s)'");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//        System.out.println(df.format(new Date(duration)));
//        JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));

//        String startd = lbl_startDate.getText();
//        String end = lbl_endDate.getText();
        rate_change_shift_summery();

        try {

//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("SELECT EPFno ,  SUM(NightShift+DayShift+(HalfDayShift/2)) FROM emp_atten_main WHERE Status='pending' AND  DATE BETWEEN '" + startd + "' AND '" + end + "'   GROUP BY EPFno ASC");
//
//            while (rs.next()) {
//
//                String epf = rs.getString("EPFno");
//                String total = rs.getString("SUM(NightShift+DayShift+(HalfDayShift/2))");
//
//                Double y = Double.parseDouble(total);
//                
//                System.out.println("EPf:"+epf+" "+"total "+y);
//// ******************* update Status column as 'processed' in emp_atten_main **********************
//                if (y < 8) {
//
//                    // ******************* get epfno,name,total worked shifts of emp's who do not worked more than 8 shifts **********************             
//                    Statement st2 = DbConnection.getconnection().createStatement();
//                    ResultSet rs2 = st2.executeQuery("select * from employee_reg where EPFno = '" + epf + "'");
//
//                    while (rs2.next()) {
//
//                        String name = rs2.getString("NameWithInitials");
//
//                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//                        Vector v = new Vector();
//
//                        v.add(epf);
//                        v.add(name);
//                        v.add(total);
//
//                        dtm.addRow(v);
//
//                    }
//
//                }
//            }
            Statement st2 = DbConnection.getconnection().createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT *, count(EmployeeNo) FROM emp_atten_main WHERE Status='pending' and Month = '" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "'  GROUP BY EmployeeNo");
            while (rs2.next()) {

                String Employee_no = rs2.getString("EmployeeNo");

                Statement st1 = DbConnection.getconnection().createStatement();
                st1.executeUpdate("update emp_atten_main  set Status = 'processed'  where EmployeeNo = '" + Employee_no + "' and Month ='" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "' ");

            }

            lbl_atten.setText("Attendance Processed Succesfully...!");
            Color cl = new Color(0, 153, 51);
            lbl_atten.setForeground(cl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        get_no_of_AttenProcessed_epms_();
        btn_Process.setText("Processed");

        long duration = System.currentTimeMillis() - start;

        DateFormat df = new SimpleDateFormat("HH 'Hours', mm 'Min(s),' ss 'Second(s)'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(df.format(new Date(duration)));
        JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));

    }

    public void epf() {

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = null;
            ResultSet rs = null;

            String sql = "select * from employee_reg";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String epf = rs.getString("EPFno");

                System.out.println(epf);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void special_holidays() {

        try {

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            Connection con = DbConnection.getconnection();

            PreparedStatement pst = con.prepareStatement("select * from special_holidays_days where Month='" + month + "' and Year='" + year + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String date = rs.getString("Date");

                String emp = "0";
                String loc = "0";
                String rank = "0";
                String poya_rate = "0";
                //String rank = "0";
                PreparedStatement pst1 = con.prepareStatement("select *,SUM(DayShift+NightShift) from emp_atten_main where Date ='" + date + "' and Month='" + month + "' and Year='" + year + "' group by EPFno");
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    emp = rs1.getString("EPFno");
                    String duty = rs1.getString("SUM(DayShift+NightShift)");

                    PreparedStatement pst2 = con.prepareStatement("select * from employee_reg where EmployeeNo ='" + emp + "'");
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        loc = rs2.getString("DefLocation");
                        rank = rs2.getString("Designation");
                        PreparedStatement pst3 = con.prepareStatement("select * from salary_rates where LocCode ='" + loc + "' and RankCode='" + rank + "'");
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {

                            poya_rate = rs3.getString("PoyaDay");
                            Double rate = Double.parseDouble(poya_rate);
                            Double shifts = Double.parseDouble(duty);
                            Double poya_amt = rate * shifts;
                            String poya_amount = String.format("%.2f", poya_amt);
                            if (poya_rate.equals("0.00")) {

                            } else {
                                PreparedStatement pst4 = con.prepareStatement("insert into  special_holiday_earnings  values ('" + emp + "','0','" + poya_amount + "','" + month + "','" + year + "')");
                                pst4.execute();
                            }

                        }

                    }

                }

            }
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sunday_amounts() {

        try {

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            Connection con = DbConnection.getconnection();

            PreparedStatement pst_del = con.prepareStatement("DELETE FROM `special_holiday_earnings` where Month='" + month + "' and Year='" + year + "'");
            pst_del.execute();

            PreparedStatement pst = con.prepareStatement("select * from sundays where Month='" + month + "' and Year='" + year + "'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String date = rs.getString("Date");

                String emp = "0";
                String loc = "0";
                String rank = "0";
                String sunday_rate = "0";
                String poya_rate = "0";
                String duty = "0";
                //String rank = "0";
                PreparedStatement pst1 = con.prepareStatement("select *,SUM(DayShift+NightShift) from emp_atten_main where Date ='" + date + "' and Month='" + month + "' and Year='" + year + "' group by EPFno");
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    duty = rs1.getString("SUM(DayShift+NightShift)");
                    emp = rs1.getString("EPFno");

                    PreparedStatement pst2 = con.prepareStatement("select * from employee_reg where EmployeeNo ='" + emp + "'");
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        loc = rs2.getString("DefLocation");
                        rank = rs2.getString("Designation");
                        PreparedStatement pst3 = con.prepareStatement("select * from salary_rates where LocCode ='" + loc + "' and RankCode='" + rank + "'");
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {

                            sunday_rate = rs3.getString("Sunday");

                            if (sunday_rate.equals("0.00")) {

                            } else {

                                Double D_duty = Double.parseDouble(duty);
                                Double D_rate = Double.parseDouble(sunday_rate);
                                Double amt = D_duty * D_rate;

                                String sun_amt = String.format("%.2f", amt);

                                PreparedStatement pst4 = con.prepareStatement("insert into  special_holiday_earnings  values ('" + emp + "','" + sun_amt + "','0','" + month + "','" + year + "')");
                                pst4.execute();
                            }

                        }

                    }

                }

            }
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }

        special_holidays();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cmb_year = new javax.swing.JComboBox();
        cmb_month = new javax.swing.JComboBox();
        btn_Process = new javax.swing.JButton();
        lbl_atten = new javax.swing.JLabel();
        lbl_runtime = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Attendance Process - Ground Staff");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1110, 10));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));
        cmb_year.setSelectedIndex(7);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 110, -1));

        btn_Process.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        btn_Process.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Workflow-48.png"))); // NOI18N
        btn_Process.setText("Process");
        btn_Process.setIconTextGap(15);
        btn_Process.setPreferredSize(new java.awt.Dimension(145, 55));
        btn_Process.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ProcessMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ProcessMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ProcessMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ProcessMousePressed(evt);
            }
        });
        btn_Process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcessActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Process, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 330, 50));

        lbl_atten.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        lbl_atten.setForeground(new java.awt.Color(0, 153, 51));
        lbl_atten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_atten.setText("Attendance Processed Succesfully...!");
        lbl_atten.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_atten, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 330, 40));

        lbl_runtime.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbl_runtime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lbl_runtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 280, 450, 20));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1110, 10));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 380, 10));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, 30));

        jProgressBar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jProgressBar1.setOpaque(true);
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 330, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Name", "Loc. Code", "Location Name", "Date", "D", "N", "H", "OT", "DN", "OTH", "Line No"
            }
        ));
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(80);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 700, 200));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        jLabel6.setText("Effective Month / Year:-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancel.png"))); // NOI18N
        jButton1.setText("Delete ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 130, 40));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel7.setText("Duplicated Attendance Records");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcessActionPerformed
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                jProgressBar1.setMaximum(100);
                btn_Process.setEnabled(false);
                //  btn_Process.set(false);
                find_duplicated_data();

                if (jTable1.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Please Delete the Duplicated Data...");
                } else {

                    // atten_main_process();
                    atten_summery_process();
                }

                long duration = System.currentTimeMillis() - start;

                DateFormat df = new SimpleDateFormat("HH'h' mm'm' ss's'");
                df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
                System.out.println(df.format(new Date(duration)));
                lbl_runtime.setText("Total Time for the Last Process: " + df.format(new Date(duration)));

            }
        });
        hilo.start();

    }//GEN-LAST:event_btn_ProcessActionPerformed

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void btn_ProcessMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ProcessMouseEntered
        btn_Process.setForeground(Color.cyan);
    }//GEN-LAST:event_btn_ProcessMouseEntered

    private void btn_ProcessMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ProcessMouseExited
        btn_Process.setForeground(Color.black);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ProcessMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void btn_ProcessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ProcessMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ProcessMouseClicked

    private void btn_ProcessMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ProcessMousePressed
        lbl_atten.setText("Processing... Please Wait...!");
        lbl_atten.setForeground(Color.red);
        lbl_atten.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ProcessMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Connection con = DbConnection.getconnection();
            int nrow = jTable1.getSelectedRowCount();

            if (nrow < 1) {
            } else {

                int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to DELETE the selected Attendance Entry ?", null, JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    int row = jTable1.getSelectedRow();
                    String Line = jTable1.getValueAt(row, 11).toString();
                    PreparedStatement pst_del = null;
                    System.out.println(Line);
                    String sql_del = "delete from emp_atten_main where Line='" + Line + "'  ";
                    pst_del = con.prepareStatement(sql_del);
                    pst_del.execute();

                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(jTable1.getSelectedRow());

                    //JOptionPane.showMessageDialog(rootPane, "Selected Attendance Entry Deleted..!");
                } else {

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
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
            java.util.logging.Logger.getLogger(Attendance_Process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Attendance_Process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Attendance_Process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Attendance_Process.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Attendance_Process().setVisible(true);
            }
        });
    }

    private void get_no_of_AttenProcessed_epms_() {

        try {
            String count = "";

            Statement st2 = DbConnection.getconnection().createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) FROM `emp_atten_main` WHERE  Month = '" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "'  GROUP BY EPFno");
            while (rs2.next()) {

                count = rs2.getString("COUNT(*)");

            }

//            lbl_no_of_emps.setText(count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void rate_change_shift_summery() {

        //emp_atten_rate_change_shift_summery
        Double totShift = 0.00;
        Double halfDay = 0.00;
        Double full_shift = 0.00;
        Double dRate = 0.00;
        String epfno = null;
        String loc = null;
        String rate = null;
        String amount = null;
        String tot = null;

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = null;
            PreparedStatement pst1 = null;
            PreparedStatement pst2 = null;
            PreparedStatement pst3 = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;

            String delete = "delete from emp_atten_rate_change_shift_summery where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' ";
            pst = con.prepareStatement(delete);
            pst.executeUpdate();
            pst.close();

            String get_epfno = "select * from emp_atten_main where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' GROUP BY EmployeeNo ASC";
            pst = con.prepareStatement(get_epfno);
            rs = pst.executeQuery();

            while (rs.next()) {

                String get_location = "select * from emp_atten_main where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' GROUP BY Location";
                pst1 = con.prepareStatement(get_location);
                rs1 = pst1.executeQuery();

                epfno = rs.getString("EmployeeNo");

                while (rs1.next()) {

                    String LocName = rs1.getString("Location");

                    String get_rate = "select *,SUM(DayShift+NightShift),SUM(HalfDayShift) from emp_atten_main  "
                            + "where EmployeeNo ='" + epfno + "' and Month='" + cmb_month.getSelectedItem().toString() + "' "
                            + "and Year='" + cmb_year.getSelectedItem().toString() + "' and Location='" + LocName + "'  "
                            + "GROUP BY EffectiveRate ";

                    pst2 = con.prepareStatement(get_rate);
                    rs2 = pst2.executeQuery();

                    while (rs2.next()) {

                        loc = rs2.getString("Location");

                        String dn_shift = rs2.getString("SUM(DayShift+NightShift)");
                        rate = rs2.getString("EffectiveRate");
                        String half_shift = rs2.getString("SUM(HalfDayShift)");

                        full_shift = Double.parseDouble(dn_shift);
                        Double halfDay1 = Double.parseDouble(half_shift);
                        dRate = Double.parseDouble(rate);

                        halfDay = halfDay1 / 2;

                        totShift = full_shift + halfDay;
                        Double amt = totShift * dRate;
                        amount = Double.toString(amt);
                        tot = Double.toString(totShift);
//
//                        System.out.println(epfno);
//                        System.out.println(loc);
//                        System.out.println(rate);
//                        System.out.println(amount);
//                        System.out.println(tot);

//                        Statement st3 = DbConnection.getconnection().createStatement();
//                        ResultSet rs3 = st3.executeQuery("select *,COUNT(EPFno) from emp_atten_rate_change_shift_summery where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
//                        while (rs3.next()) {
//                            System.out.println("st3");
//                            int count = Integer.parseInt(rs3.getString("COUNT(EPFno)"));
                        //// Statement st4 = DbConnection.getconnection().createStatement();
//                            if (count == 0) {
                        //save
                        String save = "insert into emp_atten_rate_change_shift_summery values('" + epfno + "', '" + LocName + "' , '" + rate + "' , '" + amount + "', '" + tot + "','" + cmb_month.getSelectedItem().toString() + "','" + cmb_year.getSelectedItem().toString() + "' )";
                        pst3 = con.prepareStatement(save);
                        pst3.execute();

                        System.out.println("==============save==============");
//                            } else {
////                         
//                                st4.executeUpdate("insert into emp_atten_rate_change_shift_summery values('" + epfno + "', '" + LocName + "' , '" + rate + "' , '" + amount + "', '" + tot + "','" + cmb_month.getSelectedItem().toString() + "','" + cmb_year.getSelectedItem().toString() + "' )");
//
//                                System.out.println("save");
////                            }
//
//                            }

//                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Process;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_atten;
    private javax.swing.JLabel lbl_runtime;
    // End of variables declaration//GEN-END:variables

    //target attn process
    private void atten_main_process() {

        try {
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

//***********get rates from 'salary_rates' table & set in to 'emp_atten_main' table********
            jProgressBar1.setValue(0);

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = null;
            String sql = "select * from emp_atten_main where Month='" + month + "' and Year='" + year + "' and RateChangedType='auto' group by Location,EffectiveRank";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                jProgressBar1.setValue(10);
                lbl_atten.setText("Checking Shift Rates...");
                Color cl = new Color(110, 110, 242);
                lbl_atten.setForeground(cl);

                String rank = rs.getString("EffectiveRank");
                String loc = rs.getString("Location");

                PreparedStatement pst2 = null;
                String sql2 = "select * from salary_rates where RankCode='" + rank + "' and LocCode='" + loc + "' ";
                pst2 = con.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {

                    String day_rate = rs2.getString("DayRate");
                    String night_rate = rs2.getString("NightRate");
                    String dn_rate = rs2.getString("DNRate");
                    String ot_rate = rs2.getString("OTRate");

                    PreparedStatement pst_update = null;
                    String sql_update = "update emp_atten_main set DayRate='" + day_rate + "', NightRate='" + night_rate + "', OTHRate='" + ot_rate + "', DNRate='" + dn_rate + "' where Location='" + loc + "' and EffectiveRank='" + rank + "'  and RateChangedType='auto'  ";
                    pst_update = con.prepareStatement(sql_update);
                    pst_update.executeUpdate();

                    lbl_atten.setText("Checking for Null Data...");
                    lbl_atten.setForeground(cl);

                    PreparedStatement pst_update_null = null;
                    String sql_update_null = "update emp_atten_main set DNShift='0' where DNShift=''  ";
                    pst_update_null = con.prepareStatement(sql_update_null);
                    pst_update_null.executeUpdate();

                }

            }

//******Calculate total amount (shift*rate) in each line in 'emp_atten_main' table
            PreparedStatement pst_cal = null;
            String sql_cal = "select * from emp_atten_main where Month='" + month + "' and Year='" + year + "' order by Line";
            pst_cal = con.prepareStatement(sql_cal);
            ResultSet rs_cal = pst_cal.executeQuery();
            while (rs_cal.next()) {

                jProgressBar1.setValue(20);
                lbl_atten.setText("Calculating Shifts...");
                Color cl = new Color(110, 110, 242);
                lbl_atten.setForeground(cl);

                String line = rs_cal.getString("Line");

                Double ds = Double.parseDouble(rs_cal.getString("DayShift"));
                Double ns = Double.parseDouble(rs_cal.getString("NightShift"));
                Double hds = Double.parseDouble(rs_cal.getString("HalfDayShift"));
                Double ots = Double.parseDouble(rs_cal.getString("OTShift"));
                Double oth = Double.parseDouble(rs_cal.getString("OTHours"));
                Double dns = Double.parseDouble(rs_cal.getString("DNShift"));
                Double dr = Double.parseDouble(rs_cal.getString("DayRate"));
                Double nr = Double.parseDouble(rs_cal.getString("NightRate"));
                Double othr = Double.parseDouble(rs_cal.getString("OTHRate"));
                Double dnr = Double.parseDouble(rs_cal.getString("DNRate"));

                Double amt = (ds * dr) + (ns * nr) + ((hds / 2) * dr) + (ots * dr) + (oth * othr) + (dns * dnr);
                String line_total = String.format("%.2f", amt);
                jProgressBar1.setValue(25);
                PreparedStatement pst_update = null;
                String sql_update = "update emp_atten_main set LineAmount='" + line_total + "' where Line='" + line + "'  ";
                pst_update = con.prepareStatement(sql_update);
                pst_update.executeUpdate();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        jProgressBar1.setValue(100);
        lbl_atten.setText("Attendance Processed Succesfully...!");
        Color cl = new Color(0, 153, 51);
        lbl_atten.setForeground(cl);
        btn_Process.setEnabled(true);

        //JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));
    }

    private void atten_summery_process() {

        long start = System.currentTimeMillis();

        try {
            jProgressBar1.setValue(0);
            Color cl = new Color(110, 110, 242);
            lbl_atten.setForeground(cl);

            Connection con = DbConnection.getconnection();

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

            PreparedStatement pst_update_null = null;
            String sql_update_null = "update emp_atten_main set DNShift='0' where DNShift=''  ";
            pst_update_null = con.prepareStatement(sql_update_null);
            pst_update_null.executeUpdate();

            PreparedStatement pst_update_null2 = null;
            String sql_update_null2 = "update emp_atten_main set OTHours='0' where OTHours=''  ";
            pst_update_null2 = con.prepareStatement(sql_update_null2);
            pst_update_null2.executeUpdate();

            lbl_atten.setText("Updating Attendance Status...");
            jProgressBar1.setValue(10);
            PreparedStatement pst_update_stat1 = null;
            String sql_update_stat1 = "update emp_atten_main set Status='processing' where Month='" + month + "' and Year='" + year + "'  ";
            pst_update_stat1 = con.prepareStatement(sql_update_stat1);
            pst_update_stat1.executeUpdate();
            jProgressBar1.setValue(20);

            PreparedStatement pst_del = null;
            String sql_del = "delete from emp_atten_summery where   Month='" + month + "' and Year='" + year + "'  ";
            pst_del = con.prepareStatement(sql_del);
            pst_del.executeUpdate();

            PreparedStatement pst = null;
            String sql = "select *,SUM(DayShift),SUM(DayTwoShift),SUM(NightShift),SUM(HalfDayShift/2),SUM(OTShift),SUM(OTHours),SUM(DNShift) from emp_atten_main where Month='" + month + "' and Year='" + year + "' and Status='processing' group by EPFno,Location,EffectiveRank";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            jProgressBar1.setValue(30);
            lbl_atten.setText("Collecting Shift Rates...");

            while (rs.next()) {
                jProgressBar1.setValue(35);
                String rank = rs.getString("EffectiveRank");
                String loc = rs.getString("Location");
                String EMPno = rs.getString("EPFno");

//                PreparedStatement pst1 = null;
//                String sql1 = "select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift),SUM(OTShift),SUM(OTHours),SUM(DNShift) from emp_atten_main where EPFno='" + EMPno + "' and EffectiveRank='" + rank + "' and Location='" + loc + "' and Month='" + month + "' and Year='" + year + "' ";
//                pst1 = con.prepareStatement(sql1);
//                ResultSet rs1 = pst1.executeQuery();
//                while (rs1.next()) {
                String loc_type = "";
                PreparedStatement pst1 = null;
                String sql1 = "select * from location_reg where LocCode='" + loc + "' ";
                pst1 = con.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    loc_type = rs1.getString("Tel3");
                }

                String ds = (rs.getString("SUM(DayShift)"));
                String ns = (rs.getString("SUM(NightShift)"));
                String hds = (rs.getString("SUM(HalfDayShift/2)"));
                String ots = (rs.getString("SUM(OTShift)"));
                String oth = (rs.getString("SUM(OTHours)"));
                String dns = (rs.getString("SUM(DNShift)"));
                String d2s = (rs.getString("SUM(DayTwoShift)"));

                jProgressBar1.setValue(40);

                //**** Get Shift Rates
                String day_rate = "0";
                String night_rate = "0";
                String dn_rate = "0";
                String ot_rate = "0";
                String day2_rate = "0";

                PreparedStatement pst_rates = null;
                String sql_rates = "select * from salary_rates where RankCode='" + rank + "' and LocCode='" + loc + "' ";
                pst_rates = con.prepareStatement(sql_rates);
                ResultSet rs_rates = pst_rates.executeQuery();
                while (rs_rates.next()) {

                    if (loc_type.equals("Type02") | loc_type.equals("Type03")) {
                        day_rate = rs_rates.getString("MinDayRate");
                        night_rate = rs_rates.getString("MinDayRate");
                    } else {
                        day_rate = rs_rates.getString("DayRate");
                        night_rate = rs_rates.getString("NightRate");
                        day2_rate = rs_rates.getString("DayRate_Two");
                    }

                    dn_rate = rs_rates.getString("DNRate");
                    ot_rate = rs_rates.getString("OTRate");
                }

                jProgressBar1.setValue(45);

//                if (day_rate == null | day_rate.equals("") | day_rate.isEmpty()) {
//                    day_rate = "0.00";
//                }
//                if (night_rate == null | night_rate.equals("") | night_rate.isEmpty()) {
//                    night_rate = "0.00";
//                }
//                if (dn_rate == null | dn_rate.equals("") | dn_rate.isEmpty()) {
//                    dn_rate = "0.00";
//                }
//                if (ot_rate == null | ot_rate.equals("") | ot_rate.isEmpty()) {
//                    ot_rate = "0.00";
//                }
                Double day = Double.parseDouble(ds);
                Double night = Double.parseDouble(ns);
                Double half = Double.parseDouble(hds);
                Double dn = Double.parseDouble(dns);
                Double ot_hours = Double.parseDouble(oth);
                Double day_two = Double.parseDouble(d2s);

                Double day_r = Double.parseDouble(day_rate);
                Double night_r = Double.parseDouble(night_rate);
                Double dn_r = Double.parseDouble(dn_rate);
                Double ot_hours_r = Double.parseDouble(ot_rate);
                Double day_2_r = Double.parseDouble(day2_rate);

                jProgressBar1.setValue(52);
                lbl_atten.setText("Calculating OT & Duty Amounts...");

                Double line_amount = (day * day_r) + (night * night_r) + (half * day_r) + (dn * dn_r) + (ot_hours * ot_hours_r) + (day_two * day_2_r);
                String LineAmount = String.format("%.2f", line_amount);
                String TotalOTAmount = String.format("%.2f", (ot_hours * ot_hours_r));
                jProgressBar1.setValue(58);

                jProgressBar1.setValue(75);
                lbl_atten.setText("Saving Summery Details");
                PreparedStatement pst_save = null;
                String sql_update = "insert into emp_atten_summery values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
                pst_save = con.prepareStatement(sql_update);
                jProgressBar1.setValue(80);
                pst_save.setString(1, EMPno);
                pst_save.setString(2, rank);
                pst_save.setString(3, loc);
                pst_save.setString(4, ds);
                pst_save.setString(5, ns);
                pst_save.setString(6, hds);
                pst_save.setString(7, dns);
                pst_save.setString(8, ots);
                pst_save.setString(9, oth);
                pst_save.setString(10, day_rate);
                pst_save.setString(11, night_rate);
                pst_save.setString(12, dn_rate);
                pst_save.setString(13, ot_rate);
                pst_save.setString(14, month);
                pst_save.setString(15, year);
                pst_save.setString(16, LineAmount);
                pst_save.setString(17, TotalOTAmount);
                pst_save.setString(18, d2s);
                pst_save.setString(19, day2_rate);

                jProgressBar1.setValue(85);

                pst_save.execute();

            }

            sunday_amounts();

            jProgressBar1.setValue(95);
            lbl_atten.setText("Completing...");

            PreparedStatement pst_update_stat = null;
            String sql_update_stat = "update emp_atten_main set Status='processed' where Month='" + month + "' and Year='" + year + "' and Status='processing'  ";
            pst_update_stat = con.prepareStatement(sql_update_stat);
            pst_update_stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        jProgressBar1.setValue(100);
        lbl_atten.setText("Attendance Processed Succesfully...!");
        Color cl = new Color(0, 153, 51);
        lbl_atten.setForeground(cl);
        btn_Process.setEnabled(true);

        long duration = System.currentTimeMillis() - start;

        DateFormat df = new SimpleDateFormat("HH'h' mm'm' ss's'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(df.format(new Date(duration)));
        lbl_runtime.setText("Total Time for the Last Process: " + df.format(new Date(duration)));
        //JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));

    }

    private void find_duplicated_data() {

        try {
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

            Connection con = DbConnection.getconnection();

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            //*** new alteration @ 8th november 2020
//            String sql_emp = "select * from emp_atten_main where Month='" + month + "' and Year='" + year + "' group by EPFno order by EPFno";
//            PreparedStatement pst_emp = con.prepareStatement(sql_emp);
//            ResultSet rs_emp = pst_emp.executeQuery();
//
//            while (rs_emp.next()) {
//                String emp = rs_emp.getString("EPFno");
//                System.out.println("emp " + emp);
            // String sql_duplicate = "select *,COUNT(*) from emp_atten_main where Month='" + month + "' and Year='" + year + "' group by EPFno,Date,DayShift,NightShift,HalfDayShift,OTShift,OTHours,DNShift order by EPFno";
            // String sql_duplicate = "SELECT *,SUM(DayShift)>1,SUM(NightShift)>1,SUM(HalfDayShift)>1,SUM(OTShift)>1,SUM(DNShift)>1,SUM(DayTwoShift)>1  FROM emp_atten_main WHERE  EPFno='" + emp + "' AND  MONTH='October' AND YEAR='2020' GROUP BY DATE";
            String sql_duplicate = "SELECT * ,COUNT(*),SUM(DayShift)>1,SUM(NightShift)>1,SUM(HalfDayShift)>1,SUM(OTShift)>1,SUM(DNShift)>1,SUM(DayTwoShift)>1  FROM emp_atten_main WHERE MONTH='" + month + "'  AND YEAR='" + year + "'  GROUP BY EPFno,DATE HAVING COUNT(*)> 1";

//                PreparedStatement pst = con.prepareStatement(" insert into `emp_attn_duplicated`(EPFno,Location,Date,Month,Year,SUMDay,SUMNight,SUMHalf,SUMOTShift,SUMDay2,SUMDN) SELECT *,COUNT(*),SUM(NightShift)>1,SUM(HalfDayShift)>1,SUM(OTShift)>1,SUM(DNShift)>1,SUM(DayTwoShift)>1  FROM emp_atten_main WHERE     MONTH='October' AND YEAR='2020'  GROUP BY EPFno,DATE HAVING COUNT(*)> 1");
//                pst.execute();
//
//                PreparedStatement pstdel = con.prepareStatement(" delete from  `emp_attn_duplicated` where SUMDay=0 or SUMNight=0  SUMHalf=0 or SUMOTShift=0 or SUMDay2=0 or SUMDN=0");
//               // pstdel.execute();
            PreparedStatement pst_duplicate = con.prepareStatement(sql_duplicate);
            ResultSet rs_duplicate = pst_duplicate.executeQuery();

            while (rs_duplicate.next()) {

                jProgressBar1.setValue(30);
                lbl_atten.setText("Searching for Duplicated Data...");
                Color cl = new Color(110, 110, 242);
                lbl_atten.setForeground(cl);

                int day = rs_duplicate.getInt("SUM(DayShift)>1");
                int night = rs_duplicate.getInt("SUM(NightShift)>1");
                int half = rs_duplicate.getInt("SUM(HalfDayShift)>1");
                int ot = rs_duplicate.getInt("SUM(OTShift)>1");

                System.out.println("day " + day);
                System.out.println("night " + night);
                System.out.println("half " + half);
                System.out.println("ot " + ot);

                if (day + night + half + ot >= 1) {

                    jProgressBar1.setValue(35);

                    String dup_epf = rs_duplicate.getString("EPFno");
                    String dup_date = rs_duplicate.getString("Date");

                    String sql_dup = "select * from emp_atten_main where Month='" + month + "' and Year='" + year + "' and EPFno='" + dup_epf + "' and Date='" + dup_date + "'";
                    PreparedStatement pst_dup = con.prepareStatement(sql_dup);
                    ResultSet rs_dup = pst_dup.executeQuery();
                    while (rs_dup.next()) {
                        Vector v = new Vector();
                        v.add(rs_dup.getString("EPFno"));
                        v.add("");
                        v.add(rs_dup.getString("Location"));
                        v.add("");
                        v.add(rs_dup.getString("Date"));
                        v.add(rs_dup.getString("DayShift"));
                        v.add(rs_dup.getString("NightShift"));
                        v.add(rs_dup.getString("HalfDayShift"));
                        v.add(rs_dup.getString("OTShift"));
                        v.add(rs_dup.getString("DNShift"));
                        v.add(rs_dup.getString("OTHours"));
                        v.add(rs_dup.getString("Line"));
                        dtm.addRow(v);

                    }

                }

            }

            //getting duplicated employees names & location names 
            for (int row = 0; row < jTable1.getRowCount(); row++) {

                jProgressBar1.setValue(40 + row);

                String emp = jTable1.getValueAt(row, 0).toString();
                String loc = jTable1.getValueAt(row, 2).toString();

                PreparedStatement pst_empname = con.prepareStatement("select * from employee_reg where EmployeeNo='" + emp + "'");
                ResultSet rs_empname = pst_empname.executeQuery();
                while (rs_empname.next()) {

                    dtm.setValueAt(rs_empname.getString("NameWithInitials"), row, 1);
                }

                PreparedStatement pst_loc = con.prepareStatement("select * from location_reg where LocCode='" + loc + "'");
                ResultSet rs_loc = pst_loc.executeQuery();
                while (rs_loc.next()) {

                    dtm.setValueAt(rs_loc.getString("LocName"), row, 3);
                }

            }

//            }
            //**
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void find_duplicated_data_Original() {

        try {
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

            Connection con = DbConnection.getconnection();

            String sql_duplicate = "select *,COUNT(*) from emp_atten_main where Month='" + month + "' and Year='" + year + "' group by EPFno,Date,DayShift,NightShift,HalfDayShift,OTShift,OTHours,DNShift order by EPFno";
            PreparedStatement pst_duplicate = con.prepareStatement(sql_duplicate);
            ResultSet rs_duplicate = pst_duplicate.executeQuery();

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs_duplicate.next()) {

                jProgressBar1.setValue(30);
                lbl_atten.setText("Searching for Duplicated Data...");
                Color cl = new Color(110, 110, 242);
                lbl_atten.setForeground(cl);

                if (rs_duplicate.getInt("COUNT(*)") >= 1) {

                    jProgressBar1.setValue(35);

                    String dup_epf = rs_duplicate.getString("EPFno");
                    String dup_date = rs_duplicate.getString("Date");

                    String sql_dup = "select * from emp_atten_main where Month='" + month + "' and Year='" + year + "' and EPFno='" + dup_epf + "' and Date='" + dup_date + "'";
                    PreparedStatement pst_dup = con.prepareStatement(sql_dup);
                    ResultSet rs_dup = pst_dup.executeQuery();
                    while (rs_dup.next()) {
                        Vector v = new Vector();
                        v.add(rs_dup.getString("EPFno"));
                        v.add("");
                        v.add(rs_dup.getString("Location"));
                        v.add("");
                        v.add(rs_dup.getString("Date"));
                        v.add(rs_dup.getString("DayShift"));
                        v.add(rs_dup.getString("NightShift"));
                        v.add(rs_dup.getString("HalfDayShift"));
                        v.add(rs_dup.getString("OTShift"));
                        v.add(rs_dup.getString("DNShift"));
                        v.add(rs_dup.getString("OTHours"));
                        v.add(rs_dup.getString("Line"));
                        dtm.addRow(v);

                    }

                }

            }

            //getting duplicated employees names & location names 
            for (int row = 0; row < jTable1.getRowCount(); row++) {

                jProgressBar1.setValue(40 + row);

                String emp = jTable1.getValueAt(row, 0).toString();
                String loc = jTable1.getValueAt(row, 2).toString();

                PreparedStatement pst_emp = con.prepareStatement("select * from employee_reg where EmployeeNo='" + emp + "'");
                ResultSet rs_emp = pst_emp.executeQuery();
                while (rs_emp.next()) {

                    dtm.setValueAt(rs_emp.getString("NameWithInitials"), row, 1);
                }

                PreparedStatement pst_loc = con.prepareStatement("select * from location_reg where LocCode='" + loc + "'");
                ResultSet rs_loc = pst_loc.executeQuery();
                while (rs_loc.next()) {

                    dtm.setValueAt(rs_loc.getString("LocName"), row, 3);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
