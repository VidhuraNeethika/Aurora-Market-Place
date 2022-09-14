/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.jiat.aurora_market_place.MySql;
import com.jiat.aurora_market_place.TableContentAlign;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
public class Salary_management extends javax.swing.JPanel {

    /**
     * Creates new form Manage_users
     */
    DecimalFormat df = new DecimalFormat("0.00");
    public TableCellRenderer centerAlignment = new TableContentAlign();

    public Salary_management() {
        initComponents();

        tableDesign();
        loadSalaryDetails();
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

    public void resetFields() {
        jTextField6.setText("None");
        jTextField7.setText("None");
        jTextField3.setText("None");
        jTextField4.setText("None");

        jTextField5.setText("");
        jTextField8.setText("");

        jTextField9.setText("0.00");
        jTextField10.setText("0.00");
        jTextField11.setText("0.00");
        jTextField12.setText("0.00");
    }

    public void loadSalaryDetails() {
        try {

            ResultSet salary_details = MySql.search("SELECT * FROM `salary` INNER JOIN `user` ON `salary`.`user_id`=`user`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` ORDER BY `salary`.`paid_date` DESC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (salary_details.next()) {
                Vector v = new Vector();
                v.add(salary_details.getString("user.id"));
                v.add(salary_details.getString("user.f_name") + " " + salary_details.getString("user.l_name"));
                v.add(salary_details.getString("salary.year"));
                v.add(salary_details.getString("month.name"));

                Date today = new Date();
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
                String string_date = date_format.format(today);

                String[] date_array = string_date.split("-");
                String year = date_array[0];
                String month = date_array[1];

                int last_month = Integer.parseInt(month) - 1;

                ResultSet get_days = MySql.search("SELECT COUNT(user_id) AS 'days_work' FROM `days_work` WHERE `user_id`='" + salary_details.getString("user.id") + "' AND `year`='" + year + "' AND `month_id`='" + last_month + "'");
                get_days.next();

                v.add(get_days.getString("days_work") + " Days");
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.rate_of_pay"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.allowance"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.pfd"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.epf"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.total_fund"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.etf"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.other_deductions"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.salary"))));
                v.add(salary_details.getString("salary.paid_date"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSalaryDetails(String text) {
        try {

            ResultSet salary_details = MySql.search("SELECT * FROM `salary` INNER JOIN `user` ON `salary`.`user_id`=`user`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` WHERE `user`.`f_name` LIKE '" + text + "%' ORDER BY `salary`.`paid_date` DESC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (salary_details.next()) {
                Vector v = new Vector();
                v.add(salary_details.getString("user.id"));
                v.add(salary_details.getString("user.f_name") + " " + salary_details.getString("user.l_name"));
                v.add(salary_details.getString("salary.year"));
                v.add(salary_details.getString("month.name"));

                Date today = new Date();
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
                String string_date = date_format.format(today);

                String[] date_array = string_date.split("-");
                String year = date_array[0];
                String month = date_array[1];

                int last_month = Integer.parseInt(month) - 1;

                ResultSet get_days = MySql.search("SELECT COUNT(user_id) AS 'days_work' FROM `days_work` WHERE `user_id`='" + salary_details.getString("user.id") + "' AND `year`='" + year + "' AND `month_id`='" + last_month + "'");
                get_days.next();

                v.add(get_days.getString("days_work") + " Days");
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.rate_of_pay"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.allowance"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.pfd"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.epf"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.total_fund"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.etf"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.other_deductions"))));
                v.add(df.format(Double.parseDouble(salary_details.getString("salary.salary"))));
                v.add(salary_details.getString("salary.paid_date"));
                dtm.addRow(v);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewReport() {

        try {

            Date today = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
            String string_date = date_format.format(today);

            String[] date_array = string_date.split("-");
            String year = date_array[0];
            String month = date_array[1];

            int last_month = Integer.parseInt(month) - 1;

            ResultSet month_details = MySql.search("SELECT * FROM `month` WHERE `id`='" + last_month + "'");
            month_details.next();

            //String filePath = "src//reports//aurora_market_place_salary.jrxml";
            InputStream filePath = getClass().getResourceAsStream("/reports/aurora_market_place_salary.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(filePath);

            HashMap parameters = new HashMap();
            parameters.put("employee_id", jTextField6.getText());
            parameters.put("employee_name", jTextField3.getText());
            parameters.put("year", year);
            parameters.put("month", month_details.getString("name"));
            parameters.put("days_work", jTextField4.getText());
            parameters.put("rate_of_pay", df.format(Double.parseDouble(jTextField5.getText())));
            parameters.put("allowance", df.format(Double.parseDouble(jTextField8.getText())));
            parameters.put("pfd", jTextField9.getText());
            parameters.put("epf", jTextField10.getText());
            parameters.put("fund_total", jTextField11.getText());
            parameters.put("total_salary", jTextField12.getText());
            parameters.put("total", jTextField14.getText());
            parameters.put("etf", jTextField13.getText());
            parameters.put("other_deductions", df.format(Double.parseDouble(jTextField15.getText())));

            JREmptyDataSource dataSource = new JREmptyDataSource();

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setScrolls() {
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jTextField14 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-coins.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Salary Management");

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-buld.png"))); // NOI18N

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(215, 56, 54));
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField12.setText("0.00");

        jLabel8.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel8.setText("Allowances");

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(153, 153, 153));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField10.setText("0.00");

        jTextField5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(153, 153, 153));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel2.setText("Employee Name");

        jButton5.setBackground(new java.awt.Color(222, 228, 246));
        jButton5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 102, 204));
        jButton5.setText("Select Employee");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setText("Employee Name.");

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(153, 153, 153));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel10.setText("EPF Employer (12%)");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("automatically fill Employee ID, Role, Employee Name and");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(153, 153, 153));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel4.setText("No. of Days Work");

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(153, 153, 153));
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField11.setText("0.00");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(153, 153, 153));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("None");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel11.setText("Provident Fund Total");

        jButton1.setBackground(new java.awt.Color(231, 240, 229));
        jButton1.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(112, 168, 97));
        jButton1.setText("Print Pay Sheet");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(153, 153, 153));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("None");

        jLabel18.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel18.setText("Net Salary");

        jLabel9.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel9.setText("Provident Fund Deducted (8%)");

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(153, 153, 153));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("None");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        jTextField3.setText("None");

        jLabel5.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel5.setText("Basic Salary");

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("First select employee using SELECT EMPLOYEE button. Then");

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel14.setText("ETF (3%)");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(153, 153, 153));
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField13.setText("0.00");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(153, 153, 153));
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel15.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel15.setText("Total");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel16.setText("Salary, No pay & Other Deductions");

        jTextField15.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(153, 153, 153));
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField15KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator6)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField15))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField12)))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField10)
                            .addComponent(jTextField11)
                            .addComponent(jSeparator3)
                            .addComponent(jTextField9)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel12)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField8)
                            .addComponent(jSeparator5)
                            .addComponent(jTextField14))))
                .addGap(108, 108, 108))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField5)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField8)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField9)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField10)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField11)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField13)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField15)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addGap(0, 0, 0)
                .addComponent(jLabel13)
                .addGap(0, 0, 0)
                .addComponent(jLabel19)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Employee Name", "Year", "Month", "No. of Days Work", "Basic Salary", "Allowances", "PFD (8%)", "EPF (12%)", "Fund Total", "ETF (3%)", "Other Deductions", "Net Salary", "Paid Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
        jTextField2.setText("Search Employee");
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
        jLabel3.setText("Search Employee");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-search-gren.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);

        jButton2.setBackground(new java.awt.Color(233, 233, 233));
        jButton2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Print All");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked


    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased

        String text = jTextField2.getText();
        loadSalaryDetails(text);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost

        if (jTextField2.getText().equals("")) {
            jTextField2.setText("Search Employee");
            jTextField2.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained

        if (jTextField2.getText().equals("Search Employee")) {
            jTextField2.setText("");
            jTextField2.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Select_user su = new Select_user(this, true);
        su.setVisible(true);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased

        if (jTextField5.getText().isEmpty()) {
            jTextField12.setText("0.00");
            jTextField8.setEditable(false);
        } else {

            jTextField8.setEditable(true);

            String rate_of_pay = jTextField5.getText();
            String allowance = jTextField8.getText();

            if (jTextField8.getText().isEmpty()) {
                // SALARY ONLY

                double total = Double.parseDouble(rate_of_pay);

                double fund_total = 8 * total / 100;

                double salary = total - fund_total;

                double epf = 12 * total / 100;

                double etf = 3 * total / 100;

                double provident_fund_total = fund_total + epf;

                jTextField14.setText(df.format(total));                 // TOTAL SALARY + ALLOWANCES
                jTextField9.setText(df.format(fund_total));             // FUND TOTAL
                jTextField12.setText(df.format(salary));                // NET SALARY
                jTextField10.setText(df.format(epf));                   // EPF
                jTextField13.setText(df.format(etf));                   // ETF
                jTextField11.setText(df.format(provident_fund_total));  // PFT

            } else {
                //SALARY + ALLOWANCE

                double total = Double.parseDouble(rate_of_pay) + Double.parseDouble(allowance);

                double fund_total = 8 * total / 100;

                double salary = Double.parseDouble(rate_of_pay) - fund_total;

                double epf = 12 * Double.parseDouble(rate_of_pay) + Double.parseDouble(allowance) / 100;

                double provident_fund_total = fund_total + epf;

                jTextField9.setText(df.format(fund_total));             // FUND TOTAL
                jTextField12.setText(df.format(salary));                 // TOTAL SALARY
                jTextField10.setText(df.format(epf));                   // EPF
                jTextField11.setText(df.format(provident_fund_total));  // PFT

            }

        }

    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased

        String rate_of_pay = jTextField5.getText();
        String allowance;

        if (jTextField8.getText().isEmpty()) {
            allowance = "0.00";
        } else {
            allowance = jTextField8.getText();
        }

        double t_salary = Double.parseDouble(rate_of_pay) + Double.parseDouble(allowance);

        double fund_total = 8 * t_salary / 100;

        double total = t_salary - fund_total;

        double epf = 12 * t_salary / 100;

        double provident_fund_total = fund_total + epf;

        jTextField14.setText(df.format(t_salary));              // TOTAL SALARY + ALLOWANCES
        jTextField9.setText(df.format(fund_total));             // FUND TOTAL
        jTextField12.setText(df.format(total));                 // TOTAL SALARY
        jTextField10.setText(df.format(epf));                   // EPF
        jTextField11.setText(df.format(provident_fund_total));  // PFT

    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped

        String price = jTextField5.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped

        String price = jTextField8.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField8KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Date today = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
        String string_date = date_format.format(today);

        Date todate = new Date();
        SimpleDateFormat date_format2 = new SimpleDateFormat("yyyy-MM-dd");

        String[] date_array = string_date.split("-");
        String year = date_array[0];
        String month = date_array[1];

        String user_id = jTextField6.getText();
        int last_month = Integer.parseInt(month) - 1;
        String rate_of_pay = jTextField5.getText();
        String allowances = jTextField8.getText();
        String pfd = jTextField9.getText();
        String epf = jTextField10.getText();
        String total_fund = jTextField11.getText();
        String salary = jTextField12.getText();
        String paid_date = date_format2.format(todate);
        String other_ded = jTextField15.getText();
        String etf = jTextField13.getText();

        viewReport();

        if (user_id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select employee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("(0)|([1-9][0-9]*|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*)))").matcher(rate_of_pay).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid payment", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String allowances_amount;
            String other_deductions;

            if (allowances.isEmpty()) {
                allowances_amount = "0.00";
            } else {
                allowances_amount = jTextField8.getText();
            }

            if (other_ded.isEmpty()) {
                other_deductions = "0.00";
            } else { 
                other_deductions = jTextField15.getText();
            }

            MySql.iud("INSERT INTO `salary` (`user_id`,`year`,`month_id`,`rate_of_pay`,`allowance`,`pfd`,`other_deductions`,`etf`,`epf`,`total_fund`,`salary`,`paid_date`) VALUES ('" + user_id + "','" + year + "','" + last_month + "','" + rate_of_pay + "','" + allowances_amount + "','" + pfd + "','" + other_deductions + "','" + etf + "','" + epf + "','" + total_fund + "','" + salary + "','" + paid_date + "')");

            resetFields();
            
            loadSalaryDetails();

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyReleased

        String rate_of_pay = jTextField5.getText();
        String allowance = jTextField8.getText();
        String other_deductions;

        if (jTextField15.getText().isEmpty()) {
            other_deductions = "0.00";
        } else {
            other_deductions = jTextField15.getText();
        }

        double t_salary = Double.parseDouble(rate_of_pay) + Double.parseDouble(allowance);

        double fund_total = 8 * t_salary / 100;

        double total = t_salary - fund_total - Double.parseDouble(other_deductions);

        double epf = 12 * t_salary / 100;

        double provident_fund_total = fund_total + epf;

        jTextField14.setText(df.format(t_salary));              // TOTAL SALARY + ALLOWANCES
        jTextField9.setText(df.format(fund_total));             // FUND TOTAL
        jTextField12.setText(df.format(total));                 // NET SALARY
        jTextField10.setText(df.format(epf));                   // EPF
        jTextField11.setText(df.format(provident_fund_total));  // PFT

    }//GEN-LAST:event_jTextField15KeyReleased

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        String price = jTextField15.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            //String filePath = "src//reports//aurora_market_place_stock.jrxml";
            InputStream filePath = getClass().getResourceAsStream("/reports/aurora_market_place_all_salary.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(filePath);

            HashMap parameters = new HashMap();

            TableModel tm = jTable1.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
