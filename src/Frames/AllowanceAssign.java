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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
public class AllowanceAssign extends javax.swing.JFrame {

    /**
     * Creates new form AllowanceAssign
     */
    public AllowanceAssign() {
        initComponents();
        auto_completer();
        get_data_to_table();
        
        TitleBar();
        txt_AllowAssign_RankCode.setEditable(false);
        table_allowanceAssign.getTableHeader().setDefaultRenderer(new Allowances.HeaderColor());
        table_allowanceAssign.setFont(new Font("Times New Roman", Font.PLAIN, 14));
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
    
    
    
    
    
    
    
    static ArrayList<table_data> al = new ArrayList<table_data>();
    
    private void clear() {
        
        txt_allowanceAmount.setText("");
        txt_allowanceCode.setText("");
        txt_allowanceName.setText("");
    }
    
    private void table_clear() {
        DefaultTableModel dtm = (DefaultTableModel) table_allowanceAssign.getModel();
        dtm.setRowCount(0);
    }
    
    private void auto_completer() {
        
        try {
            
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from allowances_reg ");
            
            TextAutoCompleter ta = new TextAutoCompleter(txt_allowanceCode);
            
            while (rs.next()) {
                String code = rs.getString("code");
                ta.addItem(code);
            }
            
            
              Statement st1 = DbConnection.getconnection().createStatement();
            ResultSet rs1 = st1.executeQuery("select * from rank");
            
            TextAutoCompleter ta1 = new TextAutoCompleter(txt_AllowAssign_RankCode);
            
            while (rs1.next()) {
                String code = rs1.getString("RankCode");
                ta1.addItem(code);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void get_data_to_table() {
        try {
            
            if (table_allowanceAssign.getRowCount() == 0) {
                
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT rankwise_allowance.AllowanceCode, allowances_reg.name,allowances_reg.amount FROM rankwise_allowance INNER JOIN allowances_reg ON rankwise_allowance.AllowanceCode=allowances_reg.code WHERE RankCode='" + txt_AllowAssign_RankCode.getText() + "'");
                while (rs.next()) {
                    
                    DefaultTableModel dtm = (DefaultTableModel) table_allowanceAssign.getModel();
                    Vector v = new Vector();
                    
                    v.add(rs.getString("AllowanceCode"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("amount"));
                    
                    dtm.addRow(v);
                    
                }
                
            } else {
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_AllowAssign_RankCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_close = new javax.swing.JButton();
        txt_AllowAssign_RankName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cb_setEditable = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        txt_allowanceCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_allowanceName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_allowanceAssign = new javax.swing.JTable();
        btn_apply = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        btn_remove = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_allowanceAmount = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        btn_clear2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Allowances Assign - Rank-wise");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 910, 10));

        txt_AllowAssign_RankCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_AllowAssign_RankCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_AllowAssign_RankCodeFocusLost(evt);
            }
        });
        txt_AllowAssign_RankCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_AllowAssign_RankCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_AllowAssign_RankCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 140, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("Rank Code :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 60, 80, 20));

        btn_close.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancel.png"))); // NOI18N
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 40));

        txt_AllowAssign_RankName.setEditable(false);
        txt_AllowAssign_RankName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_AllowAssign_RankName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_AllowAssign_RankNameFocusLost(evt);
            }
        });
        txt_AllowAssign_RankName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_AllowAssign_RankNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_AllowAssign_RankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 290, -1));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Rank Name :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        cb_setEditable.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cb_setEditable.setText("Edit");
        cb_setEditable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_setEditableMouseClicked(evt);
            }
        });
        getContentPane().add(cb_setEditable, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 450, 120));

        txt_allowanceCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_allowanceCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_allowanceCodeFocusLost(evt);
            }
        });
        txt_allowanceCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_allowanceCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_allowanceCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 140, -1));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Allowance Code :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 110, 20));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Allowance  Name :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 20));

        txt_allowanceName.setEditable(false);
        txt_allowanceName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_allowanceName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_allowanceNameFocusLost(evt);
            }
        });
        txt_allowanceName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_allowanceNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_allowanceName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 310, -1));

        table_allowanceAssign.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Allowance Code", "Allowance Name", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_allowanceAssign.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_allowanceAssignMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_allowanceAssign);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 400, 310));

        btn_apply.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_apply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_applyActionPerformed(evt);
            }
        });
        getContentPane().add(btn_apply, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 50, 40));

        btn_save.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 120, 40));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 910, 10));

        btn_remove.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 50, 40));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Amount :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 70, 20));

        txt_allowanceAmount.setEditable(false);
        txt_allowanceAmount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_allowanceAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_allowanceAmountFocusLost(evt);
            }
        });
        txt_allowanceAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_allowanceAmountKeyPressed(evt);
            }
        });
        getContentPane().add(txt_allowanceAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 140, -1));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 120, 40));

        btn_clear2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_clear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_clear2.setText("Clear");
        btn_clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 120, 40));

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 370));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_AllowAssign_RankCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AllowAssign_RankCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AllowAssign_RankCodeFocusLost

    private void txt_AllowAssign_RankCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AllowAssign_RankCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            table_clear();
            
            String code = txt_AllowAssign_RankCode.getText();

            try {
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from rank");

                while (rs.next()) {
                    String LocCode = rs.getString("RankCode");

                    if (code.equals(LocCode)) {
                        
                        txt_AllowAssign_RankName.setText(rs.getString("RankName"));
                        get_data_to_table();
                    } else {

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_AllowAssign_RankCodeKeyPressed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void txt_AllowAssign_RankNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_AllowAssign_RankNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AllowAssign_RankNameFocusLost

    private void txt_AllowAssign_RankNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_AllowAssign_RankNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AllowAssign_RankNameKeyPressed

    private void txt_allowanceCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_allowanceCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_allowanceCodeFocusLost

    private void txt_allowanceCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_allowanceCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from allowances_reg");
                while (rs.next()) {
                    String code = rs.getString("code");
                    String name = rs.getString("name");
                    String amount = rs.getString("amount");
                    
                    if (txt_allowanceCode.getText().equals(code)) {
                        
                        txt_allowanceName.setText(name);
                        txt_allowanceAmount.setText(amount);
                        
                    } else {
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_txt_allowanceCodeKeyPressed

    private void txt_allowanceNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_allowanceNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_allowanceNameFocusLost

    private void txt_allowanceNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_allowanceNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_allowanceNameKeyPressed

    private void btn_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_applyActionPerformed
        
        if (txt_allowanceCode.getText().isEmpty() || txt_allowanceAmount.getText().isEmpty()) {
            
        } else {
            
            table_data t = new table_data();
            
            t.AllowanceCode = txt_allowanceCode.getText();
            t.AllowanceName = txt_allowanceName.getText();
            t.AllowanceAmount = txt_allowanceAmount.getText();
            
            al.add(t);
            DefaultTableModel dtm = (DefaultTableModel) table_allowanceAssign.getModel();
            Vector v = new Vector();
            
            v.add(txt_allowanceCode.getText());
            v.add(txt_allowanceName.getText());
            v.add(txt_allowanceAmount.getText());
            
            dtm.addRow(v);
            
     
            
        }
        

    }//GEN-LAST:event_btn_applyActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try {
            
            if (txt_AllowAssign_RankCode.getText().isEmpty() || txt_allowanceCode.getText().isEmpty()) {
                
            } else {
                
                Statement st = DbConnection.getconnection().createStatement();
                
                for (table_data td : al) {
                    
                    st.executeUpdate("insert into rankwise_allowance values('" + txt_AllowAssign_RankCode.getText() + "','" + td.AllowanceCode + "')");
                    
                }
                
                JOptionPane.showMessageDialog(rootPane, "Records Saved...! ");
                txt_allowanceCode.setText("");
                txt_allowanceName.setText("");
                txt_allowanceAmount.setText("");
                
                al.clear();
                System.out.println("AL cleared");
                clear();
                get_data_to_table();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        
        if (table_allowanceAssign.getSelectedRowCount() == 0) {
            
        } else {
            
            try {
                
                Statement st = DbConnection.getconnection().createStatement();
                st.executeUpdate("delete from rankwise_allowance where AllowanceCode='" + txt_allowanceCode.getText() + "'");
                
                clear();
                
            } catch (Exception e) {
            }
        }
        

    }//GEN-LAST:event_btn_removeActionPerformed

    private void txt_allowanceAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_allowanceAmountFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_allowanceAmountFocusLost

    private void txt_allowanceAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_allowanceAmountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_allowanceAmountKeyPressed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        try {
            
            Statement st = DbConnection.getconnection().createStatement();
            st.execute("delete from rankwise_allowance where RankCode='" + txt_AllowAssign_RankCode.getText() + "' && AllowanceCode='" + txt_allowanceCode.getText() + "'");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear2ActionPerformed
        table_clear();
        
        txt_AllowAssign_RankCode.setText("");
        txt_AllowAssign_RankName.setText("");
    }//GEN-LAST:event_btn_clear2ActionPerformed

    private void cb_setEditableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_setEditableMouseClicked
        if (cb_setEditable.isSelected()) {
            txt_AllowAssign_RankCode.setEditable(true);
        }
        if (!cb_setEditable.isSelected()) {
            txt_AllowAssign_RankCode.setEditable(false);
        }
    }//GEN-LAST:event_cb_setEditableMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        get_data_to_table();
    }//GEN-LAST:event_formWindowGainedFocus

    private void table_allowanceAssignMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_allowanceAssignMouseClicked
        try {
            
            int row = table_allowanceAssign.getSelectedRow();
            String get_code = table_allowanceAssign.getModel().getValueAt(row, 0).toString();
            
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from allowances_reg where code = '" + get_code + "'");
            
            if (rs.next()) {
                
                txt_allowanceCode.setText(rs.getString("code"));
                txt_allowanceName.setText(rs.getString("name"));
                txt_allowanceAmount.setText(rs.getString("amount"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_table_allowanceAssignMouseClicked

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
            java.util.logging.Logger.getLogger(AllowanceAssign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllowanceAssign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllowanceAssign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllowanceAssign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllowanceAssign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_apply;
    private javax.swing.JButton btn_clear2;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JCheckBox cb_setEditable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable table_allowanceAssign;
    public static javax.swing.JTextField txt_AllowAssign_RankCode;
    public static javax.swing.JTextField txt_AllowAssign_RankName;
    public static javax.swing.JTextField txt_allowanceAmount;
    public static javax.swing.JTextField txt_allowanceCode;
    public static javax.swing.JTextField txt_allowanceName;
    // End of variables declaration//GEN-END:variables

 static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table_allowanceAssign, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table_allowanceAssign, value, selected, focused, row, column);
            setFont(new Font("Georgia", Font.PLAIN, 14));
            setForeground(Color.BLUE);
            setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.LIGHT_GRAY));
            setBackground(Color.LIGHT_GRAY);
            return this;

        }

    }










}
