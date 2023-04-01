package com.p.interview.mgmt.resources;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.PreDestroy;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.pojo.vo.CategoryVO;
import com.p.interview.mgmt.rpc.InterviewRPC;

/**
 * The Class CategoryResource.
 */
@Path("categories")
public class CategoryResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(CategoryResource.class.getName());

	private InterviewRPC objInterviewRPC = new InterviewRPC();
	
	@GET
	@Path("/optimized/opti")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getAllCategoriesOptimized(@Context HttpServletRequest serveletRequest) {
		List<CategoryVO> list = new ArrayList<>();
		
		try {
			list = objInterviewRPC.getAllCategoriesOptimized();
			//logger.info("Information : " + message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}
		
		return Response.status(HttpURLConnection.HTTP_OK).entity(list).build();
	}

	/**
	 * Gets the all categories list.
	 *
	 * @return the all categories list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories(@Context HttpServletRequest serveletRequest) {

		logger.info(
				"Entered into c.p.i.m.r.CategoryResource.getAllCategories(HttpServletRequest) method");
		String message = "successfully contacted the restful API server";
		
		logger.info(
				"c.p.i.m.r.CategoryResource.getAllCategories(HttpServletRequest) method called");
		logger.info("All Categories data will be fetched from DB ");

		Vector<CategoryDTO> list = new Vector<>();
		try {
			list = objInterviewRPC.fetchAllCategories();
			logger.info("Information : " + message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(list).build();
	}

	/**
	 * Gets the Category for given id from list.
	 *
	 * @param id
	 * 
	 * @return the Category from list for given id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategory(/* @PathParam("id") int id */@Context UriInfo ui) {

		MultivaluedMap<String, String> pathParams = ui.getPathParameters();
		String idstr = pathParams.getFirst("id");
		int id = Integer.parseInt(idstr);

		logger.info("Entered into c.p.i.m.r.CategoryResource.getCategory(UriInfo) method");
		String message = "successfully contacted the restful API server";
		

		logger.info(
				"c.p.i.m.r.CategoryResource.getCategory(UriInfo) method for id " + id + " called");
		
		logger.info(" CategoryID == "+id);

		CategoryDTO objCategoryDTO = new CategoryDTO();
		objCategoryDTO.setCatID(id);

		try {
			objCategoryDTO = objInterviewRPC.retrieveCategory(objCategoryDTO);
			logger.info("Information : " + message);
		} catch (RestServiceException e) {
			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no Category found for given id : " + id)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(objCategoryDTO).build();

	}

	/**
	 * Deletes the Category for given id from list.
	 *
	 * @param id
	 * 
	 * @return the Category from list for given id
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategory(@PathParam("id") int id) {

		logger.info("Entered into c.p.i.m.r.CategoryResource.deleteCategory(int) method");
		String message = "successfully contacted the restful API server";

		logger.info(
				"c.p.i.m.r.CategoryResource.deleteCategory(int) method for id " + id + " called");

		String me = null;
		try {
			me = objInterviewRPC.deleteCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		}
		logger.info("Information : " + message);
		if (me != null) {
			return Response.status(HttpURLConnection.HTTP_OK).entity(me).build();
		} else {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no Category found for given id : " + id)
					.build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveCategory(CategoryDTO topic) {

		logger.info("Entered into c.p.i.m.r.CategoryResource.saveCategory(CategoryDTO) method");

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.CategoryResource.saveCategory(CategoryDTO) method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is failing,
		 * error response code should be returned to client
		 */
		try {

			int c = 1;
			objInterviewRPC.saveCategory(topic);
			return Response.status(HttpURLConnection.HTTP_OK).entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
					+ "\", \"message\": \" Successfully created new Category : " + c + "\"}").build();
		} catch (Exception e) {

			/*
			 * TODO Error response code must be centralised, or if possible use SpringREST
			 * instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new Category : " + e + "\"}")
					.build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCategory(CategoryDTO topic) {

		logger.info("Entered into c.p.i.m.r.CategoryResource.updateCategory(CategoryDTO) method");

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("c.p.i.m.r.CategoryResource.updateCategory(CategoryDTO) method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is failing,
		 * error response code should be returned to client
		 */

		try {

			boolean b = true;
			objInterviewRPC.updateCategory(topic);

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated Category " + topic.getCatID() + "\"}")
					.build();

		} catch (Exception e) {

			e.printStackTrace();
			logger.info(e);
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while updating Category : " + e + "\"}")
					.build();
		}

	}

}
