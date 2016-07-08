<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center" style="padding: 5px;">
	<form method="post">
		<table class="tableForm">
			<tr>
				<th>导航名称</th>
				<td><input name="cname" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写导航名称'" />
				</td>
				<th>排序</th>
				<td><input name="cseq" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择菜单排序'" value="10" style="width:150px;" />
				</td>
			</tr>
			<tr>
				<th>导航地址</th>
				<td colspan="3"><textarea name="curl" style="height: 50px;width: 97%;"></textarea>
				</td>
			</tr>
			<tr>
				<th>上级导航</th>
				<td><input name="pid" style="width:200px;" />
				</td>
		
			</tr>
		</table>
	</form>
</div>