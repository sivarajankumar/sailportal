<!--
var wiseImages = new Array('./themes/tels/default/images/wiseInAction/kidsOnComputer.jpg','./themes/tels/default/images/wiseInAction/kidsinaquarium.jpg','./themes/tels/default/images/wiseInAction/csiScreenshot.jpg','./themes/tels/default/images/wiseInAction/Studentputdata.jpg','./themes/tels/default/images/wiseInAction/AirBag.jpg','./themes/tels/default/images/wiseInAction/Reflection.jpg','./themes/tels/default/images/wiseInAction/PondSim.jpg','./themes/tels/default/images/wiseInAction/OnlineDiscussion.jpg',
'./themes/tels/default/images/wiseInAction/MolecularModel.jpg','./themes/tels/default/images/wiseInAction/SkinCells.jpg');

function swapImage(id,link){
	if(document.images){
		document.getElementById(id).src=link;
	}
}

function changeNavigationColor(id,link){
	if(document.images){
		document.getElementById(id).src = link;
	}
}

function displayNotAvailable(message){
	alert(message);	
}

function moveToImage(id,value){
	if((value >=0) && (value < 10)){
		document.getElementById(id).src = wiseImages[value-1];	
	}
}

function changeText(id,value){
if(value== 0) value = 10;
	document.getElementById(id).innerHTML= value + ' of 10';	
}

//-->
