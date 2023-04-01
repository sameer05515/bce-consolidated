package com.p.sevice.common;

import org.apache.log4j.Logger;

import com.p.service.dao.GroupSession;
import com.p.service.dao.GroupViewRelationSession;
import com.p.service.dao.TopicGroupRelationSession;
import com.p.service.dao.TopicSession;
import com.p.service.dao.ViewSession;
import com.p.service.dao.ViewTopicRelationSession;
import com.p.service.daoimpl.GroupSessionImpl;
import com.p.service.daoimpl.GroupViewRelationSessionImpl;
import com.p.service.daoimpl.TopicGroupRelationSessionImpl;
import com.p.service.daoimpl.TopicSessionImpl;
import com.p.service.daoimpl.ViewSessionImpl;
import com.p.service.daoimpl.ViewTopicRelationSessionImpl;

public final class DAOFactory {
	
	/** The Constant logger. */
	private static final Logger logger = Logger
			.getLogger(DAOFactory.class.getName());
	private DAOFactory(){
		
	}
	
	public static TopicSession getTopicSessionInterface(){
		logger.debug("Created new instance of "+TopicSession.class.getName());
		return new TopicSessionImpl();
	}
	
	public static GroupSession getGroupSessionInterface(){
		logger.debug("Created new instance of "+GroupSession.class.getName());
		return new GroupSessionImpl();
	}
	
	public static ViewSession getViewSessionInterface(){
		logger.debug("Created new instance of "+ViewSession.class.getName());
		return new ViewSessionImpl();
	}
	
	public static TopicGroupRelationSession getTopicGroupRelationSessionInterface(){
		logger.debug("Created new instance of "+TopicGroupRelationSession.class.getName());
		return new TopicGroupRelationSessionImpl();
	}
	
	public static GroupViewRelationSession getGroupViewRelationSessionInterface(){
		logger.debug("Created new instance of "+GroupViewRelationSession.class.getName());
		return new GroupViewRelationSessionImpl();
	}
	
	public static ViewTopicRelationSession getViewTopicRelationSessionInterface(){
		logger.debug("Created new instance of "+ViewTopicRelationSession.class.getName());
		return new ViewTopicRelationSessionImpl();
	}

}
