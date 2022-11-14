package jdbc;

import java.sql.*;

public class JdbcEx4 {

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
            CallableStatement callableStatement = connection.prepareCall("{call getBook(?)}");
            callableStatement.setInt(1,3);
            if (callableStatement.execute()){
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()){
                    System.out.println(resultSet.getString("name"));
                }
            }

            CallableStatement callableStatement1 = connection.prepareCall("{call BooksCount(?)}");
            callableStatement1.registerOutParameter(1, Types.INTEGER);
            callableStatement1.execute();
            System.out.println("Колличество книг = " + callableStatement1.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
