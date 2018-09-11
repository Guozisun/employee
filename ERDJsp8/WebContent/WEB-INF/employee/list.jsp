

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
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
			 var deptAll='<select name="deptId" style="width: 100px; height: 30px;"><option value="">请选择部门</option><c:forEach items="${deptList}" var="depts"><option  value="${depts.id}">${depts.name}</option></c:forEach></select>';

		 $(this).children().eq(4).html(deptAll);
		 $(this).children().eq(4).find("[name=deptId]").prop("value",$(this).children().eq(4).data("deptid"));
		 
		}) 
		
		
		$("#btn2").click(function(){
			var array= new Array;
			$("#emp .upda").each(function(index, element) {
			var id = $(this).data("id");
			var name =$(this).find("[name=name]").val();
			var sex = $(this).find("[name=sex]").val();
			var age = $(this).find("[name=age]").val();
			var deptId= $(this).find("[name=deptId]").val();
			emp={
					id:id,
					name:name,
					sex:sex,
					age:age,
					d_id:deptId
			}
			array.push(emp);
			});
			var array1=encodeURIComponent(JSON.stringify(array));
			if(array!=""){
			location.href="listServlet?who=update2All&array="+array1;
			}else{
				alert("请选中数据");
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
		 if(${p.nowPage}<=1){
			$("#pre").parent().addClass("disabled");
			$("#pre").attr("onclick","return false");
		}
		if(${p.nowPage}>=${p.pages}){
			$("#next").parent().addClass("disabled");
			$("#next").attr("onclick","return false");
		}
		
		$("#emp img").mouseover(function(){
			var src=$(this)[0].src;
			$(".show1").append("<img src="+src+">").slideDown();
			
		})
		$("#emp img").mouseout(function(){
			$(".show1").empty().slideUp();
		})
	});
</script>
<style>
.box {
	margin: 0px auto;
	width: 500px;

}

.box1 {
	margin: 0px auto;
	width: 500px;
	
}
.box2{
	margin: 0 auto;
	width: 500px;
	height: 80px;
	}

.box .select {
	background: orange;
}

.upda {
	
}
input{
width: 80px;
height:30px;
color: olive;
}
select{
width: 80px;
height:30px;
color: olive;
}
.box3 {
	margin: 0px auto;
	width: 500px;
	height: 30px;
	
	
}
.show1{
display:none;
	position: absolute;
	width: 300px;
	height: 200px;

	right:200px;
	top:90px;

}
.show1 img{
width: 300px;
height: 200px;

}
.box3-1{
width: 100px;
height: 30px;
line-height: 30px;
text-align:center;
float: left;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<h3 style="text-align: center;">员工管理系统</h3>
	<div class="box3">
	<form action="listServlet?who=111"  method="post" >
	<div class="box3-1">
		<input type="text" name="name" placeholder="姓名" value="${c.name }"/>
	</div>
	<div class="box3-1">
		<select name="sex" style="width: 100px; height: 30px;">
			<option value="">请选择性别</option>
			<option <c:if test="${c.sex=='男'}">selected</c:if>>男</option>
			<option <c:if test="${c.sex=='女'}">selected</c:if>>女</option>
			</select>
	</div>
	<div class="box3-1">
		<input type="text" name="age" placeholder="年龄" value="${c.age!=-1?c.age:'' }"/>
	</div>
	<div class="box3-1">
		<select name="deptId" style="width: 100px; height: 30px;">
			<option value="">请选择部门</option>
			<c:forEach items="${deptList}" var="depts">
			
			<option <c:if test="${depts.id==c.dept.id}">selected</c:if> value="${depts.id}">${depts.name}  </option>
			
			
			</c:forEach>
			</select>
	</div>
	<div class="box3-1">
		<input type="submit" value="搜索"/>
	</div>
	</form>
	
	</div>
	<div class="box">
		<table class="table  table-bordered " id="emp">
			<thead>
				
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>部门</th>
					<th>图片</th>

				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${userList }" var="emp">
				<tr data-id=${emp.id }>

					<td>${emp.id }</td>
					<td>${emp.name }</td>
					<td>${emp.sex }</td>
					<td>${emp.age }</td>
					<td data-deptid=${emp.dept.id }>${emp.dept.name }</td>
					
					<td ><c:if test="${ not empty emp.pic }"><img src="pic/${emp.pic }" style="height: 40px" /></c:if></td>
					
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="show1"></div>
	<div class="box2">
  	<ul class="pagination" >
    <li >
      <a id="pre" href="listServlet?who=111&page=${p.nowPage-1}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&deptId=${c.dept.id}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
     <li><a href="listServlet?who=111&page=${1 }&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&deptId=${c.dept.id}">首页</a></li>
     
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
  
   
    <li <c:if test="${p.nowPage==status.index }">class="active" </c:if>><a href="listServlet?who=111&page=${status.index }&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&deptId=${c.dept.id}">${status.index }</a></li>
   </c:forEach>
    <li><a href="listServlet?who=111&page=${p.pages}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&deptId=${c.dept.id}">尾页</a></li>
    <li>
      <a id="next" href="listServlet?who=111&page=${p.nowPage+1 }&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&deptId=${c.dept.id}" aria-label="Next">
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
		<button class="btn btn-info btn-sm" id="btn5">弹窗修改</button>
		

	
 </div>
</body>
</html>