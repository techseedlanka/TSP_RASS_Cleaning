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
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Admin
 */
public class FestivalAdvance extends javax.swing.JFrame {

    /**
     * Creates new form Salary_Deductions
     */
    public FestivalAdvance() {
        initComponents();
        jTable2.getTableHeader().setUI(null);
        jTable1.getTableHeader().setUI(null);
        jScrollPane1.setVisible(false);
        jScrollPane4.setVisible(false);
        TitleBar();
        get_payTypes();
        get_Location_Details();
    }

    private void TitleBar() {

        this.setTitle("Loan Entry");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void get_payTypes() {
        try {

            String[] theSeven = {"Hand", "Bank", "Slip"};
            JComboBox combo1 = new JComboBox(theSeven);

            TableColumn col = tbl_emp_list.getColumnModel().getColumn(3);
            col.setCellEditor(new DefaultCellEditor(combo1));

            TableColumn col2 = tbl_Advance_summery.getColumnModel().getColumn(6);
            col2.setCellEditor(new DefaultCellEditor(combo1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get_Location_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from location_reg where LocStatus='1' order by LocName");
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

    private void get_initial_advance_details() {

        try {

            String loc = txt_locCode.getText();

            Connection con = DbConnection.getconnection();
            String sql = "select * from employee_reg where DefLocation='" + loc + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel dtm = (DefaultTableModel) tbl_emp_list.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                String rank = rs.getString("Designation");
                Vector v = new Vector();

                v.add(rs.getString("EmployeeNo"));
                v.add(rs.getString("NameWithInitials"));
                v.add(rank);
                v.add(rs.getString("PayType"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void rentals() {
        if (txt_empno.getText().isEmpty() | txt_loan_amt.getText().isEmpty() | txt_period.getText().isEmpty() | jDateChooser1.getDate() == null) {

            //txt_empno.setBackground(Color.red);
        } else {
            DefaultTableModel dtm2 = (DefaultTableModel) tbl_Advance_summery.getModel();

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

            String paydate = sdf.format(jDateChooser2.getDate());

            String empno = txt_empno.getText();
            String name = txt_empName.getText();
            String paytype = lbl_payType.getText();

            Vector v1 = new Vector();
            v1.add(empno);
            v1.add(name);
            v1.add(txt_loan_amt.getText());
            v1.add(txt_period.getText());
            v1.add(txt_install.getText());
            v1.add(date);
            v1.add(paytype);
            v1.add(paydate);
            v1.add(lbl_defLocation.getText());

            dtm2.addRow(v1);

            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
//            dtm.setRowCount(0);

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
                v.add(txt_empno.getText());
                v.add(Smonth);
                v.add(Integer.toString(new_y));
                v.add(txt_install.getText());
                dtm.addRow(v);

            }
        }

        txt_empno.setText("");
        txt_empName.setText("");
        txt_basic.setText("");
        txt_rank.setText("");
        lbl_payType.setText("");
    }

    private void rentals_for_all() {
        if (txt_loan_amt.getText().isEmpty() | txt_period.getText().isEmpty() | jDateChooser1.getDate() == null | jDateChooser2.getDate() == null) {

            //txt_empno.setBackground(Color.red);
            JOptionPane.showMessageDialog(rootPane, this);
        } else {

            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);

            DefaultTableModel dtm2 = (DefaultTableModel) tbl_Advance_summery.getModel();
            dtm2.setRowCount(0);

            for (int row = 0; row < tbl_emp_list.getRowCount(); row++) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooser1.getDate());
                String paydate = sdf.format(jDateChooser2.getDate());

                String year = date.substring(0, 4);
                String month = date.substring(5, 7);
                System.out.println(year + " " + month);

                int period = Integer.parseInt(txt_period.getText());
                int m = Integer.parseInt(month);

                int y = Integer.parseInt(year);
                int new_m = 0;
                int new_y = y;
                System.out.println("m:" + m + " y:" + y);

                String empno = tbl_emp_list.getValueAt(row, 0).toString();
                String name = tbl_emp_list.getValueAt(row, 1).toString();
                String paytype = tbl_emp_list.getValueAt(row, 3).toString();

                Vector v1 = new Vector();
                v1.add(empno);
                v1.add(name);
                v1.add(txt_loan_amt.getText());
                v1.add(txt_period.getText());
                v1.add(txt_install.getText());
                v1.add(date);
                v1.add(paytype);
                v1.add(paydate);
                v1.add(txt_locCode.getText());

                dtm2.addRow(v1);

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
                    v.add(empno);
                    v.add(Smonth);
                    v.add(Integer.toString(new_y));
                    v.add(txt_install.getText());
                    dtm.addRow(v);

                }//for loop

            }//main for loop
        }
    }

//    private void get_loanDetals() {
//
//        if (txt_empno.getText().isEmpty() | cmb_deduction_type.getSelectedIndex() == 0) {
//
//        } else {
//
//            try {
//
//                Connection con = DbConnection.getconnection();
//                DefaultTableModel dtm = (DefaultTableModel) tbl_loandetails.getModel();
//                dtm.setRowCount(0);
//                String sql = null;
//
////                if (jCheckBox1.isSelected()) {
//                sql = "select * from salary_deductions_summery  where Status='on-going' and EMPno='" + txt_empno.getText() + "' and Type='" + cmb_deduction_type.getSelectedItem().toString() + "' ";
////                } else {
//
//                // sql = "select * from salary_deductions_summery   where Status='on-going' and EMPno='" + txt_empno.getText() + "'  Order by Line";
////                }
//                PreparedStatement pst = con.prepareStatement(sql);
//                ResultSet rs = pst.executeQuery();
//
//                while (rs.next()) {
//
//                    String refno = rs.getString("RefNo");
//                    System.out.println(refno);
//
//                    pst = con.prepareStatement("select *,SUM(Amount) from salary_deductions where Status='on-going' and RefNo='" + refno + "' ");
//                    ResultSet rs1 = pst.executeQuery();
//                    while (rs1.next()) {
//                        txt_Outstanding.setText(Double.toString(rs1.getDouble("SUM(Amount)")));
//                    }
//
//                    String month = null;
//                    String year = null;
//                    pst = con.prepareStatement("select  MAX(Line) from salary_deductions where Status='on-going' and EMPno='" + txt_empno.getText() + "' and Type='" + cmb_deduction_type.getSelectedItem().toString() + "' and RefNo='" + refno + "' ");
//                    ResultSet rs2 = pst.executeQuery();
//                    while (rs2.next()) {
//
//                        int max = rs2.getInt("MAX(Line)");
//
//                        pst = con.prepareStatement("select  * from salary_deductions where Line=" + max + " ");
//                        ResultSet rs3 = pst.executeQuery();
//                        while (rs3.next()) {
//                            month = rs3.getString("Month");
//                            year = rs3.getString("Year");
//                        }
//
//                    }
//
//                    Vector v = new Vector();
//                    v.add(rs.getString(2));//type
//                    v.add(rs.getString(3));//loan amt
//                    v.add(rs.getString(4));//installments
//                    v.add(rs.getString(5));//renatl
//                    v.add(month + " " + year);//lastmonth
//                    dtm.addRow(v);
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_loan_amt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        txt_empno = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lbl_defLocation = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        txt_basic = new javax.swing.JTextField();
        lbl_payType = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_period = new javax.swing.JTextField();
        txt_install = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_empName = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_Advance_summery = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_emp_list = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel33 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cmb_year = new javax.swing.JComboBox();
        cmb_month = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "emp", "name", "initials", "rank", "Basic", "OLD EMP", "paytype", "loc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
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
            jTable2.getColumnModel().getColumn(6).setMinWidth(0);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(7).setMinWidth(0);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 150, 20));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 230, 20));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Festival Advance");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 1300, 10));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText(":");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 10, 20));

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel23.setText("Installment Details");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 130, 20));

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
        getContentPane().add(txt_loan_amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 120, 25));

        jTable3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Month", "Year", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setRowHeight(20);
        jScrollPane2.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 360, 290));

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
        getContentPane().add(txt_empno, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, 60, 23));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1300, 10));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 650, 170, 40));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        jButton4.setText(" Get Installments");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 190, 60));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton5.setText("Clear ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 190, 40));

        lbl_defLocation.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        lbl_defLocation.setText("Rank ");
        getContentPane().add(lbl_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 100, 20));

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
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 120, 23));

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
        getContentPane().add(txt_basic, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 120, 23));

        lbl_payType.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        lbl_payType.setText("PT");
        getContentPane().add(lbl_payType, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 20, 20));

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel22.setText("Loan Amount     ");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 120, 20));

        jLabel29.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel29.setText("Installment Amt.");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 120, 20));

        jLabel30.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel30.setText("1st Instll. Month.  ");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 110, 20));

        jLabel31.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel31.setText("Repayment Period ");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 120, 20));

        jLabel32.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        jLabel32.setText("Month(s)");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 60, 20));

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
        getContentPane().add(txt_period, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 60, 25));

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
        getContentPane().add(txt_install, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 120, 25));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 120, 23));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel34.setText(":");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 10, 20));

        jLabel35.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel35.setText(":");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 10, 20));

        jLabel36.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel36.setText(":");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 10, 20));

        jLabel38.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel38.setText(":");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 10, 20));

        jLabel40.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel40.setText(":");
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 10, 20));

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
        getContentPane().add(txt_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 270, 23));

        tbl_Advance_summery.setAutoCreateRowSorter(true);
        tbl_Advance_summery.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_Advance_summery.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Name", "Advance Amount", "Period", "Monthly Amt.", "1st Installment", "Pay Type", "Pay Date", "Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Advance_summery.setRowHeight(25);
        jScrollPane3.setViewportView(tbl_Advance_summery);
        if (tbl_Advance_summery.getColumnModel().getColumnCount() > 0) {
            tbl_Advance_summery.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbl_Advance_summery.getColumnModel().getColumn(1).setPreferredWidth(180);
            tbl_Advance_summery.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbl_Advance_summery.getColumnModel().getColumn(3).setPreferredWidth(60);
            tbl_Advance_summery.getColumnModel().getColumn(4).setPreferredWidth(80);
            tbl_Advance_summery.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbl_Advance_summery.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbl_Advance_summery.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbl_Advance_summery.getColumnModel().getColumn(8).setMinWidth(0);
            tbl_Advance_summery.getColumnModel().getColumn(8).setPreferredWidth(0);
            tbl_Advance_summery.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 830, 280));

        tbl_emp_list.setAutoCreateRowSorter(true);
        tbl_emp_list.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_emp_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Name", "Rank", "PayType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_emp_list.setRowHeight(25);
        jScrollPane5.setViewportView(tbl_emp_list);
        if (tbl_emp_list.getColumnModel().getColumnCount() > 0) {
            tbl_emp_list.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbl_emp_list.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbl_emp_list.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 440, 580));

        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jCheckBox1.setText("Apply To All");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 110, -1));

        jLabel37.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel37.setText(":");
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 10, 20));

        jLabel26.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel26.setText("Employee             ");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 120, 20));

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel27.setText("Advance Summery");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 120, 20));

        jButton16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton16.setText("Remove Installment ");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, 190, 40));

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
                cmb_defLocationPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_defLocationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 380, -1));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 50, 23));

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });
        getContentPane().add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, 120, 23));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText("Payment Date");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 90, 20));

        jButton17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton17.setText("Remove Employee");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 190, 40));

        jButton18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton18.setText("Remove from Advance Summery");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 650, 260, 40));

        jLabel28.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel28.setText("Basic Salary  ");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 100, 20));

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel25.setText("Rank ");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 100, 20));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021" }));
        cmb_year.setSelectedIndex(2);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, -1, -1));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 110, -1));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Festival Month");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 20));

        jLabel39.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel39.setText(":");
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 10, 20));

        jLabel41.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel41.setText(":");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 10, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_loan_amtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_loan_amtFocusGained
//        get_loanDetals();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_loan_amtFocusGained

    private void txt_loan_amtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_loan_amtActionPerformed
        txt_period.grabFocus();
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

    private void txt_periodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_periodFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_periodFocusGained

    private void txt_periodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_periodActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jCheckBox1.isSelected()) {
            rentals_for_all();
        } else {
            rentals();
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        txt_period.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable2.getSelectedRow();
            String code = jTable2.getValueAt(row, 0).toString();
            String name = jTable2.getValueAt(row, 2).toString();
            String basic = jTable2.getValueAt(row, 4).toString();
            String rank = jTable2.getValueAt(row, 3).toString();
            String pt = jTable2.getValueAt(row, 6).toString();
            String loc = jTable2.getValueAt(row, 7).toString();

            //            txt_search.setText(code);
            txt_empName.setText(name);
            txt_empno.setText(code);
            txt_basic.setText(basic);
            txt_rank.setText(rank);
            lbl_defLocation.setText(loc);
            lbl_payType.setText(pt);
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
            //  get_loanDetals();
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
                jScrollPane4.setBounds(580, 123, 450, 200);

                Connection con = DbConnection.getconnection();

                String empno = txt_empName.getText();

                String sql = "SELECT * FROM employee_reg WHERE  NameWithInitials LIKE ? OR EPFno Like? OR EmployeeNo Like?   ";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, empno + "%");
                pst.setString(2, empno + "%");
                pst.setString(3, empno + "%");
                ResultSet rst = pst.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                dtm.setRowCount(0);

                while (rst.next()) {

                    Vector v = new Vector();
                    v.add(rst.getString("EmployeeNo"));
                    v.add(rst.getString("Surname"));
                    v.add(rst.getString("NameWithInitials"));
                    v.add(rst.getString("Designation"));
                    v.add(rst.getString("BasicSalary"));
                    v.add(rst.getString("EPFno"));
                    v.add(rst.getString("PayType"));
                    v.add(rst.getString("DefLocation"));

                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }//GEN-LAST:event_txt_empNameKeyReleased

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
            Double loan_amt = Double.parseDouble(txt_loan_amt.getText());
            Double install = 0.00;
            Double period = Double.parseDouble(txt_period.getText());
            install = loan_amt / period;

            txt_install.setText(String.format("%.2f", install));

        }
    }

    private void txt_empnoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_empnoPropertyChange

    }//GEN-LAST:event_txt_empnoPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /**
         * need to save into two tables. 1. loan summery table 2. salary
         * deduction table
         */

        try {

            if (tbl_Advance_summery.getRowCount() < 0 | jTable3.getRowCount() < 0) {
            } else {
                Connection con = DbConnection.getconnection();
                con.setAutoCommit(false);
                PreparedStatement pst_up = null;
                PreparedStatement pst_up2 = null;

                //get loanref number
//                PreparedStatement pstref = con.prepareStatement("select MAX(RefNo) from salary_festival_deductions_summery");
//                ResultSet rs = pstref.executeQuery();
//                int RefNo = 0;
//                while (rs.next()) {
//
//                    if (rs.getInt("MAX(RefNo)") < 0) {
//                        RefNo = 1;
//                    } else {
//
//                        RefNo = rs.getInt("MAX(RefNo)") + 1;
//                    }
//
//                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                String paydate = sdf.format(jDateChooser2.getDate());

                //salary deduction save
                PreparedStatement pst = con.prepareStatement("insert into salary_advance_festival_monthly (EMPno,Amount,Month,Year,Status,RefNo) values(?,?,?,?,?,?)");
                String type = "";

                for (int r = 0; jTable3.getRowCount() > r; r++) {

                    String emp = jTable3.getValueAt(r, 0).toString();
                    String amt = jTable3.getValueAt(r, 3).toString();
                    String month = jTable3.getValueAt(r, 1).toString();
                    String year = jTable3.getValueAt(r, 2).toString();
                    String RefNo = paydate + emp + amt;

                    pst.setString(1, emp);
                    pst.setDouble(2, Double.parseDouble(amt));
                    pst.setString(3, month);
                    pst.setString(4, year);
                    pst.setString(5, "on-going");
                    pst.setString(6, RefNo);

                    pst.addBatch();
                }

                pst.executeBatch();

                PreparedStatement pst2 = con.prepareStatement("insert into salary_festival_deductions_summery (EMPno,LoanAmount,Installments,InstallAmount,IssueDate,Status,RefNo,PayType,1stInstallment,Unit,FestivYear,FestivMonth) values(?,?,?,?,?,?,?,?,?,?,?,?)");

                for (int r = 0; tbl_Advance_summery.getRowCount() > r; r++) {

                    String emp = tbl_Advance_summery.getValueAt(r, 0).toString();
                    String amt = tbl_Advance_summery.getValueAt(r, 2).toString();
                    String Installments = tbl_Advance_summery.getValueAt(r, 3).toString();
                    String InstallAmount = tbl_Advance_summery.getValueAt(r, 4).toString();
                    String IssueDate = tbl_Advance_summery.getValueAt(r, 7).toString();
                    String PayType = tbl_Advance_summery.getValueAt(r, 6).toString();
                    String firststInstallment = tbl_Advance_summery.getValueAt(r, 5).toString();
                    String RefNo = paydate + emp + amt;
                    String unit = tbl_Advance_summery.getValueAt(r, 8).toString();
                    pst2.setString(1, emp);
                    pst2.setDouble(2, Double.parseDouble(amt));
                    pst2.setString(3, Installments);
                    pst2.setDouble(4, Double.parseDouble(InstallAmount));
                    pst2.setString(5, IssueDate);
                    pst2.setString(6, "on-going");
                    pst2.setString(7, RefNo);
                    pst2.setString(8, PayType);
                    pst2.setString(9, firststInstallment);
                    pst2 .setString(10, unit);
                    pst2 .setString(11, cmb_year.getSelectedItem().toString());
                    pst2 .setString(12, cmb_month.getSelectedItem().toString());
                    pst2.addBatch();
                }

                pst2.executeBatch();
                con.commit();

                JOptionPane.showMessageDialog(rootPane, "Deductions Saved..!");

                DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                dtm.setRowCount(0);
                DefaultTableModel dtm1 = (DefaultTableModel) tbl_Advance_summery.getModel();
                dtm1.setRowCount(0);
                DefaultTableModel dtm2 = (DefaultTableModel) tbl_emp_list.getModel();
                dtm2.setRowCount(0);
                txt_empno.setText("");
                txt_empName.setText("");
                txt_basic.setText("");
                // jDateChooser1.setDate(null);

                txt_install.setText("");

                txt_loan_amt.setText("");
                txt_period.setText("");
                txt_rank.setText("");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear all?", null, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
//            DefaultTableModel dtm = (DefaultTableModel) tbl_Advance_summery.getModel();
//            dtm.setRowCount(0);
            jDateChooser1.setDate(null);
            jDateChooser2.setDate(null);
            txt_empno.setText("");
            txt_empName.setText("");
            txt_basic.setText("");
            lbl_payType.setText("");

            txt_install.setText("");

            txt_loan_amt.setText("");
            txt_period.setText("");
            txt_rank.setText("");

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_periodFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_periodFocusLost
        get_insAmount();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_periodFocusLost

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int nrow = jTable3.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) from Installment Details ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
                    dtm.removeRow(jTable3.getSelectedRow());
                }

            } else {

            }

        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationPopupMenuCanceled

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode.setText("N/A");
            txt_locCode.setForeground(Color.red);

        } else {
            txt_locCode.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where    LocStatus='1' and LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(name);
                    txt_locCode.setText(code);
                    get_initial_advance_details();
                }

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

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            txt_empName.setText("");
            txt_empName.setEnabled(false);
            txt_empno.setText("");

        } else {
            txt_empName.setText("");
            txt_empName.setEnabled(true);
            txt_empno.setText("");

        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txt_basicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_basicFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicFocusLost

    private void txt_basicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_basicFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicFocusGained

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int nrow = tbl_emp_list.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) from Employee List  ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tbl_emp_list.getModel();
                    dtm.removeRow(tbl_emp_list.getSelectedRow());
                }

            } else {

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        int nrow = tbl_Advance_summery.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) from Advance Summery ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) tbl_Advance_summery.getModel();
                    dtm.removeRow(tbl_Advance_summery.getSelectedRow());
                }

            } else {

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

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
            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
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
            java.util.logging.Logger.getLogger(FestivalAdvance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FestivalAdvance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FestivalAdvance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FestivalAdvance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FestivalAdvance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    public static javax.swing.JComboBox cmb_month;
    public static javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbl_defLocation;
    private javax.swing.JLabel lbl_payType;
    private javax.swing.JTable tbl_Advance_summery;
    private javax.swing.JTable tbl_emp_list;
    private javax.swing.JTextField txt_basic;
    public static javax.swing.JTextField txt_empName;
    private javax.swing.JTextField txt_empno;
    private javax.swing.JTextField txt_install;
    private javax.swing.JTextField txt_loan_amt;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_period;
    private javax.swing.JTextField txt_rank;
    // End of variables declaration//GEN-END:variables
}
