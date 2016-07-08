<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="general/news/js/jquery-1.3.1.js"></script>
	
	<script type="text/javascript">
		function toPage(index){
			$.ajax({
				url:"${pageContext.request.contextPath}/news!pnews.do?page="+index,
				success:function(data){
					
					$("#mydiv").html(data);
				}
			});
		}
	</script>
  </head>
  
  <body>
    <a href="javascript:toPage(1);">用户名单</a>
    <div id="mydiv"></div>
  </body>
</html>
