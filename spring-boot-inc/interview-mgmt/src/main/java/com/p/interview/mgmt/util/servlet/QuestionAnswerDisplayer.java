package com.p.interview.mgmt.util.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;

public class QuestionAnswerDisplayer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(QuestionAnswerDisplayer.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// response.setIntHeader("Refresh", 120);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(new StringBuffer().append("<html>").append(
					"<title>QuestionAnswerDisplayer</title>" + "<link rel=\"stylesheet\" type=\"text/css\" href=\""
					// + request.getContextPath()
							+ "css/lipstick.css\" />")
					.append("<body>").append("<img src=\"" // + request.getContextPath()
							+ "css/prem.jpg\" alt=\"logo\"" + " />")
					.toString());

			InterviewRPC intMgmtRPC = new InterviewRPC();
			Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
			int catCounter = 1;
			out.println("<br><br>" + "<font size=\"3\">"
					+ "=======================================================================<br>"
					+ "list of categories".toUpperCase() + "<br>" + "<ul>");
			for (CategoryDTO cats : listCats) {
				StringBuffer sbbb = new StringBuffer();
				sbbb.append("<li>").append("<a href=\"#" + cats.getCatID() + "\">")
						.append((catCounter++) + " . " + cats.getCatgoryName().toUpperCase()).append("</a>");

				Vector<QuestionDTO> listques = intMgmtRPC.fetchAllQuestionsByCategory(cats.getCatID());
				int count = 1;
				sbbb.append("<br>" + "<ol>" + "<font size=\"1\">");
				for (QuestionDTO q : listques) {

					sbbb.append("<li>").append("<a href=\"#" + cats.getCatID() + "_" + q.getQuestionID() + "\">")
							.append(q.getQuestion().toUpperCase()).append("</a>");
					sbbb.append("</li><br>");

				}

				sbbb.append("</font></ol>");
				sbbb.append("</li><br><br>");

				out.println(sbbb.toString());
			}
			out.println("</ul>" + "=======================================================================</font><br>");
			catCounter = 1;
			for (CategoryDTO cats : listCats) {
				int categoryId = cats.getCatID();
				out.print("<br>" +
				/* "<center>" + */
						"#################################" + "<br>" + "<font size=\"5\">" + "<a id=\""
						+ cats.getCatID() + "\">" + " category : ".toUpperCase() + (catCounter++) + "</a>" + "<br>"
						+ cats.getCatgoryName().toUpperCase() + "</font><br>" + "#################################"
						+ "<br>");
				Vector<QuestionDTO> listques = intMgmtRPC.fetchAllQuestionsByCategory(categoryId);
				int count = 1;
				for (QuestionDTO q : listques) {
					out.print("<br>**********<br><font size=\"3\" style=\"font-weight: bold\" color=\"red\">"
							+ "<a id=\"" + cats.getCatID() + "_" + q.getQuestionID() + "\">" + " QUESTION : "
							+ (count++) + "<br>" + q.getQuestion() + "</font><br>");
					int answerNo = 1;
					Vector<AnswerDTO> listAns = intMgmtRPC.fetchAllAnswersByQuestion(q);
					for (AnswerDTO ans : listAns) {
						out.print("<br><font size=\"3\" style=\"font-weight: bold\" color=\"green\" > { ANSWER : "
								+ answerNo + " } </font><br><font size=\"3\" style=\"font-weight: bold\">"
								+ ans.getAnswer() + "</font>");
					}
					out.print("<br>**********<br>");
				}
				out.print("<br>" +
				/* "</center>" + */
						"");
			}

			out.println("</body>");
			out.println("</html>");

		} catch (Throwable e) {
			throw new ServletException("Exception in FileHighlighter", e);
		} finally {

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

}
