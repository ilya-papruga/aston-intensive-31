package ru.aston.university.repository;

import ru.aston.university.configuration.ConnectionFactory;
import ru.aston.university.dto.CourseNoId;
import ru.aston.university.entity.Course;
import ru.aston.university.repository.api.CourseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    private final static CourseRepositoryImpl instance = new CourseRepositoryImpl();


    private final static String SELECT_SQL =
            "SELECT\n" +
            "    id,\n" +
            "    \"title\"\n" +
            "FROM\n" +
            "    main.courses;\n";


    private final static String SELECT_MAX_ID_SQL =
            "SELECT\n" +
                    "    *\n" +
            "FROM\n" +
                    "    main.courses\n" +
            "WHERE\n" +
                    "    id = (\n" +
            "SELECT\n" +
            "MAX(id)\n" +
            "FROM\n" +
             "    main.courses);";

    private final static String INSERT_SQL =
            "INSERT INTO main.courses (\"title\")\n" +
            "    VALUES (?);";

    private static final String DELETE_SQL =
            "DELETE FROM main.courses\n" +
            "WHERE id = ?;";

    private static final String UPDATE_SQL =
            "UPDATE\n" +
            "    main.courses\n" +
            "SET\n" +
            "    \"title\" = ?\n" +
            "WHERE\n" +
            "    id = ?;";


    public List<Course> readAll() {

        List<Course> courseList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL)
        ) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    courseList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return courseList;
    }


    public String create(CourseNoId course) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)
        ) {
            statement.setString(1, course.getTitle());
            {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String idInfo = "Добавлен курс с id: ";

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

    public void update(CourseNoId course, Long id) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)
        ) {
            statement.setString(1, course.getTitle());
            statement.setLong(2, id);
            {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    private Course map(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setTitle(rs.getString("title"));
        return course;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static CourseRepositoryImpl getInstance() {
        return instance;
    }

}
