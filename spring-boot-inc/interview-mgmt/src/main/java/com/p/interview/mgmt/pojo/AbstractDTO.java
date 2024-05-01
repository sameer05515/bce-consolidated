/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.pojo;

import java.util.Date;

/**
 *
 * @author PREMENDRA
 */
public abstract class AbstractDTO {
	
	protected Date dateCreated;//creation_date
	protected Date dateLastModified;//last_updation_date
	protected int rating=1;//rating
	
	protected Date dateLastRead;
	protected int totalRead=0;
	private boolean personal=false;
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastModified() {
		return dateLastModified;
	}
	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getDateLastRead() {
		return dateLastRead;
	}
	public void setDateLastRead(Date dateLastRead) {
		this.dateLastRead = dateLastRead;
	}
	public int getTotalRead() {
		return totalRead;
	}
	public void setTotalRead(int totalRead) {
		this.totalRead = totalRead;
	}
	public boolean getPersonal() {
		return personal;
	}
	public void setPersonal(boolean personal) {
		this.personal = personal;
	}
    
}
