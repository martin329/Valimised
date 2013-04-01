package com.valimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.rdbms.AppEngineDriver;

public class HaaleKustutamine extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Connection c = null;
		try {
			
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			String statement = "DELETE FROM haal WHERE haaletaja=20";
			PreparedStatement stmt = c.prepareStatement(statement);

			stmt.execute();

		} catch (SQLException e) {
			// e.printStackTrace();
			resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
		} finally {
			resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
		}
	}

}
