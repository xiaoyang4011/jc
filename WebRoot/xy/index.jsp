<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>中国石油大学胜利学院基础科学学院</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="images/mbcsmbmcp.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/easySlider1.5.js"></script>
<script type="text/javascript" src="<%=basePath%>/xy/js/desk.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>

<script type="text/javascript" charset="utf-8">
$(function(){
	image1();
	image3();
	tzgg();
	xwzx();
	hdzx();
	nav();
})
</script>

<style type="text/css">
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
	font-size: 15px;
}
</style>
</head>
<body>
<div class="block_header">
    <div class="logo">
      <div class="logo_img"> <a href="index.jsp"> <img src="images/tubiao.png"  border="0" alt="logo" /> </a></div>

    </div>
<div class="logo_txt">
    <div class="Twitter">
      <form action="http://www.baidu.com/baidu" target="_blank">
        <div style="width:80%; float:left;"><input name=tn type=hidden value=baidu>
              <input type=text name=word size=30 style="width:155px; height:20px;"></div>
              <div style="width:20%; float:right;"><input type="submit" value="搜索" class="ss">
          </div>
      </form>
    </div>
 <div id="mbmcpebul_wrapper">
  <ul id="mbmcpebul_table" class="mbmcpebul_menulist css_menu">

  </ul>
</div>
</div>
</div>
  
  <div class="focus">
   <div class="focus_body">
    <!--焦点图 begin -->
	<div class="scroll">

      <a href="javascript:void(0);" class="arr_left" id="FS_arr_left_01">左移动</a>
      <a href="javascript:void(0);" class="arr_right" id="FS_arr_right_01">右移动</a>
      <div class="scroll_cont" id="FS_Cont_01">
	
      </div>
      <div id="FS_numList_01" class="numList"></div>

     </div>



    <!--焦点图 end -->
    </div>
  </div>
  <div class="clr"></div>
  <div class="main">
  <div class="body">
    <div class="topi">
      <div class="blogi">
        <h2 class="what">学院简介</h2>
		<div class="bg"></div>
        <p><strong>简介内容</p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中国石油大学胜利学院基础科学学院成立于2014年8月，是为了更好的适应学院发展的需要，由原中国石油大学胜利学院信息与计算科学系、外语系、体育系三个系以及大学物理教研室、马列教研室等基础学科组建而成。学院下设综合办公室、学生工作办公室、数学物理系、信息技术系、外语系、思想政治理论教学部和体育教学部等7个部门。</p>
        <div  class="xq">
          <p><a href="<%=basePath%>news!doNotNeedSession_showAllContent.do?ids=7e84df42-0011-43a7-88cd-4ce514062677">详情</a></p>
        </div>
      </div>
      <div class="blogi_1">
         <h2>通知公告</h2>
        <div class="bg"></div>
        <marquee id="gundong" behavior="scroll" direction="up" height="62%" width="100%" onmousemove="this.stop()" onmouseout="this.start()" scrollamount="5">


        </marquee>
        <p align="right"><a href="news_list_1.jsp">+ more</a></p>
        
      </div>
      <div class="clr"></div>
    </div>
    <div class="body_resize">
      <div class="News">
        <h2>新闻中心</h2>
   		<div class="bg"></div>
        <ul id="xwzx">
      

        </ul>
        <p align="right"><a href="news_list_2.jsp">+ more</a></p>
        <div class="bg"></div>
      </div>
      <div class="Welcome">
        <h2>活动中心</h2>
        <div class="bg"></div>
        <ul id="hdzx">

        </ul>
        <p align="right"><a href="news_list_3.jsp">+ more</a></p>
        <div class="bg"></div>
      </div>
      <div class="act">
        <h2>学院风采</h2>
        <div class="bg"></div>
        <ul id="xyfc">
        
        </ul>
        <p align="right"><a href="news_list_7.jsp">+ more</a></p>
        
      </div>

      <div class="f_link">
        <h2>友情链接</h2>
        <div class="bg"></div>
        <div class="link_1">
          <li><a href="http://www.moe.gov.cn/">教育部</a></li>
          <li><a href="http://www.sdedu.gov.cn/jyt/index.htm">山东省教育厅</a></li>
          <li><a href="http://www.dyjy.gov.cn/index.aspx">东营市教育信息网</a></li>
        </div>
        <div class="link_2">
          <li><a href="http://www.dongying.gov.cn/">东营市人民政府</a></li>
          <li><a href="http://www.upc.edu.cn/">中国石油大学（华东）</a></li>
          <li><a href="http://web.cup.edu.cn/">中国石油大学（北京）</a></li>
        </div>
        <div class="link_3">
          <li><a href="http://slof.sinopec.com/slof/">胜利油田</a></li>
          <li><a href="http://www.univs.cn/">中国大学生在线</a></li>
          <li><a href="http://www.cyol.com/">中国青年报</a></li>
        </div>
      </div>
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