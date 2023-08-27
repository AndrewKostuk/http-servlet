package by.bsuir.andrei.http.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
