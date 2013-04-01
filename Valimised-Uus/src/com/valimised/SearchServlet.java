package com.valimised;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager
					.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB");
			String ringkond = req.getParameter("Ringkond");
			Gson gson = new Gson();

			if (ringkond == "") {
				out.println("<html><head></head><body>You are missing either a message or a name! Try again! Redirecting in 3 seconds...</body></html>");
			} else if (ringkond.equals("Kogu Eesti")) {
				ResultSet rs = c.createStatement().executeQuery(
						"SELECT * FROM isik");

			} else {
				ResultSet rs = c.createStatement().executeQuery(
						"SELECT * FROM isik where id='1'");
				String statement = "INSERT INTO entries (guestName, content) VALUES( ? , ? )";
				Collection col = new ArrayList();
				col.add(rs.getObject(1));
				col.add(rs.getObject(2));
				col.add(rs.getObject(3));

				gson.toJson(col);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException ignore) {

				}
			}
		}

	}
}
