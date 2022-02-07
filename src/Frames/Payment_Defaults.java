/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
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
 * @author Sapumal Bandara
 */
public class Payment_Defaults extends javax.swing.JFrame {

    /**
     * Creates new form Payment_Defaults
     */
    static ArrayList adv;

    public Payment_Defaults() {
        initComponents();
        TitleBar();
        lbl_loading.setVisible(false);
        adv = new ArrayList();
    }
    ImageIcon imageIcon = null;

    private void TitleBar() {
        get_Location_Details();
        imageIcon = new ImageIcon(new ImageIcon("src/Images/New Icons/loading.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));

        this.setTitle("Payment Defaults");

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

    private void get_Location_Details() {
        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("SELECT * from location_reg order by LocName");
            ResultSet rs = pst.executeQuery();
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

    private void get_details() {

        Connection con;
        PreparedStatement pst;
        ResultSet rs;

        int shiftCount = (Integer) jSpinner1.getValue();

        if (shiftCount < 0) {
        } else {

            String month = cmb_month.getSelectedItem().toString();
            String year = cmb_year.getSelectedItem().toString();

            try {

                String sql = "select *  from salary_advance_1 where PayMonth= '" + month + "' and PayYear= '" + year + "'  order by Location,EPFno  ";

                con = DbConnection.getconnection();
                pst = con.prepareCall(sql);
                rs = pst.executeQuery();

                DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
                dt.setRowCount(0);

                while (rs.next()) {

                    String epf = rs.getString("EPFno");
                    String name = rs.getString("Name");
                    String rank = rs.getString("Rank");
                    String amt = rs.getString("Amount");
                    String loc = rs.getString("Location");

                    Vector v = new Vector();
                    v.add(epf);
                    v.add(name);
                    v.add(rank);
                    v.add(loc);
                    v.add(amt);

                    dt.addRow(v);

                }

                int row_count = jTable1.getRowCount();

                for (int row = 0; row < row_count; row++) {

                    String epf = jTable1.getValueAt(row, 0).toString();

                    pst = con.prepareStatement("select * from emp_atten_shift_count_summery where  Month= '" + month + "' and  Year= '" + year + "' and EMPno='" + epf + "'");
                    rs = pst.executeQuery();
                    while (rs.next()) {

                        Double shifts = rs.getDouble("TotalShifts");

                        jTable1.setValueAt(shifts, row, 5);

                    }

                    if (jTable1.getValueAt(row, 5) == null) {
                        jTable1.setValueAt(0.00, row, 5);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void remove_irrelavant_data() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int shiftCount = (Integer) jSpinner1.getValue();

        switch (cmb_operator.getSelectedIndex()) {
            case 0:
                int row = 0;
                while (row < dtm.getRowCount()) {
                    Double value = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                    if (value == shiftCount) {
                        ++row;
                    } else {
                        dtm.removeRow(row);
                    }
                }

                break;
            case 1:
                row = 0;
                while (row < dtm.getRowCount()) {
                    Double value = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                    if (value < shiftCount) {
                        ++row;
                    } else {
                        dtm.removeRow(row);
                    }
                }
                break;
            case 2:
                row = 0;
                while (row < dtm.getRowCount()) {
                    Double value = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                    if (value > shiftCount) {
                        ++row;
                    } else {
                        dtm.removeRow(row);
                    }
                }
                break;
            case 3:
                row = 0;
                while (row < dtm.getRowCount()) {
                    Double value = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                    if (value <= shiftCount) {
                        ++row;
                    } else {
                        dtm.removeRow(row);
                    }
                }
                break;
            case 4:
                row = 0;
                while (row < dtm.getRowCount()) {
                    Double value = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                    if (value >= shiftCount) {
                        ++row;
                    } else {
                        dtm.removeRow(row);
                    }
                }
                break;

        }

    }

    private void exclude_selected_location_data() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        if (cb_selectedOnly.isSelected()) {

            int row = 0;
            while (row < dtm.getRowCount()) {

                if (jTable1.getValueAt(row, 3).toString().equals(txt_locCode.getText())) {
                    ++row;
                } else {
                    dtm.removeRow(row);
                }
            }
        } else {
            int row = 0;
            while (row < dtm.getRowCount()) {

                if (jTable1.getValueAt(row, 3).toString().equals(txt_locCode.getText())) {
                    dtm.removeRow(row);
                } else {
                    ++row;
                }
            }

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
        jLabel2 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_type = new javax.swing.JComboBox<>();
        cmb_operator = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        lbl_loading = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        cb_withoutSelected = new javax.swing.JRadioButton();
        cb_selectedOnly = new javax.swing.JRadioButton();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Payment Defaults");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));
        getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 380, 10));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("to : ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 30, 25));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 140, -1));

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
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 90, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Month/Year : ");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 100, 25));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Payment Type : ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 110, 25));

        cmb_type.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salary Advance" }));
        getContentPane().add(cmb_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 250, 25));

        cmb_operator.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_operator.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "= Equals", "< Less than", "> Greater than", "<= Less than or Equal", ">= Greater than or Equal" }));
        getContentPane().add(cmb_operator, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 180, 25));
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 40, 25));

        lbl_loading.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        lbl_loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/loading.gif"))); // NOI18N
        getContentPane().add(lbl_loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 50, 50));
        getContentPane().add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 990, 10));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_view_32px.png"))); // NOI18N
        jButton1.setText(" Get Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 220, 40));

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP no", "Name", "Rank", "Location", "Advance Amt", "No. of Shifts"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 590, 460));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/New Icons/icons8_print_32px.png"))); // NOI18N
        jButton2.setText(" Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 220, 40));

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Shift Count : ");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 110, 25));
        getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 990, 10));

        buttonGroup1.add(cb_withoutSelected);
        cb_withoutSelected.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_withoutSelected.setText("Except Above Location");
        cb_withoutSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_withoutSelectedActionPerformed(evt);
            }
        });
        getContentPane().add(cb_withoutSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, -1, -1));

        buttonGroup1.add(cb_selectedOnly);
        cb_selectedOnly.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_selectedOnly.setText("Above Location Only ");
        cb_selectedOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectedOnlyActionPerformed(evt);
            }
        });
        getContentPane().add(cb_selectedOnly, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 290, 25));

        txt_locCode.setBackground(new java.awt.Color(204, 204, 204));
        txt_locCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txt_locCode.setOpaque(true);
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 50, 25));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                jTable1.setVisible(false);
                //ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/New Icons/loading.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                lbl_loading.setIcon(imageIcon);
                lbl_loading.setVisible(true);
                get_details();
                jTable1.setVisible(true);
                remove_irrelavant_data();
                lbl_loading.setVisible(false);
                jButton2.setEnabled(true);

            }
        });
        hilo.start();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        lbl_loading.setIcon(imageIcon);
        lbl_loading.setVisible(true);
        if (cmb_defLocation.getSelectedIndex() == 0) {
            table_to_ireport();
        } else {

            if (cb_selectedOnly.isSelected() | cb_withoutSelected.isSelected()) {
                exclude_selected_location_data();
                table_to_ireport();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Please Select the report Type or Reset the Location Value to '=Location='", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        jButton2.setEnabled(false);
        lbl_loading.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cb_withoutSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_withoutSelectedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_withoutSelectedActionPerformed

    private void cb_selectedOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectedOnlyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_selectedOnlyActionPerformed

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode.setText("");
            cb_selectedOnly.setSelected(false);
            cb_withoutSelected.setSelected(false);

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
    private void table_to_ireport() {
        int nrow = jTable1.getModel().getRowCount();
        if (nrow == 0) {

        } else {
            for (int i = 0; nrow > i; i++) {
                bean_EMP_Advance bds = new bean_EMP_Advance();

                bds.setLoc_code(jTable1.getModel().getValueAt(i, 3).toString());
//                bds.setLoc_name(jTable1.getModel().getValueAt(i, 5).toString());
                bds.setEmp_code(jTable1.getModel().getValueAt(i, 0).toString());
                bds.setEmp_name(jTable1.getModel().getValueAt(i, 1).toString());
                bds.setRank(jTable1.getModel().getValueAt(i, 2).toString());
                bds.setAmount(Double.parseDouble(jTable1.getModel().getValueAt(i, 4).toString()));
                bds.setMonth(cmb_month.getSelectedItem().toString());
                bds.setYear(cmb_year.getSelectedItem().toString());
                bds.setShift_count(Double.parseDouble(jTable1.getModel().getValueAt(i, 5).toString()));

                adv.add(bds);

            }

            print();
            adv.clear();
        }

    }

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(adv);
            String path = "Reports\\Emp_Advance_Defaults.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(Payment_Defaults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment_Defaults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment_Defaults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment_Defaults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment_Defaults().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cb_selectedOnly;
    private javax.swing.JRadioButton cb_withoutSelected;
    public static javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox<String> cmb_operator;
    private javax.swing.JComboBox<String> cmb_type;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_loading;
    public static javax.swing.JLabel txt_locCode;
    // End of variables declaration//GEN-END:variables
}
