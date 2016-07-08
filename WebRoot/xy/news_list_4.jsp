<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>中国石油大学胜利学院基础科学学院</title>
<script type="text/javascript" src="js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="images/mbcsmbmcp.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/xy/js/desk.js"></script>
<script type="text/javascript">
$(function(){
	toPage(1);
	nav();

});
function toPage(index){
	$.ajax({
		url:"<%=basePath%>/news!doNotNeedSession_pnews4.do?page="+index,
		success:function(data){
			
			$("#mydiv").html(data);
		}
	});
}
</script>
<script type="text/javascript" language="javascript">

$(document).ready(function() {
    $("#pager").pager({ pagenumber: 1,pageSize:15, pagecount: 4, buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
	var currentHref=window.location.href;
	var index=currentHref.indexOf("start");
	if(index>1){
		currentHref=currentHref.substring(0,index-1)
	}
	window.location.href=currentHref+"&start="+pageclickednumber;
}
</script>
</head>
<body>
<div class="main">
  <div class="block_header">
    <div class="logo">
      <div class="logo_img"> <a href="index.jsp"> <img src="images/tubiao.png" border="0" alt="logo" /> </a></div>
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
    <div class="slice12">
      <h2>教学科研</h2>
    </div>
  </div>
  <div class="clr"></div>
  <div class="body">
    <div class="body_resize_2">
        <div class="list">
     <ul>
      <li><a href="news_list_1.jsp">新闻中心</a></li>
          <li><a href="news_list_2.jsp">通知公告</a></li>
          <li><a href="news_list_3.jsp">活动中心</a></li>
          <li><a href="news_list_4.jsp">教学科研</a></li>
          <li><a href="news_list_5.jsp">学生工作</a></li>
          <li><a href="news_list_6.jsp">下载专区</a></li>
          <li><a href="news_list_7.jsp">学院风采</a></li>
              
    </ul>
<p></p>
    </div>
      <div class="Welcome-1">
        <div id="mydiv" class="news_list">
  	
        </div>
 
      </div>
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