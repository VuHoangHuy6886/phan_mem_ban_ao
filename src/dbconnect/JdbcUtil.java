/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

    public final static String USERNAME = "sa";
    public final static String PASSWORD = "123";
    public final static String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=db_duan_ban_ao;encrypt=false;";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cn;
    }

    public static void closeConnection(Connection cn) {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Connect Database successfully");
        } else {
            System.out.println("Connect Database failure");
        }
        closeConnection(connection);
    }
}
