package com.p.interview.mgmt.xml;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;
import com.p.interview.mgmt.xml.ProjectConfigConstants.Category;

public class InterviewXMLUtility {

	/**
	 * @param xmlFileName
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */

	public enum StringComparison {
		STARTS_WITH("starts-with"), CONTAINS("contains");

		private String name;
		// private Type type;

		StringComparison(String name) {
			this.name = name;
			// this.type = type;
		}

		public String getName() {
			return name;
		}
	}

	public InterviewXMLUtility(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
		super();
		this.xmlFileName = xmlFileName;
		System.out.println("XML file going to be parsed : " + xmlFileName);
		utility = XMLUtility.getInstance(xmlFileName, false);
	}

	// public void refreshXML() throws Exception{
	//
	//
	// }

	/**
	 * --- Made the "xmlFileName" variable non-static, so that code can be used
	 * for several xml file ---
	 */
	public String xmlFileName = "C:/Users/VINU/Desktop/interview-xml/interview.xml";
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");

	XMLUtility utility = null;

	InterviewRPC intMgmtRPC = new InterviewRPC();

	// public void refreshXML() throws Exception {
	// Element rootNode = utility.getRootNodeElement();
	//
	// // //remove all categories first
	// NodeList listCategoryNode = utility.getAllNodesOfType(Category.node,
	// rootNode);
	// System.out.println("Before removal " + listCategoryNode.getLength());
	//
	// utility.removeAll(utility.getRootNode());
	//
	// System.out.println("After removal " + listCategoryNode.getLength());
	// // ///////////////
	// System.out.println("Final length = " + listCategoryNode.getLength());
	//
	// rootNode.appendChild(createCategoryList());
	//
	// utility.printToFile();
	// }
	//
	// private Element createCategoryList() throws Exception {
	//
	// Element categoryListElement = utility
	// .createElement(ProjectConfigConstants.CATEGORY_LIST);
	//
	// Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
	// for (CategoryDTO cats : listCats) {
	// int categoryId = cats.getCatID();
	//
	// Element categoryElement = utility.createElement(Category.node);
	//
	// // categoryElement=utility.setAttribute(Category.ID,
	// // categoryElement, categoryId+"", false);
	//
	// Element categoryIDElement = utility.createElement(Category.ID);
	// utility.appendCDATASection(categoryIDElement, categoryId + "",
	// false);
	// categoryElement.appendChild(categoryIDElement);
	//
	// Element categoryNameElement = utility
	// .createElement(Category.CATEGORY_NAME);
	// utility.appendCDATASection(categoryNameElement,
	// cats.getCatgoryName() + "", false);
	// categoryElement.appendChild(categoryNameElement);
	//
	// Element categoryQuestionListElement =
	// createCategoryQuestionsList(categoryId);
	//
	// categoryElement.appendChild(categoryQuestionListElement);
	//
	// categoryListElement.appendChild(categoryElement);
	// }
	// return categoryListElement;
	// }
	//
	// private Element createCategoryQuestionsList(int catID) throws Exception {
	// Element categoryQuestionListElement = utility
	// .createElement(Category.QUESTIONS_LIST);
	//
	// Vector<QuestionDTO> listques = intMgmtRPC
	// .fetchAllQuestionsByCategory(catID);
	//
	// for (QuestionDTO cats : listques) {
	// Element categoryQuestionElement = utility
	// .createElement(Question.node);
	//
	// categoryQuestionElement.appendChild(utility.appendCDATASection(
	// utility.createElement(Question.ID), cats.getQuestionID()
	// + "", false));
	//
	// categoryQuestionElement.appendChild(utility.appendCDATASection(
	// utility.createElement(Question.LINKED_CAT_ID),
	// cats.getLinkedCatID() + "", false));
	//
	// categoryQuestionElement.appendChild(utility.appendCDATASection(
	// utility.createElement(Question.QUESTION_NAME),
	// cats.getQuestion() + "", false));
	//
	// /** ################ ANSWERS START ####################### */
	//
	// Element categoryQuestionAnswerListElement =
	// fetchAllAnswersByQuestion(cats);
	//
	// categoryQuestionElement
	// .appendChild(categoryQuestionAnswerListElement);
	//
	// /** ################ ANSWERS END ####################### */
	//
	// categoryQuestionListElement.appendChild(categoryQuestionElement);
	// }
	//
	// return categoryQuestionListElement;
	// }

	// private Element fetchAllAnswersByQuestion(QuestionDTO ques)
	// throws Exception {
	// Element categoryQuestionAnswerListElement = utility
	// .createElement(Question.ANSWERS_LIST);
	//
	// Vector<AnswerDTO> listques = intMgmtRPC.fetchAllAnswersByQuestion(ques);
	//
	// for (AnswerDTO cats : listques) {
	// Element categoryQuestionAnswerElement = utility
	// .createElement(Answer.node);
	//
	// categoryQuestionAnswerElement.appendChild(utility
	// .appendCDATASection(utility.createElement(Answer.ID),
	// cats.getAnsID() + "", false));
	//
	// categoryQuestionAnswerElement.appendChild(utility
	// .appendCDATASection(
	// utility.createElement(Answer.LINKED_CAT_ID),
	// cats.getLinkedCatID() + "", false));
	//
	// categoryQuestionAnswerElement.appendChild(utility
	// .appendCDATASection(
	// utility.createElement(Answer.LINKED_QUES_ID),
	// cats.getLinkedQuesID() + "", false));
	//
	// categoryQuestionAnswerElement.appendChild(utility
	// .appendCDATASection(
	// utility.createElement(Answer.ANSWER_NAME),
	// cats.getAnswer() + "", false));
	//
	// /** ################ ANSWERS START ####################### */
	//
	// /** ################ ANSWERS END ####################### */
	//
	// categoryQuestionAnswerListElement
	// .appendChild(categoryQuestionAnswerElement);
	// }
	//
	// return categoryQuestionAnswerListElement;
	// }

	/**
	 * Fetch all categories
	 * 
	 * @throws XPathExpressionException
	 */

	public List<CategoryDTO> fetchAllCategories() throws XPathExpressionException {
		List<CategoryDTO> listCategoryDTOs = new ArrayList<CategoryDTO>();
		NodeList nl = utility.getXpathNodeListResult(utility.getDom(), "/" + ProjectConfigConstants.ROOT.getName() + "/"
				+ ProjectConfigConstants.CATEGORY_LIST.getName() + "/" + Category.node.getName());
		for (int index = 0; index < nl.getLength(); index++) {
			Node categoryNode = nl.item(index);
			CategoryDTO cat = new CategoryDTO();
			cat.setCatID(utility.getXpathIntegerResult(categoryNode, Category.ID.getName()));
			cat.setCatgoryName(utility.getXpathStringResult(categoryNode, Category.CATEGORY_NAME.getName()));

			listCategoryDTOs.add(cat);
		}
		return listCategoryDTOs;
	}

	public List<CategoryDTO> fetchAllCategoriesByName(String name, StringComparison sc)
			throws XPathExpressionException {
		List<CategoryDTO> listCategoryDTOs = new ArrayList<CategoryDTO>();
		NodeList nl = utility.getXpathNodeListResult(utility.getDom(),
				"/" + ProjectConfigConstants.ROOT.getName() + "/" + ProjectConfigConstants.CATEGORY_LIST.getName() + "/"
						+ Category.node.getName() + "[" + sc.getName() + "(" + Category.CATEGORY_NAME.getName() + ",'"
						+ name + "')]");
		for (int index = 0; index < nl.getLength(); index++) {
			Node categoryNode = nl.item(index);
			CategoryDTO cat = new CategoryDTO();
			cat.setCatID(utility.getXpathIntegerResult(categoryNode, Category.ID.getName()));
			cat.setCatgoryName(utility.getXpathStringResult(categoryNode, Category.CATEGORY_NAME.getName()));

			listCategoryDTOs.add(cat);
		}
		return listCategoryDTOs;
	}

	public CategoryDTO findCategoryByID(int CatID) throws XPathExpressionException {
		CategoryDTO objCategoryDTO = null;
		NodeList nl = utility.getXpathNodeListResult(utility.getDom(),
				"/" + ProjectConfigConstants.ROOT.getName() + "/" + ProjectConfigConstants.CATEGORY_LIST.getName() + "/"
						+ Category.node.getName() + "[" + Category.ID.getName() + "=" + CatID + "]");
		if (nl != null && nl.getLength() > 0) {
			System.out.println("Total result found == " + nl.getLength());
			Node categoryNode = nl.item(0);
			objCategoryDTO = new CategoryDTO();
			objCategoryDTO.setCatID(utility.getXpathIntegerResult(categoryNode, Category.ID.getName()));
			objCategoryDTO.setCatgoryName(utility.getXpathStringResult(categoryNode, Category.CATEGORY_NAME.getName()));
		}
		return objCategoryDTO;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			InterviewXMLUtility inter = new InterviewXMLUtility("C:/Users/VINU/Desktop/services.xml");
			// inter.refreshXML();
			List<CategoryDTO> list = inter.fetchAllCategories();
			for (CategoryDTO cat : list) {
				System.out.println("cat.getCatID() == " + cat.getCatID());
				System.out.println("cat.getCatgoryName() == " + cat.getCatgoryName());
			}
			// ############################
			// int catID = 1;
			// CategoryDTO cat = inter.findCategoryByID(catID);
			// if (cat != null) {
			// System.out.println("cat.getCatID() == " + cat.getCatID());
			// System.out.println("cat.getCatgoryName() == "
			// + cat.getCatgoryName());
			// } else {
			// System.out.println("No category for id = " + catID);
			// }

			// ##################################
			List<CategoryDTO> list1 = inter.fetchAllCategoriesByName("servi", StringComparison.CONTAINS);
			for (CategoryDTO cat : list1) {
				System.out.println("cat.getCatID() == " + cat.getCatID());
				System.out.println("cat.getCatgoryName() == " + cat.getCatgoryName());
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
