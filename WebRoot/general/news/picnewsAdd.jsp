<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form   method="post" enctype="multipart/form-data" >
		<table class="tableForm">
			<tr>
				<th style="width:80px;">图文标题</th>
				<td><input name="title" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写标题名称'" style="width: 97%;" />
				</td>
			</tr>
		
			<tr>
				<th style="width:80px;">位置</th>
				<td><!-- <input name="type"  style="width: 100%;" /> -->
				<select  style="width: 20%;" name="sort" id="new_type">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									
				</select>
				</td>
			</tr>
				<tr>
				<th style="width:80px;">类型</th>
				<td><!-- <input name="type"  style="width: 100%;" /> -->
				<select  style="width: 40%;" name="type" >
										<option value="1">首页展示</option>
										<option value="2">学院风采</option>
									
				</select>
				</td>
			</tr>
		
		
			<tr>
				<th>选择图片</th>
				<td><input name="picfile"  type="file"  style="width: 97%;" />
				</td>
			</tr>
		</table>
		
	</form>
</div>