package com.p.service.restresource;

import java.net.HttpURLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.p.service.dao.GroupViewRelationSession;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.GroupViewRelation;
import com.p.service.vo.GroupViewRelationResourceVO;
import com.p.sevice.common.DAOFactory;
import com.p.sevice.common.ErrorResponse;
import com.p.sevice.util.TopicResponseErrorCodes;
import com.p.sevice.util.TopicUtil;

/**
 * The Class TopicGroupRelationResource.
 */
@Path("gvservices")
public class GroupViewRelationResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(GroupViewRelationResource.class.getName());

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGroupsToViews(GroupViewRelationResourceVO groupViewRelationResourceVO,
			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {
		logger.info("addGroupsToViews called");
		logger.info("groupViewRelationResourceVO: " + groupViewRelationResourceVO);
		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (groupViewRelationResourceVO == null || groupViewRelationResourceVO.getViewIdList() == null
				|| groupViewRelationResourceVO.getViewIdList().size() <= 0
				|| groupViewRelationResourceVO.getGroupIdList() == null
				|| groupViewRelationResourceVO.getGroupIdList().size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		int noOfGroupViewRelationsRowsCreated = 0;
		try {

			GroupViewRelationSession topicGroupRelationSessionInterface = DAOFactory
					.getGroupViewRelationSessionInterface();

			noOfGroupViewRelationsRowsCreated = topicGroupRelationSessionInterface.addGroupsToViews(
					 groupViewRelationResourceVO.getGroupIdList(),groupViewRelationResourceVO.getViewIdList());

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

		if (noOfGroupViewRelationsRowsCreated == 0) {
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
						+ noOfGroupViewRelationsRowsCreated + "\"}")
				.build();
	}
	
	@GET
	@Path("/groups")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getGroupViewRelationListForGroup(@QueryParam("id") final List<Integer> groupIdList,
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

		List<GroupViewRelation> listGroupViewRelation;
		try {

			GroupViewRelationSession objGroupViewRelationSession = DAOFactory.getGroupViewRelationSessionInterface();

			listGroupViewRelation = objGroupViewRelationSession.getGroupViewRelationListForGroup(groupIdList);

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

		if (listGroupViewRelation == null || listGroupViewRelation.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		logger.info("got response : " + listGroupViewRelation);

		return Response.status(HttpURLConnection.HTTP_OK).entity(listGroupViewRelation).build();

	}
	
	@GET
	@Path("/views")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response getGroupViewRelationForView(@QueryParam("id") final List<Integer> viewIdList,
			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {

		logger.info("getGroupViewRelationForView called ");
		logger.info("topicIdList: " + viewIdList);

		/**
		 * Validate input parameters for the method. Return if invalid. Proceed
		 * if valid.
		 */
		if (viewIdList == null || viewIdList.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					TopicUtil.getErrMsg("Invalid viewIdList provided : viewIdList " + viewIdList));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		List<GroupViewRelation> listGroupViewRelation;
		try {

			GroupViewRelationSession objGroupViewRelationSession = DAOFactory.getGroupViewRelationSessionInterface();

			listGroupViewRelation = objGroupViewRelationSession.getGroupViewRelationForView(viewIdList);

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

		if (listGroupViewRelation == null || listGroupViewRelation.size() <= 0) {
			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001,
					TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_001));
			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
					.build();
		}

		logger.info("got response : " + listGroupViewRelation);

		return Response.status(HttpURLConnection.HTTP_OK).entity(listGroupViewRelation).build();

	}
	

//	@GET
//	@Path("/topics/{id}/count")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getGroupCountForTopic(@PathParam("id") final int topicId, @Context UriInfo uriInfo,
//			@Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
//			@Context HttpServletRequest servletRequest) {
//
//		logger.info("getTopicGroupRelationForTopic called ");
//		logger.info("topicId: " + topicId);
//
//		/**
//		 * Validate input parameters for the method. Return if invalid. Proceed
//		 * if valid.
//		 */
//		if (topicId <= 0) {
//			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
//					TopicUtil.getErrMsg("Invalid topicId provided : topicId " + topicId));
//			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
//					.build();
//		}
//
//		return null;// EmployeeLobsService.getInstance().getEmployeeLobMappingsForLob(lobList,
//					// uriInfo, headers, servletResponse, req);
//
//	}
//
//	@GET
//	@Path("/groups/{id}/count")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getTopicCountForGroup(@PathParam("id") final int groupId, @Context UriInfo uriInfo,
//			@Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
//			@Context HttpServletRequest servletRequest) {
//
//		logger.info("getTopicGroupRelationForGroup called ");
//		logger.info("groupId: " + groupId);
//
//		/**
//		 * Validate input parameters for the method. Return if invalid. Proceed
//		 * if valid.
//		 */
//		if (groupId <= 0) {
//			ErrorResponse er = new ErrorResponse(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
//					TopicUtil.getErrMsg("Invalid groupId provided : groupId " + groupId));
//			return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(er).type(MediaType.APPLICATION_JSON)
//					.build();
//		}
//
//		return null;// EmployeeLobsService.getInstance().getEmployeeLobMappingsForLob(lobList,
//					// uriInfo, headers, servletResponse, req);
//
//	}
//
//
//
//
//
//
//
//	@DELETE
//	@Path("/topics/{topicId}/groups/{groupId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response untagRelation(@PathParam("topicId") final int topicId, @PathParam("groupId") final int groupId,
//			@Context UriInfo uriInfo, @Context HttpHeaders headers, @Context HttpServletResponse servletResponse,
//			@Context HttpServletRequest servletRequest) {
//
//		logger.info("untagRelation called ");
//		logger.info("topicId: " + topicId + "groupId: " + groupId);
//
//		return null;// EmployeeLobsService.getInstance().untagEmployeesFromLob(lobId,
//					// employeeIdList, uriInfo, headers, servletResponse, req);
//
//	}

}
