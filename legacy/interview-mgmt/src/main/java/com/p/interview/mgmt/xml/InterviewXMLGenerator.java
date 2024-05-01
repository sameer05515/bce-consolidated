//package com.p.interview.mgmt.xml;
//
//import java.awt.Desktop;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.nio.file.Paths;
//import java.util.Vector;
//
//import com.p.interview.mgmt.pojo.AnswerDTO;
//import com.p.interview.mgmt.pojo.CategoryDTO;
//import com.p.interview.mgmt.pojo.QuestionDTO;
//import com.p.interview.mgmt.rpc.InterviewRPC;
//
//
//
//public class InterviewXMLGenerator {
//
//	private InterviewRPC intMgmtRPC = new InterviewRPC();
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		try {
//			InterviewXMLGenerator generator = new InterviewXMLGenerator();
//			generator.refreshXML();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
//	private static final String NEW_LINE = "\n";
//	private static final String CDATA_START = "<![CDATA[";
//	private static final String CDATA_END = "]]>";
//
//	public void refreshXML() throws Exception {
//
//
//
//		StringBuffer sb=new StringBuffer();
//		sb.append("<interview-mgmt-root>").append(NEW_LINE);
//		String categories = fetchAllCategories();
//		sb.append(categories).append(NEW_LINE);
//
//		sb.append("</interview-mgmt-root>").append(NEW_LINE);
//
//		String content=sb.toString();
//
//		String destDir = "C:/Users/VINU/Desktop";
//		File serviceXmlFile = new File(destDir + "/" + "services.xml");
//
//		if (!serviceXmlFile.exists()) {
//			serviceXmlFile.createNewFile();
//		}
//
//		/** Create file */
//		FileWriter fstream = new FileWriter(serviceXmlFile);
//		BufferedWriter out = new BufferedWriter(fstream);
//		out.write(content);
//		/** Close the output stream */
//		out.close();
//		// msg = "Successfully created services.xml ";
//
//		Desktop.getDesktop().browse(
//				Paths.get(serviceXmlFile.getAbsolutePath()).toUri());
//	}
//
//
//
//	private String fetchAllCategories() throws Exception {
//
//		Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
//
//		StringBuffer sb=new StringBuffer();
//
//		sb.append("<interview-mgmt-category-list>").append(NEW_LINE);
//		for (CategoryDTO cats : listCats) {
//			sb.append("<interview-mgmt-category>").append(NEW_LINE);
//
//			sb.append("<id>").append(CDATA_START).append(cats.getCatID())
//					.append(CDATA_END).append("</id>").append(NEW_LINE);
//			sb.append("<name>").append(CDATA_START)
//					.append(cats.getCatgoryName()).append(CDATA_END)
//					.append("</name>").append(NEW_LINE);
//			/**################  QUESTIONS START #######################*/
//
//			sb.append(fetchAllQuestionsByCategory(cats.getCatID()));
//
//			/**################  QUESTIONS END #######################*/
//
//			sb.append("</interview-mgmt-category>").append(NEW_LINE);
//		}
//		sb.append("</interview-mgmt-category-list>").append(NEW_LINE);
//
//		return sb.toString();
//	}
//
//
//	private String fetchAllQuestionsByCategory(int catID) throws Exception {
//
//		Vector<QuestionDTO> listques = intMgmtRPC
//					.fetchAllQuestionsByCategory(catID);
//		StringBuffer sb=new StringBuffer();
//
//		sb.append("<interview-mgmt-category-question-list>").append(NEW_LINE);
//		for (QuestionDTO cats : listques) {
//			sb.append("<interview-mgmt-category-question>").append(NEW_LINE);
//
//			sb.append("<id>").append(CDATA_START).append(cats.getQuestionID())
//					.append(CDATA_END).append("</id>").append(NEW_LINE);
//			sb.append("<linked-cat-id>").append(CDATA_START).append(cats.getLinkedCatID())
//			.append(CDATA_END).append("</linked-cat-id>").append(NEW_LINE);
//			sb.append("<question>").append(CDATA_START)
//					.append(cats.getQuestion()).append(CDATA_END)
//					.append("</question>").append(NEW_LINE);
//			/**################  ANSWERS START #######################*/
//
//			sb.append(fetchAllAnswersByQuestion(cats));
//
//			/**################  ANSWERS END #######################*/
//
//			sb.append("</interview-mgmt-category-question>").append(NEW_LINE);
//		}
//		sb.append("</interview-mgmt-category-question-list>").append(NEW_LINE);
//
//		return sb.toString();
//	}
//
//
//
//	private String fetchAllAnswersByQuestion(QuestionDTO q) throws Exception {
//		Vector<AnswerDTO> listAns = intMgmtRPC
//				.fetchAllAnswersByQuestion(q);
//		StringBuffer sb=new StringBuffer();
//		sb.append("<interview-mgmt-category-question-answer-list>").append(NEW_LINE);
//		for (AnswerDTO cats : listAns) {
//			sb.append("<interview-mgmt-category-question-answer>").append(NEW_LINE);
//
//			sb.append("<id>").append(CDATA_START).append(cats.getAnsID())
//					.append(CDATA_END).append("</id>").append(NEW_LINE);
//			sb.append("<linked-cat-id>").append(CDATA_START).append(cats.getLinkedCatID())
//			.append(CDATA_END).append("</linked-cat-id>").append(NEW_LINE);
//			sb.append("<linked-ques-id>").append(CDATA_START).append(cats.getLinkedQuesID())
//			.append(CDATA_END).append("</linked-ques-id>").append(NEW_LINE);
//			sb.append("<answer>").append(CDATA_START)
//					.append(cats.getAnswer()).append(CDATA_END)
//					.append("</answer>").append(NEW_LINE);
//
//
//			sb.append("</interview-mgmt-category-question-answer>").append(NEW_LINE);
//		}
//
//		sb.append("</interview-mgmt-category-question-answer-list>").append(NEW_LINE);
//		return sb.toString();
//	}
//
//}
