/*socket连接地址*/
var socketIP = "127.0.0.1";
/*实时key*/
var underway_type = "underway";
/*赛后key*/
var after_type = "after";
/*重置key*/
var clear_type = "clear";

/*获取URL的参数*/
function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
}

/*判断是否为JSON*/
function isJsonString(str) {
    try {
        if (typeof JSON.parse(str) == "object") {
            return true;
        }else{
        }
    } catch(e) {
    }
    return false;
}


/*判断空字符串*/
function isBlank(str){
	if(str == null || str == '' || str == undefined){
		return true;
	}else{
		return false;
	}
}

/*layer头部提醒消息*/
function topMsg(msg){
	layer.msg(msg, {
		  offset: 't',
		  anim: 6
		});
}
