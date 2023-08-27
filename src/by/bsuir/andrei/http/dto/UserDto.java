package by.bsuir.andrei.http.dto;

import by.bsuir.andrei.http.entity.Gender;
import by.bsuir.andrei.http.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {

    Long id;
    String email;
    String name;
    LocalDate birthday;
    Role role;
    Gender gender;
    String image;
}
