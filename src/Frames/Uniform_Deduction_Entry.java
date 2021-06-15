/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.PopUp_Emp_Table.POPUP_EMP_TABLE;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class Uniform_Deduction_Entry extends javax.swing.JFrame {

    /**
     * Creates new form Advance_RASS
     */
    public Uniform_Deduction_Entry() {
        initComponents();
        get_Location_Details();
        TitleBar();
        get_payTypes();
        auto_completer();
        jTable1.getTableHeader().setDefaultRenderer(new Table_Header.HeaderColor());

        advance_doc_no();
        txt_uniform_empName.grabFocus();
    }

    private void get_payTypes() {
        try {

            String[] theSeven = {"Hand", "Bank", "Slip"};
            JComboBox combo1 = new JComboBox(theSeven);

            TableColumn col = jTable1.getColumnModel().getColumn(5);
            col.setCellEditor(new DefaultCellEditor(combo1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void auto_completer() {

        try {

            Statement st = DbConnection.getconnection().createStatement();
            ResultSet rs = st.executeQuery("select * from employee_reg where IsResigned=0 ");

            TextAutoCompleter ta = new TextAutoCompleter(txt_uniform_empName);

            while (rs.next()) {
//                String code = rs.getString("EPFno");
                //String Employeecode = rs.getString("EmployeeNo");
//                String nic = rs.getString("NIC");
                String NameWithInitials = rs.getString("NameWithInitials");

                // ta.addItem(code);
                ta.addItem(NameWithInitials);
                // ta.addItem(nic);
                //ta.addItem(Employeecode);
            }

        } catch (Exception e) {
            System.out.println("errr");
            e.printStackTrace();

        }

    }

    private void TitleBar() {
        this.setTitle("Advance");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("techseed.png")));
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

    private void advance_doc_no() {

        try {

            Connection con = DbConnection.getconnection();
            String sql = "select MAX(ReferenceID) from salary_advance_1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                int docNo = 0;

                if ((rs.getString("MAX(ReferenceID)")) == null) {

                    docNo = 00001;

                } else {

                    int I_max = Integer.parseInt(rs.getString("MAX(ReferenceID)"));
                    docNo = I_max + 00001;

                }
                String doc = String.format("%05d", docNo);

                txt_doc_no.setText(doc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void emp_check() {
        //in Table
        int row_count = jTable1.getRowCount();
        System.out.println(row_count);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int row = 0; row < row_count; row++) {

            String EPFno = jTable1.getValueAt(row, 2).toString();

            String New_EPF = txt_EmployeeNo.getText();

            if (EPFno.equals(New_EPF)) {

                JOptionPane.showMessageDialog(rootPane, txt_EmployeeNo.getText() + "- Selected employee already in the table.  ");
                txt_EmployeeNo.setText("");
            } else {

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

        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        cmb_defLocation = new javax.swing.JComboBox();
        txt_locCode = new javax.swing.JTextField();
        txt_doc_no = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        txt_EmployeeNo = new javax.swing.JTextField();
        lbl_locCode = new javax.swing.JLabel();
        txt_rank = new javax.swing.JTextField();
        txt_uniform_empName = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jButton17 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_locName = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        date_issueOn = new com.toedter.calendar.JDateChooser();
        txt_uniform_QTY = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_uniform_UnitCost = new javax.swing.JTextField();
        txt_totalAmt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_installment = new javax.swing.JTextField();
        txt_rental = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_note = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        date_last = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        date_1st = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jLabel1.setText("Uniform Deductions");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1110, 10));

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
                cmb_defLocationPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_defLocationPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_defLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 340, -1));

        txt_locCode.setEditable(false);
        txt_locCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_locCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_locCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_locCodeFocusLost(evt);
            }
        });
        getContentPane().add(txt_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 90, 23));

        txt_doc_no.setEditable(false);
        txt_doc_no.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_doc_no.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_doc_noFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_doc_noFocusLost(evt);
            }
        });
        getContentPane().add(txt_doc_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 100, 20));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Rank", "EMP. No.", "Name", "Installments", "Rental", "Issued", "1st Install.", "Last Install.", "Quantity", "Unit Cost", "Total", "Note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(150);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 740, 510));

        jButton10.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Available Updates.png"))); // NOI18N
        jButton10.setText("Load All Employees");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 260, 50));

        jButton12.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Plus-30.png"))); // NOI18N
        jButton12.setText("Add Employee");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 160, 50));

        jButton14.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        jButton14.setText("Save");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 160, 70));

        txt_EmployeeNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_EmployeeNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_EmployeeNoKeyPressed(evt);
            }
        });
        getContentPane().add(txt_EmployeeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 60, 25));

        lbl_locCode.setBackground(new java.awt.Color(204, 204, 204));
        lbl_locCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_locCode.setOpaque(true);
        getContentPane().add(lbl_locCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 40, 20));

        txt_rank.setEditable(false);
        txt_rank.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_rank.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rankFocusGained(evt);
            }
        });
        txt_rank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rankActionPerformed(evt);
            }
        });
        txt_rank.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rankKeyPressed(evt);
            }
        });
        getContentPane().add(txt_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 60, 25));

        txt_uniform_empName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_uniform_empName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_uniform_empNameFocusGained(evt);
            }
        });
        txt_uniform_empName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_uniform_empNameActionPerformed(evt);
            }
        });
        txt_uniform_empName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_uniform_empNameKeyPressed(evt);
            }
        });
        getContentPane().add(txt_uniform_empName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 270, 25));

        jButton16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Minus-30.png"))); // NOI18N
        jButton16.setText("Remove");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 140, 50));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 850, 10));

        jButton17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jButton17.setText("Clear All");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 140, 70));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 80, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Employee :-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        lbl_locName.setBackground(new java.awt.Color(204, 204, 204));
        lbl_locName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_locName.setOpaque(true);
        getContentPane().add(lbl_locName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 350, 20));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel16.setText("Final Installment :-");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 140, 20));
        getContentPane().add(date_issueOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 120, 20));

        txt_uniform_QTY.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_uniform_QTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_uniform_QTYActionPerformed(evt);
            }
        });
        txt_uniform_QTY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_uniform_QTYKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_uniform_QTYKeyTyped(evt);
            }
        });
        getContentPane().add(txt_uniform_QTY, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 100, 25));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel17.setText("Quantity :-");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 20));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel20.setText("Unit Cost :-");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 70, 20));

        txt_uniform_UnitCost.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_uniform_UnitCost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_uniform_UnitCostFocusLost(evt);
            }
        });
        txt_uniform_UnitCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_uniform_UnitCostActionPerformed(evt);
            }
        });
        txt_uniform_UnitCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_uniform_UnitCostKeyTyped(evt);
            }
        });
        getContentPane().add(txt_uniform_UnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 100, 25));

        txt_totalAmt.setEditable(false);
        txt_totalAmt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_totalAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 100, 25));

        jLabel26.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel26.setText(" Total :- ");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 50, 20));

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel27.setText("  No. of Installments :-");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 130, 20));

        txt_installment.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_installment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_installmentActionPerformed(evt);
            }
        });
        txt_installment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_installmentKeyTyped(evt);
            }
        });
        getContentPane().add(txt_installment, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 100, 25));

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
        getContentPane().add(txt_rental, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 100, 25));

        jLabel28.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel28.setText("     Note :-");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 60, 20));

        txt_note.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noteActionPerformed(evt);
            }
        });
        getContentPane().add(txt_note, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 230, 25));

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel14.setText("    Item :-");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 50, 20));

        jComboBox1.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Uniform" }));
        jComboBox1.setEnabled(false);
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 120, -1));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel18.setText("  Issued Date :-");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 90, 20));
        getContentPane().add(date_last, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 120, 20));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel19.setText("1st Installment :-");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 20));
        getContentPane().add(date_1st, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 120, 20));

        jLabel29.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jLabel29.setText("  Rental :-");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 60, 20));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "All Employees in One Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Georgia", 0, 11))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 630, 70));

        jButton15.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        jButton15.setText("Add Deduction Details to All");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 350, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_defLocationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_defLocationFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationFocusGained

    private void cmb_defLocationPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuWillBecomeInvisible

        if (cmb_defLocation.getSelectedItem().equals("=Location=")) {

            txt_locCode.setText("N/A");
            txt_locCode.setForeground(Color.red);

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

//                ResultSet rs1 = st.executeQuery("select * from location_shift_rates where LocCode= '" + txt_locCode.getText() + "' ");
//
//                while (rs1.next()) {
//
//                    String rank = rs1.getString("Rank");
//
//                }
                //cmb_defLocation.setEditable(false);
                //cmb_defLocation.setEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_cmb_defLocationPopupMenuWillBecomeInvisible

    private void txt_locCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusGained
        // get_carder_and_enterd_shifts_total();//
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_locCodeFocusGained

    private void txt_locCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_locCodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_locCodeFocusLost

    private void txt_doc_noFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_doc_noFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_noFocusGained

    private void txt_doc_noFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_doc_noFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_noFocusLost

    private void get_initial_advance_details() {

        try {

            String loc = txt_locCode.getText();

            if (loc.equals("N/A") | loc.isEmpty()) {
            } else {
                Connection con = DbConnection.getconnection();
                String sql = "select * from employee_reg where DefLocation='" + loc + "'";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                while (rs.next()) {
                    String rank = rs.getString("Designation");
                    Vector v = new Vector();
                    v.add("");
                    v.add(rank);
                    v.add(rs.getString("EmployeeNo"));
                    v.add(rs.getString("NameWithInitials"));

                    dtm.addRow(v);

                }
            }

            //find null cells and replace with 0.00
//            int row = jTable1.getModel().getRowCount();
//            for (int i = 0; row > i; i++) {
//
//                Object adv_amt = jTable1.getModel().getValueAt(i, 4);
//
//                if (adv_amt == null) {
//                    jTable1.getModel().setValueAt("0.00", i, 4);
//
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void get_one_emp() {
        emp_check();
        String name = txt_uniform_empName.getText();
        String epf = txt_EmployeeNo.getText();
        String rank = txt_rank.getText();
        String locno = lbl_locCode.getText();
        String adv = txt_rental.getText();

        if (locno.isEmpty() | epf.isEmpty() | adv.isEmpty() | adv.equals("Advance Amount") | rank.equals("N/A")) {

        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            Vector v = new Vector();
            v.add(locno);
            v.add(rank);
            v.add(epf);
            v.add(name);
            v.add(adv);
            v.add(pay_type);
            dtm.addRow(v);

        }

    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        get_initial_advance_details();
        //jButton10.setEnabled(false);
        //cmb_defLocation.setEnabled(false);

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //get_one_emp();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Date_issueOn = sdf.format(date_issueOn.getDate());
        String Date_1st = sdf.format(date_1st.getDate());
        String Date_last = sdf.format(date_last.getDate());
        if (Date_issueOn == null | Date_1st == null | Date_last == null | txt_rental.getText().isEmpty() | txt_installment.getText().isEmpty()) {
        } else {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            Vector v = new Vector();
            v.add(jComboBox1.getSelectedItem().toString());//item
            v.add(txt_rank.getText());//rank
            v.add(txt_EmployeeNo.getText());//emp no
            v.add(txt_uniform_empName.getText());//name
            v.add(txt_installment.getText());//install
            v.add(txt_rental.getText());//rent
            v.add(Date_issueOn);//isseued
            v.add(Date_1st);//1st
            v.add(Date_last);//last
            v.add(txt_uniform_QTY.getText());//qty
            v.add(txt_uniform_UnitCost.getText());//cost
            v.add(txt_totalAmt.getText());//total
            v.add(txt_note.getText());//note
            dtm.addRow(v);

        }


    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
//        try {
//
//            Connection con = DbConnection.getconnection();
//
////            String up = "update salary_advance_1 set Status ='OLD' where PayMonth='" + cmb_month.getSelectedItem().toString() + "' and PayYear='" + cmb_year.getSelectedItem().toString() + "' and Location='" + txt_locCode.getText() + "' ";
////            PreparedStatement pstup = con.prepareStatement(up);
////            pstup.execute();
//            String sql = "insert into salary_advance_1 (EPFno,Name,Rank,IssueDate,Amount,Note,ReferenceID,Reference,Status,Location,LocType,PayMonth,PayYear) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
//            PreparedStatement pst = con.prepareStatement(sql);
//
//            int nrow = jTable1.getModel().getRowCount();
//
//            if (nrow == 0) {
//
//            } else {
//                for (int i = 0; nrow > i; i++) {
//
//                    String loc = jTable1.getModel().getValueAt(i, 0).toString();
//                    String rank = jTable1.getModel().getValueAt(i, 1).toString();
//                    String empid = jTable1.getModel().getValueAt(i, 2).toString();
//                    String name = jTable1.getModel().getValueAt(i, 3).toString();
//
//                    String up = "";
//                    PreparedStatement pstup = con.prepareStatement(up);
//                    pstup.execute();
//
//                    String amount = jTable1.getModel().getValueAt(i, 4).toString();
//                    Double d = Double.parseDouble(amount);
//                    String Adv = String.format("%.2f", d);
//
//                    String paytype = jTable1.getModel().getValueAt(i, 5).toString();
//
//                    pst.setString(1, empid);//empid
//                    pst.setString(2, name);//name
//                    pst.setString(3, rank);//rank
//                    pst.setString(4, "");//date
//                    pst.setString(5, Adv);//amonut
//                    pst.setString(6, paytype);//note
//                    pst.setString(7, txt_doc_no.getText());//ref ID
//                    pst.setString(8, txt_doc_no.getText() + "_" + empid);//Reference
//                    pst.setString(9, "PAID");//stat
//                    pst.setString(10, loc);//loc
//                    pst.setString(11, "");//loc type
//
//                    pst.addBatch();
//
//                }
//
//                pst.executeBatch();
//
//                String del = "delete from salary_advance_1 where Status='OLD' and Location='" + txt_locCode.getText() + "' ";
//                PreparedStatement pst_del = con.prepareStatement(del);
//                pst_del.execute();
//
//                JOptionPane.showMessageDialog(rootPane, "Advance Saved...!");
//
//                jButton17ActionPerformed(evt);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            Connection con = DbConnection.getconnection();
            String sql = "insert into uniform_issue (EPFno,Item,QTY,UnitCost,Installments,Rental,IssueOn,1stInstallment,LastInstallment,EmployeeName,Note,Status,TotalAmount) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = con.prepareStatement(sql);

            int r = jTable1.getRowCount();
            if (r < 0) {
            } else {
                for (int row = 0; row < r; row++) {

                    String item = jTable1.getValueAt(row, 0).toString();//item  
                    String rank = jTable1.getValueAt(row, 1).toString();//rank
                    String epf = jTable1.getValueAt(row, 2).toString();//emp no
                    String name = jTable1.getValueAt(row, 3).toString();//name
                    String install = jTable1.getValueAt(row, 4).toString();//install
                    String rent = jTable1.getValueAt(row, 5).toString();//rent
                    String issue = jTable1.getValueAt(row, 6).toString();//isseued
                    String first = jTable1.getValueAt(row, 7).toString();//1st
                    String last = jTable1.getValueAt(row, 8).toString();//last
                    String qty = jTable1.getValueAt(row, 9).toString();//qty
                    String cost = jTable1.getValueAt(row, 10).toString();//cost
                    String total = jTable1.getValueAt(row, 11).toString();//total
                    String note = jTable1.getValueAt(row, 12).toString();//note

                    pst.setString(1, epf);
                    pst.setString(2, item);
                    pst.setString(3, qty);
                    pst.setString(4, cost);
                    pst.setString(5, install);
                    pst.setString(6, rent);
                    pst.setString(7, issue);
                    pst.setString(8, first);//Reference
                    pst.setString(9, last);
                    pst.setString(10, name);
                    pst.setString(11, note);
                    pst.setString(12, "on-going");//month
                    pst.setString(13, total);//year

                    pst.addBatch();

                }

                pst.executeBatch();
                JOptionPane.showMessageDialog(rootPane, " Saved ...");
                
                jButton17ActionPerformed(evt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton14ActionPerformed

    private void txt_rankFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rankFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankFocusGained

    private void txt_rankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankActionPerformed

    private void txt_rankKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rankKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rankKeyPressed

    private void txt_uniform_empNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_uniform_empNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_uniform_empNameFocusGained

    private void txt_uniform_empNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_uniform_empNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_uniform_empNameActionPerformed
    public String pay_type = "Cash";
    private void txt_uniform_empNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uniform_empNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                String search = txt_uniform_empName.getText();

                if (search.isEmpty()  ) {
                    

                } else {

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + search + "' OR NameWithInitials='" + search + "'   ");
                    while (rs3.next()) {

                        if ((rs3.getInt("COUNT(*)") > 1)) {
                            PopUp_Emp_Table pt = new PopUp_Emp_Table();
                            pt.setVisible(true);
                            pt.setTitle("UNIFORM");

                            DefaultTableModel dtm = (DefaultTableModel) POPUP_EMP_TABLE.getModel();

                            Statement st4 = DbConnection.getconnection().createStatement();
                            ResultSet rs4 = st4.executeQuery("select * from employee_reg where EmployeeNo='" + search + "' OR NameWithInitials='" + search + "'   ");
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
                            ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + search + "'  OR NameWithInitials='" + search + "'  ");
                            while (rs.next()) {

                                String code = rs.getString("EmployeeNo");

                                String name = rs.getString("NameWithInitials");
                                String loc = rs.getString("DefLocation");
                                String rank = rs.getString("Designation");
                                String pay = rs.getString("PayType");
                                pay_type = pay;
                                txt_EmployeeNo.setText(code);
                                txt_uniform_empName.setText(name);
                                //jLabel2.setText(name);

                                txt_rank.setText(rank);
//                                txt_locNo.setText(txt_locCode.getText());
                                lbl_locCode.setText(loc);

                                Statement st1 = DbConnection.getconnection().createStatement();
                                ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'  ");
                                while (rs1.next()) {

                                    lbl_locName.setText(rs1.getString("LocName"));
                                }
                                // txt_advAmt.grabFocus();

                            }

                        }

                    }

                }
            } catch (Exception e) {
                System.out.println("eddddddr");
                e.printStackTrace();
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_uniform_empNameKeyPressed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int nrow = jTable1.getSelectedRowCount();

        if (nrow < 1) {
        } else {

            int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to remove the selected row/(s) ?", null, JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {

                for (int i = 0; nrow > i; i++) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(jTable1.getSelectedRow());
                }

            } else {

            }

        }


    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int nrow = jTable1.getRowCount();

        int reply = JOptionPane.showConfirmDialog(rootPane, "Do you want to clear the table?", null, JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            if (nrow < 1) {
            } else {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
            }

            txt_rank.setText("");
            txt_EmployeeNo.setText("");
            txt_uniform_empName.setText("");
            txt_installment.setText("");
            txt_rental.setText("");

            txt_uniform_QTY.setText("");
            txt_uniform_UnitCost.setText("");
            txt_totalAmt.setText("");
            txt_note.setText("");

        } else {

        }


    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        auto_completer();
        get_Location_Details();
        txt_rank.setText("");
        txt_EmployeeNo.setText("");
        txt_uniform_empName.setText("");
        txt_installment.setText("");
        txt_rental.setText("");

        txt_uniform_QTY.setText("");
        txt_uniform_UnitCost.setText("");
        txt_totalAmt.setText("");
        txt_note.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        MAIN.jMenuItem27.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //   MAIN.jMenuItem27.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void txt_EmployeeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmployeeNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {

                String search = txt_EmployeeNo.getText();

                if (search.isEmpty() | txt_locCode.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please select a Loctaion");

                } else {

                    Statement st3 = DbConnection.getconnection().createStatement();
                    ResultSet rs3 = st3.executeQuery("select *,Count(*) from employee_reg where EmployeeNo='" + search + "'    ");
                    while (rs3.next()) {

                        if ((rs3.getInt("COUNT(*)") > 1)) {
                            PopUp_Emp_Table pt = new PopUp_Emp_Table();
                            pt.setVisible(true);
                            pt.setTitle("Advance");

                            DefaultTableModel dtm = (DefaultTableModel) POPUP_EMP_TABLE.getModel();

                            Statement st4 = DbConnection.getconnection().createStatement();
                            ResultSet rs4 = st4.executeQuery("select * from employee_reg where EmployeeNo='" + search + "'   ");
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
                            ResultSet rs = st.executeQuery("select * from employee_reg where EmployeeNo='" + search + "'    ");
                            while (rs.next()) {

                                String code = rs.getString("EmployeeNo");

                                String name = rs.getString("NameWithInitials");
                                String loc = rs.getString("DefLocation");
                                String rank = rs.getString("Designation");
                                String pay = rs.getString("PayType");
                                pay_type = pay;
                                txt_EmployeeNo.setText(code);
                                txt_uniform_empName.setText(name);
                                //jLabel2.setText(name);

                                txt_rank.setText(rank);
//                                txt_locNo.setText(txt_locCode.getText());
                                lbl_locCode.setText(loc);

                                Statement st1 = DbConnection.getconnection().createStatement();
                                ResultSet rs1 = st1.executeQuery("select * from location_reg where LocCode='" + loc + "'  ");
                                while (rs1.next()) {

                                    lbl_locName.setText(rs1.getString("LocName"));
                                }
                                //txt_advAmt.grabFocus();

                            }

                        }

                    }

                }
            } catch (Exception e) {
                System.out.println("eddddddr");
                e.printStackTrace();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmployeeNoKeyPressed

    private void cmb_defLocationPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_defLocationPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_defLocationPopupMenuCanceled

    private void txt_uniform_QTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_uniform_QTYActionPerformed
        txt_uniform_UnitCost.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_uniform_QTYActionPerformed

    private void txt_uniform_QTYKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uniform_QTYKeyPressed

    }//GEN-LAST:event_txt_uniform_QTYKeyPressed

    private void txt_uniform_QTYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uniform_QTYKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;
            text = txt_uniform_QTY.getText().toCharArray();
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
    }//GEN-LAST:event_txt_uniform_QTYKeyTyped

    private void txt_uniform_UnitCostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_uniform_UnitCostFocusLost
        if (txt_uniform_QTY.getText().isEmpty() | txt_uniform_UnitCost.getText().isEmpty()) {

        } else {

            Double qty = Double.parseDouble(txt_uniform_QTY.getText());
            Double unitCost = Double.parseDouble(txt_uniform_UnitCost.getText());

            String total = String.format("%.2f", (qty * unitCost));

            txt_totalAmt.setText(total);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_txt_uniform_UnitCostFocusLost

    private void txt_uniform_UnitCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_uniform_UnitCostActionPerformed
        txt_installment.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_uniform_UnitCostActionPerformed

    private void txt_uniform_UnitCostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uniform_UnitCostKeyTyped
        if (Character.isDigit(evt.getKeyChar()) | (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {

            char text[];
            int count = 0;
            text = txt_uniform_UnitCost.getText().toCharArray();
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
    }//GEN-LAST:event_txt_uniform_UnitCostKeyTyped

    private void txt_installmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_installmentActionPerformed
        txt_rental.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_installmentActionPerformed

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

        }         // TODO add your handling code here:
    }//GEN-LAST:event_txt_installmentKeyTyped

    private void txt_rentalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rentalFocusGained
        if (txt_totalAmt.getText().isEmpty() | txt_uniform_UnitCost.getText().isEmpty() | txt_installment.getText().isEmpty()) {

        } else {

            Double tot = Double.parseDouble(txt_totalAmt.getText());
            Double months = Double.parseDouble(txt_installment.getText());

            String total = String.format("%.2f", (tot / months));

            txt_rental.setText(total);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txt_rentalFocusGained

    private void txt_rentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rentalActionPerformed
        txt_note.grabFocus();        // TODO add your handling code here:
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

        }              // TODO add your handling code here:
    }//GEN-LAST:event_txt_rentalKeyTyped

    private void txt_noteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noteActionPerformed

//        jButton3ActionPerformed(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noteActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Date_issueOn = sdf.format(date_issueOn.getDate());
        String Date_1st = sdf.format(date_1st.getDate());
        String Date_last = sdf.format(date_last.getDate());

        for (int row = 0; row < jTable1.getRowCount(); row++) {
            dtm.setValueAt(jComboBox1.getSelectedItem().toString(), row, 0);
            dtm.setValueAt(txt_installment.getText(), row, 4);
            dtm.setValueAt(txt_rental.getText(), row, 5);
            dtm.setValueAt(Date_issueOn, row, 6);
            dtm.setValueAt(Date_1st, row, 7);
            dtm.setValueAt(Date_last, row, 8);
            dtm.setValueAt(txt_uniform_QTY.getText(), row, 9);
            dtm.setValueAt(txt_uniform_UnitCost.getText(), row, 10);
            dtm.setValueAt(txt_totalAmt.getText(), row, 11);
            dtm.setValueAt(txt_note.getText(), row, 12);

        }


    }//GEN-LAST:event_jButton15ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Uniform_Deduction_Entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Uniform_Deduction_Entry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_defLocation;
    private com.toedter.calendar.JDateChooser date_1st;
    private com.toedter.calendar.JDateChooser date_issueOn;
    private com.toedter.calendar.JDateChooser date_last;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_locCode;
    private javax.swing.JLabel lbl_locName;
    private javax.swing.JTextField txt_EmployeeNo;
    private javax.swing.JTextField txt_doc_no;
    private javax.swing.JTextField txt_installment;
    private javax.swing.JTextField txt_locCode;
    private javax.swing.JTextField txt_note;
    private javax.swing.JTextField txt_rank;
    private javax.swing.JTextField txt_rental;
    private javax.swing.JTextField txt_totalAmt;
    private javax.swing.JTextField txt_uniform_QTY;
    private javax.swing.JTextField txt_uniform_UnitCost;
    public static javax.swing.JTextField txt_uniform_empName;
    // End of variables declaration//GEN-END:variables

}
