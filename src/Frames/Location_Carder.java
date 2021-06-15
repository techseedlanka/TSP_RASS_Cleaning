/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Location_Carder extends javax.swing.JFrame {

    /**
     * Creates new form Location_Carder
     */
    public Location_Carder() {
        initComponents();
        auto_completer();
    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from location_reg order by LocName ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_LocName);

            while (rs.next()) {

                String code = rs.getString("LocCode");
                String Name = rs.getString("LocName");
                ta.addItem(code);
                ta.addItem(Name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    ArrayList<table_data> al = new ArrayList<table_data>();

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
        jLabel17 = new javax.swing.JLabel();
        txt_noOfGuards = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_LocName = new javax.swing.JTextField();
        cmb_CarderRank = new javax.swing.JComboBox();
        cmb_carderType = new javax.swing.JComboBox();
        txt_LocCarder_locCode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_LocSave = new javax.swing.JButton();
        btn_LocDelete = new javax.swing.JButton();
        btn_LocClear = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Location Carder");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 400, 10));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("Location :-");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        txt_noOfGuards.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_noOfGuards.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_noOfGuardsFocusLost(evt);
            }
        });
        txt_noOfGuards.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_noOfGuardsKeyPressed(evt);
            }
        });
        getContentPane().add(txt_noOfGuards, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 50, 30));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel18.setText("No. of Guards");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, 20));

        txt_LocName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_LocNameFocusLost(evt);
            }
        });
        txt_LocName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_LocName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 290, 25));

        cmb_CarderRank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(cmb_CarderRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 130, 30));

        cmb_carderType.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_carderType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "Night" }));
        getContentPane().add(cmb_carderType, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, 30));

        txt_LocCarder_locCode.setEditable(false);
        txt_LocCarder_locCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txt_LocCarder_locCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_LocCarder_locCodeFocusLost(evt);
            }
        });
        txt_LocCarder_locCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocCarder_locCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_LocCarder_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 40, 25));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel20.setText("Rank");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        jButton1.setToolTipText("Add to Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 70, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Carder Type", "Rank", "No. of Guards"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 390, 180));

        btn_LocSave.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_LocSave.setText("Save");
        btn_LocSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 110, 40));

        btn_LocDelete.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_LocDelete.setText("Delete");
        btn_LocDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 110, 40));

        btn_LocClear.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_LocClear.setText("Clear");
        btn_LocClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocClearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 100, 40));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 400, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_noOfGuardsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_noOfGuardsFocusLost
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
    }//GEN-LAST:event_txt_noOfGuardsFocusLost

    private void txt_noOfGuardsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noOfGuardsKeyPressed
        //(evt.getKeyCode() == KeyEvent.VK_F1  && evt.isControlDown()

    }//GEN-LAST:event_txt_noOfGuardsKeyPressed

    private void txt_LocNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocNameFocusLost

    private void txt_LocNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_LocName.getText().isEmpty()) {
            } else {
                get_data();

            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocNameKeyPressed

    private void txt_LocCarder_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocCarder_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocCarder_locCodeFocusLost

    private void txt_LocCarder_locCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocCarder_locCodeKeyPressed

    }//GEN-LAST:event_txt_LocCarder_locCodeKeyPressed

    private void btn_LocSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocSaveActionPerformed
        try {

            Statement st = DbConnection.getconnection().createStatement();
            for (table_data t : al) {
                st.executeUpdate("insert into location_carder values('" + txt_LocCarder_locCode.getText() + "','" + t.LocCarder_CarderType + "','" + t.LocCarder_Rank + "','" + t.LocCarder_NoOfGuards + "')");
            }
            JOptionPane.showMessageDialog(rootPane, " Location Carder Saved...! ");

            txt_noOfGuards.setText("");
            al.clear();
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            txt_LocCarder_locCode.setText("");
            txt_LocName.setText("");
            txt_noOfGuards.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_LocSaveActionPerformed

    private void btn_LocDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocDeleteActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        String carderType = jTable1.getModel().getValueAt(row, 0).toString();
        String rank = jTable1.getModel().getValueAt(row, 1).toString();
        String guards = jTable1.getModel().getValueAt(row, 2).toString();

        try {

            Statement st = DbConnection.getconnection().createStatement();
            st.executeUpdate("delete from location_carder where LocCode = '" + txt_LocCarder_locCode.getText() + "' and CarderType ='" + carderType + "' and Rank = '" + rank + "' and NoOfGuards='" + guards + "'");

            JOptionPane.showMessageDialog(rootPane, " Selected Data Deleted... ");

            dtm.removeRow(row);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btn_LocDeleteActionPerformed

    private void btn_LocClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocClearActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        txt_LocCarder_locCode.setText("");
        txt_LocName.setText("");
        txt_noOfGuards.setText("");

    }//GEN-LAST:event_btn_LocClearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (cmb_carderType.getSelectedItem().toString().isEmpty() | txt_noOfGuards.getText().isEmpty() | cmb_CarderRank.getSelectedItem().toString().isEmpty()) {

        } else {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            table_data t = new table_data();

            t.LocCarder_CarderType = cmb_carderType.getSelectedItem().toString();
            t.LocCarder_NoOfGuards = txt_noOfGuards.getText();
            t.LocCarder_Rank = cmb_CarderRank.getSelectedItem().toString();

            al.add(t);

            Vector v = new Vector();

            v.add(cmb_carderType.getSelectedItem().toString());

            v.add(cmb_CarderRank.getSelectedItem().toString());
            v.add(txt_noOfGuards.getText());

            dtm.addRow(v);

            jTable1.setRowHeight(0, 20);
            jTable1.setRowHeight(1, 20);
            jTable1.setRowHeight(2, 20);
            jTable1.setRowHeight(3, 20);
            jTable1.setRowHeight(4, 20);
            jTable1.setRowHeight(5, 20);
            jTable1.setRowHeight(6, 20);
            jTable1.setRowHeight(7, 20);
            jTable1.setRowHeight(8, 20);
            jTable1.setRowHeight(9, 20);
            jTable1.setRowHeight(10, 20);

            txt_noOfGuards.setText("");

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
            java.util.logging.Logger.getLogger(Location_Carder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Location_Carder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Location_Carder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Location_Carder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Location_Carder().setVisible(true);
            }
        });
    }

    private void get_data() {

        String name = txt_LocName.getText();
        String code = null;
        try {

            cmb_CarderRank.removeAllItems();
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs2 = st.executeQuery("select * from location_reg where LocName = '" + name + "' or LocCode='" + name + "'");
            while (rs2.next()) {

                code = rs2.getString("LocCode");
                txt_LocCarder_locCode.setText(code);
                txt_LocName.setText(rs2.getString("LocName"));

            }

            ResultSet rs = st.executeQuery("select * from salary_rates where LocCode = '" + code + "'");
            while (rs.next()) {

                String rank = rs.getString("RankCode");

                cmb_CarderRank.addItem(rank);
            }

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            Statement st1 = DbConnection.getconnection().createStatement();
            ResultSet rs1 = st1.executeQuery("select * from location_carder where LocCode = '" + code + "'");

            while (rs1.next()) {

                Vector v = new Vector();
                v.add(rs1.getString("CarderType"));
                v.add(rs1.getString("Rank"));
                v.add(rs1.getString("NoOfGuards"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        jTable1.setRowHeight(0, 20);
        jTable1.setRowHeight(1, 20);
        jTable1.setRowHeight(2, 20);
        jTable1.setRowHeight(3, 20);
        jTable1.setRowHeight(4, 20);
        jTable1.setRowHeight(5, 20);
        jTable1.setRowHeight(6, 20);
        jTable1.setRowHeight(7, 20);
        jTable1.setRowHeight(8, 20);
        jTable1.setRowHeight(9, 20);
        jTable1.setRowHeight(10, 20);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LocClear;
    private javax.swing.JButton btn_LocDelete;
    public javax.swing.JButton btn_LocSave;
    private javax.swing.JComboBox cmb_CarderRank;
    private javax.swing.JComboBox cmb_carderType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField txt_LocCarder_locCode;
    private javax.swing.JTextField txt_LocName;
    private javax.swing.JTextField txt_noOfGuards;
    // End of variables declaration//GEN-END:variables
}
