package com.p.interview.mgmt.pojo.vo;

import java.util.ArrayList;
import java.util.List;

import com.p.interview.mgmt.pojo.QuestionDTO;



public class CategQuestionReadResponse {

	//private int questionID;//ques_id
    private int linkedCatID;//linked_cat_id
	private int questionID;
	private QuestionDTO question;
	private int count = 0;
	private List<CategQuestionHistory> reads = new ArrayList<>();

	public CategQuestionReadResponse(int linkedCatID,int questionID, QuestionDTO question, List<CategQuestionHistory> reads) {
		super();
		this.setLinkedCatID(linkedCatID);
		this.questionID = questionID;
		this.question = question;
		if (reads != null && reads.size() > 0) {
			this.count = reads.size();
			this.reads = reads;
		}
	}

	public CategQuestionReadResponse() {
		super();
	}

	public int getLinkedCatID() {
		return linkedCatID;
	}

	public void setLinkedCatID(int linkedCatID) {
		this.linkedCatID = linkedCatID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public QuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}

	public int getCount() {
		if (reads != null && reads.size() > 0)
			this.count=reads.size();			
			return count;
	}

	// public void setCount(int count) {
	// this.count = count;
	// }
	public List<CategQuestionHistory> getReads() {
		return reads;
	}

	public void setReads(List<CategQuestionHistory> reads) {
		if (reads != null && reads.size() > 0)
			this.reads = reads;
	}

}
