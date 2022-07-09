/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.MAIN.ONGOING;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Loan extends javax.swing.JFrame {

    /**
     * Creates new form Salary_Deductions
     */
    public Loan() {
        initComponents();

        jTable2.getTableHeader().setUI(null);
        jTable1.getTableHeader().setUI(null);
        jScrollPane1.setVisible(false);
        jScrollPane4.setVisible(false);
        TitleBar();
    }

//
//    private void get_rights() {
//
//        UserAccessCheck UAC = new UserAccessCheck(userID, moduleID);
//
//        if (UAC.SaveRights == 1) {
//            btn_save.setEnabled(true);
//        } else {
//            btn_save.setEnabled(false);
//
//        }
//
//    }
    private void TitleBar() {

        this.setTitle("Loan Entry");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void rentals() {
        if (txt_empno.getText().isEmpty() | txt_loan_amt.getText().isEmpty() | txt_period.getText().isEmpty() | txt_RepayAmt.getText().isEmpty() | jDateChooser1.getDate() == null) {

            //txt_empno.setBackground(Color.red);
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser1.getDate());

            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            System.out.println(year + " " + month);

            int period = Integer.parseInt(txt_period.getText());
            int m = Integer.parseInt(month);

            int y = Integer.parseInt(year);
            int new_m = 0;
            int new_y = y;
            System.out.println("m:" + m + " y:" + y);

            DefaultTableModel dtm = (DefaultTableModel) tbl_installments.getModel();
            dtm.setRowCount(0);

            for (int x = 0; x < period; x++) {

                if (new_m == 0) {
                    new_m = m + x;
                } else {
                    new_m = m + 1;
                }

                if (new_y == y) {
                    new_y = y;
                } else {
                    new_y = y + 1;
                }

                if (new_m > 12) {
                    new_y = y + 1;
                    new_m = new_m - 12;

                }
                y = new_y;
                m = new_m;
                System.out.println("m: " + m);
                System.out.println("x: " + x);
                System.out.println("repayment " + new_y + " " + new_m);

                String Smonth = "";
                if (new_m == 1) {
                    Smonth = "January";
                }
                if (new_m == 2) {
                    Smonth = "February";
                }
                if (new_m == 3) {
                    Smonth = "March";
                }
                if (new_m == 4) {
                    Smonth = "April";
                }
                if (new_m == 5) {
                    Smonth = "May";
                }
                if (new_m == 6) {
                    Smonth = "June";
                }
                if (new_m == 7) {
                    Smonth = "July";
                }
                if (new_m == 8) {
                    Smonth = "August";
                }
                if (new_m == 9) {
                    Smonth = "September";
                }
                if (new_m == 10) {
                    Smonth = "October";
                }
                if (new_m == 11) {
                    Smonth = "November";
                }
                if (new_m == 12) {
                    Smonth = "December";
                }
                Vector v = new Vector();
                v.add(Smonth);
                v.add(Integer.toString(new_y));
                v.add(txt_install.getText());
                dtm.addRow(v);

            }
        }
    }

    private static String LoanRefNo = "";

    private void get_loanDetals() {

        if (txt_empno.getText().isEmpty() | cmb_deduction_type.getSelectedIndex() == 0) {

        } else {

            try {

                Connection con = DbConnection.getconnection();
                DefaultTableModel dtm = (DefaultTableModel) tbl_loandetails.getModel();
                dtm.setRowCount(0);
                String sql = null;

//                if (jCheckBox1.isSelected()) {
                sql = "select * from salary_deductions_summery  where Status='on-going' and EMPno='" + txt_empno.getText() + "' and Type='" + cmb_deduction_type.getSelectedItem().toString() + "' ";
//                } else {

                // sql = "select * from salary_deductions_summery   where Status='on-going' and EMPno='" + txt_empno.getText() + "'  Order by Line";
//                }
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {

                    String refno = rs.getString("RefNo");
                    LoanRefNo = refno;
                    System.out.println(refno);

                    pst = con.prepareStatement("select *,SUM(Amount) from salary_deductions where Status='on-going' and RefNo='" + refno + "' ");
                    ResultSet rs1 = pst.executeQuery();
                    while (rs1.next()) {
                        txt_Outstanding.setText(Double.toString(rs1.getDouble("SUM(Amount)")));
                    }

                    String month = null;
                    String year = null;
                    pst = con.prepareStatement("select  MAX(Line) from salary_deductions where Status='on-going' and EMPno='" + txt_empno.getText() + "' and Type='" + cmb_deduction_type.getSelectedItem().toString() + "' and RefNo='" + refno + "' ");
                    ResultSet rs2 = pst.executeQuery();
                    while (rs2.next()) {

                        int max = rs2.getInt("MAX(Line)");

                        pst = con.prepareStatement("select  * from salary_deductions where Line=" + max + " ");
                        ResultSet rs3 = pst.executeQuery();
                        while (rs3.next()) {
                            month = rs3.getString("Month");
                            year = rs3.getString("Year");
                        }

                    }

                    Vector v = new Vector();
                    v.add(rs.getString(2));//type
                    v.add(rs.getString(3));//loan amt
                    v.add(rs.getString(4));//installments
                    v.add(rs.getString(5));//renatl
                    v.add(month + " " + year);//lastmonth
                    dtm.addRow(v);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void get_All_loanDetals() {

        if (txt_empno.getText().isEmpty() | cmb_deduction_type.getSelectedIndex() == 0) {

        } else {

            try {

                Connection con = DbConnection.getconnection();
                DefaultTableModel dtm = (DefaultTableModel) tbl_loandetails.getModel();
                dtm.setRowCount(0);
                String sql = null;

//                if (jCheckBox1.isSelected()) {
                sql = "select * from salary_deductions_summery  where Status='on-going' and EMPno='" + txt_empno.getText() + "'  ";
//                } else {

                // sql = "select * from salary_deductions_summery   where Status='on-going' and EMPno='" + txt_empno.getText() + "'  Order by Line";
//                }
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {

                    String refno = rs.getString("RefNo");
                    String type = rs.getString("Type");
                    LoanRefNo = refno;
                    System.out.println(refno);

//                    pst = con.prepareStatement("select *,SUM(Amount) from salary_deductions where Status='on-going' and RefNo='" + refno + "' ");
//                    ResultSet rs1 = pst.executeQuery();
//                    while (rs1.next()) {
//                        txt_Outstanding.setText(Double.toString(rs1.getDouble("SUM(Amount)")));
//                    }
                    String month = null;
                    String year = null;
                    pst = con.prepareStatement("select  MAX(Line) from salary_deductions where Status='on-going' and EMPno='" + txt_empno.getText() + "' and Type='" + type + "' and RefNo='" + refno + "' ");
                    ResultSet rs2 = pst.executeQuery();
                    while (rs2.next()) {

                        int max = rs2.getInt("MAX(Line)");

                        pst = con.prepareStatement("select  * from salary_deductions where Line=" + max + " ");
                        ResultSet rs3 = pst.executeQuery();
                        while (rs3.next()) {
                            month = rs3.getString("Month");
                            year = rs3.getString("Year");
                        }

                    }

                    Vector v = new Vector();
                    v.add(rs.getString(2));//type
                    v.add(rs.getString(3));//loan amt
                    v.add(rs.getString(4));//installments
                    v.add(rs.getString(5));//renatl
                    v.add(month + " " + year);//lastmonth
                    dtm.addRow(v);

                }

            } catch (Exception e) {
                e.printStackTrace();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        cmb_deduction_type = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        txt_loan_amt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_installments = new javax.swing.JTable();
        txt_int_rate = new javax.swing.JTextField();
        txt_empno = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        btn_save = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        txt_basic = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_empno3 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_RepayAmt = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_period = new javax.swing.JTextField();
        txt_install = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_empName = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_loandetails = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel42 = new javax.swing.JLabel();
        txt_Outstanding = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txt_FullTotal = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_previouslySaved = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();

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
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable2.getColumnModel().getColumn(2).setMinWidth(0);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 140, 20));

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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 230, 20));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("  %");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 30, 23));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Loan Entry");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 520, 10));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText(":");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 10, 20));

        cmb_deduction_type.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cmb_deduction_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "==SELECT==", "Loan 01", "Loan 02", "Uniform", "Fines", " " }));
        cmb_deduction_type.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_deduction_typePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_deduction_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 320, 25));

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel23.setText("Employee             ");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, 20));

        txt_loan_amt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_loan_amt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_loan_amtFocusGained(evt);
            }
        });
        txt_loan_amt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_loan_amtActionPerformed(evt);
            }
        });
        txt_loan_amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_loan_amtKeyTyped(evt);
            }
        });
        getContentPane().add(txt_loan_amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 120, 25));

        tbl_installments.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_installments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Month", "Year", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_installments.setRowHeight(20);
        jScrollPane2.setViewportView(tbl_installments);
        if (tbl_installments.getColumnModel().getColumnCount() > 0) {
            tbl_installments.getColumnModel().getColumn(0).setPreferredWidth(120);
            tbl_installments.getColumnModel().getColumn(1).setPreferredWidth(80);
            tbl_installments.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 250, 300));

        txt_int_rate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_int_rate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_int_rateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_int_rateFocusLost(evt);
            }
        });
        txt_int_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_int_rateActionPerformed(evt);
            }
        });
        txt_int_rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_int_rateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_int_rateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_int_rateKeyTyped(evt);
            }
        });
        getContentPane().add(txt_int_rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 60, 25));

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
        txt_empno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_empnoPropertyChange(evt);
            }
        });
        getContentPane().add(txt_empno, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 60, 23));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 10, 510));

        btn_save.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_save_32px.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 250, 50));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_add_32px_1.png"))); // NOI18N
        jButton4.setText(" Add Installments");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 240, 50));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_update_left_rotation_32px.png"))); // NOI18N
        jButton5.setText("Clear All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 140, 50));

        jLabel24.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel24.setText("Rank ");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_rank.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rankFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rankFocusLost(evt);
            }
        });
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 50, 23));

        txt_basic.setEditable(false);
        txt_basic.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_basic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_basicFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_basicFocusLost(evt);
            }
        });
        getContentPane().add(txt_basic, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 70, 23));

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel25.setText("Basic Salary :");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 20));

        jLabel26.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel26.setText("Welfare Saving:");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, 20));

        txt_empno3.setEditable(false);
        txt_empno3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_empno3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_empno3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_empno3FocusLost(evt);
            }
        });
        getContentPane().add(txt_empno3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 60, 23));

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel22.setText("Loan Amount     ");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 120, 20));

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel27.setText("Repayment Amt.   :");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, 20));

        jLabel28.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel28.setText("Interest Rate       ");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 120, 20));

        txt_RepayAmt.setEditable(false);
        txt_RepayAmt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_RepayAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_RepayAmtFocusGained(evt);
            }
        });
        txt_RepayAmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RepayAmtActionPerformed(evt);
            }
        });
        txt_RepayAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RepayAmtKeyTyped(evt);
            }
        });
        getContentPane().add(txt_RepayAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 120, 25));

        jLabel29.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel29.setText("Installment Amt.");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 120, 20));

        jLabel30.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel30.setText("1st Instll. Month.  ");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 120, 20));

        jLabel31.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel31.setText("Repayment Period ");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 120, 20));

        jLabel32.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        jLabel32.setText("Month(s)");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 60, 20));

        txt_period.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_period.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_periodFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_periodFocusLost(evt);
            }
        });
        txt_period.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_periodActionPerformed(evt);
            }
        });
        txt_period.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_periodKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_periodKeyTyped(evt);
            }
        });
        getContentPane().add(txt_period, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 60, 25));

        txt_install.setEditable(false);
        txt_install.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_install.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_installFocusGained(evt);
            }
        });
        txt_install.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_installActionPerformed(evt);
            }
        });
        txt_install.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_installKeyTyped(evt);
            }
        });
        getContentPane().add(txt_install, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 120, 25));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 120, 23));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel20.setText("On Going Loan Details :");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 150, 20));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText(":");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 10, 20));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel34.setText(":");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 10, 20));

        jLabel35.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel35.setText(":");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 10, 20));

        jLabel36.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel36.setText(":");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 10, 20));

        jLabel37.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel37.setText(":");
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 10, 20));

        jLabel38.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel38.setText(":");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 10, 20));

        jLabel39.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel39.setText(":");
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 10, 20));

        jLabel40.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel40.setText(":");
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 10, 20));

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
        getContentPane().add(txt_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 320, 23));

        tbl_loandetails.setAutoCreateRowSorter(true);
        tbl_loandetails.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_loandetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loan Type", "Loan Amt.", "Ins.", "Monthly Amt.", "Last Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_loandetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_loandetails.setRowHeight(25);
        jScrollPane3.setViewportView(tbl_loandetails);
        if (tbl_loandetails.getColumnModel().getColumnCount() > 0) {
            tbl_loandetails.getColumnModel().getColumn(0).setPreferredWidth(120);
            tbl_loandetails.getColumnModel().getColumn(1).setPreferredWidth(80);
            tbl_loandetails.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbl_loandetails.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbl_loandetails.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbl_loandetails.getColumnModel().getColumn(4).setHeaderValue("Last Month");
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, 200));

        jLabel41.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel41.setText("Loan Type ");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jCheckBox1.setText("Show All on-going Loan Details of this Employee ");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, -1, -1));

        jLabel42.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel42.setText("Outstanding Amt.");
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 120, 20));

        txt_Outstanding.setEditable(false);
        txt_Outstanding.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Outstanding.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_OutstandingFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_OutstandingFocusLost(evt);
            }
        });
        txt_Outstanding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OutstandingActionPerformed(evt);
            }
        });
        txt_Outstanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_OutstandingKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_OutstandingKeyTyped(evt);
            }
        });
        getContentPane().add(txt_Outstanding, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 120, 25));

        jLabel43.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel43.setText(":");
        getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 10, 20));

        jLabel44.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel44.setText("Total  Amt.");
        getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 120, 20));

        txt_FullTotal.setEditable(false);
        txt_FullTotal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_FullTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_FullTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_FullTotalFocusLost(evt);
            }
        });
        txt_FullTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_FullTotalActionPerformed(evt);
            }
        });
        txt_FullTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_FullTotalKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_FullTotalKeyTyped(evt);
            }
        });
        getContentPane().add(txt_FullTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 120, 25));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 990, 10));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 470, 10));

        tbl_previouslySaved.setAutoCreateRowSorter(true);
        tbl_previouslySaved.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_previouslySaved.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Loan Type", "Loan Amt.", "Ins.", "Monthly Amt."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_previouslySaved.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_previouslySaved.setRowHeight(25);
        jScrollPane5.setViewportView(tbl_previouslySaved);
        if (tbl_previouslySaved.getColumnModel().getColumnCount() > 0) {
            tbl_previouslySaved.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbl_previouslySaved.getColumnModel().getColumn(1).setPreferredWidth(120);
            tbl_previouslySaved.getColumnModel().getColumn(2).setPreferredWidth(80);
            tbl_previouslySaved.getColumnModel().getColumn(3).setPreferredWidth(50);
            tbl_previouslySaved.getColumnModel().getColumn(4).setPreferredWidth(120);
        }

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, -1, 220));

        jLabel45.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel45.setText("Previously Saved Loans :");
        getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 150, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_deduction_typePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_deduction_typePopupMenuWillBecomeInvisible
        jDateChooser1.setDate(null);
        LoanRefNo = "";
    }//GEN-LAST:event_cmb_deduction_typePopupMenuWillBecomeInvisible

    private void txt_loan_amtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_loan_amtFocusGained
        get_loanDetals();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_loan_amtFocusGained

    private void txt_loan_amtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_loan_amtActionPerformed
        txt_int_rate.grabFocus();
    }//GEN-LAST:event_txt_loan_amtActionPerformed

    private void txt_loan_amtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_loan_amtKeyTyped

        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_loan_amt.getText().toCharArray();
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
    }//GEN-LAST:event_txt_loan_amtKeyTyped

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable1.getSelectedRow();
            String code = jTable1.getValueAt(row, 0).toString();
            String name = jTable1.getValueAt(row, 1).toString();

//            txt_Locname_AttnEntry.setText(name);
//            txt_locCode.setText(code);
            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int row = jTable1.getSelectedRow();
            if (row == 0) {
//                txt_Locname_AttnEntry.grabFocus();
                jTable1.setVisible(false);
                jScrollPane1.setVisible(false);
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void txt_int_rateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_int_rateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_int_rateFocusGained

    private void txt_int_rateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_int_rateFocusLost
        get_TotalAmount();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_int_rateFocusLost

    private void txt_int_rateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_int_rateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //get_insAmount();
            get_TotalAmount();

        }
    }//GEN-LAST:event_txt_int_rateKeyPressed

    private void get_TotalAmount() {
        if (txt_loan_amt.getText().isEmpty()) {
        } else {

            if (txt_int_rate.getText().isEmpty()) {
                txt_int_rate.setText("0");
            }

            if (txt_Outstanding.getText().isEmpty()) {
                txt_Outstanding.setText("0");
            }

            Double loan_amt = Double.parseDouble(txt_loan_amt.getText());
            Double out_amt = Double.parseDouble(txt_Outstanding.getText());
            Double int_rate = Double.parseDouble(txt_int_rate.getText()) / 100;
            Double repay = 0.00;

            repay = (loan_amt * int_rate) + loan_amt + out_amt;
            txt_RepayAmt.setText(String.format("%.2f", (loan_amt * int_rate) + loan_amt));
            txt_FullTotal.setText(String.format("%.2f", repay));

//            Double install = 0.00;
//            Double period = Double.parseDouble(txt_period.getText());
//            install = repay / period;
//
//            txt_install.setText(String.format("%.2f", install));
        }
    }

    private void txt_int_rateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_int_rateKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_int_rateKeyReleased

    private void txt_empnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empnoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empnoFocusGained

    private void txt_empnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empnoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empnoFocusLost

    private void txt_rankFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rankFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankFocusGained

    private void txt_rankFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rankFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankFocusLost

    private void txt_basicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_basicFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicFocusGained

    private void txt_basicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_basicFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicFocusLost

    private void txt_empno3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empno3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empno3FocusGained

    private void txt_empno3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empno3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empno3FocusLost

    private void txt_RepayAmtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RepayAmtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RepayAmtFocusGained

    private void txt_RepayAmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RepayAmtActionPerformed
        txt_install.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RepayAmtActionPerformed

    private void txt_RepayAmtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RepayAmtKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_RepayAmt.getText().toCharArray();
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
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txt_RepayAmtKeyTyped

    private void txt_periodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_periodFocusGained
        get_TotalAmount();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_periodFocusGained

    private void txt_periodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_periodActionPerformed

        txt_RepayAmt.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_periodActionPerformed

    private void txt_periodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_periodKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_period.getText().toCharArray();
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
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txt_periodKeyTyped

    private void txt_installFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_installFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_installFocusGained

    private void txt_installActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_installActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_installActionPerformed

    private void txt_installKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_installKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_install.getText().toCharArray();
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
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txt_installKeyTyped

    private void txt_int_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_int_rateActionPerformed
        txt_period.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_int_rateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        rentals();


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        txt_period.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable2.getSelectedRow();
            String code = jTable2.getValueAt(row, 0).toString();
            String name = jTable2.getValueAt(row, 1).toString();
            String basic = jTable2.getValueAt(row, 4).toString();
            String rank = jTable2.getValueAt(row, 3).toString();

            //            txt_search.setText(code);
            txt_empName.setText(name);
            txt_empno.setText(code);
            txt_basic.setText(basic);
            txt_rank.setText(rank);
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
        txt_loan_amt.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameActionPerformed

    private void txt_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            get_loanDetals();
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
                jScrollPane4.setBounds(80, 104, 450, 200);

                Connection con = DbConnection.getconnection();

                String empno = txt_empName.getText();

                String sql = "SELECT * FROM employee_reg WHERE   EmployeeNo Like? OR NameWithInitials Like? OR FullName Like?  ";
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

    private void txt_int_rateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_int_rateKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_int_rate.getText().toCharArray();
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
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txt_int_rateKeyTyped

    private void txt_periodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_periodKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            get_insAmount();

        }


    }//GEN-LAST:event_txt_periodKeyPressed

    private void get_insAmount() {
        if (txt_loan_amt.getText().isEmpty() | txt_period.getText().isEmpty()) {
        } else {

//            if (txt_int_rate.getText().isEmpty()) {
//                txt_int_rate.setText("0");
//            }
//
//            Double loan_amt = Double.parseDouble(txt_loan_amt.getText());
//            Double int_rate = Double.parseDouble(txt_int_rate.getText()) / 100;
//            Double repay = 0.00;
//
//            repay = (loan_amt * int_rate) + loan_amt;
//
//            txt_totalAmt.setText(String.format("%.2f", repay));
            Double loan_amt = Double.parseDouble(txt_FullTotal.getText());
            Double install = 0.00;
            Double period = Double.parseDouble(txt_period.getText());
            install = loan_amt / period;

            txt_install.setText(String.format("%.2f", install));

        }
    }

    private void txt_empnoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_empnoPropertyChange


    }//GEN-LAST:event_txt_empnoPropertyChange

    private String get_defLocation() {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String loc = "";
        try {

            con = DbConnection.getconnection();
            pst = con.prepareStatement("select DefLocation from employee_reg where EmployeeNo='" + txt_empno.getText() + "'");
            rs = pst.executeQuery();
            while (rs.next()) {

                loc = rs.getString(1);
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

        return loc;

    }


    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

        try {

            if (cmb_deduction_type.getSelectedIndex() == 0 | txt_empno.getText().isEmpty() | tbl_installments.getRowCount() < 1) {

                if (tbl_installments.getRowCount() != Double.parseDouble(txt_period.getText().trim())) {
                    JOptionPane.showMessageDialog(rootPane, "Please add Installments before Save..!", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                Connection con = DbConnection.getconnection();
                con.setAutoCommit(false);
                PreparedStatement pst_up = null;
                PreparedStatement pst_up2 = null;

                String def_loc = get_defLocation();

                //Update Recheduled loan Details
                if (txt_Outstanding.getText().isEmpty()) {

                } else {
                    pst_up = con.prepareStatement("update salary_deductions_summery set Status='re-scheduled' where RefNo='" + LoanRefNo + "' and EMPno='" + txt_empno.getText() + "' "
                            + "and Type='" + cmb_deduction_type.getSelectedItem().toString() + "'");
                    pst_up.execute();

                    pst_up = con.prepareStatement("update salary_deductions set Status='re-scheduled' where RefNo='" + LoanRefNo + "' and EMPno='" + txt_empno.getText() + "' "
                            + "and Type='" + cmb_deduction_type.getSelectedItem().toString() + "'"
                    );
                    pst_up.execute();

                }

                //get loanref number
                PreparedStatement pstref = con.prepareStatement("select MAX(RefNo) from salary_deductions_summery");
                ResultSet rs = pstref.executeQuery();
                int RefNo = 0;
                while (rs.next()) {

                    RefNo = rs.getInt("MAX(RefNo)") + 1;

                }

                //salary deduction save
                PreparedStatement pst = con.prepareStatement("insert into salary_deductions (EMPno,Type,Amount,Month,Year,Name,BSal,Status,RefNo,Category,Location) values(?,?,?,?,?,?,?,?,?,?,?)");
                String type = cmb_deduction_type.getSelectedItem().toString();

                for (int r = 0; tbl_installments.getRowCount() > r; r++) {

                    String emp = txt_empno.getText();
                    String name = txt_empName.getText();
                    String bsal = txt_basic.getText();
                    String amt = tbl_installments.getValueAt(r, 2).toString();
                    String month = tbl_installments.getValueAt(r, 0).toString();
                    String year = tbl_installments.getValueAt(r, 1).toString();

                    pst.setString(1, emp);
                    pst.setString(2, type);
                    pst.setDouble(3, Double.parseDouble(amt));
                    pst.setString(4, month);
                    pst.setString(5, year);
                    pst.setString(6, name);
                    pst.setString(7, bsal);
                    pst.setString(8, ONGOING);
                    pst.setInt(9, RefNo);
                    pst.setString(10, "loan");
                    pst.setString(11, def_loc);
                    pst.addBatch();
                }

                pst.executeBatch();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooser1.getDate());
                PreparedStatement pst2 = con.prepareStatement("insert into salary_deductions_summery (EMPno,Type,LoanAmount,Installments,InstallAmount,Interest,IssueDate,Status,RefNo,Location) values(?,?,?,?,?,?,?,?,?,?)");

                String emp = txt_empno.getText();

                pst2.setString(1, emp);
                pst2.setString(2, type);
                pst2.setString(3, txt_FullTotal.getText());
                pst2.setString(4, txt_period.getText());
                pst2.setString(5, txt_install.getText());
                pst2.setString(6, txt_int_rate.getText());
                pst2.setString(7, date);
                pst2.setString(8, ONGOING);
                pst2.setInt(9, RefNo);
                pst.setString(10, def_loc);

                pst2.execute();
                con.commit();

                DefaultTableModel dtps = (DefaultTableModel) tbl_previouslySaved.getModel();
                Vector v = new Vector();
                v.add(emp);
                v.add(type);
                v.add(txt_FullTotal.getText());
                v.add(txt_period.getText());
                v.add(txt_install.getText());
                dtps.insertRow(0, v);

                JOptionPane.showMessageDialog(rootPane, "Deductions Saved..!");

                cmb_deduction_type.setEnabled(true);
                DefaultTableModel dtm = (DefaultTableModel) tbl_installments.getModel();
                dtm.setRowCount(0);
                DefaultTableModel dtm1 = (DefaultTableModel) tbl_loandetails.getModel();
                dtm1.setRowCount(0);
                txt_empno.setText("");
                txt_empName.setText("");
                txt_basic.setText("");
                // jDateChooser1.setDate(null);

                txt_install.setText("");
                txt_int_rate.setText("");
                txt_loan_amt.setText("");
                txt_period.setText("");
                txt_rank.setText("");
                txt_RepayAmt.setText("");
                txt_empno3.setText("");
                LoanRefNo = "";
                txt_FullTotal.setText("");

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "ER:" + e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear all?", null, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            DefaultTableModel dtm = (DefaultTableModel) tbl_loandetails.getModel();
            dtm.setRowCount(0);
            jDateChooser1.setDate(null);
            txt_empno.setText("");
            txt_empName.setText("");
            txt_basic.setText("");

            txt_install.setText("");
            txt_int_rate.setText("");
            txt_loan_amt.setText("");
            txt_period.setText("");
            txt_rank.setText("");
            txt_RepayAmt.setText("");
            txt_empno3.setText("");
            txt_Outstanding.setText("");
            txt_FullTotal.setText("");
            LoanRefNo = "";
            cmb_deduction_type.setEnabled(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        get_All_loanDetals();       // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txt_periodFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_periodFocusLost
        get_insAmount();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_periodFocusLost

    private void txt_OutstandingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_OutstandingFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OutstandingFocusGained

    private void txt_OutstandingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_OutstandingFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OutstandingFocusLost

    private void txt_OutstandingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OutstandingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OutstandingActionPerformed

    private void txt_OutstandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OutstandingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OutstandingKeyPressed

    private void txt_OutstandingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OutstandingKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OutstandingKeyTyped

    private void txt_FullTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_FullTotalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FullTotalFocusGained

    private void txt_FullTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_FullTotalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FullTotalFocusLost

    private void txt_FullTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_FullTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FullTotalActionPerformed

    private void txt_FullTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_FullTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FullTotalKeyPressed

    private void txt_FullTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_FullTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FullTotalKeyTyped

    private void get_month_year() {

        if (txt_empno.getText().isEmpty() | jDateChooser1.getDate() == null | txt_install.getText().isEmpty() | txt_loan_amt.getText().isEmpty() | txt_install.getText().isEmpty()) {

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser1.getDate());

            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            System.out.println(year + " " + month);

            int period = Integer.parseInt(txt_period.getText());
            int m = Integer.parseInt(month);

            int y = Integer.parseInt(year);
            int new_m = 0;
            int new_y = y;
            System.out.println("m:" + m + " y:" + y);
            DefaultTableModel dtm = (DefaultTableModel) tbl_installments.getModel();
            dtm.setRowCount(0);

            for (int x = 0; x < period; x++) {

                if (new_m == 0) {
                    new_m = m + x;
                } else {
                    new_m = m + 1;
                }

                if (new_y == y) {
                    new_y = y;
                } else {
                    new_y = y + 1;
                }

                if (new_m > 12) {
                    new_y = y + 1;
                    new_m = new_m - 12;

                }
                y = new_y;
                m = new_m;
                System.out.println("m: " + m);
                System.out.println("x: " + x);
                System.out.println("repayment " + new_y + " " + new_m);

                String Smonth = "";
                if (new_m == 1) {
                    Smonth = "January";
                }
                if (new_m == 2) {
                    Smonth = "February";
                }
                if (new_m == 3) {
                    Smonth = "March";
                }
                if (new_m == 4) {
                    Smonth = "April";
                }
                if (new_m == 5) {
                    Smonth = "May";
                }
                if (new_m == 6) {
                    Smonth = "June";
                }
                if (new_m == 7) {
                    Smonth = "July";
                }
                if (new_m == 8) {
                    Smonth = "August";
                }
                if (new_m == 9) {
                    Smonth = "September";
                }
                if (new_m == 10) {
                    Smonth = "October";
                }
                if (new_m == 11) {
                    Smonth = "November";
                }
                if (new_m == 12) {
                    Smonth = "December";
                }
                Vector v = new Vector();
                v.add(Smonth);
                v.add(Integer.toString(new_y));
                v.add(txt_install.getText());
                dtm.addRow(v);

            }

        }
    }

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
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox cmb_deduction_type;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tbl_installments;
    private javax.swing.JTable tbl_loandetails;
    private javax.swing.JTable tbl_previouslySaved;
    private javax.swing.JTextField txt_FullTotal;
    private javax.swing.JTextField txt_Outstanding;
    private javax.swing.JTextField txt_RepayAmt;
    private javax.swing.JTextField txt_basic;
    public static javax.swing.JTextField txt_empName;
    private javax.swing.JTextField txt_empno;
    private javax.swing.JTextField txt_empno3;
    private javax.swing.JTextField txt_install;
    public static javax.swing.JTextField txt_int_rate;
    private javax.swing.JTextField txt_loan_amt;
    private javax.swing.JTextField txt_period;
    private javax.swing.JTextField txt_rank;
    // End of variables declaration//GEN-END:variables
}
