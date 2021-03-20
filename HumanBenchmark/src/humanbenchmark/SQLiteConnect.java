package humanbenchmark;

import java.sql.*;
import java.util.ArrayList;


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
            System.out.println("bryh");
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
	public void updateStudent(String name, ArrayList<String> info) {
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
	public void addStudent(String name, ArrayList<String> info) {
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
	 * Deletes a student from this plane of existence
	 * @param name
	 */
	public void deleteStudent(String name) {
            String sql = "DELETE FROM Schedules WHERE Name = ?";
            try {
		PreparedStatement input = connect("StudentSchedule.db").prepareStatement(sql);
                input.setString(1, name);
                input.executeUpdate();
		conn.close();
            } catch (SQLException e) {
		System.out.println(e.getMessage());
            }
	}

        /**
	 * Get student info
         * @param name
         * @return
	 */
	public ArrayList<String> getStudent(String name) {
            String sql = "SELECT Name, Subject1, Subject2, Subject3, Subject4,"
			+ " Subject5, Subject6, Subject7, Subject8, Subject9 "
                        + " FROM Schedules WHERE Name == ?";
            ArrayList<String> info = new ArrayList<>();
            try {
		PreparedStatement input = connect("StudentSchedule.db").prepareStatement(sql);
		input.setString(1, name);
                        
            ResultSet rs = input.executeQuery();
            info.add(rs.getString("Name"));
                        
            for (int i = 1; i < 10; i++) {
                info.add(rs.getString("Subject" + String.valueOf(i)));
            }

            conn.close();
            } catch (SQLException e) {
		System.out.println(e.getMessage());
            }
            return info;
        }
}