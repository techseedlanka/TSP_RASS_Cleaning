/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Distress_Loan extends javax.swing.JFrame {

    /**
     * Creates new form Loan_Managment
     */
    private ImageIcon format = null;

    public Distress_Loan() {
        initComponents();
        auto_completer();
        lbl_photo.setVisible(false);
        txt_loanAmount.setText("0");
        txt_installments.setText("0");
        txt_rental.setText("0");

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);
            TextAutoCompleter ta1 = new TextAutoCompleter(txt_guarantor1);
            TextAutoCompleter ta2 = new TextAutoCompleter(txt_guarantor2);

            while (rs.next()) {
                String code = rs.getString("EmployeeNo");
                //String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");

                ta.addItem(code);
                ta.addItem(NameWithInitials);
                ta1.addItem(NameWithInitials);
                ta2.addItem(NameWithInitials);
                //ta.addItem(nic);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String ReferenceID;

    private void loan_reference() {

        if (txt_empid.getText().isEmpty()) {

        } else {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select *,MAX(ReferenceID),COUNT(ReferenceID) from distress_loan");

                while (rs.next()) {

                    int countRows = rs.getInt("COUNT(ReferenceID)");

                    if (countRows == 0) {

                        ReferenceID = "0001";

                    } else {

                        String Ref_ID = rs.getString("MAX(ReferenceID)");
                        int x = Integer.parseInt(Ref_ID);
                        int y = x + 1;

//                        ReferenceID = Integer.toString(y);
                        ReferenceID = String.format("%04d", y);

                    }

                    String epf = txt_empid.getText();

                    if (cmb_loanType.getSelectedIndex() == 0) {
                        txt_reference.setText("DL01/" + epf + "/" + ReferenceID);
                    } else if (cmb_loanType.getSelectedIndex() == 1) {
                        txt_reference.setText("DL02/" + epf + "/" + ReferenceID);

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void get_data_to_table() {

//        String LoanReference;
//        Double LoanAmt = 0.00;
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from distress_loan where EPFno = '" + txt_empid.getText() + "' and Status ='on-going'");
//
//            while (rs.next()) {
//
//                LoanReference = rs.getString("LoanReference");
//                LoanAmt = Double.parseDouble(rs.getString("LoanAmount"));
//
//                Statement st1 = DbConnection.getconnection().createStatement();
//                ResultSet rs1 = st1.executeQuery("select *,SUM(ReceivedAmount) from distress_loan_settelment where LoanReference = '" + LoanReference + "'");
//
//                while (rs1.next()) {
//
//                    Double int_receivedAmt = Double.parseDouble(rs1.getString("SUM(ReceivedAmount)"));
//                    Double int_balanceAmt = LoanAmt - int_receivedAmt;
//
//                    String receviedAmt = Double.toString(int_receivedAmt);
//                    String balanceAmt = Double.toString(int_balanceAmt);
//
//                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//                    Vector v = new Vector();
//
//                    v.add(rs.getString("LoanAmount"));
//                    v.add(receviedAmt);
//                    v.add(balanceAmt);
//                    v.add(rs.getString("Rental"));
//                    v.add(rs.getString("LoanReference"));
//
//                    dtm.addRow(v);
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void clear() {

        txt_empNic.setText("");
        txt_empid.setText("");
        txt_guarantor1.setText("");
        txt_guarantor2.setText("");
        txt_installments.setText("0");
        txt_G02_name.setText("");
        txt_G01_name.setText("");
        txt_loanAmount.setText("0");
        txt_name.setText("");
        txt_rank.setText("");
        txt_reference.setText("");
        txt_rental.setText("0");
        txt_search.setText("");
        jTextArea1.setText("");

        date_1stIns.setDate(null);
        date_IssueOn.setDate(null);
        date_lastIns.setDate(null);

        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txt_search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_empid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_empNic = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_loanAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_installments = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_rental = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        date_lastIns = new com.toedter.calendar.JDateChooser();
        date_1stIns = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        date_IssueOn = new com.toedter.calendar.JDateChooser();
        txt_G02_name = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_guarantor2 = new javax.swing.JTextField();
        txt_G01_name = new javax.swing.JTextField();
        txt_guarantor1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_reference = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cmb_loanType = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lbl_photo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 950, 10));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("      Loan");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 40));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 950, 10));

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 340, 30));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Loan Type");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, 20));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Employee Details"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPanel1.add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 50, 21));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Employee  No.  :-");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        txt_name.setEditable(false);
        txt_name.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 240, 21));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Name :-");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPanel1.add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 40, 21));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Rank :-");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 20));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText(" NIC No.  :-");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, 20));

        txt_empNic.setEditable(false);
        txt_empNic.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPanel1.add(txt_empNic, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 80, 21));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton5.setText("View Photo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 440, 90));

        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Employee Search (Name/ID) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 12))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 360, 70));

        txt_loanAmount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_loanAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_loanAmountFocusGained(evt);
            }
        });
        txt_loanAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_loanAmountActionPerformed(evt);
            }
        });
        txt_loanAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_loanAmountKeyTyped(evt);
            }
        });
        getContentPane().add(txt_loanAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 110, 25));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("Last Installment :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, 20));

        txt_installments.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_installments.setToolTipText("Installments in Months");
        txt_installments.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_installmentsFocusGained(evt);
            }
        });
        txt_installments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_installmentsActionPerformed(evt);
            }
        });
        txt_installments.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_installmentsKeyTyped(evt);
            }
        });
        getContentPane().add(txt_installments, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 50, 25));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Rental :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, -1, 20));

        txt_rental.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_rental.setToolTipText("Installments in Months");
        txt_rental.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rentalFocusGained(evt);
            }
        });
        txt_rental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rentalKeyTyped(evt);
            }
        });
        getContentPane().add(txt_rental, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 110, 25));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Installments :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, 20));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("1st Installment :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, 20));

        date_lastIns.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(date_lastIns, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 140, 23));

        date_1stIns.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(date_1stIns, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 130, 23));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Issue on :-");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 20));

        date_IssueOn.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(date_IssueOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 120, 23));

        txt_G02_name.setEditable(false);
        txt_G02_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_G02_name.setToolTipText("Installments in Months");
        getContentPane().add(txt_G02_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 60, 25));

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel14.setText("Guarantor 01 :-");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));

        txt_guarantor2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_guarantor2.setToolTipText("Installments in Months");
        txt_guarantor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_guarantor2ActionPerformed(evt);
            }
        });
        txt_guarantor2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_guarantor2KeyPressed(evt);
            }
        });
        getContentPane().add(txt_guarantor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 290, 25));

        txt_G01_name.setEditable(false);
        txt_G01_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_G01_name.setToolTipText("Installments in Months");
        getContentPane().add(txt_G01_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 60, 25));

        txt_guarantor1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_guarantor1.setToolTipText("Installments in Months");
        txt_guarantor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_guarantor1ActionPerformed(evt);
            }
        });
        txt_guarantor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_guarantor1KeyPressed(evt);
            }
        });
        getContentPane().add(txt_guarantor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 290, 25));

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel15.setText("Guarantor 02 :-");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, 20));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 340, 100));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loan Amount", "Received Amount", "Balance Amount", "Rental", "Loan Referance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 320, 460, 90));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel16.setText("Note :-");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, 20));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("Loan Reference :-");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, -1, 20));

        txt_reference.setEditable(false);
        txt_reference.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txt_reference.setToolTipText("Installments in Months");
        getContentPane().add(txt_reference, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 150, 25));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 120, 50));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 120, 50));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Previous On-Going Loan Details"));
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 500, 120));

        cmb_loanType.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cmb_loanType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Loan 01", "Loan 02" }));
        cmb_loanType.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_loanTypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_loanType, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 100, 30));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 120, 50));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("Loan Amount :-");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        lbl_photo.setBackground(new java.awt.Color(102, 204, 255));
        lbl_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        getContentPane().add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 30, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained

    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                lbl_photo.setIcon(null);

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg");
                while (rs.next()) {
                    String code = rs.getString("EmployeeNo");
                    String nic = rs.getString("NIC");
                    String name = rs.getString("NameWithInitials");
                    String rate = rs.getString("ShiftRate");
                    String rank = rs.getString("Designation");

                    byte[] imagedata = rs.getBytes("EMPImage");

                    if (txt_search.getText().equals(code) || txt_search.getText().equals(name) || txt_search.getText().equals(nic)) {

                        if (imagedata == null) {
                            lbl_photo.setText("No Image");
                            lbl_photo.setForeground(Color.red);

                        } else {

                            format = new ImageIcon(imagedata);
                            lbl_photo.setIcon(format);
                        }
                        txt_empid.setText(code);
                        txt_name.setText(name);
                        txt_empNic.setText(nic);

                        txt_rank.setText(rank);

                    } else {
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            loan_reference();
            get_data_to_table();

        }


    }//GEN-LAST:event_txt_searchKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (txt_empid.getText().isEmpty() | txt_loanAmount.getText().isEmpty() | txt_rental.getText().isEmpty() | date_IssueOn.getDate() == null | txt_installments.getText().isEmpty() | txt_installments.getText().equals("0") | txt_rental.getText().isEmpty() | txt_rental.getText().equals("0") | txt_loanAmount.getText().equals("0")) {

        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String issueOn = sdf.format(date_IssueOn.getDate());
            String firstIns = sdf.format(date_1stIns.getDate());
            String lastIns = sdf.format(date_lastIns.getDate());

            try {
                if (cmb_loanType.getSelectedIndex() == 0) {
                    //Distress Loan 01

                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeUpdate("insert into distress_loan values( '" + txt_reference.getText() + "','" + txt_empid.getText() + "','" + txt_loanAmount.getText() + "','" + txt_installments.getText() + "','" + txt_G01_name.getText() + "','" + txt_G02_name.getText() + "', '" + txt_rental.getText() + "','" + issueOn + "','" + firstIns + "','" + lastIns + "','" + ReferenceID + "','" + jTextArea1.getText() + "','on-going' )");

                    JOptionPane.showMessageDialog(rootPane, " Loan Successfully Saved...");

                    clear();

                } else if (cmb_loanType.getSelectedIndex() == 1) {

                    //Distress Loan 02
                    Statement st = DbConnection.getconnection().createStatement();
                    st.executeUpdate("insert into distress_loan_02 values( '" + txt_reference.getText() + "','" + txt_empid.getText() + "','" + txt_loanAmount.getText() + "','" + txt_installments.getText() + "','" + txt_G01_name.getText() + "','" + txt_G02_name.getText() + "', '" + txt_rental.getText() + "','" + issueOn + "','" + firstIns + "','" + lastIns + "','" + ReferenceID + "','" + jTextArea1.getText() + "','on-going' )");

                    JOptionPane.showMessageDialog(rootPane, " Loan Successfully Saved...");

                    clear();

                } else {
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmb_loanTypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_loanTypePopupMenuWillBecomeInvisible

        txt_guarantor1.setText("");
        txt_guarantor2.setText("");
        txt_installments.setText("0");
        txt_G02_name.setText("");
        txt_G01_name.setText("");
        txt_loanAmount.setText("0");
        txt_reference.setText("");
        txt_rental.setText("0");
        date_1stIns.setDate(null);
        date_IssueOn.setDate(null);
        date_lastIns.setDate(null);
        jTextArea1.setText("");

        loan_reference();

//        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
//        dt.setRowCount(0);
        try {

            if (cmb_loanType.getSelectedIndex() == 0) {
                //Search Distress Loan 01

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from distress_loan where EPFno ='" + txt_empid.getText() + "' and Status = 'on-going'");
                while (rs.next()) {

                    txt_loanAmount.setText(rs.getString("LoanAmount"));
                    date_IssueOn.setDate(rs.getDate("IssueOn"));
                    date_1stIns.setDate(rs.getDate("1stInstallment"));
                    date_lastIns.setDate(rs.getDate("LastInstallment"));
                    txt_installments.setText(rs.getString("Installments"));
                    txt_rental.setText(rs.getString("Rental"));
                    txt_guarantor1.setText(rs.getString("Guarantor01"));
                    txt_guarantor2.setText(rs.getString("Guarantor02"));
                    txt_reference.setText(rs.getString("LoanReference"));
                    jTextArea1.setText(rs.getString("Note"));
                }

            } else if (cmb_loanType.getSelectedIndex() == 1) {

                //Search Distress Loan 02
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from distress_loan_02 where EPFno ='" + txt_empid.getText() + "' and Status = 'on-going'");
                while (rs.next()) {

                    txt_loanAmount.setText(rs.getString("LoanAmount"));
                    date_IssueOn.setDate(rs.getDate("IssueOn"));
                    date_1stIns.setDate(rs.getDate("1stInstallment"));
                    date_lastIns.setDate(rs.getDate("LastInstallment"));
                    txt_installments.setText(rs.getString("Installments"));
                    txt_rental.setText(rs.getString("Rental"));
                    txt_guarantor1.setText(rs.getString("Guarantor01"));
                    txt_guarantor2.setText(rs.getString("Guarantor02"));
                    txt_reference.setText(rs.getString("LoanReference"));
                    jTextArea1.setText(rs.getString("Note"));
                }

            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmb_loanTypePopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int reply = JOptionPane.showConfirmDialog(rootPane, "<html>This Data can't be Reverse/Restore Once Delete<br>Do You Want to Delete..?</html>", "Confirmation", JOptionPane.YES_NO_OPTION);

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

                            if (cat.equals("admin")) {

//query begins*************************************************************************************************
                                Statement st = DbConnection.getconnection().createStatement();
                                if (txt_empid.getText().isEmpty() | txt_loanAmount.getText().isEmpty() | txt_rental.getText().isEmpty() | date_IssueOn.getDate() == null) {

                                } else {

                                    if (cmb_loanType.getSelectedIndex() == 0) {

                                        st.executeUpdate("delete from distress_loan where EPFno = '" + txt_empid.getText() + "' and LoanReference = '" + txt_reference.getText() + "'");

                                        JOptionPane.showMessageDialog(rootPane, "Loan " + txt_reference.getText() + " was Successfully Deleted...");
                                        clear();

                                    } else if (cmb_loanType.getSelectedIndex() == 1) {

                                        st.executeUpdate("delete from distress_loan_02 where EPFno = '" + txt_empid.getText() + "' and LoanReference = '" + txt_reference.getText() + "'");

                                        JOptionPane.showMessageDialog(rootPane, "Loan " + txt_reference.getText() + " was Successfully Deleted...");
                                        clear();

                                    }

                                }
//query ends ****************************************************************************************************
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


    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_guarantor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_guarantor1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + txt_guarantor1.getText() + "' OR NameWithInitials ='" + txt_guarantor1.getText() + "' ");
                while (rs.next()) {
                    String code = rs.getString("EmployeeNo");

                    String name = rs.getString("NameWithInitials");
                    txt_guarantor1.setText(name);
                    txt_G01_name.setText(code);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (txt_empid.getText().equals(txt_G01_name.getText())) {

                JOptionPane.showMessageDialog(rootPane, "Guarantor & RequestorCannot be Same");
                txt_G01_name.setText("");
                txt_guarantor1.setText("");

            }

        }

    }//GEN-LAST:event_txt_guarantor1KeyPressed

    private void txt_guarantor2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_guarantor2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + txt_guarantor2.getText() + "' OR NameWithInitials ='" + txt_guarantor2.getText() + "'");
                while (rs.next()) {
                    String code = rs.getString("EmployeeNo");

                    String name = rs.getString("NameWithInitials");
                    txt_guarantor2.setText(name);
                    txt_G02_name.setText(code);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (txt_empid.getText().equals(txt_G02_name.getText())) {

                JOptionPane.showMessageDialog(rootPane, "Guarantor & Requestor Cannot be Same");
                txt_G02_name.setText("");
                txt_guarantor2.setText("");

            }
        }
    }//GEN-LAST:event_txt_guarantor2KeyPressed

    private void txt_loanAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_loanAmountKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_loanAmount.getText().toCharArray();
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
        }  // TODO add your handling code here:
    }//GEN-LAST:event_txt_loanAmountKeyTyped

    private void txt_installmentsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_installmentsKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_installments.getText().toCharArray();
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
        }  // TODO add your handling code here:
    }//GEN-LAST:event_txt_installmentsKeyTyped

    private void txt_rentalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rentalKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_rental.getText().toCharArray();
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
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_rentalKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(format);
        pt.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_loanAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_loanAmountActionPerformed
        txt_installments.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_loanAmountActionPerformed

    private void txt_installmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_installmentsActionPerformed
        txt_rental.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_installmentsActionPerformed

    private void txt_guarantor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_guarantor1ActionPerformed
        txt_guarantor2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_guarantor1ActionPerformed

    private void txt_guarantor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_guarantor2ActionPerformed
        jTextArea1.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_guarantor2ActionPerformed

    private void txt_loanAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_loanAmountFocusGained
        txt_loanAmount.setText("");  // TODO add your handling code here:
    }//GEN-LAST:event_txt_loanAmountFocusGained

    private void txt_installmentsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_installmentsFocusGained
        txt_installments.setText(""); // TODO add your handling code here:
    }//GEN-LAST:event_txt_installmentsFocusGained

    private void txt_rentalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rentalFocusGained
        txt_rental.setText("");

        Double loan = 0.00;
        Double install = 0.00;
        Double rental = 0.00;

        if (txt_rental.getText().equals("")) {
            rental = 0.00;
        } else {
            rental = Double.parseDouble(txt_rental.getText());
        }

        if (txt_installments.getText().equals("")) {
            install = 0.00;
        } else {
            install = Double.parseDouble(txt_installments.getText());
        }

        if (txt_loanAmount.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Loan Amount Can not be Empty");
            txt_loanAmount.setText("0");
        } else {
            loan = Double.parseDouble(txt_loanAmount.getText());
        }

        if (!(install == 0) && !(rental == 0)) {
            Double x = loan / install;
            String total = String.format("%.2f", x);
            txt_rental.setText(total);

        } else if (!(install == 0) & (rental == 0)) {
            Double x = loan / install;
            String total = String.format("%.2f", x);
            txt_rental.setText(total);

        } else {
        }


    }//GEN-LAST:event_txt_rentalFocusGained

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
            java.util.logging.Logger.getLogger(Distress_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Distress_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Distress_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Distress_Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Distress_Loan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_loanType;
    private com.toedter.calendar.JDateChooser date_1stIns;
    private com.toedter.calendar.JDateChooser date_IssueOn;
    private com.toedter.calendar.JDateChooser date_lastIns;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JTextField txt_G01_name;
    private javax.swing.JTextField txt_G02_name;
    private javax.swing.JTextField txt_empNic;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_guarantor1;
    private javax.swing.JTextField txt_guarantor2;
    private javax.swing.JTextField txt_installments;
    private javax.swing.JTextField txt_loanAmount;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_reference;
    private javax.swing.JTextField txt_rental;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
