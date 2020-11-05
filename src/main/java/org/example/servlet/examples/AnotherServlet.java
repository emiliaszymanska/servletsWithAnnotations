package org.example.servlet.examples;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "anotherServlet", urlPatterns = {"/another"}, loadOnStartup = 2)
public class AnotherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append("<div>");
            buffer.append("<a href=\"/another?link_id=" + i + "\">");
            buffer.append("Hello " + i + ". link");
            buffer.append("</a>");
            buffer.append("</div>");
        }
        System.out.println(buffer);

        String linkId = request.getParameter("link_id");
        System.out.println(linkId);

        out.println(
                "<html>\n" +
                        "<head><title>Another page</title></head>\n" +
                        "<body>\n" +
                        "<h1>Hello!</h1>" +
                        (linkId == null ?
                                "<h3>No link was clicked</h3>" :
                                "<h3>Link " + linkId + " was clicked!</h3>") +
                        "<br/>" +
                        "<div>" + buffer.toString() + "</div>" +
                        "</body></html>"
        );
    }
}
