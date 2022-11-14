package jdbc;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEx {
    private static final String URL = "jdbc:mysql://localhost:3306/education";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            System.out.println("Не удалось загрузить класс драйвера");
        }
        try (Connection connection = DriverManager
                .getConnection(URL,USERNAME,PASSWORD);
             Statement statement = connection.createStatement()) {
//            statement.execute("INSERT INTO students" +
//                    "(age, avgGrade, course, name, sex)" +
//                    " VALUES (22, 3.99, 4, 'Igor', 'm')");
//            statement.executeUpdate("UPDATE students set avgGrade = 4.2 where id = 9;");
//            ResultSet resultSet = statement.executeQuery("Select * from students;");
//            statement.addBatch("INSERT INTO students" +
//                    "(age, avgGrade, course, name, sex)" +
//                    " VALUES (22, 3.88, 4, 'Grisha', 'm')");
//            statement.addBatch("INSERT INTO students" +
//                    "(age, avgGrade, course, name, sex)" +
//                    " VALUES (22, 4.75, 4, 'Mark', 'm')");
//            statement.addBatch("INSERT INTO students" +
//                    "(age, avgGrade, course, name, sex)" +
//                    " VALUES (22, 3.76, 4, 'Gleb', 'm')");
//            statement.executeBatch();
//            statement.clearBatch();
//            boolean status = statement.isClosed();
//            System.out.println(status);
            ResultSet resultSet = statement.executeQuery("Select * from students where id >4;");
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                int course = resultSet.getInt("course");
                double avgGrade = resultSet.getDouble("avgGrade");
                studentList.add(new Student(id, name, age, sex.toCharArray()[0], course, avgGrade));
            }
            for(Student s: studentList)
                System.out.println(s);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
class Student{
    private int id;
    private String name;
    private int age;
    private char sex;
    private int course;
    private double avgGrade;

    public Student(int id, String name, int age, char sex, int course, double avgGrade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.course = course;
        this.avgGrade = avgGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", course=" + course +
                ", avgGrade=" + avgGrade +
                '}';
    }
}