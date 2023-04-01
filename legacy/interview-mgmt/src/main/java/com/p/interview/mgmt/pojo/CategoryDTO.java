/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.pojo;

import java.util.Vector;


/**
 *
 * @author PREMENDRA
 */
public class CategoryDTO extends AbstractDTO{
    private int catID;//cat_id
    private String catgoryName;//cat_name
    
    private Vector<QuestionDTO> questions;
    private int totalQuestionsCount=0;
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
	public Vector<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(Vector<QuestionDTO> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryDTO [catID=");
		builder.append(catID);
		builder.append(", ");
		if (catgoryName != null) {
			builder.append("catgoryName=");
			builder.append(catgoryName);
			builder.append(", ");
		}
		if (questions != null) {
			builder.append("questions=");
			builder.append(questions);
			builder.append(", ");
		}
		if (dateCreated != null) {
			builder.append("dateCreated=");
			builder.append(dateCreated);
			builder.append(", ");
		}
		if (dateLastModified != null) {
			builder.append("dateLastModified=");
			builder.append(dateLastModified);
			builder.append(", ");
		}
		builder.append("rating=");
		builder.append(rating);
		builder.append(", ");
		if (dateLastRead != null) {
			builder.append("dateLastRead=");
			builder.append(dateLastRead);
			builder.append(", ");
		}
		builder.append("totalRead=");
		builder.append(totalRead);
		builder.append("]");
		return builder.toString();
	}
	public int getTotalQuestionsCount() {
		return totalQuestionsCount;
	}
	public void setTotalQuestionsCount(int totalQuestionsCount) {
		this.totalQuestionsCount = totalQuestionsCount;
	}
    
    
    
    
}
