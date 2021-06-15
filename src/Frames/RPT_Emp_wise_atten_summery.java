/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sapu
 */
public class RPT_Emp_wise_atten_summery extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Emp_wise_atten_summery
     */
    static ArrayList emp_atten_sum;

    public RPT_Emp_wise_atten_summery() {
        initComponents();
        auto_completer();
        emp_atten_sum = new ArrayList();
        TitleBar();
    }
    
    
      private void TitleBar() {

        this.setTitle("Employee Attendance Report");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }


    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0    ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);

            while (rs.next()) {
                String code = rs.getString("EPFno");
                // String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");

                ta.addItem(code);
                ta.addItem(NameWithInitials);
                //ta.addItem(nic);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String d1d = "";
    String d2d = "";
    String d3d = "";
    String d4d = "";
    String d5d = "";
    String d6d = "";
    String d7d = "";
    String d8d = "";
    String d9d = "";
    String d10d = "";
    String d11d = "";
    String d12d = "";
    String d13d = "";
    String d14d = "";
    String d15d = "";
    String d16d = "";
    String d17d = "";
    String d18d = "";
    String d19d = "";
    String d20d = "";
    String d21d = "";
    String d22d = "";
    String d23d = "";
    String d24d = "";
    String d25d = "";
    String d26d = "";
    String d27d = "";
    String d28d = "";
    String d29d = "";
    String d30d = "";
    String d31d = "";

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

    String d1n = "";
    String d2n = "";
    String d3n = "";
    String d4n = "";
    String d5n = "";
    String d6n = "";
    String d7n = "";
    String d8n = "";
    String d9n = "";
    String d10n = "";
    String d11n = "";
    String d12n = "";
    String d13n = "";
    String d14n = "";
    String d15n = "";
    String d16n = "";
    String d17n = "";
    String d18n = "";
    String d19n = "";
    String d20n = "";
    String d21n = "";
    String d22n = "";
    String d23n = "";
    String d24n = "";
    String d25n = "";
    String d26n = "";
    String d27n = "";
    String d28n = "";
    String d29n = "";
    String d30n = "";
    String d31n = "";

    String d1h = "";
    String d2h = "";
    String d3h = "";
    String d4h = "";
    String d5h = "";
    String d6h = "";
    String d7h = "";
    String d8h = "";
    String d9h = "";
    String d10h = "";
    String d11h = "";
    String d12h = "";
    String d13h = "";
    String d14h = "";
    String d15h = "";
    String d16h = "";
    String d17h = "";
    String d18h = "";
    String d19h = "";
    String d20h = "";
    String d21h = "";
    String d22h = "";
    String d23h = "";
    String d24h = "";
    String d25h = "";
    String d26h = "";
    String d27h = "";
    String d28h = "";
    String d29h = "";
    String d30h = "";
    String d31h = "";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_search = new javax.swing.JTextField();
        txt_empid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 270, 30));

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 90, 23));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("EMP No. :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        txt_name.setEditable(false);
        txt_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 270, 23));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Name :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Rank :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 60, 23));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Search:-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 180, 40));

        jProgressBar1.setOpaque(true);
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 320, 20));

        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Employee wise Monthly Atten: Summery", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 12))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 370, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
        //        if (txt_month.getText().isEmpty() | txt_locName.getText().equals("** Please Select a Location ")) {
        //
        //            JOptionPane.showMessageDialog(rootPane, "Date & Location Can not be Empty...");
        //
        //            txt_locName.grabFocus();
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
    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg");
                while (rs.next()) {
                    String code = rs.getString("EmployeeNo");
                    String nic = rs.getString("NIC");
                    String name = rs.getString("NameWithInitials");
                    //Double rate = Double.parseDouble(rs.getString("ShiftRate"));

                    String rank = rs.getString("Designation");

                    if (txt_search.getText().equals(code) || txt_search.getText().equals(name) || txt_search.getText().equals(nic)) {

                        txt_empid.setText(code);
                        txt_name.setText(name);

                        txt_rank.setText(rank);

                    } else {
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_searchKeyPressed

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
                    String emp = txt_empid.getText();

                    jProgressBar1.setValue(40);

                    Connection conn = (Connection) DbConnection.getconnection();

                    JasperDesign jd = JRXmlLoader.load("Reports\\Employee_wise_atten_details.jrxml");

                    jProgressBar1.setValue(50);
                    String sql = "select  emp_atten_main.EPFno,Date,Location,Month,Year,EffectiveRank,SUM(DayShift),SUM(NightShift),SUM(DayTwoShift),SUM(OTShift),SUM(OTHours),SUM(HalfDayShift),NameWithinitials,Designation,LocCode,LocName from `emp_atten_main` join employee_reg join location_reg where Month='"+month+"' and Year='"+year+"' and emp_atten_main.EPFno = '"+emp+"' and emp_atten_main.EPFno = EmployeeNo and Location = LocCode group by Date,Location Order by emp_atten_main.EPFno";
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
        new_print();

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(RPT_Emp_wise_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_Emp_wise_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_Emp_wise_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_Emp_wise_atten_summery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_Emp_wise_atten_summery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(emp_atten_sum);
            String path = "Reports\\EMP_wise_Attendance_Summery.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clear_ref() {

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

    }
    
    private void OLD_PRINT_HARD_CODE(){
     int mm = cmb_month.getSelectedIndex();
        int int_month = mm + 1;
        String month = String.format("%02d", int_month);

        String year = cmb_year.getSelectedItem().toString();
        int int_year = Integer.parseInt(year);

        String d1 = (year + "-" + month + "-" + "01");
        String d2 = (year + "-" + month + "-" + "02");
        String d3 = (year + "-" + month + "-" + "03");
        String d4 = (year + "-" + month + "-" + "04");
        String d5 = (year + "-" + month + "-" + "05");
        String d6 = (year + "-" + month + "-" + "06");
        String d7 = (year + "-" + month + "-" + "07");
        String d8 = (year + "-" + month + "-" + "08");
        String d9 = (year + "-" + month + "-" + "09");
        String d10 = (year + "-" + month + "-" + "10");
        String d11 = (year + "-" + month + "-" + "11");
        String d12 = (year + "-" + month + "-" + "12");
        String d13 = (year + "-" + month + "-" + "13");
        String d14 = (year + "-" + month + "-" + "14");
        String d15 = (year + "-" + month + "-" + "15");
        String d16 = (year + "-" + month + "-" + "16");
        String d17 = (year + "-" + month + "-" + "17");
        String d18 = (year + "-" + month + "-" + "18");
        String d19 = (year + "-" + month + "-" + "19");
        String d20 = (year + "-" + month + "-" + "20");
        String d21 = (year + "-" + month + "-" + "21");
        String d22 = (year + "-" + month + "-" + "22");
        String d23 = (year + "-" + month + "-" + "23");
        String d24 = (year + "-" + month + "-" + "24");
        String d25 = (year + "-" + month + "-" + "25");
        String d26 = (year + "-" + month + "-" + "26");
        String d27 = (year + "-" + month + "-" + "27");
        String d28 = (year + "-" + month + "-" + "28");
        String d29 = (year + "-" + month + "-" + "29");
        String d30 = (year + "-" + month + "-" + "30");
        String d31 = (year + "-" + month + "-" + "31");

        String get_month = cmb_month.getSelectedItem().toString();
        String get_year = cmb_year.getSelectedItem().toString();

        String LocName = null;
        String LocID = null;
        String totalShifts = null;
        String ComName = null;

        try {

            Statement st1 = DbConnection.getconnection().createStatement();
            ResultSet rs1 = st1.executeQuery("select * from emp_atten_main where Month='" + get_month + "' and Year='" + get_year + "' and EPFno='" + txt_empid.getText() + "' group by Location");
            while (rs1.next()) {

                LocID = rs1.getString("Location");

                // }
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery(" select * ,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift),SUM(DayTwoShift)  FROM emp_atten_main  where Location='" + LocID + "' and EPFno='" + txt_empid.getText() + "'  group by Date ");

                while (rs.next()) {

                    String Date = rs.getString("Date");

                    if (Date.equals(d1)) {

                        d1d = rs.getString("SUM(DayShift)");
                        d1n = rs.getString("SUM(NightShift)");
                        d1dt = rs.getString("SUM(DayTwoShift)");

                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d1h = Double.toString(halfday / 2);

                    }

                    if (Date.equals(d2)) {

                        d2d = rs.getString("SUM(DayShift)");
                        d2n = rs.getString("SUM(NightShift)");
                        d2dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d2h = Double.toString(halfday / 2);

                    }

                    if (Date.equals(d3)) {

                        d3d = rs.getString("SUM(DayShift)");
                        d3n = rs.getString("SUM(NightShift)");
                        d3dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d3h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d4)) {

                        d4d = rs.getString("SUM(DayShift)");
                        d4n = rs.getString("SUM(NightShift)");
                        d4dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d4h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d5)) {

                        d5d = rs.getString("SUM(DayShift)");
                        d5n = rs.getString("SUM(NightShift)");
                        d5dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d5h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d6)) {

                        d6d = rs.getString("SUM(DayShift)");
                        d6n = rs.getString("SUM(NightShift)");
                        d6dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d6h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d7)) {

                        d7d = rs.getString("SUM(DayShift)");
                        d7n = rs.getString("SUM(NightShift)");
                        d7dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d7h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d8)) {

                        d8d = rs.getString("SUM(DayShift)");
                        d8n = rs.getString("SUM(NightShift)");
                        d8dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d8h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d9)) {

                        d9d = rs.getString("SUM(DayShift)");
                        d9n = rs.getString("SUM(NightShift)");
                        d9dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d9h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d10)) {

                        d10d = rs.getString("SUM(DayShift)");
                        d10n = rs.getString("SUM(NightShift)");
                        d10dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d10h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d11)) {

                        d11d = rs.getString("SUM(DayShift)");
                        d11n = rs.getString("SUM(NightShift)");
                        d11dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d11h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d12)) {

                        d12d = rs.getString("SUM(DayShift)");
                        d12n = rs.getString("SUM(NightShift)");
                        d12dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d12h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d13)) {

                        d13d = rs.getString("SUM(DayShift)");
                        d13n = rs.getString("SUM(NightShift)");
                        d13dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d13h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d14)) {

                        d14d = rs.getString("SUM(DayShift)");
                        d14n = rs.getString("SUM(NightShift)");
                        d14dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d14h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d15)) {

                        d15d = rs.getString("SUM(DayShift)");
                        d15n = rs.getString("SUM(NightShift)");
                        d15dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d15h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d16)) {

                        d16d = rs.getString("SUM(DayShift)");
                        d16n = rs.getString("SUM(NightShift)");
                        d16dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d16h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d17)) {

                        d17d = rs.getString("SUM(DayShift)");
                        d17n = rs.getString("SUM(NightShift)");
                        d17dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d17h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d18)) {

                        d18d = rs.getString("SUM(DayShift)");
                        d18n = rs.getString("SUM(NightShift)");
                        d18dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d18h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d19)) {

                        d19d = rs.getString("SUM(DayShift)");
                        d19n = rs.getString("SUM(NightShift)");
                        d19dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d19h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d20)) {

                        d20d = rs.getString("SUM(DayShift)");
                        d20n = rs.getString("SUM(NightShift)");
                        d20dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d20h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d21)) {

                        d21d = rs.getString("SUM(DayShift)");
                        d21n = rs.getString("SUM(NightShift)");
                        d21dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d21h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d22)) {

                        d22d = rs.getString("SUM(DayShift)");
                        d22n = rs.getString("SUM(NightShift)");
                        d22dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d22h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d23)) {

                        d23d = rs.getString("SUM(DayShift)");
                        d23n = rs.getString("SUM(NightShift)");
                        d23dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d23h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d24)) {

                        d24d = rs.getString("SUM(DayShift)");
                        d24n = rs.getString("SUM(NightShift)");
                        d24dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d24h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d25)) {

                        d25d = rs.getString("SUM(DayShift)");
                        d25n = rs.getString("SUM(NightShift)");
                        d25dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d25h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d26)) {

                        d26d = rs.getString("SUM(DayShift)");
                        d26n = rs.getString("SUM(NightShift)");
                        d26dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d26h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d27)) {

                        d27d = rs.getString("SUM(DayShift)");
                        d27n = rs.getString("SUM(NightShift)");
                        d27dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d27h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d28)) {

                        d28d = rs.getString("SUM(DayShift)");
                        d28n = rs.getString("SUM(NightShift)");
                        d28dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d28h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d29)) {

                        d29d = rs.getString("SUM(DayShift)");
                        d29n = rs.getString("SUM(NightShift)");
                        d29dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d29h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d30)) {

                        d30d = rs.getString("SUM(DayShift)");
                        d30n = rs.getString("SUM(NightShift)");

                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);
                        d30dt = rs.getString("SUM(DayTwoShift)");
                        d30h = Double.toString(halfday / 2);

                    }
                    if (Date.equals(d31)) {

                        d31d = rs.getString("SUM(DayShift)");
                        d31n = rs.getString("SUM(NightShift)");
                        d31dt = rs.getString("SUM(DayTwoShift)");
                        String half = rs.getString("SUM(HalfDayShift)");
                        Double halfday = Double.parseDouble(half);

                        d31h = Double.toString(halfday / 2);

                    }

                    Statement st4 = DbConnection.getconnection().createStatement();
                    ResultSet rs4 = st4.executeQuery("select *,SUM(DayShift),SUM(NightShift),SUM(HalfDayShift),SUM(DayTwoShift) from emp_atten_main where Location='" + LocID + "' and EPFno='" + txt_empid.getText() + "' and Month='" + cmb_month.getSelectedItem().toString() + "' and Year='" + cmb_year.getSelectedItem().toString() + "'");
                    while (rs4.next()) {

                        Double day = Double.parseDouble(rs4.getString("SUM(DayShift)"));
                        Double night = Double.parseDouble(rs4.getString("SUM(NightShift)"));
                        Double half = Double.parseDouble(rs4.getString("SUM(HalfDayShift)"));
                        Double day2 = Double.parseDouble(rs4.getString("SUM(DayTwoShift)"));

                        Double total = day + day2 + night + (half / 2);
                        totalShifts = Double.toString(total);

                    }

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select * from location_reg where LocCode='" + LocID + "'");
                    while (rs3.next()) {

                        LocName = rs3.getString("LocName");

                    }

                }

                Statement st3 = DbConnection.getconnection().createStatement();
                ResultSet rs3 = st3.executeQuery("select * from company_reg where isDefault='1'");
                while (rs3.next()) {

                    ComName = rs3.getString("ComName");

                }

                bean_emp_atten_sum bds = new bean_emp_atten_sum();
                //LocName = rs.getString("LocName");

                bds.setL_code(LocID);
                bds.setL_name(LocName);
                bds.setCom_name(ComName);
                bds.setMonth(cmb_month.getSelectedItem().toString());
                bds.setYear(cmb_year.getSelectedItem().toString());
                bds.setEmp_code(txt_empid.getText());
                bds.setEmp_name(txt_name.getText());

                bds.setDay1d(d1d);
                bds.setDay1n(d1n);
                bds.setDay1h(d1h);
                bds.setDay1dt(d1dt);

                bds.setDay2d(d2d);
                bds.setDay2n(d2n);
                bds.setDay2h(d2h);
                bds.setDay2dt(d2dt);

                bds.setDay3d(d3d);
                bds.setDay3n(d3n);
                bds.setDay3h(d3h);
                bds.setDay3dt(d3dt);

                bds.setDay4d(d4d);
                bds.setDay4n(d4n);
                bds.setDay4h(d4h);
                bds.setDay4dt(d4dt);

                bds.setDay5d(d5d);
                bds.setDay5n(d5n);
                bds.setDay5h(d5h);
                bds.setDay5dt(d5dt);

                bds.setDay6d(d6d);
                bds.setDay6n(d6n);
                bds.setDay6h(d6h);
                bds.setDay6dt(d6dt);

                bds.setDay7d(d7d);
                bds.setDay7n(d7n);
                bds.setDay7h(d7h);
                bds.setDay7dt(d7dt);

                bds.setDay8d(d8d);
                bds.setDay8n(d8n);
                bds.setDay8h(d8h);
                bds.setDay8dt(d8dt);

                bds.setDay9d(d9d);
                bds.setDay9n(d9n);
                bds.setDay9h(d9h);
                bds.setDay9dt(d9dt);

                bds.setDay10d(d10d);
                bds.setDay10n(d10n);
                bds.setDay10h(d10h);
                bds.setDay10dt(d10dt);

                bds.setDay11d(d11d);
                bds.setDay11n(d11n);
                bds.setDay11h(d11h);
                bds.setDay11dt(d11dt);

                bds.setDay12d(d12d);
                bds.setDay12n(d12n);
                bds.setDay12h(d12h);
                bds.setDay12dt(d12dt);

                bds.setDay13d(d13d);
                bds.setDay13n(d13n);
                bds.setDay13h(d13h);
                bds.setDay13dt(d13dt);

                bds.setDay14d(d14d);
                bds.setDay14n(d14n);
                bds.setDay14h(d14h);
                bds.setDay14dt(d14dt);

                bds.setDay15d(d15d);
                bds.setDay15n(d15n);
                bds.setDay15h(d15h);
                bds.setDay15dt(d15dt);

//                bds.setDay16d(d16d);
//                bds.setDay16n(d16n);
//                bds.setDay16h(d16h);
//                  bds.setDay2dt(d2dt);
                bds.setDay16d(d16d);
                bds.setDay16n(d16n);
                bds.setDay16h(d16h);
                bds.setDay16dt(d16dt);

                bds.setDay17d(d17d);
                bds.setDay17n(d17n);
                bds.setDay17h(d17h);
                bds.setDay17dt(d17dt);

                bds.setDay18d(d18d);
                bds.setDay18n(d18n);
                bds.setDay18h(d18h);
                bds.setDay18dt(d18dt);

                bds.setDay19d(d19d);
                bds.setDay19n(d19n);
                bds.setDay19h(d19h);
                bds.setDay19dt(d19dt);

                bds.setDay20d(d20d);
                bds.setDay20n(d20n);
                bds.setDay20h(d20h);
                bds.setDay20dt(d20dt);

                bds.setDay21d(d21d);
                bds.setDay21n(d21n);
                bds.setDay21h(d21h);
                bds.setDay21dt(d21dt);

                bds.setDay22d(d22d);
                bds.setDay22n(d22n);
                bds.setDay22h(d22h);
                bds.setDay22dt(d22dt);

                bds.setDay23d(d23d);
                bds.setDay23n(d23n);
                bds.setDay23h(d23h);
                bds.setDay23dt(d23dt);

                bds.setDay24d(d24d);
                bds.setDay24n(d24n);
                bds.setDay24h(d24h);
                bds.setDay24dt(d24dt);

                bds.setDay25d(d25d);
                bds.setDay25n(d25n);
                bds.setDay25h(d25h);
                bds.setDay25dt(d25dt);

                bds.setDay26d(d26d);
                bds.setDay26n(d26n);
                bds.setDay26h(d26h);
                bds.setDay26dt(d26dt);

                bds.setDay27d(d27d);
                bds.setDay27n(d27n);
                bds.setDay27h(d27h);
                bds.setDay27dt(d27dt);

                bds.setDay28d(d28d);
                bds.setDay28n(d28n);
                bds.setDay28h(d28h);
                bds.setDay28dt(d28dt);

                bds.setDay29d(d29d);
                bds.setDay29n(d29n);
                bds.setDay29h(d29h);
                bds.setDay29dt(d29dt);

                bds.setDay30d(d30d);
                bds.setDay30n(d30n);
                bds.setDay30h(d30h);
                bds.setDay30dt(d30dt);

                bds.setDay31d(d31d);
                bds.setDay31n(d31n);
                bds.setDay31h(d31h);
                bds.setDay31dt(d31dt);

                bds.setTotal_shifts(totalShifts);

                clear_ref();

                emp_atten_sum.add(bds);

            }

            print();
            emp_atten_sum.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
