package com.valimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.*;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Kandideeri_testServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager
					.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB");
			String fname = req.getParameter("Erakond");
			String content = req.getParameter("Piirkond");
			

			if (fname == "" || content == "") {
				out.println("<html><head></head><body>You are missing either a message or a name! Try again! Redirecting in 3 seconds...</body></html>");
			} else {
				String statement = "INSERT INTO kandidaat VALUES( ? , ? , ? , ?)";
				PreparedStatement stmt = c.prepareStatement(statement);
				stmt.setLong(1, 11);
				stmt.setLong(2, 2);
				stmt.setLong(3, 3);
				stmt.setLong(4, 4);
				int success = 2;
				success = stmt.executeUpdate();
				if (success == 1) {
					// out.println("<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
				} else if (success == 0) {
					// out.println("<html><head></head><body>Failure! Please try again! Redirecting in 3 seconds...</body></html>");
				}
			}
		} catch (SQLException e) {
			out.println("<html><head></head><body>Olete juba kandidaadiks lisatud</body></html>");
			resp.setHeader("Refresh", "3; url=/html/lisamiseTagasisideError.html");
			return;
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (SQLException ignore) {
				}
		}
		resp.setHeader("Refresh", "3; url=/html/lisamiseTagasiside.html");
	}
}
