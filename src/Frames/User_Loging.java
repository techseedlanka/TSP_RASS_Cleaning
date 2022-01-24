/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.MAIN.lbl_current_date;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Sapu
 */
public class User_Loging extends javax.swing.JFrame {

    /**
     * Creates new form User_Loging
     */
    String to_date = null;

    public User_Loging() {
        initComponents();

        lbl_warn.setVisible(false);
        //TitleBar();
        
        Date d = new Date();
        String date = d.toString();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        to_date = ("" + ft.format(d));

        seticon();

    }

    private void seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
  
        try {

            Date date_today = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
            if (date_today.after(date)) {
                jLabel9.setVisible(true);
            } else {
                jLabel9.setVisible(false);
            }
        } catch (Exception e) {
        }

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

//        this.setResizable(false);
//        ImageIcon img = new ImageIcon("src\\Images\\techseed.png");
//        this.setIconImage(img.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txt_UserName = new javax.swing.JTextField();
        lbl_warn = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        btn_log = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ll_frame = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/TechSeed_Logo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 170, 150));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 10, 140));

        txt_UserName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_UserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_UserNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_UserNameFocusLost(evt);
            }
        });
        txt_UserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_UserNameActionPerformed(evt);
            }
        });
        txt_UserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_UserNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_UserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 170, 30));

        lbl_warn.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        lbl_warn.setForeground(new java.awt.Color(255, 0, 51));
        lbl_warn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Id Not Verified-32.png"))); // NOI18N
        lbl_warn.setText("Incorrect User Name or Password");
        getContentPane().add(lbl_warn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 260, 40));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("User Name :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, 30));

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 170, 30));

        btn_log.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        btn_log.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Entering Heaven Alive.png"))); // NOI18N
        btn_log.setText("Log-in");
        btn_log.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        btn_log.setMinimumSize(new java.awt.Dimension(83, 37));
        btn_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logActionPerformed(evt);
            }
        });
        getContentPane().add(btn_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 120, 50));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit Sign.png"))); // NOI18N
        jButton2.setText("Log-out");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 120, 50));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 102));
        jLabel9.setText("License will expire on January 25th 2022");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 260, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Payroll Filled-50.png"))); // NOI18N
        jLabel1.setText("TechSeed Payroll System       V3.11");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 370, -1));
        getContentPane().add(ll_frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 350));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Password :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 70, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UserNameFocusLost

    }//GEN-LAST:event_txt_UserNameFocusLost

    private void txt_UserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UserNameKeyPressed


    }//GEN-LAST:event_txt_UserNameKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(rootPane, "<html><br>First line.<br>Second line."
                + "<ul>"
                + "<li>list 01</li>"
                + "<li>list 02</li>"
                + "</ul"
                + "</html>");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logActionPerformed

        Date date_today = null;
        Date date_final = null;
        try {
            date_today = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from test_print where col2='43eQ33FKGNnBkdNRvsz5hAuWNWPPEEB7ZwR36PMC'   ");
            while (rs.next()) {

                String strd = rs.getString(1);
                date_final = new SimpleDateFormat("yyyy-MM-dd").parse(strd);

            }

            if (date_today.after(date_final)) {
                jLabel9.setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "License Key Expired..! Please insert Valid License Key for this Year!");
            } else if (date_today.equals(date_final)) {
                jLabel9.setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "License Key Expired..! Please insert Valid License Key for this Year!");
            } else {
                jLabel9.setVisible(false);
                System.out.println("after");

                String userNameValue;
                String passwordValue;

//        JLabel jUserName = new JLabel("User Name");
//        JTextField userName = new JTextField();
//        JLabel jPassword = new JLabel("Password");
//        JTextField password = new JPasswordField();
//        Object[] ob = {jUserName, userName, jPassword, password};
//
//        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);
//
//        if (result == JOptionPane.OK_OPTION) {
                userNameValue = txt_UserName.getText();
                passwordValue = jPasswordField1.getText();

                Statement st2 = DbConnection.getconnection().createStatement();
                ResultSet rs2 = st2.executeQuery("select * from user where UserName = '" + userNameValue + "'");

                while (rs2.next()) {

                    String UN = rs2.getString("UserName");
                    String pass = rs2.getString("Password");
                    String cat = rs2.getString("UserCat");

                    if (UN.equals(userNameValue) & pass.equals(passwordValue)) {

//update query begins*************************************************************************************************
                        MAIN main = new MAIN();
                        main.setVisible(true);

                        MAIN.lbl_userCat.setText(cat);
                        MAIN.lbl_logged_user.setText(UN);

                        this.dispose();

//update query ends ****************************************************************************************************
                    } else {
                        lbl_warn.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }

//        }

    }//GEN-LAST:event_btn_logActionPerformed

    private void txt_UserNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UserNameFocusGained
        lbl_warn.setVisible(false);
    }//GEN-LAST:event_txt_UserNameFocusGained

    private void txt_UserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_UserNameActionPerformed
        jPasswordField1.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_UserNameActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        btn_log.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

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
            java.util.logging.Logger.getLogger(User_Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Loging.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Loging().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_log;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_warn;
    private javax.swing.JLabel ll_frame;
    private javax.swing.JTextField txt_UserName;
    // End of variables declaration//GEN-END:variables
}
