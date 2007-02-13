function addEvent(obj, eventType, fn){ 
 if (obj.addEventListener){ 
   obj.addEventListener(eventType, fn, false); 
   return true; 
 }
 else if (obj.attachEvent){ 
   var r = obj.attachEvent("on"+eventType, fn); 
   return r; 
 }
 else { 
   return false; 
 } 
}
