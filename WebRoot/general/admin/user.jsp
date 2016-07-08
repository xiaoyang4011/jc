<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	//网站类别测试
	var TypeData;

	$(function() {
		TypeData = [ {
			value : '类别1',
			text : '3'
		} ,
		{
			value : '类别2',
			text : '2'
		} ,
		{
			value : '类别3',
			text : '1'
		} ];
		datagrid = $('#datagrid').datagrid({
			url : 'user!datagrid.do',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : true,
			nowrap : true,
			border : false,
			idField : 'cid',
			sortName : 'cstatus',
			sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'cid',
				width : 10,
				sortable : true,
				checkbox : true
			}, {
				title : '用户名',
				field : 'cname',
				width : 100,
				sortable : true
			}, {
				title : '真实姓名',
				field : 'crealname',
				width : 100
			} ] ],
			columns : [ [ {
				title : '所属角色ID',
				field : 'roleIds',
				width : 10,
				hidden : true
			}, {
				title : '所属角色',
				field : 'roleNames',
				width : 100
			}, {
				title : '所属部门',
				field : 'deptName',
				width : 120
			}, {
				title : '邮箱地址',
				field : 'cemail',
				width : 80
			}, {
				title : '创建时间',
				field : 'ccreatedatetime',
				sortable : true,
				width : 120
			}, {
				title : '最后修改时间',
				field : 'cmodifydatetime',
				sortable : true,
				width : 120
			}, {
				title : '状态',
				field : 'cstatus',
				sortable : true,
				width : 40,formatter: function(value,row,index){
					if (value=="0"){
						return "启用";
					} else {
						return "停用";
					}
				}
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('clearSelections');
					datagrid.datagrid('unselectAll');
				}
			}, '-', {
				text : '批量修改用户角色',
				iconCls : 'icon-edit',
				handler : function() {
					editRole();
				}
			}, '-' ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});

	function editRole() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].cid);
			}
			var p = parent.dj.dialog({
				title : '批量编辑用户角色',
				href : '${pageContext.request.contextPath}/user!userRoleEdit.do',
				width : 250,
				height : 130,
				buttons : [ {
					text : '编辑',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/user!roleEdit.do',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									datagrid.datagrid('reload');
									p.dialog('close');
								}
								parent.dj.messagerShow({
									msg : json.msg,
									title : '提示'
								});
							}
						});
					}
				} ],
				onLoad : function() {
					var f = p.find('form');
					var idsInput = f.find('input[name=ids]');
					var roleIds = f.find('input[name=roleIds]');
					var roleIdsCombobox = roleIds.combobox({
						url : '${pageContext.request.contextPath}/role!doNotNeedSession_combobox.do',
						valueField : 'cid',
						textField : 'cname',
						multiple : true,
						editable : false,
						panelHeight : 'auto',
						onLoadSuccess : function() {
							f.form('load', {
								ids : ids.join(',')
							});
						}
					});
				}
			});
		} else {
			parent.dj.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	}

	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			var p = parent.dj.dialog({
				title : '编辑用户',
				href : '${pageContext.request.contextPath}/user!userEdit.do',
				width : 450,
				height : 200,
				buttons : [ {
					text : '编辑',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/user!edit.do',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									datagrid.datagrid('reload');
									p.dialog('close');
								}
								parent.dj.messagerShow({
									msg : json.msg,
									title : '提示'
								});
							}
						});
					}
				} ],
				onLoad : function() {
					var f = p.find('form');
					var roleIds = f.find('input[name=roleIds]');
					var roleIdsCombobox = roleIds.combobox({
						url : '${pageContext.request.contextPath}/role!doNotNeedSession_combobox.do',
						valueField : 'cid',
						textField : 'cname',
						multiple : true,
						editable : false,
						panelHeight : 'auto',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
						}
					});
					var deptId = f.find('input[name=deptId]');
					var deptIdComboboxTree = deptId.combotree({
						url : '${pageContext.request.contextPath}/dept!doNotNeedSession_treeRecursive.do',
						multiple : false,
						editable : false,
						panelHeight : 'auto'
					});
					f.form('load', {
						cid : rows[0].cid,
						cname : rows[0].cname,
						crealname : rows[0].crealname,
						cstatus : rows[0].cstatus,
						cemail : rows[0].cemail,
						deptId : rows[0].deptId,
						roleIds : dj.getList(rows[0].roleIds),
					});
				}
			});
		} else if (rows.length > 1) {
			parent.dj.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.dj.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	}

	function append() {
		var p = parent.dj.dialog({
			title : '添加用户',
			href : '${pageContext.request.contextPath}/user!userAdd.do',
			width : 440,
			height : 200,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/user!add.do',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');
							}
							parent.dj.messagerShow({
								msg : json.msg,
								title : '提示'
							});
						}
					});
				}
			} ],
			onLoad : function() {
				var f = p.find('form');
				var roleIds = f.find('input[name=roleIds]');
				var roleIdsCombobox = roleIds.combobox({
					url : '${pageContext.request.contextPath}/role!doNotNeedSession_combobox.do',
					valueField : 'cid',
					textField : 'cname',
					multiple : true,
					editable : false,
					panelHeight : 'auto'
				});
				var deptId = f.find('input[name=deptId]');
				var deptIdComboboxTree = deptId.combotree({
					url : '${pageContext.request.contextPath}/dept!doNotNeedSession_treeRecursive.do',
					multiple : false,
					editable : false,
					panelHeight : 'auto'
				});
			}
		});
	}

//测试新闻添加
/**

function append() {
	var p = parent.dj.dialog({
		title : '新闻添加',
		href : '${pageContext.request.contextPath}/user!userAdd.do',
		width : 850,
		height : 700,
		buttons : [ {
			text : '添加',
			handler : function() {
				var f = p.find('form');
				f.form({
					url : '${pageContext.request.contextPath}/message!add.do',
					success : function(d) {
						var json = $.parseJSON(d);
						if (json.success) {
							datagrid.datagrid('reload');
							p.dialog('close');
						}
						parent.dj.messagerShow({
							msg : json.msg,
							title : '提示'
						});
					}
				});
				f.submit();
			}
		} ],
		onLoad : function() {
			var f = p.find('form');
			var editor = f.find('textarea[name=content]').xheditor({
				tools : 'full',
				html5Upload : true,
				upMultiple : 4,
				upLinkUrl : '${pageContext.request.contextPath}/bug!upload.do',
				upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
				upImgUrl : '${pageContext.request.contextPath}/bug!upload.do',
				upImgExt : 'jpg,jpeg,gif,png'
			});
			var type = p.find('input[name=type]');
			var iconCombo = type.combobox({
				data : TypeData,
				formatter : function(v) {
					return dj.fs('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
				}
			});
		}
	});
}
 * 
 */
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.dj.messagerConfirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].cid);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/user!delete.do',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							parent.dj.messagerShow({
								title : '提示',
								msg : d.msg
							});
						}
					});
				}
			});
		} else {
			parent.dj.messagerAlert('提示', '请勾选要删除的记录！', 'error');
		}
	}
	function _search() {
		datagrid.datagrid('load', dj.serializeObject($('#searchForm')));
	}
	function cleanSearch() {
		datagrid.datagrid('load', {});
		$('#searchForm input').val('');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 55px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>用户名</th>
					<td><input name="cname" style="width:317px;" />
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>