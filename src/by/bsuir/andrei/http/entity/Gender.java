package by.bsuir.andrei.http.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Gender {

    MALE,
    FEMALE;

    public static Optional<Gender> find(String name) {
        return Arrays.stream(values()).filter(gender -> Objects.equals(gender.name(), name))
                .findFirst();
    }
}
