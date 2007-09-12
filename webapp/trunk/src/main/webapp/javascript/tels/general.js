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
	if(value==''){
		alert('Please enter in the number of the image you wish to go to');
	}else if((value < 0) || (value > 9)){
		alert('Please enter in a value between 0 and 9');
	}else{
		document.getElementById(id).src = wiseImages[value-1];	
	}
}

//-->
