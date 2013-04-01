package com.valimised;

import java.io.*;
import javax.servlet.*;
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
	              c = DriverManager.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB", "root", "");
	            
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