<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'product.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
  <!-- 分页控件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pagination/jquery.pagination.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery.pagination/pagination.css" type="text/css"></link>
		
		<!-- 弹出框控件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/window.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pop-up.css" type="text/css"></link>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/positioning/positioning.js"></script>
  <script type="text/javascript">
        
               var pageIndex = 0;//页面索引初始值  
			   var pageSize = 4;//每页显示条数初始化，修改显示条数，修改这里即可
			   var pageCount;//总数
			   
			   $(function(){
                         pagination();
                    })
                    
                    
		         function pagination(){//创建分页标签
		                  $.ajax({
								type:"post",
						  		url: "Product_productList",
						  		dataType: "json",
						  		data:"start="+pageIndex+"&pageSize="+pageSize,
						  		success: function(result){
						  		      console.log(result);
						              pageCount=result.ListSize;
										              //分页，pageCount是总条目数，这是必选参数，其它参数都是可选
										$("#pagination").pagination(pageCount, {
									 	  callback: pageCallback,
									 	  prev_text: '上一页',        //上一页按钮里text  
										  next_text: '下一页',        //下一页按钮里text  
										  items_per_page: pageSize,  //显示条数  
										  num_display_entries: 6,    //连续分页主体部分分页条目数  
										  current_page: pageIndex,   //当前页索引  
										  num_edge_entries: 3       //两侧首尾分页条目数
										});
						              
						  		},
				                    error:function(){
				                        toastr.error('系统异常！');
				                    }
							});
			  		
			  		}
					//翻页回调
					function pageCallback(index,jq){
						//加载列表
						pageIndex=index;
						loadList();
						return false;
					}
					
					
			function loadList(){
			  var a="1000";
				$.ajax({
					type:"post",
			  		url: "Product_productList",
			  		dataType: "json",
			  		data:"start="+pageIndex+"&pageSize="+pageSize,
			  		success: function(result){
			  		      console.log(result);
			  		      pageCount=result.ListSize;
			  		      if(result.productList.length>0){
			                         con = "";
			                         $.each(result.productList, function(index, item){
			                         //加载主页面数据
			                         var productStr=item.productId.substring(0,3);
			                         
			                        con +="<div style='width:100%;height:95px;margin-top:1%;background:#fff;border:1px solid #dcdcdc'>"
								    con +=   "<table border='1' style='width:100%;height:100%'>"
								    con +=     "<tr>"
								    con +=        "<td style='width:400px'><span style='margin-left:20px;font-weight:bold;font-size:12px'>"+item.productName+"</span></td>"
								    con +=       "<td style='width:130px;text-align:center;font-size:12px;font-family:微软雅黑;font-weight:lighter;color:#696969;'>当前版本</td>"
								    con +=        "<td style='text-align:center;font-size:12px;font-family:微软雅黑;font-weight:lighter;color:#696969;'>版本更新时间</td>"
								    con +=     "</tr>"
								    con +=      "<tr>"
								    con +=        "<td><span style='margin-left:20px;font-size:12px;font-family:微软雅黑;font-weight:lighter;color:#0000cd;'>"+item.industry+"</span>"
								    if(productStr=="zcz"){
								                           con +=   "<a href='javascript:void(0)' onclick='javascript:addOrUpdate(\"upgrade\",\""+item.productId+"\")' style='text-decoration:none;'><span style='margin-left:10px;font-size:12px;font-family:微软雅黑;font-weight:lighter;color:#1e90ff;'>更新固件版本(正式)</span></a>"
								                           
								                           con +=   "<a href='javascript:void(0)' onclick='javascript:addOrUpdateT(\"upgrade\",\""+item.productId+"\")' style='text-decoration:none;'><span style='margin-left:10px;font-size:12px;font-family:微软雅黑;font-weight:lighter;color:#1e90ff;'>更新固件版本(测试)</span></a></td>"
								                 }
								    con +=        "<td style='text-align:center;font-weight:bold;font-size:12px;font-family:微软雅黑'><span>"+item.softVersion+"</span></td>"
								    con +=        "<td style='text-align:center;font-weight:bold;font-size:12px;font-family:微软雅黑'><span>"+item.version_updateTime+"</span></td>"
								    con +=      "</tr>"
								    con +=    "</table>"
								    con +=    "</div>"
		                             
		                         });
					                    $("#adiv022").html(con); 
		                         }else{
		                             $("#num").html(pageCount);
		                             noneList="";
		                             noneList+="<center><div style='color:999999;margin-left:-17%;margin-top:1%'>暂无匹配数据</div></center>";
		                             $("#adiv022").html(noneList); 
		                         }
			  		},
	                    error:function(){
	                        toastr.error('系统异常！');
	                    }
				});
			}
		
		function addOrUpdate(choose,productId){
		       var popBox = document.getElementById("popBox");
		       var popLayer = document.getElementById("popLayer");
		        $("#title").html("固件版本升级");
		        var url="Product_dojump?pageName=update&&id="+productId;
				$("#content02").load(url);
		        popBox.style.display = "block";
		        popLayer.style.display = "block";
		        
		}
		
		function addOrUpdateT(choose,productId){
		       var popBox = document.getElementById("popBox");
		       var popLayer = document.getElementById("popLayer");
		        $("#title").html("固件版本升级(测试)");
		        var url="Product_dojump?pageName=updatet&&id="+productId;
				$("#content02").load(url);
		        popBox.style.display = "block";
		        popLayer.style.display = "block";
		        
		}
		
		/*关闭弹窗*/
		    function closeBox() {
		        var popBox = document.getElementById("popBox");
		        var popLayer = document.getElementById("popLayer");
		        popBox.style.display = "none";
		        popLayer.style.display = "none";
		        pagination();
		    }
  
  </script>
  </head>
  <body style="margin:0; padding:0;height:100%;width:100%;position:relative;">
  <div style="height:80px;width:100%;background:#000000">
           <div style="padding-top:1%;font-size:25px;margin-left:5%;color:#fff">ESP8266固件升级</div>
  </div>
     <center>
     <div style="width:70%;height:auto;background:;margin-top:6%;">
     <div id="adiv022" >
       
     </div>
             <div id="pagination" style="background:;width:auto;height:auto;margin-top:1%;float:right"></div>
             </div>
     </center>
     
     <!-- 弹出框 -->
	      <div id="popLayer"></div>
				<div id="popBox" style="background:#fff">
				    <div class="close">
				        <a href="javascript:void(0)" onclick="closeBox()">
				            <span id="title" style="color:#fff"></span><div style="background:url(${pageContext.request.contextPath}/images/icon/close.png)no-repeat;width:5%;height:70%;float:right;margin-top:%"></div>
				        </a> 
				    </div>
				    <div class="content02" id="content02"></div>
				</div>
  </body>
</html>
