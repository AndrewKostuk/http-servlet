package by.bsuir.andrei.http.service;

import by.bsuir.andrei.http.dao.TicketDao;
import by.bsuir.andrei.http.dto.TicketDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> getTicketsByFlightId(Integer flightId) {
        var allByFlightId = ticketDao.findAllByFlightId(flightId);
        return allByFlightId.stream()
                .map(ticket -> {
                    Integer id = ticket.getId();
                    String description = """
                            %s %s %s
                            """.formatted(ticket.getPassengerNo(),
                            ticket.getPassengerName(),
                            ticket.getSeatNo());
                    return TicketDto.builder().id(id).description(description).build();
                }).collect(toList());
    }
}
