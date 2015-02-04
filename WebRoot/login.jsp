<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SyPro</title>
<!-- 引入jQuery -->
<script src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 修复EasyUI1.3.3中layout组件的BUG -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/reg.css" type="text/css">
<script type="text/javascript" charset="utf-8">

	$(function() {
		$('#login input').keyup(function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		});
		
	});
	function loginFun() {
			var form = $("#login");//选中的tab里面的form
			$.post('${pageContext.request.contextPath}/userController/login', form.serialize(), function(result) {
				if (result.success) {
					window.location.href= "${pageContext.request.contextPath}/initController/index"; 
				} else {
					$.messager.alert('错误', result.msg, 'error');
				}
				
			}, "JSON");

				
	}
	</script>
	</head>
	<body>
	<div class="reg_con">
    <div class="reg_m">
        <form id="login" action="${pageContext.request.contextPath}/userController/login" method="post" >
           <input name="name"  type="text" placeholder="请输入登录名" value="jt56" class="reg_input1"/>
           <br /><br />
           <input  name="pwd" type="password" placeholder="请输入密码" value="123456" class="reg_input2" />
           <br /><br />
           <input name="" type="checkbox" value="" class="login_c" /> 记住密码 <a href="#" class="forget_key">忘记密码？</a>
            <br /><br /><br />
          <br /><br /><br />
           <input name="" type="button" onclick="loginFun();" class="reg_input5" />
        </form>
    </div>
    <div class="reg_bottom"></div>
</div>

</body>
</html>