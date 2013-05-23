package com.valimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelFailureException;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.rdbms.AppEngineDriver;

public class HaaleKustutamine extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Connection c = null;
		try {
			
			
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			String haaletajaId = req.getParameter("kasutajaID");
			String statement2 = "SELECT kandidaat FROM haal WHERE haaletaja="+haaletajaId;
			PreparedStatement stmt2 = c.prepareStatement(statement2);
			ResultSet rs=stmt2.executeQuery(statement2);
			int kandidaat = 0;
			if(rs.next()){
				kandidaat = rs.getInt("kandidaat");
			}
			
			String statement = "DELETE FROM haal WHERE haaletaja="+haaletajaId;
			PreparedStatement stmt = c.prepareStatement(statement);
			stmt.execute();
			System.out.println("h‰‰l kustutatud");
			
			String msg = "d_" + Integer.toString(kandidaat);
			System.out.println(msg);
			// Get all channel client ids available
			String query = "select from " + ChannelClient.class.getName();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<ChannelClient> ids = (List<ChannelClient>) pm.newQuery(query).execute();



			ChannelService channelService = ChannelServiceFactory.getChannelService();
			for (ChannelClient m : ids) {
				String client = m.getClientId();
				try {
					channelService.sendMessage(new ChannelMessage(client, msg));
					//System.out.println("sent json stream: " + json);
					//System.out.println("to client: " + client);
				}catch (ChannelFailureException e) {
					//e.printStackTrace();
				}

			}
			System.out.println(msg);
			pm.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sqlexception");
			resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
		} finally {
			System.out.println("finally");
			resp.setHeader("Refresh", "0; url=/html/menu_pohi.html");
		}
	}

}
