/////////////////////////////////////////////////////////
//get scroll position
var get_scroll = function(){
var x = 0, y = 0;
if( typeof( window.pageYOffset ) == 'number' ) {
    //Netscape compliant
    y = window.pageYOffset;
    x = window.pageXOffset;
} else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
    //DOM compliant
    y = document.body.scrollTop;
    x = document.body.scrollLeft;
} else if( document.documentElement && 
( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
    //IE6 standards compliant mode
    y = document.documentElement.scrollTop;
    x = document.documentElement.scrollLeft;
}
var obj = new Object();
obj.x = x;
obj.y = y;
return obj;
};
//////////////////////////////////
function saveScroll(){
var scroll = get_scroll(); 
document.getElementById("x").value = scroll.x;
document.getElementById("y").value = scroll.y;
}
//////////////////////////////////////////////
////////This runs at <body onload = "setScroll()" >///////////////////////////
function setScroll(){
var x = "<?php echo $_POST['x']; ?>";
var y = "<?php echo $_POST['y']; ?>";

if(typeof x != 'undefined')
window.scrollTo(x, y);
}
////////////////////////////////////////////////////////////////


//<body onload = "setScroll()">
//
//<!--Post variables to save x & y -->
//
//<input name="x" id="x" type="hidden" value="" />
//<input name="y" id="y" type="hidden" value="" />
//</body>