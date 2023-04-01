package com.p.interview.mgmt.pojo;

import java.util.Date;
import java.util.Vector;

public class QuestionJsonResponseForReport {
	
	private int categoryId;
	private String categoryName;
	private Date categoryDateCreated;//creation_date
	private Date categoryDateLastModified;//last_updation_date
	private int categoryRating=1;//rating
	
	private Date categoryDateLastRead;
	private int categoryTotalRead=0;
	private boolean categoryPersonal=false;
	
	
	private int questionId;
	private String questionName;
	private Date dateCreated;//creation_date
	private Date dateLastModified;//last_updation_date
	private int rating=1;//rating
	
	private Date dateLastRead;
	private int totalRead=0;
	private boolean personal=false;
	
	private Vector<AnswerDTO> answers;

	/**
	 * 
	 */
	public QuestionJsonResponseForReport(CategoryDTO categoryDTO,QuestionDTO questionDTO) {
		super();
		
		this.categoryId = categoryDTO.getCatID();
		this.categoryName = categoryDTO.getCatgoryName();
		this.categoryDateCreated = categoryDTO.getDateCreated();
		this.categoryDateLastModified = categoryDTO.getDateLastModified();
		this.categoryRating = categoryDTO.getRating();
		this.categoryDateLastRead = categoryDTO.getDateLastRead();
		this.categoryTotalRead = categoryDTO.getTotalRead();
		this.categoryPersonal = categoryDTO.getPersonal();
		this.questionId = questionDTO.getQuestionID();
		this.questionName = questionDTO.getQuestion();
		this.dateCreated = questionDTO.getDateCreated();
		this.dateLastModified = questionDTO.dateLastModified;
		this.rating = questionDTO.getRating();
		this.dateLastRead = questionDTO.getDateLastRead();
		this.totalRead = questionDTO.getTotalRead();
		this.personal = questionDTO.getPersonal();
		this.answers = questionDTO.getAnswers();
		
	}

	/**
	 * @param categoryId
	 * @param categoryName
	 * @param categoryDateCreated
	 * @param categoryDateLastModified
	 * @param categoryRating
	 * @param categoryDateLastRead
	 * @param categoryTotalRead
	 * @param categoryPersonal
	 * @param questionId
	 * @param questionName
	 * @param dateCreated
	 * @param dateLastModified
	 * @param rating
	 * @param dateLastRead
	 * @param totalRead
	 * @param personal
	 * @param answers
	 */
	public QuestionJsonResponseForReport(int categoryId, String categoryName, Date categoryDateCreated,
			Date categoryDateLastModified, int categoryRating, Date categoryDateLastRead, int categoryTotalRead,
			boolean categoryPersonal, int questionId, String questionName, Date dateCreated, Date dateLastModified,
			int rating, Date dateLastRead, int totalRead, boolean personal, Vector<AnswerDTO> answers) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDateCreated = categoryDateCreated;
		this.categoryDateLastModified = categoryDateLastModified;
		this.categoryRating = categoryRating;
		this.categoryDateLastRead = categoryDateLastRead;
		this.categoryTotalRead = categoryTotalRead;
		this.categoryPersonal = categoryPersonal;
		this.questionId = questionId;
		this.questionName = questionName;
		this.dateCreated = dateCreated;
		this.dateLastModified = dateLastModified;
		this.rating = rating;
		this.dateLastRead = dateLastRead;
		this.totalRead = totalRead;
		this.personal = personal;
		this.answers = answers;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Date getCategoryDateCreated() {
		return categoryDateCreated;
	}

	public Date getCategoryDateLastModified() {
		return categoryDateLastModified;
	}

	public int getCategoryRating() {
		return categoryRating;
	}

	public Date getCategoryDateLastRead() {
		return categoryDateLastRead;
	}

	public int getCategoryTotalRead() {
		return categoryTotalRead;
	}

	public boolean isCategoryPersonal() {
		return categoryPersonal;
	}

	public int getQuestionId() {
		return questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public int getRating() {
		return rating;
	}

	public Date getDateLastRead() {
		return dateLastRead;
	}

	public int getTotalRead() {
		return totalRead;
	}

	public boolean isPersonal() {
		return personal;
	}

	public Vector<AnswerDTO> getAnswers() {
		return answers;
	}
	
	
	
	
	

}
