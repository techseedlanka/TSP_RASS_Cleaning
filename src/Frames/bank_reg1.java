/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SAPU
 */
public class bank_reg1 extends javax.swing.JFrame {

    /**
     * Creates new form bank_reg
     */
    public bank_reg1() {
        initComponents();

        TitleBar();
        get_bank_name();
        get_all_banks();

    }

    static String def_code;
    static int isDefault;

    private void clear() {
        txt_bankName.setText("");
        txt_bankCode.setText("");
        txt_branchCode.setText("");
        txt_branchName.setText("");

    }

    private void TitleBar() {

        this.setResizable(false);
        ImageIcon img = new ImageIcon("src\\Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

    private void get_bank_name() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from bank_main group by BankName  ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_bankName);
            // TextAutoCompleter a = new TextAutoCompleter(txt_branchName);

            while (rs.next()) {

                String name = rs.getString("BankName");
                ta.addItem(name);

                //  a.addItem(rs.getString("BranchName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get_all_banks() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from bank_main  order by BankName Asc ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                dtm.addRow(v);

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
        jLabel7 = new javax.swing.JLabel();
        txt_bankName = new javax.swing.JTextField();
        txt_bankCode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_branchName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_branchCode = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Bank & Branch Details");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1, 170, 40));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 550, 10));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText(" Bank Name :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        txt_bankName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_bankName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_bankNameFocusGained(evt);
            }
        });
        txt_bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bankNameActionPerformed(evt);
            }
        });
        txt_bankName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_bankNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 270, 25));

        txt_bankCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_bankCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 60, 25));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText(" Bank Code :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, 20));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bank Code", "Bank Name", "Branch Code", "Branch Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(23);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(350);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 530, 330));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Branch Name :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 20));

        txt_branchName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_branchName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_branchNameFocusGained(evt);
            }
        });
        txt_branchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_branchNameActionPerformed(evt);
            }
        });
        txt_branchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_branchNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_branchName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 270, 25));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Branch Code :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, 20));

        txt_branchCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_branchCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 60, 25));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 130, 40));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 140, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bankNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bankNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameFocusGained

    private void txt_bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameActionPerformed

    private void txt_bankNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bankNameKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (txt_bankName.getText().isEmpty()) {

            } else {
                try {

                    Statement st = DbConnection.getconnection().createStatement();
                    ResultSet rs = st.executeQuery("select * from bank_main where BankName='" + txt_bankName.getText() + "'  ");
                    while (rs.next()) {

                        String code = rs.getString("BankCode");

                        txt_bankCode.setText(code);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                txt_branchName.grabFocus();
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameKeyPressed

    private void txt_branchNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_branchNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_branchNameFocusGained

    private void txt_branchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_branchNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_branchNameActionPerformed

    private void txt_branchNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_branchNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_branchNameKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (txt_bankCode.getText().isEmpty() | txt_branchCode.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Every text field should be filled");
        } else {

            try {

                java.sql.Connection con = DbConnection.getconnection();
                PreparedStatement pst = null;

                String sql = "insert into bank_main (BankCode,BankName,BranchCode,BranchName) values (?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, txt_bankCode.getText());
                pst.setString(2, txt_bankName.getText());
                pst.setString(3, txt_branchCode.getText());
                pst.setString(4, txt_branchName.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(rootPane, "Bank Details Saved!");
                txt_bankCode.setText("");
                txt_bankName.setText("");
                txt_branchCode.setText("");
                txt_branchName.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        get_all_banks();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(bank_reg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bank_reg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bank_reg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bank_reg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bank_reg1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_bankCode;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_branchCode;
    private javax.swing.JTextField txt_branchName;
    // End of variables declaration//GEN-END:variables
}
