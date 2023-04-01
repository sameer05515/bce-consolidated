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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;

/**
 * The Class AnswerResource.
 */
@Path("categories/{linkedCategoryID}/questions/{linkedQuestionID}/answers")
public class AnswerResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AnswerResource.class.getName());

	private InterviewRPC objInterviewRPC = new InterviewRPC();

	/**
	 * Gets the all Answers list.
	 *
	 * @return the all Answers list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAnswers(@Context HttpServletRequest serveletRequest,
			@PathParam("linkedCategoryID") int linkedCategoryID, @PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info(
				"Entered into c.p.i.m.r.AnswerResource.getAllAnswers(HttpServletRequest, int, int) method");

		logger.info(
				"c.p.i.m.r.AnswerResource.getAllAnswers(HttpServletRequest, int, int) method called");
		logger.info("linkedCategoryID == "+linkedCategoryID+"linkedQuestionID == "+linkedQuestionID);

		String message = "successfully contacted the restful API server";
		Vector<AnswerDTO> list = new Vector<>();

		QuestionDTO objQuestionDTO = new QuestionDTO();
		objQuestionDTO.setQuestionID(linkedQuestionID);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);

		try {

			list = objInterviewRPC.fetchAllAnswersByQuestion(objQuestionDTO);

			logger.info("Information : " + message + list);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(list).build();
	}

	/**
	 * Gets the Answer for given id from list.
	 *
	 * @param id
	 * 
	 * @return the Answer from list for given id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswer(@PathParam("id") int id, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into c.p.i.m.r.AnswerResource.getAnswer(int, int, int) method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.AnswerResource.getAnswer(int, int, int) method for id " + id
				+ " called");
		logger.info(" linkedCategoryID == "+linkedCategoryID+" linkedQuestionID == "+linkedQuestionID+" answerId == "+id);

		AnswerDTO objQuestionDTO = new AnswerDTO();
		objQuestionDTO.setLinkedQuesID(linkedQuestionID);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);
		objQuestionDTO.setAnsID(id);

		try {
			objQuestionDTO = objInterviewRPC.retrieveAnswer(objQuestionDTO);
			return Response.status(HttpURLConnection.HTTP_OK).entity(objQuestionDTO).build();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" no answer found for given id :  " + id + "\"}")
					.build();
		}

	}

	/**
	 * Deletes the Answer for given id from list.
	 *
	 * @param id
	 * 
	 * @return the Answer from list for given id
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAnswer(@PathParam("id") int id, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into c.p.i.m.r.AnswerResource.deleteAnswer(int, int, int) method");
		String message = "successfully contacted the restful API server";

		logger.info("c.p.i.m.r.AnswerResource.deleteAnswer(int, int, int) method for id " + id
				+ " called");
		
		logger.info(" linkedCategoryID == "+linkedCategoryID+" linkedQuestionID == "+linkedQuestionID+" answerId == "+id);

		String me = null;

		AnswerDTO objQuestionDTO = new AnswerDTO();
		objQuestionDTO.setLinkedQuesID(linkedQuestionID);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);
		objQuestionDTO.setAnsID(id);

		try {
			me = objInterviewRPC.deleteAnswer(objQuestionDTO);
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
							+ "\", \"message\": \" no answer found for given id :  " + id + "\"}")
					.build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveAnswer(AnswerDTO objAnswerDTO, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info(
				"Entered into c.p.i.m.r.AnswerResource.saveAnswer(AnswerDTO, int, int) method");

		String message = "successfully contacted the restful API server";

		

		logger.info("c.p.i.m.r.AnswerResource.saveAnswer(AnswerDTO, int, int) method called");
		logger.info(" linkedCategoryID == "+linkedCategoryID+" linkedQuestionID == "+linkedQuestionID);

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */

		objAnswerDTO.setLinkedCatID(linkedCategoryID);
		objAnswerDTO.setLinkedQuesID(linkedQuestionID);

		try {

			int c = 1;
			objInterviewRPC.saveAnswer(objAnswerDTO);
			logger.info("Information : " + message);
			return Response.status(HttpURLConnection.HTTP_OK).entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
					+ "\", \"message\": \" Successfully created new Answer : " + c + "\"}").build();
		} catch (Exception e) {

			/*
			 * TODO Error response code must be centralised, or if possible use
			 * SpringREST instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new Answer : " + e + "\"}")
					.build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAnswer(AnswerDTO objAnswerDTO, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info(
				"Entered into c.p.i.m.r.AnswerResource.updateAnswer(AnswerDTO, int, int) method");

		String message = "successfully contacted the restful API server";
		

		logger.info("c.p.i.m.r.AnswerResource.updateAnswer(AnswerDTO, int, int) method called");
		logger.info(" linkedCategoryID == "+linkedCategoryID+" linkedQuestionID == "+linkedQuestionID);

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		objAnswerDTO.setLinkedCatID(linkedCategoryID);
		objAnswerDTO.setLinkedQuesID(linkedQuestionID);

		try {

			boolean b = true;
			objInterviewRPC.updateAnswer(objAnswerDTO);

			logger.info("Information : " + message);
			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated Answer " + objAnswerDTO.getAnsID() + "\"}")
					.build();

		} catch (Exception e) {

			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while updating Answer : " + e + "\"}")
					.build();
		}
	}

}
