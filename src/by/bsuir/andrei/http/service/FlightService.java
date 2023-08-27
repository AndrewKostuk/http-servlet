package by.bsuir.andrei.http.service;

import by.bsuir.andrei.http.dao.FlightDao;
import by.bsuir.andrei.http.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    public static FlightService getInstance() {
        return INSTANCE;
    }

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> getFlights() {
        return flightDao.findAll().stream()
                .map(flight -> {
                    Integer id = flight.getId();
                    String description = """
                            from %s to %s status %s
                            """.formatted(flight.getDepartureAirportCode(),
                            flight.getArrivalAirportCode(),
                            flight.getStatus().name());
                    return FlightDto.builder().id(id).description(description).build();
                }).collect(toList());
    }
}
