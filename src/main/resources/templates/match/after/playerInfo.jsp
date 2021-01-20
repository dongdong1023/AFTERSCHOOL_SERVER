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
<link rel="stylesheet" href="${ctx!}/css/after/playerInfo.css">
<link rel="stylesheet" href="${ctx!}/css/animateUtil.css">
<script src="${ctx!}/js/jquery.min.js"></script>
<script src="${ctx!}/js/util.js"></script>
<script src="${ctx!}/js/jquery.easing.1.3.js"></script>
<script src="${ctx!}/js/animate.js"></script>
<script src="${ctx!}/js/jquery.easing.1.3.js"></script>
<script src="${ctx!}/js/animate.js"></script>
<title>队伍数据</title>
</head>

<body>
	<div class="all-bg" id="all-bg">
		<div class="people-infos" id="people-infos"></div>
	</div>
</body>


<script type="text/javascript">

$(function() {
	$.ajax({
		method : "get",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		url : '${ctx!}/match/allInfo.action?rank=kills&limit=5',
		async : false,
		success : function(data) {
			var obj = data.data;
			var html = '';
			for (var i = 0; i < obj.length; i++) {
				html += '<div class="people">';
				html += '<div class="head-img" id="head-img"><img class="img" src="${ctx!}/'
						+ obj[i].imageFile + '"/></div>';
				html += '<div class="info name" id="name"><div class="name-text" id="name-text">'
						+ obj[i].gameName + '</div></div>';
				html += '<div class="info kills" id="kills"><div class="num-png" id="kills">'
						+ obj[i].kills + '</div></div>';
				html += '<div class="info deaths" id="deaths"><div class="num-png" id="deaths">'
						+ obj[i].deaths + '</div></div>';
				html += '<div class="info assists" id="assists"><div class="num-png" id="assists">'
						+ obj[i].assists + '</div></div>';
				html += '<div class="info entry-kills" id="entry-kills"><div class="num-png" id="entry-kills">'
						+ obj[i].entryKills + '</div></div>';
				html += '<div class="info kd-ratio" id="kd-ratio"><div class="num-png" id="kd-ratio">'
						+ obj[i].kdRatio + '</div></div>';
				html += '</div>';
			}
			$('#people-infos').html(html);
			/*无图片是容错图*/
			//$("img").attr("onerror",'this.src="/image/default.jpg"');
		}
	});
});

</script>
</html>