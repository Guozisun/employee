<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<script type="text/javascript">

 $().ready(function(){
		$("form input").blur(function(){
			
			var $parent = $(this).parent();
			$parent.find(".msg").remove();
			
			if ($(this).is("#username")) {
				var username=$("#username").val();
				var pattern = /^[\u4e00-\u9fa5]{1,20}$/;
				if ($.trim(username)=="") {
					var errorMsg = "用户名不能为空";
					//class='msg onError' 中间的空格是层叠样式的格式
					$parent.append("<span class='msg high'>" + errorMsg + "</span>");
					return false;
				}/* else if(pattern.test(this.value)==false){
					var erroMsg="请输入中文姓名！";
					$parent.append("<span class='msg high'>"+ erroMsg+"</span>");
					return false;
				} */
				else{
					return true;
				}
				
			}
			
			if($(this).is("#password")) {
				//运用jQuery中的$.trim()方法，去掉首位空格
				var passwordReg =  /^[\\da-zA-Z]{4,20}$/;
				if($.trim(this.value) == "" ) {
					var errorMsg = " 密码不能为空！";
					//class='msg onError' 中间的空格是层叠样式的格式
					$parent.append("<span class='msg high'>" + errorMsg + "</span>");
					return false;
				}/* else if(passwordReg.test($.trim(this.value))==false) {
					var errorMsg = " 请输入6位混合密码";
					//class='msg onError' 中间的空格是层叠样式的格式
					$parent.append("<span class='msg high'>" + errorMsg + "</span>");
					
				} */
				else {
					
					return true;
				}
			}
			$("span").delay(2000).fadeOut(100);
		}).keyup(function() {
			//triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
			$(this).triggerHandler("blur");
		}).focus(function() {
			$(this).triggerHandler("blur");
		});
		
		$("#img1").click(function(){
			$(this).attr("src","drawImage1?"+Math.random());
				
		})
		$("#msg").delay(1000).fadeOut(100);
		/*  $("#send").click(function() {
			//trigger 事件执行完后，浏览器会为submit按钮获得焦点
			$("form input").trigger("blur");
			var numError = $(".high").length;
			if(numError) {
				return false;
			}
			var username= $("form").find("[name='username']").val();
			var password= $("form").find("[name='password']").val();
			
			$.ajax({
				url:"user?type=login",
				type:"post",
				data:{
					username:username,
					password:password
					
				},
				success:function(data){
					alert(data);
					if(data== 'true'){
						
						location.href="index";
					}else{
						
						$("#msf").append("<span class='msf'>用户名或密码不正确</span>");
						$("span").delay(200).fadeOut(100);
					} 
				}
				
				
				
				
				
			})
		}); */
		
		 /* if($.cookie("password") != ''){
             $("#password").val($.cookie("password"));
      	 }
       if($.cookie("username") != ''){
             $("#username").val($.cookie("username"));
       }
       function check(){  
           //记住我功能使用
           //写入cookie
           if ($("#remember-me").prop("checked") == true) {
               var name = $("#name").val();
               var password = $("#password").val();
               //alert(passWord);
               $.cookie("name", name);
               $.cookie("password", password,{ expires: 7 }); // 存储一个带7天期限的 cookie 如果{ expires: 7 } 不写则cookie只相当回话效果
           } else {
               $.cookie("name", "");
               $.cookie("password", "");
           }
       }  */
	});

</script>
<title>Insert title here</title>
<style>
*{
	padding: 0px;
	margin: 0px;
	
}
body{
	 background:  url('img/001.jpg') no-repeat fixed center; 

}
.high {
	color: red;
	text-align: center;
	
}

.msg {
	font-size: 15px;
}
.box{
width: 500px;
height: 500px;
margin: 100px auto;




}
.msf{
	color: red;
	font-size: 20px;

}
#msf{
	text-align: center;

}
.box1{
	width: 500px;
	height: 50px;
	text-align: center;
	line-height: 50px;
	color: #BB4444;
	font-size: 25px;

}


.color{
	color: orange;

}

</style>
</head>
<body>
	<div class="box">
	
	<div class="box1">
	用户登录
	</div>

	<form class="form-horizontal" id="form" action="user?type=login" method="post">
  		<div class="form-group" >
    <label  class="col-sm-3 control-label color">Username</label>
    <div class="col-sm-7">
      <input type="text" class="form-control" name="username" id="username" placeholder="username" value="${abc }">
    </div>
  </div>
  <div class="form-group" >
    <label for="inputPassword3" class="col-sm-3 control-label color">Password</label>
    <div class="col-sm-7">
      <input type="password" class="form-control" name="password" id="password" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
  <label for="inputPassword3" class="col-sm-3 control-label color">验证码</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" name="check" id="check" placeholder="验证码">
    </div>
     <div class="col-sm-5">
      <img id="img1" src="${pageContext.request.contextPath}/drawImage1"/>
    </div>
  </div>
   <div id="msg">${msg }</div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-4">
      <button type="submit" class="btn btn-success btn-lg" style="width: 200px"  id="send">登录</button>
    </div>
  </div>
  <div class="form-group">
  <div class="col-sm-offset-3 col-sm-4">
    	<a href="user?type=risgter">账号未注册，请注册</a>
  </div>
  </div>
  <div class="form-group" id="msf">
  
  
  
  </div>
 
</form>
</div>


</body>
</html>