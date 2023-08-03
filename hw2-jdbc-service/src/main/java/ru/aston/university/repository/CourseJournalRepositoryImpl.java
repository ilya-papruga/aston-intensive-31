package ru.aston.university.repository;

import ru.aston.university.configuration.ConnectionFactory;
import ru.aston.university.dto.CourseDto;
import ru.aston.university.dto.CourseJournalDto;
import ru.aston.university.dto.StudentCoursesDto;
import ru.aston.university.entity.Course;
import ru.aston.university.entity.Student;
import ru.aston.university.repository.api.CourseJournalRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseJournalRepositoryImpl implements CourseJournalRepository {

    private final static CourseJournalRepositoryImpl instance = new CourseJournalRepositoryImpl();

    private final static String SELECT_JOIN_SQL_ALL =
            "SELECT\n" +
            "    courses.\"title\",\n" +
            "    name,\n" +
            "    age,\n" +
            "    score,\n" +
            "    olympic_gamer\n" +
            "FROM\n" +
            "    main.students\n" +
            "    JOIN main.cross_courses_students ON students.id = student_id\n" +
            "    JOIN main.courses ON courses.id = course_id\n"+
            "ORDER BY courses.\"title\", name;";

    private final static String SELECT_JOIN_SQL_ONE_COURSE =
            "SELECT\n" +
            "    students.id," +
            "    name,\n" +
            "    age,\n" +
            "    score,\n" +
            "    olympic_gamer\n" +
            "FROM\n" +
            "    main.students\n" +
            "    JOIN main.cross_courses_students ON students.id = student_id\n" +
            "    JOIN main.courses ON courses.id = course_id\n"+
            "WHERE courses.\"title\" = ?\n" +
             "ORDER BY name;";

    private final static String SELECT_JOIN_SQL_ONE_STUDENT =
            "SELECT\n" +
            "    courses.id," +
            "    courses.title\n" +
            "FROM\n" +
            "    main.courses\n" +
            "    JOIN main.cross_courses_students ON courses.id = course_id\n" +
            "    JOIN main.students ON students.id = student_id\n"+
            "WHERE students.\"name\" = ?\n" +
             "ORDER BY courses.title;";


     public List<CourseJournalDto> readAll() {

        List<CourseJournalDto> journalList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_JOIN_SQL_ALL)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    journalList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return journalList;
    }

    public CourseDto readCourseStudents(String title) {

         CourseDto course = new CourseDto(title);
         List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_JOIN_SQL_ONE_COURSE)
        ) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    students.add(mapStudent(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        course.setStudentList(students);
        return course;
    }

    @Override
    public StudentCoursesDto readStudentCourses(String name) {

        StudentCoursesDto student = new StudentCoursesDto(name);
        List<Course> courses = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_JOIN_SQL_ONE_STUDENT)
        ) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    courses.add(mapCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        student.setCoursesList(courses);
        return student;
    }

    private CourseJournalDto map(ResultSet rs) throws SQLException {
        CourseJournalDto journal = new CourseJournalDto();
        journal.setTitle(rs.getString("title"));
        journal.setName(rs.getString("name"));
        journal.setAge(rs.getInt("age"));
        journal.setScore(rs.getDouble("score"));
        journal.setOlympicGamer(rs.getBoolean("olympic_gamer"));
        return journal;
    }


    private Student mapStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympic_gamer"));
        return student;
    }

    private Course mapCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setTitle(rs.getString("title"));
        return course;
    }


    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static CourseJournalRepositoryImpl getInstance() {
        return instance;
    }
}

