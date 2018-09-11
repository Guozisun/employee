<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
/* $().ready(function(){
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
//				} else if($.trim(username)!=""&&$.trim(username).length<=1||$.trim(username).length>4){
//					var erroMsg="用户名长度过长！";
//					$parent.append("<span class='msg high'>"+ erroMsg+"</span>");
//					return false;
				}else if(pattern.test(this.value)==false){
					var erroMsg="请输入中文姓名！";
					$parent.append("<span class='msg high'>"+ erroMsg+"</span>");
					return false;
				}
				else{
					var okMsg = " 输入正确";
					$parent.find(".high").remove();
					$parent.append("<span class='msg '>" + okMsg + "</span>");
					$("span").delay(2000).fadeOut(100);
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
				}else if(passwordReg.test($.trim(this.value))==false) {
					var errorMsg = " 请输入6位混合密码";
					//class='msg onError' 中间的空格是层叠样式的格式
					$parent.append("<span class='msg high'>" + errorMsg + "</span>");
					
				}
				else {
					var okMsg = " 输入正确";
					$parent.find(".high").remove();
					$parent.append("<span class='msg '>" + okMsg + "</span>");
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
		$("#send").click(function() {
			//trigger 事件执行完后，浏览器会为submit按钮获得焦点
			$("form input").trigger("blur");
			var numError = $("form .high").length;
			if(numError) {
				return false;
			}
		});
		
		
	});
 */
</script>
<title>Insert title here</title>
<style>
.high {
	color: red;
	text-align: center;
	
}

.msg {
	font-size: 15px;
}

.box {
	width: 300px;
	height: 210px;
	border: 1px solid;
	margin: 0 auto;
	background-color: navy;
}

.box-1 {
	width: 100%;
	height: 65px;
	line-height: 50px;
	margin-left: 30px;
}

.box-2 {
	width: 100%;
	height: 65px;
	padding-left: 130px;
	color: yellow;
}


</style>
</head>
<body>
	<div class="box">
		<form action="user" method="post">
			<input type="hidden" name="hedden" value="login">
			<div class="box-1">
				<label>账号：</label><input type="text" name="username" id="username" /><br />
			</div>
			<div class="box-1">
				<label>密码：</label><input type="password" name="password"
					id="password" /><br />
			</div>
			<div class="box-2">
				<input type="submit" value="提交" /><br />
			</div>

		</form>
		
	</div>


</body>
</html>