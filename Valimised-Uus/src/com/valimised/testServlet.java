package com.valimised;
import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class testServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		PrintWriter out = resp.getWriter();
		Connection c = null;
		try{
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB", "root", "");
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM erakond");
			rs.next();
			out.println(rs.getString("nimi"));
			out.println("Midagi saab andmebaasist kätte");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) 
				try {
					c.close();
				} catch (SQLException ignore) {
				}
		}
		resp.setHeader("Refresh","10; url=/html/index.html");
	}

}
