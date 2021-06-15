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
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Sapu
 */
public class SAMPATH_Bank_Excell_Export extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Advance
     */
    static ArrayList adv;

    public SAMPATH_Bank_Excell_Export() {
        initComponents();
        get_Location_Details();
        adv = new ArrayList();
        seticon();
//        jTable1.setVisible(false);
//        lbl_bank.setVisible(false);
//        lbl_cash.setVisible(false);
//        lbl_slip.setVisible(false);
//        jScrollPane1.setVisible(false);
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

    private void table() {

        String LocCode = "";
//        String LocName = null;
        String EmpNo = "";
        String EmpName = "";
        String AdvAmount = "";
        String PayType = "";
//        String Month = null;
//        String Year = null;
//        String Bank_amount = null;
//        String Cash_Amount = null;
//        String Slip_Amount = null;
//        String BankName = "0";
        String Rank = "";
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            Connection con = DbConnection.getconnection();
            // String sql = "SELECT * from salary_advance_1  WHERE   Location='" + txt_locCode.getText() + "' and PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "'";
            String sql = "SELECT * from salary_advance_1  WHERE    PayMonth='" + cmb_month.getSelectedItem().toString() + "' and  PayYear='" + cmb_year.getSelectedItem().toString() + "'";

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

                Vector v = new Vector();
                v.add(EmpNo);
                v.add(EmpName);
                v.add(Rank);
                v.add(Double.parseDouble(AdvAmount));
                v.add(LocCode);
                v.add("");
                v.add(PayType);

                dtm.addRow(v);

            }

            int nrow = jTable1.getModel().getRowCount();

            if (nrow == 0) {

            } else {
                lbl_bank.setText("0.00");
                lbl_cash.setText("0.00");
                lbl_slip.setText("0.00");

                for (int i = 0; i < nrow; i++) {

                    String loc = jTable1.getModel().getValueAt(i, 4).toString();
                    String epf = jTable1.getModel().getValueAt(i, 0).toString();

                    String sql2 = "SELECT * from location_reg  WHERE  LocCode='" + loc + "' ";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        String loName = rs2.getString("LocName");

                        jTable1.getModel().setValueAt(loName, i, 5);

                    }

                    String sql3 = "SELECT * from emp_bank_acc  WHERE  EMPno='" + epf + "' ";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    ResultSet rs3 = pst3.executeQuery();
                    while (rs3.next()) {

                        String bankCode = rs3.getString("Bank");
                        System.out.println(epf + " bank code:" + bankCode);
                        String bankname = "";
                        if (bankCode == null | bankCode.equals("N/A")) {
                            bankCode = "NA";
                            bankname = "NA";
                            // System.out.println(epf+" bank name:" + bankname);

                        } else {
                            String sql4 = "SELECT * from bank_main  WHERE  BankCode='" + bankCode + "' ";
                            PreparedStatement pst4 = con.prepareStatement(sql4);
                            ResultSet rs4 = pst4.executeQuery();
                            while (rs4.next()) {

                                bankname = rs4.getString("BankName");
                                //  System.out.println(epf+" bank name:" + bankname);
                                if (bankname == null | bankname.equals("N/A")) {
                                    bankname = "NA";
                                } else {
                                    jTable1.getModel().setValueAt(bankname, i, 7);
                                }

                            }
                        }

                    }

                    Double adv_amt = Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString());
                    String pay = jTable1.getModel().getValueAt(i, 6).toString();

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

                for (int i = 0; i < nrow; i++) {

                    if (jTable1.getValueAt(i, 7) == null) {
                        jTable1.getModel().setValueAt("NA", i, 7);

                    }

                }
            }

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
    private void export_excel() {
        try {
            long start = System.currentTimeMillis();
            // new WorkbookFactory();
            Workbook wb = new XSSFWorkbook(); //Excell workbook
            Sheet sheet = wb.createSheet(); //WorkSheet
            Row row = sheet.createRow(1); //Row created at line 2
            TableModel model = jTable1.getModel(); //Table model

            int table_rowcount = jTable1.getRowCount();
            jProgressBar1.setMaximum(table_rowcount);
            Row headerRow = sheet.createRow(0); //Create row at line 0
            for (int headings = 0; headings < model.getColumnCount(); headings++) { //For each column
                headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
            }

            for (int rows = 0; rows < model.getRowCount(); rows++) { //For each table row
                for (int cols = 0; cols < jTable1.getColumnCount(); cols++) { //For each table column
                    row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
                    if (cols == 3) {

                        String x = model.getValueAt(rows, cols).toString();
                        Double amount = Double.parseDouble(x);
                        row.createCell(cols).setCellValue(amount);
                    }
                }

                //Set the row to the next one in the sequence 
                row = sheet.createRow((rows + 2));

                jProgressBar1.setValue(rows + 1);
            }
            wb.write(new FileOutputStream("E:\\TEST_Advance.xlsx"));//Save the file
            long duration = System.currentTimeMillis() - start;
            DateFormat df = new SimpleDateFormat("HH 'Hours', mm 'Min(s),' ss 'Second(s)'");
            df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
            System.out.println(df.format(new Date(duration)));
            JOptionPane.showMessageDialog(rootPane, "Process Completed in " + df.format(new Date(duration)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void table_to_ireport() {
//        int nrow = jTable1.getModel().getRowCount();
//        if (nrow == 0) {
//
//        } else {
//            for (int i = 0; nrow > i; i++) {
//                bean_EMP_Advance bds = new bean_EMP_Advance();
//
//                bds.setLoc_code(jTable1.getModel().getValueAt(i, 4).toString());
//                bds.setLoc_name(jTable1.getModel().getValueAt(i, 5).toString());
//                bds.setEmp_code(jTable1.getModel().getValueAt(i, 0).toString());
//                bds.setEmp_name(jTable1.getModel().getValueAt(i, 1).toString());
//                bds.setRank(jTable1.getModel().getValueAt(i, 2).toString());
//                bds.setAmount(Double.parseDouble(jTable1.getModel().getValueAt(i, 3).toString()));
//                bds.setMonth(cmb_month.getSelectedItem().toString());
//                bds.setYear(cmb_year.getSelectedItem().toString());
//
//                if (jTable1.getModel().getValueAt(i, 7) == null) {
//                    bds.setBank_Name("");
//                } else {
//                    bds.setBank_Name(jTable1.getModel().getValueAt(i, 7).toString());
//                }
//
//                bds.setPay_Type(jTable1.getModel().getValueAt(i, 6).toString());
//                bds.setBank_total(Double.parseDouble(lbl_bank.getText()));
//                bds.setCash_total(Double.parseDouble(lbl_cash.getText()));
//                bds.setSlip_total(Double.parseDouble(lbl_slip.getText()));
//                adv.add(bds);
//
//            }
//
//            print();
//            adv.clear();
//        }
//
//    }
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_slip = new javax.swing.JLabel();
        lbl_bank = new javax.swing.JLabel();
        lbl_cash = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton4.setText("Export to Excell");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 180, 50));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Location :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txt_locCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txt_locCode.setText("LocCode");
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 70, 20));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Salary Advance Export");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 40));
        getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 770, 10));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 330, -1));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, -1, -1));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 60, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP", "Name", "Rank", "Amount", "Location", "LocationName", "PayType", "Bank Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 750, 320));

        lbl_slip.setBackground(new java.awt.Color(204, 204, 204));
        lbl_slip.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbl_slip.setText("0.00");
        lbl_slip.setOpaque(true);
        getContentPane().add(lbl_slip, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 460, 60, 20));

        lbl_bank.setBackground(new java.awt.Color(204, 204, 204));
        lbl_bank.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbl_bank.setText("0.00");
        lbl_bank.setOpaque(true);
        getContentPane().add(lbl_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 60, 20));

        lbl_cash.setBackground(new java.awt.Color(204, 204, 204));
        lbl_cash.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbl_cash.setText("0.00");
        lbl_cash.setOpaque(true);
        getContentPane().add(lbl_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 60, 20));
        getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 760, 10));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Pay Month :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("Bank Amount :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, -1, 20));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Employee Count:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Slip Amount :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, 20));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Hand Amount :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 20));

        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 340, 20));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton5.setText("Data To Table");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 180, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {

                export_excel();
                cmb_defLocation.setEditable(true);

            }
        });
        hilo.start();


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

                cmb_defLocation.setEditable(false);
                //                cmb_defLocation.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible


    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        table();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(SAMPATH_Bank_Excell_Export.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SAMPATH_Bank_Excell_Export.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SAMPATH_Bank_Excell_Export.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SAMPATH_Bank_Excell_Export.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SAMPATH_Bank_Excell_Export().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cmb_defLocation;
    public static javax.swing.JComboBox cmb_month;
    public static javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_bank;
    private javax.swing.JLabel lbl_cash;
    private javax.swing.JLabel lbl_slip;
    public static javax.swing.JLabel txt_locCode;
    // End of variables declaration//GEN-END:variables
}
