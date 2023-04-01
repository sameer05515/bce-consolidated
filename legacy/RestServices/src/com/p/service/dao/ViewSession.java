package com.p.service.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.p.service.exception.RestServiceException;
import com.p.service.pojo.View;




// TODO: Auto-generated Javadoc
/**
 * The Interface CoachingActionInterface.
 */
public interface ViewSession {
	
	/**
	 * Gets the coaching sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the coaching sessions grid
	 * @throws ZettaCoachingException the zetta coaching exception
	 */ 	
 	public List<View> getAll() throws RestServiceException;
 	
 	/**
	 * Gets the coaching sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the coaching sessions grid
	 * @throws ZettaCoachingException the zetta coaching exception
	 */ 
	public int create(View lob) throws RestServiceException ;

	/**
	 * Gets the coaching sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the coaching sessions grid
	 * @throws ZettaCoachingException the zetta coaching exception
	 */ 
	public boolean update(View lob) throws RestServiceException;

	/**
	 * Gets the coaching sessions grid.
	 *
	 * @param accountId the account id
	 * @param publishAccStatus the publish acc status
	 * @param dateFrom the date from
	 * @param dateTo the date to
	 * @return the coaching sessions grid
	 * @throws ZettaCoachingException the zetta coaching exception
	 */ 
	public View get(Integer id) throws RestServiceException; 	
	
}
