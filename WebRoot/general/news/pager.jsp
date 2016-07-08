<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table align="center">
	<tr>
		<td>
			id
		</td>
		<td>
			name
		</td>
		<td>
			address
		</td>
	</tr>
	<c:forEach items="${list}" var="a">
		<tr>
			<td>
				${a.ids }
			</td>
			<td>
				${a.title }
			</td>
			<td>
				${a.createdatetime }
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="3">
			<a href="javascript:toPage(1);">首页</a>
			<a
				href="javascript:toPage('${page==1?1:page-1}');">上一页</a>
			<a
				href="javascript:toPage('${page==pagecount?(pagecount):page+1}');">下一页</a>
			<a href="javascript:toPage(${pagecount});">尾页</a>
		</td>
	</tr>
</table>
