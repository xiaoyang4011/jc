
var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

var server_context=basePath;


function nav(){
	   //定义我需要的数组   

	   
		$.ajax({type:"GET",url: ""+basePath+"/nav!doNotNeedSession_treegrid.do",cache:false,
		    success: function(msg){
			  var ul1 = "";      
		     var array = eval("("+msg+")");
		  
		    var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		     //alert(b['ids']+"值"+b['title'].substring(0, 4));
			 
		     // alert(a);
		     a= a+1;
		     var trs1 = "";
		     if(a==7){
		    	 trs1+="<li><div class='buttonbg'><a class='button_3' onmouseout='out("+b['cid']+");' onmousemove='cnav("+b['cid']+");' href='"+b['curl']+"'>"+b['cname']+"</a></div> <ul id='"+b['cid']+"'></ul></li>";
		     }else{
		    	 trs1+="<li><div class='buttonbg'><a class='button_3' onmouseout='out("+b['cid']+");' onmousemove='cnav("+b['cid']+");' href='"+basePath+"/"+b['curl']+"'>"+b['cname']+"</a></div> <ul id='"+b['cid']+"'></ul></li>";
		     }
		    
	         ul1 += trs1;   
	          
			 });
		    $("#mbmcpebul_table").append(ul1);
		    //添加标签渲染效果
		   
		   }
		});

	}
var b=0;//标志位
var ary = []; //数组
function cnav(val){
	   //定义我需要的数组   
		b=b+1;
		//给b添加标志
		//判断是否读取过数据
		if($.inArray(val, ary) == -1){
		if(b==1){
		$.ajax({type:"GET",url: ""+basePath+"/nav!doNotNeedSession_treegrid.do?id="+val+"",cache:false,
		    success: function(msg){
			  var ul1 = "";      
		     var array = eval("("+msg+")");
		  
		    	var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		   //  alert(b['cid']+"值"+b['cname'].substring(0, 4));
			 
		     // alert(a);
		     var trs1 = "";
		     trs1+="<li><a href='"+basePath+"/"+b['curl']+"'>"+b['cname']+"</a></li>";
	         ul1 += trs1;   
	          
			 });
		    $("#"+val+"").append(ul1);
			
		   }
		});
		}
		}
	}
/*
 * 鼠标移开执行动作
 * 标志清零
 * push数组记录
 * push前判断是否在数组里  在 == -1
 */
function out(id){
	b=0;
	if($.inArray(id, ary)==-1){
		ary.push(id);

		}
	//alert(ary);
}

//搜索JS
function searchCommit() {

	if (document.forms['search'].conName.value.replace(/\s/g, "") == "") {
		alert("请输入搜索信息标题！");
		document.forms['search'].conName.focus();
		return false;
	} else {
		document.forms['search'].submit();
	}
}
//通知公告
function tzgg(){
	   //定义我需要的数组   

	   
		$.ajax({type:"GET",url: ""+basePath+"/news!doNotNeedSession_cnews1.do",cache:false,
		    success: function(msg){
			  var ul1 = "";      
		     var array = eval("("+msg+")");
		  
		    var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		     //alert(b['ids']+"值"+b['title'].substring(0, 4));
			 
		     // alert(a);
		     var trs1 = "";
		   
		     trs1+="  <ul><a href='"+basePath+"/news!doNotNeedSession_showAllContent.do?ids="+b['ids']+"'>"+b['title'].substring(0, 20)+"</a><li>"+b['ctime']+"</li></ul><div class='bg_1'></div>";
	       // trs1+="<li><a href='"+basePath+"/browse2.do?new_id="+b['new_id']+"' title="+b['new_title']+" target='_blank'><span class='font_orange'>"+b['new_date'].substring(0, 9)+"</span><span style='color:'>&nbsp;&nbsp;&nbsp;&nbsp;"+b['new_title'].substring(0, 16)+"</span></a></li>";          
	        ul1 += trs1;   
	          
			 });
		    $("#gundong").append(ul1);
			
		   }
		});

	}
//新闻中心
function xwzx(){
	   //定义我需要的数组   

	   
		$.ajax({type:"GET",url: ""+basePath+"/news!doNotNeedSession_cnews2.do",cache:false,
		    success: function(msg){
			  var ul1 = "";      
		     var array = eval("("+msg+")");
		  
		    var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		     //alert(b['ids']+"值"+b['title'].substring(0, 4));
			 
		     // alert(a);
		     var trs1 = "";
		    
		     trs1+="<li><a  href='"+basePath+"/news!doNotNeedSession_showAllContent.do?ids="+b['ids']+"'>"+b['title'].substring(0, 16)+"</a><a class='time'>"+b['ctime']+"</a></li>";
	       // trs1+="<li><a href='"+basePath+"/browse2.do?new_id="+b['new_id']+"' title="+b['new_title']+" target='_blank'><span class='font_orange'>"+b['new_date'].substring(0, 9)+"</span><span style='color:'>&nbsp;&nbsp;&nbsp;&nbsp;"+b['new_title'].substring(0, 16)+"</span></a></li>";          
	        ul1 += trs1;   
	          
			 });
		    $("#xwzx").append(ul1);
			
		   }
		});

	}

//活动中心
function hdzx(){
	   //定义我需要的数组   

	   
		$.ajax({type:"GET",url: ""+basePath+"/news!doNotNeedSession_cnews3.do",cache:false,
		    success: function(msg){
			  var ul1 = "";      
		     var array = eval("("+msg+")");
		   //  ul1+="<MARQUEE id=a onmouseover=a.stop()  onmouseout=a.start() scrollAmount=3 direction=up height='260px'>";
		    var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		     //alert(b['ids']+"值"+b['title'].substring(0, 4));
			 
		     // alert(a);
		     var trs1 = "";
		     trs1+="<li><a  href='"+basePath+"/news!doNotNeedSession_showAllContent.do?ids="+b['ids']+"'>"+b['title'].substring(0, 16)+"</a><a class='time'>"+b['ctime']+"</a></li>";
		     //trs1+="<div><a1 target='_blank' href='"+basePath+"/news!doNotNeedSession_showAllContent.do?ids="+b['ids']+"'>"+b['title'].substring(0, 16)+"...</a1><a2>"+b['ctime']+"</a2></div>";          
		     ul1 += trs1;   
	          
			 });
		    // ul1+="</MARQUEE>";
		    $("#hdzx").append(ul1);
		 
		   }
		});
		
	
	}



function image1(){
	   //定义我需要的数组   

	   
		$.ajax({type:"GET",url: ""+basePath+"/picnews!doNotNeedSession_pic.do",cache:false,
		    success: function(msg){
			  var ul1 = "";    
			
		     var array = eval("("+msg+")");
		  
		   // var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		     //alert(b['img_src']);
			 //  a=a+1;
		     // alert(a);
		      var trs1 = "";

	         //trs += "<tr id="+b['news_id'] +"><td>" + b['news_id'] +"</td> <td>" + b['img_src'] +"</td></tr>";
	       
		      trs1+="<div class='box'><a><img  src='"+basePath+"/images/"+b['picfileFileName']+"' /></a></div>";          

	          ul1 += trs1;   
	         
			 });
		    
			
		     $("#FS_Cont_01").append(ul1);
	
		 
		     image2();
		
			
		   }
		});

	}



function image3(){
	   //定义我需要的数组   

	   
		$.ajax({type:"GET",url: ""+basePath+"/picnews!doNotNeedSession_pic2.do",cache:false,
		    success: function(msg){
			  var ul1 = "";    
			
		     var array = eval("("+msg+")");
		  
		   // var a=0;
		     
		     $.each(array,function(a,b){
			     //字符串字数截取格式.substring(0, 4))
		     //alert(b['img_src']);
			 //  a=a+1;
		     // alert(a);
		      var trs1 = "";

	         //trs += "<tr id="+b['news_id'] +"><td>" + b['news_id'] +"</td> <td>" + b['img_src'] +"</td></tr>";
		    
		      trs1+="<img height='144' width='192' src='"+basePath+"/images/"+b['picfileFileName']+"' /><li><a href='"+basePath+"/news!doNotNeedSession_showAllContent.do?ids="+b['news']+"' target='_blank'>"+b['title']+"</a></li>";          

	          ul1 += trs1;   
	         
			 });
		    
			
		     $("#xyfc").append(ul1);
		  
		
			
		   }
		});

	}
function image2(){
	  <!--//--><![CDATA[//><!--
	  var focusScroll_01 = new ScrollPic();
	  focusScroll_01.scrollContId   = "FS_Cont_01"; //内容容器ID
	  focusScroll_01.arrLeftId      = "FS_arr_left_01";//左箭头ID
	  focusScroll_01.arrRightId     = "FS_arr_right_01"; //右箭头ID

	  focusScroll_01.dotListId      = "FS_numList_01";//点列表ID
	  focusScroll_01.dotClassName   = "";//点className
	  focusScroll_01.dotOnClassName	= "selected";//当前点className
	  focusScroll_01.listType		= "number";//列表类型(number:数字，其它为空)
	  focusScroll_01.listEvent      = "onmouseover"; //切换事件

	  focusScroll_01.frameWidth     = 1100;//显示框宽度
	  focusScroll_01.pageWidth      = 1100; //翻页宽度
	  focusScroll_01.upright        = false; //垂直滚动
	  focusScroll_01.speed          = 10; //移动速度(单位毫秒，越小越快)
	  focusScroll_01.space          = 50; //每次移动像素(单位px，越大越快)
	  focusScroll_01.autoPlay       = true; //自动播放
	  focusScroll_01.autoPlayTime   = 5; //自动播放间隔时间(秒)
	  focusScroll_01.circularly     = true;
	  focusScroll_01.initialize(); //初始化
	  focusScroll_01.onpagechange = function(){
	   $(".scroll_info").hide();
	   $("#txt0"+(focusScroll_01.pageIndex+1)).show();
	  };

}




