package by.bsuir.andrei.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/flights");


//        req.getRequestDispatcher("/flights").forward(req, resp);
//
//        try (var writer = resp.getWriter()) {
//            writer.write("abcd");
//        }

//        req.getRequestDispatcher("/flights").include(req, resp);
//
//        try (var writer = resp.getWriter()) {
//            writer.write("abcd");
//        }
    }
}
