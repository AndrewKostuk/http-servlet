package by.bsuir.andrei.http.mapper;

import by.bsuir.andrei.http.dto.CreateUserDto;
import by.bsuir.andrei.http.entity.Gender;
import by.bsuir.andrei.http.entity.Role;
import by.bsuir.andrei.http.entity.User;
import by.bsuir.andrei.http.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "users/";

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .gender(Gender.valueOf(object.getGender()))
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
