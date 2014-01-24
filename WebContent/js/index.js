e=function element(element){
	return  document.getElementById(element);
}

document.addEventListener('DOMContentLoaded',function(){
sessionStorage.clear();

urlParam = function(name){
    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (!results)
    { 
        return 0; 
    }
    return results[1] || 0;
}	
e('email').focus();
var ac = urlParam('ac');
if(ac)
	activateuser(ac);
e('bsignup').onclick=function(){
	newuser();
}
e('sSignin').onclick=function(){return;
	e('signup').style.display = 'none';
	e('signin').style.display = 'block';
	e('sSignup').className='';
	this.className='activeMenu';
	e('email').focus();
}
e('sSignup').onclick=function(){return;
	e('signin').style.display = 'none';
	e('signup').style.display = 'block';
	e('sSignin').className='';
	this.className='activeMenu';
	e('newemail').focus();
}
e('password').onkeyup=function(e){
	if(e.keyCode==13){
		login();}
}
e('newemail').onkeyup=function(){
e('mEmail').style.color="red";
	email=e('newemail').value;
	e('mEmail').innerHTML="";
	if(email.length>3){
		if(validateEmail(email))
			checkEmail(email);
		else
			e('mEmail').innerHTML="Invalid email format";
	}else
	e('mEmail').innerHTML="";
}

});
function newuser(){
	//alert('This has been disabled due to new updates on the system');
//	return;
	var email = e("newemail").value;
	var name = e("name").value;
	var surname = e("surname").value;
	var password = e("newpassword").value;
	
	if(email=='' || validateEmail(email)!=true){
		e('mEmail').innerHTML="Please enter valid email";
		e("newemail").focus();
		return;
	}else e('mEmail').innerHTML="";
	if(name==''){
		e('mName').innerHTML="Please enter your name";
		e("name").focus();
		return;
	}else e('mName').innerHTML='';
	if(password==''){
		e('mPassword').innerHTML="Please enter password";
		e("newpassword").focus();
		return;
	}else e('mPassword').innerHTML="";
	
	params="op=newuser&email="+email+"&name="+name+"&surname="+surname+"&password="+password;
	ajax('php/pad.php',params,function(data){
		message(data);
		});
}
function checkEmail(email){
	e('mEmail').innerHTML="Checking...";
	var url = "php/pad.php";
	var params = "op=checkEmail&email="+email;
	ajax(url,params,function(data){
		if(data!='0'){
			e('mEmail').innerHTML="This email is choosen by another user";
		}else{
			e('mEmail').innerHTML="Available email &#10003;";
			e('mEmail').style.color="green";
		}
//			alert('this email exists');
	
			//self.location="home.php";
	});
	
}
function activateuser(ac){
	ajax("php/pad.php","op=activateuser&ac="+ac,
	function(data){		
		message(data);
	//	$('#vertical').html(data);
	});
}
function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 
function message(msg){
	e('top').innerHTML="<div id='message' class='red'>"+msg+"</div>";
}
function ajax(url,params,callback){
	var http = new XMLHttpRequest();
	http.open("POST", url, true);
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	http.setRequestHeader("Content-length", params.length);
	http.setRequestHeader("Connection", "close");
	http.onreadystatechange = function() {//Call a function when the state changes.
		if(http.readyState == 4 && http.status == 200) {
			var data = http.responseText;
			if(data)
				callback(data);
			//else
			//	alert('error');
		}
	}
	http.send(params);
}