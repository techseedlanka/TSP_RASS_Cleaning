/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.PopUp_Emp_Table.POPUP_EMP_TABLE;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class EMP_Atten_NEW extends javax.swing.JFrame {

    /**
     * Creates new form EMP_Atten
     */
    private ImageIcon format = null;

    public EMP_Atten_NEW() {

        initComponents();
        auto_completer();
        get_Location_Details();
        TitleBar();
        get_shiftType();
        jPanel2.setVisible(false);

//        tbl_atten.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//        txt_shiftRate.setText("0.00");
        Color cl = new Color(238, 152, 152);
        lbl_shifts_left.setBackground(cl);
        lbl_shifts_left.setOpaque(true);

        Color cl2 = new Color(51, 255, 151);
        lbl_total_shifts.setBackground(cl2);
        lbl_total_shifts.setOpaque(true);

        tbl_atten.getTableHeader().setUI(null);

    }

    private void get_shiftType() {
        try {

            String[] theSeven = {"Day", "Night", "Day/Night", "OTShift", "Halfday", "OFF", "Day2", "Day2/Night"};
            JComboBox combo1 = new JComboBox(theSeven);

            tbl_atten.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(combo1));
            tbl_atten.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(combo1));
            tbl_atten.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(combo1));
            tbl_atten.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(combo1));
            tbl_atten.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(combo1));
            tbl_atten.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(combo1));
            tbl_atten.getColumnModel().getColumn(15).setCellEditor(new DefaultCellEditor(combo1));

            txt_ot.setVisible(true);
            txt_ot.setEnabled(true);
            txt_ot.setEditable(true);

            tbl_atten.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(txt_ot));
            tbl_atten.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(txt_ot));
            tbl_atten.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(txt_ot));
            tbl_atten.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(txt_ot));
            tbl_atten.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(txt_ot));
            tbl_atten.getColumnModel().getColumn(14).setCellEditor(new DefaultCellEditor(txt_ot));
            tbl_atten.getColumnModel().getColumn(16).setCellEditor(new DefaultCellEditor(txt_ot));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int day;
    int night;
    int full;
    int half;

    static ArrayList<table_data> al = new ArrayList<table_data>();

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned='0' ");

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
        //this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));

    }

    private void Attn_SAVE() {
        try {
// save attendance details to emp_attn_main table 

            if (tbl_atten.getRowCount() == 0) {

                JOptionPane.showMessageDialog(rootPane, " Attendance Details are Empty...");

            } else {

                String LocCde = txt_locCode.getText();
                String Month = txt_month.getText();
                String Year = txt_Year.getText();

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = null;
                String sql = "insert into emp_atten_main (EPFno,Location,Date,DayShift,NightShift,HalfDayShift,OTShift,OTHours,Month,Year,Status,SalaryStatus,EffectiveRank,Company,DNShift,DayTwoShift) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);

                int rows = tbl_atten.getModel().getRowCount();
                int cols = tbl_atten.getColumnCount();
                aa:
                for (int r = 0; rows > r; r++) {
                    String emp = tbl_atten.getValueAt(r, 0).toString();
                    String rank = tbl_atten.getValueAt(r, 2).toString();
                    String date = null;
                    String dayshift = "0";
                    String nightshift = "0";
                    String halfshift = "0";
                    String otshift = "0";
                    String DNshift = "0";
                    String otHrs = "0";
                    String dayshift2 = "0";
                    String dayshift2_night = "0";
                    String off = "0";
                    for (int c = 3; cols > c; c++) {

                        if (tbl_atten.getValueAt(r, c) == null) {

                        } else {

                            String type = "";

                            if (tbl_atten.getValueAt(r, c) == null) {
                                type = "";
                            } else {
                                type = tbl_atten.getValueAt(r, c).toString();
                            }

//                            if (c % 2 != 0) {
                            if (c == 3) {
                                date = lbl_day_1.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }

                            }
                            if (c == 5) {
                                date = lbl_day_2.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }
                            }
                            if (c == 7) {
                                date = lbl_day_3.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }
                            }
                            if (c == 9) {
                                date = lbl_day_4.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }
                            }
                            if (c == 11) {
                                date = lbl_day_5.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }
                            }
                            if (c == 13) {
                                date = lbl_day_6.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }
                            }
                            if (c == 15) {
                                date = lbl_day_7.getText();
                                if (tbl_atten.getValueAt(r, c + 1) == null) {
                                    otHrs = "0";
                                } else {
                                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                                }
                            }

                            if (type.equals("Day")) {
                                dayshift = "1";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "0";

                            }

                            if (type.equals("Night")) {
                                dayshift = "0";
                                nightshift = "1";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "0";

                            }
                            if (type.equals("Day/Night")) {
                                dayshift = "1";
                                nightshift = "1";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "0";

                            }
                            if (type.equals("OTShift")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "1";
                                DNshift = "0";
                                dayshift2 = "0";

                            }
                            if (type.equals("Halfday")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "1";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "0";

                            }
                            if (type.equals("D&N_ANCI:")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "1";
                                dayshift2 = "0";

                            }
                            if (type.equals("OFF")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "0";

                            }

                            if (type.equals("Day2")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "1";

                            }

                            if (type.equals("Day2/Night")) {
                                dayshift = "0";
                                nightshift = "1";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "1";

                            }

                            if (type.equals("")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                dayshift2 = "0";
                            }

                            if (dayshift2.equals("0") && dayshift.equals("0") && nightshift.equals("0") && halfshift.equals("0") && otshift.equals("0") && DNshift.equals("0") && otHrs.equals("0")) { //| otHrs.contains("[a-zA-Z]+") == false
                                // JOptionPane.showMessageDialog(rootPane, "Enter Numbers Only as OT Value ");
                                //lbl_warn.setText("Enter Numbers Only as OT Value");

                            } else {
                                pst.setString(1, emp);
                                pst.setString(2, LocCde);
                                pst.setString(3, date);
                                pst.setString(4, dayshift);
                                pst.setString(5, nightshift);
                                pst.setString(6, halfshift);
                                pst.setString(7, otshift);
                                pst.setString(8, otHrs);
                                pst.setString(9, Month);
                                pst.setString(10, Year);
                                pst.setString(11, "pending");
                                pst.setString(12, "not-processed");
                                pst.setString(13, rank);
                                pst.setString(14, "Target");
                                pst.setString(15, DNshift);
                                pst.setString(16, dayshift2);
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                otHrs = "0";
                                dayshift2 = "0";
                                pst.addBatch();
                            }
                        }//relevent cell null or not
//                         
                    }//column

                    pst.executeBatch();

                }//rows
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String from_date = sdf.format(jdate_from_date.getDate());
//                String to_date = sdf.format(jdate_to_date.getDate());
//
//                pst = con.prepareStatement("select SUM(DNShift+DayShift+NightShift+(HalfDayShift/2)) from emp_atten_main where Date BETWEEN '" + from_date + "' AND '" + to_date + "'");
//                ResultSet rs1 = pst.executeQuery();
//                while (rs1.next()) {
//                    rs1.getString("SUM(DNShift+DayShift+NightShift+(HalfDayShift/2))");
//
//                }
                JOptionPane.showMessageDialog(rootPane, " Attendance Saved...!");

            }//table row count

            int r = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear the table?", "CLEAR", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {

                DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
                dtm.setRowCount(0);
                dtm.getDataVector().removeAllElements();
//                cmb_defLocation.setEnabled(true);
//                cmb_defLocation.setEditable(true);

                clear();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
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

//    private void add_to_table() {
//        
//        left = Double.parseDouble(lbl_shifts_left.getText());
//        total = Double.parseDouble(lbl_total_shifts.getText());
//        
//        if (left <= 0) {
//            System.out.println("data  not add");
//            
//            JOptionPane.showMessageDialog(rootPane, "  You are Exceeding the Total No. of Allowed Shifts for Selected Location ");
//            
//        } else if (Objects.equals(total, left) | total > left) {
//            System.out.println("data add");
//            
//            if (!(cb_day.isSelected() | cb_night.isSelected() | cb_fullLeave.isSelected()) && (txt_empid.getText().isEmpty())) {
//                
//            } else {
//                
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String date = sdf.format(jDateChooser1.getDate());
//                
//                get_month_and_year();
//                
//                table_data t = new table_data();
//                
//                if (cb_night.isSelected()) {
//                    night = 1;
//                } else {
//                    night = 0;
//                }
//                if (cb_day.isSelected()) {
//                    day = 1;
//                } else {
//                    day = 0;
//                }
//                if (cb_fullLeave.isSelected()) {
//                    full = 1;
//                } else {
//                    full = 0;
//                }
//                if (cb_halfLeave.isSelected()) {
//                    half = 1;
//                } else {
//                    half = 0;
//                }
//                
//                t.Date = date;
//                t.EMPID = txt_empid.getText();
//                t.day = day;
//                t.night = night;
//                t.fullleave = full;
//                t.halfleave = half;
//                t.month = txt_month.getText();
//                t.year = txt_Year.getText();
//                t.EffectRank = cmb_EffectiveRank.getSelectedItem().toString();
//                t.EffectRate = txt_EffectiveShiftRatess.getText();
//                t.DefCompany = EMP_COM.getText();
//                t.ot = txt_OT.getText();
//                t.otRate = txt_OT_Rate.getText();
//                
//                al.add(t);
//                DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
//                Vector v = new Vector();
//                
//                v.add(date);
//                v.add(txt_empid.getText());
//                v.add(day);
//                v.add(night);
//                //v.add(full);
//                v.add(half);
//                v.add(txt_month.getText());
//                v.add(txt_Year.getText());
//                v.add(txt_EffectiveShiftRatess.getText());
//                v.add(txt_OT.getText());
//                v.add(txt_OT_Rate.getText());
//                v.add(cmb_EffectiveRank.getSelectedItem().toString());
//                v.add(EMP_COM.getText());
//                dtm.addRow(v);
//                
//                calculate_balance_shifts_in_Tbale();
//
//                //                get_holidays();
//                //change_rates_save();
//                cb_day.setEnabled(true);
//                cb_night.setEnabled(true);
//                cb_fullLeave.setEnabled(true);
//                cb_halfLeave.setEnabled(true);
//                
//                cb_day.setSelected(false);
//                cb_night.setSelected(false);
//                cb_fullLeave.setSelected(false);
//                cb_halfLeave.setSelected(false);
//                
//                int row = dtm.getRowCount();
//                lbl_rowCount.setText(Integer.toString(row));
//                
//            }
//            
//        } else if (total == 0) {
//            
//            JOptionPane.showMessageDialog(rootPane, "  Please Enter Selected Location's Employee Cadre in order to Add Attendance Details ");
//        } else {
//            
//        }
//        
//    }
//////////////    private void get_relevent_shift_rate() {
//////////////        
//////////////        int numOfMenus = cmb_EffectiveRank.getItemCount();
//////////////        
//////////////        for (int i = 0; i < numOfMenus; i++) {
//////////////            String LocRanks = (String) cmb_EffectiveRank.getItemAt(i);
//////////////            
//////////////            if (LocRanks.contains(txt_rank.getText())) {
//////////////                
//////////////                cmb_EffectiveRank.setSelectedIndex(i);
//////////////                
//////////////                String loc = txt_locCode.getText();
//////////////                String rank = cmb_EffectiveRank.getSelectedItem().toString();
//////////////                
//////////////                try {
//////////////                    
//////////////                    Statement st = DbConnection.getconnection().createStatement();
//////////////                    ResultSet rs = st.executeQuery("select * from salary_rates where LocCode='" + loc + "' and RankCode ='" + rank + "'");
//////////////                    while (rs.next()) {
//////////////                        
//////////////                        if (jrb_day_rate.isSelected()) {
//////////////                            txt_EffectiveShiftRatess.setText(rs.getString("DayRate"));
//////////////                        }
//////////////                        if (jrb_night_rate.isSelected()) {
//////////////                            txt_EffectiveShiftRatess.setText(rs.getString("NightRate"));
//////////////                        }
//////////////                        
//////////////                    }
//////////////                } catch (Exception e) {
//////////////                    e.printStackTrace();
//////////////                }
//////////////                
//////////////            }
//////////////            
//////////////        }
//////////////        
//////////////    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_day_2 = new javax.swing.JLabel();
        lbl_day_3 = new javax.swing.JLabel();
        lbl_day_4 = new javax.swing.JLabel();
        lbl_day_5 = new javax.swing.JLabel();
        lbl_day_6 = new javax.swing.JLabel();
        lbl_day_7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jdate_from_date = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        lbl_total_shifts = new javax.swing.JLabel();
        cmb_defLocation = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_atten = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        txt_locCode = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btn_RemoveRow = new javax.swing.JButton();
        lbl_day_1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_shifts_left = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_extraShifts = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_night_carder = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_day_carder = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbl_max_shift_permonth = new javax.swing.JLabel();
        lbl_photo = new javax.swing.JLabel();
        EMP_COM = new javax.swing.JLabel();
        txt_to_date = new javax.swing.JTextField();
        txt_ot = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lbl_warn = new javax.swing.JLabel();
        btn_RemoveRow1 = new javax.swing.JButton();
        txt_rank = new javax.swing.JTextField();
        txt_empid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lbl_rowCount = new javax.swing.JLabel();
        txt_def_loc_name = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jdate_to_date = new com.toedter.calendar.JDateChooser();
        txt_Year = new javax.swing.JTextField();
        txt_month = new javax.swing.JTextField();
        txt_def_loc = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

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
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1240, 10));

        lbl_day_2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_2.setText("D2");
        getContentPane().add(lbl_day_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 105, 110, 30));

        lbl_day_3.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_3.setText("D3");
        getContentPane().add(lbl_day_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 105, 110, 30));

        lbl_day_4.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_4.setText("D4");
        getContentPane().add(lbl_day_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 105, 110, 30));

        lbl_day_5.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_5.setText("D5");
        getContentPane().add(lbl_day_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 105, 110, 30));

        lbl_day_6.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_6.setText("D6");
        getContentPane().add(lbl_day_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 105, 110, 30));

        lbl_day_7.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_7.setText("D7");
        getContentPane().add(lbl_day_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 105, 110, 30));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel1.setText("Employee Attendence Entry");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jdate_from_date.setDateFormatString("yyyy-MM-dd");
        jdate_from_date.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jdate_from_date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdate_from_dateFocusLost(evt);
            }
        });
        getContentPane().add(jdate_from_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 110, 25));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel2.setText("To :- ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, -1, 20));

        lbl_total_shifts.setBackground(new java.awt.Color(51, 255, 151));
        lbl_total_shifts.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_total_shifts.setText("00.00");
        lbl_total_shifts.setToolTipText("");
        getContentPane().add(lbl_total_shifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, 70, 20));

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
                cmb_defLocationPopupMenuWillBecomeVisible(evt);
            }
        });
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 340, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 1080, 0));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel3.setText("Employee :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, 20));

        tbl_atten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbl_atten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP no", "Name", "Rank", "D1", "OT1", "D2", "OT2", "D3", "OT3", "D4", "OT4", "D5", "OT5", "D6", "OT6", "D7", "OT7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_atten.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_atten.setDragEnabled(true);
        tbl_atten.setRowHeight(25);
        tbl_atten.getTableHeader().setReorderingAllowed(false);
        tbl_atten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_attenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_atten);
        if (tbl_atten.getColumnModel().getColumnCount() > 0) {
            tbl_atten.getColumnModel().getColumn(0).setResizable(false);
            tbl_atten.getColumnModel().getColumn(0).setPreferredWidth(70);
            tbl_atten.getColumnModel().getColumn(1).setResizable(false);
            tbl_atten.getColumnModel().getColumn(1).setPreferredWidth(230);
            tbl_atten.getColumnModel().getColumn(2).setResizable(false);
            tbl_atten.getColumnModel().getColumn(2).setPreferredWidth(60);
            tbl_atten.getColumnModel().getColumn(3).setResizable(false);
            tbl_atten.getColumnModel().getColumn(3).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(4).setResizable(false);
            tbl_atten.getColumnModel().getColumn(4).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(5).setResizable(false);
            tbl_atten.getColumnModel().getColumn(5).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(6).setResizable(false);
            tbl_atten.getColumnModel().getColumn(6).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(7).setResizable(false);
            tbl_atten.getColumnModel().getColumn(7).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(8).setResizable(false);
            tbl_atten.getColumnModel().getColumn(8).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(9).setResizable(false);
            tbl_atten.getColumnModel().getColumn(9).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(10).setResizable(false);
            tbl_atten.getColumnModel().getColumn(10).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(11).setResizable(false);
            tbl_atten.getColumnModel().getColumn(11).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(12).setResizable(false);
            tbl_atten.getColumnModel().getColumn(12).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(13).setResizable(false);
            tbl_atten.getColumnModel().getColumn(13).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(14).setResizable(false);
            tbl_atten.getColumnModel().getColumn(14).setPreferredWidth(40);
            tbl_atten.getColumnModel().getColumn(15).setResizable(false);
            tbl_atten.getColumnModel().getColumn(15).setPreferredWidth(80);
            tbl_atten.getColumnModel().getColumn(16).setResizable(false);
            tbl_atten.getColumnModel().getColumn(16).setPreferredWidth(40);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 154, 1220, 320));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton4.setText("Clear All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 130, 40));

        btn_Save.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_Save.setText("Save");
        btn_Save.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.gray));
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 523, 170, 100));

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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 200, 25));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 60, 23));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 89, 1240, 10));

        btn_RemoveRow.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_RemoveRow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        btn_RemoveRow.setText("Remove Row");
        btn_RemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveRowActionPerformed(evt);
            }
        });
        getContentPane().add(btn_RemoveRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, 150, 40));

        lbl_day_1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        lbl_day_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_day_1.setText("D1");
        getContentPane().add(lbl_day_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 105, 110, 30));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Balance Shifts");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 580, 90, 20));

        lbl_shifts_left.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_shifts_left.setText("00.00");
        lbl_shifts_left.setToolTipText("");
        getContentPane().add(lbl_shifts_left, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 80, 20));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Total Shifts");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, -1, 20));

        btn_extraShifts.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_extraShifts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_extraShifts.setText("Add Extra Shifts to Curret Carder");
        btn_extraShifts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_extraShiftsActionPerformed(evt);
            }
        });
        getContentPane().add(btn_extraShifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 290, 40));

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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 480, 90, 140));

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

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, 90, 140));

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_max_shift_permonth.setText("Max Days");
        jPanel2.add(lbl_max_shift_permonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 50, -1));

        lbl_photo.setBackground(new java.awt.Color(102, 204, 255));
        lbl_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 20));

        EMP_COM.setText("EMP_Company");
        jPanel2.add(EMP_COM, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 80, 20));

        txt_to_date.setEditable(false);
        txt_to_date.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_to_date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_to_dateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_to_dateFocusLost(evt);
            }
        });
        txt_to_date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_to_dateKeyPressed(evt);
            }
        });
        jPanel2.add(txt_to_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 100, 10));

        txt_ot.setEditable(false);
        txt_ot.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ot.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_otFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_otFocusLost(evt);
            }
        });
        txt_ot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_otMouseClicked(evt);
            }
        });
        txt_ot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_otKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_otKeyTyped(evt);
            }
        });
        jPanel2.add(txt_ot, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 30, -1));

        jButton3.setText("SAVE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton5.setText("ccheck OT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        lbl_warn.setText("jLabel6");
        jPanel2.add(lbl_warn, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 70, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 190, 40));

        btn_RemoveRow1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_RemoveRow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-data-sheet-30.png"))); // NOI18N
        btn_RemoveRow1.setText("Attendance History");
        btn_RemoveRow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveRow1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_RemoveRow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, 190, 90));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, 40, 25));

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 50, 25));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setText("EMP Photo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 100, 23));

        lbl_rowCount.setBackground(new java.awt.Color(153, 255, 204));
        lbl_rowCount.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_rowCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_rowCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lbl_rowCount.setOpaque(true);
        getContentPane().add(lbl_rowCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 480, 60, 30));

        txt_def_loc_name.setEditable(false);
        txt_def_loc_name.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(txt_def_loc_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 160, 25));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setText("Employee Count:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 480, 110, 30));

        jLabel11.setBackground(new java.awt.Color(51, 204, 255));
        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText("        Shift         |  OT  |          Shift        | OT  |          Shift       |  OT   |          Shift       |  OT   |        Shift        |  OT   |        Shift         |   OT   |         Shift       |    OT  |");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jLabel11.setOpaque(true);
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 135, 860, 20));

        jLabel4.setBackground(new java.awt.Color(51, 204, 255));
        jLabel4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel4.setText("|EMP No.|                          Name                                      |  Rank    ");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 360, 50));

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel14.setText("Location:-");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, 40));

        jLabel5.setBackground(new java.awt.Color(51, 204, 255));
        jLabel5.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel5.setText("|EMP No.|                    Name                                          |  Rank    |                                        |                                       |                                     |                                        |                                     |                                        |                                       |");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 1220, 30));

        jLabel34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel34.setText(" Month  :-");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel7.setText("From :- ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, -1, 20));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        jButton1.setText("Load Employees");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 180, 50));

        jdate_to_date.setDateFormatString("yyyy-MM-dd");
        jdate_to_date.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jdate_to_date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdate_to_dateFocusLost(evt);
            }
        });
        getContentPane().add(jdate_to_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, 110, 25));

        txt_Year.setEditable(false);
        txt_Year.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 60, 23));

        txt_month.setEditable(false);
        txt_month.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 90, 23));

        txt_def_loc.setEditable(false);
        txt_def_loc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_def_loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, 40, 25));

        jButton12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-plus-20.png"))); // NOI18N
        jButton12.setText("Add Emp.");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, -1, 27));

        jButton6.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton6.setText("Sort");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 70, -1));

        jComboBox1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "==Sort By...", "EMPno", "Name", "Rank" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 130, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // lbl_gross.setText("0.00");
            lbl_max_shift_permonth.setText("0.00");
//            txt_shiftRate.setText("0.00");
            txt_empid.setText("");
            txt_rank.setText("");
            txt_def_loc_name.setText("");
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

                            lbl_max_shift_permonth.setText(max_days);
                            txt_def_loc_name.setText(def_loc);
                            txt_def_loc.setText(def_loc);

                            Statement st5 = DbConnection.getconnection().createStatement();
                            ResultSet rs5 = st5.executeQuery("select * from location_reg where LocCode='" + def_loc + "'  ");
                            while (rs5.next()) {
                                txt_def_loc_name.setText(rs5.getString("LocName"));

                            }
//
//                    } else {
//                    }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (txt_empid.getText().isEmpty() | txt_rank.getText().isEmpty()) {
            } else {

                int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want Add this Employee?", null, JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    add_one_emp();
                } else {
                }

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

            } catch (Exception e) {
                e.printStackTrace();
            }
//            
//            get_carder_and_enterd_shifts_total();
//            loc_carder_NIGHT();
//            loc_carder_DAY();
//            
        }


    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int r = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear?", "CLEAR", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            cmb_defLocation.setEditable(true);
            cmb_defLocation.setEnabled(true);
            jButton1.setEnabled(true);
            txt_month.setText("");
            txt_Year.setText("");
            txt_def_loc.setText("");
            txt_empid.setText("");
            DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
            dtm.setRowCount(0);

            lbl_rowCount.setText("");
        } else {

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed

        Attn_SAVE();


    }//GEN-LAST:event_btn_SaveActionPerformed

    private void jdate_from_dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdate_from_dateFocusLost
        get_month_and_year();
    }//GEN-LAST:event_jdate_from_dateFocusLost

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
//        if (txt_locCode.getText().equals("** Please Select a Location ")) {
//
//            JOptionPane.showMessageDialog(rootPane, "Date & Location Can not be Empty...");
//
//            txt_locCode.grabFocus();
//
//        } else {
//
//            int m = jdate_from_date.getDate().getMonth();
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
//        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_searchFocusGained

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:

        // get_month_and_year();
//        get_carder_and_enterd_shifts_total();
    }//GEN-LAST:event_txt_locCodeFocusGained

    private void btn_RemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveRowActionPerformed

        try {

            if (tbl_atten.getSelectedRowCount() < 1) {

            } else {
                int row = tbl_atten.getSelectedRow();

                int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete selected data?", null, JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {

                    //calculate_shifts_after_remove_row();
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
                // MAIN.jMenuItem32.setEnabled(true);
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
//        MAIN.jMenuItem32.setEnabled(false);        // TODO add your handling code here:
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

    private void txt_to_dateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_to_dateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_to_dateFocusGained

    private void txt_to_dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_to_dateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_to_dateFocusLost

    private void add_days_to_selected_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String from_date = sdf.format(jdate_from_date.getDate());

        Date date;
        try {
            date = sdf.parse(from_date);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 7); // Add 07 days
            Date futureDate = cal.getTime();
            String to_date = sdf.format(futureDate);
            txt_to_date.setText(to_date);

            String from_month = from_date.substring(5, 7);
            System.out.println(from_month);

        } catch (ParseException ex) {
            Logger.getLogger(EMP_Atten_NEW.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void get_emps() {

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst;

            String sql = "select * from employee_reg where DefLocation='" + txt_locCode.getText() + "' and IsResigned='0'";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("EmployeeNo"));
                v.add(rs.getString("NameWithInitials"));
                v.add(rs.getString("Designation"));
                dtm.addRow(v);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        get_month_and_year();
        get_shiftType();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String from_date = sdf.format(jdate_from_date.getDate());
        String to_date = sdf.format(jdate_to_date.getDate());
        int i = jdate_from_date.getDate().getMonth();
        int j = jdate_to_date.getDate().getMonth();
        if (i - j == 0) {
            Date from;
            Date to;
            try {
                from = sdf.parse(from_date);
                to = sdf.parse(to_date);

                long dif = to.getTime() - from.getTime();
                int days = (int) ((dif / (1000 * 60 * 60 * 24)) + 1);

                if (days <= 7) {

                    System.out.println(days);
                    lbl_day_1.setText("");
                    lbl_day_2.setText("");
                    lbl_day_3.setText("");
                    lbl_day_4.setText("");
                    lbl_day_5.setText("");
                    lbl_day_6.setText("");
                    lbl_day_7.setText("");

                    for (int d = 0; d < days; d++) {

                        String Date = from_date.substring(8, 10);
                        String Month = from_date.substring(5, 7);
                        String Year = from_date.substring(0, 4);
                        //System.out.println(Date);

                        int x = Integer.parseInt(Date);
                        int y = (int) (x + d);

                        String new_day = String.format("%02d", y);

                        String date = Year + "-" + Month + "-" + new_day;

                        if (d == 0) {
                            lbl_day_1.setText(date);
                        }
                        if (d == 1) {
                            lbl_day_2.setText(date);
                        }
                        if (d == 2) {
                            lbl_day_3.setText(date);
                        }
                        if (d == 3) {
                            lbl_day_4.setText(date);
                        }
                        if (d == 4) {
                            lbl_day_5.setText(date);
                        }
                        if (d == 5) {
                            lbl_day_6.setText(date);
                        }
                        if (d == 6) {
                            lbl_day_7.setText(date);
                        }

                        System.out.println(date);

                        //emps
                        //get_emps();
                    }
                    get_emps();
                    int row = tbl_atten.getModel().getRowCount();
                    lbl_rowCount.setText(Integer.toString(row));

                    if (lbl_day_1.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jt));

                    }
                    if (lbl_day_2.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jt));

                    }
                    if (lbl_day_3.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(jt));

                    }
                    if (lbl_day_4.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(jt));

                    }
                    if (lbl_day_5.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(jt));

                    }
                    if (lbl_day_6.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(14).setCellEditor(new DefaultCellEditor(jt));

                    }
                    if (lbl_day_7.getText().isEmpty()) {
                        JTextField jt = new JTextField();
                        jt.setEditable(false);
                        jt.setEnabled(false);

                        tbl_atten.getColumnModel().getColumn(15).setCellEditor(new DefaultCellEditor(jt));
                        tbl_atten.getColumnModel().getColumn(16).setCellEditor(new DefaultCellEditor(jt));

                    }
                    cmb_defLocation.setEditable(false);
                    cmb_defLocation.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Maximum Date Range is 7days");
                }

            } catch (ParseException ex) {
                Logger.getLogger(EMP_Atten_NEW.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "From Date & To Date Must be in the Same Month");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jdate_to_dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdate_to_dateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jdate_to_dateFocusLost

    private void txt_to_dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_to_dateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_to_dateKeyPressed

    private void cmb_defLocationPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeVisible
    private void add_one_emp() {

        if (txt_empid.getText().isEmpty() | jdate_from_date.getDate() == null | jdate_to_date.getDate() == null | txt_locCode.getText().isEmpty()) {
        } else {

            get_month_and_year();
            get_shiftType();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String from_date = sdf.format(jdate_from_date.getDate());
            String to_date = sdf.format(jdate_to_date.getDate());
            int i = jdate_from_date.getDate().getMonth();
            int j = jdate_to_date.getDate().getMonth();
            if (i - j == 0) {
                Date from;
                Date to;
                try {
                    from = sdf.parse(from_date);
                    to = sdf.parse(to_date);

                    long dif = to.getTime() - from.getTime();
                    int days = (int) ((dif / (1000 * 60 * 60 * 24)) + 1);

                    if (days <= 7) {

                        System.out.println(days);
                        lbl_day_1.setText("");
                        lbl_day_2.setText("");
                        lbl_day_3.setText("");
                        lbl_day_4.setText("");
                        lbl_day_5.setText("");
                        lbl_day_6.setText("");
                        lbl_day_7.setText("");

                        for (int d = 0; d < days; d++) {

                            String Date = from_date.substring(8, 10);
                            String Month = from_date.substring(5, 7);
                            String Year = from_date.substring(0, 4);
                            //System.out.println(Date);

                            int x = Integer.parseInt(Date);
                            int y = (int) (x + d);

                            String new_day = String.format("%02d", y);

                            String date = Year + "-" + Month + "-" + new_day;

                            if (d == 0) {
                                lbl_day_1.setText(date);
                            }
                            if (d == 1) {
                                lbl_day_2.setText(date);
                            }
                            if (d == 2) {
                                lbl_day_3.setText(date);
                            }
                            if (d == 3) {
                                lbl_day_4.setText(date);
                            }
                            if (d == 4) {
                                lbl_day_5.setText(date);
                            }
                            if (d == 5) {
                                lbl_day_6.setText(date);
                            }
                            if (d == 6) {
                                lbl_day_7.setText(date);
                            }

                            System.out.println(date);

                        }

                        //emps
                        DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();

                        Vector v = new Vector();
                        v.add(txt_empid.getText());
                        v.add(txt_search.getText());
                        v.add(txt_rank.getText());
                        dtm.addRow(v);

                        if (lbl_day_1.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jt));

                        }
                        if (lbl_day_2.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jt));

                        }
                        if (lbl_day_3.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(jt));

                        }
                        if (lbl_day_4.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(jt));

                        }
                        if (lbl_day_5.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(jt));

                        }
                        if (lbl_day_6.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(14).setCellEditor(new DefaultCellEditor(jt));

                        }
                        if (lbl_day_7.getText().isEmpty()) {
                            JTextField jt = new JTextField();
                            jt.setEditable(false);
                            jt.setEnabled(false);

                            tbl_atten.getColumnModel().getColumn(15).setCellEditor(new DefaultCellEditor(jt));
                            tbl_atten.getColumnModel().getColumn(16).setCellEditor(new DefaultCellEditor(jt));

                        }
                        cmb_defLocation.setEditable(false);
                        cmb_defLocation.setEnabled(false);
                        jButton1.setEnabled(false);

                        txt_empid.setText("");
                        txt_search.setText("");
                        txt_rank.setText("");
                        txt_def_loc.setText("");
                        txt_def_loc_name.setText("");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Maximum Date Range is 7days");
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(EMP_Atten_NEW.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "From Date & To Date Must be in the Same Month");
            }

        }

    }
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        add_one_emp();

    }//GEN-LAST:event_jButton12ActionPerformed

    private void txt_otKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_ot.getText().toCharArray();
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
        }                   // TODO add your handling code here:
    }//GEN-LAST:event_txt_otKeyTyped

    private void txt_otKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otKeyPressed


    }//GEN-LAST:event_txt_otKeyPressed

    private void txt_otFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_otFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otFocusLost

    private void txt_otFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_otFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
// save attendance details to emp_attn_main table 

            if (tbl_atten.getRowCount() == 0) {

                JOptionPane.showMessageDialog(rootPane, " Attendance Details are Empty...");

            } else {

                String LocCde = txt_locCode.getText();
                String Month = txt_month.getText();
                String Year = txt_Year.getText();

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = null;
                String sql = "insert into emp_atten_main values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);

                int rows = tbl_atten.getModel().getRowCount();
                int cols = tbl_atten.getColumnCount();

                for (int r = 0; rows > r; r++) {
                    String emp = tbl_atten.getValueAt(r, 0).toString();
                    String rank = tbl_atten.getValueAt(r, 2).toString();
                    String date = null;
                    String dayshift = "0";
                    String nightshift = "0";
                    String halfshift = "0";
                    String otshift = "0";
                    String DNshift = "0";
                    String otHrs = "0";
                    for (int c = 3; cols > c; c++) {

                        if (tbl_atten.getValueAt(r, c) == null) {

                        } else {

                            String type = tbl_atten.getValueAt(r, c).toString();

//                            if (c % 2 != 0) {
                            if (c == 3) {
                                date = lbl_day_1.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }
                            if (c == 5) {
                                date = lbl_day_2.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }
                            if (c == 7) {
                                date = lbl_day_3.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }
                            if (c == 9) {
                                date = lbl_day_4.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }
                            if (c == 11) {
                                date = lbl_day_5.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }
                            if (c == 13) {
                                date = lbl_day_6.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }
                            if (c == 15) {
                                date = lbl_day_7.getText();
                                otHrs = tbl_atten.getValueAt(r, c + 1).toString();
                            }

                            if (type.equals("Day")) {
                                dayshift = "1";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                            }

                            if (type.equals("Night")) {
                                dayshift = "0";
                                nightshift = "1";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                            }
                            if (type.equals("Day/Night")) {
                                dayshift = "1";
                                nightshift = "1";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                            }
                            if (type.equals("OTShift")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "1";
                                DNshift = "0";
                            }
                            if (type.equals("Halfday")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "1";
                                otshift = "0";
                                DNshift = "0";
                            }
                            if (type.equals("D&N_ANCI:")) {
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "1";
                            }

                            if (dayshift.equals("0") && nightshift.equals("0") && halfshift.equals("0") && otshift.equals("0") && DNshift.equals("0") && otHrs.equals("0")) {

                            } else {
                                pst.setString(1, emp);
                                pst.setString(2, LocCde);
                                pst.setString(3, date);
                                pst.setString(4, dayshift);
                                pst.setString(5, nightshift);
                                pst.setString(6, halfshift);
                                pst.setString(7, otshift);
                                pst.setString(8, otHrs);
                                pst.setString(9, Month);
                                pst.setString(10, Year);
                                pst.setString(11, "pending");
                                pst.setString(12, "not-processed");
                                pst.setString(13, rank);
                                pst.setString(14, "Target");
                                pst.setString(15, DNshift);
                                dayshift = "0";
                                nightshift = "0";
                                halfshift = "0";
                                otshift = "0";
                                DNshift = "0";
                                otHrs = "0";
                                pst.addBatch();
                            }
                        }//relevent cell null or not
//                         
                    }//column

                    pst.executeBatch();
                }//rows

                JOptionPane.showMessageDialog(rootPane, " Attendance Saved...!");
            }//table row count

            DefaultTableModel dtm = (DefaultTableModel) tbl_atten.getModel();
            dtm.setRowCount(0);
            dtm.getDataVector().removeAllElements();
            cmb_defLocation.setEnabled(true);

            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int rows = tbl_atten.getModel().getRowCount();
        int cols = tbl_atten.getColumnCount();

        for (int r = 0; rows > r; r++) {

            String otHrs = "0";
            for (int c = 4; cols > c; c++) {

                if (c % 2 == 0) {

                    otHrs = tbl_atten.getValueAt(r, c + 1).toString();

                    if (otHrs.contains("[a-zA-Z]+") == false) {
                        break;

                    } else {

                    }
                }

            }//column

        }//rows
        JOptionPane.showMessageDialog(rootPane, "Enter Numbers Only as OT Value ");

    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_otMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_otMouseClicked


    }//GEN-LAST:event_txt_otMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (jComboBox1.getSelectedIndex() == 0) {

        } else {

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbl_atten.getModel());
            tbl_atten.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();

            int columnIndexToSort = 1;
            columnIndexToSort = jComboBox1.getSelectedIndex() - 1;

            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

            sorter.setSortKeys(sortKeys);
            sorter.sort();
        }


    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(EMP_Atten_NEW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten_NEW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten_NEW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EMP_Atten_NEW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EMP_Atten_NEW().setVisible(true);
            }
        });
    }

    private void get_holidays() {

        try {

            if (jdate_from_date.getDate() == null) {

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
                String date = sdf.format(jdate_from_date.getDate());

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = null;
                ResultSet rs = null;
                String sql = "select * from poya_days where Date='" + date + "' ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {

                    String poya = rs.getString("Date");

                    if (poya.equals(date)) {
//                        
//                      Double basic = Double.parseDouble(lbl_basic.getText());
//                        Double poya_rate = (basic / 26) * 1.5;
//                        
//                        DefaultTableModel tm = (DefaultTableModel) jtable_salary_earnings.getModel();
//                        
//                        Vector v = new Vector();
//                        
//                        v.add(txt_empid.getText());
//                        v.add("0");
//                        v.add("0");
//                        v.add("0");
//                        v.add(String.format("%.2f", (poya_rate)));
//                        v.add("0");
//                        v.add("0");
//                        v.add(txt_month.getText());
//                        v.add(txt_Year.getText());
//                        
//                        tm.addRow(v);

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Getting Sundays [ER_001]");

        }

    }

    private static Double number_Of_DaysInMonth = 0.00;

    private void get_month_and_year() {

        Date date = jdate_from_date.getDate();
        if (date == null) {
            jLabel2.setForeground(Color.red);

        } else {

            int m = jdate_from_date.getDate().getMonth();

            int year = jdate_from_date.getDate().getYear();
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

//    private void calculate_balance_shifts_in_Tbale() {
//        
//        Double tbl_night;
//        Double tbl_day;
//        Double tbl_half;
//        
//        if (cb_night.isSelected()) {
//            tbl_night = 1.0;
//        } else {
//            tbl_night = 0.0;
//        }
//        if (cb_day.isSelected()) {
//            tbl_day = 1.0;
//        } else {
//            tbl_day = 0.0;
//        }
//        
//        if (cb_halfLeave.isSelected()) {
//            tbl_half = 0.5;
//        } else {
//            tbl_half = 0.0;
//        }
//        Double left_shifts = Double.parseDouble(lbl_shifts_left.getText());
//        Double x = left_shifts - (tbl_night + tbl_day + tbl_half);
//        lbl_shifts_left.setText(Double.toString(x));
//        
//    }
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

        if (cmb_defLocation.getSelectedItem().equals("=Location=") | jdate_from_date.getDate() == null) {
        } else {

            try {

                int days_in_Month = jdate_from_date.getCalendar().getActualMaximum(Calendar.DATE);

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
//                ResultSet rs1 = st1.executeQuery("select count(EPFno),sum(DayShift+NightShift),sum(HalfDayShift) from emp_atten_main where Location = '" + txt_locCode.getText() + "' and Month = '" + txt_month.getText() + "' and Year = '" + txt_Year.getText() + "'");
                ResultSet rs1 = null;
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

        jdate_from_date.setEnabled(true);

        lbl_photo.setIcon(null);
        txt_empid.setText("");

        txt_search.setText("");

        //txt_locCode.setText("");
        txt_def_loc_name.setText("");
        lbl_total_shifts.setText("00.00");
        lbl_shifts_left.setText("00.00");

        DefaultTableModel dtm = (DefaultTableModel) table_day_carder.getModel();
        dtm.setRowCount(0);

        DefaultTableModel dtm1 = (DefaultTableModel) table_night_carder.getModel();
        dtm1.setRowCount(0);

    }

//    private void previous_atten_checker() {
////in DB
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String date = sdf.format(jDateChooser1.getDate());
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from emp_atten_main where EPFno='" + txt_empid.getText() + "' and Date='" + date + "'");
//            
//            while (rs.next()) {
//                
//                int dayshift = Integer.parseInt(rs.getString("DayShift"));
//                int nightshift = Integer.parseInt(rs.getString("NightShift"));
//                int halfshift = Integer.parseInt(rs.getString("HalfDayShift"));
//                
//                if (dayshift > 0) {
//                    cb_day.setSelected(false);
//                    // cb_day.setEnabled(false);
//
//                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Day Shift is already Entered to Selected Date.  ");
//                    
//                }
//                
//                if (nightshift > 0) {
//                    cb_night.setSelected(false);
//                    //cb_night.setEnabled(false);
//                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Night Shift is already Entered to Selected Date.  ");
//                }
//                
//                if (halfshift > 0) {
//                    cb_halfLeave.setSelected(false);
//                    //cb_halfLeave.setEnabled(false);
//                    JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Half Day Shift is already Entered to Selected Date.  ");
//                }
//                
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private void table_attn_check() {
//        //in Table
//        int row_count = tbl_atten.getRowCount();
//        System.out.println(row_count);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        if (row_count > 0) {
//            
//            String New_Date = sdf.format(jDateChooser1.getDate());
//            System.out.println(New_Date);
//            for (int row = 0; row < row_count; row++) {
//                
//                String Date = tbl_atten.getValueAt(row, 0).toString();
//                String EPFno = tbl_atten.getValueAt(row, 1).toString();
//                String Day = tbl_atten.getValueAt(row, 2).toString();
//                String Night = tbl_atten.getValueAt(row, 3).toString();
//                String Half = tbl_atten.getValueAt(row, 4).toString();
//                
//                String New_EPF = txt_empid.getText();
//                
//                System.out.println(Date);
//                System.out.println(Day);
//                System.out.println(Night);
//                
//                if (Date.equals(New_Date) && EPFno.equals(New_EPF)) {
//                    
//                    if (cb_day.isSelected() && Day.equals("1")) {
//                        cb_day.setSelected(false);
//                        JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Day Shift is already Entered to Selected Date.  ");
//                    }
//                    
//                    if (cb_night.isSelected() && Night.equals("1")) {
//                        cb_night.setSelected(false);
//                        JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s Night Shift is already Entered to Selected Date.  ");
//                    }
//                    
//                    if (cb_halfLeave.isSelected() && Half.equals("1")) {
//                        cb_halfLeave.setSelected(false);
//                        JOptionPane.showMessageDialog(rootPane, txt_empid.getText() + "'s HalfDay Shift is already Entered to Selected Date.  ");
//                    }
//                    
//                } else {
//                    
//                }
//                
//            }
//        }
//        
//    }
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
    private javax.swing.JButton btn_RemoveRow;
    private javax.swing.JButton btn_RemoveRow1;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_extraShifts;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable2;
    private com.toedter.calendar.JDateChooser jdate_from_date;
    private com.toedter.calendar.JDateChooser jdate_to_date;
    private javax.swing.JLabel lbl_day_1;
    private javax.swing.JLabel lbl_day_2;
    private javax.swing.JLabel lbl_day_3;
    private javax.swing.JLabel lbl_day_4;
    private javax.swing.JLabel lbl_day_5;
    private javax.swing.JLabel lbl_day_6;
    private javax.swing.JLabel lbl_day_7;
    private javax.swing.JLabel lbl_max_shift_permonth;
    public static javax.swing.JLabel lbl_photo;
    private javax.swing.JLabel lbl_rowCount;
    private javax.swing.JLabel lbl_shifts_left;
    private javax.swing.JLabel lbl_total_shifts;
    private javax.swing.JLabel lbl_warn;
    private javax.swing.JTable table_day_carder;
    private javax.swing.JTable table_night_carder;
    private javax.swing.JTable tbl_atten;
    private javax.swing.JTextField txt_Year;
    private javax.swing.JTextField txt_def_loc;
    private javax.swing.JTextField txt_def_loc_name;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_month;
    private javax.swing.JTextField txt_ot;
    private javax.swing.JTextField txt_rank;
    public static javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_to_date;
    // End of variables declaration//GEN-END:variables
}
