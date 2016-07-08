<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" >
 
	var i = ${type};
	var t = ${top};
	var d = ${news_link};
	//alert(i);
	document.getElementById('type322').options[d].selected=true;  
	
	document.getElementById('type1').options[i-1].selected=true;  
	if(t==1){
		 document.getElementById('radioBox2').checked=true;
		}else{
			document.getElementById('radioBox').checked=true;
			}
	function a(){
		document.getElementById("radioBox").value="0";
	}
	function b(){
		document.getElementById("radioBox2").value="1";
	}

</script>


<div style="padding: 5px;">
	<form method="post">
		<table class="tableForm">
			<tr>
				<th style="width:80px;">新闻标题</th>
				<td>
				<input type="hidden" name="ids" />
				<input name="title" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写标题名称'" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">新闻作者</th>
				<td><input name="author" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写作者名称'" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">新闻类别</th>
				<td> <input  id="hh" name="type2"  type="hidden"/> 
				<select id="type1"  style="width: 15%;" name="type" >
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
				<th style="width:50px;">是否置顶</th>
				<td><input id="radioBox" type="radio" name="top" value="0" onclick="a()" style="width: 3%;" /> 是
				<input id="radioBox2" type="radio" name="top" value="1"  onclick="b()" style="width: 3%;"/> 否
				</td>
			</tr>
			<tr>
				<th style="width:80px;text-align:center;">图文链接</th>
				<td><!-- <input name="type"  style="width: 100%;" /> -->
				<select id="type322" style="width: 15%;" name="pic_news" >
										<option value="0">无图文链接</option>
										<option value="1">第一张</option>
										<option value="2">第二张</option>
										<option value="3">第三张</option>
										
				</select>
				</td>
			</tr>
			<tr>
				<th>新闻内容</th>
				<td><textarea name="content" style="height: 500px;width: 98%;">${content}</textarea>
				</td>
			</tr>
		</table>
	</form>

</div>