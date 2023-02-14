package connect;

import java.sql.*;

public class Database {
    public static Connection con = null;
    private static Database instance = new Database();

    public static Database getInstance() {
//        if (instance == null)
//            instance = new Database();
        return instance;
    }

    /**
     * Kết nối database
     * @throws SQLException
     */
    public void connect(){
        String severName = "localhost";
        String databaseName = "QL_KARAOKE_NICE";
        String username = "sa";
        String password = "123456";
        String url = "jdbc:sqlserver://" + severName + ":1433;databaseName=" + databaseName;
        try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Ngắt kết nối database
     */
    public void disconnect() {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * Lấy ra Connection đang kết nối đến database
     * @return Connection
     */
    public static Connection getConnection() {
        return con;
    }
}
