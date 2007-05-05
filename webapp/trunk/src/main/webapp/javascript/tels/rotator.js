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
var imgs = new Array('./themes/tels/default/images/wiseInAction/kidsOnComputer.jpg','./themes/tels/default/images/wiseInAction/kidsinaquarium.jpg','./themes/tels/default/images/wiseInAction/csiScreenshot.jpg','./themes/tels/default/images/wiseInAction/Studentputdata.jpg','./themes/tels/default/images/wiseInAction/AirBag.jpg',
'./themes/tels/default/images/wiseInAction/Reflection.jpg','./themes/tels/default/images/wiseInAction/PondSim.jpg','./themes/tels/default/images/wiseInAction/OnlineDiscussion.jpg',
'./themes/tels/default/images/wiseInAction/MolecularModel.jpg','./themes/tels/default/images/wiseInAction/SkinCells.jpg');

var imgAr = new Array("images/Action-Button-White.png",
"images/Action-Button-Red.png");
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

function colorImage(old,num){
  switch(old){
	    case 1: document.circles.src = imgs2[num].src; break;
		case 2: document.circles2.src = imgs2[num].src; break;
		case 3: document.circles3.src = imgs2[num].src; break;
		case 4: document.circles4.src = imgs2[num].src; break;
		case 5: document.circles5.src = imgs2[num].src; break;
		case 6: document.circles6.src = imgs2[num].src; break;
		case 7: document.circles7.src = imgs2[num].src; break;
		case 8: document.circles8.src = imgs2[num].src; break;
		case 9: document.circles9.src = imgs2[num].src; break;
		case 10: document.circles10.src = imgs2[num].src; break;
		default: break;
  }
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

function onOrOff(prevClick,ind,name){
   var onOff = 0;
   if(prevClick==ind){
     onOff = 1;
   }
   swapImage(name,onOff);
}

function onOrOff_T(prevClick_T,ind,name){
  var onOff = 0;
  if(prevClick_T==ind){
    onOff = 1;
  }
  swapImage(name,onOff);
}

function swapImage(name,num){
   if(document.images){
    switch(name){
	 case 'circles':
		document.circles.src = imgs2[num].src;
		break;
	 case 'circles2':
	    document.circles2.src = imgs2[num].src;
		break;
     case 'circles3':
	    document.circles3.src = imgs2[num].src;
		break;
	 case 'circles4':
	    document.circles4.src = imgs2[num].src;
		break;
	 case 'circles5':
	    document.circles5.src = imgs2[num].src;
		break;
	 case 'circles6':
	    document.circles6.src = imgs2[num].src;
		break;
	 case 'circles7':
   	    document.circles7.src = imgs2[num].src;
		break;
	 case 'circles8':
	    document.circles8.src = imgs2[num].src;
		break;
	 case 'circles9':
  	    document.circles9.src = imgs2[num].src;
		break;
	 case 'circles10':
	    document.circles10.src = imgs2[num].src;
		break;
	 case 'circles11':
		document.circles11.src = imgs2[num].src;
		break;
	 case 'circles12':
	    document.circles12.src = imgs2[num].src;
		break;
     case 'circles13':
	    document.circles13.src = imgs2[num].src;
		break;
	 case 'circles14':
	    document.circles14.src = imgs2[num].src;
		break;
	 case 'circles15':
	    document.circles15.src = imgs2[num].src;
		break;
	 case 'circles16':
	    document.circles16.src = imgs2[num].src;
		break;
	 case 'circles17':
   	    document.circles17.src = imgs2[num].src;
		break;
	 case 'circles18':
	    document.circles18.src = imgs2[num].src;
		break;
	 case 'circles19':
  	    document.circles19.src = imgs2[num].src;
		break;
	 case 'circles20':
	    document.circles20.src = imgs2[num].src;
		break;

	default:
	    break;
   }
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
	      case 0: document.circles.src = imgs2[0].src; break;
		  case 1: document.circles2.src = imgs2[0].src; break;
		  case 2: document.circles3.src = imgs2[0].src; break;
		  case 3: document.circles4.src = imgs2[0].src; break;
		  case 4: document.circles5.src = imgs2[0].src; break;
		  case 5: document.circles6.src = imgs2[0].src; break;
		  case 6: document.circles7.src = imgs2[0].src; break;
		  case 7: document.circles8.src = imgs2[0].src; break;
		  case 8: document.circles9.src = imgs2[0].src; break;
		  case 9: document.circles10.src = imgs2[0].src; break;
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
		
	if(direction==1){thisvalue--;}else{thisvalue++;}
	
	switch(thisvalue){
	case 1: if(direction==1){ document.circles.src = imgs2[0].src;document.circles2.src = imgs2[1].src;}
			else{document.circles3.src = imgs2[0].src;document.circles2.src = imgs2[1].src;}
			break;
	case 2: if(direction==1){ document.circles2.src = imgs2[0].src;document.circles3.src = imgs2[1].src;}
			else{document.circles4.src = imgs2[0].src;document.circles3.src = imgs2[1].src;}
			break;
	case 3: if(direction==1){ document.circles3.src = imgs2[0].src;document.circles4.src = imgs2[1].src;}
			else{document.circles5.src = imgs2[0].src;document.circles4.src = imgs2[1].src;}
			break;
	case 4: if(direction==1){ document.circles4.src = imgs2[0].src;document.circles5.src = imgs2[1].src;}
			else{document.circles6.src = imgs2[0].src;document.circles5.src = imgs2[1].src;}
			break;
	case 5: if(direction==1){ document.circles5.src = imgs2[0].src;document.circles6.src = imgs2[1].src;}
			else{document.circles7.src = imgs2[0].src;document.circles6.src = imgs2[1].src;}
			break;
	case 6: if(direction==1){ document.circles6.src = imgs2[0].src;document.circles7.src = imgs2[1].src;}
			else{document.circles8.src = imgs2[0].src;document.circles7.src = imgs2[1].src;}
			break;
	case 7: if(direction==1){ document.circles7.src = imgs2[0].src;document.circles8.src = imgs2[1].src;}
			else{document.circles9.src = imgs2[0].src;document.circles8.src = imgs2[1].src;}
			break;
	case 8: if(direction==1){ document.circles8.src = imgs2[0].src;document.circles9.src = imgs2[1].src;}
			else{document.circles10.src = imgs2[0].src;document.circles9.src = imgs2[1].src;}
			break;
	case 9: if(direction==1){ document.circles9.src = imgs2[0].src;document.circles10.src = imgs2[1].src;}
			else{document.circles.src = imgs2[0].src;document.circles10.src = imgs2[1].src;}
			break;
	case 0:if(direction==1){ document.circles10.src = imgs2[0].src;document.circles.src = imgs2[1].src;}
			else{document.circles2.src = imgs2[0].src;document.circles.src = imgs2[1].src;}
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
	colorImage(counter,0);
   
	if(counter == (imgs.length)){
		counter = 0;
	}
  
	MM_swapImage('rotator', '', imgs[counter++]);
	colorImage(counter,1);
	setTimeout('randomImages()', delay);
	}
}



//-->
