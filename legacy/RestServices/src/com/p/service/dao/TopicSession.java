package com.p.service.dao;

import java.util.List;

import com.p.service.exception.RestServiceException;
import com.p.service.pojo.Topic;
import com.p.service.pojo.TopicHistory;
import com.p.service.pojo.TopicHistoryReport;




// TODO: Auto-generated Javadoc
/**
 * The Interface ActionInterface.
 */
public interface TopicSession {
	
	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 	
 	public List<Topic> getAll() throws RestServiceException;
 	
 	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	public int create(Topic lob) throws RestServiceException ;

	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	public boolean update(Topic lob) throws RestServiceException;

	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	public Topic get(Integer id) throws RestServiceException;
	
	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	public Topic get(String uniqueStrid) throws RestServiceException;
	
	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	//public boolean addRead(Topic id) throws RestServiceException;
	
	public boolean addTopicHistory(Topic id, String action) throws RestServiceException;
	
	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	//public List<TopicReads> getReads(Topic id) throws RestServiceException;

	public List<TopicHistory> getTopicHistory(Topic id, String action) throws RestServiceException;

	/**
	 * Gets the Topic sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the Topic sessions grid
	 * @throws Exception the  Topic exception
	 */ 
	public List<TopicHistoryReport> getTopicHistoryReport(String action) throws RestServiceException;

	
	
}
