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
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapumal Bandara<TechSeed Lanka>
 */
public class Deductions_NewUI extends javax.swing.JFrame {

    /**
     * Creates new form Deductions_NewUI
     */
    public Deductions_NewUI() {
        initComponents();
        get_Location_Details();
        TitleBar();
    }

    private void get_emp() {

        try {

            Connection con = DbConnection.getconnection();
            PreparedStatement pst = con.prepareStatement("select * from employee_reg where DefLocation='" + txt_locName.getText() + "' and isResigned='0' ");
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("EmployeeNo"));
                v.add(rs.getString("NameWithInitials"));
                dtm.addRow(v);

            }

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

                // cmb_defLocation.addItem(code);
                cmb_defLocation.addItem(name);
            }

            AutoCompleteDecorator.decorate(cmb_defLocation);

        } catch (Exception e) {
        }

    }

    private void Dedauction_SAVE() {

        String month = cmb_month.getSelectedItem().toString();
        String year = cmb_year.getSelectedItem().toString();

        try {

            int nrow = jTable2.getModel().getRowCount();
            Connection con = DbConnection.getconnection();
            String sql = "insert into salary_manual_deductions values(?,?,?,?,?,?,?,?,?,?,?,?) ";

            PreparedStatement pst = con.prepareStatement(sql);

            if (nrow == 0) {

            } else {
                for (int i = 0; nrow > i; i++) {

                    String epf = jTable2.getModel().getValueAt(i, 0).toString();
                    String welfare = "0";
                    String meal = jTable2.getModel().getValueAt(i, 2).toString();
//            String fines = txt_fine.getText();
                    String rental = jTable2.getModel().getValueAt(i, 3).toString();
                    String less = "0";
                    String otherD = jTable2.getModel().getValueAt(i, 4).toString();
                    String death = jTable2.getModel().getValueAt(i, 5).toString();
                    String fines = jTable2.getModel().getValueAt(i, 7).toString();
                    String insu = jTable2.getModel().getValueAt(i, 6).toString();
                    String lessDays = "0";

                    pst.setString(1, epf);//epf
                    pst.setString(2, welfare);//welfare
                    pst.setString(3, meal);//meal
                    pst.setString(4, fines);//fines
                    pst.setString(5, rental);//rental
                    pst.setString(6, less);//less shift
                    pst.setString(7, lessDays);//less day
                    pst.setString(8, otherD);//other
                    pst.setString(9, death);//death
                    pst.setString(10, insu);//insu
                    pst.setString(11, month);//month
                    pst.setString(12, year);//year

                    pst.addBatch();

                }
                pst.executeBatch();
                JOptionPane.showMessageDialog(rootPane, " Deductions Saved... ");
                clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clear() {
        txt_insua.setText("0");
        txt_death.setText("0");
        txt_fines.setText("0");
        txt_locName.setText("");
        txt_meal.setText("0");
        txt_rental.setText("0");
        txt_pettycash.setText("0");

        cmb_defLocation.setSelectedIndex(0);

        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.setRowCount(0);
    }
    
     private void TitleBar() {

        this.setTitle("Bulk Deduction Entry");
        this.setResizable(false);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_meal = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_rental = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_pettycash = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_death = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_insua = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_fines = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Bulk Deduction Entry");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1060, 10));

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP No", "Employee Name", "Meal", "Rental", "Other Deductions", "Death Donations", "Insuarance", "Fines"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setRowHeight(23);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(120);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 1010, 460));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel33.setText("Effective Salary Month / Year  :-");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        cmb_month.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_month.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 204)));
        cmb_month.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_monthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 120, -1));

        cmb_year.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2018", "2019", "2020", "2021", " " }));
        cmb_year.setSelectedIndex(3);
        cmb_year.setSelectedItem("2017");
        cmb_year.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 204)));
        cmb_year.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_yearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 70, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel4.setText("Location:-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 80, 40));

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
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 480, 25));

        txt_locName.setEditable(false);
        txt_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locNameFocusGained(evt);
            }
        });
        getContentPane().add(txt_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, 80, 25));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText("Meal :-");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, 20));

        txt_meal.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_meal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_mealFocusGained(evt);
            }
        });
        txt_meal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mealActionPerformed(evt);
            }
        });
        txt_meal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mealKeyTyped(evt);
            }
        });
        getContentPane().add(txt_meal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 100, 25));

        jLabel21.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel21.setText("Rental :-");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, 20));

        txt_rental.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_rental.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rentalFocusGained(evt);
            }
        });
        txt_rental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rentalActionPerformed(evt);
            }
        });
        txt_rental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rentalKeyTyped(evt);
            }
        });
        getContentPane().add(txt_rental, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 100, 25));

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 11)); // NOI18N
        jLabel23.setText(" Other Deduction :-");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, 20));

        txt_pettycash.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_pettycash.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pettycashFocusGained(evt);
            }
        });
        txt_pettycash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_pettycashKeyTyped(evt);
            }
        });
        getContentPane().add(txt_pettycash, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 100, 25));

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel22.setText("Death Donation :-");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        txt_death.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_death.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_deathFocusGained(evt);
            }
        });
        txt_death.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_deathKeyTyped(evt);
            }
        });
        getContentPane().add(txt_death, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 100, 25));

        jLabel24.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel24.setText("Insuarance :-");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 80, 20));

        txt_insua.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_insua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_insuaFocusGained(evt);
            }
        });
        txt_insua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_insuaKeyTyped(evt);
            }
        });
        getContentPane().add(txt_insua, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 100, 25));

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel25.setText(" Fines :-");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, -1, 20));

        txt_fines.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_fines.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_finesFocusGained(evt);
            }
        });
        txt_fines.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_finesKeyTyped(evt);
            }
        });
        getContentPane().add(txt_fines, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 100, 25));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton1.setText(" Clear All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 680, 170, 60));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        jButton2.setText("  Assign Deduction to All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 250, 70));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 170, 60));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton4.setText("Remove Row");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 680, 170, 60));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1060, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_monthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_monthPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_monthPopupMenuWillBecomeInvisible

    private void cmb_yearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_yearPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_cmb_yearPopupMenuWillBecomeInvisible

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locName.setText("** Please Select a Location ");
            txt_locName.setForeground(Color.red);

        } else {
            txt_locName.setForeground(Color.black);
            try {

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * from location_reg where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' OR LocName = '" + cmb_defLocation.getSelectedItem().toString() + "'");
                while (rs.next()) {

                    String name = rs.getString("LocName");
                    String code = rs.getString("LocCode");

                    txt_locName.setText(code);
                    cmb_defLocation.setSelectedItem(name);

                    get_emp();
                }
//
//                ResultSet rs1 = st.executeQuery("select * from salary_rates where LocCode= '" + cmb_defLocation.getSelectedItem().toString() + "' and Rank ='" + txt_rank.getText() + "'");
//
//                while (rs1.next()) {
//
//                    String rankAmt = rs1.getString("Sunday");
//                    txt_Sunday_perDay.setText(rankAmt);
//                    String poya = rs1.getString("Poyaday");
//                    txt_poyaDayAmt.setText(poya);
//
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void txt_locNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locNameFocusGained

    }//GEN-LAST:event_txt_locNameFocusGained

    private void txt_mealFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mealFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mealFocusGained

    private void txt_mealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mealActionPerformed
        txt_rental.grabFocus();
    }//GEN-LAST:event_txt_mealActionPerformed

    private void txt_mealKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mealKeyTyped

        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_meal.getText().toCharArray();
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
        }
    }//GEN-LAST:event_txt_mealKeyTyped

    private void txt_rentalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rentalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rentalFocusGained

    private void txt_rentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rentalActionPerformed
        txt_pettycash.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rentalActionPerformed

    private void txt_rentalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rentalKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_rental.getText().toCharArray();
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rentalKeyTyped

    private void txt_pettycashFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pettycashFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pettycashFocusGained

    private void txt_pettycashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pettycashKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_pettycash.getText().toCharArray();
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
        }         // TODO add your handling code here:
    }//GEN-LAST:event_txt_pettycashKeyTyped

    private void txt_deathFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_deathFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_deathFocusGained

    private void txt_deathKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_deathKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_death.getText().toCharArray();
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
    }//GEN-LAST:event_txt_deathKeyTyped

    private void txt_insuaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_insuaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_insuaFocusGained

    private void txt_insuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_insuaKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_insua.getText().toCharArray();
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
    }//GEN-LAST:event_txt_insuaKeyTyped

    private void txt_finesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_finesFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_finesFocusGained

    private void txt_finesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_finesKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;

            text = txt_fines.getText().toCharArray();
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
    }//GEN-LAST:event_txt_finesKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int nrow = jTable2.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
                    dtm.removeRow(jTable2.getSelectedRow());
                }

            } else {

            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clear();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row_count = jTable2.getRowCount();

        if (txt_insua.getText().equals("")) {
            txt_insua.setText("0");
        }

        if (txt_death.getText().equals("")) {
            txt_death.setText("0");
        }

        if (txt_fines.getText().equals("")) {
            txt_fines.setText("0");
        }

        if (txt_meal.getText().equals("")) {
            txt_meal.setText("0");
        }

        if (txt_rental.getText().equals("")) {
            txt_rental.setText("0");
        }

        if (txt_pettycash.getText().equals("")) {
            txt_pettycash.setText("0");
        }

        if (row_count > 0) {
            for (int row = 0; row < row_count; row++) {

                jTable2.setValueAt(txt_meal.getText(), row, 2);
                jTable2.setValueAt(txt_rental.getText(), row, 3);
                jTable2.setValueAt(txt_pettycash.getText(), row, 4);
                jTable2.setValueAt(txt_death.getText(), row, 5);
                jTable2.setValueAt(txt_insua.getText(), row, 6);
                jTable2.setValueAt(txt_fines.getText(), row, 7);
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Dedauction_SAVE();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Deductions_NewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Deductions_NewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Deductions_NewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Deductions_NewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Deductions_NewUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_death;
    private javax.swing.JTextField txt_fines;
    private javax.swing.JTextField txt_insua;
    private javax.swing.JTextField txt_locName;
    private javax.swing.JTextField txt_meal;
    private javax.swing.JTextField txt_pettycash;
    private javax.swing.JTextField txt_rental;
    // End of variables declaration//GEN-END:variables
}
