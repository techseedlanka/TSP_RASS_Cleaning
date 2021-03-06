/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.EMP_Atten.lbl_photo;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sapu
 */
public class Shoe_Issue extends javax.swing.JFrame {

    /**
     * Creates new form Shoe_Issue
     */
    private ImageIcon format = null;

    public Shoe_Issue() {
        initComponents();
        auto_completer();

//        JScrollPane pane = new JScrollPane();
//        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS; 
// 
//       int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
//       pane = new javax.swing.JScrollPane(jTable2, v, h);
//       
//JScrollPane pane = new JScrollPane();
//
//        pane = new JScrollPane(jTable2);   
//            pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);   
//            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void get_data_to_table() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from shoes_issue where EPFno ='" + txt_empid.getText() + "'");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getString("Item"));
                v.add(rs.getString("QTY"));
                v.add(rs.getString("UnitCost"));
                v.add(rs.getString("IssueOn"));
                // v.add(rs.getString("HandOverTo"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_search);

            while (rs.next()) {
                String code = rs.getString("EPFno");
                String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");

                ta.addItem(code);
                ta.addItem(NameWithInitials);
              //  ta.addItem(nic);
            }

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

        jLabel1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txt_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_empid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_empNic = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        lbl_photo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        date_issueOn = new com.toedter.calendar.JDateChooser();
        cb_shoes = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_installment = new javax.swing.JTextField();
        txt_shoe_QTY = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_shoe_UnitCost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_totalAmt = new javax.swing.JTextField();
        txt_rental = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbl_Issue_ref = new javax.swing.JLabel();
        txt_note = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Shoes Issue");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 960, 10));

        txt_search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 370, 30));

        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Employee Search (Name/ID) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 12))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 390, 60));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Employee Details"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_empid.setEditable(false);
        txt_empid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txt_empid, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 130, 21));

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel6.setText("EPF No.  :-");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txt_name.setEditable(false);
        txt_name.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 230, 21));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel7.setText("Name :-");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 20));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel11.setText(" NIC No.  :-");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txt_empNic.setEditable(false);
        txt_empNic.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txt_empNic, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 130, -1));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel8.setText("Rank :-");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N
        jPanel1.add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 130, -1));

        lbl_photo.setBackground(new java.awt.Color(102, 204, 255));
        lbl_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(lbl_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 70, 60));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton1.setText("Veiw Employee Photo");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 150, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 530, 140));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "QTY", "Unit Cost", "Issued Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 510, 150));

        jButton3.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save 1.png"))); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 170, 50));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, 130, 50));

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel3.setText("Unit Cost :-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 70, 20));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel12.setText("  Issued Date :-");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 90, 20));
        getContentPane().add(date_issueOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 120, 25));

        cb_shoes.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cb_shoes.setSelected(true);
        cb_shoes.setText("Shoes");
        cb_shoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_shoesActionPerformed(evt);
            }
        });
        getContentPane().add(cb_shoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel4.setText("    Item :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 50, 20));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel5.setText(" Total :- ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 50, 20));

        txt_installment.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_installment.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_installmentFocusGained(evt);
            }
        });
        txt_installment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_installmentKeyTyped(evt);
            }
        });
        getContentPane().add(txt_installment, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 50, 25));

        txt_shoe_QTY.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_shoe_QTY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_shoe_QTYKeyTyped(evt);
            }
        });
        getContentPane().add(txt_shoe_QTY, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 50, 25));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel9.setText("  No. of Installments :-");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 130, 20));

        txt_shoe_UnitCost.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_shoe_UnitCost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_shoe_UnitCostFocusLost(evt);
            }
        });
        txt_shoe_UnitCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_shoe_UnitCostKeyTyped(evt);
            }
        });
        getContentPane().add(txt_shoe_UnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 90, 25));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel10.setText("Quantity :-");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 20));

        txt_totalAmt.setEditable(false);
        txt_totalAmt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_totalAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 90, 25));

        txt_rental.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_rental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rentalKeyTyped(evt);
            }
        });
        getContentPane().add(txt_rental, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 100, 25));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel13.setText("  Rental :-");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 60, 20));

        lbl_Issue_ref.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        getContentPane().add(lbl_Issue_ref, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 80, 20));

        txt_note.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noteActionPerformed(evt);
            }
        });
        getContentPane().add(txt_note, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 230, 25));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 960, 10));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(252, 15, 15));
        jLabel25.setText("*");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, 20));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(252, 15, 15));
        jLabel22.setText(" *");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 20, 30));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(252, 15, 15));
        jLabel16.setText("*");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 20, 20));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(252, 15, 15));
        jLabel24.setText("*");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 20, 20));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(252, 15, 15));
        jLabel21.setText("*");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 10, 30));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton4.setText("Shoe Issue Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 400, 170, 50));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Recent Issues"));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 530, 180));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 102, 0), 2, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 102, 0))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 390, 340));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("Note :- ");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained

    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                lbl_photo.setIcon(null);

                Statement st = DbConnection.getconnection().createStatement();
                ResultSet rs = st.executeQuery("select * from employee_reg");
                while (rs.next()) {
                    String code = rs.getString("EPFno");
                    String nic = rs.getString("NIC");
                    String name = rs.getString("NameWithInitials");
                    String rate = rs.getString("ShiftRate");
                    String rank = rs.getString("Designation");

                    byte[] imagedata = rs.getBytes("EMPImage");

                    if (txt_search.getText().equals(code) || txt_search.getText().equals(name) || txt_search.getText().equals(nic)) {

                        if (imagedata == null) {

                            lbl_photo.setText("No Image");
                            lbl_photo.setForeground(Color.red);
                            format = new ImageIcon("NO Image");

                        } else {

                            format = new ImageIcon(imagedata);
                            lbl_photo.setIcon(format);

                        }
                        txt_empid.setText(code);
                        txt_name.setText(name);
                        txt_empNic.setText(nic);
                        txt_rank.setText(rank);

                    } else {
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            shoe_issue_ref();
            
            get_data_to_table();
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    String New_ID = "";

    private void shoe_issue_ref() {

        try {

            Connection conn = DbConnection.getconnection();
            String sql = "select MAX(Ref) from shoes_issue";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                String id = rs.getString("MAX(Ref)");
                int new_id = 0;
                if (id == null) {
                    id = "0";
                    new_id = 1;
                } else {

                    String[] parts = id.split("/");
                    String id_part = parts[0];
                    String epmID_part = parts[1];

                    int ref_id = Integer.parseInt(id_part);
                    new_id = ref_id + 1;

                }
                New_ID = Integer.toString(new_id);
                String ref = Integer.toString(new_id) + "/" + txt_empid.getText();
                lbl_Issue_ref.setText(ref);

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (date_issueOn.getDate() == null | txt_empid.getText().isEmpty() | txt_installment.getText().isEmpty() | txt_shoe_QTY.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, " Star Marked Fields Cannot be Empty ");

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String issueOn = sdf.format(date_issueOn.getDate());

            String item = "Shoes";

            try {
                Statement st = DbConnection.getconnection().createStatement();

                //shoe_issue_ref();

                st.executeUpdate("insert into shoes_issue values('" + txt_empid.getText() + "','" + item + "','" + txt_shoe_QTY.getText() + "','" + txt_shoe_UnitCost.getText() + "','" + txt_installment.getText() + "','" + txt_rental.getText() + "','" + issueOn + "','00-00-000','00-00-0000','" + txt_name.getText() + "','" + txt_note.getText() + "','on-going', '" + txt_totalAmt.getText() + "','" + lbl_Issue_ref.getText() + "')");

                JOptionPane.showMessageDialog(rootPane, " Saved ...");
                clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cb_shoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_shoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_shoesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        photo_test pt = new photo_test();
        pt.lbl_test.setIcon(format);
        pt.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_installmentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_installmentFocusGained

        if (txt_shoe_QTY.getText().isEmpty() | txt_shoe_UnitCost.getText().isEmpty()) {

        } else {

            Double qty = Double.parseDouble(txt_shoe_QTY.getText());
            Double unitCost = Double.parseDouble(txt_shoe_UnitCost.getText());

            String total = String.format("%.2f", (qty * unitCost));

            txt_totalAmt.setText(total);
        }
    }//GEN-LAST:event_txt_installmentFocusGained

    private void txt_shoe_UnitCostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shoe_UnitCostFocusLost
        if (txt_shoe_QTY.getText().isEmpty() | txt_shoe_UnitCost.getText().isEmpty()) {

        } else {

            Double qty = Double.parseDouble(txt_shoe_QTY.getText());
            Double unitCost = Double.parseDouble(txt_shoe_UnitCost.getText());

            String total = String.format("%.2f", (qty * unitCost));

            txt_totalAmt.setText(total);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_shoe_UnitCostFocusLost

    private void txt_shoe_QTYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shoe_QTYKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;
            text = txt_shoe_QTY.getText().toCharArray();
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
    }//GEN-LAST:event_txt_shoe_QTYKeyTyped

    private void txt_shoe_UnitCostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shoe_UnitCostKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;
            text = txt_shoe_UnitCost.getText().toCharArray();
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
    }//GEN-LAST:event_txt_shoe_UnitCostKeyTyped

    private void txt_installmentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_installmentKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;
            text = txt_installment.getText().toCharArray();
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
    }//GEN-LAST:event_txt_installmentKeyTyped

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

    private void txt_noteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noteActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Shoe_Issue_DELETE si = new Shoe_Issue_DELETE();
        si.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKeyReleased

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
            java.util.logging.Logger.getLogger(Shoe_Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Shoe_Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Shoe_Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Shoe_Issue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shoe_Issue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cb_shoes;
    private com.toedter.calendar.JDateChooser date_issueOn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_Issue_ref;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JTextField txt_empNic;
    private javax.swing.JTextField txt_empid;
    private javax.swing.JTextField txt_installment;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_note;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_rental;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_shoe_QTY;
    private javax.swing.JTextField txt_shoe_UnitCost;
    private javax.swing.JTextField txt_totalAmt;
    // End of variables declaration//GEN-END:variables

    private void clear() {

        date_issueOn.setDate(null);

        txt_empNic.setText("");
        txt_empid.setText("");

        txt_installment.setText("");

        txt_name.setText("");
        txt_rank.setText("");
        txt_note.setText("");
        txt_totalAmt.setText("");
        txt_shoe_UnitCost.setText("");
        txt_rental.setText("");

        txt_shoe_QTY.setText("");
        
        lbl_Issue_ref.setText("");

        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);

    }

}
