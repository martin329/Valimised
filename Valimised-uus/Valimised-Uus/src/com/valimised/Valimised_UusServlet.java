package com.valimised;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Valimised_UusServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
              throws IOException {
        
    	Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        resp.setContentType("text/html");
        
        try {
        	con = DriverManager.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB","root", "");
        	stmt = con.createStatement();
        	rs = stmt.executeQuery("SELECT NAME, PHONE FROM EMPLOYEES");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	
        }
    	
    }
}
