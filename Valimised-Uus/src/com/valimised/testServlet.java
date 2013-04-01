package com.valimised;

import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class testServlet extends HttpServlet {
	private String partyName;
	private String candidate;
	private String region;
	int erakond_id = 0;
	int piirkond_id = 0;


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String json ="";
		Gson gson = new Gson();
		partyName = req.getParameter("Erakond");
		erakond_id = UtilitiesServlet.getErakondId(partyName);

		region = req.getParameter("Ringkond");
		piirkond_id=UtilitiesServlet.getPiirkondId(region);

		candidate = req.getParameter("Text1");


		Connection con = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new AppEngineDriver());
			con = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			Statement stmt =con.createStatement();
			String sql="SELECT kandidaat.id, eesnimi, perenimi, erakond.nimi AS erakond, piirkond.nimi as piirkond " +
					"FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id LEFT JOIN piirkond on kandidaat.piirkond=piirkond.id " +
					"LEFT JOIN erakond ON kandidaat.erakond=erakond.id ";
			if (piirkond_id==0){
				if(erakond_id==0){
					if(candidate=="" || candidate==null){
						sql+=";";
					}
					else{
						sql += ("WHERE isik.perenimi LIKE '"+candidate.subSequence(0, 2)+"%'");
					}
				}
				else{
					if(candidate=="" || candidate==null){
						sql+=("WHERE kandidaat.erakond="+erakond_id+";");
					}
					else{
						sql+=("WHERE  kandidaat.erakond="+erakond_id+" && isik.perenimi LIKE '"+candidate.subSequence(0, 2)+"%'");
					}

				}
			}
			else {
				if (erakond_id==0){
					if(candidate=="" || candidate==null){
						sql+=("WHERE kandidaat.piirkond="+piirkond_id+";");
					}
					else{
						sql+=("WHERE kandidaat.piirkond="+piirkond_id+" && isik.perenimi LIKE '"+candidate.subSequence(0, 2)+"%'");
					}

				}
				else{
					if(candidate=="" || candidate==null){
						sql+=("WHERE kandidaat.erakond="+erakond_id+" && kandidaat.piirkond="+piirkond_id+";");
					}
					else{
						sql+=("WHERE kandidaat.erakond="+erakond_id+" && kandidaat.piirkond="+piirkond_id+
								" && isik.perenimi LIKE '"+candidate.subSequence(0, 2)+"%';");
					}
				}
			}
			rs=stmt.executeQuery(sql);
			Kandidaadid kandidaadid = new Kandidaadid(new ArrayList<Kandidaat>());
			while(rs.next()){

				kandidaadid.kandidaadid.add(new Kandidaat(rs.getInt(1),rs.getString(4),rs.getString(5),rs.getString(2),rs.getString(3)));

			}
			json = gson.toJson(kandidaadid);
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
