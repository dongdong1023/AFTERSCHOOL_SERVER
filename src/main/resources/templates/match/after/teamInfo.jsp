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
<link rel="stylesheet" href="${ctx!}/css/after/bestPlayerSpecial.css">
<link rel="stylesheet" href="${ctx!}/css/animateUtil.css">
<script src="${ctx!}/js/jquery.min.js"></script>
<script src="${ctx!}/js/util.js"></script>
<script src="${ctx!}/js/jquery.easing.1.3.js"></script>
<script src="${ctx!}/js/animate.js"></script>
<title>战队数据对比</title>
</head>

<body>
	<div class="all-bg" id="all-bg">
		<div class="ct-people" id="ct-people">
			<div class="img" id="ct-img" style="background: url('${ctx!}/${bestPlayerCT.imageFile}');"></div>
			<div class="name-info" id="ct-name-info">
				<div class="dream-name">${bestPlayerCT.dreamName}</div>
				<div class="name">${bestPlayerCT.name}</div>
			</div>
			<div class="data-info" id="ct-data">
			<div class="data"></div>
			<div class="data">${bestPlayerCT.kills}</div>
			<div class="data">${bestPlayerCT.kdRatio}</div>
			<div class="data"></div>
			</div>
		</div>
		<div class="t-people" id="t-people">
			<div class="img" id="t-img" style="background: url('${ctx!}/${bestPlayerT.imageFile}');"></div>
			<div class="name-info" id="t-name-info">
				<div class="dream-name">${bestPlayerT.dreamName}</div>
				<div class="name">${bestPlayerT.name}</div>
			</div>
			<div class="data-info" id="t-data">
			<div class="data"></div>
			<div class="data">${bestPlayerT.kills}</div>
			<div class="data">${bestPlayerT.kdRatio}</div>
			<div class="data"></div>
			</div>
		</div>
	</div>
</body>

</html>