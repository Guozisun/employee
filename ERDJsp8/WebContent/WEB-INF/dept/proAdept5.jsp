

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/drag.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$(document).on("click","li",function(){
			$(this).toggleClass("select");
		});
	
		$( "#sortable1,#sortable2" ).sortable({
			 connectWith: ".connectedSortable",
			 dropOnEmpty: true,
			 revert: true
		}).disableSelection();
		$(" #sortable1").sortable({receive:function(event,ui){
			var array = new Array();
			var proNoId=ui.item.data("id");
			var dAp={
			 	  	d_id:${dept.id},
				  	p_id:proNoId
					
			}
		array.push(dAp);
		var array1=JSON.stringify(array);
		var i=0;
		if(array!=null){
		$.ajax({
			url:"deptServlet?who=addPro3",
			type:"post",
			dataType:"json",
			data:{
				array:array1
				},
			success:function(data){
				
				
			}
				
		}) 
		
		}else{
			
			alert("没有可选数据!");
		}
		 
			
			
			
		}});
		$(" #main1 #sortable2").sortable({receive:function(event,ui){
			var array = new Array();
			var proNoId=ui.item.data("id");
			var dAp={
				  	d_id:${dept.id},
				  	p_id:proNoId
					
			}
		array.push(dAp);
		var array1=JSON.stringify(array);
		var i=0;
		if(array!=null){
		$.ajax({
			url:"deptServlet?who=deletePro3",
			type:"post",
			dataType:"json",
			data:{
				array:array1
				},
			success:function(data){
				
				
			},
			error:function(data){
				
				if(data==false){
					alert("111");
				}	
			}
			}) 
		
		}else{
			
			alert("没有可选数据!");
		}
		 
			
			
			
		}});
	})
</script>
<style>
*{
margin: 0;
padding: 0;

}
#main1 .box1{
	width: 100%;
	height: 30px;
	
	text-align: center;
	font-size: 22px;
}
#main1 .box2{
	width: 550px;
	height: 150px;
	margin: 10px auto;
	border: 1px solid red;
	background-image: url("mag/1.png");
	



}
#main1 .box4{
	width: 550px;
	height: 150px;
	margin: 10px auto;
	border: 1px solid;
	background-image: url("mag/2.png");
	



}

#main1 #sortable1,#main1 #sortable2{

list-style: none;
	width: 600px;
	height: 150px;
}
#sortable1 li,#sortable2 li{
	float:left;
	width: 120px;
	height: 30px;
	font-size: 12px;
	line-height:30px;
	text-align:center;
	margin-left: 10px;
	margin-bottom: 20px;
	background-color: #7FFF00;
	opacity: 0.7;
	border-radius: 8px;

}
#main1 li:hover{
	cursor: pointer;

}
 #main1 .box3{
	width: 600px;
	height: 40px;
	margin: 0px auto;
	


}
 #main1 .box3-1{
	width: 298px;
	height: 40px;
	margin: 0px auto;
	line-height:40px;
	text-align: center;
	float:left;
}
#main1 .select{
	color: 	#8B7500;
	background-color:#8B0000;
	font-size: 14px;

}

/* .box {
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

} */

</style>
<title>部门and项目</title>
</head>
<body>
	<div id="main1">
	 <div class="box1">${dept.name }</div>
	 <div class="box2 " id="add2">
	
	 <div class="ul1" >
	 <ul id="sortable1" class="connectedSortable">
	  	<c:forEach items="${proList}" var="pro">
	 
	 		<li data-id="${pro.id }">${pro.name}</li>
	 	</c:forEach>
		 </ul>
	 	
	 </div>
	
	 </div>
	 <div class="box3">
	 <div class="box3-1">
	 	<button class="btn btn-primary btn-sm" id="btn1"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span></button></div>
		<div class="box3-1"><button class="btn btn-success btn-sm" id="btn4" ><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span></button></div>
	 </div>
	 <div class="box4 " id="add1">
	 
		 <div class="ul1" >
			 <ul id="sortable2" class=" connectedSortable">
	 		 	<c:forEach items="${proNoList}" var="proNo">
	 
	 				<li data-id="${proNo.id }">${proNo.name}</li>
			 	</c:forEach>
			 </ul>
	 	
		 </div>
	 </div>
	</div>
</body>
</html>