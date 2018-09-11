package com.sun.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionLiseter
 *
 */
@WebListener
public class SessionLiseter implements HttpSessionListener {

    public int count=0;
    public SessionLiseter() {
        // TODO Auto-generated constructor stub
    }

	
    public void sessionCreated(HttpSessionEvent se)  { 
    	
    	ServletContext application = se.getSession().getServletContext();
    	if(se.getSession().isNew()) {
    	count++;
    	application.setAttribute("count", count);
    	}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	count--;
    	ServletContext application = se.getSession().getServletContext();
    	if (count<0) {
			count=1;
		}
    	application.setAttribute("count", count);
    }
	
}
