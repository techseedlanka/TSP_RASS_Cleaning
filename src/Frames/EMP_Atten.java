/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class EMP_Atten extends javax.swing.JFrame {

    /**
     * Creates new form EMP_Atten
     */
    private ImageIcon format = null;

    public EMP_Atten() {
        initComponents();
        auto_completer();
        get_Location_Details();
        TitleBar();

        // btn_extraShifts.setText("<html>Add Extra<br> Shifts to<br> Carder</html>");
        tbl_atten.setFont(new Font("Times New Roman", Font.PLAIN, 14));

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
                String code = rs.getString("EPFno");
                String Employeecode = rs.getString("EmployeeNo");
                String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");

                ta.addItem(code);
                ta.addItem(NameWithInitials);
                ta.addItem(nic);
                ta.addItem(Employeecode);
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

        tbl_atten.getColumnModel().getColumn(0).setPreferredWidth(100);

        try {
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg where isDefault=1 ");
            while (rs.next()) {
                String ComName = rs.getString("ComName");
                this.setTitle("TechSeed Payroll" + "    -    " + ComName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setResizable(false);
        ImageIcon img = new ImageIcon("src\\Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

    private void clear_rank_cmb() {
        cmb_EffectiveRank.removeAllItems();
        cmb_EffectiveRank.addItem("==Effective Rank==");

    }

    private void get_real_Month_Year() {
        Date date = jDateChooser1.getDate();
        if (date == null) {
            jLabel2.setForeground(Color.red);

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

            int year = jDateChooser1.getDate().getYear();
            int j = year - 100;
            int k = j + 2000;

            txt_Year.setText(Integer.toString(k));

        }

    }
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

            if (!(cb_day.isSelected() | cb_night.isSelected() | cb_fullLeave.isSelected()) && (txt_EPFno.getText().isEmpty())) {

            } else {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooser1.getDate());

                get_real_Month_Year();

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
                t.EMPID = txt_EPFno.getText();
                t.day = day;
                t.night = night;
                t.fullleave = full;
                t.halfleave = half;
                t.month = txt_month.getText();
                t.year = txt_Year.getText();
                t.EffectRank = cmb_EffectiveRank.getSelectedItem().toString();
                t.EffectRate = txt_EffectiveShiftRatess.getText();
                t.DefCompany = EMP_COM.getText();
                t.EmployeeNo = txt_EmployeeNo.getText();
                t.Location = txt_locCode.getText();

                al.add(t);
                DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
                Vector v = new Vector();

                v.add(date);
                v.add(txt_EPFno.getText());
                v.add(txt_EmployeeNo.getText());
                v.add(day);
                v.add(night);
                //v.add(full);
                v.add(half);
                v.add(txt_month.getText());
                v.add(txt_Year.getText());
                v.add(txt_EffectiveShiftRatess.getText());
                v.add(cmb_EffectiveRank.getSelectedItem().toString());
                v.add(EMP_COM.getText());
                v.add(txt_locCode.getText());

                dtm.addRow(v);

                calculate_balance_shifts_in_Tbale();

                //change_rates_save();
                cb_day.setEnabled(true);
                cb_night.setEnabled(true);
                cb_fullLeave.setEnabled(true);
                cb_halfLeave.setEnabled(true);

                cb_day.setSelected(false);
                cb_night.setSelected(false);
                cb_fullLeave.setSelected(false);
                cb_halfLeave.setSelected(false);

            }

        } else if (total == 0) {

            JOptionPane.showMessageDialog(rootPane, "  Please Enter Selected Location's Employee Carder in order to Add Attendance Details ");
        } else {

        }

    }

    private void change_rates_save() {
        //Saving when effective & Default shift rates are having diiference

        String EffectiveRate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(jDateChooser1.getDate());

        try {

            Statement st = DbConnection.getconnection().createStatement();

            if (cb_fullLeave.isSelected()) {

            } else {
                if (txt_EffectiveShiftRatess.getText().equals(txt_shiftRate.getText())) {

                } else {

                    if (cb_night.isSelected()) {
                        night = 1;
                        EffectiveRate = txt_EffectiveShiftRatess.getText();
                    } else {
                        night = 0;
                    }
                    if (cb_day.isSelected()) {
                        day = 1;
                        EffectiveRate = txt_EffectiveShiftRatess.getText();
                    } else {
                        day = 0;
                    }
                    if (cb_halfLeave.isSelected()) {
                        half = 1;
                        Double rate = Double.parseDouble(txt_EffectiveShiftRatess.getText());
                        Double halfRate = rate / 2;
                        EffectiveRate = Double.toString(halfRate);
                    } else {
                        half = 0;
                    }

                    String DayShift = Integer.toString(day);
                    String NightShift = Integer.toString(night);
                    String HalfShift = Integer.toString(half);

                    st.executeUpdate("insert into emp_atten_rate_changed_temp values('" + txt_EPFno.getText() + "','" + cmb_defLocation.getSelectedItem() + "','" + EffectiveRate + "','" + DayShift + "','" + NightShift + "','" + HalfShift + "','" + txt_Year.getText() + "','" + txt_month.getText() + "','" + date + "','pending') ");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
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
                    ResultSet rs = st.executeQuery("select * from location_shift_rates where LocCode='" + loc + "' and Rank ='" + rank + "'");
                    while (rs.next()) {

                        txt_EffectiveShiftRatess.setText(rs.getString("ShiftRate"));

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
        jPanel1 = new javax.swing.JPanel();
        txt_EPFno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_shiftRate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_empNic = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lbl_photo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_EmployeeNo = new javax.swing.JTextField();
        EMP_COM = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        cb_day = new javax.swing.JCheckBox();
        cb_night = new javax.swing.JCheckBox();
        cb_fullLeave = new javax.swing.JCheckBox();
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
        cmb_EffectiveRank = new javax.swing.JComboBox();
        txt_EffectiveShiftRatess = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

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
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 10));

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
        getContentPane().add(lbl_total_shifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 250, 70, 20));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 480, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 1080, 0));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Employee Search (Name/ID) :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 103, -1, 20));

        btn_ADD.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_ADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_ADD.setText("A D D");
        btn_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 110, -1));

        tbl_atten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_atten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "EPF no", "EMP no", "Day  ", "Night ", "Half Day", "Month", "Year", "Rate", "Ef.Rank", "COM."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
            tbl_atten.getColumnModel().getColumn(2).setResizable(false);
            tbl_atten.getColumnModel().getColumn(9).setResizable(false);
            tbl_atten.getColumnModel().getColumn(10).setResizable(false);
            tbl_atten.getColumnModel().getColumn(10).setPreferredWidth(1);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 184, 560, 290));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Employee Details"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_EPFno.setEditable(false);
        txt_EPFno.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txt_EPFno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 60, 21));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("EPF No.  :-");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        txt_name.setEditable(false);
        txt_name.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 210, 21));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Name :-");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jPanel1.add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 90, 21));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Rank :-");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        txt_shiftRate.setEditable(false);
        txt_shiftRate.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel1.add(txt_shiftRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 90, 21));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText(" NIC No.  :-");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, 20));

        txt_empNic.setEditable(false);
        txt_empNic.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txt_empNic, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 120, 21));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setText("Maximize Photo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 130, 40));

        lbl_photo.setBackground(new java.awt.Color(102, 204, 255));
        lbl_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 50, 40));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("EMP. No.  :-");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, 20));

        txt_EmployeeNo.setEditable(false);
        txt_EmployeeNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txt_EmployeeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 60, 21));

        EMP_COM.setText("EMP_Company");
        jPanel1.add(EMP_COM, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 80, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 490, 130));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton4.setText("Re-Fresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 150, 40));

        cb_day.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        cb_day.setText("Day Shift");
        cb_day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_dayActionPerformed(evt);
            }
        });
        getContentPane().add(cb_day, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        cb_night.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        cb_night.setText("Night Shift");
        cb_night.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nightActionPerformed(evt);
            }
        });
        getContentPane().add(cb_night, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        cb_fullLeave.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        cb_fullLeave.setText("Full Day Leave");
        cb_fullLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_fullLeaveActionPerformed(evt);
            }
        });
        getContentPane().add(cb_fullLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, -1));

        cb_halfLeave.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        cb_halfLeave.setText("Half Day");
        cb_halfLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_halfLeaveActionPerformed(evt);
            }
        });
        getContentPane().add(cb_halfLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 80, -1));

        btn_Save.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_Save.setText("Save");
        btn_Save.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, 120, 90));

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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 360, 30));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, 90, 23));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 89, 1080, 10));

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
        getContentPane().add(btn_RemoveRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 330, 150, 40));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setText("Location:-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, 40));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Balance Shifts");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 230, 90, 20));

        lbl_shifts_left.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_shifts_left.setText("00.00");
        lbl_shifts_left.setToolTipText("");
        getContentPane().add(lbl_shifts_left, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 250, 90, 20));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Total Shifts");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 230, -1, 20));

        btn_extraShifts.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_extraShifts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_extraShifts.setText("Add Extra Shifts to Curret Carder");
        btn_extraShifts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_extraShiftsActionPerformed(evt);
            }
        });
        getContentPane().add(btn_extraShifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 430, -1, 40));

        table_night_carder.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        table_night_carder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Night Carder"
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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 100, 150));

        table_day_carder.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        table_day_carder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day Carder"
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

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 100, 150));

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
        getContentPane().add(cmb_EffectiveRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, -1, 25));

        txt_EffectiveShiftRatess.setEditable(false);
        txt_EffectiveShiftRatess.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_EffectiveShiftRatess.setText("0");
        txt_EffectiveShiftRatess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EffectiveShiftRatessActionPerformed(evt);
            }
        });
        getContentPane().add(txt_EffectiveShiftRatess, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, 90, 25));

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel5.setText("Location Shift Rate & Effective Rank ; ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, -1, -1));

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

        if (cmb_EffectiveRank.getSelectedItem().toString().equals("==Effective Rank==") | cmb_EffectiveRank.getSelectedItem().toString().equals("")) {

            JOptionPane.showMessageDialog(rootPane, "Please select the relevent rank in 'Effective Rank / Shift Rate' ");

        } else {

            previous_atten_checker();

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from location_shift_rates where LocCode='" + cmb_defLocation.getSelectedItem().toString() + "' and Rank ='" + cmb_EffectiveRank.getSelectedItem().toString() + "'");
                while (rs.next()) {

                    txt_EffectiveShiftRatess.setText(rs.getString("ShiftRate"));

                }
            } catch (Exception e) {
            }
            if (txt_EmployeeNo.getText().isEmpty()) { //!(txt_EmployeeNo.getText().isEmpty()  | txt_EPFno.getText().isEmpty())

                JOptionPane.showMessageDialog(rootPane, " Please select an Employee ");
            } else {

                if (!(cb_halfLeave.isSelected() | cb_day.isSelected() | cb_fullLeave.isSelected() | cb_night.isSelected())) {

                    JOptionPane.showMessageDialog(rootPane, "Empty Shift...");

                } else {

                    if (txt_shiftRate.getText().isEmpty()) {
                        txt_shiftRate.setText("0.00");
                    }

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
                        int reply = JOptionPane.showConfirmDialog(rootPane, "Shift RAtes are not equal. do you want to proceed with the Location Shift Rate?", null, JOptionPane.YES_NO_OPTION);
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
                            int reply1 = JOptionPane.showConfirmDialog(rootPane, "Do you want to proceed with the emp's default shift rate?", null, JOptionPane.OK_OPTION);
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
            try {

                lbl_photo.setIcon(null);
                String search = txt_search.getText();

                if (search.isEmpty()) {
                } else {

                    Statement st = DbConnection.getconnection().createStatement();
                    ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + search + "' OR EPFno='" + search + "'OR NameWithInitials='" + search + "'OR NIC='" + search + "'  ");
                    while (rs.next()) {
                        String code = rs.getString("EPFno");
                        String nic = rs.getString("NIC");
                        String name = rs.getString("NameWithInitials");
                        String com = rs.getString("DefCompany");
                        String EMP = rs.getString("EmployeeNo");
                        String rank = rs.getString("Designation");
                        System.out.println(EMP);

                        byte[] imagedata = rs.getBytes("EMPImage");

                        if (imagedata == null) {
                            lbl_photo.setText("No Image");
                            lbl_photo.setForeground(Color.red);

                            txt_EPFno.setText(code);
                            txt_name.setText(name);
                            txt_empNic.setText(nic);
                            txt_shiftRate.setText(rs.getString("ShiftRate"));
                            txt_rank.setText(rank);
                            EMP_COM.setText(com);
                            txt_EmployeeNo.setText(rs.getString("EmployeeNo"));

                        } else {

                            format = new ImageIcon(imagedata);
                            lbl_photo.setIcon(format);

                            txt_EPFno.setText(code);
                            txt_name.setText(name);
                            txt_empNic.setText(nic);
                            txt_shiftRate.setText(rs.getString("ShiftRate"));
                            txt_rank.setText(rank);
                            EMP_COM.setText(com);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            try {
//
//                lbl_photo.setIcon(null);
//                
//                
//                
//
//                Statement st = DbConnection.getconnection().createStatement();
//                ResultSet rs = st.executeQuery("select * from employee_reg");
//                while (rs.next()) {
//                    String code = rs.getString("EPFno");
//                    String nic = rs.getString("NIC");
//                    String name = rs.getString("NameWithInitials");
//                    String com = rs.getString("DefCompany");
//                    String EMP = rs.getString("EmployeeNo");
//                    //Double rate = Double.parseDouble(rs.getString("ShiftRate"));
//
//                    String rank = rs.getString("Designation");
//
//                    byte[] imagedata = rs.getBytes("EMPImage");
//
//                    if (txt_search.getText().equals(code) || txt_search.getText().equals(name) || txt_search.getText().equals(nic) || txt_search.getText().equals(EMP)) {
//
//                        if (imagedata == null) {
//                            lbl_photo.setText("No Image");
//                            lbl_photo.setForeground(Color.red);
//
//                            txt_EPFno.setText(code);
//                            txt_name.setText(name);
//                            txt_empNic.setText(nic);
//                            txt_shiftRate.setText(rs.getString("ShiftRate"));
//                            txt_rank.setText(rank);
//                            EMP_COM.setText(com);
//                            txt_EMPno.setText(EMP);
//
//                        } else {
//
//                            format = new ImageIcon(imagedata);
//                            lbl_photo.setIcon(format);
//
//                            txt_EPFno.setText(code);
//                            txt_name.setText(name);
//                            txt_empNic.setText(nic);
//                            txt_shiftRate.setText(rs.getString("ShiftRate"));
//                            txt_rank.setText(rank);
//                            EMP_COM.setText(com);
//                        }
//
//                    } else {
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            int numOfMenus = cmb_EffectiveRank.getItemCount();

            for (int i = 0; i < numOfMenus; i++) {
                String LocRanks = (String) cmb_EffectiveRank.getItemAt(i);

                if (LocRanks.contains(txt_rank.getText())) {

                    cmb_EffectiveRank.setSelectedIndex(i);

                    String loc = txt_locCode.getText();
                    String rank = cmb_EffectiveRank.getSelectedItem().toString();

                    try {

                        Statement st = DbConnection.getconnection().createStatement();
                        ResultSet rs = st.executeQuery("select * from location_shift_rates where LocCode='" + loc + "' && Rank ='" + rank + "'");
                        while (rs.next()) {

                            txt_EffectiveShiftRatess.setText(rs.getString("ShiftRate"));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    cmb_EffectiveRank.setSelectedItem("==Effective Rank==");
                }

            }

            if (cmb_EffectiveRank.getSelectedItem().equals("==Effective Rank==")) {
                JOptionPane.showMessageDialog(rootPane, "Emp's Rank & Location Ranks are not equal. Please select the Effective Rank");;
                txt_EffectiveShiftRatess.setBackground(Color.red);
                cmb_EffectiveRank.setForeground(Color.red);

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

                clear_rank_cmb();

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

                    cmb_EffectiveRank.addItem(rank);

                }

                //cmb_defLocation.setEditable(false);
                //cmb_defLocation.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            get_month_and_year();
            get_carder_and_enterd_shifts_total();
            loc_carder_NIGHT();
            loc_carder_DAY();

            txt_search.grabFocus();

        }


    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txt_locCode.setText("");
        jDateChooser1.setEnabled(true);
        cmb_defLocation.setEnabled(true);
        cmb_defLocation.setEditable(true);
        lbl_photo.setIcon(null);
        txt_EPFno.setText("");

        txt_search.setText("");
        cb_day.setSelected(false);
        cb_night.setSelected(false);
        cb_fullLeave.setSelected(false);
        cb_halfLeave.setSelected(false);
        txt_EffectiveShiftRatess.setText("0.00");
        txt_empNic.setText("");
        txt_name.setText("");
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

    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        try {
// save attendance details to emp_attn_main table 

            if (tbl_atten.getRowCount() == 0) {

                JOptionPane.showMessageDialog(rootPane, " Attendance Details are Empty...");

            } else {
                Statement st = DbConnection.getconnection().createStatement();
                for (table_data t : al) {
                    st.executeUpdate("insert into emp_atten_main values('" + t.EMPID + "','" + t.Location + "','" + t.Date + "','" + t.day + "','" + t.night + "','" + t.halfleave + "','" + t.fullleave + "','" + t.month + "','" + t.year + "', 'pending','salary_pending','" + t.EffectRank + "','" + t.EffectRate + "','" + t.DefCompany + "','" + t.EmployeeNo + "')");

                }
                JOptionPane.showMessageDialog(rootPane, " Attendance Saved...!");
            }

// save tempory saved data of rate changed attendance info to permanant table
//            ResultSet rs = st.executeQuery(" SELECT  COUNT(*) FROM   emp_atten_rate_changed_temp ");
//
//            while (rs.next()) {
//
//                String count = rs.getString("COUNT(*)");
//                int countrows = Integer.parseInt(count);
//
//                if (countrows == 0) {
//
//                } else {
//                    Statement st1 = DbConnection.getconnection().createStatement();
//                    ResultSet rs1 = st1.executeQuery(" SELECT  *  FROM   emp_atten_rate_changed_temp where Location='" + cmb_defLocation.getSelectedItem().toString() + "' ");
//                    while (rs1.next()) {
//
//                        String a = rs1.getString(1);
//                        String b = rs1.getString(2);
//                        String c = rs1.getString(3);
//                        String d = rs1.getString(4);
//                        String e = rs1.getString(5);
//                        String f = rs1.getString(6);
//                        String g = rs1.getString(7);
//                        String h = rs1.getString(8);
//                        String i = rs1.getString(9);
//                        String j = rs1.getString(10);
//
//                        Statement st2 = DbConnection.getconnection().createStatement();
//                        st2.executeUpdate("insert into emp_atten_rate_changed_shift_details values('" + a + "','" + b + "','" + c + "','" + d + "','" + e + "','" + f + "','" + g + "','" + h + "','" + i + "','" + j + "','salary_pending')");
//
//                        st2.executeUpdate("delete from emp_atten_rate_changed_temp where Location = '" + cmb_defLocation.getSelectedItem().toString() + "'");
//
//                    }
//
//                }
//
//            }
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
                ResultSet rs = st.executeQuery("select * from location_shift_rates where LocCode='" + loc + "' && Rank ='" + rank + "'");

                while (rs.next()) {

                    txt_EffectiveShiftRatess.setText(rs.getString("ShiftRate"));

                }

                txt_EffectiveShiftRatess.setBackground(Color.white);
                cmb_EffectiveRank.setForeground(Color.black);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_cmb_EffectiveRankPopupMenuWillBecomeInvisible

    private void txt_EffectiveShiftRatessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EffectiveShiftRatessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EffectiveShiftRatessActionPerformed

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

            Statement st = DbConnection.getconnection().createStatement();

            int reply = JOptionPane.showConfirmDialog(rootPane, "<html><span style=font-size:110%;>You are about to Close this Window. <br> Changes you made will not be Saved. </br> <br> <span style=color:red;> Want to close this Window? </br></span></html>", null, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {

//                st.executeUpdate("delete from emp_atten_rate_changed_temp where Location = '" + cmb_defLocation.getSelectedItem().toString() + "'");
//                
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                MAIN.jMenuItem32.setEnabled(true);
            } else {

                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(lbl_photo.getIcon());
        pt.setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed

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

                        if (cat.equals("admin")) {

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
            java.util.logging.Logger.getLogger(EMP_Atten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EMP_Atten().setVisible(true);
            }
        });
    }

    private void get_month_and_year() {

        Date date = jDateChooser1.getDate();
        if (date == null) {
            jLabel2.setForeground(Color.red);

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

            int year = jDateChooser1.getDate().getYear();
            int j = year - 100;
            int k = j + 2000;

            txt_Year.setText(Integer.toString(k));

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
        //     cmb_defLocation.setSelectedItem("=Location=");
//        cb_fullLeave.setEnabled(true);
//        cb_halfLeave.setEnabled(true);
//        cb_day.setEnabled(true);
//        cb_night.setEnabled(true);
//
//        //cmb_EffectiveRank.removeAllItems();
//        cmb_EffectiveRank.setSelectedItem("==Effective Rank==");

        jDateChooser1.setEnabled(true);
        cmb_defLocation.setEnabled(true);
        lbl_photo.setIcon(null);
        txt_EPFno.setText("");

        txt_search.setText("");
        cb_day.setSelected(false);
        cb_night.setSelected(false);
        cb_fullLeave.setSelected(false);
        cb_halfLeave.setSelected(false);
        txt_EffectiveShiftRatess.setText("0.00");
        txt_empNic.setText("");
        txt_name.setText("");
        txt_shiftRate.setText("0.00");
        cmb_defLocation.setSelectedItem("=Location=");
        cmb_defLocation.setEditable(true);
        cb_fullLeave.setEnabled(true);
        cb_halfLeave.setEnabled(true);
        cb_day.setEnabled(true);
        cb_night.setEnabled(true);
        txt_locCode.setText("");

        lbl_total_shifts.setText("00.00");
        lbl_shifts_left.setText("00.00");

        txt_EmployeeNo.setText("");

        DefaultTableModel dtm = (DefaultTableModel) table_day_carder.getModel();
        dtm.setRowCount(0);

        DefaultTableModel dtm1 = (DefaultTableModel) table_night_carder.getModel();
        dtm1.setRowCount(0);

        clear_rank_cmb();

    }

    private void previous_atten_checker() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooser1.getDate());
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from emp_atten_main where EmployeeNo='" + txt_EmployeeNo.getText() + "' and Date='" + date + "'");

            while (rs.next()) {

                int dayshift = Integer.parseInt(rs.getString("DayShift"));
                int nightshift = Integer.parseInt(rs.getString("NightShift"));
                int halfshift = Integer.parseInt(rs.getString("HalfDayShift"));

                if (dayshift > 0) {
                    cb_day.setSelected(false);
                    // cb_day.setEnabled(false);

                    JOptionPane.showMessageDialog(rootPane, txt_EPFno.getText() + "'s Day Shift is already Entered to Selected Date.  ");

                }

                if (nightshift > 0) {
                    cb_night.setSelected(false);
                    //cb_night.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, txt_EPFno.getText() + "'s Night Shift is already Entered to Selected Date.  ");
                }

                if (halfshift > 0) {
                    cb_halfLeave.setSelected(false);
                    //cb_halfLeave.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, txt_EPFno.getText() + "'s Half Day Shift is already Entered to Selected Date.  ");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
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
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_extraShifts;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable2;
    public static javax.swing.JLabel lbl_photo;
    private javax.swing.JLabel lbl_shifts_left;
    private javax.swing.JLabel lbl_total_shifts;
    private javax.swing.JTable table_day_carder;
    private javax.swing.JTable table_night_carder;
    private javax.swing.JTable tbl_atten;
    private javax.swing.JTextField txt_EPFno;
    private javax.swing.JTextField txt_EffectiveShiftRatess;
    private javax.swing.JTextField txt_EmployeeNo;
    private javax.swing.JTextField txt_Year;
    private javax.swing.JTextField txt_empNic;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_month;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_shiftRate;
    // End of variables declaration//GEN-END:variables
}
