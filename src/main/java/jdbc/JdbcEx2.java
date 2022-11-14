package jdbc;

import java.sql.*;

public class JdbcEx2 {

    private static final String URL = "jdbc:mysql://localhost:3306/education";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into students(age,avgGrade,course,name,sex) values (?,?,?,?,?)");
            preparedStatement.setInt(1,19);
            preparedStatement.setDouble(2,4.67);
            preparedStatement.setInt(3,1);
            preparedStatement.setString(4,"Nina");
            preparedStatement.setString(5,"f");
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
