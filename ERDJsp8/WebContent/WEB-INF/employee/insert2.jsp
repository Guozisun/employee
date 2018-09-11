<%@page import="com.sun.pojo.Employee"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
$().ready(function(){
	


});
</script>
<style>
.box2{
	margin:30px auto;
	width: 700px;
	height: 400px;
}
.box {
	width: 400px;
	height: 300px;
	float:left;
}
.box1{
height:300px;
width: 300px;
float:left;
}
.box1 img{
	
	margin-left:20px;
	border: 1px solid;
	

}
</style>
<title>Insert title here</title>
</head>
<body>
	
	<div class="box2">
		<form action="listServlet?who=addFild"  method="post" class="form-horizontal" >
		<div class="box">
			<div class="form-group">
				<label  class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						id="inputEmail3" placeholder="请输入姓名！">
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">性别:</label>
				<div class="col-sm-10">
					<input type="radio" name="sex" value="男" checked="checked" >男
					<input type="radio" name="sex" value="女">女
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">年龄:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						id="inputEmail3" placeholder="请输入年龄！">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">部门:</label>
				<div class="col-sm-10">
					<select name="deptId">
						<option value="">请选择部门</option>
						<c:forEach items="${deptList }" var="dept" >
							<option value="${dept.id }">${dept.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			</div>
			<!-- <div class="box1">
				<img id="imageview" alt=""/>
				<input type="file" name="myFile1" style="margin-left: 20px;" id="fileupload" multiple/>
			</div> -->
			
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-8">
					<button type="submit" class="btn btn-success" style="width: 200px">保存</button>
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>