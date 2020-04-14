<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'Upgrade.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/window.js"></script>
	</head>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.pagination/jquery.pagination.js"></script>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/js/jquery.pagination/pagination.css"
		type="text/css"></link>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<script type="text/javascript">
  var theRequestId='<%=request.getParameter("id")%>';
           $(function(){
		           if(theRequestId!=0){
		             intProductInfo();//初始化产品信息
		           }
           });
  
  
  function intProductInfo(){
                   var data02;
                     $.ajax({
               		     type:"post",
                         url:"Product_productListById",
                         dataType:"json",
                         data:"id="+theRequestId+"",
                         success:function(result){
                             console.log(result);
                             //var a=eval("("+result.productList+")");
                             //JSON.stringify(jsonobj)；
                             $("#productIdStr").val(result.productList[0].productId);
                             $("#productId").html(result.productList[0].productId);
                              $("#softVersion").val(result.productList[0].softVersion);
                               $("#version_updateContent").val(result.productList[0].version_updateContent);
                         },
			             error:function(){
			                alert("系统异常！");
	                    }
            
            });
           }
           
           function submit0(){
                var softVersion=$("#softVersion").val();
                var uploadfile2=$("#uploadfile2").val();
                var file=$("#uploadfile").val();
                var form = document.getElementById('formbu');
                
                 // var fileName = getFileName(file);
                  //alert(file);
                var fileName1=file.substring(file.lastIndexOf("\\")+1);
                var fileName2=uploadfile2.substring(uploadfile2.lastIndexOf("\\")+1);
                
                if(softVersion.length<=0){
                  alert("版本号不能为空！");
                  return false;
                }
                
                if(file.length<=0){
                  alert("请选择文件一！");
                  return false;
                }else if(fileName1!="user1.1024.new.2.bin"){
                   alert("请选择正确的文件一");
                    return false;
                }
                
                if(uploadfile2.length<=0){
                  alert("请选择文件二！");
                  return false;
                }else if(fileName2!="user2.1024.new.2.bin"){
                   alert("请选择正确的文件二");
                    return false;
                }
                form.submit();
           }
           
            
  
  
  </script>

	<body>
		<center>
			<form action="${pageContext.request.contextPath}/Product_upload"
				enctype="multipart/form-data" method="post" id="formbu">
				<table border="0">
					<tr>
						<td>
							产品id:
						</td>
						<td>
							<span id="productId" style="font-size: 12px"></span>
							<input type="hidden" id="productIdStr" name="productIdStr">
						</td>
					</tr>
					<tr>
						<td>
							版本号:
						</td>
						<td>
							<input type="text" style="width: 200px" id="softVersion"
								name="softVersion">
						</td>
					</tr>
					<tr>
						<td valign="top" style="padding-top: 0%">
							更新内容:
						</td>
						<td>
							<textarea
								style="height: 70px; width: 200px; color: resize : none; font-size: 12px"
								id="version_updateContent" name="version_updateContent"></textarea>
						</td>
					</tr>

					<tr>
						<td>
							选择文件1：
						</td>
						<td>
							<input type="File" name="uploadfile" id="uploadfile">
						</td>
					</tr>

					<tr>
						<td>
							选择文件2：
						</td>
						<td>
							<input type="File" name="uploadfile2" id="uploadfile2">
						</td>
					</tr>

					<tr>
						<td>
						</td>
						<td>
							<input type="button" onclick="submit0()" value="上传更新"
								id="submitBu">
						</td>
					</tr>
				</table>
		</center>
		</form>
	</body>
</html>
