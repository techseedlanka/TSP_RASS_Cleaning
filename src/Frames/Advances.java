/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.EMP_Atten.lbl_photo;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Advances extends javax.swing.JFrame {

    /**
     * Creates new form Advances
     */
    private ImageIcon format = null;

    public Advances() {
        initComponents();

        auto_completer();

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);

            while (rs.next()) {
//                String code = rs.getString("EPFno");
                //String Employeecode = rs.getString("EmployeeNo");
//                String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");

                // ta.addItem(code);
                ta.addItem(NameWithInitials);
                // ta.addItem(nic);
                //ta.addItem(Employeecode);
            }

        } catch (Exception e) {
            System.out.println("errr");
            e.printStackTrace();

        }

    }

    String Reference_SA01;
    String ReferenceID_SA01;
    String Reference_SA02;
    String ReferenceID_SA02;
    String Reference_FA;
    String ReferenceID_FA;

    String Status = "on-going";

    private void get_advance_details() {
        if (txt_empid.getText().isEmpty()) {

        } else {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            try {

//                Statement st = DbConnection.getconnection().createStatement();
//                ResultSet rs = st.executeQuery("select * from salary_advance_1 where EPFno ='" + txt_empid.getText() + "' and Status ='on-going'");
//                while (rs.next()) {
//
//                    Vector v = new Vector();
//
//                    v.add(rs.getString("IssueDate"));
//                    v.add(rs.getString("Amount"));
//                    v.add(rs.getString("Note"));
//                    v.add(rs.getString("Reference"));
//                    v.add("Advance 01");
//
//                    dtm.addRow(v);
//                }
                Statement st1 = DbConnection.getconnection().createStatement();
                ResultSet rs1 = st1.executeQuery("select * from salary_advance_2 where EPFno ='" + txt_empid.getText() + "' and Status ='on-going'");
                while (rs1.next()) {

                    Vector v = new Vector();

                    v.add(rs1.getString("IssueDate"));
                    v.add(rs1.getString("Amount"));
                    v.add(rs1.getString("Note"));
                    v.add(rs1.getString("Reference"));
                    v.add("Advance 02");

                    dtm.addRow(v);
                }

                Statement st2 = DbConnection.getconnection().createStatement();
                ResultSet rs2 = st2.executeQuery("select * from salary_advance_festival where EPFno ='" + txt_empid.getText() + "' and Status ='on-going' ");
                while (rs2.next()) {

//                    String note = rs2.getString("Note");
//                    String festiv_note = "Festival_Advance: ";
//                    String set_note = festiv_note+note;
                    Vector v = new Vector();

                    v.add(rs2.getString("IssueDate"));
                    v.add(rs2.getString("Amount"));
                    v.add(rs2.getString("Note"));
                    v.add(rs2.getString("Reference"));
                    v.add("Festival Advance");

                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void clear() {

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        ReferenceID_SA01 = "";
        ReferenceID_SA02 = "";
        ReferenceID_FA = "";

        Reference_SA01 = "";
        Reference_SA02 = "";
        Reference_FA = "";

//        date_1st_SA01.setDate(null);
//        date_1st_SA02.setDate(null);
        date_1st_FA.setDate(null);

//        date_issueOn_SA01.setDate(null);
//        date_issueOn1_SA02.setDate(null);
//        date_issueOn_FA.setDate(null);
//        date_last_SA01.setDate(null);
//        date_last_SA02.setDate(null);
        date_last_FA.setDate(null);

        txt_empid.setText("");
        txt_empNic.setText("");
        txt_rank.setText("");
        txt_name.setText("");

        txt_amount_SA01.setText("");
        txt_Amount_SA02.setText("");
        txt_Amount_FA.setText("");

//        txt_ins_SA01.setText("");
//        txt_ins_SA02.setText("");
        txt_ins_FA.setText("");
//
//        txt_rental_SA01.setText("");
//        txt_rental_SA02.setText("");
        txt_rental_FA.setText("");

//        txt_handOverTo_SA01.setText("");
//        txt_handOverTo_SA02.setText("");
        

//        txt_issuedToName.setText("");
//        txt_issuedToName1.setText("");
       

        txt_note_SA01.setText("");
        txt_note_SA02.setText("");
        txt_note_FA.setText("");

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
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_note_SA01 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        date_issueOn_SA01 = new com.toedter.calendar.JDateChooser();
        txt_amount_SA01 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_note_SA02 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        date_issueOn1_SA02 = new com.toedter.calendar.JDateChooser();
        txt_Amount_SA02 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_note_FA = new javax.swing.JTextField();
        date_1st_FA = new com.toedter.calendar.JDateChooser();
        txt_ins_FA = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        date_last_FA = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_Amount_FA = new javax.swing.JTextField();
        txt_rental_FA = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        date_issueOn_FA = new com.toedter.calendar.JDateChooser();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_empid = new javax.swing.JTextField();
        txt_empNic = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_salaryAdvance01 = new javax.swing.JButton();
        btn_salaryAdvance02 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btn_salaryAdvanceFestival = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        lbl_reference_fa = new javax.swing.JLabel();
        lbl_reference_SA1 = new javax.swing.JLabel();
        lbl_reference_SA2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        cmb_sal_year_SA01 = new javax.swing.JComboBox();
        cmb_sal_month_SA01 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmb_sal_year_SA02 = new javax.swing.JComboBox();
        cmb_sal_month_SA02 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Advances");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 1030, 10));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Issue Date :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, 20));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Salary Month / Year :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));

        txt_note_SA01.setEditable(false);
        txt_note_SA01.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_note_SA01, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 130, 40));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Note  :-");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, 20));

        date_issueOn_SA01.setBackground(new java.awt.Color(255, 255, 255));
        date_issueOn_SA01.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        date_issueOn_SA01.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                date_issueOn_SA01ComponentAdded(evt);
            }
        });
        date_issueOn_SA01.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_issueOn_SA01PropertyChange(evt);
            }
        });
        getContentPane().add(date_issueOn_SA01, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 120, 20));

        txt_amount_SA01.setEditable(false);
        txt_amount_SA01.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        txt_amount_SA01.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_amount_SA01FocusGained(evt);
            }
        });
        getContentPane().add(txt_amount_SA01, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 90, 25));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("Issue Date :-");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, 20));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel16.setText("Amount :-");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, 20));

        txt_note_SA02.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        getContentPane().add(txt_note_SA02, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 130, 40));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Note  :-");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, 20));

        date_issueOn1_SA02.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        date_issueOn1_SA02.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_issueOn1_SA02PropertyChange(evt);
            }
        });
        getContentPane().add(date_issueOn1_SA02, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 120, 20));

        txt_Amount_SA02.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txt_Amount_SA02.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Amount_SA02FocusGained(evt);
            }
        });
        getContentPane().add(txt_Amount_SA02, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 90, 25));

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel25.setText("Installmens :-");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, -1, 20));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setText("1st Installment :-");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 320, -1, 20));

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel27.setText("Amount :-");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, -1, 20));

        txt_note_FA.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_note_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 400, 200, 25));

        date_1st_FA.setToolTipText("1st Installment");
        getContentPane().add(date_1st_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 120, 20));

        txt_ins_FA.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ins_FA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ins_FAActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ins_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 60, 25));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel29.setText("Note  :-");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 400, -1, 20));

        date_last_FA.setToolTipText("Last Installment");
        getContentPane().add(date_last_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 120, 20));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("Last Installment :-");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 110, 20));

        jLabel31.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel31.setText("Rental :-");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 280, -1, 20));

        txt_Amount_FA.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Amount_FA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Amount_FAFocusGained(evt);
            }
        });
        txt_Amount_FA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Amount_FAActionPerformed(evt);
            }
        });
        getContentPane().add(txt_Amount_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, 90, 25));

        txt_rental_FA.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_rental_FA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rental_FAFocusGained(evt);
            }
        });
        getContentPane().add(txt_rental_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 100, 25));

        jLabel32.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel32.setText("Issue Date :-");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, -1, 20));
        getContentPane().add(date_issueOn_FA, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 120, 20));

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 75, 280, 30));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Employee EPF No.  :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 130, 21));

        txt_empNic.setEditable(false);
        txt_empNic.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_empNic, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 130, 21));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText("Employee NIC No.  :-");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        txt_name.setEditable(false);
        txt_name.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 200, 21));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Name :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Rank :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, -1, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 90, 21));

        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Employee Search (Name/ID) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 12))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 330, 70));

        btn_salaryAdvance01.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_salaryAdvance01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_salaryAdvance01.setText("Save");
        btn_salaryAdvance01.setEnabled(false);
        btn_salaryAdvance01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salaryAdvance01ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salaryAdvance01, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 105, 40));

        btn_salaryAdvance02.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_salaryAdvance02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_salaryAdvance02.setText("Save");
        btn_salaryAdvance02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salaryAdvance02ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salaryAdvance02, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 105, 40));

        jButton6.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, 105, 40));

        btn_salaryAdvanceFestival.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_salaryAdvanceFestival.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_salaryAdvanceFestival.setText("Save");
        btn_salaryAdvanceFestival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salaryAdvanceFestivalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salaryAdvanceFestival, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 460, 130, 50));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1030, 10));

        lbl_reference_fa.setText("reference");
        getContentPane().add(lbl_reference_fa, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 80, -1));

        lbl_reference_SA1.setText("reference");
        getContentPane().add(lbl_reference_SA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 80, -1));

        lbl_reference_SA2.setText("reference");
        getContentPane().add(lbl_reference_SA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 80, -1));

        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Festival Advance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 16))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 340, 410));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(204, 0, 0));
        jLabel34.setText("*");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 30, -1));

        jLabel35.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(204, 0, 0));
        jLabel35.setText("*");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 30, -1));

        jLabel40.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 0, 0));
        jLabel40.setText("*");
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 30, -1));

        jLabel41.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 0, 0));
        jLabel41.setText("*");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 30, -1));

        jLabel46.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(204, 0, 0));
        jLabel46.setText("*");
        getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 160, 30, -1));

        jLabel47.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(204, 0, 0));
        jLabel47.setText("*");
        getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 200, 30, -1));

        jLabel48.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(204, 0, 0));
        jLabel48.setText("*");
        getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 240, 30, -1));

        jLabel49.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(204, 0, 0));
        jLabel49.setText("*");
        getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 280, 30, -1));

        jLabel50.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 0, 0));
        jLabel50.setText("*");
        getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 30, -1));

        jLabel51.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(204, 0, 0));
        jLabel51.setText("*");
        getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 360, 30, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setText("View Photo");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 60, 100, 20));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Amount", "Note", "Reference", "Advance Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 650, 200));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText("Amount :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        cmb_sal_year_SA01.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_sal_year_SA01.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
        getContentPane().add(cmb_sal_year_SA01, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 70, -1));

        cmb_sal_month_SA01.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_sal_month_SA01.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_sal_month_SA01.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_sal_month_SA01PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_sal_month_SA01, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 110, -1));

        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Salary Advance - 01", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 16))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 340, 200));

        cmb_sal_year_SA02.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_sal_year_SA02.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
        getContentPane().add(cmb_sal_year_SA02, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 70, -1));

        cmb_sal_month_SA02.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_sal_month_SA02.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_sal_month_SA02, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 110, -1));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Salary Month / Year :-");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, 20));

        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Salary Advance - 02 ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 16))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 340, 200));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained

    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg");
                while (rs.next()) {
                    String code = rs.getString("EmployeeNo");
                    String nic = rs.getString("NIC");
                    String name = rs.getString("NameWithInitials");

                    String rank = rs.getString("Designation");

                    byte[] imagedata = rs.getBytes("EMPImage");

                    if (txt_search.getText().equals(code) || txt_search.getText().equals(name) || txt_search.getText().equals(nic)) {

                        if (imagedata == null) {

                            format = new ImageIcon("imagedata");

                        } else {

                            format = new ImageIcon(imagedata);

                        }

                        txt_empid.setText(code);
                        txt_name.setText(name);
                        txt_empNic.setText(nic);

                        txt_rank.setText(rank);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            get_advance_details();
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void btn_salaryAdvance01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salaryAdvance01ActionPerformed

        if (txt_empid.getText().isEmpty() | txt_amount_SA01.getText().isEmpty() | date_issueOn_SA01.getDate() == null) {

            JOptionPane.showMessageDialog(rootPane, "Please fill the all SATR(*) marked fields to continue");

        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "<html>Make Sure the Selected 'Salary Month/Year' is Correct.<br>Do You Want to Save?</html> ", Status, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {

                get_month_and_year_in_SA01();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String issueOn = sdf.format(date_issueOn_SA01.getDate());
//            String firstIns = sdf.format(date_1st_SA01.getDate());
//            String lastIns = sdf.format(date_last_SA01.getDate());

                try {

                    Statement st = DbConnection.getconnection().createStatement();
                    get_SA01_Reference();
                    st.executeUpdate("insert into salary_advance_1 values('" + txt_empid.getText() + "','" + txt_name.getText() + "','" + txt_rank.getText() + "','" + issueOn + "','" + txt_amount_SA01.getText() + "','" + txt_note_SA01.getText() + "','" + ReferenceID_SA01 + "','" + Reference_SA01 + "','" + Status + "','null','null','" + cmb_sal_month_SA01.getSelectedItem().toString() + "','" + cmb_sal_year_SA01.getSelectedItem().toString() + "')");
                    JOptionPane.showMessageDialog(rootPane, " Saved... ");

                    clear();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

            }
        }

    }//GEN-LAST:event_btn_salaryAdvance01ActionPerformed

    private void btn_salaryAdvance02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salaryAdvance02ActionPerformed

        if (txt_empid.getText().isEmpty() | txt_Amount_SA02.getText().isEmpty() | date_issueOn1_SA02.getDate() == null) {

            JOptionPane.showMessageDialog(rootPane, "Please fill the all SATR(*) marked fields to continue");

        } else {

            get_month_and_year_in_SA02();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String issueOn = sdf.format(date_issueOn1_SA02.getDate());
//            String firstIns = sdf.format(date_1st_SA02.getDate());
//            String lastIns = sdf.format(date_last_SA02.getDate());

            try {

                Statement st = DbConnection.getconnection().createStatement();
                get_SA02_Reference();
                st.executeUpdate("insert into salary_advance_2 values('" + txt_empid.getText() + "','" + txt_name.getText() + "','" + txt_rank.getText() + "','" + issueOn + "','" + txt_Amount_SA02.getText() + "','" + txt_note_SA02.getText() + "','" + ReferenceID_SA02 + "','" + Reference_SA02 + "','" + Status + "','null','null','" + cmb_sal_month_SA02.getSelectedItem().toString() + "','" + cmb_sal_year_SA02.getSelectedItem().toString() + "')");
                JOptionPane.showMessageDialog(rootPane, " Saved... ");
            } catch (Exception e) {
                e.printStackTrace();
            }

            clear();
        }
    }//GEN-LAST:event_btn_salaryAdvance02ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        clear();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_salaryAdvanceFestivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salaryAdvanceFestivalActionPerformed
    
        if (txt_empid.getText().isEmpty() | txt_Amount_FA.getText().isEmpty() | txt_rental_FA.getText().isEmpty() | date_issueOn_FA.getDate() == null | date_1st_FA.getDate() == (null) | date_last_FA.getDate() == (null)) {

            JOptionPane.showMessageDialog(rootPane, "Please fill the all SATR(*) marked fields to continue");

        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String issueOn = sdf.format(date_issueOn_FA.getDate());
            String firstIns = sdf.format(date_1st_FA.getDate());
            String lastIns = sdf.format(date_last_FA.getDate());

            try {

                Statement st = DbConnection.getconnection().createStatement();
                get_FA_Reference();
                st.executeUpdate("insert into salary_advance_festival values('" + txt_empid.getText() + "','" + issueOn + "','" + txt_Amount_FA.getText() + "','" + txt_ins_FA.getText() + "','" + txt_rental_FA.getText() + "','" + firstIns + "','" + lastIns + "',' ','" + txt_note_FA.getText() + "','" + ReferenceID_FA + "','" + Reference_FA + "','" + Status + "')");

            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(rootPane, " Saved... ");
            clear();
        }
    }//GEN-LAST:event_btn_salaryAdvanceFestivalActionPerformed

    private void txt_amount_SA01FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_amount_SA01FocusGained
        get_SA01_Reference();
    }//GEN-LAST:event_txt_amount_SA01FocusGained

    private void txt_Amount_SA02FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Amount_SA02FocusGained
        get_SA02_Reference();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Amount_SA02FocusGained

    private void txt_Amount_FAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Amount_FAFocusGained
        get_FA_Reference();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Amount_FAFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(format);
        pt.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void date_issueOn_SA01ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_date_issueOn_SA01ComponentAdded

    }//GEN-LAST:event_date_issueOn_SA01ComponentAdded

    private void date_issueOn_SA01PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_issueOn_SA01PropertyChange
        get_month_and_year_in_SA01();        // TODO add your handling code here:
    }//GEN-LAST:event_date_issueOn_SA01PropertyChange

    private void date_issueOn1_SA02PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_issueOn1_SA02PropertyChange
        get_month_and_year_in_SA02();        // TODO add your handling code here:
    }//GEN-LAST:event_date_issueOn1_SA02PropertyChange

    private void cmb_sal_month_SA01PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_sal_month_SA01PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_sal_month_SA01PopupMenuWillBecomeInvisible

    private void txt_Amount_FAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Amount_FAActionPerformed
        txt_ins_FA.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Amount_FAActionPerformed

    private void txt_ins_FAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ins_FAActionPerformed
        txt_rental_FA.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ins_FAActionPerformed

    private void txt_rental_FAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rental_FAFocusGained

        if (txt_Amount_FA.getText().isEmpty() | txt_ins_FA.getText().isEmpty()) {

        } else {
            Double amt = Double.parseDouble(txt_Amount_FA.getText());
            Double ins = Double.parseDouble(txt_ins_FA.getText());
            Double rent = amt / ins;
            txt_rental_FA.setText(Double.toString(rent));
        }


    }//GEN-LAST:event_txt_rental_FAFocusGained

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
            java.util.logging.Logger.getLogger(Advances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Advances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Advances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Advances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Advances().setVisible(true);
            }
        });
    }

    private void get_month_and_year_in_SA01() {

        Date date = date_issueOn_SA01.getDate();
        if (date == null) {
            jLabel2.setForeground(Color.red);

        } else {

            int m = date_issueOn_SA01.getDate().getMonth();

            int y = date_issueOn_SA01.getCalendar().get(Calendar.YEAR);

            int d = m + 1;
            String d1 = null;

            if (d == 2) {
                d1 = "February";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 3) {
                d1 = "March";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 4) {
                d1 = "April";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 5) {
                d1 = "May";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 6) {
                d1 = "June";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 7) {
                d1 = "July";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 8) {
                d1 = "August";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 9) {
                d1 = "September";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 10) {
                d1 = "October";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 11) {
                d1 = "November";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 12) {
                d1 = "December";
                String year = Integer.toString(y);
                cmb_sal_year_SA01.setSelectedItem(year);
            }
            if (d == 1) {
                d1 = "January";
                int YEAR = y + 1;
                String year = Integer.toString(YEAR);
                cmb_sal_year_SA01.setSelectedItem(year);
            }

            cmb_sal_month_SA01.setSelectedItem(d1);

        }

    }

    private void get_month_and_year_in_SA02() {

        Date date = date_issueOn1_SA02.getDate();
        if (date == null) {
            jLabel2.setForeground(Color.red);

        } else {

            int m = date_issueOn1_SA02.getDate().getMonth();

            int y = date_issueOn1_SA02.getCalendar().get(Calendar.YEAR);

            int d = m + 1;
            String d1 = null;

            if (d == 2) {
                d1 = "February";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 3) {
                d1 = "March";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 4) {
                d1 = "April";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 5) {
                d1 = "May";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 6) {
                d1 = "June";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 7) {
                d1 = "July";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 8) {
                d1 = "August";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 9) {
                d1 = "September";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 10) {
                d1 = "October";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 11) {
                d1 = "November";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);
            }
            if (d == 12) {
                d1 = "December";
                String year = Integer.toString(y);
                cmb_sal_year_SA02.setSelectedItem(year);

            }
            if (d == 1) {
                d1 = "January";
                int YEAR = y + 1;
                String year = Integer.toString(YEAR);
                cmb_sal_year_SA02.setSelectedItem(year);
            }

            cmb_sal_month_SA02.setSelectedItem(d1);

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_salaryAdvance01;
    private javax.swing.JButton btn_salaryAdvance02;
    private javax.swing.JButton btn_salaryAdvanceFestival;
    private javax.swing.JComboBox cmb_sal_month_SA01;
    private javax.swing.JComboBox cmb_sal_month_SA02;
    private javax.swing.JComboBox cmb_sal_year_SA01;
    private javax.swing.JComboBox cmb_sal_year_SA02;
    private com.toedter.calendar.JDateChooser date_1st_FA;
    private com.toedter.calendar.JDateChooser date_issueOn1_SA02;
    private com.toedter.calendar.JDateChooser date_issueOn_FA;
    private com.toedter.calendar.JDateChooser date_issueOn_SA01;
    private com.toedter.calendar.JDateChooser date_last_FA;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_reference_SA1;
    private javax.swing.JLabel lbl_reference_SA2;
    private javax.swing.JLabel lbl_reference_fa;
    private javax.swing.JTextField txt_Amount_FA;
    private javax.swing.JTextField txt_Amount_SA02;
    private javax.swing.JTextField txt_amount_SA01;
    private javax.swing.JTextField txt_empNic;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_ins_FA;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_note_FA;
    private javax.swing.JTextField txt_note_SA01;
    private javax.swing.JTextField txt_note_SA02;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_rental_FA;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables

    private void get_SA01_Reference() {

        try {

            if (txt_empid.getText().isEmpty()) {

            } else {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select *,MAX(ReferenceID),COUNT(Reference) from salary_advance_1");

                while (rs.next()) {

                    int count = Integer.parseInt(rs.getString("COUNT(Reference)"));

                    if (count == 0) {

                        ReferenceID_SA01 = "0001";
                        Reference_SA01 = "SA01-0001";
                    } else {

                        int max = Integer.parseInt(rs.getString("MAX(ReferenceID)"));
                        int x = max + 0001;

                        ReferenceID_SA01 = String.format("%04d", x);
                        Reference_SA01 = "SA01-" + ReferenceID_SA01;

                    }

                }

            }

            lbl_reference_SA1.setText(Reference_SA01);

//            System.out.println("ref id :"+ReferenceID_SA01+"    ref :"+Reference_SA01);
        } catch (Exception e) {
        }

    }

    private void get_SA02_Reference() {

        try {

            if (txt_empid.getText().isEmpty()) {

            } else {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select *,MAX(ReferenceID),COUNT(Reference) from salary_advance_2");

                while (rs.next()) {

                    int count = Integer.parseInt(rs.getString("COUNT(Reference)"));

                    if (count == 0) {

                        ReferenceID_SA02 = "0001";
                        Reference_SA02 = "SA02-0001";
                    } else {

                        int max = Integer.parseInt(rs.getString("MAX(ReferenceID)"));
                        int x = max + 0001;

                        ReferenceID_SA02 = String.format("%04d", x);
                        Reference_SA02 = "SA02-" + ReferenceID_SA02;

                    }

                }

            }

//            System.out.println("ref id :"+ReferenceID_SA01+"    ref :"+Reference_SA01);
            lbl_reference_SA2.setText(Reference_SA02);

        } catch (Exception e) {
        }

    }

    private void get_FA_Reference() {

        try {

            if (txt_empid.getText().isEmpty()) {

            } else {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select *,MAX(ReferenceID),COUNT(Reference) from salary_advance_festival");

                while (rs.next()) {

                    int count = Integer.parseInt(rs.getString("COUNT(Reference)"));

                    if (count == 0) {

                        ReferenceID_FA = "0001";
                        Reference_FA = "FA01-0001";
                    } else {

                        int max = Integer.parseInt(rs.getString("MAX(ReferenceID)"));
                        int x = max + 0001;

                        ReferenceID_FA = String.format("%04d", x);
                        Reference_FA = "FA01-" + ReferenceID_FA;

                    }

                }

            }

//            System.out.println("ref id :"+ReferenceID_SA01+"    ref :"+Reference_SA01);
            lbl_reference_fa.setText(Reference_FA);

        } catch (Exception e) {
        }

    }

}
