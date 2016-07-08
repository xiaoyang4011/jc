<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post">
		<table class="tableForm">
			<tr>
				<th  style="width:80px;text-align:center;">新闻标题</th>
				<td><input name="title" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写标题名称'" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;text-align:center;">新闻作者</th>
				<td><input name="author" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写作者名称'" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;text-align:center;">新闻类别</th>
				<td><!-- <input name="type"  style="width: 100%;" /> -->
				<select  style="width: 15%;" name="type" id="new_type">
										<option value="1">通知公告</option>
										<option value="2">新闻中心</option>
										<option value="3">活动中心</option>
										<option value="4">教学科研</option>
										<option value="5">学生工作</option>
										<option value="6">下载专区</option>
										<option value="7">学院风采</option>
										<option value="8">其他</option>
				</select>
				</td>
			</tr>
				<tr>
				<th style="width:50px;text-align:center;">是否置顶</th>
				<td><input type="radio" name="top" value="0" style="width: 3%;" /> 是
				<input type="radio" name="top" value="1" checked style="width: 3%;"/> 否
				</td>
			</tr>
			
		
			<tr>
				<th style="text-align:center;">新闻内容</th>
				<td><textarea name="content" style="height: 500px;width: 98%;"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>