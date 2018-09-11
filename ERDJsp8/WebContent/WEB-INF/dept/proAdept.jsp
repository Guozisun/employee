

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
		var selectId = -1;
		var ids = new Array();
		$("tr").click(function() {
			$("tr").removeClass("select");
			$(this).addClass("select");
		})
		
		$("#btn1").click(function(){
			var proId= $(".box1-1").find("[name=proId]").val();
			if(proId!=null){
			location.href="deptServlet?who=addPro&deptId=${dept.id}&proId="+proId;
			}else{
				$(this).unbind("click");
				$(this).addClass("disabled");
				alert("数据已选完，请不要重复点击");
			}
		})
		$("#btn4").click(function(){
			var proId= $("#pro .select").data("id");
			if(proId!=null){
			location.href="deptServlet?who=deletePro&deptId=${dept.id}&proId="+proId;
			}else{
				$(this).unbind("click");
				$(this).addClass("disabled");
				alert("请选中数据");
				
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
	padding:0px;
	margin: 0 auto;
	width: 400px;
	height: 60px;
	line-height: 60px;
	}

.box .select {
	background: orange;
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
	text-align: center;
	font-size: 20px;
	
	
}
.box1-1{
width: 200px;
height: 22px;
line-height: 22px;
text-align:center;
float:left;

}

</style>
<title>Insert title here</title>
</head>
<body>
	<div class=box3>
	${dept.name }
	 </div>
	<div class="box">
		<table class="table  table-bordered " id="pro">
			<thead>
				
				<tr>
					<th>ID</th>
					<th>项目名称</th>
			
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${proList }" var="pro">
				<tr data-id=${pro.id }>
					<td>${pro.id }</td>
					<td>${pro.name }</td>
				</tr>
				</c:forEach>
			</tbody>


		</table>

	</div>
	<div class="box2">
	
  	<ul class="pagination" style="float:right;">
    <li >
      <a id="pre" href="deptServlet?who=xm&page=${p.nowPage-1}&deptId=${dept.id}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
     <li><a href="deptServlet?who=xm&page=${1 }&deptId=${dept.id}">首页</a></li>
     
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
  
   
    <li <c:if test="${p.nowPage==status.index }">class="active" </c:if>><a href="deptServlet?who=xm&page=${status.index }&deptId=${dept.id}">${status.index }</a></li>
   </c:forEach>
    <li><a href="deptServlet?who=xm&page=${p.pages}&deptId=${dept.id}">尾页</a></li>
    <li>
      <a id="next" href="deptServlet?who=xm&page=${p.nowPage+1 }&deptId=${dept.id}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>


	</div>

	<div class="box1">
		<div class="box1-1">
		<select style="width: 150px;float:left;height: 22px" name="proId" >
		<c:forEach items="${proNoList }" var="proNo">
			<option value="${proNo.id }">${proNo.name }</option>
		</c:forEach> 
		</select>
		</div>
		<div class="box1-1">
		<button class="btn btn-primary btn-xs" id="btn1" style="float:left;">添加</button>
		<button class="btn btn-success btn-xs" id="btn4" style="float:right;">删除</button>
		</div>
	</div>
	
</body>
</html>