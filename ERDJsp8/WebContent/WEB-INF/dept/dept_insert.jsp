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
	
</script>
<style>
.box {
	margin: 20px auto;
	width: 400px;
	height: 300px;
	overflow: scroll;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<div class="box">
		<form action="deptServlet?who=add"  method="post"  class="form-horizontal">
			<div class="form-group">
				<label  class="col-sm-2 control-label">姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						id="inputEmail3" placeholder="请输入姓名！">
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