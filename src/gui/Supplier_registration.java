/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.jiat.aurora_market_place.MySql;
import com.jiat.aurora_market_place.TableContentAlign;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DELL
 */
public class Supplier_registration extends javax.swing.JPanel {
    
    public TableCellRenderer centerAlignment = new TableContentAlign();

    public Supplier_registration() {
        initComponents();

        tableDesign();
        loadSuppliers();
    }

    public void tableDesign() {
        JTableHeader table_header = jTable1.getTableHeader();
        jTable1.setBackground(Color.WHITE);

        Color table_header_color = new Color(121, 203, 96);
        Color table_header_font_color = new Color(91, 97, 135);

        table_header.setBackground(table_header_color);
        table_header.setForeground(Color.WHITE);

        table_header.setFont(new Font("Yu Gothic Ui", Font.BOLD, 14));
        
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(centerAlignment);
    }

    public void resetFields() {
        jTextField1.setText("None");
        jTextField5.setText("None");
        jTextField7.setText("None");
        jTextField9.setText("None");

        jTextField3.setText("");
        jTextField10.setText("");
        jTextField6.setText("");
        jTextField4.setText("");
        jTextField8.setText("");
        
        jButton5.setText("Deactivate");

        jTextField3.grabFocus();

        jButton3.setEnabled(false);
        jButton5.setEnabled(false);
        jButton2.setEnabled(false);
        jButton4.setEnabled(true);

    }

    public void loadSuppliers() {
        try {
            ResultSet rs = MySql.search("SELECT * FROM `supplier` INNER JOIN `company_branch` ON `supplier`.`company_branch_id`=`company_branch`.`id` INNER JOIN `company_branch_address` ON `company_branch_address`.`id`=`company_branch`.`company_branch_address_id` INNER JOIN `city` ON `city`.`id`= `company_branch_address`.`city_id` INNER JOIN `status` ON `supplier`.`status_id` = `status`.`id` ORDER BY `supplier`.`f_name` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("supplier.id"));

                String supplier_name = rs.getString("supplier.f_name") + " " + rs.getString("supplier.l_name");
                v.add(supplier_name);

                v.add(rs.getString("supplier.contact_number"));
                v.add(rs.getString("supplier.email"));
                v.add(rs.getString("company_branch.name"));
                v.add(rs.getString("company_branch.branch_contact_number"));

                String address = rs.getString("company_branch_address.line1") + "," + rs.getString("company_branch_address.line2") + "," + rs.getString("city.name");
                v.add(address);

                v.add(rs.getString("status.name"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSuppliers(String text) {
        try {
            ResultSet rs = MySql.search("SELECT * FROM `supplier` INNER JOIN `company_branch` ON `supplier`.`company_branch_id`=`company_branch`.`id` INNER JOIN `company_branch_address` ON `company_branch_address`.`id`=`company_branch`.`company_branch_address_id` INNER JOIN `city` ON `city`.`id`= `company_branch_address`.`city_id` INNER JOIN `status` ON `supplier`.`status_id` = `status`.`id` WHERE `supplier`.`f_name` LIKE '" + text + "%' ORDER BY `supplier`.`f_name` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("supplier.id"));

                String supplier_name = rs.getString("supplier.f_name") + " " + rs.getString("supplier.l_name");
                v.add(supplier_name);

                v.add(rs.getString("supplier.contact_number"));
                v.add(rs.getString("supplier.email"));
                v.add(rs.getString("company_branch.name"));
                v.add(rs.getString("company_branch.branch_contact_number"));

                String address = rs.getString("company_branch_address.line1") + "," + rs.getString("company_branch_address.line2") + "," + rs.getString("city.name");
                v.add(address);

                v.add(rs.getString("status.name"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

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

        jPanel1 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-supplier.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Supplier Registration");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel2.setText("First Name");

        jTextField3.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jButton1.setBackground(new java.awt.Color(231, 240, 229));
        jButton1.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(112, 168, 97));
        jButton1.setText("Save Supplier");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-buld.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Fill all these fields then click SAVE SUPPLIER button.");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("After that you can save supplier to system.");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-buld.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Double-Click to select supplier using this table.");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("Then autimatically fill all these fields. After that you can");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setText("UPDATE , DELETE or DEACTIVATE supplier.");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel4.setText("Contact Number");

        jTextField4.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(219, 237, 255));
        jButton4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 73, 145));
        jButton4.setText("Select Branch");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("None");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(153, 153, 153));
        jTextField5.setText("None");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Branch Name");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel8.setText("Email");

        jTextField6.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Branch Address");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(153, 153, 153));
        jTextField7.setText("None");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Company Id");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(153, 153, 153));
        jTextField8.setText("None");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Company Name");

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(153, 153, 153));
        jTextField9.setText("None");

        jButton5.setBackground(new java.awt.Color(237, 237, 237));
        jButton5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton5.setForeground(new java.awt.Color(102, 102, 102));
        jButton5.setText("Deactivate");
        jButton5.setBorderPainted(false);
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(237, 237, 237));
        jButton2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Delete");
        jButton2.setBorderPainted(false);
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(237, 237, 237));
        jButton3.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(102, 102, 102));
        jButton3.setText("Update");
        jButton3.setBorderPainted(false);
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel18.setText("Last Name");

        jTextField10.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                    .addComponent(jTextField5)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 181, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addGap(0, 0, 0)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(0, 0, 0)
                .addComponent(jLabel16)
                .addGap(0, 0, 0)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier Id", "Supplier Name", "Contact Number", "Email", "Branch Name", "Branch Contact Number", "Branch Address", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAlignmentX(1.0F);
        jTable1.setAlignmentY(1.0F);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setGridColor(new java.awt.Color(201, 233, 191));
        jTable1.setRowHeight(23);
        jTable1.setSelectionBackground(new java.awt.Color(216, 243, 207));
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        kGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkBorderRadius(20);
        kGradientPanel3.setkEndColor(new java.awt.Color(242, 242, 242));
        kGradientPanel3.setkStartColor(new java.awt.Color(242, 242, 242));

        jLabel7.setBackground(new Color(0,0,0,0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-search.png"))); // NOI18N

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("Search Supplier");
        jTextField2.setBorder(null);
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2)
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel3.setText("Search Supplier");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-search-gren.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 354, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            jButton4.setEnabled(false);
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select category", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    String branch_name = jTable1.getValueAt(r, 4).toString();

                    ResultSet branch_details = MySql.search("SELECT * FROM `company_branch` WHERE `name`='" + branch_name + "'");
                    branch_details.next();

                    String company_branch_address_id = branch_details.getString("company_branch_address_id");

                    ResultSet company_branch_address_details = MySql.search("SELECT * FROM `company_branch_address` WHERE `id`='" + company_branch_address_id + "'");
                    company_branch_address_details.next();

                    ResultSet city_details = MySql.search("SELECT * FROM `city` WHERE `id`='" + company_branch_address_details.getString("city_id") + "'");
                    city_details.next();

                    ResultSet company_details = MySql.search("SELECT * FROM `company` WHERE `id`='" + branch_details.getString("company_id") + "'");
                    company_details.next();

                    ResultSet supplier_details = MySql.search("SELECT * FROM `supplier` WHERE `id`='" + jTable1.getValueAt(r, 0).toString() + "'");
                    supplier_details.next();

                    jTextField1.setText(branch_details.getString("id"));
                    jTextField5.setText(branch_details.getString("name"));
                    jTextField7.setText(company_branch_address_details.getString("line1") + "," + company_branch_address_details.getString("line2") + "," + city_details.getString("name"));
                    jTextField8.setText(branch_details.getString("company_id"));
                    jTextField9.setText(company_details.getString("name"));

                    jTextField3.setText(supplier_details.getString("f_name"));
                    jTextField10.setText(supplier_details.getString("l_name"));
                    jTextField6.setText(jTable1.getValueAt(r, 3).toString());
                    jTextField4.setText(jTable1.getValueAt(r, 2).toString());

                    String currentStatus = jTable1.getValueAt(r, 7).toString();

                    if (currentStatus.equals("In-Active")) {
                        jButton5.setText("Activate");
                    } else {
                        jButton5.setText("Deactivate");
                    }

                    jButton3.setEnabled(true);
                    jButton5.setEnabled(true);
                    jButton2.setEnabled(true);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased

        String text = jTextField2.getText();
        loadSuppliers(text);

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained

        if (jTextField2.getText().equals("Search Supplier")) {
            jTextField2.setText("");
            jTextField2.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost

        if (jTextField2.getText().equals("")) {
            jTextField2.setText("Search Supplier");
            jTextField2.setForeground(new Color(153, 153, 153));
        }

    }//GEN-LAST:event_jTextField2FocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String f_name = jTextField3.getText();
        String l_name = jTextField10.getText();
        String cnumber = jTextField4.getText();
        String email = jTextField6.getText().toLowerCase();
        String bid = jTextField1.getText();

        if (f_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter first name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (l_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter last name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("07[01245678][0-9]{7}").matcher(cnumber).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid contact number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$").matcher(email).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid Email address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (bid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select branch", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                ResultSet rs = MySql.search("SELECT * FROM `supplier` WHERE `email`='" + email + "' OR`contact_number`='" + cnumber + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Supplier already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    Random rnd = new Random();
                    int number = rnd.nextInt(999999);

                    String supplier_id = "SUP-" + String.format("%06d", number);

                    MySql.iud("INSERT INTO `supplier` (`id`,`f_name`,`l_name`,`contact_number`,`email`,`company_branch_id`) VALUES ('" + supplier_id + "','" + f_name + "','" + l_name + "','" + cnumber + "','" + email + "','" + bid + "')");

                    resetFields();
                    loadSuppliers();

                    JOptionPane.showMessageDialog(this, "New supplier added", "Success", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped

        String mobile = jTextField4.getText();
        String text = mobile + evt.getKeyChar();

        if (mobile.length() == 10) {
            evt.consume();
        } else {
            if (!Pattern.compile("[0-9]+").matcher(text).matches()) {
                evt.consume();
            }
        }

    }//GEN-LAST:event_jTextField4KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Select_branch sb = new Select_branch(this, true);
        sb.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int r = jTable1.getSelectedRow();
        String supplier_id = jTable1.getValueAt(r, 0).toString();

        try {

            String currentStatus = jTable1.getValueAt(r, 7).toString();

            int status;

            if (currentStatus.equals("Active")) {
                status = 2;
            } else {
                status = 1;
            }

            MySql.iud("UPDATE `supplier` SET `status_id`=" + status + " WHERE `id`='" + supplier_id + "'");

            resetFields();
            loadSuppliers();

            JOptionPane.showMessageDialog(this, "User status updated", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int r = jTable1.getSelectedRow();
        String supplier_id = jTable1.getValueAt(r, 0).toString();

        int option = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            try {

                MySql.iud("DELETE FROM `supplier` WHERE `id`='" + supplier_id + "'");
                resetFields();
                loadSuppliers();

                JOptionPane.showMessageDialog(this, "Successfully deleted", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String f_name = jTextField3.getText();
        String l_name = jTextField10.getText();
        String cnumber = jTextField4.getText();
        String email = jTextField6.getText().toLowerCase();
        String bid = jTextField1.getText();

        if (f_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter first name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (l_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter last name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("07[01245678][0-9]{7}").matcher(cnumber).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid contact number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$").matcher(email).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid Email address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (bid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select branch", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                int r = jTable1.getSelectedRow();

                String supplier_id = jTable1.getValueAt(r, 0).toString();

                MySql.iud("UPDATE `supplier` SET `f_name`='" + f_name + "',`l_name`='" + l_name + "',`contact_number`='" + cnumber + "',`email`='" + email + "' WHERE `id`='" + supplier_id + "'");

                resetFields();
                loadSuppliers();
                JOptionPane.showMessageDialog(this, "Successfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    public javax.swing.JTextField jTextField9;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
