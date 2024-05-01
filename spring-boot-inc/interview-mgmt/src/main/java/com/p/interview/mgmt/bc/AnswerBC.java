/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.bc;

import java.util.Vector;

import com.p.interview.mgmt.dao.AnswerDAO;
import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;


/**
 * 
 * @author PREMENDRA
 */
public class AnswerBC extends AbstractBC {
	AnswerDAO objAnswerDAO;

	public AnswerBC() {
		objAnswerDAO = new AnswerDAO();
	}

	public boolean keyExists(AnswerDTO objAnswerDTO) throws Exception {
		return objAnswerDAO.keyExists(objAnswerDTO);
	}

	public void save(AnswerDTO objAnswerDTO) throws Exception {
		objAnswerDAO.save(objAnswerDTO);
	}

	public void update(AnswerDTO objAnswerDTO) throws Exception {
		objAnswerDAO.update(objAnswerDTO);
	}

	public AnswerDTO retrieve(AnswerDTO objAnswerDTO) throws Exception {
		return objAnswerDAO.retrieve(objAnswerDTO);
	}

	public Vector<AnswerDTO> fetchAllByQuestion(QuestionDTO objQuestionDTO) throws Exception {
		return objAnswerDAO.fetchAllByQuestion(objQuestionDTO);
	}
	
	public String deleteAnswer(AnswerDTO objAnswerDTO) throws Exception {
		return objAnswerDAO.deleteAnswer(objAnswerDTO);
	}
}
