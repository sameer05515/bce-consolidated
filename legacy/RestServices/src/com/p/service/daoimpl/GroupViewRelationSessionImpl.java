package com.p.service.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.p.service.dao.GroupViewRelationSession;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.Group;
import com.p.service.pojo.GroupViewRelation;
import com.p.service.pojo.View;
import com.p.sevice.common.HibernateSessionFactory;
import com.p.sevice.util.TopicResponseErrorCodes;
import com.p.sevice.util.TopicUtil;

public class GroupViewRelationSessionImpl implements GroupViewRelationSession {

	private static final Logger logger = Logger.getLogger(GroupViewRelationSessionImpl.class.getName());

	@Override
	public List<GroupViewRelation> getAll() throws RestServiceException {

		Session session = null;
		List<GroupViewRelation> accounts = null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin();
			Query hqlQuery = session.createQuery("from GroupViewRelation");
			accounts = hqlQuery.list();

			session.getTransaction().commit();
		} catch (Exception e) {
			throw new RestServiceException(e);
		} finally {
			session.close();
		}
		return accounts;

	}

	@Override
	public int addGroupsToViews(List<Integer> groupIdList, List<Integer> viewIdList) throws RestServiceException {

		logger.info("Entering method addGroupsToViews(List<Integer> groupIdList, List<Integer> viewIdList)");

		// Validate input parameters for the method. Return if invalid. Proceed
		// if valid.
		if (groupIdList == null || groupIdList.size() == 0 || viewIdList == null || viewIdList.size() == 0) {
			logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					new Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
			throw new RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
		}

		List<GroupViewRelation> groupViewRelationListToSave = new ArrayList<GroupViewRelation>();

		for (Integer groupId : groupIdList) {
			Group group = new Group();
			group.setId(groupId);
			for (Integer viewId : viewIdList) {
				View view = new View();
				view.setId(viewId);
				groupViewRelationListToSave.add(new GroupViewRelation(group, view));
			}

		}

		Session session = null;
		Transaction tx = null;
		List<Integer> savedNewGroupViewRelationList = new ArrayList<Integer>();
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			tx = session.beginTransaction();
			for (GroupViewRelation topicGroupRelationToSave : groupViewRelationListToSave) {

				savedNewGroupViewRelationList.add((int) session.save(topicGroupRelationToSave));
			}

			tx.commit();

			logger.info("savedNewGroupViewRelationList.size(): " + savedNewGroupViewRelationList.size());
		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}

		logger.info("Exiting method addGroupsToViews(List<Integer> groupIdList, List<Integer> viewIdList)");
		return savedNewGroupViewRelationList.size();
	}

	@Override
	public List<GroupViewRelation> getGroupViewRelationListForGroup(List<Integer> groupIdList)
			throws RestServiceException {
		logger.info("Entering method getGroupViewRelationListForGroup(List<Integer> groupIdList)");

		// Validate input parameters for the method. Return if invalid. Proceed
		// if valid.groupIdList
		if (groupIdList == null || groupIdList.size() == 0) {
			logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					new Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
			throw new RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
		}

		Session session = null;
		List<GroupViewRelation> groupViewRelationList = null;
		// List<EmployeesLobRowJson> employeesLobRowJsonList=null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin(); //// changed on 8 sept 2015
			Query hqlQuery = session.createQuery("from GroupViewRelation where groups.id in (:groupIdList)");
			hqlQuery.setParameterList("groupIdList", groupIdList);

			groupViewRelationList = hqlQuery.list();
			for (GroupViewRelation tr : groupViewRelationList) {
				tr.getViews();
				tr.getGroups();
			}
			// employeesLobRowJsonList=prepareEmployeesLobRowJsonList(employeeLobsList);

			logger.info("numberOfRecorsFetched:" + groupViewRelationList);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}

		logger.info("Exiting method getGroupViewRelationListForGroup(List<Integer> lobIdList)");
		return groupViewRelationList;
	}

	@Override
	public List<GroupViewRelation> getGroupViewRelationForView(List<Integer> viewIdList) throws RestServiceException {
		logger.info("Entering method getGroupViewRelationForView(List<Integer> viewIdList)");

		// Validate input parameters for the method. Return if invalid. Proceed
		// if valid.groupIdList
		//
		//
		if (viewIdList == null || viewIdList.size() == 0) {
			logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					new Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
			throw new RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
		}

		Session session = null;
		List<GroupViewRelation> groupViewRelationList = null;
		// List<EmployeesLobRowJson> employeesLobRowJsonList=null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin(); //// changed on 8 sept 2015
			Query hqlQuery = session.createQuery("from GroupViewRelation where views.id in (:viewIdList)");
			hqlQuery.setParameterList("viewIdList", viewIdList);

			groupViewRelationList = hqlQuery.list();
			for (GroupViewRelation tr : groupViewRelationList) {
				tr.getViews();
				tr.getGroups();
			}
			// employeesLobRowJsonList=prepareEmployeesLobRowJsonList(employeeLobsList);

			logger.info("numberOfRecorsFetched:" + groupViewRelationList);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}

		logger.info("Exiting method getGroupViewRelationForView(List<Integer> viewIdList)");
		return groupViewRelationList;
	}

}