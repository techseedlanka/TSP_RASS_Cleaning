/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class RPT_Advance extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Advance
     */
    static ArrayList adv;

    public RPT_Advance() {
        initComponents();
        get_Location_Details();
        adv = new ArrayList();
        seticon();
        jTable1.setVisible(false);

        lbl_bank.setVisible(false);
        lbl_cash.setVisible(false);
        lbl_slip.setVisible(false);

        lbl_PAID_bank.setVisible(false);
        lbl_PAID_cash.setVisible(false);
        lbl_PAID_slip.setVisible(false);

        lbl_UNPAID_bank.setVisible(false);
        lbl_UNPAID_cash.setVisible(false);
        lbl_UNPAID_slip.setVisible(false);

        btn_festv_adv.setVisible(false);

        jScrollPane1.setVisible(false);

    }

    private void seticon() {
        this.setTitle("Advance Report");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
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

    void Festival_adv_print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(adv);
            String path = "Reports\\Emp_Festival_Advance.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
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

    void print_sum() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(adv);
            String path = "Reports\\Emp_Monthly_Advance_SUM.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void table() {

        String LocCode = null;
//        String LocName = null;
        String EmpNo = null;
        String EmpName = null;
        String AdvAmount = null;
        String PayType = null;
        String Status = null;
//        String Month = null;
//        String Year = null;
//        String Bank_amount = null;
//        String Cash_Amount = null;
//        String Slip_Amount = null;
//        String BankName = "0";
        String Rank = null;
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            Connection con = DbConnection.getconnection();
            String sql = "SELECT * from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                LocCode = rs.getString("Location");
                // LocName = rs.getString("location_reg.LocName");
                EmpNo = rs.getString("EPFno");
                EmpName = rs.getString("Name");
                AdvAmount = rs.getString("Amount");
                PayType = rs.getString("Note");
//                Month = rs.getString("PayMonth");
//                Year = rs.getString("PayYear");
                Rank = rs.getString("Rank");
                Status = rs.getString("Status");

                Vector v = new Vector();
                v.add(EmpNo);
                v.add(EmpName);
                v.add(Rank);
                v.add(AdvAmount);
                v.add(LocCode);
                v.add("");
                v.add(PayType);
                v.add("");
                v.add(Status);

                dtm.addRow(v);

            }

            int nrow = jTable1.getModel().getRowCount();

            if (nrow == 0) {

            } else {
                lbl_bank.setText("0.00");
                lbl_cash.setText("0.00");
                lbl_slip.setText("0.00");

                for (int i = 0; nrow > i; i++) {

                    String loc = jTable1.getModel().getValueAt(i, 4).toString();
                    String epf = jTable1.getModel().getValueAt(i, 0).toString();
                    String pay = jTable1.getModel().getValueAt(i, 6).toString();

                    String sql2 = "SELECT * from location_reg  WHERE  LocCode='" + loc + "' ";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        String loName = rs2.getString("LocName");

                        jTable1.getModel().setValueAt(loName, i, 5);

                    }

                    if (pay.equals("Bank")) {

                        String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
                        PreparedStatement pst3 = con.prepareStatement(sql3);
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {

                            String bankCode = rs3.getString("Bank");

                            if (bankCode == null) {
                                bankCode = "";
                            } else {
                                String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
                                PreparedStatement pst4 = con.prepareStatement(sql4);
                                ResultSet rs4 = pst4.executeQuery();
                                while (rs4.next()) {

                                    String bankname = rs4.getString("BankName");
                                    if (bankname == null) {
                                        bankname = "";
                                    } else {
                                        jTable1.getModel().setValueAt(bankname, i, 7);
                                    }

                                }
                            }

                        }
                    } else {

                    }

//                    String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
//                    PreparedStatement pst3 = con.prepareStatement(sql3);
//                    ResultSet rs3 = pst3.executeQuery();
//                    while (rs3.next()) {
//
//                        String bankCode = rs3.getString("Bank");
//
//                        if (bankCode == null) {
//                            bankCode = "";
//                        } else {
//                            String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
//                            PreparedStatement pst4 = con.prepareStatement(sql4);
//                            ResultSet rs4 = pst4.executeQuery();
//                            while (rs4.next()) {
//
//                                String bankname = rs4.getString("BankName");
//                                if (bankname == null) {
//                                    bankname = "";
//                                } else {
//                                    jTable1.getModel().setValueAt(bankname, i, 7);
//                                }
//
//                            }
//                        }
//
//                    }
                    Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString());
//                    String pay = jTable1.getModel().getValueAt(i, 6).toString();

                    if (pay.equals("Bank")) {

                        Double x = Double.parseDouble(lbl_bank.getText());
                        //x += adv_amt;
                        lbl_bank.setText(String.format("%.2f", x += adv_amt));

                    }

                    if (pay.equals("Hand")) {

                        Double x = Double.parseDouble(lbl_cash.getText());
                        //x += adv_amt;
                        lbl_cash.setText(String.format("%.2f", x += adv_amt));

                    }

                    if (pay.equals("Slip")) {

                        Double x = Double.parseDouble(lbl_slip.getText());
                        //x += adv_amt;
                        lbl_slip.setText(String.format("%.2f", x += adv_amt));

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void table_to_ireport() {
        int nrow = jTable1.getModel().getRowCount();
        if (nrow == 0) {

        } else {
            for (int i = 0; nrow > i; i++) {
                bean_EMP_Advance bds = new bean_EMP_Advance();

                bds.setLoc_code(jTable1.getModel().getValueAt(i, 4).toString());
                bds.setLoc_name(jTable1.getModel().getValueAt(i, 5).toString());
                bds.setEmp_code(jTable1.getModel().getValueAt(i, 0).toString());
                bds.setEmp_name(jTable1.getModel().getValueAt(i, 1).toString());
                bds.setRank(jTable1.getModel().getValueAt(i, 2).toString());
                bds.setAmount(Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString()));
                bds.setMonth(cmb_month.getSelectedItem().toString());
                bds.setYear(cmb_year.getSelectedItem().toString());

                if (jTable1.getModel().getValueAt(i, 7) == null) {
                    bds.setBank_Name("");
                } else {
                    bds.setBank_Name(jTable1.getModel().getValueAt(i, 7).toString());
                }

                bds.setPay_Type(jTable1.getModel().getValueAt(i, 6).toString());
                bds.setBank_total(Double.parseDouble(lbl_bank.getText()));
                bds.setCash_total(Double.parseDouble(lbl_cash.getText()));
                bds.setSlip_total(Double.parseDouble(lbl_slip.getText()));
                adv.add(bds);
//Emp_Monthly_Advance_StatusWise.jrxml
            }

            print();
            adv.clear();
        }

    }

    private void get_paid_only_amt() {

        try {
            Connection con = DbConnection.getconnection();
            String sql = "SELECT *,SUM(Amount) from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='PAID' and Note='Bank'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lbl_PAID_bank.setText(rs.getString("SUM(Amount)"));
            }

            String sql2 = "SELECT *,SUM(Amount) from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='PAID' and Note='Hand'";
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                lbl_PAID_cash.setText(rs2.getString("SUM(Amount)"));
            }

            String sql3 = "SELECT *,SUM(Amount) from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='PAID' and Note='Slip'";
            PreparedStatement pst3 = con.prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery();
            while (rs3.next()) {
                lbl_PAID_slip.setText(rs3.getString("SUM(Amount)"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error Occurred!,Error Code:-ERX0523");
        }
    }

    private void get_unpaid_only_amt() {

        try {
            Connection con = DbConnection.getconnection();
            String sql = "SELECT *,SUM(Amount) from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='UNPAID' and Note='Bank'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lbl_UNPAID_bank.setText(rs.getString("SUM(Amount)"));
            }

            String sql2 = "SELECT *,SUM(Amount) from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='UNPAID' and Note='Hand'";
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                lbl_UNPAID_cash.setText(rs2.getString("SUM(Amount)"));
            }

            String sql3 = "SELECT *,SUM(Amount) from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='UNPAID' and Note='Slip'";
            PreparedStatement pst3 = con.prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery();
            while (rs3.next()) {
                lbl_UNPAID_slip.setText(rs3.getString("SUM(Amount)"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error Occurred!,Error Code:-EXER_'get_unpaid_total'");
        }
    }

    private void get_status_wise_data_to_table() {

        String LocCode = null;
        String EmpNo = null;
        String EmpName = null;
        String AdvAmount = null;
        String PayType = null;
        String Status = null;

        if (cmb_reconStatus.getSelectedIndex() == 2) {
            Status = "on-going";
        } else {
            Status = cmb_reconStatus.getSelectedItem().toString();
        }

        String Rank = null;
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            Connection con = DbConnection.getconnection();
            String sql = null;

            if (cb_all.isSelected()) {

                sql = "SELECT * from salary_advance_1  WHERE   PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='" + Status + "'";
            } else {
                if (cmb_defLocation.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Please Select a Location!", "Empty Location", JOptionPane.WARNING_MESSAGE);

                } else {
                    sql = "SELECT * from salary_advance_1  WHERE  Location='" + txt_locCode.getText() + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' "
                            + "and  PayYear='" + cmb_year.getSelectedItem().toString() + "' and Status='" + Status + "'";
                }
            }

            if (sql != null) {

                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    LocCode = rs.getString("Location");
                    EmpNo = rs.getString("EPFno");
                    EmpName = rs.getString("Name");
                    AdvAmount = rs.getString("Amount");
                    PayType = rs.getString("Note");
                    Rank = rs.getString("Rank");
                    Status = rs.getString("Status");

                    Vector v = new Vector();
                    v.add(EmpNo);
                    v.add(EmpName);
                    v.add(Rank);
                    v.add(AdvAmount);
                    v.add(LocCode);
                    v.add("");
                    v.add(PayType);
                    v.add("");
                    v.add(Status);

                    dtm.addRow(v);

                }
            }

            int nrow = jTable1.getModel().getRowCount();

            if (nrow == 0) {

            } else {
                lbl_bank.setText("0.00");
                lbl_cash.setText("0.00");
                lbl_slip.setText("0.00");

                for (int i = 0; nrow > i; i++) {

                    String loc = jTable1.getModel().getValueAt(i, 4).toString();
                    String epf = jTable1.getModel().getValueAt(i, 0).toString();
                    String pay = jTable1.getModel().getValueAt(i, 6).toString();

                    String sql2 = "SELECT * from location_reg  WHERE  LocCode='" + loc + "' ";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        String loName = rs2.getString("LocName");

                        jTable1.getModel().setValueAt(loName, i, 5);

                    }

                    if (pay.equals("Bank")) {

                        String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
                        PreparedStatement pst3 = con.prepareStatement(sql3);
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {

                            String bankCode = rs3.getString("Bank");

                            if (bankCode == null) {
                                bankCode = "";
                            } else {
                                String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
                                PreparedStatement pst4 = con.prepareStatement(sql4);
                                ResultSet rs4 = pst4.executeQuery();
                                while (rs4.next()) {

                                    String bankname = rs4.getString("BankName");
                                    if (bankname == null) {
                                        bankname = "";
                                    } else {
                                        jTable1.getModel().setValueAt(bankname, i, 7);
                                    }

                                }
                            }

                        }
                    } else {

                    }

                    Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString());

                    if (pay.equals("Bank")) {

                        Double x = Double.parseDouble(lbl_bank.getText());
                        //x += adv_amt;
                        lbl_bank.setText(String.format("%.2f", x += adv_amt));

                    }

                    if (pay.equals("Hand")) {

                        Double x = Double.parseDouble(lbl_cash.getText());
                        //x += adv_amt;
                        lbl_cash.setText(String.format("%.2f", x += adv_amt));

                    }

                    if (pay.equals("Slip")) {

                        Double x = Double.parseDouble(lbl_slip.getText());
                        //x += adv_amt;
                        lbl_slip.setText(String.format("%.2f", x += adv_amt));

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("SQL String can not be NULL")) {
                System.out.println("Null SQL");
            }
        }

    }

    private void print_status_wise_report() {
        int nrow = jTable1.getModel().getRowCount();
        if (nrow == 0) {

        } else {
            for (int i = 0; nrow > i; i++) {
                bean_EMP_Advance bds = new bean_EMP_Advance();

                bds.setLoc_code(jTable1.getModel().getValueAt(i, 4).toString());
                bds.setLoc_name(jTable1.getModel().getValueAt(i, 5).toString());
                bds.setEmp_code(jTable1.getModel().getValueAt(i, 0).toString());
                bds.setEmp_name(jTable1.getModel().getValueAt(i, 1).toString());
                bds.setRank(jTable1.getModel().getValueAt(i, 2).toString());
                bds.setAmount(Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString()));
                bds.setMonth(cmb_month.getSelectedItem().toString());
                bds.setYear(cmb_year.getSelectedItem().toString());

                if (jTable1.getModel().getValueAt(i, 7) == null) {
                    bds.setBank_Name("");
                } else {
                    bds.setBank_Name(jTable1.getModel().getValueAt(i, 7).toString());
                }

                bds.setStat(jTable1.getModel().getValueAt(i, 8).toString());

                bds.setPay_Type(jTable1.getModel().getValueAt(i, 6).toString());
                bds.setBank_total(Double.parseDouble(lbl_bank.getText()));
                bds.setCash_total(Double.parseDouble(lbl_cash.getText()));
                bds.setSlip_total(Double.parseDouble(lbl_slip.getText()));
                adv.add(bds);
//Emp_Monthly_Advance_StatusWise.jrxml
            }

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(adv);
                String path = "Reports\\Emp_Monthly_Advance_StatusWise.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            adv.clear();

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

        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_locCode = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        cmb_defLocation = new javax.swing.JComboBox();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_slip = new javax.swing.JLabel();
        lbl_bank = new javax.swing.JLabel();
        lbl_cash = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        lbl_PAID_slip = new javax.swing.JLabel();
        lbl_PAID_bank = new javax.swing.JLabel();
        lbl_PAID_cash = new javax.swing.JLabel();
        lbl_UNPAID_cash = new javax.swing.JLabel();
        lbl_UNPAID_bank = new javax.swing.JLabel();
        lbl_UNPAID_slip = new javax.swing.JLabel();
        btn_festv_adv = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JSeparator();
        cmb_reconStatus = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_all = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton4.setText("Salary Advance");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Location :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        txt_locCode.setBackground(new java.awt.Color(204, 204, 204));
        txt_locCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txt_locCode.setOpaque(true);
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 70, 20));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Salary Advance Report");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 40));
        getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 390, 10));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 270, -1));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021", "2022" }));
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP", "Name", "Rank", "Amount", "Location", "LocationName", "PayType", "Bank Name", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 70, 20));

        lbl_slip.setText("0.00");
        getContentPane().add(lbl_slip, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, -1));

        lbl_bank.setText("0.00");
        getContentPane().add(lbl_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 30, -1));

        lbl_cash.setText("0.00");
        getContentPane().add(lbl_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 30, -1));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton5.setText("Advance Summery");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, 40));
        getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 390, 10));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Status wise Report :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 20));

        jButton6.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton6.setText("Advance Reconciliation");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 370, -1));

        lbl_PAID_slip.setText("0.00");
        getContentPane().add(lbl_PAID_slip, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 30, -1));

        lbl_PAID_bank.setText("0.00");
        getContentPane().add(lbl_PAID_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 30, -1));

        lbl_PAID_cash.setText("0.00");
        getContentPane().add(lbl_PAID_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 30, -1));

        lbl_UNPAID_cash.setText("0.00");
        getContentPane().add(lbl_UNPAID_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 30, -1));

        lbl_UNPAID_bank.setText("0.00");
        getContentPane().add(lbl_UNPAID_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 30, -1));

        lbl_UNPAID_slip.setText("0.00");
        getContentPane().add(lbl_UNPAID_slip, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 30, -1));

        btn_festv_adv.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_festv_adv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        btn_festv_adv.setText("Festival Advance");
        btn_festv_adv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_festv_advActionPerformed(evt);
            }
        });
        getContentPane().add(btn_festv_adv, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 20, -1));
        getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 390, 10));

        cmb_reconStatus.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_reconStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAID", "UNPAID", "No Action" }));
        getContentPane().add(cmb_reconStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 110, 30));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Pay Month :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));

        cb_all.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_all.setText("All");
        getContentPane().add(cb_all, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, 30));

        jButton7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton7.setText("Preview Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 180, -1));
        getContentPane().add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 390, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        table();
        table_to_ireport();
        cmb_defLocation.setEditable(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

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

                //cmb_defLocation.setEditable(false);
                //                cmb_defLocation.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible


    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

//        try {
//            Connection conn = DbConnection.getconnection();
//            JasperDesign jd = JRXmlLoader.load("Reports\\EMP_Monthly_Advance_Summery_Loc_wise.jrxml");
//            String sql = "SELECT *,SUM(Amount),COUNT(EPFno),location_reg.LocType FROM salary_advance_1  JOIN location_reg ON salary_advance_1.Location = location_reg.LocCode where salary_advance_1.PayMonth='" + cmb_month.getSelectedItem().toString() + "'  and PayYear='" + cmb_year.getSelectedItem().toString() + "' GROUP BY salary_advance_1.Location ORDER BY location_reg.LocName ";
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
        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst;

            String loc = null;
            String loc_amt = "0.00";
            String bank = "0.00";
            String hand = "0.00";
            String slip = "0.00";
            String loc_name = null;

            String sql_loc = "select *,SUM(Amount) from salary_advance_1 where  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "'  group by Location";
            pst = con.prepareStatement(sql_loc);
            ResultSet rs_loc = pst.executeQuery();
            while (rs_loc.next()) {

                loc = rs_loc.getString("Location");
                loc_amt = rs_loc.getString("SUM(Amount)");

                String sql = "select *,SUM(Amount) from salary_advance_1 where Location='" + loc + "' and PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Bank'";
                pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    if ((rs.getString("SUM(Amount)")) == null) {
                        bank = "0.00";
                    } else {
                        bank = (rs.getString("SUM(Amount)"));
                    }

                }

                String sql1 = "select *,SUM(Amount) from salary_advance_1 where Location='" + loc + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Hand'";
                pst = con.prepareStatement(sql1);
                ResultSet rs1 = pst.executeQuery();
                while (rs1.next()) {

                    if ((rs1.getString("SUM(Amount)")) == null) {
                        hand = "0.00";
                    } else {
                        hand = (rs1.getString("SUM(Amount)"));
                    }

                }

                String sql2 = "select *,SUM(Amount) from salary_advance_1 where Location='" + loc + "' and  PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "' and Note='Slip'";
                pst = con.prepareStatement(sql2);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next()) {
                    if ((rs2.getString("SUM(Amount)")) == null) {
                        slip = "0.00";
                    } else {
                        slip = (rs2.getString("SUM(Amount)"));
                    }

                }

                String sql3 = "select *  from location_reg where LocCode='" + loc + "'";
                pst = con.prepareStatement(sql3);
                ResultSet rs3 = pst.executeQuery();
                while (rs3.next()) {
                    loc_name = (rs3.getString("LocName"));
                }

                bean_EMP_Advance bds = new bean_EMP_Advance();
                bds.setLoc_code(loc);
                bds.setLoc_name(loc_name);
                bds.setAmount(Double.parseDouble(loc_amt));
                bds.setBank_total(Double.parseDouble(bank));
                bds.setCash_total(Double.parseDouble(hand));
                bds.setSlip_total(Double.parseDouble(slip));
                bds.setMonth(cmb_month.getSelectedItem().toString());
                bds.setYear(cmb_year.getSelectedItem().toString());
                adv.add(bds);

            }

            print_sum();
            adv.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        table();
        get_paid_only_amt();
        get_unpaid_only_amt();
        int nrow = jTable1.getModel().getRowCount();
        if (nrow == 0) {

        } else {

            Double paid_bank = 0.00;
            Double paid_hand = 0.00;
            Double paid_slip = 0.00;

            if (lbl_PAID_bank.getText() == null) {

            } else {
                paid_bank = Double.parseDouble(lbl_PAID_bank.getText());
            }

            if (lbl_PAID_cash.getText() == null) {
            } else {
                paid_hand = Double.parseDouble(lbl_PAID_cash.getText());
            }

            if (lbl_PAID_slip.getText() == null) {
            } else {
                paid_slip = Double.parseDouble(lbl_PAID_slip.getText());
            }

            Double unpaid_bank = 0.00;
            Double unpaid_hand = 0.00;
            Double unpaid_slip = 0.00;

            if (lbl_UNPAID_bank.getText() == null) {

            } else {
                unpaid_bank = Double.parseDouble(lbl_UNPAID_bank.getText());
            }

            if (lbl_UNPAID_cash.getText() == null) {
            } else {
                unpaid_hand = Double.parseDouble(lbl_UNPAID_cash.getText());
            }

            if (lbl_UNPAID_slip.getText() == null) {
            } else {
                unpaid_slip = Double.parseDouble(lbl_UNPAID_slip.getText());
            }

            for (int i = 0; nrow > i; i++) {
                bean_EMP_Advance bds = new bean_EMP_Advance();

                bds.setLoc_code(jTable1.getModel().getValueAt(i, 4).toString());
                bds.setLoc_name(jTable1.getModel().getValueAt(i, 5).toString());
                bds.setEmp_code(jTable1.getModel().getValueAt(i, 0).toString());
                bds.setEmp_name(jTable1.getModel().getValueAt(i, 1).toString());
                bds.setRank(jTable1.getModel().getValueAt(i, 2).toString());
                bds.setAmount(Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString()));
                bds.setMonth(cmb_month.getSelectedItem().toString());
                bds.setYear(cmb_year.getSelectedItem().toString());

                if (jTable1.getModel().getValueAt(i, 7) == null) {
                    bds.setBank_Name("");
                } else {
                    bds.setBank_Name(jTable1.getModel().getValueAt(i, 7).toString());
                }

                bds.setPay_Type(jTable1.getModel().getValueAt(i, 6).toString());
                bds.setBank_total(Double.parseDouble(lbl_bank.getText()));
                bds.setCash_total(Double.parseDouble(lbl_cash.getText()));
                bds.setSlip_total(Double.parseDouble(lbl_slip.getText()));
                bds.setStat(jTable1.getModel().getValueAt(i, 8).toString());
                bds.setPaid_bank_total(paid_bank);
                bds.setPaid_cash_total(paid_hand);
                bds.setPaid_slip_total(paid_slip);
                bds.setUnpaid_bank_total(unpaid_bank);
                bds.setUnpaid_slip_total(unpaid_slip);
                bds.setUnpaid_cash_total(unpaid_hand);

                adv.add(bds);

            }

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(adv);
                String path = "Reports\\Emp_Monthly_Advance_Reconsiliation.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            adv.clear();
        }
        cmb_defLocation.setEditable(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_festv_advActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_festv_advActionPerformed

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("select * from salary_advance_festival where Status='on-going' ORDER BY EPFno");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String epf = rs.getString("EPFno");
                String amount = rs.getString("Amount");
                String firtsissue = rs.getString("FirstIns");
                String lastissue = rs.getString("LastIns");
                String install = rs.getString("Installments");
                String rent = rs.getString("Rental");
                String name = "";
                String rank = "";

                String sql = "select * from employee_reg where EmployeeNo='" + epf + "' ";
                PreparedStatement pst2 = con.prepareStatement(sql);
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {

                    name = rs2.getString("NameWithInitials");
                    rank = rs2.getString("Designation");

                }

                bean_EMP_Advance bds = new bean_EMP_Advance();
                bds.setFes_epf(epf);
                bds.setFes_name(name);
                bds.setFes_rent(Double.parseDouble(rent));
                bds.setFes_amount(Double.parseDouble(amount));
                bds.setFes_first(firtsissue);
                bds.setFes_last(lastissue);
                bds.setFes_install(install);
                bds.setFes_rank(rank);
                adv.add(bds);

            }
            Festival_adv_print();
            adv.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btn_festv_advActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        get_status_wise_data_to_table();
        print_status_wise_report();
    }//GEN-LAST:event_jButton7ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RPT_Advance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_Advance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_Advance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_Advance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_Advance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_festv_adv;
    private javax.swing.JCheckBox cb_all;
    public static javax.swing.JComboBox cmb_defLocation;
    public static javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox<String> cmb_reconStatus;
    public static javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_PAID_bank;
    private javax.swing.JLabel lbl_PAID_cash;
    private javax.swing.JLabel lbl_PAID_slip;
    private javax.swing.JLabel lbl_UNPAID_bank;
    private javax.swing.JLabel lbl_UNPAID_cash;
    private javax.swing.JLabel lbl_UNPAID_slip;
    private javax.swing.JLabel lbl_bank;
    private javax.swing.JLabel lbl_cash;
    private javax.swing.JLabel lbl_slip;
    public static javax.swing.JLabel txt_locCode;
    // End of variables declaration//GEN-END:variables
}
