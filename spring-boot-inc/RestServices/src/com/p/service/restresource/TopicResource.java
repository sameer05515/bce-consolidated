package com.p.service.restresource;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.p.service.exception.RestServiceException;
import com.p.service.pojo.Topic;
import com.p.service.pojo.TopicHistory;
import com.p.service.pojo.TopicHistoryReport;
import com.p.service.vo.TopicReadResponse;
import com.p.sevice.common.DAOFactory;

/**
 * The Class TopicResource.
 */
@Path("topics")
public class TopicResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TopicResource.class.getName());

	/**
	 * Gets the all topics list.
	 *
	 * @return the all topics list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest serveletRequest) {

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";
		try {

			List<Topic> topics = DAOFactory.getTopicSessionInterface().getAll();

			logger.info("Information : " + message + topics);
			return Response.status(HttpURLConnection.HTTP_OK).entity(topics).build();
		} catch (RestServiceException e) {

			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}
	}

	/**
	 * Gets the topic for given id from list.
	 *
	 * @param id
	 * 
	 * @return the topic from list for given id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		try {
			Topic topic = DAOFactory.getTopicSessionInterface().get(id);
			return Response.status(HttpURLConnection.HTTP_OK).entity(topic).build();
		} catch (RestServiceException e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no topic found for given id : " + id)
					.build();
		}

	}

	@GET
	@Path("/uid/{uniqueStrid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("uniqueStrid") String uniqueStrid) {

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		try {
			Topic topic = DAOFactory.getTopicSessionInterface().get(uniqueStrid);
			return Response.status(HttpURLConnection.HTTP_OK).entity(topic).build();
		} catch (RestServiceException e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("no topic found for given id : " + uniqueStrid).build();
		}

	}

	/**
	 * Deletes the topic for given id from list.
	 *
	 * @param id
	 * 
	 * @return the topic from list for given id
	 */
	// @DELETE
	// @Path("/{id}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response delete(@PathParam("id") int id) {
	//
	// logger.info("Entered into getCoachingList method");
	// String message = "successfully contacted the restful API server";
	// Topic tt=null;
	// for(Topic t:topics){
	// if(t.getId()==id){
	// tt=t;
	// }
	// }
	// logger.info("Information : "+message);
	// if(tt!=null){
	// return Response.status(HttpURLConnection.HTTP_OK).entity(tt)
	// .build();
	// }else{
	// return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no topic
	// found for given id : "+id)
	// .build();
	// }
	//
	// }

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Topic topic) {

		logger.info("Entered into getCoachingList method");

		logger.info("person.getFirstName()" + topic.getTitle() + "person.getLastName()" + topic.getDescription()
				+ "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		/*
		 * TODO Validation of the topic object came , and if any assertion is failing,
		 * error response code should be returned to client
		 */
		try {
			topic.setDateCreated(new Date());
			topic.setDateLastModified(new Date());
			topic.setDateLastRead(new Date());
			int c = DAOFactory.getTopicSessionInterface().create(topic);

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
							+ "\", \"message\": \" Successfully created new topic : " + c + "\" , \"new_topic_id\": \""
							+ c + "\"}")
					.build();
		} catch (RestServiceException e) {

			/*
			 * TODO Error response code must be centralised, or if possible use SpringREST
			 * instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new topic : " + e + "\"}")
					.build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Topic topic) {

		logger.info("Entered into getCoachingList method");

		logger.info("person.getFirstName()" + topic.getTitle() + "person.getLastName()" + topic.getDescription()
				+ "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		/*
		 * TODO Validation of the topic object came , and if any assertion is failing,
		 * error response code should be returned to client
		 */

		try {
			topic.setDateLastModified(new Date());
			boolean b = DAOFactory.getTopicSessionInterface().update(topic);
			boolean b1 = DAOFactory.getTopicSessionInterface().addTopicHistory(topic, "update");

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated group " + topic.getId() + "\"}")
					.build();

		} catch (RestServiceException e) {

			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new topic : " + e + "\"}")
					.build();
		}
	}

	@PUT
	@Path("/{id}/mark/{action}")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response addTopicHistory(@PathParam("id") int id, @PathParam("action") String action) {

		logger.info("Entered into addTopicHistory method");

//		logger.info("person.getFirstName()" + topic.getTitle() + "person.getLastName()" + topic.getDescription()
//				+ "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		/*
		 * TODO Validation of the topic object came , and if any assertion is failing,
		 * error response code should be returned to client
		 */

		try {
			Topic topic = DAOFactory.getTopicSessionInterface().get(id);
			// topic.setDateLastModified(new Date());
			boolean b = DAOFactory.getTopicSessionInterface().addTopicHistory(topic, action);

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated group " + topic.getId() + "\"}")
					.build();

		} catch (RestServiceException e) {

			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new topic : " + e + "\"}")
					.build();
		}

	}

	/**
	 * Gets the topic for given id from list.
	 *
	 * @param id
	 * 
	 * @return the topic from list for given id
	 */
	@GET
	@Path("/{id}/history/{action}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopicHistory(@PathParam("id") int id, @PathParam("action") String action) {

		logger.info("Entered into getTopicHistory method");
//		String message = "successfully contacted the restful API server";
//		logger.info("Information : " + message);

		try {
			Topic topic = DAOFactory.getTopicSessionInterface().get(id);
			List<TopicHistory> list = DAOFactory.getTopicSessionInterface().getTopicHistory(topic, action);
			return Response.status(HttpURLConnection.HTTP_OK).entity(new TopicReadResponse(id, topic, list)).build();
		} catch (RestServiceException e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no topic found for given id : " + id)
					.build();
		}

	}

	/**
	 * Gets the topic for given id from list.
	 *
	 * @param id
	 * 
	 * @return the topic from list for given id
	 */
	@GET
	@Path("/history/{action}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopicHistory(@PathParam("action") String action) {

		logger.info("Entered into getTopicHistory method");
//		String message = "successfully contacted the restful API server";
//		logger.info("Information : " + message);

		try {

			List<TopicHistoryReport> list = DAOFactory.getTopicSessionInterface().getTopicHistoryReport(action);
			return Response.status(HttpURLConnection.HTTP_OK).entity(list).build();
		} catch (RestServiceException e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("no topic history found for given action : " + action).build();
		}

	}

}
