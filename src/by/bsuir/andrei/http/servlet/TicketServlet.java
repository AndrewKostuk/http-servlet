package by.bsuir.andrei.http.servlet;

import by.bsuir.andrei.http.service.TicketService;
import by.bsuir.andrei.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer flightId = Integer.valueOf(req.getParameter("flightId"));
        req.setAttribute("tickets", ticketService.getTicketsByFlightId(flightId));
        req.setAttribute("flightId", flightId);
        req.getRequestDispatcher(JspHelper.getPath("tickets")).forward(req, resp);
    }
}
