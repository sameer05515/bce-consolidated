<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*,java.sql.*,java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*,com.p.interview.mgmt.pojo.*,com.p.interview.mgmt.rpc.*"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>"QuestionAnswerDisplayer using jsp Premendra Kumar"</title>

		<script type="text/javascript" src="http://127.0.0.1:8080/jquerytest/a/jquery-1.3.2.min.js"></script>
		<style type="text/css">
		.htmlClass{
			padding:8px;
			border:1px solid blue;
			margin-bottom:8px;
		}
		.excerciseHeader{
			FONT: 24px Verdana, Arial, Helvetica, sans-serif;
		}
		
		.albumHeader{
			FONT: 18px Verdana, Arial, Helvetica, sans-serif;
		}
		.commandHeader{
			padding:2px;
			border:1px solid red;
			FONT: 12px Verdana, Arial, Helvetica, sans-serif;
		}
		.commandHeader:focus,
		.commandHeader:hover {
			border:1px solid yellow;
			FONT: 16px Verdana, Arial, Helvetica, sans-serif;
			font-weight: bold;
		}
		</style>

		<style>
		body {
			width: 1000px;
			margin: 0 auto;
			background: green;
			/*background: #000;*/
			color: #FFF;
			font: 12px sans-serif;
		}
		h1 {
			font-size: 24px;
		}
		h2 {
			font-size: 18px;
			margin-top: 0;
		}
		a {
			color: #FFF;
		}
		a:focus,
		a:hover {
			text-decoration: none;
		}
		table {
			margin-bottom: 10px;
			border-spacing: 0;
		}
		caption {
			margin-bottom: 10px;
			font-size: 14px;
			font-weight: bold;
			text-align: left;
		}
		th,
		td {
			padding: 0 10px 0 0;
			text-align: left;
		}
		.planet {
			margin: 10px 0;
			padding: 20px 20px 20px 50px;
			border: 1px solid #FFF;
			background-position: 100px 20px;
			/*background-repeat: no-repeat;*/
		}

		.premu426{
			background-image: url(http://127.0.0.1:8080/bce/css/prem.jpg);
			FONT: 16px Verdana, Arial, Helvetica, sans-serif;
			font-weight: bold;
		}
		</style>
		
	</head>
	<body onload="showNextExerciseAndImages()">
		<span class="albumHeader">
			jQuery html() example : This html page is designed to help in memorizing Interview questions
		</span>
		
		<br/>
		
		<span id="nextExcerciseMover"> Next Excercise move </span>

		<div id="currentStatusdisplayer">changu Mangu</div>
		
		<%!
			public static final String exerciseNodeContainer="exerciseNodeContainer";
			public static final String exerciseHeaderNode="exerciseHeaderNode";
			public static final String currentExerciseNodeAllowedTimeToDisplay="currentExerciseNodeAllowedTimeToDisplay";
			public static final String exerciseNode="exerciseNode";
			public static final String exerciseNodeImageList="exerciseNodeImageList";
			public static final String exerciseNodeImage="exerciseNodeImage";
			public static final String fileWriterURL="http://127.0.0.1:8080/bce/fileWriter?documentId=";
			public static final String imageWidth="500";
			public static final String imageHeight="500";	
		%>
		
		

		<%
			try {
			// String docID = request.getParameter("catId").trim();
			InterviewRPC intMgmtRPC = new InterviewRPC();
			Vector<CategoryDTO> listCats = intMgmtRPC.fetchAllCategories();
			int catCounter = 1;
		%>

		<br />
		
		
		<%
			catCounter = 1;
			for (CategoryDTO cats : listCats) {
			int categoryId = cats.getCatID();
		%>

		<div id="<%=exerciseNodeContainer+(categoryId)%>">
			<div id="<%=exerciseHeaderNode+(categoryId)%>" class="excerciseHeader">
							
				<%=" category : ".toUpperCase() + (catCounter++)%>
				<%=cats.getCatgoryName().toUpperCase()%>						
							
			</div>

			<%
				Vector<QuestionDTO> listques = intMgmtRPC.fetchAllQuestionsByCategory(categoryId);
				int count = 1;
			%>
			<div id="<%=currentExerciseNodeAllowedTimeToDisplay+(categoryId)%>">
				<%=(listques.size())%>
			</div>
			<div id="<%=exerciseNode+(categoryId)%>" class="htmlClass prem123456 planet premu426">
				<div id="<%=exerciseNodeImageList+(categoryId)%>">
					<%
						for (QuestionDTO q : listques) {
					%>
					<div id="<%=exerciseNodeImage+(categoryId)+"_"+(q.getQuestionID())%>">
						**********
						<br />
						
							
						<%=" QUESTION : " + (count++) + "<br>"+ q.getQuestion()%>
							
						
					
						

						<%
							int answerNo = 1;
							Vector<AnswerDTO> listAns = intMgmtRPC.fetchAllAnswersByQuestion(q);
							for (AnswerDTO ans : listAns) {
						%>
						<br />
					
						
						<%="{ ANSWER : " + (answerNo++) + " }"%>
						
						<br />
					
						
						<%=ans.getAnswer()%>
						
					
						<%
							}
						%>
						<br />**********
					</div><!--excercise node image closes here-->
					<%
						}
					%> <!--question list for loop ends here-->
				</div> <!--excercise node image list closes here-->
			</div><!--excercise node closes here-->
			
			
		</div>		
		
		<%
			}

			} catch (Throwable e) {
				throw new ServletException("Exception in FileHighlighter", e);
			} finally {}
		%>
		
		
		<script type="text/javascript">
		
			var exerciseNode='exerciseNode';
			var exerciseHeaderNode='exerciseHeaderNode';
			var exerciseAllowedTimeNode='currentExerciseNodeAllowedTimeToDisplay';
			var exerciseImageListNode='exerciseNodeImageList';
			var exerciseContainerNode='exerciseNodeContainer';

			var exerciseObjectList=[];

			var selectedExerciseNodeIndex=0;
			var selectedExerciseNodeImageIndex=0;

			function showExerciseNode(node){
				$('#'+node.exerciseContainerNodeID).show();	
				$('#'+node.excerciseID).show();	
			}

			function hideExerciseNode(node){
				// hide all image divs before hiding the excercise node
				for (var i=0; i<=node.imageIDList.length; i++) {
					var imgDivID=node.imageIDList[i];
					$('#'+imgDivID).hide();				
					}
				$('#'+node.excerciseID).hide();	
				$('#'+node.exerciseContainerNodeID).hide();	
			}
			
			/**
			* Initial method to fetch all excercise nodes and create one array of nodes
			*/
			$("div").each(function() {

					if($(this).hasClass('prem123456')==false){ return; }

					var execerciseObj=new Object();

					var divKiID=$(this).attr('id');

					execerciseObj.excerciseID=divKiID;
					var spanKiID=exerciseHeaderNode+divKiID.substr(exerciseNode.length);
					execerciseObj.exerciseHeaderID=spanKiID;		
					var allowedTimeToDisplay=exerciseAllowedTimeNode+divKiID.substr(exerciseNode.length);
					$('#'+allowedTimeToDisplay).hide();
					execerciseObj.allowedTime=$('#'+allowedTimeToDisplay).text();
					//alert(execerciseObj.allowedTime+'');
					execerciseObj.imageIDList=fetchAllImageNodes(execerciseObj);

					execerciseObj.exerciseContainerNodeID=exerciseContainerNode+divKiID.substr(exerciseNode.length);

					//alert(execerciseObj.allowedTime+'  '+execerciseObj.imageIDList.length);

					exerciseObjectList.push(execerciseObj);

					hideExerciseNode(execerciseObj);

					$('#'+execerciseObj.exerciseHeaderID).live('click',function () {		
														$('#'+execerciseObj.excerciseID).toggle();		  
					});
			});

			$('#'+'nextExcerciseMover').live('click',function () {				
					delay=-1;
			});

			/**
			* Method to fetch all image nodes of one excercise node
			*/

			function fetchAllImageNodes(node){
				var allowedTimeToDisplay=exerciseImageListNode+node.excerciseID.substr(exerciseNode.length);
				var allImageDivIDs=[];
				$("#"+allowedTimeToDisplay).children('div').each(function() {
						var imageKiID=$(this).attr('id');
						//alert(imageKiID);
						allImageDivIDs.push(imageKiID);
				});
				return allImageDivIDs;
			}


			var delay = 10;
			
			function winClose() {
				window.close();
			}

			function runTimer() { 
				if(delay <= 0){				
					hideExerciseNode(exerciseObjectList[selectedExerciseNodeIndex]);

					selectedExerciseNodeIndex=getNextIndex(selectedExerciseNodeIndex,exerciseObjectList);

					showNextExerciseAndImages();			
				}else {
					delay--;

					document.getElementById('currentStatusdisplayer').innerText = 'Prem('+delay+')' +'  selectedExerciseNodeIndex : '+selectedExerciseNodeIndex+' allowedTimeVal : '+exerciseObjectList[selectedExerciseNodeIndex].allowedTime;
					showExerciseNode(exerciseObjectList[selectedExerciseNodeIndex]);
					showNextImage(exerciseObjectList[selectedExerciseNodeIndex]);
					setTimeout('runTimer()', 10*1000);
				}
			}

			/**
			*Central method to get next index out of given array in cyclic order , i.e. 0-maxValue-0
			*/
			function getNextIndex(currIndex,arrayOfObj){
				var nxt=(currIndex + (arrayOfObj.length) + 1)%(arrayOfObj.length);
				nxt=parseInt(nxt);
				return nxt;
			}

			function showNextImage(node){			
				hideSelectedImageDiv();			
				selectedExerciseNodeImageIndex=getNextIndex(selectedExerciseNodeImageIndex,node.imageIDList);
				showSelectedImageDiv();			
			}

			function showSelectedImageDiv(){ 
				var selNode=exerciseObjectList[selectedExerciseNodeIndex];
				$('#'+selNode.imageIDList[selectedExerciseNodeImageIndex]).show();
			}

			function hideSelectedImageDiv(){ 
				var selNode=exerciseObjectList[selectedExerciseNodeIndex];
				$('#'+selNode.imageIDList[selectedExerciseNodeImageIndex]).hide();
			}

			function showNextExerciseAndImages() {
				var allowedTimeVal=0;
				allowedTimeVal=(exerciseObjectList[selectedExerciseNodeIndex]).allowedTime;
				delay=parseInt(allowedTimeVal);
				selectedExerciseNodeImageIndex=0;

				showExerciseNode(exerciseObjectList[selectedExerciseNodeIndex]);
				showSelectedImageDiv();
				runTimer();
			}
			
			
		</script>
	</body>
</html>