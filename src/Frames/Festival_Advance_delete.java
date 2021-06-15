/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class Festival_Advance_delete extends javax.swing.JFrame {

    /**
     * Creates new form Advance_delete
     */
    public Festival_Advance_delete() {
        initComponents();

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);

        Color cl2 = new Color(238, 152, 152);
        jButton1.setContentAreaFilled(false);
        jButton1.setBackground(cl2);//178 255 102
        jButton1.setOpaque(true);
        get_Location_Details();
        TitleBar();
    }

    private void TitleBar() {
        this.setTitle("Advance Delete");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void get_Location_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from location_reg order by LocName");
            while (rs.next()) {

                Object name = rs.getString("LocName");
                //Object code = rs.getString("LocCode");

                //cmb_defLocation.addItem(code);
                cmb_defLocation.addItem(name);
            }

            AutoCompleteDecorator.decorate(cmb_defLocation);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get_advance_details() {
        String sql = "0";
        if (jCheckBox1.isSelected()) {

            sql = "select * from salary_festival_deductions_summery join employee_reg on EMPno=EmployeeNo where FestivMonth ='" + cmb_month.getSelectedItem().toString() + "' and FestivYear= '" + cmb_year.getSelectedItem().toString() + "' and Status!='PAID' ";

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println(sql);
                while (rs.next()) {

                    Vector v = new Vector();

                    v.add(rs.getString("EMPno"));
                    v.add(rs.getString("Designation"));
                    v.add(rs.getString("NameWithInitials"));
                    v.add(rs.getString("LoanAmount"));
                    v.add(rs.getString("Unit"));
                    v.add(rs.getString("FestivMonth"));
                    v.add(rs.getString("FestivYear"));
                    v.add(rs.getString("RefNo"));
                    v.add(rs.getString("PayType"));

                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            if (cmb_defLocation.getSelectedItem().toString().equals("=Location=")) {

            } else {
                sql = "select * from salary_festival_deductions_summery join employee_reg on EMPno=EmployeeNo where FestivMonth ='" + cmb_month.getSelectedItem().toString() + "' and FestivYear= '" + cmb_year.getSelectedItem().toString() + "' and Status!='PAID' and Unit='" + txt_locCode.getText() + "' ";

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                try {

                    Statement st = DbConnection.getconnection().createStatement();
                    ResultSet rs = st.executeQuery(sql);

                    System.out.println(sql);
                    while (rs.next()) {

                        Vector v = new Vector();

                        v.add(rs.getString("EMPno"));
                        v.add(rs.getString("Designation"));
                        v.add(rs.getString("NameWithInitials"));
                        v.add(rs.getString("LoanAmount"));
                        v.add(rs.getString("Unit"));
                        v.add(rs.getString("FestivMonth"));
                        v.add(rs.getString("FestivYear"));
                        v.add(rs.getString("RefNo"));
                        v.add(rs.getString("PayType"));
                        dtm.addRow(v);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

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
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_doc_no = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Employee Festival Advance Edit/Delete");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 300, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 810, 10));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 810, 10));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP No.", "Rank", "Name", "Amount", "Location", "Festiv Month", "Festiv  Year", "Reference", "PayType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 680, 290));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancel.png"))); // NOI18N
        jButton1.setText("Delete");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 100, 60));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 810, 10));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        jButton2.setText("Get Advance Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 210, 60));

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
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2018", "2019", "2020", "2021" }));
        cmb_year.setSelectedIndex(3);
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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        cmb_defLocation.setEditable(true);
        cmb_defLocation.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_defLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=Location=" }));
        cmb_defLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_defLocationFocusGained(evt);
            }
        });
        cmb_defLocation.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_defLocationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmb_defLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_defLocationActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 340, -1));

        txt_locCode.setEditable(false);
        txt_locCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_locCodeFocusLost(evt);
            }
        });
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 40, 23));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setText("Location :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel34.setText(" Month  :-");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        txt_doc_no.setEditable(false);
        txt_doc_no.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_doc_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_doc_noFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_doc_noFocusLost(evt);
            }
        });
        getContentPane().add(txt_doc_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 90, 23));

        jLabel33.setBackground(new java.awt.Color(153, 255, 153));
        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setOpaque(true);
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 90, 20));

        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox1.setText("All Locations");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hand", "Bank", "Slip" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 100, 25));

        jLabel35.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel35.setText(" Doc. No  :-");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 80, 70, 20));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton3.setText("<html>Update <br>PayType</html>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, 100, 40));

        jLabel36.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel36.setText("PayType;");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 70, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int nrow = jTable1.getSelectedRowCount();

        int select_row = jTable1.getSelectedRow();

        if (nrow == 0) {

            JOptionPane.showMessageDialog(rootPane, "Zero(0) Rows Selected");

        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, " Do you want to Delete the selected Festival Advance of this Employee?", "Festival Advance Delete", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

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

                                if (cat.equals("admin") | cat.equals("system_admin") | cat.equals("manager")) {

//delete query begins*************************************************************************************************
                                    String EPF = jTable1.getModel().getValueAt(select_row, 0).toString();
                                    String amount = jTable1.getModel().getValueAt(select_row, 1).toString();
                                    String Month = jTable1.getModel().getValueAt(select_row, 2).toString();
                                    String reference = jTable1.getModel().getValueAt(select_row, 7).toString();
                                    String ref = reference.substring(0, 13);

                                    System.out.println(ref);

                                    Statement st = DbConnection.getconnection().createStatement();
                                    st.executeUpdate("delete FROM   salary_festival_deductions_summery  where RefNo  LIKE '%" + ref + "%'");
                                    st.executeUpdate("delete FROM   salary_advance_festival_monthly  where RefNo  LIKE '%" + ref + "%'");
                                   // st.executeUpdate("UPDATE  salary_advance_festival_monthly  SET Status='DELETED'  RefNo  LIKE '%" + ref + "%'");
//                                    st.executeUpdate("delete from salary_advance_2 where EPFno = '" + txt_empid.getText() + "' and IssueDate = '" + Date + "' and Amount = '" + amount + "' and Note = '" + note + "' and Reference = '" + reference + "'");
//                                    st.executeUpdate("delete from salary_advance_festival where EPFno = '" + txt_empid.getText() + "' and IssueDate = '" + Date + "' and Amount = '" + amount + "' and Note = '" + note + "' and Reference = '" + reference + "'");

                                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                                    dtm.removeRow(jTable1.getSelectedRow());

                                    JOptionPane.showMessageDialog(rootPane, " Festival Advance Deleted... ");
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

            } else {

            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        get_advance_details();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode.setText("N/A");
            txt_locCode.setForeground(Color.red);

        } else {
            txt_locCode.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(name);
                    txt_locCode.setText(code);
                }

                //                ResultSet rs1 = st.executeQuery("select * from location_shift_rates where LocCode= '" + txt_locCode.getText() + "' ");
                //
                //                while (rs1.next()) {
                //
                //                    String rank = rs1.getString("Rank");
                //
                //                }
                //cmb_defLocation.setEditable(false);
                //cmb_defLocation.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_locCodeFocusGained

    private void txt_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusLost

    private void txt_doc_noFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_doc_noFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_noFocusGained

    private void txt_doc_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_doc_noFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_noFocusLost

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            cmb_defLocation.setEnabled(false);
        } else {

            cmb_defLocation.setEnabled(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void cmb_defLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_defLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getRowCount() > 0) {

            if (evt.getClickCount() > 1) {

                try {

                    String emp = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                    String pt = jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString();
                    jLabel33.setText(emp);
                    jComboBox1.setSelectedItem(pt);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            String emp = jLabel33.getText();

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("update salary_festival_deductions_summery set PayType = '" + jComboBox1.getSelectedItem().toString() + "' where EMPno='" + emp + "' and FestivMonth='" + cmb_month.getSelectedItem().toString() + "' and FestivYear='" + cmb_year.getSelectedItem().toString() + "' ");
            pst.executeUpdate();

            get_advance_details();

            jLabel33.setText("Updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }

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
            java.util.logging.Logger.getLogger(Festival_Advance_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Festival_Advance_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Festival_Advance_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Festival_Advance_delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Festival_Advance_delete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_doc_no;
    private javax.swing.JTextField txt_locCode;
    // End of variables declaration//GEN-END:variables
}
