package by.bsuir.andrei.http.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
