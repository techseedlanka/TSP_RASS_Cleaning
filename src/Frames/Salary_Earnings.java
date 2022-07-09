/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.Salary_Deductions.txt_amt;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Salary_Earnings extends javax.swing.JFrame {

    /**
     * Creates new form Salary_Deductions
     */
    public Salary_Earnings() {
        initComponents();
        jTable2.getTableHeader().setUI(null);
        jScrollPane4.setVisible(false);
        jTable1.getTableHeader().setUI(null);
        jScrollPane1.setVisible(false);
        TitleBar();
        txt_basic.setVisible(false);
        jCheckBox1.setSelected(false);
        // cb_all.setVisible(false);
    }

    private void TitleBar() {

        this.setTitle("Monthly Salary Earnings");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void add_to_table() {
        // search_duplicates();
        if (txt_empno.getText().isEmpty() | txt_amt.getText().isEmpty() | cmb_earning_type.getSelectedIndex() == 0) {
        } else {

            if (txt_totalAmtl.getText().isEmpty()) {
                txt_totalAmtl.setText("0.00");
            }

            //adding new amount to current total
            Double total = Double.parseDouble(txt_totalAmtl.getText());
            Double amt = Double.parseDouble(txt_amt.getText());
            total += amt;
            txt_totalAmtl.setText(String.format("%.2f", total));

            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            Vector v = new Vector();
            String RankType = get_rankType(txt_empno.getText());
             System.out.println(RankType);
            v.add(txt_empno.getText());
            v.add(txt_empName.getText());
            v.add(txt_basic.getText());
            v.add(String.format("%.2f", amt));
            v.add(cmb_month.getSelectedItem().toString());
            v.add(cmb_year.getSelectedItem().toString());
            v.add(RankType);
            dtm.addRow(v);

            txt_empno.setText("");
            txt_empName.setText("");
            txt_basic.setText("");
            txt_amt.setText("");
            txt_empName.grabFocus();

            if (jTable3.getRowCount() > 0) {
                cmb_earning_type.setEnabled(false);

            }

        }

    }

    private void get_sum() {
        int rows = jTable3.getRowCount();
        if (jTable3.getRowCount() <= 0) {

        } else {
            Double total = 0.00;

            for (int r = 0; rows > r; r++) {

                Double amt = Double.parseDouble(jTable3.getValueAt(r, 3).toString());

                total += amt;

            }

            txt_totalAmtl.setText(String.format("%.2f", total));
        }
        lbl_empCount.setText("Emp. Count : " + Integer.toString(rows));

    }

    private void search_duplicates() {

        if (jTable3.getRowCount() <= 0) {

        } else {

            for (int r = 0; jTable3.getRowCount() > r; r++) {

                String emp = jTable3.getValueAt(r, 0).toString();

                if (emp.equals(txt_empno.getText())) {

                    JOptionPane.showMessageDialog(rootPane, "Duplicate Entry...!");
                    txt_empno.setText("");
                }

            }

        }

    }

    private void get_saved_data() {
        try {
            txt_totalAmtl.setText("");
            if (cmb_earning_type.getSelectedIndex() == 0) {

            } else {

                String month = cmb_month.getSelectedItem().toString();
                String year = cmb_year.getSelectedItem().toString();
                String type = cmb_earning_type.getSelectedItem().toString();

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement("select * from salary_earnings where Month='" + month + "' and Year='" + year + "' and Type='" + type + "' and Location='" + txt_locCode.getText() + "' ");
                ResultSet rs = pst.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                dtm.setRowCount(0);

                while (rs.next()) {

                    Vector v = new Vector();
                    v.add(rs.getString("EMPno"));
                    v.add(rs.getString("Name"));
                    v.add(rs.getString("BSal"));
                    v.add(String.format("%.2f", rs.getDouble("Amount")));
                    v.add(rs.getString("Month"));
                    v.add(rs.getString("Year"));
                    v.add(rs.getString("RankCat"));
                    dtm.addRow(v);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void get_death_donation() {
//        try {
//            txt_totalAmtl.setText("");
//            if (cmb_deduction_type.getSelectedIndex() == 0) {
//
//            } else {
//
//                Connection con = DbConnection.getconnection();
//                PreparedStatement pst = con.prepareStatement("select * from salary_deductions_to_all where Month='" + cmb_month.getSelectedItem().toString() + "' "
//                        + "and Year='" + cmb_year.getSelectedItem().toString() + "' and RankCat='" + cmb_emp_type.getSelectedItem().toString() + "'");
//                ResultSet rs = pst.executeQuery();
//
//                while (rs.next()) {
//
//                    txt_totalAmtl.setText(rs.getString("Amount"));
//
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        txt_basic = new javax.swing.JLabel();
        cmb_earning_type = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        txt_totalAmtl = new javax.swing.JTextField();
        txt_empName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        txt_amt = new javax.swing.JTextField();
        txt_empno = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lbl_empCount = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txt_Locname = new javax.swing.JTextField();
        txt_locCode = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cmb_selectAllEmp = new javax.swing.JComboBox();
        btn_apply = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "code", "name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(23);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 110, 40));

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "emp", "name", "initials", "rank", "Basic", "OldEmp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.setRowHeight(23);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 140, 40));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Salary Earnings");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 670, 10));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_month.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_monthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 90, 25));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021", "2022", "2023" }));
        cmb_year.setSelectedIndex(3);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, 25));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel20.setText("Month & Year :");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 50, 90, 25));

        txt_basic.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        txt_basic.setText("basic");
        getContentPane().add(txt_basic, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 70, 20));

        cmb_earning_type.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_earning_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=Select EARN. Type=", "Machine Allowance", "Special Allowance", "Attendance Allowance", "Other Allowance", "Additional Shift Amount", "Poya", " " }));
        cmb_earning_type.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_earning_typePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_earning_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 280, 25));

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Location : ");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 80, 20));

        txt_totalAmtl.setEditable(false);
        txt_totalAmtl.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_totalAmtl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_totalAmtlFocusGained(evt);
            }
        });
        txt_totalAmtl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalAmtlActionPerformed(evt);
            }
        });
        txt_totalAmtl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_totalAmtlKeyTyped(evt);
            }
        });
        getContentPane().add(txt_totalAmtl, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 625, 140, 30));

        txt_empName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_empName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_empNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_empNameFocusLost(evt);
            }
        });
        txt_empName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_empNameActionPerformed(evt);
            }
        });
        txt_empName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_empNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_empNameKeyReleased(evt);
            }
        });
        getContentPane().add(txt_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 480, 25));

        jTable3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Name", "Basic Salary", "Amount", "month", "year", "RankCat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setRowHeight(25);
        jTable3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable3PropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTable3.getColumnModel().getColumn(4).setMinWidth(0);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTable3.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable3.getColumnModel().getColumn(5).setMinWidth(0);
            jTable3.getColumnModel().getColumn(5).setPreferredWidth(0);
            jTable3.getColumnModel().getColumn(5).setMaxWidth(0);
            jTable3.getColumnModel().getColumn(6).setMinWidth(0);
            jTable3.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable3.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 650, 430));

        txt_amt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_amt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_amtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_amtFocusLost(evt);
            }
        });
        txt_amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_amtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_amtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_amtKeyTyped(evt);
            }
        });
        getContentPane().add(txt_amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 100, 30));

        txt_empno.setEditable(false);
        txt_empno.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_empno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_empnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_empnoFocusLost(evt);
            }
        });
        getContentPane().add(txt_empno, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 70, 25));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 670, 10));

        jButton3.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_save_32px.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 641, 140, 60));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_minus_sign_32px.png"))); // NOI18N
        jButton4.setText("Remove Row");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 660, -1, 40));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_update_left_rotation_32px.png"))); // NOI18N
        jButton5.setText("Clear All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 660, 130, 40));

        lbl_empCount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_empCount.setText("Total Amount :");
        getContentPane().add(lbl_empCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 630, 120, 20));

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel22.setText(" Amount :");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 60, 30));

        jButton6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_print_file_32px.png"))); // NOI18N
        jButton6.setText("Earning Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 660, -1, 40));

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel25.setText("Earning Type :");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 100, 25));

        txt_Locname.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Locname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_LocnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_LocnameFocusLost(evt);
            }
        });
        txt_Locname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocnameActionPerformed(evt);
            }
        });
        txt_Locname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_LocnameKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Locname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 480, 25));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 70, 25));

        jLabel26.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel26.setText("    Employee :");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 80, 20));

        cmb_selectAllEmp.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_selectAllEmp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=SELECT=", "All Active Employees", "Having Attendance this Month " }));
        cmb_selectAllEmp.setEnabled(false);
        cmb_selectAllEmp.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_selectAllEmpPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_selectAllEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 230, 30));

        btn_apply.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_apply.setText("Apply");
        btn_apply.setEnabled(false);
        btn_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_applyActionPerformed(evt);
            }
        });
        getContentPane().add(btn_apply, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 110, 30));

        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox1.setText("Apply to :");
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 90, -1));

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel27.setText("Total Amount :");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 630, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible
        cmb_earning_type.setSelectedIndex(0);
    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
        cmb_earning_type.setSelectedIndex(0);        //  days_per_month();
    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void cmb_earning_typePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_earning_typePopupMenuWillBecomeInvisible

        if (cmb_earning_type.getSelectedIndex() == 0) {

        } else {
            get_saved_data();
        }

        get_sum();


    }//GEN-LAST:event_cmb_earning_typePopupMenuWillBecomeInvisible

    private void txt_totalAmtlFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_totalAmtlFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalAmtlFocusGained

    private void txt_totalAmtlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalAmtlActionPerformed
//        txt_rental.grabFocus();
    }//GEN-LAST:event_txt_totalAmtlActionPerformed

    private void txt_totalAmtlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalAmtlKeyTyped

        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_totalAmtl.getText().toCharArray();
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
    }//GEN-LAST:event_txt_totalAmtlKeyTyped

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable2.getSelectedRow();
            String code = jTable2.getValueAt(row, 0).toString();
            String name = jTable2.getValueAt(row, 1).toString();
            String basic = jTable2.getValueAt(row, 4).toString();

//            txt_search.setText(code);
            txt_empName.setText(name);
            txt_empno.setText(code);
            txt_basic.setText(basic);
            txt_amt.grabFocus();
            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);
            //  txt_searchKeyPressed(evt);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {

            if (jTable2.getSelectedRow() == 0) {
                jTable2.setVisible(false);
                jScrollPane4.setVisible(false);
                txt_empName.grabFocus();
            }

        }
    }//GEN-LAST:event_jTable2KeyPressed

    private void txt_empNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empNameFocusGained
        txt_empName.setBackground(Color.ORANGE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameFocusGained

    private void txt_empNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empNameFocusLost
        txt_empName.setBackground(Color.WHITE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameFocusLost

    private void txt_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTable2.getRowCount() < 1) {
            } else {
                jTable2.setRowSelectionInterval(0, 0);
                jTable2.grabFocus();
            }

            //jScrollPane4.setVisible(false);
        }
    }//GEN-LAST:event_txt_empNameKeyPressed

    private void txt_empNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER | evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

        } else {
            try {

                jTable2.setVisible(true);
                jScrollPane4.setVisible(true);
                jScrollPane4.setBounds(110, 155, 450, 200);

                Connection con = DbConnection.getconnection();

                String empno = txt_empName.getText();

                String sql = "SELECT * FROM employee_reg WHERE  FullName LIKE ? OR EmployeeNo Like? OR NameWithInitials Like?   ";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, "%" + empno + "%");
                pst.setString(2, "%" + empno + "%");
                pst.setString(3, "%" + empno + "%");
                ResultSet rst = pst.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                dtm.setRowCount(0);

                while (rst.next()) {

                    Vector v = new Vector();
                    v.add(rst.getString("EmployeeNo"));
                    v.add(rst.getString("NameWithInitials"));
                    v.add(rst.getString("FullName"));
                    v.add(rst.getString("Designation"));
                    v.add(rst.getString("BasicSalary"));

                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }//GEN-LAST:event_txt_empNameKeyReleased

    private void txt_amtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_amtFocusGained
        txt_amt.setBackground(Color.ORANGE);             // TODO add your handling code here:
    }//GEN-LAST:event_txt_amtFocusGained

    private void txt_amtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_amtFocusLost
        txt_amt.setBackground(Color.WHITE);       // TODO add your handling code here:
    }//GEN-LAST:event_txt_amtFocusLost

    private void txt_amtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amtKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            add_to_table();
            get_sum();

        }

    }//GEN-LAST:event_txt_amtKeyPressed

    private void txt_amtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_amtKeyReleased

    private void txt_empnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empnoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empnoFocusGained

    private void txt_empnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empnoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empnoFocusLost

    private void txt_empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empNameActionPerformed
        if (txt_empno.getText().isEmpty()) {

        } else {
            txt_amt.grabFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameActionPerformed

    private void txt_amtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amtKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_amt.getText().toCharArray();
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_amtKeyTyped

    private void jTable3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable3PropertyChange
        get_sum();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3PropertyChange

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear all?", null, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);

            txt_empno.setText("");
            txt_empName.setText("");
            txt_basic.setText("");
            txt_amt.setText("");
            txt_totalAmtl.setText("0.00");
            cmb_earning_type.setEnabled(true);
            jCheckBox1.setSelected(false);
            cmb_selectAllEmp.setSelectedIndex(0);
            txt_Locname.setText("");
            txt_locCode.setText("");
            lbl_empCount.setText("");

        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int nrow = jTable3.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                    dtm.removeRow(jTable3.getSelectedRow());
                }

            } else {

            }

            get_sum();

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private String get_rankType(String empno) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String rankcat = "";
        try {

            con = DbConnection.getconnection();
            pst = con.prepareStatement("select RankCategory from employee_reg where EmployeeNo='" + empno + "'");
            rs = pst.executeQuery();
            while (rs.next()) {

                rankcat = rs.getString(1);
            }

        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
            }
        }

        return rankcat;

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 
        try {

            Connection con = DbConnection.getconnection();
            con.setAutoCommit(false);

            String type = cmb_earning_type.getSelectedItem().toString();
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            String location = txt_locCode.getText();

            if (jTable3.getRowCount() < 0 | cmb_earning_type.getSelectedIndex() == 0) {
            } else {
                PreparedStatement pstdel = con.prepareStatement("delete from salary_earnings where Month='" + month + "' "
                        + "and Year ='" + year + "' and Type='" + type + "' and Location='" + location + "'");
                pstdel.executeUpdate();

                PreparedStatement pst = con.prepareStatement("insert into salary_earnings (EMPno,Type,Amount,Month,Year,Name,BSal,Status,RankCat,Location) values(?,?,?,?,?,?,?,?,?,?)");
//                EMPno,Type,Amount,Month,Year,Name,BSal,Status,Note
// String type = cmb_deduction_type.getSelectedItem().toString();

                for (int r = 0; jTable3.getRowCount() > r; r++) {

                    String emp = jTable3.getValueAt(r, 0).toString();
                    String name = jTable3.getValueAt(r, 1).toString();
                    String bsal = jTable3.getValueAt(r, 2).toString();
                    String amt = jTable3.getValueAt(r, 3).toString();
                    String rankCategory = jTable3.getValueAt(r, 6).toString();

                    pst.setString(1, emp);
                    pst.setString(2, type);
                    pst.setDouble(3, Double.parseDouble(amt));
                    pst.setString(4, month);
                    pst.setString(5, year);
                    pst.setString(6, name);
                    pst.setString(7, bsal);
                    pst.setString(8, "on-going");
                    pst.setString(9, rankCategory);
                    pst.setString(10, location);
                    pst.addBatch();
                }

                pst.executeBatch();

                con.commit();

                JOptionPane.showMessageDialog(rootPane, "Salary Earnings Saved..!");

                cmb_earning_type.setEnabled(true);

                DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                dtm.setRowCount(0);
                txt_totalAmtl.setText("");
                txt_amt.setText("");
                jCheckBox1.setSelected(false);
                cmb_selectAllEmp.setSelectedIndex(0);
                lbl_empCount.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error");
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void print() {

        String employeeType = "%a%";
        String type = cmb_earning_type.getSelectedItem().toString();
        String month = cmb_month.getSelectedItem().toString();
        String year = cmb_year.getSelectedItem().toString();
        String location = txt_locCode.getText();

        HashMap hm = new HashMap();
        hm.put("month", month);
        hm.put("year", year);
        hm.put("locCode", location);
        hm.put("deductionType", type);
        hm.put("rankCat", employeeType);

        String path = "Reports\\Salary_Earning_Report_Monthly.jrxml";
        String title = "Monthly Salary Earning Report";
        ReportView rv = new ReportView();
        
        rv.ReportView(path, hm, title);

    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        print();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_LocnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocnameFocusGained
        txt_Locname.setBackground(Color.ORANGE);             // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameFocusGained

    private void txt_LocnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocnameActionPerformed
        if (txt_Locname.getText().isEmpty()) {

        } else {
            txt_empName.grabFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameActionPerformed

    private void txt_LocnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameKeyPressed

    private void txt_LocnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocnameKeyReleased

        if (txt_Locname.getText().isEmpty()) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);
        } else {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    jTable1.grabFocus();
                    break;
                case KeyEvent.VK_ENTER:
                    break;
                default:
                    try {

                        jTable1.setVisible(true);
                        jScrollPane1.setVisible(true);
                        jScrollPane1.setBounds(110, 115, 400, 200);

                        Connection con = DbConnection.getconnection();

                        String empno = txt_Locname.getText();

                        String sql = "SELECT * FROM location_reg WHERE  LocCode LIKE ? OR LocName Like? ORDER BY LocCode  ";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, "%" + empno + "%");
                        pst.setString(2, "%" + empno + "%");
                        ResultSet rst = pst.executeQuery();

                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                        dtm.setRowCount(0);

                        while (rst.next()) {

                            Vector v = new Vector();
                            v.add(rst.getString("LocCode"));
                            v.add(rst.getString("LocName"));

                            dtm.addRow(v);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
                    break;
            }
        }

    }//GEN-LAST:event_txt_LocnameKeyReleased

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusGained

    private void txt_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusLost

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable1.getSelectedRow();
            String code = jTable1.getValueAt(row, 0).toString();
            String name = jTable1.getValueAt(row, 1).toString();

            txt_Locname.setText(name);
            txt_locCode.setText(code);
            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);
            txt_empName.grabFocus();
            get_saved_data();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);
            txt_Locname.grabFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int row = jTable1.getSelectedRow();
            if (row == 0) {
                txt_Locname.grabFocus();
                jTable1.setVisible(false);
                jScrollPane1.setVisible(false);
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void cmb_selectAllEmpPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_selectAllEmpPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_selectAllEmpPopupMenuWillBecomeInvisible

    private void btn_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_applyActionPerformed
        if (txt_amt.getText().isEmpty() | cmb_selectAllEmp.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Invalid Input!");
        } else {
            @SuppressWarnings("CallToPrintStackTrace")
            Thread hilo = new Thread(() -> {
                Connection con = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql = null;
                String month = cmb_month.getSelectedItem().toString();
                String year = cmb_year.getSelectedItem().toString();
                String location = txt_locCode.getText();
                try {
                    switch (cmb_selectAllEmp.getSelectedIndex()) {
                        case 1: // All Active Employees
                            sql = "Select EmployeeNo, NameWithInitials, BasicSalary from employee_reg where DefLocation='" + location + "' and isResigned='0' ";
                            break;
                        case 2: // Employees Having Attendance in selected Month
                            sql = "SELECT EmployeeNo, NameWithInitials, BasicSalary FROM `emp_atten_summery` JOIN employee_reg ON emp_atten_summery.EMPno=EmployeeNo \n"
                                    + "WHERE Loc='" + location + "' AND MONTH='" + month + "' AND YEAR='" + year + "' GROUP BY emp_atten_summery.EMPno";
                            break;
                        default:
                            break;
                    }
                    con = DbConnection.getconnection();
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                    dtm.setRowCount(0);
                    Double amt = Double.parseDouble(txt_amt.getText());
                    while (rs.next()) {
                        String emp = rs.getString(1);
                        String name1 = rs.getString(2);
                        String sal = rs.getString(3);
                        String RankType = get_rankType(emp);
                        System.out.println(RankType);

                        Vector v = new Vector();
                        v.add(emp);
                        v.add(name1);
                        v.add(sal);
                        v.add(String.format("%.2f", amt));
                        v.add(month);
                        v.add(year);
                        v.add(RankType);
                        dtm.addRow(v);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                get_sum();
            });
            hilo.start();
        }
    }//GEN-LAST:event_btn_applyActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked

        if (jCheckBox1.isSelected()) {
            if (txt_amt.getText().isEmpty()) {
                cmb_selectAllEmp.setEnabled(false);
                btn_apply.setEnabled(false);
            } else {
                cmb_selectAllEmp.setEnabled(true);
                btn_apply.setEnabled(true);
            }

        } else {
            cmb_selectAllEmp.setEnabled(false);
            btn_apply.setEnabled(false);

        }

    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        if (jCheckBox1.isSelected()) {
            if (txt_amt.getText().isEmpty()) {
                cmb_selectAllEmp.setEnabled(false);
                btn_apply.setEnabled(false);
            } else {
                cmb_selectAllEmp.setEnabled(true);
                btn_apply.setEnabled(true);
            }

        } else {
            cmb_selectAllEmp.setEnabled(false);
            btn_apply.setEnabled(false);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void txt_LocnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocnameFocusLost
        txt_Locname.setBackground(Color.WHITE);             // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameFocusLost

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
            java.util.logging.Logger.getLogger(Salary_Earnings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salary_Earnings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salary_Earnings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salary_Earnings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salary_Earnings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_apply;
    private javax.swing.JComboBox cmb_earning_type;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_selectAllEmp;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbl_empCount;
    private static javax.swing.JTextField txt_Locname;
    public static javax.swing.JTextField txt_amt;
    private javax.swing.JLabel txt_basic;
    public static javax.swing.JTextField txt_empName;
    private javax.swing.JTextField txt_empno;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_totalAmtl;
    // End of variables declaration//GEN-END:variables
}
