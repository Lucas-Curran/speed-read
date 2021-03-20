package humanbenchmark;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class SQLiteConnect {
	private static Connection conn = null;
	
	
	/**
	 * Creates default database
	 * @param args
	 */
	public static void main(String[] args) {
            connect("benchmark_data.db");

	}
	
	/**
	 * Connects to database file
	 * @return
	 */
	public static Connection connect(String database) {
            String url = "jdbc:sqlite:" + database;
            try {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(url);
                System.out.println("Connected");
            } catch (SQLException e) {
		System.out.println(e.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return conn;
	}
	

	/**
	 * Creates a new student from the database
         * @param date
	 */
	public void addRow() {
            String sql = "INSERT INTO Data(ID, Timing, Reaction, Date) VALUES(?,?,?,?) ";
            try {
		PreparedStatement input = connect("benchmark_data").prepareStatement(sql);
                input.setString(4, String.valueOf(java.time.LocalDate.now()));
		input.executeUpdate();
		conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
	}

        /**
	 * Creates a new student from the database
         * @param timing
	 */
	public void updateTiming(int timing) {
            String sql = "UPDATE Data SET Timing = " + timing + " WHERE id = ?";
            try {
		PreparedStatement input = connect("benchmark_data").prepareStatement(sql);
		input.executeUpdate();
		conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
	}
        
        /**
	 * Creates a new student from the database
         * @param reaction
	 */
	public void updateReaction(int reaction) {
            String sql = "UPDATE Data SET Reaction = " + reaction + " WHERE id = ?";
            try {
		PreparedStatement input = connect("benchmark_data").prepareStatement(sql);
		input.executeUpdate();
		conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
	}

        /**
	 * Get table info
         * 
         * @return
	 */
	public DefaultTableModel getData() {
            String sql = "SELECT * Data";
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Timing");
            model.addColumn("Reaction");
            model.addColumn("Date");
            try {
		PreparedStatement input = connect("benchmark_data.db").prepareStatement(sql);
                ResultSet rs = input.executeQuery();
                
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)});
                }

            conn.close();
            } catch (SQLException e) {
		System.out.println(e.getMessage());
            }
            return model;
        }
}