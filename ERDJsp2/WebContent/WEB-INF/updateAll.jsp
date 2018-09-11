<%@page import="com.sun.pojo.Employee"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>


<style>
.box {
	
	width: 400px;
	height: 300px;
	float:left;
	
}
</style>
<title>Insert title here</title>
</head>
<body>
			<%Employee emp = (Employee)request.getAttribute("emp");
				String ids = (String)request.getAttribute("ids");
			
			%>
			
	<div class="box">
		<form class="form-horizontal" action="listServlet?who=update" method="post">
			<div class="form-group">
					<input type="hidden" name="ids" value=<%=ids%>>
				<label  class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name1"
						value=<%=emp.getName() %> >
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">性别:</label>
				<div class="col-sm-10">
					<input type="radio" name="sex1" value="男"<%if(emp.getSex().equals("男")){ %> checked="checked"<%} %> >男
					<input type="radio" name="sex1" value="女"<%if(emp.getSex().equals("女")){ %> checked="checked"<%} %>>女
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">年龄:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age1"
						id="inputEmail3" value=<%=emp.getAge() %>>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">保存</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>