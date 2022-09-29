package rikkei.academy.service;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService{
    private Connection connection = ConnectMySQL.getConnection();
    private static final String STUDENT_LIST = "select * from students;";

    private static final String CREATE_STUDENT = "insert into students(name,age) values(?,?);";
    @Override
    public List<Students> findAll() {
        List<Students> studentsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STUDENT_LIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Students students = new Students(id, name, age);
                studentsList.add(students);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentsList;
    }

    @Override
    public void save(Students students) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENT);
            preparedStatement.setString(1, students.getName());
            preparedStatement.setInt(2, students.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Students findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Students> findByName(String name) {
        return null;
    }
}
