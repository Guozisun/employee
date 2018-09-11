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
	$().ready(function() {
		$("#btn1").click(function() {
			location.href = "listServlet?who=add1";
		})
		var selectId = -1;
		/*  批量删除2种方法
			一种是数组方式
			var array = new Array();
			array.push(选中元素)
			一种是拼接形式
			var	ids = "";
			ids+=选中元素+“，”；
			
		
		 */
		/* var ids = ""; */
		var ids = new Array();
		$("tr").click(function() {
			$(this).toggleClass("select");

		})
		
		 $("tbody tr").dblclick(function(index, element) {

			$(this).toggleClass("upda");
			
			if($(this).find("input").length>0)
			{
			return false;
			}
		 $(this).children().eq(1).html("<input type='text' name='name' value="+$(this).children().eq(1).text()+">"); 
		 if($(this).children().eq(2).text()=="男"){
		 $(this).children().eq(2).html("<select name='sex'><option selected>男</option><option>女</option></select>");
		 }
		 if($(this).children().eq(2).text()=="女"){
			 $(this).children().eq(2).html("<select name='sex'><option>男</option><option selected>女</option></select>");
			 }
		 $(this).children().eq(3).html("<input type='text' name='age' value="+$(this).children().eq(3).text()+">");
		}) 
		
		
		$("#btn2").click(function(){
			var array="";
			$("#emp .upda").each(function(index, element) {
			var id = $(this).data("id");
			var name =$(this).find("[name=name]").val();
			var sex = $(this).find("[name=sex]").val();
			var age = $(this).find("[name=age]").val();
			array+=id+","+name+","+sex+","+age+";";
			});
			array=array.substring(0,array.length-1);
			if(array!=""){
			location.href="listServlet?who=update2All&array="+array;
			}else{
				alert("请选中数据");
			}
		});

		$("#btn6").click(function(){
			var array=new Array();
			$("#emp .upda").each(function(index, element) {
			var id = $(this).data("id");
			var name =$(this).find("[name=name]").val();
			var sex = $(this).find("[name=sex]").val();
			var age = $(this).find("[name=age]").val();
			var emp ={
					id:id,
					name:name,
					sex:sex,
					age:age	
			}
			array.push(emp);
			});
			/* 将信息转为json数据 */
		var str = encodeURIComponent(JSON.stringify(array));
		
			 if(str!=""){
				location.href="listServlet?who=update3All&array="+str;
			} 
			 else{
				 alert("请选中数据！")
			 }
		});
		$("#btn4").click(function() {
			$("#emp .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {
				if (confirm("确认删除吗？")) {
					location.href = "listServlet?who=delete&ids=" + ids;
				}
			} else {
				alert("请选中数据！");

			}
		})
		$("#btn3").click(function() {
			$("#emp .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {

				location.href = "listServlet?who=update1&ids=" + ids;

			} else {
				alert("请选中数据！");

			}
		})
		$("#btn5").click(function() {
			$("#emp .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {

				location.href = "listServlet?who=update2&ids=" + ids;

			} else {
				alert("请选中数据！");

			}
		})

	})
</script>
<style>
.box {
	margin: 0px auto;
	width: 400px;
	height: 300px;
	overflow: scroll;
}

.box1 {
	margin: 0px auto;
	width: 400px;
	
}
.box2{
	margin: 0 auto;
	width: 400px;
	height: 80px;
	}

.box .select {
	background: orange;
}

.upda {
	
}
input{
width: 100%;
height:30px;
color: olive;
}
select{
width: 80px;
height:25px;
color: olive;
}

</style>
<title>Insert title here</title>
</head>
<body>
	<h3 style="text-align: center;">员工管理系统</h3>
	<div class="box">
		<table class="table  table-bordered " id="emp">
			<thead>
				
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>#</th>

				</tr>
			</thead>
			<tbody>
				<%
					List<Employee> list = (List<Employee>) request.getAttribute("userList");
				%>
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr data-id=<%=list.get(i).getId()%>>

					<td><%=list.get(i).getId()%></td>
					<td><%=list.get(i).getName()%></td>
					<td><%=list.get(i).getSex()%></td>
					<td><%=list.get(i).getAge()%></td>
					
				</tr>
				<%
					}
				%>
			</tbody>


		</table>

	</div>
	<div class="box2">
	
  	<ul class="pagination" >
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
     <li><a href="#">首页</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li><a href="#">尾页</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>

	</div>
	<div class="box1">
		<button class="btn btn-primary btn-sm" id="btn1">添加</button>
		<button class="btn btn-success btn-sm" id="btn2">页面修改</button>
		<button class="btn btn-info btn-sm" id="btn3">批量修改1</button>
		<button class="btn btn-success btn-sm" id="btn4">批量删除</button>
		<button class="btn btn-info btn-sm" id="btn5">批量修改2</button>
		<button class="btn btn-info btn-sm" id="btn6">json</button>
	</div>
</body>
</html>