package com.valimised;

import java.io.*;
import javax.servlet.http.*;

import com.google.appengine.api.rdbms.AppEngineDriver; 
import java.io.IOException;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class SuggestKeywordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	          throws IOException {

	          PrintWriter out = resp.getWriter();
	          Connection c = null;
	            try {
	              DriverManager.registerDriver(new AppEngineDriver());
	              String lname = req.getParameter("lname");
	              String fname = req.getParameter("fname");
	              c = DriverManager.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB", "root", "");
	              String statement = "SELECT eesnimi, perenimi FROM kandidaat WHERE eesnimi LIKE ?";
	              PreparedStatement stmt = c.prepareStatement(statement);
	              stmt.setString(1, fname + "%");
	              stmt.setString(1, lname + "%");
	           
	              
			      String gson = new Gson().toJson("");
			      resp.setContentType("application/json");
			      out.write(gson);
			      out.flush();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			          try {
			            c.close();
			            } catch (SQLException ignore) {
			            	System.out.println(ignore.getMessage());
			            }
			    }
	}
}