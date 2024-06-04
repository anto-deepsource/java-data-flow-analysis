package server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(value = "/userLogin", name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // <-- Taint source
        String user = req.getParameter("user"); // <-- Taint propagated from source
        String query = "SELECT * FROM users WHERE user = " + user; // <-- Taint propagated from the above variable

        Connection connection = DriverManager.getConnection("jdbc:yourdatabaseurl", "username", "password");
        PreparedStatement statement = connection.prepareStatement(query); // <-- Taint sink
    }
}
