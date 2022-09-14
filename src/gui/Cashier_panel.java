/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.IntelliJTheme;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import com.jiat.aurora_market_place.MySql;
import com.jiat.aurora_market_place.TableContentAlign;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.table.TableCellRenderer;
import scrollbar.ScrollBarCustom;

/**
 *
 * @author DELL
 */
public class Cashier_panel extends javax.swing.JFrame {

    /**
     * Creates new form Aurora_market_place
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("0.00");
    Date today = new Date();
    String date = sdf.format(today);

    public TableCellRenderer centerAlignment = new TableContentAlign();

    public Cashier_panel() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        time();
        closeOptionsConfirmation();
        getUserDetails();
        setIconImage();
        jLabel9.setText(date);
        setScrolls();
        Stock s = new Stock();
        jPanel4.removeAll();
        jPanel4.add(s);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Stock");
        markAttendance();

    }

    public void markAttendance() {
        String user_id = Sign_In.userId;

        try {

            Date Mtoday = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
            String string_date = date_format.format(Mtoday);

            String[] date_array = string_date.split("-");
            String year = date_array[0];
            String month = date_array[1];
            String day = date_array[2];

            ResultSet days_work = MySql.search("SELECT * FROM `days_work` WHERE `user_id`='" + user_id + "' AND `year`='" + year + "' AND `month_id`='" + month + "' AND `day`='" + day + "'");

            if (days_work.next()) {

            } else {

                MySql.iud("INSERT INTO `days_work` (`user_id`,`year`,`month_id`,`day`) VALUES ('" + user_id + "','" + year + "','" + month + "','" + day + "')");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScrolls() {
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
    }

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/tab-icon.png")));
    }

    public void time() {

        Thread t = new Thread(new Runnable() {

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void run() {

                while (true) {

                    Date d = new Date();
                    String date = sdf.format(d);

                    jLabel17.setText(date);

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        t.start();

    }

    public final void closeOptionsConfirmation() {

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

    }

    public void getUserDetails() {

        String user_id = Sign_In.userId;

        try {

            ResultSet user_details = MySql.search("SELECT * FROM `user` WHERE `id`='" + user_id + "'");
            user_details.next();

            String first_name = user_details.getString("f_name");
            String last_name = user_details.getString("l_name");
            String user_role_id = user_details.getString("user_type_id");

            ResultSet user_role_details = MySql.search("SELECT * FROM `user_type` WHERE `id`='" + user_role_id + "'");
            user_role_details.next();

            jLabel4.setText(first_name + " " + last_name);
            jLabel5.setText(first_name + " " + last_name);

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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton24 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jButton30 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aurora Market Place");
        setMinimumSize(new java.awt.Dimension(1250, 740));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-logo-short.png"))); // NOI18N

        kGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkBorderRadius(20);
        kGradientPanel3.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel3.setkStartColor(new java.awt.Color(245, 245, 245));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel7.setText("Today");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setText("None");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-stock.png"))); // NOI18N
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);

        jButton7.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton7.setText("Check Stock");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton7.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton7FocusLost(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-invoice.png"))); // NOI18N
        jButton23.setBorderPainted(false);
        jButton23.setContentAreaFilled(false);

        jButton9.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton9.setText("Invoice");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton9.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton9FocusLost(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-profile.png"))); // NOI18N
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);

        jButton10.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton10.setText("Profile");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton10.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton10FocusLost(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-power.png"))); // NOI18N
        jButton25.setBorderPainted(false);
        jButton25.setContentAreaFilled(false);

        jButton11.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton11.setText("Exit");
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton11.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton11FocusLost(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-customer.png"))); // NOI18N
        jButton32.setBorderPainted(false);
        jButton32.setContentAreaFilled(false);

        jButton33.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton33.setText("Manage Customers");
        jButton33.setBorderPainted(false);
        jButton33.setContentAreaFilled(false);
        jButton33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton33.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton33.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton33FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton33FocusLost(evt);
            }
        });
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-plus.png"))); // NOI18N
        jButton45.setBorderPainted(false);
        jButton45.setContentAreaFilled(false);

        jButton46.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton46.setForeground(new java.awt.Color(79, 79, 79));
        jButton46.setText("Issue Invoice");
        jButton46.setBorderPainted(false);
        jButton46.setContentAreaFilled(false);
        jButton46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton46.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton46.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton46.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton46FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton46FocusLost(evt);
            }
        });
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-eye-outline.png"))); // NOI18N
        jButton47.setBorderPainted(false);
        jButton47.setContentAreaFilled(false);

        jButton48.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton48.setForeground(new java.awt.Color(79, 79, 79));
        jButton48.setText("View Previous Invoices");
        jButton48.setBorderPainted(false);
        jButton48.setContentAreaFilled(false);
        jButton48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton48.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton48.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton48.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton48FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton48FocusLost(evt);
            }
        });
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton21)
                        .addGap(6, 6, 6)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(314, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 69));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel2.setText("Welcome Back");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jLabel4.setText("Vidhura Neethika");

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-profile.png"))); // NOI18N
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Vidhura Neethika");

        kGradientPanel1.setkBorderRadius(30);
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(121, 203, 96));
        kGradientPanel1.setMaximumSize(new java.awt.Dimension(113, 35));
        kGradientPanel1.setMinimumSize(new java.awt.Dimension(113, 35));

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-power.png"))); // NOI18N
        jButton30.setBorderPainted(false);
        jButton30.setContentAreaFilled(false);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-bell.png"))); // NOI18N
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-settings.png"))); // NOI18N
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton30)
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kGradientPanel2.setkBorderRadius(30);
        kGradientPanel2.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-search.png"))); // NOI18N

        jTextField1.setBackground(new Color(0,0,0,0));
        jTextField1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Search");
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
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

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.CardLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(237, 237, 237)));
        jPanel7.setPreferredSize(new java.awt.Dimension(0, 25));

        jLabel10.setFont(new java.awt.Font("Ebrima", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(156, 156, 156));
        jLabel10.setText("UTF - 8");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-bell.png"))); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-info.png"))); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-share.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-grid.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Ebrima", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(156, 156, 156));
        jLabel15.setText("ENG");

        jLabel16.setFont(new java.awt.Font("Ebrima", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(156, 156, 156));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Dashboard");

        jLabel17.setFont(new java.awt.Font("Ebrima", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("12:46:10 PM");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(30, 30, 30)
                .addComponent(jLabel10)
                .addGap(33, 33, 33)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addComponent(jLabel17))
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(237, 237, 237)));

        jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-store.png"))); // NOI18N
        jButton49.setBorderPainted(false);
        jButton49.setContentAreaFilled(false);
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-grns.png"))); // NOI18N
        jButton50.setBorderPainted(false);
        jButton50.setContentAreaFilled(false);
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-unpack.png"))); // NOI18N
        jButton51.setBorderPainted(false);
        jButton51.setContentAreaFilled(false);
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-invoice.png"))); // NOI18N
        jButton52.setBorderPainted(false);
        jButton52.setContentAreaFilled(false);
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton52)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1438, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N

        jMenu1.setText("  File  ");

        jMenuItem1.setText("Open");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("  Edit  ");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("  View  ");
        jMenuBar1.add(jMenu3);

        jMenu6.setText("  Tools  ");
        jMenuBar1.add(jMenu6);

        jMenu8.setText("  Account  ");
        jMenuBar1.add(jMenu8);

        jMenu4.setText("  About  ");
        jMenuBar1.add(jMenu4);

        jMenu7.setText("  Help  ");
        jMenuBar1.add(jMenu7);

        jMenu5.setText("  Exit  ");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        Stock s = new Stock();
        jPanel4.removeAll();
        jPanel4.add(s);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Stock");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        Invoice in = new Invoice();
        jPanel4.removeAll();
        jPanel4.add(in);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Invoice");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed

        Manage_customers mc = new Manage_customers();
        jPanel4.removeAll();
        jPanel4.add(mc);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Manage Customers");
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed

        Invoice in = new Invoice();
        jPanel4.removeAll();
        jPanel4.add(in);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Invoice");

    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed

        View_invoices vi = new View_invoices();
        jPanel4.removeAll();
        jPanel4.add(vi);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("View Previous Invoices");

    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed

        Profile pf = new Profile();
        jPanel4.removeAll();
        jPanel4.add(pf);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Profile");

    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if (jTextField1.getText().equals("Search")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (jTextField1.getText().equals("")) {
            jTextField1.setText("Search");
            jTextField1.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed

        Stock s = new Stock();
        jPanel4.removeAll();
        jPanel4.add(s);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Stock");

    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed

        GRN grn = new GRN();
        jPanel4.removeAll();
        jPanel4.add(grn);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Goods Receive Note");

    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed

        Damages dm = new Damages();
        jPanel4.removeAll();
        jPanel4.add(dm);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Goods Returns Note");

    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed

        Invoice in = new Invoice();
        jPanel4.removeAll();
        jPanel4.add(in);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Invoice");

    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        Profile pf = new Profile();
        jPanel4.removeAll();
        jPanel4.add(pf);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Profile");

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton33FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton33FocusGained

        jButton33.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton33FocusGained

    private void jButton33FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton33FocusLost

        jButton33.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton33FocusLost

    private void jButton7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton7FocusGained

        jButton7.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton7FocusGained

    private void jButton7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton7FocusLost

        jButton7.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton7FocusLost

    private void jButton9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton9FocusGained

        jButton9.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton9FocusGained

    private void jButton9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton9FocusLost

        jButton9.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton9FocusLost

    private void jButton46FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton46FocusGained

        jButton46.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton46FocusGained

    private void jButton46FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton46FocusLost

        jButton46.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton46FocusLost

    private void jButton48FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton48FocusGained

        jButton48.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton48FocusGained

    private void jButton48FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton48FocusLost

        jButton48.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton48FocusLost

    private void jButton10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton10FocusGained

        jButton10.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton10FocusGained

    private void jButton10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton10FocusLost

        jButton10.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton10FocusLost

    private void jButton11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton11FocusGained

        jButton11.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton11FocusGained

    private void jButton11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton11FocusLost

        jButton11.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton11FocusLost

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        String text = jTextField1.getText();
        if (text.equals("")) {
            Stock s = new Stock();
            jPanel4.removeAll();
            jPanel4.add(s);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Stock");
        } else if (text.equals("Manage Customers")) {
            Manage_customers mc = new Manage_customers();
            jPanel4.removeAll();
            jPanel4.add(mc);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Manage Customers");
        } else if (text.equals("Stock")) {
            Stock s = new Stock();
            jPanel4.removeAll();
            jPanel4.add(s);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Stock");
        } else if (text.equals("Invoice")) {
            Invoice in = new Invoice();
            jPanel4.removeAll();
            jPanel4.add(in);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Invoice");
        } else if (text.equals("Issue Invoice")) {
            Invoice in = new Invoice();
            jPanel4.removeAll();
            jPanel4.add(in);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Invoice");
        } else if (text.equals("View Previous Invoices")) {
            View_invoices vi = new View_invoices();
            jPanel4.removeAll();
            jPanel4.add(vi);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("View Previous Invoices");
        } else if (text.equals("Profile")) {
            Profile pf = new Profile();
            jPanel4.removeAll();
            jPanel4.add(pf);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Profile");
        } else {
            Empty_result em = new Empty_result();
            jPanel4.removeAll();
            jPanel4.add(em);
            jPanel4.revalidate();
            jPanel4.repaint();
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {

            InputStream is = Splash.class.getResourceAsStream("/resources/GitHub.theme.json");
            IntelliJTheme.setup(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cashier_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextField1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
