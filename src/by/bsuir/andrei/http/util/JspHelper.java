package by.bsuir.andrei.http.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String TEMPLATE = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String jsp) {
        return TEMPLATE.formatted(jsp);
    }
}
