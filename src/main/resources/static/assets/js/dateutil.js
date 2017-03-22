function getCurrentDate(){
	
	var date= new Date();
	var month = parseInt(date.getMonth()+1);
	var currentMonth = addZeroPrefix(month);
	var todayDate = date.getFullYear() +"-" + currentMonth+"-" + addZeroPrefix(date.getDate());
	console.log(todayDate);
	return todayDate;
}

function addZeroPrefix(num){
	var m ="";
	if ( num <10){
		m= "0"+ num;
	}
	else
	{
		m = num;
	}
	return m;
}

