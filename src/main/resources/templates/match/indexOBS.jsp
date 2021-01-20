<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="page-view-size" content="1920*1080">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache" CONTENT="no-cache">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<script src="${ctx!}/js/jquery.min.js"></script>
<script src="${ctx!}/js/util.js"></script>
<title>OBS浏览器</title>
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	overflow-x: hidden;
	overflow-y: hidden;
	background-color: transparent;
	font-family: Microsoft YaHei;
}

iframe {
	width: 1920px;
	height: 1080px;
}
</style>
</head>

<body>
	<iframe id="bestPlayer" allowfullscreen
		src="" frameborder="0"></iframe>
	<iframe id="killsContrast" allowfullscreen
		src="" frameborder="0"></iframe>
	<iframe id="playerInfo" allowfullscreen
		src="" frameborder="0"></iframe>
	<iframe id="bestPlayerSpecial" allowfullscreen
		src="" frameborder="0"></iframe>
	<iframe id="memberInfo" allowfullscreen
		src="" frameborder="0"></iframe>
</body>

<script type="text/javascript">
	$(function() {
		//新增iframe时需要在init中加入新id
		initIframe();
		//设置切换OBS延时
		var timeout = {};
		var websocket = null;
		//判断当前浏览器是否支持WebSocket
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://" + socketIP + "/command");
		} else {
			console.log('当前浏览器 Not support websocket');
		}

		//打开事件
		websocket.onopen = function() {
			console.log("Socket 已打开>>>>>>>>>>>");
			//这是来自客户端的消息
			websocket.send("客户端:OBS浏览器初始化成功...");
		};

		//获得消息事件
		websocket.onmessage = function(msg) {
			console.log("服务端消息:" + msg.data);
			//发现消息进入    调后台获取
			if (isJsonString(msg.data)) {
				var datas = JSON.parse(msg.data);
				if (datas.code == 0) {
					var info = datas.data;
					if (info.clear == 'true') {
						//重置
						$("iframe").hide();
						$("iframe").attr("src","");
						//停止所有定时任务
						$.map(timeout,function(value,key){
							window.clearTimeout(timeout[key]); 
						});
						timeout = {};
					}else{
						var pushTime = info['pushTime'];
						delete info["pushTime"];
						//间隔时间初始化
						$.map(info,function(obs,key){  
						    console.log(key+":"+obs);  
						    timeout[obs.index] = setTimeout(function(){
								  if(obs.type == underway_type){
										//实时iframe
										console.log(">>>>>实时数据");
										$("iframe").hide();
										$("#" + obs.page).show();
									}else if(obs.type == after_type){
										//赛后iframe
										console.log(">>>>>赛后数据");
										$("iframe").attr("src","");
										$("iframe").hide();
										var page = obs.page;
										if(page.indexOf("memberInfo")!= -1 ){
											page = page.split("_")[0];
										}
										$("#" + page).attr("src",obs.url);
										$("#" + page).show();
									}
							},pushTime*1000*obs.index);  
						}); 
					}
					//1.多个任务画面画面前需要执行定时任务
				
				} else {
					console.log("接收OBS管理链接返回码异常" + datas);
				}
			}
		};
		//关闭事件
		websocket.onclose = function() {
			console.log("Socket已关闭>>>>>>>>>>>");
		};
		//发生了错误事件
		websocket.onerror = function() {
			console.log("Socket发生了错误>>>>>>>>>>>");
			websocket.close();
		}

	});

	function initIframe() {
		/*管理iframe*/
		var docElm = document.documentElement;

		if (docElm.requestFullscreen) {
			//W3C 
			//docElm.requestFullscreen();
		} else if (docElm.mozRequestFullScreen) {
			//FireFox 
			docElm.mozRequestFullScreen();
		} else if (docElm.webkitRequestFullScreen) {
			//Chrome等
			docElm.webkitRequestFullScreen();
		} else if (elem.msRequestFullscreen) {
			//IE11
			elem.msRequestFullscreen();
		}
		//输入你希望根据页面高度自动调整高度的iframe的名称的列表
		//用逗号把每个iframe的ID分隔. 例如: ["myframe1", "myframe2"]，可以只有一个窗体，则不用逗号。
		//定义iframe的ID
		var iframeids = [ "bestPlayer", "killsContrast","playerInfo","bestPlayerSpecial","memberInfo"];
		//如果用户的浏览器不支持iframe是否将iframe隐藏 yes 表示隐藏，no表示不隐藏
		var iframehide = "yes";
		function dyniframesize() {
			var dyniframe = new Array()
			for (i = 0; i < iframeids.length; i++) {
				if (document.getElementById) {
					//自动调整iframe高度
					dyniframe[dyniframe.length] = document
							.getElementById(iframeids[i]);
					if (dyniframe[i] && !window.opera) {
						dyniframe[i].style.display = "block";
						if (dyniframe[i].contentDocument
								&& dyniframe[i].contentDocument.body.offsetHeight) //如果用户的浏览器是NetScape
							dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight;
						else if (dyniframe[i].Document
								&& dyniframe[i].Document.body.scrollHeight) //如果用户的浏览器是IE
							dyniframe[i].height = dyniframe[i].Document.body.scrollHeight;
					}
				}
				//根据设定的参数来处理不支持iframe的浏览器的显示问题
				if ((document.all || document.getElementById)
						&& iframehide == "no") {
					var tempobj = document.all ? document.all[iframeids[i]]
							: document.getElementById(iframeids[i]);
					tempobj.style.display = "block";
				}
			}
		}
		if (window.addEventListener)
			window.addEventListener("load", dyniframesize, false);
		else if (window.attachEvent)
			window.attachEvent("onload", dyniframesize);
		else
			window.onload = dyniframesize;
	}
</script>
</html>