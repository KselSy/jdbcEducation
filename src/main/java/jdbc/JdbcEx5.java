package jdbc;

import java.sql.*;

public class JdbcEx5 {

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
            CallableStatement callableStatement = connection.prepareCall("{call getCount()}");
            boolean hasResult = callableStatement.execute();
            while (hasResult){
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()){
                    System.out.println(resultSet.getInt(1));
                }
                hasResult = callableStatement.getMoreResults();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
