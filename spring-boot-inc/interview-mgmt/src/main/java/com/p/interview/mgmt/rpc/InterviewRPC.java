package com.p.interview.mgmt.rpc;

import java.util.List;
import java.util.Vector;

import com.p.interview.mgmt.bc.AnswerBC;
import com.p.interview.mgmt.bc.CategoryBC;
import com.p.interview.mgmt.bc.QuestionBC;
import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistory;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistoryReport;
import com.p.interview.mgmt.pojo.vo.CategoryVO;



public class InterviewRPC {

	private CategoryBC objCategoryBC;
	private QuestionBC objQuestionBC;
	private AnswerBC objAnswerBC;

	public InterviewRPC() {
		objCategoryBC = new CategoryBC();
		objQuestionBC = new QuestionBC();
		objAnswerBC = new AnswerBC();
	}

	public boolean isValidLogin(String loginName, String loginPassword) {

		if (loginName == null) {
			return false;
		}

		if (loginPassword == null) {
			return false;
		}
		if (loginName.equalsIgnoreCase("WISH")
				&& loginPassword.equalsIgnoreCase("123")) {
			return true;
		} else {
			return false;
		}
	}

	// Category
	public boolean categoryKeyExists(CategoryDTO objCategoryDTO)
			throws Exception {
		return objCategoryBC.keyExists(objCategoryDTO);
	}

	public void saveCategory(CategoryDTO objCategoryDTO) throws Exception {
		objCategoryBC.save(objCategoryDTO);
	}

	public void updateCategory(CategoryDTO objCategoryDTO) throws Exception {
		objCategoryBC.update(objCategoryDTO);
	}

	public CategoryDTO retrieveCategory(CategoryDTO objCategoryDTO) throws Exception {
		return objCategoryBC.retrieve(objCategoryDTO);
	}

	public Vector<CategoryDTO> fetchAllCategories() throws Exception {
		return objCategoryBC.fetchAll();
	}

	public String deleteCategory(int catId) throws Exception {
		return objCategoryBC.deleteCategory(catId);
	}

	// Question
	public boolean questionKeyExists(QuestionDTO objQuestionDTO)
			throws Exception {
		return objQuestionBC.keyExists(objQuestionDTO);
	}

	public void saveQuestion(QuestionDTO objQuestionDTO) throws Exception {
		objQuestionBC.save(objQuestionDTO);
	}
	
	public void addQuestionHistory(int linkedCategoryID,int id,String action) throws Exception {
		objQuestionBC.addQuestionHistory(linkedCategoryID,id,action);
	}
	//markPrivate
	public void markPrivate(int linkedCategoryID,int id) throws Exception {
		objQuestionBC.markPrivate(linkedCategoryID,id);
	}

	public void updateQuestion(QuestionDTO objQuestionDTO) throws Exception {
		objQuestionBC.update(objQuestionDTO);
	}

	public QuestionDTO retrieveQuestion(QuestionDTO objQuestionDTO) throws Exception {
		return objQuestionBC.retrieve(objQuestionDTO);
	}

	public Vector<QuestionDTO> fetchAllQuestionsByCategory(int linkedCategId)
			throws Exception {
		return objQuestionBC.fetchAllByCategory(linkedCategId);
	}

	public String deleteQuestion(QuestionDTO objQuestionDTO) throws Exception {
		return objQuestionBC.deleteQuestion(objQuestionDTO);
	}

	// Answer
	public boolean answerKeyExists(AnswerDTO objAnswerDTO) throws Exception {
		return objAnswerBC.keyExists(objAnswerDTO);
	}

	public void saveAnswer(AnswerDTO objAnswerDTO) throws Exception {
		objAnswerBC.save(objAnswerDTO);
	}

	public void updateAnswer(AnswerDTO objAnswerDTO) throws Exception {
		objAnswerBC.update(objAnswerDTO);
	}

	public AnswerDTO retrieveAnswer(AnswerDTO objAnswerDTO) throws Exception {
		return objAnswerBC.retrieve(objAnswerDTO);
	}

	public Vector<AnswerDTO> fetchAllAnswersByQuestion(
			QuestionDTO objQuestionDTO) throws Exception {
		return objAnswerBC.fetchAllByQuestion(objQuestionDTO);
	}

	public String deleteAnswer(AnswerDTO objAnswerDTO) throws Exception {
		return objAnswerBC.deleteAnswer(objAnswerDTO);
	}

	public List<CategQuestionHistory> getQuestionHistory(int linkedCategoryID, int id) throws Exception {
		return objQuestionBC.getQuestionHistory(linkedCategoryID,id);		
	}

	public List<CategQuestionHistoryReport> getAllQuestionsHistoryForAction(String action) {

		return objQuestionBC.getAllQuestionsHistoryForAction(action);
	}

	public List<CategoryVO> getAllCategoriesOptimized() throws Exception {
		
		return objCategoryBC.getAllCategoriesOptimized();
	}
}
