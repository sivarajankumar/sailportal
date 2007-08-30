<!--
var delay = 3000;
var counter = 0;
var flag=0;
var oldCtr = -1;
var firstLClicked = 0;
var firstRClicked = 0;
var prevClick = -1;

var oldCtr_T = 0;
var counter_T = 0;
var prevClick_T = 0;
var firstLClicked_T = 0;
var firstRClicked_T = 0;

//obtained the tips on rotating Images, source code from http://www.communitymx.com/content/article.cfm?cid=651FF
// Comma separated list of images to rotate
var imgs = new Array('./themes/tels/default/images/wiseInAction/kidsOnComputer.jpg','./themes/tels/default/images/wiseInAction/kidsinaquarium.jpg','./themes/tels/default/images/wiseInAction/csiScreenshot.jpg','./themes/tels/default/images/wiseInAction/Studentputdata.jpg','./themes/tels/default/images/wiseInAction/AirBag.jpg','./themes/tels/default/images/wiseInAction/Reflection.jpg','./themes/tels/default/images/wiseInAction/PondSim.jpg','./themes/tels/default/images/wiseInAction/OnlineDiscussion.jpg',
'./themes/tels/default/images/wiseInAction/MolecularModel.jpg','./themes/tels/default/images/wiseInAction/SkinCells.jpg');

var imgAr = new Array("./themes/tels/default/images/wiseInAction/Action-Button-White.png","./themes/tels/default/images/wiseInAction/Action-Button-Yellow.png",
"./themes/tels/default/images/wiseInAction/Action-Button-Red.png");

var wiseButtons = new Array("./themes/tels/default/images/Overview-of-WISE-button.png",
							"./themes/tels/default/images/Overview-of-WISE-button-rollover.png",
							"./themes/tels/default/images/preview_wise.png",
							"./themes/tels/default/images/preview_wise_rollover.png",
							"./themes/tels/default/images/common_questions.png",
							"./themes/tels/default/images/common_questions_rollover.png",
							"./themes/tels/default/images/join_wise.png",
							"./themes/tels/default/images/join_wise_rollover.png");

var signInButtons = new Array("./themes/tels/default/images/sign_in.png",
							  "./themes/tels/default/images/sign_in_rollover.png");

var signInNewAccountButtons = new Array("./themes/tels/default/images/Go-To-Home-Page.png",
										"./themes/tels/default/images/Go-To-Home-Page-Roll.png");
										
var newsArchiveButtons = new Array("./themes/tels/default/images/News-Archive.png",
	  							   "./themes/tels/default/images/News-Archive-Roll.png");								

var registerStudentButtons = new Array("./themes/tels/default/images/Register-Another.png",
									   "./themes/tels/default/images/Register-Another-Roll.png");

var saveButtons = new Array("./themes/tels/default/images/Save.png",
							"./themes/tels/default/images/Save-Roll.png");

var cancelButtons = new Array("./themes/tels/default/images/Cancel-Reg.png",
							  "./themes/tels/default/images/Cancel-Reg-Roll.png");

var accountButtons = new Array("./themes/tels/default/images/Student-Account.png",
							   "./themes/tels/default/images/Student-Account-Roll.png",
							   "./themes/tels/default/images/Teacher-Account.png",
							   "./themes/tels/default/images/Teacher-Account-Roll.png");
							   							  
var gotomyprojectButtons = new Array("./themes/tels/default/images/Go-to-My-Proj-Runs.png", 
									 "./themes/tels/default/images/Go-to-My-Proj-Runs-Roll.png");					
									 
									 		
if(document.images){
var imgs2 = new Array();
  for(var i = 0; i < imgAr.length; i++){
	imgs2[i] = new Image();
	imgs2[i].src = imgAr[i];
  }
}

var testimonials = new Array('./themes/tels/default/images/wiseInAction/dummyfile.jpg','./themes/tels/default/images/wiseInAction/dummyfile2.jpg','./themes/tels/default/images/wiseInAction/dummyfile3.jpg',
							 './themes/tels/default/images/wiseInAction/dummyfile4.jpg','./themes/tels/default/images/wiseInAction/dummyfile5.jpg','./themes/tels/default/images/wiseInAction/dummyfile6.jpg',
							 './themes/tels/default/images/wiseInAction/dummyfile7.jpg','./themes/tels/default/images/wiseInAction/dummyfile8.jpg','./themes/tels/default/images/wiseInAction/dummyfile9.jpg',
							 './themes/tels/default/images/wiseInAction/dummyfile10.jpg');

//http://code.blizaga.com/javascript_drawing_gradients.html
function js_gradient(grWidth, grHeight, grDir, grRed1, grGreen1, grBlue1, grRed2, grGreen2, grBlue2) { 
  var i = 0;
  document.write("<div style=\"display: block; width: "+grWidth+"; height: "+grHeight+"\">"); 
  if (grDir == 'horizontal') { 
    while (i < grWidth) {  
      red=grRed1+Math.round(i*((grRed2-grRed1)/grWidth));  
      green=grGreen1+Math.round(i*((grGreen2-grGreen1)/grWidth));  
      blue=grBlue1+Math.round(i*((grBlue2-grBlue1)/grWidth));  
      document.write("<div style=\"display: block; font-size: 1px; ");  
      document.write("width: 1px; height: "+grHeight+"px; float: left;");  
      document.write("background: RGB("+red+","+green+","+blue+"); ");  
      document.write("\"><table width=1 height=1></table></div>");  
      i++;    
    }  
  } 
  else { 
    i=0; 
    while (i < grHeight) { 
      red=grRed1+Math.round(i*((grRed2-grRed1)/grHeight)); 
      green=grGreen1+Math.round(i*((grGreen2-grGreen1)/grHeight)); 
      blue=grBlue1+Math.round(i*((grBlue2-grBlue1)/grHeight)); 
      document.write("<div style=\"display: block; font-size: 1px; "); 
      document.write("width: "+grWidth+"px; height: 1px; "); 
      document.write("background: RGB("+red+","+green+","+blue+"); "); 
      document.write("\"><table width=1 height=1></table></div>"); 
      i++;   
    } 
  } 
//  document.write("</div>");
}

							 
function MM_findObj(n, d) { //  v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.01
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function setLClicked(){
  if(firstLClicked==0){
    firstLClicked = 1;
  }else{
    firstLClicked = 2;
  }
    return firstLClicked;
}

function setLClicked_T(){
 if(firstLClicked_T==0){
    firstLClicked_T = 1;
 }else{
    firstLClicked_T = 2;
 }
    return firstLClicked_T;
}

function setRClicked(){
  if(firstRClicked==0){
    firstRClicked = 1;
  }else{
    firstRClicked = 2;
  }
    return firstRClicked;
}

function setRClicked_T(){
 if(firstRClicked_T==0){
    firstRClicked_T = 1;
 }else{
    firstRClicked_T = 2;
 }
    return firstRClicked_T;
}

function onOrOff(prevClick,ind,id){
   var onOff = 0;
   if(prevClick==ind){
     onOff = 2;
   }
   swapImage(id,onOff);
}

function onOrOff_T(prevClick_T,ind,name){
  var onOff = 0;
  if(prevClick_T==ind){
    onOff = 1;
  }
  swapImage(name,onOff);
}


function swapSignInNewAccount(id,num){
	if(document.images){
		document.getElementById(id).src = signInNewAccountButtons[num];
	}
}

function swapSignIn(id,num){
	if(document.images){
		document.getElementById(id).src=signInButtons[num];
	}
}

function swapBigImage(index,counter,whichAction){
  if(whichAction=='Action'){
	flag=1;
	MM_swapImage('rotator', '', imgs[index]);
	counter=index;
	return counter;
  }else{
    MM_swapImage('rotatorT','',testimonials[index]); 
    counter_T=index;	
    return counter_T;
   }	
}

function getPrevClick(prevClick,curr,oldCtr){
      if(prevClick < 0){
			prevClick = oldCtr-1;
      }
	  switch(prevClick){
	      case 0: document.getElementById('imgPos1').src = imgs2[0].src; break;
		  case 1: document.getElementById('imgPos2').src = imgs2[0].src; break;
		  case 2: document.getElementById('imgPos3').src = imgs2[0].src; break;
		  case 3: document.getElementById('imgPos4').src = imgs2[0].src; break;
		  case 4: document.getElementById('imgPos5').src = imgs2[0].src; break;
		  case 5: document.getElementById('imgPos6').src = imgs2[0].src; break;
		  case 6: document.getElementById('imgPos7').src = imgs2[0].src; break;
		  case 7: document.getElementById('imgPos8').src = imgs2[0].src; break;
		  case 8: document.getElementById('imgPos9').src = imgs2[0].src; break;
		  case 9: document.getElementById('imgPos10').src = imgs2[0].src; break;
		  default: break;
	   }
	   prevClick = curr;
   	   return prevClick;
}

function getPrevClick_T(prevClick_T,curr,oldCtr_T){
    if(prevClick_T < 0){
	  prevClick_T = oldCtr_T - 1;
	}
	switch(prevClick_T){
	  case 0: document.circles11.src = imgs2[0].src; break;
	  case 1: document.circles12.src = imgs2[0].src; break;
	  case 2: document.circles13.src = imgs2[0].src; break;
	  case 3: document.circles14.src = imgs2[0].src; break;
	  case 4: document.circles15.src = imgs2[0].src; break;
	  case 5: document.circles16.src = imgs2[0].src; break;
	  case 6: document.circles17.src = imgs2[0].src; break;
	  case 7: document.circles18.src = imgs2[0].src; break;
	  case 8: document.circles19.src = imgs2[0].src; break;
	  case 9: document.circles20.src = imgs2[0].src; break;
	  default: break;
	}
	prevClick_T = curr;
	return prevClick_T;
}

function moveCircle(thisvalue,direction){
	var num1 = 0;
	var num2 = 2;
		
	if(direction==1){thisvalue--;}else{thisvalue++;}
	
	switch(thisvalue){
	case 1: if(direction==1){ 
				document.getElementById('imgPos1').src = imgs2[num1].src;
				document.getElementById('imgPos2').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos3').src = imgs2[num1].src;
				document.getElementById('imgPos2').src = imgs2[num2].src;
			}
			break;
	case 2: if(direction==1){ 
				document.getElementById('imgPos2').src = imgs2[num1].src;
				document.getElementById('imgPos3').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos4').src = imgs2[num1].src;
				document.getElementById('imgPos3').src = imgs2[num2].src;
			}
			break;
	case 3: if(direction==1){ 
				document.getElementById('imgPos3').src = imgs2[num1].src;
				document.getElementById('imgPos4').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos5').src = imgs2[num1].src;
				document.getElementById('imgPos4').src = imgs2[num2].src;
			}
			break;
	case 4: if(direction==1){ 
				document.getElementById('imgPos4').src = imgs2[num1].src;
				document.getElementById('imgPos5').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos6').src = imgs2[num1].src;
				document.getElementById('imgPos5').src = imgs2[num2].src;
			}
			break;
	case 5: if(direction==1){ 
				document.getElementById('imgPos5').src = imgs2[num1].src;
				document.getElementById('imgPos6').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos7').src = imgs2[num1].src;
				document.getElementById('imgPos6').src = imgs2[num2].src;
			}
			break;
	case 6: if(direction==1){ 
				document.getElementById('imgPos6').src = imgs2[num1].src;
				document.getElementById('imgPos7').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos8').src = imgs2[num1].src;
				document.getElementById('imgPos7').src = imgs2[num2].src;
			}
			break;
	case 7: if(direction==1){ 
				document.getElementById('imgPos7').src = imgs2[num1].src;
				document.getElementById('imgPos8').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos9').src = imgs2[num1].src;
				document.getElementById('imgPos8').src = imgs2[num2].src;
			}
			break;
	case 8: if(direction==1){ 
				document.getElementById('imgPos8').src = imgs2[num1].src;
				document.getElementById('imgPos9').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos10').src = imgs2[num1].src;
				document.getElementById('imgPos9').src = imgs2[num2].src;
			}
			break;
	case 9: if(direction==1){ 
				document.getElementById('imgPos9').src = imgs2[num1].src;
				document.getElementById('imgPos10').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos1').src = imgs2[num1].src;
				document.getElementById('imgPos10').src = imgs2[num2].src;
			}
			break;
	case 0:if(direction==1){ 
				document.getElementById('imgPos10').src = imgs2[num1].src;
				document.getElementById('imgPos1').src = imgs2[num2].src;
			}else{
				document.getElementById('imgPos2').src = imgs2[num1].src;
				document.getElementById('imgPos1').src = imgs2[num2].src;
			}
			break;

	default: break;
  }
	  return thisvalue;
}

function moveCircle_T(thisvalue,direction){	
	if(direction==1){thisvalue--;}else{thisvalue++;}
	
	switch(thisvalue){
	case 1: if(direction==1){ document.circles11.src = imgs2[0].src;document.circles12.src = imgs2[1].src;}
			else{document.circles13.src = imgs2[0].src;document.circles12.src = imgs2[1].src;}
			break;
	case 2: if(direction==1){ document.circles12.src = imgs2[0].src;document.circles13.src = imgs2[1].src;}
			else{document.circles14.src = imgs2[0].src;document.circles13.src = imgs2[1].src;}
			break;
	case 3: if(direction==1){ document.circles13.src = imgs2[0].src;document.circles14.src = imgs2[1].src;}
			else{document.circles15.src = imgs2[0].src;document.circles14.src = imgs2[1].src;}
			break;
	case 4: if(direction==1){ document.circles14.src = imgs2[0].src;document.circles15.src = imgs2[1].src;}
			else{document.circles16.src = imgs2[0].src;document.circles15.src = imgs2[1].src;}
			break;
	case 5: if(direction==1){ document.circles15.src = imgs2[0].src;document.circles16.src = imgs2[1].src;}
			else{document.circles17.src = imgs2[0].src;document.circles16.src = imgs2[1].src;}
			break;
	case 6: if(direction==1){ document.circles16.src = imgs2[0].src;document.circles17.src = imgs2[1].src;}
			else{document.circles18.src = imgs2[0].src;document.circles17.src = imgs2[1].src;}
			break;
	case 7: if(direction==1){ document.circles17.src = imgs2[0].src;document.circles18.src = imgs2[1].src;}
			else{document.circles19.src = imgs2[0].src;document.circles18.src = imgs2[1].src;}
			break;
	case 8: if(direction==1){ document.circles18.src = imgs2[0].src;document.circles19.src = imgs2[1].src;}
			else{document.circles20.src = imgs2[0].src;document.circles19.src = imgs2[1].src;}
			break;
	case 9: if(direction==1){ document.circles19.src = imgs2[0].src;document.circles20.src = imgs2[1].src;}
			else{document.circles11.src = imgs2[0].src;document.circles20.src = imgs2[1].src;}
			break;
	case 0:if(direction==1){ document.circles20.src = imgs2[0].src;document.circles11.src = imgs2[1].src;}
			else{document.circles12.src = imgs2[0].src;document.circles11.src = imgs2[1].src;}
			break;

	default: break;
  }
  return thisvalue;
}

function proceedToPreviousImage(firstLClicked,counter){
  flag=1;
  if(firstLClicked==1){
    counter=counter-1;
  }
  if(counter<0){
    counter=9;
	}
	MM_swapImage('rotator','',imgs[counter]);
	prevClick=counter;
	return counter-1;
//document.write(counter);

}


function proceedToPreviousImage_T(firstLClicked_T,counter_T){
  if(firstLClicked_T==1){
    counter_T=counter_T-1;
  }
  
  if(counter_T<0){
    counter_T=9;
  }
	MM_swapImage('rotatorT','',testimonials[counter_T]);
	prevClick_T = counter_T;
	return counter_T-1;
//document.write(counter);
}

function proceedToNextImage(firstRClicked,counter){
  var old = counter;
  if(flag==0){
	counter=counter-1;
  }
  flag=1;
  if(firstRClicked==1){
    counter=counter+1;
  }
  
  if(counter == (imgs.length)){
    counter = 0;
  }
  MM_swapImage('rotator', '', imgs[counter++]);
  prevClick=counter+1;
  return counter;
}

function proceedToNextImage_T(firstRClicked_T,counter_T){
  if(firstRClicked_T==1){
    counter_T = counter_T+1;
  }
  if(counter_T == (testimonials.length)){
    counter_T = 0;
  }
  MM_swapImage('rotatorT', '', testimonials[counter_T++]);
  prevClick_T = counter_T+1;
  return counter_T;
}

function randomImages(){
   if(flag==0){
	//colorImage(counter,0);
   
	if(counter == (imgs.length)){
		counter = 0;
	}
  
	MM_swapImage('rotator', '', imgs[counter++]);
	//colorImage(counter,2);
	setTimeout('randomImages()', delay);
	}
}

//-->
