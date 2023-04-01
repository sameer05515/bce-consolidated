/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.bc;

import java.util.List;
import java.util.Vector;

import com.p.interview.mgmt.dao.QuestionDAO;
import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistory;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistoryReport;

/**
 * 
 * @author PREMENDRA
 */
public class QuestionBC extends AbstractBC {
	QuestionDAO objQuestionDAO;

	AnswerBC objAnswerBC = new AnswerBC();

	public QuestionBC() {
		objQuestionDAO = new QuestionDAO();
	}

	public boolean keyExists(QuestionDTO objQuestionDTO) throws Exception {
		return objQuestionDAO.keyExists(objQuestionDTO);
	}

	public void save(QuestionDTO objQuestionDTO) throws Exception {
		objQuestionDAO.saveDetails(objQuestionDTO);
	}
	
	public void addQuestionHistory(int linkedCategoryID,int id,String action) throws Exception {
		objQuestionDAO.addQuestionHistoryDetails(linkedCategoryID,id,action);
		
	}
	
	public void markPrivate(int linkedCategoryID,int id) throws Exception {
		objQuestionDAO.markPrivate(linkedCategoryID,id);
		
	}

	public void update(QuestionDTO objQuestionDTO) throws Exception {
		objQuestionDAO.updateDetails(objQuestionDTO);
	}

	public QuestionDTO retrieve(QuestionDTO objQuestionDTO) throws Exception {

		QuestionDTO questionDTO = objQuestionDAO.retrieve(objQuestionDTO);

		Vector<AnswerDTO> objAnswerDTOs = objAnswerBC.fetchAllByQuestion(questionDTO);
		questionDTO.setAnswers(objAnswerDTOs);

		return questionDTO;
	}

	public Vector<QuestionDTO> fetchAllByCategory(int linkedCategId) throws Exception {
		Vector<QuestionDTO> objQuestionDTOs = objQuestionDAO.fetchAllByCategory(linkedCategId);

		for (QuestionDTO questionDTO : objQuestionDTOs) {
			Vector<AnswerDTO> objAnswerDTOs = objAnswerBC.fetchAllByQuestion(questionDTO);
			questionDTO.setAnswers(objAnswerDTOs);
		}
		return objQuestionDTOs;
	}

	public String deleteQuestion(QuestionDTO objQuestionDTO) throws Exception {
		String msg = "";
		AnswerBC objAnswerBC = new AnswerBC();
		Vector<AnswerDTO> objAnswerDTOs = objAnswerBC.fetchAllByQuestion(objQuestionDTO);
		for (AnswerDTO objAnswerDTO : objAnswerDTOs) {
			msg += "\n" + objAnswerBC.deleteAnswer(objAnswerDTO);
		}
		msg += objQuestionDAO.deleteQuestion(objQuestionDTO);
		return msg;
	}

	public List<CategQuestionHistory> getQuestionHistory(int linkedCategoryID, int id) throws Exception {
		return objQuestionDAO.getQuestionHistory(linkedCategoryID,id);
	}

	public List<CategQuestionHistoryReport> getAllQuestionsHistoryForAction(String action) {
		return objQuestionDAO.getAllQuestionsHistoryForAction(action);
	}

	
}
