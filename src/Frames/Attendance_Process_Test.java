/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static com.mongodb.BasicDBObjectBuilder.start;
import static com.mongodb.BasicDBObjectBuilder.start;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
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
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sapu
 */
public class Attendance_Process_Test extends javax.swing.JFrame {

    /**
     * Creates new form Attendance_Process
     */
    public Attendance_Process_Test() {
        initComponents();

        lbl_atten.setVisible(false);

    }

    public void updateBoard() {
        long start = System.currentTimeMillis();

        String startd = lbl_startDate.getText();
        String end = lbl_endDate.getText();
        rate_change_shift_summery();

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = null;

            String sql = ("SELECT EPFno ,  SUM(NightShift+DayShift+(HalfDayShift/2)) FROM emp_atten_main WHERE Status='pending' AND  DATE BETWEEN '" + startd + "' AND '" + end + "'  GROUP BY EPFno");
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String epf = rs.getString("EPFno");
                String total = rs.getString("SUM(NightShift+DayShift+(HalfDayShift/2))");

                int y = Integer.parseInt(total);

//// ******************* update Status column as 'processed' in emp_atten_main **********************
                if (y < 8) {

                    // ******************* get epfno,name,total worked shifts of emp's who do not worked more than 8 shifts **********************             
                    String sql1 = ("select * from employee_reg where EPFno = '" + epf + "'");
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    ResultSet rs2 = pst1.executeQuery();

                    while (rs2.next()) {

                        String name = rs2.getString("NameWithInitials");

                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                        Vector v = new Vector();

                        v.add(epf);
                        v.add(name);
                        v.add(total);

                        dtm.addRow(v);

                    }

                }
            }

            String sql2 = ("SELECT *, count(EPFno) FROM emp_atten_main WHERE Status='pending' and Month = '" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "'  GROUP BY EPFno");
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {

                String EPFno = rs2.getString("EPFno");

                Statement st1 = DbConnection.getconnection().createStatement();
                st1.executeUpdate("update emp_atten_main  set Status = 'processed'  where EPFno = '" + EPFno + "' and Month ='" + cmb_month.getSelectedItem().toString() + "' and Year = '" + cmb_year.getSelectedItem().toString() + "' ");

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbl_endDate = new javax.swing.JLabel();
        cmb_year = new javax.swing.JComboBox();
        cmb_month = new javax.swing.JComboBox();
        lbl_no_of_emps = new javax.swing.JLabel();
        lbl_startDate = new javax.swing.JLabel();
        btn_Process = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_atten = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        jLabel1.setText("Attendance Process - Site Employees");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 610, 10));

        lbl_endDate.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        getContentPane().add(lbl_endDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 80, 20));

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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 110, -1));

        lbl_no_of_emps.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_no_of_emps.setForeground(new java.awt.Color(0, 0, 204));
        getContentPane().add(lbl_no_of_emps, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 80, 30));

        lbl_startDate.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        getContentPane().add(lbl_startDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 80, 20));

        btn_Process.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        btn_Process.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Workflow-48.png"))); // NOI18N
        btn_Process.setText("Process");
        btn_Process.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.white, java.awt.Color.darkGray, null));
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
        getContentPane().add(btn_Process, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 48, 170, 45));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EPF No", "Name", "Total Shifts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 590, 140));

        lbl_atten.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        lbl_atten.setForeground(new java.awt.Color(0, 153, 51));
        lbl_atten.setText("Attendance Processed Succesfully...!");
        getContentPane().add(lbl_atten, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 320, 40));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        jLabel5.setText("Effective Month / Year:-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("From :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, 40));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("To :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 30, 40));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 610, 10));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 10));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 10));

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("Employees with less than 08 Shifts");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jLabel8.setText("Total No. of Attendance Processed Employees in above Selected Month :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 30));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 610, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcessActionPerformed
        updateBoard();

////        
////        if (cmb_month.getSelectedItem().equals(null)) {
////
////        } else {
////            btn_Process.setText("Processing");
////
////        }
////        try {
////            Thread.sleep(15000);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        String start = lbl_startDate.getText();
//        String end = lbl_endDate.getText();
//        rate_change_shift_summery();
//
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("SELECT EPFno ,  SUM(NightShift+DayShift+(HalfDayShift/2)) FROM emp_atten_main WHERE Status='pending' AND  DATE BETWEEN '" + start + "' AND '" + end + "'  GROUP BY EPFno");
//
//            while (rs.next()) {
//
//                String epf = rs.getString("EPFno");
//                String total = rs.getString("SUM(NightShift+DayShift+(HalfDayShift/2))");
//
//                int y = Integer.parseInt(total);
//
////// ******************* update Status column as 'processed' in emp_atten_main **********************
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
//            lbl_atten.setForeground(Color.green);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        get_no_of_AttenProcessed_epms_();
//        btn_Process.setText("Processed");

    }//GEN-LAST:event_btn_ProcessActionPerformed

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
        int month = 0;
        month = cmb_month.getSelectedIndex();
        String year = cmb_year.getSelectedItem().toString();

        if (month == 11) {

            month = 0;
//            Object m1 = cmb_month.getItemAt(month);
//            System.out.println(m1);
//            System.out.println("0" + (month + 1));

            int x = Integer.parseInt(year);
            int nextYear = x + 1;

            lbl_startDate.setText(nextYear + "-" + ("0" + (month + 1)) + "-" + "01");
            lbl_endDate.setText(nextYear + "-" + ("0" + (month + 1)) + "-" + "10");

        } else {
            int next = month + 2;
            String nextM = Integer.toString(next);
            int len = nextM.length();

//            Object mm = cmb_month.getItemAt(month + 1);
//            System.out.println(mm);
            if (len == 1) {
//                System.out.println("0" + nextM);

                lbl_startDate.setText(year + "-" + ("0" + (nextM)) + "-" + "01");
                lbl_endDate.setText(year + "-" + ("0" + (nextM)) + "-" + "10");

            } else {
//                System.out.println(nextM);
                lbl_startDate.setText(year + "-" + ((nextM)) + "-" + "01");
                lbl_endDate.setText(year + "-" + ((nextM)) + "-" + "10");

            }

        }

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

//        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//        int nRow = dtm.getRowCount();
//
//        if (nRow == 0) {
//            this.dispose();
//        } else {
//
//            JOptionPane.showMessageDialog(rootPane, " Can't Leave while Table is already full... Please empty the Table to Exit from this Window. ");
//
//        }

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
            java.util.logging.Logger.getLogger(Attendance_Process_Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Attendance_Process_Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Attendance_Process_Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Attendance_Process_Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Attendance_Process_Test().setVisible(true);
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

            lbl_no_of_emps.setText(count);

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

            //Statement st = DbConnection.getconnection().createStatement();
            String sql = ("delete from emp_atten_rate_change_shift_summery where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' ");
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();

            String sql1 = ("select * from emp_atten_main where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' GROUP BY EPFno");
            pst = con.prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                epfno = rs.getString("EPFno");

                PreparedStatement pst1 = null;

                String sql2 = ("select * from emp_atten_main where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' GROUP BY Location");
                pst1 = con.prepareStatement(sql2);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    String LocName = rs1.getString("Location");

                    String sql3 = ("select *,SUM(DayShift+NightShift),SUM(HalfDayShift) from emp_atten_main  where EPFno ='" + epfno + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' and Location='" + LocName + "'  GROUP BY EffectiveRate ");
                    PreparedStatement pst2 = con.prepareStatement(sql3);
                    ResultSet rs2 = pst2.executeQuery();
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

                        System.out.println(epfno);
                        System.out.println(loc);
                        System.out.println(rate);
                        System.out.println(amount);
                        System.out.println(tot);

                        String sql4 = ("select *,COUNT(EPFno) from emp_atten_rate_change_shift_summery where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                        PreparedStatement pst3 = con.prepareStatement(sql4);
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {
                            System.out.println("st3");

                            int count = Integer.parseInt(rs3.getString("COUNT(EPFno)"));

                            PreparedStatement pst4 = null;
                            if (count == 0) {
                                //save

                                String sql5 = ("insert into emp_atten_rate_change_shift_summery values('" + epfno + "', '" + LocName + "' , '" + rate + "' , '" + amount + "', '" + tot + "','" + cmb_month.getSelectedItem().toString() + "','" + cmb_year.getSelectedItem().toString() + "' )");
                                pst4 = con.prepareStatement(sql5);
                                pst4.execute();
                                System.out.println("save");
                            } else {
//                         
                                String sql6 = ("insert into emp_atten_rate_change_shift_summery values('" + epfno + "', '" + LocName + "' , '" + rate + "' , '" + amount + "', '" + tot + "','" + cmb_month.getSelectedItem().toString() + "','" + cmb_year.getSelectedItem().toString() + "' )");
                                pst4 = con.prepareStatement(sql6);
                                pst4.execute();
                                System.out.println("save");
//                            }

                            }

                        }
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_atten;
    private javax.swing.JLabel lbl_endDate;
    private javax.swing.JLabel lbl_no_of_emps;
    private javax.swing.JLabel lbl_startDate;
    // End of variables declaration//GEN-END:variables

}
