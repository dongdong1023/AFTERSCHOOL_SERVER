<#include "/admin/layout/layout.jsp">
<#assign css>
<style>
	.left-manage{
		border: 1px solid #7777771f;
		font-size: 20px;
		height: 793px;
	}
	
	.right-show{
		border: 1px solid #7777771f;
		font-size: 18px;
		height: 793px;
	}
	.frames{
		height:648px;
		width:1152px;
		background: url('/assets/img/bg-game.jpg') no-repeat;
		background-size:100% 100%;
		-moz-background-size:100% 100%;
		position:relative;
		overflow:hidden;
	}
	#showIframe{
		transform:scale(0.6);
		position:absolute;
		top:-216px;
		left:-384px;
		overflow:hidden;
	}
	.top-manage-btn{
	margin-top: 5px;
    margin-bottom: 28px;
    }
    
    #badge{
    margin-left: 5px;
    vertical-align: middle;
    background: #2196f3;
    padding: 4px 7px 4px;
    }
    
    .times{
        margin-top: 35px;
    }
    
    .times label{
    	font-size: 15px;
    }
    
     .times #pushTime{
    	margin-left: -18px;
    	margin-top: -5px;
    }
</style>
</#assign>
<@layout title="推送管理" active="command">
<section class="content-header">
    <h1>
        推送管理
        <small>管理推送信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-random"></i>推送管理</a></li>
        <li><a href="#"><i class="fa fa-video-camera"></i>比赛推流</a></li>
        <li class="active"><i class="fa fa-table"></i>推流列表</li>
    </ol>
</section>

<section class="content">

	<div class="left-manage col-sm-3" id="left-manage">
	<div class="col-sm-12 text-muted text-center" style="line-height: 60px;">本场数据展示</div>
	<div class="col-sm-12 text-center">
		<div class="col-sm-12 top-manage-btn" style="line-height: 30px;">
			<div class="col-sm-1"></div>
			<div class="btn btn-sm btn-primary col-sm-4" onclick="checkAll()">全选</div>
			<div class="col-sm-1"></div>
			<div class="btn btn-sm btn-danger col-sm-4" onclick="clearAll('clear','','')">重置</div>
			<div class="col-sm-1"></div>
		</div>
		<div  class="btn-group-vertical" id="checkPush" role="group">
		 <button type="button" class="btn btn-default" id="bestPlayer" onclick="checkBtn('bestPlayer','after','/match/after/bestPlayer',this)">最佳选手<span class="badge ml5" id="badge"></span></button>
		 <button type="button" class="btn btn-default" id="killsContrast" onclick="checkBtn('killsContrast','after','/match/after/killsContrast',this)">全队击杀对比<span class="badge ml5" id="badge"></span></button>
		 <button type="button" class="btn btn-default" id="teamData" onclick="checkBtn('playerInfo','after','/match/after/playerInfo',this)">队伍数据(击杀Top5)<span class="badge ml5" id="badge"></span></button>
		 <button type="button" class="btn btn-default" id="bestPlayerSpecial" onclick="checkBtn('bestPlayerSpecial','after','/match/after/bestPlayerSpecial',this)">狙击手数据对比<span class="badge ml5" id="badge"></span></button>
		 <#list membersA as membersA>
		   <button type="button" class="btn btn-default" style="color: blue;" id="memberInfo_${membersA.steamId}" onclick="checkBtn('memberInfo_${membersA.steamId}','after','/match/underway/memberInfo/${membersA.steamId}',this)">(A队)${membersA.gameName}<span class="badge ml5" id="badge"></span></button>
		 </#list>
		 <#list membersB as membersB>
		   <button type="button" class="btn btn-default" style="color: #ff5722;" id="memberInfo_${membersB.steamId}" onclick="checkBtn('memberInfo_${membersB.steamId}','after','/match/underway/memberInfo/${membersB.steamId}',this)">(B队)${membersB.gameName}<span class="badge ml5" id="badge"></span></button>
		 </#list>
		</div>
			<div class="col-sm-12 times">
				<label for="pushTime" class="col-sm-4  text-left">间隔时间:</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="pushTime"
						placeholder="请输入推送间隔时间(秒)...">
				</div>
			</div>
			<div class="col-sm-10 btn btn-sm btn-success" style="margin-left: 20px;margin-top: 35px;" onclick="push()">推送</div>
	</div>
	</div>
	<div class="right-show col-sm-9" id="right-show">
		<div class="col-sm-10 text-muted text-left" style="line-height: 50px">推流地址:<a href="${ctx!}/match/indexOBS" target="view_window" style="text-decoration: underline;">http://127.0.0.1/match/indexOBS</a></div>
		<div class="col-sm-2 btn btn-sm btn-danger" style="top: 10px;" onclick="resetData()">重新计算比赛结果</div>
		<div class="frames col-sm-12">
			<iframe id="showIframe" allowfullscreen src="" frameborder="0" width="1920px" height="1080px"  ></iframe>
		</div>
	</div>
</section>

<#assign js>
<script src="${ctx!}/assets/js/iframe-resizer.js"></script>
<script type="text/javascript">
	var websocket = null;
	$(function() {
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
			websocket.send("客户端:管理OBS浏览器初始化成功...");
		};

		//获得消息事件
		websocket.onmessage = function(msg) {
			console.log("服务端消息:" + msg.data);
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
		
		$('#showIframe').on('load',function(){
			 $(this).contents().find("all-bg").css('width','200px');
		});
		
	});
	
	var ifr = document.getElementById('showIframe');
	  ifr.onload = function() {
	  	  //解决打开高度太高的页面后再打开高度较小页面滚动条不收缩
		  $("#showIframe").contents().find("#all-bg").css({'height':'160%','width':'165%'});
	  }
	
	var checkDatas = {};
	/*初始化显示数字*/
	var num = 0;
	function checkBtn(page,type,url,ele){
		if(checkDatas.hasOwnProperty(page)){
			var checkNum = $(ele).children("span").text();
			$(ele).children("span").html('');
			/*移除map*/
			delete checkDatas[page];
			for(var key in checkDatas){
				if(checkDatas[key].index>=checkNum){
					$("#"+checkDatas[key].page).children("span").html(checkDatas[key].index);
					/*将新的index重新定位*/
					checkDatas[key].index = checkDatas[key].index-1;
				}
			}
			num--;
		}else{
			var data = new Object();
			data.page = page;
			data.type = type;
			data.url = url;
			data.index = num;
			checkDatas[page]=data;
			num++;
			$(ele).children("span").html(num);
			$("#showIframe").attr('src',url);
		}
	}
	
	/*重置所有元素*/
	function clearAll(){
		var clear = {};
		clear['clear'] = 'true';
		//调用send方法
		websocket.send(JSON.stringify(clear));
		num=0;
		$(".badge").html('');
		checkDatas = {};
		$("#showIframe").attr('src','');
		layer.msg("重置成功");
	}

	
	/*全选*/
	function checkAll(){
		$("#checkPush").find("button").click();
	}
	
	/*推送*/
	function push(){
		var size = Object.keys(checkDatas).length;
		if(size <= 0){
			topMsg('请选择要推送的内容');
			return;
		}else if(size == 1){
			//单选还是多推送
			checkDatas['checks'] == false;
			websocket.send(JSON.stringify(checkDatas));
			layer.msg("推送成功");
		}else{
			checkDatas['checks'] == true;
			var pushTime = $("#pushTime").val();
			if(isBlank(pushTime)){
				layer.confirm('当前间隔时间未设置,是否继续推送？', {
					skin:'layui-layer-lan',
					title:'警告',
					time : 20000, //20s后自动关闭
					btn : [ '推送', '去设置']
				},function(){
					checkDatas["pushTime"] = 1;
					websocket.send(JSON.stringify(checkDatas));
					layer.closeAll();
					layer.msg("推送成功");
					delete checkDatas["pushTime"];
					return;
				});
			}else if(isNaN(pushTime)||!(/^(\+|-)?\d+$/.test(pushTime))||pushTime<0){
				topMsg('请输入合法的间隔时间');
				return;
			}else{
				checkDatas["pushTime"]=pushTime;
				/*向后端发送请求数据*/
				websocket.send(JSON.stringify(checkDatas));
				delete checkDatas["pushTime"];
				layer.msg("推送成功");
			}
		}
	}
	
	
	function resetData(){
		layer.confirm('此功能仅用于<span style="color:red">赛后数据</span>异常进行重新计算,请勿在比赛中执行', {
			skin:'layui-layer-lan',
			title:'警告',
			time : 20000, //20s后自动关闭
			btn : [ '确定', '取消']
		},function(){
		     $.ajax({
	                type: "GET",
	                dataType: "json",
	                url: "${ctx!}/admin/manage/resetData",
	                success: function(res){
	                	layer.closeAll();
	                    layer.msg(res.message, {time: 2000}, function () {});
	                }
	            });
			return;
		});
	}
	
</script>
</#assign>
</@layout>