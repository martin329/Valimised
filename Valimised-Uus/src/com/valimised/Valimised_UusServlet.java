package com.valimised;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Valimised_UusServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
              throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        
        if (user != null) {
        	
        	
        	if (user.getNickname().indexOf("@")==-1) {
        		resp.setContentType("text/plain");
                resp.getWriter().println("Hello gmail, ");
                resp.getWriter().println("http://localhost:8888" + userService.createLogoutURL(req.getRequestURI()));
                
        	} else {
        		resp.setContentType("text/plain");
        		resp.getWriter().println("Hell0, " + user.getNickname());
        		resp.getWriter().println("http://localhost:8888" + userService.createLogoutURL(req.getRequestURI()));
        	}
        	
        } else {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
    }
}

/*package com.valimised;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Valimised_UusServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
*/