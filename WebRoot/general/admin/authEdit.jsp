<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$.messager.progress({
		text : '数据加载中....',
		interval : 100
	});
</script>
<div style="padding: 5px;">
	<form method="post">
		<input name="cid" type="hidden" />
		<table class="tableForm">
			<tr>
				<th style="width: 70px;">权限名称</th>
				<td><input name="cname" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" style="width: 155px;" />
				</td>
				<th style="width: 50px;">排序</th>
				<td><input name="cseq" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择菜单排序'" value="10" style="width:120px;" />
				</td>
			</tr>
			<tr>
				<th>权限地址</th>
				<td colspan="3"><input name="curl" style="width:98%;" />
				</td>
			</tr>
			<tr>
				<th>权限描述</th>
				<td colspan="3"><input name="cdesc" style="width:98%;" />
				</td>
			</tr>
			<tr>
				<th>上级权限</th>
				<td colspan="3"><input name="pid" style="width: 335px;" />
				</td>
			</tr>
		</table>
	</form>
</div>