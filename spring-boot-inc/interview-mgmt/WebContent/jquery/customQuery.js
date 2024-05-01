$(document).ready(function(){


var state = true;
$( "#btnListOfCategories" ).text("Hide");
$( "#btnListOfCategories" ).click(function() {
	if ( state ) {
		
		$( "#btnListOfCategories" ).text("Show");
	} else {
		
		$( "#btnListOfCategories" ).text("Hide");
	}
	state = !state;
	$("#textSectionListOfCategories").toggle("slow");
});



$(".guess_box").click(function() {
//$(".guess_box div").toggle("slow");
//$(".guess_box").each( function(){
//$(this).unbind('click');
//});
});

$(".guess_box_ques").click(function() {
//$(".guess_box_ques div").toggle("slow");
//$(".guess_box_ques").each( function(){
//$(this).unbind('click');
//});
});

$(".guess_box_ques_ans").click(function() {
//$(".guess_box_ques_ans div").toggle("slow");
//$(".guess_box_ques_ans").each( function(){
//$(this).unbind('click');
//});
});

});