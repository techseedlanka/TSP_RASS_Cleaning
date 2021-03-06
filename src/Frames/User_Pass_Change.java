/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Sapu
 */
public class User_Pass_Change extends javax.swing.JFrame {

    /**
     * Creates new form User_Pass_Change
     */
    public User_Pass_Change() {
        initComponents();
        get_user();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void get_user() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from user where UserName='" + MAIN.lbl_logged_user.getText() + "'");
            while (rs.next()) {

                txt_Pass.setText(rs.getString("Password"));
                txt_ConfirmPass.setText(rs.getString("Password"));
                txt_name.setText(rs.getString("UserFullName"));

                String userCat = rs.getString("UserCat");
                txt_get_user_cat.setText(userCat);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txt_userID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Pass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txt_ConfirmPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_get_user_cat = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btn_update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel1.setText("User Password Change");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 430, 10));

        txt_userID.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        txt_userID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userIDActionPerformed(evt);
            }
        });
        txt_userID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_userIDKeyPressed(evt);
            }
        });
        getContentPane().add(txt_userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 140, 25));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel2.setText("User ID :-");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 20));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Password :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 85, -1, -1));

        txt_Pass.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        getContentPane().add(txt_Pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 85, 140, 23));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("Confirm Password :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, -1));

        txt_ConfirmPass.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        getContentPane().add(txt_ConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 115, 140, 23));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("User's Name :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 152, -1, 20));

        txt_name.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 260, 23));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel6.setText("User Type  :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 90, 20));

        txt_get_user_cat.setEditable(false);
        txt_get_user_cat.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        getContentPane().add(txt_get_user_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 260, 23));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 430, 10));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 310, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_userIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userIDActionPerformed

    private void txt_userIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_userIDKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from user where UserName='" + txt_userID.getText() + "'");
                while (rs.next()) {

                    txt_Pass.setText(rs.getString("Password"));
                    txt_ConfirmPass.setText(rs.getString("Password"));
                    txt_name.setText(rs.getString("UserFullName"));

                    String userCat = rs.getString("UserCat");
                    txt_get_user_cat.setText(userCat);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_userIDKeyPressed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

        // String uset_cat = MAIN.lbl_userCat.getText();
        //        if(){}
        if (!txt_Pass.getText().equals(txt_ConfirmPass.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Password dosen't match. Please check!");
        } else if (txt_Pass.getText().isEmpty() | txt_ConfirmPass.getText().isEmpty() | txt_name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, " UserID , Password & User's Name can't be empty...!");
        } else {

            try {

                Statement st = DbConnection.getconnection().createStatement();
                st.executeUpdate("update user set Password='" + txt_ConfirmPass.getText() + "',UserFullName='" + txt_name.getText() + "' where UserName='" + txt_userID.getText() + "'");

                JOptionPane.showMessageDialog(rootPane, "Sucessfuly Updated...!");

                txt_ConfirmPass.setText("");
                txt_Pass.setText("");
                txt_get_user_cat.setText("");
                txt_name.setText("");
                txt_userID.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

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
            java.util.logging.Logger.getLogger(User_Pass_Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Pass_Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Pass_Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Pass_Change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Pass_Change().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txt_ConfirmPass;
    private javax.swing.JPasswordField txt_Pass;
    private javax.swing.JTextField txt_get_user_cat;
    private javax.swing.JTextField txt_name;
    public static javax.swing.JTextField txt_userID;
    // End of variables declaration//GEN-END:variables
}
