/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Sapu
 */
public class LocationReg extends javax.swing.JFrame {

    /**
     * Creates new form LocationReg
     */
    public LocationReg() {
        initComponents();

        btn_LocCarder.setText("<html>Location <br> Carder</html>");
//        btn_poya1.setText("<html>PoyaDay<br>Rates </html>");
        //      btn_sun.setText("<html>SunDay<br>Rates </html>");

        TitleBar();
        auto_completer();

        LocIncharge();
        //cmb_locType.setVisible(false);
        // jLabel24.setVisible(false);
        jCheckBox2.setSelected(true);
        btn_LocCarder.setVisible(false);
        Loc_Status();

    }

    static ArrayList<table_data> al = new ArrayList<table_data>();

    private void Loc_Status() {

        if (jCheckBox2.isSelected()) {

            lbl_Loc_Status.setText("   Active Location  ");
            lbl_Loc_Status.setBackground(Color.green);
        } else {
            lbl_Loc_Status.setText("   In-Active Location  ");
            lbl_Loc_Status.setBackground(Color.red);
        }

    }

    private void clear() {
        txt_LocCode.setText("");
        txt_LocName.setText("");
        txt_LocAddress.setText("");
        txt_LocTele1.setText("");
        txt_LocTel2.setText("");
//        txt_LocTel3.setText("");
        txt_LocCity.setText("");
        txt_LocDistrict.setText("");
        txt_LocIncharge.setText("");
        txt_LocInchargeName.setText("");
        txt_LocOwnerName.setText("");
        txt_LocOwnerTel.setText("");

        txt_LocCode.setBackground(Color.WHITE);
        txt_LocName.setBackground(Color.WHITE);
        txt_LocAddress.setBackground(Color.WHITE);
        txt_LocIncharge.setBackground(Color.WHITE);

        txt_search.setText("Search Here");
        txt_LocCode.grabFocus();

        jCheckBox2.setSelected(false);
        Color Grey = new Color(240, 240, 240);
        lbl_Loc_Status.setBackground(Grey);
        lbl_Loc_Status.setText("");
    }

    String inchargeID;

    private void TitleBar() {
        company();
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
        ImageIcon img = new ImageIcon("src\\Images\\techseed.png");
        this.setIconImage(img.getImage());

    }

    public void get_incharge() {

        inchargeID = "name";

    }
    
    private void company() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from company_reg order by ComName ");

            //TextAutoCompleter ta = new TextAutoCompleter(txt_search);

            while (rs.next()) {

                String code = rs.getString("ComCode");
               // String Name = rs.getString("LocName");
                
                cmb_loc_def_company.addItem(code);
               // ta.addItem(Name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from location_reg order by LocName ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);

            while (rs.next()) {

                String code = rs.getString("LocCode");
                String Name = rs.getString("LocName");
                ta.addItem(code);
                ta.addItem(Name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void LocIncharge() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_LocIncharge);

            while (rs.next()) {

                String code = rs.getString("EPFno");
                String Name = rs.getString("NameWithInitials");
                String FName = rs.getString("FullName");
                String SName = rs.getString("Surname");
                ta.addItem(code);
                ta.addItem(Name);
                ta.addItem(FName);
                ta.addItem(SName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        lbl_Loc_Status = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_LocIncharge = new javax.swing.JTextField();
        txt_LocAddress = new javax.swing.JTextField();
        txt_LocOwnerName = new javax.swing.JTextField();
        txt_LocCode = new javax.swing.JTextField();
        txt_LocName = new javax.swing.JTextField();
        txt_LocTel2 = new javax.swing.JTextField();
        txt_LocCity = new javax.swing.JTextField();
        txt_LocTele1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btn_LocSave = new javax.swing.JButton();
        btn_LocUpdate = new javax.swing.JButton();
        btn_LocDelete = new javax.swing.JButton();
        btn_LocClear = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_LocInchargeName = new javax.swing.JTextField();
        txt_LocDistrict = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txt_LocOwnerTel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_LocOwnerMobile = new javax.swing.JTextField();
        txt_LocInchargeRank = new javax.swing.JTextField();
        btn_LocCarder = new javax.swing.JButton();
        cmb_loc_def_company = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cmb_locType = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 430));
        setPreferredSize(new java.awt.Dimension(600, 430));
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
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

        jCheckBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jCheckBox1.setText("Auto Generate");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Location Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        lbl_Loc_Status.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        lbl_Loc_Status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl_Loc_Status.setOpaque(true);
        getContentPane().add(lbl_Loc_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 170, 20));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText("Address   :-");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("City  :-");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 70, 40));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("District  :-");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, 20));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Loc. Incharge  :-");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 40));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("Fax  :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, -1, 20));

        txt_LocIncharge.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocIncharge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_LocInchargeFocusLost(evt);
            }
        });
        txt_LocIncharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocInchargeActionPerformed(evt);
            }
        });
        txt_LocIncharge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocInchargeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_LocIncharge, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 90, -1));

        txt_LocAddress.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocAddressActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 430, -1));

        txt_LocOwnerName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocOwnerName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_LocOwnerNameFocusGained(evt);
            }
        });
        txt_LocOwnerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocOwnerNameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocOwnerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 220, -1));

        txt_LocCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_LocCodeFocusLost(evt);
            }
        });
        txt_LocCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocCodeActionPerformed(evt);
            }
        });
        txt_LocCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_LocCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 80, -1));

        txt_LocName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocNameActionPerformed(evt);
            }
        });
        txt_LocName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_LocName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 430, -1));

        txt_LocTel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocTel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocTel2ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocTel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 180, -1));

        txt_LocCity.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocCityActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 160, -1));

        txt_LocTele1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocTele1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocTele1ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocTele1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 160, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 590, 10));

        btn_LocSave.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        btn_LocSave.setText("Save");
        btn_LocSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 110, 40));

        btn_LocUpdate.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save as.png"))); // NOI18N
        btn_LocUpdate.setText("Update");
        btn_LocUpdate.setPreferredSize(new java.awt.Dimension(100, 23));
        btn_LocUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 110, 40));

        btn_LocDelete.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Eraser.png"))); // NOI18N
        btn_LocDelete.setText("Delete");
        btn_LocDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 110, 40));

        btn_LocClear.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        btn_LocClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        btn_LocClear.setText("Clear");
        btn_LocClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocClearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 110, 40));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jLabel10.setText("Location Owner/Contact Person Details :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 350, 30));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("Name  :-");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 20));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("Telephone  :-");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, 20));

        txt_LocInchargeName.setEditable(false);
        txt_LocInchargeName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_LocInchargeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 240, -1));

        txt_LocDistrict.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocDistrict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocDistrictActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocDistrict, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 180, -1));

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel14.setText("Mobile :-");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, 20));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 1060, 0));

        txt_LocOwnerTel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocOwnerTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LocOwnerTelActionPerformed(evt);
            }
        });
        getContentPane().add(txt_LocOwnerTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 110, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("*");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 20, 10));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("*");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 20, 10));

        jLabel21.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("*");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 20, 10));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("Default Company :-");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 120, 20));

        txt_LocOwnerMobile.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_LocOwnerMobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_LocOwnerMobileKeyPressed(evt);
            }
        });
        getContentPane().add(txt_LocOwnerMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 110, -1));

        txt_LocInchargeRank.setEditable(false);
        txt_LocInchargeRank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(txt_LocInchargeRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 80, -1));

        btn_LocCarder.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        btn_LocCarder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Conference Call-50.png"))); // NOI18N
        btn_LocCarder.setText("Loc:Carder");
        btn_LocCarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocCarderActionPerformed(evt);
            }
        });
        getContentPane().add(btn_LocCarder, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, 20));

        cmb_loc_def_company.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cmb_loc_def_company.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_loc_def_companyPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmb_loc_def_company.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmb_loc_def_companyPropertyChange(evt);
            }
        });
        getContentPane().add(cmb_loc_def_company, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 90, 23));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText("Location Code :-");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("*");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 10, 10));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 590, 10));

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 40, 40));

        txt_search.setBackground(new java.awt.Color(204, 255, 204));
        txt_search.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        txt_search.setText("Search Here");
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 360, 23));

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel22.setText("Location Name  :-");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));

        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 20, -1));

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel23.setText("Location Status  :-");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 20));

        jLabel24.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel24.setText("Salary Type :-");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 90, 20));

        cmb_locType.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmb_locType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Type01", "Type02", "Type03", "Type04", "Type05" }));
        cmb_locType.setToolTipText("<html>\nType01 - Normal Salary <br>\nType02 - Arrival Salary <br>\nType03 - Kurunegala Salary <br>\nType04 - LRH Salary  \n</html>");
        cmb_locType.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_locTypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cmb_locType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmb_locTypePropertyChange(evt);
            }
        });
        getContentPane().add(cmb_locType, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 90, 23));

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel25.setText("Telephone  :-");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_LocInchargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocInchargeActionPerformed
        txt_LocOwnerName.grabFocus();
    }//GEN-LAST:event_txt_LocInchargeActionPerformed

    private void btn_LocUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocUpdateActionPerformed
        String code = txt_LocCode.getText();
        String name = txt_LocName.getText();
        String address = txt_LocAddress.getText();
        String Tel1 = txt_LocTele1.getText();
        String Tel2 = txt_LocTel2.getText();
        //String Tel3 = txt_LocTel3.getText();
        String city = txt_LocCity.getText();
        String district = txt_LocDistrict.getText();
        String incharge = txt_LocIncharge.getText();
        String OwnerName = txt_LocOwnerName.getText();
        String OwnerTel = txt_LocOwnerTel.getText();
        String OwnerMobile = txt_LocOwnerMobile.getText();
        String LocStatus = "0";
        if (jCheckBox2.isSelected()) {
            LocStatus = "1";
        } else {
            LocStatus = "0";
        }

        String loctype = cmb_loc_def_company.getSelectedItem().toString();

        try {
            Statement st = DbConnection.getconnection().createStatement();
            st.executeUpdate("update location_reg set LocName='" + name + "',LocAddress='" + address + "', Tel1='" + Tel1 + "', Tel2='" + Tel2 + "',  Tel3='" + cmb_locType.getSelectedItem().toString() + "', City='" + city + "' , District='" + district + "',  LocInchargeID='" + txt_LocIncharge.getText() + "', LocInchargeName='" + txt_LocInchargeName.getText() + "',   LocOwnerName='" + OwnerName + "',  LocOwnerTel='" + OwnerTel + "' ,  LocOwnerMobile='" + OwnerMobile + "', LocType='" + loctype + "',LocStatus='" + LocStatus + "' where LocCode='" + code + "' ");
            JOptionPane.showMessageDialog(rootPane, " Company Details Successfully Updated... ");
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btn_LocUpdateActionPerformed

    private void btn_LocSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocSaveActionPerformed
        // get_incharge();

        String code = txt_LocCode.getText();
        String name = txt_LocName.getText();
        String address = txt_LocAddress.getText();
        String Tel1 = txt_LocTele1.getText();
        String Tel2 = txt_LocTel2.getText();
        // String Tel3 = txt_LocTel3.getText();
        String city = txt_LocCity.getText();
        String district = txt_LocDistrict.getText();
        String incharge = txt_LocInchargeName.getText();
        String OwnerName = txt_LocOwnerName.getText();
        String OwnerTel = txt_LocOwnerTel.getText();
        String OwnerMobile = txt_LocOwnerMobile.getText();

        String loctype = cmb_loc_def_company.getSelectedItem().toString();

        if (code.isEmpty() || name.isEmpty() || address.isEmpty()) {

            Color color = new Color(255, 161, 161);

            if (code.isEmpty()) {
                txt_LocCode.setBackground(color);
            }

            if (name.isEmpty()) {
                txt_LocName.setBackground(color);
            }

            if (address.isEmpty()) {
                txt_LocAddress.setBackground(color);
            }

        } else {

            try {
                Statement st = DbConnection.getconnection().createStatement();
                st.executeUpdate("INSERT INTO location_reg values('" + code + "','" + name + "','" + address + "',"
                        + "'" + Tel1 + "','" + Tel2 + "','" + cmb_locType.getSelectedItem().toString() + "','" + city + "','" + district + "','" + txt_LocIncharge.getText() + "','" + txt_LocInchargeName.getText() + "','" + OwnerName + "',"
                        + "'" + OwnerTel + "','" + OwnerMobile + "','" + loctype + "','1')");

                System.out.println("DONE");

                JOptionPane.showMessageDialog(rootPane, " Location Details Successfully Saved... ");

                Statement st1 = DbConnection.getconnection().createStatement();
                ResultSet rs = st1.executeQuery("select * from location_reg");
                TextAutoCompleter ta = new TextAutoCompleter(txt_LocCode);

                while (rs.next()) {

                    String code1 = rs.getString("LocCode");
                    ta.addItem(code1);
                }

                System.out.println("refresh done");

            } catch (Exception e) {
                e.printStackTrace();
            }
            clear();
        }

    }//GEN-LAST:event_btn_LocSaveActionPerformed

    private void btn_LocClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocClearActionPerformed

    }//GEN-LAST:event_btn_LocClearActionPerformed

    private void txt_LocCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocCodeFocusLost
//        String code = txt_LocCode.getText();
//
//        try {
//            Statement st = DbConnection.getconnection().createStatement();
//            ResultSet rs = st.executeQuery("select * from location_reg");
//
//            while (rs.next()) {
//                String LocCode = rs.getString("LocCode");
//
//                if (code.equals(LocCode)) {
//                    btn_LocSave.setEnabled(false);
//                } else {
//
//                }
//            }
//
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_txt_LocCodeFocusLost

    private void txt_LocCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocCodeKeyPressed
        //(evt.getKeyCode() == KeyEvent.VK_F1  && evt.isControlDown()

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String code = txt_LocCode.getText();

            try {
                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from location_reg where LocCode='" + txt_LocCode.getText() + "' OR LocName='" + txt_LocCode.getText() + "'");

                while (rs.next()) {

                    btn_LocSave.setEnabled(false);
                    txt_LocCode.setText(rs.getString("LocCode"));
                    txt_LocName.setText(rs.getString("LocName"));
                    txt_LocAddress.setText(rs.getString("LocAddress"));
                    txt_LocTele1.setText(rs.getString("Tel1"));
                    txt_LocTel2.setText(rs.getString("Tel2"));
                    cmb_locType.setSelectedItem(rs.getString("Tel3"));
                    txt_LocCity.setText(rs.getString("City"));
                    txt_LocDistrict.setText(rs.getString("District"));
                    txt_LocIncharge.setText(rs.getString("LocInchargeName"));
                    txt_LocOwnerName.setText(rs.getString("LocOwnerName"));
                    txt_LocOwnerTel.setText(rs.getString("LocOwnerTel"));
                    txt_LocOwnerMobile.setText(rs.getString("LocOwnerMobile"));
                    cmb_loc_def_company.setSelectedItem(rs.getString("LocType"));

                    String Status = rs.getString("LocStatus");
                    if (Status.equals("1")) {
                        jCheckBox2.setSelected(true);
                    } else {
                        jCheckBox2.setSelected(false);
                    }
                    Loc_Status();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_txt_LocCodeKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void btn_LocDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocDeleteActionPerformed

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from location_reg");
            while (rs.next()) {
                String code = rs.getString("LocCode");

                if (txt_LocCode.getText().equals(code)) {

                    int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to Delete This Location...?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {

                        Statement st1 = DbConnection.getconnection().createStatement();
                        st1.execute("delete from location_reg where LocCode='" + txt_LocCode.getText() + "'");

                        JOptionPane.showMessageDialog(rootPane, "Location was Deleted");
                        clear();
                        btn_LocSave.setEnabled(true);

                    } else {

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_LocDeleteActionPerformed

    private void txt_LocOwnerMobileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocOwnerMobileKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocOwnerMobileKeyPressed

    private void txt_LocInchargeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocInchargeFocusLost

    }//GEN-LAST:event_txt_LocInchargeFocusLost

    private void txt_LocOwnerNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_LocOwnerNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocOwnerNameFocusGained

    private void txt_LocInchargeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocInchargeKeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            if (txt_LocIncharge.getText().isEmpty()) {
            } else {

                try {

                    Statement st = DbConnection.getconnection().createStatement();
                    ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + txt_LocIncharge.getText() + "' OR NameWithInitials='" + txt_LocIncharge.getText() + "' ");

                    while (rs.next()) {

                        String name = rs.getString("NameWithInitials");
                        String rank = rs.getString("Designation");
                        String epf = rs.getString("EmployeeNo");

                        txt_LocInchargeName.setText(name);
                        txt_LocInchargeRank.setText(rank);
                        txt_LocIncharge.setText(epf);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            txt_LocOwnerName.grabFocus();
        }
    }//GEN-LAST:event_txt_LocInchargeKeyPressed

    private void btn_LocCarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocCarderActionPerformed
        Location_Carder lc = new Location_Carder();
        lc.setVisible(true);

    }//GEN-LAST:event_btn_LocCarderActionPerformed

    private void txt_LocCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocCodeActionPerformed
        txt_LocName.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocCodeActionPerformed

    private void txt_LocNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocNameActionPerformed
        txt_LocAddress.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocNameActionPerformed

    private void txt_LocAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocAddressActionPerformed
        txt_LocTele1.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocAddressActionPerformed

    private void txt_LocTel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocTel2ActionPerformed
        txt_LocCity.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocTel2ActionPerformed

    private void txt_LocTele1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocTele1ActionPerformed
        txt_LocTel2.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocTele1ActionPerformed

    private void txt_LocCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocCityActionPerformed
        txt_LocDistrict.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocCityActionPerformed

    private void txt_LocDistrictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocDistrictActionPerformed
        txt_LocIncharge.grabFocus();          // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocDistrictActionPerformed

    private void txt_LocOwnerTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocOwnerTelActionPerformed
        txt_LocOwnerMobile.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocOwnerTelActionPerformed

    private void txt_LocOwnerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LocOwnerNameActionPerformed
        txt_LocOwnerTel.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LocOwnerNameActionPerformed

    private void txt_LocNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_LocNameKeyPressed
//          if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//
//            String name = txt_LocName.getText();
//
//            try {
//                Statement st = DbConnection.getconnection().createStatement();
//                ResultSet rs = st.executeQuery("select * from location_reg");
//
//                while (rs.next()) {
//                    String LocName = rs.getString("LocName");
//
//                    if (name.equals(LocName)) {
//                        btn_LocSave.setEnabled(false);
//
//                        txt_LocCode.setText(rs.getString("LocCode"));
//                        txt_LocAddress.setText(rs.getString("LocAddress"));
//                        txt_LocTele1.setText(rs.getString("Tel1"));
//                        txt_LocTel2.setText(rs.getString("Tel2"));
//                        txt_LocTel3.setText(rs.getString("Tel3"));
//                        txt_LocCity.setText(rs.getString("City"));
//                        txt_LocDistrict.setText(rs.getString("District"));
//                        txt_LocIncharge.setText(rs.getString("LocInchargeName"));
//                        txt_LocOwnerName.setText(rs.getString("LocOwnerName"));
//                        txt_LocOwnerTel.setText(rs.getString("LocOwnerTel"));
//                        txt_ShiftRate.setText(rs.getString("LocOwnerMobile"));
//                        cmb_locType.setSelectedItem(rs.getString("LocType"));
//
//                    } else {
//
//                    }
//                }
//                DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
//                dtm.setRowCount(0);
//
//                Statement st1 = DbConnection.getconnection().createStatement();
//                ResultSet rs1 = st1.executeQuery("select * from location_shift_rates where LocCode = '" + txt_LocCode.getText() + "'");
//
//                while (rs1.next()) {
//
//                    Vector v = new Vector();
//                    v.add(rs1.getString("Rank"));
//                    v.add(rs1.getString("ShiftRate"));
//
//                    dtm.addRow(v);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
    }//GEN-LAST:event_txt_LocNameKeyPressed

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        if (txt_search.getText().equals("Search Here")) {

            txt_search.setText("");
        } else {

        }
    }//GEN-LAST:event_txt_searchMouseClicked

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (txt_search.getText().isEmpty()) {

        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                String code = txt_LocCode.getText();

                try {
                    Statement st = DbConnection.getconnection().createStatement();
                    ResultSet rs = st.executeQuery("select * from location_reg where LocCode='" + txt_search.getText() + "' OR LocName='" + txt_search.getText() + "'");

                    while (rs.next()) {

                        btn_LocSave.setEnabled(false);
                        txt_LocCode.setText(rs.getString("LocCode"));
                        txt_LocName.setText(rs.getString("LocName"));
                        txt_LocAddress.setText(rs.getString("LocAddress"));
                        txt_LocTele1.setText(rs.getString("Tel1"));
                        txt_LocTel2.setText(rs.getString("Tel2"));
//                        txt_LocTel3.setText(rs.getString("Tel3"));
                        cmb_locType.setSelectedItem(rs.getString("Tel3"));
                        txt_LocCity.setText(rs.getString("City"));
                        txt_LocDistrict.setText(rs.getString("District"));
                        txt_LocIncharge.setText(rs.getString("LocInchargeName"));
                        txt_LocOwnerName.setText(rs.getString("LocOwnerName"));
                        txt_LocOwnerTel.setText(rs.getString("LocOwnerTel"));
                        txt_LocOwnerMobile.setText(rs.getString("LocOwnerMobile"));
                        cmb_loc_def_company.setSelectedItem(rs.getString("LocType"));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txt_LocCode.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void get_latest_LocCode() {

        try {
            if (cmb_loc_def_company.getSelectedIndex() == 0) {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select MAX(LocCode) from location_reg WHERE LocType = 'Express' ");

                while (rs.next()) {

                    String loc = rs.getString("MAX(LocCode)");
                    String code = loc.substring(1, 4);
                    System.out.println(code);
                    int MAX = Integer.parseInt(code);

                    int NEW_MAX = MAX + 1;

                    String formatted = String.format("%03d", NEW_MAX);

                    txt_LocCode.setText("E" + formatted);

                }

            } else if (cmb_loc_def_company.getSelectedIndex() == 1) {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select MAX(LocCode) from location_reg WHERE LocType = 'Target' ");

                while (rs.next()) {

                    String loc = rs.getString("MAX(LocCode)");
                    String code = loc.substring(1, 4);
                    System.out.println(code);

                    int MAX = Integer.parseInt(code);

                    int NEW_MAX = MAX + 1;

                    String formatted = String.format("%03d", NEW_MAX);

                    txt_LocCode.setText("T" + formatted);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, " Unexpected Error...! ");
        }

    }


    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        if (jCheckBox1.isSelected()) {
            get_latest_LocCode();
            txt_LocCode.setEditable(false);
        } else {

            txt_LocCode.setText("");
            txt_LocCode.setEditable(true);

        }


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        Loc_Status();        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void cmb_loc_def_companyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmb_loc_def_companyPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_loc_def_companyPropertyChange

    private void cmb_loc_def_companyPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_loc_def_companyPopupMenuWillBecomeInvisible
        txt_LocCode.setText("");
        txt_LocCode.setEditable(true);
        txt_LocCode.setEnabled(true);
        jCheckBox1.setSelected(false);        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_loc_def_companyPopupMenuWillBecomeInvisible

    private void cmb_locTypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_locTypePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_locTypePopupMenuWillBecomeInvisible

    private void cmb_locTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmb_locTypePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_locTypePropertyChange

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
            java.util.logging.Logger.getLogger(LocationReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocationReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocationReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocationReg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocationReg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_LocCarder;
    private javax.swing.JButton btn_LocClear;
    private javax.swing.JButton btn_LocDelete;
    public javax.swing.JButton btn_LocSave;
    private javax.swing.JButton btn_LocUpdate;
    private javax.swing.JComboBox cmb_locType;
    private javax.swing.JComboBox cmb_loc_def_company;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_Loc_Status;
    private javax.swing.JTextField txt_LocAddress;
    private javax.swing.JTextField txt_LocCity;
    private javax.swing.JTextField txt_LocCode;
    private javax.swing.JTextField txt_LocDistrict;
    private javax.swing.JTextField txt_LocIncharge;
    private javax.swing.JTextField txt_LocInchargeName;
    private javax.swing.JTextField txt_LocInchargeRank;
    private javax.swing.JTextField txt_LocName;
    private javax.swing.JTextField txt_LocOwnerMobile;
    private javax.swing.JTextField txt_LocOwnerName;
    private javax.swing.JTextField txt_LocOwnerTel;
    private javax.swing.JTextField txt_LocTel2;
    private javax.swing.JTextField txt_LocTele1;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
