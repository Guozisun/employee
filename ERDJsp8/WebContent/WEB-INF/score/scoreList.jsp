

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>



<script type="text/javascript">
	$().ready(function() {
		
		
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
		$("tr").dblclick(function() {
			$(this).toggleClass("select");
		

		})
		
		 $("tbody tr").dblclick(function(index, element) {

			$(this).toggleClass("upda");
			
			if($(this).find("input").length>0)
			{
			return false;
			}
		 $(this).children().eq(4).html("<input type='text' name='value' value="+$(this).children().eq(4).text()+">"); 
		
		 
		}) 
		
		
		$("#btn2").click(function(){
			var array= new Array;
			$("#emp .upda").each(function(index, element) {
			 var id = $(this).data("id");
			
			 var e_id=$(this).find("td").eq(1).data("empid");
			
			 var p_id=$(this).find("td").eq(3).data("proid");
				
			var value =$(this).find("[name=value]").val();
			
			var score={
					id:id,
					e_id:e_id,
					p_id:p_id,
					value:value
					
			}
		
			array.push(score);
			});
			var array1=encodeURIComponent(JSON.stringify(array));
			if(array!=""){
			location.href="scoreServlet?who=update2All&array="+array1;
			}else{
				alert("请选中数据");
			}
		});
		
		
	
		if(${p.nowPage}<=1){
			$("#pre").parent().addClass("disabled");
			$("#pre").attr("onclick","return false");
		}
		if(${p.nowPage}>=${p.pages}){
			$("#next").parent().addClass("disabled");
			$("#next").attr("onclick","return false");
		}
		
		
	 });
</script>
<style>
.box {
	margin: 0px auto;
	width: 600px;
}

.box1 {
	margin: 0px auto;
	width: 600px;
	text-align: center;
}

.box2 {
	margin: 0 auto;
	width: 600px;
	height: 80px;
}

.box .select {
	background: orange;
}

.upda {
	
}

input {
	width: 80px;
	height: 30px;
	color: olive;
}

select {
	width: 80px;
	height: 30px;
	color: olive;
}

.box3 {
	margin: 0px auto;
	width: 600px;
	height: 30px;
}

.box3-1 {
	width: 100px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	float: left;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<h3 style="text-align: center;">绩效管理系统</h3>
	<div class="box3">
		<form action="scoreServlet" method="post">
			<div class="box3-1">
				<input type="text" name="name" placeholder="姓名"
					value="${c.employee.name}" />
			</div>
			<div class="box3-1">
				<select name="deptId" style="width: 100px; height: 30px;">
					<option value="">请选择部门</option>
					<c:forEach items="${depts}" var="dept">

						<option
							<c:if test="${dept.id==c.employee.dept.id}">selected</c:if>
							value="${dept.id}">${dept.name}</option>


					</c:forEach>
				</select>
			</div>
			<div class="box3-1">
				<select name="proId" style="width: 100px; height: 30px;">
					<option value="">请选择项目</option>
					<c:forEach items="${projects}" var="project">

						<option <c:if test="${project.id==c.project.id}">selected</c:if>
							value="${project.id}">${project.name}</option>


					</c:forEach>
				</select>
			</div>
			<div class="box3-1">
				<input type="text" name="value" placeholder="成绩"
					value="${c.value!=-1?c.value:'' }" />
			</div>
			<div class="box3-1">
				<select name="gradeId" style="width: 100px; height: 30px;">
					<option value="">请选择等级</option>
					<c:forEach items="${gradeName}" var="grade" varStatus="g">

						<option <c:if test="${g.index==-1}">selected</c:if>
							value="${g.index}">${grade}</option>


					</c:forEach>
				</select>
			</div>
			<div class="box3-1">
				<input type="submit" value="搜索" />
			</div>
		</form>

	</div>
	<div class="box">
		<table class="table  table-bordered " id="emp">
			<thead>

				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>部门</th>
					<th>项目</th>
					<th>得分</th>
					<th>等级</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${userList }" var="score">
					<tr data-id=${score.id }>

						<td>${score.id }</td>
						<td data-empid=${score.employee.id }>${score.employee.name}</td>
						<td data-deptid=${score.employee.dept.id }>${score.employee.dept.name}</td>
						<td data-proid=${score.project.id }>${score.project.name }</td>
						<td>${score.value }</td>
						<td>${score.grade }</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="box2">
		<ul class="pagination" style="float: right;">
			<li><a id="pre"
				href="scoreServlet?page=${p.nowPage-1}&name=${c.employee.name }&deptId=${c.employee.dept.id}&proId=${c.project.id}&value=${c.value!=-1?c.value:'' }&gradeId=${g.index}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a
				href="scoreServlet?page=${1 }&name=${c.employee.name }&deptId=${c.employee.dept.id}&proId=${c.project.id}&value=${c.value!=-1?c.value:'' }&gradeId=${g.index}">首页</a></li>

			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">


				<li <c:if test="${p.nowPage==status.index }">class="active" </c:if>><a
					href="scoreServlet?page=${status.index }&name=${c.employee.name }&deptId=${c.employee.dept.id}&proId=${c.project.id}&value=${c.value!=-1?c.value:'' }&gradeId=${g.index}">${status.index }</a></li>
			</c:forEach>
			<li><a
				href="scoreServlet?page=${p.pages}&name=${c.employee.name }&deptId=${c.employee.dept.id}&proId=${c.project.id}&value=${c.value!=-1?c.value:'' }&gradeId=${g.index}">尾页</a></li>
			<li><a id="next"
				href="scoreServlet?page=${p.nowPage+1 }&name=${c.employee.name }&deptId=${c.employee.dept.id}&proId=${c.project.id}&value=${c.value!=-1?c.value:'' }&gradeId=${g.index}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>


	</div>


	<div class="box1">

		<button class="btn btn-success btn-sm" id="btn2">页面修改</button>



	</div>
</body>
</html>