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

/**
 *
 * @author Sapu
 */
public class TEST extends javax.swing.JFrame {

    /**
     * Creates new form TEST
     */
    public TEST() {
        initComponents();
    }

    public int month = 0;
    public int year = 0;
    public String month_name = "";

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_month.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_monthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 110, -1));

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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jButton1.setText("3 to 10 Shifts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 100, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible
        month = cmb_month.getSelectedIndex();
    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

        year = Integer.parseInt(cmb_year.getSelectedItem().toString());

        //        int month = 0;
        //        month = cmb_month.getSelectedIndex();
        //        String year = cmb_year.getSelectedItem().toString();
        //
        //        if (month == 11) {
        //
        //            month = 0;
        //            //            Object m1 = cmb_month.getItemAt(month);
        //            //            System.out.println(m1);
        //            //            System.out.println("0" + (month + 1));
        //
        //            int x = Integer.parseInt(year);
        //            int nextYear = x + 1;
        //
        //            lbl_startDate.setText(nextYear + "-" + ("0" + (month + 1)) + "-" + "01");
        //            lbl_endDate.setText(nextYear + "-" + ("0" + (month + 1)) + "-" + "10");
        //
        //        } else {
        //            int next = month + 2;
        //            String nextM = Integer.toString(next);
        //            int len = nextM.length();
        //
        //            //            Object mm = cmb_month.getItemAt(month + 1);
        //            //            System.out.println(mm);
        //            if (len == 1) {
        //                //                System.out.println("0" + nextM);
        //
        //                lbl_startDate.setText(year + "-" + ("0" + (nextM)) + "-" + "01");
        //                lbl_endDate.setText(year + "-" + ("0" + (nextM)) + "-" + "10");
        //
        //            } else {
        //                //                System.out.println(nextM);
        //                lbl_startDate.setText(year + "-" + ((nextM)) + "-" + "01");
        //                lbl_endDate.setText(year + "-" + ((nextM)) + "-" + "10");
        //
        //            }
        //
        //        }
    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int Start_day = 25;
        int end_date = 30;

        String from_date = year + "-" + month + "-" + Start_day;
        System.out.println(from_date);

        String to_date = year + "-" + month + "-" + end_date;
        System.out.println(to_date);

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = null;
            ResultSet rs = null;

            String sql = "SELECT EPFno,SUM(DayShift+NightShift+(HalfDayShift/2)) ,DATE FROM `emp_atten_main` WHERE  DATE BETWEEN '" + from_date + "' AND '" + end_date + "' GROUP BY EPFno ";

            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
            
                Double shift = Double.parseDouble(rs.getString("SUM(DayShift+NightShift+(HalfDayShift/2))"));
                
                if(shift>3 && shift<10){
                
                    //get employees who worked 3-10 shifts between 25th to 30th.
                    
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
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TEST().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
