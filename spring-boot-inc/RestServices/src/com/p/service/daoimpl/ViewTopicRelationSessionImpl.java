package com.p.service.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.p.service.dao.ViewTopicRelationSession;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.TopicGroupRelation;
import com.p.service.pojo.ViewTopicRelation;
import com.p.sevice.common.HibernateSessionFactory;

public class ViewTopicRelationSessionImpl implements ViewTopicRelationSession {

	private static final Logger logger = Logger.getLogger(ViewTopicRelationSessionImpl.class.getName());

	@Override
	public List<ViewTopicRelation> getAll() throws RestServiceException {
		
		Session session=null;
		List<ViewTopicRelation> accounts=null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin();
			Query hqlQuery=session.createQuery("from ViewTopicRelation");
			accounts=hqlQuery.list();
			
			session.getTransaction().commit();	
		} catch (Exception e) {
			throw new RestServiceException(e); 
		} finally {
			session.close();
		}
		return accounts;
	
	}

	
}