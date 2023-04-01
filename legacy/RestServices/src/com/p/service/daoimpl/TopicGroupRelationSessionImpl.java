package com.p.service.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.p.service.dao.TopicGroupRelationSession;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.Group;
import com.p.service.pojo.Topic;
import com.p.service.pojo.TopicGroupRelation;
import com.p.sevice.common.HibernateSessionFactory;
import com.p.sevice.util.TopicResponseErrorCodes;
import com.p.sevice.util.TopicUtil;

public class TopicGroupRelationSessionImpl implements TopicGroupRelationSession {

	private static final Logger logger = Logger.getLogger(TopicGroupRelationSessionImpl.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TopicGroupRelation> getAll() throws RestServiceException{
		
		Session session=null;
		List<TopicGroupRelation> accounts=null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin();
			Query hqlQuery=session.createQuery("from TopicGroupRelation");
			accounts=hqlQuery.list();
			
			session.getTransaction().commit();	
		} catch (Exception e) {
			throw new RestServiceException(e); 
		} finally {
			session.close();
		}
		return accounts;
	
	}

	@Override
	public int getTopicCountForGroup(Integer groupId) throws RestServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGroupCountForTopic(Integer topicId) throws RestServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	// topicIdList
	public List<TopicGroupRelation> getTopicGroupRelationForTopic(List<Integer> topicIdList)
			throws RestServiceException {
		logger.info("Entering method getTopicGroupRelationForTopic(List<Integer> lobIdList)");

		// Validate input parameters for the method. Return if invalid. Proceed
		// if valid.
		if (topicIdList == null || topicIdList.size() == 0) {
			logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					new Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
			throw new RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
		}

		Session session = null;
		List<TopicGroupRelation> topicGroupRelationList = null;
		// List<EmployeesLobRowJson> employeesLobRowJsonList=null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin(); //// changed on 8 sept 2015
			Query hqlQuery = session.createQuery("from TopicGroupRelation where topics.id in (:topicIdList)");
			hqlQuery.setParameterList("topicIdList", topicIdList);

			topicGroupRelationList = hqlQuery.list();
			for (TopicGroupRelation tr : topicGroupRelationList) {
				tr.getTopics();
				tr.getGroups();
			}
			// employeesLobRowJsonList=prepareEmployeesLobRowJsonList(employeeLobsList);

			logger.info("numberOfRecorsFetched:" + topicGroupRelationList);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}

		logger.info("Exiting method getTopicGroupRelationForTopic(List<Integer> lobIdList)");
		return topicGroupRelationList;
	}

	@Override
	public List<TopicGroupRelation> getTopicGroupRelationForGroup(List<Integer> groupIdList)
			throws RestServiceException {
		logger.info("Entering method getTopicGroupRelationForGroup(List<Integer> groupIdList)");

		// Validate input parameters for the method. Return if invalid. Proceed
		// if valid.groupIdList
		if (groupIdList == null || groupIdList.size() == 0) {
			logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					new Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
			throw new RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
		}

		Session session = null;
		List<TopicGroupRelation> employeeLobsList = null;
		// List<EmployeesLobRowJson> employeesLobRowJsonList=null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin(); //// changed on 8 sept 2015
			Query hqlQuery = session.createQuery("from TopicGroupRelation where groups.id in (:groupIdList)");
			hqlQuery.setParameterList("groupIdList", groupIdList);

			employeeLobsList = hqlQuery.list();
			for (TopicGroupRelation tr : employeeLobsList) {
				tr.getTopics();
				tr.getGroups();
			}
			// employeesLobRowJsonList=prepareEmployeesLobRowJsonList(employeeLobsList);

			logger.info("numberOfRecorsFetched:" + employeeLobsList);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}

		logger.info("Exiting method getTopicGroupRelationForGroup(List<Integer> lobIdList)");
		return employeeLobsList;
	}

	// @Override
	// public int addTopicsToGroup(int groupId, List<Integer> topicIdList)
	// throws RestServiceException {
	//
	// logger.info("Entering method addTopicsToGroup(int groupId,List<Integer>
	// topicIdList)");
	//
	// // Validate input parameters for the method. Return if invalid. Proceed
	// // if valid.
	// if (topicIdList == null || topicIdList.size() == 0 || groupId <= 0) {
	// logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
	// new
	// Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
	// throw new
	// RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
	// }
	//
	// List<Integer> groupIdList = new ArrayList<Integer>();
	// groupIdList.add(groupId);
	//
	// int noOfSavedEmployeeLobs = addTopicsToMultipleGroups(groupIdList,
	// topicIdList);
	//
	// logger.info("Exiting method addTopicsToGroup(int groupId,List<Integer>
	// topicIdList)");
	// return noOfSavedEmployeeLobs;
	//
	// }

	@Override
	public int addTopicsToGroups(List<Integer> topicIdList, List<Integer> groupIdList) throws RestServiceException {

		logger.info("Entering method addTopicsToGroups(List<Integer> topicIdList, List<Integer> groupIdList)");

		// Validate input parameters for the method. Return if invalid. Proceed
		// if valid.
		if (topicIdList == null || topicIdList.size() == 0 || groupIdList == null || groupIdList.size() == 0) {
			logger.error(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006,
					new Throwable(TopicUtil.getErrMsg(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006)));
			throw new RestServiceException(TopicResponseErrorCodes.ZT_EMP_LOB_ERR_006);
		}

		List<TopicGroupRelation> topicGroupRelationListToSave = new ArrayList<TopicGroupRelation>();

		for (Integer topicId : topicIdList) {
			Topic topic = new Topic();
			topic.setId(topicId);
			for (Integer groupId : groupIdList) {
				Group group = new Group();
				group.setId(groupId);
				topicGroupRelationListToSave.add(new TopicGroupRelation(topic, group));
			}

		}

		Session session = null;
		Transaction tx = null;
		List<Integer> savedNewTopicGroupRelationList = new ArrayList<Integer>();
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			tx = session.beginTransaction();
			for (TopicGroupRelation topicGroupRelationToSave : topicGroupRelationListToSave) {

				savedNewTopicGroupRelationList.add((int) session.save(topicGroupRelationToSave));
			}

			tx.commit();

			logger.info("savedNewTopicGroupRelationList.size(): " + savedNewTopicGroupRelationList.size());
		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}

		logger.info("Exiting method addTopicsToGroups(List<Integer> topicIdList, List<Integer> groupIdList)");
		return savedNewTopicGroupRelationList.size();
	}

	@Override
	public int untagRelation(int groupId, int topicId) throws RestServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

}