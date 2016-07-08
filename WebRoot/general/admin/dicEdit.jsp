<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center" style="padding: 5px;overflow: hidden;">
	<form id="editForm" method="post">
	<input type="hidden" name="cid"/>
		<table class="tableForm">
			<tr>
				<td>名称:</td>
				<td><input type="text" name="ckey"/></td>
			</tr>
			<tr>
				<td>值:</td>
				<td><input type="text" name="cvalue"/></td>
			</tr>
			<tr>
				<td>类型:</td>
				<td><input type="text" name="ctype"/></td>
			</tr>
			<tr>
				<td>状态:</td>
				<td><select name="cstatus">
						<option value="0">启用</option>
						<option value="1">停用</option>
					</select></td>
			</tr>
		</table>
	</form>
</div>