/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

//import com.mysql.jdbc.Connection;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
public class MAIN extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form MAIN
     */
    static String s;

    static ArrayList al_Loc_Rates;
    static ArrayList al_Loc_Carder;

    public MAIN() {

        al_Loc_Rates = new ArrayList();
        al_Loc_Carder = new ArrayList();
        initComponents();
        UserCat_wise_access_control();
        run();
        TitleBar();
        system_check();
        jButton2.setVisible(false);
        // test_print();
        // seticon();
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    private void system_check() {

        try {

            Connection con = DbConnection.getconnection();

            String date = lbl_current_date.getText();

            String sql = "update sys_ui set Active='0' where Valid='" + date + "'";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

            String sql1 = "select * from sys_ui";

            PreparedStatement pst1 = con.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                String active = rs1.getString("Active");

                if (active.equals("0")) {
                    JOptionPane.showMessageDialog(rootPane, "<html><h2 style=color:red>Your Trial Version Expired!!!</h2> <b>Database will be deleted soon.</b> <p> Please contact System Developers on </p> <p>fb/TechSeedSolutions Or (+94) 776-230-669 / (+94) 716-775-177 </p> </html>  ");
                    System.exit(0);
                }

            }

//ResultSet rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void seticon() {
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
//    }
    private void test_print() {

        try {
            lbl_logged_time.getText();
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Connection conn = (Connection) DbConnection.getconnection();

            JasperDesign jd = JRXmlLoader.load("Reports\\test_print.jrxml");
            String sql = "select * from test_print";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            //JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void TitleBar() {

        this.setTitle("RASS (Pvt)Ltd. - TechSeed Payroll 2.2.0");

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));

    }

    public void run() {

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Date d = new Date();
                    String date = d.toString();

                    SimpleDateFormat ft
                            = new SimpleDateFormat("yyyy-MM-dd");
                    lbl_current_date.setText("" + ft.format(d));
                    SimpleDateFormat ft1
                            = new SimpleDateFormat("hh:mm:ss a");
                    lbl_current_time.setText("" + ft1.format(d));

                }
            }
        }).start();

        s = lbl_current_date.getText();

    }

    private void UserCat_wise_access_control() {

        if (lbl_userCat.getText().equals("")) {

        }
        if (lbl_userCat.getText().equals("report")) {

            menu_approvals.setEnabled(false);
            menu_attendance.setEnabled(false);
            menu_employee.setEnabled(false);
            menu_registrations.setEnabled(false);
            menu_salary.setEnabled(false);
            menu_settings.setEnabled(false);
            menu_process.setEnabled(false);
            jMenuItem14.setEnabled(false);
//            Menu_atten_delete.setEnabled(false);
            Menu_user_reg.setEnabled(false);
            Menu_user_all_view.setEnabled(false);
        }
        if (lbl_userCat.getText().equals("manager")) {

            menu_settings.setEnabled(false);
            jMenuItem14.setEnabled(false);
            jMenuItem29.setEnabled(false);
            //  subMenu_sikura_salary_change.setEnabled(false);
            //    Menu_atten_delete.setEnabled(false);
            Menu_user_reg.setEnabled(false);
            Menu_user_all_view.setEnabled(false);

        }
        if (lbl_userCat.getText().equals("data_entry")) {

            //menu_salary.setEnabled(false);
            jMenuItem29.setEnabled(false);
//            subMenu_sikura_salary_change.setEnabled(false);
            jMenuItem14.setEnabled(false);
//            Menu_atten_delete.setEnabled(false);
            Menu_user_reg.setEnabled(false);
            Menu_user_all_view.setEnabled(false);
        }
        if (lbl_userCat.getText().equals("admin")) {

            jMenuItem14.setEnabled(false);
            Menu_user_all_view.setEnabled(false);
        }

    }

    void print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al_Loc_Rates);
            String path = "Reports\\location_rates_rpt.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, bcds);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void carder_print() {
        try {
            JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al_Loc_Carder);
            String path = "Reports\\location_carder_rpt.jrxml";
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        lbl_current_date = new javax.swing.JLabel();
        lbl_current_time = new javax.swing.JLabel();
        lbl_logged_time = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_logged_user = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_userCat = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_registrations = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem54 = new javax.swing.JMenuItem();
        menu_employee = new javax.swing.JMenu();
        jMenuItem39 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JPopupMenu.Separator();
        jMenuItem57 = new javax.swing.JMenuItem();
        jMenuItem59 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem55 = new javax.swing.JMenuItem();
        menu_attendance = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        menu_salary = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem56 = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        subMenu_adv_delete = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem50 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItem52 = new javax.swing.JMenuItem();
        menu_approvals = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        menu_reports = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        jMenuItem41 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JPopupMenu.Separator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem35 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem58 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem33 = new javax.swing.JMenuItem();
        menu_process = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator31 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JPopupMenu.Separator();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        menu_settings = new javax.swing.JMenu();
        Menu_user_reg = new javax.swing.JMenuItem();
        Menu_user_all_view = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Menu_user_all_view1 = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        Menu_user_all_view2 = new javax.swing.JMenuItem();
        Menu_user_all_view3 = new javax.swing.JMenuItem();
        Menu_user_all_view4 = new javax.swing.JMenuItem();
        Menu_user_all_view5 = new javax.swing.JMenuItem();
        menu_settings1 = new javax.swing.JMenu();
        Menu_user_all_view6 = new javax.swing.JMenuItem();
        Menu_user_all_view7 = new javax.swing.JMenuItem();
        Menu_user_all_view8 = new javax.swing.JMenuItem();
        Menu_user_all_view9 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu8.setText("jMenu8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_current_date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_current_date.setText("Date");
        lbl_current_date.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(lbl_current_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 150, -1));

        lbl_current_time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_current_time.setText("Current time");
        lbl_current_time.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(lbl_current_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 140, -1));

        lbl_logged_time.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_logged_time.setText("Loged time");
        lbl_logged_time.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(lbl_logged_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 570, 120, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1200, 10));

        lbl_logged_user.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_logged_user.setText("User001");
        lbl_logged_user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(lbl_logged_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 90, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel3.setText("TechSeed Solutions All Rights Reserved - 2018  ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 530, 390, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Copyright.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 540, 20, 20));

        lbl_userCat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_userCat.setText("UserType");
        lbl_userCat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(lbl_userCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 570, 80, -1));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Change User Male Filled_32.png"))); // NOI18N
        jButton1.setText("Change My Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 520, 290, 50));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Updates - till 02nd Dec 2019;\n===============================\n* Machine Allowance & Attendance \nAllowance Entry UI & Salary Calc.\n\n* Remove Attn. Allow. from \nSalary Rates UI\n* Salary Stop Payment\n* Adavance Reconciliation (UI & Report)\n* Discrepancy payledger\n* Final & Temporary Payledgers\n* Standard & Signing Payledgers\n* Print all option for payledgers\n* Rank changing for entered \nattendance records (New UI)\n* Duplicate Attendance Records Checker\n* Final salary Process lock\n* Salary Export to Sampath Cooperate \nExcel file \n\n\n\nLatest Update - 10th Sept 2019;\n===============================\n* New Attendance UI - as Requested\n   => Cadre Control is Deactivated.\n\n* Salary Rates UI Updated with\n   \"Day & Night Shift Rate\" Column\n\n");
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 340, 480));

        jButton2.setText("Attn Rank ReSet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jMenuBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(500, 40));

        menu_registrations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Page Overview -30.png"))); // NOI18N
        menu_registrations.setText("Registrations     ");
        menu_registrations.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_registrations.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem2.setText("Employee");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_registrations.add(jMenuItem2);
        menu_registrations.add(jSeparator20);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem3.setText("Company");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu_registrations.add(jMenuItem3);
        menu_registrations.add(jSeparator21);

        jMenu14.setText("Location");
        jMenu14.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem4.setText("New Location");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem4);

        jMenuItem7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem7.setText("Location Cadre");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem7);

        menu_registrations.add(jMenu14);
        menu_registrations.add(jSeparator22);

        jMenuItem5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem5.setText("Rank");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menu_registrations.add(jMenuItem5);
        menu_registrations.add(jSeparator23);

        jMenuItem6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem6.setText("Bank");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menu_registrations.add(jMenuItem6);
        menu_registrations.add(jSeparator30);

        jMenuItem28.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem28.setText("Salary Rates");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        menu_registrations.add(jMenuItem28);

        jMenuItem54.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem54.setText("Allowannce");
        jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem54ActionPerformed(evt);
            }
        });
        menu_registrations.add(jMenuItem54);

        jMenuBar1.add(menu_registrations);

        menu_employee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Employee.png"))); // NOI18N
        menu_employee.setText("Employee       ");
        menu_employee.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenuItem39.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem39.setText("Employee Bank Account");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        menu_employee.add(jMenuItem39);
        menu_employee.add(jSeparator6);

        jMenu1.setText("Loan Managment");
        jMenu1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem11.setText("Loan Entry");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);
        jMenu1.add(jSeparator33);

        jMenuItem57.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem57.setText("Festival Advance Entry");
        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem57);

        jMenuItem59.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem59.setText("Festival Advance Edit/Delete");
        jMenuItem59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem59ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem59);

        menu_employee.add(jMenu1);
        menu_employee.add(jSeparator19);

        jMenuItem15.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem15.setText("Resignation");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        menu_employee.add(jMenuItem15);
        menu_employee.add(jSeparator18);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem12.setText("Disciplinary Records");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        menu_employee.add(jMenuItem12);
        menu_employee.add(jSeparator17);

        jMenuItem16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem16.setText("Uniforms Issue");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        menu_employee.add(jMenuItem16);
        menu_employee.add(jSeparator15);

        jMenuItem55.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem55.setText("Change Employee Pay Type");
        jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem55ActionPerformed(evt);
            }
        });
        menu_employee.add(jMenuItem55);

        jMenuBar1.add(menu_employee);

        menu_attendance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Month View Filled-30.png"))); // NOI18N
        menu_attendance.setText("Attendance         ");
        menu_attendance.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenuItem32.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem32.setText("Attendance Enter - Ground Staff");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        menu_attendance.add(jMenuItem32);
        menu_attendance.add(jSeparator16);

        jMenuItem37.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem37.setText("Attendance Enter - Admin Staff");
        jMenuItem37.setEnabled(false);
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        menu_attendance.add(jMenuItem37);

        jMenuItem51.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem51.setText("Attendance Rank Change - Ground Staff");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        menu_attendance.add(jMenuItem51);

        jMenuBar1.add(menu_attendance);

        menu_salary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Salary.png"))); // NOI18N
        menu_salary.setText("Salary             ");
        menu_salary.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenuItem24.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem24.setText("Earnings & Deductions");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        menu_salary.add(jMenuItem24);

        jMenuItem56.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem56.setText("Bulk Deductions Entry ");
        jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem56ActionPerformed(evt);
            }
        });
        menu_salary.add(jMenuItem56);
        menu_salary.add(jSeparator14);

        subMenu_adv_delete.setText("Advance");
        subMenu_adv_delete.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        jMenuItem27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem27.setText(" Advance Enter");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        subMenu_adv_delete.add(jMenuItem27);
        subMenu_adv_delete.add(jSeparator12);

        jMenuItem29.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem29.setText("Advance Delete");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        subMenu_adv_delete.add(jMenuItem29);

        jMenuItem50.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem50.setText("Advance Reconciliation");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        subMenu_adv_delete.add(jMenuItem50);

        menu_salary.add(subMenu_adv_delete);
        menu_salary.add(jSeparator13);

        jMenuItem52.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem52.setText("Salary Stop Payment");
        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        menu_salary.add(jMenuItem52);

        jMenuBar1.add(menu_salary);

        menu_approvals.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Approval-30 (1).png"))); // NOI18N
        menu_approvals.setText("Approvals          ");
        menu_approvals.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenuItem14.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem14.setText("Disciplinary Records Approve");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        menu_approvals.add(jMenuItem14);

        jMenuBar1.add(menu_approvals);

        menu_reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Report Card-30.png"))); // NOI18N
        menu_reports.setText("Reports        ");
        menu_reports.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenu9.setText("Location");
        jMenu9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem9.setText("Location Details");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem9);

        jMenuItem19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem19.setText("Location Shift Rates");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenuItem42.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem42.setText("Location Carder Details");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem42);
        jMenu9.add(jSeparator27);

        jMenuItem41.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem41.setText("Worked & Contracted Shift Variance");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem41);

        menu_reports.add(jMenu9);
        menu_reports.add(jSeparator9);

        jMenu10.setText("Employee");
        jMenu10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenu10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu10ActionPerformed(evt);
            }
        });

        jMenuItem34.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem34.setText("Employee Detail Report");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem34);

        jMenuItem20.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem20.setText("Employee Summery Report");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem20);

        jMenuItem26.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem26.setText("Employee Bank Acc Details");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem26);

        jMenuItem53.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem53.setText("Employee Salary Details");
        jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem53ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem53);
        jMenu10.add(jSeparator26);

        jMenuItem46.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem46.setText("Rank wise Employee Details");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem46);

        jMenuItem45.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem45.setText("Location wise Employee Details");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem45);

        jMenuItem47.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem47.setText("Enlisted Date wise Employee Details");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem47);

        jMenuItem48.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem48.setText("Employees with Relevant Documents");
        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem48);
        jMenu10.add(jSeparator29);

        jMenuItem18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem18.setText("Employee Loan Details");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem18);
        jMenu10.add(jSeparator3);

        jMenuItem21.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem21.setText("Disciplinary Records");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem21);

        menu_reports.add(jMenu10);
        menu_reports.add(jSeparator10);

        jMenu13.setText("Attendance");
        jMenu13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenu13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu13ActionPerformed(evt);
            }
        });

        jMenuItem35.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem35.setText("All EMP. Attendance Details");
        jMenuItem35.setEnabled(false);
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem35);
        jMenu13.add(jSeparator4);

        jMenuItem36.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem36.setText("Location wise Attendance Details");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem36);

        jMenuItem38.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem38.setText("Employee Wise Attendance Details");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem38);

        jMenuItem44.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem44.setText("Date Range wise Total Shifts ");
        jMenuItem44.setEnabled(false);
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem44);
        jMenu13.add(jSeparator5);

        jMenuItem40.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem40.setText("Shift Rate Summery - All Employee");
        jMenuItem40.setEnabled(false);
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem40);

        jMenuItem43.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem43.setText("Shift Rate Details - Single Employee");
        jMenuItem43.setEnabled(false);
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem43);

        menu_reports.add(jMenu13);
        menu_reports.add(jSeparator11);

        jMenu12.setText("Salary");
        jMenu12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu12ActionPerformed(evt);
            }
        });

        jMenuItem22.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem22.setText("Salary Deductions Report");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem22);

        jMenuItem30.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem30.setText("EPF/ETF Deductions Report");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem30);

        jMenuItem49.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem49.setText("Salary Advance Report");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem49);

        jMenuItem58.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem58.setText("Festival Advance  Reports");
        jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem58ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem58);
        jMenu12.add(jSeparator7);

        jMenuItem25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem25.setText("Pay Ledger");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem25);

        jMenuItem31.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem31.setText("Pay Slip");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem31);
        jMenu12.add(jSeparator8);

        jMenuItem33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem33.setText("Salary Cash Denomination");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem33);

        menu_reports.add(jMenu12);

        jMenuBar1.add(menu_reports);

        menu_process.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Approval-30 (1).png"))); // NOI18N
        menu_process.setText("Process         ");
        menu_process.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        jMenuItem23.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem23.setText("Attendance Process");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        menu_process.add(jMenuItem23);
        menu_process.add(jSeparator31);

        jMenuItem10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem10.setText("Salary Process");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menu_process.add(jMenuItem10);
        menu_process.add(jSeparator24);
        menu_process.add(jSeparator28);

        jMenuItem17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem17.setText("Sampath CPS Excel");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        menu_process.add(jMenuItem17);
        menu_process.add(jSeparator32);
        menu_process.add(jSeparator35);
        menu_process.add(jSeparator34);

        jMenuItem13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jMenuItem13.setText("Month End Process");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        menu_process.add(jMenuItem13);

        jMenuBar1.add(menu_process);

        menu_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Settings 3-30.png"))); // NOI18N
        menu_settings.setText("Settings       ");
        menu_settings.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        Menu_user_reg.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_reg.setText("User Registration");
        Menu_user_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_regActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_reg);

        Menu_user_all_view.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view.setText("View All Users");
        Menu_user_all_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_viewActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_all_view);
        menu_settings.add(jSeparator2);

        Menu_user_all_view1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view1.setText("Tax & NBT Rates");
        Menu_user_all_view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view1ActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_all_view1);
        menu_settings.add(jSeparator25);

        Menu_user_all_view2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view2.setText("Invoice Shift Rates");
        Menu_user_all_view2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view2ActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_all_view2);

        Menu_user_all_view3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view3.setText("Working Days");
        Menu_user_all_view3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view3ActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_all_view3);

        Menu_user_all_view4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view4.setText("Special Holidays");
        Menu_user_all_view4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view4ActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_all_view4);

        Menu_user_all_view5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view5.setText("Define Sundays");
        Menu_user_all_view5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view5ActionPerformed(evt);
            }
        });
        menu_settings.add(Menu_user_all_view5);

        jMenuBar1.add(menu_settings);

        menu_settings1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MenuBarIcons/Invoice.png"))); // NOI18N
        menu_settings1.setText("Invoice");
        menu_settings1.setEnabled(false);
        menu_settings1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N

        Menu_user_all_view6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view6.setText("Invoice Process");
        Menu_user_all_view6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view6ActionPerformed(evt);
            }
        });
        menu_settings1.add(Menu_user_all_view6);

        Menu_user_all_view7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view7.setText("Invoice Print");
        Menu_user_all_view7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view7ActionPerformed(evt);
            }
        });
        menu_settings1.add(Menu_user_all_view7);

        Menu_user_all_view8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view8.setText("Invoice Adjustments");
        Menu_user_all_view8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view8ActionPerformed(evt);
            }
        });
        menu_settings1.add(Menu_user_all_view8);

        Menu_user_all_view9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        Menu_user_all_view9.setText("Invoice Estimated Income");
        Menu_user_all_view9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_user_all_view9ActionPerformed(evt);
            }
        });
        menu_settings1.add(Menu_user_all_view9);

        jMenuBar1.add(menu_settings1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        UserCat_wise_access_control();
        lbl_logged_time.setText(lbl_current_time.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        employee_reg em = null;
        try {
            em = new employee_reg();
        } catch (Exception ex) {
            Logger.getLogger(MAIN.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        CompanyReg cm = new CompanyReg();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        LocationReg lc = new LocationReg();
        lc.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        RankReg cm = new RankReg();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        bank_reg1 cm = new bank_reg1();
        cm.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            //JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            Connection conn = (Connection) DbConnection.getconnection();

            String path = "Reports\\location_details.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        Decsiplinary_Approval em = new Decsiplinary_Approval();
        em.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

//        String locName = null;
//
//        try {
//
//            Statement st2 = DbConnection.getconnection().createStatement();
//            ResultSet rs2 = st2.executeQuery("select * from location_shift_rates group by LocCode");
//            while (rs2.next()) {
//
//                String loc = rs2.getString("LocCode");
//
//                Statement st = DbConnection.getconnection().createStatement();
//                ResultSet rs = st.executeQuery("select * from location_shift_rates where LocCode='" + loc + "'");
//
//                while (rs.next()) {
//
//                    //String loc = rs.getString("LocCode");
//                    String rank = rs.getString("Rank");
//                    String rate = rs.getString("ShiftRate");
//
//                    Statement st1 = DbConnection.getconnection().createStatement();
//                    ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'");
//                    while (rs1.next()) {
//
//                        locName = rs1.getString("LocName");
//
//                    }
//
//                    bean_Loc_rates bds = new bean_Loc_rates();
//
//                    bds.setLocCode(loc);
//                    bds.setLocName(locName);
//                    bds.setLocRank(rank);
//                    bds.setLocRate(rate);
//
//                    al_Loc_Rates.add(bds);
//
//                }
//            }
//
//            print();
//            al_Loc_Rates.clear();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            //JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            Connection conn = (Connection) DbConnection.getconnection();

            String path = "Reports\\Salary_Rates.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        try {
            //JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            Connection conn = (Connection) DbConnection.getconnection();

            String path = "Reports\\employee_summery_rpt2.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        RPT_Desciplinary ap = new RPT_Desciplinary();
        ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        RPT_Deductions ap = new RPT_Deductions();
        ap.setVisible(true);


    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        Salary_Manual_Adjustments ap = new Salary_Manual_Adjustments();
        ap.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenu10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Location_Carder ad = new Location_Carder();
        ad.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        Advance_RASS sm = new Advance_RASS();
        sm.setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        Advance_delete ad = new Advance_delete();
        ad.setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed

        EMP_Atten_NEW sp = new EMP_Atten_NEW();
        sp.setVisible(true);

    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        RPT_pay_ledger sp = new RPT_pay_ledger();
        sp.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        RPT_Pay_Slip sp = new RPT_Pay_Slip();
        sp.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenu12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu12ActionPerformed

    private void Menu_user_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_regActionPerformed
        User_register ur = new User_register();
        ur.setVisible(true);
    }//GEN-LAST:event_Menu_user_regActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        try {
            //JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(al);
            Connection conn = (Connection) DbConnection.getconnection();

            String path = "Reports\\employee_summery_rpt.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed

        RPT_All_EMP_Atten ur = new RPT_All_EMP_Atten();
        ur.setVisible(true);

    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        RPT_atten_summery ur = new RPT_atten_summery();
        ur.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenu13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu13ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        RPT_Emp_wise_atten_summery ur = new RPT_Emp_wise_atten_summery();
        ur.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        Uniform_Deduction_Entry uni = new Uniform_Deduction_Entry();
        uni.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

        Decsiplinary_Details em = new Decsiplinary_Details();
        em.setVisible(true);


    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed

        employee_resign em = new employee_resign();
        em.setVisible(true);


    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        Distress_Loan em = new Distress_Loan();
        em.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed

        RPT_rate_change_shifts_summery em = new RPT_rate_change_shifts_summery();
        em.setVisible(true);


    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed

        RPT_Total_Shifts_loc_wise em = new RPT_Total_Shifts_loc_wise();
        em.setVisible(true);

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed

        String locName = null;

        try {

            Statement st2 = DbConnection.getconnection().createStatement();
            ResultSet rs2 = st2.executeQuery("select * from location_carder group by LocCode");
            while (rs2.next()) {

                String loc = rs2.getString("LocCode");

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from location_carder where LocCode='" + loc + "' order by CarderType");

                while (rs.next()) {

                    //String loc = rs.getString("LocCode");
                    String rank = rs.getString("Rank");
                    String shiftType = rs.getString("CarderType");
                    String guards = rs.getString("NoOfGuards");

                    Statement st1 = DbConnection.getconnection().createStatement();
                    ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'");
                    while (rs1.next()) {

                        locName = rs1.getString("LocName");

                    }

                    bean_loc_carder bds = new bean_loc_carder();

                    bds.setLocCode(loc);
                    bds.setLocName(locName);
                    bds.setRank(rank);
                    bds.setShiftType(shiftType);
                    bds.setGuards(guards);

                    al_Loc_Carder.add(bds);

                }
            }

            carder_print();
            al_Loc_Carder.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Salary_process_DEMO_NEW sp = new Salary_process_DEMO_NEW();
        sp.setVisible(true);
        jMenuItem10.setEnabled(false);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        Attendance_Process ap = new Attendance_Process();
        ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        UserCat_wise_access_control();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        User_Pass_Change ap = new User_Pass_Change();
        User_Pass_Change.txt_userID.setText(lbl_logged_user.getText());

        ap.setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void Menu_user_all_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_viewActionPerformed
        User_viewAll ap = new User_viewAll();
        ap.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_Menu_user_all_viewActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        RPT_Cash_Denomination ap = new RPT_Cash_Denomination();
        ap.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        RPT_ShiftRateDetails_SingleEmployee ap = new RPT_ShiftRateDetails_SingleEmployee();
        ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        AdminStaff_Attendance ap = new AdminStaff_Attendance();
        ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void Menu_user_all_view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view1ActionPerformed
        String userNameValue;
        String passwordValue;

        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};

        int result = JOptionPane.showConfirmDialog(null, ob, "Enter User Name & Password to Proceed", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            userNameValue = userName.getText();
            passwordValue = password.getText();

            String uname = null;

            try {

                Statement st2 = DbConnection.getconnection().createStatement();
                ResultSet rs2 = st2.executeQuery("select * from user where UserName = '" + userName.getText() + "'");

                while (rs2.next()) {

                    String UN = rs2.getString("UserName");
                    String pass = rs2.getString("Password");
                    String cat = rs2.getString("UserCat");

                    if (UN.equals(userNameValue) & pass.equals(passwordValue)) {

                        if (cat.equals("admin") | cat.equals("system_admin")) {

//  query begins  *************************************************************************************************
                            Tax_NBT ap = new Tax_NBT();
                            ap.setVisible(true);
//  query ends    *************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_Menu_user_all_view1ActionPerformed

    private void Menu_user_all_view2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view2ActionPerformed

        String userNameValue;
        String passwordValue;

        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};

        int result = JOptionPane.showConfirmDialog(null, ob, "Enter User Name & Password to Proceed", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            userNameValue = userName.getText();
            passwordValue = password.getText();

            String uname = null;

            try {

                Statement st2 = DbConnection.getconnection().createStatement();
                ResultSet rs2 = st2.executeQuery("select * from user where UserName = '" + userName.getText() + "'");

                while (rs2.next()) {

                    String UN = rs2.getString("UserName");
                    String pass = rs2.getString("Password");
                    String cat = rs2.getString("UserCat");

                    if (UN.equals(userNameValue) & pass.equals(passwordValue)) {

                        if (cat.equals("admin") | cat.equals("system_admin")) {

//  query begins  *************************************************************************************************
                            Invoice_ShiftRates ap = new Invoice_ShiftRates();
                            ap.setVisible(true);
//  query ends    *************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_Menu_user_all_view2ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        RPT_No_of_Shifts_in_Selected_DateRange ur = new RPT_No_of_Shifts_in_Selected_DateRange();
        ur.setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        RPT_EPF_Deduction_List ur = new RPT_EPF_Deduction_List();
        ur.setVisible(true);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        RPT_LocationWise_EMP ap = new RPT_LocationWise_EMP();
        ap.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        RPT_Rank_Wise_Guards ap = new RPT_Rank_Wise_Guards();
        ap.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        RPT_Enlisted_Date_wise_Emp ap = new RPT_Enlisted_Date_wise_Emp();
        ap.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        RPT_EMPs_with_Docs ap = new RPT_EMPs_with_Docs();
        ap.setVisible(true);
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

        Month_End ap = new Month_End();
        ap.setVisible(true);


    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        Loan_Details em = new Loan_Details();
        em.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        Salary_Rates cm = new Salary_Rates();
        cm.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        RPT_Advance cm = new RPT_Advance();
        cm.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        EMP_BankAcc_Details cm = new EMP_BankAcc_Details();
        cm.setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void Menu_user_all_view9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view9ActionPerformed
        RPT_Invoice_Estimated_income em = new RPT_Invoice_Estimated_income();
        em.setVisible(true);
    }//GEN-LAST:event_Menu_user_all_view9ActionPerformed

    private void Menu_user_all_view8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view8ActionPerformed
        Invoice_Deduc_and_Add ur = new Invoice_Deduc_and_Add();
        ur.setVisible(true);
    }//GEN-LAST:event_Menu_user_all_view8ActionPerformed

    private void Menu_user_all_view7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Menu_user_all_view7ActionPerformed

    private void Menu_user_all_view6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view6ActionPerformed
        Invoice_Proces ur = new Invoice_Proces();
        ur.setVisible(true);
    }//GEN-LAST:event_Menu_user_all_view6ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        SAMPATH_Bank_PayMasterFile ur = new SAMPATH_Bank_PayMasterFile();
        ur.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void Menu_user_all_view3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view3ActionPerformed
        Total_Working_Days ur = new Total_Working_Days();
        ur.setVisible(true);
    }//GEN-LAST:event_Menu_user_all_view3ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        Advance_Reconcilation ad = new Advance_Reconcilation();
        ad.setVisible(true);
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        Emp_Bank_Acc_details ad = new Emp_Bank_Acc_details();
        ad.setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        Attn_Rank_Changing_UI ad = new Attn_Rank_Changing_UI();
        ad.setVisible(true);
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        Salary_stop_Payment ad = new Salary_stop_Payment();
        ad.setVisible(true);
    }//GEN-LAST:event_jMenuItem52ActionPerformed

    private void Menu_user_all_view4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view4ActionPerformed
        Special_Holidays ad = new Special_Holidays();
        ad.setVisible(true);
    }//GEN-LAST:event_Menu_user_all_view4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    Connection con = DbConnection.getconnection();
                    PreparedStatement pst = con.prepareStatement("select * from emp_atten_main group by EPFno");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {

                        String emp = rs.getString("EPFno");

                        PreparedStatement pst1 = con.prepareStatement("select * from employee_reg where EmployeeNo='" + emp + "'");
                        ResultSet rs1 = pst1.executeQuery();
                        while (rs1.next()) {

                            String rank = rs1.getString("Designation");

                            PreparedStatement pst2 = con.prepareStatement("update emp_atten_main set EffectiveRank='" + rank + "' where EPFno='" + emp + "'");
                            pst2.execute();

                        }

                    }

                    JOptionPane.showMessageDialog(rootPane, "All Ranks Are Resetted!");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.start();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        Emp_Salary_Details cm = new Emp_Salary_Details();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
        Attn_Allowance cm = new Attn_Allowance();
        cm.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void Menu_user_all_view5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_user_all_view5ActionPerformed
        Sundays_define cm = new Sundays_define();
        cm.setVisible(true);
    }//GEN-LAST:event_Menu_user_all_view5ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        Employee_PayType_Change_bulk cm = new Employee_PayType_Change_bulk();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
        Deductions_NewUI cm = new Deductions_NewUI();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem56ActionPerformed

    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        FestivalAdvance cm = new FestivalAdvance();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem57ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        RPT_Deductions_new cm = new RPT_Deductions_new();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void jMenuItem59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem59ActionPerformed
       Festival_Advance_delete cm = new Festival_Advance_delete();
        cm.setVisible(true);
    }//GEN-LAST:event_jMenuItem59ActionPerformed

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
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MAIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Menu_user_all_view;
    private javax.swing.JMenuItem Menu_user_all_view1;
    private javax.swing.JMenuItem Menu_user_all_view2;
    private javax.swing.JMenuItem Menu_user_all_view3;
    private javax.swing.JMenuItem Menu_user_all_view4;
    private javax.swing.JMenuItem Menu_user_all_view5;
    private javax.swing.JMenuItem Menu_user_all_view6;
    private javax.swing.JMenuItem Menu_user_all_view7;
    private javax.swing.JMenuItem Menu_user_all_view8;
    private javax.swing.JMenuItem Menu_user_all_view9;
    private javax.swing.JMenuItem Menu_user_reg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    public static javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    public static javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    public static javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    public static javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    public static javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    public static javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem59;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JPopupMenu.Separator jSeparator25;
    private javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JPopupMenu.Separator jSeparator29;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator30;
    private javax.swing.JPopupMenu.Separator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator32;
    private javax.swing.JPopupMenu.Separator jSeparator33;
    private javax.swing.JPopupMenu.Separator jSeparator34;
    private javax.swing.JPopupMenu.Separator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    public static javax.swing.JLabel lbl_current_date;
    private javax.swing.JLabel lbl_current_time;
    private javax.swing.JLabel lbl_logged_time;
    public static javax.swing.JLabel lbl_logged_user;
    public static javax.swing.JLabel lbl_userCat;
    private javax.swing.JMenu menu_approvals;
    private javax.swing.JMenu menu_attendance;
    private javax.swing.JMenu menu_employee;
    private javax.swing.JMenu menu_process;
    private javax.swing.JMenu menu_registrations;
    private javax.swing.JMenu menu_reports;
    private javax.swing.JMenu menu_salary;
    private javax.swing.JMenu menu_settings;
    private javax.swing.JMenu menu_settings1;
    private javax.swing.JMenu subMenu_adv_delete;
    // End of variables declaration//GEN-END:variables
}
