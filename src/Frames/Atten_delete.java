/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class Atten_delete extends javax.swing.JFrame {

    /**
     * Creates new form Atten_delete
     */
    private ImageIcon format = null;

    public Atten_delete() {
        initComponents();
        auto_completer();
        TitleBar();

    }
    
     private void TitleBar() {
        this.setTitle("Attendance Delete");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);

            while (rs.next()) {
                String code = rs.getString("EmployeeNo");

                String NameWithInitials = rs.getString("NameWithInitials");

                ta.addItem(code);
                ta.addItem(NameWithInitials);

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txt_search = new javax.swing.JTextField();
        txt_empid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        lbl_rankCat = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txt_reason = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        cmb_Del_type = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Employee Attendance Delete ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 340, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 410, 10));

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 320, 25));

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 70, 21));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Employee :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 50, 21));

        lbl_rankCat.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        getContentPane().add(lbl_rankCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 110, 20));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setText("View Photo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 100, -1));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 410, 10));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Rank :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, 20));

        txt_reason.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_reason, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 320, 25));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Reason :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 20));

        btn_delete.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 170, 40));

        btn_clear.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 120, 40));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 410, 10));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_month.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 204)));
        cmb_month.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_monthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 120, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021", "2022" }));
        cmb_year.setSelectedItem("2017");
        cmb_year.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 204)));
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel34.setText(" Month  :-");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, 20));

        cmb_Del_type.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_Del_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Attendance Delete", "Earnings Delete", "Deductions Delete" }));
        cmb_Del_type.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 0, 0)));
        cmb_Del_type.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_Del_typePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_Del_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("EMP No.  :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained

    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg");
                while (rs.next()) {
                    String code = rs.getString("EmployeeNo");
                    //String nic = rs.getString("NIC");
                    String name = rs.getString("NameWithInitials");

                    String rank = rs.getString("Designation");
                    //String rankCat = rs.getString("RankCategory");

                    byte[] imagedata = rs.getBytes("EMPImage");

                    if (txt_search.getText().equals(code) || txt_search.getText().equals(name)) {

                        if (imagedata == null) {

                            format = new ImageIcon("imagedata");

                        } else {

                            format = new ImageIcon(imagedata);

                        }

                        txt_empid.setText(code);
                        txt_search.setText(name);

                        txt_rank.setText(rank);
                        //lbl_rankCat.setText(rankCat);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(format);
        pt.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

        if (txt_reason.getText().isEmpty() | txt_empid.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, " EMP No & Reason for Deletion cannot be Empty ");

        } else {
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

                            if (cat.equals("admin") | cat.equals("manager")) {

//delete query begins*************************************************************************************************
                                Connection con = DbConnection.getconnection();

                                if (cmb_Del_type.getSelectedIndex() == 0) {//attn delete

                                    PreparedStatement pst = con.prepareStatement("delete from emp_atten_main where EMPno='" + txt_empid.getText() + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                                    pst.executeUpdate();
                                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s All Attendance Details were Deleted!");
                                    clear();

                                } else if (cmb_Del_type.getSelectedIndex() == 1) {//earnings delete

                                    PreparedStatement pst = con.prepareStatement("delete from salary_manual_earnings where EMPno='" + txt_empid.getText() + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                                    pst.executeUpdate();
                                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s All Earnings & Allowances  were Deleted!");
                                    clear();

                                } else {//deductions delete

                                    PreparedStatement pst = con.prepareStatement("delete from salary_manual_deductions where EPFno='" + txt_empid.getText() + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                                    pst.executeUpdate();
                                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s All Deductions were Deleted!");
                                    clear();

                                }

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

        }

    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void cmb_Del_typePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_Del_typePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_Del_typePopupMenuWillBecomeInvisible

    private void clear() {

        txt_empid.setText("");

        txt_reason.setText("");
        txt_search.setText("");
        txt_rank.setText("");
        lbl_rankCat.setText("");

        format = null;

    }

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
            java.util.logging.Logger.getLogger(Atten_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Atten_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Atten_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Atten_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Atten_delete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JComboBox cmb_Del_type;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lbl_rankCat;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_reason;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
