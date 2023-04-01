package com.p.service.restresource;

import java.net.HttpURLConnection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.p.service.pojo.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class CoachingActionResource.
 */
@Path("rest")
public class CoachingActionResource {

	/** The Constant logger. */
	private static final Logger logger = Logger
			.getLogger(CoachingActionResource.class.getName());

	/**
	 * Gets the coaching listfor account id.
	 *
	 * @param accId
	 *            the acc id
	 * @return the coaching listfor account id
	 */
	// @RolesAllowed("Configure Coaching Actions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response getCoachingListforAccountID(
	/*
	 * @PathParam("accountId") int accId,
	 */@QueryParam("item") String item, @QueryParam("Status") String Status,
			@QueryParam("actionsFor") String actionFor) {

		logger.info("Entered into getCoachingList method");

		System.out.println("item" + item + "Status" + Status + "actionFor"
				+ actionFor);

		// return
		// CoachingActionService.getInstance().getCoachingListforAccountID(accId,item,Status,actionFor);
		String message = "successfully contacted the restful API server";
		return Response.status(HttpURLConnection.HTTP_OK).entity(message)
				.build();
	}

	@POST
	@Path("/employee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Person person) {

		logger.info("Entered into getCoachingList method");

		System.out.println("person.getFirstName()" + person.getFirstName()
				+ "person.getLastName()" + person.getLastName());

		// return
		// CoachingActionService.getInstance().getCoachingListforAccountID(accId,item,Status,actionFor);
		String message = "successfully contacted the restful API server";
		return Response.status(HttpURLConnection.HTTP_OK).entity(person)
				.build();
	}

	/**
	 * Creates the and update coaching.
	 *
	 * @param createCoachingAction
	 *            the create coaching action
	 * @param accId
	 *            the acc id
	 * @return the response
	 */
	// @RolesAllowed("Configure Coaching Actions")
	// @POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// // / @Produces(MediaType.APPLICATION_JSON)
	// public Response createAndUpdateCoaching(
	// CoachingActionJson createCoachingAction,
	// @PathParam("accountId") int accId,
	// @Context HttpServletRequest httpRequest) {
	// logger.info("Entered into createAndUpdateCoaching method");
	//
	// Response response = null;
	// // UserSessionVO uservo=null;
	// // if(httpRequest!=null){
	// //
	// uservo=(UserSessionVO)httpRequest.getSession().getAttribute("UserLoginJson");
	// // }
	// //
	// // if(uservo!=null){
	// // createCoachingAction.setCreatedBy(uservo.getEmployeeId());
	// // }
	// // if(createCoachingAction!=null){
	// // if(createCoachingAction.getAccountId()==null){
	// // createCoachingAction.setAccountId(accId);
	// // }
	// // response=
	// //
	// CoachingActionService.getInstance().saveAndUpdateCoaching(createCoachingAction);
	// // }
	//
	// return response;
	// }

	// @RolesAllowed("Configure Coaching Actions")
	// @GET
	// @Path("/itemsList")
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response getItemsList(@PathParam("accountId") int accId) {
	// Response response = null;
	//
	// // response= CoachingActionService.getInstance().getItemsList(accId);
	// //
	// // return response;
	// return null;
	// }

}
