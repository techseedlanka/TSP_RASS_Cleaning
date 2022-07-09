/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
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

/**
 *
 * @author Sapu
 */
public class RPT_atten_summery extends javax.swing.JFrame {

    /**
     * Creates new form RPT_atten_summery
     */
    static ArrayList atten_sum;

    public RPT_atten_summery() {
        initComponents();

        atten_sum = new ArrayList();
        //jButton2.setVisible(false);
//        get_Location_Details();
        TitleBar();
        jTable1.getTableHeader().setUI(null);

        jScrollPane1.setVisible(false);

    }

    private void TitleBar() {

        this.setTitle("Attendance Report");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

//    private void get_Location_Details() {
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("SELECT * from location_reg order by LocName");
//            while (rs.next()) {
//
//                Object name = rs.getString("LocName");
//                //Object code = rs.getString("LocCode");
//
//                //cmb_defLocation.addItem(code);
//                cmb_LocName.addItem(name);
//            }
//
//            AutoCompleteDecorator.decorate(cmb_LocName);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    Double Day = null;
    Double Night = null;
    Double Half = null;
    Double DayNight = null;
    Double OTShift = null;
    Double OThrsperday = null;
    Double total = null;
    Double Day2 = null;

    String d1d = "0";
    String d2d = "0";
    String d3d = "0";
    String d4d = "0";
    String d5d = "0";
    String d6d = "0";
    String d7d = "0";
    String d8d = "0";
    String d9d = "0";
    String d10d = "0";
    String d11d = "0";
    String d12d = "0";
    String d13d = "0";
    String d14d = "0";
    String d15d = "0";
    String d16d = "0";
    String d17d = "0";
    String d18d = "0";
    String d19d = "0";
    String d20d = "0";
    String d21d = "0";
    String d22d = "0";
    String d23d = "0";
    String d24d = "0";
    String d25d = "0";
    String d26d = "0";
    String d27d = "0";
    String d28d = "0";
    String d29d = "0";
    String d30d = "0";
    String d31d = "0";

    String d1dt = "";
    String d2dt = "";
    String d3dt = "";
    String d4dt = "";
    String d5dt = "";
    String d6dt = "";
    String d7dt = "";
    String d8dt = "";
    String d9dt = "";
    String d10dt = "";
    String d11dt = "";
    String d12dt = "";
    String d13dt = "";
    String d14dt = "";
    String d15dt = "";
    String d16dt = "";
    String d17dt = "";
    String d18dt = "";
    String d19dt = "";
    String d20dt = "";
    String d21dt = "";
    String d22dt = "";
    String d23dt = "";
    String d24dt = "";
    String d25dt = "";
    String d26dt = "";
    String d27dt = "";
    String d28dt = "";
    String d29dt = "";
    String d30dt = "";
    String d31dt = "";

    String d1n = "0";
    String d2n = "0";
    String d3n = "0";
    String d4n = "0";
    String d5n = "0";
    String d6n = "0";
    String d7n = "0";
    String d8n = "0";
    String d9n = "0";
    String d10n = "0";
    String d11n = "0";
    String d12n = "0";
    String d13n = "0";
    String d14n = "0";
    String d15n = "0";
    String d16n = "0";
    String d17n = "0";
    String d18n = "0";
    String d19n = "0";
    String d20n = "0";
    String d21n = "0";
    String d22n = "0";
    String d23n = "0";
    String d24n = "0";
    String d25n = "0";
    String d26n = "0";
    String d27n = "0";
    String d28n = "0";
    String d29n = "0";
    String d30n = "0";
    String d31n = "0";

    String d1h = "0";
    String d2h = "0";
    String d3h = "0";
    String d4h = "0";
    String d5h = "0";
    String d6h = "0";
    String d7h = "0";
    String d8h = "0";
    String d9h = "0";
    String d10h = "0";
    String d11h = "0";
    String d12h = "0";
    String d13h = "0";
    String d14h = "0";
    String d15h = "0";
    String d16h = "0";
    String d17h = "0";
    String d18h = "0";
    String d19h = "0";
    String d20h = "0";
    String d21h = "0";
    String d22h = "0";
    String d23h = "0";
    String d24h = "0";
    String d25h = "0";
    String d26h = "0";
    String d27h = "0";
    String d28h = "0";
    String d29h = "0";
    String d30h = "0";
    String d31h = "0.0";

    String d1dn = "";
    String d2dn = "";
    String d3dn = "";
    String d4dn = "";
    String d5dn = "";
    String d6dn = "";
    String d7dn = "";
    String d8dn = "";
    String d9dn = "";
    String d10dn = "";
    String d11dn = "";
    String d12dn = "";
    String d13dn = "";
    String d14dn = "";
    String d15dn = "";
    String d16dn = "";
    String d17dn = "";
    String d18dn = "";
    String d19dn = "";
    String d20dn = "";
    String d21dn = "";
    String d22dn = "";
    String d23dn = "";
    String d24dn = "";
    String d25dn = "";
    String d26dn = "";
    String d27dn = "";
    String d28dn = "";
    String d29dn = "";
    String d30dn = "";
    String d31dn = "0.0";

    String d1ot = "0";
    String d2ot = "0";
    String d3ot = "0";
    String d4ot = "0";
    String d5ot = "0";
    String d6ot = "0";
    String d7ot = "0";
    String d8ot = "0";
    String d9ot = "0";
    String d10ot = "0";
    String d11ot = "0";
    String d12ot = "0";
    String d13ot = "0";
    String d14ot = "0";
    String d15ot = "0";
    String d16ot = "0";
    String d17ot = "0";
    String d18ot = "0";
    String d19ot = "0";
    String d20ot = "0";
    String d21ot = "0";
    String d22ot = "0";
    String d23ot = "0";
    String d24ot = "0";
    String d25ot = "0";
    String d26ot = "0";
    String d27ot = "0";
    String d28ot = "0";
    String d29ot = "0";
    String d30ot = "0";
    String d31ot = "0";

    String d1othr = "0";
    String d2othr = "0";
    String d3othr = "0";
    String d4othr = "0";
    String d5othr = "0";
    String d6othr = "0";
    String d7othr = "0";
    String d8othr = "0";
    String d9othr = "0";
    String d10othr = "0";
    String d11othr = "0";
    String d12othr = "0";
    String d13othr = "0";
    String d14othr = "0";
    String d15othr = "0";
    String d16othr = "0";
    String d17othr = "0";
    String d18othr = "0";
    String d19othr = "0";
    String d20othr = "0";
    String d21othr = "0";
    String d22othr = "0";
    String d23othr = "0";
    String d24othr = "0";
    String d25othr = "0";
    String d26othr = "0";
    String d27othr = "0";
    String d28othr = "0";
    String d29othr = "0";
    String d30othr = "0";
    String d31othr = "0";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rb_shiftSummery = new javax.swing.JRadioButton();
        rb_attendance = new javax.swing.JRadioButton();
        rb_rankWiseShifts = new javax.swing.JRadioButton();
        txt_Locname = new javax.swing.JTextField();
        txt_locCode = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cmb_locType = new javax.swing.JComboBox();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "code", "name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(23);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 40));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 130, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));
        cmb_year.setSelectedIndex(7);
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 100, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_print_file_32px.png"))); // NOI18N
        jButton2.setText("View ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 240, 40));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Location wise Attendance");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 280, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 450, 10));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Month/Year : ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 40));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 450, 10));

        jProgressBar1.setOpaque(true);
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 410, 20));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Report Type : ");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 90, 40));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Location : ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 70, 40));

        buttonGroup1.add(rb_shiftSummery);
        rb_shiftSummery.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        rb_shiftSummery.setText("Shift Summery Report");
        getContentPane().add(rb_shiftSummery, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 160, -1));

        buttonGroup1.add(rb_attendance);
        rb_attendance.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        rb_attendance.setText("Attendance Report");
        rb_attendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_attendanceActionPerformed(evt);
            }
        });
        getContentPane().add(rb_attendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 160, -1));

        buttonGroup1.add(rb_rankWiseShifts);
        rb_rankWiseShifts.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        rb_rankWiseShifts.setText("Rank wise Shift Summery");
        getContentPane().add(rb_rankWiseShifts, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 190, -1));

        txt_Locname.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Locname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_LocnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_LocnameFocusLost(evt);
            }
        });
        txt_Locname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocnameActionPerformed(evt);
            }
        });
        txt_Locname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_LocnameKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Locname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 280, 25));

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
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 50, 25));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Loc. Type : ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, 40));

        cmb_locType.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_locType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=NA=", "All Locations", "Target Locations", "Express Locations", "Diamond Locations" }));
        cmb_locType.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_locTypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_locType, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 330, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
//        int mm = cmb_month.getSelectedIndex();
//        int int_month = mm + 1;
//        String month = String.format("%02d", int_month);
//
//        String year = cmb_year.getSelectedItem().toString();
//        int int_year = Integer.parseInt(year);
//
//        String d1 = (year + "-" + month + "-" + "01");
//        String d2 = (year + "-" + month + "-" + "02");
//        String d3 = (year + "-" + month + "-" + "03");
//        String d4 = (year + "-" + month + "-" + "04");
//        String d5 = (year + "-" + month + "-" + "05");
//        String d6 = (year + "-" + month + "-" + "06");
//        String d7 = (year + "-" + month + "-" + "07");
//        String d8 = (year + "-" + month + "-" + "08");
//        String d9 = (year + "-" + month + "-" + "09");
//        String d10 = (year + "-" + month + "-" + "10");
//        String d11 = (year + "-" + month + "-" + "11");
//        String d12 = (year + "-" + month + "-" + "12");
//        String d13 = (year + "-" + month + "-" + "13");
//        String d14 = (year + "-" + month + "-" + "14");
//        String d15 = (year + "-" + month + "-" + "15");
//        String d16 = (year + "-" + month + "-" + "16");
//        String d17 = (year + "-" + month + "-" + "17");
//        String d18 = (year + "-" + month + "-" + "18");
//        String d19 = (year + "-" + month + "-" + "19");
//        String d20 = (year + "-" + month + "-" + "20");
//        String d21 = (year + "-" + month + "-" + "21");
//        String d22 = (year + "-" + month + "-" + "22");
//        String d23 = (year + "-" + month + "-" + "23");
//        String d24 = (year + "-" + month + "-" + "24");
//        String d25 = (year + "-" + month + "-" + "25");
//        String d26 = (year + "-" + month + "-" + "26");
//        String d27 = (year + "-" + month + "-" + "27");
//        String d28 = (year + "-" + month + "-" + "28");
//        String d29 = (year + "-" + month + "-" + "29");
//        String d30 = (year + "-" + month + "-" + "30");
//        String d31 = (year + "-" + month + "-" + "31");
//
//        String d1d = "0";
//        String d2d = "0";
//        String d3d = "0";
//        String d4d = "0";
//        String d5d = "0";
//        String d6d = "0";
//        String d7d = "0";
//        String d8d = "0";
//        String d9d = "0";
//        String d10d = "0";
//        String d11d = "0";
//        String d12d = "0";
//        String d13d = "0";
//        String d14d = "0";
//        String d15d = "0";
//        String d16d = "0";
//        String d17d = "0";
//        String d18d = "0";
//        String d19d = "0";
//        String d20d = "0";
//        String d21d = "0";
//        String d22d = "0";
//        String d23d = "0";
//        String d24d = "0";
//        String d25d = "0";
//        String d26d = "0";
//        String d27d = "0";
//        String d28d = "0";
//        String d29d = "0";
//        String d30d = "0";
//        String d31d = "0";
//
//        String d1n = "0";
//        String d2n = "0";
//        String d3n = "0";
//        String d4n = "0";
//        String d5n = "0";
//        String d6n = "0";
//        String d7n = "0";
//        String d8n = "0";
//        String d9n = "0";
//        String d10n = "0";
//        String d11n = "0";
//        String d12n = "0";
//        String d13n = "0";
//        String d14n = "0";
//        String d15n = "0";
//        String d16n = "0";
//        String d17n = "0";
//        String d18n = "0";
//        String d19n = "0";
//        String d20n = "0";
//        String d21n = "0";
//        String d22n = "0";
//        String d23n = "0";
//        String d24n = "0";
//        String d25n = "0";
//        String d26n = "0";
//        String d27n = "0";
//        String d28n = "0";
//        String d29n = "0";
//        String d30n = "0";
//        String d31n = "0";
//
//        String d1h = "0";
//        String d2h = "0";
//        String d3h = "0";
//        String d4h = "0";
//        String d5h = "0";
//        String d6h = "0";
//        String d7h = "0";
//        String d8h = "0";
//        String d9h = "0";
//        String d10h = "0";
//        String d11h = "0";
//        String d12h = "0";
//        String d13h = "0";
//        String d14h = "0";
//        String d15h = "0";
//        String d16h = "0";
//        String d17h = "0";
//        String d18h = "0";
//        String d19h = "0";
//        String d20h = "0";
//        String d21h = "0";
//        String d22h = "0";
//        String d23h = "0";
//        String d24h = "0";
//        String d25h = "0";
//        String d26h = "0";
//        String d27h = "0";
//        String d28h = "0";
//        String d29h = "0";
//        String d30h = "0";
//        String d31h = "0";
//
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//
//            ResultSet rs = st.executeQuery("select LocName,Location, Date, SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) from `location_reg`INNER JOIN `emp_atten_main`  ON location_reg.`LocCode` = emp_atten_main.`Location` where Month='February' group by Date");
//
//            //ResultSet rs = st.executeQuery("SELECT Location, Date, SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) FROM `location_reg`INNER JOIN `emp_atten_main`  ON location_reg.`LocCode` = emp_atten_main.`Location`  WHERE MONTH='" + cmb_month.getSelectedItem().toString() + "' GROUP BY Date ");
//            while (rs.next()) {
//
//                String Date = rs.getString("Date");
//
//                if (Date.equals(d1)) {
//
//                    d1d = rs.getString("SUM(DayShift)");
//                    d1n = rs.getString("SUM(NightShift)");
//
//                    String half = rs.getString("SUM(HalfDayShift)");
//                    Double halfday = Double.parseDouble(half);
//
//                    d1h = Double.toString(halfday / 2);
//
//                }
//                if (Date.equals(d2)) {
//
//                    d2d = rs.getString("SUM(DayShift)");
//                    d2n = rs.getString("SUM(NightShift)");
//
//                    String half = rs.getString("SUM(HalfDayShift)");
//                    Double halfday = Double.parseDouble(half);
//
//                    d2h = Double.toString(halfday / 2);
//
//                }
//                if (Date.equals(d3)) {
//
//                    d3d = rs.getString("SUM(DayShift)");
//                    d3n = rs.getString("SUM(NightShift)");
//
//                    String half = rs.getString("SUM(HalfDayShift)");
//                    Double halfday = Double.parseDouble(half);
//
//                    d3h = Double.toString(halfday / 2);
//
//                }
//
//                String LocCode = rs.getString("Location");
//                String LocName = rs.getString("LocName");
//
//                System.out.println(LocCode);
//                System.out.println(LocName);
//
//                bean_ateen_summery bds = new bean_ateen_summery();
//
//                bds.setL_code(LocCode);
//                bds.setL_name(LocName);
//
//                bds.setDay1day(d1d);
//                bds.setDay1night(d1n);
//                bds.setDay1half(d1h);
//
//                bds.setDay2day(d2d);
//                bds.setDay2night(d2n);
//                bds.setDay2half(d2h);
//
//                atten_sum.add(bds);
//            }
//
//            print();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void new_print() {
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                jProgressBar1.setMaximum(100);

                try {
                    jProgressBar1.setMaximum(100);
                    jProgressBar1.setValue(20);

                    String year = cmb_year.getSelectedItem().toString();
                    String month = cmb_month.getSelectedItem().toString();
                    String LocID = txt_locCode.getText();

                    jProgressBar1.setValue(40);

                    Connection conn = (Connection) DbConnection.getconnection();
                    JasperDesign jd = null;
                    String sql = null;

                    switch (cmb_locType.getSelectedIndex()) {
                        case 1:
                            txt_locCode.setText("");
                            jd = JRXmlLoader.load("Reports\\All_Location_atten_Summery.jrxml");
                            sql = "select  Date,Location,Month,Year,SUM(DayShift),SUM(NightShift),SUM(DayTwoShift),SUM(OTShift),SUM(OTHours),SUM(HalfDayShift),LocCode,LocName from `emp_atten_main` join location_reg where Month='" + month + "' and Year='" + year + "' and Location = LocCode group by Date,Location Order by Location";
                            break;
                        case 3:
                            txt_locCode.setText("");
                            jd = JRXmlLoader.load("Reports\\Express_Locations_atten_Summery.jrxml");
                            sql = "select  Date,Location,Month,Year,SUM(DayShift),SUM(NightShift),SUM(DayTwoShift),SUM(OTShift),SUM(OTHours),SUM(HalfDayShift),LocCode,LocName from `emp_atten_main` join location_reg where Location LIKE 'E%' and Month='" + month + "' and Year='" + year + "' and Location = LocCode group by Date,Location Order by Location";
                            break;
                        case 2:
                            txt_locCode.setText("");
                            jd = JRXmlLoader.load("Reports\\Target_Locations_atten_Summery.jrxml");
                            sql = "select  Date,Location,Month,Year,SUM(DayShift),SUM(NightShift),SUM(DayTwoShift),SUM(OTShift),SUM(OTHours),SUM(HalfDayShift),LocCode,LocName from `emp_atten_main` join location_reg where Location LIKE 'T%' and Month='" + month + "' and Year='" + year + "' and Location = LocCode group by Date,Location Order by Location";
                            break;
                        case 4:
                            txt_locCode.setText("");
                            jd = JRXmlLoader.load("Reports\\Diamond_Locations_atten_Summery.jrxml");
                            sql = "select  Date,Location,Month,Year,SUM(DayShift),SUM(NightShift),SUM(DayTwoShift),SUM(OTShift),SUM(OTHours),SUM(HalfDayShift),LocCode,LocName from `emp_atten_main` join location_reg where Location LIKE 'D%' and Month='" + month + "' and Year='" + year + "' and Location = LocCode group by Date,Location Order by Location";
                            break;
                        default:
                            jd = JRXmlLoader.load("Reports\\Location_wise_Attn_Detail.jrxml");
                            sql = "select  emp_atten_main.EPFno,Date,Location,Month,Year,EffectiveRank,SUM(DayShift),SUM(NightShift),SUM(DayTwoShift),SUM(OTShift),SUM(OTHours),SUM(HalfDayShift),NameWithinitials,Designation,LocCode,LocName from `emp_atten_main` join employee_reg join location_reg where Month='" + month + "' AND Year='" + year + "' and Location='" + LocID + "' and emp_atten_main.EPFno = EmployeeNo and Location = LocCode group by Date,emp_atten_main.EPFno Order by emp_atten_main.EPFno";
                            break;
                    }

                    jProgressBar1.setValue(50);
                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(sql);
                    jProgressBar1.setValue(70);
                    jd.setQuery(newQuery);

                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    jProgressBar1.setValue(80);
                    JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
                    jProgressBar1.setValue(90);
                    JasperViewer.viewReport(jp, false);
                    jProgressBar1.setValue(100);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, "Error with Attendance Data. Please re-check & try againg");
                }

            }
        });
        hilo.start();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Thread trd = new Thread(() -> {

            if (rb_attendance.isSelected()) {
                new_print();
            } else if (rb_rankWiseShifts.isSelected()) {

                jProgressBar1.setValue(75);
                if (txt_locCode.getText().equals("NA") | txt_locCode.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please select a Location to view Summery Report");
                } else {

                    HashMap hm = new HashMap();
                    hm.put("month", cmb_month.getSelectedItem().toString());
                    hm.put("year", cmb_year.getSelectedItem().toString());
                    hm.put("location", txt_locCode.getText());
                    String path = "Reports\\rankWiseShiftSummery.jrxml";
                    String title = "Rank Wise Shift Summery";
                    ReportView rv = new ReportView();

                    rv.ReportView(path, hm, title);
                    jProgressBar1.setValue(100);
                }

            } else {
                jProgressBar1.setValue(75);
                if (txt_locCode.getText().equals("NA") | txt_locCode.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please select a Location to view Summery Report");
                } else {

                    HashMap hm = new HashMap();
                    hm.put("month", cmb_month.getSelectedItem().toString());
                    hm.put("year", cmb_year.getSelectedItem().toString());
                    hm.put("location", txt_locCode.getText());
                    String path = "Reports\\shiftRateDetails.jrxml";
                    String title = "Employee Shift Summery";
                    ReportView rv = new ReportView();

                    rv.ReportView(path, hm, title);
                    jProgressBar1.setValue(100);
                }
            }
        });
        trd.start();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void rb_attendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_attendanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_attendanceActionPerformed

    private void txt_LocnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocnameFocusGained
        txt_Locname.setBackground(Color.ORANGE);             // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameFocusGained

    private void txt_LocnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocnameFocusLost
        txt_Locname.setBackground(Color.WHITE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameFocusLost

    private void txt_LocnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocnameActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameActionPerformed

    private void txt_LocnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocnameKeyPressed

    private void txt_LocnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocnameKeyReleased

        if (txt_Locname.getText().isEmpty()) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                jTable1.grabFocus();
            } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            } else {
                try {

                    jTable1.setVisible(true);
                    jScrollPane1.setVisible(true);
                    jScrollPane1.setBounds(100, 145, 340, 200);

                    Connection con = DbConnection.getconnection();

                    String empno = txt_Locname.getText();

                    String sql = "SELECT LocCode,LocName FROM location_reg WHERE  LocCode LIKE ? OR LocName Like? ORDER BY LocCode  ";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, "%" + empno + "%");
                    pst.setString(2, "%" + empno + "%");
                    ResultSet rst = pst.executeQuery();

                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.setRowCount(0);

                    while (rst.next()) {

                        Vector v = new Vector();
                        v.add(rst.getString("LocCode"));
                        v.add(rst.getString("LocName"));

                        dtm.addRow(v);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, e);
                }

            }
        }
    }//GEN-LAST:event_txt_LocnameKeyReleased

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusGained

    private void txt_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusLost

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable1.getSelectedRow();
            String code = jTable1.getValueAt(row, 0).toString();
            String name = jTable1.getValueAt(row, 1).toString();

            txt_Locname.setText(name);
            txt_locCode.setText(code);
            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);
            txt_Locname.grabFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable1.setVisible(false);
            jScrollPane1.setVisible(false);
            txt_Locname.grabFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int row = jTable1.getSelectedRow();
            if (row == 0) {
                txt_Locname.grabFocus();
                jTable1.setVisible(false);
                jScrollPane1.setVisible(false);
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void cmb_locTypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_locTypePopupMenuWillBecomeInvisible
        if (cmb_locType.getSelectedIndex() == 0) {
            txt_Locname.setEnabled(true);
        } else {

            txt_Locname.setEnabled(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_locTypePopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(RPT_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_atten_summery().setVisible(true);
            }
        });
    }

//    private void print_report() {
//
//        int mm = cmb_month.getSelectedIndex();
//        int int_month = mm + 1;
//        String month = String.format("%02d", int_month);
//
//        String year = cmb_year.getSelectedItem().toString();
//        int int_year = Integer.parseInt(year);
//
//        String d1 = (year + "-" + month + "-" + "01");
//        String d2 = (year + "-" + month + "-" + "02");
//        String d3 = (year + "-" + month + "-" + "03");
//        String d4 = (year + "-" + month + "-" + "04");
//        String d5 = (year + "-" + month + "-" + "05");
//        String d6 = (year + "-" + month + "-" + "06");
//        String d7 = (year + "-" + month + "-" + "07");
//        String d8 = (year + "-" + month + "-" + "08");
//        String d9 = (year + "-" + month + "-" + "09");
//        String d10 = (year + "-" + month + "-" + "10");
//        String d11 = (year + "-" + month + "-" + "11");
//        String d12 = (year + "-" + month + "-" + "12");
//        String d13 = (year + "-" + month + "-" + "13");
//        String d14 = (year + "-" + month + "-" + "14");
//        String d15 = (year + "-" + month + "-" + "15");
//        String d16 = (year + "-" + month + "-" + "16");
//        String d17 = (year + "-" + month + "-" + "17");
//        String d18 = (year + "-" + month + "-" + "18");
//        String d19 = (year + "-" + month + "-" + "19");
//        String d20 = (year + "-" + month + "-" + "20");
//        String d21 = (year + "-" + month + "-" + "21");
//        String d22 = (year + "-" + month + "-" + "22");
//        String d23 = (year + "-" + month + "-" + "23");
//        String d24 = (year + "-" + month + "-" + "24");
//        String d25 = (year + "-" + month + "-" + "25");
//        String d26 = (year + "-" + month + "-" + "26");
//        String d27 = (year + "-" + month + "-" + "27");
//        String d28 = (year + "-" + month + "-" + "28");
//        String d29 = (year + "-" + month + "-" + "29");
//        String d30 = (year + "-" + month + "-" + "30");
//        String d31 = (year + "-" + month + "-" + "31");
//
//        String get_month = cmb_month.getSelectedItem().toString();
//        String get_year = cmb_year.getSelectedItem().toString();
//
//        String LocName = null;
//        String LocID = null;
//        String totalShifts = null;
//        String ComName = null;
//        String LocType = null;
//
//        if (cmb_loc_type.getSelectedItem().toString().equals("Bank Only")) {
//            LocType = "Bank";
//        } else {
//
//        }
//
//        if (cmb_loc_type.getSelectedItem().toString().equals("Bank Only")) {
//
//            try {
//
//                Statement st1 = DbConnection.getconnection().createStatement();
//                ResultSet rs1 = st1.executeQuery("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' group by Location");
//                while (rs1.next()) {
//
//                    String LocID1 = rs1.getString("Location");
//
//                    Statement st11 = DbConnection.getconnection().createStatement();
//                    ResultSet rs11 = st11.executeQuery("select * from location_reg where LocCode='" + LocID1 + "' and LocType='Bank'");
//                    while (rs11.next()) {
//
//                        String locCat = rs11.getString("LocType");
//                        LocID = rs11.getString("LocCode");
//
//                        if (locCat.equals("Bank")) {
//
////                            Statement st1 = DbConnection.getconnection().createStatement();
////                            ResultSet rs1 = st1.executeQuery("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' group by Location");
////                            while (rs1.next()) {
////
////                                LocID = rs1.getString("Location");
//                            // }
//                            Statement st = DbConnection.getconnection().createStatement();
//                            ResultSet rs = st.executeQuery(" select * ,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift)  FROM emp_atten_main  where Location='" + LocID + "' group by Date ");
//
//                            while (rs.next()) {
//
//                                String Date = rs.getString("Date");
//
//                                if (Date.equals(d1)) {
//
//                                    d1d = rs.getString("SUM(DayShift)");
//                                    d1n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d1h = Double.toString(halfday / 2);
//
//                                }
//
//                                if (Date.equals(d2)) {
//
//                                    d2d = rs.getString("SUM(DayShift)");
//                                    d2n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d2h = Double.toString(halfday / 2);
//
//                                }
//
//                                if (Date.equals(d3)) {
//
//                                    d3d = rs.getString("SUM(DayShift)");
//                                    d3n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d3h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d4)) {
//
//                                    d4d = rs.getString("SUM(DayShift)");
//                                    d4n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d4h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d5)) {
//
//                                    d5d = rs.getString("SUM(DayShift)");
//                                    d5n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d5h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d6)) {
//
//                                    d6d = rs.getString("SUM(DayShift)");
//                                    d6n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d6h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d7)) {
//
//                                    d7d = rs.getString("SUM(DayShift)");
//                                    d7n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d7h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d8)) {
//
//                                    d8d = rs.getString("SUM(DayShift)");
//                                    d8n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d8h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d9)) {
//
//                                    d9d = rs.getString("SUM(DayShift)");
//                                    d9n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d9h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d10)) {
//
//                                    d10d = rs.getString("SUM(DayShift)");
//                                    d10n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d10h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d11)) {
//
//                                    d11d = rs.getString("SUM(DayShift)");
//                                    d11n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d11h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d12)) {
//
//                                    d12d = rs.getString("SUM(DayShift)");
//                                    d12n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d12h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d13)) {
//
//                                    d13d = rs.getString("SUM(DayShift)");
//                                    d13n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d13h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d14)) {
//
//                                    d14d = rs.getString("SUM(DayShift)");
//                                    d14n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d14h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d15)) {
//
//                                    d15d = rs.getString("SUM(DayShift)");
//                                    d15n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d15h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d16)) {
//
//                                    d16d = rs.getString("SUM(DayShift)");
//                                    d16n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d16h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d17)) {
//
//                                    d17d = rs.getString("SUM(DayShift)");
//                                    d17n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d17h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d18)) {
//
//                                    d18d = rs.getString("SUM(DayShift)");
//                                    d18n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d18h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d19)) {
//
//                                    d19d = rs.getString("SUM(DayShift)");
//                                    d19n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d19h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d20)) {
//
//                                    d20d = rs.getString("SUM(DayShift)");
//                                    d20n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d20h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d21)) {
//
//                                    d21d = rs.getString("SUM(DayShift)");
//                                    d21n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d21h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d22)) {
//
//                                    d22d = rs.getString("SUM(DayShift)");
//                                    d22n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d22h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d23)) {
//
//                                    d23d = rs.getString("SUM(DayShift)");
//                                    d23n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d23h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d24)) {
//
//                                    d24d = rs.getString("SUM(DayShift)");
//                                    d24n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d24h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d25)) {
//
//                                    d25d = rs.getString("SUM(DayShift)");
//                                    d25n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d25h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d26)) {
//
//                                    d26d = rs.getString("SUM(DayShift)");
//                                    d26n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d26h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d27)) {
//
//                                    d27d = rs.getString("SUM(DayShift)");
//                                    d27n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d27h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d28)) {
//
//                                    d28d = rs.getString("SUM(DayShift)");
//                                    d28n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d28h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d29)) {
//
//                                    d29d = rs.getString("SUM(DayShift)");
//                                    d29n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d29h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d30)) {
//
//                                    d30d = rs.getString("SUM(DayShift)");
//                                    d30n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d30h = Double.toString(halfday / 2);
//
//                                }
//                                if (Date.equals(d31)) {
//
//                                    d31d = rs.getString("SUM(DayShift)");
//                                    d31n = rs.getString("SUM(NightShift)");
//
//                                    String half = rs.getString("SUM(HalfDayShift)");
//                                    Double halfday = Double.parseDouble(half);
//
//                                    d31h = Double.toString(halfday / 2);
//
//                                }
//
//                                Statement st4 = DbConnection.getconnection().createStatement();
//                                ResultSet rs4 = st4.executeQuery("select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) from emp_atten_main where Location='" + LocID + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' ");
//                                while (rs4.next()) {
//
//                                    Double day = Double.parseDouble(rs4.getString("SUM(DayShift)"));
//                                    Double night = Double.parseDouble(rs4.getString("SUM(NightShift)"));
//                                    Double half = Double.parseDouble(rs4.getString("SUM(HalfDayShift)"));
//
//                                    total = day + night + (half / 2);
//                                    totalShifts = Double.toString(total);
//
//                                }
//
//                                Statement st3 = DbConnection.getconnection().createStatement();
//                                ResultSet rs3 = st3.executeQuery("select * from location_reg where LocCode='" + LocID + "'");
//                                while (rs3.next()) {
//
//                                    LocName = rs3.getString("LocName");
//
//                                }
//
//                            }
//
//                            Statement st3 = DbConnection.getconnection().createStatement();
//                            ResultSet rs3 = st3.executeQuery("select * from company_reg where isDefault='1'");
//                            while (rs3.next()) {
//
//                                ComName = rs3.getString("ComName");
//
//                            }
//
//                            bean_ateen_summery bds = new bean_ateen_summery();
//                            //LocName = rs.getString("LocName");
//
//                            bds.setL_code(LocID);
//                            bds.setL_name(LocName);
//                            bds.setCom_name(ComName);
//                            bds.setMonth(cmb_month.getSelectedItem().toString());
//                            bds.setYear(cmb_year.getSelectedItem().toString());
//                            bds.setBank_other("Bank Only");
//
//                            bds.setDay1d(d1d);
//                            bds.setDay1n(d1n);
//                            bds.setDay1h(d1h);
//
//                            bds.setDay2d(d2d);
//                            bds.setDay2n(d2n);
//                            bds.setDay2h(d2h);
//
//                            bds.setDay3d(d3d);
//                            bds.setDay3n(d3n);
//                            bds.setDay3h(d3h);
//
//                            bds.setDay4d(d4d);
//                            bds.setDay4n(d4n);
//                            bds.setDay4h(d4h);
//
//                            bds.setDay5d(d5d);
//                            bds.setDay5n(d5n);
//                            bds.setDay5h(d5h);
//
//                            bds.setDay6d(d6d);
//                            bds.setDay6n(d6n);
//                            bds.setDay6h(d6h);
//
//                            bds.setDay7d(d7d);
//                            bds.setDay7n(d7n);
//                            bds.setDay7h(d7h);
//
//                            bds.setDay8d(d8d);
//                            bds.setDay8n(d8n);
//                            bds.setDay8h(d8h);
//
//                            bds.setDay9d(d9d);
//                            bds.setDay9n(d9n);
//                            bds.setDay9h(d9h);
//
//                            bds.setDay10d(d10d);
//                            bds.setDay10n(d10n);
//                            bds.setDay10h(d10h);
//
//                            bds.setDay11d(d11d);
//                            bds.setDay11n(d11n);
//                            bds.setDay11h(d11h);
//
//                            bds.setDay12d(d12d);
//                            bds.setDay12n(d12n);
//                            bds.setDay12h(d12h);
//
//                            bds.setDay13d(d13d);
//                            bds.setDay13n(d13n);
//                            bds.setDay13h(d13h);
//
//                            bds.setDay14d(d14d);
//                            bds.setDay14n(d14n);
//                            bds.setDay14h(d14h);
//
//                            bds.setDay15d(d15d);
//                            bds.setDay15n(d15n);
//                            bds.setDay15h(d15h);
//
//                            bds.setDay16d(d16d);
//                            bds.setDay16n(d16n);
//                            bds.setDay16h(d16h);
//
//                            bds.setDay16d(d16d);
//                            bds.setDay16n(d16n);
//                            bds.setDay16h(d16h);
//
//                            bds.setDay17d(d17d);
//                            bds.setDay17n(d17n);
//                            bds.setDay17h(d17h);
//
//                            bds.setDay18d(d18d);
//                            bds.setDay18n(d18n);
//                            bds.setDay18h(d18h);
//
//                            bds.setDay19d(d19d);
//                            bds.setDay19n(d19n);
//                            bds.setDay19h(d19h);
//
//                            bds.setDay20d(d20d);
//                            bds.setDay20n(d20n);
//                            bds.setDay20h(d20h);
//
//                            bds.setDay21d(d21d);
//                            bds.setDay21n(d21n);
//                            bds.setDay21h(d21h);
//
//                            bds.setDay22d(d22d);
//                            bds.setDay22n(d22n);
//                            bds.setDay22h(d22h);
//
//                            bds.setDay23d(d23d);
//                            bds.setDay23n(d23n);
//                            bds.setDay23h(d23h);
//
//                            bds.setDay24d(d24d);
//                            bds.setDay24n(d24n);
//                            bds.setDay24h(d24h);
//
//                            bds.setDay25d(d25d);
//                            bds.setDay25n(d25n);
//                            bds.setDay25h(d25h);
//
//                            bds.setDay26d(d26d);
//                            bds.setDay26n(d26n);
//                            bds.setDay26h(d26h);
//
//                            bds.setDay27d(d27d);
//                            bds.setDay27n(d27n);
//                            bds.setDay27h(d27h);
//
//                            bds.setDay28d(d28d);
//                            bds.setDay28n(d28n);
//                            bds.setDay28h(d28h);
//
//                            bds.setDay29d(d29d);
//                            bds.setDay29n(d29n);
//                            bds.setDay29h(d29h);
//
//                            bds.setDay30d(d30d);
//                            bds.setDay30n(d30n);
//                            bds.setDay30h(d30h);
//
//                            bds.setDay31d(d31d);
//                            bds.setDay31n(d31n);
//                            bds.setDay31h(d31h);
//
//                            bds.setTotal_shifts(total);
//
//                            clear_ref();
//
//                            atten_sum.add(bds);
//
//                            // }
//                        }
//
//                    }
//
//                }
//                print();
//                atten_sum.clear();
//                clear_ref();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
//
//            try {
//
//                Statement st1 = DbConnection.getconnection().createStatement();
//                ResultSet rs1 = st1.executeQuery("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' group by Location");
//                while (rs1.next()) {
//
//                    LocID = rs1.getString("Location");
//
//                    // }
//                    Statement st = DbConnection.getconnection().createStatement();
//                    ResultSet rs = st.executeQuery(" select * ,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift)  FROM emp_atten_main  where Location='" + LocID + "' group by Date ");
//
//                    while (rs.next()) {
//
//                        String Date = rs.getString("Date");
//
//                        if (Date.equals(d1)) {
//
//                            d1d = rs.getString("SUM(DayShift)");
//                            d1n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d1h = Double.toString(halfday / 2);
//
//                        }
//
//                        if (Date.equals(d2)) {
//
//                            d2d = rs.getString("SUM(DayShift)");
//                            d2n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d2h = Double.toString(halfday / 2);
//
//                        }
//
//                        if (Date.equals(d3)) {
//
//                            d3d = rs.getString("SUM(DayShift)");
//                            d3n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d3h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d4)) {
//
//                            d4d = rs.getString("SUM(DayShift)");
//                            d4n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d4h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d5)) {
//
//                            d5d = rs.getString("SUM(DayShift)");
//                            d5n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d5h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d6)) {
//
//                            d6d = rs.getString("SUM(DayShift)");
//                            d6n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d6h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d7)) {
//
//                            d7d = rs.getString("SUM(DayShift)");
//                            d7n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d7h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d8)) {
//
//                            d8d = rs.getString("SUM(DayShift)");
//                            d8n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d8h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d9)) {
//
//                            d9d = rs.getString("SUM(DayShift)");
//                            d9n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d9h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d10)) {
//
//                            d10d = rs.getString("SUM(DayShift)");
//                            d10n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d10h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d11)) {
//
//                            d11d = rs.getString("SUM(DayShift)");
//                            d11n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d11h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d12)) {
//
//                            d12d = rs.getString("SUM(DayShift)");
//                            d12n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d12h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d13)) {
//
//                            d13d = rs.getString("SUM(DayShift)");
//                            d13n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d13h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d14)) {
//
//                            d14d = rs.getString("SUM(DayShift)");
//                            d14n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d14h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d15)) {
//
//                            d15d = rs.getString("SUM(DayShift)");
//                            d15n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d15h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d16)) {
//
//                            d16d = rs.getString("SUM(DayShift)");
//                            d16n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d16h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d17)) {
//
//                            d17d = rs.getString("SUM(DayShift)");
//                            d17n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d17h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d18)) {
//
//                            d18d = rs.getString("SUM(DayShift)");
//                            d18n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d18h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d19)) {
//
//                            d19d = rs.getString("SUM(DayShift)");
//                            d19n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d19h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d20)) {
//
//                            d20d = rs.getString("SUM(DayShift)");
//                            d20n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d20h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d21)) {
//
//                            d21d = rs.getString("SUM(DayShift)");
//                            d21n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d21h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d22)) {
//
//                            d22d = rs.getString("SUM(DayShift)");
//                            d22n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d22h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d23)) {
//
//                            d23d = rs.getString("SUM(DayShift)");
//                            d23n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d23h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d24)) {
//
//                            d24d = rs.getString("SUM(DayShift)");
//                            d24n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d24h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d25)) {
//
//                            d25d = rs.getString("SUM(DayShift)");
//                            d25n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d25h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d26)) {
//
//                            d26d = rs.getString("SUM(DayShift)");
//                            d26n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d26h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d27)) {
//
//                            d27d = rs.getString("SUM(DayShift)");
//                            d27n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d27h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d28)) {
//
//                            d28d = rs.getString("SUM(DayShift)");
//                            d28n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d28h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d29)) {
//
//                            d29d = rs.getString("SUM(DayShift)");
//                            d29n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d29h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d30)) {
//
//                            d30d = rs.getString("SUM(DayShift)");
//                            d30n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d30h = Double.toString(halfday / 2);
//
//                        }
//                        if (Date.equals(d31)) {
//
//                            d31d = rs.getString("SUM(DayShift)");
//                            d31n = rs.getString("SUM(NightShift)");
//
//                            String half = rs.getString("SUM(HalfDayShift)");
//                            Double halfday = Double.parseDouble(half);
//
//                            d31h = Double.toString(halfday / 2);
//
//                        }
//
//                        Statement st4 = DbConnection.getconnection().createStatement();
//                        ResultSet rs4 = st4.executeQuery("select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift) from emp_atten_main where Location='" + LocID + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' ");
//                        while (rs4.next()) {
//
//                            Double day = Double.parseDouble(rs4.getString("SUM(DayShift)"));
//                            Double night = Double.parseDouble(rs4.getString("SUM(NightShift)"));
//                            Double half = Double.parseDouble(rs4.getString("SUM(HalfDayShift)"));
//
//                            total = day + night + (half / 2);
//                            totalShifts = Double.toString(total);
//
//                        }
//
//                        Statement st3 = DbConnection.getconnection().createStatement();
//                        ResultSet rs3 = st3.executeQuery("select * from location_reg where LocCode='" + LocID + "'");
//                        while (rs3.next()) {
//
//                            LocName = rs3.getString("LocName");
//
//                        }
//
//                    }
//
//                    Statement st3 = DbConnection.getconnection().createStatement();
//                    ResultSet rs3 = st3.executeQuery("select * from company_reg where isDefault='1'");
//                    while (rs3.next()) {
//
//                        ComName = rs3.getString("ComName");
//
//                    }
//
//                    bean_ateen_summery bds = new bean_ateen_summery();
//                    //LocName = rs.getString("LocName");
//
////                    System.out.println(LocID);
////                    System.out.println(LocName);
//                    bds.setL_code(LocID);
//                    bds.setL_name(LocName);
//                    bds.setCom_name(ComName);
//                    bds.setMonth(cmb_month.getSelectedItem().toString());
//                    bds.setYear(cmb_year.getSelectedItem().toString());
//                    bds.setBank_other("All Locations");
//
//                    bds.setDay1d(d1d);
//                    bds.setDay1n(d1n);
//                    bds.setDay1h(d1h);
//
//                    bds.setDay2d(d2d);
//                    bds.setDay2n(d2n);
//                    bds.setDay2h(d2h);
//
//                    bds.setDay3d(d3d);
//                    bds.setDay3n(d3n);
//                    bds.setDay3h(d3h);
//
//                    bds.setDay4d(d4d);
//                    bds.setDay4n(d4n);
//                    bds.setDay4h(d4h);
//
//                    bds.setDay5d(d5d);
//                    bds.setDay5n(d5n);
//                    bds.setDay5h(d5h);
//
//                    bds.setDay6d(d6d);
//                    bds.setDay6n(d6n);
//                    bds.setDay6h(d6h);
//
//                    bds.setDay7d(d7d);
//                    bds.setDay7n(d7n);
//                    bds.setDay7h(d7h);
//
//                    bds.setDay8d(d8d);
//                    bds.setDay8n(d8n);
//                    bds.setDay8h(d8h);
//
//                    bds.setDay9d(d9d);
//                    bds.setDay9n(d9n);
//                    bds.setDay9h(d9h);
//
//                    bds.setDay10d(d10d);
//                    bds.setDay10n(d10n);
//                    bds.setDay10h(d10h);
//
//                    bds.setDay11d(d11d);
//                    bds.setDay11n(d11n);
//                    bds.setDay11h(d11h);
//
//                    bds.setDay12d(d12d);
//                    bds.setDay12n(d12n);
//                    bds.setDay12h(d12h);
//
//                    bds.setDay13d(d13d);
//                    bds.setDay13n(d13n);
//                    bds.setDay13h(d13h);
//
//                    bds.setDay14d(d14d);
//                    bds.setDay14n(d14n);
//                    bds.setDay14h(d14h);
//
//                    bds.setDay15d(d15d);
//                    bds.setDay15n(d15n);
//                    bds.setDay15h(d15h);
//
//                    bds.setDay16d(d16d);
//                    bds.setDay16n(d16n);
//                    bds.setDay16h(d16h);
//
//                    bds.setDay16d(d16d);
//                    bds.setDay16n(d16n);
//                    bds.setDay16h(d16h);
//
//                    bds.setDay17d(d17d);
//                    bds.setDay17n(d17n);
//                    bds.setDay17h(d17h);
//
//                    bds.setDay18d(d18d);
//                    bds.setDay18n(d18n);
//                    bds.setDay18h(d18h);
//
//                    bds.setDay19d(d19d);
//                    bds.setDay19n(d19n);
//                    bds.setDay19h(d19h);
//
//                    bds.setDay20d(d20d);
//                    bds.setDay20n(d20n);
//                    bds.setDay20h(d20h);
//
//                    bds.setDay21d(d21d);
//                    bds.setDay21n(d21n);
//                    bds.setDay21h(d21h);
//
//                    bds.setDay22d(d22d);
//                    bds.setDay22n(d22n);
//                    bds.setDay22h(d22h);
//
//                    bds.setDay23d(d23d);
//                    bds.setDay23n(d23n);
//                    bds.setDay23h(d23h);
//
//                    bds.setDay24d(d24d);
//                    bds.setDay24n(d24n);
//                    bds.setDay24h(d24h);
//
//                    bds.setDay25d(d25d);
//                    bds.setDay25n(d25n);
//                    bds.setDay25h(d25h);
//
//                    bds.setDay26d(d26d);
//                    bds.setDay26n(d26n);
//                    bds.setDay26h(d26h);
//
//                    bds.setDay27d(d27d);
//                    bds.setDay27n(d27n);
//                    bds.setDay27h(d27h);
//
//                    bds.setDay28d(d28d);
//                    bds.setDay28n(d28n);
//                    bds.setDay28h(d28h);
//
//                    bds.setDay29d(d29d);
//                    bds.setDay29n(d29n);
//                    bds.setDay29h(d29h);
//
//                    bds.setDay30d(d30d);
//                    bds.setDay30n(d30n);
//                    bds.setDay30h(d30h);
//
//                    bds.setDay31d(d31d);
//                    bds.setDay31n(d31n);
//                    bds.setDay31h(d31h);
//
//                    bds.setTotal_shifts(total);
//
//                    clear_ref();
//
//                    atten_sum.add(bds);
//
//                }
//
//                print();
//                atten_sum.clear();
//
//                clear_ref();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmb_locType;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rb_attendance;
    private javax.swing.JRadioButton rb_rankWiseShifts;
    private javax.swing.JRadioButton rb_shiftSummery;
    public static javax.swing.JTextField txt_Locname;
    private javax.swing.JTextField txt_locCode;
    // End of variables declaration//GEN-END:variables

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(atten_sum);
            String path = "Reports\\Attendance_Summery.jrxml";//Attendance_Summery_Single_Loc
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void print_SingleLoc() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(atten_sum);
            String path = "Reports\\Attendance_Summery_Single_Loc.jrxml";//Attendance_Summery_Single_Loc
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clear_ref() {

        Half = null;
        Day = null;
        Night = null;
        DayNight = null;
        OTShift = null;
        OThrsperday = null;
        total = null;
        d1d = "";
        d2d = "";
        d3d = "";
        d4d = "";
        d5d = "";
        d6d = "";
        d7d = "";
        d8d = "";
        d9d = "";
        d10d = "";
        d11d = "";
        d12d = "";
        d13d = "";
        d14d = "";
        d15d = "";
        d16d = "";
        d17d = "";
        d18d = "";
        d19d = "";
        d20d = "";
        d21d = "";
        d22d = "";
        d23d = "";
        d24d = "";
        d25d = "";
        d26d = "";
        d27d = "";
        d28d = "";
        d29d = "";
        d30d = "";
        d31d = "";

        d1dt = "";
        d2dt = "";
        d3dt = "";
        d4dt = "";
        d5dt = "";
        d6dt = "";
        d7dt = "";
        d8dt = "";
        d9dt = "";
        d10dt = "";
        d11dt = "";
        d12dt = "";
        d13dt = "";
        d14dt = "";
        d15dt = "";
        d16dt = "";
        d17dt = "";
        d18dt = "";
        d19dt = "";
        d20dt = "";
        d21dt = "";
        d22dt = "";
        d23dt = "";
        d24dt = "";
        d25dt = "";
        d26dt = "";
        d27dt = "";
        d28dt = "";
        d29dt = "";
        d30dt = "";
        d31dt = "";

        d1n = "";
        d2n = "";
        d3n = "";
        d4n = "";
        d5n = "";
        d6n = "";
        d7n = "";
        d8n = "";
        d9n = "";
        d10n = "";
        d11n = "";
        d12n = "";
        d13n = "";
        d14n = "";
        d15n = "";
        d16n = "";
        d17n = "";
        d18n = "";
        d19n = "";
        d20n = "";
        d21n = "";
        d22n = "";
        d23n = "";
        d24n = "";
        d25n = "";
        d26n = "";
        d27n = "";
        d28n = "";
        d29n = "";
        d30n = "";
        d31n = "";

        d1h = "";
        d2h = "";
        d3h = "";
        d4h = "";
        d5h = "";
        d6h = "";
        d7h = "";
        d8h = "";
        d9h = "";
        d10h = "";
        d11h = "";
        d12h = "";
        d13h = "";
        d14h = "";
        d15h = "";
        d16h = "";
        d17h = "";
        d18h = "";
        d19h = "";
        d20h = "";
        d21h = "";
        d22h = "";
        d23h = "";
        d24h = "";
        d25h = "";
        d26h = "";
        d27h = "";
        d28h = "";
        d29h = "";
        d30h = "";
        d31h = "";

        d1dn = "";
        d2dn = "";
        d3dn = "";
        d4dn = "";
        d5dn = "";
        d6dn = "";
        d7dn = "";
        d8dn = "";
        d9dn = "";
        d10dn = "";
        d11dn = "";
        d12dn = "";
        d13dn = "";
        d14dn = "";
        d15dn = "";
        d16dn = "";
        d17dn = "";
        d18dn = "";
        d19dn = "";
        d20dn = "";
        d21dn = "";
        d22dn = "";
        d23dn = "";
        d24dn = "";
        d25dn = "";
        d26dn = "";
        d27dn = "";
        d28dn = "";
        d29dn = "";
        d30dn = "";
        d31dn = "";

        d1ot = "";
        d2ot = "";
        d3ot = "";
        d4ot = "";
        d5ot = "";
        d6ot = "";
        d7ot = "";
        d8ot = "";
        d9ot = "";
        d10ot = "";
        d11ot = "";
        d12ot = "";
        d13ot = "";
        d14ot = "";
        d15ot = "";
        d16ot = "";
        d17ot = "";
        d18ot = "";
        d19ot = "";
        d20ot = "";
        d21ot = "";
        d22ot = "";
        d23ot = "";
        d24ot = "";
        d25ot = "";
        d26ot = "";
        d27ot = "";
        d28ot = "";
        d29ot = "";
        d30ot = "";
        d31ot = "";

        d1othr = "";
        d2othr = "";
        d3othr = "";
        d4othr = "";
        d5othr = "";
        d6othr = "";
        d7othr = "";
        d8othr = "";
        d9othr = "";
        d10othr = "";
        d11othr = "";
        d12othr = "";
        d13othr = "";
        d14othr = "";
        d15othr = "";
        d16othr = "";
        d17othr = "";
        d18othr = "";
        d19othr = "";
        d20othr = "";
        d21othr = "";
        d22othr = "";
        d23othr = "";
        d24othr = "";
        d25othr = "";
        d26othr = "";
        d27othr = "";
        d28othr = "";
        d29othr = "";
        d30othr = "";
        d31othr = "";

    }

//    private void ALL_LOCATION_HARDCODE() {
//        int mm = cmb_month.getSelectedIndex();
//        int int_month = mm + 1;
//        String month = String.format("%02d", int_month);
//
//        String year = cmb_year.getSelectedItem().toString();
//        int int_year = Integer.parseInt(year);
//
//        String d1 = (year + "-" + month + "-" + "01");
//        String d2 = (year + "-" + month + "-" + "02");
//        String d3 = (year + "-" + month + "-" + "03");
//        String d4 = (year + "-" + month + "-" + "04");
//        String d5 = (year + "-" + month + "-" + "05");
//        String d6 = (year + "-" + month + "-" + "06");
//        String d7 = (year + "-" + month + "-" + "07");
//        String d8 = (year + "-" + month + "-" + "08");
//        String d9 = (year + "-" + month + "-" + "09");
//        String d10 = (year + "-" + month + "-" + "10");
//        String d11 = (year + "-" + month + "-" + "11");
//        String d12 = (year + "-" + month + "-" + "12");
//        String d13 = (year + "-" + month + "-" + "13");
//        String d14 = (year + "-" + month + "-" + "14");
//        String d15 = (year + "-" + month + "-" + "15");
//        String d16 = (year + "-" + month + "-" + "16");
//        String d17 = (year + "-" + month + "-" + "17");
//        String d18 = (year + "-" + month + "-" + "18");
//        String d19 = (year + "-" + month + "-" + "19");
//        String d20 = (year + "-" + month + "-" + "20");
//        String d21 = (year + "-" + month + "-" + "21");
//        String d22 = (year + "-" + month + "-" + "22");
//        String d23 = (year + "-" + month + "-" + "23");
//        String d24 = (year + "-" + month + "-" + "24");
//        String d25 = (year + "-" + month + "-" + "25");
//        String d26 = (year + "-" + month + "-" + "26");
//        String d27 = (year + "-" + month + "-" + "27");
//        String d28 = (year + "-" + month + "-" + "28");
//        String d29 = (year + "-" + month + "-" + "29");
//        String d30 = (year + "-" + month + "-" + "30");
//        String d31 = (year + "-" + month + "-" + "31");
//
//        String get_month = cmb_month.getSelectedItem().toString();
//        String get_year = cmb_year.getSelectedItem().toString();
//
//        String LocName = null;
//        String LocID = null;
//        String totalShifts = null;
//        String ComName = null;
//
//        try {
//            String sql = "";
//
//            if (cmb_loc_type.getSelectedItem().toString().equals("All")) {
//                sql = ("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' group by Location");
//                ComName = "Company - ALL";
//            } else if (cmb_loc_type.getSelectedItem().toString().equals("Target")) {
//                ComName = "Company - TARGET";
//                sql = ("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' and Location LIKE 'T%' group by Location");
//            } else if (cmb_loc_type.getSelectedItem().toString().equals("Express")) {
//                //sql = ("select * from emp_atten_main att join location_reg loc on att.Location=loc.LocCode  where loc. Month='" + get_month + "' and Year='" + get_year + "' group by Location");
//                ComName = "Company - EXPRESS";
//                sql = ("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' and Location LIKE 'E%' group by Location");
//
//            }
//
//            Statement st1 = DbConnection.getconnection().createStatement();
//            //ResultSet rs1 = st1.executeQuery("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' group by Location");
//            ResultSet rs1 = st1.executeQuery(sql);
//
//            while (rs1.next()) {
//
//                LocID = rs1.getString("Location");
//
//                // }
//                Statement st = DbConnection.getconnection().createStatement();
//                ResultSet rs = st.executeQuery(" select * ,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift),SUM(OTShift),SUM(DNShift),SUM(OTHours)  FROM emp_atten_main  where Location='" + LocID + "' group by Date ");
//
//                while (rs.next()) {
//
//                    String Date = rs.getString("Date");
//
//                    if (Date.equals(d1)) {
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d1d = rs.getString("SUM(DayShift)");
//                        d1n = rs.getString("SUM(NightShift)");
//                        d1h = Double.toString(halfday / 2);
//                        d1dn = rs.getString("SUM(DNShift)");
//                        d1ot = rs.getString("SUM(OTShift)");
//                        d1othr = rs.getString("SUM(OTHours)");
//
//                        if (d1d.equals("0") | d1d == null) {
//                            d1d = "";
//                        }
//                        if (d1n.equals("0") | d1n == null) {
//                            d1n = "";
//                        }
//                        if (d1h.equals("0.0") | d1h == null) {
//                            d1h = "";
//                        }
//                        if (d1dn.equals("0") | d1dn == null) {
//                            d1dn = "";
//                        }
//                        if (d1ot.equals("0") | d1ot == null) {
//                            d1ot = "";
//                        }
//                        if (d1othr.equals("0") | d1othr == null) {
//                            d1othr = "";
//                        }
//
//                    }
//
//                    if (Date.equals(d2)) {
//
//                        d2d = rs.getString("SUM(DayShift)");
//                        d2n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d2h = Double.toString(halfday / 2);
//                        d2dn = rs.getString("SUM(DNShift)");
//                        d2ot = rs.getString("SUM(OTShift)");
//                        d2othr = rs.getString("SUM(OTHours)");
//
//                        if (d2d.equals("0") | d2d == null) {
//                            d2d = "";
//                        }
//                        if (d2n.equals("0") | d2n == null) {
//                            d2n = "";
//                        }
//                        if (d2h.equals("0.0") | d2h == null) {
//                            d2h = "";
//                        }
//                        if (d2dn.equals("0") | d2dn == null) {
//                            d2dn = "";
//                        }
//                        if (d2ot.equals("0") | d2ot == null) {
//                            d2ot = "";
//                        }
//                        if (d2othr.equals("0") | d2othr == null) {
//                            d2othr = "";
//                        }
//
//                    }
//
//                    if (Date.equals(d3)) {
//
//                        d3d = rs.getString("SUM(DayShift)");
//                        d3n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d3h = Double.toString(halfday / 2);
//                        d3dn = rs.getString("SUM(DNShift)");
//                        d3ot = rs.getString("SUM(OTShift)");
//                        d3othr = rs.getString("SUM(OTHours)");
//
//                        if (d3d.equals("0") | d3d == null) {
//                            d3d = "";
//                        }
//                        if (d3n.equals("0") | d3n == null) {
//                            d3n = "";
//                        }
//                        if (d3h.equals("0.0") | d3h == null) {
//                            d3h = "";
//                        }
//                        if (d3dn.equals("0") | d3dn == null) {
//                            d3dn = "";
//                        }
//                        if (d3ot.equals("0") | d3ot == null) {
//                            d3ot = "";
//                        }
//                        if (d3othr.equals("0") | d3othr == null) {
//                            d3othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d4)) {
//
//                        d4d = rs.getString("SUM(DayShift)");
//                        d4n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d4h = Double.toString(halfday / 2);
//                        d4dn = rs.getString("SUM(DNShift)");
//                        d4ot = rs.getString("SUM(OTShift)");
//                        d4othr = rs.getString("SUM(OTHours)");
//
//                        if (d4d.equals("0") | d4d == null) {
//                            d4d = "";
//                        }
//                        if (d4n.equals("0") | d4n == null) {
//                            d4n = "";
//                        }
//                        if (d4h.equals("0.0") | d4h == null) {
//                            d4h = "";
//                        }
//                        if (d4dn.equals("0") | d4dn == null) {
//                            d4dn = "";
//                        }
//                        if (d4ot.equals("0") | d4ot == null) {
//                            d4ot = "";
//                        }
//                        if (d4othr.equals("0") | d4othr == null) {
//                            d4othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d5)) {
//
//                        d5d = rs.getString("SUM(DayShift)");
//                        d5n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d5h = Double.toString(halfday / 2);
//                        d5dn = rs.getString("SUM(DNShift)");
//                        d5ot = rs.getString("SUM(OTShift)");
//                        d5othr = rs.getString("SUM(OTHours)");
//
//                        if (d5d.equals("0") | d5d == null) {
//                            d5d = "";
//                        }
//                        if (d5n.equals("0") | d5n == null) {
//                            d5n = "";
//                        }
//                        if (d5h.equals("0.0") | d5h == null) {
//                            d5h = "";
//                        }
//                        if (d5dn.equals("0") | d5dn == null) {
//                            d5dn = "";
//                        }
//                        if (d5ot.equals("0") | d5ot == null) {
//                            d5ot = "";
//                        }
//                        if (d5othr.equals("0") | d5othr == null) {
//                            d5othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d6)) {
//
//                        d6d = rs.getString("SUM(DayShift)");
//                        d6n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d6h = Double.toString(halfday / 2);
//                        d6dn = rs.getString("SUM(DNShift)");
//                        d6ot = rs.getString("SUM(OTShift)");
//                        d6othr = rs.getString("SUM(OTHours)");
//                        if (d6d.equals("0") | d6d == null) {
//                            d6d = "";
//                        }
//                        if (d6n.equals("0") | d6n == null) {
//                            d6n = "";
//                        }
//                        if (d6h.equals("0.0") | d6h == null) {
//                            d6h = "";
//                        }
//                        if (d6dn.equals("0") | d6dn == null) {
//                            d6dn = "";
//                        }
//                        if (d6ot.equals("0") | d6ot == null) {
//                            d6ot = "";
//                        }
//                        if (d6othr.equals("0") | d6othr == null) {
//                            d6othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d7)) {
//
//                        d7d = rs.getString("SUM(DayShift)");
//                        d7n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d7h = Double.toString(halfday / 2);
//                        d7dn = rs.getString("SUM(DNShift)");
//                        d7ot = rs.getString("SUM(OTShift)");
//                        d7othr = rs.getString("SUM(OTHours)");
//                        if (d7d.equals("0") | d7d == null) {
//                            d7d = "";
//                        }
//                        if (d7n.equals("0") | d7n == null) {
//                            d7n = "";
//                        }
//                        if (d7h.equals("0.0") | d7h == null) {
//                            d7h = "";
//                        }
//                        if (d7dn.equals("0") | d7dn == null) {
//                            d7dn = "";
//                        }
//                        if (d7ot.equals("0") | d7ot == null) {
//                            d7ot = "";
//                        }
//                        if (d7othr.equals("0") | d7othr == null) {
//                            d7othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d8)) {
//
//                        d8d = rs.getString("SUM(DayShift)");
//                        d8n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d8h = Double.toString(halfday / 2);
//                        d8dn = rs.getString("SUM(DNShift)");
//                        d8ot = rs.getString("SUM(OTShift)");
//                        d8othr = rs.getString("SUM(OTHours)");
//                        if (d8d.equals("0") | d8d == null) {
//                            d8d = "";
//                        }
//                        if (d8n.equals("0") | d8n == null) {
//                            d8n = "";
//                        }
//                        if (d8h.equals("0.0") | d8h == null) {
//                            d8h = "";
//                        }
//                        if (d8dn.equals("0") | d8dn == null) {
//                            d8dn = "";
//                        }
//                        if (d8ot.equals("0") | d8ot == null) {
//                            d8ot = "";
//                        }
//                        if (d8othr.equals("0") | d8othr == null) {
//                            d8othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d9)) {
//
//                        d9d = rs.getString("SUM(DayShift)");
//                        d9n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d9h = Double.toString(halfday / 2);
//                        d9dn = rs.getString("SUM(DNShift)");
//                        d9ot = rs.getString("SUM(OTShift)");
//                        d9othr = rs.getString("SUM(OTHours)");
//                        if (d9d.equals("0") | d9d == null) {
//                            d9d = "";
//                        }
//                        if (d9n.equals("0") | d9n == null) {
//                            d9n = "";
//                        }
//                        if (d9h.equals("0.0") | d9h == null) {
//                            d9h = "";
//                        }
//                        if (d9dn.equals("0") | d9dn == null) {
//                            d9dn = "";
//                        }
//                        if (d9ot.equals("0") | d9ot == null) {
//                            d9ot = "";
//                        }
//                        if (d9othr.equals("0") | d9othr == null) {
//                            d9othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d10)) {
//
//                        d10d = rs.getString("SUM(DayShift)");
//                        d10n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d10h = Double.toString(halfday / 2);
//                        d10dn = rs.getString("SUM(DNShift)");
//                        d10ot = rs.getString("SUM(OTShift)");
//                        d10othr = rs.getString("SUM(OTHours)");
//                        if (d10d.equals("0") | d10d == null) {
//                            d10d = "";
//                        }
//                        if (d10n.equals("0") | d10n == null) {
//                            d10n = "";
//                        }
//                        if (d10h.equals("0.0") | d10h == null) {
//                            d10h = "";
//                        }
//                        if (d10dn.equals("0") | d10dn == null) {
//                            d10dn = "";
//                        }
//                        if (d10ot.equals("0") | d10ot == null) {
//                            d10ot = "";
//                        }
//                        if (d10othr.equals("0") | d10othr == null) {
//                            d10othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d11)) {
//
//                        d11d = rs.getString("SUM(DayShift)");
//                        d11n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d11h = Double.toString(halfday / 2);
//                        d11dn = rs.getString("SUM(DNShift)");
//                        d11ot = rs.getString("SUM(OTShift)");
//                        d11othr = rs.getString("SUM(OTHours)");
//                        if (d11d.equals("0") | d11d == null) {
//                            d11d = "";
//                        }
//                        if (d11n.equals("0") | d11n == null) {
//                            d11n = "";
//                        }
//                        if (d11h.equals("0.0") | d11h == null) {
//                            d11h = "";
//                        }
//                        if (d11dn.equals("0") | d11dn == null) {
//                            d11dn = "";
//                        }
//                        if (d11ot.equals("0") | d11ot == null) {
//                            d11ot = "";
//                        }
//                        if (d11othr.equals("0") | d11othr == null) {
//                            d11othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d12)) {
//
//                        d12d = rs.getString("SUM(DayShift)");
//                        d12n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d12h = Double.toString(halfday / 2);
//                        d12dn = rs.getString("SUM(DNShift)");
//                        d12ot = rs.getString("SUM(OTShift)");
//                        d12othr = rs.getString("SUM(OTHours)");
//                        if (d12d.equals("0") | d12d == null) {
//                            d12d = "";
//                        }
//                        if (d12n.equals("0") | d12n == null) {
//                            d12n = "";
//                        }
//                        if (d12h.equals("0.0") | d12h == null) {
//                            d12h = "";
//                        }
//                        if (d12dn.equals("0") | d12dn == null) {
//                            d12dn = "";
//                        }
//                        if (d12ot.equals("0") | d12ot == null) {
//                            d12ot = "";
//                        }
//                        if (d12othr.equals("0") | d12othr == null) {
//                            d12othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d13)) {
//
//                        d13d = rs.getString("SUM(DayShift)");
//                        d13n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d13h = Double.toString(halfday / 2);
//                        d13dn = rs.getString("SUM(DNShift)");
//                        d13ot = rs.getString("SUM(OTShift)");
//                        d13othr = rs.getString("SUM(OTHours)");
//                        if (d13d.equals("0") | d13d == null) {
//                            d13d = "";
//                        }
//                        if (d13n.equals("0") | d13n == null) {
//                            d13n = "";
//                        }
//                        if (d13h.equals("0.0") | d13h == null) {
//                            d13h = "";
//                        }
//                        if (d13dn.equals("0") | d13dn == null) {
//                            d13dn = "";
//                        }
//                        if (d13ot.equals("0") | d13ot == null) {
//                            d13ot = "";
//                        }
//                        if (d13othr.equals("0") | d13othr == null) {
//                            d13othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d14)) {
//
//                        d14d = rs.getString("SUM(DayShift)");
//                        d14n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d14h = Double.toString(halfday / 2);
//                        d14dn = rs.getString("SUM(DNShift)");
//                        d14ot = rs.getString("SUM(OTShift)");
//                        d14othr = rs.getString("SUM(OTHours)");
//                        if (d14d.equals("0") | d14d == null) {
//                            d14d = "";
//                        }
//                        if (d14n.equals("0") | d14n == null) {
//                            d14n = "";
//                        }
//                        if (d14h.equals("0.0") | d14h == null) {
//                            d14h = "";
//                        }
//                        if (d14dn.equals("0") | d14dn == null) {
//                            d14dn = "";
//                        }
//                        if (d14ot.equals("0") | d14ot == null) {
//                            d14ot = "";
//                        }
//                        if (d14othr.equals("0") | d14othr == null) {
//                            d14othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d15)) {
//
//                        d15d = rs.getString("SUM(DayShift)");
//                        d15n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d15h = Double.toString(halfday / 2);
//                        d15dn = rs.getString("SUM(DNShift)");
//                        d15ot = rs.getString("SUM(OTShift)");
//                        d15othr = rs.getString("SUM(OTHours)");
//                        if (d15d.equals("0") | d15d == null) {
//                            d15d = "";
//                        }
//                        if (d15n.equals("0") | d15n == null) {
//                            d15n = "";
//                        }
//                        if (d15h.equals("0.0") | d15h == null) {
//                            d15h = "";
//                        }
//                        if (d15dn.equals("0") | d15dn == null) {
//                            d15dn = "";
//                        }
//                        if (d15ot.equals("0") | d15ot == null) {
//                            d15ot = "";
//                        }
//                        if (d15othr.equals("0") | d15othr == null) {
//                            d15othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d16)) {
//
//                        d16d = rs.getString("SUM(DayShift)");
//                        d16n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d16h = Double.toString(halfday / 2);
//                        d16dn = rs.getString("SUM(DNShift)");
//                        d16ot = rs.getString("SUM(OTShift)");
//                        d16othr = rs.getString("SUM(OTHours)");
//                        if (d16d.equals("0") | d16d == null) {
//                            d16d = "";
//                        }
//                        if (d16n.equals("0") | d16n == null) {
//                            d16n = "";
//                        }
//                        if (d16h.equals("0.0") | d16h == null) {
//                            d16h = "";
//                        }
//                        if (d16dn.equals("0") | d16dn == null) {
//                            d16dn = "";
//                        }
//                        if (d16ot.equals("0") | d16ot == null) {
//                            d16ot = "";
//                        }
//                        if (d16othr.equals("0") | d16othr == null) {
//                            d16othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d17)) {
//
//                        d17d = rs.getString("SUM(DayShift)");
//                        d17n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d17h = Double.toString(halfday / 2);
//                        d17dn = rs.getString("SUM(DNShift)");
//                        d17ot = rs.getString("SUM(OTShift)");
//                        d17othr = rs.getString("SUM(OTHours)");
//                        if (d17d.equals("0") | d17d == null) {
//                            d17d = "";
//                        }
//                        if (d17n.equals("0") | d17n == null) {
//                            d17n = "";
//                        }
//                        if (d17h.equals("0.0") | d17h == null) {
//                            d17h = "";
//                        }
//                        if (d17dn.equals("0") | d17dn == null) {
//                            d17dn = "";
//                        }
//                        if (d17ot.equals("0") | d17ot == null) {
//                            d17ot = "";
//                        }
//                        if (d17othr.equals("0") | d17othr == null) {
//                            d17othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d18)) {
//
//                        d18d = rs.getString("SUM(DayShift)");
//                        d18n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d18h = Double.toString(halfday / 2);
//                        d18dn = rs.getString("SUM(DNShift)");
//                        d18ot = rs.getString("SUM(OTShift)");
//                        d18othr = rs.getString("SUM(OTHours)");
//                        if (d18d.equals("0") | d18d == null) {
//                            d18d = "";
//                        }
//                        if (d18n.equals("0") | d18n == null) {
//                            d18n = "";
//                        }
//                        if (d18h.equals("0.0") | d18h == null) {
//                            d18h = "";
//                        }
//                        if (d18dn.equals("0") | d18dn == null) {
//                            d18dn = "";
//                        }
//                        if (d18ot.equals("0") | d18ot == null) {
//                            d18ot = "";
//                        }
//                        if (d18othr.equals("0") | d18othr == null) {
//                            d18othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d19)) {
//
//                        d19d = rs.getString("SUM(DayShift)");
//                        d19n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d19h = Double.toString(halfday / 2);
//                        d19dn = rs.getString("SUM(DNShift)");
//                        d19ot = rs.getString("SUM(OTShift)");
//                        d19othr = rs.getString("SUM(OTHours)");
//                        if (d19d.equals("0") | d19d == null) {
//                            d19d = "";
//                        }
//                        if (d19n.equals("0") | d19n == null) {
//                            d19n = "";
//                        }
//                        if (d19h.equals("0.0") | d19h == null) {
//                            d19h = "";
//                        }
//                        if (d19dn.equals("0") | d19dn == null) {
//                            d19dn = "";
//                        }
//                        if (d19ot.equals("0") | d19ot == null) {
//                            d19ot = "";
//                        }
//                        if (d19othr.equals("0") | d19othr == null) {
//                            d19othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d20)) {
//
//                        d20d = rs.getString("SUM(DayShift)");
//                        d20n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d20h = Double.toString(halfday / 2);
//                        d20dn = rs.getString("SUM(DNShift)");
//                        d20ot = rs.getString("SUM(OTShift)");
//                        d20othr = rs.getString("SUM(OTHours)");
//                        if (d20d.equals("0") | d20d == null) {
//                            d20d = "";
//                        }
//                        if (d20n.equals("0") | d20n == null) {
//                            d20n = "";
//                        }
//                        if (d20h.equals("0.0") | d20h == null) {
//                            d20h = "";
//                        }
//                        if (d20dn.equals("0") | d20dn == null) {
//                            d20dn = "";
//                        }
//                        if (d20ot.equals("0") | d20ot == null) {
//                            d20ot = "";
//                        }
//                        if (d20othr.equals("0") | d20othr == null) {
//                            d20othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d21)) {
//
//                        d21d = rs.getString("SUM(DayShift)");
//                        d21n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d21h = Double.toString(halfday / 2);
//                        d21dn = rs.getString("SUM(DNShift)");
//                        d21ot = rs.getString("SUM(OTShift)");
//                        d21othr = rs.getString("SUM(OTHours)");
//                        if (d21d.equals("0") | d21d == null) {
//                            d21d = "";
//                        }
//                        if (d21n.equals("0") | d21n == null) {
//                            d21n = "";
//                        }
//                        if (d21h.equals("0.0") | d21h == null) {
//                            d21h = "";
//                        }
//                        if (d21dn.equals("0") | d21dn == null) {
//                            d21dn = "";
//                        }
//                        if (d21ot.equals("0") | d21ot == null) {
//                            d21ot = "";
//                        }
//                        if (d21othr.equals("0") | d21othr == null) {
//                            d21othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d22)) {
//
//                        d22d = rs.getString("SUM(DayShift)");
//                        d22n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d22h = Double.toString(halfday / 2);
//                        d22dn = rs.getString("SUM(DNShift)");
//                        d22ot = rs.getString("SUM(OTShift)");
//                        d22othr = rs.getString("SUM(OTHours)");
//                        if (d22d.equals("0") | d22d == null) {
//                            d22d = "";
//                        }
//                        if (d22n.equals("0") | d22n == null) {
//                            d22n = "";
//                        }
//                        if (d22h.equals("0.0") | d22h == null) {
//                            d22h = "";
//                        }
//                        if (d22dn.equals("0") | d22dn == null) {
//                            d22dn = "";
//                        }
//                        if (d22ot.equals("0") | d22ot == null) {
//                            d22ot = "";
//                        }
//                        if (d22othr.equals("0") | d22othr == null) {
//                            d22othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d23)) {
//
//                        d23d = rs.getString("SUM(DayShift)");
//                        d23n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d23h = Double.toString(halfday / 2);
//                        d23dn = rs.getString("SUM(DNShift)");
//                        d23ot = rs.getString("SUM(OTShift)");
//                        d23othr = rs.getString("SUM(OTHours)");
//                        if (d23d.equals("0") | d23d == null) {
//                            d23d = "";
//                        }
//                        if (d23n.equals("0") | d23n == null) {
//                            d23n = "";
//                        }
//                        if (d23h.equals("0.0") | d23h == null) {
//                            d23h = "";
//                        }
//                        if (d23dn.equals("0") | d23dn == null) {
//                            d23dn = "";
//                        }
//                        if (d23ot.equals("0") | d23ot == null) {
//                            d23ot = "";
//                        }
//                        if (d23othr.equals("0") | d23othr == null) {
//                            d23othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d24)) {
//
//                        d24d = rs.getString("SUM(DayShift)");
//                        d24n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d24h = Double.toString(halfday / 2);
//                        d24dn = rs.getString("SUM(DNShift)");
//                        d24ot = rs.getString("SUM(OTShift)");
//                        d24othr = rs.getString("SUM(OTHours)");
//                        if (d24d.equals("0") | d24d == null) {
//                            d24d = "";
//                        }
//                        if (d24n.equals("0") | d24n == null) {
//                            d24n = "";
//                        }
//                        if (d24h.equals("0.0") | d24h == null) {
//                            d24h = "";
//                        }
//                        if (d24dn.equals("0") | d24dn == null) {
//                            d24dn = "";
//                        }
//                        if (d24ot.equals("0") | d24ot == null) {
//                            d24ot = "";
//                        }
//                        if (d24othr.equals("0") | d24othr == null) {
//                            d24othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d25)) {
//
//                        d25d = rs.getString("SUM(DayShift)");
//                        d25n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d25h = Double.toString(halfday / 2);
//                        d25dn = rs.getString("SUM(DNShift)");
//                        d25ot = rs.getString("SUM(OTShift)");
//                        d25othr = rs.getString("SUM(OTHours)");
//                        if (d25d.equals("0") | d25d == null) {
//                            d25d = "";
//                        }
//                        if (d25n.equals("0") | d25n == null) {
//                            d25n = "";
//                        }
//                        if (d25h.equals("0.0") | d25h == null) {
//                            d25h = "";
//                        }
//                        if (d25dn.equals("0") | d25dn == null) {
//                            d25dn = "";
//                        }
//                        if (d25ot.equals("0") | d25ot == null) {
//                            d25ot = "";
//                        }
//                        if (d25othr.equals("0") | d25othr == null) {
//                            d25othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d26)) {
//
//                        d26d = rs.getString("SUM(DayShift)");
//                        d26n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d26h = Double.toString(halfday / 2);
//                        d26dn = rs.getString("SUM(DNShift)");
//                        d26ot = rs.getString("SUM(OTShift)");
//                        d26othr = rs.getString("SUM(OTHours)");
//                        if (d26d.equals("0") | d26d == null) {
//                            d26d = "";
//                        }
//                        if (d26n.equals("0") | d26n == null) {
//                            d26n = "";
//                        }
//                        if (d26h.equals("0.0") | d26h == null) {
//                            d26h = "";
//                        }
//                        if (d26dn.equals("0") | d26dn == null) {
//                            d26dn = "";
//                        }
//                        if (d26ot.equals("0") | d26ot == null) {
//                            d26ot = "";
//                        }
//                        if (d26othr.equals("0") | d26othr == null) {
//                            d26othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d27)) {
//
//                        d27d = rs.getString("SUM(DayShift)");
//                        d27n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d27h = Double.toString(halfday / 2);
//                        d27dn = rs.getString("SUM(DNShift)");
//                        d27ot = rs.getString("SUM(OTShift)");
//                        d27othr = rs.getString("SUM(OTHours)");
//                        if (d27d.equals("0") | d27d == null) {
//                            d27d = "";
//                        }
//                        if (d27n.equals("0") | d27n == null) {
//                            d27n = "";
//                        }
//                        if (d27h.equals("0.0") | d27h == null) {
//                            d27h = "";
//                        }
//                        if (d27dn.equals("0") | d27dn == null) {
//                            d27dn = "";
//                        }
//                        if (d27ot.equals("0") | d27ot == null) {
//                            d27ot = "";
//                        }
//                        if (d27othr.equals("0") | d27othr == null) {
//                            d27othr = "";
//                        }
//                    }
//                    if (Date.equals(d28)) {
//
//                        d28d = rs.getString("SUM(DayShift)");
//                        d28n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d28h = Double.toString(halfday / 2);
//                        d28dn = rs.getString("SUM(DNShift)");
//                        d28ot = rs.getString("SUM(OTShift)");
//                        d28othr = rs.getString("SUM(OTHours)");
//                        if (d28d.equals("0") | d28d == null) {
//                            d28d = "";
//                        }
//                        if (d28n.equals("0") | d28n == null) {
//                            d28n = "";
//                        }
//                        if (d28h.equals("0.0") | d28h == null) {
//                            d28h = "";
//                        }
//                        if (d28dn.equals("0") | d28dn == null) {
//                            d28dn = "";
//                        }
//                        if (d28ot.equals("0") | d28ot == null) {
//                            d28ot = "";
//                        }
//                        if (d28othr.equals("0") | d28othr == null) {
//                            d28othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d29)) {
//
//                        d29d = rs.getString("SUM(DayShift)");
//                        d29n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d29h = Double.toString(halfday / 2);
//                        d29dn = rs.getString("SUM(DNShift)");
//                        d29ot = rs.getString("SUM(OTShift)");
//                        d29othr = rs.getString("SUM(OTHours)");
//                        if (d29d.equals("0") | d29d == null) {
//                            d29d = "";
//                        }
//                        if (d29n.equals("0") | d29n == null) {
//                            d29n = "";
//                        }
//                        if (d29h.equals("0.0") | d29h == null) {
//                            d29h = "";
//                        }
//                        if (d29dn.equals("0") | d29dn == null) {
//                            d29dn = "";
//                        }
//                        if (d29ot.equals("0") | d29ot == null) {
//                            d29ot = "";
//                        }
//                        if (d29othr.equals("0") | d29othr == null) {
//                            d29othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d30)) {
//
//                        d30d = rs.getString("SUM(DayShift)");
//                        d30n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d30h = Double.toString(halfday / 2);
//                        d30dn = rs.getString("SUM(DNShift)");
//                        d30ot = rs.getString("SUM(OTShift)");
//                        d30othr = rs.getString("SUM(OTHours)");
//                        if (d30d.equals("0") | d30d == null) {
//                            d30d = "";
//                        }
//                        if (d30n.equals("0") | d30n == null) {
//                            d30n = "";
//                        }
//                        if (d30h.equals("0.0") | d30h == null) {
//                            d30h = "";
//                        }
//                        if (d30dn.equals("0") | d30dn == null) {
//                            d30dn = "";
//                        }
//                        if (d30ot.equals("0") | d30ot == null) {
//                            d30ot = "";
//                        }
//                        if (d30othr.equals("0") | d30othr == null) {
//                            d30othr = "";
//                        }
//
//                    }
//                    if (Date.equals(d31)) {
//
//                        d31d = rs.getString("SUM(DayShift)");
//                        d31n = rs.getString("SUM(NightShift)");
//
//                        String half = rs.getString("SUM(HalfDayShift)");
//                        Double halfday = Double.parseDouble(half);
//
//                        d31h = Double.toString(halfday / 2);
//                        d31dn = rs.getString("SUM(DNShift)");
//                        d31ot = rs.getString("SUM(OTShift)");
//                        d31othr = rs.getString("SUM(OTHours)");
//
//                        if (d31h.equals("0") | d31h == null) {
//                            d31h = "";
//                        }
//                        if (d31d.equals("0") | d31d == null) {
//                            d31d = "";
//                        }
//                        if (d31n.equals("0.0") | d31n == null) {
//                            d31n = "";
//                        }
//                        if (d31dn.equals("0") | d31dn == null) {
//                            d31dn = "";
//                        }
//                        if (d31ot.equals("0") | d31ot == null) {
//                            d31ot = "";
//                        }
//                        if (d31othr.equals("0") | d31othr == null) {
//                            d31othr = "";
//                        }
//
//                    }
//
//                    Statement st4 = DbConnection.getconnection().createStatement();
//                    ResultSet rs4 = st4.executeQuery("select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift),SUM(OTHours),SUM(OTShift),SUM(DNShift) from emp_atten_main where Location='" + LocID + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "' ");
//                    while (rs4.next()) {
//
//                        Double day = Double.parseDouble(rs4.getString("SUM(DayShift)"));
//                        Double night = Double.parseDouble(rs4.getString("SUM(NightShift)"));
//                        Double half = Double.parseDouble(rs4.getString("SUM(HalfDayShift)"));
//                        Double DN = Double.parseDouble(rs4.getString("SUM(DNShift)"));
//                        Double OT = Double.parseDouble(rs4.getString("SUM(OTShift)"));
//                        Double OThrs = Double.parseDouble(rs4.getString("SUM(OTHours)"));
//
//                        total = day + night + (half / 2);
//                        totalShifts = Double.toString(total);
//
//                        Day = day;
//                        Night = night;
//                        DayNight = DN;
//                        OTShift = OT;
//                        OThrsperday = OThrs;
//                        Half = (half / 2);
//
//                    }
//
//                    Statement st3 = DbConnection.getconnection().createStatement();
//                    ResultSet rs3 = st3.executeQuery("select * from location_reg where LocCode='" + LocID + "'");
//                    while (rs3.next()) {
//
//                        LocName = rs3.getString("LocName");
//
//                    }
//
//                }
//
////                Statement st3 = DbConnection.getconnection().createStatement();
////                ResultSet rs3 = st3.executeQuery("select * from company_reg where isDefault='1'");
////                while (rs3.next()) {
////
////                    ComName = rs3.getString("ComName");
////
////                }
//                bean_ateen_summery bds = new bean_ateen_summery();
//                //LocName = rs.getString("LocName");
//
////                    System.out.println(LocID);
////                    System.out.println(LocName);
//                bds.setL_code(LocID);
//                bds.setL_name(LocName);
//                bds.setCom_name(ComName);
//                bds.setMonth(cmb_month.getSelectedItem().toString());
//                bds.setYear(cmb_year.getSelectedItem().toString());
//                bds.setBank_other("All Locations");
//
//                bds.setDay1d(d1d);
//                bds.setDay1n(d1n);
//                bds.setDay1h(d1h);
//                bds.setD1dn(d1dn);
//                bds.setD1ot(d1ot);
//                bds.setD1othr(d1othr);
//
//                bds.setDay2d(d2d);
//                bds.setDay2n(d2n);
//                bds.setDay2h(d2h);
//                bds.setD2dn(d2dn);
//                bds.setD2ot(d2ot);
//                bds.setD2othr(d2othr);
//
//                bds.setDay3d(d3d);
//                bds.setDay3n(d3n);
//                bds.setDay3h(d3h);
//                bds.setD3dn(d3dn);
//                bds.setD3ot(d3ot);
//                bds.setD3othr(d3othr);
//
//                bds.setDay4d(d4d);
//                bds.setDay4n(d4n);
//                bds.setDay4h(d4h);
//                bds.setD4dn(d4dn);
//                bds.setD4ot(d4ot);
//                bds.setD4othr(d4othr);
//
//                bds.setDay5d(d5d);
//                bds.setDay5n(d5n);
//                bds.setDay5h(d5h);
//                bds.setD5dn(d5dn);
//                bds.setD5ot(d5ot);
//                bds.setD5othr(d5othr);
//
//                bds.setDay6d(d6d);
//                bds.setDay6n(d6n);
//                bds.setDay6h(d6h);
//                bds.setD6dn(d6dn);
//                bds.setD6ot(d6ot);
//                bds.setD6othr(d6othr);
//
//                bds.setDay7d(d7d);
//                bds.setDay7n(d7n);
//                bds.setDay7h(d7h);
//                bds.setD7dn(d7dn);
//                bds.setD7ot(d7ot);
//                bds.setD7othr(d7othr);
//
//                bds.setDay8d(d8d);
//                bds.setDay8n(d8n);
//                bds.setDay8h(d8h);
//                bds.setD8dn(d8dn);
//                bds.setD8ot(d8ot);
//                bds.setD8othr(d8othr);
//
//                bds.setDay9d(d9d);
//                bds.setDay9n(d9n);
//                bds.setDay9h(d9h);
//                bds.setD9dn(d9dn);
//                bds.setD9ot(d9ot);
//                bds.setD9othr(d9othr);
//
//                bds.setDay10d(d10d);
//                bds.setDay10n(d10n);
//                bds.setDay10h(d10h);
//                bds.setD10dn(d10dn);
//                bds.setD10ot(d10ot);
//                bds.setD10othr(d10othr);
//
//                bds.setDay11d(d11d);
//                bds.setDay11n(d11n);
//                bds.setDay11h(d11h);
//                bds.setD11dn(d11dn);
//                bds.setD11ot(d11ot);
//                bds.setD11othr(d11othr);
//
//                bds.setDay12d(d12d);
//                bds.setDay12n(d12n);
//                bds.setDay12h(d12h);
//                bds.setD12dn(d12dn);
//                bds.setD12ot(d12ot);
//                bds.setD12othr(d12othr);
//
//                bds.setDay13d(d13d);
//                bds.setDay13n(d13n);
//                bds.setDay13h(d13h);
//                bds.setD13dn(d13dn);
//                bds.setD13ot(d13ot);
//                bds.setD13othr(d13othr);
//
//                bds.setDay14d(d14d);
//                bds.setDay14n(d14n);
//                bds.setDay14h(d14h);
//                bds.setD14dn(d14dn);
//                bds.setD14ot(d14ot);
//                bds.setD14othr(d14othr);
//
//                bds.setDay15d(d15d);
//                bds.setDay15n(d15n);
//                bds.setDay15h(d15h);
//                bds.setD5dn(d15dn);
//                bds.setD15ot(d15ot);
//                bds.setD15othr(d15othr);
//
//                bds.setDay16d(d16d);
//                bds.setDay16n(d16n);
//                bds.setDay16h(d16h);
//                bds.setD16dn(d16dn);
//                bds.setD16ot(d16ot);
//                bds.setD16othr(d16othr);
//
//                bds.setDay17d(d17d);
//                bds.setDay17n(d17n);
//                bds.setDay17h(d17h);
//                bds.setD17dn(d17dn);
//                bds.setD17ot(d17ot);
//                bds.setD17othr(d17othr);
//
//                bds.setDay18d(d18d);
//                bds.setDay18n(d18n);
//                bds.setDay18h(d18h);
//                bds.setD18dn(d18dn);
//                bds.setD18ot(d18ot);
//                bds.setD18othr(d18othr);
//
//                bds.setDay19d(d19d);
//                bds.setDay19n(d19n);
//                bds.setDay19h(d19h);
//                bds.setD19dn(d19dn);
//                bds.setD19ot(d19ot);
//                bds.setD19othr(d19othr);
//
//                bds.setDay20d(d20d);
//                bds.setDay20n(d20n);
//                bds.setDay20h(d20h);
//                bds.setD20dn(d20dn);
//                bds.setD20ot(d20ot);
//                bds.setD20othr(d20othr);
//
//                bds.setDay21d(d21d);
//                bds.setDay21n(d21n);
//                bds.setDay21h(d21h);
//                bds.setD21dn(d21dn);
//                bds.setD21ot(d21ot);
//                bds.setD21othr(d21othr);
//
//                bds.setDay22d(d22d);
//                bds.setDay22n(d22n);
//                bds.setDay22h(d22h);
//                bds.setD22dn(d22dn);
//                bds.setD22ot(d22ot);
//                bds.setD22othr(d22othr);
//
//                bds.setDay23d(d23d);
//                bds.setDay23n(d23n);
//                bds.setDay23h(d23h);
//                bds.setD23dn(d23dn);
//                bds.setD23ot(d23ot);
//                bds.setD23othr(d23othr);
//
//                bds.setDay24d(d24d);
//                bds.setDay24n(d24n);
//                bds.setDay24h(d24h);
//                bds.setD24dn(d24dn);
//                bds.setD24ot(d24ot);
//                bds.setD24othr(d24othr);
//
//                bds.setDay25d(d25d);
//                bds.setDay25n(d25n);
//                bds.setDay25h(d25h);
//                bds.setD25dn(d25dn);
//                bds.setD25ot(d25ot);
//                bds.setD25othr(d25othr);
//
//                bds.setDay26d(d26d);
//                bds.setDay26n(d26n);
//                bds.setDay26h(d26h);
//                bds.setD26dn(d26dn);
//                bds.setD26ot(d26ot);
//                bds.setD26othr(d26othr);
//
//                bds.setDay27d(d27d);
//                bds.setDay27n(d27n);
//                bds.setDay27h(d27h);
//                bds.setD27dn(d27dn);
//                bds.setD27ot(d27ot);
//                bds.setD27othr(d27othr);
//
//                bds.setDay28d(d28d);
//                bds.setDay28n(d28n);
//                bds.setDay28h(d28h);
//                bds.setD28dn(d28dn);
//                bds.setD28ot(d28ot);
//                bds.setD28othr(d28othr);
//
//                bds.setDay29d(d29d);
//                bds.setDay29n(d29n);
//                bds.setDay29h(d29h);
//                bds.setD29dn(d29dn);
//                bds.setD29ot(d29ot);
//                bds.setD29othr(d29othr);
//
//                bds.setDay30d(d30d);
//                bds.setDay30n(d30n);
//                bds.setDay30h(d30h);
//                bds.setD30dn(d30dn);
//                bds.setD30ot(d30ot);
//                bds.setD30othr(d30othr);
//
//                bds.setDay31d(d31d);
//                bds.setDay31n(d31n);
//                bds.setDay31h(d31h);
//                bds.setD31dn(d31dn);
//                bds.setD31ot(d31ot);
//                bds.setD31othr(d31othr);
//
//                bds.setTotal_shifts(total);
//                bds.setDn(DayNight);
//                bds.setOt(OTShift);
//                bds.setDay(Day);
//                bds.setNight(Night);
//                bds.setHalf(Half);
//                bds.setOthrs(OThrsperday);
//
//                clear_ref();
//
//                atten_sum.add(bds);
//
//            }
//
//            print();
//            atten_sum.clear();
//
//            clear_ref();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
