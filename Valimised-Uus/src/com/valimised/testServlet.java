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
	public String type;
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
		type = req.getParameter("type");
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		Kandidaat kand = new Kandidaat(0,0,0,"","");

		try {
			DriverManager.registerDriver(new AppEngineDriver());
			con = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			stmt = con.createStatement();
			if (type=="byParty"){
				rs = stmt.executeQuery("SELECT * FROM kandidaat;");
				Collection<Kandidaat> col = new ArrayList<Kandidaat>();
				while(rs.next()){
					kand.id=rs.getInt(1);
					kand.eesnimi=rs.getString(2);
					kand.perenimi=rs.getString(3);
					kand.piirkond=rs.getInt(4);
					kand.erakond=erakond_id;
					col.add(kand);

				}
				json = gson.toJson(col);
			}
			else if(type == "byRegion"){
				rs = stmt.executeQuery("SELECT kandidaat.id, eesnimi, perenimi, erakond.id FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id, erakond WHERE kandidaat.piirkond="
						+ piirkond_id);
				Collection<Kandidaat> col = new ArrayList<Kandidaat>();
				while(rs.next()){
					kand.id=rs.getInt(1);
					kand.eesnimi=rs.getString(2);
					kand.perenimi=rs.getString(3);
					kand.piirkond=piirkond_id;
					kand.erakond=rs.getInt(4);
					col.add(kand);
				}
				json = gson.toJson(col);
			}
			else if (type == "byPartyandRegion"){
				rs = stmt.executeQuery("SELECT kandidaat.id, eesnimi, perenimi FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id WHERE kandidaat.erakond="
						+ erakond_id);
				Collection<Kandidaat> col = new ArrayList<Kandidaat>();
				while(rs.next()){

				}
				json = gson.toJson(col);
			}

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
