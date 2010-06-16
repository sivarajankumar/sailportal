
var requiredFirefoxVersion = '3.0';
var requiredInternetExplorerVersion = '7.0';
var requiredChromeVersion = '5.0';
var requiredSafariVersion = '3.0';
var requiredQuickTimeVersion = '5.0';
var requiredFlashVersion = '5.0';
var requiredJavaVersion = '1.4';

function checkCompatibility() {
	checkJavascript();
	checkBrowser();
	checkQuickTime();
	checkFlash();
	checkJava();
}

function checkJavascript() {
	document.getElementById('javascriptResource').innerHTML = 'Javascript';
	document.getElementById('javascriptRequiredVersion').innerHTML = 'Enabled';
	document.getElementById('javascriptYourVersion').innerHTML = 'Enabled';
	document.getElementById('javascriptRequirementMet').innerHTML = "<img src='./themes/tels/default/images/check_16.gif' />";
	document.getElementById('javascriptAdditionalInfo').innerHTML = "<a href='https://www.google.com/support/adsense/bin/answer.py?answer=12654'>How to enable Javascript</a>";
}

function checkBrowser() {
	document.getElementById('browserResource').innerHTML = getBrowserName();
	document.getElementById('browserRequiredVersion').innerHTML = getBrowserRequiredVersion();
	document.getElementById('browserYourVersion').innerHTML = getBrowserVersion();
	
	var requirementMet = '';
	
	if(checkBrowserVersion()) {
		requirementMet = "<img src='./themes/tels/default/images/check_16.gif' />"
	} else {
		requirementMet = "<img src='./themes/tels/default/images/error_16.gif' />"
	}
	
	document.getElementById('browserRequirementMet').innerHTML = requirementMet;
	document.getElementById('browserAdditionalInfo').innerHTML = getBrowserAdditionalInfo();
}

function checkBrowserVersion() {
	var requiredVersion = getBrowserRequiredVersion();
	var yourVersion = getBrowserVersion();
	
	requiredVersion = parseFloat(requiredVersion);
	yourVersion = parseFloat(yourVersion);
	
	if(yourVersion >= requiredVersion) {
		return true;
	} else {
		return false;
	}
}

function getBrowserName() {
	return BrowserDetect.browser;
}

function getBrowserRequiredVersion() {
	var browserName = getBrowserName();
	var requiredVersion = '';
	
	if(browserName == 'Firefox') {
		requiredVersion = requiredFirefoxVersion;
	} else if(browserName == 'Internet Explorer') {
		requiredVersion = requiredInternetExplorerVersion;
	} else if(browserName == 'Chrome') {
		requiredVersion = requiredChromeVersion;
	} else if(browserName == 'Safari') {
		requiredVersion = requiredSafariVersion;
	}
	
	return requiredVersion;
}

function getBrowserVersion() {
	return BrowserDetect.version;
}

function getBrowserAdditionalInfo() {
	return "<a href='http://www.mozilla.com/firefox/'>Upgrade Firefox</a>";
}

function checkQuickTime() {
	document.getElementById('quickTimeResource').innerHTML = getQuickTimeName();
	document.getElementById('quickTimeRequiredVersion').innerHTML = getQuickTimeRequiredVersion();
	document.getElementById('quickTimeYourVersion').innerHTML = getQuickTimeVersion();
	
	var requirementMet = '';
	
	if(checkQuickTimeVersion()) {
		requirementMet = "<img src='./themes/tels/default/images/check_16.gif' />"
	} else {
		requirementMet = "<img src='./themes/tels/default/images/error_16.gif' />"
	}
	
	document.getElementById('quickTimeRequirementMet').innerHTML = requirementMet;
	document.getElementById('quickTimeAdditionalInfo').innerHTML = getQuickTimeAdditionalInfo();
}

function getQuickTimeName() {
	return "QuickTime";
}

function getQuickTimeRequiredVersion() {
	return requiredQuickTimeVersion;
}

function checkQuickTimeVersion() {
	var requiredVersion = getQuickTimeRequiredVersion();
	var yourVersion = getQuickTimeVersion();
	
	requiredVersion = parseFloat(requiredVersion);
	yourVersion = parseFloat(yourVersion);
	
	if(yourVersion >= requiredVersion) {
		return true;
	} else {
		return false;
	}
}

function getQuickTimeVersion() {
	var qtVersion = 'Not Installed';
	
	if (navigator.plugins) {
		for (i=0; i < navigator.plugins.length; i++ ) {
			if (navigator.plugins[i].name.indexOf("QuickTime") >= 0)
			{
				qtVersion = navigator.plugins[i].name.replace('QuickTime Plug-In ', '').replace('QuickTime Plug-in ', '');
			}
		}
	}

	return qtVersion;
}

function getQuickTimeAdditionalInfo() {
	return "<a href='http://www.apple.com/quicktime/download/'>Upgrade QuickTime</a>";
}



function getOS() {
	return BrowserDetect.OS;
}

function checkJava() {
	document.getElementById('javaResource').innerHTML = getJavaName();
	document.getElementById('javaRequiredVersion').innerHTML = getJavaRequiredVersion();
	document.getElementById('javaYourVersion').innerHTML = getJavaVersion();
	
	var requirementMet = '';
	
	if(checkJavaVersion()) {
		requirementMet = "<img src='./themes/tels/default/images/check_16.gif' />"
	} else {
		requirementMet = "<img src='./themes/tels/default/images/error_16.gif' />"
	}
	
	document.getElementById('javaRequirementMet').innerHTML = requirementMet;
	document.getElementById('javaAdditionalInfo').innerHTML = getJavaAdditionalInfo();

}

function getJavaName() {
	return "Java";
}

function getJavaRequiredVersion() {
	return requiredJavaVersion;
}

function getJavaVersion() {
	var javaVersion = 'Not Installed';
	
	var jres = deployJava.getJREs();
	
	if(jres.length > 0) {
		javaVersion = jres.toString();		
	}
	
	return javaVersion;
}

function checkJavaVersion() {
	var requiredVersion = getJavaRequiredVersion();
	var yourVersion = getJavaVersion();
	
	requiredVersion = parseFloat(requiredVersion);
	yourVersion = parseFloat(yourVersion);
	
	if(yourVersion >= requiredVersion) {
		return true;
	} else {
		return false;
	}
}

function getJavaAdditionalInfo() {
	return "<a href='http://www.java.com/download/'>Upgrade Java</a>";
}

function checkFlash() {
	document.getElementById('flashResource').innerHTML = getFlashName();
	document.getElementById('flashRequiredVersion').innerHTML = getFlashRequiredVersion();
	document.getElementById('flashYourVersion').innerHTML = getFlashVersion();
	
	var requirementMet = '';
	
	if(checkFlashVersion()) {
		requirementMet = "<img src='./themes/tels/default/images/check_16.gif' />"
	} else {
		requirementMet = "<img src='./themes/tels/default/images/error_16.gif' />"
	}
	
	document.getElementById('flashRequirementMet').innerHTML = requirementMet;
	document.getElementById('flashAdditionalInfo').innerHTML = getFlashAdditionalInfo();
}

function getFlashName() {
	return "Flash";
}

function getFlashRequiredVersion() {
	return requiredFlashVersion;
}

function getFlashVersion() {
	var flashVersion = 'Not Installed';
	
	var getFlashVersion = JSGetSwfVer();
	
	if(getFlashVersion != -1) {
		flashVersion = getFlashVersion;
	}
	
	return flashVersion;
}

function checkFlashVersion() {
	var requiredVersion = getFlashRequiredVersion();
	var yourVersion = getFlashVersion();
	
	requiredVersion = parseFloat(requiredVersion);
	yourVersion = parseFloat(yourVersion);
	
	if(yourVersion >= requiredVersion) {
		return true;
	} else {
		return false;
	}
}

function getFlashAdditionalInfo() {
	return "<a href='http://get.adobe.com/flashplayer/'>Upgrade Flash</a>";
}

//from Macromedia's Flash detection kit

//Detect Client Browser type
var isIE  = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
var isWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
var isOpera = (navigator.userAgent.indexOf("Opera") != -1) ? true : false;
jsVersion = 1.1;
//JavaScript helper required to detect Flash Player PlugIn version information
function JSGetSwfVer(i){
	// NS/Opera version >= 3 check for Flash plugin in plugin array
	if (navigator.plugins != null && navigator.plugins.length > 0) {
		if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
			var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
   		var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
			descArray = flashDescription.split(" ");
			tempArrayMajor = descArray[2].split(".");
			versionMajor = tempArrayMajor[0];
			versionMinor = tempArrayMajor[1];
			if ( descArray[3] != "" ) {
				tempArrayMinor = descArray[3].split("r");
			} else {
				tempArrayMinor = descArray[4].split("r");
			}
   		versionRevision = tempArrayMinor[1] > 0 ? tempArrayMinor[1] : 0;
         flashVer = versionMajor + "." + versionMinor + "." + versionRevision;
   	} else {
			flashVer = -1;
		}
	}
	// MSN/WebTV 2.6 supports Flash 4
	else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) flashVer = 4;
	// WebTV 2.5 supports Flash 3
	else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) flashVer = 3;
	// older WebTV supports Flash 2
	else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) flashVer = 2;
	// Can't detect in all other cases
	else {
		
		flashVer = -1;
	}
	return flashVer;
} 
//When called with reqMajorVer, reqMinorVer, reqRevision returns true if that version or greater is available
function DetectFlashVer(reqMajorVer, reqMinorVer, reqRevision) 
{
	reqVer = parseFloat(reqMajorVer + "." + reqRevision);
	// loop backwards through the versions until we find the newest version	
	for (i=25;i>0;i--) {	
		if (isIE && isWin && !isOpera) {
			versionStr = VBGetSwfVer(i);
		} else {
			versionStr = JSGetSwfVer(i);		
		}
		if (versionStr == -1 ) { 
			return false;
		} else if (versionStr != 0) {
			if(isIE && isWin && !isOpera) {
				tempArray         = versionStr.split(" ");
				tempString        = tempArray[1];
				versionArray      = tempString .split(",");				
			} else {
				versionArray      = versionStr.split(".");
			}
			versionMajor      = versionArray[0];
			versionMinor      = versionArray[1];
			versionRevision   = versionArray[2];
			
			versionString     = versionMajor + "." + versionRevision;   // 7.0r24 == 7.24
			versionNum        = parseFloat(versionString);
     	// is the major.revision >= requested major.revision AND the minor version >= requested minor
			if ( (versionMajor > reqMajorVer) && (versionNum >= reqVer) ) {
				return true;
			} else {
				return ((versionNum >= reqVer && versionMinor >= reqMinorVer) ? true : false );	
			}
		}
	}	
}