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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class Loan_DetailsNEW extends javax.swing.JFrame {

    /**
     * Creates new form Loan_Details
     */
//    int userID = 0;
//    String moduleID = "";
//int LoggeduserId, String ModuleID
    public Loan_DetailsNEW() {
        initComponents();

//        userID = LoggeduserId;
//        moduleID = ModuleID;
//        get_rights();
        TitleBar();
        jTable2.getTableHeader().setUI(null);

        //jScrollPane1.setVisible(false);
        jScrollPane4.setVisible(false);
    }

//    public Loan_DetailsNEW() {
//
//    }
//    private void get_rights() {
//
//        UserAccessCheck UAC = new UserAccessCheck(userID, moduleID);
//
//        if (UAC.UpdateRights == 1) {
//            btn_update.setEnabled(true);
//
//        } else {
//            btn_update.setEnabled(false);
//
//        }
//
//    }
    private void TitleBar() {

        this.setTitle("Issued Loan Details");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void Employee_auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg   ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_empName);

            while (rs.next()) {
                String code = rs.getString("EPFno");
                String NameWithInitials = rs.getString("NameWithInitials");

                ta.addItem(code);
                ta.addItem(NameWithInitials);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get_loan_details() {

        if (txt_empid.getText().isEmpty()) {

        } else {

            Connection con = null;
            PreparedStatement pst = null;

            try {

                con = DbConnection.getconnection();
                String sql = "";

                if (cmb_deduction_type.getSelectedIndex() == 0) {
                    sql = "select * from salary_deductions_summery where EMPno='" + txt_empid.getText() + "' and Status='" + cmb_loanStatus.getSelectedItem().toString() + "'";
                } else {
                    sql = "select * from salary_deductions_summery where EMPno='" + txt_empid.getText() + "' and Type='" + cmb_deduction_type.getSelectedItem().toString() + "' and Status='" + cmb_loanStatus.getSelectedItem().toString() + "'";
                }

                pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel dt = (DefaultTableModel) jTable_Loan_details.getModel();
                dt.setRowCount(0);

                DefaultTableModel dt1 = (DefaultTableModel) jTable_Installments.getModel();
                dt1.setRowCount(0);

                while (rs.next()) {

                    String empID = rs.getString("EMPno");
                    String name = "";
                    pst = con.prepareStatement("select * from employee_reg where EmployeeNo='" + empID + "'  ");
                    ResultSet rs1 = pst.executeQuery();
                    while (rs1.next()) {
                        name = rs1.getString("NameWithInitials");
                    }

                    Vector v = new Vector();
                    v.add(empID);
                    v.add(name);
                    v.add(rs.getString("Type"));
                    v.add(rs.getString("LoanAmount"));
                    v.add(rs.getString("Installments"));
                    v.add(rs.getString("Interest"));
                    v.add(rs.getString("InstallAmount"));
                    v.add(rs.getString("IssueDate"));
                    v.add(rs.getString("Status"));
                    v.add(rs.getString("RefNo"));
                    dt.addRow(v);

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                try {
                    
                    pst.close();
                    con.close();
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_loanStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Installments = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Loan_details = new javax.swing.JTable();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txt_status = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_loan_ref = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cmb_ChangeStatus = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmb_deduction_type = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txt_empName = new javax.swing.JTextField();
        txt_empid = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "emp", "name", "initials", "rank", "Basic"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(290);
            jTable2.getColumnModel().getColumn(2).setMinWidth(0);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(0);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 140, 20));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel1.setText("Loan ' Status ' Change");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 180, 30));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Loan Status  :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, 20));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Loan Type :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 80, 20));

        cmb_loanStatus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_loanStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Settled", "On-Going", "Hold", "Delete" }));
        getContentPane().add(cmb_loanStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 90, -1));

        jTable_Installments.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable_Installments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Amount", "Month", "Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Installments.setRowHeight(23);
        jScrollPane1.setViewportView(jTable_Installments);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 380, 200));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_search_database_32px.png"))); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 120, 40));

        filler1.setBackground(new java.awt.Color(102, 102, 102));
        filler1.setForeground(new java.awt.Color(102, 102, 102));
        filler1.setOpaque(true);
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 40, 1, 440));

        jTable_Loan_details.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable_Loan_details.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Emp Name", "Loan Type", "Loan Amount", "Installments", "Int. Rate", "Mothly Amount", "Issue Date", "Status", "Ref"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Loan_details.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable_Loan_details.setRowHeight(25);
        jTable_Loan_details.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Loan_detailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Loan_details);
        if (jTable_Loan_details.getColumnModel().getColumnCount() > 0) {
            jTable_Loan_details.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable_Loan_details.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable_Loan_details.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable_Loan_details.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable_Loan_details.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable_Loan_details.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTable_Loan_details.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable_Loan_details.getColumnModel().getColumn(7).setPreferredWidth(100);
            jTable_Loan_details.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTable_Loan_details.getColumnModel().getColumn(9).setMinWidth(0);
            jTable_Loan_details.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTable_Loan_details.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 620, 320));
        getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 1030, 10));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Issued Loan Managment");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, 40));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_update_left_rotation_32px.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, -1, -1));
        getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1020, 10));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel3.setText("Installments Details");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 230, 30));

        txt_status.setEditable(false);
        txt_status.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 360, 70, -1));

        btn_update.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_save_as_32px.png"))); // NOI18N
        btn_update.setText("Change");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 420, -1, -1));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Loan Reference :-");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, -1, 20));

        txt_loan_ref.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_loan_ref, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, 150, -1));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setText("Set As :-");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, -1, 20));

        cmb_ChangeStatus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_ChangeStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  ", "Hold", "settled", "on-going", "Delete" }));
        getContentPane().add(cmb_ChangeStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 430, 140, 25));

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel14.setText("Current Status :-");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, -1, 20));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Change Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 12))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 390, 80));

        cmb_deduction_type.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_deduction_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=ALL=", "Uniform", "Welfare Loan", "Special Advance", "Insuarance", "Certificate Deposit", "Security Fund" }));
        cmb_deduction_type.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_deduction_typePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_deduction_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 300, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText("Employee  :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 50, 80, 20));

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
        getContentPane().add(txt_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 320, 23));

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_empid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_empidFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_empidFocusLost(evt);
            }
        });
        txt_empid.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_empidPropertyChange(evt);
            }
        });
        getContentPane().add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 60, 23));

        jSeparator12.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 400, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//    private void search_loan() {
//
//        try {
//
//            Connection con = DbConnection.getconnection();
//            PreparedStatement pst;
//            ResultSet rs;
//            String sql = null;
//
//            String loan_type = null;
//            String loan_stat = null;
//
//            if (cmb_loanType.getSelectedIndex() == 0) {
//                loan_type = "distress_loan";
//            } else {
//                loan_type = "distress_loan_02";
//            }
//
//            if (cmb_loanStatus.getSelectedIndex() == 0) {
//                loan_stat = "settled";
//            }
//
//            if (cmb_loanStatus.getSelectedIndex() == 1) {
//                loan_stat = "on-going";
//            }
//            if (cmb_loanStatus.getSelectedIndex() == 2) {
//                loan_stat = "HOLD";
//            }
//
//            if (jCheckBox1.isSelected()) {//all_employee
//
//                if (cb_date_all.isSelected()) {//all_date
//
//                    sql = "select * from  `" + loan_type + "` where Status='" + loan_stat + "'";
//
//                } else {//selected_date
//
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                    if (date_from.getDate() == null | date_from.getDate() == null) {
//
//                    } else {
//
//                        String from = sdf.format(date_from.getDate());
//                        String to = sdf.format(date_to.getDate());
//                        sql = "select * from  `" + loan_type + "` where Status='" + loan_stat + "' and IssueOn between '" + from + "' and '" + to + "'";
//
//                    }
//
//                }
//
//            } else {//selected_employee
//
//                if (cb_date_all.isSelected()) {//all_date
//
//                    sql = "select * from  `" + loan_type + "` where EPFno='" + txt_empid.getText() + "' and Status='" + loan_stat + "'";
//
//                } else {//selected_date
//
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                    if (date_from.getDate() == null | date_from.getDate() == null) {
//
//                    } else {
//
//                        String from = sdf.format(date_from.getDate());
//                        String to = sdf.format(date_to.getDate());
//                        sql = "select * from  `" + loan_type + "` where EPFno='" + txt_empid.getText() + "' and Status='" + loan_stat + "' and IssueOn between '" + from + "' and '" + to + "'";
//
//                    }
//
//                }
//
//            }
//
//            pst = con.prepareStatement(sql);
//
//            rs = pst.executeQuery();
//
//            DefaultTableModel dtm = (DefaultTableModel) jTable_Loan_details.getModel();
//            dtm.setRowCount(0);
//
//            while (rs.next()) {
//
//                Vector v = new Vector();
//
//                v.add(rs.getString("LoanReference"));
//                v.add(rs.getString("LoanAmount"));
//                v.add(rs.getString("Installments"));
//                v.add(rs.getString("Rental"));
//                v.add(rs.getString("IssueOn"));
//                v.add(rs.getString("LastInstallment"));
//
//                dtm.addRow(v);
//
//            }
//
//            cmb_loanStatus.setEnabled(false);
//            cmb_loanType.setEnabled(false);
//            txt_emp_name.setEnabled(false);
//            jButton1.setEnabled(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(rootPane, e);
//
//        }
//
//    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        get_loan_details();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cmb_loanStatus.setEnabled(true);

        txt_empName.setEnabled(true);
        txt_loan_ref.setText("");
        txt_status.setText("");
//        cb_allEmp.setSelected(false);

        jButton1.setEnabled(true);

        DefaultTableModel dtm = (DefaultTableModel) jTable_Loan_details.getModel();
        dtm.setRowCount(0);

        DefaultTableModel dtm1 = (DefaultTableModel) jTable_Installments.getModel();
        dtm1.setRowCount(0);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable_Loan_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Loan_detailsMouseClicked
        if (evt.getClickCount() >= 1) {

            int row = jTable_Loan_details.getRowCount();
            DefaultTableModel dtm1 = (DefaultTableModel) jTable_Installments.getModel();
            dtm1.setRowCount(0);
            txt_loan_ref.setText("");
            txt_status.setText("");
            if (row > 0) {
                int get_row = jTable_Loan_details.getSelectedRow();
                String ref = jTable_Loan_details.getModel().getValueAt(get_row, 9).toString();
                String stat = jTable_Loan_details.getModel().getValueAt(get_row, 8).toString();
                String loan_type = null;
                txt_loan_ref.setText(ref);
                txt_status.setText(stat);

                try {

                    Connection con = DbConnection.getconnection();
                    PreparedStatement pstt = null;
                    ResultSet rst = null;

                    String sql = "select * from salary_deductions where RefNo='" + ref + "' order by Line ";

                    pstt = con.prepareStatement(sql);
                    rst = pstt.executeQuery();

                    DefaultTableModel dtm = (DefaultTableModel) jTable_Installments.getModel();
                    dtm.setRowCount(0);

                    while (rst.next()) {

                        String month = rst.getString("Month");
                        String year = rst.getString("Year");

                        Vector v = new Vector();

                        v.add(rst.getString("Amount"));

                        v.add(month);
                        v.add(year);

                        dtm.addRow(v);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_Loan_detailsMouseClicked

    private void status_change() {

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

                        if (cat.equals("system_admin") | cat.equals("admin") | cat.equals("manager")) {

//update query begins*************************************************************************************************
                            Connection con = DbConnection.getconnection();
                            PreparedStatement pst;

                            if (cmb_ChangeStatus.getSelectedIndex() == 0) {

                            } else {
                                String sql = "update salary_deductions_summery set   Status='" + cmb_ChangeStatus.getSelectedItem().toString() + "' where RefNo='" + txt_loan_ref.getText() + "'";
                                pst = con.prepareStatement(sql);
                                pst.executeUpdate();

                                String sql1 = "update salary_deductions set   Status='" + cmb_ChangeStatus.getSelectedItem().toString() + "' where RefNo='" + txt_loan_ref.getText() + "'";
                                pst = con.prepareStatement(sql1);
                                pst.executeUpdate();

                                JOptionPane.showMessageDialog(rootPane, "Loan Ref: " + txt_loan_ref.getText() + "  Status Changed...!");
                                txt_loan_ref.setText("");
                                txt_status.setText("");

                                DefaultTableModel dtm = (DefaultTableModel) jTable_Loan_details.getModel();
                                dtm.setRowCount(0);

                                DefaultTableModel dtm1 = (DefaultTableModel) jTable_Installments.getModel();
                                dtm1.setRowCount(0);

                            }

                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation ( Admin & Managment Staff Only )");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        status_change();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateActionPerformed

    private void cmb_deduction_typePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_deduction_typePopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_deduction_typePopupMenuWillBecomeInvisible

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable2.getSelectedRow();
            String code = jTable2.getValueAt(row, 0).toString();
            String name = jTable2.getValueAt(row, 1).toString();
//            String basic = jTable2.getValueAt(row, 4).toString();
//            String rank = jTable2.getValueAt(row, 3).toString();

            //            txt_search.setText(code);
            txt_empName.setText(name);
            txt_empid.setText(code);
//            txt_basic.setText(basic);
//            txt_rank.setText(rank);
            txt_empName.grabFocus();
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

    private void txt_empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameActionPerformed

    private void txt_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // get_loanDetals();
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
                jScrollPane4.setBounds(110, 74, 450, 200);

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

    private void txt_empidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empidFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empidFocusGained

    private void txt_empidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empidFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empidFocusLost

    private void txt_empidPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_empidPropertyChange

    }//GEN-LAST:event_txt_empidPropertyChange

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
            java.util.logging.Logger.getLogger(Loan_DetailsNEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loan_DetailsNEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loan_DetailsNEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loan_DetailsNEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loan_DetailsNEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_ChangeStatus;
    private javax.swing.JComboBox cmb_deduction_type;
    private javax.swing.JComboBox<String> cmb_loanStatus;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable_Installments;
    private javax.swing.JTable jTable_Loan_details;
    public static javax.swing.JTextField txt_empName;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_loan_ref;
    private javax.swing.JTextField txt_status;
    // End of variables declaration//GEN-END:variables
}
