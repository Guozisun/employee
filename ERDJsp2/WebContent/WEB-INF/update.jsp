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
<script type="text/javascript">
	$().ready(function(){
		$("#btn1").click(function(){
			var array="";
			$(".emp").each(function(index,element){
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=name1]").val();
				var sex = $(this).find("[name=sex1]:checked").val();
				var age = $(this).find("[name=age1]").val();
				array+=id+","+name+","+sex+","+age+";";
			})
			array=array.substring(0,array.length-1);
			location.href="listServlet?who=update2All&array="+array;
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
			<%List<Employee> list = (List<Employee>)request.getAttribute("list");%>
			<%for(Employee emp:list){ %>
	<div class="box">
		<form class="form-horizontal emp"  method="post">
			<div class="form-group">
					<input type="hidden" name="id" value=<%=emp.getId()%>>
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
		</form>
	</div>
<%} %>
<button id="btn1" type="button" class="btn btn-block btn-success btn-default">按钮</button>

</body>
</html>