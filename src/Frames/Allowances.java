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
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_S;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.Double;
import java.math.BigInteger;
import java.text.DecimalFormatSymbols;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sapu
 */
public class Allowances extends javax.swing.JFrame {

    /**
     * Creates new form Allowances
     */
    public Allowances() {
        initComponents();

        get_data_to_table();
        TitleBar();
        auto_completer();
        table_properties();

        table_Allowances.getTableHeader().setDefaultRenderer(new HeaderColor());

     
    
    }
    
    

    private void table_properties() {

        table_Allowances.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        table_Allowances.getColumnModel().getColumn(0).setPreferredWidth(1);
        table_Allowances.getColumnModel().getColumn(1).setPreferredWidth(100);
        table_Allowances.getColumnModel().getColumn(2).setPreferredWidth(50);
        table_Allowances.getColumnModel().getColumn(3).setPreferredWidth(200);
        table_Allowances.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from allowances_reg ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_AllwancesCode);

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

            ResultSet rs = st.executeQuery("select * from allowances_reg");

            while (rs.next()) {

                DefaultTableModel dtm = (DefaultTableModel) table_Allowances.getModel();
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

        txt_AllowanceAmount.setText("");
        txt_AllowanceDescription.setText("");
        txt_AllowanceName.setText("");
        txt_AllwancesCode.setText("");

    }

    private void tableclear() {

        DefaultTableModel dtm = (DefaultTableModel) table_Allowances.getModel();
        dtm.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_refresh = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_AllowanceAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_AllwancesCode = new javax.swing.JTextField();
        txt_AllowanceName = new javax.swing.JTextField();
        txt_AllowanceDescription = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Allowances = new javax.swing.JTable();
        btn_save = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(732, 540));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_refresh.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        getContentPane().add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, -1, 40));

        btn_delete.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, 110, 40));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 110, 40));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Allowances Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Amount :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, 20));

        txt_AllowanceAmount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_AllowanceAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_AllowanceAmountFocusLost(evt);
            }
        });
        getContentPane().add(txt_AllowanceAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 110, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 20, 10));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Allowance Name :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Description :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        txt_AllwancesCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_AllwancesCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_AllwancesCodeFocusLost(evt);
            }
        });
        txt_AllwancesCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_AllwancesCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_AllwancesCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, -1));

        txt_AllowanceName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_AllowanceName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_AllowanceNameFocusGained(evt);
            }
        });
        getContentPane().add(txt_AllowanceName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 440, -1));

        txt_AllowanceDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_AllowanceDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 440, -1));

        table_Allowances.setModel(new javax.swing.table.DefaultTableModel(
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
        table_Allowances.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_AllowancesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_Allowances);

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

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Allowance Code :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 20, 10));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 20, 10));

        text.setText("jTextField1");
        getContentPane().add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 160, -1));

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
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 130, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from allowances_reg");
            while (rs.next()) {
                String code = rs.getString("code");

                if (txt_AllwancesCode.getText().equals(code)) {
                    int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to Update?", "Update", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        Statement st1 = DbConnection.getconnection().createStatement();
                        st1.executeUpdate("update allowances_reg set name='" + txt_AllowanceName.getText() + "',description='" + txt_AllowanceDescription.getText() + "',amount='" + txt_AllowanceAmount.getText() + "' where code='" + txt_AllwancesCode.getText() + "'");
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        if (txt_AllwancesCode.getText().isEmpty() || txt_AllowanceName.getText().isEmpty()
                || txt_AllowanceAmount.getText().isEmpty()) {

            if (txt_AllwancesCode.getText().isEmpty()) {
                txt_AllwancesCode.setBackground(Color.RED);
            }
            if (txt_AllowanceName.getText().isEmpty()) {
                txt_AllowanceName.setBackground(Color.red);
            }
            if (txt_AllowanceAmount.getText().isEmpty()) {
                txt_AllowanceAmount.setBackground(Color.red);
            }
            JOptionPane.showMessageDialog(rootPane, "Code,Name,Amount Cannot be Empty");
        } else {

            try {

                String code = txt_AllwancesCode.getText();
                String name = txt_AllowanceName.getText();
                String description = txt_AllowanceDescription.getText();

                String ab = txt_AllowanceAmount.getText();
                double a = Double.parseDouble(ab);
                String amount = String.format("%,.2f", a);

                Statement st = DbConnection.getconnection().createStatement();
                st.executeUpdate("insert into allowances_reg values('" + code + "','" + name + "','" + description + "','" + amount + "')");

                JOptionPane.showMessageDialog(rootPane, "Data has been saved...!", "Done", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }

            tableclear();
            get_data_to_table();
            clear();

        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void txt_AllowanceAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AllowanceAmountFocusLost

    }//GEN-LAST:event_txt_AllowanceAmountFocusLost

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        clear();
        tableclear();
        get_data_to_table();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

        if (txt_AllwancesCode.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Invalid input");

        } else {

            try {

                Statement st1 = DbConnection.getconnection().createStatement();
                ResultSet rs = st1.executeQuery("select * from allowances_reg");
                while (rs.next()) {
                    String code = rs.getString("code");

                    if (txt_AllwancesCode.getText().equals(code)) {
                        int reply = JOptionPane.showConfirmDialog(rootPane, "Do yo want to delete this Allowance ?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                        if (reply == JOptionPane.YES_OPTION) {

                            DefaultTableModel dtm = (DefaultTableModel) table_Allowances.getModel();
                            dtm.removeRow(table_Allowances.getSelectedRow());

                            Statement st = DbConnection.getconnection().createStatement();
                            st.execute("delete from allowances_reg where code='" + txt_AllwancesCode.getText() + "'");

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

        }


    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_AllwancesCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AllwancesCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from allowances_reg");
                while (rs.next()) {
                    String code = rs.getString("code");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String amount = rs.getString("amount");

                    if (txt_AllwancesCode.getText().equals(code)) {

                        btn_save.setEnabled(false);
                        txt_AllowanceName.setText(name);
                        txt_AllowanceAmount.setText(amount);
                        txt_AllowanceDescription.setText(description);

                    } else {
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_txt_AllwancesCodeKeyPressed

    private void txt_AllwancesCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AllwancesCodeFocusLost
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from allowances_reg");
//            while (rs.next()) {
//                String code = rs.getString("code");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                String amount = rs.getString("amount");
//
//                if (txt_AllwancesCode.getText().equals(code)) {
//                    btn_save.setEnabled(false);
//                    txt_AllowanceName.setText(name);
//                    txt_AllowanceAmount.setText(amount);
//                    txt_AllowanceDescription.setText(description);
//                } else {
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_txt_AllwancesCodeFocusLost

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        btn_save.setEnabled(true);
        clear();
        txt_AllwancesCode.setBackground(Color.WHITE);
        txt_AllowanceName.setBackground(Color.WHITE);
        txt_AllowanceAmount.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void table_AllowancesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_AllowancesMouseClicked
        try {

            int row = table_Allowances.getSelectedRow();
            String get_code = table_Allowances.getModel().getValueAt(row, 0).toString();

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from allowances_reg where code = '" + get_code + "'");

            if (rs.next()) {

                txt_AllwancesCode.setText(rs.getString("code"));
                txt_AllowanceName.setText(rs.getString("name"));
                txt_AllowanceAmount.setText(rs.getString("amount"));
                txt_AllowanceDescription.setText(rs.getString("description"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_save.setEnabled(false);
    }//GEN-LAST:event_table_AllowancesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        JOptionPane.showMessageDialog(rootPane, "<html><br>First line.<br>Second line."
//                + "<ul>"
//                + "<li>list 01</li>"
//                + "<li>list 02</li>"
//                + "</ul"
//                + "</html>");
        
    
//    MAIN main = new MAIN();
//    main.dispose();
//        
      
        
        
//        System.exit(0);
//        
//        this.dispose();
        
        
        java.awt.Window win[] = java.awt.Window.getWindows(); 
for(int i=0;i<win.length;i++){ 
win[i].dispose(); 
//     User_Loging us = new User_Loging();
//        us.setVisible(true);
} 
     User_Loging us = new User_Loging();
        us.setVisible(true);
        
        
        
//    try 
//{
//   Thread.sleep(5000);
//     User_Loging us = new User_Loging();
//        us.setVisible(true);
//} 
//catch (InterruptedException e) 
//{
//   e.printStackTrace();
//}
//
//System.exit(0);
//  User_Loging us = new User_Loging();
//        us.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_AllowanceNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AllowanceNameFocusGained
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from allowances_reg");
//            while (rs.next()) {
//                String code = rs.getString("code");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                String amount = rs.getString("amount");
//
//                if (txt_AllwancesCode.getText().equals(code)) {
//                    btn_save.setEnabled(false);
//                    txt_AllowanceName.setText(name);
//                    txt_AllowanceAmount.setText(amount);
//                    txt_AllowanceDescription.setText(description);
//                } else {
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_txt_AllowanceNameFocusGained

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
            java.util.logging.Logger.getLogger(Allowances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Allowances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Allowances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Allowances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Allowances().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table_Allowances;
    private javax.swing.JTextField text;
    private javax.swing.JTextField txt_AllowanceAmount;
    private javax.swing.JTextField txt_AllowanceDescription;
    private javax.swing.JTextField txt_AllowanceName;
    private javax.swing.JTextField txt_AllwancesCode;
    // End of variables declaration//GEN-END:variables

    static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table_allowances, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table_allowances, value, selected, focused, row, column);
            setFont(new Font("Georgia", Font.PLAIN, 14));
            setForeground(Color.BLUE);
            setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.LIGHT_GRAY));
            setBackground(Color.LIGHT_GRAY);
            return this;

        }

    }

}
