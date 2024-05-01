//package com.p.interview.mgmt.xml;
//
//import java.awt.Desktop;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.Vector;
//
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Element;
//import org.xml.sax.SAXException;
//
//import com.p.interview.mgmt.pojo.AnswerDTO;
//import com.p.interview.mgmt.pojo.CategoryDTO;
//import com.p.interview.mgmt.pojo.QuestionDTO;
//import com.p.interview.mgmt.rpc.InterviewRPC;
//
//
//
//public class InterviewXMLGeneratorProper {
//
//	private InterviewRPC intMgmtRPC = new InterviewRPC();
//
//	/**
//	 * @param args
//	 */
//	XMLUtility utility = null;
//
//	private static String destDir = "C:/Users/VINU/Desktop";
//	private static String serviceXmlFileName = destDir + "/" + "services.xml";
//
//	public InterviewXMLGeneratorProper(String xmlFileName)
//			throws ParserConfigurationException, SAXException, IOException {
//		InterviewXMLGeneratorProper.serviceXmlFileName = xmlFileName != null ? xmlFileName
//				: serviceXmlFileName;
//		System.out.println("XML file going to be parsed : " + xmlFileName);
//		utility = XMLUtility.getInstance(xmlFileName,true);
//	}
//
//	public InterviewXMLGeneratorProper() throws ParserConfigurationException,
//			SAXException, IOException {
//		this(null);
//	}
//
//	public static void main(String[] args) {
//		try {
//			InterviewXMLGeneratorProper generator = new InterviewXMLGeneratorProper(
//					serviceXmlFileName);
//			generator.refreshXML();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
//
//
//	public void refreshXML() throws Exception {
//
//		File serviceXmlFile = new File(serviceXmlFileName);
//
//		Element rootNode = utility.getRootNodeElement();
//
//		rootNode.appendChild(createCategoryList());
//
//		//utility.printToFile();
//
//		Desktop.getDesktop().browse(
//				Paths.get(serviceXmlFile.getAbsolutePath()).toUri());
//	}
//
//	private Element createCategoryList() throws Exception {
//		Element categoryListElement = utility
//				.createElement(ProjectConfigConstants.CATEGORY_LIST);
//
//		Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
//
//		for (CategoryDTO cats : listCats) {
//
//			Element categoryElement = utility
//					.createElement(ProjectConfigConstants.Category.node);
//
//			categoryElement.appendChild(utility.appendCDATASection(
//					utility.createElement(ProjectConfigConstants.Category.ID),
//					cats.getCatID() + "", true));
//
//			categoryElement
//					.appendChild(utility.appendCDATASection(
//							utility.createElement(ProjectConfigConstants.Category.CATEGORY_NAME),
//							cats.getCatgoryName() + "", true));
//
//			/** ################ QUESTIONS START ####################### */
//
//			categoryElement.appendChild(createCategoryQuestionList(cats
//					.getCatID()));
//
//			/** ################ QUESTIONS END ####################### */
//
//			categoryListElement.appendChild(categoryElement);
//		}
//
//		return categoryListElement;
//	}
//
//	private Element createCategoryQuestionList(int catID) throws Exception {
//
//		Element categoryQuestionListElement = utility
//				.createElement(ProjectConfigConstants.Category.QUESTIONS_LIST);
//
//		Vector<QuestionDTO> listques = intMgmtRPC
//				.fetchAllQuestionsByCategory(catID);
//
//		for (QuestionDTO cats : listques) {
//
//			Element categoryQuestionElement = utility
//					.createElement(ProjectConfigConstants.Question.node);
//
//			categoryQuestionElement.appendChild(utility.appendCDATASection(
//					utility.createElement(ProjectConfigConstants.Question.ID),
//					cats.getQuestionID() + "", true));
//
//			categoryQuestionElement
//					.appendChild(utility.appendCDATASection(
//							utility.createElement(ProjectConfigConstants.Question.LINKED_CAT_ID),
//							cats.getLinkedCatID() + "", true));
//
//			categoryQuestionElement
//					.appendChild(utility.appendCDATASection(
//							utility.createElement(ProjectConfigConstants.Question.QUESTION_NAME),
//							cats.getQuestion() + "", true));
//
//			/** ################ ANSWERS START ####################### */
//
//			categoryQuestionElement
//					.appendChild(createCategoryQuestionAnswerList(cats));
//
//			/** ################ ANSWERS END ####################### */
//
//			categoryQuestionListElement.appendChild(categoryQuestionElement);
//
//		}
//
//		return categoryQuestionListElement;
//	}
//
//	private Element createCategoryQuestionAnswerList(QuestionDTO q) throws Exception {
//
//		Element categoryQuestionAnswerListElement = utility
//				.createElement(ProjectConfigConstants.Question.ANSWERS_LIST);
//
//		Vector<AnswerDTO> listAns = intMgmtRPC.fetchAllAnswersByQuestion(q);
//
//		for (AnswerDTO cats : listAns) {
//
//			Element categoryQuestionAnswerElement = utility
//					.createElement(ProjectConfigConstants.Answer.node);
//
//			categoryQuestionAnswerElement.appendChild(utility
//					.appendCDATASection(utility
//							.createElement(ProjectConfigConstants.Answer.ID),
//							cats.getAnsID() + "", true));
//
//			categoryQuestionAnswerElement
//					.appendChild(utility.appendCDATASection(
//							utility.createElement(ProjectConfigConstants.Answer.LINKED_CAT_ID),
//							cats.getLinkedCatID() + "", true));
//
//			categoryQuestionAnswerElement
//					.appendChild(utility.appendCDATASection(
//							utility.createElement(ProjectConfigConstants.Answer.LINKED_QUES_ID),
//							cats.getLinkedQuesID() + "", true));
//
//			categoryQuestionAnswerElement
//					.appendChild(utility.appendCDATASection(
//							utility.createElement(ProjectConfigConstants.Answer.ANSWER_NAME),
//							cats.getAnswer() + "", true));
//
//			categoryQuestionAnswerListElement
//					.appendChild(categoryQuestionAnswerElement);
//
//		}
//
//		return categoryQuestionAnswerListElement;
//	}
//
//}
