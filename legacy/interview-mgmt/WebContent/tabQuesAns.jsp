<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*,java.sql.*,java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page
	import="javax.servlet.http.*,com.p.interview.mgmt.pojo.*,com.p.interview.mgmt.rpc.*"%>
<html lang="en">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>"Question Answer Displayer using jsp Premendra Kumar"</title>
			<link rel="stylesheet" href="ext-lib/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
			<script src="ext-lib/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
			<script src="ext-lib/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
			<script src="ext-lib/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
			<script src="ext-lib/jquery-ui-1.10.3/ui/jquery.ui.mouse.js"></script>
			<script src="ext-lib/jquery-ui-1.10.3/ui/jquery.ui.sortable.js"></script>
			<script src="ext-lib/jquery-ui-1.10.3/ui/jquery.ui.accordion.js"></script>
			
			<script src="ext-lib/jquery-ui-1.10.3/ui/jquery.ui.tabs.js"></script>
			<link rel="stylesheet" href="ext-lib/jquery-ui-1.10.3/demos/demos.css">
			<style>
				/* IE has layout issues when sorting (see #5413) */
				.group { zoom: 1 }
				
				#myTabs {
				border:1px solid #636363;
				background:#c2c2c2 none;
				padding: 10px 10px 10px 10px;
				}
			
				body {
					text-decoration: deeppink;
					background-color: #d2b31c;
					margin-left: 5%;
					margin-right: 5%;
					background-image: url(css/bg3_1440x900.jpg);
					border: 1px dotted gray;
					padding: 10px 10px 10px 10px;
					font-family: sans-serif;					
					border-style: dashed;
					border-color: red;
				}
				
				table {
				margin-left: 20px;
				margin-right: 20px;
				border: thin solid black;
				border-collapse: collapse;
				}
				th {
					border: 3px solid red;
					background-color: #cc6600;
				}
				td{
					border: 1px solid black ; font: 900;
				}
				#full-screen-background-image {
				  z-index: -999;
				  min-height: 100%;
				  min-width: 1024px;
				  width: 100%;
				  height: auto;
				  position: fixed;
				  top: 0;
				  left: 0;
				}
			</style>
			<script>
			$(function() {
			
				$( "#accordion" ).tabs();
			});
			</script>
		</head>
		
		
		<body>

			<img src="css/prem.jpg" alt="logo" id="full-screen-background-image"/>

				<%
					try {
						// String docID = request.getParameter("catId").trim();
						InterviewRPC intMgmtRPC = new InterviewRPC();
						Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
						int catCounter = 0;
				%>
				
				

			<div id="accordion">

							<!----------------------------------->
							<ul>
							<%
								for (CategoryDTO cats : listCats) {
										StringBuffer sbbb = new StringBuffer();
										catCounter=catCounter+1;
										int categoryId = cats.getCatID();
							%>
							
							<li><a href="#group-<%=(catCounter)%>"><font style="font-weight: bold" color="green"><%=(catCounter) + " . "
										+ cats.getCatgoryName().toUpperCase()%></font></a></li>
							<%
								}
								listCats = intMgmtRPC.fetchAllCategories();
								catCounter = 0;
							%>
							</ul>
							<!----------------------------------->
							
							
							<%
								for (CategoryDTO cats : listCats) {
										StringBuffer sbbb = new StringBuffer();
										catCounter=catCounter+1;
										int categoryId = cats.getCatID();
							%>
							
							<div id="group-<%=(catCounter)%>">
								<h3><font style="font-weight: bold" color="green"><%=(catCounter) + " . "
										+ cats.getCatgoryName().toUpperCase()%></font></h3>
								<div id="myTabs">
								
								
								<!-------------------------------->
								<%
									Vector<QuestionDTO> listques = intMgmtRPC
													.fetchAllQuestionsByCategory(categoryId);
											int count = 1;
											
								%>
								<ol>
								<font size="2"> <%
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
																
								<!-------------------------------->
								<%
									listques = intMgmtRPC
													.fetchAllQuestionsByCategory(categoryId);
											count = 1;
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
								<font size="3"> <%=ans.getAnswer()%>
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
								
									<p></p>
								</div>
							</div>
							
							<%
								}
							%>
				
				
				
				
			</div>
			

				<%
					} catch (Throwable e) {
						throw new ServletException("Exception in FileHighlighter", e);
					} finally {

					}
				%>
		</body>
</html>