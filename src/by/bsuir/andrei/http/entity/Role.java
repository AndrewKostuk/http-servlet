package by.bsuir.andrei.http.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Role {

    USER,
    ADMIN;

    public static Optional<Role> find(String name) {
        return Arrays.stream(values()).filter(role -> Objects.equals(role.name(), name))
                .findFirst();
    }
}
