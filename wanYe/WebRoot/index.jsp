<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8" deferredSyntaxAllowedAsLiteral="true"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>用户管理</title>
</head>
		<body class="rightBody">
				<form id="form" name="form" action="WanyeApiUpdateHeads_updateHead" method="post" enctype="multipart/form-data">
				    <div class="p_d_1">
				        <div class="p_d_1_1">
				            <div class="content_info">
				    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
				    <div class="tableH2">新增用户</div>
				    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
				        <tr>
				            <td class="tdBg" width="200px">用户id：</td>
				            <td><input type="text" name="userId"></input></td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">头像：</td>
				            <td>
				                <input type="file" name="headImg"/>
				            </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">用户名：</td>
				            <td><s:textfield name="user.name"/> </td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">帐号：</td>
				            <td><s:textfield name="111111" id="userId" value="minApp100001"/></td>
				        </tr>
				        <tr>
				            <td class="tdBg" width="200px">密码：</td>
				            <td><s:textfield name="user.password"/></td>
				        </tr>
				    </table>
				    <div class="tc mt20">
				        <input type="submit" class="btnB2" value="保存" />
				        &nbsp;&nbsp;&nbsp;&nbsp;
				        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
				    </div>
				    </div></div></div>
				</form>
		</body>
</html>
