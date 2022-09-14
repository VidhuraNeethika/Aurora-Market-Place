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
public class Damages extends javax.swing.JPanel {

    DecimalFormat df = new DecimalFormat("0.00");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Damages() {
        initComponents();

        tableDesign();
        setScrolls();
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

    public void updateTotal() {
        double total = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String t = jTable1.getValueAt(i, 9).toString();
            total = total + Double.parseDouble(t);
        }

        jTextField15.setText(df.format(total));
    }

    public void resetFields() {
        jTextField2.setText("None"); // SID
        jTextField4.setText("None"); // PID
        jTextField5.setText("None"); // NAME
        jTextField6.setText("None"); // BRAND
        jTextField7.setText("None"); // CATEGORY
        jTextField8.setText("None"); // MFD
        jTextField1.setText(""); // QUANTITY
        jTextField10.setText("None"); // BUYING PRICE
        jTextField9.setText("None"); // EXD
        jTextField17.setText("None"); // QTY

        jTextField13.setText("None");
    }

    public void setScrolls() {
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
    }

    public void viewDamageReport(String damage_id, String date) {

        try {

            String supplier_id = jTextField3.getText();
            ResultSet supplier_details = MySql.search("SELECT * FROM `supplier` WHERE `id`='" + supplier_id + "'");
            supplier_details.next();

            String company_branch_id = supplier_details.getString("company_branch_id");

            ResultSet company_details = MySql.search("SELECT * FROM `company_branch` WHERE `id`='" + company_branch_id + "'");
            company_details.next();

            String company_branch_address_id = company_details.getString("company_branch_address_id");

            ResultSet company_branch_address_details = MySql.search("SELECT * FROM `company_branch_address` WHERE `id`='" + company_branch_address_id + "'");
            company_branch_address_details.next();

            String city_id = company_branch_address_details.getString("city_id");

            ResultSet city_details = MySql.search("SELECT * FROM `city` WHERE `id`='" + city_id + "'");
            city_details.next();

            //REPORT DETAILS
            String supplier_name = supplier_details.getString("f_name") + " " + supplier_details.getString("l_name");
            String supplier_contact_number = supplier_details.getString("contact_number");
            String supplier_email = supplier_details.getString("email");
            String branch_name = company_details.getString("name");
            String branch_contact_number = company_details.getString("branch_contact_number");
            String branch_address = company_branch_address_details.getString("line1") + "," + company_branch_address_details.getString("line2") + "," + city_details.getString("name");
            String grand_total = jTextField15.getText();
            //REPORT DETAILS

            //String filePath = "src//reports//aurora_market_place_returns.jrxml";
            InputStream filePath = getClass().getResourceAsStream("/reports/aurora_market_place_returns.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(filePath);

            HashMap parameters = new HashMap();
            parameters.put("supplier", supplier_name);
            parameters.put("supplier_contact_number", supplier_contact_number);
            parameters.put("supplier_email", supplier_email);
            parameters.put("branch_name", branch_name);
            parameters.put("branch_contact_number", branch_contact_number);
            parameters.put("branch_address", branch_address);
            parameters.put("grand_total", grand_total);

            TableModel tm = jTable1.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            ex.printStackTrace();
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
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jTextField15 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField13 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setBackground(new java.awt.Color(219, 236, 214));
        jButton5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton5.setForeground(new java.awt.Color(116, 171, 99));
        jButton5.setText("Add");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Quantity");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock Id", "Category", "Product Id", "Brand", "Name", "Quantity", "Buying Price", "MFD", "EXD", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(201, 233, 191));
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel21.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setText("Total Price");

        jButton4.setBackground(new java.awt.Color(238, 238, 238));
        jButton4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(102, 102, 102));
        jButton4.setText("Add to Damages");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel10.setText("Add");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-plus.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(201, 57, 57));
        jTextField15.setText("0.00");

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Supplier ID");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        jTextField3.setText("None");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("Supplier Name");

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(153, 153, 153));
        jTextField11.setText("None");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("Company Branch");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(153, 153, 153));
        jTextField12.setText("None");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(153, 153, 153));
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setText("None");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel21)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);

        jButton2.setBackground(new java.awt.Color(255, 229, 212));
        jButton2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(205, 87, 47));
        jButton2.setText("Select Product");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Stock ID");

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Prouct ID");

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("Name ");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("Brand ");

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Category ");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("MFD");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setText("EXD");

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("Buying Price");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("None");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        jTextField4.setText("None");

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

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("Quantity");

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(153, 153, 153));
        jTextField17.setText("None");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField17)
                    .addComponent(jTextField10)
                    .addComponent(jTextField9)
                    .addComponent(jTextField8)
                    .addComponent(jTextField7)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5)
                    .addComponent(jTextField4)
                    .addComponent(jTextField2)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addGap(4, 4, 4)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-damage.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Goods Return Notes");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Select_damages sd = new Select_damages(this, true);
        sd.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

        String qty = jTextField1.getText();
        String text = qty + evt.getKeyChar();

        if (!Pattern.compile("[1-9][0-9]*").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String sid = jTextField2.getText();
        String pid = jTextField4.getText();
        String name = jTextField5.getText();
        String brand = jTextField6.getText();
        String category = jTextField7.getText();
        String mfd = jTextField8.getText();
        String qty = jTextField1.getText();
        String bp = jTextField10.getText();
        String exd = jTextField9.getText();

        if (sid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please select damages", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("[1-9][0-9]*").matcher(qty).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                ResultSet rs = MySql.search("SELECT * FROM `stock` WHERE `stock`.`id`='" + sid + "'");
                rs.next();

                String availableQty = rs.getString("quantity");

                if (Integer.parseInt(availableQty) < Integer.parseInt(qty)) {
                    JOptionPane.showMessageDialog(this, "Quantity out of stock", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

                    boolean isFound = false;
                    int x = -1;

                    for (int i = 0; i < dtm.getRowCount(); i++) {
                        String s = jTable1.getValueAt(i, 2).toString();

                        if (s.equals(pid)) {
                            isFound = true;
                            x = i;
                            break;
                        }

                    }

                    if (isFound) {

                        int option = JOptionPane.showConfirmDialog(this, "This product already added.Do you want to update?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if (option == JOptionPane.YES_OPTION) {

                            ///////////
                            String oldQtyS = jTable1.getValueAt(x, 5).toString();
                            String[] qt_array = oldQtyS.split(" ");
                            String qt = qt_array[0];
                            String qt_t = qt_array[1];

                            int oldQty = Integer.parseInt(qt);

                            int finalQty = oldQty + Integer.parseInt(qty);

                            //Check stock
                            if (Integer.parseInt(availableQty) < finalQty) {
                                JOptionPane.showMessageDialog(this, "Quantity out of stock", "Warning", JOptionPane.WARNING_MESSAGE);
                            } else {
                                jTable1.setValueAt(String.valueOf(finalQty)+ " " + qt_t, x, 5);

                                double updatedItemTotal = finalQty * Double.parseDouble(bp);
                                jTable1.setValueAt(df.format(updatedItemTotal), x, 9);
                                ////////

                                updateTotal();
                            }
                            //Check stock

                        }

                        resetFields();

                    } else {

                        Vector v = new Vector();
                        v.add(sid);
                        v.add(category);
                        v.add(pid);
                        v.add(brand);
                        v.add(name);
                        v.add(qty + " " + jTextField13.getText());
                        v.add(bp);
                        v.add(mfd);
                        v.add(exd);

                        if (jTextField13.getText().equals("Units")) {
                            double itemTotal = Integer.parseInt(qty) * Double.parseDouble(bp);
                            v.add(df.format(itemTotal));
                        } else {
                            v.add(bp);
                        }

                        dtm.addRow(v);

                        updateTotal();

                        resetFields();

                        JOptionPane.showMessageDialog(this, "Product added to the damages", "Success", JOptionPane.INFORMATION_MESSAGE);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a invoice item", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                int option = JOptionPane.showConfirmDialog(this, "Do you wan't to remove?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.removeRow(r);

                    updateTotal();

                    //payments
                    jTextField15.setText("0.00");
                    //payments

                    JOptionPane.showMessageDialog(this, "Damage item removed", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add products", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            //Damages insert
            Random rnd = new Random();
            int number = rnd.nextInt(999999);

            String damage_id = "DMG-" + String.format("%06d", number);

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dNow = sdf2.format(new Date());

            viewDamageReport(damage_id, dNow);

            MySql.iud("INSERT INTO `damages`(`id`,`user_id`,`date_time`) VALUES ('" + damage_id + "','" + Sign_In.userId + "','" + dNow + "')");
            //Damage insert

            try {

                //Damage payment insert
                ResultSet rs = MySql.search("SELECT * FROM `damages` WHERE `id`='" + damage_id + "'");
                rs.next();
                String id = rs.getString("id");

                String balance = jTextField15.getText();
                String supplier_id = jTextField3.getText();

                MySql.iud("INSERT INTO `damages_payment` (`supplier_id`,`damages_id`,`balance`) VALUES ('" + supplier_id + "','" + id + "','" + balance + "')");
                //Damage payment insert

                //Damage Item INSERT and Stock UPDATE
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    String sid = jTable1.getValueAt(i, 0).toString();

                    // QUANTITY DATA
                    String full_quantity = jTable1.getValueAt(i, 5).toString();
                    String[] quantity_array = full_quantity.split(" ");

                    int quantity = Integer.parseInt(quantity_array[0]);
                    String quantity_type = quantity_array[1];
                    ResultSet quantity_details = MySql.search("SELECT * FROM `quantity_type` WHERE `name`='" + quantity_type + "'");
                    quantity_details.next();
                    String qty_type_id = quantity_details.getString("id");
                    // QUANTITY DATA

                    ResultSet rs3 = MySql.search("SELECT * FROM `stock` WHERE `stock`.`id`='" + sid + "'");
                    rs3.next();

                    String availableQty = rs3.getString("quantity");

                    int updatedQty = Integer.parseInt(availableQty) - quantity;

                    MySql.iud("UPDATE `stock` SET `quantity`='" + updatedQty + "' WHERE `id`='" + sid + "'");

                    Random rnd2 = new Random();
                    int number2 = rnd2.nextInt(999999);

                    String damage_items_id = "DMG-ITM-" + String.format("%06d", number2);

                    MySql.iud("INSERT INTO `damage_items`(`id`,`stock_id`,`qty`,`quantity_type_id`,`damages_id`) VALUES('" + damage_items_id + "','" + sid + "','" + quantity + "','" + qty_type_id + "','" + id + "')");

                }

                jTextField3.setText("None"); //SUPPLIER ID
                jTextField11.setText("None"); //SUPPLIER NAME
                jTextField12.setText("None"); //COMPANY BRANCH
                resetFields();

                //payments
                jTextField15.setText("0.00");

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
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
    public javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    public javax.swing.JTextField jTextField17;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    public javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
