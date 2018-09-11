

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
	$().ready(function() {
		$("#btn1").click(function() {
			location.href = "proServlet?who=add1";
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
		/*  $(this).children().eq(2).html("<input type='text' name='emp_cpunt' value="+$(this).children().eq(2).text()+">"); */
		}) 
		
		
		$("#btn2").click(function(){
			var array= new Array();
			$("#pro .upda").each(function(index, element) {
			var id = $(this).data("id");
			var name =$(this).find("[name=name]").val();
			/* var emp_count = $(this).find("[name=emp_cpunt]").val(); */
			var dept={
					id:id,
					name:name		
			}
			array.push(dept);
			});
			var array1=encodeURIComponent(JSON.stringify(array));;
			if(array!=""){
			location.href="proServlet?who=update2All&array="+array1;
			}else{
				alert("请选中数据");
			}
		});
		$("#btn4").click(function() {
			$("#pro .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {
				if (confirm("确认删除吗？")) {
					location.href = "proServlet?who=delete&ids=" + ids;
				}
			} else {
				alert("请选中数据！");

			}
		})
		/* $("#btn3").click(function() {
			$("#dept .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {

				location.href = "deptServlet?who=update1&ids=" + ids;

			} else {
				alert("请选中数据！");

			}
		})
		$("#btn5").click(function() {
			$("#dept .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {

				location.href = "deptServlet?who=update2&ids=" + ids;

			} else {
				alert("请选中数据！");

			}
		}) */
		if(${p.nowPage}<=1){
			$("#pre").parent().addClass("disabled");
			$("#pre").attr("onclick","return false");
		}
		if(${p.nowPage}>=${p.pages}){
			$("#next").parent().addClass("disabled");
			$("#next").attr("onclick","return false");
		} 

	})
</script>
<style>
.box {
	margin: 0px auto;
	width: 400px;

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
.box3 {
	margin: 0px auto;
	width: 400px;
	height: 30px;
	
	
}
.box3-1{
width: 200px;
height: 30px;
line-height: 30px;
text-align:center;
float: left;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<h3 style="text-align: center;">项目管理系统</h3>
	<div class="box3">
	<form action="proServlet"  method="post" >
	<div class="box3-1">
		<input type="text" name="name" placeholder="姓名" value="${c.name }"/>
	</div>
	<div class="box3-1">
		<input type="submit" value="搜索"/>
	</div>
	</form>
	
	</div>
	<div class="box">
		<table class="table  table-bordered " id="pro">
			<thead>
				
				<tr>
					<th>ID</th>
					<th>项目</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${userList }" var="pro">
				<tr data-id=${pro.id }>

					<td>${pro.id }</td>
					<td>${pro.name }</td>
				
					
					
				</tr>
				</c:forEach>
				
				
			</tbody>


		</table>

	</div>
	<div class="box2">
	
  	<ul class="pagination" >
    <li >
      <a id="pre" href="proServlet?page=${p.nowPage-1}&name=${c.name}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
     <li><a href="proServlet?page=${1 }&name=${c.name}">首页</a></li>
     
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
  
   
    <li <c:if test="${p.nowPage==status.index }">class="active" </c:if>><a href="proServlet?page=${status.index }&name=${c.name}">${status.index }</a></li>
   </c:forEach>
    <li><a href="proServlet?page=${p.pages}&name=${c.name}">尾页</a></li>
    <li>
      <a id="next" href="proServlet?page=${p.nowPage+1 }&name=${c.name}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>


	</div>
	<div class="box1">
		<button class="btn btn-primary btn-sm" id="btn1">添加</button>
		<button class="btn btn-success btn-sm" id="btn2">页面修改</button>
		<!-- <button class="btn btn-info btn-sm" id="btn3">批量修改1</button> -->
		<button class="btn btn-success btn-sm" id="btn4">批量删除</button>
	<!-- 	<button class="btn btn-info btn-sm" id="btn5">批量修改2</button> -->
	</div>
</body>
</html>