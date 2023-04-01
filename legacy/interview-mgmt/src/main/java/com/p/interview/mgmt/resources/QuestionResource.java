package com.p.interview.mgmt.resources;

import java.net.HttpURLConnection;
import java.util.List;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistory;
import com.p.interview.mgmt.pojo.vo.CategQuestionReadResponse;
import com.p.interview.mgmt.rpc.InterviewRPC;

/**
 * The Class QuestionResource.
 */
@Path("categories/{linkedCategoryID}/questions")
public class QuestionResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(QuestionResource.class.getName());

	private InterviewRPC objInterviewRPC = new InterviewRPC();

	/**
	 * Gets the all Questions list.
	 *
	 * @return the all Questions list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllQuestions(@Context HttpServletRequest serveletRequest,
			@PathParam("linkedCategoryID") int linkedCategoryID) {

		logger.info(
				"Entered into c.p.i.m.r.QuestionResource.getAllQuestions(HttpServletRequest, int) method");

		logger.info(
				"c.p.i.m.r.QuestionResource.getAllQuestions(HttpServletRequest, int)s method called");

		String message = "successfully contacted the restful API server";

		Vector<QuestionDTO> list = new Vector<>();

		try {

			list = objInterviewRPC.fetchAllQuestionsByCategory(linkedCategoryID);

			logger.info("Information : " + message + list);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(list).build();

	}

	/**
	 * Gets the Question for given id from list.
	 *
	 * @param id
	 * 
	 * @return the Question from list for given id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getQuestion(@PathParam("id") int id, @PathParam("linkedCategoryID") int linkedCategoryID) {

		logger.info("Entered into c.p.i.m.r.QuestionResource.getQuestion(int, int) method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.QuestionResource.getQuestion(int, int) method for id " + id
				+ " called");

		QuestionDTO objQuestionDTO = new QuestionDTO();
		objQuestionDTO.setQuestionID(id);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);

		try {
			objQuestionDTO = objInterviewRPC.retrieveQuestion(objQuestionDTO);
			return Response.status(HttpURLConnection.HTTP_OK).entity(objQuestionDTO).build();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" no question found for given id :  " + id + "\"}")
					.build();
		}
	}

	/**
	 * Deletes the Question for given id from list.
	 *
	 * @param id
	 * 
	 * @return the Question from list for given id
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@PathParam("id") int id, @PathParam("linkedCategoryID") int linkedCategoryID) {

		logger.info("Entered into c.p.i.m.r.QuestionResource.deleteQuestion(int, int) method");
		String message = "successfully contacted the restful API server";

		logger.info("c.p.i.m.r.QuestionResource.deleteQuestion(int, int) method for id " + id
				+ " called");

		String me = null;
		QuestionDTO objQuestionDTO = new QuestionDTO();
		objQuestionDTO.setQuestionID(id);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);
		try {
			me = objInterviewRPC.deleteQuestion(objQuestionDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		}
		logger.info("Information : " + message);
		if (me != null) {
			return Response.status(HttpURLConnection.HTTP_OK).entity(me).build();
		} else {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" no question found for given id :  " + id + "\"}")
					.build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveQuestion(QuestionDTO objQuestionDTO, @PathParam("linkedCategoryID") int linkedCategoryID) {

		logger.info(
				"Entered into c.p.i.m.r.QuestionResource.saveQuestion(QuestionDTO, int) method");

		String message = "successfully contacted the restful API server";

		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.QuestionResource.saveQuestion(QuestionDTO, int) method called");

		objQuestionDTO.setLinkedCatID(linkedCategoryID);

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		try {

			int c = 1;
			objInterviewRPC.saveQuestion(objQuestionDTO);
//			objInterviewRPC.addQuestionHistory(linkedCategoryID, id,"create");
			return Response.status(HttpURLConnection.HTTP_OK).entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
					+ "\", \"message\": \" Successfully created new Question : " + c + "\"}").build();
		} catch (Exception e) {

			/*
			 * TODO Error response code must be centralised, or if possible use
			 * SpringREST instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new Question : " + e + "\"}")
					.build();
		}

	}

	// ###########Save History (read,update,create) of a Category-Question time#########
	@PUT
	@Path("/{id}/mark/{action}")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestionHistory(@PathParam("linkedCategoryID") int linkedCategoryID, 
			@PathParam("id") int id,@PathParam("action") String action) {

		logger.info("Entered into c.p.i.m.r.QuestionResource.addRead(int, int) method");

		String message = "successfully contacted the restful API server";

		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.QuestionResource.addRead(int, int) called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		try {
			// topic.setDateCreated(new Date());
			// topic.setDateLastModified(new Date());
			int c = 1;
			objInterviewRPC.addQuestionHistory(linkedCategoryID, id,action);
			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
							+ "\", \"message\": \" Successfully updated read time for given question : " + c + "\"}")
					.build();
		} catch (Exception e) {

			/*
			 * TODO Error response code must be centralised, or if possible use
			 * SpringREST instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while updating read time for given question : " + e + "\"}")
					.build();
		}

	}
	
	// ###########Save Read time#########
		@PUT
		@Path("/{id}/markprivate")
		@Produces(MediaType.APPLICATION_JSON)
		// @Consumes(MediaType.APPLICATION_JSON)
		public Response markPrivate(@PathParam("linkedCategoryID") int linkedCategoryID, @PathParam("id") int id) {

			logger.info("Entered into c.p.i.m.r.QuestionResource.addRead(int, int) method");

			String message = "successfully contacted the restful API server";

			logger.info("Information : " + message);

			logger.info("c.p.i.m.r.QuestionResource.addRead(int, int) called");

			/*
			 * TODO Validation of the topic object came , and if any assertion is
			 * failing, error response code should be returned to client
			 */
			try {
				// topic.setDateCreated(new Date());
				// topic.setDateLastModified(new Date());
				int c = 1;
				objInterviewRPC.markPrivate(linkedCategoryID, id);
				return Response.status(HttpURLConnection.HTTP_OK)
						.entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
								+ "\", \"message\": \" Successfully updated read time for given question : " + c + "\"}")
						.build();
			} catch (Exception e) {

				/*
				 * TODO Error response code must be centralised, or if possible use
				 * SpringREST instead of Jersey framework
				 */
				e.printStackTrace();
				logger.info(e);

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
						.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
								+ "\", \"message\": \" Error while updating read time for given question : " + e + "\"}")
						.build();
			}

		}
		
		@GET
		@Path("/{id}/history/{action}")
		@Produces(MediaType.APPLICATION_JSON)	
	public Response getQuestionHistory(@PathParam("linkedCategoryID") int linkedCategoryID, 
			@PathParam("id") int id) {
			logger.info("Entered into c.p.i.m.r.QuestionResource.addRead(int, int) method");

			String message = "successfully contacted the restful API server";

			logger.info("Information : " + message);

			logger.info("c.p.i.m.r.QuestionResource.addRead(int, int) called");

			/*
			 * TODO Validation of the topic object came , and if any assertion is
			 * failing, error response code should be returned to client
			 */
			try {
				// topic.setDateCreated(new Date());
				// topic.setDateLastModified(new Date());
				int c = 1;
				QuestionDTO objQuestionDTO = new QuestionDTO();
				objQuestionDTO.setQuestionID(id);
				objQuestionDTO.setLinkedCatID(linkedCategoryID);
				
				objQuestionDTO = objInterviewRPC.retrieveQuestion(objQuestionDTO);
				List<CategQuestionHistory> reads=objInterviewRPC.getQuestionHistory(linkedCategoryID, id);
				CategQuestionReadResponse resp=new CategQuestionReadResponse(linkedCategoryID, id, objQuestionDTO, reads);
				return Response.status(HttpURLConnection.HTTP_OK)
						.entity(resp)
						.build();
			} catch (Exception e) {

				/*
				 * TODO Error response code must be centralised, or if possible use
				 * SpringREST instead of Jersey framework
				 */
				e.printStackTrace();
				logger.info(e);

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
						.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
								+ "\", \"message\": \" Error while updating read time for given question : " + e + "\"}")
						.build();
			}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuestion(QuestionDTO objQuestionDTO, @PathParam("linkedCategoryID") int linkedCategoryID) {

		logger.info(
				"Entered into c.p.i.m.r.QuestionResource.updateQuestion(QuestionDTO, int) method");

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.QuestionResource.updateQuestion(QuestionDTO, int) method called");

		objQuestionDTO.setLinkedCatID(linkedCategoryID);

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */

		try {

			boolean b = true;
			objInterviewRPC.updateQuestion(objQuestionDTO);

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated Question " + objQuestionDTO.getQuestionID() + "\"}")
					.build();

		} catch (Exception e) {

			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while updating Question : " + e + "\"}")
					.build();
		}
	}

}
