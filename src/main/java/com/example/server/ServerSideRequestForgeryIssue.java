package server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet(value = "/userProfile", name = "UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // <-- Taint source
      String user = req.getParameter("user"); // <-- Taint propagated from source
      String userId = user + "_id"; // <-- Taint propagated from above variable
      HttpRequest httpRequest = HttpRequest.newBuilder(userId).GET().build(); // <-- Taint sink
    }
}
