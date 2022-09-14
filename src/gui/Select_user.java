/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import com.jiat.aurora_market_place.MySql;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author DELL
 */
public class Select_user extends javax.swing.JDialog {

    Salary_management sm;

    public Select_user(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadUsers();
        tableDesign();
    }

    public Select_user(Salary_management parent, boolean modal) {
        initComponents();
        loadUsers();
        tableDesign();
        this.sm = parent;
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

                Date today = new Date();
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
                String string_date = date_format.format(today);

                String[] date_array = string_date.split("-");
                String year = date_array[0];
                String month = date_array[1];
                String day = date_array[2];

                int last_month = Integer.parseInt(month) - 1;

                ResultSet paid_details = MySql.search("SELECT * FROM `salary` WHERE `year`='" + year + "' AND `month_id`='" + last_month + "' AND `user_id`='" + rs.getString("id") + "'");

                if (paid_details.next()) {
                    v.add("Paid");
                }else{
                     v.add("Not Paid");
                }

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
                
                Date today = new Date();
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
                String string_date = date_format.format(today);

                String[] date_array = string_date.split("-");
                String year = date_array[0];
                String month = date_array[1];
                String day = date_array[2];

                int last_month = Integer.parseInt(month) - 1;

                ResultSet paid_details = MySql.search("SELECT * FROM `salary` WHERE `year`='" + year + "' AND `month_id`='" + last_month + "' AND `user_id`='" + rs.getString("id") + "'");

                if (paid_details.next()) {
                    v.add("Paid");
                }else{
                     v.add("Not Paid");
                }
                
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "First Name", "Last Name", "Username", "Password", "Contact", "User Type", "City", "Status", "Salary"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-search-gren.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel3.setText("Search Users");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
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
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select user", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                try {

                    String user_id = jTable1.getValueAt(r, 0).toString();

                    Date today = new Date();
                    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
                    String string_date = date_format.format(today);

                    String[] date_array = string_date.split("-");
                    String year = date_array[0];
                    String month = date_array[1];
                    String day = date_array[2];

                    int last_month = Integer.parseInt(month) - 1;

                    ResultSet paid_details = MySql.search("SELECT * FROM `salary` WHERE `year`='" + year + "' AND `month_id`='" + last_month + "' AND `user_id`='" + user_id + "'");

                    if (paid_details.next()) {

                        JOptionPane.showMessageDialog(this, "Already paid", "Warning", JOptionPane.WARNING_MESSAGE);

                    } else {

                        sm.jTextField6.setText(jTable1.getValueAt(r, 0).toString());
                        sm.jTextField7.setText(jTable1.getValueAt(r, 6).toString());
                        sm.jTextField3.setText(jTable1.getValueAt(r, 1).toString() + " " + jTable1.getValueAt(r, 2).toString());

                        ResultSet days_work = MySql.search("SELECT * FROM `days_work` WHERE `user_id`='" + user_id + "' AND `year`='" + year + "' AND `month_id`='" + last_month + "'");

                        if (days_work.next()) {

                            ResultSet get_days = MySql.search("SELECT COUNT(user_id) AS 'days_work' FROM `days_work` WHERE `user_id`='" + user_id + "' AND `year`='" + year + "' AND `month_id`='" + last_month + "'");
                            get_days.next();
                            sm.jTextField4.setText(get_days.getString("days_work") + " Days");

                        } else {
                            sm.jTextField4.setText("0 Days");
                        }

                        this.dispose();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

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

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased

        String text = jTextField2.getText();
        loadUsers(text);
    }//GEN-LAST:event_jTextField2KeyReleased

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
            java.util.logging.Logger.getLogger(Select_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Select_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Select_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Select_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Select_user dialog = new Select_user(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
