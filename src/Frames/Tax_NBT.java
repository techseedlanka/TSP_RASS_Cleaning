/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Sapu
 */
public class Tax_NBT extends javax.swing.JFrame {

    /**
     * Creates new form Tax_NBT
     */
    public Tax_NBT() {
        initComponents();
        get_amounts();
    }
    
    private void get_amounts() {
        
        try {
            
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
            
            con = DbConnection.getconnection();
            String sql = "select * from tax_and_nbt";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                lbl_nbt.setText(rs.getString("NBT") + " %");
                lbl_tax.setText(rs.getString("Tax") + " %");
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txt_nbt = new javax.swing.JTextField();
        lbl_nbt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_tax = new javax.swing.JTextField();
        lbl_tax = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        btn_save = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Tax & N.B.T ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 290, 10));

        txt_nbt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_nbt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 100, 25));

        lbl_nbt.setBackground(new java.awt.Color(255, 204, 204));
        lbl_nbt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_nbt.setOpaque(true);
        getContentPane().add(lbl_nbt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 50, 25));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel3.setText("Tax Rate :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel4.setText("N.B.T Rate :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 120, 80, 20));

        txt_tax.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 100, 25));

        lbl_tax.setBackground(new java.awt.Color(255, 204, 204));
        lbl_tax.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_tax.setOpaque(true);
        getContentPane().add(lbl_tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 50, 25));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 290, 10));

        btn_save.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 120, 40));

        btn_clear.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 120, 40));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 290, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        
        try {
            
            Connection con;
            PreparedStatement pst;
            
            con = DbConnection.getconnection();
            String update = "delete from tax_and_nbt";
            
            pst = con.prepareStatement(update);
            pst.execute();
            
            String sql = "insert into tax_and_nbt values('" + txt_tax.getText() + "','" + txt_nbt.getText() + "')";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(rootPane, "Tax & NBT Values are Updated...!");
            
            txt_nbt.setText("");
            txt_tax.setText("");
            
            get_amounts();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed

    }//GEN-LAST:event_btn_clearActionPerformed

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
            java.util.logging.Logger.getLogger(Tax_NBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tax_NBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tax_NBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tax_NBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tax_NBT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbl_nbt;
    private javax.swing.JLabel lbl_tax;
    private javax.swing.JTextField txt_nbt;
    private javax.swing.JTextField txt_tax;
    // End of variables declaration//GEN-END:variables
}
