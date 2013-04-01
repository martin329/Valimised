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
	private int tuup;
	int erakond_id = 0;
	int piirkond_id = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String json ="";
		Gson gson = new Gson();
		partyName = req.getParameter("Erakond");
		erakond_id = UtilitiesServlet.getErakondId(partyName);
		candidate = req.getParameter("Text1");
		region = req.getParameter("Ringkond");
		piirkond_id=UtilitiesServlet.getPiirkondId(region);
		tuup = Integer.parseInt(req.getParameter("type"));
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		

		try {
			DriverManager.registerDriver(new AppEngineDriver());
			con = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			stmt = con.createStatement();
			if (this.tuup == 1){
				rs = stmt.executeQuery("SELECT kandidaat.id, eesnimi, perenimi, piirkond.nimi FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id, piirkond WHERE kandidaat.piirkond=piirkond.id && kandidaat.erakond="
						+ erakond_id +";");
				Kandidaadid kandidaadid = new Kandidaadid(new ArrayList<Kandidaat>());
				while(rs.next()){
					
					kandidaadid.kandidaadid.add(new Kandidaat(rs.getInt(1),rs.getString(4),partyName,rs.getString(2),rs.getString(3)));

				}
				json = gson.toJson(kandidaadid);
			}
			else if(this.tuup == 2){
				rs = stmt.executeQuery("SELECT kandidaat.id, eesnimi, perenimi, erakond.nimi FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id, erakond WHERE kandidaat.piirkond="
						+ piirkond_id);
				Kandidaadid kandidaadid = new Kandidaadid(new ArrayList<Kandidaat>());
				while(rs.next()){
				kandidaadid.kandidaadid.add(new Kandidaat(rs.getInt(1),region,rs.getString(3),rs.getString(2),rs.getString(3)));
				}
				json = gson.toJson(kandidaadid);
			}
			else if (this.tuup == 3){
				rs = stmt.executeQuery("SELECT kandidaat.id, eesnimi, perenimi FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id WHERE kandidaat.erakond="
						+ erakond_id + " && kandidaat.piirkond=" + piirkond_id);
				Kandidaadid kandidaadid = new Kandidaadid(new ArrayList<Kandidaat>());
				while(rs.next()){
				kandidaadid.kandidaadid.add(new Kandidaat(rs.getInt(1),region,partyName,rs.getString(2),rs.getString(3)));
				}
				json = gson.toJson(kandidaadid);
			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException ignore) {
				}
		}
		// resp.setHeader("Refresh","10; url=/html/index.html");
		
	}

}
