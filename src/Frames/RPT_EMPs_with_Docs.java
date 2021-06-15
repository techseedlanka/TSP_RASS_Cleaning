/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class RPT_EMPs_with_Docs extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Rank_Wise_Guards
     */
    static ArrayList al;

    public RPT_EMPs_with_Docs() {
        initComponents();

        al = new ArrayList();
        TitleBar();
    }

    Connection con;

    private void TitleBar() {

        try {
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg where isDefault=1 ");
            while (rs.next()) {
                String ComName = rs.getString("ComName");
                this.setTitle("TechSeed Payroll" + "    -    " + ComName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setResizable(false);
        ImageIcon img = new ImageIcon("Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

//    private void get_ranks() {
//
//        try {
//            con = DbConnection.getconnection();
//            PreparedStatement pst;
//            ResultSet rs;
//
//            String sql = "select * from rank group by RankCode";
//
//            pst = con.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//
//                String rank = rs.getString("RankCode");
//
//                jComboBox1.addItem(rank);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(rootPane, e);
//
//        }
//
//    }
//    private void get_emp_details() {
//
//        try {
//
//            JasperDesign jd = JRXmlLoader.load("Reports\\RPT_RankWise_Emp.jrxml");
//            String sql = "SELECT * FROM employee_reg   where Designation='" + jComboBox1.getSelectedItem().toString() + "'";
//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(sql);
//            jd.setQuery(newQuery);
//
//            JasperReport jr = JasperCompileManager.compileReport(jd);
//            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
//            JasperViewer.viewReport(jp, false);
//        } catch (JRException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(rootPane, e);
//        }
//
//    }
    private void test() {
        try {

            con = DbConnection.getconnection();
            PreparedStatement pst;
            String sql = null;
            String RPT_NAME = null;

            if (cb_birth.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE BirthCert='YES'  ";
                RPT_NAME = "Birth Certificate";
            }

            if (cb_GS.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE GramaCert='YES'  ";
                RPT_NAME = "Gramasewaka Certificate";
            }

            if (cb_POlice.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE PoliceCert='YES'  ";
                RPT_NAME = "Police Report";
            }
            
            if (cb_character.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE CharacterCert='YES'  ";
                RPT_NAME = "Character Certificate";
            }
            
            if (cb_srvice.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE ServiceCert='YES'  ";
                RPT_NAME = "Service Certificates";
            }
            
            if (cb_Police_and_GS.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE GramaCert='YES' and PoliceCert='YES'  ";
                RPT_NAME = "Police Report & Gramasewaka Certificate";
            }
            
             if (cb_All.isSelected()) {
                sql = "SELECT * FROM employee_reg WHERE BirthCert='YES' AND `GramaCert`='YES'  AND `PoliceCert`='YES' AND `ServiceCert`='YES' AND `CharacterCert`='YES'  ";
                RPT_NAME = "All Essential Reports";
            }
             
             

            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                bean_EMP_Doc_Data bds = new bean_EMP_Doc_Data();

                bds.setEpfno(rs.getString("EPFno"));
                bds.setName(rs.getString("NameWithInitials"));
                bds.setJoin(rs.getString("JoinedDate"));
                bds.setRank(rs.getString("Designation"));
                bds.setRpt_name(RPT_NAME);

                al.add(bds);

            }

            print();
            al.clear();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            String path = "Reports\\RPT_EMP_Relavant_Docs.jrxml";
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
        jLabel2 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        cb_srvice = new javax.swing.JCheckBox();
        cb_birth = new javax.swing.JCheckBox();
        cb_GS = new javax.swing.JCheckBox();
        cb_POlice = new javax.swing.JCheckBox();
        cb_character = new javax.swing.JCheckBox();
        cb_Police_and_GS = new javax.swing.JCheckBox();
        cb_All = new javax.swing.JCheckBox();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel2.setText("Employees with Relevant Documents");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        jSeparator10.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 230, 10));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        jButton1.setText("View Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 160, 40));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 280, 10));

        buttonGroup1.add(cb_srvice);
        cb_srvice.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_srvice.setText("Service Sertificates");
        getContentPane().add(cb_srvice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        buttonGroup1.add(cb_birth);
        cb_birth.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_birth.setText("Birth Certificate");
        getContentPane().add(cb_birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        buttonGroup1.add(cb_GS);
        cb_GS.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_GS.setText("Grama Sewaka Certificate");
        getContentPane().add(cb_GS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        buttonGroup1.add(cb_POlice);
        cb_POlice.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_POlice.setText("Police Report");
        getContentPane().add(cb_POlice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        buttonGroup1.add(cb_character);
        cb_character.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_character.setText("Character Certificate");
        getContentPane().add(cb_character, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        buttonGroup1.add(cb_Police_and_GS);
        cb_Police_and_GS.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_Police_and_GS.setText("Police Report And GS Certificate");
        getContentPane().add(cb_Police_and_GS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        buttonGroup1.add(cb_All);
        cb_All.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_All.setText("All Essential Certificates & Reports");
        getContentPane().add(cb_All, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));
        getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 280, 10));

        jSeparator13.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 230, 10));
        getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 280, 0));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        test();     // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RPT_EMPs_with_Docs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_EMPs_with_Docs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_EMPs_with_Docs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_EMPs_with_Docs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_EMPs_with_Docs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cb_All;
    private javax.swing.JCheckBox cb_GS;
    private javax.swing.JCheckBox cb_POlice;
    private javax.swing.JCheckBox cb_Police_and_GS;
    private javax.swing.JCheckBox cb_birth;
    private javax.swing.JCheckBox cb_character;
    private javax.swing.JCheckBox cb_srvice;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    // End of variables declaration//GEN-END:variables
}
