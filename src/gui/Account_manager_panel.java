/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import barchart.ModelChart;
import polarchart.ModelPolarAreaChart;
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
import scrollbar.ScrollBarCustom;
import com.jiat.aurora_market_place.MySql;
import com.jiat.aurora_market_place.TableContentAlign;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DELL
 */
public class Account_manager_panel extends javax.swing.JFrame {

    /**
     * Creates new form Aurora_market_place
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("0.00");
    Date today = new Date();
    String date = sdf.format(today);

    public TableCellRenderer centerAlignment = new TableContentAlign();

    public Account_manager_panel() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setScrolls();
        time();
        closeOptionsConfirmation();
        getUserDetails();
        setIconImage();
        jLabel9.setText(date);
        barChartViewer();
        polarChartViewer();
        loadUsers();
        tableDesign();
        fillDashboard();
    }

    public void barChartViewer() {

        chart.addLegend("Income", new Color(245, 189, 135));
        chart.addLegend("Cost", new Color(135, 189, 245));

        try {

            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String string_date = date_format.format(today);
            String[] year_array = string_date.split("-");
            String year = year_array[0];
            String month_now = year_array[1];

            ResultSet invoice_details = MySql.search("SELECT * FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "%'");

            ResultSet outcome_details = MySql.search("SELECT * FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "%'");

            double january_outcome = 0, february_outcome = 0, march_outcome = 0, april_outcome = 0, may_outcome = 0, june_outcome = 0, july_outcome = 0, august_outcome = 0, september_outcome = 0, october_outcome = 0, november_outcome = 0, december_outcome = 0;

            double january_invoice = 0, february_invoice = 0, march_invoice = 0, april_invoice = 0, may_invoice = 0, june_invoice = 0, july_invoice = 0, august_invoice = 0, september_invoice = 0, october_invoice = 0, november_invoice = 0, december_invoice = 0;

            while (invoice_details.next()) {

                String date = invoice_details.getString("invoice.date_time");
                String[] date_array = date.split("-");
                String month = date_array[1];

                if (month.equals("01")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        january_invoice = 0;
                    } else {
                        january_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("02")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        february_invoice = 0;
                    } else {
                        february_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("03")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        march_invoice = 0;
                    } else {
                        march_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("04")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        april_invoice = 0;
                    } else {
                        april_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("05")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        may_invoice = 0;
                    } else {
                        may_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("06")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        june_invoice = 0;
                    } else {
                        june_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("07")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        july_invoice = 0;
                    } else {
                        july_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("08")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        august_invoice = 0;
                    } else {
                        august_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("09")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        september_invoice = 0;
                    } else {
                        september_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("10")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        october_invoice = 0;
                    } else {
                        october_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("11")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        november_invoice = 0;
                    } else {
                        november_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                } else if (month.equals("12")) {

                    ResultSet invoice = MySql.search("SELECT SUM(`payment`) AS 'total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
                    invoice.next();

                    if (invoice.getString("total") == null) {
                        december_invoice = 0;
                    } else {
                        december_invoice = Double.parseDouble(invoice.getString("total"));
                    }

                }

            }

            //while (damages_details.next()) {
            //System.out.println("damages dates: " + damages_details.getString("damages.date_time"));
            //}
            while (outcome_details.next()) {

                String outcome_date = outcome_details.getString("paid_date");
                String[] date_array = outcome_date.split("-");
                String month_outcome = date_array[1];

                if (month_outcome.equals("01")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        january_outcome = 0;
                    } else {
                        january_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("02")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        february_outcome = 0;
                    } else {
                        february_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("03")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        march_outcome = 0;
                    } else {
                        march_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("04")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        april_outcome = 0;
                    } else {
                        april_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("05")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        may_outcome = 0;
                    } else {
                        may_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("06")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        june_outcome = 0;
                    } else {
                        june_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("07")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        july_outcome = 0;
                    } else {
                        july_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("08")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        august_outcome = 0;
                    } else {
                        august_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("09")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        september_outcome = 0;
                    } else {
                        september_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("10")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        october_outcome = 0;
                    } else {
                        october_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("11")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        november_outcome = 0;
                    } else {
                        november_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                } else if (month_outcome.equals("12")) {

                    ResultSet outcome = MySql.search("SELECT SUM(`salary`) AS 'salary',SUM(epf) AS 'epf',SUM(etf) AS 'etf' FROM `salary` WHERE `salary`.`paid_date` LIKE '%" + year + "-" + month_outcome + "%'");
                    outcome.next();

                    if (outcome.getString("salary") == null && outcome.getString("epf") == null && outcome.getString("etf") == null) {
                        december_outcome = 0;
                    } else {
                        december_outcome = Double.parseDouble(outcome.getString("salary")) + Double.parseDouble(outcome.getString("epf")) + Double.parseDouble(outcome.getString("etf"));
                    }

                }

            }

            if (null != month_now) {
                switch (month_now) {
                    case "01":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        break;
                    case "02":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        break;
                    case "03":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        break;
                    case "04":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        break;
                    case "05":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        break;
                    case "06":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        break;
                    case "07":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        chart.addData(new ModelChart("July", new double[]{july_invoice, july_outcome}));
                        break;
                    case "08":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        chart.addData(new ModelChart("July", new double[]{july_invoice, july_outcome}));
                        chart.addData(new ModelChart("August", new double[]{august_invoice, august_outcome}));
                        break;
                    case "09":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        chart.addData(new ModelChart("July", new double[]{july_invoice, july_outcome}));
                        chart.addData(new ModelChart("August", new double[]{august_invoice, august_outcome}));
                        chart.addData(new ModelChart("September", new double[]{september_invoice, september_outcome}));
                        break;
                    case "10":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        chart.addData(new ModelChart("July", new double[]{july_invoice, july_outcome}));
                        chart.addData(new ModelChart("August", new double[]{august_invoice, august_outcome}));
                        chart.addData(new ModelChart("September", new double[]{september_invoice, september_outcome}));
                        chart.addData(new ModelChart("October", new double[]{october_invoice, october_outcome}));
                        break;
                    case "11":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        chart.addData(new ModelChart("July", new double[]{july_invoice, july_outcome}));
                        chart.addData(new ModelChart("August", new double[]{august_invoice, august_outcome}));
                        chart.addData(new ModelChart("September", new double[]{september_invoice, september_outcome}));
                        chart.addData(new ModelChart("October", new double[]{october_invoice, october_outcome}));
                        chart.addData(new ModelChart("November", new double[]{november_invoice, november_outcome}));
                        break;
                    case "12":
                        chart.addData(new ModelChart("January", new double[]{january_invoice, january_outcome}));
                        chart.addData(new ModelChart("February", new double[]{february_invoice, february_outcome}));
                        chart.addData(new ModelChart("March", new double[]{march_invoice, march_outcome}));
                        chart.addData(new ModelChart("April", new double[]{april_invoice, april_outcome}));
                        chart.addData(new ModelChart("May", new double[]{may_invoice, may_outcome}));
                        chart.addData(new ModelChart("June", new double[]{june_invoice, june_outcome}));
                        chart.addData(new ModelChart("July", new double[]{july_invoice, july_outcome}));
                        chart.addData(new ModelChart("August", new double[]{august_invoice, august_outcome}));
                        chart.addData(new ModelChart("September", new double[]{september_invoice, september_outcome}));
                        chart.addData(new ModelChart("October", new double[]{october_invoice, october_outcome}));
                        chart.addData(new ModelChart("November", new double[]{november_invoice, november_outcome}));
                        chart.addData(new ModelChart("December", new double[]{december_invoice, december_outcome}));
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        chart.start();

    }

    public void polarChartViewer() {

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String string_date = date_format.format(today);
        String[] year_array = string_date.split("-");
        String year = year_array[0];
        String month = year_array[1];

        try {

            ResultSet grn_details = MySql.search("SELECT SUM(`payment`) AS 'monthly_total' FROM `grn_payment` INNER JOIN `grn` ON `grn_payment`.`grn_id`=`grn`.`id` WHERE `grn`.`date_time` LIKE '%" + year + "-" + month + "%'");
            grn_details.next();

            ResultSet damages_details = MySql.search("SELECT SUM(`balance`) AS 'monthly_total' FROM `damages_payment` INNER JOIN `damages` ON `damages_payment`.`damages_id`=`damages`.`id` WHERE `damages`.`date_time` LIKE '%" + year + "-" + month + "%'");
            damages_details.next();

            ResultSet invoice_details = MySql.search("SELECT SUM(`payment`) AS 'monthly_total' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `invoice`.`date_time` LIKE '%" + year + "-" + month + "%'");
            invoice_details.next();

            polarAreaChart.addItem(new ModelPolarAreaChart(new Color(52, 148, 203), "GRN", Double.parseDouble(grn_details.getString("monthly_total"))));
            polarAreaChart.addItem(new ModelPolarAreaChart(new Color(175, 67, 237), "Retruns", Double.parseDouble(damages_details.getString("monthly_total"))));
            polarAreaChart.addItem(new ModelPolarAreaChart(new Color(87, 218, 137), "Sellings", Double.parseDouble(invoice_details.getString("monthly_total"))));

        } catch (Exception e) {
            e.printStackTrace();
        }

        polarAreaChart.start();
    }

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/tab-icon.png")));
    }

    public void setScrolls() {
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.setHorizontalScrollBar(new scrollbar.ScrollBarCustom());
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
            jLabel19.setText(first_name + " " + last_name);
            jLabel22.setText(user_role_details.getString("name"));

            Date Mtoday = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
            String string_date = date_format.format(Mtoday);

            String[] date_array = string_date.split("-");
            String year = date_array[0];
            String month = date_array[1];
            String day = date_array[2];

            ResultSet days_work = MySql.search("SELECT * FROM `days_work` WHERE `user_id`='" + user_id + "' AND `year`='" + year + "' AND `month_id`='" + month + "' AND `day`='" + day + "'");

            if (days_work.next()) {
                jButton2.setText("Marked");
            } else {
                jButton2.setEnabled(true);
                jButton2.setText("Mark");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void loadUsers() {
        try {
            ResultSet rs = MySql.search("SELECT `user`.`id`,`user`.`f_name`,`user`.`l_name`,`user`.`username`,`user`.`password`,`user`.`contact_number`,`city`.`name`  AS 'city_name',`status`.`name` AS 'status_name',`user_type`.`name` AS 'user_type_name' FROM `user` INNER JOIN `city` ON  `user`.`city_id` = `city`.`id` INNER JOIN `user_type` ON `user`.`user_type_id` = `user_type`.`id` INNER JOIN `status` ON `user`.`status_id` = `status`.`id` WHERE `user_type_id`!='1' ORDER BY `id` ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("f_name") + " " + rs.getString("l_name"));
                v.add(rs.getString("username"));
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

    public void tableDesign() {
        JTableHeader table_header = jTable1.getTableHeader();
        jTable1.setBackground(Color.WHITE);

        Color table_header_color = new Color(250, 250, 250);
        Color table_header_font_color = new Color(91, 97, 135);

        table_header.setBackground(table_header_color);
        table_header.setForeground(Color.DARK_GRAY);

        table_header.setFont(new Font("Yu Gothic Ui", Font.BOLD, 14));

        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(centerAlignment);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(centerAlignment);
    }

    public void fillDashboard() {
        try {

            ResultSet stock_details = MySql.search("SELECT SUM(`selling_price`) AS 'stock_total' FROM `stock`");
            stock_details.next();

            jLabel28.setText("Rs. " + df.format(Double.parseDouble(stock_details.getString("stock_total"))));

            Date today = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
            String string_date = date_format.format(today);

            String[] date_array = string_date.split("-");
            String year = date_array[0];
            String month = date_array[1];

            ResultSet monthly_earning_details = MySql.search("SELECT SUM(`payment`) AS 'monthly_earnings' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id` WHERE `date_time` LIKE '%" + year + "-" + month + "%'");
            monthly_earning_details.next();

            jLabel30.setText("Rs. " + df.format(Double.parseDouble(monthly_earning_details.getString("monthly_earnings"))));

            ResultSet total_sale_details = MySql.search("SELECT SUM(`payment`) AS 'total_sales' FROM `invoice_payment` INNER JOIN `invoice` ON `invoice_payment`.`invoice_id`=`invoice`.`id`");
            total_sale_details.next();

            jLabel29.setText("Rs. " + df.format(Double.parseDouble(total_sale_details.getString("total_sales"))));

            ResultSet cashiers_count = MySql.search("SELECT COUNT(`user`.`id`) AS 'count' FROM `user` INNER JOIN `user_type` ON `user`.`user_type_id`=`user_type`.`id` WHERE `user_type`.`name`='Cashier'");
            cashiers_count.next();

            jLabel42.setText(cashiers_count.getString("count"));

            ResultSet Account_Manager_count = MySql.search("SELECT COUNT(`user`.`id`) AS 'count' FROM `user` INNER JOIN `user_type` ON `user`.`user_type_id`=`user_type`.`id` WHERE `user_type`.`name`='Account Manager'");
            Account_Manager_count.next();

            jLabel39.setText(Account_Manager_count.getString("count"));

            ResultSet all_users_count = MySql.search("SELECT COUNT(`user`.`id`) AS 'count' FROM `user` INNER JOIN `user_type` ON `user`.`user_type_id`=`user_type`.`id`");
            all_users_count.next();

            jLabel44.setText(all_users_count.getString("count"));

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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton24 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
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
        jPanel5 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        kGradientPanel4 = new com.k33ptoo.components.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        kGradientPanel5 = new com.k33ptoo.components.KGradientPanel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        kGradientPanel9 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel6 = new com.k33ptoo.components.KGradientPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        kGradientPanel11 = new com.k33ptoo.components.KGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        kGradientPanel10 = new com.k33ptoo.components.KGradientPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        kGradientPanel7 = new com.k33ptoo.components.KGradientPanel();
        jLabel27 = new javax.swing.JLabel();
        chart = new barchart.Chart();
        kGradientPanel8 = new com.k33ptoo.components.KGradientPanel();
        jLabel33 = new javax.swing.JLabel();
        polarAreaChart = new polarchart.PolarAreaChart();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        kGradientPanel12 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel14 = new com.k33ptoo.components.KGradientPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        kGradientPanel15 = new com.k33ptoo.components.KGradientPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        kGradientPanel13 = new com.k33ptoo.components.KGradientPanel();
        jLabel39 = new javax.swing.JLabel();
        kGradientPanel16 = new com.k33ptoo.components.KGradientPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        kGradientPanel17 = new com.k33ptoo.components.KGradientPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        kGradientPanel18 = new com.k33ptoo.components.KGradientPanel();
        jLabel44 = new javax.swing.JLabel();
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
        jButton55 = new javax.swing.JButton();
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
        setMinimumSize(new java.awt.Dimension(1260, 740));

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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-home.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-supplier.png"))); // NOI18N
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);

        jButton5.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton5.setText("Manage Suppliers");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton5.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton5FocusLost(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-company.png"))); // NOI18N
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);

        jButton6.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton6.setText("Company Registration");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton6.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton6FocusLost(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-branch.png"))); // NOI18N
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);

        jButton12.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton12.setText("Branch Registration");
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton12.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton12FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton12FocusLost(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

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

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-grn.png"))); // NOI18N
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);

        jButton8.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton8.setText("Goods Receive Notes");
        jButton8.setAlignmentY(0.0F);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton8.setIconTextGap(0);
        jButton8.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton8FocusLost(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
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

        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-eye-outline.png"))); // NOI18N
        jButton36.setBorderPainted(false);
        jButton36.setContentAreaFilled(false);

        jButton37.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton37.setForeground(new java.awt.Color(79, 79, 79));
        jButton37.setText("View Previous Notes");
        jButton37.setBorderPainted(false);
        jButton37.setContentAreaFilled(false);
        jButton37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton37.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton37.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton37FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton37FocusLost(evt);
            }
        });
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-plus.png"))); // NOI18N
        jButton38.setBorderPainted(false);
        jButton38.setContentAreaFilled(false);

        jButton39.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton39.setForeground(new java.awt.Color(79, 79, 79));
        jButton39.setText("Issue GRN");
        jButton39.setBorderPainted(false);
        jButton39.setContentAreaFilled(false);
        jButton39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton39.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton39.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton39.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton39FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton39FocusLost(evt);
            }
        });
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton40.setText("Dashboard");
        jButton40.setBorderPainted(false);
        jButton40.setContentAreaFilled(false);
        jButton40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton40.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton40.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton40.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton40FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton40FocusLost(evt);
            }
        });
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton35.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton35.setText("Goods Return Notes");
        jButton35.setBorderPainted(false);
        jButton35.setContentAreaFilled(false);
        jButton35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton35.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton35.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton35.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton35FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton35FocusLost(evt);
            }
        });
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-damage.png"))); // NOI18N
        jButton34.setBorderPainted(false);
        jButton34.setContentAreaFilled(false);

        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-plus.png"))); // NOI18N
        jButton41.setBorderPainted(false);
        jButton41.setContentAreaFilled(false);

        jButton42.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton42.setForeground(new java.awt.Color(79, 79, 79));
        jButton42.setText("Issue Goods Re...");
        jButton42.setBorderPainted(false);
        jButton42.setContentAreaFilled(false);
        jButton42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton42.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton42.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton42.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton42FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton42FocusLost(evt);
            }
        });
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-eye-outline.png"))); // NOI18N
        jButton43.setBorderPainted(false);
        jButton43.setContentAreaFilled(false);

        jButton44.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton44.setForeground(new java.awt.Color(79, 79, 79));
        jButton44.setText("View Previous Notes");
        jButton44.setBorderPainted(false);
        jButton44.setContentAreaFilled(false);
        jButton44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton44.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton44.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton44.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton44FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton44FocusLost(evt);
            }
        });
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
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

        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-coins.png"))); // NOI18N
        jButton53.setBorderPainted(false);
        jButton53.setContentAreaFilled(false);

        jButton54.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jButton54.setText("Salary");
        jButton54.setBorderPainted(false);
        jButton54.setContentAreaFilled(false);
        jButton54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton54.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton54.setMargin(new java.awt.Insets(2, 0, 2, 14));
        jButton54.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton54FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton54FocusLost(evt);
            }
        });
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButton22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jButton25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jButton24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jButton20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jButton19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jButton4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton26)
                                        .addComponent(jButton21))
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jButton34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
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
                                            .addGap(0, 0, Short.MAX_VALUE))))))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton53)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(186, Short.MAX_VALUE))
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
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(923, 583));

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-home.png"))); // NOI18N
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setText("Dashboard");

        jScrollPane1.setBorder(null);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel4.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel4.setkBorderRadius(20);
        kGradientPanel4.setkEndColor(new java.awt.Color(247, 248, 250));
        kGradientPanel4.setkStartColor(new java.awt.Color(247, 248, 250));
        kGradientPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Welcome ,");
        kGradientPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 9, -1, -1));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Vidhura Neethika");
        kGradientPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 9, 175, -1));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("Aurora Market Place");
        kGradientPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 29, 247, -1));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/big-check.png"))); // NOI18N
        kGradientPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 90, 100));

        kGradientPanel5.setBackground(new java.awt.Color(247, 248, 250));
        kGradientPanel5.setkBorderRadius(30);
        kGradientPanel5.setkStartColor(new java.awt.Color(121, 203, 96));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Mark");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        kGradientPanel4.add(kGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-verify.png"))); // NOI18N
        kGradientPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel22.setText("None");
        kGradientPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, -1));

        kGradientPanel9.setkStartColor(new java.awt.Color(121, 203, 96));

        javax.swing.GroupLayout kGradientPanel9Layout = new javax.swing.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        kGradientPanel4.add(kGradientPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 30, -1));

        kGradientPanel6.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setkBorderRadius(20);
        kGradientPanel6.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 147, 150));

        jLabel23.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Stock Total");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dash-stock.png"))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Rs. 5400000.00");

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel6Layout.createSequentialGroup()
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, kGradientPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26)))
                .addGap(14, 14, 14))
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(17, 17, 17))
        );

        kGradientPanel11.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel11.setkBorderRadius(20);
        kGradientPanel11.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel11.setkStartColor(new java.awt.Color(58, 155, 232));

        jLabel24.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Monthly Earnings");

        jLabel30.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Rs. 5400000.00");

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dash-monthly-earnings.png"))); // NOI18N

        javax.swing.GroupLayout kGradientPanel11Layout = new javax.swing.GroupLayout(kGradientPanel11);
        kGradientPanel11.setLayout(kGradientPanel11Layout);
        kGradientPanel11Layout.setHorizontalGroup(
            kGradientPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(14, 14, 14))
            .addGroup(kGradientPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel11Layout.setVerticalGroup(
            kGradientPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(kGradientPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(17, 17, 17))
        );

        kGradientPanel10.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setkBorderRadius(20);
        kGradientPanel10.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel10.setkStartColor(new java.awt.Color(56, 210, 188));

        jLabel25.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Total Sale");

        jLabel29.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Rs. 5400000.00");

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dash-sale.png"))); // NOI18N

        javax.swing.GroupLayout kGradientPanel10Layout = new javax.swing.GroupLayout(kGradientPanel10);
        kGradientPanel10.setLayout(kGradientPanel10Layout);
        kGradientPanel10Layout.setHorizontalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(14, 14, 14))
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel10Layout.setVerticalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(17, 17, 17))
        );

        kGradientPanel7.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel7.setkBorderRadius(30);
        kGradientPanel7.setkEndColor(new java.awt.Color(252, 252, 252));
        kGradientPanel7.setkStartColor(new java.awt.Color(252, 252, 252));

        jLabel27.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("  Total Growth");

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        kGradientPanel8.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel8.setkBorderRadius(30);
        kGradientPanel8.setkEndColor(new java.awt.Color(252, 252, 252));
        kGradientPanel8.setkStartColor(new java.awt.Color(252, 252, 252));

        jLabel33.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Products Overview");

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(polarAreaChart, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(polarAreaChart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Yu Gothic UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Employee Name", "Username", "Contact", "User Type", "City", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(250, 250, 250));
        jTable1.setRowHeight(23);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionBackground(new java.awt.Color(216, 243, 207));
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);

        jLabel34.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Total Employees");

        kGradientPanel12.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel12.setkBorderRadius(30);
        kGradientPanel12.setkEndColor(new java.awt.Color(252, 252, 252));
        kGradientPanel12.setkStartColor(new java.awt.Color(252, 252, 252));

        kGradientPanel14.setBackground(new java.awt.Color(252, 252, 252));
        kGradientPanel14.setkBorderRadius(25);
        kGradientPanel14.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel14.setkStartColor(new java.awt.Color(245, 245, 245));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-owner.png"))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Owner - Vidhura Neethika");

        javax.swing.GroupLayout kGradientPanel14Layout = new javax.swing.GroupLayout(kGradientPanel14);
        kGradientPanel14.setLayout(kGradientPanel14Layout);
        kGradientPanel14Layout.setHorizontalGroup(
            kGradientPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel14Layout.setVerticalGroup(
            kGradientPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel15.setBackground(new java.awt.Color(252, 252, 252));
        kGradientPanel15.setkBorderRadius(25);
        kGradientPanel15.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel15.setkStartColor(new java.awt.Color(245, 245, 245));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-accountant.png"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Account Managers");

        kGradientPanel13.setBackground(new java.awt.Color(239, 239, 239));
        kGradientPanel13.setkEndColor(new java.awt.Color(102, 102, 102));
        kGradientPanel13.setkStartColor(new java.awt.Color(102, 102, 102));

        jLabel39.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("10");

        javax.swing.GroupLayout kGradientPanel13Layout = new javax.swing.GroupLayout(kGradientPanel13);
        kGradientPanel13.setLayout(kGradientPanel13Layout);
        kGradientPanel13Layout.setHorizontalGroup(
            kGradientPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );
        kGradientPanel13Layout.setVerticalGroup(
            kGradientPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout kGradientPanel15Layout = new javax.swing.GroupLayout(kGradientPanel15);
        kGradientPanel15.setLayout(kGradientPanel15Layout);
        kGradientPanel15Layout.setHorizontalGroup(
            kGradientPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kGradientPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        kGradientPanel15Layout.setVerticalGroup(
            kGradientPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel16.setBackground(new java.awt.Color(252, 252, 252));
        kGradientPanel16.setkBorderRadius(25);
        kGradientPanel16.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel16.setkStartColor(new java.awt.Color(245, 245, 245));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/small-cashier.png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Cashiers");

        kGradientPanel17.setBackground(new java.awt.Color(239, 239, 239));
        kGradientPanel17.setkEndColor(new java.awt.Color(102, 102, 102));
        kGradientPanel17.setkStartColor(new java.awt.Color(102, 102, 102));

        jLabel42.setFont(new java.awt.Font("Ebrima", 1, 13)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("10");

        javax.swing.GroupLayout kGradientPanel17Layout = new javax.swing.GroupLayout(kGradientPanel17);
        kGradientPanel17.setLayout(kGradientPanel17Layout);
        kGradientPanel17Layout.setHorizontalGroup(
            kGradientPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );
        kGradientPanel17Layout.setVerticalGroup(
            kGradientPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout kGradientPanel16Layout = new javax.swing.GroupLayout(kGradientPanel16);
        kGradientPanel16.setLayout(kGradientPanel16Layout);
        kGradientPanel16Layout.setHorizontalGroup(
            kGradientPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kGradientPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        kGradientPanel16Layout.setVerticalGroup(
            kGradientPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel43.setFont(new java.awt.Font("Microsoft Tai Le", 1, 15)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(153, 153, 153));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Total of Active Employees");

        kGradientPanel18.setBackground(new java.awt.Color(252, 252, 252));
        kGradientPanel18.setkBorderRadius(500);
        kGradientPanel18.setkStartColor(new java.awt.Color(121, 203, 96));

        jLabel44.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("20");

        javax.swing.GroupLayout kGradientPanel18Layout = new javax.swing.GroupLayout(kGradientPanel18);
        kGradientPanel18.setLayout(kGradientPanel18Layout);
        kGradientPanel18Layout.setHorizontalGroup(
            kGradientPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel18Layout.setVerticalGroup(
            kGradientPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel18Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel44)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel12Layout = new javax.swing.GroupLayout(kGradientPanel12);
        kGradientPanel12.setLayout(kGradientPanel12Layout);
        kGradientPanel12Layout.setHorizontalGroup(
            kGradientPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kGradientPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        kGradientPanel12Layout.setVerticalGroup(
            kGradientPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(kGradientPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kGradientPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kGradientPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(kGradientPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(kGradientPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(kGradientPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kGradientPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(kGradientPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(kGradientPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(kGradientPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3)))
                        .addGap(18, 18, 18))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(kGradientPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel5, "card2");

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

        jButton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/xs-salary.png"))); // NOI18N
        jButton55.setBorderPainted(false);
        jButton55.setContentAreaFilled(false);
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Supplier_registration sr = new Supplier_registration();
        jPanel4.removeAll();
        jPanel4.add(sr);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Manage Suppliers");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        Company_registration comr = new Company_registration();
        jPanel4.removeAll();
        jPanel4.add(comr);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Company Registration");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        Branch_registration brnchReg = new Branch_registration();
        jPanel4.removeAll();
        jPanel4.add(brnchReg);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Branch Registration");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        Stock s = new Stock();
        jPanel4.removeAll();
        jPanel4.add(s);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Stock");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        GRN grn = new GRN();
        jPanel4.removeAll();
        jPanel4.add(grn);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Goods Receive Note");
    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        View_grn vg = new View_grn();
        jPanel4.removeAll();
        jPanel4.add(vg);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("View Previous Goods Receive Notes");
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed

        GRN grn = new GRN();
        jPanel4.removeAll();
        jPanel4.add(grn);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Issue Goods Receive Note");
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed

        jPanel4.removeAll();
        jPanel4.add(jPanel5);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Dashboard");
        fillDashboard();
        loadUsers();

    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed

        Damages dm = new Damages();
        jPanel4.removeAll();
        jPanel4.add(dm);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Goods Returns Note");
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed

        Damages dm = new Damages();
        jPanel4.removeAll();
        jPanel4.add(dm);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Goods Returns Note");

    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed

        View_damages vgs = new View_damages();
        jPanel4.removeAll();
        jPanel4.add(vgs);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("View Previous Goods Return Notes");

    }//GEN-LAST:event_jButton44ActionPerformed

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

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed

        Salary_management sm = new Salary_management();
        jPanel4.removeAll();
        jPanel4.add(sm);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Salary");

    }//GEN-LAST:event_jButton54ActionPerformed

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

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed

        Salary_management sm = new Salary_management();
        jPanel4.removeAll();
        jPanel4.add(sm);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Salary");

    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

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

                JOptionPane.showMessageDialog(this, "Already Marked", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {

                MySql.iud("INSERT INTO `days_work` (`user_id`,`year`,`month_id`,`day`) VALUES ('" + user_id + "','" + year + "','" + month + "','" + day + "')");

                jButton2.setText("Marked");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        Profile pf = new Profile();
        jPanel4.removeAll();
        jPanel4.add(pf);
        jPanel4.revalidate();
        jPanel4.repaint();
        jLabel16.setText("Profile");

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton40FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton40FocusGained

        jButton40.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton40FocusGained

    private void jButton40FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton40FocusLost

        jButton40.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton40FocusLost

    private void jButton54FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton54FocusGained

        jButton54.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton54FocusGained

    private void jButton54FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton54FocusLost

        jButton54.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton54FocusLost

    private void jButton5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton5FocusGained

        jButton5.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton5FocusGained

    private void jButton5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton5FocusLost

        jButton5.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton5FocusLost

    private void jButton6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusGained

        jButton6.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton6FocusGained

    private void jButton6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusLost

        jButton6.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton6FocusLost

    private void jButton12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton12FocusGained

        jButton12.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton12FocusGained

    private void jButton12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton12FocusLost

        jButton12.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton12FocusLost

    private void jButton7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton7FocusGained

        jButton7.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton7FocusGained

    private void jButton7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton7FocusLost

        jButton7.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton7FocusLost

    private void jButton8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton8FocusGained

        jButton8.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton8FocusGained

    private void jButton8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton8FocusLost

        jButton8.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton8FocusLost

    private void jButton39FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton39FocusGained

        jButton39.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton39FocusGained

    private void jButton39FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton39FocusLost

        jButton39.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton39FocusLost

    private void jButton37FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton37FocusGained

        jButton37.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton37FocusGained

    private void jButton37FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton37FocusLost

        jButton37.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton37FocusLost

    private void jButton35FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton35FocusGained

        jButton35.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton35FocusGained

    private void jButton35FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton35FocusLost

        jButton35.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton35FocusLost

    private void jButton42FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton42FocusGained

        jButton42.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton42FocusGained

    private void jButton42FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton42FocusLost

        jButton42.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton42FocusLost

    private void jButton44FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton44FocusGained

        jButton44.setFont(new Font("Ebrima", Font.BOLD, 13));

    }//GEN-LAST:event_jButton44FocusGained

    private void jButton44FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton44FocusLost

        jButton44.setFont(new Font("Yu Gothic Ui", Font.PLAIN, 13));

    }//GEN-LAST:event_jButton44FocusLost

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

        if (text.equals("Dashboard")) {
            jPanel4.removeAll();
            jPanel4.add(jPanel5);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Dashboard");
            fillDashboard();
            loadUsers();
        } else if (text.equals("")) {
            jPanel4.removeAll();
            jPanel4.add(jPanel5);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Dashboard");
            fillDashboard();
            loadUsers();
        } else if (text.equals("Salary")) {
            Salary_management sm = new Salary_management();
            jPanel4.removeAll();
            jPanel4.add(sm);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Salary");
        }  else if (text.equals("Manage Suppliers")) {
            Supplier_registration sr = new Supplier_registration();
            jPanel4.removeAll();
            jPanel4.add(sr);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Manage Suppliers");
        } else if (text.equals("Company Registration")) {
            Company_registration comr = new Company_registration();
            jPanel4.removeAll();
            jPanel4.add(comr);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Company Registration");
        } else if (text.equals("Branch Registration")) {
            Branch_registration brnchReg = new Branch_registration();
            jPanel4.removeAll();
            jPanel4.add(brnchReg);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Branch Registration");
        } else if (text.equals("Stock")) {
            Stock s = new Stock();
            jPanel4.removeAll();
            jPanel4.add(s);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Stock");
        } else if (text.equals("GRN")) {
            GRN grn = new GRN();
            jPanel4.removeAll();
            jPanel4.add(grn);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Goods Receive Note");
        } else if (text.equals("Issue GRN")) {
            GRN grn = new GRN();
            jPanel4.removeAll();
            jPanel4.add(grn);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Goods Receive Note");
        } else if (text.equals("View Previous Goods Receive")) {
            View_grn vg = new View_grn();
            jPanel4.removeAll();
            jPanel4.add(vg);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("View Previous Goods Receive Notes");
        } else if (text.equals("Goods Returns")) {
            Damages dm = new Damages();
            jPanel4.removeAll();
            jPanel4.add(dm);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Goods Returns Note");
        } else if (text.equals("Issue Goods Ret")) {
            Damages dm = new Damages();
            jPanel4.removeAll();
            jPanel4.add(dm);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("Goods Returns Note");
        } else if (text.equals("View Previous Goods Return Notes")) {
            View_damages vgs = new View_damages();
            jPanel4.removeAll();
            jPanel4.add(vgs);
            jPanel4.revalidate();
            jPanel4.repaint();
            jLabel16.setText("View Previous Goods Return Notes");
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
        }else{
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
                new Account_manager_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private barchart.Chart chart;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel10;
    private com.k33ptoo.components.KGradientPanel kGradientPanel11;
    private com.k33ptoo.components.KGradientPanel kGradientPanel12;
    private com.k33ptoo.components.KGradientPanel kGradientPanel13;
    private com.k33ptoo.components.KGradientPanel kGradientPanel14;
    private com.k33ptoo.components.KGradientPanel kGradientPanel15;
    private com.k33ptoo.components.KGradientPanel kGradientPanel16;
    private com.k33ptoo.components.KGradientPanel kGradientPanel17;
    private com.k33ptoo.components.KGradientPanel kGradientPanel18;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private com.k33ptoo.components.KGradientPanel kGradientPanel4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel5;
    private com.k33ptoo.components.KGradientPanel kGradientPanel6;
    private com.k33ptoo.components.KGradientPanel kGradientPanel7;
    private com.k33ptoo.components.KGradientPanel kGradientPanel8;
    private com.k33ptoo.components.KGradientPanel kGradientPanel9;
    private polarchart.PolarAreaChart polarAreaChart;
    // End of variables declaration//GEN-END:variables
}
