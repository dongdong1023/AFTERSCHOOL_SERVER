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
<link rel="stylesheet" href="${ctx!}/css/after/killsContrast.css">
<link rel="stylesheet" href="${ctx!}/css/animateUtil.css">
<script src="${ctx!}/js/jquery.min.js"></script>
<script src="${ctx!}/js/util.js"></script>
<script src="${ctx!}/js/jquery.easing.1.3.js"></script>
<script src="${ctx!}/js/animate.js"></script>
<title>击杀对比</title>
</head>

<body>
	<div class="all-bg" id="all-bg">
		<div class="dreamIcon" id="ct-dreamIcon"></div>
		<div class="ct-people" id="ct-people"></div>
		<div class="dreamIcon" id="t-dreamIcon"></div>
		<div class="t-people" id="t-people"></div>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		$.ajax({
			method : "get",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			url : '${ctx!}/match/allInfo.action?rank=team,kills&limit=10',
			async : false,
			success : function(data) {
				var info = data.data;
				var tHtml = '';
				var ctHtml = '';
				var tDreamIcon = '';
				var ctDreamIcon = '';
				for (var i = 0; i < info.length; i++) {
					if (info[i].team == 'CT') {
						ctDreamIcon ='<img id="dreamIcon-ct" src="${cxt!}/'+info[i].dreamIcon+'" >';
						ctHtml += '<div class="ct-info" id="ct-info">';
						ctHtml += '<div class="name" id="ct-name">'
								+ info[i].gameName + '</div>';
						ctHtml += '<div class="kills" id="ct-kills">'
								+ info[i].kills + '</div>';
						ctHtml += '</div>'
					} else if (info[i].team == 'T') {
						tDreamIcon ='<img id="dreamIcon-t" src="${cxt!}/'+info[i].dreamIcon+'" >';
						tHtml += '<div class="info t-info" id="t-info">';
						tHtml += '<div class="name" id="t-name">'
								+ info[i].gameName + '</div>';
						tHtml += '<div class="kills" id="t-kills">'
								+ info[i].kills + '</div>';
						tHtml += '</div>';
					}
				}
  				$("#ct-dreamIcon").html(ctDreamIcon);
				$("#t-dreamIcon").html(tDreamIcon);  
				$("#ct-people").html(ctHtml);
				$("#t-people").html(tHtml);
			}
		});
	});
</script>
</html>