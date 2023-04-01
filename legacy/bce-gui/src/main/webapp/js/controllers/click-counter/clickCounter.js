var button = document.getElementById("clickme"),
  count = 0,
  thousandCount=0,
  lacCount=0,
  croreCount=0,
  arabCount=0;
  
  var prem = document.getElementById("prem");
  var mybody = document.getElementById("mybody");
  
  //////////////
  
  var timerInterval = 1000;
  var timerStarted = false;
   var timerID = null;
			
	var slideShowStart = function () {
		timerStarted = true;
		timerID = setTimeout(function nextAltFn() {
			mybody.onclick();
			console.log("aaaaaaa=>>>>");
			timerID = setTimeout(nextAltFn, timerInterval);
		}, timerInterval);
		console.log("Timer started : timerID " + timerID);
		console.log("timerStarted " + timerStarted);
	};

	var slideShowCancel = function () {
		timerStarted = false;
		clearTimeout(timerID);
		timerID = null;

		console.log("Timer stopped : timerID " + timerID);
		console.log("timerStarted " + timerStarted);
	};
	
	//slideShowStart();
  
  ///////////////////
  
  
mybody.onclick= function() {
  count += 1;
  if(count>=1000){	  
	  thousandCount += 1;
	  if(thousandCount>=100){
		  lacCount+=1;
		  thousandCount=(thousandCount-100);
	  }
	  if(lacCount>=100){
		  croreCount+=1;
		  lacCount=(lacCount-100);
	  }
	  if(croreCount>=100){
		  arabCount+=1;
		  croreCount=(croreCount-100);
	  }
	  count=(count - 1000);
	  
  }
  button.innerHTML = "Click me: " + ((arabCount>0)? arabCount +" Arab , ":" ")
  + ((croreCount>0)? croreCount +" Crore , ":" ")
  + ((lacCount>0)? lacCount +" Lac , ":" ")
  + ((thousandCount>0)? thousandCount +" Thousand , ":" ") + 
  ((count>0)?count:" ");
  
  /* button.innerHTML = "Click me: " + prefixAdd(" Arab , ",arabCount)+ prefixAdd(" Crore , ",croreCount)+ prefixAdd(" Lac , ",lacCount)+ prefixAdd(" Thousand , ",thousandCount) + 
  ((count>0)?count:" "); */
}

/* function prefixAdd(prefix, countt){
	
	return (((countt>0)? countt +prefix:" ");
} */




