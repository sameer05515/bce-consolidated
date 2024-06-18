package com.p.service.restresource;

import java.net.HttpURLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.p.service.dao.TopicGroupRelationSession;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.TopicGroupRelation;
import com.p.service.vo.TopicGroupRelationResourceVO;
import com.p.sevice.common.DAOFactory;
import com.p.sevice.common.ErrorResponse;
import com.p.sevice.util.TopicResponseErrorCodes;
import com.p.sevice.util.TopicUtil;

/**
 * The Class TopicGroupRelationResource.
 */
@Path("tgservices")
public class TopicGroupRelationResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TopicGroupRelationResource.class.getName());

	@GET
	@Path("/topics/{id}/count")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getGroupCountForTopic(@PathParam("id") final int topicId, @Context UriInfo uriInfo,
			@Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {

		logger.info("getTopicGroupRelationForTopic called ");
		logger.info("topicId: " + topicId);

		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (topicId <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg("Invalid topicId provided : topicId " + topicId));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		return null;// EmployeeLobsService.getInstance().getEmployeeLobMappingsForLob(lobList,
					// uriInfo, headers, servletResponse, req);

	}

	@GET
	@Path("/groups/{id}/count")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTopicCountForGroup(@PathParam("id") final int groupId, @Context UriInfo uriInfo,
			@Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {

		logger.info("getTopicGroupRelationForGroup called ");
		logger.info("groupId: " + groupId);

		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (groupId <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg("Invalid groupId provided : groupId " + groupId));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		return null;// EmployeeLobsService.getInstance().getEmployeeLobMappingsForLob(lobList,
					// uriInfo, headers, servletResponse, req);

	}

	@GET
	@Path("/topics")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response getTopicGroupRelationForTopic(@QueryParam("id") final List<Integer> topicIdList,
			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {

		logger.info("getTopicGroupRelationForTopic called ");
		logger.info("topicIdList: " + topicIdList);

		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (topicIdList == null || topicIdList.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg("Invalid topicIdList provided : topicIdList " + topicIdList));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		List<TopicGroupRelation> listTopicGroupRelation;
		try {

			TopicGroupRelationSession objTopicGroupRelationSession = DAOFactory.getTopicGroupRelationSessionInterface();

			listTopicGroupRelation = objTopicGroupRelationSession.getTopicGroupRelationForTopic(topicIdList);

		} catch (RestServiceException e) {
			if (e.getErrCode() == TopicResponseErrorCodes.ZT_EMP_LOB_ERR_009) {
				ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_009, e.getMessage(),
						e.getMessage());
				logger.error(er.getErrorCode(), e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(er)
						.type(MediaType.APPLICATION_JSON).build();
			}
			e.printStackTrace();
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001), e.getMessage());
			logger.error(er.getErrorCode(), e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		if (listTopicGroupRelation == null || listTopicGroupRelation.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		logger.info("got response : " + listTopicGroupRelation);

		return Response.status(HttpURLConnection.HTTP_OK).entity(listTopicGroupRelation).build();

	}

	@GET
	@Path("/groups")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTopicGroupRelationForGroup(@QueryParam("id") final List<Integer> groupIdList,
			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {

		logger.info("getTopicGroupRelationForGroup called ");
		logger.info("groupIdList: " + groupIdList);

		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (groupIdList == null || groupIdList.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg("Invalid groupIdList provided : groupIdList " + groupIdList));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		List<TopicGroupRelation> listTopicGroupRelation;
		try {

			TopicGroupRelationSession objTopicGroupRelationSession = DAOFactory.getTopicGroupRelationSessionInterface();

			listTopicGroupRelation = objTopicGroupRelationSession.getTopicGroupRelationForGroup(groupIdList);

		} catch (RestServiceException e) {
			if (e.getErrCode() == TopicResponseErrorCodes.ZT_EMP_LOB_ERR_009) {
				ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_009, e.getMessage(),
						e.getMessage());
				logger.error(er.getErrorCode(), e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(er)
						.type(MediaType.APPLICATION_JSON).build();
			}
			e.printStackTrace();
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001), e.getMessage());
			logger.error(er.getErrorCode(), e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		if (listTopicGroupRelation == null || listTopicGroupRelation.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		logger.info("got response : " + listTopicGroupRelation);

		return Response.status(HttpURLConnection.HTTP_OK).entity(listTopicGroupRelation).build();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTopicsToGroups(TopicGroupRelationResourceVO topicGroupRelationResourceVO,
			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {
		logger.info("addTopicsToGroups called");
		logger.info("employeesInLobJson: " + topicGroupRelationResourceVO);
		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (topicGroupRelationResourceVO == null || topicGroupRelationResourceVO.getTopicIdList() == null
				|| topicGroupRelationResourceVO.getTopicIdList().size() <= 0
				|| topicGroupRelationResourceVO.getGroupIdList() == null
				|| topicGroupRelationResourceVO.getGroupIdList().size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		int noOfTopicGroupRelationsRowsCreated = 0;
		try {

			TopicGroupRelationSession topicGroupRelationSessionInterface = DAOFactory
					.getTopicGroupRelationSessionInterface();

			noOfTopicGroupRelationsRowsCreated = topicGroupRelationSessionInterface.addTopicsToGroups(
					topicGroupRelationResourceVO.getTopicIdList(), topicGroupRelationResourceVO.getGroupIdList());

		} catch (RestServiceException e) {
			if (e.getErrCode() == TopicResponseErrorCodes.ZT_EMP_LOB_ERR_009) {
				ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_009, e.getMessage(),
						e.getMessage());
				logger.error(er.getErrorCode(), e);
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(er)
						.type(MediaType.APPLICATION_JSON).build();
			}
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001), e.getMessage());
			logger.error(er.getErrorCode(), e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		if (noOfTopicGroupRelationsRowsCreated == 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		// return Response.status(HttpURLConnection.HTTP_OK)
		// .entity(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_INFO_002)
		// + employeeAssignedToLobCount)
		// .build();

		return Response.status(HttpURLConnection.HTTP_OK)
				.entity("{\"status\":\"" + HttpURLConnection.HTTP_OK + "\", \"message\": \""
						+ TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_INFO_002)
						+ noOfTopicGroupRelationsRowsCreated + "\"}")
				.build();
	}

	@DELETE
	@Path("/topics/{topicId}/groups/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response untagRelation(@PathParam("topicId") final int topicId, @PathParam("groupId") final int groupId,
			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {

		logger.info("untagRelation called ");
		logger.info("topicId: " + topicId + "groupId: " + groupId);

		return null;// EmployeeLobsService.getInstance().untagEmployeesFromLob(lobId,
					// employeeIdList, uriInfo, headers, servletResponse, req);

	}

}
