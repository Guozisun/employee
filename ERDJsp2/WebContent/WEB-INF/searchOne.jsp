<%@page import="com.sun.pojo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.box {
	width: 400px;
	height: 300px;
	margin: 0 auto;
	overflow: scroll;
}

.box1 {
	width: 400px;
	height: 20px;
	margin: 0 auto;
}

.box1 button {
	background-color: orange;
	border: 0px;
	width: 90px;
}

table tbody tr td {
	height: 20px;
	width: 80px;
	text-align: center;
	border: 1px solid #cad9ea;
}

table {
	margin: 0px;
	text-align: center;
	padding: 0px;
	border-collapse: collapse;
}

table thead tr {
	background: gray;
}

table tbody tr:nth-child(odd) {
	background: #fff;
}

table tbody tr:nth-child(even) {
	background: #f5fafa;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<div class="box">
		<table>
			<thead>
				
				<tr>
					<td>ID</td>
					<td>姓名</td>
					<td>性别</td>
					<td>年龄</td>
					<td>#</td>
					<td>#</td>
				</tr>
			</thead>
			<tbody>
				<%
					Employee list = (Employee) request.getAttribute("user");
				%>
				
				<tr>
					<td><%=list.getId()%></td>
					<td><%=list.getName()%></td>
					<td><%=list.getSex()%></td>
					<td><%=list.getAge()%></td>
					<td><a href="listServlet?who=delete&id=<%=list.getId()%>">删除</a></td>
					<td><a href="update.jsp?who=update&id=<%=list.getId()%>&name=<%=list.getName()%>&sex=<%=list.getSex()%>&age=<%=list.getAge()%>">修改</a></td>
				</tr>
				<%
					
				%>
			</tbody>


		</table>

	</div>
	<div class="box1">
		<a href="insert.jsp"><button>增加</button></a>&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="text" name="sousuo" />
		<a href="listServlet?who=search"><button>搜索</button></a>
				

	</div>
</body>
</html>