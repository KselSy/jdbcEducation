package jdbc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class JdbcEx3 {

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
             Statement statement = connection.createStatement()) {
            statement.execute("drop table if exists Books");
            statement.executeUpdate("create table if not exists Books (id MEDIUMINT not null auto_increment, name CHAR(30) not null, img BLOB, primary key (id))");
            BufferedImage image = ImageIO.read(new File("C:\\Users\\branh\\IdeaProjects\\jdbcEducation\\src\\main\\java\\jdbc\\smile.jpg"));
            Blob blob = connection.createBlob();
            try (OutputStream outputStream = blob.setBinaryStream(1)) {
                ImageIO.write(image, "jpg", outputStream);
            }
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Books (name, img) values (?,?)");
            preparedStatement.setString(1, "cooman");
            preparedStatement.setBlob(2, blob);
            preparedStatement.execute();

            ResultSet resultSet = statement.executeQuery("Select * from books");
            while (resultSet.next()){
                Blob blob1 = resultSet.getBlob("img");
                BufferedImage image1 = ImageIO.read(blob1.getBinaryStream());
                File outputFile = new File("saved.png");
                ImageIO.write(image1, "png", outputFile);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
