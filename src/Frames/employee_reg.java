/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.PopUp_Emp_Table.POPUP_EMP_TABLE;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapu
 */
public class employee_reg extends javax.swing.JFrame {

    Connection c = null;
    ResultSet rst = null;
    PreparedStatement pst = null;

    static String isResigned = "0";

    static String BirthCert;
    static String GramaCert;
    static String PoliceCert;
    static String CharacterCert;
    static String NIC;
    static String ServiceCert;
    static String edu;
    static String Active_EPF;
    static String SikuraEmpShiftrateBasisSalary = "0";

    String filename;
    int s = 0;
    byte[] EMPImage = null;
    private ImageIcon format = null;

    public employee_reg() throws Exception {

        initComponents();

        // skills();
        //c = sql.ConnectDb();
        c = DbConnection.getconnection();
        cb_active_epf.setSelected(true);
//       max_epfno();
        jButton3.setText("<html>Salary Details<br>Update</html>");

//        jToggleButton1.setSelected(false);
//        jToggleButton1.setText("Serch Off");
        date();
        seticon();
        get_Company_Details();
        get_Location_Details();
        get_Rank_Details();
        TitleBar();
        // get_EMP_Details();

        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);
        jTable2.getTableHeader().setUI(null);
        jTable2.setVisible(false);
        jScrollPane4.setVisible(false);
    }

    private void seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
    }

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

    }

    private void date() {
        Date date = new Date();
        DateChooser_joinedDate.setDate(date);

    }

    private void get_Company_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from company_reg order by ComName");
            while (rs.next()) {

                String code = rs.getString("ComCode");
                String name = rs.getString("ComName");

                cmb_defCompany.addItem(code);
                //cmb_defCompany.addItem(name);

                AutoCompleteDecorator.decorate(cmb_defCompany);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void get_Location_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from location_reg order by LocName");
            while (rs.next()) {

                // Object code = rs.getString("LocCode");
                Object name = rs.getString("LocName");

                //cmb_defLocation.addItem(code);
                cmb_defLocation.addItem(name);

                AutoCompleteDecorator.decorate(cmb_defLocation);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void get_Rank_Details() {
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from rank");
            while (rs.next()) {

                Object code = rs.getString("RankCode");

                cmb_designation.addItem(code);
            }

        } catch (Exception e) {
        }

    }

    private void get_EMP_Details() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg   ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);
            // TextAutoCompleter ta1 = new TextAutoCompleter(txt_search);

            while (rs.next()) {

                //String code = rs.getString("EPFno");
                String name = rs.getString("NameWithInitials");
                //  String Fname = rs.getString("FullName");
                // String ID = rs.getString("NIC");
                //  String SName = rs.getString("Surname");

                //  ta.addItem(code);
                ta.addItem(name);
                //  ta1.addItem(Fname);
                // ta.addItem(SName);
                //  ta.addItem(ID);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void Company_combo_auto_fills() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg");

            while (rs.next()) {

                String code = rs.getString("ComCode");
                String name = rs.getString("ComName");

                cmb_defCompany.addItem(code);
                cmb_defCompany.addItem(name);

                AutoCompleteDecorator.decorate(cmb_defCompany);

            }

        } catch (Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nic = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_EPFno = new javax.swing.JTextField();
        txt_fullName = new javax.swing.JTextField();
        txt_NameWithInitials = new javax.swing.JTextField();
        txt_dob = new javax.swing.JTextField();
        txt_address = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_tel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_mobile1 = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cb_eduQuali = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        DateChooser_joinedDate = new com.toedter.calendar.JDateChooser();
        cmb_designation = new javax.swing.JComboBox();
        cb_birth = new javax.swing.JCheckBox();
        cb_nic = new javax.swing.JCheckBox();
        cb_grama = new javax.swing.JCheckBox();
        cb_police = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        txt_inCaseName = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_inCaseRelation = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_inCaseTel = new javax.swing.JTextField();
        txt_inCaseMobile = new javax.swing.JTextField();
        txt_inCaseAddress = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btn_save = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        txt_mobile2 = new javax.swing.JTextField();
        cmb_defCompany = new javax.swing.JComboBox();
        lbl_activeEMP = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cmb_civilS = new javax.swing.JComboBox();
        cmb_defLocation = new javax.swing.JComboBox();
        cmb_gender = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_eduQuali = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        txt_locName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        btn_print2 = new javax.swing.JButton();
        txt_policeArea = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_gramaDivision = new javax.swing.JTextField();
        cb_character = new javax.swing.JCheckBox();
        cb_service = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_workP1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txt_workP2 = new javax.swing.JTextField();
        txt_employer1 = new javax.swing.JTextField();
        txt_employer2 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        btn_print3 = new javax.swing.JButton();
        lbl_RankCat = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lbl_photo = new javax.swing.JLabel();
        btn_print1 = new javax.swing.JButton();
        DateChooser_posting_date = new com.toedter.calendar.JDateChooser();
        jButton8 = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_shiftRate = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txt_basicSalary = new javax.swing.JTextField();
        txt_BrAllo = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txt_Gross = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txt_Welfare = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        cb_active_epf = new javax.swing.JCheckBox();
        txt_MCAllowance = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        cmb_payType = new javax.swing.JComboBox<>();
        cb_extra_shift = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        txt_sur_name = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        txt_EmployeeNo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        btn_print4 = new javax.swing.JButton();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Registration");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "emp", "initials", "fullname", "rank", "epf"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.setRowHeight(23);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(350);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 140, 40));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Employee Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel2.setText("NIC No. :-");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 74, -1, 30));

        txt_nic.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_nic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nicFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nicFocusLost(evt);
            }
        });
        txt_nic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nicActionPerformed(evt);
            }
        });
        txt_nic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nicKeyPressed(evt);
            }
        });
        getContentPane().add(txt_nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 110, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 40, 40));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("Full Name :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, 20));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Name with Initials :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, 20));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Date of Birth");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 80, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Gender");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, 20));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("  Civil Status");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 80, 20));

        txt_EPFno.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_EPFno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_EPFnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_EPFnoFocusLost(evt);
            }
        });
        txt_EPFno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EPFnoActionPerformed(evt);
            }
        });
        txt_EPFno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EPFnoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_EPFnoKeyTyped(evt);
            }
        });
        getContentPane().add(txt_EPFno, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 70, -1));

        txt_fullName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_fullName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_fullNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_fullNameFocusLost(evt);
            }
        });
        txt_fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fullNameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_fullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 120, 390, -1));

        txt_NameWithInitials.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_NameWithInitials.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_NameWithInitialsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_NameWithInitialsFocusLost(evt);
            }
        });
        txt_NameWithInitials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NameWithInitialsActionPerformed(evt);
            }
        });
        getContentPane().add(txt_NameWithInitials, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 340, -1));

        txt_dob.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_dob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_dobFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_dobFocusLost(evt);
            }
        });
        txt_dob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dobActionPerformed(evt);
            }
        });
        getContentPane().add(txt_dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 140, -1));

        txt_address.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_addressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_addressFocusLost(evt);
            }
        });
        txt_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_addressActionPerformed(evt);
            }
        });
        getContentPane().add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 382, -1));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Address :-");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel15.setText("Designation :-");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, -1, 20));

        txt_tel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_tel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_telFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_telFocusLost(evt);
            }
        });
        txt_tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telActionPerformed(evt);
            }
        });
        getContentPane().add(txt_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 166, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("*");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 30, 20));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel16.setText("Mobile :-");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, 20));

        txt_mobile1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_mobile1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_mobile1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mobile1FocusLost(evt);
            }
        });
        txt_mobile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mobile1ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_mobile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 134, -1));

        txt_email.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 166, -1));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("Mobile 2 :-");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, 20));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel18.setText("Telephone :-");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 80, 40));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel20.setText("Email:-");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, 20));

        cb_eduQuali.setText("Edu. / Other Qualifications");
        cb_eduQuali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_eduQualiActionPerformed(evt);
            }
        });
        getContentPane().add(cb_eduQuali, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 380, 160, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Previous Employement Details :-");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, 20));

        DateChooser_joinedDate.setDateFormatString("yyyy-MM-dd");
        DateChooser_joinedDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DateChooser_joinedDateFocusLost(evt);
            }
        });
        getContentPane().add(DateChooser_joinedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 140, -1));

        cmb_designation.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_designation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=Select Designation=" }));
        cmb_designation.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_designationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_designation, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, 150, -1));

        cb_birth.setText("Birth Certificate");
        getContentPane().add(cb_birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, -1, -1));

        cb_nic.setText("NIC (Copy)");
        getContentPane().add(cb_nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, -1, -1));

        cb_grama.setText("Grama Niladhari Certificate");
        getContentPane().add(cb_grama, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, -1, -1));

        cb_police.setText("Police Report");
        getContentPane().add(cb_police, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 320, 100, -1));

        jLabel24.setText("Name :-");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, -1, 20));

        txt_inCaseName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_inCaseName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_inCaseNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_inCaseNameFocusLost(evt);
            }
        });
        txt_inCaseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inCaseNameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_inCaseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 170, -1));

        jLabel25.setText("Relationship :-");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, -1, 20));

        txt_inCaseRelation.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_inCaseRelation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_inCaseRelationFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_inCaseRelationFocusLost(evt);
            }
        });
        txt_inCaseRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inCaseRelationActionPerformed(evt);
            }
        });
        getContentPane().add(txt_inCaseRelation, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, 134, -1));

        jLabel26.setText("Mobile :-");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, -1, 20));

        txt_inCaseTel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_inCaseTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_inCaseTelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_inCaseTelFocusLost(evt);
            }
        });
        txt_inCaseTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inCaseTelActionPerformed(evt);
            }
        });
        getContentPane().add(txt_inCaseTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 160, -1));

        txt_inCaseMobile.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_inCaseMobile.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_inCaseMobileFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_inCaseMobileFocusLost(evt);
            }
        });
        txt_inCaseMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inCaseMobileActionPerformed(evt);
            }
        });
        getContentPane().add(txt_inCaseMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, 134, -1));

        txt_inCaseAddress.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_inCaseAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_inCaseAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_inCaseAddressFocusLost(evt);
            }
        });
        txt_inCaseAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inCaseAddressActionPerformed(evt);
            }
        });
        getContentPane().add(txt_inCaseAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 390, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1210, 10));

        btn_save.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 60, 120, 50));

        jButton7.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 140, 120, 40));

        txt_mobile2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_mobile2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_mobile2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mobile2FocusLost(evt);
            }
        });
        txt_mobile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mobile2ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_mobile2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 134, -1));

        cmb_defCompany.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_defCompany.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cmb_defCompany.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_defCompanyPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_defCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 170, -1));

        lbl_activeEMP.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        lbl_activeEMP.setText("Active  Employee ");
        getContentPane().add(lbl_activeEMP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, 20));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText("Location :-");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, 20));

        cmb_civilS.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_civilS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Married", "UnMarried" }));
        cmb_civilS.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_civilSPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmb_civilS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmb_civilSKeyPressed(evt);
            }
        });
        getContentPane().add(cmb_civilS, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 130, -1));

        cmb_defLocation.setEditable(true);
        cmb_defLocation.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_defLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cmb_defLocation.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_defLocationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 170, -1));

        cmb_gender.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Male", "Female" }));
        getContentPane().add(cmb_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 120, -1));

        jLabel35.setText("Telephone :-");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, 20));

        txt_eduQuali.setColumns(20);
        txt_eduQuali.setRows(5);
        txt_eduQuali.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_eduQualiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_eduQualiFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txt_eduQuali);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 296, 210, 110));

        jLabel36.setText("Address :-");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "In Case of Emergency"));
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 500, 150));

        jLabel37.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel37.setText("Posting Date :-");
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, -1, 20));

        btn_print.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        btn_print.setText("Print CV");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 340, 120, 40));

        txt_locName.setEditable(false);
        txt_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locName.setToolTipText("Location Incharge's Name");
        txt_locName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_locNameFocusLost(evt);
            }
        });
        getContentPane().add(txt_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 250, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("*");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 30, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 0, 0));
        jLabel31.setText("*");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 40, 20, 40));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setText("*");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, 30, 30));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 0, 0));
        jLabel39.setText("*");
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 30, 30));

        btn_print2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_print2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btn_print2.setText("Clear");
        btn_print2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 460, 120, 40));

        txt_policeArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_policeArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_policeAreaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_policeAreaFocusLost(evt);
            }
        });
        txt_policeArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_policeAreaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_policeArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 380, -1));

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel27.setText("Police Area :-");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, 20));

        jLabel28.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel28.setText("Gramaniladhari Division :-");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 20));

        txt_gramaDivision.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_gramaDivision.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_gramaDivisionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_gramaDivisionFocusLost(evt);
            }
        });
        txt_gramaDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gramaDivisionActionPerformed(evt);
            }
        });
        getContentPane().add(txt_gramaDivision, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 300, -1));

        cb_character.setText("Character Certificate");
        getContentPane().add(cb_character, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, -1, -1));

        cb_service.setText("Service Certificates");
        getContentPane().add(cb_service, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Certificates & Documents Accepted");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, -1, 20));

        jLabel41.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel41.setText("Work Period");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, -1, 20));

        txt_workP1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_workP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_workP1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_workP1FocusLost(evt);
            }
        });
        getContentPane().add(txt_workP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 120, -1));

        jLabel42.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel42.setText("2");
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 10, 20));

        txt_workP2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_workP2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_workP2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_workP2FocusLost(evt);
            }
        });
        getContentPane().add(txt_workP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 120, -1));

        txt_employer1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_employer1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_employer1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_employer1FocusLost(evt);
            }
        });
        getContentPane().add(txt_employer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 330, -1));

        txt_employer2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_employer2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_employer2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_employer2FocusLost(evt);
            }
        });
        getContentPane().add(txt_employer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 330, -1));

        jLabel43.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel43.setText("Employer");
        getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, -1, 20));

        jLabel44.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel44.setText("1");
        getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 10, 20));

        btn_print3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_print3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancel.png"))); // NOI18N
        btn_print3.setText("DeActivate");
        btn_print3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1067, 520, -1, 40));

        lbl_RankCat.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        lbl_RankCat.setText("Rank Category");
        getContentPane().add(lbl_RankCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 150, 20));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton3.setText("Salary Rates Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 580, 120, 50));

        jLabel49.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel49.setText("Company :-");
        getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, 20));

        jLabel50.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel50.setText("Joined Date :-");
        getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, -1, 20));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Maximize Window-25.png"))); // NOI18N
        jButton4.setToolTipText("Maximize Photo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 610, 30, 30));

        jButton5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton5.setText("Browse Photo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 610, 140, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 0, 51));
        jButton6.setText("X");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 443, -1, -1));

        lbl_photo.setBackground(new java.awt.Color(204, 255, 255));
        lbl_photo.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        lbl_photo.setAutoscrolls(true);
        lbl_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 200, 210));

        btn_print1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_print1.setText("Posting Order");
        btn_print1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 280, 120, 40));

        DateChooser_posting_date.setDateFormatString("yyyy-MM-dd");
        DateChooser_posting_date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DateChooser_posting_dateFocusLost(evt);
            }
        });
        getContentPane().add(DateChooser_posting_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 140, -1));

        jButton8.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton8.setText("Image Update");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 210, -1, 40));

        txt_search.setBackground(new java.awt.Color(204, 255, 204));
        txt_search.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        txt_search.setText("Search Here");
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
        });
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_searchMouseReleased(evt);
            }
        });
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 460, 23));

        jButton10.setBackground(new java.awt.Color(204, 204, 255));
        jButton10.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jButton10.setText("Employee Salary Details");
        jButton10.setToolTipText("Click to view Salary Details");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 420, 330, 30));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setToolTipText("Please fill only the applicable fields");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_shiftRate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_shiftRate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_shiftRateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_shiftRateFocusLost(evt);
            }
        });
        txt_shiftRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_shiftRateActionPerformed(evt);
            }
        });
        txt_shiftRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_shiftRateKeyTyped(evt);
            }
        });
        jPanel1.add(txt_shiftRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 70, -1));

        jLabel45.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel45.setText("Basic    :-");
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 60, 20));

        txt_basicSalary.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_basicSalary.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_basicSalaryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_basicSalaryFocusLost(evt);
            }
        });
        txt_basicSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_basicSalaryActionPerformed(evt);
            }
        });
        txt_basicSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_basicSalaryKeyTyped(evt);
            }
        });
        jPanel1.add(txt_basicSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 70, -1));

        txt_BrAllo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_BrAllo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_BrAlloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_BrAlloFocusLost(evt);
            }
        });
        txt_BrAllo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BrAlloActionPerformed(evt);
            }
        });
        txt_BrAllo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_BrAlloKeyTyped(evt);
            }
        });
        jPanel1.add(txt_BrAllo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 70, -1));

        jLabel46.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel46.setText("BR Allowance :-");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        txt_Gross.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Gross.setToolTipText("Not Applicable for Security Guards ");
        txt_Gross.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_GrossFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_GrossFocusLost(evt);
            }
        });
        txt_Gross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GrossActionPerformed(evt);
            }
        });
        txt_Gross.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_GrossKeyTyped(evt);
            }
        });
        jPanel1.add(txt_Gross, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 70, -1));

        jLabel48.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel48.setText("Gross Salary   :-");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel52.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel52.setText("Max Shifts   :-");
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, 20));

        txt_Welfare.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_Welfare.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_WelfareFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_WelfareFocusLost(evt);
            }
        });
        txt_Welfare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_WelfareActionPerformed(evt);
            }
        });
        txt_Welfare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_WelfareKeyTyped(evt);
            }
        });
        jPanel1.add(txt_Welfare, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 70, -1));

        jLabel51.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel51.setText("Welfare Deduc. :-");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 100, 20));

        cb_active_epf.setBackground(new java.awt.Color(204, 204, 255));
        cb_active_epf.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cb_active_epf.setText("EPF Active");
        cb_active_epf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_active_epfMouseClicked(evt);
            }
        });
        jPanel1.add(cb_active_epf, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        txt_MCAllowance.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_MCAllowance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_MCAllowanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_MCAllowanceFocusLost(evt);
            }
        });
        txt_MCAllowance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_MCAllowanceKeyTyped(evt);
            }
        });
        jPanel1.add(txt_MCAllowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 70, -1));

        jLabel53.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel53.setText("Pay Type ;");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 100, 20));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton2.setText("Bank Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 120, 30));

        jLabel54.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel54.setText("Fuel Allowance :-");
        jPanel1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 100, 20));

        cmb_payType.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cmb_payType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Hand", "Bank", "Slip" }));
        cmb_payType.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                cmb_payTypePopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_payTypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel1.add(cmb_payType, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 100, 25));

        cb_extra_shift.setBackground(new java.awt.Color(204, 204, 255));
        cb_extra_shift.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        cb_extra_shift.setText("Extra Shift Allowed");
        cb_extra_shift.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_extra_shiftMouseClicked(evt);
            }
        });
        jPanel1.add(cb_extra_shift, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 330, 210));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("               Surname    :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        txt_sur_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_sur_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_sur_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_sur_nameFocusLost(evt);
            }
        });
        txt_sur_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sur_nameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_sur_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 340, -1));

        jButton9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        jButton9.setText("Refresh Details");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 0, -1, -1));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(204, 0, 0));
        jLabel47.setText("*");
        getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 30, 20));

        jCheckBox1.setBackground(new java.awt.Color(204, 204, 204));
        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(153, 0, 0));
        jCheckBox1.setText("AUTO EPF No.");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 120, -1));

        jLabel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Official Use"));
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 540, 380));

        txt_EmployeeNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_EmployeeNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_EmployeeNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_EmployeeNoFocusLost(evt);
            }
        });
        txt_EmployeeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmployeeNoActionPerformed(evt);
            }
        });
        txt_EmployeeNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EmployeeNoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_EmployeeNoKeyTyped(evt);
            }
        });
        getContentPane().add(txt_EmployeeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 80, -1));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("EPF No. :-");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, 20));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText("Emp. No.");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 60, 20));

        jCheckBox2.setBackground(new java.awt.Color(204, 204, 204));
        jCheckBox2.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(153, 0, 0));
        jCheckBox2.setText("AUTO EMP No.");
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 130, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Personal Information"));
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 450));

        btn_print4.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_print4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_print4.setText("Delete");
        btn_print4.setEnabled(false);
        btn_print4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 400, 120, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

//        try {
//            JFileChooser jfc = new JFileChooser();
//            jfc.showOpenDialog(null);
//            File f = jfc.getSelectedFile();
//            Image image = ImageIO.read(f);
//
//           lbl_photo.setIcon(new ImageIcon(image.getScaledInstance(150, 150, image.SCALE_SMOOTH)));
//           
//            filename = f.getAbsolutePath();
//            txt_designation.setText(filename);
//            
//            
//            BufferedImage buffered = ((ToolkitImage) image).getBufferedImage();
//            
//
//            File imagefile = new File(filename);
//            FileInputStream fis = new FileInputStream(imagefile); 
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//            for (int readNum; (readNum = fis.read(buf)) != -1;) {
//                bos.write(buf, 0, readNum);
//
//            }
//            EMPImage = bos.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(null);
            File f = jfc.getSelectedFile();
            Image image = ImageIO.read(f);

            lbl_photo.setIcon(new ImageIcon(image.getScaledInstance(200, 200, image.SCALE_SMOOTH)));

            filename = f.getAbsolutePath();
            // txt_joinedDate.setText(filename);

            // BufferedImage buffered = ((ToolkitImage) image).getBufferedImage();
            File imagefile = new File(filename);
            FileInputStream fis = new FileInputStream(imagefile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);

            }
            EMPImage = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_nicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nicFocusLost
        txt_nic.setBackground(Color.WHITE);
    }//GEN-LAST:event_txt_nicFocusLost

    private void txt_nicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nicActionPerformed
        txt_EPFno.grabFocus();

    }//GEN-LAST:event_txt_nicActionPerformed

    private void txt_EPFnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EPFnoActionPerformed
        txt_EmployeeNo.grabFocus();
    }//GEN-LAST:event_txt_EPFnoActionPerformed

    private void txt_fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fullNameActionPerformed
        txt_NameWithInitials.grabFocus();
    }//GEN-LAST:event_txt_fullNameActionPerformed

    private void txt_NameWithInitialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameWithInitialsActionPerformed
        txt_address.grabFocus();
    }//GEN-LAST:event_txt_NameWithInitialsActionPerformed

    private void txt_dobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dobActionPerformed
        txt_tel.grabFocus();
    }//GEN-LAST:event_txt_dobActionPerformed

    private void txt_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_addressActionPerformed
        txt_dob.grabFocus();
    }//GEN-LAST:event_txt_addressActionPerformed

    private void txt_telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telActionPerformed
        txt_mobile1.grabFocus();
    }//GEN-LAST:event_txt_telActionPerformed

    private void txt_mobile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mobile1ActionPerformed
        txt_mobile2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mobile1ActionPerformed

    private void txt_mobile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mobile2ActionPerformed
        txt_email.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mobile2ActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        txt_policeArea.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void txt_EPFnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EPFnoFocusGained
        txt_EPFno.setBackground(Color.ORANGE); // TODO add your handling code here:
    }//GEN-LAST:event_txt_EPFnoFocusGained

    private void txt_nicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nicFocusGained
        txt_nic.setBackground(Color.ORANGE);      // TODO add your handling code here:
    }//GEN-LAST:event_txt_nicFocusGained

    private void txt_EPFnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EPFnoFocusLost
        txt_EPFno.setBackground(Color.WHITE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EPFnoFocusLost

    private void txt_fullNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_fullNameFocusGained
        txt_fullName.setBackground(Color.ORANGE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fullNameFocusGained

    private void txt_fullNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_fullNameFocusLost
        txt_fullName.setBackground(Color.WHITE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fullNameFocusLost

    private void txt_NameWithInitialsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NameWithInitialsFocusGained
        txt_NameWithInitials.setBackground(Color.ORANGE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameWithInitialsFocusGained

    private void txt_NameWithInitialsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NameWithInitialsFocusLost
        txt_NameWithInitials.setBackground(Color.WHITE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameWithInitialsFocusLost

    private void txt_dobFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_dobFocusGained
        txt_dob.setBackground(Color.ORANGE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dobFocusGained

    private void txt_dobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_dobFocusLost
        txt_dob.setBackground(Color.WHITE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dobFocusLost

    private void txt_addressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_addressFocusGained
        txt_address.setBackground(Color.ORANGE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressFocusGained

    private void txt_addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_addressFocusLost
        txt_address.setBackground(Color.WHITE);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressFocusLost

    private void txt_telFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_telFocusGained
        txt_tel.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_telFocusGained

    private void txt_telFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_telFocusLost
        txt_tel.setBackground(Color.WHITE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_telFocusLost

    private void txt_mobile1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mobile1FocusGained
        txt_mobile1.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_mobile1FocusGained

    private void txt_mobile1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mobile1FocusLost
        txt_mobile1.setBackground(Color.WHITE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_mobile1FocusLost

    private void txt_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusGained
        txt_email.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailFocusGained

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        txt_email.setBackground(Color.WHITE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailFocusLost

    private void txt_mobile2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mobile2FocusGained
        txt_mobile2.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_mobile2FocusGained

    private void txt_mobile2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mobile2FocusLost
        txt_mobile2.setBackground(Color.WHITE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_mobile2FocusLost

    private void txt_shiftRateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shiftRateFocusGained
//        txt_shiftRate.setBackground(Color.ORANGE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_shiftRateFocusGained

    private void txt_shiftRateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shiftRateFocusLost
        txt_shiftRate.setBackground(Color.WHITE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_shiftRateFocusLost

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

        if (txt_locName.getText().isEmpty() | cmb_defCompany.getSelectedIndex() == 0 | cmb_designation.getSelectedIndex() == 0 | cmb_payType.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(rootPane, "Please Select the Posting Location , Company , Designation & Pay Type of this Employee");
        } else {

            empty_txt_to_zeros();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String JoinedDate = sdf.format(DateChooser_joinedDate.getDate());
            String PostingDate = null;
            if (DateChooser_posting_date.getDate() == null) {

            } else {
                PostingDate = sdf.format(DateChooser_posting_date.getDate());
            }
//        txt_joinedDate.setText(date);

//Personal Details
            String nic = txt_nic.getText();

            String fullName = txt_fullName.getText();
            String initials = txt_NameWithInitials.getText();
            String address = txt_address.getText();
            String dob = txt_dob.getText();
            String gender = cmb_gender.getSelectedItem().toString();
            String civilS = cmb_civilS.getSelectedItem().toString();
            String tel = txt_tel.getText();
            String mob1 = txt_mobile1.getText();
            String mob2 = txt_mobile2.getText();
            String email = txt_email.getText();
            String policerpt = txt_policeArea.getText();
            String gramarpt = txt_gramaDivision.getText();
//Official Details        
            String company = cmb_defCompany.getSelectedItem().toString();
            String location = cmb_defLocation.getSelectedItem().toString();
            String designation = cmb_designation.getSelectedItem().toString();

            String eduCert = txt_eduQuali.getText();
            String workp1 = txt_workP1.getText();
            String workp2 = txt_workP2.getText();
            String emp1 = txt_employer1.getText();
            String emp2 = txt_employer2.getText();
            String rankCat = lbl_RankCat.getText();//    pst.setString(42, rankCat);//RankCategory
//InCase of Imagency Details
            String inCasename = txt_inCaseName.getText();
            String inCaseAddress = txt_inCaseAddress.getText();
            String inCaseTel = txt_inCaseTel.getText();
            String inCaseMob = txt_inCaseMobile.getText();
            String inCaseRel = txt_inCaseRelation.getText();

            Double str_basic = Double.parseDouble(txt_basicSalary.getText());
            String basic = String.format("%.2f", str_basic);

            Double str_br = Double.parseDouble(txt_BrAllo.getText());
            String br = String.format("%.2f", str_br);

            Double str_gross = Double.parseDouble(txt_Gross.getText());
            String gross = String.format("%.2f", str_gross);

            Double rate = Double.parseDouble(txt_shiftRate.getText());
            String ShiftRate = String.format("%.2f", rate);

            Double welfare = Double.parseDouble(txt_Welfare.getText());
            String Welfare = String.format("%.2f", welfare);

            Double MCAllo = Double.parseDouble(txt_MCAllowance.getText());
            String MCAllowance = String.format("%.2f", MCAllo);

            if (initials.isEmpty() || txt_EmployeeNo.getText().isEmpty() || DateChooser_joinedDate == null || location.isEmpty() || company.isEmpty() || designation.equals("=Select Designation=")) {
                Color c1 = new Color(255, 161, 161);

                if (initials.isEmpty()) {
                    txt_NameWithInitials.setBackground(c1);
                }
                if (txt_EmployeeNo.getText().isEmpty()) {
                    txt_EmployeeNo.setBackground(c1);
                }
//                if (address.isEmpty()) {
//                    txt_address.setBackground(c1);
//                }
//                

//                if (address.isEmpty()) {
//                    txt_address.setBackground(c1);
//                }
                JOptionPane.showMessageDialog(rootPane, "Look for the Highlighted Fields and Star marked Feilds. Those Feilds can't be Empty.");

            } else {

                try {

                    if (cb_birth.isSelected()) {
                        BirthCert = "YES";
                    } else {
                        BirthCert = "NO";
                    }
                    if (cb_grama.isSelected()) {
                        GramaCert = "YES";
                    } else {
                        GramaCert = "NO";
                    }
                    if (cb_character.isSelected()) {
                        CharacterCert = "YES";
                    } else {
                        CharacterCert = "NO";
                    }
                    if (cb_police.isSelected()) {
                        PoliceCert = "YES";
                    } else {
                        PoliceCert = "NO";
                    }
                    if (cb_nic.isSelected()) {
                        NIC = "YES";
                    } else {
                        NIC = "NO";
                    }
                    if (cb_service.isSelected()) {
                        ServiceCert = "YES";
                    } else {
                        ServiceCert = "NO";
                    }
                    if (cb_eduQuali.isSelected()) {
                        edu = "YES";
                    } else {
                        edu = "NO";
                    }

                    if (cb_active_epf.isSelected()) {
                        Active_EPF = "1";
                    } else {
                        Active_EPF = "0";
                    }
//
                    //Prevent duplicating different employees in same EPF number  when save concurrently
                    if (jCheckBox1.isSelected()) {
                        get_latest_EPFno();
                    } else {

                    }

                    //Prevent duplicating different employees in same EMP number  when save concurrently
                    if (jCheckBox2.isSelected()) {
                        get_latest_EMPno();
                    } else {

                    }

                    String employee_no = txt_EmployeeNo.getText();
                    String empid = txt_EPFno.getText();

                    String sql = "INSERT INTO employee_reg (NIC,EPFno,FullName,NameWithInitials,Address,DOB,Gender,CivilStatus,Tel,Mob1,Mob2,Email,PoliceArea,GramaDivision,InCaseName,InCaseRel,IncaseTel,IncaseMob,IncaseAddress,DefCompany,DefLocation,Designation,JoinedDate,ShiftRate,BirthCert,GramaCert,PoliceCert,CharacterCert,NICcopy,ServiceCert,EduQuali,EduQualiDetails,EMPImage,IsResigned,WorkP1,emp1,WorkP2,emp2,BasicSalary,BRAllowance,GrossSalary,RankCategory,PostingDate,Welfare,ActiveEPF,Surname,MCAllowance,PayType,EmployeeNo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    pst = c.prepareStatement(sql);

                    pst.setString(1, nic);
                    pst.setString(2, empid);
                    pst.setString(3, fullName);
                    pst.setString(4, initials);
                    pst.setString(5, address);
                    pst.setString(6, dob);
                    pst.setString(7, gender);
                    pst.setString(8, civilS);
                    pst.setString(9, tel);
                    pst.setString(10, mob1);
                    pst.setString(11, mob2);
                    pst.setString(12, email);
                    pst.setString(13, policerpt);
                    pst.setString(14, txt_gramaDivision.getText());
                    pst.setString(15, inCasename);
                    pst.setString(16, inCaseRel);
                    pst.setString(17, inCaseTel);
                    pst.setString(18, inCaseMob);
                    pst.setString(19, inCaseAddress);
                    pst.setString(20, company);
                    pst.setString(21, location);
                    pst.setString(22, designation);
                    pst.setString(23, JoinedDate);
                    pst.setString(24, ShiftRate);
                    pst.setString(25, BirthCert);
                    pst.setString(26, GramaCert);
                    pst.setString(27, PoliceCert);
                    pst.setString(28, CharacterCert);
                    pst.setString(29, NIC);
                    pst.setString(30, ServiceCert);
                    pst.setString(31, edu);
                    pst.setString(32, eduCert);
                    pst.setBytes(33, EMPImage);
                    pst.setString(34, isResigned);
                    pst.setString(35, workp1);
                    pst.setString(36, emp1);
                    pst.setString(37, workp2);
                    pst.setString(38, emp2);
                    pst.setString(39, basic);
                    pst.setString(40, br);
                    pst.setString(41, gross);
                    pst.setString(42, rankCat);//RankCategory
                    pst.setString(43, PostingDate);
                    pst.setString(44, Welfare);
                    pst.setString(45, Active_EPF);
                    pst.setString(46, txt_sur_name.getText());
                    pst.setString(47, MCAllowance);
                    pst.setString(48, cmb_payType.getSelectedItem().toString());
                    pst.setString(49, employee_no);

                    pst.execute();
                    JOptionPane.showMessageDialog(rootPane, " Employee Details Saved... ");

                    clear();
//max_epfno();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void cmb_designationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_designationPopupMenuWillBecomeInvisible
        try {
            lbl_RankCat.setText("");
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from rank where  RankCode= '" + cmb_designation.getSelectedItem().toString() + "' ");
            while (rs.next()) {

                String name = rs.getString("Category");

                lbl_RankCat.setText(name);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmb_designationPopupMenuWillBecomeInvisible
    DefaultListModel model = new DefaultListModel();
    private void cmb_civilSPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_civilSPopupMenuWillBecomeInvisible
//        String s = cmb_civilS.getSelectedItem().toString();
//        model.addElement(s);
//        jList1.setModel(model);
    }//GEN-LAST:event_cmb_civilSPopupMenuWillBecomeInvisible

    private void cmb_civilSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_civilSKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            String s = cmb_civilS.getSelectedItem().toString();
//            model.addElement(s);
//            jList1.setModel(model);
//
//        }  // TODO add your handling code here:
    }//GEN-LAST:event_cmb_civilSKeyPressed

    private void txt_eduQualiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_eduQualiFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_eduQualiFocusGained

    private void txt_eduQualiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_eduQualiFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_eduQualiFocusLost

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed

        try {

            Connection conn = (Connection) DbConnection.getconnection();

            JasperDesign jd = JRXmlLoader.load("Reports\\EMP_BIO.jrxml");
            String sql = "SELECT * FROM employee_reg where EPFno='" + txt_EPFno.getText() + "'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_nicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nicKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (txt_nic.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "You are Leaving NIC Field Empty");
                // System.out.println("NIC Empty");

            } else {
                //{

                //NIC Query goes here
                String nic = txt_nic.getText();
                int nicLength = nic.length();
                //System.out.println(nicLength);

                //*********************** IF OLD NIC NUMBER *****************************
                if (nicLength == 10) {

                    String LastLetter = nic.substring(9, 10);

                    if (LastLetter.equals("V") || LastLetter.equals("X")) {
                        System.out.println("Last Letter V or X");

                        String year = nic.substring(0, 2);//922680914==> 92
                        String y = ("19" + year);//jTextField2.setText

                        String month = nic.substring(2, 5);//922680914 ==> 268
                        // jTextField3.setText(month);
                        int i = Integer.parseInt(month);
                        if (i <= 500) {
                            cmb_gender.setSelectedItem("Male");
                            String m;
                            int d;

                            if (i > 0 & i <= 31) {
                                m = "Jan";
                                d = i;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 31 & i <= 60) {
                                m = "Feb";
                                d = i - 31;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 60 & i <= 91) {
                                m = "Mar";
                                d = i - 60;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 91 & i <= 121) {
                                m = "Apr";
                                d = i - 91;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 121 & i <= 152) {
                                m = "May";
                                d = i - 121;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 152 & i <= 182) {
                                m = "Jun";
                                d = i - 152;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 182 & i <= 213) {
                                m = "Jul";
                                d = i - 182;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 213 & i <= 244) {
                                m = "Aug";
                                d = i - 213;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 244 & i <= 274) {
                                m = "Sep";
                                d = i - 244;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 274 & i <= 305) {
                                m = "Oct";
                                d = i - 274;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 305 & i <= 335) {
                                m = "Nov";
                                d = i - 305;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 335 & i <= 366) {
                                m = "Dec";
                                d = i - 335;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                        } else if (i > 500) {
                            cmb_gender.setSelectedItem("Female");
                            String m;
                            int d;

                            if (i > 500 & i <= 531) {
                                m = "Jan";
                                d = i - 500;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 531 & i <= 560) {
                                m = "Feb";
                                d = i - 531;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 560 & i <= 591) {
                                m = "Mar";
                                d = i - 560;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 591 & i <= 621) {
                                m = "Apr";
                                d = i - 591;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 621 & i <= 652) {
                                m = "May";
                                d = i - 621;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 652 & i <= 682) {
                                m = "Jun";
                                d = i - 652;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 682 & i <= 713) {
                                m = "Jul";
                                d = i - 682;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 713 & i <= 744) {
                                m = "Aug";
                                d = i - 713;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 744 & i <= 774) {
                                m = "Sep";
                                d = i - 744;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 774 & i <= 805) {
                                m = "Oct";
                                d = i - 774;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 805 & i <= 835) {
                                m = "Nov";
                                d = i - 805;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                            if (i > 835 & i <= 866) {
                                m = "Dec";
                                d = i - 835;
                                txt_dob.setText(y + " - " + m + " - " + d);
                            }

                        }

                    } else if (!LastLetter.equals("V") || !LastLetter.equals("X")) {

                        JOptionPane.showMessageDialog(rootPane, "<html>Invalid NIC Number.<br> <br>Last Letter Should be V or X in OLD* NIC numbers<br><br> *OLD = Issued before 01.01.2016</html>");
                    }

                    //System.out.println("OLD NIC");
                } //******************* IF NEW NIC NUMBER***************************************           
                else if (nicLength == 12) {

                    String year = nic.substring(0, 4);
                    String month = nic.substring(4, 7);

                    int i = Integer.parseInt(month);

                    if (i <= 500) {

                        cmb_gender.setSelectedItem("Male");

                        String m;
                        int d;

                        if (i > 0 & i <= 31) {
                            m = "Jan";
                            d = i;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 31 & i <= 60) {
                            m = "Feb";
                            d = i - 31;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 60 & i <= 91) {
                            m = "Mar";
                            d = i - 60;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 91 & i <= 121) {
                            m = "Apr";
                            d = i - 91;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 121 & i <= 152) {
                            m = "May";
                            d = i - 121;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 152 & i <= 182) {
                            m = "Jun";
                            d = i - 152;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 182 & i <= 213) {
                            m = "Jul";
                            d = i - 182;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 213 & i <= 244) {
                            m = "Aug";
                            d = i - 213;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 244 & i <= 274) {
                            m = "Sep";
                            d = i - 244;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 274 & i <= 305) {
                            m = "Oct";
                            d = i - 274;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 305 & i <= 335) {
                            m = "Nov";
                            d = i - 305;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 335 & i <= 366) {
                            m = "Dec";
                            d = i - 335;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                    } else if (i > 500) {
                        cmb_gender.setSelectedItem("Female");
                        String m;
                        int d;

                        if (i > 500 & i <= 531) {
                            m = "Jan";
                            d = i - 500;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 531 & i <= 560) {
                            m = "Feb";
                            d = i - 531;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 560 & i <= 591) {
                            m = "Mar";
                            d = i - 560;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 591 & i <= 621) {
                            m = "Apr";
                            d = i - 591;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 621 & i <= 652) {
                            m = "May";
                            d = i - 621;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 652 & i <= 682) {
                            m = "Jun";
                            d = i - 652;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 682 & i <= 713) {
                            m = "Jul";
                            d = i - 682;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 713 & i <= 744) {
                            m = "Aug";
                            d = i - 713;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 744 & i <= 774) {
                            m = "Sep";
                            d = i - 744;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 774 & i <= 805) {
                            m = "Oct";
                            d = i - 774;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 805 & i <= 835) {
                            m = "Nov";
                            d = i - 805;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                        if (i > 835 & i <= 866) {
                            m = "Dec";
                            d = i - 835;
                            txt_dob.setText(year + " - " + m + " - " + d);
                        }

                    }

                } else {

                    JOptionPane.showMessageDialog(rootPane, "Invalid NIC number");
                }

                // }
            }

        }
    }//GEN-LAST:event_txt_nicKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String JoinedDate = null;
        String PostingDate = null;
        if (DateChooser_posting_date.getDate() == null) {
            PostingDate = "2019-08-01";
        } else {
            PostingDate = sdf.format(DateChooser_posting_date.getDate());
        }

        if (DateChooser_joinedDate.getDate() == null) {
            JoinedDate = "2019-08-01";
        } else {
            JoinedDate = sdf.format(DateChooser_joinedDate.getDate());
        }

//        txt_joinedDate.setText(date);
//Personal Details
        String nic = txt_nic.getText();
        String empid = txt_EPFno.getText();
        String fullName = txt_fullName.getText();
        String initials = txt_NameWithInitials.getText();
        String address = txt_address.getText();
        String dob = txt_dob.getText();
        String gender = null;
        if (cmb_gender.getSelectedItem() == null) {
            cmb_gender.setSelectedIndex(0);
        }
        String civilS = null;
        if (cmb_civilS.getSelectedItem() == null) {
            cmb_civilS.setSelectedIndex(0);
        }
        String tel = txt_tel.getText();
        String mob1 = txt_mobile1.getText();
        String mob2 = txt_mobile2.getText();
        String email = txt_email.getText();
        String policerpt = txt_policeArea.getText();
        String gramarpt = txt_gramaDivision.getText();
//Official Details        
        String company = cmb_defCompany.getSelectedItem().toString();
        String location = cmb_defLocation.getSelectedItem().toString();
        String designation = cmb_designation.getSelectedItem().toString();
        String ShiftRate = txt_shiftRate.getText();
        String eduCert = txt_eduQuali.getText();
        String workp1 = txt_workP1.getText();
        String workp2 = txt_workP2.getText();
        String emp1 = txt_employer1.getText();
        String emp2 = txt_employer2.getText();
        String rankCat = lbl_RankCat.getText();
//InCase of Imagency Details
        String inCasename = txt_inCaseName.getText();
        String inCaseAddress = txt_inCaseAddress.getText();
        String inCaseTel = txt_inCaseTel.getText();
        String inCaseMob = txt_inCaseMobile.getText();
        String inCaseRel = txt_inCaseRelation.getText();

        String basic = txt_basicSalary.getText();
        String br = txt_BrAllo.getText();
        String gross = txt_Gross.getText();
        String employee_no = txt_EmployeeNo.getText();

        try {

//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + empid + "'");
//            
//            while (rs.next()) {
//                
//                String EMPID = rs.getString("EPFno");
//                
//                if (EMPID.equals(empid)) {
            if (cb_birth.isSelected()) {
                BirthCert = "YES";
            } else {
                BirthCert = "NO";
            }
            if (cb_grama.isSelected()) {
                GramaCert = "YES";
            } else {
                GramaCert = "NO";
            }
            if (cb_character.isSelected()) {
                CharacterCert = "YES";
            } else {
                CharacterCert = "NO";
            }
            if (cb_police.isSelected()) {
                PoliceCert = "YES";
            } else {
                PoliceCert = "NO";
            }
            if (cb_nic.isSelected()) {
                NIC = "YES";
            } else {
                NIC = "NO";
            }
            if (cb_service.isSelected()) {
                ServiceCert = "YES";
            } else {
                ServiceCert = "NO";
            }
            if (cb_eduQuali.isSelected()) {
                edu = "YES";
            } else {
                edu = "NO";
            }

//                    if (cb_salary_cat.isSelected()) {
//                        SikuraEmpShiftrateBasisSalary = "1";
//                    } else {
//                        SikuraEmpShiftrateBasisSalary = "0";
//                    }
            String sql = "UPDATE employee_reg SET NIC=?,FullName=?,NameWithInitials=?,Address=?,DOB=?,Gender=?,CivilStatus=?,"
                    + "Tel=?,Mob1=?,Mob2=?,Email=?,PoliceArea=?,GramaDivision=?,InCaseName=?,InCaseRel=?,IncaseTel=?,"
                    + "IncaseMob=?,IncaseAddress=?,DefCompany=?,DefLocation=?,Designation=?,JoinedDate=?,BirthCert=?,"
                    + "GramaCert=?,PoliceCert=?,CharacterCert=?,NICcopy=?,ServiceCert=?,EduQuali=?,EduQualiDetails=?,"
                    + "IsResigned=?,WorkP1=?,emp1=?,WorkP2=?,emp2=?,RankCategory=?,PostingDate=?,Surname=?,"
                    + "EPFno=?   WHERE EmployeeNo=?";

            pst = c.prepareStatement(sql);

            pst.setString(1, nic);
            //pst.setString(2, empid);
            pst.setString(2, fullName);
            pst.setString(3, initials);
            pst.setString(4, address);
            pst.setString(5, dob);
            pst.setString(6, gender);
            pst.setString(7, civilS);
            pst.setString(8, tel);
            pst.setString(9, mob1);
            pst.setString(10, mob2);
            pst.setString(11, email);
            pst.setString(12, policerpt);
            pst.setString(13, gramarpt);
            pst.setString(14, inCasename);
            pst.setString(15, inCaseRel);
            pst.setString(16, inCaseTel);
            pst.setString(17, inCaseMob);
            pst.setString(18, inCaseAddress);
            pst.setString(19, company);
            pst.setString(20, location);
            pst.setString(21, designation);
            pst.setString(22, JoinedDate);
            // pst.setString(23, ShiftRate);
            pst.setString(23, BirthCert);
            pst.setString(24, GramaCert);
            pst.setString(25, PoliceCert);
            pst.setString(26, CharacterCert);
            pst.setString(27, NIC);
            pst.setString(28, ServiceCert);
            pst.setString(29, edu);
            pst.setString(30, eduCert);
            //pst.setBytes(32, EMPImage);
            pst.setString(31, isResigned);
            pst.setString(32, workp1);
            pst.setString(33, emp1);
            pst.setString(34, workp2);
            pst.setString(35, emp2);
            pst.setString(36, rankCat);
            pst.setString(37, PostingDate);
            pst.setString(38, txt_sur_name.getText());
            pst.setString(39, empid);

            pst.setString(40, employee_no);

            pst.execute();
            JOptionPane.showMessageDialog(rootPane, "Employee Details Succefully Updated");
            pst.close();
            clear();

//                }
//                
//            }
            // 
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//
//            String sql1 = "UPDATE employee_reg SET EMPImage=? WHERE EPFno='" + txt_EPFno.getText() + "'";
//
//            pst = c.prepareStatement(sql1);
//
//            pst.setBytes(1, EMPImage);
//            pst.execute();
//
//            JOptionPane.showMessageDialog(rootPane, " Employee Image Saved... ");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }//GEN-LAST:event_jButton7ActionPerformed

    static String birth;
    static String grama;
    static String police;
    static String charac;


    private void txt_locNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locNameFocusGained

    private void txt_locNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locNameFocusLost

    private void DateChooser_joinedDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateChooser_joinedDateFocusLost

    }//GEN-LAST:event_DateChooser_joinedDateFocusLost

    private void txt_EPFnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EPFnoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {

                jPanel1.setVisible(false);

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg where EPFno = '" + txt_EPFno.getText() + "' ");
                if (rs.next()) {

                    byte[] imagedata = rs.getBytes("EMPImage");

                    if (imagedata == null) {
                        lbl_photo.setText("No Image");
                        lbl_photo.setForeground(Color.red);

                    } else {

                        format = new ImageIcon(imagedata);
                        lbl_photo.setIcon(format);

                    }

                    txt_nic.setText(rs.getString("NIC"));
                    txt_EPFno.setText(rs.getString("EPFno"));
                    txt_fullName.setText(rs.getString("FullName"));
                    txt_NameWithInitials.setText(rs.getString("NameWithInitials"));
                    txt_dob.setText(rs.getString("DOB"));
                    cmb_gender.setSelectedItem(rs.getString("Gender"));
                    cmb_civilS.setSelectedItem(rs.getString("CivilStatus"));
                    txt_address.setText(rs.getString("Address"));
                    txt_tel.setText(rs.getString("Tel"));
                    txt_mobile1.setText(rs.getString("Mob1"));
                    txt_mobile2.setText(rs.getString("Mob2"));
                    txt_email.setText(rs.getString("Email"));
                    txt_policeArea.setText(rs.getString("PoliceArea"));
                    txt_gramaDivision.setText(rs.getString("GramaDivision"));

                    txt_inCaseName.setText(rs.getString("InCaseName"));
                    txt_inCaseRelation.setText(rs.getString("InCaseRel"));
                    txt_inCaseTel.setText(rs.getString("IncaseTel"));
                    txt_inCaseMobile.setText(rs.getString("IncaseMob"));
                    txt_inCaseAddress.setText(rs.getString("IncaseAddress"));

                    cmb_defCompany.setSelectedItem(rs.getString("DefCompany"));
                    cmb_defLocation.setSelectedItem(rs.getString("DefLocation"));
                    cmb_designation.setSelectedItem(rs.getString("Designation"));
                    DateChooser_joinedDate.setDate(rs.getDate("JoinedDate"));
                    DateChooser_posting_date.setDate(rs.getDate("PostingDate"));
                    txt_shiftRate.setText(rs.getString("ShiftRate"));
                    txt_eduQuali.setText(rs.getString("EduQualiDetails"));
                    txt_workP1.setText(rs.getString("WorkP1"));
                    txt_workP2.setText(rs.getString("WorkP2"));
                    txt_employer1.setText(rs.getString("emp1"));
                    txt_employer2.setText(rs.getString("emp2"));

                    txt_basicSalary.setText(rs.getString("BasicSalary"));
                    txt_Gross.setText(rs.getString("GrossSalary"));
                    txt_BrAllo.setText(rs.getString("BRAllowance"));
                    txt_Welfare.setText(rs.getString("Welfare"));

                    txt_sur_name.setText(rs.getString("Surname"));

                    String active_epf = rs.getString("ActiveEPF");

                    if (active_epf.equals("1")) {
                        cb_active_epf.setSelected(true);
                    } else {
                        cb_active_epf.setSelected(false);

                    }

                    String extra_shift = rs.getString("ExtraShiftAllowed");

                    if (extra_shift.equals("1")) {
                        cb_extra_shift.setSelected(true);
                    } else {
                        cb_extra_shift.setSelected(false);
                    }

                    String resign = rs.getString("IsResigned");

                    if (resign.equals("0")) {

                        lbl_activeEMP.setText("Active Employee");
                        lbl_activeEMP.setBackground(Color.green);
                        lbl_activeEMP.setOpaque(true);

                    } else {
                        Color c = new Color(255, 161, 161);
                        lbl_activeEMP.setText("Resigned Employee");
                        lbl_activeEMP.setBackground(c);
                        lbl_activeEMP.setOpaque(true);
                    }

                    if (rs.getString("BirthCert").equals("YES")) {
                        cb_birth.setSelected(true);
                    } else {
                        cb_birth.setSelected(false);
                    }

                    if (rs.getString("GramaCert").equals("YES")) {
                        cb_grama.setSelected(true);
                    } else {
                        cb_grama.setSelected(false);
                    }

                    if (rs.getString("PoliceCert").equals("YES")) {
                        cb_police.setSelected(true);
                    } else {
                        cb_police.setSelected(false);
                    }

                    if (rs.getString("CharacterCert").equals("YES")) {
                        cb_character.setSelected(true);
                    } else {
                        cb_character.setSelected(false);
                    }
                    if (rs.getString("NICcopy").equals("YES")) {
                        cb_nic.setSelected(true);
                    } else {
                        cb_nic.setSelected(false);
                    }
                    if (rs.getString("ServiceCert").equals("YES")) {
                        cb_service.setSelected(true);
                    } else {
                        cb_service.setSelected(false);
                    }
                    if (rs.getString("EduQuali").equals("YES")) {
                        cb_eduQuali.setSelected(true);
                    } else {
                        cb_eduQuali.setSelected(false);
                    }

                    String pay = rs.getString("PayType");
                    cmb_payType.setSelectedItem(pay);
                    btn_save.setEnabled(false);
                    txt_EmployeeNo.setText(rs.getString("EmployeeNo"));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                lbl_RankCat.setText("");
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from rank where  RankCode= '" + cmb_designation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String name = rs.getString("Category");

                    lbl_RankCat.setText(name);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                txt_locName.setText("");
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where  LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "'");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(code);
                    txt_locName.setText(name);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cmb_defLocation.getSelectedItem().equals("E001")) {
                jPanel1.setVisible(false);
            } else {
            }
        }
    }//GEN-LAST:event_txt_EPFnoKeyPressed

    private void txt_inCaseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inCaseNameActionPerformed
        txt_inCaseRelation.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseNameActionPerformed

    private void txt_inCaseTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inCaseTelActionPerformed
        txt_inCaseMobile.grabFocus(); // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseTelActionPerformed

    private void txt_inCaseMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inCaseMobileActionPerformed
        txt_inCaseAddress.grabFocus();    // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseMobileActionPerformed

    private void txt_inCaseAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inCaseAddressActionPerformed
//        txt_IncaseOtherDetails.grabFocus();   // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseAddressActionPerformed

    private void txt_inCaseRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inCaseRelationActionPerformed
        txt_inCaseTel.grabFocus();    // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseRelationActionPerformed

    private void txt_inCaseNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseNameFocusGained
        txt_inCaseName.setBackground(Color.ORANGE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseNameFocusGained

    private void txt_inCaseRelationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseRelationFocusGained
        txt_inCaseRelation.setBackground(Color.ORANGE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseRelationFocusGained

    private void txt_inCaseTelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseTelFocusGained
        txt_inCaseTel.setBackground(Color.ORANGE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseTelFocusGained

    private void txt_inCaseMobileFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseMobileFocusGained
        txt_inCaseMobile.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseMobileFocusGained

    private void txt_inCaseAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseAddressFocusGained
        txt_inCaseAddress.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseAddressFocusGained

    private void txt_inCaseNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseNameFocusLost
        txt_inCaseName.setBackground(Color.WHITE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseNameFocusLost

    private void txt_inCaseRelationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseRelationFocusLost
        txt_inCaseRelation.setBackground(Color.WHITE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseRelationFocusLost

    private void txt_inCaseTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseTelFocusLost
        txt_inCaseTel.setBackground(Color.WHITE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseTelFocusLost

    private void txt_inCaseMobileFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseMobileFocusLost
        txt_inCaseMobile.setBackground(Color.WHITE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseMobileFocusLost

    private void txt_inCaseAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_inCaseAddressFocusLost
        txt_inCaseAddress.setBackground(Color.WHITE);         // TODO add your handling code here:
    }//GEN-LAST:event_txt_inCaseAddressFocusLost

    private void btn_print2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print2ActionPerformed

        cb_active_epf.setSelected(false);
        cb_extra_shift.setSelected(false);
        txt_sur_name.setText("");
        txt_Welfare.setText("");
        lbl_activeEMP.setText(" Employee Status");
        lbl_activeEMP.setBackground(Color.WHITE);
        lbl_activeEMP.setOpaque(true);
        txt_EPFno.setText("");
        txt_policeArea.setText("");
        txt_NameWithInitials.setText("");
        txt_eduQuali.setText("");
        txt_shiftRate.setText("");
        txt_address.setText("");
        txt_gramaDivision.setText("");
        txt_locName.setText("");
        txt_EmployeeNo.setText("");
        txt_dob.setText("");
        txt_email.setText("");
        txt_EPFno.setText("");
        txt_fullName.setText("");
        txt_inCaseAddress.setText("");
        txt_inCaseMobile.setText("");
        txt_inCaseName.setText("");
        txt_inCaseRelation.setText("");
        txt_inCaseTel.setText("");
        txt_mobile1.setText("");
        txt_mobile2.setText("");
        txt_workP1.setText("");
        txt_workP2.setText("");
        txt_employer1.setText("");
        txt_employer2.setText("");
        txt_nic.setText("");
        txt_tel.setText("");
        cb_birth.setSelected(false);
        cb_nic.setSelected(false);
        cb_grama.setSelected(false);
        cb_eduQuali.setSelected(false);
        cb_police.setSelected(false);
        cb_nic.setSelected(false);
        cb_service.setSelected(false);
        cb_eduQuali.setSelected(false);
        cmb_civilS.setSelectedIndex(0);
        cmb_defCompany.setSelectedIndex(0);
        cmb_defLocation.setSelectedIndex(0);
        cmb_designation.setSelectedIndex(0);
        cmb_gender.setSelectedIndex(0);

        lbl_RankCat.setText("");

        txt_basicSalary.setText("");
        txt_BrAllo.setText("");
        txt_Gross.setText("");

        lbl_photo.setIcon(null);
        DateChooser_joinedDate.setDate(null);

//        Color col = new Color(204, 204, 255);
//        cb_active_epf.setBackground(col);
        btn_save.setEnabled(true);
        cmb_payType.setSelectedIndex(0);

//        max_epfno();

    }//GEN-LAST:event_btn_print2ActionPerformed

    private void txt_policeAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_policeAreaFocusGained
        txt_policeArea.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_policeAreaFocusGained

    private void txt_policeAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_policeAreaFocusLost
        txt_policeArea.setBackground(Color.WHITE);      // TODO add your handling code here:
    }//GEN-LAST:event_txt_policeAreaFocusLost

    private void txt_policeAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_policeAreaActionPerformed
        txt_gramaDivision.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_policeAreaActionPerformed

    private void txt_gramaDivisionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_gramaDivisionFocusGained
        txt_gramaDivision.setBackground(Color.ORANGE);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_gramaDivisionFocusGained

    private void txt_gramaDivisionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_gramaDivisionFocusLost
        txt_gramaDivision.setBackground(Color.white);     // TODO add your handling code here:
    }//GEN-LAST:event_txt_gramaDivisionFocusLost

    private void txt_gramaDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gramaDivisionActionPerformed
        txt_inCaseName.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gramaDivisionActionPerformed

    private void txt_workP1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_workP1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_workP1FocusGained

    private void txt_workP1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_workP1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_workP1FocusLost

    private void txt_workP2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_workP2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_workP2FocusGained

    private void txt_workP2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_workP2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_workP2FocusLost

    private void txt_employer1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_employer1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employer1FocusGained

    private void txt_employer1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_employer1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employer1FocusLost

    private void txt_employer2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_employer2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employer2FocusGained

    private void txt_employer2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_employer2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employer2FocusLost

    private void btn_print3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print3ActionPerformed

        String userNameValue;
        String passwordValue;

        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};

        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);

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

                        if (cat.equals("system_admin") | cat.equals("admin")) {

//  query begins*************************************************************************************************
                            Statement st = DbConnection.getconnection().createStatement();
                            st.executeUpdate("update  employee_reg SET IsResigned='1' where EmployeeNo='" + txt_EmployeeNo.getText() + "'");

                            JOptionPane.showMessageDialog(rootPane, " Employee Deactivated...! ");

                            clear();

//  query ends ****************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation ( System Admin Only )");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_btn_print3ActionPerformed

    private void txt_basicSalaryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_basicSalaryFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicSalaryFocusGained

    private void txt_basicSalaryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_basicSalaryFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicSalaryFocusLost

    private void txt_BrAlloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BrAlloFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BrAlloFocusGained

    private void txt_BrAlloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_BrAlloFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BrAlloFocusLost

    private void txt_GrossFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_GrossFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GrossFocusGained

    private void txt_GrossFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_GrossFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GrossFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        empty_txt_to_zeros();

        String userNameValue;
        String passwordValue;

        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};

        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);

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

                        if (cat.equals("system_admin")) {

//update query begins*************************************************************************************************
                            Double str_basic = Double.parseDouble(txt_basicSalary.getText());
                            String basic = String.format("%.2f", str_basic);

                            Double str_br = Double.parseDouble(txt_BrAllo.getText());
                            String br = String.format("%.2f", str_br);

                            Double str_gross = Double.parseDouble(txt_Gross.getText());
                            String gross = String.format("%.2f", str_gross);

                            Double rate = Double.parseDouble(txt_shiftRate.getText());
                            String ShiftRate = String.format("%.2f", rate);

                            Double welfare = Double.parseDouble(txt_Welfare.getText());
                            String Welfare = String.format("%.2f", welfare);

                            Double MCAllo = Double.parseDouble(txt_MCAllowance.getText());
                            String MCAllowance = String.format("%.2f", MCAllo);

                            if (cb_active_epf.isSelected()) {
                                Active_EPF = "1";
                            } else {
                                Active_EPF = "0";
                            }

                            String extra_shift = null;

                            if (cb_extra_shift.isSelected()) {
                                extra_shift = "1";
                            } else {
                                extra_shift = "0";
                            }

                            Statement st = DbConnection.getconnection().createStatement();
                            st.executeUpdate("update employee_reg set ShiftRate = '" + ShiftRate + "', BasicSalary = '" + basic + "', BRAllowance = '" + br + "',GrossSalary = '" + gross + "',Welfare='" + Welfare + "',ActiveEPF='" + Active_EPF + "',MCAllowance='" + MCAllowance + "',PayType='" + cmb_payType.getSelectedItem().toString() + "',ExtraShiftAllowed='" + extra_shift + "' WHERE EmployeeNo='" + txt_EmployeeNo.getText() + "' ");

                            JOptionPane.showMessageDialog(rootPane, " Salary Rates Successfully Updated");
                            clear();

//update query ends ****************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation ( System Admin Only )");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmb_defCompanyPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defCompanyPopupMenuWillBecomeInvisible
        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from company_reg where ComCode= '" + cmb_defCompany.getSelectedItem().toString() + "' OR  ComName= '" + cmb_defCompany.getSelectedItem().toString() + "' ");
            while (rs.next()) {

                String code = rs.getString("ComCode");
                String name = rs.getString("ComName");

                cmb_defCompany.setSelectedItem(code);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (cmb_defCompany.getSelectedItem().toString().equals("SIKURA")) {
//
//            cb_salary_cat.setVisible(true);
//
//        } else {
//            cb_salary_cat.setVisible(false);
//            cb_salary_cat.setSelected(false);
//        }

    }//GEN-LAST:event_cmb_defCompanyPopupMenuWillBecomeInvisible

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible
        try {
            txt_locName.setText("");
            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * from location_reg where  LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "'");
            while (rs.next()) {

                String code = rs.getString("LocCode");
                String name = rs.getString("LocName");

                cmb_defLocation.setSelectedItem(code);
                txt_locName.setText(name);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(lbl_photo.getIcon());
        pt.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        lbl_photo.setIcon(null);
        lbl_photo.setText("No Image Selected");
        lbl_photo.setForeground(Color.red);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_print1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print1ActionPerformed
        try {

            Connection conn = (Connection) DbConnection.getconnection();

            JasperDesign jd = JRXmlLoader.load("Reports\\posting_order1.jrxml");
            String sql = " SELECT employee_reg.`EPFno`,employee_reg.`NIC`,employee_reg.`NameWithInitials`,employee_reg.`FullName`,employee_reg.`Designation`,employee_reg.`DefLocation`,location_reg.`LocCode`,location_reg.`LocName`,location_reg.`LocInchargeID`,location_reg.`LocInchargeName`,employee_reg.`EMPImage`,employee_reg.`PostingDate`FROM`employee_reg` employee_reg INNER JOIN `location_reg` location_reg ON employee_reg.`DefLocation` = location_reg.`LocCode` where EPFno='" + txt_EPFno.getText() + "'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_print1ActionPerformed

    private void DateChooser_posting_dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DateChooser_posting_dateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_DateChooser_posting_dateFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txt_nic.grabFocus(); // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {

            String sql1 = "UPDATE employee_reg SET EMPImage=? WHERE EPFno='" + txt_EPFno.getText() + "'";

            pst = c.prepareStatement(sql1);

            pst.setBytes(1, EMPImage);
            pst.execute();

            JOptionPane.showMessageDialog(rootPane, " Employee Image Saved... ");

        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txt_WelfareFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_WelfareFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_WelfareFocusGained

    private void txt_WelfareFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_WelfareFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_WelfareFocusLost

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        try {
            if (txt_search.getText().equals("Search Here by \"Name with Initials\"")) {

                txt_search.setText("");
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txt_searchMouseClicked

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed

        if (txt_search.getText().isEmpty()) {

        } else {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                try {

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + txt_search.getText() + "' OR NameWithInitials='" + txt_search.getText() + "'   ");
                    while (rs3.next()) {

                        if ((rs3.getInt("COUNT(*)") > 1)) {
                            PopUp_Emp_Table pt = new PopUp_Emp_Table();
                            pt.setVisible(true);
                            pt.setTitle("Emp. Reg.");

                            DefaultTableModel dtm = (DefaultTableModel) POPUP_EMP_TABLE.getModel();

                            Statement st4 = DbConnection.getconnection().createStatement();
                            ResultSet rs4 = st4.executeQuery("select * from employee_reg where EmployeeNo='" + txt_search.getText() + "' OR NameWithInitials='" + txt_search.getText() + "'   ");
                            while (rs4.next()) {
                                Vector v = new Vector();
                                v.add(rs4.getString("EmployeeNo"));
                                v.add(rs4.getString("NameWithInitials"));
                                String defloc = rs4.getString("DefLocation");
                                v.add(defloc);

                                Statement st5 = DbConnection.getconnection().createStatement();
                                ResultSet rs5 = st5.executeQuery("select * from location_reg where LocCode='" + defloc + "'  ");
                                while (rs5.next()) {

                                    v.add(rs5.getString("LocName"));
                                }

                                dtm.addRow(v);
                                PopUp_Emp_Table.POPUP_EMP_TABLE.grabFocus();
                            }

                        } else {

                            Statement st = DbConnection.getconnection().createStatement();
                            ResultSet rs = st.executeQuery("select * from employee_reg where NameWithInitials = '" + txt_search.getText() + "' OR EmployeeNo = '" + txt_search.getText() + "' ");
                            if (rs.next()) {

                                byte[] imagedata = rs.getBytes("EMPImage");

                                if (imagedata == null) {
                                    lbl_photo.setText("No Image");
                                    lbl_photo.setForeground(Color.red);

                                } else {

                                    format = new ImageIcon(imagedata);
                                    lbl_photo.setIcon(format);

                                }

                                txt_nic.setText(rs.getString("NIC"));
                                txt_EPFno.setText(rs.getString("EPFno"));
                                txt_fullName.setText(rs.getString("FullName"));
                                txt_NameWithInitials.setText(rs.getString("NameWithInitials"));
                                txt_sur_name.setText(rs.getString("Surname"));
                                txt_dob.setText(rs.getString("DOB"));
                                cmb_gender.setSelectedItem(rs.getString("Gender"));
                                cmb_civilS.setSelectedItem(rs.getString("CivilStatus"));
                                txt_address.setText(rs.getString("Address"));
                                txt_tel.setText(rs.getString("Tel"));
                                txt_mobile1.setText(rs.getString("Mob1"));
                                txt_mobile2.setText(rs.getString("Mob2"));
                                txt_email.setText(rs.getString("Email"));
                                txt_policeArea.setText(rs.getString("PoliceArea"));
                                txt_gramaDivision.setText(rs.getString("GramaDivision"));

                                txt_inCaseName.setText(rs.getString("InCaseName"));
                                txt_inCaseRelation.setText(rs.getString("InCaseRel"));
                                txt_inCaseTel.setText(rs.getString("IncaseTel"));
                                txt_inCaseMobile.setText(rs.getString("IncaseMob"));
                                txt_inCaseAddress.setText(rs.getString("IncaseAddress"));

                                cmb_defCompany.setSelectedItem(rs.getString("DefCompany"));
                                cmb_defLocation.setSelectedItem(rs.getString("DefLocation"));
                                cmb_designation.setSelectedItem(rs.getString("Designation"));
                                DateChooser_joinedDate.setDate(rs.getDate("JoinedDate"));
                                DateChooser_posting_date.setDate(rs.getDate("PostingDate"));
                                txt_shiftRate.setText(rs.getString("ShiftRate"));
                                txt_eduQuali.setText(rs.getString("EduQualiDetails"));
                                txt_workP1.setText(rs.getString("WorkP1"));
                                txt_workP2.setText(rs.getString("WorkP2"));
                                txt_employer1.setText(rs.getString("emp1"));
                                txt_employer2.setText(rs.getString("emp2"));

                                txt_basicSalary.setText(rs.getString("BasicSalary"));
                                txt_Gross.setText(rs.getString("GrossSalary"));
                                txt_BrAllo.setText(rs.getString("BRAllowance"));
                                txt_Welfare.setText(rs.getString("Welfare"));

                                txt_sur_name.setText(rs.getString("Surname"));

                                String active_epf = rs.getString("ActiveEPF");
                                String extra_shift = rs.getString("ExtraShiftAllowed");

                                if (extra_shift.equals("1")) {
                                    cb_extra_shift.setSelected(true);
                                } else {
                                    cb_extra_shift.setSelected(false);
                                }

                                if (active_epf.equals("1")) {
                                    cb_active_epf.setSelected(true);
                                } else {
                                    cb_active_epf.setSelected(false);

                                }

                                String resign = rs.getString("IsResigned");

                                if (resign.equals("0")) {

                                    lbl_activeEMP.setText("Active Employee");
                                    lbl_activeEMP.setBackground(Color.green);
                                    lbl_activeEMP.setOpaque(true);

                                } else {
                                    Color c = new Color(255, 161, 161);
                                    lbl_activeEMP.setText("Resigned Employee");
                                    lbl_activeEMP.setBackground(c);
                                    lbl_activeEMP.setOpaque(true);
                                }

                                if (rs.getString("BirthCert").equals("YES")) {
                                    cb_birth.setSelected(true);
                                } else {
                                    cb_birth.setSelected(false);
                                }

                                if (rs.getString("GramaCert").equals("YES")) {
                                    cb_grama.setSelected(true);
                                } else {
                                    cb_grama.setSelected(false);
                                }

                                if (rs.getString("PoliceCert").equals("YES")) {
                                    cb_police.setSelected(true);
                                } else {
                                    cb_police.setSelected(false);
                                }

                                if (rs.getString("CharacterCert").equals("YES")) {
                                    cb_character.setSelected(true);
                                } else {
                                    cb_character.setSelected(false);
                                }
                                if (rs.getString("NICcopy").equals("YES")) {
                                    cb_nic.setSelected(true);
                                } else {
                                    cb_nic.setSelected(false);
                                }
                                if (rs.getString("ServiceCert").equals("YES")) {
                                    cb_service.setSelected(true);
                                } else {
                                    cb_service.setSelected(false);
                                }
                                if (rs.getString("EduQuali").equals("YES")) {
                                    cb_eduQuali.setSelected(true);
                                } else {
                                    cb_eduQuali.setSelected(false);
                                }

                                String pay = rs.getString("PayType");
                                cmb_payType.setSelectedItem(pay);
                                txt_EmployeeNo.setText(rs.getString("EmployeeNo"));
                                btn_save.setEnabled(false);

                            }

                            lbl_RankCat.setText("");
                            Statement st7 = DbConnection.getconnection().createStatement();
                            ResultSet rs7 = st7.executeQuery("SELECT * from rank where  RankCode= '" + cmb_designation.getSelectedItem().toString() + "' ");
                            while (rs7.next()) {

                                String name = rs7.getString("Category");

                                lbl_RankCat.setText(name);

                            }

                            txt_locName.setText("");
                            Statement st8 = DbConnection.getconnection().createStatement();
                            ResultSet rs8 = st8.executeQuery("SELECT * from location_reg where  LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "'");
                            while (rs8.next()) {

                                String code = rs8.getString("LocCode");
                                String name = rs8.getString("LocName");

                                cmb_defLocation.setSelectedItem(code);
                                txt_locName.setText(name);

                            }

                            if (cmb_defLocation.getSelectedItem().equals("E001")) {
                                jPanel1.setVisible(false);
                            } else {
                            }

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void cb_active_epfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_active_epfMouseClicked
        if (cb_active_epf.isSelected()) {

            cb_active_epf.setBackground(Color.green);
        } else {

            Color c = new Color(204, 204, 255);
            cb_active_epf.setBackground(c);

        }


    }//GEN-LAST:event_cb_active_epfMouseClicked

    private void txt_sur_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_sur_nameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sur_nameFocusGained

    private void txt_sur_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_sur_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sur_nameFocusLost

    private void txt_sur_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sur_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sur_nameActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        cmb_designation.removeAllItems();
        cmb_designation.addItem("=Select Designation=");

        cmb_defLocation.removeAllItems();
        cmb_defLocation.addItem(" ");

        cmb_defCompany.removeAllItems();
        cmb_defCompany.addItem(" ");

        date();
        get_Company_Details();
        get_Location_Details();
        get_Rank_Details();

        get_EMP_Details();               // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txt_basicSalaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_basicSalaryKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_basicSalary.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicSalaryKeyTyped

    private void txt_BrAlloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BrAlloKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_BrAllo.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }    // TODO add your handling code here:
    }//GEN-LAST:event_txt_BrAlloKeyTyped

    private void txt_shiftRateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shiftRateKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_shiftRate.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txt_shiftRateKeyTyped

    private void txt_GrossKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GrossKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_Gross.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txt_GrossKeyTyped

    private void txt_WelfareKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_WelfareKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_Welfare.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }   // TODO add your handling code here:
    }//GEN-LAST:event_txt_WelfareKeyTyped

    private void txt_MCAllowanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MCAllowanceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MCAllowanceFocusGained

    private void txt_MCAllowanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_MCAllowanceFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MCAllowanceFocusLost

    private void txt_MCAllowanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MCAllowanceKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_MCAllowance.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }  // TODO add your handling code here:
    }//GEN-LAST:event_txt_MCAllowanceKeyTyped

    private void get_latest_EPFno() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select MAX(EPFno) from employee_reg ");

            while (rs.next()) {

                int MAX = Integer.parseInt(rs.getString("MAX(EPFno)"));

                int NEW_MAX = MAX + 1;

                txt_EPFno.setText(Integer.toString(NEW_MAX));

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, " Unexpected Error...! ");
        }

    }

    private void get_latest_EMPno() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select MAX(EmployeeNo) from employee_reg ");

            while (rs.next()) {

                int MAX = Integer.parseInt(rs.getString("MAX(EmployeeNo)"));

                int NEW_MAX = MAX + 1;

                txt_EmployeeNo.setText(String.format("%05d", NEW_MAX));

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, " Unexpected Error...! ");
        }

    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        if (jCheckBox1.isSelected()) {
            get_latest_EPFno();
            txt_EPFno.setEditable(false);
        } else {

            txt_EPFno.setText("");
            txt_EPFno.setEditable(true);

        }


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void cb_eduQualiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_eduQualiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_eduQualiActionPerformed

    private void txt_basicSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_basicSalaryActionPerformed
        txt_BrAllo.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicSalaryActionPerformed

    private void txt_BrAlloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BrAlloActionPerformed
        txt_shiftRate.grabFocus();
// TODO add your handling code here:
    }//GEN-LAST:event_txt_BrAlloActionPerformed

    private void txt_shiftRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_shiftRateActionPerformed
        txt_Gross.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_shiftRateActionPerformed

    private void txt_GrossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GrossActionPerformed
        txt_Welfare.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GrossActionPerformed

    private void txt_WelfareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_WelfareActionPerformed
        txt_MCAllowance.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_WelfareActionPerformed

    private void txt_EmployeeNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EmployeeNoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoFocusGained

    private void txt_EmployeeNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EmployeeNoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoFocusLost

    private void txt_EmployeeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmployeeNoActionPerformed
        txt_fullName.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoActionPerformed

    private void txt_EmployeeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmployeeNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo = '" + txt_EmployeeNo.getText() + "' ");
                if (rs.next()) {

                    byte[] imagedata = rs.getBytes("EMPImage");

                    if (imagedata == null) {
                        lbl_photo.setText("No Image");
                        lbl_photo.setForeground(Color.red);

                    } else {

                        format = new ImageIcon(imagedata);
                        lbl_photo.setIcon(format);

                    }

                    txt_nic.setText(rs.getString("NIC"));
                    txt_EPFno.setText(rs.getString("EPFno"));
                    txt_fullName.setText(rs.getString("FullName"));
                    txt_NameWithInitials.setText(rs.getString("NameWithInitials"));
                    txt_dob.setText(rs.getString("DOB"));
                    cmb_gender.setSelectedItem(rs.getString("Gender"));
                    cmb_civilS.setSelectedItem(rs.getString("CivilStatus"));
                    txt_address.setText(rs.getString("Address"));
                    txt_tel.setText(rs.getString("Tel"));
                    txt_mobile1.setText(rs.getString("Mob1"));
                    txt_mobile2.setText(rs.getString("Mob2"));
                    txt_email.setText(rs.getString("Email"));
                    txt_policeArea.setText(rs.getString("PoliceArea"));
                    txt_gramaDivision.setText(rs.getString("GramaDivision"));

                    txt_inCaseName.setText(rs.getString("InCaseName"));
                    txt_inCaseRelation.setText(rs.getString("InCaseRel"));
                    txt_inCaseTel.setText(rs.getString("IncaseTel"));
                    txt_inCaseMobile.setText(rs.getString("IncaseMob"));
                    txt_inCaseAddress.setText(rs.getString("IncaseAddress"));

                    cmb_defCompany.setSelectedItem(rs.getString("DefCompany"));
                    cmb_defLocation.setSelectedItem(rs.getString("DefLocation"));
                    cmb_designation.setSelectedItem(rs.getString("Designation"));
                    DateChooser_joinedDate.setDate(rs.getDate("JoinedDate"));
                    DateChooser_posting_date.setDate(rs.getDate("PostingDate"));
                    txt_shiftRate.setText(rs.getString("ShiftRate"));
                    txt_eduQuali.setText(rs.getString("EduQualiDetails"));
                    txt_workP1.setText(rs.getString("WorkP1"));
                    txt_workP2.setText(rs.getString("WorkP2"));
                    txt_employer1.setText(rs.getString("emp1"));
                    txt_employer2.setText(rs.getString("emp2"));

                    txt_basicSalary.setText(rs.getString("BasicSalary"));
                    txt_Gross.setText(rs.getString("GrossSalary"));
                    txt_BrAllo.setText(rs.getString("BRAllowance"));
                    txt_Welfare.setText(rs.getString("Welfare"));

                    txt_sur_name.setText(rs.getString("Surname"));

                    String active_epf = rs.getString("ActiveEPF");
                    String extra_shift = rs.getString("ExtraShiftAllowed");

                    if (extra_shift.equals("1")) {
                        cb_extra_shift.setSelected(true);
                    } else {
                        cb_extra_shift.setSelected(false);
                    }

                    if (active_epf.equals("1")) {
                        cb_active_epf.setSelected(true);
                    } else {
                        cb_active_epf.setSelected(false);

                    }

                    String resign = rs.getString("IsResigned");

                    if (resign.equals("0")) {

                        lbl_activeEMP.setText("Active Employee");
                        lbl_activeEMP.setBackground(Color.green);
                        lbl_activeEMP.setOpaque(true);

                    } else {
                        Color c = new Color(255, 161, 161);
                        lbl_activeEMP.setText("Resigned Employee");
                        lbl_activeEMP.setBackground(c);
                        lbl_activeEMP.setOpaque(true);
                    }

                    if (rs.getString("BirthCert").equals("YES")) {
                        cb_birth.setSelected(true);
                    } else {
                        cb_birth.setSelected(false);
                    }

                    if (rs.getString("GramaCert").equals("YES")) {
                        cb_grama.setSelected(true);
                    } else {
                        cb_grama.setSelected(false);
                    }

                    if (rs.getString("PoliceCert").equals("YES")) {
                        cb_police.setSelected(true);
                    } else {
                        cb_police.setSelected(false);
                    }

                    if (rs.getString("CharacterCert").equals("YES")) {
                        cb_character.setSelected(true);
                    } else {
                        cb_character.setSelected(false);
                    }
                    if (rs.getString("NICcopy").equals("YES")) {
                        cb_nic.setSelected(true);
                    } else {
                        cb_nic.setSelected(false);
                    }
                    if (rs.getString("ServiceCert").equals("YES")) {
                        cb_service.setSelected(true);
                    } else {
                        cb_service.setSelected(false);
                    }
                    if (rs.getString("EduQuali").equals("YES")) {
                        cb_eduQuali.setSelected(true);
                    } else {
                        cb_eduQuali.setSelected(false);
                    }
                    String pay = rs.getString("PayType");
                    cmb_payType.setSelectedItem(pay);
                    btn_save.setEnabled(false);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                lbl_RankCat.setText("");
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from rank where  RankCode= '" + cmb_designation.getSelectedItem().toString() + "' ");
                while (rs.next()) {

                    String name = rs.getString("Category");

                    lbl_RankCat.setText(name);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                txt_locName.setText("");
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where  LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName= '" + cmb_defLocation.getSelectedItem().toString() + "'");
                while (rs.next()) {

                    String code = rs.getString("LocCode");
                    String name = rs.getString("LocName");

                    cmb_defLocation.setSelectedItem(code);
                    txt_locName.setText(name);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cmb_defLocation.getSelectedItem().equals("E001")) {
                jPanel1.setVisible(false);
            } else {
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoKeyPressed

    private void txt_searchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseReleased

//                
    }//GEN-LAST:event_txt_searchMouseReleased

    private void cmb_payTypePopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_payTypePopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_payTypePopupMenuCanceled

    private void cmb_payTypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_payTypePopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_payTypePopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EMP_BankAcc_Details cm = new EMP_BankAcc_Details();
        cm.setVisible(true);
        EMP_BankAcc_Details.txt_empName.setText(txt_EmployeeNo.getText());

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            get_latest_EMPno();
            txt_EmployeeNo.setEditable(false);
        } else {

            txt_EmployeeNo.setText("");
            txt_EmployeeNo.setEditable(true);

        }     
        
     
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void txt_EPFnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EPFnoKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {

            char text[];
            int count = 0;

            text = txt_EPFno.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txt_EPFnoKeyTyped

    private void txt_EmployeeNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmployeeNoKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {

            char text[];
            int count = 0;

            text = txt_EmployeeNo.getText().toCharArray();
            for (int i = 0; i < text.length; i++) {

                if (text[i] == '.') {
                    count++;
                }

            }
            if (count >= 1 && evt.getKeyChar() == '.') {
                evt.consume();
            }

        } else {
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoKeyTyped

    private void btn_print4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print4ActionPerformed
        String userNameValue;
        String passwordValue;

        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};

        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);

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

                        if (cat.equals("system_admin") | cat.equals("admin")) {

//  query begins*************************************************************************************************
                            Statement st = DbConnection.getconnection().createStatement();
                            st.executeUpdate("delete from employee_reg where EmployeeNo='" + txt_EmployeeNo.getText() + "'");

                            JOptionPane.showMessageDialog(rootPane, " Employee Details were Deleted...! ");

                            clear();

//  query ends ****************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation ( System Admin Only )");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password NOT matched");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_btn_print4ActionPerformed

    private void cb_extra_shiftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_extra_shiftMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_extra_shiftMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String userNameValue;
        String passwordValue;

        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, userName, jPassword, password};

        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password", JOptionPane.OK_CANCEL_OPTION);

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

                        if (cat.equals("system_admin") | cat.equals("admin")) {

//update query begins*************************************************************************************************
                            jPanel1.setVisible(true);
                            // jButton10.setEnabled(false);

//update query ends ****************************************************************************************************
                        } else {
                            JOptionPane.showMessageDialog(rootPane, " You are NOT ALLOWED for this Operation. ( Admin(s) Only. )");
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, " User Name & Password does NOT match");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
//        if (jCheckBox1.isSelected()) {
//            get_latest_EPFno();
//            txt_EPFno.setEditable(false);
//        } else {
//
//            txt_EPFno.setText("");
//            txt_EPFno.setEditable(true);
//
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
//        if (jCheckBox2.isSelected()) {
//            get_latest_EMPno();
//            txt_EmployeeNo.setEditable(false);
//        } else {
//
//            txt_EmployeeNo.setText("");
//            txt_EmployeeNo.setEditable(true);
//
//        }         // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2StateChanged

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable2.getSelectedRow();
            String code = jTable2.getValueAt(row, 0).toString();
//            String name = jTable2.getValueAt(row, 2).toString();
//            String rank = jTable2.getValueAt(row, 3).toString();

            txt_search.setText(code);

            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);
            txt_searchKeyPressed(evt);
            txt_EPFno.grabFocus();

        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);
            txt_EPFno.grabFocus();

        }
    }//GEN-LAST:event_jTable2KeyPressed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

            jTable2.grabFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER | evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jTable2.setVisible(false);
            jScrollPane4.setVisible(false);

        } else {
            try {

                jTable2.setVisible(true);
                jScrollPane4.setVisible(true);
                jScrollPane4.setBounds(450, 33, 550, 200);

                Connection con = DbConnection.getconnection();

                String empno = txt_search.getText();

                String sql = "SELECT * FROM employee_reg WHERE  FullName LIKE ? OR EmployeeNo Like? OR NameWithInitials Like?  ";
                PreparedStatement pststmt = con.prepareStatement(sql);
                pststmt.setString(1, "%"+ empno + "%");
                pststmt.setString(2, "%"+ empno + "%");
                pststmt.setString(3, "%"+ empno + "%");
                ResultSet resultset = pststmt.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                dtm.setRowCount(0);

                while (resultset.next()) {

                    Vector v = new Vector();
                    v.add(resultset.getString("EmployeeNo"));
                    v.add(resultset.getString("NameWithInitials"));
                    v.add(resultset.getString("FullName"));
                    v.add(resultset.getString("Designation"));
                    v.add(resultset.getString("EPFno"));

                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
if(txt_search.getText().equals("Search Here")){
txt_search.setText("");  
}      // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchFocusGained

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
            java.util.logging.Logger.getLogger(employee_reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(employee_reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(employee_reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(employee_reg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new employee_reg().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(employee_reg.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

//    private void skills() {
//        
//        try {
//            
//            Connection con = DbConnection.getconnection();
//            PreparedStatement pst1 = con.prepareStatement("select * from skills ");
//            ResultSet rs = pst1.executeQuery();
//            
//            cmb_skill.removeAllItems();
//            
//            while (rs.next()) {
//                
//                cmb_skill.addItem(rs.getString("SkillName"));
//                
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
    private void clear() {
        cb_active_epf.setSelected(true);
        txt_EmployeeNo.setText("");
        txt_EPFno.setText("");
        txt_policeArea.setText("");
        txt_NameWithInitials.setText("");
        txt_eduQuali.setText("");
        txt_shiftRate.setText("");
        txt_address.setText("");
        txt_gramaDivision.setText("");
        txt_locName.setText("");
        txt_sur_name.setText("");
        txt_Welfare.setText("");
        txt_MCAllowance.setText("");

        txt_dob.setText("");
        txt_email.setText("");
        txt_EPFno.setText("");
        txt_fullName.setText("");
        txt_inCaseAddress.setText("");
        txt_inCaseMobile.setText("");
        txt_inCaseName.setText("");
        txt_inCaseRelation.setText("");
        txt_inCaseTel.setText("");
        txt_mobile1.setText("");
        txt_mobile2.setText("");
        txt_workP1.setText("");
        txt_workP2.setText("");
        txt_employer1.setText("");
        txt_employer2.setText("");
        txt_nic.setText("");
        txt_tel.setText("");
        cb_birth.setSelected(false);
        cb_nic.setSelected(false);
        cb_grama.setSelected(false);
        cb_eduQuali.setSelected(false);
        cb_police.setSelected(false);
        cb_nic.setSelected(false);
        cb_service.setSelected(false);
        cb_eduQuali.setSelected(false);
        cb_character.setSelected(false);
        cmb_civilS.setSelectedIndex(0);
        cmb_defCompany.setSelectedIndex(0);
        cmb_defLocation.setSelectedIndex(0);
        cmb_designation.setSelectedIndex(0);
        cmb_gender.setSelectedIndex(0);
        DateChooser_posting_date.setDate(null);

        txt_basicSalary.setText("");
        txt_BrAllo.setText("");
        txt_Gross.setText("");

        lbl_photo.setIcon(null);
        DateChooser_joinedDate.setDate(null);

        btn_save.setEnabled(true);
        lbl_RankCat.setText("");

        lbl_activeEMP.setText(" Employee Status");
        lbl_activeEMP.setBackground(Color.WHITE);
        lbl_activeEMP.setOpaque(true);
        cmb_payType.setSelectedIndex(0);

//        Color c = new Color(204, 204, 255);
//        cb_active_epf.setBackground(c);
    }

    private void empty_txt_to_zeros() {

        if (txt_shiftRate.getText().isEmpty()) {
            txt_shiftRate.setText("0.00");
        }
        if (txt_basicSalary.getText().isEmpty()) {
            txt_basicSalary.setText("0.00");
        }
        if (txt_BrAllo.getText().isEmpty()) {
            txt_BrAllo.setText("0.00");
        }
        if (txt_Gross.getText().isEmpty()) {
            txt_Gross.setText("0.00");
        }
        if (txt_Welfare.getText().isEmpty()) {
            txt_Welfare.setText("0.00");
        }
        if (txt_MCAllowance.getText().isEmpty()) {
            txt_MCAllowance.setText("0.00");
        }

    }

//    
//    private void max_epfno(){
//    
//        try {
//            
//            Statement st = DbConnection.getconnection().createStatement();
//    ResultSet rs = st.executeQuery("select MAX(EPFno) from employee_reg");
//    while(rs.next()){
//    
//        int maxEPF = Integer.parseInt(rs.getString("MAX(EPFno)"));
//        int i = maxEPF+1;
//        String nextEPF = Integer.toString(i);
//        txt_EPFno.setText(nextEPF);
//    
//    }
//            
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser_joinedDate;
    private com.toedter.calendar.JDateChooser DateChooser_posting_date;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_print1;
    private javax.swing.JButton btn_print2;
    private javax.swing.JButton btn_print3;
    private javax.swing.JButton btn_print4;
    private javax.swing.JButton btn_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb_active_epf;
    private javax.swing.JCheckBox cb_birth;
    private javax.swing.JCheckBox cb_character;
    private javax.swing.JCheckBox cb_eduQuali;
    private javax.swing.JCheckBox cb_extra_shift;
    private javax.swing.JCheckBox cb_grama;
    private javax.swing.JCheckBox cb_nic;
    private javax.swing.JCheckBox cb_police;
    private javax.swing.JCheckBox cb_service;
    private javax.swing.JComboBox cmb_civilS;
    private javax.swing.JComboBox cmb_defCompany;
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_designation;
    private javax.swing.JComboBox cmb_gender;
    private javax.swing.JComboBox<String> cmb_payType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbl_RankCat;
    private javax.swing.JLabel lbl_activeEMP;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JTextField txt_BrAllo;
    private javax.swing.JTextField txt_EPFno;
    public static javax.swing.JTextField txt_EmployeeNo;
    private javax.swing.JTextField txt_Gross;
    private javax.swing.JTextField txt_MCAllowance;
    private javax.swing.JTextField txt_NameWithInitials;
    private javax.swing.JTextField txt_Welfare;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_basicSalary;
    private javax.swing.JTextField txt_dob;
    private javax.swing.JTextArea txt_eduQuali;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_employer1;
    private javax.swing.JTextField txt_employer2;
    private javax.swing.JTextField txt_fullName;
    private javax.swing.JTextField txt_gramaDivision;
    private javax.swing.JTextField txt_inCaseAddress;
    private javax.swing.JTextField txt_inCaseMobile;
    private javax.swing.JTextField txt_inCaseName;
    private javax.swing.JTextField txt_inCaseRelation;
    private javax.swing.JTextField txt_inCaseTel;
    private javax.swing.JTextField txt_locName;
    private javax.swing.JTextField txt_mobile1;
    private javax.swing.JTextField txt_mobile2;
    private javax.swing.JTextField txt_nic;
    private javax.swing.JTextField txt_policeArea;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_shiftRate;
    private javax.swing.JTextField txt_sur_name;
    private javax.swing.JTextField txt_tel;
    private javax.swing.JTextField txt_workP1;
    private javax.swing.JTextField txt_workP2;
    // End of variables declaration//GEN-END:variables
}
