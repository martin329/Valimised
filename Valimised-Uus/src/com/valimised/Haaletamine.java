package com.valimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.rdbms.AppEngineDriver;

public class Haaletamine extends HttpServlet {
	private int haaletaja;
	private int kandidaat;
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		haaletaja = Integer.parseInt(req.getParameter("haaletaja"));
		kandidaat = Integer.parseInt(req.getParameter("kandidaat"));
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			String statement = "INSERT INTO haal(haaletaja,kandidaat,aeg) VALUES(? , ? , ?)";
			PreparedStatement stmt = c.prepareStatement(statement);
			
			stmt.setLong(1, haaletaja);
			stmt.setLong(2, kandidaat);
			java.util.Date date= new java.util.Date();
			stmt.setTimestamp(3, new Timestamp(date.getTime()));

			stmt.execute();
			
		} catch (SQLException e) {
			resp.setHeader("Refresh",
					"0; url=/html/haaletamiseTagasisideError.html");
		} finally {
			resp.setHeader("Refresh",
					"0; url=/html/haaletamiseTagasiside.html");
		}
	}
}
