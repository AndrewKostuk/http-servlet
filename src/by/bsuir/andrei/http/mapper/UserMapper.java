package by.bsuir.andrei.http.mapper;

import by.bsuir.andrei.http.dto.UserDto;
import by.bsuir.andrei.http.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .email(object.getEmail())
                .name(object.getName())
                .birthday(object.getBirthday())
                .role(object.getRole())
                .gender(object.getGender())
                .image(object.getImage())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
