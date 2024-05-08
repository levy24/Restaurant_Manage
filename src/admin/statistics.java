package admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import connectDTB.connect;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class statistics extends JPanel {
	private JButton btnShowChart;
    private JComboBox<String> comboBox;
    private ChartPanel chartPanel;
    private static JSpinner start;
    private static JSpinner end;
    private AbstractButton txtStartDate, txtEndDate;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public statistics() {
		setBounds(0, 0, 1540, 815);
        setLayout(null);
        JLabel lblStartDate = new JLabel("Từ:");
        lblStartDate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblStartDate.setBounds(279, 6, 39, 27);
        add(lblStartDate);
        
        start = new JSpinner();
        start.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        start.setBounds(328, 10, 156, 20);
        add(start);
        start.setModel(new SpinnerDateModel(new Date(1713978000000L), null, null, Calendar.DAY_OF_YEAR));

        JLabel lblEndDate = new JLabel("Đến:");
        lblEndDate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblEndDate.setBounds(514, 9, 44, 20);
        add(lblEndDate);
        
        end = new JSpinner();
        end.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        end.setBounds(557, 10, 156, 20);
        add(end);
        end.setModel(new SpinnerDateModel(new Date(1713978000000L), null, null, Calendar.DAY_OF_YEAR));

        String[] types = {"Ngày", "Tháng", "Năm"};
        comboBox = new JComboBox<>(types);
        comboBox.setBounds(742, 12, 54, 19);
        add(comboBox);

        btnShowChart = new JButton("Xem biểu đồ");
        btnShowChart.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        btnShowChart.setBounds(817, 11, 125, 21);
        btnShowChart.addActionListener(new ActionListener() {
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
        });
        
        add(btnShowChart);

        chartPanel = new ChartPanel(null);
        chartPanel.setBounds(117, 84, 1354, 656);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        add(chartPanel);
    
	}
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
	
}
