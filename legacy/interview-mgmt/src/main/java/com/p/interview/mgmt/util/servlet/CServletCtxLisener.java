package com.p.interview.mgmt.util.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


public class CServletCtxLisener implements ServletContextListener {

//	private static String confFolderPath="C:/APPSERVERS/TEST-apache-tomcat-8.0.30/webapps/interview-mgmt/WEB-INF/conf";
	private static String confFolderPath="D:\\CUSTOM-INSTALLATIONS\\apache-tomcat-8.5.47\\webapps\\interview-mgmt\\WEB-INF\\conf";
	private static String contextPath;
	static Logger log = Logger.getLogger(CServletCtxLisener.class);

	/**
	 * blank implementation of method from interface
	 * javax.servlet.ServletContextListener
	 * */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent evt) {
		ServletContext context = evt.getServletContext();
		String path = context.getRealPath("/WEB-INF/conf");
		log.info("found path of conf folder " + path);
		confFolderPath = path;
		contextPath=context.getRealPath("/");
	}

	public static String getConfFolderPath() {
		return confFolderPath;
	}

	/**
	 * @return the contextPath
	 */
	public static String getContextPath() {
		return contextPath;
	}

}
