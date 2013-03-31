package com.valimised;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.*;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Kandideeri_testServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			String statement = "INSERT INTO kandidaat(isik,erakond,piirkond) VALUES(? , ? , ?)";
			PreparedStatement stmt = c.prepareStatement(statement);
			Statement stmt2 = c.createStatement();

			String erakond = req.getParameter("Erakond");
			String piirkond = req.getParameter("Piirkond");

			int erakond_id = 0;

			switch (erakond) {
			case "Eesti Iseseisvuspartei":
				erakond_id = 1;
				break;
			case "Eesti Keskerakond":
				erakond_id = 2;
				break;
			case "Eesti Konservatiivne Rahvaerakond":
				erakond_id = 3;
				break;
			case "Eesti Reformierakond":
				erakond_id = 4;
				break;
			case "Eesti Vabaduspartei ñ Pıllumeeste Kogu":
				erakond_id = 5;
				break;
			case "Eestimaa ‹hendatud Vasakpartei":
				erakond_id = 6;
				break;
			case "Erakond Eesti Kristlikud Demokraadid":
				erakond_id = 7;
				break;
			case "Erakond Eestimaa Rohelised":
				erakond_id = 8;
				break;
			case "Erakond Isamaa ja Res Publica Liit":
				erakond_id = 9;
				break;
			case "Sotsiaaldemokraatlik Erakond":
				erakond_id = 10;
				break;
			case "‹ksikkandidaadid":
				erakond_id = 11;
				break;
			}

			int piirkond_id = 0;
			switch (piirkond) {
			case "Tallinna Haabersti, Pıhja-Tallinna ja Kristiine linnaosa":
				piirkond_id = 1;
				break;
			case "Tallinna Kesklinna, Lasnam‰e ja Pirita linnaosa":
				piirkond_id = 2;
				break;
			case "Tallinna Mustam‰e ja Nımme linnaosa":
				piirkond_id = 3;
				break;
			case "Harju- ja Raplamaa":
				piirkond_id = 4;
				break;
			case "Hiiu-, L‰‰ne ja Saaremaa":
				piirkond_id = 5;
				break;
			case "L‰‰ne-Virumaa":
				piirkond_id = 6;
				break;
			case "Ida-Virumaa":
				piirkond_id = 7;
				break;
			case "J‰rva- ja Viljandimaa":
				piirkond_id = 8;
				break;
			case "Jıgeva- ja Tartumaa":
				piirkond_id = 9;
				break;
			case "Tartu Linn":
				piirkond_id = 10;
				break;
			case "Vıru-, Valga- ja Pılvamaa":
				piirkond_id = 11;
				break;
			case "P‰rnumaa":
				piirkond_id = 12;
				break;
			}

			stmt.setLong(1, 20);
			stmt.setLong(2, erakond_id);
			stmt.setLong(3, piirkond_id);
			int success = 2;
			success = stmt.executeUpdate();
			if (success == 1) {
				// out.println("<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
			} else if (success == 0) {
				// out.println("<html><head></head><body>Failure! Please try again! Redirecting in 3 seconds...</body></html>");
			}

		} catch (SQLException e) {
			// out.println("<html><head></head><body>Olete juba kandidaadiks lisatud</body></html>");
			resp.setHeader("Refresh",
					"0; url=/html/lisamiseTagasisideError.html");
			return;
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (SQLException ignore) {
				}
		}
		resp.setHeader("Refresh", "0; url=/html/lisamiseTagasiside.html");
	}
}
