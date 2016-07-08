<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post">
		<table class="tableForm">
			<tr>
				<th style="width:80px;">新闻标题</th>
				<td><input name="title" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写标题名称'" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">新闻作者</th>
				<td><input name="author" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写作者名称'" style="width: 97%;" />
				</td>
			</tr>
		
		
			<tr>
				<th>新闻内容</th>
				<td><textarea name="content" style="height: 500px;width: 98%;"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>