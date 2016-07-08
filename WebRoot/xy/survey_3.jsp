<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>中国石油大学胜利学院基础科学学院</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="images/mbcsmbmcp.css" type="text/css" />
</head>
<body>
<div class="main">
  <div class="block_header">
    <div class="logo">
      <div class="logo_img"> <a href="index.jsp"> <img src="images/tubiao.png" width="290px" height="80px" border="0" alt="logo" /> </a></div>
      <div class="logo_zt">基础科学学院</div>
    </div>
    <div class="logo_2"><a href="index.jsp"></a></div>
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
    <div class="clr"></div>
    <div id="mbmcpebul_wrapper">
  <ul id="mbmcpebul_table" class="mbmcpebul_menulist css_menu">
  <li><div class="buttonbg"><a href="index.jsp">首页</a></div></li>
  <li><div class="buttonbg"><a href="survey_1.jsp">学院概况</a></div></li>
  <li><div class="arrow buttonbg"><a class="button_3">机构设置</a></div>
    <ul>
	<li><a href="news_list_4.jsp">综合办公室</a></li>
    <li><a href="news_list_5.jsp">学生工作办公室</a></li>
    <li><a href="news_list_6.jsp">信息技术系</a></li>
    <li><a href="news_list_7.jsp">数学物理系</a></li>
    <li><a href="news_list_8.jsp">外语系</a></li>
    <li><a href="news_list_9.jsp">思想政治理论教学部</a></li>
    <li><a href="news_list_10.jsp">体育教学部</a></li>
    </ul></li>
 <li><div class="buttonbg"><a href="news_list_11.jsp">教学科研</a></div></li>
  <li><div class="buttonbg"><a href="news_list_12.jsp">学生工作</a></div></li>
  <li><div class="buttonbg"><a href="news_list_13.jsp">下载专区</a></div></li>
  <li><div class="buttonbg" href=""><a href="http://www.pusc.cn/" target="_blank">学院首页</a></div></li>
  </ul>
</div>
    <div class="clr"></div>
  </div>
  <div class="slider2">
    <div class="slice12">
      <h2>学院概况</h2>
    </div>
  </div>
  <div class="clr"></div>
  <div class="body">
    <div class="body_resize_2">
      <div class="list">
        <ul>
          <li><a href="survey_1.jsp">学院简介</a></li>
          <li><a href="survey_2.jsp">学院领导</a></li>
          <li><a href="survey_3.jsp">组织机构</a></li>
        </ul>
        <p></p>
      </div>
      <div class="Welcome-1">
        <h2>组织机构</h2>
        <div id="content">
          <table cellspacing="0" id="table">
          <tr>
              <th width="50%">名称</td>
              <th width="50%">联系方式</td>
            </tr>
            <tr>
              <td width="50%">综合办公室</td>
              <td width="50%">&nbsp;</td>
            </tr>
            <tr>
              <td width="50%">学生工作办公室</td>
              <td width="50%">&nbsp;</td>
            </tr>
            <tr>
              <td width="50%">信息技术系</td>
              <td width="50%">&nbsp;</td>
            </tr>
            <tr>
              <td width="50%">数学物理系</td>
              <td width="50%">&nbsp;</td>
            </tr>
              <tr>
              <td width="50%">外语系</td>
              <td width="50%">&nbsp;</td>
            </tr>
              <tr>
              <td width="50%">思想政治理论教学部</td>
              <td width="50%">&nbsp;</td>
            </tr>
              <tr>
              <td width="50%">体育教学部</td>
              <td width="50%">&nbsp;</td>
            </tr>
          </table>
        </div>
        <p>&nbsp;</p>
      </div>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>
  </div>
  <div class="footer">
    <div class="resize">
      <div>
        <p class="footer_logo">版权所有：中国石油大学胜利学院基础科学学院</p>
        <p class="footer_logo">设计制作：中国石油大学胜利学院软件小组</p>
        <p><a href="">管理入口</a></p>
      </div>
    </div>
    <p class="clr"></p>
  </div>
</div>
</body>
</html>