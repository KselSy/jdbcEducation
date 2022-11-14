package jdbc;

import java.sql.*;

public class JdbcEx6 {

    private static final String URL = "jdbc:mysql://localhost:3306/education";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection =
                     DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
