<%@page import="com.sun.pojo.Employee"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#btn1").click(function(){
			var array= new Array();
			$(".emp").each(function(index,element){
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=name1]").val();
				var sex = $(this).find("[name=sex1]:checked").val();
				var age = $(this).find("[name=age1]").val();
				var deptId=$(this).find("[name=deptId]").val();
				emp={
						id:id,
						name:name,
						sex:sex,
						age:age,
						d_id:deptId
				}
				array.push(emp);
			})
		var str = encodeURIComponent(JSON.stringify(array)); 
			location.href="listServlet?who=update2All&array="+str;
		})
		
		
		
	})
	</script>

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
			<c:forEach items="${list}" var="emp">
	<div class="box">
		<form class="form-horizontal emp"  method="post">
			<div class="form-group">
					<input type="hidden" name="id" value=${emp.id }>
				<label  class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name1"
						value=${emp.name } >
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-2 control-label">性别:</label>
				<div class="col-sm-10">
					<input type="radio" name="sex1" value="男"<c:if test="${emp.sex=='男' }"> checked="checked"</c:if> >男
					<input type="radio" name="sex1" value="女"<c:if test="${emp.sex=='女' }"> checked="checked"</c:if>>女
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
		</form>
	</div>
</c:forEach>
<button id="btn1" type="button" class="btn btn-block btn-success btn-default">按钮</button>

</body>
</html>