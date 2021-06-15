/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class RPT_Desciplinary extends javax.swing.JFrame {

    /**
     * Creates new form RPT_Desciplinary
     */
    public RPT_Desciplinary() {
        initComponents();
        
    }

//    private void auto_completer() {
//
//        try {
//
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from employee_reg    ");
//
//            TextAutoCompleter ta = new TextAutoCompleter(txt_search);
//
//            while (rs.next()) {
//                String code = rs.getString("EPFno");
//                String nic = rs.getString("NIC");
//                String NameWithInitials = rs.getString("NameWithInitials");
//
//                ta.addItem(code);
//                ta.addItem(NameWithInitials);
//                ta.addItem(nic);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        date_to = new com.toedter.calendar.JDateChooser();
        date_from = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Employee Desciplinary Report");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, 40));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 340, 10));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fine Print.png"))); // NOI18N
        jButton4.setText("View Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 230, 50));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 340, 10));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 340, 10));
        getContentPane().add(date_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 160, -1));
        getContentPane().add(date_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 160, -1));

        jLabel36.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel36.setText("Incident Date Range  :-");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 20));

        jLabel37.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel37.setText("To  :-");
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, 20));

        jLabel38.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel38.setText(" From  :-");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String FromDate = sdf.format(date_from.getDate());
            String ToDate = sdf.format(date_to.getDate());
            Connection conn = (Connection) DbConnection.getconnection();

            JasperDesign jd = JRXmlLoader.load("Reports\\DisciplinaryRPT.jrxml");
            String sql = "SELECT * FROM emp_discipline where  IncidentDate BETWEEN '"+FromDate+"' and '"+ToDate+"'    ";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(RPT_Desciplinary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPT_Desciplinary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPT_Desciplinary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPT_Desciplinary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPT_Desciplinary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date_from;
    private com.toedter.calendar.JDateChooser date_to;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
