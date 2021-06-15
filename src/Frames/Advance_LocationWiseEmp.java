/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class Advance_LocationWiseEmp extends javax.swing.JFrame {

    /**
     * Creates new form Advance_generate
     */
    public Advance_LocationWiseEmp() {
        initComponents();

        jTable1.getTableHeader().setDefaultRenderer(new Table_Header.HeaderColor());

        //jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

    private void Get_Total_TableAmount() {
        int nrow = jTable1.getModel().getRowCount();

        if (nrow == 0) {

        } else {
            Double Stotal = 0.00;
            for (int i = 0; nrow > i; i++) {

                String amount = jTable1.getModel().getValueAt(i, 3).toString();

                Double sum_total = Double.parseDouble(amount);
                Stotal += sum_total;

            }
            String Gtotal = String.format("%.2f", (Stotal));
            txt_Total_advance.setText(Gtotal);

        }

    }

    private void Get_Total_Employees_inTable() {
        int nrow = jTable1.getModel().getRowCount();

        if (nrow == 0) {

        } else {

            String total = Integer.toString(nrow);
            txt_no_of_emp.setText(total);

        }

    }

    private void Load_employees() {

        if (txt_locCode.getText().equals("=NA=")) {
//Do Nothing
        } else {
            int nrow = jTable1.getModel().getRowCount();
            if (nrow == 0) {

                try {
                    Connection con = DbConnection.getconnection();
                    PreparedStatement pst = null;
                    ResultSet rs = null;

                    String sql = "select *,COUNT(*) from advance_temp where  Month= '" + cmb_month.getSelectedItem().toString() + "' and Year= '" + cmb_year.getSelectedItem().toString() + "' and LocCode='" + txt_locCode.getText() + "' order by EMPno ";
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

                        int count = Integer.parseInt(rs.getString("COUNT(*)"));
                        if (count > 0) { //Get Details from "advance_temp" table

                            Vector v = new Vector();
                            v.add(rs.getString("Rank"));
                            v.add(rs.getString("EMPno"));
                            v.add(rs.getString("EPFno"));
                            v.add(rs.getString("Name"));
                            v.add(rs.getString("Amount"));
                            v.add(rs.getString("Rank"));

                        } else if (count == 0) {//Get details from emp_reg table

                        } else {
                            //Do Nothing
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, "Error while getting the employees");
                }

            } else {
//Do Nothing
                JOptionPane.showMessageDialog(rootPane, "Table Should be empty in order to Load Employees again. ");
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

        txt_Total_advance = new javax.swing.JTextField();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        txt_no_of_emp = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        cmb_defLocation = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txt_locCode = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Total_advance.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Total_advance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Total_advanceFocusLost(evt);
            }
        });
        txt_Total_advance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Total_advanceKeyPressed(evt);
            }
        });
        getContentPane().add(txt_Total_advance, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, 80, -1));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 110, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2018", "2019", "2020", "2021", "2022", "2023", "2025" }));
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel18.setText("Salary Month / Year :-");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/In Progress-48.png"))); // NOI18N
        jButton1.setText("Load Employees");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 280, 50));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank", "EMP No.", "EPF No.", "Name", "Advance Amt."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(20);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(85);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(85);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 640, 370));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText("Advance Total :-");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 100, 20));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Advance Sheet Generate");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 850, 10));

        jLabel21.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel21.setText("No. of Employees:-");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, -1, 20));

        txt_no_of_emp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_no_of_emp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_no_of_empFocusLost(evt);
            }
        });
        txt_no_of_emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_no_of_empKeyPressed(evt);
            }
        });
        getContentPane().add(txt_no_of_emp, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 430, 80, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton2.setText("Remove  Row");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 170, 40));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 170, 40));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 170, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 850, 10));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 420, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setText("Location:-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 40));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 50, 23));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 170, 40));

        jButton6.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton6.setText("Advance Issue");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 180, 90));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_Total_advanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Total_advanceFocusLost
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
    }//GEN-LAST:event_txt_Total_advanceFocusLost

    private void txt_Total_advanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Total_advanceKeyPressed
//        //(evt.getKeyCode() == KeyEvent.VK_F1  && evt.isControlDown()
//
//            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                String code = txt_LocCode.getText();
//
//                try {
//                    Statement st = DbConnection.getconnection().createStatement();
//                    ResultSet rs = st.executeQuery("select * from location_reg");
//
//                    while (rs.next()) {
//                        String LocCode = rs.getString("LocCode");
//
//                        if (code.equals(LocCode)) {
//                            btn_LocSave.setEnabled(false);
//
//                            txt_LocName.setText(rs.getString("LocName"));
//                            txt_LocAddress.setText(rs.getString("LocAddress"));
//                            txt_LocTel1.setText(rs.getString("Tel1"));
//                            txt_LocTel2.setText(rs.getString("Tel2"));
//                            txt_LocTel3.setText(rs.getString("Tel3"));
//                            txt_LocCity.setText(rs.getString("City"));
//                            txt_LocDistrict.setText(rs.getString("District"));
//                            txt_LocIncharge.setText(rs.getString("LocInchargeName"));
//                            txt_LocOwnerName.setText(rs.getString("LocOwnerName"));
//                            txt_LocOwnerTel.setText(rs.getString("LocOwnerTel"));
//                            txt_ShiftRate.setText(rs.getString("LocOwnerMobile"));
//                            cmb_locType.setSelectedItem(rs.getString("LocType"));
//
//                        } else {
//
//                        }
//                    }
//                    DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
//                    dtm.setRowCount(0);
//
//                    Statement st1 = DbConnection.getconnection().createStatement();
//                    ResultSet rs1 = st1.executeQuery("select * from location_shift_rates where LocCode = '" + txt_LocCode.getText() + "'");
//
//                    while (rs1.next()) {
//
//                        Vector v = new Vector();
//                        v.add(rs1.getString("Rank"));
//                        v.add(rs1.getString("ShiftRate"));
//
//                        dtm.addRow(v);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
    }//GEN-LAST:event_txt_Total_advanceKeyPressed

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible


    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_no_of_empFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_no_of_empFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_no_of_empFocusLost

    private void txt_no_of_empKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_no_of_empKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_no_of_empKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int select_row = jTable1.getSelectedRowCount();

        if (select_row == 0) {
        } else {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.removeRow(jTable1.getSelectedRow());

            txt_Total_advance.setText("0.00");
            txt_no_of_emp.setText("0");

            Get_Total_TableAmount();
            Get_Total_Employees_inTable();

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int nrow = jTable1.getModel().getRowCount();

        if (nrow == 0) {

        } else {
            for (int i = 0; nrow > i; i++) {

                String epf = jTable1.getModel().getValueAt(i, 2).toString();
                String name = jTable1.getModel().getValueAt(i, 3).toString();
                String rank = jTable1.getModel().getValueAt(i, 0).toString();
                String amount = jTable1.getModel().getValueAt(i, 4).toString();
                String emp = jTable1.getModel().getValueAt(i, 1).toString();

                String year = cmb_year.getSelectedItem().toString();
                String month = cmb_month.getSelectedItem().toString();
                String location = cmb_defLocation.getSelectedItem().toString();
                String locCode = txt_locCode.getText();

                try {

                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeUpdate("insert into advance_temp values('" + epf + "','" + emp + "','" + name + "','" + rank + "','" + amount + "','" + locCode + "','" + location + "'')");

                    JOptionPane.showMessageDialog(rootPane, "Saved...!");
                    jButton3.setEnabled(false);
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
            JOptionPane.showMessageDialog(rootPane, " Advances Successfully Saved...  ");

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        jButton3.setEnabled(true);

        txt_Total_advance.setText("");
        txt_no_of_emp.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode.setText("=NA=");
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

                ResultSet rs1 = st.executeQuery("select * from location_shift_rates where LocCode= '" + txt_locCode.getText() + "' ");

                while (rs1.next()) {

                    String rank = rs1.getString("Rank");

                }

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (txt_locCode.getText().equals("=NA=")) {
            JOptionPane.showMessageDialog(rootPane, "Please select a Location");
        } else {
            try {

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\Advance_Sheet.jrxml");
                String sql = "SELECT * FROM advance_temp  where Month= '" + cmb_month.getSelectedItem().toString() + "' and Year= '" + cmb_year.getSelectedItem().toString() + "' and LocCode='" + txt_locCode.getText() + "' order by EMPno ";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Advance_Sheet Report Error");
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Advance_LocationWiseEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Advance_LocationWiseEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Advance_LocationWiseEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Advance_LocationWiseEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Advance_LocationWiseEmp().setVisible(true);
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_Total_advance;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_no_of_emp;
    // End of variables declaration//GEN-END:variables

    static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable jTable1, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(jTable1, value, selected, focused, row, column);
            setFont(new Font("Georgia", Font.PLAIN, 14));
            setForeground(Color.BLUE);
            setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.LIGHT_GRAY));
            setBackground(Color.LIGHT_GRAY);
            return this;

        }

    }

}
