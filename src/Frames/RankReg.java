/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Sapu
 */
public class RankReg extends javax.swing.JFrame {

    /**
     * Creates new form RankReg
     */
    public RankReg() {
        initComponents();

        get_data_to_table();
        auto_completer();
        TitleBar();
    }

    private void clear() {
        txt_RankCode.setText("");
        txt_RankDes.setText("");
        txt_RankName.setText("");
    }

    private void tableclear() {

        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.setRowCount(0);
    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from rank ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_RankCode);

            while (rs.next()) {
                String code = rs.getString("RankCode");
                ta.addItem(code);
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        this.setResizable(false);
        ImageIcon img = new ImageIcon("src\\Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

    private void get_data_to_table() {

        try {

            Statement st = DbConnection.getconnection().createStatement();

            ResultSet rs = st.executeQuery("select * from rank");

            while (rs.next()) {

                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                Vector v = new Vector();

                v.add(rs.getString("RankCode"));
                v.add(rs.getString("RankName"));
                v.add(rs.getString("Category"));

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
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        txt_RankCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_RankName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_RankDes = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        btn_clear = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        cmb_ranCat = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Rank / Designation Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 540, 10));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit Sign.png"))); // NOI18N
        jButton1.setText("Log-out");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 100, 40));

        txt_RankCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_RankCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RankCodeFocusLost(evt);
            }
        });
        txt_RankCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RankCodeActionPerformed(evt);
            }
        });
        txt_RankCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RankCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_RankCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText(" Category :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 80, 20));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("*");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 20, 10));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("   Name :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 60, 20));

        txt_RankName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_RankName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RankNameFocusLost(evt);
            }
        });
        txt_RankName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RankNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_RankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 400, -1));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Display Rank :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        txt_RankDes.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_RankDes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_RankDesFocusLost(evt);
            }
        });
        txt_RankDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RankDesActionPerformed(evt);
            }
        });
        txt_RankDes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RankDesKeyPressed(evt);
            }
        });
        getContentPane().add(txt_RankDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 400, -1));

        jTable2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(20);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 340, 220));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 20, 10));

        btn_clear.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 110, 40));

        btn_save.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 110, 40));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 110, 40));

        btn_delete.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 110, 40));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 540, 10));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("     Code :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 70, 20));

        cmb_ranCat.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cmb_ranCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supervisor", "Janitor", "Admin Staff", "Director Staff" }));
        getContentPane().add(cmb_ranCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 170, 25));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, "<html><br>First line.<br>Second line."
                + "<ul>"
                + "<li>list 01</li>"
                + "<li>list 02</li>"
                + "</ul"
                + "</html>");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_RankCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RankCodeFocusLost
        //        String code = txt_LocCode.getText();
        //
        //        try {
        //            Statement st = DbConnection.getconnection().createStatement();
        //            ResultSet rs = st.executeQuery("select * from location_reg");
        //
        //            while (rs.next()) {
        //                String LocCode = rs.getString("LocCode");
        //
        //                if (code.equals(LocCode)) {
        //                    btn_LocSave.setEnabled(false);
        //                } else {
        //
        //                }
        //            }
        //
        //        } catch (Exception e) {
        //        }
        
        
    }//GEN-LAST:event_txt_RankCodeFocusLost

    private void txt_RankCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RankCodeKeyPressed
        //(evt.getKeyCode() == KeyEvent.VK_F1  && evt.isControlDown()

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String code = txt_RankCode.getText();

            try {
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from rank");

                while (rs.next()) {
                    String LocCode = rs.getString("RankCode");

                    if (code.equals(LocCode)) {

                    } else {

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_RankCodeKeyPressed

    private void txt_RankNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RankNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RankNameFocusLost

    private void txt_RankNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RankNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RankNameKeyPressed

    private void txt_RankDesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RankDesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RankDesFocusLost

    private void txt_RankDesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RankDesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RankDesKeyPressed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clear();
        tableclear();
        get_data_to_table();
        btn_save.setEnabled(true);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        String name = txt_RankName.getText();
        String code = txt_RankCode.getText();
        String des = txt_RankDes.getText();

        if (name.isEmpty() || code.isEmpty()) {
            Color color = new Color(255, 161, 161);
            if (name.isEmpty()) {
                txt_RankName.setBackground(color);
            }
            if (code.isEmpty()) {
                txt_RankCode.setBackground(color);
            }

        } else {
            try {
                Statement st = DbConnection.getconnection().createStatement();
                st.executeUpdate("insert into rank values('" + code + "','" + name + "','" + des + "','" + cmb_ranCat.getSelectedItem().toString() + "')");

                JOptionPane.showMessageDialog(rootPane, " Rank Successfully Saved...!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            clear();
            tableclear();
            get_data_to_table();

        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

        try {

            Statement st = DbConnection.getconnection().createStatement();
            st.executeUpdate("update rank set RankName='" + txt_RankName.getText() + "' ,RankDescription='" + txt_RankDes.getText() + "', Category='" + cmb_ranCat.getSelectedItem().toString() + "' where RankCode='" + txt_RankCode.getText() + "'");

            JOptionPane.showMessageDialog(rootPane, " Rank Detail Updated... ");

            clear();
            tableclear();
            get_data_to_table();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        try {

            if (txt_RankCode.getText().isEmpty()) {

            } else {

                int reply = JOptionPane.showConfirmDialog(rootPane, " Do you want to Delete this Rank? ", null, JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeUpdate("delete from rank  where RankCode='" + txt_RankCode.getText() + "'");

                    JOptionPane.showMessageDialog(rootPane, " Rank Detail Deleted... ");

                    clear();
                    tableclear();
                    get_data_to_table();

                } else {

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        try {

            int row = jTable2.getSelectedRow();
            String get_code = jTable2.getModel().getValueAt(row, 0).toString();

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from rank where RankCode = '" + get_code + "'");

            if (rs.next()) {

                txt_RankCode.setText(rs.getString("RankCode"));
                txt_RankName.setText(rs.getString("RankName"));
                txt_RankDes.setText(rs.getString("RankDescription"));
                cmb_ranCat.setSelectedItem(rs.getString("Category"));

            }

        } catch (Exception e) {
        }

        btn_save.setEnabled(false);
    }//GEN-LAST:event_jTable2MouseClicked

    private void txt_RankCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RankCodeActionPerformed
txt_RankName.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RankCodeActionPerformed

    private void txt_RankDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RankDesActionPerformed
txt_RankDes.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_RankDesActionPerformed

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
            java.util.logging.Logger.getLogger(RankReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RankReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RankReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RankReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RankReg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox cmb_ranCat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_RankCode;
    private javax.swing.JTextField txt_RankDes;
    private javax.swing.JTextField txt_RankName;
    // End of variables declaration//GEN-END:variables
}
