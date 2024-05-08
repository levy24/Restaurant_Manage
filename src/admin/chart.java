package admin;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

import static org.junit.Assert.assertFalse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import connectDTB.connect;


public class chart extends JFrame implements ActionListener {
    private JButton btnShowChart;
    private JComboBox<String> comboBox;
    private ChartPanel chartPanel;
    private static JSpinner start;
    private static JSpinner end;
    private AbstractButton txtStartDate, txtEndDate;

    private static CategoryDataset createDataset(String startDate, String endDate, String type) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        connect connector = new connect();
        Connection conn = connector.connection;
        if (conn != null) {
            String query = "";
            switch (type) {
                case "Ngày":
                    query = "SELECT DATE(time) as time, SUM(total) as total FROM bill WHERE DATE(time) BETWEEN ? AND ? GROUP BY DATE(time)";
                    break;
                case "Tháng":
                    query = "SELECT MONTH(time) AS time, SUM(total) AS total FROM bill WHERE time BETWEEN ? AND ? GROUP BY  MONTH(time);";
                    break;
                case "Năm":
                    query = "SELECT YEAR(time) AS time, SUM(total) AS total FROM bill WHERE YEAR(time) BETWEEN ? AND ? GROUP BY YEAR(time)";
                    break;
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, startDate);
                stmt.setString(2, endDate);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String time = rs.getString("time");
                    int total = rs.getInt("total");
                    dataset.addValue(total, "Số tiền", time);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connector.closeConnection();
            }
        }
        return dataset;
    }


    public JFreeChart createChart(String startDate, String endDate, String type) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU NHÀ HÀNG",
                type, "Số tiền",
                createDataset(startDate, endDate, type), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == btnShowChart) {
    		String strStartDate = null, strEndDate = null;
            
            Date startDate = (Date) start.getValue();
            Date endDate = (Date) end.getValue();
                
                // Chuyển đổi ngày tháng năm thành chuỗi định dạng yyyy-MM-dd
                strStartDate = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
                strEndDate = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
                
             // Kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu  
             if (strEndDate.compareTo(strStartDate) < 0)
                        // Nếu ngày kết thúc không lớn hơn ngày bắt đầu, hiển thị thông báo và yêu cầu nhập lại
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             else {
            String selectedType = (String) comboBox.getSelectedItem();
            JFreeChart chart = createChart(strStartDate, strEndDate, selectedType);
            chartPanel.setChart(chart);
             }
        }
    }

    public chart() {
        setTitle("Biểu đồ JFreeChart trong Java Swing");
        setSize(1900, 1000);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel lblStartDate = new JLabel("Từ:");
        panel.add(lblStartDate);
        
        start = new JSpinner();
        panel.add(start);
        start.setModel(new SpinnerDateModel(new Date(1713978000000L), null, null, Calendar.DAY_OF_YEAR));

        JLabel lblEndDate = new JLabel("Đến:");
        panel.add(lblEndDate);
        
        end = new JSpinner();
        panel.add(end);
        end.setModel(new SpinnerDateModel(new Date(1713978000000L), null, null, Calendar.DAY_OF_YEAR));

        String[] types = {"Ngày", "Tháng", "Năm"};
        comboBox = new JComboBox<>(types);
        panel.add(comboBox);

        btnShowChart = new JButton("Xem biểu đồ");
        btnShowChart.addActionListener(this);
        panel.add(btnShowChart);

        chartPanel = new ChartPanel(null);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            chart chart = new chart();
            chart.setVisible(true);
        });
    }
}