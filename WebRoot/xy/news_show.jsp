<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>中国石油大学胜利学院基础科学学院</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<%=basePath%>/xy/css/style1.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>/xy/images/mbcsmbmcp.css" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/xy/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/xy/js/desk.js"></script>
<script type="text/javascript">
$(function(){

	nav();

});

</script>
</head>
<body>
<div class="main">
  <div class="block_header">
    <div class="logo">
      <div class="logo_img"> <a href="index.jsp"> <img src="<%=basePath%>/xy/images/tubiao.png"  border="0" alt="logo" /> </a></div>
    </div>
  <div class="logo_txt">
    <div class="Twitter">
      <form action="http://www.baidu.com/baidu" target="_blank">
        <div style="width:80%; float:left;">
          <input name=tn type=hidden value=baidu>
          <input type=text name=word size=30 style="width:155px; height:20px;">
        </div>
        <div style="width:20%; float:right;">
          <input type="submit" value="搜索" class="ss">
        </div>
      </form>
    </div>
    <div id="mbmcpebul_wrapper">
      <ul id="mbmcpebul_table" class="mbmcpebul_menulist css_menu">
       
      </ul>
    </div>
  </div>
    <div class="clr"></div>
  </div>
  <div class="slider2">
    <div class="slice12_news">
     <img src="<%=basePath%>/xy/images/ht.jpg" />
    </div>
  </div>
  <div class="clr"></div>
  <div class="body">
    <div class="body_resize_2">
    	<c:forEach items="${show}" var="a">
        <div class="news_show">
       
       <div class="bt"> 
        <h2>${a.title}</h2>
      <div class="1">发布人：${a.author} &nbsp;&nbsp;&nbsp;&nbsp; 发布时间：${a.ctime}</div>
      <div class="bg"></div>
      </div>
       ${a.content}
          </div>
          	</c:forEach>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>
  </div>
  <div class="footer">
    <div class="resize">
      <div>
        <p class="footer_logo">版权所有：中国石油大学胜利学院基础科学学院</p>
        <p class="footer_logo">设计制作：中国石油大学胜利学院基础科学学院软件协会</p>
        <p><a href="<%=basePath%>/user!doNotNeedSession_login.do">管理入口</a></p>
      </div>
    </div>
    <p class="clr"></p>
  </div>
</div>
</body>
</html>