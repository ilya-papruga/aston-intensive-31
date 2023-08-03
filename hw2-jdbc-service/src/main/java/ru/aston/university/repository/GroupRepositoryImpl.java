package ru.aston.university.repository;

import ru.aston.university.dto.GroupNoId;
import ru.aston.university.entity.Group;
import ru.aston.university.repository.api.GroupRepository;
import ru.aston.university.configuration.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryImpl implements GroupRepository {

    private final static GroupRepositoryImpl instance = new GroupRepositoryImpl();


    private final static String SELECT_SQL =
            "SELECT\n" +
            "    id,\n" +
            "    \"number\"\n" +
            "FROM\n" +
            "    main.groups;\n";


    private final static String SELECT_MAX_ID_SQL =
            "SELECT\n" +
                    "    *\n" +
            "FROM\n" +
                    "    main.groups\n" +
            "WHERE\n" +
                    "    id = (\n" +
            "SELECT\n" +
            "MAX(id)\n" +
            "FROM\n" +
             "    main.groups);";

    private final static String INSERT_SQL =
            "INSERT INTO main.groups (\"number\")\n" +
            "    VALUES (?);";

    private static final String DELETE_SQL =
            "DELETE FROM main.groups\n" +
            "WHERE id = ?;";

    private static final String UPDATE_SQL =
            "UPDATE\n" +
            "    main.groups\n" +
            "SET\n" +
            "    \"number\" = ?\n" +
            "WHERE\n" +
            "    id = ?;";


    public List<Group> readAll() {

        List<Group> groupList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL)
        ) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    groupList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return groupList;
    }


    public String create(GroupNoId group) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)
        ) {
            statement.setString(1, group.getNumber());
            {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String idInfo = "Добавлена группа с id: ";

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

    public void update(GroupNoId group, Long id) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)
        ) {
            statement.setString(1, group.getNumber());
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

    private Group map(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setNumber(rs.getString("number"));
        return group;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static GroupRepositoryImpl getInstance() {
        return instance;
    }

}
