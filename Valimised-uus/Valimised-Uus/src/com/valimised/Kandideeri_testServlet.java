package com.valimised;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			
			long kandidaadiId = Long.parseLong(req.getParameter("kasutajaID"));
			
			String statement1 = "DELETE FROM kandidaat WHERE isik="+kandidaadiId;
			PreparedStatement pstmt1 = c.prepareStatement(statement1);
			pstmt1.execute();
			
			String statement = "INSERT INTO kandidaat(isik,erakond,piirkond) VALUES(? , ? , ?)";
			PreparedStatement stmt = c.prepareStatement(statement);
			Statement stmt2 = c.createStatement();

			String erakond = req.getParameter("Erakond");
			String piirkond = req.getParameter("Piirkond");
			

			int erakond_id;
			String statementForErakond = "SELECT id FROM erakond WHERE nimi LIKE '"+erakond+"'";
			Statement pstmtForErakond = c.prepareStatement(statementForErakond);
			ResultSet rsForErakond = pstmtForErakond.executeQuery(statementForErakond);
			rsForErakond.next();
			erakond_id = rsForErakond.getInt("id");

			
			int piirkond_id;
			String statementForPiirkond = "SELECT id FROM piirkond WHERE nimi LIKE '"+piirkond+"'";
			Statement pstmtForPiirkond = c.prepareStatement(statementForPiirkond);
			ResultSet rsForPiirkond = pstmtForPiirkond.executeQuery(statementForPiirkond);
			rsForPiirkond.next();
			piirkond_id = rsForPiirkond.getInt("id");
			

			stmt.setLong(1, kandidaadiId);
			stmt.setLong(2, erakond_id);
			stmt.setLong(3, piirkond_id);
			int success = 2;
			success = stmt.executeUpdate();
			if (success == 1) {
				// out.println("<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
			} else if (success == 0) {
				// out.println("<html><head></head><body>Failure! Please try again! Redirecting in 3 seconds...</body></html>");
			}

		} catch (SQLException e) {
			out.println(e.getMessage());
			resp.setHeader("Refresh",
					"5; url=/html/lisamiseTagasisideError.html");
			return;
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (SQLException ignore) {
				}
		}
		resp.setHeader("Refresh", "0; url=/html/lisamiseTagasiside.html");
	}
}
