package com.valimised;
import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class testServlet extends HttpServlet {
	private String partyName;
	private String candidate;
	private String region;
	private String type;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		Gson gson=new Gson();
		partyName=req.getParameter("erakond").trim();
		candidate = req.getParameter("nimi");
		region = req.getParameter("piirkond");
		type = req.getParameter("type");
		Connection c = null;
		try{
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection("jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB", "root", "");
			Statement stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("select Kandidaat.Id, Eesnimi, Perenimi from Kandidaat, Isik where Kandidaat.Partei=(select Id from Partei where Nimi='"+partyName+ "') and Kandidaat.Isik=Isik.Id" );
			Collection collection= new ArrayList();
			collection.add(rs.getObject(1));
			collection.add(rs.getObject(2));
			collection.add(rs.getObject(3));
			gson.toJson(collection);
			resp.setContentType("application/json");
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
