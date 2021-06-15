/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.PopUp_Emp_Table.POPUP_EMP_TABLE;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * TECHSEED SOLUTIONS
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class Salary_stop_Payment extends javax.swing.JFrame {

    /**
     * Creates new form Salary_stop_Payment
     */
    public Salary_stop_Payment() {
        initComponents();
        get_payTypes();
        TitleBar();
        auto_completer();
        get_Location_Details();
    }

    private void get_payTypes() {
        try {

            String[] theSeven = {"RList", "Hold", "on-going"};
            JComboBox combo1 = new JComboBox(theSeven);

            TableColumn col = table_main.getColumnModel().getColumn(4);
            col.setCellEditor(new DefaultCellEditor(combo1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_RLIST_empName);

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
        this.setTitle("Salary Stop Payment");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void get_saved_stop_details() {
        try {

            DefaultTableModel dtm2 = (DefaultTableModel) table_AutoRList.getModel();
            dtm2.setRowCount(0);
            DefaultTableModel dtm1 = (DefaultTableModel) table_main.getModel();
            dtm1.setRowCount(0);

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            String emp = "0";
            String stat = "0";

            String name = "0";
            String rank = "0";
            String loc = "0";
            String locname = "0";

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("select *  from salary_stop_payment where  Month='" + month + "' and Year='" + year + "' group by EMPno  ");
            ResultSet rs = pst.executeQuery();

            DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                emp = rs.getString("EMPno");
                stat = rs.getString("Status");

                PreparedStatement pst1 = con.prepareStatement("select * from employee_reg where EmployeeNo='" + emp + "'");
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    name = rs1.getString("NameWithInitials");
                    rank = rs1.getString("Designation");
                    loc = rs1.getString("DefLocation");

                }

                PreparedStatement pst2 = con.prepareStatement("select * from location_reg where LocCode='" + loc + "'");
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    locname = rs2.getString("LocName");
                }

                PreparedStatement pst3 = con.prepareStatement("select *,SUM(DayShift+NightShift) from emp_atten_main where EPFno='" + emp + "' and  Month='" + month + "' and Year='" + year + "' group by EPFno  ");
                ResultSet rs3 = pst3.executeQuery();
                while (rs3.next()) {
                    String total_duty = rs3.getString("SUM(DayShift+NightShift)");

                    Vector v = new Vector();
                    v.add(emp);
                    v.add(name);
                    v.add(rank);
                    v.add(total_duty);
                    v.add(stat);
                    v.add(locname);
                    dtm.addRow(v);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_AutoRList = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_main = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        lbl_locName = new javax.swing.JLabel();
        txt_RLIST_empName = new javax.swing.JTextField();
        txt_RLSIT_EmployeeNo = new javax.swing.JTextField();
        txt_rank = new javax.swing.JTextField();
        txt_locCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        cmb_defLocation = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txt_locCode1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Salary Stop Payment");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 1200, 10));

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
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 220, 40));

        table_AutoRList.setAutoCreateRowSorter(true);
        table_AutoRList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Employee Name", "Rank", "Shift", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table_AutoRList);
        if (table_AutoRList.getColumnModel().getColumnCount() > 0) {
            table_AutoRList.getColumnModel().getColumn(0).setPreferredWidth(50);
            table_AutoRList.getColumnModel().getColumn(1).setPreferredWidth(150);
            table_AutoRList.getColumnModel().getColumn(2).setPreferredWidth(50);
            table_AutoRList.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_AutoRList.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 430, 410));

        jButton5.setBackground(new java.awt.Color(153, 204, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/double-right-32.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 35, 120));

        table_main.setAutoCreateRowSorter(true);
        table_main.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        table_main.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPno", "Employee Name", "Rank", "Shift", "Status", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_main.setRowHeight(25);
        jScrollPane1.setViewportView(table_main);
        if (table_main.getColumnModel().getColumnCount() > 0) {
            table_main.getColumnModel().getColumn(0).setPreferredWidth(40);
            table_main.getColumnModel().getColumn(1).setPreferredWidth(120);
            table_main.getColumnModel().getColumn(2).setPreferredWidth(30);
            table_main.getColumnModel().getColumn(3).setPreferredWidth(40);
            table_main.getColumnModel().getColumn(4).setPreferredWidth(80);
            table_main.getColumnModel().getColumn(5).setPreferredWidth(180);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 530, 410));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Auto Detected R-List   ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 430, 20));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Salary Stop Payment - MAIN");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 190, 20));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 200, 10));

        jButton6.setBackground(new java.awt.Color(153, 204, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RightArrow.png"))); // NOI18N
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 35, 130));

        jButton7.setBackground(new java.awt.Color(153, 204, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/left-arow-32.png"))); // NOI18N
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 35, 110));

        lbl_locName.setBackground(new java.awt.Color(204, 204, 204));
        lbl_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_locName.setOpaque(true);
        getContentPane().add(lbl_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 75, 330, 25));

        txt_RLIST_empName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_RLIST_empName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_RLIST_empNameFocusGained(evt);
            }
        });
        txt_RLIST_empName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RLIST_empNameActionPerformed(evt);
            }
        });
        txt_RLIST_empName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RLIST_empNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_RLIST_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 270, 25));

        txt_RLSIT_EmployeeNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_RLSIT_EmployeeNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RLSIT_EmployeeNoKeyPressed(evt);
            }
        });
        getContentPane().add(txt_RLSIT_EmployeeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 60, 25));

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
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 60, 25));

        txt_locCode.setEditable(false);
        txt_locCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 75, 60, 25));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel4.setText("Employee :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 90, 20));

        jButton12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        jButton12.setText("Add Emp.");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, -1, 50));

        jButton14.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton14.setText("Save");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 130, 120, 90));

        jButton16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton16.setText("Remove");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 300, 120, 50));

        jButton17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton17.setText("Clear All");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 440, 120, 90));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 390, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setText("  Location :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 80, 40));

        txt_locCode1.setEditable(false);
        txt_locCode1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locCode1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locCode1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_locCode1FocusLost(evt);
            }
        });
        getContentPane().add(txt_locCode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 130, 23));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                jLabel9.setText("Collecting Data... Please Wait...");
                get_saved_stop_details();

                try {

                    jButton2.setEnabled(false);
                    String month = cmb_month.getSelectedItem().toString();
                    String year = cmb_year.getSelectedItem().toString();
                    int month_no = cmb_month.getSelectedIndex() + 1;
                    String month_no_s = String.format("%02d", month_no);
                    String date_from = year + "-" + month_no_s + "-" + "20";
                    String date_to = year + "-" + month_no_s + "-" + "31";
                    System.out.println("from " + date_from);
                    System.out.println("to" + date_to);

                    Connection con = DbConnection.getconnection();

                    PreparedStatement pst0 = con.prepareStatement("select * from emp_atten_main where    Month='" + month + "' and Year='" + year + "' group by EPFno  ");
                    ResultSet rs0 = pst0.executeQuery();
                    DefaultTableModel dtm = (DefaultTableModel) table_AutoRList.getModel();
                    while (rs0.next()) {

                        String emp = rs0.getString("EPFno");

                        PreparedStatement pst = con.prepareStatement("select *,SUM(DayShift+NightShift+(HalfDayShift)/2) from emp_atten_main where Date BETWEEN '" + date_from + "' and '" + date_to + "' and  Month='" + month + "' and Year='" + year + "' and EPFno='" + emp + "'  ");
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {

                            String total_duty = rs.getString("SUM(DayShift+NightShift+(HalfDayShift)/2)");
                            System.out.println(emp + "  " + total_duty);
                            Double duty = 0.0;
                            if (total_duty == null) {
                                duty = 0.0;
                            } else {
                                duty = Double.parseDouble(total_duty);
                            }

                            if (duty == 0.0) {

                                Vector v = new Vector();

                                PreparedStatement pst1 = con.prepareStatement("select * from employee_reg where EmployeeNo='" + emp + "'");
                                ResultSet rs1 = pst1.executeQuery();
                                while (rs1.next()) {

                                    String name = rs1.getString("NameWithInitials");
                                    String rank = rs1.getString("Designation");
                                    String loc = rs1.getString("DefLocation");

                                    PreparedStatement pst2 = con.prepareStatement("select * from location_reg where LocCode='" + loc + "'");
                                    ResultSet rs2 = pst2.executeQuery();
                                    while (rs2.next()) {
                                        String locname = rs2.getString("LocName");

                                        String shifts = "1000";
                                        PreparedStatement pst3 = con.prepareStatement("select *,SUM(DayShift+NightShift+(HalfDayShift)/2) from emp_atten_main where EPFno='" + emp + "' and Month='" + month + "' and Year='" + year + "'");
                                        ResultSet rs3 = pst3.executeQuery();
                                        while (rs3.next()) {
                                            shifts = rs3.getString("SUM(DayShift+NightShift+(HalfDayShift)/2)");

                                        }

                                        v.add(emp);
                                        v.add(name);
                                        v.add(rank);
                                        v.add(shifts);
                                        v.add(locname);
                                        dtm.addRow(v);

                                    }

                                }
                            }

                        }
                    }
//                    get_Ranks();
                    jButton2.setEnabled(true);
                    jLabel9.setText(" Successfully Detected R-List ");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        hilo.start();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int rowCount = table_AutoRList.getRowCount();
        if (rowCount < 1) {

        } else {
            DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();
            for (int row = 0; row < rowCount; row++) {

                String emp = table_AutoRList.getValueAt(row, 0).toString();
                String name = table_AutoRList.getValueAt(row, 1).toString();
                String rank = table_AutoRList.getValueAt(row, 2).toString();
                String shift = table_AutoRList.getValueAt(row, 3).toString();
                String loc = table_AutoRList.getValueAt(row, 4).toString();

                Vector v = new Vector();
                v.add(emp);
                v.add(name);
                v.add(rank);
                v.add(shift);
                v.add("RList");
                v.add(loc);
                dtm.addRow(v);

            }

            DefaultTableModel dtm1 = (DefaultTableModel) table_AutoRList.getModel();
            dtm1.setRowCount(0);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int row = table_AutoRList.getSelectedRow();

        if (row < 0) {

        } else {
            String emp = table_AutoRList.getValueAt(row, 0).toString();
            String name = table_AutoRList.getValueAt(row, 1).toString();
            String rank = table_AutoRList.getValueAt(row, 2).toString();
            String shift = table_AutoRList.getValueAt(row, 3).toString();
            String loc = table_AutoRList.getValueAt(row, 4).toString();

            DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();

            Vector v = new Vector();
            v.add(emp);
            v.add(name);
            v.add(rank);
            v.add(shift);
            v.add("RList");
            v.add(loc);
            dtm.addRow(v);

            DefaultTableModel dtm1 = (DefaultTableModel) table_AutoRList.getModel();
            dtm1.removeRow(row);
        }

//        dtm.setValueAt(emp, 0, 0);
//        dtm.setValueAt(name, 0, 1);
//        dtm.setValueAt(rank, 0, 2);
//        dtm.setValueAt(shift, 0, 3);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int row = table_main.getSelectedRow();

        if (row < 0) {

        } else {
            String emp = table_main.getValueAt(row, 0).toString();
            String name = table_main.getValueAt(row, 1).toString();
            String rank = table_main.getValueAt(row, 2).toString();
            String shift = table_main.getValueAt(row, 3).toString();
            String loc = table_main.getValueAt(row, 5).toString();

            DefaultTableModel dtm = (DefaultTableModel) table_AutoRList.getModel();

            Vector v = new Vector();
            v.add(emp);
            v.add(name);
            v.add(rank);
            v.add(shift);
            v.add(loc);

            dtm.addRow(v);

            DefaultTableModel dtm1 = (DefaultTableModel) table_main.getModel();
            dtm1.removeRow(row);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_RLIST_empNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_RLIST_empNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RLIST_empNameFocusGained

    private void txt_RLIST_empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RLIST_empNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RLIST_empNameActionPerformed

    private void txt_RLIST_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RLIST_empNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                String search = txt_RLIST_empName.getText();

                if (search.isEmpty()) {
                    // JOptionPane.showMessageDialog(rootPane, "Please select a Loctaion");

                } else {

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + search + "' OR NameWithInitials='" + search + "'   ");
                    while (rs3.next()) {

                        if ((rs3.getInt("COUNT(*)") > 1)) {
                            PopUp_Emp_Table pt = new PopUp_Emp_Table();
                            pt.setVisible(true);
                            pt.setTitle("RLIST");

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

                                txt_RLSIT_EmployeeNo.setText(code);
                                txt_RLIST_empName.setText(name);
                                txt_rank.setText(rank);
                                txt_locCode.setText(loc);

                                Statement st1 = DbConnection.getconnection().createStatement();
                                ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'  ");
                                while (rs1.next()) {

                                    lbl_locName.setText(rs1.getString("LocName"));
                                }

                            }

                        }

                    }

                    jButton12.grabFocus();

                }
            } catch (Exception e) {
                System.out.println("eddddddr");
                e.printStackTrace();
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_RLIST_empNameKeyPressed

    private void txt_RLSIT_EmployeeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RLSIT_EmployeeNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                String search = txt_RLSIT_EmployeeNo.getText();

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

                                txt_RLSIT_EmployeeNo.setText(code);
                                txt_RLIST_empName.setText(name);
                                //jLabel2.setText(name);

                                txt_rank.setText(rank);
                                txt_locCode.setText(loc);

                                Statement st1 = DbConnection.getconnection().createStatement();
                                ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'  ");
                                while (rs1.next()) {

                                    lbl_locName.setText(rs1.getString("LocName"));
                                }

                            }

                        }

                    }
                    jButton12.grabFocus();
                }
            } catch (Exception e) {
                System.out.println("eddddddr");
                e.printStackTrace();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RLSIT_EmployeeNoKeyPressed

    private void txt_rankFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rankFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankFocusGained

    private void txt_rankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankActionPerformed

    private void txt_rankKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rankKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankKeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                if (cmb_defLocation.getSelectedIndex() == 0 | txt_locCode1.getText().isEmpty()) { //one by one employee add

                    if (txt_RLSIT_EmployeeNo.getText().isEmpty() | txt_RLIST_empName.getText().isEmpty()) {

                    } else {
                        String emp = txt_RLSIT_EmployeeNo.getText();
                        String name = txt_RLIST_empName.getText();
                        String rank = txt_rank.getText();
                        String shift = "0";

                        String month = cmb_month.getSelectedItem().toString();
                        String year = cmb_year.getSelectedItem().toString();

                        try {

                            Connection con = DbConnection.getconnection();
                            PreparedStatement pst = con.prepareStatement("select *,SUM(DayShift+NightShift) from emp_atten_main where EPFno='" + emp + "' and  Month='" + month + "' and Year='" + year + "' group by EPFno  ");
                            ResultSet rs = pst.executeQuery();
                            DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();
                            while (rs.next()) {
                                String total_duty = rs.getString("SUM(DayShift+NightShift)");
                                shift = total_duty;
                                Vector v = new Vector();
                                v.add(emp);
                                v.add(name);
                                v.add(rank);
                                v.add(shift);
                                v.add("Click to Hold");
                                v.add(lbl_locName.getText());
                                dtm.addRow(v);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        txt_RLIST_empName.setText("");
                        txt_RLSIT_EmployeeNo.setText("");
                        txt_rank.setText("");
                        txt_locCode.setText("");
                        lbl_locName.setText("");
                    }

                } else {//all emp in one location

                    try {
                        String shift = "0";
                        DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();

                        String month = cmb_month.getSelectedItem().toString();
                        String year = cmb_year.getSelectedItem().toString();
                        Connection con = DbConnection.getconnection();
                        PreparedStatement pst = con.prepareStatement("select *,SUM(DayShift+NightShift) from emp_atten_main where Location='" + txt_locCode1.getText() + "' and  Month='" + month + "' and Year='" + year + "' group by EPFno  ");
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            String total_duty = rs.getString("SUM(DayShift+NightShift)");
                            shift = total_duty;
                            String emp = rs.getString("EPFno");

                            PreparedStatement pst1 = con.prepareStatement("select * from employee_reg where EmployeeNo='" + emp + "' ");
                            ResultSet rs1 = pst1.executeQuery();
                            while (rs1.next()) {
                                String name = rs1.getString("NameWithInitials");
                                String rank = rs1.getString("Designation");

                                Vector v = new Vector();
                                v.add(emp);
                                v.add(name);
                                v.add(rank);
                                v.add(shift);
                                v.add("Click to Hold");
                                v.add(cmb_defLocation.getSelectedItem().toString());
                                dtm.addRow(v);

                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        hilo.start();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {

            Connection con = DbConnection.getconnection();

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

            String sql = "insert into salary_stop_payment values (?,?,?,?) ";
            PreparedStatement pst = con.prepareStatement(sql);

            int nrow = table_main.getModel().getRowCount();

            if (nrow == 0) {

            } else {
                for (int i = 0; nrow > i; i++) {

                    String empid = table_main.getModel().getValueAt(i, 0).toString();
                    String stat = table_main.getModel().getValueAt(i, 4).toString();

                    pst.setString(1, empid);
                    pst.setString(2, stat);
                    pst.setString(3, month);
                    pst.setString(4, year);

                    pst.addBatch();

                    String del = "delete from salary_stop_payment where EMPno='" + empid + "' and Month='" + month + "' and Year='" + year + "' ";
                    PreparedStatement pst_del = con.prepareStatement(del);
                    pst_del.execute();

                }

                pst.executeBatch();

                JOptionPane.showMessageDialog(rootPane, "Salary Stop Payment Details Saved...!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int nrow = table_main.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();
                    dtm.removeRow(table_main.getSelectedRow());
                }

            } else {

            }

        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int nrow = table_main.getRowCount();

        int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear All Data?", null, JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            if (nrow < 1) {
            } else {
                DefaultTableModel dtm = (DefaultTableModel) table_main.getModel();
                dtm.setRowCount(0);
            }

            txt_RLIST_empName.setText("");
            txt_RLSIT_EmployeeNo.setText("");
            txt_rank.setText("");
            txt_locCode.setText("");
            lbl_locName.setText("");

        } else {

        }

    }//GEN-LAST:event_jButton17ActionPerformed

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationPopupMenuCanceled

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode1.setText("N/A");
            txt_locCode1.setForeground(Color.red);

        } else {
            txt_locCode.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(name);
                    txt_locCode1.setText(code);
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

    private void txt_locCode1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCode1FocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_locCode1FocusGained

    private void txt_locCode1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCode1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCode1FocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//*************************Developed By TechSeed Solutions*************************************************
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
            java.util.logging.Logger.getLogger(Salary_stop_Payment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salary_stop_Payment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salary_stop_Payment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salary_stop_Payment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salary_stop_Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbl_locName;
    private javax.swing.JTable table_AutoRList;
    private javax.swing.JTable table_main;
    public static javax.swing.JTextField txt_RLIST_empName;
    private javax.swing.JTextField txt_RLSIT_EmployeeNo;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_locCode1;
    private javax.swing.JTextField txt_rank;
    // End of variables declaration//GEN-END:variables

}
