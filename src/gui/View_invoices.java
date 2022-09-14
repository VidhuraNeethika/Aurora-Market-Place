/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.jiat.aurora_market_place.MySql;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author DELL
 */
public class View_invoices extends javax.swing.JPanel {

    DecimalFormat df = new DecimalFormat("0.00");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public View_invoices() {
        initComponents();
        tableDesign();
        loadInvoices();
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

    public void loadInvoices() {

        try {

            ResultSet rs = MySql.search("SELECT * FROM `invoice_item` INNER JOIN `invoice` ON `invoice_item`.`invoice_id`=`invoice`.`id` INNER JOIN `stock` ON `invoice_item`.`stock_id`=`stock`.`id` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` INNER JOIN `customer` ON `invoice`.`customer_id`=`customer`.`id` INNER JOIN `user` ON `invoice`.`user_id`=`user`.`id` INNER JOIN `invoice_payment` ON `invoice`.`id`=`invoice_payment`.`invoice_id` INNER JOIN `quantity_type` ON `invoice_item`.`quantity_type_id`=`quantity_type`.`id` ORDER BY `invoice`.`date_time` DESC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("invoice_item.invoice_id"));
                v.add(rs.getString("invoice_item.stock_id"));
                v.add(rs.getString("user.f_name") + " " + rs.getString("user.l_name"));
                v.add(rs.getString("customer.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty") + " " + rs.getString("quantity_type.name"));
                v.add(df.format(Double.parseDouble(rs.getString("stock.selling_price"))));
                v.add(rs.getString("invoice.date_time"));
                v.add(df.format(Double.parseDouble(rs.getString("invoice_payment.payment"))));
                v.add(df.format(Double.parseDouble(rs.getString("invoice_payment.balance"))));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchInvoices() {

        if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {

            String starting_date = sdf.format(jDateChooser1.getDate());
            String ending_date = sdf.format(jDateChooser2.getDate());

            try {

                ResultSet rs = MySql.search("SELECT * FROM `invoice_item` INNER JOIN `invoice` ON `invoice_item`.`invoice_id`=`invoice`.`id` INNER JOIN `stock` ON `invoice_item`.`stock_id`=`stock`.`id` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` INNER JOIN `customer` ON `invoice`.`customer_id`=`customer`.`id` INNER JOIN `user` ON `invoice`.`user_id`=`user`.`id` INNER JOIN `invoice_payment` ON `invoice`.`id`=`invoice_payment`.`invoice_id` INNER JOIN `quantity_type` ON `invoice_item`.`quantity_type_id`=`quantity_type`.`id` WHERE `invoice`.`date_time`>='" + starting_date + "' AND `invoice`.`date_time`<='" + ending_date + "' ORDER BY `invoice`.`date_time` DESC");
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("invoice_item.invoice_id"));
                    v.add(rs.getString("invoice_item.stock_id"));
                    v.add(rs.getString("user.f_name") + " " + rs.getString("user.l_name"));
                    v.add(rs.getString("customer.name"));
                    v.add(rs.getString("product.name"));
                    v.add(rs.getString("invoice_item.qty") + " " + rs.getString("quantity_type.name"));
                    v.add(df.format(Double.parseDouble(rs.getString("stock.selling_price"))));
                    v.add(rs.getString("invoice.date_time"));
                    v.add(df.format(Double.parseDouble(rs.getString("invoice_payment.payment"))));
                    v.add(df.format(Double.parseDouble(rs.getString("invoice_payment.balance"))));
                    dtm.addRow(v);
                }

                jTable1.setModel(dtm);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void resetAll() {
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        loadInvoices();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-eye.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("View Previous Invoices");

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Stock ID", "Cashier Name", "Customer Name", "Product Name", "Quantity", "Selling Price", "Date", "Payment", "Balance"
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
        jTable1.setSelectionBackground(new java.awt.Color(216, 243, 207));
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel2.setText("Invoice Date");

        jDateChooser1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("To");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-reset.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addGap(518, 518, 518))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange

        searchInvoices();

    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        searchInvoices();
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        resetAll();
    }//GEN-LAST:event_jButton16ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
