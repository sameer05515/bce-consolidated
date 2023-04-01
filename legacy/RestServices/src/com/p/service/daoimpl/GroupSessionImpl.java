package com.p.service.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.p.service.dao.GroupSession;
import com.p.service.exception.RestServiceException;
import com.p.service.pojo.Group;
import com.p.sevice.common.HibernateSessionFactory;

public class GroupSessionImpl implements GroupSession {

	private static final Logger logger = Logger.getLogger(GroupSessionImpl.class.getName());

	@Override
	public int create(Group group) throws RestServiceException {
		Session session = null;
		Transaction tx = null;
		int id = 0;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			tx = session.beginTransaction();
			id = (int) session.save(group);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public boolean update(Group group) throws RestServiceException {
		Session session = null;
		Transaction tx = null;
		boolean updateSuccess = false;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			logger.debug("going to update Group" + group);
			tx = session.beginTransaction();
			session.update(group);
			tx.commit();
			updateSuccess = true;
		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
			throw new RestServiceException(e);
		} finally {
			session.close();
		}
		return updateSuccess;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAll() throws RestServiceException {

		Session session = null;
		List<Group> groups = null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin();
			Query hqlQuery = session.createQuery("from Group");
			groups = hqlQuery.list();

			session.getTransaction().commit();
		} catch (Exception e) {
			throw new RestServiceException(e);
		} finally {
			session.close();
		}
		return groups;

	}

	@Override
	public Group get(Integer id) throws RestServiceException {
		Session session = null;
		Group groups = null;
		try {
			SessionFactory hsf = HibernateSessionFactory.getSessionFactory();
			session = hsf.openSession();
			session.getTransaction().begin();

			groups = (Group) session.createCriteria(Group.class).add(Restrictions.eq("id", id)).uniqueResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			throw new RestServiceException(e);
		} finally {
			session.close();
		}
		return groups;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new GroupSessionImpl().getAll());
		} catch (RestServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}