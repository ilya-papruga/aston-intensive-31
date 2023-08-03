package ru.aston.university.repository;

import ru.aston.university.configuration.ConnectionFactory;
import ru.aston.university.dto.CrossCourseStudentDto;
import ru.aston.university.dto.CrossCourseStudentDtoGet;
import ru.aston.university.repository.api.CrossCourseStudentRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrossCourseStudentRepoImpl implements CrossCourseStudentRepo {

    private final static CrossCourseStudentRepoImpl instance = new CrossCourseStudentRepoImpl();

    private final static String SELECT_SQL =
            "SELECT\n" +
            "    course_id,\n" +
            "    student_id\n" +
            "FROM\n" +
            "    main.cross_courses_students;";

    private final static String INSERT_SQL =
            "INSERT INTO main.cross_courses_students" +
            " (course_id, student_id)\n" +
            "    VALUES (?, ?);";

    private final static String DELETE_SQL =
            "DELETE FROM main.cross_courses_students\n" +
            "WHERE course_id = ?\n" +
            "AND student_id = ?;";



    public List<CrossCourseStudentDtoGet> readAll() {

        List<CrossCourseStudentDtoGet> crossList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    crossList.add(mapCross(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return crossList;
    }


      public void create(CrossCourseStudentDto cross) {
          for (int i = 0; i < cross.getCourseId().size(); i++) {
              for (int k = 0; k < cross.getStudentId().size(); k++) {
                  try (Connection connection = ConnectionFactory.getConnection();
                       PreparedStatement statement = connection.prepareStatement(INSERT_SQL)
                  ) {

                      statement.setLong(1, cross.getCourseId().get(i));
                      statement.setLong(2, cross.getStudentId().get(k));

                      {
                          statement.executeUpdate();
                      }
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
              }
          }
    }

    public void delete(CrossCourseStudentDto cross) {
          for (int i = 0; i < cross.getCourseId().size(); i++) {
              for (int k = 0; k < cross.getStudentId().size(); k++) {
                  try (Connection connection = ConnectionFactory.getConnection();
                       PreparedStatement statement = connection.prepareStatement(DELETE_SQL)
                  ) {
                      statement.setLong(1, cross.getCourseId().get(i));
                      statement.setLong(2, cross.getStudentId().get(k));

                      {
                          statement.executeUpdate();
                      }
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
              }
          }
    }

    private CrossCourseStudentDtoGet mapCross(ResultSet rs) throws SQLException {
        CrossCourseStudentDtoGet cross = new CrossCourseStudentDtoGet();
        cross.setCourseId(rs.getLong("course_id"));
        cross.setStudentId(rs.getLong("student_id"));
        return cross;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static CrossCourseStudentRepoImpl getInstance() {
        return instance;
    }
}
