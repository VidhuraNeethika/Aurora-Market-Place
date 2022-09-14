/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import com.jiat.aurora_market_place.MySql;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author DELL
 */
public class Select_damages extends javax.swing.JDialog {

    Damages dm;
    DecimalFormat df = new DecimalFormat("0.00");

    public Select_damages(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tableDesign();
        loadStock();
    }

    public Select_damages(Damages parent, boolean modal) {
        initComponents();
        this.dm = parent;
        tableDesign();
        loadStock();
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

    public void loadStock() {
        try {
            ResultSet rs = MySql.search("SELECT DISTINCT `stock`.`id`,`product`.`id`,`category`.`name`,`brand`.`name`,`product`.`name`,`stock`.`quantity`,`quantity_type`.`name`,`grn_item`.`buying_price`,`stock`.`selling_price`,`stock`.`mfd`, `stock`.`exd`,`grn`.`supplier_id`,`supplier`.`f_name`,`supplier`.`l_name`,`company_branch`.`name` FROM `stock` INNER JOIN `grn_item` ON `grn_item`.`stock_id` = `stock`.`id` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id` INNER JOIN category ON `product`.`category_id` = `category`.id INNER JOIN `grn` ON `grn_item`.`grn_id`=`grn`.`id` INNER JOIN `supplier` ON `grn`.`supplier_id` =`supplier`.`id` INNER JOIN `company_branch` ON `supplier`.`company_branch_id`=`company_branch`.`id` INNER JOIN `quantity_type` ON `stock`.`quantity_type_id`=`quantity_type`.`id` ORDER BY `product`.`name` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("grn.supplier_id"));
                v.add(rs.getString("supplier.f_name") + " " + rs.getString("supplier.l_name"));
                v.add(rs.getString("company_branch.name"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("category.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("stock.quantity") + " " + rs.getString("quantity_type.name"));
                v.add(df.format(Double.parseDouble(rs.getString("grn_item.buying_price"))));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("stock.exd"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStock(String text) {
        try {
            ResultSet rs = MySql.search("SELECT DISTINCT `stock`.`id`,`product`.`id`,`category`.`name`,`brand`.`name`,`product`.`name`,`stock`.`quantity`,`quantity_type`.`name`,`grn_item`.`buying_price`,`stock`.`selling_price`,`stock`.`mfd`, `stock`.`exd`,`grn`.`supplier_id`,`supplier`.`f_name`,`supplier`.`l_name`,`company_branch`.`name` FROM `stock` INNER JOIN `grn_item` ON `grn_item`.`stock_id` = `stock`.`id` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id` INNER JOIN category ON `product`.`category_id` = `category`.id INNER JOIN `grn` ON `grn_item`.`grn_id`=`grn`.`id` INNER JOIN `supplier` ON `grn`.`supplier_id` =`supplier`.`id` INNER JOIN `company_branch` ON `supplier`.`company_branch_id`=`company_branch`.`id` INNER JOIN `quantity_type` ON `stock`.`quantity_type_id`=`quantity_type`.`id` WHERE `product`.`name` LIKE '" + text + "%' ORDER BY `product`.`name` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("grn.supplier_id"));
                v.add(rs.getString("supplier.f_name") + " " + rs.getString("supplier.l_name"));
                v.add(rs.getString("company_branch.name"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("category.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("stock.quantity") + " " + rs.getString("quantity_type.name"));
                v.add(df.format(Double.parseDouble(rs.getString("grn_item.buying_price"))));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("stock.exd"));
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
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Damages");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-stock.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Select Stock");

        kGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkBorderRadius(20);
        kGradientPanel3.setkEndColor(new java.awt.Color(242, 242, 242));
        kGradientPanel3.setkStartColor(new java.awt.Color(242, 242, 242));

        jLabel7.setBackground(new Color(0,0,0,0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-search.png"))); // NOI18N

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Search Stock");
        jTextField1.setBorder(null);
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
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
                .addComponent(jTextField1)
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock Id", "Supplier Id", "Supplier Name", "Company Branch", "Product Id", "Category", "Brand", "Product Name", "Quatity", "Buying Price", "MFD", "EXD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(201, 233, 191));
        jTable1.setRowHeight(23);
        jTable1.setSelectionBackground(new java.awt.Color(216, 243, 207));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained

        if (jTextField1.getText().equals("Search Stock")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost

        if (jTextField1.getText().equals("")) {
            jTextField1.setText("Search Stock");
            jTextField1.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        String text = jTextField1.getText();
        loadStock(text);

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select damages", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                dm.jTextField2.setText(jTable1.getValueAt(r, 0).toString());
                dm.jTextField4.setText(jTable1.getValueAt(r, 4).toString());
                dm.jTextField5.setText(jTable1.getValueAt(r, 7).toString());
                dm.jTextField6.setText(jTable1.getValueAt(r, 6).toString());
                dm.jTextField7.setText(jTable1.getValueAt(r, 5).toString());
                dm.jTextField8.setText(jTable1.getValueAt(r, 10).toString());
                dm.jTextField10.setText(df.format(Double.parseDouble(jTable1.getValueAt(r, 9).toString())));
                dm.jTextField9.setText(jTable1.getValueAt(r, 11).toString());
                dm.jTextField17.setText(jTable1.getValueAt(r, 8).toString());
                dm.jTextField3.setText(jTable1.getValueAt(r, 1).toString());
                dm.jTextField11.setText(jTable1.getValueAt(r, 2).toString());
                dm.jTextField12.setText(jTable1.getValueAt(r, 3).toString());

                // QUANTITY DATA
                String full_quantity = jTable1.getValueAt(r, 8).toString();
                String[] quantity_array = full_quantity.split(" ");

                int quantity = Integer.parseInt(quantity_array[0]);
                String quantity_type = quantity_array[1];
                // QUANTITY DATA

                dm.jTextField13.setText(quantity_type);

                this.dispose();

            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Select_damages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Select_damages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Select_damages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Select_damages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Select_damages dialog = new Select_damages(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
