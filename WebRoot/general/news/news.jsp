<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">

	var datagrid;
	//网站类别测试
	$(function() {
	
		datagrid = $('#datagrid').datagrid({
			url : 'news!datagrid.do',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : true,
			nowrap : true,
			border : false,
			idField : 'ids',
			//sortName : 'cstatus',
			//sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'ids',
				width : 10,
				sortable : true,
				checkbox : true
			} ] ],
			columns : [ [ 
			 			{
				title : '标题',
				field : 'title',
				width : 100,
				//sortable : true
			},{
				title : '作者',
				field : 'author',
				width : 100,
			}, {
				title : '发布时间',
				field : 'createdatetime',
				width : 100
			},{
				title : '新闻类型',
				field : 'type',
				width : 100
			}, {
				title : '是否置顶',
				field : 'top',
				width : 50
			}, {
				title : '查看内容',
				field : 'content',
				width : 50,
				formatter : function(value, rowData, rowIndex) {
					return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showContent(' + rowIndex + ');">查看</a>';
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
				text : '批量删除新闻',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
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

//测试新闻添加
function append() {
	var p = parent.dj.dialog({
		title : '新闻添加',
		href : '${pageContext.request.contextPath}/news!newsAdd.do',
		width : 750,
		height : 600,
		
		
		buttons : [ {
			text : '添加',
			handler : function() {
				var f = p.find('form');
				f.form({
					url : '${pageContext.request.contextPath}/news!add.do',
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
			
		}
	});
}
function edit() {
	var rows = datagrid.datagrid('getSelections');
	if (rows.length == 1) {
		var p = parent.dj.dialog({
			title : '修改新闻',
			href : '${pageContext.request.contextPath}/news!newsEdit.do?ids=' + rows[0].ids,
			width : 850,
			height : 700,
			buttons : [ {
				text : '修改',
				handler : function() {
					var f = p.find('form');
					f.form({
						url : '${pageContext.request.contextPath}/news!edit.do',
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
			
				f.find('input[name=ids]').val(rows[0].ids);
				f.find('input[name=title]').val(rows[0].title);
				f.find('input[name=author]').val(rows[0].author);
				var editor = f.find('textarea[name=content]').xheditor({
					tools : 'full',
					html5Upload : true,
					upMultiple : 4,
					upLinkUrl : '${pageContext.request.contextPath}/bug!upload.do',
					upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
					upImgUrl : '${pageContext.request.contextPath}/bug!upload.do',
					upImgExt : 'jpg,jpeg,gif,png'
				});
				
			
				
			}
		
		});
	} else if (rows.length > 1) {
		parent.dj.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
	} else {
		parent.dj.messagerAlert('提示', '请选择要编辑的记录！', 'error');
	}
}
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.dj.messagerConfirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].ids);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/news!delete.do',
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
	function showContent(rowIndex) {
		var rows = datagrid.datagrid('getRows');
		var row = rows[rowIndex];

		var p = parent.dj.dialog({
			title : '[' + row.title + ']',
			modal : true,
			maximizable : true,
			width : 800,
			height : 600,
			content : '<iframe src="${pageContext.request.contextPath}/news!showContent.do?ids=' + row.ids + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>'
		});

		datagrid.datagrid('unselectAll');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 55px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>新闻名称</th>
					<td><input name="title" style="width:200px;" />
					新闻类别
						<select  style="width:100px;" name="type">
										<option value="">全部</option>
										<option value="1">通知公告</option>
										<option value="2">新闻中心</option>
										<option value="3">活动中心</option>
										<option value="4">教学科研</option>
										<option value="5">学生工作</option>
										<option value="6">下载专区</option>
										<option value="7">学院风采</option>
										<option value="8">其他</option>
			        	</select>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a>
					</td>
					
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