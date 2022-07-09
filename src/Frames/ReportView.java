/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sapumal Bandara
 */
public class ReportView {

    public ReportView(){}
    
    public void ReportView(String filePath, HashMap<String, Object> hashmap, String title) {

        try {

            Connection conn = DbConnection.getconnection();

//            String cwd = System.getProperty("user.dir");
//            System.out.println("Current working directory : " + cwd);
            JasperReport jr = JasperCompileManager.compileReport(filePath);
            JasperPrint jp = JasperFillManager.fillReport(jr, hashmap, conn);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setExtendedState(MAXIMIZED_BOTH);
            jv.setTitle(title);
            //jv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jv.setVisible(true);

            conn.close();
            
//            System.out.println(jr);System.out.println(jp);System.out.println(jv);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
