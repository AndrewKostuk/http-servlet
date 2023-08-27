package by.bsuir.andrei.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookiesServlet extends HttpServlet {

    private static final String COOKIE_NAME = "userId";
    private static final AtomicInteger COUNTER = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = req.getCookies();
        if (cookies == null || Arrays.stream(cookies)
                .filter(cookie -> COOKIE_NAME.equals(cookie.getName()))
                .findFirst().isEmpty()) {
            Cookie cookie = new Cookie(COOKIE_NAME, "0");
            cookie.setPath("/cookies");
            cookie.setMaxAge(2 * 60);
            resp.addCookie(cookie);
            COUNTER.incrementAndGet();
        }
        try (var writer = resp.getWriter()) {
            writer.write(String.valueOf(COUNTER.get()));
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }
}
