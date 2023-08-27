package by.bsuir.andrei.http.servlet;

import by.bsuir.andrei.http.dto.FlightDto;
import by.bsuir.andrei.http.service.FlightService;
import by.bsuir.andrei.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flights = flightService.getFlights();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher(JspHelper.getPath("flights")).forward(req, resp);
    }
}
