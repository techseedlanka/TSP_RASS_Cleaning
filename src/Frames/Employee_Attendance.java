/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class Employee_Attendance extends javax.swing.JFrame {

    /**
     * Creates new form Employee_Attendance
     */
    public Employee_Attendance() {
        initComponents();

        //jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        get_Location_Details();
//        get_payTypes();
        employee_auto_completer();

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Date d = new Date();
        String date = d.toString();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        lbl_date.setText("" + ft.format(d));
        TitleBar();

//        //Add Combo box to Jtable Cell
//        TableColumn col = jTable1.getColumnModel().getColumn(3);
//        col.setCellEditor(new DefaultCellEditor(cmb_MachineAllow));
    }

    private void TitleBar() {
        this.setTitle("Employee Attendance Entry");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

//    private void get_payTypes() {
//        try {
//
//            String[] theSeven = {"Hand", "Bank", "Slip"};
//            JComboBox combo1 = new JComboBox(theSeven);
//
//            TableColumn col = jTable1.getColumnModel().getColumn(13);
//            col.setCellEditor(new DefaultCellEditor(combo1));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
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

    private void get_shiftRates() {

        try {
            Connection con = DbConnection.getconnection();
            PreparedStatement pst;
            ResultSet rs;

            int row_count = jTable1.getRowCount();

            String loc = txt_locCode.getText();
            lbl_emp_count.setText(Integer.toString(row_count));

            for (int row = 0; row < row_count; row++) {

                String rank = jTable1.getValueAt(row, 2).toString();
                String rankCat = jTable1.getValueAt(row, 22).toString();
                String empno = jTable1.getValueAt(row, 0).toString();
                String sql = "select * from salary_rates where LocCode='" + loc + "' and RankCode ='" + rank + "'";

                pst = con.prepareStatement(sql);

                rs = pst.executeQuery();

                while (rs.next()) {

                    String day = "0";
                    String night = "0";
                    String sunday = rs.getString("Sunday");//21
                    String poyaday = rs.getString("Poyaday");//20
                    String otrate = rs.getString("OTRate");//18
                    String extra = rs.getString("NightRate");//19

                    if (jCheckBox1.isSelected()) {
                        day = txt_NewDNRates.getText();
                        night = txt_NewDNRates.getText();
                    } else {
                        day = rs.getString("DayRate");
                        night = rs.getString("NightRate");
                    }

                    jTable1.setValueAt(day, row, 16);
                    jTable1.setValueAt(night, row, 17);
                    jTable1.setValueAt(otrate, row, 18);
                    jTable1.setValueAt(extra, row, 19);
                    jTable1.setValueAt(sunday, row, 21);
                    jTable1.setValueAt(poyaday, row, 20);

                }

                if (rankCat.equals("Supervisor")) {

                    PreparedStatement pst1 = con.prepareStatement("select * from employee_reg where EmployeeNo='" + empno + "'");
                    ResultSet rs1 = pst1.executeQuery();
                    while (rs1.next()) {

                        String gross = rs1.getString("GrossSalary");
                        String shifts = rs1.getString("ShiftRate");

                        if (gross == null) {
                        } else {

                            Double g = Double.parseDouble(gross);
                            Double s = Double.parseDouble(shifts);
                            Double rate = g / s;

                            jTable1.setValueAt(String.format("%.2f", rate), row, 16);
                            jTable1.setValueAt(String.format("%.2f", rate), row, 17);
                            jTable1.setValueAt(String.format("%.2f", s), row, 23);

                        }

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void get_shiftRates_OneEMP() {

        try {
            Connection con = DbConnection.getconnection();
            PreparedStatement pst;
            ResultSet rs;

            int row_count = jTable1.getRowCount();

            String loc = txt_locCode.getText();
            lbl_emp_count.setText(Integer.toString(row_count));
            int row = 0;
            // for (int row = 0; row < row_count; row++) {
            String rank = txt_EmployeeNo1.getText();
            String rankCat = jTable1.getValueAt(row, 22).toString();
            String empno = txt_EmployeeNo.getText();
            String sql = "select * from salary_rates where LocCode='" + loc + "' and RankCode ='" + rank + "'";

            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()) {

                String day = "0";
                String night = "0";
                String sunday = rs.getString("Sunday");//21
                String poyaday = rs.getString("Poyaday");//20
                String otrate = rs.getString("OTRate");//18
                String extra = rs.getString("NightRate");//19

                if (jCheckBox1.isSelected()) {
                    day = txt_NewDNRates.getText();
                    night = txt_NewDNRates.getText();
                } else {
                    day = rs.getString("DayRate");
                    night = rs.getString("NightRate");
                }

                jTable1.setValueAt(day, row, 16);
                jTable1.setValueAt(night, row, 17);
                jTable1.setValueAt(otrate, row, 18);
                jTable1.setValueAt(extra, row, 19);
                jTable1.setValueAt(sunday, row, 21);
                jTable1.setValueAt(poyaday, row, 20);

            }

            if (rankCat.equals("Supervisor")) {

                PreparedStatement pst1 = con.prepareStatement("select * from employee_reg where EmployeeNo='" + empno + "'");
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    String gross = rs1.getString("GrossSalary");
                    String shifts = rs1.getString("ShiftRate");

                    if (gross == null) {
                    } else {

                        Double g = Double.parseDouble(gross);
                        Double s = Double.parseDouble(shifts);
                        Double rate = g / s;

                        jTable1.setValueAt(String.format("%.2f", rate), row, 16);
                        jTable1.setValueAt(String.format("%.2f", rate), row, 17);
                        jTable1.setValueAt(String.format("%.2f", s), row, 23);

                    }

                }

            }
            //}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void employee_auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_empName);

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

    private void add_employee_to_table() {

        if (txt_EmployeeNo.getText().isEmpty() | txt_locCode.getText().isEmpty()) {
        } else {

            try {

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement("select * from employee_reg where EmployeeNo='" + txt_EmployeeNo.getText() + "' and IsResigned='0'");
                ResultSet rs = pst.executeQuery();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                //dtm.setRowCount(0);
                while (rs.next()) {

                    String emp = rs.getString("EmployeeNo");
                    PreparedStatement pst2 = con.prepareStatement("select *,COUNT(*) from emp_atten_main where EMPno='" + emp + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {
                        int count = rs2.getInt("Count(*)");

                        if (count > 0) {

                            JOptionPane.showMessageDialog(rootPane, "Selected Employee's Attendance details already entered.");
                            txt_EmployeeNo.setText("");
                            txt_empName.grabFocus();

                        } else {

                            int rows = jTable1.getRowCount();
                            jProgressBar1.setMaximum(rows);

                            if (rows > 0) {

                                for (int i = 0; rows > i; i++) {
                                    jProgressBar1.setValue(i + 1);
                                    String emp_col = jTable1.getValueAt(i, 0).toString();

                                    if (emp_col.equals(txt_EmployeeNo.getText())) {
                                        JOptionPane.showMessageDialog(rootPane, "Selected Employee already in the Table");
                                        txt_EmployeeNo.setText("");
                                        txt_empName.grabFocus();
                                        break;
                                    } else {
                                        Vector v = new Vector();
                                        v.add(rs.getString("EmployeeNo"));
                                        v.add(rs.getString("NameWithInitials"));
                                        v.add(rs.getString("Designation"));

                                        v.add("");//4
                                        v.add("");//5
                                        v.add("");
                                        v.add("");
                                        v.add("");
                                        v.add("");
                                        v.add("");//10
                                        v.add("");
                                        v.add("");
                                        v.add("");
                                        v.add("");
                                        v.add("");//15
                                        v.add("");
                                        v.add("");
                                        v.add("");
                                        v.add("");
                                        v.add("");//20
                                        v.add("");
                                        v.add("");
                                        v.add(rs.getString("RankCategory"));

                                        dtm.addRow(v);
                                        get_shiftRates();

                                    }

                                }

                            } else {
                                //nothing
                            }

//                            Vector v = new Vector();
//                            v.add(rs.getString("EmployeeNo"));
//                            v.add(rs.getString("NameWithInitials"));
//                            v.add(rs.getString("Designation"));
//
//                            v.add("");//4
//                            v.add("");//5
//                            v.add("");
//                            v.add("");
//                            v.add("");
//                            v.add("");
//                            v.add("");//10
//                            v.add("");
//                            v.add("");
//                            v.add("");
//                            v.add("");
//                            v.add("");//15
//                            v.add("");
//                            v.add("");
//                            v.add("");
//                            v.add("");
//                            v.add("");//20
//                            v.add("");
//                            v.add("");
//                            v.add(rs.getString("RankCategory"));
//
//                            dtm.addRow(v);
//                            get_shiftRates();
                        }

                    }

                }

                //get_shiftRates();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void add_employee_to_table_NEW() {

        if (txt_EmployeeNo.getText().isEmpty() | txt_locCode.getText().isEmpty()) {
        } else {

            int rows = jTable1.getRowCount();
            jProgressBar1.setMaximum(rows);

            if (rows > 0) {

                for (int i = 0; rows > i; i++) {
                    jProgressBar1.setValue(i + 1);
                    String emp_col = jTable1.getValueAt(i, 0).toString();

                    if (emp_col.equals(txt_EmployeeNo.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Selected Employee already in the Table");
                        txt_EmployeeNo.setText("");
                        txt_empName.setText("");
                        txt_empName.grabFocus();

                    } else {

                    }
                }
            }

            try {

                Connection con = DbConnection.getconnection();
                String emp = txt_EmployeeNo.getText();
                PreparedStatement pst2 = con.prepareStatement("select *,COUNT(*) from emp_atten_main where EMPno='" + emp + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    int count = rs2.getInt("Count(*)");

                    if (count > 0) {

                        JOptionPane.showMessageDialog(rootPane, "Selected Employee's Attendance details already entered.");
                        txt_EmployeeNo.setText("");
                        txt_empName.grabFocus();

                    } else {

                        PreparedStatement pst = con.prepareStatement("select * from employee_reg where EmployeeNo='" + txt_EmployeeNo.getText() + "' and IsResigned='0'");
                        ResultSet rs = pst.executeQuery();
                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                        //dtm.setRowCount(0);
                        while (rs.next()) {

                            Vector v = new Vector();
                            v.add(rs.getString("EmployeeNo"));
                            v.add(rs.getString("NameWithInitials"));
                            v.add(rs.getString("Designation"));

                            v.add("");//4
                            v.add("");//5
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");//10
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");//15
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");//20
                            v.add("");
                            v.add("");
                            v.add(rs.getString("RankCategory"));

                            dtm.insertRow(0, v);

                        }
                        get_shiftRates_OneEMP();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            txt_empName.setText("");
            txt_EmployeeNo.setText("");
            txt_EmployeeNo1.setText("");

            jTable1.setCellSelectionEnabled(true);
            jTable1.changeSelection(0, 3, false, false);
            jTable1.editCellAt(0, 3);
            jTable1.getEditorComponent().requestFocusInWindow();

        }

    }

    private void total_duty_cal() {
        int row_count = jTable1.getRowCount();

        if (row_count <= 0) {

        } else {

            int row = jTable1.getSelectedRow();
            String TotalDuty = "0.00";

            Double day = 0.00;
            Double night = 0.00;
            Double extra = 0.00;
            Double tot = 0.00;
            Double max = 0.00;
            if (jTable1.getValueAt(row, 3).toString().isEmpty()) {

            } else {
                day = Double.parseDouble(jTable1.getValueAt(row, 3).toString());
            }
            if (jTable1.getValueAt(row, 4).toString().isEmpty()) {

            } else {
                night = Double.parseDouble(jTable1.getValueAt(row, 4).toString());
            }
            if (jTable1.getValueAt(row, 5).toString().isEmpty()) {

            } else {
                extra = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
            }

            Object Smax = jTable1.getValueAt(row, 23);
            if (Smax != null) {
                max = Double.parseDouble(Smax.toString());
                if ((day + night) > max) {
                    JOptionPane.showMessageDialog(rootPane, "<html> This Emp's Allowed max duties are " + max + "<br> Please enter additional duties in to the 'Extra Duty' column</html>");
                }
            } else {
                Smax = "0";
            }

            tot = day + night + extra;

            if (tot > 62) {
                JOptionPane.showMessageDialog(rootPane, "<html> Maximum Allowed Duties Exceeding. Please re-Check the Entered Duty Totals <br> (Day Duty + Night Duty + Extra Duty) Should be less than 62 </html>");
                jTable1.setValueAt("0", row, 6);
            } else {
                TotalDuty = String.format("%.2f", (tot));
                jTable1.setValueAt(TotalDuty, row, 6);
            }

//            }
//            }
        }
    }

    private void delete() {

        try {
            Connection con = DbConnection.getconnection();

            String sql_del = "delete from emp_atten_main where Location='" + txt_locCode.getText() + "' AND Month='" + cmb_month.getSelectedItem().toString() + "' AND Year='" + cmb_year.getSelectedItem().toString() + "' ";
            PreparedStatement pst = con.prepareStatement(sql_del);
            pst.execute();

            int row_count = jTable1.getRowCount();

            if (row_count <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Table row Count = 0 (zero)");

            } else {

                for (int row = 0; row < row_count; row++) {
                    String emp = jTable1.getValueAt(row, 0).toString();

                    String sql_del2 = "delete from salary_manual_earnings where EMPno='" + emp + "' AND Month='" + cmb_month.getSelectedItem().toString() + "' AND Year='" + cmb_year.getSelectedItem().toString() + "' ";
                    PreparedStatement pst2 = con.prepareStatement(sql_del2);
                    pst2.execute();

                    String sql_del3 = "delete from salary_manual_deductions where EPFno='" + emp + "' AND Month='" + cmb_month.getSelectedItem().toString() + "' AND Year='" + cmb_year.getSelectedItem().toString() + "' ";
                    PreparedStatement pst3 = con.prepareStatement(sql_del3);
                    pst3.execute();
                }
            }

        } catch (Exception e) {
        }

    }

    private void save() {
       
        try {
            Connection con = DbConnection.getconnection();

            String sql = "insert into emp_atten_main (EMPno,Rank,Location,Date,DayShift,NightShift,OTHours,DayRate,NightRate,OTRate,Month,Year,Status,SalaryStatus,Company,ExtraShift,ExtraShiftRate)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            String sql_deduc = "insert into salary_manual_deductions (EPFno,Meal,Rental,Month,Year)values (?,?,?,?,?)";
            PreparedStatement pst_deduc = con.prepareStatement(sql_deduc);

            String sql_allow = "insert into salary_manual_earnings (EMPno,SunDays,PerDayAmt_Sunday,PerDayAmt_Poyaday,Poyadays,MachineAllow,AttenAllow,SpecialAllow,OtherAllow,Month,Year)values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst_allow = con.prepareStatement(sql_allow);

            String loc = txt_locCode.getText();
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            String date = lbl_date.getText();
            String com = lbl_com.getText();

            int row_count = jTable1.getRowCount();

            if (row_count <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Table row Count = 0 (zero)");

            } else {

                for (int row = 0; row < row_count; row++) {

                    if (jTable1.getValueAt(row, 6) == null | jTable1.getValueAt(row, 6).equals("0") | jTable1.getValueAt(row, 6).toString().isEmpty()) {
                        //JOptionPane.showMessageDialog(rootPane, "<html>Please re-check the 'Total Duty' column. <br> 'Total Duty' Cannot be empty or 0(zero) </html>");
                        lbl_stat.setText("Please re-check the 'Total Duty' column.  'Total Duty' Cannot be empty or 0(zero)");
                        break;
//                        try {
//                               System.exit(1);
//                        } catch (Exception e) {
//                        }
                    } else {
                        System.out.println(jTable1.getValueAt(row, 6));
                        String empno = jTable1.getValueAt(row, 0).toString();
                        String rank = jTable1.getValueAt(row, 2).toString();
                        String day = jTable1.getValueAt(row, 3).toString();
                        if (day == null | day.isEmpty() | day.equals("")) {
                            day = "0.00";
                        }
                        String night = jTable1.getValueAt(row, 4).toString();
                        if (night == null | night.isEmpty() | night.equals("")) {
                            night = "0.00";
                        }
                        String extra = jTable1.getValueAt(row, 5).toString();
                        if (extra == null | extra.isEmpty() | extra.equals("")) {
                            extra = "0.00";
                        }
                        String ot = jTable1.getValueAt(row, 7).toString();
                        if (ot == null | ot.isEmpty() | ot.equals("")) {
                            ot = "0.00";
                        }
                        String dayRate = jTable1.getValueAt(row, 16).toString();
                        if (dayRate == null | dayRate.isEmpty() | dayRate.equals("")) {
                            dayRate = "0.00";
                        }
                        String nightRate = jTable1.getValueAt(row, 17).toString();
                        if (nightRate == null | nightRate.isEmpty() | nightRate.equals("")) {
                            nightRate = "0.00";
                        }
                        String otRate = jTable1.getValueAt(row, 18).toString();
                        if (otRate == null | otRate.isEmpty() | otRate.equals("")) {
                            otRate = "0.00";
                        }
                        String extraRate = jTable1.getValueAt(row, 19).toString();
                        if (extraRate == null | extraRate.isEmpty() | extraRate.equals("")) {
                            extraRate = "0.00";
                        }

                        pst.setString(1, empno);
                        pst.setString(2, rank);
                        pst.setString(3, loc);
                        pst.setString(4, date);
                        pst.setString(5, day);
                        pst.setString(6, night);
                        pst.setString(7, ot);
                        pst.setString(8, dayRate);
                        pst.setString(9, nightRate);
                        pst.setString(10, otRate);
                        pst.setString(11, month);
                        pst.setString(12, year);
                        pst.setString(13, "pending");
                        pst.setString(14, "pending");
                        pst.setString(15, com);
                        pst.setString(16, extra);
                        pst.setString(17, extraRate);
                        pst.addBatch();

                        String ma = jTable1.getValueAt(row, 10).toString();
                        if (ma == null | ma.isEmpty() | ma.equals("")) {
                            ma = "0.00";
                        }
                        String aa = jTable1.getValueAt(row, 11).toString();
                        if (aa == null | aa.isEmpty() | aa.equals("")) {
                            aa = "0.00";
                        }
                        String sa = jTable1.getValueAt(row, 12).toString();
                        if (sa == null | sa.isEmpty() | sa.equals("")) {
                            sa = "0.00";
                        }
                        String oa = jTable1.getValueAt(row, 13).toString();
                        if (oa == null | oa.isEmpty() | oa.equals("")) {
                            oa = "0.00";
                        }
                        String sunday = jTable1.getValueAt(row, 8).toString();
                        if (sunday == null | sunday.isEmpty() | sunday.equals("")) {
                            sunday = "0.00";
                        }
                        String poya = jTable1.getValueAt(row, 9).toString();
                        if (poya == null | poya.isEmpty() | poya.equals("")) {
                            poya = "0.00";
                        }
                        String sundayRate = jTable1.getValueAt(row, 20).toString();
                        if (sundayRate == null | sundayRate.isEmpty() | sundayRate.equals("")) {
                            sundayRate = "0.00";
                        }
                        String poyaRate = jTable1.getValueAt(row, 21).toString();
                        if (poyaRate == null | poyaRate.isEmpty() | poyaRate.equals("")) {
                            poyaRate = "0.00";
                        }

                        pst_allow.setString(1, empno);
                        pst_allow.setString(2, sunday);
                        pst_allow.setString(3, sundayRate);
                        pst_allow.setString(4, poyaRate);
                        pst_allow.setString(5, poya);
                        pst_allow.setString(6, ma);
                        pst_allow.setString(7, aa);
                        pst_allow.setString(8, sa);
                        pst_allow.setString(9, oa);
                        pst_allow.setString(10, month);
                        pst_allow.setString(11, year);

                        pst_allow.addBatch();

                        String meal = jTable1.getValueAt(row, 14).toString();
                        if (meal == null | meal.isEmpty() | meal.equals("")) {
                            meal = "0.00";
                        }
                        String rent = jTable1.getValueAt(row, 15).toString();
                        if (rent == null | rent.isEmpty() | rent.equals("")) {
                            rent = "0.00";
                        }

                        pst_deduc.setString(1, empno);
                        pst_deduc.setString(2, meal);
                        pst_deduc.setString(3, rent);
                        pst_deduc.setString(4, month);
                        pst_deduc.setString(5, year);
                        pst_deduc.addBatch();
                    }
                }

                lbl_stat.setText("Data Saving...... Please Wait for the Confirmation");
                lbl_stat.setForeground(Color.red);

                pst.executeBatch();

                pst_allow.executeBatch();
                pst_deduc.executeBatch();

                lbl_stat.setText("Data Saved Successfully !");
                lbl_stat.setForeground(Color.green);
                JOptionPane.showMessageDialog(rootPane, "Data Saved Successfully !");
                clear();

            }
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

        jSeparator1 = new javax.swing.JSeparator();
        lbl_stat = new javax.swing.JLabel();
        lbl_name = new javax.swing.JLabel();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        txt_empName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_EmployeeNo = new javax.swing.JTextField();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        btn_ADD = new javax.swing.JButton();
        btn_ADD1 = new javax.swing.JButton();
        btn_save1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btn_RemoveRow = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_date = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_emp_count = new javax.swing.JLabel();
        lbl_com = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txt_EmployeeNo1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        txt_NewDNRates = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 1270, 10));

        lbl_stat.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        getContentPane().add(lbl_stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 480, 30));

        lbl_name.setBackground(new java.awt.Color(204, 204, 204));
        lbl_name.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_name.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_name.setOpaque(true);
        getContentPane().add(lbl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 350, 30));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 380, -1));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 90, 23));

        txt_empName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_empName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_empNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_empNameFocusLost(evt);
            }
        });
        txt_empName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_empNameMouseClicked(evt);
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_empNameKeyTyped(evt);
            }
        });
        getContentPane().add(txt_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 210, 25));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Employee :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        txt_EmployeeNo.setEditable(false);
        txt_EmployeeNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_EmployeeNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EmployeeNoKeyPressed(evt);
            }
        });
        getContentPane().add(txt_EmployeeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 60, 25));

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
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021", "2022", "2023" }));
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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText("Salary Month / Year  :-");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        btn_ADD.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_ADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_ADD.setText("Load All Employees");
        btn_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 50, 40));

        btn_ADD1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_ADD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        btn_ADD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADD1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ADD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 60, -1));

        btn_save1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save1.setText("Save");
        btn_save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 140, 50));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1270, 10));

        btn_RemoveRow.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        btn_RemoveRow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        btn_RemoveRow.setText("Remove Row");
        btn_RemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveRowActionPerformed(evt);
            }
        });
        getContentPane().add(btn_RemoveRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 550, 150, 50));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton4.setText("Re-Fresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 550, 150, 50));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP. No.", "Employee Name", "Rank", "Day Duty", "Night Duty", "Extra Duty", "Total Duty", "OT Hours", "Sun:", "Poya", "M/A", "Attn: Allow", "Special Allow:", "Other Allow:", "Meal", "Rental", "Day Rate", "Night Rate", "OT Rate", "Ex. Duty Rate", "Sun:Rate", "PoyaRate", "Rank Cat", "AllowedMaxShifts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName(""); // NOI18N
        jTable1.setNextFocusableComponent(txt_empName);
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(220);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(13).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(14).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(15).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(16).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(17).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(18).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(19).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(20).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(21).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(22).setResizable(false);
            jTable1.getColumnModel().getColumn(22).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(23).setResizable(false);
            jTable1.getColumnModel().getColumn(23).setPreferredWidth(50);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 1250, 400));

        lbl_date.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_date.setText("date");
        lbl_date.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 20, 100, 20));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setText("Pay Location:-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, 40));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Emp. Count :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, -1, 30));

        lbl_emp_count.setBackground(new java.awt.Color(204, 204, 204));
        lbl_emp_count.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_emp_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_emp_count.setText("0");
        lbl_emp_count.setOpaque(true);
        getContentPane().add(lbl_emp_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 40, 30));

        lbl_com.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        lbl_com.setText("company");
        getContentPane().add(lbl_com, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 40, 90, 20));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel2.setText("Employee Attendence Entry");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 320, -1));

        txt_EmployeeNo1.setEditable(false);
        txt_EmployeeNo1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_EmployeeNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EmployeeNo1KeyPressed(evt);
            }
        });
        getContentPane().add(txt_EmployeeNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 50, 25));

        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox1.setText("Add  New D/N  Rates :-");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        txt_NewDNRates.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_NewDNRates.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_NewDNRatesKeyPressed(evt);
            }
        });
        getContentPane().add(txt_NewDNRates, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 90, 25));

        jButton1.setText("Get Employees");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 120, 30));

        jButton2.setText("Set Rates");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, 120, 30));

        jButton3.setText("Delete Saved Data");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 560, 160, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
            while (rs.next()) {

                String code = rs.getString("LocCode");
                String name = rs.getString("LocName");

                cmb_defLocation.setSelectedItem(name);
                txt_locCode.setText(code);

                String com = "";
                if (code.startsWith("E")) {
                    com = "Express";
                } else {
                    com = "Target";
                }

                lbl_com.setText(com);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:
//
//        get_month_and_year();
//        get_carder_and_enterd_shifts_total();
    }//GEN-LAST:event_txt_locCodeFocusGained

    private void txt_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusLost

    private void txt_empNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empNameFocusGained
//        if (txt_month.getText().isEmpty() | txt_locCode.getText().equals("** Please Select a Location ")) {
//
//            JOptionPane.showMessageDialog(rootPane, "Date & Location Can not be Empty...");
//
//            txt_locCode.grabFocus();
//
//        } else {
//
//            int m = jDateChooser1.getDate().getMonth();
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
//        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameFocusGained

    private void txt_empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_empNameActionPerformed
        txt_EmployeeNo.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameActionPerformed

    private void txt_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + txt_empName.getText() + "' OR NameWithInitials='" + txt_empName.getText() + "'   ");
                while (rs.next()) {

                    String code = rs.getString("EmployeeNo");

                    String name = rs.getString("NameWithInitials");
                    String rank = rs.getString("Designation");

                    txt_EmployeeNo.setText(code);
                    txt_empName.setText(name);
                    txt_EmployeeNo1.setText(rank);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            Thread hilo = new Thread(new Runnable() {

                @Override
                public void run() {
                    add_employee_to_table_NEW();
                }
            });
            hilo.start();

        }

    }//GEN-LAST:event_txt_empNameKeyPressed

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible
//        get_SAVED_EARN_DEDUC_DETAILS();        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
//        get_SAVED_EARN_DEDUC_DETAILS();
    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void btn_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDActionPerformed
        if (txt_locCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please select a Location");
        } else {

            try {

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement("select * from employee_reg where DefLocation='" + txt_locCode.getText() + "'");
                ResultSet rs = pst.executeQuery();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                while (rs.next()) {

                    String emp = rs.getString("EmployeeNo");
                    PreparedStatement pst2 = con.prepareStatement("select *,COUNT(*) from emp_atten_main where EMPno='" + emp + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {
                        int count = rs2.getInt("Count(*)");

                        if (count > 0) {

                        } else {
                            Vector v = new Vector();
                            v.add(rs.getString("EmployeeNo"));
                            v.add(rs.getString("NameWithInitials"));
                            v.add(rs.getString("Designation"));

                            v.add("");//4
                            v.add("");//5
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");//10
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");//15
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");
                            v.add("");//20
                            v.add("");
                            v.add("");
                            v.add(rs.getString("RankCategory"));

                            dtm.addRow(v);
                        }

                    }

                }
                cmb_defLocation.setEnabled(false);
                btn_ADD.setEnabled(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
            get_shiftRates();
            lbl_name.setText("");

        }


    }//GEN-LAST:event_btn_ADDActionPerformed

    private void btn_ADD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADD1ActionPerformed
        if (txt_locCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please select a Location");
        } else {
            add_employee_to_table_NEW();
        }


    }//GEN-LAST:event_btn_ADD1ActionPerformed

    private void btn_save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save1ActionPerformed
 
        if (txt_locCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please select a Location");
        } else {
            int i = JOptionPane.showConfirmDialog(rootPane, "Do you want to Save?", "SAVE", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {

                Thread hilo = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        save();
                    }
                });
                hilo.start();

            } else {
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_save1ActionPerformed

    private void btn_RemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveRowActionPerformed

        int nrow = jTable1.getSelectedRowCount();
        System.out.println(nrow);

        if (nrow < 0) {
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

        int row_count = jTable1.getRowCount();
        lbl_emp_count.setText(Integer.toString(row_count));

    }//GEN-LAST:event_btn_RemoveRowActionPerformed

    private void clear() {

        cmb_defLocation.setEnabled(true);
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        lbl_com.setText("");
        txt_locCode.setText("");
        txt_EmployeeNo.setText("");
        txt_empName.setText("");
        int row_count = jTable1.getRowCount();
        lbl_emp_count.setText(Integer.toString(row_count));
        lbl_stat.setText("");
        btn_ADD.setEnabled(true);

    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int i = JOptionPane.showConfirmDialog(rootPane, "Do you want to Clear All?", "CLEAR", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            cmb_defLocation.setEnabled(true);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            lbl_com.setText("");
            txt_locCode.setText("");
            txt_EmployeeNo.setText("");
            txt_empName.setText("");
            int row_count = jTable1.getRowCount();
            lbl_emp_count.setText(Integer.toString(row_count));
            lbl_stat.setText("");
            btn_ADD.setEnabled(true);
            txt_NewDNRates.setText("");
            jCheckBox1.setSelected(false);
        } else {
            lbl_stat.setText("");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_EmployeeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmployeeNoKeyPressed
// if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//  add_employee_to_table();
// }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoKeyPressed

    private void txt_empNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameKeyTyped

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange


    }//GEN-LAST:event_jTable1PropertyChange

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped

    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isAltDown()) {
            txt_empName.grabFocus();
            txt_empName.setText("");
        }
        total_duty_cal();

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN | evt.getKeyCode() == KeyEvent.VK_UP) {

            int i = jTable1.getSelectedRow();
            int rows = jTable1.getRowCount();

            if (i >= 0) {

                String name = jTable1.getValueAt(i, 1).toString();
                String code = jTable1.getValueAt(i, 0).toString();
                lbl_name.setText(code + "   " + name);

                if (i == rows) {
                    evt.consume();
                } else {

                }

            }

        }
//        total_duty_cal();

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        int i = jTable1.getSelectedRow();
        int rows = jTable1.getRowCount();

        if (i >= 0) {

            String name = jTable1.getValueAt(i, 1).toString();
            String code = jTable1.getValueAt(i, 0).toString();
            lbl_name.setText(code + "   " + name);

            if (i == rows) {
                evt.consume();
            } else {

            }

        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void txt_empNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_empNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameFocusLost

    private void txt_empNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empNameKeyReleased

    }//GEN-LAST:event_txt_empNameKeyReleased

    private void txt_empNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_empNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_empNameMouseClicked

    private void txt_EmployeeNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmployeeNo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNo1KeyPressed

    private void txt_NewDNRatesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NewDNRatesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NewDNRatesKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {

        } else {
            txt_NewDNRates.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            Connection con = DbConnection.getconnection();
            String sql = "select * from emp_atten_main where Location='" + txt_locCode.getText() + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("EMPno"));
                v.add("");//name
                v.add(rs.getString("Rank"));//rank
                v.add(rs.getString("DayShift"));//dayshift
                v.add(rs.getString("NightShift"));//nightshift
                v.add(rs.getString("ExtraShift"));//extra shift
                v.add("");//total duty
                v.add(rs.getString("OTHours"));//ot hrs
                v.add("");//sunday
                v.add("");//poyaday
                v.add("");//MA
                v.add("");//AA
                v.add("");//SA
                v.add("");//OA
                v.add("");//MEal
                v.add("");//Rental
                v.add(rs.getString("DayRate"));//day rate
                v.add(rs.getString("NightRate"));//night rate
                v.add(rs.getString("OTRate"));// ot rate
                v.add(rs.getString("ExtraShiftRate"));// extra rate
                v.add("");//sunday Rate
                v.add("");//poya rate
                v.add("");//rank cat
                v.add("62");//max days allowed (only for FS & MS)

                dtm.addRow(v);

            }

            int row = jTable1.getRowCount();

            for (int i = 0; i < row; i++) {

                String emp = jTable1.getValueAt(i, 0).toString();
                //                String loc = jTable1.getValueAt(i, 12).toString();

                String sql1 = "select * from employee_reg where EmployeeNo='" + emp + "' ";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    jTable1.setValueAt(rs1.getString("NameWithInitials"), i, 1);

                    if (rs1.getString("ShiftRate").equals("0.00") | rs1.getString("ShiftRate").equals("") | rs1.getString("ShiftRate") == null) {
                    } else {
                        jTable1.setValueAt(rs1.getString("ShiftRate"), i, 23);
                    }

                }

                String sql2 = "select * from salary_manual_earnings where EMPno='" + emp + "' ";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {

                    jTable1.setValueAt(rs2.getString("SunDays"), i, 8);//8 sun
                    jTable1.setValueAt(rs2.getString("Poyadays"), i, 9);//9 poya
                    jTable1.setValueAt(rs2.getString("MachineAllow"), i, 10);//10 ma
                    jTable1.setValueAt(rs2.getString("AttenAllow"), i, 11);//11 aa
                    jTable1.setValueAt(rs2.getString("SpecialAllow"), i, 12);//12 sa
                    jTable1.setValueAt(rs2.getString("OtherAllow"), i, 13);//13 oa

                }

                String sql3 = "select * from salary_manual_deductions where EPFno='" + emp + "' ";
                PreparedStatement pst3 = con.prepareStatement(sql3);
                ResultSet rs3 = pst3.executeQuery();
                while (rs2.next()) {

                    jTable1.setValueAt(rs3.getString("Meal"), i, 14);//14 meal
                    jTable1.setValueAt(rs3.getString("Rental"), i, 15);//15 rent

                }
            }
            int row_count = jTable1.getRowCount();
            lbl_emp_count.setText(Integer.toString(row_count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {

            Connection con = DbConnection.getconnection();

            int row = jTable1.getRowCount();

            for (int i = 0; i < row; i++) {

                String rank = jTable1.getValueAt(i, 2).toString();
                // String loc = jTable1.getValueAt(i, 12).toString();

                if (rank.equals("FS") | rank.equals("MS")) {

                    String sql1 = "select * from salary_rates where LocCode='" + txt_locCode.getText() + "' and RankCode='" + rank + "'";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery();
                    while (rs1.next()) {

                        //                        jTable1.setValueAt(rs1.getString("DayRate"), i, 6);
                        //                        jTable1.setValueAt(rs1.getString("NightRate"), i, 7);
                        jTable1.setValueAt(rs1.getString("OTRate"), i, 18);
                        jTable1.setValueAt(rs1.getString("NightRate"), i, 19);//extra
                        jTable1.setValueAt(rs1.getString("Sunday"), i, 20);//sunda
                        jTable1.setValueAt(rs1.getString("PoyaDay"), i, 21);//poya
                    }
                } else {
                    String sql1 = "select * from salary_rates where LocCode='" + txt_locCode.getText() + "' and RankCode='" + rank + "'";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery();
                    while (rs1.next()) {

                        jTable1.setValueAt(rs1.getString("DayRate"), i, 16);
                        jTable1.setValueAt(rs1.getString("NightRate"), i, 17);
                        jTable1.setValueAt(rs1.getString("OTRate"), i, 18);
                        jTable1.setValueAt(rs1.getString("NightRate"), i, 19);//extra
                        jTable1.setValueAt(rs1.getString("Sunday"), i, 20);//sunda
                        jTable1.setValueAt(rs1.getString("PoyaDay"), i, 21);//poya

                    }
                }

            }

            //  total_duty_cal();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
  delete();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Employee_Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Attendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_Attendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ADD;
    private javax.swing.JButton btn_ADD1;
    private javax.swing.JButton btn_RemoveRow;
    private javax.swing.JButton btn_save1;
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_com;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_emp_count;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_stat;
    private javax.swing.JTextField txt_EmployeeNo;
    private javax.swing.JTextField txt_EmployeeNo1;
    private javax.swing.JTextField txt_NewDNRates;
    private javax.swing.JTextField txt_empName;
    private javax.swing.JTextField txt_locCode;
    // End of variables declaration//GEN-END:variables
}
