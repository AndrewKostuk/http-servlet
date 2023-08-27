package by.bsuir.andrei.http.dao;

import by.bsuir.andrei.http.entity.Gender;
import by.bsuir.andrei.http.entity.Role;
import by.bsuir.andrei.http.entity.User;
import by.bsuir.andrei.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password, role, gender, image)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT id, name, birthday, email, password, role, gender, image
            FROM users
            WHERE email=? AND password=?
            """;

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setDate(2, Date.valueOf(entity.getBirthday()));
            prepareStatement.setString(3, entity.getEmail());
            prepareStatement.setString(4, entity.getPassword());
            prepareStatement.setString(5, entity.getRole().name());
            prepareStatement.setString(6, entity.getGender().name());
            prepareStatement.setString(7, entity.getImage());
            prepareStatement.executeUpdate();
            var generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            }
            return entity;
        }
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {
            prepareStatement.setString(1, email);
            prepareStatement.setString(2, password);
            var resultSet = prepareStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role")))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .image(resultSet.getString("image"))
                .build();
    }
}
