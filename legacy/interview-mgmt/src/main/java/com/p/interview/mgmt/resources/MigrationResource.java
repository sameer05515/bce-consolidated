package com.p.interview.mgmt.resources;

import java.net.HttpURLConnection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.p.interview.mgmt.backup.MainTest;
import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;


/**
 * The Class TopicResource.
 */
@Path("migration")
public class MigrationResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(MigrationResource.class.getName());
	
	private InterviewRPC objInterviewRPC=new InterviewRPC(); 



	@GET
	@Path("/export")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response createCategoryJSON(@QueryParam("jsonDataDirectory") String jsonDataDirectory) {

		logger.info("Entered into c.p.i.m.r.MigrationResource.createCategoryJSON(String) method");

		String message = "successfully contacted the restful API server";		
		logger.info("Information : " + message);
		
		logger.info("c.p.i.m.r.MigrationResource.createCategoryJSON(String) method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		try {
//			topic.setDateCreated(new Date());
//			topic.setDateLastModified(new Date());
			//int c = 1;
			String s=MainTest.createCategoryJSON(jsonDataDirectory+MainTest.categoryDataJsonFileName);
			return Response.status(HttpURLConnection.HTTP_OK).entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
					+ "\", \"message\": \" Successfully exported interview-mgmt data : " + s + "\"}").build();
		} catch (Exception e) {

			/*
			 * TODO Error response code must be centralised, or if possible use
			 * SpringREST instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while exporting interview-mgmt data : " + e + "\"}")
					.build();
		}	
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeCategoryJSON(@QueryParam("jsonDataDirectory") String jsonDataDirectory) {
		
		

		logger.info("Entered into c.p.i.m.r.MigrationResource.consumeCategoryJSON(CategoryDTO) method");

//		logger.info("person.getFirstName()" + topic.getTitle() + "person.getLastName()" + topic.getDescription()
//				+ "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);
		
		logger.info("c.p.i.m.r.MigrationResource.consumeCategoryJSON(CategoryDTO) method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */

		try {
			
			throw new Exception("Method not completely implemented!!");

//			boolean b = true;
//			objInterviewRPC.updateCategory(topic);
//
//			return Response.status(HttpURLConnection.HTTP_OK)
//					.entity("{\"status\":\""
//							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
//							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
//							+ "updated group " + topic.getCatID() + "\"}")
//					.build();

		} catch (Exception e) {

			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while importing data : " + e + "\"}")
					.build();
		}	
		
	}

}
