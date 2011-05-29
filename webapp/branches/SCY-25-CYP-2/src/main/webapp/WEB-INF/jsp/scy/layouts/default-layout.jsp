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

    <script type="text/javascript" src="/webapp/themes/scy/eportfolio/swfobject.js"></script>
    <script type="text/javascript" src="http://www.intermedia.uio.no/www-data-public/app-scy/eportfolio/history/history.js"></script>
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
        function postForm(form, retId){
            var returnid = null;
            if(retId != null) returnid = retId.id;
            var xhrArgs = {
                    form: dojo.byId(form),
                    handleAs: "text",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                    },

                    load: function(data) {
                        if(returnid != null && document.getElementById(returnid)){
                            document.getElementById(returnid).innerHMTL = data;
                        } else {
                            console.log("Return id does not exist!!");
                        }
                        //dojo.byId("response").innerHTML = "Form posted.";
                        console.log("Form posted.");
                    },
                    error: function(error) {
                         console.log("Form not posted." + error);
                        //dojo.byId("response").innerHTML = "Form not posted.";
                    }
                }
                //Call the asynchronous xhrPost
                console.log("Form being sent...");
                var deferred = dojo.xhrPost(xhrArgs);
            }



        function loadDialog(url, title, width, height){
            var dialogWidth = 500;
            var dialogHeight = 300;

            if(width != null){
                dialogWidth = width;
            }

            if(height != null){
                dialogHeight = height;
            }

            var theDialog =  new dijit.Dialog({
                title: title,
                style: "width:" + dialogWidth + "px;height:" + dialogHeight + "px;",
                href: url
            });

            theDialog.show();
        }

        function updateAjaxSlider(id, sliderObject){
            dojo.byId('sliderValue' + id ).value=sliderObject.value;
            
            postForm('ajaxSliderForm' + id);
        }

        function updateActivityStatus(statusid, username, missionURI){
            console.info("updating status....." + statusid);
            var targetNode = dojo.byId(statusid);
            var xhrArgs = {
                url: '/webapp/components/realtime/currentStudentActivity.html?username=' + username + '&missionURI=' + missionURI,
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
            dojo.require("dijit.form.Button");
            dojo.require("dijit.Dialog");
            dojo.require("dijit.layout.TabContainer");
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dojox.layout.ContentPane");



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

        function createLasContentBox(lasObj, lasId, lasURL, dialog){
            var showInPopup;
            if(dialog == null){
                showInPopup = false;
            } else {
                if(dialog == true){
                    showInPopup = true;
                } else {
                    showInPopup = false;
                }
            }

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
                if(showInPopup){
                    if(dijit.byId("dialog_" + lasId)){
                    dijit.byId("dialog_" + lasId).destroy();                    
                }
                var theDialog =  new dijit.Dialog({
                    title: lasObj.label,
                    style: "width:500px;height:300px;",
                    id: "dialog_" + lasId,
                    content: "<div id='dialogContents_" + lasId + "'></div>"
                              


                });

                theDialog.show();
                dojo.xhrGet( {
                        url: lasURL,
                        //handleAs: "text",
                        load: function(data) {
                            if(data && !data.error){
                                document.getElementById('dialogContents_' + lasId).innerHTML = data;
                            } else {
                                console.error("Something went wrong when loading data:" + data.error);                                
                            }
                        }});
                } else {
                    if(!userClicked){
                        location.href = lasURL;
                    } else {
                        userClicked = false;
                    }


                }
            }
            document.getElementById("world").appendChild(s2Div);


        }
        
        function updateLasContentBox(UserLASConnection){
           var userIconArray = new Array();
            userIconArray[0] = "<img src='${baseUrl}/themes/scy/default/images/green_man_icon.png' />";
            userIconArray[1] = "<img src='${baseUrl}/themes/scy/default/images/brown_man_icon.png' />";
            for(i = 0;i<UserLASConnection.length;i++){
                var lasId = UserLASConnection[i].lasId;
                var userName = UserLASConnection[i].userName;
                var currentUser = new User(userName);
                currentUser.setLASId(lasId);
                userLasController.addUser(currentUser);

           }



        }
    var userClicked = false;

    function UserLasController(){
        this.lases = new Array();
        this.users = new Array();
    }



    UserLasController.prototype.addLas = function(las){
        //console.info("Adding las" + las);
        this.lases.push(las);
    }

    UserLasController.prototype.init = function(){
        setInterval("updateUserLasConnection(userLasController)", 3000);
    }

    UserLasController.prototype.setRuntimeUserInfoUrl = function(url){
        this.runtimeUserInfoUrl = url;
    }



    var lasRuntimeInfoUrl;


    function updateUserLasConnection(controller){
         dojo.xhrGet({
            url: controller.runtimeUserInfoUrl,
             handleAs: "json",
             load: function(data){
                 console.info("Users: " + data.model.UserLASConnection.length);
                 for(i = 0;i<data.model.UserLASConnection.length;i++){
                 //console.log(data.model.UserLASConnection[i].lasId);
                 console.log("userName " + data.model.UserLASConnection[i].userName);
                     var userFound = false;
                     for(j = 0;j<controller.users.length;j++){
                         if(controller.users[j].userName == data.model.UserLASConnection[i].userName){
                             //console.info("User found. Not adding: " + data.model.UserLASConnection[i].userName) ;
                             userFound = true;
                             break;
                         }
                     }
                     if(controller.users.length == 0 || !userFound){

                        controller.addUser(new User(data.model.UserLASConnection[i].userName));
                     }

                     for(var j = 0;j<controller.users.length;j++){
                         if(controller.users[j].userName == data.model.UserLASConnection[i].userName){
                            controller.users[j].setPedPlanId(data.model.UserLASConnection[i].pedagogicalPlanId);
                             console.info("PEDPLAN: " + data.model.UserLASConnection[i].pedagogicalPlanId);
                            controller.users[j].setLASId(data.model.UserLASConnection[i].lasId, controller);

                         }
                     }


                 }

             }
         })





        console.info("Updating userLasConnection....");
        console.info("Users: " + controller.users.length);
        for(i = 0;i<controller.users.length;i++){
            console.info("username: " + controller.users[i].userName);
            console.info("lasid: " + controller.users[i].LASId);
            addUserImageToLas(controller.users[i].LASId, controller.users[i].userName, controller.users[i].pedPlanId);

        }
    }

    UserLasController.prototype.addUser = function(user){
        console.info("Adding user");
        this.users.push(user);
    }

    UserLasController.prototype.updateUserLas = function(oldLasId, userId){
        if(document.getElementById(oldLasId)){
            if(document.getElementById("userIcon_" + userId)){
                document.getElementById(oldLasId).removeChild(document.getElementById("userIcon_" + userId));
            }
        }
    }

    function User(userName){
        this.userName = userName;
        this.LASId = null;
        this.pedPlanId = null;
    }

    User.prototype.setLASId = function(id, controller){
        if(id != this.LASId){
            if(this.LASId != null){
                controller.updateUserLas(this.LASId, this.userName);
            }
        }
        this.LASId = id;
        addUserImageToLas(this.LASId, this.userName, this.pedPlanId);
    }

    User.prototype.setPedPlanId = function(id){
        this.pedPlanId = id;        
    }

    function viewUser(userId, pedPlanId){
        userClicked = true;
        location.href=lasRuntimeInfoUrl + "?username=" + userId + "&pedplanid=" + pedPlanId;

    }

    function addUserImageToLas(lasId, userId, pedPlanId){
        if(!document.getElementById("userIcon_" + userId)){
            var userIcon = document.createElement("img");
            userIcon.setAttribute("src", "${baseUrl}/themes/scy/default/images/green_man_icon.png");
            userIcon.setAttribute("id", "userIcon_" + userId);
            userIcon.setAttribute("onClick", "viewUser('" + userId + "', '" + pedPlanId + "');");

        } else {
            var userIcon = document.getElementById("userIcon_" + userId);
        }

        if(document.getElementById(lasId)){
            document.getElementById(lasId).appendChild(userIcon);
        }
    }

    function loadPage(url, container){
         dojo.xhrGet({
             url: url,
             load: function(responseObject, ioArgs) {
                    if(document.getElementById('currentActivityContainer')){
                        document.getElementById('currentActivityContainer').innerHTML = responseObject;
                    }
                }
             });
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
