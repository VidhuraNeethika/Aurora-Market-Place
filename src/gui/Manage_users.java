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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DELL
 */
public class Manage_users extends javax.swing.JPanel {

    public TableCellRenderer centerAlignment = new TableContentAlign();
    
    public Manage_users() {
        initComponents();

        tableDesign();
        loadUsers();
        loadUserTypes();
        loadCities();
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
        jTable1.getColumnModel().getColumn(5).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(8).setCellRenderer(centerAlignment);
    }

    public void loadUsers() {
        try {
            ResultSet rs = MySql.search("SELECT `user`.`id`,`user`.`f_name`,`user`.`l_name`,`user`.`username`,`user`.`password`,`user`.`contact_number`,`city`.`name`  AS 'city_name',`status`.`name` AS 'status_name',`user_type`.`name` AS 'user_type_name' FROM `user` INNER JOIN `city` ON  `user`.`city_id` = `city`.`id` INNER JOIN `user_type` ON `user`.`user_type_id` = `user_type`.`id` INNER JOIN `status` ON `user`.`status_id` = `status`.`id` ORDER BY `id` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("f_name"));
                v.add(rs.getString("l_name"));
                v.add(rs.getString("username"));
                v.add(rs.getString("password"));
                v.add(rs.getString("contact_number"));
                v.add(rs.getString("user_type_name"));
                v.add(rs.getString("city_name"));
                v.add(rs.getString("status_name"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUsers(String text) {
        try {
            ResultSet rs = MySql.search("SELECT `user`.`id`,`user`.`f_name`,`user`.`l_name`,`user`.`username`,`user`.`password`,`user`.`contact_number`,`city`.`name`  AS 'city_name',`status`.`name` AS 'status_name',`user_type`.`name` AS 'user_type_name' FROM `user` INNER JOIN `city` ON  `user`.`city_id` = `city`.`id` INNER JOIN `user_type` ON `user`.`user_type_id` = `user_type`.`id` INNER JOIN `status` ON `user`.`status_id` = `status`.`id` WHERE `user`.`f_name` LIKE '" + text + "%' OR `user`.`contact_number` LIKE '" + text + "%' ORDER BY `id` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("f_name"));
                v.add(rs.getString("l_name"));
                v.add(rs.getString("username"));
                v.add(rs.getString("password"));
                v.add(rs.getString("contact_number"));
                v.add(rs.getString("user_type_name"));
                v.add(rs.getString("city_name"));
                v.add(rs.getString("status_name"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserTypes() {

        try {
            ResultSet rs = MySql.search("SELECT * FROM `user_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadCities() {
        try {
            ResultSet rs = MySql.search("SELECT * FROM `city`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetFields() {
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jPasswordField1.setText("");
        jTextField7.setText("");
        jComboBox1.setSelectedItem("Select");
        jComboBox2.setSelectedItem("Select");

        jButton4.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);

        jButton4.setText("Deactivate");
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
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-user.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Manage Users");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel2.setText("First Name");

        jTextField3.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel5.setText("Username");

        jTextField5.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel9.setText("Contact Number");

        jTextField7.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel10.setText("User Type");

        jComboBox1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel11.setText("City");

        jComboBox2.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jButton1.setBackground(new java.awt.Color(231, 240, 229));
        jButton1.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(112, 168, 97));
        jButton1.setText("Create User Account");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jButton4.setBackground(new java.awt.Color(237, 237, 237));
        jButton4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(102, 102, 102));
        jButton4.setText("Deactivate");
        jButton4.setBorderPainted(false);
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-buld.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Fill all these fields then click CREATE USER ACCOUNT button.");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("After that you can create user account.");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-buld.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Double-Click to select user using this table.");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("Then autimatically fill all these fields. After that you can");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setText("UPDATE , DELETE or DEACTIVATE user.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1))
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)))
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))))
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17))))
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
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
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
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "First Name", "Last Name", "Username", "Password", "Contact", "User Type", "City", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setGridColor(new java.awt.Color(201, 233, 191));
        jTable1.setRowHeight(23);
        jTable1.setSelectionBackground(new java.awt.Color(216, 243, 207));
        jTable1.setShowGrid(false);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
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
        jTextField2.setText("Search Users");
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
        jLabel3.setText("Search Users");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(0, 0, 0)
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int r = jTable1.getSelectedRow();
        String user_id = jTable1.getValueAt(r, 0).toString();

        try {

            String currentStatus = jTable1.getValueAt(r, 8).toString();

            int status;

            if (currentStatus.equals("Active")) {
                status = 2;
            } else {
                status = 1;
            }

            MySql.iud("UPDATE `user` SET `status_id`=" + status + " WHERE `id`='" + user_id + "'");

            resetFields();
            loadUsers();

            JOptionPane.showMessageDialog(this, "User status updated", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String f_name = jTextField3.getText();
        String l_name = jTextField4.getText();
        String username = jTextField5.getText();
        String password = jPasswordField1.getText();
        String mobile = jTextField7.getText();
        String type = jComboBox1.getSelectedItem().toString();
        String city = jComboBox2.getSelectedItem().toString();

        if (f_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter first name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (l_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter last name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter password", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("07[01245678][0-9]{7}").matcher(mobile).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid contact number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (type.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet city_details = MySql.search("SELECT * FROM `city` WHERE `name`='" + city + "'");
                city_details.next();

                ResultSet user_type_details = MySql.search("SELECT * FROM `user_type` WHERE `name`='" + type + "'");
                user_type_details.next();

                String city_id = city_details.getString("id");
                String type_id = user_type_details.getString("id");

                //RAMDOM NUMBER
                Random rnd = new Random();
                int number = rnd.nextInt(999999);

                String random_number = String.format("%06d", number);

                String user_id = "US-" + random_number;
                //RAMDOM NUMBER

                MySql.iud("INSERT INTO `user` (`id`,`f_name`,`l_name`,`username`,`password`,`contact_number`,`user_type_id`,`city_id`) VALUES ('" + user_id + "','" + f_name + "','" + l_name + "','" + username + "','" + password + "','" + mobile + "'," + Integer.parseInt(type_id) + "," + Integer.parseInt(city_id) + ")");

                resetFields();
                loadUsers();

                JOptionPane.showMessageDialog(this, "New user account created", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped

        String mobile = jTextField7.getText();
        String text = mobile + evt.getKeyChar();

        if (text.length() == 1) {
            if (!text.equals("0")) {
                evt.consume();
            }
        } else if (text.length() == 2) {
            if (!text.equals("07")) {
                evt.consume();
            }
        } else if (text.length() == 3) {
            if (!Pattern.compile("07[01245678]").matcher(text).matches()) {
                evt.consume();
            }
        } else if (text.length() <= 10) {
            if (!Pattern.compile("07[01245678]?[0-9]+").matcher(text).matches()) {
                evt.consume();
            }

        } else {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased

        if (jTextField7.getText().length() == 10) {
            jTextField7.setEditable(false);
        }

    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select user", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                jComboBox1.setSelectedItem(jTable1.getValueAt(r, 6));
                jComboBox2.setSelectedItem(jTable1.getValueAt(r, 7));
                jTextField3.setText(jTable1.getValueAt(r, 1).toString());
                jTextField4.setText(jTable1.getValueAt(r, 2).toString());
                jTextField5.setText(jTable1.getValueAt(r, 3).toString());
                jPasswordField1.setText(jTable1.getValueAt(r, 4).toString());
                jTextField7.setText(jTable1.getValueAt(r, 5).toString());

                String currentStatus = jTable1.getValueAt(r, 8).toString();

                if (currentStatus.equals("In-Active")) {
                    jButton4.setText("Activate");
                } else {
                    jButton4.setText("Deactivate");
                }

                jButton4.setEnabled(true);
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);

            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int r = jTable1.getSelectedRow();

        String user_id = jTable1.getValueAt(r, 0).toString();
        String f_name = jTextField3.getText();
        String l_name = jTextField4.getText();
        String username = jTextField5.getText();
        String password = jPasswordField1.getText();
        String mobile = jTextField7.getText();
        String type = jComboBox1.getSelectedItem().toString();
        String city = jComboBox2.getSelectedItem().toString();

        try {

            ResultSet city_details = MySql.search("SELECT * FROM `city` WHERE `name`='" + city + "'");
            city_details.next();

            ResultSet user_type_details = MySql.search("SELECT * FROM `user_type` WHERE `name`='" + type + "'");
            user_type_details.next();

            String city_id = city_details.getString("id");
            String type_id = user_type_details.getString("id");

            MySql.iud("UPDATE `user` SET `f_name`='" + f_name + "',`l_name`='" + l_name + "',`username`='" + username + "',`password`='" + password + "',`contact_number`='" + mobile + "',`user_type_id`='" + type_id + "',`city_id`='" + city_id + "' WHERE `id`='" + user_id + "'");

            resetFields();
            loadUsers();

            JOptionPane.showMessageDialog(this, "Successfully Updated", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int r = jTable1.getSelectedRow();
        String user_id = jTable1.getValueAt(r, 0).toString();

        int option = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            try {

                MySql.iud("DELETE FROM `user` WHERE `id`='" + user_id + "'");
                resetFields();
                loadUsers();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased

        String text = jTextField2.getText();
        loadUsers(text);

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained

        if (jTextField2.getText().equals("Search Users")) {
            jTextField2.setText("");
            jTextField2.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost

        if (jTextField2.getText().equals("")) {
            jTextField2.setText("Search Users");
            jTextField2.setForeground(new Color(153, 153, 153));
        }

    }//GEN-LAST:event_jTextField2FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
