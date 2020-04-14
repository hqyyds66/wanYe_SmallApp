<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>名豆之约app下载页</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
		<script type="text/javascript"
			src="https://cdn.bootcss.com/html5media/1.1.8/html5media.min.js"></script>
		<script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"52730",secure:"52737"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script>
	</head>
	<script type="text/javascript">

var is_weixin = (function(){return navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1})();
    
   $(function(){
                var butonAndroid=document.getElementById("android");
                var butonIos=document.getElementById("ios");
   }) 
  
   function android(){
   
      //window.location.href="https://fir.im/8c3n";     
    if(is_weixin){
        alert("请点击右上方在浏览器中打开后下载!");
}else{
  //window.location.href="http://shouji.360tpcdn.com/V1GjjsrgR-qTgLR51P1VFqep0Hm7YdzXJzMtpMQKkT-sFn04JgSwRn5VlvtxkzjvEhN87hKIbIYGuhPqfQCUOGfL8znDSUcHb9OlzzrBXOE1.apk?sign=d0a51c6b2868855b";
  window.location.href="${pageContext.request.contextPath}/video/名豆之约v2.0.0.apk";
}
        //后台方法、文件类型、文件名
        //downloadTemplate('${pageContext.request.contextPath}/WanyeApiUser_exportVehicleInfo', 'apk', 'mindor');
   }
   
    

   /**
     * 用于下载导入模板时的影藏form表单的提交，采用post方式提交
     * @param action 请求后台方法
     * @param type 文件类型
     * @param value 文件名
     */
    function downloadTemplate(action, type, value){
        var form = document.createElement('form');
        document.body.appendChild(form);
        form.style.display = "none";
        form.action = action;
        form.id = 'apk';
        form.method = 'post';
        
        var newElement = document.createElement("input");  
        newElement.setAttribute("type","hidden");  
        newElement.name = type;
        newElement.value = value;
        form.appendChild(newElement); 
        
        form.submit();
    }
   
   function ios(){
         if(is_weixin){
        alert("请点击右上方在浏览器中打开后下载!");
}else{
      window.location.href="https://itunes.apple.com/cn/app/id1457303419?mt=8"; 
}
   }
  
  </script>

	<body
		style="width: auto; height: auto; position: relative; margin: 0; padding: 0;"
		data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-0"
		data-genuitec-path="/wanYe/WebRoot/WEB-INF/appDownload/appDownload.jsp">
		<center data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-0"
			data-genuitec-path="/wanYe/WebRoot/WEB-INF/appDownload/appDownload.jsp">
			<div
				style="width:100%;height:4628px;background:url(${pageContext.request.contextPath}/images/app/app.png)no-repeat;background-size:100% 100%;">

				<div style="margin-left: 400px">
					<video width="320" height="200" controls
						poster="${pageContext.request.contextPath}/images/app/use.jpg">
					<source src="video/use.mp4" type="video/mp4">
					你的浏览器不支持 video 标签。
					</video>
				</div>
				<div>
					&nbsp
				</div>
				<a href="javascript:void(0)" onclick="android()"
					style="text-decoration: none;">
					<div id="android"
						style="width:666px;height:88px;background:url(${pageContext.request.contextPath}/images/app/android_n.png)no-repeat;margin-top:3900px;margin-left:12px"></div>
				</a>
				<br>
				<a href="javascript:void(0)" onclick="ios()"
					style="text-decoration: none;">
					<div id="ios"
						style="width:666px;height:88px;background:url(${pageContext.request.contextPath}/images/app/ios_n.png)no-repeat;margin-left:12px;margin-top:15px"></div>
				</a>

			</div>
			<div
				style="color: #fff; font-family: 微软雅黑; font-weight: lighter; font-size: 12px; position: absolute; bottom: 1%; left: 0; right: 0"
				id="foot">
				<sapn>
				客服电话：
				<a href="tel:020-37266903"
					style="text-decoration: none; color: #fff">020-37266903</a>
				</span>
				<br>
				<span>客服qq： <a
					href="tencent://message/?uin=3219368124&Site=&Menu-=yes"
					style="text-decoration: none; color: #fff">3219368124</a> </span>
				<br>
				<span>客服微信：mindor2017</span>
				<br>
				<span>公众号：名豆之约</sapn> <br> <br> <span style="color: ">Copyright©2016
						广州万烨生物科技有限公司 版权所有 18135624 &nbsp 公司电话：<a href="tel:37266903"
						style="text-decoration: none; color: #fff">37266903</a> </span>
			</div>
		</center>
	</body>
</html>
