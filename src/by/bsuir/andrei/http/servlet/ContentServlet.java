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
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flightDtos = flightService.getFlights();
        req.setAttribute("flights", flightDtos);
        req.getSession().setAttribute("flightsMap",
                flightDtos.stream().collect(toMap(FlightDto::getId, FlightDto::getDescription)));
        var flightsMap = (Map<Integer, String>) req.getSession().getAttribute("flightsMap");
        System.out.println(flightsMap.get(1));
        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
