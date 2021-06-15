/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class RPT_pay_ledger extends javax.swing.JFrame {

    /**
     * Creates new form RPT_pay_ledger
     */
    static ArrayList al;

    public RPT_pay_ledger() {

        al = new ArrayList();

        initComponents();
        get_Location_Details();
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        cmb_loc_type.setVisible(false);
        TitleBar();

    }

    private void TitleBar() {

        this.setTitle("Pay Ledger");

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
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            String path = "Reports\\Pay_Ledger.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void print_Type3_and4() {

        if (CB_Signing.isSelected()) {

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_Sign_Type3_4.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
                //JasperPrintManager.printPage(jp, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_NEW_Type3_4.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
                //JasperPrintManager.printPage(jp, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

      void print_Type5() {

        if (CB_Signing.isSelected()) {

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_Sign_Type3_4.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
                //JasperPrintManager.printPage(jp, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_NEW_Type5.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
                //JasperPrintManager.printPage(jp, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    void print2() {

        if (CB_Signing.isSelected()) {

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_Sign.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
                //JasperPrintManager.printPage(jp, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_NEW.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                JasperViewer.viewReport(jp, false);
                //JasperPrintManager.printPage(jp, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void print_Repeat() {
// 
        if (CB_Signing.isSelected()) {

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_Sign.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                // JasperViewer.viewReport(jp, false);
                JasperPrintManager.printReport(jp, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_NEW.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                //JasperViewer.viewReport(jp, false);
                JasperPrintManager.printReport(jp, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        try {
//           // ArrayList jasperPrintList = 
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            JRPdfExporter exporter = new JRPdfExporter();
//
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//
//            exporter.exportReport();
//
//            inputStreamForStruts2 = new ByteArrayInputStream(outputStream.toByteArray());
//        } catch (Exception e) {
//        }
    }

    void print_Repeat_Type3_and4() {
//
        if (CB_Signing.isSelected()) {

            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_Sign_Type3_4.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                // JasperViewer.viewReport(jp, false);
                JasperPrintManager.printReport(jp, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
                String path = "Reports\\Target_PayLedger_NEW_Type3_4.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(path);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
                //JasperViewer.viewReport(jp, false);
                JasperPrintManager.printReport(jp, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        try {
//           // ArrayList jasperPrintList = 
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            JRPdfExporter exporter = new JRPdfExporter();
//
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//
//            exporter.exportReport();
//
//            inputStreamForStruts2 = new ByteArrayInputStream(outputStream.toByteArray());
//        } catch (Exception e) {
//        }
    }

    void print_sum() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            String path = "Reports\\Target_PayLedger_NEW_SUMMERY.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        cmb_loc_type = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        CB_Signing = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        cb_final = new javax.swing.JCheckBox();
        CB_Signing1 = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cmb_salary_type = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 110, 30));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021" }));
        cmb_year.setSelectedIndex(6);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, 30));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Pay Ledger");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 400, 10));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton3.setText("Pay Ledger");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 180, -1));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 350, -1));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 40, 23));

        jComboBox1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "with R-List", "without R-List", "R-List Only" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 190, 30));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton1.setText("Preview Ledger");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 80, 20));

        cmb_loc_type.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_loc_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ground Staff", "Admin Staff", "Director Staff" }));
        getContentPane().add(cmb_loc_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 40, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton2.setText("Location wise Pay Ledger");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 60, 20));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton4.setText("Pay Ledger Summery");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 390, 40));

        jComboBox2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Express", "Target" }));
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 90, -1));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton5.setText("Print Pay Ledgers");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 210, -1));

        buttonGroup1.add(CB_Signing);
        CB_Signing.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        CB_Signing.setText("Signing");
        getContentPane().add(CB_Signing, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, 30));

        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox2.setText("Standard");
        getContentPane().add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 210, 200));

        jButton6.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        jButton6.setText("Remove");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 210, -1));

        buttonGroup2.add(cb_final);
        cb_final.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cb_final.setText("Final");
        getContentPane().add(cb_final, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, 30));

        buttonGroup2.add(CB_Signing1);
        CB_Signing1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        CB_Signing1.setText("TEMP.");
        getContentPane().add(CB_Signing1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, 30));

        jLabel21.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel21.setText("Salary Month / Year :-");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 30));

        jButton7.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/compare-32.png"))); // NOI18N
        jButton7.setText("Discrepancy");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 190, -1));

        jLabel18.setFont(new java.awt.Font("Georgia", 2, 11)); // NOI18N
        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Single Location Pay Ledgers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 2, 12))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 410, 190));

        cmb_salary_type.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_salary_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Type01", "Type02", "Type03", "Type04", "Type05" }));
        cmb_salary_type.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_salary_typePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_salary_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 100, -1));

        jButton8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton8.setText("Get Locations");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 210, -1));

        jLabel20.setFont(new java.awt.Font("Georgia", 2, 11)); // NOI18N
        jLabel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Print All Locations' Pay Ledgers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 2, 12))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 230, 390));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
        // set_date_range();
    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String month = cmb_month.getSelectedItem().toString();
        String year = cmb_year.getSelectedItem().toString();

        String EMP_Loc_Type = null;
        String Ledger_Type = null;

        Double Sikura_NoPAyDays = null;
        Double Sikura_NoPAyAMT = null;

        String sql = null;

        //***************************SIKURA Employee PayLedger****************************
        if (cmb_loc_type.getSelectedIndex() == 4) {

            Ledger_Type = "SIKURA";

            sql = "select * from salary_final_sikura_staff where Month = '" + month + "' and Year = '" + year + "'   ";

            String epf = null;
            String locCode = null;
            String locName = null;
            String ComName = null;
            String ComAdd = null;
            String ComTel = null;

            try {

                Statement st3 = DbConnection.getconnection().createStatement();
                ResultSet rs3 = st3.executeQuery("select * from company_reg where isDefault = '1'");
                while (rs3.next()) {

                    ComName = rs3.getString("ComName");
                    ComAdd = rs3.getString("ComAddress");
                    ComTel = rs3.getString("ComTel1");
                }

                //emp salary details for selected month
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {

                    epf = rs.getString("EPFno");

                    //get employee's location code
                    Statement st1 = DbConnection.getconnection().createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM employee_reg where EPFno='" + epf + "'");
                    while (rs1.next()) {

                        locCode = rs1.getString("DefLocation");

                        //get employee's location name
                        Statement st2 = DbConnection.getconnection().createStatement();
                        ResultSet rs2 = st2.executeQuery("SELECT * FROM location_reg where LocCode='" + locCode + "'");
                        while (rs2.next()) {

                            locName = rs2.getString("LocName");

                        }

                    }

                    bean_data_salary bds = new bean_data_salary();

                    // bds.setRpt_workDay(rs.getString(1));
                    bds.setRpt_epf(rs.getString(1));
                    bds.setRpt_basic(Double.parseDouble(rs.getString(2)));
                    bds.setRpt_budjet(Double.parseDouble(rs.getString(3)));
                    bds.setRpt_taxEarn(Double.parseDouble(rs.getString(4)));
                    bds.setRpt_OThrs(Double.parseDouble(rs.getString(5)));
                    bds.setRpt_OT(Double.parseDouble(rs.getString(6)));
                    bds.setRpt_attenInsen(Double.parseDouble(rs.getString(7)));
                    bds.setRpt_noOfSunday(Double.parseDouble(rs.getString(8)));
                    bds.setRpt_sunDay(Double.parseDouble(rs.getString(9)));
                    bds.setRpt_poya(Double.parseDouble(rs.getString(10)));
                    bds.setRpt_shiftInten(Double.parseDouble(rs.getString(11)));
                    bds.setRpt_performance(Double.parseDouble(rs.getString(12)));
                    bds.setRpt_other(Double.parseDouble(rs.getString(13)));
                    bds.setRpt_gross(Double.parseDouble(rs.getString(14)));
                    bds.setRpt_welfare(Double.parseDouble(rs.getString(15)));
                    bds.setRpt_adv1(Double.parseDouble(rs.getString(16)));
                    bds.setRpt_adv2(Double.parseDouble(rs.getString(17)));
                    bds.setRpt_festivAdv(Double.parseDouble(rs.getString(18)));
                    bds.setRpt_meals(Double.parseDouble(rs.getString(19)));
                    //bds.setRpt_meals(rs.getString(20));
                    bds.setRpt_noPay(Double.parseDouble(rs.getString(21)));
                    bds.setRpt_rental(Double.parseDouble(rs.getString(22)));
                    bds.setRpt_death(Double.parseDouble(rs.getString(23)));
                    bds.setRpt_otherDeduc(Double.parseDouble(rs.getString(24)));
                    bds.setRpt_insu(Double.parseDouble(rs.getString(25)));
                    bds.setRpt_shoe(Double.parseDouble(rs.getString(26)));
                    bds.setRpt_uni(Double.parseDouble(rs.getString(27)));
                    bds.setRpt_loan1(Double.parseDouble(rs.getString(28)));
                    // bds.setRpt_epf8(rs.getString(29));
                    // bds.setRpt_totDeduc(rs.getString(30));
                    bds.setRpt_epf8(Double.parseDouble(rs.getString(31)));
                    bds.setRpt_totDeduc(Double.parseDouble(rs.getString(32)));
                    bds.setRpt_bankCash(Double.parseDouble(rs.getString(33)));
                    //bds.setRpt_epf(rs.getString(34));
                    //bds.setRpt_epf(rs.getString(35));
                    //bds.setRpt_gross(rs.getString(36));//36-GRND TOTAL
                    bds.setRpt_month(rs.getString(37));
                    bds.setRpt_year(rs.getString(38));
                    // bds.setRpt_epf(rs.getString(39));
                    bds.setRpt_name(rs.getString(40));

                    bds.setRpt_loan2(Double.parseDouble(rs.getString(48)));
                    bds.setRpt_rank(rs.getString(41));
                    bds.setRpt_totShift(Double.parseDouble(rs.getString(42)));

                    bds.setRpt_emp_loc(locCode);
                    bds.setRpt_emp_locName(locName);

                    bds.setRpt_company(ComName);
                    bds.setRpt_address(ComAdd);
                    bds.setRpt_tel(ComTel);
                    bds.setRpt_employee_type(Ledger_Type);

                    bds.setRpt_sikura_noPay_amount(Double.parseDouble(rs.getString(52)));
                    bds.setRpt_sikura_noPay_days(Double.parseDouble(rs.getString(51)));

                    al.add(bds);

                }

                print();
                al.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
//*******************SEleena Employee Pay Ledger***********************************
            if (cmb_loc_type.getSelectedIndex() == 0) {

                EMP_Loc_Type = "Bank";
                Ledger_Type = "Bank Guards";
                Sikura_NoPAyDays = 0.00;
                Sikura_NoPAyAMT = 0.00;

                sql = "select * from salary_final where Month = '" + month + "' and Year = '" + year + "' and LocationType='" + EMP_Loc_Type + "'";

            } else if (cmb_loc_type.getSelectedIndex() == 1) {

                EMP_Loc_Type = "Other";
                Ledger_Type = "Non-Bank Guards";

                sql = "select * from salary_final where Month = '" + month + "' and Year = '" + year + "' and LocationType='" + EMP_Loc_Type + "'";

            } else if (cmb_loc_type.getSelectedIndex() == 2) {
                Ledger_Type = "All Guards";

                sql = "select * from salary_final where Month = '" + month + "' and Year = '" + year + "'   ";

            } else if (cmb_loc_type.getSelectedIndex() == 3) {
                Ledger_Type = "Admin Staff";

                sql = "select * from salary_final_admin_staff where Month = '" + month + "' and Year = '" + year + "' and EmpType='admin'   ";

            } else if (cmb_loc_type.getSelectedIndex() == 5) {
                Ledger_Type = "Director Staff";

                sql = "select * from salary_final_admin_staff where Month = '" + month + "' and Year = '" + year + "' and EmpType='director'   ";

            }

            String epf = null;
            String locCode = null;
            String locName = null;
            String ComName = null;
            String ComAdd = null;
            String ComTel = null;

            try {

                Statement st3 = DbConnection.getconnection().createStatement();
                ResultSet rs3 = st3.executeQuery("select * from company_reg where isDefault = '1'");
                while (rs3.next()) {

                    ComName = rs3.getString("ComName");
                    ComAdd = rs3.getString("ComAddress");
                    ComTel = rs3.getString("ComTel1");
                }

                //emp salary details for selected month
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {

                    epf = rs.getString("EPFno");

                    //get employee's location code
                    Statement st1 = DbConnection.getconnection().createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM employee_reg where EmployeeNo='" + epf + "'");
                    while (rs1.next()) {

                        locCode = rs1.getString("DefLocation");

                        //get employee's location name
                        Statement st2 = DbConnection.getconnection().createStatement();
                        ResultSet rs2 = st2.executeQuery("SELECT * FROM location_reg where LocCode='" + locCode + "'");
                        while (rs2.next()) {

                            locName = rs2.getString("LocName");

                        }

                    }

                    bean_data_salary bds = new bean_data_salary();

                    // bds.setRpt_workDay(rs.getString(1));
                    bds.setRpt_epf(rs.getString(1));
                    bds.setRpt_basic(Double.parseDouble(rs.getString(2)));
                    bds.setRpt_budjet(Double.parseDouble(rs.getString(3)));
                    bds.setRpt_taxEarn(Double.parseDouble(rs.getString(4)));
                    bds.setRpt_OThrs(Double.parseDouble(rs.getString(5)));
                    bds.setRpt_OT(Double.parseDouble(rs.getString(6)));
                    bds.setRpt_attenInsen(Double.parseDouble(rs.getString(7)));
                    bds.setRpt_noOfSunday(Double.parseDouble(rs.getString(8)));
                    bds.setRpt_sunDay(Double.parseDouble(rs.getString(9)));
                    bds.setRpt_poya(Double.parseDouble(rs.getString(10)));
                    bds.setRpt_shiftInten(Double.parseDouble(rs.getString(11)));
                    bds.setRpt_performance(Double.parseDouble(rs.getString(12)));
                    bds.setRpt_other(Double.parseDouble(rs.getString(13)));
                    bds.setRpt_gross(Double.parseDouble(rs.getString(14)));
                    bds.setRpt_welfare(Double.parseDouble(rs.getString(15)));
                    bds.setRpt_adv1(Double.parseDouble(rs.getString(16)));
                    bds.setRpt_adv2(Double.parseDouble(rs.getString(17)));
                    bds.setRpt_festivAdv(Double.parseDouble(rs.getString(18)));
                    bds.setRpt_meals(Double.parseDouble(rs.getString(19)));
                    //bds.setRpt_meals(rs.getString(20));
                    bds.setRpt_noPay(Double.parseDouble(rs.getString(21)));
                    bds.setRpt_rental(Double.parseDouble(rs.getString(22)));
                    bds.setRpt_death(Double.parseDouble(rs.getString(23)));
                    bds.setRpt_otherDeduc(Double.parseDouble(rs.getString(24)));
                    bds.setRpt_insu(Double.parseDouble(rs.getString(25)));
                    bds.setRpt_shoe(Double.parseDouble(rs.getString(26)));
                    bds.setRpt_uni(Double.parseDouble(rs.getString(27)));
                    bds.setRpt_loan1(Double.parseDouble(rs.getString(28)));
                    // bds.setRpt_epf8(rs.getString(29));
                    // bds.setRpt_totDeduc(rs.getString(30));
                    bds.setRpt_epf8(Double.parseDouble(rs.getString(31)));
                    bds.setRpt_totDeduc(Double.parseDouble(rs.getString(32)));
                    bds.setRpt_bankCash(Double.parseDouble(rs.getString(33)));
                    //bds.setRpt_epf(rs.getString(34));
                    //bds.setRpt_epf(rs.getString(35));
                    //bds.setRpt_gross(rs.getString(36));//36-GRND TOTAL
                    bds.setRpt_month(rs.getString(37));
                    bds.setRpt_year(rs.getString(38));
                    // bds.setRpt_epf(rs.getString(39));
                    bds.setRpt_name(rs.getString(40));

                    bds.setRpt_loan2(Double.parseDouble(rs.getString(48)));
                    bds.setRpt_rank(rs.getString(41));
                    bds.setRpt_totShift(Double.parseDouble(rs.getString(42)));

                    bds.setRpt_emp_loc(locCode);
                    bds.setRpt_emp_locName(locName);

                    bds.setRpt_company(ComName);
                    bds.setRpt_address(ComAdd);
                    bds.setRpt_tel(ComTel);
                    bds.setRpt_employee_type(Ledger_Type);

                    bds.setRpt_sikura_noPay_amount(Sikura_NoPAyAMT);
                    bds.setRpt_sikura_noPay_days(Sikura_NoPAyDays);

                    al.add(bds);

                }

                print();
                al.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        RPT_pay_ledger_LocationWise pll = new RPT_pay_ledger_LocationWise();
        pll.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void target_old_pyledger() {
        if (txt_locCode.getText().startsWith("T")) {
            try {

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\Target_PayLedger.jrxml");
                String sql = "select * from salary_final_site_employees join location_reg on salary_final_site_employees.Loc = location_reg.LocCode where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' and salary_final_site_employees.Loc='" + txt_locCode.getText() + "'   order by salary_final_site_employees.EMPno ASC";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {

                Connection conn = (Connection) DbConnection.getconnection();

                JasperDesign jd = JRXmlLoader.load("Reports\\Express_PayLedger.jrxml");
                String sql = "select * from salary_final_site_employees join location_reg on salary_final_site_employees.Loc = location_reg.LocCode where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'and salary_final_site_employees.Loc='" + txt_locCode.getText() + "'  order by salary_final_site_employees.EMPno ASC";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                JasperViewer.viewReport(jp, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            String loc = txt_locCode.getText();

            if (cmb_defLocation.getSelectedIndex() == 0 | loc.isEmpty()) {

            } else {

                String sql = null;
                String sql_bank = null;
                String sql_hand = null;
                String sql_slip = null;
                String ComName = null;
                String Report_name = null;
                String process_type = "TEMP";

                if (loc.startsWith("T")) {
                    ComName = "TARGET ENVIRONMENTAL SERVICES  (PVT) LTD.";
                } else {
                    ComName = "EXPRESS ENVIRONMENTAL SERVICES  (PVT) LTD.";
                }

                if (cb_final.isSelected()) {
                    process_type = "FINAL";
                } else {
                    process_type = "TEMP";

                }

                if (jComboBox1.getSelectedIndex() == 0) { //with RList
                    Report_name = "With R-List" + " - " + process_type;
                    sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";
                    sql_bank = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";
                    sql_hand = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";
                    sql_slip = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";

                } else if (jComboBox1.getSelectedIndex() == 1) { //Without RList
                    Report_name = "Without R-List" + " - " + process_type;
                    sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW' and SalaryType='" + process_type + "'";
                    sql_bank = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW' and SalaryType='" + process_type + "' ";
                    sql_hand = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW' and SalaryType='" + process_type + "' ";
                    sql_slip = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW' and SalaryType='" + process_type + "'  ";

                } else {//RList Only
                    Report_name = "R-List Only" + " - " + process_type;
                    sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'  and SalaryType='" + process_type + "'  ";
                    sql_bank = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'  and SalaryType='" + process_type + "'  ";
                    sql_hand = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'  and SalaryType='" + process_type + "'  ";
                    sql_slip = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'  and SalaryType='" + process_type + "'  ";

                }

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    String emp = rs.getString("EMPno");

                    Double bank_net = 0.00;
                    Double hand_net = 0.00;
                    Double slip_net = 0.00;
                    PreparedStatement pst1 = con.prepareStatement(sql_bank);
                    ResultSet rs1 = pst1.executeQuery();
                    while (rs1.next()) {

                        bank_net = rs1.getDouble("SUM(NetPay)");

                    }

                    PreparedStatement pst2 = con.prepareStatement(sql_hand);
                    ResultSet rs2 = pst2.executeQuery();
                    while (rs2.next()) {

                        hand_net = rs2.getDouble("SUM(NetPay)");

                    }

                    PreparedStatement pst3 = con.prepareStatement(sql_slip);
                    ResultSet rs3 = pst3.executeQuery();
                    while (rs3.next()) {

                        slip_net = rs3.getDouble("SUM(NetPay)");

                    }

                    //***
                    bean_data_salary bds = new bean_data_salary();

                    bds.setRpt_emp(emp);
                    bds.setRpt_epf(rs.getString("EPFno"));
                    bds.setRpt_basic(Double.parseDouble(rs.getString("Basic")));
                    bds.setRpt_budjet(Double.parseDouble(rs.getString("BRA")));
                    bds.setRpt_poya(Double.parseDouble(rs.getString("PoyaAndSunday")));
                    bds.setRpt_taxEarn(Double.parseDouble(rs.getString("SalaryforEPF")));

                    bds.setRpt_OT(Double.parseDouble(rs.getString("OT")));
                    bds.setRpt_attenInsen(Double.parseDouble(rs.getString("AttnIncentive")));

                    bds.setRpt_shiftInten(Double.parseDouble(rs.getString("SiteIncentive")));
                    bds.setRpt_gross(Double.parseDouble(rs.getString("GrossSalary")));
                    bds.setEtf3(Double.parseDouble(rs.getString("ETF3")));
                    bds.setEpf12(Double.parseDouble(rs.getString("EPF12")));
                    bds.setRpt_welfare(Double.parseDouble(rs.getString("Welfare")));
                    bds.setRpt_adv1(Double.parseDouble(rs.getString("Advance")));
                    bds.setRpt_loan1(Double.parseDouble(rs.getString("Loan")));
                    bds.setRpt_otherDeduc(Double.parseDouble(rs.getString("OtherDeductions")));
                    bds.setRpt_uni(Double.parseDouble(rs.getString("Uniform")));

                    bds.setRpt_meals(Double.parseDouble(rs.getString("Meals")));
                    bds.setRpt_epf8(Double.parseDouble(rs.getString("EPF8")));
                    bds.setRpt_totDeduc(Double.parseDouble(rs.getString("TotalDeductions")));
                    bds.setNetpay(Double.parseDouble(rs.getString("NetPay")));
                    bds.setPaytype(rs.getString("PayType"));

                    bds.setRpt_month(rs.getString("Month"));
                    bds.setRpt_year(rs.getString("Year"));

                    bds.setRpt_emp_loc(loc);
                    bds.setRpt_emp_locName(cmb_defLocation.getSelectedItem().toString());
                    bds.setHand(hand_net);
                    bds.setBank(bank_net);
                    bds.setSlip(slip_net);
                    bds.setRpt_rank(rs.getString("Rank"));
                    bds.setRpt_name(rs.getString("Name"));

                    bds.setDay_duty(Double.parseDouble(rs.getString("Day")));
                    bds.setDay2_duty(Double.parseDouble(rs.getString("DayTwoShifts")));
                    bds.setNight_duty(Double.parseDouble(rs.getString("Night")));

                    bds.setRpt_company(ComName);
                    bds.setReportName(Report_name);
                    bds.setAdvance2(Double.parseDouble(rs.getString("Advance2")));
                    bds.setLoan2(Double.parseDouble(rs.getString("Loan2")));
                    bds.setFestival(Double.parseDouble(rs.getString("Festival")));
                    bds.setEpf_duty(Double.parseDouble(rs.getString("EPFDuty")));
                    bds.setStamp(Double.parseDouble(rs.getString("Stamp")));
//                    bds.setRpt_address(ComAdd);
//                    bds.setRpt_tel(ComTel);
//                    bds.setRpt_employee_type(Ledger_Type);
                    al.add(bds);

                    //***
                }

                if (Loc_Type.equals("Type01") | Loc_Type.equals("Type02")) {
                    print2();
                } else  if (Loc_Type.equals("Type03") | Loc_Type.equals("Type04")) {
                    print_Type3_and4();
                }else{
                    print_Type5();
                }

                al.clear();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    public String Loc_Type = "Type01";

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode.setText(" Please Select a Location ");
            txt_locCode.setForeground(Color.red);

        } else {
            txt_locCode.setForeground(Color.black);

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");
                    Loc_Type = rs.getString("Tel3");
                    cmb_defLocation.setSelectedItem(name);
                    txt_locCode.setText(code);
                }

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();
            String loc = txt_locCode.getText();

//            if (cmb_defLocation.getSelectedIndex() == 0 | loc.isEmpty()) {
//
//            } else {
            String sql = null;
            String sql_bank = null;
            String sql_hand = null;
            String sql_slip = null;
            String ComName = null;
            String Report_name = null;
            String process_type = "TEMP";
//                if (loc.startsWith("T")) {
//                    ComName = "TARGET ENVIRONMENTAL SERVICES  (PVT) LTD.";
//                } else {
//                    ComName = "EXPRESS ENVIRONMENTAL SERVICES  (PVT) LTD.";
//                }
            Connection con = DbConnection.getconnection();

            PreparedStatement pstloc = con.prepareStatement("select * from salary_final_site_employees where Month='" + month + "' and Year='" + year + "'  group by Loc");
            ResultSet rsloc = pstloc.executeQuery();
            while (rsloc.next()) {

                loc = rsloc.getString("Loc");

                if (cb_final.isSelected()) {
                    process_type = "FINAL";
                } else {
                    process_type = "TEMP";

                }

                if (jComboBox1.getSelectedIndex() == 0) { //with RList
                    Report_name = "With R-List";
                    sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";
                    sql_bank = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";
                    sql_hand = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "' ";
                    sql_slip = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and SalaryType='" + process_type + "'  ";

                } else if (jComboBox1.getSelectedIndex() == 1) { //Without RList
                    Report_name = "Without R-List";
                    sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'  and SalaryType='" + process_type + "'";
                    sql_bank = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'  and SalaryType='" + process_type + "'";
                    sql_hand = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'  and SalaryType='" + process_type + "'";
                    sql_slip = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'  and SalaryType='" + process_type + "'";

                } else {//RList Only
                    Report_name = "R-List Only";
                    sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   and SalaryType='" + process_type + "' ";
                    sql_bank = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   and SalaryType='" + process_type + "' ";
                    sql_hand = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   and SalaryType='" + process_type + "' ";
                    sql_slip = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   and SalaryType='" + process_type + "' ";

                }
//
//                    PreparedStatement pst = con.prepareStatement(sql);
//                    ResultSet rs = pst.executeQuery();
//                    while (rs.next()) {
//
//                        String emp = rs.getString("EMPno");

                Double bank_net = 0.00;
                Double hand_net = 0.00;
                Double slip_net = 0.00;
                PreparedStatement pst1 = con.prepareStatement(sql_bank);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {

                    bank_net = rs1.getDouble("SUM(NetPay)");

                }

                PreparedStatement pst2 = con.prepareStatement(sql_hand);
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {

                    hand_net = rs2.getDouble("SUM(NetPay)");

                }

                PreparedStatement pst3 = con.prepareStatement(sql_slip);
                ResultSet rs3 = pst3.executeQuery();
                while (rs3.next()) {

                    slip_net = rs3.getDouble("SUM(NetPay)");

                }

                String locname = "";
                PreparedStatement pstn = con.prepareStatement("select * from location_reg where LocCode='" + loc + "'");
                ResultSet rsn = pstn.executeQuery();
                while (rsn.next()) {

                    locname = rsn.getString("LocName");

                }

                //***
                bean_data_salary bds = new bean_data_salary();

//                        bds.setRpt_emp(emp);
//                        bds.setRpt_epf(rs.getString("EPFno"));
//                        bds.setRpt_basic(Double.parseDouble(rs.getString("Basic")));
//                        bds.setRpt_budjet(Double.parseDouble(rs.getString("BRA")));
//                        bds.setRpt_poya(Double.parseDouble(rs.getString("PoyaAndSunday")));
//                        bds.setRpt_taxEarn(Double.parseDouble(rs.getString("SalaryforEPF")));
//
//                        bds.setRpt_OT(Double.parseDouble(rs.getString("OT")));
//                        bds.setRpt_attenInsen(Double.parseDouble(rs.getString("AttnIncentive")));
//
//                        bds.setRpt_shiftInten(Double.parseDouble(rs.getString("SiteIncentive")));
//                        bds.setRpt_gross(Double.parseDouble(rs.getString("GrossSalary")));
//                        bds.setEtf3(Double.parseDouble(rs.getString("ETF3")));
//                        bds.setEpf12(Double.parseDouble(rs.getString("EPF12")));
//                        bds.setRpt_welfare(Double.parseDouble(rs.getString("Welfare")));
//                        bds.setRpt_adv1(Double.parseDouble(rs.getString("Advance")));
//                        bds.setRpt_loan1(Double.parseDouble(rs.getString("Loan")));
//                        bds.setRpt_otherDeduc(Double.parseDouble(rs.getString("OtherDeductions")));
//                        bds.setRpt_uni(Double.parseDouble(rs.getString("Uniform")));
//
//                        bds.setRpt_meals(Double.parseDouble(rs.getString("Meals")));
//                        bds.setRpt_epf8(Double.parseDouble(rs.getString("EPF8")));
//                        bds.setRpt_totDeduc(Double.parseDouble(rs.getString("TotalDeductions")));
//                        bds.setNetpay(Double.parseDouble(rs.getString("NetPay")));
//                        bds.setPaytype(rs.getString("PayType"));
//
                bds.setRpt_month(month);
                bds.setRpt_year(year);
                bds.setRpt_emp_loc(loc);
                bds.setRpt_emp_locName(locname);
                bds.setHand(hand_net);
                bds.setBank(bank_net);
                bds.setSlip(slip_net);
//                        bds.setRpt_rank(rs.getString("Rank"));
//                        bds.setRpt_name(rs.getString("Name"));

//                        bds.setDay_duty(Double.parseDouble(rs.getString("Day")));
//                        bds.setNight_duty(Double.parseDouble(rs.getString("Night")));
//                        bds.setRpt_company(ComName);
                bds.setReportName(Report_name);
//                    bds.setRpt_address(ComAdd);
//                    bds.setRpt_tel(ComTel);
//                    bds.setRpt_employee_type(Ledger_Type);
                al.add(bds);

                //***
//                    }
            }

            print_sum();
            al.clear();

//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {

            int r = jTable1.getRowCount();
            if (r < 0) {

            } else {

                for (int row = 0; row < r; row++) {

                    String loc = jTable1.getValueAt(row, 0).toString();
                    System.out.println(loc);

                    String month = cmb_month.getSelectedItem().toString();
                    String year = cmb_year.getSelectedItem().toString();
                    // String loc = txt_locCode.getText();

                    if (loc.isEmpty()) {

                    } else {

                        String sql = null;
                        String sql_bank = null;
                        String sql_hand = null;
                        String sql_slip = null;
                        String ComName = null;
                        String Report_name = null;
                        if (loc.startsWith("T")) {
                            ComName = "TARGET ENVIRONMENTAL SERVICES  (PVT) LTD.";
                        } else {
                            ComName = "EXPRESS ENVIRONMENTAL SERVICES  (PVT) LTD.";
                        }

                        if (jComboBox1.getSelectedIndex() == 0) { //with RList
                            Report_name = "With R-List";
                            sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' ";
                            sql_bank = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' ";
                            sql_hand = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' ";
                            sql_slip = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' ";

                        } else if (jComboBox1.getSelectedIndex() == 1) { //Without RList
                            Report_name = "Without R-List";
                            sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'";
                            sql_bank = "select *,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'";
                            sql_hand = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'";
                            sql_slip = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='NEW'";

                        } else {//RList Only
                            Report_name = "R-List Only";
                            sql = "select * from salary_final_site_employees where Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   ";
                            sql_bank = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Bank' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   ";
                            sql_hand = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Hand' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   ";
                            sql_slip = "select * ,SUM(NetPay) from salary_final_site_employees where PayType='Slip' and Loc='" + loc + "' and Month='" + month + "' and Year='" + year + "' and Status='RList'   ";

                        }

                        Connection con = DbConnection.getconnection();
                        PreparedStatement pst = con.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {

                            String emp = rs.getString("EMPno");

                            Double bank_net = 0.00;
                            Double hand_net = 0.00;
                            Double slip_net = 0.00;
                            PreparedStatement pst1 = con.prepareStatement(sql_bank);
                            ResultSet rs1 = pst1.executeQuery();
                            while (rs1.next()) {

                                bank_net = rs1.getDouble("SUM(NetPay)");

                            }

                            PreparedStatement pst2 = con.prepareStatement(sql_hand);
                            ResultSet rs2 = pst2.executeQuery();
                            while (rs2.next()) {

                                hand_net = rs2.getDouble("SUM(NetPay)");

                            }

                            PreparedStatement pst3 = con.prepareStatement(sql_slip);
                            ResultSet rs3 = pst3.executeQuery();
                            while (rs3.next()) {

                                slip_net = rs3.getDouble("SUM(NetPay)");

                            }

                            String loc_name = "";

                            PreparedStatement pst12 = con.prepareStatement("select * from location_reg where LocCode='" + loc + "' ");
                            ResultSet rs12 = pst12.executeQuery();

                            while (rs12.next()) {
                                loc_name = rs12.getString("LocName");
                            }

                            //***
                            bean_data_salary bds = new bean_data_salary();

                            bds.setRpt_emp(emp);
                            bds.setRpt_epf(rs.getString("EPFno"));
                            bds.setRpt_basic(Double.parseDouble(rs.getString("Basic")));
                            bds.setRpt_budjet(Double.parseDouble(rs.getString("BRA")));
                            bds.setRpt_poya(Double.parseDouble(rs.getString("PoyaAndSunday")));
                            bds.setRpt_taxEarn(Double.parseDouble(rs.getString("SalaryforEPF")));

                            bds.setRpt_OT(Double.parseDouble(rs.getString("OT")));
                            bds.setRpt_attenInsen(Double.parseDouble(rs.getString("AttnIncentive")));

                            bds.setRpt_shiftInten(Double.parseDouble(rs.getString("SiteIncentive")));
                            bds.setRpt_gross(Double.parseDouble(rs.getString("GrossSalary")));
                            bds.setEtf3(Double.parseDouble(rs.getString("ETF3")));
                            bds.setEpf12(Double.parseDouble(rs.getString("EPF12")));
                            bds.setRpt_welfare(Double.parseDouble(rs.getString("Welfare")));
                            bds.setRpt_adv1(Double.parseDouble(rs.getString("Advance")));
                            bds.setRpt_loan1(Double.parseDouble(rs.getString("Loan")));
                            bds.setRpt_otherDeduc(Double.parseDouble(rs.getString("OtherDeductions")));
                            bds.setRpt_uni(Double.parseDouble(rs.getString("Uniform")));

                            bds.setRpt_meals(Double.parseDouble(rs.getString("Meals")));
                            bds.setRpt_epf8(Double.parseDouble(rs.getString("EPF8")));
                            bds.setRpt_totDeduc(Double.parseDouble(rs.getString("TotalDeductions")));
                            bds.setNetpay(Double.parseDouble(rs.getString("NetPay")));
                            bds.setPaytype(rs.getString("PayType"));

                            bds.setRpt_month(rs.getString("Month"));
                            bds.setRpt_year(rs.getString("Year"));

                            bds.setRpt_emp_loc(loc);
                            bds.setRpt_emp_locName(loc_name);
                            bds.setHand(hand_net);
                            bds.setBank(bank_net);
                            bds.setSlip(slip_net);
                            bds.setRpt_rank(rs.getString("Rank"));
                            bds.setRpt_name(rs.getString("Name"));

                            bds.setDay_duty(Double.parseDouble(rs.getString("Day")));
                            bds.setNight_duty(Double.parseDouble(rs.getString("Night")));

                            bds.setRpt_company(ComName);
                            bds.setReportName(Report_name);
                            bds.setAdvance2(Double.parseDouble(rs.getString("Advance2")));
                            bds.setLoan2(Double.parseDouble(rs.getString("Loan2")));
                            bds.setFestival(Double.parseDouble(rs.getString("Festival")));
                            bds.setEpf_duty(Double.parseDouble(rs.getString("EPFDuty")));
//                    bds.setRpt_address(ComAdd);
//                    bds.setRpt_tel(ComTel);
//                    bds.setRpt_employee_type(Ledger_Type);
                            al.add(bds);
//                            print_Repeat();
                            //***
                        }

                        if (cmb_loc_type.getSelectedIndex() == 0 | cmb_loc_type.getSelectedIndex() == 1) {
                            print_Repeat();
                        } else {
                            print_Repeat_Type3_and4();
                        }

                        al.clear();

                    }

                }

            }
            JOptionPane.showMessageDialog(rootPane, "All Ledgers are sent to the Printer");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
//         try {
//
//            if (jComboBox2.getSelectedIndex() == 0) {
//                Connection con = DbConnection.getconnection();
//                PreparedStatement pst = con.prepareStatement("select * from salary_final_site_employees where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' and Loc like 'E%' group by Loc ");
//                ResultSet rs = pst.executeQuery();
//                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//                dtm.setRowCount(0);
//                while (rs.next()) {
//
//                    Vector v = new Vector();
//                    v.add(rs.getString("Loc"));
//                    dtm.addRow(v);
//
//                }
//            } else {
//                Connection con = DbConnection.getconnection();
//                PreparedStatement pst = con.prepareStatement("select * from salary_final_site_employees where Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' and Loc like 'T%' group by Loc ");
//                ResultSet rs = pst.executeQuery();
//                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//                dtm.setRowCount(0);
//                while (rs.next()) {
//
//                    Vector v = new Vector();
//                    v.add(rs.getString("Loc"));
//                    dtm.addRow(v);
//
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        try {

            if (cmb_defLocation.getSelectedIndex() == 0) {
            } else {

                String month = cmb_month.getSelectedItem().toString();
                String year = cmb_year.getSelectedItem().toString();
                String loc = txt_locCode.getText();

                String EMP = "";
                String EPF = "";
                String Name = "";
                String Rank = "";
                String LocName = "";
                String CompanyAddress = "";
                String Company = "";
                String PayType = "";
                String Report_name = "Discrepancy PayLedger ";

                Double BRA = 0.00;
                Double Basic = 0.00;
                Double PoyaAndSunday = 0.00;
                Double SalaryforEPF = 0.00;
                Double OT = 0.00;
                Double AttnIncentive = 0.00;
                Double SiteIncentive = 0.00;
                Double GrossSalary = 0.00;
                Double ETF3 = 0.00;
                Double EPF12 = 0.00;
                Double AttnAllowance = 0.00;
                Double MachineAllowance = 0.00;
                Double SpecialAllowance = 0.00;
                Double OtherAllowance = 0.00;
                Double Welfare = 0.00;
                Double Advance = 0.00;
                Double Loan = 0.00;
                Double OtherDeductions = 0.00;
                Double Uniform = 0.00;
                Double Meals = 0.00;
                Double EPF8 = 0.00;
                Double TotalDeductions = 0.00;
                Double NetPay = 0.00;
                Double EPFDuty = 0.00;
                Double Loan2 = 0.00;
                Double Advance2 = 0.00;
                Double Festival = 0.00;

                Double TEMP_BRA = 0.00;
                Double TEMP_Basic = 0.00;
                Double TEMP_PoyaAndSunday = 0.00;
                Double TEMP_SalaryforEPF = 0.00;
                Double TEMP_OT = 0.00;
                Double TEMP_AttnIncentive = 0.00;
                Double TEMP_SiteIncentive = 0.00;
                Double TEMP_GrossSalary = 0.00;
                Double TEMP_ETF3 = 0.00;
                Double TEMP_EPF12 = 0.00;
                Double TEMP_AttnAllowance = 0.00;
                Double TEMP_MachineAllowance = 0.00;
                Double TEMP_SpecialAllowance = 0.00;
                Double TEMP_OtherAllowance = 0.00;
                Double TEMP_Welfare = 0.00;
                Double TEMP_Advance = 0.00;
                Double TEMP_Loan = 0.00;
                Double TEMP_OtherDeductions = 0.00;
                Double TEMP_Uniform = 0.00;
                Double TEMP_Meals = 0.00;
                Double TEMP_EPF8 = 0.00;
                Double TEMP_TotalDeductions = 0.00;
                Double TEMP_NetPay = 0.00;
                Double TEMP_EPFDuty = 0.00;
                Double TEMP_Loan2 = 0.00;
                Double TEMP_Advance2 = 0.00;
                Double TEMP_Festival = 0.00;

                Double FINAL_BRA = 0.00;
                Double FINAL_Basic = 0.00;
                Double FINAL_PoyaAndSunday = 0.00;
                Double FINAL_SalaryforEPF = 0.00;
                Double FINAL_OT = 0.00;
                Double FINAL_AttnIncentive = 0.00;
                Double FINAL_SiteIncentive = 0.00;
                Double FINAL_GrossSalary = 0.00;
                Double FINAL_ETF3 = 0.00;
                Double FINAL_EPF12 = 0.00;
                Double FINAL_AttnAllowance = 0.00;
                Double FINAL_MachineAllowance = 0.00;
                Double FINAL_SpecialAllowance = 0.00;
                Double FINAL_OtherAllowance = 0.00;
                Double FINAL_Welfare = 0.00;
                Double FINAL_Advance = 0.00;
                Double FINAL_Loan = 0.00;
                Double FINAL_OtherDeductions = 0.00;
                Double FINAL_Uniform = 0.00;
                Double FINAL_Meals = 0.00;
                Double FINAL_EPF8 = 0.00;
                Double FINAL_TotalDeductions = 0.00;
                Double FINAL_NetPay = 0.00;
                Double FINAL_EPFDuty = 0.00;
                Double FINAL_Loan2 = 0.00;
                Double FINAL_Advance2 = 0.00;
                Double FINAL_Festival = 0.00;

                Double bank_net = 0.00;
                Double hand_net = 0.00;
                Double slip_net = 0.00;

                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement("select * from salary_final_site_employees where Month='" + month + "' and Year ='" + year + "' and SalaryType='TEMP' and Loc = '" + loc + "' Order by EMPno ");
                ResultSet rs_temp = pst.executeQuery();
                while (rs_temp.next()) {

                    TEMP_BRA = rs_temp.getDouble("BRA");
                    TEMP_Basic = rs_temp.getDouble("Basic");
                    TEMP_PoyaAndSunday = rs_temp.getDouble("PoyaAndSunday");
                    TEMP_SalaryforEPF = rs_temp.getDouble("SalaryforEPF");
                    TEMP_OT = rs_temp.getDouble("OT");
                    TEMP_AttnIncentive = rs_temp.getDouble("AttnIncentive");
                    TEMP_SiteIncentive = rs_temp.getDouble("SiteIncentive");
                    TEMP_GrossSalary = rs_temp.getDouble("GrossSalary");
                    TEMP_ETF3 = rs_temp.getDouble("ETF3");
                    TEMP_EPF12 = rs_temp.getDouble("EPF12");
                    TEMP_AttnAllowance = rs_temp.getDouble("AttnAllowance");
                    TEMP_MachineAllowance = rs_temp.getDouble("MachineAllowance");
                    TEMP_SpecialAllowance = rs_temp.getDouble("SpecialAllowance");
                    TEMP_OtherAllowance = rs_temp.getDouble("OtherAllowance");
                    TEMP_Welfare = rs_temp.getDouble("Welfare");
                    TEMP_Advance = rs_temp.getDouble("Advance");
                    TEMP_Loan = rs_temp.getDouble("Loan");
                    TEMP_OtherDeductions = rs_temp.getDouble("OtherDeductions");
                    TEMP_Uniform = rs_temp.getDouble("Uniform");
                    TEMP_Meals = rs_temp.getDouble("Meals");
                    TEMP_EPF8 = rs_temp.getDouble("EPF8");
                    TEMP_TotalDeductions = rs_temp.getDouble("TotalDeductions");
                    TEMP_NetPay = rs_temp.getDouble("NetPay");
                    TEMP_EPFDuty = rs_temp.getDouble("EPFDuty");
                    TEMP_Loan2 = rs_temp.getDouble("Loan2");
                    TEMP_Advance2 = rs_temp.getDouble("Advance2");
                    TEMP_Festival = rs_temp.getDouble("Festival");
                    EMP = rs_temp.getString("EMPno");

                    PreparedStatement pst1 = con.prepareStatement("select * from salary_final_site_employees where Month='" + month + "' and Year ='" + year + "' and SalaryType='FINAL' and EMPno='" + EMP + "' ");
                    ResultSet rs_final = pst1.executeQuery();
                    while (rs_final.next()) {

                        FINAL_BRA = rs_final.getDouble("BRA");
                        FINAL_Basic = rs_final.getDouble("Basic");
                        FINAL_PoyaAndSunday = rs_final.getDouble("PoyaAndSunday");
                        FINAL_SalaryforEPF = rs_final.getDouble("SalaryforEPF");
                        FINAL_OT = rs_final.getDouble("OT");
                        FINAL_AttnIncentive = rs_final.getDouble("AttnIncentive");
                        FINAL_SiteIncentive = rs_final.getDouble("SiteIncentive");
                        FINAL_GrossSalary = rs_final.getDouble("GrossSalary");
                        FINAL_ETF3 = rs_final.getDouble("ETF3");
                        FINAL_EPF12 = rs_final.getDouble("EPF12");
                        FINAL_AttnAllowance = rs_final.getDouble("AttnAllowance");
                        FINAL_MachineAllowance = rs_final.getDouble("MachineAllowance");
                        FINAL_SpecialAllowance = rs_final.getDouble("SpecialAllowance");
                        FINAL_OtherAllowance = rs_final.getDouble("OtherAllowance");
                        FINAL_Welfare = rs_final.getDouble("Welfare");
                        FINAL_Advance = rs_final.getDouble("Advance");
                        FINAL_Loan = rs_final.getDouble("Loan");
                        FINAL_OtherDeductions = rs_final.getDouble("OtherDeductions");
                        FINAL_Uniform = rs_final.getDouble("Uniform");
                        FINAL_Meals = rs_final.getDouble("Meals");
                        FINAL_EPF8 = rs_final.getDouble("EPF8");
                        FINAL_TotalDeductions = rs_final.getDouble("TotalDeductions");
                        FINAL_NetPay = rs_final.getDouble("NetPay");
                        FINAL_EPFDuty = rs_final.getDouble("EPFDuty");
                        FINAL_Loan2 = rs_final.getDouble("Loan2");
                        FINAL_Advance2 = rs_final.getDouble("Advance2");
                        FINAL_Festival = rs_final.getDouble("Festival");

                        EPF = rs_final.getString("EPFno");
                        Name = rs_final.getString("Name");
                        Rank = rs_final.getString("Rank");
                        LocName = rs_final.getString("LocName");
                        CompanyAddress = rs_final.getString("CompanyAddress");
                        Company = rs_final.getString("Company");
                        PayType = rs_final.getString("PayType");

                        // }
                        if (TEMP_NetPay - FINAL_NetPay == 0) {

                        } else {
                            BRA = TEMP_BRA - FINAL_BRA;
                            Basic = TEMP_Basic - FINAL_Basic;
                            PoyaAndSunday = TEMP_PoyaAndSunday - FINAL_PoyaAndSunday;
                            SalaryforEPF = TEMP_SalaryforEPF - FINAL_SalaryforEPF;
                            OT = TEMP_OT - FINAL_OT;
                            AttnIncentive = TEMP_AttnIncentive - FINAL_AttnIncentive;
                            SiteIncentive = TEMP_SiteIncentive - FINAL_SiteIncentive;
                            GrossSalary = TEMP_GrossSalary - FINAL_GrossSalary;
                            ETF3 = TEMP_ETF3 - FINAL_ETF3;
                            EPF12 = TEMP_EPF12 - FINAL_EPF12;
                            AttnAllowance = TEMP_AttnAllowance - FINAL_AttnAllowance;
                            MachineAllowance = TEMP_MachineAllowance - FINAL_MachineAllowance;
                            SpecialAllowance = TEMP_SpecialAllowance - FINAL_SpecialAllowance;
                            OtherAllowance = TEMP_OtherAllowance - FINAL_OtherAllowance;
                            Welfare = TEMP_Welfare - FINAL_Welfare;
                            Advance = TEMP_Advance - FINAL_Advance;
                            Loan = TEMP_Loan - FINAL_Loan;
                            OtherDeductions = TEMP_OtherDeductions - FINAL_OtherDeductions;
                            Uniform = TEMP_Uniform - FINAL_Uniform;
                            Meals = TEMP_Meals - FINAL_Meals;
                            EPF8 = TEMP_EPF8 - FINAL_EPF8;
                            TotalDeductions = TEMP_TotalDeductions - FINAL_TotalDeductions;
                            NetPay = TEMP_NetPay - FINAL_NetPay;
                            EPFDuty = TEMP_EPFDuty - FINAL_EPFDuty;
                            Loan2 = TEMP_Loan2 - FINAL_Loan2;
                            Advance2 = TEMP_Advance2 - FINAL_Advance2;
                            Festival = TEMP_Festival - FINAL_Festival;

                            if (PayType.equals("Hand")) {
                                hand_net += NetPay;

                            }
                            if (PayType.equals("Slip")) {
                                slip_net += NetPay;

                            }
                            if (PayType.equals("Bank")) {
                                bank_net += NetPay;

                            }

                            bean_data_salary bds = new bean_data_salary();

                            bds.setRpt_emp(EMP);
                            bds.setRpt_epf(EPF);
                            bds.setRpt_basic(Basic);
                            bds.setRpt_budjet(BRA);
                            bds.setRpt_poya(PoyaAndSunday);
                            bds.setRpt_taxEarn(SalaryforEPF);

                            bds.setRpt_OT(OT);
                            bds.setRpt_attenInsen(AttnIncentive);

                            bds.setRpt_shiftInten(SiteIncentive);
                            bds.setRpt_gross(GrossSalary);
                            bds.setEtf3(ETF3);
                            bds.setEpf12(EPF12);
                            bds.setRpt_welfare(Welfare);
                            bds.setRpt_adv1(Advance);
                            bds.setRpt_loan1(Loan);
                            bds.setRpt_otherDeduc(OtherDeductions);
                            bds.setRpt_uni(Uniform);

                            bds.setRpt_meals(Meals);
                            bds.setRpt_epf8(EPF8);
                            bds.setRpt_totDeduc(TotalDeductions);
                            bds.setNetpay(NetPay);
                            bds.setPaytype(PayType);

                            bds.setRpt_month(month);
                            bds.setRpt_year(year);

                            bds.setRpt_emp_loc("");
                            bds.setRpt_emp_locName(LocName);
                            bds.setHand(hand_net);
                            bds.setBank(bank_net);
                            bds.setSlip(slip_net);
                            bds.setRpt_rank(Rank);
                            bds.setRpt_name(Name);

                            bds.setDay_duty(Double.parseDouble(rs_final.getString("Day")));
                            bds.setNight_duty(Double.parseDouble(rs_final.getString("Night")));

                            bds.setRpt_company(Company);
                            bds.setReportName(Report_name);
                            bds.setAdvance2(Advance2);
                            bds.setLoan2(Loan2);
                            bds.setFestival(Festival);
                            bds.setEpf_duty(EPFDuty);
                            bds.setStamp(0.00);
//                    bds.setRpt_address(ComAdd);
//                    bds.setRpt_tel(ComTel);
//                    bds.setRpt_employee_type(Ledger_Type);
                            al.add(bds);

                        }

                    }

                }

               if (Loc_Type.equals("Type01") | Loc_Type.equals("Type02")) {
                    print2();
                } else  if (Loc_Type.equals("Type03") | Loc_Type.equals("Type04")) {
                    print_Type3_and4();
                }else{
                    print_Type5();
                }
                al.clear();

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }


    }//GEN-LAST:event_jButton7ActionPerformed

    private void cmb_salary_typePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_salary_typePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_salary_typePopupMenuWillBecomeInvisible

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {

            String type = cmb_salary_type.getSelectedItem().toString();

            if (jComboBox2.getSelectedIndex() == 0) {
                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement("select * from salary_final_site_employees where LocType='" + type + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' and Loc like 'E%' group by Loc ");
                ResultSet rs = pst.executeQuery();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                // dtm.setRowCount(0);
                while (rs.next()) {

                    Vector v = new Vector();
                    v.add(rs.getString("Loc"));
                    dtm.addRow(v);

                }
            } else {
                Connection con = DbConnection.getconnection();
                PreparedStatement pst = con.prepareStatement("select * from salary_final_site_employees where LocType='" + type + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' and Loc like 'T%' group by Loc ");
                ResultSet rs = pst.executeQuery();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                while (rs.next()) {

                    Vector v = new Vector();
                    v.add(rs.getString("Loc"));
                    dtm.addRow(v);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(RPT_pay_ledger.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_pay_ledger.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_pay_ledger.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_pay_ledger.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_pay_ledger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_Signing;
    private javax.swing.JCheckBox CB_Signing1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cb_final;
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_loc_type;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox<String> cmb_salary_type;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_locCode;
    // End of variables declaration//GEN-END:variables
}
