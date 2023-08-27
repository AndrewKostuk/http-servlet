package by.bsuir.andrei.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.bsuir.andrei.http.util.UrlPath.LOGIN;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var lang = req.getParameter("lang");
        req.getSession().setAttribute("lang", lang);
        var previousURI = req.getHeader("referer");
        previousURI = previousURI == null ? LOGIN : previousURI;
        String pathToRedirect = previousURI + "?lang=" + lang;
        resp.sendRedirect(pathToRedirect);
    }
}
