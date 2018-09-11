

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			
			
	<div class="box">
		<form class="form-horizontal" action="listServlet?who=update" method="post">
			<div class="form-group">
					<input type="hidden" name="ids" value=${ids }>
				<label  class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name1"
						value=${emp.name } >
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">性别:</label>
				<div class="col-sm-10">
					<input type="radio" <c:if test="${emp.sex=='男' }">checked</c:if> name="sex1" value="男" />男
					<input type="radio" <c:if test="${emp.sex=='女' }">checked</c:if>  name="sex1" value="女" />女
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">年龄:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age1"
						id="inputEmail3" value=${emp.age }>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">部门:</label>
				<div class="col-sm-10">
					<select name="deptId">
						<option value="">请选择部门</option>
						<c:forEach items="${deptList }" var="dept" >
							<option <c:if test="${dept.id==emp.dept.id }"> selected</c:if> value="${dept.id }">${dept.name}</option>
						</c:forEach>
					</select>
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