/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Uniform_Issue_DELETE extends javax.swing.JFrame {

    /**
     * Creates new form Shoe_Issue_DELETE
     */
    public Uniform_Issue_DELETE() {
        initComponents();
    }

    private void get_details() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (date_from.getDate() == null | date_to.getDate() == null) {

            JOptionPane.showMessageDialog(rootPane, " Please Select a Valid Date Range ");

        } else {

            String from = sdf.format(date_from.getDate());
            String to = sdf.format(date_to.getDate());

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery(" SELECT * FROM uniform_issue WHERE IssueOn BETWEEN '" + from + "' AND '" + to + "' and Status='on-going' ORDER BY EPFno");

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                while (rs.next()) {

                    Vector v = new Vector();

                    v.add(rs.getString(1));
                    v.add(rs.getString(10));
                    v.add(rs.getString(3));
                    v.add(rs.getString(4));
                    v.add(rs.getString(13));
                    v.add(rs.getString(5));
                    v.add(rs.getString(6));
                    v.add(rs.getString(7));
                    v.add(rs.getString(11));

                    dtm.addRow(v);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        date_from = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        date_to = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EPF No.", "Employee Name", "Uniform QTY", "Unit Cost", "Total", "Installments", "Rental", "Issued Date", "Note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(23);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(85);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(150);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 980, 370));
        getContentPane().add(date_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 150, 25));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel16.setText("  Issued Date     FROM :-");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 140, 20));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Uniform Issue - DELETE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 980, 10));
        getContentPane().add(date_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 140, 25));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("TO :-");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 40, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        jButton1.setText("DELETE Selected Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 300, -1));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 980, 10));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton2.setText("Clear All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 480, 200, -1));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 200, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int nrow = jTable1.getSelectedRowCount();

        int select_row = jTable1.getSelectedRow();

        if (nrow == 0) {

            JOptionPane.showMessageDialog(rootPane, "Zero(0) Rows Selected");

        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, " Do you want to Delete the selected Shoe Issues of this Employee?", "Advance Delete", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                String userNameValue;
                String passwordValue;

                JLabel jUserName = new JLabel("User Name");
                JTextField userName = new JTextField();
                JLabel jPassword = new JLabel("Password");
                JTextField password = new JPasswordField();
                Object[] ob = {jUserName, userName, jPassword, password};

                int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    userNameValue = userName.getText();
                    passwordValue = password.getText();

                    String uname = null;

                    try {
                        Statement st2 = DbConnection.getconnection().createStatement();
                        ResultSet rs2 = st2.executeQuery("select * from user where UserName = '" + userName.getText() + "'");

                        while (rs2.next()) {

                            String UN = rs2.getString("UserName");
                            String pass = rs2.getString("Password");
                            String cat = rs2.getString("UserCat");

                            if (UN.equals(userNameValue) & pass.equals(passwordValue)) {

                                if (cat.equals("admin") | cat.equals("system_admin")) {

//delete query begins*************************************************************************************************
                                    String EPFno = jTable1.getModel().getValueAt(select_row, 0).toString();
                                    String Installments = jTable1.getModel().getValueAt(select_row, 5).toString();
                                    String Rental = jTable1.getModel().getValueAt(select_row, 6).toString();
                                    String IssueDate = jTable1.getModel().getValueAt(select_row, 7).toString();

                                    String Ref = EPFno + "/" + IssueDate;

                                    Statement st = DbConnection.getconnection().createStatement();
                                    st.executeUpdate("delete from uniform_issue where EPFno = '" + EPFno + "' and IssueOn = '" + IssueDate + "' and Rental = '" + Rental + "' and Installments = '" + Installments + "' ");
                                    st.executeUpdate("delete from uniform_issue_settlement where EPFno = '" + EPFno + "' and Reference = '" + Ref + "' ");
                                    //  st.executeUpdate("delete from salary_advance_festival where EPFno = '" + txt_empid.getText() + "' and IssueDate = '" + Date + "' and Amount = '" + amount + "' and Note = '" + note + "' and Reference = '" + reference + "'");

                                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                                    dtm.removeRow(jTable1.getSelectedRow());

                                    JOptionPane.showMessageDialog(rootPane, " Issued Uniform Details were Deleted... ");
                                    //delete query ends ****************************************************************************************************
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation");
                                }

                            } else {
                                JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            } else {

            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        get_details(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Uniform_Issue_DELETE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Uniform_Issue_DELETE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Uniform_Issue_DELETE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Uniform_Issue_DELETE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Uniform_Issue_DELETE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date_from;
    private com.toedter.calendar.JDateChooser date_to;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
