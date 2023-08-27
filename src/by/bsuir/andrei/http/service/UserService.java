package by.bsuir.andrei.http.service;

import by.bsuir.andrei.http.dao.UserDao;
import by.bsuir.andrei.http.dto.CreateUserDto;
import by.bsuir.andrei.http.dto.UserDto;
import by.bsuir.andrei.http.exception.ValidationException;
import by.bsuir.andrei.http.mapper.CreateUserMapper;
import by.bsuir.andrei.http.mapper.UserMapper;
import by.bsuir.andrei.http.validator.CreateUserValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Long create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        var user = createUserMapper.mapFrom(userDto);
        imageService.upload(user.getImage(), userDto.getImage().getInputStream());
        var save = userDao.save(user);
        return save.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
