package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getDbConnection() {
        Connection conn = null;
        try {
            //獲取連接的基本資料
            String url = "jdbc:mysql://localhost:3306/evancebookstore";
            String user = "root";
            String password = "1234";

            //獲取driver的對象
            Class.forName("com.mysql.cj.jdbc.Driver");
            //獲取連結
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("已連接資料庫");
        System.out.println(conn);

        return conn;
    }



    public static void closeResource(Connection conn, PreparedStatement ps){
        //資源關閉
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    };

    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs){
        //資源關閉
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

}
