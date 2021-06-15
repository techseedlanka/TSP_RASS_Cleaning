/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Deductions extends javax.swing.JFrame {

    /**
     * Creates new form Allowances
     */
    public Deductions() {
        initComponents();

        get_data_to_table();
        TitleBar();
        auto_completer();
        table_properties();

        table_Deduct.getTableHeader().setDefaultRenderer(new Allowances.HeaderColor());
    }

    private void table_properties() {

        table_Deduct.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        table_Deduct.getColumnModel().getColumn(0).setPreferredWidth(1);
        table_Deduct.getColumnModel().getColumn(1).setPreferredWidth(100);
        table_Deduct.getColumnModel().getColumn(2).setPreferredWidth(50);
        table_Deduct.getColumnModel().getColumn(3).setPreferredWidth(200);
        table_Deduct.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from deductions_reg ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_DeductCode);

            while (rs.next()) {
                String code = rs.getString("code");
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
                this.setTitle("TechSeed Payroll"+"    -    "+ComName );

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

            ResultSet rs = st.executeQuery("select * from deductions_reg");

            while (rs.next()) {

                DefaultTableModel dtm = (DefaultTableModel) table_Deduct.getModel();
                Vector v = new Vector();

                v.add(rs.getString("code"));
                v.add(rs.getString("name"));
                v.add(rs.getString("amount"));
                v.add(rs.getString("description"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clear() {

        txt_DeductAmount.setText("");
        txt_DeductDescription.setText("");
        txt_DeductName.setText("");
        txt_DeductCode.setText("");

    }

    private void tableclear() {

        DefaultTableModel dtm = (DefaultTableModel) table_Deduct.getModel();
        dtm.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_refresh = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_DeductAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_DeductCode = new javax.swing.JTextField();
        txt_DeductName = new javax.swing.JTextField();
        txt_DeductDescription = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Deduct = new javax.swing.JTable();
        btn_save = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_refresh.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        getContentPane().add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, -1, 40));

        btn_delete.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 110, 40));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 40));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Deductions Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 730, 10));

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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 100, 40));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Amount :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, 20));

        txt_DeductAmount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_DeductAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 110, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("Deduction Code :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Deduction Name :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Description :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        txt_DeductCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_DeductCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, -1));

        txt_DeductName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_DeductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 440, -1));

        txt_DeductDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_DeductDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 440, -1));

        table_Deduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Amount", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Deduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_DeductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_Deduct);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 570, 310));

        btn_save.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 110, 40));

        btn_clear.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 110, 40));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 20, 10));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 20, 10));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("*");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 20, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from deductions_reg");
            while (rs.next()) {
                String code = rs.getString("code");

                if (txt_DeductCode.getText().equals(code)) {
                    int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to Update?", "Update", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        Statement st1 = DbConnection.getconnection().createStatement();
                        st1.executeUpdate("update deductions_reg set name='" + txt_DeductName.getText() + "',description='" + txt_DeductDescription.getText() + "',amount='" + txt_DeductAmount.getText() + "' where code='" + txt_DeductCode.getText() + "'");
                        JOptionPane.showMessageDialog(rootPane, "Your Data has been Updated Successfully...!");
                    }
                    if (reply == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(rootPane, "Did not Update any Data");
                    }
                }

            }
            clear();
            tableclear();
            get_data_to_table();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        if (txt_DeductCode.getText().isEmpty() || txt_DeductName.getText().isEmpty()
                || txt_DeductAmount.getText().isEmpty()) {
                    
                Color color = new Color(255, 161, 161);
                
            if (txt_DeductCode.getText().isEmpty()) {
                txt_DeductCode.setBackground(color);
            }
            if (txt_DeductName.getText().isEmpty()) {
                txt_DeductName.setBackground(color);
            }
            if (txt_DeductAmount.getText().isEmpty()) {
                txt_DeductAmount.setBackground(color);
            }
            JOptionPane.showMessageDialog(rootPane, "Code,Name,Amount Cannot be Empty");
        } else {

            try {

                String code = txt_DeductCode.getText();
                String name = txt_DeductName.getText();
                String description = txt_DeductDescription.getText();

                String ab = txt_DeductAmount.getText();
                double a = Double.parseDouble(ab);
                String amount = String.format("%,.2f", a);

                Statement st = DbConnection.getconnection().createStatement();
                st.executeUpdate("insert into deductions_reg values('" + code + "','" + name + "','" + description + "','" + amount + "')");

                JOptionPane.showMessageDialog(rootPane, "Data has been saved...!", "Done", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }

            tableclear();
            get_data_to_table();
            clear();

        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (txt_DeductCode.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Invalid input");

        } else {

            try {

                Statement st1 = DbConnection.getconnection().createStatement();
                ResultSet rs = st1.executeQuery("select * from deductions_reg");
                while (rs.next()) {
                    String code = rs.getString("code");

                    if (txt_DeductCode.getText().equals(code)) {
                        int reply = JOptionPane.showConfirmDialog(rootPane, "Do yo want to delete this Allowance ?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                        if (reply == JOptionPane.YES_OPTION) {

                            DefaultTableModel dtm = (DefaultTableModel) table_Deduct.getModel();
                            dtm.removeRow(table_Deduct.getSelectedRow());

                            Statement st = DbConnection.getconnection().createStatement();
                            st.execute("delete from deductions_reg where code='" + txt_DeductCode.getText() + "'");

                            JOptionPane.showMessageDialog(rootPane, "successfully deleted...!");
                            clear();

                        }
                        if (reply == JOptionPane.NO_OPTION) {

                            JOptionPane.showMessageDialog(rootPane, "Data did not deleted");

                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        clear();
        tableclear();
        get_data_to_table();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void table_DeductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DeductMouseClicked
        try {

            int row = table_Deduct.getSelectedRow();
            String get_code = table_Deduct.getModel().getValueAt(row, 0).toString();

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from deductions_reg where code = '" + get_code + "'");

            if (rs.next()) {

                txt_DeductCode.setText(rs.getString("code"));
                txt_DeductName.setText(rs.getString("name"));
                txt_DeductAmount.setText(rs.getString("amount"));
                txt_DeductDescription.setText(rs.getString("description"));

            }

        } catch (Exception e) {
        }

        btn_save.setEnabled(false);
    }//GEN-LAST:event_table_DeductMouseClicked

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
          btn_save.setEnabled(true);
        clear();
        txt_DeductCode.setBackground(Color.WHITE);
        txt_DeductName.setBackground(Color.WHITE);
        txt_DeductAmount.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       JOptionPane.showMessageDialog(rootPane, "<html><br>First line.<br>Second line."
                + "<ul>"
                + "<li>list 01</li>"
                + "<li>list 02</li>"
                + "</ul"
                + "</html>");
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
            java.util.logging.Logger.getLogger(Deductions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Deductions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Deductions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Deductions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Deductions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable table_Deduct;
    private javax.swing.JTextField txt_DeductAmount;
    private javax.swing.JTextField txt_DeductCode;
    private javax.swing.JTextField txt_DeductDescription;
    private javax.swing.JTextField txt_DeductName;
    // End of variables declaration//GEN-END:variables

    static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table_Deduct, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table_Deduct, value, selected, focused, row, column);
            setFont(new Font("Georgia", Font.PLAIN, 14));
            setForeground(Color.BLUE);
            setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.LIGHT_GRAY));
            setBackground(Color.LIGHT_GRAY);
            return this;

        }

    }

}
