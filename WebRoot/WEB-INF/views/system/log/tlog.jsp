<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<jsp:include page="../../../../inc.jsp"></jsp:include>

	<script type="text/javascript">
		$.canEdit = true;
	</script>

	<script type="text/javascript">
		$.canDelete = true;
	</script>

<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/tlogController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			} ] ],
			columns : [ [ 					{field:'userid',title:'用户ID',width : 100,align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.userid;
							}
						},
					{field:'username',title:'用户名称',width : 150,align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.username;
							}
						},
					{field:'ip',title:'用户IP',width : 150,align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.ip;
							}
						},	
					{field:'operation_type',width : 150,title:'操作类型',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.operation_type;
							}
						},
					{field:'tablename',width : 150,title:'操作表名',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.tablename;
							}
						},
					{field:'tableid',width : 150,title:'操作主键',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.tableid;
							}
						},
					{field:'comment',width : 150,title:'备注',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.comment;
							}
						},
					{field:'ctime',width : 150,title:'操作时间',align:'center',sortable:true,
							formatter:function(value,row,index){
								 var unixTimestamp = new Date(value);  
		                         return unixTimestamp.toLocaleString(); 
							}
						},
 {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll').datagrid('uncheckAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	});

	
	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/tlogController/delete', {
						id : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑用户',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/tlogController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 50px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
					<tr>
						<th>登录名</th>
						<td><input name="name" placeholder="可以模糊查询登录名" class="span2" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	
	
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		
			<div onclick="editFun();" data-options="iconCls:'pencil'">查看</div>
	</div>
</body>
</html>
