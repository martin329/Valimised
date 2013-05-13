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

public class CheckIfInIsik extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
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
			String eesnimi = req.getParameter("kasutajaE");
			String perenimi = req.getParameter("kasutajaP");
			long kandidaatId = Long.parseLong(req.getParameter("kasutajaId"));
			String cmnd = "Select * FROM isik WHERE id="+kandidaatId;
			rs = stmt.executeQuery(cmnd);
			
		

			if (rs.next()) {
				resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
			} else {
				String statement = "INSERT INTO isik(id,eesnimi,perenimi) VALUES(? , ? , ?)";
				PreparedStatement stmtnt = c.prepareStatement(statement);
				stmtnt.setLong(1, kandidaatId);
				stmtnt.setString(2, eesnimi);
				stmtnt.setString(3, perenimi);
				stmtnt.execute();
				resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
		} finally {
//			resp.setHeader("Refresh", "0; url=/html/haaletamine.html");
		}
	}

}
