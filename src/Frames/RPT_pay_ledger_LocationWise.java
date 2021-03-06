/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
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
public class RPT_pay_ledger_LocationWise extends javax.swing.JFrame {

    /**
     * Creates new form RPT_pay_ledger
     */
    static ArrayList al;

    public RPT_pay_ledger_LocationWise() {

        al = new ArrayList();

        initComponents();
        get_Location_Details();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        cmb_loc_type = new javax.swing.JComboBox();
        txt_locName = new javax.swing.JTextField();
        cmb_defLocation = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 100, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
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

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel18.setText("Salary Month / Year :-");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Pay Ledger");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 330, 10));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton1.setText("Preview Ledger");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 200, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 330, 10));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Company :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 80, 40));

        cmb_loc_type.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_loc_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELEENA", "SIKURA" }));
        getContentPane().add(cmb_loc_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 200, -1));

        txt_locName.setEditable(false);
        txt_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_locNameFocusLost(evt);
            }
        });
        getContentPane().add(txt_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 310, 30));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 200, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setText("Location :-");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 70, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible
        // set_date_range();
    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (cmb_defLocation.getSelectedItem().toString().equals("=Location=")) {

        } else {

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

            String EMP_Loc_Type = null;
            String Ledger_Type = null;

            Double Sikura_NoPAyDays = null;
            Double Sikura_NoPAyAMT = null;

            String sql = null;

            //***************************SIKURA Employee PayLedger****************************
            if (cmb_loc_type.getSelectedIndex() == 1) {

                Ledger_Type = "SIKURA - " + cmb_defLocation.getSelectedItem().toString();

                sql = "select * from salary_final_sikura_staff where Month = '" + month + "' and Year = '" + year + "' and EmpLocation='" + cmb_defLocation.getSelectedItem().toString() + "'   ";

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

            } //*******************SEleena Employee Pay Ledger***********************************
            else {
                try {

                    EMP_Loc_Type = cmb_defLocation.getSelectedItem().toString();
                    Ledger_Type = "Seleena - " + cmb_defLocation.getSelectedItem().toString();
                    Sikura_NoPAyDays = 0.00;
                    Sikura_NoPAyAMT = 0.00;

                    sql = "select * from salary_final where Month = '" + month + "' and Year = '" + year + "' and EmpLocation='" + EMP_Loc_Type + "'";

                    //}
                    String epf = null;
                    String locCode = null;
                    String locName = null;
                    String ComName = null;
                    String ComAdd = null;
                    String ComTel = null;

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

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_locNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locNameFocusGained

    private void txt_locNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locNameFocusLost

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locName.setText(" Please Select a Location ");
            txt_locName.setForeground(Color.red);

        } else {
            txt_locName.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(code);
                    txt_locName.setText(name);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(RPT_pay_ledger_LocationWise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_pay_ledger_LocationWise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_pay_ledger_LocationWise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_pay_ledger_LocationWise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_pay_ledger_LocationWise().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_loc_type;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField txt_locName;
    // End of variables declaration//GEN-END:variables
}
