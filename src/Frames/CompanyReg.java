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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sapu
 */
public class CompanyReg extends javax.swing.JFrame {

    /**
     * Creates new form CompanyReg
     */
    public CompanyReg() {
        initComponents();
        auto_completer();
        get_default();
        TitleBar();

        txt_getDefCode.setVisible(false);
    }

    static int Set_default;
    JTextField tf;

    private void TitleBar() {

        this.setTitle("Company Registration");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    public void auto_completer() {
        TextAutoCompleter ta = new TextAutoCompleter(txt_ComCode);

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg");

            while (rs.next()) {

                String ComCode = rs.getString("ComCode");
                ta.addItem(ComCode);

            }

        } catch (Exception e) {
        }

    }

    public void get_default() {
        try {

            Statement st2 = DbConnection.getconnection().createStatement();
            ResultSet rs = st2.executeQuery("select ComCode from company_reg where isDefault=1");
            while (rs.next()) {
                String default_code = rs.getString("ComCode");
                txt_getDefCode.setText(default_code);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clear() {
        txt_ComCode.setText("");
        txt_ComName.setText("");
        txt_ComAddress.setText("");
        txt_ComTel1.setText("");
        txt_ComTel2.setText("");
        txt_ComTel3.setText("");
        txt_ComRegNo.setText("");
        txt_ComVatNo.setText("");
        txt_ComTaxNo.setText("");
        txt_ComNote.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_ComTaxNo = new javax.swing.JTextField();
        txt_ComAddress = new javax.swing.JTextField();
        txt_ComRegNo = new javax.swing.JTextField();
        txt_ComCode = new javax.swing.JTextField();
        txt_ComName = new javax.swing.JTextField();
        chkb_SetDefault = new javax.swing.JCheckBox();
        txt_ComTel2 = new javax.swing.JTextField();
        txt_ComVatNo = new javax.swing.JTextField();
        txt_ComTel3 = new javax.swing.JTextField();
        txt_ComTel1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_ComNote = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btn_clear = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_getDefCode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Company Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 600, 10));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel2.setText("Note  :-");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Company Code :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("Company Name :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 30));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Address   :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Reg. No.  :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 70, 40));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("VAT Reg. No.  :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("TAX Reg. No.  :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, 40));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Telephone  :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 20));

        txt_ComTaxNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComTaxNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComTaxNoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComTaxNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 140, -1));

        txt_ComAddress.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComAddressActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 430, -1));

        txt_ComRegNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComRegNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComRegNoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComRegNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 160, -1));

        txt_ComCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ComCodeFocusLost(evt);
            }
        });
        txt_ComCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComCodeActionPerformed(evt);
            }
        });
        txt_ComCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ComCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_ComCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, -1));

        txt_ComName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComNameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 430, -1));

        chkb_SetDefault.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        chkb_SetDefault.setText("Set Default");
        getContentPane().add(chkb_SetDefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        txt_ComTel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComTel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComTel2ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComTel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 120, -1));

        txt_ComVatNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComVatNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComVatNoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComVatNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 140, -1));

        txt_ComTel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComTel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComTel3ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ComTel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 120, -1));

        txt_ComTel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComTel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ComTel1ActionPerformed(evt);
            }
        });
        txt_ComTel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ComTel1KeyTyped(evt);
            }
        });
        getContentPane().add(txt_ComTel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 120, -1));

        txt_ComNote.setColumns(20);
        txt_ComNote.setRows(5);
        jScrollPane1.setViewportView(txt_ComNote);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 440, 80));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 600, 10));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 600, 10));

        btn_clear.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, -1, 40));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, -1, 40));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 110, 40));

        btn_save.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, 40));

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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 100, 40));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 20, 10));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("*");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 20, 10));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("*");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 20, 10));
        getContentPane().add(txt_getDefCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 20, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_ComTaxNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComTaxNoActionPerformed
        txt_ComVatNo.grabFocus();
    }//GEN-LAST:event_txt_ComTaxNoActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

        String code = txt_ComCode.getText();
        String name = txt_ComName.getText();
        String address = txt_ComAddress.getText();
        String tel1 = txt_ComTel1.getText();
        String tel2 = txt_ComTel2.getText();
        String tel3 = txt_ComTel3.getText();
        String com_reg = txt_ComRegNo.getText();
        String vat = txt_ComVatNo.getText();
        String tax = txt_ComTaxNo.getText();
        String note = txt_ComNote.getText();

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg");

            while (rs.next()) {

                String ComCode = rs.getString("ComCode");

                if (code.equals(ComCode)) {

                    if (chkb_SetDefault.isSelected()) {

                        Set_default = 1;

                        Statement st1 = DbConnection.getconnection().createStatement();
                        st1.executeUpdate("update company_reg set isDefault='0' where isDefault='1'");

                        st1.executeUpdate("update company_reg set ComName='" + name + "',ComAddress='" + address + "',"
                                + "ComTel1='" + tel1 + "',ComTel2='" + tel2 + "',ComTel3='" + tel3 + "',ComRegNo='" + com_reg + "',ComVatNo='" + vat + "',"
                                + "ComTaxNo='" + tax + "',ComNote='" + note + "',isDefault='" + Set_default + "' where ComCode='" + code + "'");

                    } else if (!chkb_SetDefault.isSelected()) {

                        Set_default = 0;

                        Statement st3 = DbConnection.getconnection().createStatement();
                        st3.executeUpdate("update company_reg set ComName='" + name + "',ComAddress='" + address + "',"
                                + "ComTel1='" + tel1 + "',ComTel2='" + tel2 + "',ComTel3='" + tel3 + "',ComRegNo='" + com_reg + "',ComVatNo='" + vat + "',"
                                + "ComTaxNo='" + tax + "',ComNote='" + note + "',isDefault='" + Set_default + "' where ComCode='" + code + "'");
                    }

                }
            }

            clear();
            get_default();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        JOptionPane.showMessageDialog(rootPane, "<html><br>First line.<br>Second line."
//                + "<ul>"
//                + "<li>list 01</li>"
//                + "<li>list 02</li>"
//                + "</ul"
//                + "</html>");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

        String code = txt_ComCode.getText();
        String name = txt_ComName.getText();
        String address = txt_ComAddress.getText();
        String tel1 = txt_ComTel1.getText();
        String tel2 = txt_ComTel2.getText();
        String tel3 = txt_ComTel3.getText();
        String com_reg = txt_ComRegNo.getText();
        String vat = txt_ComVatNo.getText();
        String tax = txt_ComTaxNo.getText();
        String note = txt_ComNote.getText();

        if (code.isEmpty() || name.isEmpty() || address.isEmpty()) {

            Color color = new Color(255, 161, 161);

            if (code.isEmpty()) {
                txt_ComCode.setBackground(color);
            }
            if (name.isEmpty()) {
                txt_ComName.setBackground(color);
            }
            if (address.isEmpty()) {
                txt_ComAddress.setBackground(color);
            }

            JOptionPane.showMessageDialog(null, " Code , Name & Address can not be Empty  ");

        } else {

            try {

                if (chkb_SetDefault.isSelected()) {

                    Set_default = 1;
                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeUpdate("update company_reg set isDefault='0' where isDefault='1'");
                    
                    st.executeUpdate("insert into company_reg values('" + code + "','" + name + "','" + address + "',"
                            + "'" + tel1 + "','" + tel2 + "','" + tel3 + "','" + com_reg + "','" + vat + "','" + tax + "',"
                            + "'" + note + "','" + Set_default + "')");

                } else {
                    Set_default = 0;
                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeUpdate("insert into company_reg values('" + code + "','" + name + "','" + address + "',"
                            + "'" + tel1 + "','" + tel2 + "','" + tel3 + "','" + com_reg + "','" + vat + "','" + tax + "',"
                            + "'" + note + "','" + Set_default + "')");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, " Company Details are Successfully Saved ");

            clear();
            get_default();
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void txt_ComCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ComCodeFocusLost


    }//GEN-LAST:event_txt_ComCodeFocusLost

    private void txt_ComCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ComCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String code = txt_ComCode.getText();

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from company_reg where ComCode='" + code + "'");
                while (rs.next()) {

                    String LocCode = rs.getString("ComCode");
                    String set_defualt = rs.getString("isDefault");

                    if (code.equals(LocCode)) {

                        if (set_defualt.equals("1")) {

                            chkb_SetDefault.setSelected(true);

                            txt_ComName.setText(rs.getString("ComName"));
                            txt_ComAddress.setText(rs.getString("ComAddress"));
                            txt_ComTel1.setText(rs.getString("ComTel1"));
                            txt_ComTel2.setText(rs.getString("ComTel2"));
                            txt_ComTel3.setText(rs.getString("ComTel3"));
                            txt_ComRegNo.setText(rs.getString("ComRegNo"));
                            txt_ComVatNo.setText(rs.getString("ComVatNo"));
                            txt_ComTaxNo.setText(rs.getString("ComTaxNo"));
                            txt_ComNote.setText(rs.getString("ComNote"));

                        } else if (set_defualt.equals("0")) {

                            chkb_SetDefault.setSelected(false);

                            txt_ComName.setText(rs.getString("ComName"));
                            txt_ComAddress.setText(rs.getString("ComAddress"));
                            txt_ComTel1.setText(rs.getString("ComTel1"));
                            txt_ComTel2.setText(rs.getString("ComTel2"));
                            txt_ComTel3.setText(rs.getString("ComTel3"));
                            txt_ComRegNo.setText(rs.getString("ComRegNo"));
                            txt_ComVatNo.setText(rs.getString("ComVatNo"));
                            txt_ComTaxNo.setText(rs.getString("ComTaxNo"));
                            txt_ComNote.setText(rs.getString("ComNote"));

                            btn_save.setEnabled(false);
                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_ComCodeKeyPressed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed

        clear();

        txt_ComCode.setBackground(Color.WHITE);
        txt_ComName.setBackground(Color.WHITE);
        txt_ComAddress.setBackground(Color.WHITE);

        get_default();// TODO add your handling code here:
    }//GEN-LAST:event_btn_clearActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            String code = txt_ComCode.getText();
            Statement st1 = DbConnection.getconnection().createStatement();
            ResultSet rs = st1.executeQuery("select * from company_reg");

            while (rs.next()) {

                String ComCode = rs.getString("ComCode");

                if (code.equals(ComCode)) {

                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeQuery("delete from company_reg where ComCode='" + txt_ComCode.getText() + "'");

                    JOptionPane.showMessageDialog(rootPane, "Location was Deleted");
                    clear();
                    btn_save.setEnabled(true);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_ComTel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ComTel1KeyTyped

//        char count = evt.getKeyChar();
//        String num = txt_ComTel1.getText();
//        int length = txt_ComTel1.getText().length();
//        String pe = num.substring(0, length);
//        
//        String dot = ".";
//        
//
//        if (pe.contains(".") && pe.contains(".")) {
//(Character.isDigit(evt.getKeyChar()))
//            System.out.println("dot have");
//        }
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;
            text = txt_ComTel1.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {
                if (text[i] == '.') {
                    count++;
                }
            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }
        } else {
            evt.consume();
        }

//        
//        char c = evt.getKeyChar();
//        if (!((c >= 0) && (c <= 9) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
//            getToolkit().beep();
//            evt.consume();
//        }
    }//GEN-LAST:event_txt_ComTel1KeyTyped

    private void txt_ComCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComCodeActionPerformed
        txt_ComName.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComCodeActionPerformed

    private void txt_ComAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComAddressActionPerformed
        txt_ComTel1.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComAddressActionPerformed

    private void txt_ComNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComNameActionPerformed
        txt_ComAddress.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComNameActionPerformed

    private void txt_ComTel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComTel1ActionPerformed
        txt_ComTel2.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComTel1ActionPerformed

    private void txt_ComTel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComTel2ActionPerformed
        txt_ComTel3.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComTel2ActionPerformed

    private void txt_ComTel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComTel3ActionPerformed
        txt_ComRegNo.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComTel3ActionPerformed

    private void txt_ComRegNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComRegNoActionPerformed
        txt_ComTaxNo.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComRegNoActionPerformed

    private void txt_ComVatNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ComVatNoActionPerformed
        txt_ComNote.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComVatNoActionPerformed

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
            java.util.logging.Logger.getLogger(CompanyReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompanyReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompanyReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompanyReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompanyReg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JCheckBox chkb_SetDefault;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField txt_ComAddress;
    private javax.swing.JTextField txt_ComCode;
    private javax.swing.JTextField txt_ComName;
    private javax.swing.JTextArea txt_ComNote;
    private javax.swing.JTextField txt_ComRegNo;
    private javax.swing.JTextField txt_ComTaxNo;
    private javax.swing.JTextField txt_ComTel1;
    private javax.swing.JTextField txt_ComTel2;
    private javax.swing.JTextField txt_ComTel3;
    private javax.swing.JTextField txt_ComVatNo;
    private javax.swing.JTextField txt_getDefCode;
    // End of variables declaration//GEN-END:variables
}
