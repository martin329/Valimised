package com.valimised;

import java.io.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.appengine.api.rdbms.AppEngineDriver; 
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


/**
 * Servlet implementation class SuggestKeywordServlet
 */
public class SuggestKeywordServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuggestKeywordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	          throws IOException {	

	          PrintWriter out = response.getWriter();
	          Connection c = null;
	          java.util.List<String> kandidaadid = new ArrayList<String>();
	          String key = request.getParameter("foo");
	          
	          try {
	              DriverManager.registerDriver(new AppEngineDriver());
	              c = DriverManager.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB", "root", "");
	              String statement = "SELECT CONCAT(perenimi,', ', eesnimi) AS perenimi FROM isik WHERE perenimi LIKE '" + key + "%';";
	              System.out.println(statement);
	              
	              Statement stmt = c.createStatement();
	              ResultSet rs = stmt.executeQuery(statement);
	              
	              while (rs.next()) {
	  	    			kandidaadid.add(rs.getString("perenimi"));
	              }
	                           
			      String gson = new Gson().toJson(kandidaadid);
			      response.setContentType("application/json");
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