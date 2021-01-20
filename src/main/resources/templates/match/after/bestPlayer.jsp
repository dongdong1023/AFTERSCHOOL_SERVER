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
<link rel="stylesheet" href="${ctx!}/css/after/bestPlayer.css">
<link rel="stylesheet" href="${ctx!}/css/animateUtil.css">
<script src="${ctx!}/js/jquery.min.js"></script>
<script src="${ctx!}/js/util.js"></script>
<script src="${ctx!}/js/jquery.easing.1.3.js"></script>
<script src="${ctx!}/js/animate.js"></script>
<title>最佳选手</title>
</head>

<body>
	<div class="all-bg" id="all-bg">
	
		<div class="ct-people" id="ct-people">
			<div class="ct-info" id="ct-info">
				<div class="picture" id="ct-picture"><img src="${ctx!}/${bestPlayerCT.imageFile}" style="width: 100%;height: 100%"></div>
				<div class="name" id="ct-name">${bestPlayerCT.gameName}</div>
				<div class="dream-name" id="ct-dream-name">${bestPlayerCT.dreamName}</div>
			</div>
			<div class="ct-datas datas" id="ct-datas">
				<div class="kills data-num" id="ct-kills">${bestPlayerCT.kills}</div>
				<div class="deaths data-num" id="ct-deaths">${bestPlayerCT.deaths}</div>
				<div class="assists data-num" id="ct-assists">${bestPlayerCT.assists}</div>
				<div class="adr data-num" id="ct-adr">${bestPlayerCT.adrRating}</div>
				<div class="rating data-num" id="ct-rating">${bestPlayerCT.rating}</div>
			</div>
		</div>
		
		<div class="t-people" id="t-people">
			<div class="t-info" id="t-info">
				<div class="picture" id="t-picture"><img src="${ctx!}/${bestPlayerT.imageFile}" style="width: 100%;height: 100%"></div>
				<div class="name" id="t-name">${bestPlayerT.gameName}</div>
				<div class="dream-name" id="t-dream-name">${bestPlayerT.dreamName}</div>
			</div>
			<div class="t-datas datas" id="t-datas">
				<div class="kills data-num" id="t-kills">${bestPlayerT.kills}</div>
				<div class="deaths data-num" id="t-deaths">${bestPlayerT.deaths}</div>
				<div class="assists data-num" id="t-assists">${bestPlayerT.assists}</div>
				<div class="adr data-num" id="t-adr">${bestPlayerT.adrRating}</div>
				<div class="rating data-num" id="t-rating">${bestPlayerT.rating}</div>
			</div>
		</div>
	</div>
</body>

</html>