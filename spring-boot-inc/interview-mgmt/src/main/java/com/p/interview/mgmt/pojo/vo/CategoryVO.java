package com.p.interview.mgmt.pojo.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.p.interview.mgmt.pojo.QuestionDTO;

public class CategoryVO {
	private int catID;//cat_id
    private String catgoryName;//cat_name
    private Date dateCreated;//creation_date
    private Date dateLastModified;//last_updation_date
    private int rating=1;//rating	
    private Date dateLastRead;
    private int totalRead=0;
	private boolean personal=false;
	private int totalQuestionsCount=0;
	
	private List<QuestionDTO> questions=new ArrayList<QuestionDTO>();
	
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	public String getCatgoryName() {
		return catgoryName;
	}
	public void setCatgoryName(String catgoryName) {
		this.catgoryName = catgoryName;
	}
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
	public boolean isPersonal() {
		return personal;
	}
	public void setPersonal(boolean personal) {
		this.personal = personal;
	}
	public int getTotalQuestionsCount() {
		return totalQuestionsCount;
	}
	public void setTotalQuestionsCount(int totalQuestionsCount) {
		this.totalQuestionsCount = totalQuestionsCount;
	}
	@Override
	public String toString() {
		return "CategoryVO [catID=" + catID + ", catgoryName=" + catgoryName + ", dateCreated=" + dateCreated
				+ ", dateLastModified=" + dateLastModified + ", rating=" + rating + ", dateLastRead=" + dateLastRead
				+ ", totalRead=" + totalRead + ", personal=" + personal + ", totalQuestionsCount=" + totalQuestionsCount
				+ ", questions=" + questions + "]";
	}
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
}
