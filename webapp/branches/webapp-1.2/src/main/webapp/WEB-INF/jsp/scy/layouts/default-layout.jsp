<%@ include file="../common-taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

   
    <script type="text/javascript" src="${baseUrl}/javascript/joint/joint.all-min.js"></script>

	<link rel="stylesheet" type="text/css"
		  href="http://yui.yahooapis.com/2.5.1/build/reset-fonts-grids/reset-fonts-grids.css"/>

	<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css"/>

	<link rel="shortcut icon" href="/webapp/themes/tels/default/images/favicon_panda.ico" />
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dijit/themes/soria/soria.css" />
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dijit/themes/soria/layout/Dialog.css" />
    <link href="http://ajax.googleapis.com/ajax/libs/dojo/1.4.0/dojox/form/resources/FileUploader.css" rel="stylesheet" />

	<script type="text/javascript" src="http://www.google.com/jsapi"></script>

	<script type="text/javascript">


        function initUploader(){
            dojo.require("dojox.form.FileUploader");
            var uploader = new dojox.form.FileUploader({
                hoverClass:"uploadHover",
                activeClass:"uploadBtn",
                pressClass:"uploadPress",
                disabledClass:"uploadDisable",
                uploadUrl:'/webapp/components/fileupload/fileupload.html'
            }, "btn");

        }
        function postForm(form){
            var xhrArgs = {
                    form: dojo.byId(form),
                    handleAs: "text",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                    },

                    load: function(data) {
                        //dojo.byId("response").innerHTML = "Form posted.";
                        console.log("Form posted.");
                    },
                    error: function(error) {
                         console.log("Form not posted.");
                        //dojo.byId("response").innerHTML = "Form not posted.";
                    }
                }
                //Call the asynchronous xhrPost
                console.log("Form being sent...");
                var deferred = dojo.xhrPost(xhrArgs);
            }



        function loadDialog(url, title){
            var theDialog =  new dijit.Dialog({
                title: title,
                style: "width:500px;height:300px;",
                href: url
            });

            theDialog.show();
        }

        function updateAjaxSlider(id, sliderObject){
            dojo.byId('sliderValue' + id ).value=sliderObject.value;
            
            postForm('ajaxSliderForm' + id);
        }

        function updateActivityStatus(statusid, username){
            console.info("updating status....." + statusid);
            var targetNode = dojo.byId(statusid);
            var xhrArgs = {
                url: '/webapp/components/realtime/currentStudentActivity.html?username=' + username,
                handleAs: "text",
                load: function(data){
                    data = data.replace(/\n/g, "<br/>");

                    data = data.replace(/\t/g, "&nbsp;&nbsp;&nbsp;");
                    targetNode.innerHTML = data;
                },
                error: function(error){
                    targetNode.innerHTML = "An unexpected error occured: " + error;
                }
            }

            var deferred = dojo.xhrGet(xhrArgs);
        }
        function doUpload(){

            console.log("doUpload");
            dojo.byId("fileToUpload").innerHTML="uploading...";
            f0.upload();
        };
        //var f0 = null;
        //dojo.connect(f0,"onChange",function(_a){console.log("DATA:",_a);dojo.forEach(_a,function(d){if(selectMultipleFiles){dojo.byId("fileToUpload").value+=d.name+" "+Math.ceil(d.size*0.001)+"kb \n";}else{dojo.byId("fileToUpload").value=d.name+" "+Math.ceil(d.size*0.001)+"kb \n";}});});dojo.connect(f0,"onProgress",function(_c){console.warn("onProgress",_c);dojo.byId("fileToUpload").value="";dojo.forEach(_c,function(d){dojo.byId("fileToUpload").value+="("+d.percent+"%) "+d.name+" \n";});});dojo.connect(f0,"onComplete",function(_e){console.warn("onComplete",_e);dojo.forEach(_e,function(d){dojo.byId("uploadedFiles").value+=d.file+" \n";dojo.byId("rgtCol").innerHTML+=imageHTML(d);rmFiles+=d.file+";";});});Destroy=function(){f0.destroyAll();};
		var djConfig = {
			isDebug: true,
			parseOnLoad: true,
			xdWaitSeconds: 5,
			noFirebugLite: true,
			cacheBust: new Date(),
            uploaderPath: '../themes/scy/default/images/uploader.swf'

		};
		google.load("dojo", "1.4.0");
        google.setOnLoadCallback(startDojo);
        function startDojo(){

            dojo.require("dojo.parser");
            dojo.require("dojo.dnd.Moveable");
            dojo.require("dijit.form.CheckBox");
            dojo.require("dijit.InlineEditBox");
            dojo.require("dijit.form.Textarea");
            dojo.require("dijit.form.TextBox");
            dojo.require("dijit.Dialog");
            dojo.require("dijit.form.HorizontalSlider");
            dojo.require("dijit.form.HorizontalRuleLabels");
            dojo.require("dijit.form.DateTextBox");
            dojo.require("dijit.form.TimeTextBox");
            dojo.require("dijit.form.NumberTextBox");
            dojo.require("dojox.gfx");


        }

      var lasMap = new Array();
      var eloMap = new Array();
      function createLas(lasObj){
           var lasX = lasObj.xPos;
           var lasY = lasObj.yPos;
           var lasTitle = lasObj.name;
           var lasW = 80;
           var lasH = 50;
                    // "createLasContentBox(lasObj, lasW, lasH);" +

            return uml.State.create({
              rect: {x: lasX, y: lasY, width: lasW, height: lasH},
              label: lasTitle,
              attrs: {
                fill: "90-#000-#0af:1-#fff"
           },
              actions: {

              // entry: \"create()\"\n" +
              }
            }).toggleGhosting();
        }

        function createElo(eloObj){
               var eloX = eloObj.xPos;
               var eloY = eloObj.yPos; 
               return  uml.Class.create({
                  rect: {x: eloX, y: eloY, width: 20, height: 20, rotate: 45},
                  //label: \"Client\",\n" +
                  attrs: {
                    fill: "135-#000-#0a0:1-#fff"
                  }
                });
            }

        function createLasContentBox(lasObj, lasId){


            var worldLeftOffset = document.getElementById("world").offsetLeft;
            var worldTopOffset = document.getElementById("world").offsetTop;

            var s2w = lasObj.rect.width;
            var s2h = lasObj.rect.height;
            var s2x = lasObj.rect.x;
            var s2y = lasObj.rect.y;
            var s2Div = document.createElement("div");
            s2Div.setAttribute("id", lasId);
            s2Div.style.width = s2w + "px";
            s2Div.style.height = "40px";
            s2Div.style.backgroundColor = "transparent";

            s2Div.style.cursor = "pointer";

            s2Div.style.position = "absolute"
            s2Div.style.left = (worldLeftOffset + s2x) + "px";

            s2Div.style.top = (worldTopOffset + s2y) -10 + "px";
            s2Div.style.paddingTop = "30px";
            s2Div.onclick = function(){
                var theDialog =  new dijit.Dialog({
                    title: lasObj.label,
                    style: "width:500px;height:300px;",
                    id: "dialog_" + lasObj.id,
                    content: "<div id='dialogContents_\" + lasObj.id + \"'>Contents here</div>"
                });

                theDialog.show();
            }
            document.getElementById("world").appendChild(s2Div);

        }
        
        function updateLasContentBox(){
            var maxNumberOfParticipants = 6;
            var rndNumber = 1;
            var rndIconNumber = 0;
            var userIconArray = new Array();
            userIconArray[0] = "<img src='${baseUrl}/themes/scy/default/images/green_man_icon.png' />";
            userIconArray[1] = "<img src='${baseUrl}/themes/scy/default/images/brown_man_icon.png' />";



            for(var i in lasMap){
                rndNumber =Math.floor(Math.random()*maxNumberOfParticipants);
                var imgStr = "";
                for(j = 0;j<rndNumber;j++){
                    rndIconNumber = Math.floor(Math.random()*2);
                    imgStr += userIconArray[rndIconNumber];
                }
                document.getElementById(i).innerHTML = imgStr;
            }
        }



	</script>
	<title><tiles:insertAttribute name="title" defaultValue=""/></title>
	<tiles:insertAttribute name="extrahead" defaultValue=""/>
</head>
<body class="<tiles:insertAttribute name="bodyclass" defaultValue=""/> soria">
<div id="doc4" class="yui-t7">
	<div id="hd" role="banner">
        <div class="options">
			<tiles:insertAttribute name="options"/>
		</div>
		<div class="logo"></div>
		<div class="menubar-wrapper">
			<div class="border-left">
				<div class="border-right">
					<ul class="menu">
						<tiles:insertAttribute name="menu"/>
					</ul>
				</div>
			</div>
		</div>
        <div id="breadcrumbs"><s:breadcrumbs/></div>

	</div>

	<div id="bd" role="main">

        <div id="centeredDiv">

                             
							<tiles:insertAttribute name="main"/>

        </div>
	</div>
	<div id="ft" role="contentinfo" class="footer">
		<div class="border-left">
			<div class="border-right">
				<tiles:insertAttribute name="footer"/>
			</div>
		</div>
		<div class="bottom">
			<div class="border-left">
				<div class="border-right"></div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
