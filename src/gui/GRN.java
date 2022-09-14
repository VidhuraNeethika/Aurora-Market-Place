/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.jiat.aurora_market_place.MySql;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import scrollbar.ScrollBarCustom;

/**
 *
 * @author DELL
 */
public class GRN extends javax.swing.JPanel {

    DecimalFormat df = new DecimalFormat("0.00");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public GRN() {
        initComponents();

        tableDesign();
        loadPaymentMethods();
        setScrolls();
        loadQTYtypes();
    }

    public void tableDesign() {
        JTableHeader table_header = jTable1.getTableHeader();
        jTable1.setBackground(Color.WHITE);

        Color table_header_color = new Color(121, 203, 96);
        Color table_header_font_color = new Color(91, 97, 135);

        table_header.setBackground(table_header_color);
        table_header.setForeground(Color.WHITE);

        table_header.setFont(new Font("Yu Gothic Ui", Font.BOLD, 14));
    }

    public void loadPaymentMethods() {

        try {
            ResultSet rs = MySql.search("SELECT * FROM `payment_type`");

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

    public void updateTotal() {
        try {

            double total = 0;
            double damage = 0;
            double pre_balance = 0;

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String t = jTable1.getValueAt(i, 9).toString();
                total = total + Double.parseDouble(t);
            }

            String supplier_id = jTextField5.getText();

            ResultSet supplier_availability = MySql.search("SELECT * FROM `damages_payment` WHERE `supplier_id`='" + supplier_id + "'");

            if (supplier_availability.next()) {

                ResultSet damage_details = MySql.search("SELECT SUM(`balance`) AS `damages_balance` FROM `damages_payment` WHERE `supplier_id`='" + supplier_id + "'");
                damage_details.next();
                damage = Double.parseDouble(damage_details.getString("damages_balance"));

            }

            ResultSet supplier_grn_details = MySql.search("SELECT * FROM `grn` WHERE `grn`.`supplier_id` ='" + supplier_id + "'");

            if (supplier_grn_details.next()) {

                ResultSet pre_balance_details = MySql.search("SELECT SUM(`grn_payment`.`balance`) AS `pre_balance` FROM `grn_payment` INNER JOIN `grn` ON `grn_payment`.`grn_id` = `grn`.`id` WHERE `grn`.`supplier_id` ='" + supplier_id + "'");
                pre_balance_details.next();
                pre_balance = Double.parseDouble(pre_balance_details.getString("pre_balance"));
            }

            jTextField15.setText(df.format(damage));
            jTextField16.setText(df.format(pre_balance));

            Double total_payment = total - pre_balance - damage;

            jTextField13.setText(df.format(total_payment)); //TOTAL

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetFields() {
        jTextField9.setText("None");
        jTextField10.setText("None");
        jTextField11.setText("None");
        jTextField12.setText("None");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField1.grabFocus();
        jComboBox2.setSelectedIndex(0);
    }

    public void viewGRN() {

        try {

            String supplier_id = jTextField5.getText();
            String branch = jTextField8.getText();

            ResultSet supplier_details = MySql.search("SELECT * FROM `supplier` WHERE `id` = '" + supplier_id + "'");
            supplier_details.next();

            ResultSet branch_details = MySql.search("SELECT * FROM `company_branch` WHERE `name` = '" + branch + "'");
            branch_details.next();

            String branch_address_id = branch_details.getString("company_branch_address_id");

            ResultSet branch_address_details = MySql.search("SELECT * FROM `company_branch_address` WHERE `id` = '" + branch_address_id + "'");
            branch_address_details.next();

            String branch_city = branch_address_details.getString("city_id");

            ResultSet city_details = MySql.search("SELECT * FROM `city` WHERE `id` = '" + branch_city + "'");
            city_details.next();

            // REPORT DETAILS
            String supplier = jTextField5.getText();
            String supplier_contact_number = jTextField7.getText();
            String supplier_email = supplier_details.getString("email");

            String branch_name = branch_details.getString("name");
            String branch_contact_number = branch_details.getString("branch_contact_number");
            String branch_address = branch_address_details.getString("line1") + "," + branch_address_details.getString("line2") + "," + city_details.getString("name");

            String payment = (df.format(Integer.parseInt(jTextField3.getText())));
            String balance = jTextField14.getText();
            String grand_total = jTextField13.getText();
            String payment_type = jComboBox1.getSelectedItem().toString();
            String damages = jTextField15.getText();
            String pre_balance = jTextField16.getText();
            // REPORT DETAILS

            //String filePath = "src//reports//aurora_market_place_GRN.jrxml";
            InputStream filePath = getClass().getResourceAsStream("/reports/aurora_market_place_GRN.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(filePath);

            HashMap parameters = new HashMap();

            parameters.put("supplier", supplier);
            parameters.put("supplier_contact_number", supplier_contact_number);
            parameters.put("supplier_email", supplier_email);

            parameters.put("branch_name", branch_name);
            parameters.put("branch_contact_number", branch_contact_number);
            parameters.put("branch_address", branch_address);

            parameters.put("payment", payment);
            parameters.put("balance", balance);
            parameters.put("grand_total", grand_total);
            parameters.put("payment_type", payment_type);
            parameters.put("damages", damages);
            parameters.put("pre_balance", pre_balance);

//             JREmptyDataSource dataSource = new JREmptyDataSource();
            TableModel tm = jTable1.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void setScrolls() {
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
    }

    public void loadQTYtypes() {
        try {

            ResultSet city_details = MySql.search("SELECT * FROM `quantity_type`");

            Vector v = new Vector();

            while (city_details.next()) {
                v.add(city_details.getString("name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);

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

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Product Id", "Brand", "Name", "Quantity", "Buying Price", "Selling Price", "MFD", "EXD", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel22.setText("Quantity");

        jTextField1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel23.setText("Buying Price");

        jTextField2.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel27.setText("Selling Price");

        jTextField4.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel28.setText("MFD");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel29.setText("EXD");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jButton3.setBackground(new java.awt.Color(234, 249, 230));
        jButton3.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(106, 163, 89));
        jButton3.setText("Add to GRN");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel24.setText("Add");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-plus.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setText("Total Payment");

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel30.setText("Payment Method");

        jComboBox1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel31.setText("Payment");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("Balance");

        jButton4.setBackground(new java.awt.Color(229, 229, 229));
        jButton4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 51, 51));
        jButton4.setText("Print GRN");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(232, 79, 79));
        jTextField13.setText("0.00");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(153, 153, 153));
        jTextField14.setText("0.00");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(153, 153, 153));
        jLabel26.setText("PR-DMG");

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(226, 123, 54));
        jTextField15.setText("0.00");

        jComboBox2.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setText("PR-B");

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(204, 102, 0));
        jTextField16.setText("0.00");

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkBorderRadius(25);
        kGradientPanel1.setkEndColor(new java.awt.Color(242, 242, 242));
        kGradientPanel1.setkStartColor(new java.awt.Color(242, 242, 242));

        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-eye-outline.png"))); // NOI18N
        jButton36.setBorderPainted(false);
        jButton36.setContentAreaFilled(false);

        jButton37.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        jButton37.setForeground(new java.awt.Color(79, 79, 79));
        jButton37.setText("View Previous GRNs");
        jButton37.setBorderPainted(false);
        jButton37.setContentAreaFilled(false);
        jButton37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton37.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton37)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField13)))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField14)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jTextField1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(29, 29, 29)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)))
                .addGap(9, 9, 9))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);

        jButton1.setBackground(new java.awt.Color(218, 226, 252));
        jButton1.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 153));
        jButton1.setText("Select Supplier");
        jButton1.setBorderPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Id");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Name");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Contact Number");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Branch");

        jButton2.setBackground(new java.awt.Color(255, 232, 198));
        jButton2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 102, 0));
        jButton2.setText("Select Product");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Id  ");

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Name ");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("Brand ");

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("Category ");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(153, 153, 153));
        jTextField5.setText("None");

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(153, 153, 153));
        jTextField6.setText("None");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(153, 153, 153));
        jTextField7.setText("None");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(153, 153, 153));
        jTextField8.setText("None");

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(153, 153, 153));
        jTextField9.setText("None");

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(153, 153, 153));
        jTextField10.setText("None");

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(153, 153, 153));
        jTextField11.setText("None");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(153, 153, 153));
        jTextField12.setText("None");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField5)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField10)
                    .addComponent(jTextField11)
                    .addComponent(jTextField12))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-grn.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Goods Receive Notes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a grn item", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                int option = JOptionPane.showConfirmDialog(this, "Do you wan't to remove?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(r);

                    updateTotal();

                    //PAYMENT
                    jTextField13.setText("0.00");
                    jTextField3.setText("");
                    jTextField3.setEditable(false);
                    jTextField14.setText("0.00");
                    jComboBox1.setSelectedIndex(0);
                    jTextField15.setText("0.00");
                    jTextField16.setText("0.00");
                    //PAYMENT

                    JOptionPane.showMessageDialog(this, "GRN item removed", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        if (evt.getClickCount() == 2) {
            jButton1.setEnabled(true);
            jButton1.setText("Select Supplier");

            jTextField5.setText("None");
            jTextField6.setText("None");
            jLabel7.setText("None");
            jLabel9.setText("None");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Select_supplier sp = new Select_supplier(this, true);
        sp.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Select_product sp = new Select_product(this, true);
        sp.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

        String qty = jTextField1.getText();
        String text = qty + evt.getKeyChar();

        if (!Pattern.compile("[1-9][0-9]*").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

        String price = jTextField2.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped

        String price = jTextField4.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String sid = jTextField3.getText();
        String pid = jTextField9.getText();
        String qty = jTextField1.getText();
        String buyingPrice = jTextField2.getText();

        //update
        String selling_price = jTextField4.getText();
        Date mfd = jDateChooser1.getDate();
        Date exd = jDateChooser2.getDate();
        //update

        if (sid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select supplier", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (pid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select product", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("[1-9][0-9]*").matcher(qty).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*)))").matcher(buyingPrice).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid buying price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*)))").matcher(selling_price).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid selling price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Double.parseDouble(buyingPrice) >= Double.parseDouble(selling_price)) {
            JOptionPane.showMessageDialog(this, "Invalid selling price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mfd == null) {
            JOptionPane.showMessageDialog(this, "Invalid MFD", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mfd.after(new Date())) {
            JOptionPane.showMessageDialog(this, "Invalid MFD", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (exd == null) {
            JOptionPane.showMessageDialog(this, "Invalid EXD", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (exd.before(new Date())) {
            JOptionPane.showMessageDialog(this, "Invalid EXD", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            boolean isFound = false;
            int x = -1;

            for (int i = 0; i < dtm.getRowCount(); i++) {
                String id = jTable1.getValueAt(i, 1).toString();

                if (id.equals(pid)) {
                    isFound = true;
                    x = i;
                    break;
                }

            }

            if (isFound) {

                int option = JOptionPane.showConfirmDialog(this, "This product already added.Do you want to update?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {

                    //////////
                    String oldQtyS = jTable1.getValueAt(x, 4).toString();

                    String[] qt_array = oldQtyS.split(" ");
                    String qt = qt_array[0];
                    String qt_t = qt_array[1];

                    int oldQty = Integer.parseInt(qt);

                    int finalQty = oldQty + Integer.parseInt(qty);

                    jTable1.setValueAt(String.valueOf(finalQty) + " " + qt_t, x, 4);
                    jTable1.setValueAt(df.format(Integer.parseInt(buyingPrice)), x, 5);

                    double updatedItemTotal = finalQty * Double.parseDouble(buyingPrice);
                    jTable1.setValueAt(df.format(updatedItemTotal), x, 9);
                    ///////////

                    updateTotal();
                    resetFields();

                }

            } else {

                Vector v = new Vector();
                v.add(jTextField12.getText());
                v.add(pid);
                v.add(jTextField11.getText());
                v.add(jTextField10.getText());
                v.add(qty + " " + jComboBox2.getSelectedItem());
                v.add(df.format(Integer.parseInt(buyingPrice)));

                v.add(df.format(Integer.parseInt(selling_price)));
                v.add(sdf.format(mfd));
                v.add(sdf.format(exd));

                if (jComboBox2.getSelectedItem().equals("Units")) {
                    double itemTotal = Integer.parseInt(qty) * Double.parseDouble(buyingPrice);
                    v.add(df.format(itemTotal));
                } else {
                    v.add(df.format(Integer.parseInt(buyingPrice)));
                }

                dtm.addRow(v);

                updateTotal();
                resetFields();

                JOptionPane.showMessageDialog(this, "Product added to the GRN", "Success", JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        String text = jComboBox1.getSelectedItem().toString();

        if (text.equals("Select")) {
            jTextField3.setEditable(false);
            jTextField3.setText("");
            jTextField14.setText("0.00");
            jTextField14.setForeground(Color.BLACK);
        } else {
            jTextField3.setEditable(true);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased

        if (jTextField3.getText().isEmpty()) {
            jTextField14.setText("0.00");
            jTextField14.setForeground(Color.BLACK);
        } else {
            String total = jTextField13.getText();
            String payment = jTextField3.getText();

            double balance = Double.parseDouble(payment) - Double.parseDouble(total);

            if (balance < 0) {
                jTextField14.setForeground(Color.RED);
            } else {
                jTextField14.setForeground(Color.GREEN);
            }

            jTextField14.setText(df.format(balance));

        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped

        String price = jTextField3.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String payment = jTextField3.getText();
        String payment_type = jComboBox1.getSelectedItem().toString();

        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add product", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (payment_type.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select payment method", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("(0)|([1-9][0-9]*|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*)))").matcher(payment).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid payment", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            viewGRN();

            //GRN INSERT
            Random rnd2 = new Random();
            int number2 = rnd2.nextInt(999999);

            String grn_un_id = "GRN-" + String.format("%06d", number2);

            String sid = jTextField5.getText();

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dNow = sdf2.format(new Date());

            MySql.iud("INSERT INTO `grn`(`id`,`supplier_id`,`date_time`,`user_id`) VALUES ('" + grn_un_id + "','" + sid + "','" + dNow + "','" + Sign_In.userId + "')");
            //GRN INSERT

            // DAMAGES PAYMENT UPDATE
            MySql.iud("UPDATE `damages_payment` SET `balance`='0',`grn_paid_date`='" + dNow + "',`grn_payment`='" + payment + "' WHERE `supplier_id`='" + sid + "'");
            // DAMAGES PAYMENT UPDATE

            try {

                // GRN PAYMENT BALANCE UPDATE
                ResultSet get_grn_details = MySql.search("SELECT * FROM `grn` WHERE `supplier_id`='" + sid + "'");
                while (get_grn_details.next()) {
                    String pre_grn_id = get_grn_details.getString("id");
                    MySql.iud("UPDATE `grn_payment` SET `balance`='0' WHERE `grn_id`='" + pre_grn_id + "'");
                }
                // GRN PAYMENT BALANCE UPDATE

                //GRN_PAYMENT_INSERT
                ResultSet rs = MySql.search("SELECT * FROM `grn` WHERE `id`='" + grn_un_id + "'");
                rs.next();
                String id = rs.getString("id");

                ResultSet rs2 = MySql.search("SELECT* FROM `payment_type` WHERE `name`='" + payment_type + "'");
                rs2.next();

                String payment_type_id = rs2.getString("id");
                String balance = jTextField14.getText();

                MySql.iud("INSERT INTO `grn_payment` (`grn_id`,`payment_type_id`,`payment`,`balance`) VALUES ('" + id + "','" + payment_type_id + "','" + payment + "','" + balance + "')");
                //GRN_PAYMENT_INSERT

                //GRN ITEM INSERT OR UPDATE
                for (int i = 0; i < jTable1.getRowCount(); i++) {

                    String pid = jTable1.getValueAt(i, 1).toString();

                    // QUANTITY DATA
                    String full_quantity = jTable1.getValueAt(i, 4).toString();
                    String[] quantity_array = full_quantity.split(" ");

                    int quantity = Integer.parseInt(quantity_array[0]);
                    String quantity_type = quantity_array[1];
                    ResultSet quantity_details = MySql.search("SELECT * FROM `quantity_type` WHERE `name`='" + quantity_type + "'");
                    quantity_details.next();
                    String qty_type_id = quantity_details.getString("id");
                    // QUANTITY DATA

                    String buying_price = jTable1.getValueAt(i, 5).toString();
                    String selling_price = jTable1.getValueAt(i, 6).toString();
                    String mfd = jTable1.getValueAt(i, 7).toString();
                    String exd = jTable1.getValueAt(i, 8).toString();
                    // grn_id = id;
                    // stock_id=?;

                    ResultSet rs3 = MySql.search("SELECT * FROM `stock` WHERE `product_id`='" + pid + "' AND `selling_price`='" + selling_price + "' AND `mfd`='" + mfd + "' AND `exd`='" + exd + "' ");

                    {
                        String stock_id;

                        if (rs3.next()) {
                            // UPDATE
                            stock_id = rs3.getString("id");
                            String stock_qty = rs3.getString("quantity");

                            int updated_qty = Integer.parseInt(stock_qty) + quantity;

                            MySql.iud("UPDATE `stock` SET `quantity`='" + updated_qty + "' WHERE `id`='" + stock_id + "'");

                        } else {
                            // INSERT

                            Random rnd = new Random();
                            int number = rnd.nextInt(999999);

                            String new_stock_id = "ST-" + String.format("%06d", number);

                            MySql.iud("INSERT INTO `stock` (`id`,`product_id`,`quantity`,`quantity_type_id`,`selling_price`,`mfd`,`exd`) VALUES ('" + new_stock_id + "','" + pid + "','" + quantity + "','" + qty_type_id + "','" + selling_price + "','" + mfd + "','" + exd + "')");

                            ResultSet rs4 = MySql.search("SELECT * FROM `stock` WHERE `product_id`='" + pid + "' AND `selling_price`='" + selling_price + "' AND `mfd`='" + mfd + "' AND `exd`='" + exd + "' ");
                            rs4.next();
                            stock_id = rs4.getString("id");
                        }

                        MySql.iud("INSERT INTO `grn_item` (`quantity`,quantity_type_id,`buying_price`,`grn_id`,`stock_id`) VALUES ('" + quantity + "','" + qty_type_id + "','" + buying_price + "','" + id + "','" + stock_id + "')");
                    }
                }

                resetFields();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                //SUPPLIER
                jTextField5.setText("None");
                jTextField6.setText("None");
                jTextField7.setText("None");
                jTextField8.setText("None");
                jButton1.setEnabled(true);
                jButton1.setText("Select Supplier");
                //SUPPLIER

                //PAYMENT
                jTextField13.setText("0.00");
                jTextField3.setText("");
                jTextField3.setEditable(false);
                jTextField14.setText("0.00");
                jComboBox1.setSelectedIndex(0);
                jTextField15.setText("0.00");
                jTextField16.setText("0.00");
                //PAYMENT

                //GRN ITEM INSERT OR UPDATE
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        View_pre_GRNs vpg = new View_pre_GRNs(this, true);
        vpg.setVisible(true);

    }//GEN-LAST:event_jButton37ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField10;
    public javax.swing.JTextField jTextField11;
    public javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    public javax.swing.JTextField jTextField9;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
