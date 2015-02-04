<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/tlogController/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
							<tr>
					<td><label>ID</label></td>					
					<td><input name="id" type="text" class="span2" readonly="readonly" value="${id}"></td>
				</tr>
							<tr>
					<td><label>用户ID</label></td>					
					<td><input name="userid" type="text" placeholder="请输入用户ID" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
							<tr>
					<td><label>用户名称</label></td>					
					<td><input name="username" type="text" placeholder="请输入用户名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
							<tr>
					<td><label>操作类型</label></td>					
					<td><input name="operation_type" type="text" placeholder="请输入操作类型" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
							<tr>
					<td><label>操作表名</label></td>					
					<td><input name="tablename" type="text" placeholder="请输入操作表名" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
							<tr>
					<td><label>操作主键</label></td>					
					<td><input name="tableid" type="text" placeholder="请输入操作主键" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
							<tr>
					<td><label>备注</label></td>					
					<td><input name="comment" type="text" placeholder="请输入备注" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
							<tr>
					<td><label>操作时间</label></td>					
					<td><input name="ctime" type="text" placeholder="请输入操作时间" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
						</table>
		</form>
	</div>
</div>


