package ru.aston.university.repository;

import ru.aston.university.dto.StudentNoId;
import ru.aston.university.entity.Student;
import ru.aston.university.repository.api.StudentRepository;
import ru.aston.university.configuration.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final static StudentRepositoryImpl instance = new StudentRepositoryImpl();

    private final static String SELECT_SQL =
                    "SELECT\n" +
                    "    id,\n" +
                    "    name,\n" +
                    "    age,\n" +
                    "    score,\n" +
                    "    olympic_gamer\n" +
                    "FROM\n" +
                    "    main.students;";
    private final static String SELECT_MAX_ID_SQL =
            "SELECT\n" +
                    "    *\n" +
                    "FROM\n" +
                    "    main.students\n" +
                    "WHERE\n" +
                    "    id = (\n" +
                    "        SELECT\n" +
                    "            max(id)\n" +
                    "        FROM\n" +
                    "            main.students);";

    private final static String INSERT_SQL = "INSERT INTO main.students (name, age, score, olympic_gamer)\n" +
            "    VALUES (?, ?, ?, ?);\n";

    private final static String DELETE_SQL = "DELETE FROM main.students\n" +
            "WHERE id = ?;";

    private final static String UPDATE_SQL = "UPDATE\n" +
            "    main.students\n" +
            "SET\n" +
            "    name = ?,\n" +
            "    age = ?,\n" +
            "    score = ?,\n" +
            "    olympic_gamer = ?\n" +
            "WHERE\n" +
            "    id = ?;\n";


    public List<Student> readAll() {

        List<Student> studentList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL)
        ) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    studentList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return studentList;
    }

    public String create(StudentNoId student) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String idInfo = "Добавлен студент с id: ";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MAX_ID_SQL)
        ) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    idInfo = idInfo + resultSet.getInt("id");

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return idInfo;

    }

    public void delete(Long id) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)
        ) {
            statement.setLong(1, id);
            {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(StudentNoId student, Long id) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            statement.setLong(5, id);
            {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private Student map(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympic_gamer"));
        return student;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static StudentRepositoryImpl getInstance() {
        return instance;
    }


}
