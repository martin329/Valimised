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

public class CheckIfCanVote extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			stmt=c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM haal WHERE haaletaja=20");

			if (rs.next()) {
				resp.setHeader("Refresh", "0; url=/html/haaleKustutamine.html");
			} else {
				resp.setHeader("Refresh", "0; url=/html/haaletamine.html");
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
		} finally {
//			resp.setHeader("Refresh", "0; url=/html/haaletamine.html");
		}
	}

}
