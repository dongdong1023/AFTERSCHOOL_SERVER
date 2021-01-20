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
<link rel="stylesheet" href="${ctx!}/css/match/memberInfo.css">
<link rel="stylesheet" href="${ctx!}/css/animateUtil.css">
<script src="${ctx!}/js/jquery.min.js"></script>
<script src="${ctx!}/js/util.js"></script>
<script src="${ctx!}/js/jquery.easing.1.3.js"></script>
<script src="${ctx!}/js/animate.js"></script>
<title>个人数据</title>
</head>

<body>
	<input type="hidden" id="steamId" value="${steamId}">
	<div class="all-bg" id="all-bg">
		<div class="people-info" id="people-info">
		</div>
		
		<div class="people-data" id="people-data">
		</div>
	</div>
</body>

<script type="text/javascript">

	var steamId = $("#steamId").val();
	$(function() {
		if(isBlank(steamId)){
			console.log("steamId为空");
			return false;
		}
		$.ajax({
			method : "get",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			url : '${ctx!}/match/member/'+ steamId,
			async : false,
			success : function(data) {
				var info = data.data;
				var infoHtml = '';
				var dataHtml = '';
				if(data.code == 0){
					/*左边人员信息*/
					infoHtml += '<img class="img" src="${ctx!}/'+info.imageFile+'"></img>';
					infoHtml += '<div class="name-rail">';
					infoHtml += '<div class="name-info">';
					infoHtml += '<div class="dream-name">'+info.gameName+'</div>';
					infoHtml += '<div class="name">'+info.dreamName+'</div>';
					infoHtml += '</div>';
					infoHtml += '</div>';

					/*右边详细数据*/
					dataHtml += '<div class="data">'+info.kills+'</div>';
					dataHtml += '<div class="data">'+info.deaths+'</div>';
					dataHtml += '<div class="data">'+info.assists+'</div>';
					dataHtml += '<div class="data">'+info.entryKills+'</div>';
					dataHtml += '<div class="data">'+info.kdRatio+'</div>';
				}
				$("#people-info").html(infoHtml);
				$("#people-data").html(dataHtml);
			}
		});
		});
</script>