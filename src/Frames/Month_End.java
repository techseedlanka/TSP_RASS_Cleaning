/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Sapu
 */
public class Month_End extends javax.swing.JFrame {

    /**
     * Creates new form Month_End
     */
    public Month_End() {
        initComponents();
        lbl_atten.setVisible(false);
        TitleBar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     *
     */
    private void TitleBar() {

        this.setResizable(false);
        ImageIcon img = new ImageIcon("src\\Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

    private void Loan_One() {

        try {

            Connection conn = DbConnection.getconnection();
            PreparedStatement pst;
            PreparedStatement pst2;
            PreparedStatement pst3;

            ResultSet rst;
            ResultSet rst2;

            String sql = "select * from distress_loan where Status='on-going'";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            while (rst.next()) {

                String loan_ref = rst.getString(1);
                String loan_amount = rst.getString(3);
                String loan_installments = rst.getString(4);

                String sql2 = "select *,SUM(ReceivedAmount) from distress_loan_settelment where LoanReference='" + loan_ref + "'";
                pst2 = conn.prepareStatement(sql2);
                rst2 = pst2.executeQuery();

                while (rst2.next()) {

                    // Double recevied_amt = Double.parseDouble(rst2.getString("SUM(ReceivedAmount)"));
                    String recevied_amt = rst2.getString("SUM(ReceivedAmount)");
                    System.out.println(loan_ref);
                    System.out.println(recevied_amt);

                    Double loan_amt = 0.00;
                    Double recv_amt = 0.00;
                    if (recevied_amt == null) {
                        recv_amt = 0.00;
                    } else {
                        recv_amt = Double.parseDouble(recevied_amt);
                    }
                    loan_amt = Double.parseDouble(loan_amount);

                    if (loan_amt <= recv_amt) {

                        String sql1 = " update distress_loan set Status='Settled' where LoanReference='" + loan_ref + "' ";
                        pst3 = conn.prepareStatement(sql1);
                        pst3.execute();

                    } else {

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    private void Loan_Two() {

        try {

            Connection conn = DbConnection.getconnection();
            PreparedStatement pst;
            PreparedStatement pst2;
            PreparedStatement pst3;

            ResultSet rst;
            ResultSet rst2;

            String sql = "select * from distress_loan_02 where Status='on-going'";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            while (rst.next()) {

                String loan_ref = rst.getString(1);
                String loan_amount = rst.getString(3);
                String loan_installments = rst.getString(4);

                String sql2 = "select *,SUM(ReceivedAmount) from distress_loan_settelment_02 where LoanReference='" + loan_ref + "'";
                pst2 = conn.prepareStatement(sql2);
                rst2 = pst2.executeQuery();

                while (rst2.next()) {

                    // Double recevied_amt = Double.parseDouble(rst2.getString("SUM(ReceivedAmount)"));
                    String recevied_amt = rst2.getString("SUM(ReceivedAmount)");
                    System.out.println(loan_ref);
                    System.out.println(recevied_amt);

                    Double loan_amt = 0.00;
                    Double recv_amt = 0.00;
                    if (recevied_amt == null) {
                        recv_amt = 0.00;
                    } else {
                        recv_amt = Double.parseDouble(recevied_amt);
                    }
                    loan_amt = Double.parseDouble(loan_amount);

                    if (loan_amt == recv_amt) {

                        String sql1 = " update distress_loan_02 set Status='Settled' where LoanReference='" + loan_ref + "' ";
                        pst3 = conn.prepareStatement(sql1);
                        pst3.execute();

                    } else {

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    private void Shoe_Issue() {

        try {

            Connection conn = DbConnection.getconnection();
            PreparedStatement pst;
            PreparedStatement pst2;
            PreparedStatement pst3;

            ResultSet rst;
            ResultSet rst2;

            String sql = "select * from shoes_issue where Status='on-going'";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            while (rst.next()) {

                String loan_ref = rst.getString("Ref");
                String loan_amount = rst.getString(3);
                String loan_installments = rst.getString(4);

                if (loan_ref == null) {
                } else {

                    String sql2 = "select *,SUM(ReceivedAmount) from shoe_issue_settlement where  Reference='" + loan_ref + "'";
                    pst2 = conn.prepareStatement(sql2);
                    rst2 = pst2.executeQuery();

                    while (rst2.next()) {

                        // Double recevied_amt = Double.parseDouble(rst2.getString("SUM(ReceivedAmount)"));
                        String recevied_amt = rst2.getString("SUM(ReceivedAmount)");
                        System.out.println(loan_ref);
                        System.out.println(recevied_amt);

                        Double loan_amt = 0.00;
                        Double recv_amt = 0.00;
                        if (recevied_amt == null) {
                            recv_amt = 0.00;
                        } else {
                            recv_amt = Double.parseDouble(recevied_amt);
                        }
                        loan_amt = Double.parseDouble(loan_amount);

                        if (loan_amt <= recv_amt) {

                            String sql1 = " update shoes_issue set Status='Settled' where Ref='" + loan_ref + "' ";
                            pst3 = conn.prepareStatement(sql1);
                            pst3.execute();

                        } else {

                        }

                    }
                }

            }

            rst.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    private void Uniform_Issue() {

        try {

            Connection conn = DbConnection.getconnection();
            PreparedStatement pst;
            PreparedStatement pst2;
            PreparedStatement pst3;

            ResultSet rst;
            ResultSet rst2;

            String sql = "select * from uniform_issue where Status='on-going'";

            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();

            while (rst.next()) {

                String loan_ref = rst.getString("Ref");
                String loan_amount = rst.getString(3);
                String loan_installments = rst.getString(4);

                if (loan_ref == null) {
                } else {

                    String sql2 = "select *,SUM(ReceivedAmount) from uniform_issue_settlement where  Reference='" + loan_ref + "'";
                    pst2 = conn.prepareStatement(sql2);
                    rst2 = pst2.executeQuery();

                    while (rst2.next()) {

                        // Double recevied_amt = Double.parseDouble(rst2.getString("SUM(ReceivedAmount)"));
                        String recevied_amt = rst2.getString("SUM(ReceivedAmount)");
                        System.out.println(loan_ref);
                        System.out.println(recevied_amt);

                        Double loan_amt = 0.00;
                        Double recv_amt = 0.00;
                        if (recevied_amt == null) {
                            recv_amt = 0.00;
                        } else {
                            recv_amt = Double.parseDouble(recevied_amt);
                        }
                        loan_amt = Double.parseDouble(loan_amount);

                        if (loan_amt <= recv_amt) {

                            String sql1 = " update uniform_issue set Status='Settled' where Ref='" + loan_ref + "' ";
                            pst3 = conn.prepareStatement(sql1);
                            pst3.execute();

                        } else {

                        }

                    }
                }

            }

            rst.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);

        }

    }

    private void Save_Month_End() {

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst;
            Date d = new Date();
            String date = d.toString();

            String sql = "insert into month_end values ('" + date + "','" + cmb_month.getSelectedItem().toString() + "','" + cmb_year.getSelectedItem().toString() + "')";
            pst = con.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(rootPane, " Month End Processed Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error Occurd during Process of Month End");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel33 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        lbl_atten = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Month End");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel34.setText("Month / Year  :-");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 110, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021" }));
        cmb_year.setSelectedIndex(2);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 130, 25));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText("Processing Date  :-");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Workflow-48.png"))); // NOI18N
        jButton1.setText("Month End Process");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 320, 50));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 330, 10));

        lbl_atten.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        lbl_atten.setForeground(new java.awt.Color(0, 153, 51));
        lbl_atten.setText("Month End  Processed  Succesfully...!");
        lbl_atten.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Status:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 12))); // NOI18N
        getContentPane().add(lbl_atten, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 320, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
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

        if (jDateChooser1.getDate() == null) {

        } else {
            Shoe_Issue();
            Loan_One();
            Loan_Two();
            Uniform_Issue();
            Save_Month_End();
            lbl_atten.setText(" Month End Procssed...!");
            Color cl = new Color(0, 153, 51);
            lbl_atten.setForeground(cl);
            lbl_atten.setVisible(true);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        lbl_atten.setText("  Processing.. Please Wait..");
        lbl_atten.setForeground(Color.red);
        lbl_atten.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MousePressed

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
            java.util.logging.Logger.getLogger(Month_End.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Month_End.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Month_End.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Month_End.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Month_End().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lbl_atten;
    // End of variables declaration//GEN-END:variables
}
