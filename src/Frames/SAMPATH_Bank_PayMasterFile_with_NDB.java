/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Sapu
 */
public class SAMPATH_Bank_PayMasterFile_with_NDB extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Advance
     */
    static ArrayList adv;

    public SAMPATH_Bank_PayMasterFile_with_NDB() {
        initComponents();

        adv = new ArrayList();
        seticon();
        get_Location_Details();
        get_Company_Details();
        jButton3.setVisible(false);
        txt_path.setVisible(false);
    }

    private void seticon() {
        this.setTitle("Bank File Generate");
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

    private void get_Company_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from company_reg  ");
            while (rs.next()) {

                Object name = rs.getString("ComName");
                //Object code = rs.getString("LocCode");

                //cmb_defLocation.addItem(code);
                cmb_Company.addItem(name);
            }

            AutoCompleteDecorator.decorate(cmb_Company);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(adv);
            String path = "Reports\\Emp_Monthly_Advance.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Advance_table() {
        if (txt_locCode.getText().isEmpty() && !jCheckBox1.isSelected()) {

            JOptionPane.showMessageDialog(rootPane, "Please Select a Location or Click All Locations to Process Bank Transfer File.");

        } else {

            lbl_emp_without_Bank.setText("0");
            String LocCode = "";
            String EmpNo = "";
            String EmpName = "";
            String AdvAmount = "";
            String PayType = "";
            String Rank = "";
            try {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                Connection con = DbConnection.getconnection();
                String sql = null;

//                if (jCheckBox1.isSelected()) {
//                    sql = "SELECT * from salary_advance_1  WHERE    PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank'";
//
//                } else {
//                    sql = "SELECT * from salary_advance_1  WHERE    PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank' and Location='" + txt_locCode.getText() + "' ";
//
//                }
//                
                if (cmb_Company.getSelectedIndex() == 0) {
                    //employees of both companies

                    if (jCheckBox1.isSelected()) {
                        sql = "SELECT * from salary_advance_1  WHERE    PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank'";

                    } else {
                        sql = "SELECT * from salary_advance_1  WHERE    PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank' and Location='" + txt_locCode.getText() + "' ";

                    }

                } else {
                    String beginLetter = companyName.substring(0, 1);

                    if (jCheckBox1.isSelected()) {
                        sql = "SELECT * from salary_advance_1  WHERE  Location Like '" + beginLetter + "%' And  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank'";

                    } else {
                        sql = "SELECT * from salary_advance_1  WHERE  Location Like '" + beginLetter + "%' And  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank' and Location='" + txt_locCode.getText() + "' ";

                    }

                }

                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    LocCode = rs.getString("Location");
                    EmpNo = rs.getString("EPFno");
                    EmpName = rs.getString("Name");
                    AdvAmount = rs.getString("Amount");
                    PayType = rs.getString("Note");
                    Rank = rs.getString("Rank");

                    Vector v = new Vector();
                    v.add(EmpNo);
                    v.add(Rank);
                    v.add(EmpName);
                    v.add("");
                    v.add("");
                    v.add("");
                    v.add(Double.parseDouble(AdvAmount));
                    v.add("");
                    v.add("");
                    v.add("");
                    v.add(LocCode);
                    v.add(PayType);

                    dtm.addRow(v);

                }

                int nrow = jTable1.getModel().getRowCount();
                jProgressBar1.setMaximum(nrow);

                if (nrow == 0) {

                } else {
                    lbl_total.setText("0.00");

                    for (int i = 0; i < nrow; i++) {
                        jProgressBar1.setValue(i + 1);
                        lbl_lbl.setForeground(Color.BLACK);
                        lbl_lbl.setText("Collecting Data...");

                        String loc = jTable1.getModel().getValueAt(i, 10).toString();
                        String epf = jTable1.getModel().getValueAt(i, 0).toString();

                        String sql2 = "SELECT * from location_reg  WHERE  LocCode='" + loc + "' ";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                        ResultSet rs2 = pst2.executeQuery();
                        while (rs2.next()) {

                            String loName = rs2.getString("LocName");
                            jTable1.getModel().setValueAt(loName, i, 9);

                        }

                        String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
                        PreparedStatement pst3 = con.prepareStatement(sql3);
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {

                            String bankCode = rs3.getString("Bank");
                            String branchCode = rs3.getString("Branch");
                            String account = rs3.getString("AccName");
                            System.out.println(epf + " bank code:" + bankCode);
                            String bankname = "";
                            if (bankCode == null | bankCode.equals("N/A") | bankCode.equals("") | account.equals("")) {

                                bankCode = "NA";
                                bankname = "NA";
                                account = "NA";

                            } else {
                                String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
                                PreparedStatement pst4 = con.prepareStatement(sql4);
                                ResultSet rs4 = pst4.executeQuery();
                                while (rs4.next()) {

                                    bankname = rs4.getString("BankName");
                                    if (bankname == null | bankname.equals("N/A")) {
                                        bankname = "NA";
                                        branchCode = "NA";
                                    } else {
                                        jTable1.getModel().setValueAt(bankCode, i, 4);
                                        jTable1.getModel().setValueAt(bankname, i, 7);
                                        jTable1.getModel().setValueAt(branchCode, i, 5);
                                        jTable1.getModel().setValueAt(account, i, 3);

                                    }

                                }
                            }

                            String sql5 = "SELECT * from bank_names_in_sampath_excel  WHERE  BankCode='" + bankCode + "' ";
                            PreparedStatement pst4 = con.prepareStatement(sql5);
                            ResultSet rs4 = pst4.executeQuery();
                            while (rs4.next()) {

                                String new_bank_name = rs4.getString("BankName");

                                if (new_bank_name == null | new_bank_name.isEmpty()) {
                                    jTable1.getModel().setValueAt(bankname, i, 11);

                                } else {
                                    jTable1.getModel().setValueAt(new_bank_name, i, 11);
                                }

                            }

                        }

                        if (loc.startsWith("T")) {
                            jTable1.getModel().setValueAt("T", i, 8);
                        } else {
                            jTable1.getModel().setValueAt("E", i, 8);
                        }

                        if (jTable1.getValueAt(i, 3).toString().equals("")) {
                            int emp_count = Integer.parseInt(lbl_emp_without_Bank.getText());
                            lbl_emp_without_Bank.setText(Integer.toString(emp_count + 1));
                        }

                        Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 6).toString());
                        Double x = Double.parseDouble(lbl_total.getText());
                        lbl_total.setText(String.format("%.2f", x += adv_amt));
                    }

                    Double d = Double.parseDouble(lbl_total.getText());
                    lbl_total.setText((String.format("RS. %,.2f", d)));

                    for (int i = 0; i < nrow; i++) {

                        if (jTable1.getValueAt(i, 7) == null) {
                            jTable1.getModel().setValueAt("NA", i, 7);

                        }

                    }
                }
                int i = dtm.getRowCount();
                lbl_emp_count.setText(Integer.toString(i));
                lbl_lbl.setText("Data Collected Succesfully...!");
                lbl_lbl.setForeground(Color.WHITE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void SALARY_table() {

        if (txt_locCode.getText().isEmpty() && !jCheckBox1.isSelected()) {

            JOptionPane.showMessageDialog(rootPane, "Please Select a Location or Click All Locations to Process Bank Transfer File.");

        } else {

            lbl_emp_without_Bank.setText("0");
            String LocCode = "";
            String EmpNo = "";
            String EmpName = "";
            String AdvAmount = "";
            String PayType = "";
            String Rank = "";
            try {

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                Connection con = DbConnection.getconnection();

                String sql = "SELECT * from salary_final_site_employees  WHERE Month='" + cmb_month.getSelectedItem().toString() + "' and   Year='" + cmb_year.getSelectedItem().toString() + "' and PayType='Bank' and SalaryType='FINAL' ORDER BY loc";

                if (cmb_Company.getSelectedIndex() == 0) {
                    //employees of both companies

                    if (jCheckBox1.isSelected()) {
                        sql = "SELECT * from salary_final_site_employees  WHERE Month='" + cmb_month.getSelectedItem().toString() + "' and   Year='" + cmb_year.getSelectedItem().toString() + "' and PayType='Bank' and SalaryType='FINAL' ORDER BY loc ";

                    } else {
                        sql = "SELECT * from salary_final_site_employees  WHERE Month='" + cmb_month.getSelectedItem().toString() + "' and   Year='" + cmb_year.getSelectedItem().toString() + "' and PayType='Bank' and SalaryType='FINAL' and loc='" + txt_locCode.getText() + "' ORDER BY loc";

                    }

                } else {
                    String beginLetter = companyName.substring(0, 1);

                    if (jCheckBox1.isSelected()) {
                        sql = "SELECT * from salary_final_site_employees  WHERE Loc Like '" + beginLetter + "%' And Month='" + cmb_month.getSelectedItem().toString() + "' and   Year='" + cmb_year.getSelectedItem().toString() + "' and PayType='Bank' and SalaryType='FINAL' ORDER BY loc ";

                    } else {
                        sql = "SELECT * from salary_final_site_employees  WHERE Loc Like '" + beginLetter + "%' And Month='" + cmb_month.getSelectedItem().toString() + "' and   Year='" + cmb_year.getSelectedItem().toString() + "' and PayType='Bank' and SalaryType='FINAL' and loc='" + txt_locCode.getText() + "' ORDER BY loc";

                    }

                }

                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    LocCode = rs.getString("Loc");
                    EmpNo = rs.getString("EMPno");
                    EmpName = rs.getString("Name");
                    AdvAmount = rs.getString("NetPay");
                    PayType = rs.getString("Status");
                    Rank = rs.getString("Rank");

                    Vector v = new Vector();
                    v.add(EmpNo);
                    v.add(Rank);
                    v.add(EmpName);
                    v.add("");
                    v.add("");
                    v.add("");
                    v.add(Double.parseDouble(AdvAmount));
                    v.add("");
                    v.add("");
                    v.add("");
                    v.add(LocCode);
                    v.add(PayType);

                    dtm.addRow(v);

                }

                int nrow = jTable1.getModel().getRowCount();
                jProgressBar1.setMaximum(nrow);

                if (nrow == 0) {

                } else {
                    lbl_total.setText("0.00");

                    for (int i = 0; i < nrow; i++) {
                        jProgressBar1.setValue(i + 1);
                        lbl_lbl.setForeground(Color.BLACK);
                        lbl_lbl.setText("Collecting Data...");

                        String loc = jTable1.getModel().getValueAt(i, 10).toString();
                        String epf = jTable1.getModel().getValueAt(i, 0).toString();

                        String sql2 = "SELECT * from location_reg  WHERE  LocCode='" + loc + "' ";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                        ResultSet rs2 = pst2.executeQuery();
                        while (rs2.next()) {

                            String loName = rs2.getString("LocName");
                            jTable1.getModel().setValueAt(loName, i, 9);

                        }

                        String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
                        PreparedStatement pst3 = con.prepareStatement(sql3);
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {

                            String bankCode = rs3.getString("Bank");
                            String branchCode = rs3.getString("Branch");
                            String account = rs3.getString("AccName");
                            System.out.println(epf + " bank code:" + bankCode);
                            String bankname = "";
                            if (bankCode == null | bankCode.equals("N/A") | bankCode.equals("") | account.equals("")) {

                                bankCode = "NA";
                                bankname = "NA";
                                account = "NA";

                            } else {
                                String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
                                PreparedStatement pst4 = con.prepareStatement(sql4);
                                ResultSet rs4 = pst4.executeQuery();
                                while (rs4.next()) {

                                    bankname = rs4.getString("BankName");
                                    if (bankname == null | bankname.equals("N/A")) {
                                        bankname = "NA";
                                        branchCode = "NA";
                                    } else {
                                        jTable1.getModel().setValueAt(bankCode, i, 4);
                                        jTable1.getModel().setValueAt(bankname, i, 7);
                                        jTable1.getModel().setValueAt(branchCode, i, 5);
                                        jTable1.getModel().setValueAt(account, i, 3);

                                    }

                                }
                            }

                            String sql5 = "SELECT * from bank_names_in_sampath_excel  WHERE  BankCode='" + bankCode + "' ";
                            PreparedStatement pst4 = con.prepareStatement(sql5);
                            ResultSet rs4 = pst4.executeQuery();
                            while (rs4.next()) {

                                String new_bank_name = rs4.getString("BankName");

                                if (new_bank_name == null | new_bank_name.isEmpty()) {
                                    jTable1.getModel().setValueAt(bankname, i, 11);

                                } else {
                                    jTable1.getModel().setValueAt(new_bank_name, i, 11);
                                }

                            }

                        }

                        if (loc.startsWith("T")) {
                            jTable1.getModel().setValueAt("T", i, 8);
                        } else {
                            jTable1.getModel().setValueAt("E", i, 8);
                        }

                        if (jTable1.getValueAt(i, 3).toString().equals("")) {
                            int emp_count = Integer.parseInt(lbl_emp_without_Bank.getText());
                            lbl_emp_without_Bank.setText(Integer.toString(emp_count + 1));
                        }

                        Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 6).toString());
                        Double x = Double.parseDouble(lbl_total.getText());
                        lbl_total.setText(String.format("%.2f", x += adv_amt));
                    }

                    Double d = Double.parseDouble(lbl_total.getText());
                    lbl_total.setText((String.format("RS. %,.2f", d)));

                    for (int i = 0; i < nrow; i++) {

                        if (jTable1.getValueAt(i, 7) == null) {
                            jTable1.getModel().setValueAt("NA", i, 7);

                        }

                    }
                }
                int i = dtm.getRowCount();
                lbl_emp_count.setText(Integer.toString(i));
                lbl_lbl.setText("Data Collected Succesfully...!");
                lbl_lbl.setForeground(Color.WHITE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void festival_table() {
        lbl_emp_without_Bank.setText("0");
        String LocCode = "";
        String EmpNo = "";
        String EmpName = "";
        String AdvAmount = "";
        String Unit = "";
        String Rank = "";

        String month = cmb_month.getSelectedItem().toString();
        String year = cmb_year.getSelectedItem().toString();
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            Connection con = DbConnection.getconnection();
            String sql = "SELECT * from salary_festival_deductions_summery  WHERE  PayStatus='PENDING' and PayType='Bank' and FestivYear='" + year + "' and FestivMonth='" + month + "' ORDER BY Unit,EMPno";

            if (cmb_Company.getSelectedIndex() == 0) {
                //employees of both companies

                if (jCheckBox1.isSelected()) {
                    sql = "SELECT * from salary_festival_deductions_summery  WHERE  PayStatus='PENDING' and PayType='Bank' and PayType='Bank' and FestivYear='" + year + "' and FestivMonth='" + month + "' ORDER BY Unit,EMPno ";

                } else {
                    sql = "SELECT * from salary_festival_deductions_summery  WHERE  PayStatus='PENDING' and PayType='Bank' and FestivYear='" + year + "' and FestivMonth='" + month + "' and Unit='" + txt_locCode.getText() + "' ORDER BY Unit,EMPno";

                }

            } else {
                String beginLetter = companyName.substring(0, 1);

                if (jCheckBox1.isSelected()) {
                    sql = "SELECT * from salary_festival_deductions_summery  WHERE Unit Like '" + beginLetter + "%' And  PayStatus='PENDING' and PayType='Bank' and PayType='Bank' and FestivYear='" + year + "' and FestivMonth='" + month + "' ORDER BY Unit,EMPno ";

                } else {
                    sql = "SELECT * from salary_festival_deductions_summery  WHERE Unit Like '" + beginLetter + "%' And  PayStatus='PENDING' and PayType='Bank' and Unit='" + txt_locCode.getText() + "' and FestivYear='" + year + "' and FestivMonth='" + month + "' ORDER BY Unit,EMPno";

                }

            }

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                // LocCode = rs.getString("Location");
                EmpNo = rs.getString("EMPno");
                // EmpName = rs.getString("Name");
                AdvAmount = rs.getString("LoanAmount");
                Unit = rs.getString("Unit");
                // PayType = rs.getString("Note");

                Vector v = new Vector();
                v.add(EmpNo);
                v.add("");
                v.add("");
                v.add("");
                v.add("");
                v.add("");
                v.add(Double.parseDouble(AdvAmount));
                v.add("");
                v.add("");
                v.add("");
                v.add(Unit);
                v.add("");

                dtm.addRow(v);

            }

            int nrow = jTable1.getModel().getRowCount();
            jProgressBar1.setMaximum(nrow);

            if (nrow == 0) {

            } else {
                lbl_total.setText("0.00");

                for (int i = 0; i < nrow; i++) {
                    jProgressBar1.setValue(i + 1);
                    lbl_lbl.setForeground(Color.BLACK);
                    lbl_lbl.setText("Collecting Data...");

                    String loc = "";
                    String epf = jTable1.getModel().getValueAt(i, 0).toString();

                    String sql1 = "SELECT * from employee_reg  WHERE  EmployeeNo='" + epf + "' ";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery();
                    while (rs1.next()) {

                        EmpName = rs1.getString("NameWithInitials");
                        loc = rs1.getString("DefLocation");
                        Rank = rs1.getString("Designation");
                        jTable1.getModel().setValueAt(EmpName, i, 2);
                        jTable1.getModel().setValueAt(Rank, i, 1);

                    }

                    String sql2 = "SELECT * from location_reg  WHERE  LocCode='" + loc + "' ";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        String loName = rs2.getString("LocName");
                        jTable1.getModel().setValueAt(loName, i, 9);

                    }

                    String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    ResultSet rs3 = pst3.executeQuery();
                    while (rs3.next()) {

                        String bankCode = rs3.getString("Bank");
                        String branchCode = rs3.getString("Branch");
                        String account = rs3.getString("AccName");
                        System.out.println(epf + " bank code:" + bankCode);
                        String bankname = "";
                        if (bankCode == null | bankCode.equals("N/A") | bankCode.equals("") | account.equals("")) {

                            bankCode = "NA";
                            bankname = "NA";
                            account = "NA";

                        } else {
                            String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
                            PreparedStatement pst4 = con.prepareStatement(sql4);
                            ResultSet rs4 = pst4.executeQuery();
                            while (rs4.next()) {

                                bankname = rs4.getString("BankName");
                                if (bankname == null | bankname.equals("N/A")) {
                                    bankname = "NA";
                                    branchCode = "NA";
                                } else {
                                    jTable1.getModel().setValueAt(bankCode, i, 4);
                                    jTable1.getModel().setValueAt(bankname, i, 7);
                                    jTable1.getModel().setValueAt(branchCode, i, 5);
                                    jTable1.getModel().setValueAt(account, i, 3);

                                }

                            }
                        }

                        String sql5 = "SELECT * from bank_names_in_sampath_excel  WHERE  BankCode='" + bankCode + "' ";
                        PreparedStatement pst4 = con.prepareStatement(sql5);
                        ResultSet rs4 = pst4.executeQuery();
                        while (rs4.next()) {

                            String new_bank_name = rs4.getString("BankName");

                            if (new_bank_name == null | new_bank_name.isEmpty()) {
                                jTable1.getModel().setValueAt(bankname, i, 11);

                            } else {
                                jTable1.getModel().setValueAt(new_bank_name, i, 11);
                            }

                        }

                    }

                    if (loc.startsWith("T")) {
                        jTable1.getModel().setValueAt("T", i, 8);
                    } else {
                        jTable1.getModel().setValueAt("E", i, 8);
                    }

                    if (jTable1.getValueAt(i, 3).toString().equals("")) {
                        // dtm.removeRow(i);
                        int emp_count = Integer.parseInt(lbl_emp_without_Bank.getText());
                        lbl_emp_without_Bank.setText(Integer.toString(emp_count + 1));
                    }

                    Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 6).toString());
                    Double x = Double.parseDouble(lbl_total.getText());
                    lbl_total.setText(String.format("%.2f", x += adv_amt));

//                    
                }

                Double d = Double.parseDouble(lbl_total.getText());
                lbl_total.setText((String.format("RS. %,.2f", d)));

                for (int i = 0; i < nrow; i++) {

                    if (jTable1.getValueAt(i, 7) == null) {
                        jTable1.getModel().setValueAt("NA", i, 7);

                    }

                }
            }
            int i = dtm.getRowCount();
            lbl_emp_count.setText(Integer.toString(i));
            lbl_lbl.setText("Data Collected Succesfully...!");
            lbl_lbl.setForeground(Color.WHITE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void export_to_excell() {
//
//        try {
//
//            // Create a Workbook
//            Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
//
//            /* CreationHelper helps us create instances of various things like DataFormat, 
//           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
//            CreationHelper createHelper = workbook.getCreationHelper();
//
//            // Create a Sheet
//            Sheet sheet = workbook.createSheet("Employee");
//
//            // Create a Font for styling header cells
//            Font headerFont = workbook.createFont();
//            headerFont.setBold(true);
//            headerFont.setFontHeightInPoints((short) 14);
//            headerFont.setColor(IndexedColors.RED.getIndex());
//
//            // Create a CellStyle with the font
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFont(headerFont);
//
//            // Create a Row
//            Row headerRow = sheet.createRow(0);
//
//            // Create cells
//            for (int i = 0; i < columns.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(columns[i]);
//                cell.setCellStyle(headerCellStyle);
//            }
//
//            // Create Cell Style for formatting Date
//            CellStyle dateCellStyle = workbook.createCellStyle();
//            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//
//            // Create Other rows and cells with employees data
//            int rowNum = 1;
//            for (Employee employee : employees) {
//                Row row = sheet.createRow(rowNum++);
//
//                row.createCell(0)
//                        .setCellValue(employee.getName());
//
//                row.createCell(1)
//                        .setCellValue(employee.getEmail());
//
//                Cell dateOfBirthCell = row.createCell(2);
//                dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//                dateOfBirthCell.setCellStyle(dateCellStyle);
//
//                row.createCell(3)
//                        .setCellValue(employee.getSalary());
//            }
//
//            // Resize all columns to fit the content size
//            for (int i = 0; i < columns.length; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            // Write the output to a file
//            FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
//            workbook.write(fileOut);
//            fileOut.close();
//
//            // Closing the workbook
//            workbook.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private void export_excel() {
//        try {
//            long start = System.currentTimeMillis();
//            // new WorkbookFactory();
//            Workbook wb = new XSSFWorkbook(); //Excell workbook
//            Sheet sheet = wb.createSheet(); //WorkSheet
//            Row row = sheet.createRow(1); //Row created at line 2
//            TableModel model = jTable1.getModel(); //Table model
//
//            int table_rowcount = jTable1.getRowCount();
//            jProgressBar1.setMaximum(table_rowcount);
//            Row headerRow = sheet.createRow(0); //Create row at line 0
//            for (int headings = 0; headings < model.getColumnCount(); headings++) { //For each column
//                headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
//            }
//
//            for (int rows = 0; rows < model.getRowCount(); rows++) { //For each table row
//                for (int cols = 0; cols < jTable1.getColumnCount(); cols++) { //For each table column
//                    row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
//                    if (cols == 3) {
//
//                        String x = model.getValueAt(rows, cols).toString();
//                        Double amount = Double.parseDouble(x);
//                        row.createCell(cols).setCellValue(amount);
//                    }
//                }
//
//                //Set the row to the next one in the sequence 
//                row = sheet.createRow((rows + 2));
//
//                jProgressBar1.setValue(rows + 1);
//            }
//            wb.write(new FileOutputStream("E:\\TEST_Advance.xlsx"));//Save the file
//            long duration = System.currentTimeMillis() - start;
//            DateFormat df = new SimpleDateFormat("HH 'Hours', mm 'Min(s),' ss 'Second(s)'");
//            df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//            System.out.println(df.format(new Date(duration)));
//            JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    private String sendNameToSampathBankFile(String name) {

        //String new_name = "Guards_Salary";
        String name1 = cmb_trans_type.getSelectedItem().toString().replaceAll("\\s", "");

        String filename = name1 + "_" + cmb_PayType.getSelectedItem().toString() + "_" + cmb_month.getSelectedItem().toString() + cmb_year.getSelectedItem().toString();
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss-a");
        Date date = new Date();
        String v = df.format(date);
        return name + "\\" + filename + "_" + v + ".xls";
    }

    private void Update_Excell_xsls_EPF() {

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File(sendNameToSampathBankFile("")));
        int click = fc.showSaveDialog(this);
        if (click == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String path = file.getParent();
            path = sendNameToSampathBankFile(path);
            txt_path.setText(path);

        }

        if (txt_path.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please select the Save Location for Excel file.");

        } else {

            try {
                lbl_lbl.setForeground(Color.BLACK);
                lbl_lbl.setText("Uploading Data into Excel File...");
                jProgressBar1.setValue(0);

                try {

                    File originalWb = new File("Sampath_Bank_System_Excel.xls");
                    File clonedWb = new File(txt_path.getText());
                    Files.copy(originalWb.toPath(), clonedWb.toPath());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SAMPATH_Bank_PayMasterFile_with_NDB.class.getName()).log(Level.SEVERE, null, ex);
                }

                String path = txt_path.getText();

                FileInputStream fsIP = new FileInputStream(new File(path));
                // FileInputStream fsIP2= new FileInputStream(new File("E:\\01. Sampath Bank System Excel.xls"));
                //Access the workbook
                HSSFWorkbook wb = new HSSFWorkbook(fsIP);

                //Access the worksheet, so that we can update / modify it.
                HSSFSheet worksheet = wb.getSheetAt(0);

                // declare a Cell object
                Cell cell = null;

                // Access the second cell in second row to update the value
//            cell = worksheet.getRow(8).getCell(1);//A/C
//            cell = worksheet.getRow(8).getCell(2);//Bank
//            cell = worksheet.getRow(8).getCell(3);//Branch
//            
//            cell = worksheet.getRow(8).getCell(5);//BankName
//            cell = worksheet.getRow(8).getCell(6);//TorE
//            cell = worksheet.getRow(8).getCell(7);//Unit
                //update multiple names from Jtable
                int nrow = jTable1.getRowCount();
                jProgressBar1.setMaximum(nrow);
                int start_row = 8;
                jProgressBar1.setValue(1);
//            cell = worksheet.getRow(4).getCell(6);//Name
//            String month = cmb_month.getSelectedItem().toString() + " " + cmb_year.getSelectedItem().toString();
//            cell.setCellValue(month);
                for (int i = 0; i < nrow; i++) {

                    String name = jTable1.getModel().getValueAt(i, 2).toString();
                    System.out.println("Name: " + name);
                    cell = worksheet.getRow(i + start_row).getCell(0);//Name
                    System.out.println("cell:" + cell);
                    cell.setCellValue(name);

                    String acc = jTable1.getModel().getValueAt(i, 3).toString();
                    cell = worksheet.getRow(i + start_row).getCell(1);//ACC
                    cell.setCellValue(acc);

                    String bank = jTable1.getModel().getValueAt(i, 4).toString();
                    cell = worksheet.getRow(i + start_row).getCell(2);//Bank
                    cell.setCellValue(bank);

                    String branch = jTable1.getModel().getValueAt(i, 5).toString();
                    cell = worksheet.getRow(i + start_row).getCell(3);//Branch
                    cell.setCellValue(branch);

                    String amount = jTable1.getModel().getValueAt(i, 6).toString();
                    Double d1 = Double.parseDouble(amount);
                    cell = worksheet.getRow(i + start_row).getCell(4);//Amount
                    cell.setCellValue(d1);

                    String bank_name = "";
                    if (jTable1.getModel().getValueAt(i, 11) == null) {
                        bank_name = "";
                    } else {
                        bank_name = jTable1.getModel().getValueAt(i, 11).toString();
                    }
                    cell = worksheet.getRow(i + start_row).getCell(5);//Bank Name
                    cell.setCellValue(bank_name);

                    String company = jTable1.getModel().getValueAt(i, 8).toString();
                    cell = worksheet.getRow(i + start_row).getCell(6);//Company 
                    cell.setCellValue(company);

                    String location = jTable1.getModel().getValueAt(i, 9).toString();
                    cell = worksheet.getRow(i + start_row).getCell(7);//location 
                    cell.setCellValue(location);

                    jProgressBar1.setValue(jProgressBar1.getValue() + 1);
                }

                HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
                fsIP.close();

                FileOutputStream output_file = new FileOutputStream(new File(path));

                wb.write(output_file);

                output_file.close();

                lbl_lbl.setText("Excel File Updated Succesfully...!");
                lbl_lbl.setForeground(Color.WHITE);
                System.out.println("done");

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "ERROR: Please Close the Excel File");
            }
        }
    }

    private String sendNameDB(String name) {

        //String new_name = "Guards_Salary";
        String name1 = cmb_trans_type.getSelectedItem().toString().replaceAll("\\s", "");

        String filename = name1 + cmb_PayType.getSelectedItem().toString() + cmb_month.getSelectedItem().toString() + cmb_year.getSelectedItem().toString();
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss-a");
        Date date = new Date();
        String v = df.format(date);
        return name + "\\" + filename + "_" + v + ".xlsx";
    }

    private void Update_NDB_Excell_xsls() {

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File(sendNameDB("")));
        int click = fc.showSaveDialog(this);
        if (click == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String path = file.getParent();
            path = sendNameDB(path);
            txt_path.setText(path);

        }

        if (txt_path.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please select the Save Location for Excel file.");

        } else {

            try {
                lbl_lbl.setForeground(Color.BLACK);
                lbl_lbl.setText("Uploading Data into Excel File...");
                jProgressBar1.setValue(0);

                try {

                    File originalWb = new File("NDB_BANK_FILE.xlsx");
                    File clonedWb = new File(txt_path.getText());
                    Files.copy(originalWb.toPath(), clonedWb.toPath());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SAMPATH_Bank_PayMasterFile_with_NDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                String path = txt_path.getText();

                //Read the spreadsheet that needs to be updated
                FileInputStream fsIP = new FileInputStream(new File(path));
                // FileInputStream fsIP2= new FileInputStream(new File("E:\\01. Sampath Bank System Excel.xls"));
                //Access the workbook
                XSSFWorkbook wb = new XSSFWorkbook(fsIP);

                //Access the worksheet, so that we can update / modify it.
                XSSFSheet worksheet = wb.getSheetAt(0);

                // declare a Cell object
                Cell cell = null;

                // Access the second cell in second row to update the value
//            cell = worksheet.getRow(8).getCell(1);//A/C
//            cell = worksheet.getRow(8).getCell(2);//Bank
//            cell = worksheet.getRow(8).getCell(3);//Branch
//            
//            cell = worksheet.getRow(8).getCell(5);//BankName
//            cell = worksheet.getRow(8).getCell(6);//TorE
//            cell = worksheet.getRow(8).getCell(7);//Unit
                //update multiple names from Jtable
                int nrow = jTable1.getRowCount();
                jProgressBar1.setMaximum(nrow);
                int start_row = 6;
                jProgressBar1.setValue(1);
//            cell = worksheet.getRow(4).getCell(6);//Name
//            String month = cmb_month.getSelectedItem().toString() + " " + cmb_year.getSelectedItem().toString();
//            cell.setCellValue(month);

                for (int i = 0; i < nrow; i++) {

                    /*
                    1. Dest. Bank No
                    2. Dest Branch No
                    3. Dest. Acc. No
                    4. Dest. Acc. Name
                    5. Amount
                    6. Ref
                                                        
                     */
                    //Dest. Bank No
                    String BanckCode = jTable1.getModel().getValueAt(i, 4).toString();
                    cell = worksheet.getRow(i + start_row).getCell(1);
                    System.out.println("bank code " + cell);
                    int code_01 = Integer.parseInt(BanckCode);
                    cell.setCellValue(code_01);

                    //Dest Branch No
                    String BranchCode = jTable1.getModel().getValueAt(i, 5).toString();
                    cell = worksheet.getRow(i + start_row).getCell(2);
                    System.out.println("branch code " + cell);
                    int code_2 = Integer.parseInt(BranchCode);
                    cell.setCellValue(code_2);

                    //Dest. Acc. No
                    String Acc = jTable1.getModel().getValueAt(i, 3).toString();
                    cell = worksheet.getRow(i + start_row).getCell(3);
                    long code_3 = Long.valueOf(Acc);
                    cell.setCellValue(code_3);

                    //Dest. Acc. Name
                    String Name = jTable1.getModel().getValueAt(i, 2).toString();
                    cell = worksheet.getRow(i + start_row).getCell(4);//name
                    cell.setCellValue(Name);
                    System.out.println(Name);

                    // Amount
                    String amount = jTable1.getModel().getValueAt(i, 6).toString();
                    Double d1 = Double.parseDouble(amount);
                    cell = worksheet.getRow(i + start_row).getCell(5);//Amount
                    cell.setCellValue(d1);

                    //Ref
//                    String narration = jTable1.getModel().getValueAt(i, 7).toString();
//                    cell = worksheet.getRow(i + start_row).getCell(14);//narration
//                    cell.setCellValue(narration);
                    jProgressBar1.setValue(jProgressBar1.getValue() + 1);

                }

                fsIP.close();

                FileOutputStream output_file = new FileOutputStream(new File(path));

                wb.write(output_file);

                output_file.close();

                lbl_lbl.setText("Excel File Saved Succesfully...!");
                lbl_lbl.setForeground(Color.WHITE);

                JOptionPane.showMessageDialog(rootPane, "Excel File Saved Succesfully...!");
                System.out.println("done");

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "ERROR: Please Close the Excel File");
            }

        }
    }

    private void Update_Excell_xsls_EPF_NEW() {
        try {

            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File(sendNameDB("")));
            int click = fc.showSaveDialog(this);
            if (click == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String path = file.getParent();
                path = sendNameDB(path);
                txt_path.setText(path);

            }

            lbl_lbl.setForeground(Color.BLACK);
            lbl_lbl.setText("Uploading Data into Excel File...");
            jProgressBar1.setValue(0);
            //Read the spreadsheet that needs to be updated
            String path = "Reports/Sampath_Bank_System_Excel.xls";
            FileInputStream fsIP = new FileInputStream(new File(path));
            // FileInputStream fsIP2= new FileInputStream(new File("E:\\01. Sampath Bank System Excel.xls"));
            //Access the workbook
            XSSFWorkbook wb = new XSSFWorkbook(fsIP);

            //Access the worksheet, so that we can update / modify it.
            XSSFSheet worksheet = wb.getSheetAt(0);

            // declare a Cell object
            Cell cell = null;

            // Access the second cell in second row to update the value
//            cell = worksheet.getRow(8).getCell(1);//A/C
//            cell = worksheet.getRow(8).getCell(2);//Bank
//            cell = worksheet.getRow(8).getCell(3);//Branch
//            
//            cell = worksheet.getRow(8).getCell(5);//BankName
//            cell = worksheet.getRow(8).getCell(6);//TorE
//            cell = worksheet.getRow(8).getCell(7);//Unit
            //update multiple names from Jtable
            int nrow = jTable1.getRowCount();
            jProgressBar1.setMaximum(nrow);
            int start_row = 8;
            jProgressBar1.setValue(1);
//            cell = worksheet.getRow(4).getCell(6);//Name
//            String month = cmb_month.getSelectedItem().toString() + " " + cmb_year.getSelectedItem().toString();
//            cell.setCellValue(month);
            for (int i = 0; i < nrow; i++) {

                String name = jTable1.getModel().getValueAt(i, 2).toString();
                System.out.println("Name: " + name);
                cell = worksheet.getRow(i + start_row).getCell(0);//Name
                System.out.println("cell:" + cell);
                cell.setCellValue(name);

                String acc = jTable1.getModel().getValueAt(i, 3).toString();
                cell = worksheet.getRow(i + start_row).getCell(1);//ACC
                cell.setCellValue(acc);

                String bank = jTable1.getModel().getValueAt(i, 4).toString();
                cell = worksheet.getRow(i + start_row).getCell(2);//Bank
                cell.setCellValue(bank);

                String branch = jTable1.getModel().getValueAt(i, 5).toString();
                cell = worksheet.getRow(i + start_row).getCell(3);//Branch
                cell.setCellValue(branch);

                String amount = jTable1.getModel().getValueAt(i, 6).toString();
                Double d1 = Double.parseDouble(amount);
                cell = worksheet.getRow(i + start_row).getCell(4);//Amount
                cell.setCellValue(d1);

                String bank_name = "";
                if (jTable1.getModel().getValueAt(i, 11) == null) {
                    bank_name = "";
                } else {
                    bank_name = jTable1.getModel().getValueAt(i, 11).toString();
                }
                cell = worksheet.getRow(i + start_row).getCell(5);//Bank Name
                cell.setCellValue(bank_name);

                String company = jTable1.getModel().getValueAt(i, 8).toString();
                cell = worksheet.getRow(i + start_row).getCell(6);//Company 
                cell.setCellValue(company);

                String location = jTable1.getModel().getValueAt(i, 9).toString();
                cell = worksheet.getRow(i + start_row).getCell(7);//location 
                cell.setCellValue(location);

                jProgressBar1.setValue(jProgressBar1.getValue() + 1);
            }

            XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
            fsIP.close();

            FileOutputStream output_file = new FileOutputStream(new File(path));

            wb.write(output_file);

            output_file.close();

            lbl_lbl.setText("Excel File Updated Succesfully...!");
            lbl_lbl.setForeground(Color.WHITE);
            System.out.println("done");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "ERROR: Please Close the Excel File");
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

        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        lbl_emp_count = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_total = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_lbl = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        cmb_trans_type = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmb_PayType = new javax.swing.JComboBox();
        lbl_emp_without_Bank = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txt_path = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmb_Company = new javax.swing.JComboBox();
        txt_ComCode = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        cmb_bank = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton5.setText("Get Data");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 200, 110));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton4.setText("Export to Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, 210, 50));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Salary & Advance Export  - Sampath Corporate Payment System (SCPS)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 530, 40));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021", "2022" }));
        cmb_year.setSelectedIndex(3);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        lbl_emp_count.setBackground(new java.awt.Color(204, 204, 204));
        lbl_emp_count.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_emp_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_emp_count.setOpaque(true);
        getContentPane().add(lbl_emp_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, 60, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP No", "Rank", "Name", "Account No.", "Bank Code", "Branch Code", "Amount", "Bank Name", "Company", "LocationName", "Location", "Bank Name For Excel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(120);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1000, 370));

        lbl_total.setBackground(new java.awt.Color(204, 204, 204));
        lbl_total.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_total.setText("0.00");
        lbl_total.setOpaque(true);
        getContentPane().add(lbl_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, 130, 20));
        getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 1020, 10));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Pay Month :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Employee Count:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, 20));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Bank Amount :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 570, -1, 20));

        lbl_lbl.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        getContentPane().add(lbl_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 330, 20));

        jProgressBar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 330, 80));

        cmb_trans_type.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_trans_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Advance", "Monthly Salary", "Festival Advance" }));
        getContentPane().add(cmb_trans_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 170, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Transaction Type :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Type :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 40, 20));

        cmb_PayType.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_PayType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bank", "Hand", "Slip" }));
        getContentPane().add(cmb_PayType, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 140, -1));

        lbl_emp_without_Bank.setBackground(new java.awt.Color(204, 204, 204));
        lbl_emp_without_Bank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_emp_without_Bank.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_emp_without_Bank.setText("0");
        lbl_emp_without_Bank.setOpaque(true);
        getContentPane().add(lbl_emp_without_Bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 570, 40, 20));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton3.setText("Browse Excel File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 50, 30));

        txt_path.setEditable(false);
        txt_path.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(txt_path, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 40, 23));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText("Emps Without Bank Details :");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 570, 170, 20));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Location     :");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 80, 40));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 430, -1));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 60, 23));

        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox1.setText("  All Locations");
        jCheckBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCheckBox1PropertyChange(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, -1, -1));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Remove Selected Rows");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 280, 50));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Company   :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 70, 20));

        cmb_Company.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_Company.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=ALL=" }));
        cmb_Company.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_CompanyPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_Company, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 430, -1));

        txt_ComCode.setEditable(false);
        txt_ComCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_ComCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ComCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ComCodeFocusLost(evt);
            }
        });
        getContentPane().add(txt_ComCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 60, 23));
        getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1020, 10));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("Bank :");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 40, 20));

        cmb_bank.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_bank.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sampath", "NDB" }));
        getContentPane().add(cmb_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 140, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {

//                if (txt_path.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(rootPane, "Please select the excel file.");
//                } else {
                if (cmb_bank.getSelectedIndex() == 0) {
                    Update_Excell_xsls_EPF();
                } else {
                    Update_NDB_Excell_xsls();
                }
//
//                }

            }
        });
        hilo.start();


    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible


    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {

                if (cmb_trans_type.getSelectedIndex() == 0) {
                    Advance_table();
                } else if (cmb_trans_type.getSelectedIndex() == 1) {

                    SALARY_table();
                } else {
                    festival_table();

                }

            }
        });
        hilo.start();               // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File f;
        String file;

        if (fc.getSelectedFile() == null) {
            txt_path.setText("");
        } else {
            f = fc.getSelectedFile();
            file = f.getAbsolutePath();
            txt_path.setText(file);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void jCheckBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCheckBox1PropertyChange
        if (jCheckBox1.isSelected()) {
            cmb_defLocation.setEnabled(false);
            txt_locCode.setText("");
        } else {
            cmb_defLocation.setEnabled(true);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int nrow = jTable1.getSelectedRowCount();
        if (nrow > 0) {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to Remove Selected Rows?", "Remove Rows", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            lbl_total.setText("0");
            lbl_emp_without_Bank.setText("0");
            lbl_emp_count.setText("0");
            if (reply == JOptionPane.YES_OPTION) {

                for (int row = 0; nrow > row; row++) {

                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(jTable1.getSelectedRow());

                }

                for (int i = 0; jTable1.getRowCount() > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

                    Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 6).toString());
                    Double x = Double.parseDouble(lbl_total.getText());
                    lbl_total.setText(String.format("%.2f", x += adv_amt));

                    int count = dtm.getRowCount();
                    lbl_emp_count.setText(Integer.toString(count));

                    if (jTable1.getValueAt(i, 3).toString().equals("")) {
                        int emp_count = Integer.parseInt(lbl_emp_without_Bank.getText());
                        lbl_emp_without_Bank.setText(Integer.toString(emp_count + 1));
                    }

                }

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Select one or more Rows to Remove...");

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_ComCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ComCodeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComCodeFocusGained

    private void txt_ComCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ComCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ComCodeFocusLost
    String companyName = "";
    private void cmb_CompanyPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_CompanyPopupMenuWillBecomeInvisible
        if (cmb_Company.getSelectedItem().equals("=ALL=")) {

            txt_ComCode.setText("N/A");
            txt_ComCode.setForeground(Color.red);

        } else {
            txt_ComCode.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from company_reg where  ComCode= '" + cmb_Company.getSelectedItem().toString() + "' OR ComName= '" + cmb_Company.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("ComCode");
                    String name = rs.getString("ComName");

                    cmb_Company.setSelectedItem(name);
                    txt_ComCode.setText(code);
                    companyName = code;
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

        }        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CompanyPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(SAMPATH_Bank_PayMasterFile_with_NDB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SAMPATH_Bank_PayMasterFile_with_NDB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SAMPATH_Bank_PayMasterFile_with_NDB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SAMPATH_Bank_PayMasterFile_with_NDB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SAMPATH_Bank_PayMasterFile_with_NDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cmb_Company;
    public static javax.swing.JComboBox cmb_PayType;
    public static javax.swing.JComboBox cmb_bank;
    private javax.swing.JComboBox cmb_defLocation;
    public static javax.swing.JComboBox cmb_month;
    public static javax.swing.JComboBox cmb_trans_type;
    public static javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_emp_count;
    private javax.swing.JLabel lbl_emp_without_Bank;
    private javax.swing.JLabel lbl_lbl;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JTextField txt_ComCode;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_path;
    // End of variables declaration//GEN-END:variables
}
