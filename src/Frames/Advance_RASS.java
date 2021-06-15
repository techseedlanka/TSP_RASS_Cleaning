/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.PopUp_Emp_Table.POPUP_EMP_TABLE;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class Advance_RASS extends javax.swing.JFrame {

    /**
     * Creates new form Advance_RASS
     */
    public Advance_RASS() {
        initComponents();
        get_Location_Details();
        TitleBar();
        get_payTypes();
        auto_completer();
        jTable1.getTableHeader().setDefaultRenderer(new Table_Header.HeaderColor());
        txt_locNo.setVisible(false);
        advance_doc_no();
        txt_ADVANCE_empName.grabFocus();
    }

    private void get_payTypes() {
        try {

            String[] theSeven = {"Hand", "Bank", "Slip"};
            JComboBox combo1 = new JComboBox(theSeven);

            TableColumn col = jTable1.getColumnModel().getColumn(5);
            col.setCellEditor(new DefaultCellEditor(combo1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_ADVANCE_empName);

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

    private void TitleBar() {
        this.setTitle("Advance");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
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

    private void advance_doc_no() {

        try {

            Connection con = DbConnection.getconnection();
            String sql = "select MAX(ReferenceID) from salary_advance_1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                int docNo = 0;

                if ((rs.getString("MAX(ReferenceID)")) == null) {

                    docNo = 00001;

                } else {

                    int I_max = Integer.parseInt(rs.getString("MAX(ReferenceID)"));
                    docNo = I_max + 00001;

                }
                String doc = String.format("%05d", docNo);

                txt_doc_no.setText(doc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void emp_check() {
        //in Table
        int row_count = jTable1.getRowCount();
        System.out.println(row_count);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int row = 0; row < row_count; row++) {

            String EPFno = jTable1.getValueAt(row, 2).toString();

            String New_EPF = txt_EmployeeNo.getText();

            if (EPFno.equals(New_EPF)) {

                JOptionPane.showMessageDialog(rootPane, txt_EmployeeNo.getText() + "- Selected employee already in the table.  ");
                txt_EmployeeNo.setText("");
            } else {

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
        jSeparator7 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_doc_no = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        txt_advAmt = new javax.swing.JTextField();
        txt_EmployeeNo = new javax.swing.JTextField();
        lbl_locCode = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        txt_locNo = new javax.swing.JTextField();
        txt_ADVANCE_empName = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jButton17 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_locName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Advances");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 850, 10));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setText("Location :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 40));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 390, -1));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 90, 23));

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
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021" }));
        cmb_year.setSelectedIndex(2);
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

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText(" Doc. No  :-");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 80, 70, 20));

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

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Unit", "Rank", "EMP. No.", "Name", "Advance", "PayType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(420);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 700, 470));

        jButton10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        jButton10.setText("Load Employees");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 260, 50));

        jButton12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        jButton12.setText("Add Emp.");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(717, 570, -1, 50));

        jButton14.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton14.setText("Save");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 120, 70));

        jButton13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton13.setText("Print");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 290, 120, 50));

        txt_advAmt.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        txt_advAmt.setForeground(new java.awt.Color(102, 102, 102));
        txt_advAmt.setText("Advance Amount");
        txt_advAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_advAmtFocusGained(evt);
            }
        });
        txt_advAmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_advAmtActionPerformed(evt);
            }
        });
        txt_advAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_advAmtKeyPressed(evt);
            }
        });
        getContentPane().add(txt_advAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 590, 130, 25));

        txt_EmployeeNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_EmployeeNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EmployeeNoKeyPressed(evt);
            }
        });
        getContentPane().add(txt_EmployeeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, 60, 25));

        lbl_locCode.setBackground(new java.awt.Color(204, 204, 204));
        lbl_locCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_locCode.setOpaque(true);
        getContentPane().add(lbl_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, 40, 20));

        txt_rank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_rank.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rankFocusGained(evt);
            }
        });
        txt_rank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rankActionPerformed(evt);
            }
        });
        txt_rank.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rankKeyPressed(evt);
            }
        });
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 90, 25));

        txt_locNo.setEditable(false);
        txt_locNo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(txt_locNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 590, 20, 25));

        txt_ADVANCE_empName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ADVANCE_empName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ADVANCE_empNameFocusGained(evt);
            }
        });
        txt_ADVANCE_empName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ADVANCE_empNameActionPerformed(evt);
            }
        });
        txt_ADVANCE_empName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ADVANCE_empNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_ADVANCE_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, 270, 25));

        jButton16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton16.setText("Remove");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 120, 50));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 850, 10));

        jButton17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton17.setText("Clear All");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 120, 50));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 120, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Employee :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, 20));

        lbl_locName.setBackground(new java.awt.Color(204, 204, 204));
        lbl_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_locName.setOpaque(true);
        getContentPane().add(lbl_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 290, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                ResultSet rs = st.executeQuery("SELECT * from location_reg where    LocStatus='1' and LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
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

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void txt_doc_noFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_doc_noFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_noFocusGained

    private void txt_doc_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_doc_noFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_noFocusLost

    private void get_initial_advance_details() {

        try {

            String loc = txt_locCode.getText();

            if (loc.equals("N/A") | loc.isEmpty()) {
            } else {
                Connection con = DbConnection.getconnection();
                String sql = "select * from employee_reg where DefLocation='" + loc + "'";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                while (rs.next()) {
                    String rank = rs.getString("Designation");
                    Vector v = new Vector();
                    v.add(txt_locCode.getText());
                    v.add(rank);
                    v.add(rs.getString("EmployeeNo"));
                    v.add(rs.getString("NameWithInitials"));

                    String sql1 = "select * from salary_rates where LocCode='" + loc + "' and RankCode='" + rank + "'";

                    String Adv = "0.00";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery();

                    while (rs1.next()) {
                        Adv = rs1.getString("Advance");

                        if (Adv == null | Adv.equals("0.00")) {
                            Adv = "0.00";
                        } else {
                            Adv = rs1.getString("Advance");
                        }

                    }
                    v.add(Adv);
                    v.add(rs.getString("PayType"));

                    dtm.addRow(v);

                }
            }

            //find null cells and replace with 0.00
            int row = jTable1.getModel().getRowCount();
            for (int i = 0; row > i; i++) {

                Object adv_amt = jTable1.getModel().getValueAt(i, 4);

                if (adv_amt == null) {
                    jTable1.getModel().setValueAt("0.00", i, 4);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void get_saved_advance_details() {
        try {
            Connection con = DbConnection.getconnection();
            String sql = "select *  from salary_advance_1 where PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear ='" + cmb_year.getSelectedItem().toString() + "' and Location='" + txt_locCode.getText() + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                String rank = rs.getString("Rank");

                Vector v = new Vector();
                v.add(txt_locCode.getText());
                v.add(rank);
                v.add(rs.getString("EPFno"));
                v.add(rs.getString("Name"));

                String Adv = rs.getString("Amount");
                System.out.println(Adv);
                if (Adv == null | Adv.equals("0.00")) {
                    v.add("0.00");
                } else {
                    v.add(Adv);
                }
                v.add(rs.getString("Note"));

                dtm.addRow(v);
                txt_doc_no.setText(rs.getString("ReferenceID"));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void emp_Advance_details() {
        try {

            Connection con = DbConnection.getconnection();
            String sql = "select *,COUNT(*) from salary_advance_1 where PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear ='" + cmb_year.getSelectedItem().toString() + "' and Location='" + txt_locCode.getText() + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                int count = rs.getInt("COUNT(*)");
                if (count == 0) {
                    get_initial_advance_details();
                } else {
                    get_saved_advance_details();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get_one_emp() {
        emp_check();
        String name = txt_ADVANCE_empName.getText();
        String epf = txt_EmployeeNo.getText();
        String rank = txt_rank.getText();
        String locno = txt_locNo.getText();
        String adv = txt_advAmt.getText();

        if (locno.isEmpty() | epf.isEmpty() | adv.isEmpty() | adv.equals("Advance Amount") | rank.equals("N/A")) {

        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            Vector v = new Vector();
            v.add(locno);
            v.add(rank);
            v.add(epf);
            v.add(name);
            v.add(adv);
            v.add(pay_type);
            dtm.addRow(v);

            txt_ADVANCE_empName.setText("");
            txt_EmployeeNo.setText("");
            txt_rank.setText("");
            txt_locNo.setText("");
            txt_advAmt.setText("");
            pay_type = "";

            txt_ADVANCE_empName.grabFocus();
        }

    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        emp_Advance_details();

        jButton10.setEnabled(false);
        cmb_defLocation.setEnabled(false);


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        get_one_emp();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {

            Connection con = DbConnection.getconnection();

//            String up = "update salary_advance_1 set Status ='OLD' where PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "' and Location='" + txt_locCode.getText() + "' ";
//            PreparedStatement pstup = con.prepareStatement(up);
//            pstup.execute();
            String sql = "insert into salary_advance_1 (EPFno,Name,Rank,IssueDate,Amount,Note,ReferenceID,Reference,Status,Location,LocType,PayMonth,PayYear) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = con.prepareStatement(sql);

            int nrow = jTable1.getModel().getRowCount();

            if (nrow == 0) {

            } else {
                for (int i = 0; nrow > i; i++) {

                    String loc = jTable1.getModel().getValueAt(i, 0).toString();
                    String rank = jTable1.getModel().getValueAt(i, 1).toString();
                    String empid = jTable1.getModel().getValueAt(i, 2).toString();
                    String name = jTable1.getModel().getValueAt(i, 3).toString();

                    String up = "update salary_advance_1 set Status ='OLD' where PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "' and Location='" + txt_locCode.getText() + "' and EPFno='" + empid + "' ";
                    PreparedStatement pstup = con.prepareStatement(up);
                    pstup.execute();

                    String amount = jTable1.getModel().getValueAt(i, 4).toString();
                    Double d = Double.parseDouble(amount);
                    String Adv = String.format("%.2f", d);

                    String paytype = jTable1.getModel().getValueAt(i, 5).toString();

                    pst.setString(1, empid);//empid
                    pst.setString(2, name);//name
                    pst.setString(3, rank);//rank
                    pst.setString(4, "");//date
                    pst.setString(5, Adv);//amonut
                    pst.setString(6, paytype);//note
                    pst.setString(7, txt_doc_no.getText());//ref ID
                    pst.setString(8, txt_doc_no.getText() + "_" + empid);//Reference
                    pst.setString(9, "PAID");//stat
                    pst.setString(10, loc);//loc
                    pst.setString(11, "");//loc type
                    pst.setString(12, cmb_month.getSelectedItem().toString());//month
                    pst.setString(13, cmb_year.getSelectedItem().toString());//year

                    pst.addBatch();

                }

                pst.executeBatch();

                String del = "delete from salary_advance_1 where Status='OLD' and Location='" + txt_locCode.getText() + "' ";
                PreparedStatement pst_del = con.prepareStatement(del);
                pst_del.execute();

                JOptionPane.showMessageDialog(rootPane, "Advance Saved...!");

                jButton17ActionPerformed(evt);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        RPT_Advance ad = new RPT_Advance();
        ad.setVisible(true);
        RPT_Advance.cmb_month.setSelectedItem(cmb_month.getSelectedItem());
        RPT_Advance.cmb_year.setSelectedItem(cmb_year.getSelectedItem());
        RPT_Advance.cmb_defLocation.setSelectedItem(cmb_defLocation.getSelectedItem());
        RPT_Advance.txt_locCode.setText(txt_locCode.getText());

//        try {
//
//            Connection conn = (Connection) DbConnection.getconnection();
//
//            JasperDesign jd = JRXmlLoader.load("Reports\\Advance_LocationWise.jrxml");
//            //String sql = "SELECT * FROM salary_advance_1  where IssueDate between '" + FromDate + "' and '" + ToDate + "' order by EPFno ";
//
//            int i = Integer.parseInt(txt_doc_no.getText());
//            int x = i - 00001;
//            String ref = String.format("%05d", x);
//            System.out.println(ref);
//
//            String sql = "SELECT * FROM salary_advance_1 JOIN location_reg ON salary_advance_1.Location=location_reg.LocCode JOIN company_reg ON location_reg.LocType=company_reg.ComCode WHERE salary_advance_1.ReferenceID='" + ref + "'  ";
//
//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(sql);
//            jd.setQuery(newQuery);
//
//            JasperReport jr = JasperCompileManager.compileReport(jd);
//            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
//            JasperViewer.viewReport(jp, false);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txt_advAmtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_advAmtFocusGained

        if (txt_advAmt.getText().equals("Advance Amount")) {
            txt_advAmt.setText("");
            txt_advAmt.setForeground(Color.BLACK);
            Font font = new Font("Times New Roman", Font.PLAIN, 14);
            txt_advAmt.setFont(font);
        } else {

        }

        //txt_search.setFont("Times New Roman 14 Plain");

    }//GEN-LAST:event_txt_advAmtFocusGained

    private void txt_advAmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_advAmtActionPerformed

    }//GEN-LAST:event_txt_advAmtActionPerformed

    private void txt_advAmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_advAmtKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            get_one_emp();
        }


    }//GEN-LAST:event_txt_advAmtKeyPressed

    private void txt_rankFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rankFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankFocusGained

    private void txt_rankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankActionPerformed

    private void txt_rankKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rankKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankKeyPressed

    private void txt_ADVANCE_empNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ADVANCE_empNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ADVANCE_empNameFocusGained

    private void txt_ADVANCE_empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ADVANCE_empNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ADVANCE_empNameActionPerformed
    public String pay_type = "Cash";
    private void txt_ADVANCE_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ADVANCE_empNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                String search = txt_ADVANCE_empName.getText();

                if (search.isEmpty() | txt_locCode.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please select a Loctaion");

                } else {

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + search + "' OR NameWithInitials='" + search + "'   ");
                    while (rs3.next()) {

                        if ((rs3.getInt("COUNT(*)") > 1)) {
                            PopUp_Emp_Table pt = new PopUp_Emp_Table();
                            pt.setVisible(true);
                            pt.setTitle("Advance");

                            DefaultTableModel dtm = (DefaultTableModel) POPUP_EMP_TABLE.getModel();

                            Statement st4 = DbConnection.getconnection().createStatement();
                            ResultSet rs4 = st4.executeQuery("select * from employee_reg where EmployeeNo='" + search + "' OR NameWithInitials='" + search + "'   ");
                            while (rs4.next()) {
                                Vector v = new Vector();
                                v.add(rs4.getString("EmployeeNo"));
                                v.add(rs4.getString("NameWithInitials"));
                                String defloc = rs4.getString("DefLocation");
                                v.add(defloc);

                                Statement st5 = DbConnection.getconnection().createStatement();
                                ResultSet rs5 = st5.executeQuery("select * from location_reg where LocCode='" + defloc + "'  ");
                                while (rs5.next()) {

                                    v.add(rs5.getString("LocName"));
                                }

                                dtm.addRow(v);
                                PopUp_Emp_Table.POPUP_EMP_TABLE.grabFocus();
                            }

                        } else {
                            Statement st = DbConnection.getconnection().createStatement();
                            ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + search + "'  OR NameWithInitials='" + search + "'  ");
                            while (rs.next()) {

                                String code = rs.getString("EmployeeNo");

                                String name = rs.getString("NameWithInitials");
                                String loc = rs.getString("DefLocation");
                                String rank = rs.getString("Designation");
                                String pay = rs.getString("PayType");
                                pay_type = pay;
                                txt_EmployeeNo.setText(code);
                                txt_ADVANCE_empName.setText(name);
                                //jLabel2.setText(name);

                                txt_rank.setText(rank);
                                txt_locNo.setText(txt_locCode.getText());
                                lbl_locCode.setText(loc);

                                Statement st1 = DbConnection.getconnection().createStatement();
                                ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'  ");
                                while (rs1.next()) {

                                    lbl_locName.setText(rs1.getString("LocName"));
                                }
                                txt_advAmt.grabFocus();

                            }

                        }

                    }

                }
            } catch (Exception e) {
                System.out.println("eddddddr");
                e.printStackTrace();
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_ADVANCE_empNameKeyPressed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int nrow = jTable1.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(jTable1.getSelectedRow());
                }

            } else {

            }

        }


    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int nrow = jTable1.getRowCount();

        int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear the table?", null, JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            if (nrow < 1) {
            } else {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
            }

            txt_ADVANCE_empName.setText("");
            txt_EmployeeNo.setText("");
            txt_rank.setText("");
            txt_locNo.setText("");
            txt_advAmt.setText("");
            cmb_defLocation.setSelectedIndex(0);
//                cmb_month.setSelectedIndex(0);
//                cmb_year.setSelectedIndex(0);
            advance_doc_no();
            txt_locCode.setText("");

            jButton10.setEnabled(true);
            cmb_defLocation.setEnabled(true);
            advance_doc_no();
        } else {

        }


    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        auto_completer();
        get_Location_Details();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        MAIN.jMenuItem27.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        MAIN.jMenuItem27.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void txt_EmployeeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmployeeNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                String search = txt_EmployeeNo.getText();

                if (search.isEmpty() | txt_locCode.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please select a Loctaion");

                } else {

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + search + "'    ");
                    while (rs3.next()) {

                        if ((rs3.getInt("COUNT(*)") > 1)) {
                            PopUp_Emp_Table pt = new PopUp_Emp_Table();
                            pt.setVisible(true);
                            pt.setTitle("Advance");

                            DefaultTableModel dtm = (DefaultTableModel) POPUP_EMP_TABLE.getModel();

                            Statement st4 = DbConnection.getconnection().createStatement();
                            ResultSet rs4 = st4.executeQuery("select * from employee_reg where EmployeeNo='" + search + "'   ");
                            while (rs4.next()) {
                                Vector v = new Vector();
                                v.add(rs4.getString("EmployeeNo"));
                                v.add(rs4.getString("NameWithInitials"));
                                String defloc = rs4.getString("DefLocation");
                                v.add(defloc);

                                Statement st5 = DbConnection.getconnection().createStatement();
                                ResultSet rs5 = st5.executeQuery("select * from location_reg where LocCode='" + defloc + "'  ");
                                while (rs5.next()) {

                                    v.add(rs5.getString("LocName"));
                                }

                                dtm.addRow(v);
                                PopUp_Emp_Table.POPUP_EMP_TABLE.grabFocus();
                            }

                        } else {
                            Statement st = DbConnection.getconnection().createStatement();
                            ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + search + "'    ");
                            while (rs.next()) {

                                String code = rs.getString("EmployeeNo");

                                String name = rs.getString("NameWithInitials");
                                String loc = rs.getString("DefLocation");
                                String rank = rs.getString("Designation");
                                String pay = rs.getString("PayType");
                                pay_type = pay;
                                txt_EmployeeNo.setText(code);
                                txt_ADVANCE_empName.setText(name);
                                //jLabel2.setText(name);

                                txt_rank.setText(rank);
                                txt_locNo.setText(txt_locCode.getText());
                                lbl_locCode.setText(loc);

                                Statement st1 = DbConnection.getconnection().createStatement();
                                ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'  ");
                                while (rs1.next()) {

                                    lbl_locName.setText(rs1.getString("LocName"));
                                }
                                txt_advAmt.grabFocus();

                            }

                        }

                    }

                }
            } catch (Exception e) {
                System.out.println("eddddddr");
                e.printStackTrace();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoKeyPressed

    private void cmb_defLocationPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationPopupMenuCanceled

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Advance_RASS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Advance_RASS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_locCode;
    private javax.swing.JLabel lbl_locName;
    public static javax.swing.JTextField txt_ADVANCE_empName;
    private javax.swing.JTextField txt_EmployeeNo;
    private javax.swing.JTextField txt_advAmt;
    private javax.swing.JTextField txt_doc_no;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_locNo;
    private javax.swing.JTextField txt_rank;
    // End of variables declaration//GEN-END:variables

}
