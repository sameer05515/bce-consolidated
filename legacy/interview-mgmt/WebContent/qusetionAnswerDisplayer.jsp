<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*,java.sql.*,java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page
	import="javax.servlet.http.*,com.p.interview.mgmt.pojo.*,com.p.interview.mgmt.rpc.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>"QuestionAnswerDisplayer using jsp Premendra Kumar"</title>
<script src="jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css;" href="css/lipstick.css">
<script src="jquery/customQuery.js" type="text/javascript"></script>
</head>
<body>
	<img src="css/prem.jpg" alt="logo" />

	<%
		try {
			// String docID = request.getParameter("catId").trim();
			InterviewRPC intMgmtRPC = new InterviewRPC();
			Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
			int catCounter = 1;
	%>

	<br />
	<br />
	<font size="3">=======================================================================<br><%="list of categories".toUpperCase()%>

		<button id="btnListOfCategories">show</button>
		<div id="textSectionListOfCategories">
			<br/>
			<ul>
				<%
					for (CategoryDTO cats : listCats) {
							StringBuffer sbbb = new StringBuffer();
				%>

				<li><a id="<%="cats_"+cats.getCatID()%>" href="<%="#" + cats.getCatID()%>"><%=(catCounter++) + " . "
							+ cats.getCatgoryName().toUpperCase()%>							
							</a> 							
							<div class="guess_box">
							<img src="css/icons/Address_Icon_inactive.png"/>
							<div>

							<%
 	Vector<QuestionDTO> listques = intMgmtRPC
 					.fetchAllQuestionsByCategory(cats.getCatID());
 			int count = 1;
 %> <br />
					<ol>
						<font size="1"> <%
 	for (QuestionDTO q : listques) {
 %>

							<li><a id="<%="ques_" + cats.getCatID()+ "_" + q.getQuestionID()%>"
								href="<%="#" + cats.getCatID() + "_"
								+ q.getQuestionID()%>">
									<%=q.getQuestion().toUpperCase()%>
							</a></li> <br /> <%
 	}
 %>

						</font>
					</ol>					
					</div>
					</div>
					</li>
				<br />
				<br />

				<%
					}
				%>

			</ul>
			=======================================================================
		</div>
	</font>
	<br />
	<%
		catCounter = 1;
			for (CategoryDTO cats : listCats) {
				int categoryId = cats.getCatID();
	%>

	<br />#################################
	<br />
	<font size="5"> <a id="<%=cats.getCatID()%>"> <%=" category : ".toUpperCase() + (catCounter++)%>
	</a><br /><%=cats.getCatgoryName().toUpperCase()%></font>

	<span><a href="<%="#cats_" + cats.getCatID()%>"> <img src="images/up_to_summary.png" width="15" height="25"/></a> </span>
	
	<div class="guess_box_ques">
	<img src="css/icons/Address_Icon_inactive.png"/>
	<div>
	<br /> #################################
	<br />

	<%
		Vector<QuestionDTO> listques = intMgmtRPC
						.fetchAllQuestionsByCategory(categoryId);
				int count = 1;
				for (QuestionDTO q : listques) {
	%>

	<br />**********
	<br />
	<font size="3" style="font-weight: bold" color="red"> <a
		id="<%=cats.getCatID() + "_" + q.getQuestionID()%>"> <%=" QUESTION : " + (count++) + "<br>"
								+ q.getQuestion()%></a></font>
								
			<span><a href="<%="#ques_" + cats.getCatID()+ "_" + q.getQuestionID()%>"> <img src="images/up_to_summary.png" width="15" height="25"/></a> </span>
	<br />
	
	<div class="guess_box_ques_ans">
	<img src="css/icons/Address_Icon_inactive.png"/>
	<div>

	<%
		int answerNo = 1;
					Vector<AnswerDTO> listAns = intMgmtRPC
							.fetchAllAnswersByQuestion(q);
					for (AnswerDTO ans : listAns) {
	%>
	<br />
	<font size="3" style="font-weight: bold" color="green"> <%="{ ANSWER : " + (answerNo++) + " }"%>
	</font>
	<br />
	<font size="3" style="font-weight: bold"> <%=ans.getAnswer()%>
	</font>


	<%
		}
	%>
	</div>
	</div>
	<br />**********
	<br />
	<%
		}
	%>
	
	</div>
	</div>
	<br />
	<%
		}

		} catch (Throwable e) {
			throw new ServletException("Exception in FileHighlighter", e);
		} finally {

		}
	%>
</body>
</html>