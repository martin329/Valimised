package com.valimised;

import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class PushJsonServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String json ="";
		Gson gson = new Gson();
		Connection con = null;
		ResultSet rs = null;
		String kandidaatId = req.getParameter("kandidaat");
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			con = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			Statement stmt =con.createStatement();
			String sql="SELECT kandidaat.id, eesnimi, perenimi, erakond.nimi AS erakond, piirkond.nimi as piirkond, kandidaat.haali " +
					"FROM kandidaat LEFT JOIN isik on kandidaat.isik=isik.id LEFT JOIN piirkond on kandidaat.piirkond=piirkond.id " +
					"LEFT JOIN erakond ON kandidaat.erakond=erakond.id WHERE kandidaat.id=" + kandidaatId;
			rs=stmt.executeQuery(sql);
			if (rs.next()){
				Kandidaat kandidaat = new Kandidaat(rs.getInt(1),rs.getString(5),rs.getString(4),rs.getString(2),rs.getString(3), rs.getInt(6));
				json = gson.toJson(kandidaat);
				System.out.println("Json: "+ json);
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				resp.getWriter().write(json);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException ignore) {
				}
		}
	}

}
