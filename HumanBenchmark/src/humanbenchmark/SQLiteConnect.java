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
	 * Updates a student's schedule
	 * @param info
         * @param name
	 */
	public void updateReading(String name, ArrayList<String> info) {
            String sql = "UPDATE Schedules SET"
			+ " Subject1 = ? ,"
			+ " Subject2 = ? ,"
			+ " Subject3 = ? ,"
			+ " Subject4 = ? ,"
			+ " Subject5 = ? ,"
			+ " Subject6 = ? ,"
                        + " Subject7 = ? ,"
                        + " Subject8 = ? ,"
                        + " Subject9 = ?  "
                        + " WHERE Name = ?";
            try {
		PreparedStatement input = connect("StudentSchedule.db").prepareStatement(sql);
                input.setString(1,name);
                for (int i = 1; i < info.size(); i++) {
                    input.setString(i+1, info.get(i));
                }
		input.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}
	/**
	 * Creates a new student from the database
	 * @param info
         * @param name
	 */
	public void addTest(String name, ArrayList<String> info) {
            String sql = "INSERT INTO Schedules(Name, Subject1, Subject2,"
			+ " Subject3, Subject4, Subject5, Subject6, Subject7,"
                        + " Subject8, Subject9) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try {
		PreparedStatement input = connect("StudentSchedule.db").prepareStatement(sql);
                for (int i = 0; i < info.size(); i++) {
                    input.setString(i+1, info.get(i));
                }
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
            String sql = "SELECT ID, Reading, Reaction, Date";
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Reading");
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