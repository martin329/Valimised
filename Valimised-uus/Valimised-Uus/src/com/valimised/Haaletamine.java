package com.valimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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

public class Haaletamine extends HttpServlet {
	private long haaletaja;
	private long kandidaat;
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		haaletaja = Long.parseLong(req.getParameter("haaletaja"));
		kandidaat = Long.parseLong(req.getParameter("kandidaat"));
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager.getConnection(
					"jdbc:google:rdbms://jjmmtvdb:jjmmtvdb/valimisedDB",
					"root", "");
			String statement = "INSERT INTO haal(haaletaja,kandidaat,aeg) VALUES(? , ? , ?)";
			PreparedStatement stmt = c.prepareStatement(statement);
			
			stmt.setLong(1, haaletaja);
			stmt.setLong(2, kandidaat);
			java.util.Date date= new java.util.Date();
			stmt.setTimestamp(3, new Timestamp(date.getTime()));

			stmt.execute();
			
			String msg = "h_" + Long.toString(kandidaat);
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
					e.printStackTrace();
				}
			}
			System.out.println(msg);
			pm.close();
			
		} catch (SQLException e) {
			resp.setHeader("Refresh",
					"0; url=/html/haaletamiseTagasisideError.html");
		} finally {
			resp.setHeader("Refresh",
					"0; url=/html/haaletamiseTagasiside.html");
		}
	}
}