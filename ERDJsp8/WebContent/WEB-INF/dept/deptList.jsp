

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#btn1").click(function() {
			location.href = "deptServlet?who=add1";
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
			
			$("tr").removeClass("select");
			$(this).addClass("select");
			

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
			$("#dept .upda").each(function(index, element) {
			var id = $(this).data("id");
			var name =$(this).find("[name=name]").val();
			/* var emp_count = $(this).find("[name=emp_cpunt]").val(); */
			var dept={
					id:id,
					name:name		
			}
			
			array.push(dept);
			});
			var array1=encodeURIComponent(JSON.stringify(array));
			if(array!=""){
			location.href="deptServlet?who=update2All&array="+array1;
			}else{
				alert("请选中数据");
			}
		});
		$("#btn4").click(function() {
			$("#dept .select").each(function(index, element) {
				selectId = $(this).data("id");
				ids.push(selectId);
			})
			if (ids != "") {
				if (confirm("确认删除吗？")) {
					location.href = "deptServlet?who=delete&ids=" + ids;
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
		})  */
		$("#btn6").click(function(){
			
			selectId = $("#dept .select").data("id");
			if(selectId>-1){
				
				$.ajax({
					url:"deptServlet?who=update4",
					method:"post",
					data:{
						ids:selectId
					},
					success:function(data){
						var dept=JSON.parse(data);
						
						 $("#tanchuang").find("[name=id]").val(dept.id); 
						 $("#tanchuang").find("[name=name1]").val(dept.name); 
						 $("#myModal").modal('show'); 
						
					}
			
				})
				
			}
			else{
				alert("请选中数据！");
				
			}
		
		})
		$("#updateBtn").click(function(){
			var id = $("#tanchuang").find("[name=id]").val();
			var name=$("#tanchuang").find("[name=name1]").val();
			var dept={
					id:id,
					name:name
					
			}
			var array= JSON.stringify(dept);
			$.ajax({
				url:"deptServlet?who=update41",
				method:"post",
				data:{
					dept:array
					
				},
				success:function(data){
					if(data=="true"){
						
						$("#myModal").modal('hide');
						$("#dept .select").children().eq(1).text(name);
					
					}
				}
				
				
			})
			
			
			
		})
		$("#modalbody").html("");
		$("#btnTM5").click(function(){
		
			selectId = $("#dept .select").data("id");
			if(selectId>-1){
				var url="deptServlet?who=xm5&deptId="+ selectId;
				
				$("#modalbody").load(url);
				$("#XMGL").modal('show');
		
			}
			
			
			
		})
		/* $("#myModal").modal('hide'); */
		if(${p.nowPage}<=1){
			$("#pre").parent().addClass("disabled");
			$("#pre").attr("onclick","return false");
		}
		 if(${p.nowPage}>=${p.pages}){
			$("#next").parent().addClass("disabled");
			$("#next").attr("onclick","return false");
		} 
		$("#btnTM").click(function(){
			selectId=$("#dept .select").data("id");
			if(!selectId<1){
				location.href="deptServlet?who=xm&deptId="+ selectId;
				 $("tr").removeClass("select");
			/* 	 $("#myModal").modal('show'); */
			}else{
				alert("请选中部门！");
			}
			
		 });
		 $("#btnTM2").click(function(){
			selectId=$("#dept .select").data("id");
			if(!selectId<1){
				location.href="deptServlet?who=xm2&deptId="+ selectId;
				 $("tr").removeClass("select");
			/* 	 $("#myModal").modal('show'); */
			}else{
				alert("请选中部门！");
			}
			
		 })
		  
		 $("#btnTM3").click(function(){
				selectId=$("#dept .select").data("id");
				if(!selectId<1){
					location.href="deptServlet?who=xm3&deptId="+ selectId;
					 $("tr").removeClass("select");
				/* 	 $("#myModal").modal('show'); */
				}else{
					alert("请选中部门！");
				}
				
			 })
			 $("#btnTM4").click(function(){
					selectId=$("#dept .select").data("id");
					if(!selectId<1){
						location.href="deptServlet?who=xm4&deptId="+ selectId;
						 $("tr").removeClass("select");
					/* 	 $("#myModal").modal('show'); */
					}else{
						alert("请选中部门！");
					}
					
			 	 })


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
.box .select1 {
	background: yellow;
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
width: 133px;
height: 30px;
line-height: 30px;
text-align:center;
float: left;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<h3 style="text-align: center;">部门管理系统</h3>
	<div class="box3">
	<form action="deptServlet"  method="post" >
	<div class="box3-1">
		<input type="text" name="name" placeholder="姓名" value="${c.name }"/>
	</div>
	
	<div class="box3-1">
		<input type="text" name="emp_count" placeholder="数量" value="${c.emp_count!=-1?c.emp_count:'' }"/>
	</div>
	<div class="box3-1">
		<input type="submit" value="搜索"/>
	</div>
	</form>
	
	</div>
	<div class="box">
		<table class="table  table-bordered " id="dept">
			<thead>
				
				<tr>
					<th>ID</th>
					<th>部门</th>
					<th>员工数量</th>
					
				

				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${userList }" var="dept">
				<tr data-id=${dept.id } id="dept12">

					<td>${dept.id }</td>
					<td>${dept.name }</td>
					<td><a href="listServlet?deptId = ${dept.id }">${dept.emp_count }</a></td>
					
					
				</tr>
				</c:forEach>
				
				
			</tbody>


		</table>

	</div>
	<div class="box2">
	
  	<ul class="pagination" >
    <li >
      <a id="pre" href="deptServlet?who=111&page=${p.nowPage-1}&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
     <li><a href="deptServlet?who=111&page=${1 }&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}">首页</a></li>
     
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
  
   
    <li <c:if test="${p.nowPage==status.index }">class="active" </c:if>><a href="deptServlet?who=111&page=${status.index }&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}">${status.index }</a></li>
   </c:forEach>
    <li><a href="deptServlet?who=111&page=${p.pages}&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}">尾页</a></li>
    <li>
      <a id="next" href="deptServlet?who=111&page=${p.nowPage+1 }&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>


	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">项目管理</h4>
      </div>
      <div class="modal-body">
     
      	<form class="form-horizontal emp"  method="post" id="tanchuang">
			<div class="form-group">
					<input type="hidden" name="id" value=  >
				<label  class="col-sm-2 control-label">部门名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name1"
						value= >
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
      
       <button type="button" class="btn btn-primary" id="updateBtn">修改</button>
     
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="XMGL" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">项目管理</h4>
      </div>
      <div class="modal-body" id="modalbody">
     
      
      </div>
      
    </div>
  </div>
</div>
	<div class="box1">
		<button class="btn btn-primary btn-sm" id="btn1">添加</button>
		<button class="btn btn-success btn-sm" id="btn2">页面修改</button>
		<!-- <button class="btn btn-info btn-sm" id="btn3">批量修改1</button> -->
		<button class="btn btn-success btn-sm" id="btn4">删除</button>
		<button class="btn btn-info btn-sm" id="btn6">弹窗修改</button>
		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" id="btnTM" >项目管理</button>
		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" id="btnTM2" >项目管理1</button>
		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" id="btnTM3" >项目管理2</button>
		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" id="btnTM4" >项目管理拖拽</button>
		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" id="btnTM5" >项目管理弹窗</button>
	</div>
	
</body>
</html>