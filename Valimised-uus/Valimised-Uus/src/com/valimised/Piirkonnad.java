package com.valimised;

import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class Piirkonnad extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String json ="";
		Gson gson = new Gson();
		Connection con = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new AppEngineDriver());
			con = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			Statement stmt =con.createStatement();
			String sql="SELECT piirkond, id, MAX(haali), SUM(haali) FROM v_erakond_haaled GROUP BY piirkond;";
			rs=stmt.executeQuery(sql);
			ArrayList<Piirkond> piirkonnad = new ArrayList<Piirkond>();
			int piirkondId, erakondId, haali, piirkonnasHaali;
			while(rs.next()){
				piirkondId = rs.getInt(1);
				erakondId = rs.getInt(2);
				haali = rs.getInt(3);
				piirkonnasHaali = rs.getInt(4);
				
				Piirkond piirkond = new Piirkond(piirkondId, erakondId, haali, piirkonnasHaali);
				piirkonnad.add(piirkond);
				
				
			}
			
			
			json = gson.toJson(piirkonnad);
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
	}

}
