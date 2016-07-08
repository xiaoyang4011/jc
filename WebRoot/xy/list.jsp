<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<ul>

	<c:forEach items="${list}" var="a">
	
      <li><a href="<%=basePath%>/news!doNotNeedSession_showAllContent.do?ids=${a.ids}">${a.title}</a><strong> ${a.ctime} </strong></li>  
	</c:forEach>
	</ul>
	
<div class="blankline1"></div>

<table    width="92%" height="10%" align="center" border="0" cellpadding="0" cellspacing="0" padding-left="10px">
	<tbody><tr id="pager"><td class="pages">
	<a href="javascript:toPage(1);" class="pgNext pgEmpty">[首页</a>
	<a href="javascript:toPage('${page==1?1:page-1}');" class="pgNext pgEmpty">上一页</a>
	<a href="javascript:toPage('${page==pagecount?(pagecount):page+1}');" class="pgNext">下一页</a>
	<a href="javascript:toPage(${pagecount});" class="pgNext">尾页]</a>
	</td>
	<td width="110" align="right" valign="middle" style="font-size:13px;">10  条资讯/页 </td>
	<td width="50" align="right" valign="middle"  style="font-size:13px;">共    ${count} 条  </td>
	
</tbody></table>
