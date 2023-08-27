package by.bsuir.andrei.http.dao;

import by.bsuir.andrei.http.entity.Ticket;
import by.bsuir.andrei.http.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Integer, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();
    private final FlightDao flightDao = FlightDao.getInstance();
    private static final String SELECT_ALL_BY_FLIGHT_ID = """
            SELECT id,
                   passenger_no,
                   passenger_name,
                   flight_id,
                   seat_no,
                   cost
            FROM ticket
            WHERE flight_id = ?
            """;

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private TicketDao() {
    }

    public List<Ticket> findAllByFlightId(Integer flightId) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SELECT_ALL_BY_FLIGHT_ID)) {
            List<Ticket> tickets = new ArrayList<>();
            prepareStatement.setInt(1, flightId);
            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getInt("id"),
                resultSet.getString("passenger_no"),
                resultSet.getString("passenger_name"),
                flightDao.findById(resultSet.getInt("flight_id"),
                        resultSet.getStatement().getConnection()).orElse(null),
                resultSet.getString("seat_no"),
                resultSet.getBigDecimal("cost")
        );
    }
}
