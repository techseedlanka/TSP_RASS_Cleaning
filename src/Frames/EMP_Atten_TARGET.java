/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.PopUp_Emp_Table.POPUP_EMP_TABLE;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class EMP_Atten_TARGET extends javax.swing.JFrame {

    /**
     * Creates new form EMP_Atten
     */
    private ImageIcon format = null;
    
    public EMP_Atten_TARGET() {
        initComponents();
        auto_completer();
        get_Location_Details();
        TitleBar();
        jrb_day_rate.setSelected(true);
        jPanel2.setVisible(false);
        lbl_basic.setVisible(false);

//        tbl_atten.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txt_EffectiveShiftRatess.setText("0.00");
        txt_shiftRate.setText("0.00");
        
        Color cl = new Color(238, 152, 152);
        lbl_shifts_left.setBackground(cl);
        lbl_shifts_left.setOpaque(true);
        
        Color cl2 = new Color(51, 255, 151);
        lbl_total_shifts.setBackground(cl2);
        lbl_total_shifts.setOpaque(true);

        // tbl_atten.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
    int day;
    int night;
    int full;
    int half;
    
    static ArrayList<table_data> al = new ArrayList<table_data>();
    
    private void auto_completer() {
        
        try {
            
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");
            
            TextAutoCompleter ta = new TextAutoCompleter(txt_search);
            
            while (rs.next()) {
                String code = rs.getString("EmployeeNo");
                //String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");
                
                ta.addItem(code);
                ta.addItem(NameWithInitials);
                // ta.addItem(nic);
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
    
    private void TitleBar() {
        
        this.setTitle("Attendance Entry");
        this.setResizable(false);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
        
    }
    
    private void clear_rank_cmb() {
        cmb_EffectiveRank.removeAllItems();
        cmb_EffectiveRank.addItem("==Effective Rank==");
        
    }

//    private void get_real_Month_Year() {
//        Date date = jDateChooser1.getDate();
//        if (date == null) {
//            jLabel2.setForeground(Color.red);
//
//        } else {
//
//            int m = jDateChooser1.getDate().getMonth();
//
//            int d = m + 1;
//            String d1 = null;
//
//            if (d == 1) {
//                d1 = "January";
//            }
//            if (d == 2) {
//                d1 = "February";
//            }
//            if (d == 3) {
//                d1 = "March";
//            }
//            if (d == 4) {
//                d1 = "April";
//            }
//            if (d == 5) {
//                d1 = "May";
//            }
//            if (d == 6) {
//                d1 = "June";
//            }
//            if (d == 7) {
//                d1 = "July";
//            }
//            if (d == 8) {
//                d1 = "August";
//            }
//            if (d == 9) {
//                d1 = "September";
//            }
//            if (d == 10) {
//                d1 = "October";
//            }
//            if (d == 11) {
//                d1 = "November";
//            }
//            if (d == 12) {
//                d1 = "December";
//            }
//
//            //String d1 = Integer.toHexString(d);
//            txt_month.setText(d1);
//
//            int year = jDateChooser1.getDate().getYear();
//            int j = year - 100;
//            int k = j + 2000;
//
//            txt_Year.setText(Integer.toString(k));
//
//        }
//
//    }
    Double left = 0.00;
    Double total = 0.00;
    
    private void add_to_table() {
        
        left = Double.parseDouble(lbl_shifts_left.getText());
        total = Double.parseDouble(lbl_total_shifts.getText());
        
        if (left <= 0) {
            System.out.println("data  not add");
            
            JOptionPane.showMessageDialog(rootPane, "  You are Exceeding the Total No. of Allowed Shifts for Selected Location ");
            
        } else if (Objects.equals(total, left) | total > left) {
            System.out.println("data add");
            
            if (!(cb_day.isSelected() | cb_night.isSelected() | cb_fullLeave.isSelected()) && (txt_empid.getText().isEmpty())) {
                
            } else {
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooser1.getDate());
                
                get_month_and_year();
                
                table_data t = new table_data();
                
                if (cb_night.isSelected()) {
                    night = 1;
                } else {
                    night = 0;
                }
                if (cb_day.isSelected()) {
                    day = 1;
                } else {
                    day = 0;
                }
                if (cb_fullLeave.isSelected()) {
                    full = 1;
                } else {
                    full = 0;
                }
                if (cb_halfLeave.isSelected()) {
                    half = 1;
                } else {
                    half = 0;
                }
                
                t.Date = date;
                t.EMPID = txt_empid.getText();
                t.day = day;
                t.night = night;
                t.fullleave = full;
                t.halfleave = half;
                t.month = txt_month.getText();
                t.year = txt_Year.getText();
                t.EffectRank = cmb_EffectiveRank.getSelectedItem().toString();
                t.EffectRate = txt_EffectiveShiftRatess.getText();
                t.DefCompany = EMP_COM.getText();
                t.ot = txt_OT.getText();
                t.otRate = txt_OT_Rate.getText();
                
                al.add(t);
                DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
                Vector v = new Vector();
                
                v.add(date);
                v.add(txt_empid.getText());
                v.add(day);
                v.add(night);
                //v.add(full);
                v.add(half);
                v.add(txt_month.getText());
                v.add(txt_Year.getText());
                v.add(txt_EffectiveShiftRatess.getText());
                v.add(txt_OT.getText());
                v.add(txt_OT_Rate.getText());
                v.add(cmb_EffectiveRank.getSelectedItem().toString());
                v.add(EMP_COM.getText());
                dtm.addRow(v);
                
                calculate_balance_shifts_in_Tbale();

                //                get_holidays();
                //change_rates_save();
                cb_day.setEnabled(true);
                cb_night.setEnabled(true);
                cb_fullLeave.setEnabled(true);
                cb_halfLeave.setEnabled(true);
                
                cb_day.setSelected(false);
                cb_night.setSelected(false);
                cb_fullLeave.setSelected(false);
                cb_halfLeave.setSelected(false);
                
                int row = dtm.getRowCount();
                lbl_rowCount.setText(Integer.toString(row));
                
            }
            
        } else if (total == 0) {
            
            JOptionPane.showMessageDialog(rootPane, "  Please Enter Selected Location's Employee Cadre in order to Add Attendance Details ");
        } else {
            
        }
        
    }
    
    private void get_relevent_shift_rate() {
        
        int numOfMenus = cmb_EffectiveRank.getItemCount();
        
        for (int i = 0; i < numOfMenus; i++) {
            String LocRanks = (String) cmb_EffectiveRank.getItemAt(i);
            
            if (LocRanks.contains(txt_rank.getText())) {
                
                cmb_EffectiveRank.setSelectedIndex(i);
                
                String loc = txt_locCode.getText();
                String rank = cmb_EffectiveRank.getSelectedItem().toString();
                
                try {
                    
                    Statement st = DbConnection.getconnection().createStatement();
                    ResultSet rs = st.executeQuery("select * from salary_rates where LocCode='" + loc + "' and RankCode ='" + rank + "'");
                    while (rs.next()) {
                        
                        if (jrb_day_rate.isSelected()) {
                            txt_EffectiveShiftRatess.setText(rs.getString("DayRate"));
                        }
                        if (jrb_night_rate.isSelected()) {
                            txt_EffectiveShiftRatess.setText(rs.getString("NightRate"));
                        }
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        lbl_total_shifts = new javax.swing.JLabel();
        cmb_defLocation = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btn_ADD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_atten = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        cb_day = new javax.swing.JCheckBox();
        cb_night = new javax.swing.JCheckBox();
        cb_halfLeave = new javax.swing.JCheckBox();
        btn_Save = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        txt_month = new javax.swing.JTextField();
        txt_locCode = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_Year = new javax.swing.JTextField();
        btn_RemoveRow = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_shifts_left = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_extraShifts = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_night_carder = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_day_carder = new javax.swing.JTable();
        cb_fullLeave = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtable_salary_earnings = new javax.swing.JTable();
        lbl_max_shift_permonth = new javax.swing.JLabel();
        lbl_basic = new javax.swing.JLabel();
        lbl_gross = new javax.swing.JLabel();
        lbl_photo = new javax.swing.JLabel();
        EMP_COM = new javax.swing.JLabel();
        btn_RemoveRow1 = new javax.swing.JButton();
        txt_shiftRate = new javax.swing.JTextField();
        txt_rank = new javax.swing.JTextField();
        txt_empid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lbl_rowCount = new javax.swing.JLabel();
        cmb_EffectiveRank = new javax.swing.JComboBox();
        txt_EffectiveShiftRatess = new javax.swing.JTextField();
        jrb_night_rate = new javax.swing.JRadioButton();
        jrb_day_rate = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txt_OT_Rate = new javax.swing.JTextField();
        txt_OT = new javax.swing.JTextField();
        txt_def_loc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1090, 10));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel1.setText("Employee Attendence Entry");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jDateChooser1.setDateFormatString("dd MMM yyyy");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDateChooser1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDateChooser1FocusLost(evt);
            }
        });
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 130, 25));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel2.setText("Date :- ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        lbl_total_shifts.setBackground(new java.awt.Color(51, 255, 151));
        lbl_total_shifts.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_total_shifts.setText("00.00");
        lbl_total_shifts.setToolTipText("");
        getContentPane().add(lbl_total_shifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 65, 70, 20));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 400, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 1080, 0));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel3.setText("Employee :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, 30));

        btn_ADD.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_ADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_ADD.setText("Add  To Table");
        btn_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 460, 40));

        tbl_atten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_atten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "EMP no", "Day  ", "Night ", "Half Day", "Month", "Year", "Rate", "OT", "OT Rate", "Ef.Rank", "COM."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_atten.setRowHeight(20);
        tbl_atten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_attenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_atten);
        if (tbl_atten.getColumnModel().getColumnCount() > 0) {
            tbl_atten.getColumnModel().getColumn(0).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(1).setPreferredWidth(60);
            tbl_atten.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(3).setPreferredWidth(50);
            tbl_atten.getColumnModel().getColumn(4).setPreferredWidth(50);
            tbl_atten.getColumnModel().getColumn(5).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(6).setPreferredWidth(50);
            tbl_atten.getColumnModel().getColumn(7).setPreferredWidth(65);
            tbl_atten.getColumnModel().getColumn(8).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(9).setPreferredWidth(60);
            tbl_atten.getColumnModel().getColumn(10).setResizable(false);
            tbl_atten.getColumnModel().getColumn(10).setPreferredWidth(60);
            tbl_atten.getColumnModel().getColumn(11).setResizable(false);
            tbl_atten.getColumnModel().getColumn(11).setPreferredWidth(1);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, 590, 390));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton4.setText("Re-Fresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 150, 40));

        cb_day.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        cb_day.setText("Day Shift");
        cb_day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_dayActionPerformed(evt);
            }
        });
        getContentPane().add(cb_day, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, -1));

        cb_night.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        cb_night.setText("Night Shift");
        cb_night.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nightActionPerformed(evt);
            }
        });
        getContentPane().add(cb_night, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, -1, -1));

        cb_halfLeave.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        cb_halfLeave.setText("Half Day");
        cb_halfLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_halfLeaveActionPerformed(evt);
            }
        });
        getContentPane().add(cb_halfLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 80, -1));

        btn_Save.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_Save.setText("Save");
        btn_Save.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.gray));
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, 120, 83));

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 310, 25));

        txt_month.setEditable(false);
        txt_month.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 70, 23));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 60, 23));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 89, 1090, 10));

        txt_Year.setEditable(false);
        txt_Year.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 60, 23));

        btn_RemoveRow.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_RemoveRow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        btn_RemoveRow.setText("Remove Row");
        btn_RemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveRowActionPerformed(evt);
            }
        });
        getContentPane().add(btn_RemoveRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 385, 150, 40));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setText("Location:-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, 40));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Balance Shifts");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, 90, 20));

        lbl_shifts_left.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_shifts_left.setText("00.00");
        lbl_shifts_left.setToolTipText("");
        getContentPane().add(lbl_shifts_left, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 65, 80, 20));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Total Shifts");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, 20));

        btn_extraShifts.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_extraShifts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_extraShifts.setText("Add Extra Shifts to Curret Carder");
        btn_extraShifts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_extraShiftsActionPerformed(evt);
            }
        });
        getContentPane().add(btn_extraShifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 430, 280, 40));

        table_night_carder.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        table_night_carder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Night Cadre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_night_carder.setRowHeight(20);
        jScrollPane3.setViewportView(table_night_carder);
        if (table_night_carder.getColumnModel().getColumnCount() > 0) {
            table_night_carder.getColumnModel().getColumn(0).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 90, 100));

        table_day_carder.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        table_day_carder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day Cadre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_day_carder.setRowHeight(20);
        jScrollPane4.setViewportView(table_day_carder);
        if (table_day_carder.getColumnModel().getColumnCount() > 0) {
            table_day_carder.getColumnModel().getColumn(0).setResizable(false);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 90, 100));

        cb_fullLeave.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        cb_fullLeave.setText("OT Shift");
        cb_fullLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_fullLeaveActionPerformed(evt);
            }
        });
        getContentPane().add(cb_fullLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 170, -1, -1));

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtable_salary_earnings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EPF", "Sundays", "SundayRate", "TotalSunday", "PoyaRate", "MC", "Other", "Month", "Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jtable_salary_earnings);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 289, 30));

        lbl_max_shift_permonth.setText("Max Days");
        jPanel2.add(lbl_max_shift_permonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 50, -1));

        lbl_basic.setText("Baisc");
        jPanel2.add(lbl_basic, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 50, -1));

        lbl_gross.setText("Gross");
        jPanel2.add(lbl_gross, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 50, -1));

        lbl_photo.setBackground(new java.awt.Color(102, 204, 255));
        lbl_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 30, 20));

        EMP_COM.setText("EMP_Company");
        jPanel2.add(EMP_COM, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 80, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 520, 30));

        btn_RemoveRow1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_RemoveRow1.setText("Attendance History");
        btn_RemoveRow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveRow1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_RemoveRow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 340, 150, 40));

        txt_shiftRate.setEditable(false);
        txt_shiftRate.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_shiftRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 90, 21));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 60, -1));

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 70, 21));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText(" EMP.  No.  :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 80, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Rank :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, -1, 20));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setText("EMP Photo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 100, -1));

        lbl_rowCount.setBackground(new java.awt.Color(153, 255, 204));
        lbl_rowCount.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_rowCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_rowCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lbl_rowCount.setOpaque(true);
        getContentPane().add(lbl_rowCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 60, 30));

        cmb_EffectiveRank.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_EffectiveRank.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "==Effective Rank==" }));
        cmb_EffectiveRank.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_EffectiveRankPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_EffectiveRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 150, 25));

        txt_EffectiveShiftRatess.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_EffectiveShiftRatess.setText("0");
        txt_EffectiveShiftRatess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EffectiveShiftRatessActionPerformed(evt);
            }
        });
        txt_EffectiveShiftRatess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_EffectiveShiftRatessKeyTyped(evt);
            }
        });
        getContentPane().add(txt_EffectiveShiftRatess, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, 90, -1));

        buttonGroup1.add(jrb_night_rate);
        jrb_night_rate.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jrb_night_rate.setText("Night Rate");
        jrb_night_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_night_rateActionPerformed(evt);
            }
        });
        getContentPane().add(jrb_night_rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 220, -1, -1));

        buttonGroup1.add(jrb_day_rate);
        jrb_day_rate.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jrb_day_rate.setText("Day Rate");
        jrb_day_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_day_rateActionPerformed(evt);
            }
        });
        getContentPane().add(jrb_day_rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 200, -1, -1));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel7.setText("Effective Rank & Rate :- ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, 20));

        txt_OT_Rate.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_OT_Rate.setText("0");
        txt_OT_Rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OT_RateActionPerformed(evt);
            }
        });
        txt_OT_Rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_OT_RateKeyTyped(evt);
            }
        });
        getContentPane().add(txt_OT_Rate, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 240, 80, -1));

        txt_OT.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_OT.setText("0");
        txt_OT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OTActionPerformed(evt);
            }
        });
        txt_OT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_OTKeyTyped(evt);
            }
        });
        getContentPane().add(txt_OT, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 70, -1));

        txt_def_loc.setEditable(false);
        txt_def_loc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_def_loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, 70, 25));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setText("       OT Hours & Rate :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 150, 20));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setText("Employee Count:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 110, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cb_dayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_dayActionPerformed
        if (cb_day.isSelected()) {
            cb_fullLeave.setSelected(false);
        }
        
        if (cb_day.isSelected() && cb_night.isSelected()) {
            cb_fullLeave.setSelected(false);
            cb_halfLeave.setSelected(false);
            
        }
    }//GEN-LAST:event_cb_dayActionPerformed

    private void cb_nightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nightActionPerformed
        if (cb_night.isSelected()) {
            cb_fullLeave.setSelected(false);
            
        }
        
        if (cb_day.isSelected() && cb_night.isSelected()) {
            cb_fullLeave.setSelected(false);
            
            cb_halfLeave.setSelected(false);
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_nightActionPerformed

    private void cb_fullLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_fullLeaveActionPerformed
        if (cb_fullLeave.isSelected()) {
            cb_day.setSelected(false);
            cb_night.setSelected(false);
            cb_halfLeave.setSelected(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cb_fullLeaveActionPerformed

    private void cb_halfLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_halfLeaveActionPerformed
        if (cb_halfLeave.isSelected()) {
            cb_fullLeave.setSelected(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cb_halfLeaveActionPerformed

    private void btn_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDActionPerformed
        
        if (cmb_EffectiveRank.getSelectedItem().toString().equals("==Effective Rank==") | cmb_EffectiveRank.getSelectedItem().toString().equals("") | txt_EffectiveShiftRatess.getText().equals("")) {
            
            JOptionPane.showMessageDialog(rootPane, "Please select the relevent rank in 'Effective Rank / Shift Rate'");
            
        } else {
            
            previous_atten_checker();
            table_attn_check();
            
            try {
                
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from salary_rates where LocCode='" + txt_locCode.getText() + "' and RankCode ='" + cmb_EffectiveRank.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    
                    if (jrb_day_rate.isSelected()) {
                        txt_EffectiveShiftRatess.setText(rs.getString("DayRate"));
                    }
                    if (jrb_night_rate.isSelected()) {
                        txt_EffectiveShiftRatess.setText(rs.getString("NightRate"));
                    }
                    
                }
            } catch (Exception e) {
            }
            if (txt_empid.getText().isEmpty() | txt_search.getText().isEmpty()) {
                
                JOptionPane.showMessageDialog(rootPane, " Please select an Employee ");
            } else {
                
                if (!(cb_halfLeave.isSelected() | cb_day.isSelected() | cb_fullLeave.isSelected() | cb_night.isSelected())) {
                    
                    JOptionPane.showMessageDialog(rootPane, "Empty Shift...");
                    
                } else {
                    
                    double x = Double.parseDouble(txt_EffectiveShiftRatess.getText());
                    double y = Double.parseDouble(txt_shiftRate.getText());

                    //x=Location Shift Rate
                    //y=Employee's Shift Rate
//                if (cmb_EffectiveRank.getSelectedItem().equals(txt_rank.getText())) {
                    //YES
                    if (x == y) {
                        //System.out.println("X y is Equal");

                        //Save Query
                        add_to_table();
                        
                    } else {

                        //System.out.println("NOT EQUAL");
                        //PopUp Msg == Shift RAtes are not equal. do you want to proceed with the Location Shift Rate??
                        int reply = JOptionPane.showConfirmDialog(rootPane, "Shift Rates are not equal. do you want to proceed with the Location Shift Rate?", null, JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {//==>YES_OPTION== Save Query

                            if (cmb_EffectiveRank.getSelectedItem().toString().equals("") | cmb_EffectiveRank.getSelectedItem().toString().equals("==Effective Rank==")) {
                                
                                JOptionPane.showMessageDialog(rootPane, " Select Effective Rank ");
                            } else {

//                        try {
//
//                            Statement st = DbConnection.getconnection().createStatement();
//                            ResultSet rs = st.executeQuery("select * from location_shift_rates where LocCode='" + cmb_defLocation.getSelectedItem().toString() + "' and Rank ='" + cmb_EffectiveRank.getSelectedItem().toString() + "'");
//                            while (rs.next()) {
//
//                                txt_EffectiveShiftRatess.setText(rs.getString("ShiftRate"));
//
//                            }
//                        } catch (Exception e) {
//                        }
//                        
//                        }
                                add_to_table();
                                cmb_EffectiveRank.setSelectedItem("==Effective Rank==");
                            }
                        } else if (reply == JOptionPane.NO_OPTION) {
                            //==>NO_OPTION==PopUp Msg(with OK_NO_Option)== Do you want to proceed with the emp's default shift rate?()
                            int reply1 = JOptionPane.showConfirmDialog(rootPane, "Do you want to proceed with the Employee's default shift rate?", null, JOptionPane.OK_OPTION);
                            if (reply1 == JOptionPane.OK_OPTION) {
                                txt_EffectiveShiftRatess.setText(txt_shiftRate.getText());
                                add_to_table();
                                get_relevent_shift_rate();
                                cmb_EffectiveRank.setSelectedItem("==Effective Rank==");
                            } else {
                                
                            }
                            
                        } else {
                            
                        }

//                } else {
//        //NO
//
//                    //popUP Msg == Emp's Rank & Location Ranks are not equal. Please select the Effective Rank.
//                    int reply1 = JOptionPane.showConfirmDialog(rootPane, "<html>Selected Effective Rank's Shift Rate is not equals with Emp's Rank & default shift rate.<br>Do you want to proceed with the location shift rate?</html> Do you want to proceed with the location shift rate? ", null, JOptionPane.YES_NO_OPTION);
//                    if (reply1 == JOptionPane.YES_OPTION) {//==>YES_OPTION== Save Query
//                        add_to_table();
//                    }
//                    if (reply1 == JOptionPane.NO_OPTION) {
//                        int reply2 = JOptionPane.showConfirmDialog(rootPane, "Do you want to proceed with the emp's default shift rate?", null, JOptionPane.YES_NO_OPTION);
//                        if (reply2 == JOptionPane.YES_OPTION) {//==>YES_OPTION== Save Query
//                            txt_EffectiveShiftRatess.setText(txt_shiftRate.getText());
//                            add_to_table();
//                            get_relevent_shift_rate();
//                            cmb_EffectiveRank.setSelectedItem("==Effective Rank==");
//                        } else {
//                        }
//                    }
//
//                }
                    }
                }
            }
        }

//        Object[] b = cmb_EffectiveRank.geti

    }//GEN-LAST:event_btn_ADDActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lbl_gross.setText("0.00");
            lbl_max_shift_permonth.setText("0.00");
            txt_shiftRate.setText("0.00");
            txt_empid.setText("");
            txt_rank.setText("");
            txt_def_loc.setText("");
            try {
                
                Statement st3 = DbConnection.getconnection().createStatement();
                ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + txt_search.getText() + "' OR NameWithInitials='" + txt_search.getText() + "'   ");
                while (rs3.next()) {
                    
                    if ((rs3.getInt("COUNT(*)") > 1)) {
                        PopUp_Emp_Table pt = new PopUp_Emp_Table();
                        pt.setVisible(true);
                        pt.setTitle("Attendance");
                        
                        DefaultTableModel dtm = (DefaultTableModel) POPUP_EMP_TABLE.getModel();
                        
                        Statement st4 = DbConnection.getconnection().createStatement();
                        ResultSet rs4 = st4.executeQuery("select * from employee_reg where EmployeeNo='" + txt_search.getText() + "' OR NameWithInitials='" + txt_search.getText() + "'   ");
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
                        
                        lbl_photo.setIcon(null);
                        
                        Statement st = DbConnection.getconnection().createStatement();
                        ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + txt_search.getText() + "' or NameWithInitials='" + txt_search.getText() + "' ");
                        while (rs.next()) {
                            String code = rs.getString("EmployeeNo");
                            //  String nic = rs.getString("NIC");
                            String name = rs.getString("NameWithInitials");
                            String com = rs.getString("DefCompany");
                            //Double rate = Double.parseDouble(rs.getString("ShiftRate"));

                            String rank = rs.getString("Designation");
                            String basic = rs.getString("BasicSalary");
                            String gross = rs.getString("GrossSalary");
                            String max_days = rs.getString("ShiftRate");
                            String def_loc = rs.getString("DefLocation");
                            
                            byte[] imagedata = rs.getBytes("EMPImage");
                            
                            if (imagedata == null) {
                                lbl_photo.setText("No Image");
                                lbl_photo.setForeground(Color.red);
                            } else {
                                format = new ImageIcon(imagedata);
                                lbl_photo.setIcon(format);
                            }
                            
                            txt_empid.setText(code);
                            txt_search.setText(name);
                            txt_rank.setText(rank);
                            EMP_COM.setText(com);
                            lbl_basic.setText(basic);
                            lbl_gross.setText(gross);
                            lbl_max_shift_permonth.setText(max_days);
                            txt_def_loc.setText(def_loc);
//
//                    } else {
//                    }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            int numOfMenus = cmb_EffectiveRank.getItemCount();
            
            for (int i = 0; i < numOfMenus; i++) {
                String LocRanks = (String) cmb_EffectiveRank.getItemAt(i);
                
                if (LocRanks.contains(txt_rank.getText())) {
                    
                    cmb_EffectiveRank.setSelectedIndex(i);
                    
                    String loc = txt_locCode.getText();
                    String rank = cmb_EffectiveRank.getSelectedItem().toString();
                    
                    try {
                        
                        Statement st = DbConnection.getconnection().createStatement();
                        ResultSet rs = st.executeQuery("select * from salary_rates where LocCode='" + loc + "' && RankCode ='" + rank + "'");
                        while (rs.next()) {
                            
                            if (jrb_day_rate.isSelected()) {
                                txt_EffectiveShiftRatess.setText(rs.getString("DayRate"));
                            }
                            if (jrb_night_rate.isSelected()) {
                                txt_EffectiveShiftRatess.setText(rs.getString("NightRate"));
                            }
                            
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                } else {
                    cmb_EffectiveRank.setSelectedItem("==Effective Rank==");
                }
                
            }
            
            if (cmb_EffectiveRank.getSelectedItem().equals("==Effective Rank==")) {
                //JOptionPane.showMessageDialog(rootPane, "Emp's Rank & Location Ranks are not equal. Please select the Effective Rank");
                txt_EffectiveShiftRatess.setBackground(Color.red);
                cmb_EffectiveRank.setForeground(Color.red);
                
            }

            //Get Supervisors Rates
            if (lbl_gross.getText().equals("0.00") | lbl_gross.getText().equals("") | lbl_gross.getText().equals("Gross")) {
                
            } else {
                
                Double gross = Double.parseDouble(lbl_gross.getText());
                Double max_days = Double.parseDouble(lbl_max_shift_permonth.getText());
                Double rate = 0.00;
                if (max_days < 30.00) {
                    
                    rate = gross / max_days;
                    
                } else {
                    
                    rate = gross / number_Of_DaysInMonth;
                }
                
                String r = String.format("%.2f", rate);
                txt_shiftRate.setText(r);
                
            }
            
        }

    }//GEN-LAST:event_txt_searchKeyPressed

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible
        
        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {
            
            txt_locCode.setText("** Please Select a Location ");
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
                
                ResultSet rs1 = st.executeQuery("select * from salary_rates where LocCode= '" + txt_locCode.getText() + "' ");
                
                while (rs1.next()) {
                    
                    String rank = rs1.getString("RankCode");
                    
                    cmb_EffectiveRank.addItem(rank);
                    
                }
                
                ResultSet rs2 = st.executeQuery("select * from salary_rates where LocCode= '" + txt_locCode.getText() + "' and RankCode='" + txt_rank.getText() + "' ");
                
                while (rs2.next()) {
                    
                    String OtRate = rs2.getString("OTRate");
                    
                    txt_OT_Rate.setText(OtRate);
                    
                }
                cmb_defLocation.setEditable(false);
                cmb_defLocation.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            get_month_and_year();
            get_carder_and_enterd_shifts_total();
            loc_carder_NIGHT();
            loc_carder_DAY();
            
        }
        

    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        int reply = JOptionPane.showConfirmDialog(rootPane, "This Action will clear the all Attendance details in the Table", "Clear & Refresh", JOptionPane.OK_CANCEL_OPTION);
        
        if (reply == JOptionPane.OK_OPTION) {
            
            txt_locCode.setText("");
            jDateChooser1.setEnabled(true);
            cmb_defLocation.setEnabled(true);
            cmb_defLocation.setEditable(true);
            lbl_photo.setIcon(null);
            txt_empid.setText("");
            txt_def_loc.setText("");
            txt_search.setText("");
            cb_day.setSelected(false);
            cb_night.setSelected(false);
            cb_fullLeave.setSelected(false);
            cb_halfLeave.setSelected(false);
            txt_EffectiveShiftRatess.setText("0.00");
//            txt_empNic.setText("");

            txt_shiftRate.setText("0.00");
            cmb_defLocation.setSelectedItem("=Location=");
            cb_fullLeave.setEnabled(true);
            cb_halfLeave.setEnabled(true);
            cb_day.setEnabled(true);
            cb_night.setEnabled(true);
            
            lbl_total_shifts.setText("00.00");
            lbl_shifts_left.setText("00.00");
            
            DefaultTableModel dtm = (DefaultTableModel) table_day_carder.getModel();
            dtm.setRowCount(0);
            
            al.clear();
            DefaultTableModel dtm_tbl_atten = (DefaultTableModel) tbl_atten.getModel();
            dtm_tbl_atten.setRowCount(0);
            
            DefaultTableModel dtm1 = (DefaultTableModel) table_night_carder.getModel();
            dtm1.setRowCount(0);
            
            clear_rank_cmb();
        } else {
            
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        try {
// save attendance details to emp_attn_main table 

            if (tbl_atten.getRowCount() == 0) {
                
                JOptionPane.showMessageDialog(rootPane, " Attendance Details are Empty...");
                
            } else {
                Statement st = DbConnection.getconnection().createStatement();
                for (table_data t : al) {
                    st.executeUpdate("insert into emp_atten_main values('" + t.EMPID + "','" + txt_locCode.getText() + "','" + t.Date + "','" + t.day + "','" + t.night + "','" + t.halfleave + "','" + t.fullleave + "','" + t.month + "','" + t.year + "', 'pending','salary_pending','" + t.EffectRank + "','" + t.EffectRate + "','" + t.DefCompany + "','" + t.ot + "','" + t.otRate + "')");
                    
                }

                // save_holidays();
                JOptionPane.showMessageDialog(rootPane, " Attendance Saved...!");
            }
            
            DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
            dtm.setRowCount(0);
            dtm.getDataVector().removeAllElements();
            cmb_defLocation.setEnabled(true);
            al.clear();
            
            clear();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void jDateChooser1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1FocusLost

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
        if (txt_month.getText().isEmpty() | txt_locCode.getText().equals("** Please Select a Location ")) {
            
            JOptionPane.showMessageDialog(rootPane, "Date & Location Can not be Empty...");
            
            txt_locCode.grabFocus();
            
        } else {
            
            int m = jDateChooser1.getDate().getMonth();
            int d = m + 1;
            String d1 = null;
            
            if (d == 1) {
                d1 = "January";
            }
            if (d == 2) {
                d1 = "February";
            }
            if (d == 3) {
                d1 = "March";
            }
            if (d == 4) {
                d1 = "April";
            }
            if (d == 5) {
                d1 = "May";
            }
            if (d == 6) {
                d1 = "June";
            }
            if (d == 7) {
                d1 = "July";
            }
            if (d == 8) {
                d1 = "August";
            }
            if (d == 9) {
                d1 = "September";
            }
            if (d == 10) {
                d1 = "October";
            }
            if (d == 11) {
                d1 = "November";
            }
            if (d == 12) {
                d1 = "December";
            }

            //String d1 = Integer.toHexString(d);
            txt_month.setText(d1);
            
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_searchFocusGained

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:

        get_month_and_year();
        get_carder_and_enterd_shifts_total();
    }//GEN-LAST:event_txt_locCodeFocusGained

    private void btn_RemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveRowActionPerformed
        
        try {
            
            if (tbl_atten.getSelectedRowCount() < 1) {
                
            } else {
                int row = tbl_atten.getSelectedRow();
                
                int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete selected data?", null, JOptionPane.YES_NO_OPTION);
                
                if (reply == JOptionPane.YES_OPTION) {
                    
                    calculate_shifts_after_remove_row();
                    
                    al.remove(row);
                    
                    DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
                    dtm.removeRow(tbl_atten.getSelectedRow());
                    int rowCount = dtm.getRowCount();
                    lbl_rowCount.setText(Integer.toString(rowCount));
                    
                } else {
                    
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }//GEN-LAST:event_btn_RemoveRowActionPerformed

    private void tbl_attenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_attenMouseClicked

    }//GEN-LAST:event_tbl_attenMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            
           
            
            int reply = JOptionPane.showConfirmDialog(rootPane, "<html><span style=font-size:110%;>You are about to Close this Window. <br> Changes you made will not be Saved. </br> <br> <span style=color:red;> Want to close this Window? </br></span></html>", null, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {

//                st.executeUpdate("delete from emp_atten_rate_changed_temp where Location = '" + cmb_defLocation.getSelectedItem().toString() + "'");
//                
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                MAIN.jMenuItem32.setEnabled(true);
            } else {
                
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
        

    }//GEN-LAST:event_formWindowClosing

    private void btn_extraShiftsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_extraShiftsActionPerformed
        
        String userNameValue;
        String passwordValue;
        
        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        JLabel jAddingDays = new JLabel("Extra Days");
        JComboBox cmbAddingDays = new JComboBox();
        
        int a = (1);
        int b = (2);
        int c = (3);
        int d = (4);
        int e = (5);
        int f = (6);
        int g = (7);
        int h = (8);
        int i = (9);
        int j = (10);
        cmbAddingDays.addItem(a);
        cmbAddingDays.addItem(b);
        cmbAddingDays.addItem(c);
        cmbAddingDays.addItem(d);
        cmbAddingDays.addItem(e);
        cmbAddingDays.addItem(f);
        cmbAddingDays.addItem(g);
        cmbAddingDays.addItem(h);
        cmbAddingDays.addItem(i);
        cmbAddingDays.addItem(j);
        
        Object[] ob = {jUserName, userName, jPassword, password, jAddingDays, cmbAddingDays};

        //   Object[] ob = {jUserName, userName, jPassword, password};
        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            userNameValue = userName.getText();
            passwordValue = password.getText();
            
            Double extra = Double.parseDouble(cmbAddingDays.getSelectedItem().toString());
            
            String uname = null;
            
            try {
                Statement st2 = DbConnection.getconnection().createStatement();
                ResultSet rs2 = st2.executeQuery("select * from user where UserName = '" + userName.getText() + "'");
                
                while (rs2.next()) {
                    
                    String UN = rs2.getString("UserName");
                    String pass = rs2.getString("Password");
                    String cat = rs2.getString("UserCat");
                    
                    if (UN.equals(userNameValue) & pass.equals(passwordValue)) {
                        
                        if (cat.equals("admin") | cat.equals("system_admin")) {

//update query begins*************************************************************************************************
                            Double extra_left = Double.parseDouble(lbl_shifts_left.getText());
                            Double final_extra_left = extra_left + extra;
                            
                            Double extra_tot = Double.parseDouble(lbl_total_shifts.getText());
                            Double final_extra_tot = extra_tot + extra;
                            
                            lbl_shifts_left.setText(Double.toString(final_extra_left));
                            lbl_total_shifts.setText(Double.toString(final_extra_tot));
//update query ends ****************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation");
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }
                
            } catch (Exception n) {
                n.printStackTrace();
            }
            
        }

    }//GEN-LAST:event_btn_extraShiftsActionPerformed

    private void txt_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        MAIN.jMenuItem32.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void btn_RemoveRow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveRow1ActionPerformed
        RPT_Attendance_History em = new RPT_Attendance_History();
        em.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_RemoveRow1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(lbl_photo.getIcon());
        pt.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmb_EffectiveRankPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_EffectiveRankPopupMenuWillBecomeInvisible
        
        if (cmb_EffectiveRank.getSelectedItem().equals("==Effective Rank==")) {
            
            txt_EffectiveShiftRatess.setText("0.00");
            txt_EffectiveShiftRatess.setBackground(Color.white);
            cmb_EffectiveRank.setForeground(Color.black);
            
        } else {
            
            try {
                String loc = txt_locCode.getText();
                String rank = cmb_EffectiveRank.getSelectedItem().toString();
                
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from salary_rates where LocCode='" + loc + "' && RankCode ='" + rank + "'");
                
                while (rs.next()) {
                    
                    if (jrb_day_rate.isSelected()) {
                        txt_EffectiveShiftRatess.setText(rs.getString("DayRate"));
                    }
                    if (jrb_night_rate.isSelected()) {
                        txt_EffectiveShiftRatess.setText(rs.getString("NightRate"));
                    }
                    String OtRate = rs.getString("OTRate");
                    
                    txt_OT_Rate.setText(OtRate);
                    
                }
                
                txt_EffectiveShiftRatess.setBackground(Color.white);
                cmb_EffectiveRank.setForeground(Color.black);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

    }//GEN-LAST:event_cmb_EffectiveRankPopupMenuWillBecomeInvisible

    private void txt_EffectiveShiftRatessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EffectiveShiftRatessActionPerformed
        txt_OT.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EffectiveShiftRatessActionPerformed

    private void jrb_day_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_day_rateActionPerformed
        txt_EffectiveShiftRatess.setText("0");    // TODO add your handling code here:
    }//GEN-LAST:event_jrb_day_rateActionPerformed

    private void jrb_night_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_night_rateActionPerformed
        txt_EffectiveShiftRatess.setText("0");        // TODO add your handling code here:
    }//GEN-LAST:event_jrb_night_rateActionPerformed

    private void txt_OT_RateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OT_RateActionPerformed
        btn_ADD.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_OT_RateActionPerformed

    private void txt_OTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OTActionPerformed
        txt_OT_Rate.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_OTActionPerformed

    private void txt_EffectiveShiftRatessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EffectiveShiftRatessKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            
            char text[];
            int count = 0;
            
            text = txt_EffectiveShiftRatess.getText().toCharArray();
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
    }//GEN-LAST:event_txt_EffectiveShiftRatessKeyTyped

    private void txt_OTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OTKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            
            char text[];
            int count = 0;
            
            text = txt_OT.getText().toCharArray();
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
    }//GEN-LAST:event_txt_OTKeyTyped

    private void txt_OT_RateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OT_RateKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            
            char text[];
            int count = 0;
            
            text = txt_OT_Rate.getText().toCharArray();
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
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txt_OT_RateKeyTyped

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
            java.util.logging.Logger.getLogger(EMP_Atten_TARGET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten_TARGET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten_TARGET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten_TARGET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EMP_Atten_TARGET().setVisible(true);
            }
        });
    }
    
    private void get_holidays() {
        
        try {
            
            if (jDateChooser1.getDate() == null) {
                
            } else {
//                String get_day = jDateChooser1.getDate().toString();
//                String day_name = get_day.substring(0, 3);
//                System.out.println(day_name);
//
//                if (day_name.equals("Sun") && EMP_COM.getText().equals("SSS")) {
//
//                    Double basic = Double.parseDouble(lbl_basic.getText());
//                    Double sunday_rate = (basic / 25) * 1.5;
//
//                    DefaultTableModel tm = (DefaultTableModel) jtable_salary_earnings.getModel();
//
//                    Vector v = new Vector();
//
//                    v.add(txt_empid.getText());
//                    v.add("1");
//                    v.add(sunday_rate);
//                    v.add(sunday_rate);
//                    v.add("0");
//                    v.add("0");
//                    v.add("0");
//                    v.add(txt_month.getText());
//                    v.add(txt_Year.getText());
//
//                    tm.addRow(v);
//
//                } else {
//
//                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooser1.getDate());
                
                Connection con = DbConnection.getconnection();
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql = "select * from poya_days where Date='" + date + "' ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    
                    String poya = rs.getString("Date");
                    
                    if (poya.equals(date)) {
                        
                        Double basic = Double.parseDouble(lbl_basic.getText());
                        Double poya_rate = (basic / 26) * 1.5;
                        
                        DefaultTableModel tm = (DefaultTableModel) jtable_salary_earnings.getModel();
                        
                        Vector v = new Vector();
                        
                        v.add(txt_empid.getText());
                        v.add("0");
                        v.add("0");
                        v.add("0");
                        v.add(String.format("%.2f", (poya_rate)));
                        v.add("0");
                        v.add("0");
                        v.add(txt_month.getText());
                        v.add(txt_Year.getText());
                        
                        tm.addRow(v);
                        
                    }
                    
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Getting Sundays [ER_001]");
            
        }
        
    }
    
    private void save_holidays() {
        try {
            
            Connection con = DbConnection.getconnection();
            PreparedStatement pst = null;
            String sql = "insert into salary_manual_earnings (EPFno,NoOfSunDays,PerDayAmt_Sunday,TotalAmt_Sunday,Poyaday,MCAllowance,Other,Month,Year) values(?,?,?,?,?,?,?,?,?) ";
            
            pst = con.prepareStatement(sql);
            
            int row_count = jtable_salary_earnings.getRowCount();
            
            for (int row = 0; row < row_count; row++) {
                
                String epf = jtable_salary_earnings.getValueAt(row, 0).toString();
                String sunday = jtable_salary_earnings.getValueAt(row, 1).toString();
                String rate = jtable_salary_earnings.getValueAt(row, 2).toString();
                String sun_total = jtable_salary_earnings.getValueAt(row, 3).toString();
                String poya = jtable_salary_earnings.getValueAt(row, 4).toString();
                String mc = jtable_salary_earnings.getValueAt(row, 5).toString();
                String other = jtable_salary_earnings.getValueAt(row, 6).toString();
                String month = jtable_salary_earnings.getValueAt(row, 7).toString();
                String year = jtable_salary_earnings.getValueAt(row, 8).toString();
                
                pst.setString(1, epf);
                pst.setString(2, sunday);
                pst.setString(3, rate);
                pst.setString(4, sun_total);
                pst.setString(5, poya);
                pst.setString(6, mc);
                pst.setString(7, other);
                pst.setString(8, month);
                pst.setString(9, year);
                
                pst.addBatch();
            }
            pst.executeBatch();
            DefaultTableModel dtm = (DefaultTableModel) jtable_salary_earnings.getModel();
            dtm.setRowCount(0);
            dtm.getDataVector().removeAllElements();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Save Holiday Rates [ER_001]");
            
        }
        
    }
    
    private static Double number_Of_DaysInMonth = 0.00;
    
    private void get_month_and_year() {
        
        Date date = jDateChooser1.getDate();
        if (date == null) {
            jLabel2.setForeground(Color.red);
            
        } else {
            
            int m = jDateChooser1.getDate().getMonth();
            
            int year = jDateChooser1.getDate().getYear();
            int j = year - 100;
            int real_year = j + 2000;
            txt_Year.setText(Integer.toString(real_year));
            
            int d = m + 1;
            String d1 = null;
            
            if (d == 1) {
                d1 = "January";
                number_Of_DaysInMonth = 31.0;
            }
            if (d == 2) {
                d1 = "February";
                if ((real_year % 400 == 0) || ((real_year % 4 == 0) && (real_year % 100 != 0))) {
                    number_Of_DaysInMonth = 29.0;
                } else {
                    number_Of_DaysInMonth = 28.0;
                }
                
            }
            if (d == 3) {
                d1 = "March";
                number_Of_DaysInMonth = 31.0;
            }
            if (d == 4) {
                d1 = "April";
                number_Of_DaysInMonth = 30.0;
            }
            if (d == 5) {
                d1 = "May";
                number_Of_DaysInMonth = 31.0;
            }
            if (d == 6) {
                d1 = "June";
                number_Of_DaysInMonth = 30.0;
            }
            if (d == 7) {
                d1 = "July";
                number_Of_DaysInMonth = 31.0;
            }
            if (d == 8) {
                d1 = "August";
                number_Of_DaysInMonth = 31.0;
            }
            if (d == 9) {
                d1 = "September";
                number_Of_DaysInMonth = 30.0;
            }
            if (d == 10) {
                d1 = "October";
                number_Of_DaysInMonth = 31.0;
            }
            if (d == 11) {
                d1 = "November";
                number_Of_DaysInMonth = 30.0;
            }
            if (d == 12) {
                d1 = "December";
                number_Of_DaysInMonth = 31.0;
            }

            //String d1 = Integer.toHexString(d);
            txt_month.setText(d1);
            
            System.out.println(number_Of_DaysInMonth);
            
        }
        
    }
    
    private void calculate_balance_shifts_in_Tbale() {
        
        Double tbl_night;
        Double tbl_day;
        Double tbl_half;
        
        if (cb_night.isSelected()) {
            tbl_night = 1.0;
        } else {
            tbl_night = 0.0;
        }
        if (cb_day.isSelected()) {
            tbl_day = 1.0;
        } else {
            tbl_day = 0.0;
        }
        
        if (cb_halfLeave.isSelected()) {
            tbl_half = 0.5;
        } else {
            tbl_half = 0.0;
        }
        Double left_shifts = Double.parseDouble(lbl_shifts_left.getText());
        Double x = left_shifts - (tbl_night + tbl_day + tbl_half);
        lbl_shifts_left.setText(Double.toString(x));
        
    }
    
    private void calculate_shifts_after_remove_row() {
        int row = tbl_atten.getModel().getRowCount();
        
        int select_row = tbl_atten.getSelectedRow();
        
        if (row == 0) {
        } else {
            
            Double table_day = Double.parseDouble(tbl_atten.getModel().getValueAt(select_row, 2).toString());
            Double table_night = Double.parseDouble(tbl_atten.getModel().getValueAt(select_row, 3).toString());
            Double table_half = Double.parseDouble(tbl_atten.getModel().getValueAt(select_row, 4).toString());
            
            Double table_HalfDay = table_half / 2;
            
            Double Table_total = table_day + table_night + table_HalfDay;
            Double left_shifts = Double.parseDouble(lbl_shifts_left.getText());
            
            Double balance = left_shifts + Table_total;
            
            lbl_shifts_left.setText(Double.toString(balance));
            
        }
        
    }
    
    private void get_carder_and_enterd_shifts_total() {
        
        if (cmb_defLocation.getSelectedItem().equals("=Location=") | jDateChooser1.getDate() == null) {
        } else {
            
            try {
                
                int days_in_Month = jDateChooser1.getCalendar().getActualMaximum(Calendar.DATE);
                
                int guards = 0;
                
                Double total_shifts = 0.00;
                Double Entered_shifts = 0.00;

//get location carder details
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select sum(NoOfGuards),COUNT(*) from location_carder where LocCode = '" + txt_locCode.getText() + "'");
                while (rs.next()) {
                    
                    int count = Integer.parseInt(rs.getString("COUNT(*)"));
                    if (count == 0) {
                        guards = 0;
                    } else {
                        
                        guards = Integer.parseInt(rs.getString("sum(NoOfGuards)"));
                    }
                }

//get attendence details of relevent location
                Statement st1 = DbConnection.getconnection().createStatement();
                ResultSet rs1 = st1.executeQuery("select count(EPFno),sum(DayShift+NightShift),sum(HalfDayShift) from emp_atten_main where Location = '" + txt_locCode.getText() + "' and Month = '" + txt_month.getText() + "' and Year = '" + txt_Year.getText() + "'");
                while (rs1.next()) {

                    //System.out.println(rs1.getString("sum(DayShift+NightShift)"));
                    int Count = Integer.parseInt(rs1.getString("count(EPFno)"));
                    if (Count == 0) {
                        
                        Entered_shifts = 0.00;
                    } else {
                        Double day_night = Double.parseDouble(rs1.getString("sum(DayShift+NightShift)"));
                        Double halfDay = Double.parseDouble(rs1.getString("sum(HalfDayShift)"));
                        Double HalfDay_shifts = halfDay / 2;
                        
                        Entered_shifts = day_night + HalfDay_shifts;
                    }

                    //System.out.println(Entered_shifts);
                }

// shifts calculate
                int All_Shifts = days_in_Month * guards;
                
                total_shifts = (double) All_Shifts;
                
                Double Count_Down = total_shifts - Entered_shifts;
                
                lbl_total_shifts.setText(Double.toString(total_shifts));
                lbl_shifts_left.setText(Double.toString(Count_Down));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    private void clear() {
//        jDateChooser1.setEnabled(true);
//        cmb_defLocation.setEnabled(true);
//        lbl_photo.setIcon(null);
//        txt_empid.setText("");
//
//        txt_search.setText("");
//        cb_day.setSelected(false);
//        cb_night.setSelected(false);
//        cb_fullLeave.setSelected(false);
//        cb_halfLeave.setSelected(false);
//        
//        txt_EffectiveShiftRatess.setText("0.00");
//        txt_empNic.setText("");
//        txt_name.setText("");
//        txt_shiftRate.setText("0.00");
//        cmb_defLocation.setSelectedItem("=Location=");
//        cb_fullLeave.setEnabled(true);
//        cb_halfLeave.setEnabled(true);
//        cb_day.setEnabled(true);
//        cb_night.setEnabled(true);
//
//        //cmb_EffectiveRank.removeAllItems();
//        cmb_EffectiveRank.setSelectedItem("==Effective Rank==");

        jDateChooser1.setEnabled(true);
        // cmb_defLocation.setEnabled(true);
        lbl_photo.setIcon(null);
        txt_empid.setText("");
        
        txt_search.setText("");
        cb_day.setSelected(false);
        cb_night.setSelected(false);
        cb_fullLeave.setSelected(false);
        cb_halfLeave.setSelected(false);
        txt_EffectiveShiftRatess.setText("0.00");
//        txt_empNic.setText("");

        txt_shiftRate.setText("0.00");
        // cmb_defLocation.setSelectedItem("=Location=");
        cb_fullLeave.setEnabled(true);
        cb_halfLeave.setEnabled(true);
        cb_day.setEnabled(true);
        cb_night.setEnabled(true);
        txt_locCode.setText("");
        txt_def_loc.setText("");
        lbl_total_shifts.setText("00.00");
        lbl_shifts_left.setText("00.00");
        
        DefaultTableModel dtm = (DefaultTableModel) table_day_carder.getModel();
        dtm.setRowCount(0);
        
        DefaultTableModel dtm1 = (DefaultTableModel) table_night_carder.getModel();
        dtm1.setRowCount(0);
        
        clear_rank_cmb();
        
    }
    
    private void previous_atten_checker() {
//in DB
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser1.getDate());
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from emp_atten_main where EPFno='" + txt_empid.getText() + "' and Date='" + date + "'");
            
            while (rs.next()) {
                
                int dayshift = Integer.parseInt(rs.getString("DayShift"));
                int nightshift = Integer.parseInt(rs.getString("NightShift"));
                int halfshift = Integer.parseInt(rs.getString("HalfDayShift"));
                
                if (dayshift > 0) {
                    cb_day.setSelected(false);
                    // cb_day.setEnabled(false);

                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Day Shift is already Entered to Selected Date.  ");
                    
                }
                
                if (nightshift > 0) {
                    cb_night.setSelected(false);
                    //cb_night.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Night Shift is already Entered to Selected Date.  ");
                }
                
                if (halfshift > 0) {
                    cb_halfLeave.setSelected(false);
                    //cb_halfLeave.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Half Day Shift is already Entered to Selected Date.  ");
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void table_attn_check() {
        //in Table
        int row_count = tbl_atten.getRowCount();
        System.out.println(row_count);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (row_count > 0) {
            
            String New_Date = sdf.format(jDateChooser1.getDate());
            System.out.println(New_Date);
            for (int row = 0; row < row_count; row++) {
                
                String Date = tbl_atten.getValueAt(row, 0).toString();
                String EPFno = tbl_atten.getValueAt(row, 1).toString();
                String Day = tbl_atten.getValueAt(row, 2).toString();
                String Night = tbl_atten.getValueAt(row, 3).toString();
                String Half = tbl_atten.getValueAt(row, 4).toString();
                
                String New_EPF = txt_empid.getText();
                
                System.out.println(Date);
                System.out.println(Day);
                System.out.println(Night);
                
                if (Date.equals(New_Date) && EPFno.equals(New_EPF)) {
                    
                    if (cb_day.isSelected() && Day.equals("1")) {
                        cb_day.setSelected(false);
                        JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Day Shift is already Entered to Selected Date.  ");
                    }
                    
                    if (cb_night.isSelected() && Night.equals("1")) {
                        cb_night.setSelected(false);
                        JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Night Shift is already Entered to Selected Date.  ");
                    }
                    
                    if (cb_halfLeave.isSelected() && Half.equals("1")) {
                        cb_halfLeave.setSelected(false);
                        JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s HalfDay Shift is already Entered to Selected Date.  ");
                    }
                    
                } else {
                    
                }
                
            }
        }
        
    }
    
    private void loc_carder_DAY() {
        DefaultTableModel dtm = (DefaultTableModel) table_day_carder.getModel();
        dtm.setRowCount(0);
        
        try {
            
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from location_carder where LocCode='" + txt_locCode.getText() + "' and CarderType='Day'");
            while (rs.next()) {
                
                Vector v = new Vector();
                
                v.add(rs.getString("Rank"));
                dtm.addRow(v);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }
    
    private void loc_carder_NIGHT() {
        
        DefaultTableModel dtm = (DefaultTableModel) table_night_carder.getModel();
        dtm.setRowCount(0);
        
        try {
            
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from location_carder where LocCode='" + txt_locCode.getText() + "' and CarderType='Night'");
            while (rs.next()) {
                
                Vector v = new Vector();
                
                v.add(rs.getString("Rank"));
                dtm.addRow(v);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EMP_COM;
    private javax.swing.JButton btn_ADD;
    private javax.swing.JButton btn_RemoveRow;
    private javax.swing.JButton btn_RemoveRow1;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_extraShifts;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb_day;
    private javax.swing.JCheckBox cb_fullLeave;
    private javax.swing.JCheckBox cb_halfLeave;
    private javax.swing.JCheckBox cb_night;
    private javax.swing.JComboBox cmb_EffectiveRank;
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton jrb_day_rate;
    private javax.swing.JRadioButton jrb_night_rate;
    private javax.swing.JTable jtable_salary_earnings;
    private javax.swing.JLabel lbl_basic;
    private javax.swing.JLabel lbl_gross;
    private javax.swing.JLabel lbl_max_shift_permonth;
    public static javax.swing.JLabel lbl_photo;
    private javax.swing.JLabel lbl_rowCount;
    private javax.swing.JLabel lbl_shifts_left;
    private javax.swing.JLabel lbl_total_shifts;
    private javax.swing.JTable table_day_carder;
    private javax.swing.JTable table_night_carder;
    private javax.swing.JTable tbl_atten;
    private javax.swing.JTextField txt_EffectiveShiftRatess;
    private javax.swing.JTextField txt_OT;
    private javax.swing.JTextField txt_OT_Rate;
    private javax.swing.JTextField txt_Year;
    private javax.swing.JTextField txt_def_loc;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_month;
    private javax.swing.JTextField txt_rank;
    public static javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_shiftRate;
    // End of variables declaration//GEN-END:variables
}
