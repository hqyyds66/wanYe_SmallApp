<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>修改商城参数</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
    $(function(){
              init();
		 });
    
     function doUpdate(){
       var jsonStr=$("#str").val();
       
       if(isJsonString(jsonStr)){
          $.ajax({
                         type:"post",
                         url:"WanyeApiApp_updateStr",
                         dataType:"json",
                         data:"jsonStr="+"["+jsonStr+"]",
                         success:function(result){
                             console.log(result); 
                             alert("修改成功！");
                             init();
                         },
			             error:function(){
			                toastr.error( '系统异常！');
	                    }
                     });
         
       }else{
          alert("妈的，格式写错了");
          return false;
       }
     } 
     
     function init(){
        $.ajax({
                         type:"post",
                         url:"WanyeApiApp_selectVersion",
                         dataType:"json",
                         data:"system=ios",
                         success:function(result){
                             console.log(result); 
                             var str=result.data[0].wjMinstore;
                             str=str.substring(1,str.indexOf(']'));
                             $("#strInit").val(str);
                         },
			             error:function(){
			                toastr.error( '系统异常！');
	                    }
                     });
     }
  
  	function myFocus(obj,color){
		             //设置文本框获取焦点时候背景颜色变换
		             obj.style.color=color;
         			}
 
		         function myblur(obj,color){
		             //当鼠标离开时候改变文本框背景颜色
		             obj.style.color=color;
		           }
		           
		           function isJsonString(str) {
					  try {
					    JSON.parse(str)
					    return true
					  } catch (err) {
					    return false
					  }
					}
  
  </script>
	</head>

	<body
		style="position: relative; height: 100%; width: 100%; padding: 0; margin: 0">
		<div
			style="width: 50%; height: 60%; position: absolute; top: 0; bottom: 0; left: 0; right: 0; background: ; margin: auto">
			<textarea
				style="overflow: scroll; overflow-x: hidden; height: 80%; width: 45%; color: #999999; text-indent: 5px; resize: none; font-size: 15px; border-radius: 5px; border: 1px solid #adadad"
				id="strInit" onfocus="myFocus(this,'black')"
				onblur="myblur(this,'#999999')" readonly="readonly"></textarea>
			<textarea
				style="overflow: scroll; overflow-x: hidden; height: 80%; width: 45%; color: #999999; margin-left: 9%; text-indent: 5px; resize: none; font-size: 15px; border-radius: 5px; border: 1px solid #adadad"
				id="str" onfocus="myFocus(this,'black')"
				onblur="myblur(this,'#999999')"></textarea>
			<a href="javascript:void(0)" onclick="doUpdate()"
				style="text-decoration: none; color: #000">
				<div
					style="width: 120px; height: 35px; float: right; background: #4C5FB5; border-radius: 5px; position: relative;">
					<div
						style="position: absolute; top: 0; left: 0; bottom: 0; right: 0; margin: auto; height: 20px; width: 35px; color: #fff">
						保存
					</div>
				</div> </a>
		</div>

	</body>
</html>
