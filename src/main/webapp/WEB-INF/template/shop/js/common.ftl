
var shopxx = {
	base: "${base}",
	locale: "${locale}"
};

var setting = {
	priceScale: "${setting.priceScale}",
	priceRoundType: "${setting.priceRoundType}",
	currencySign: "${setting.currencySign}",
	currencyUnit: "${setting.currencyUnit}",
	uploadImageExtension: "${setting.uploadImageExtension}",
	uploadFlashExtension: "${setting.uploadFlashExtension}",
	uploadMediaExtension: "${setting.uploadMediaExtension}",
	uploadFileExtension: "${setting.uploadFileExtension}"
};

var messages = {
	"shop.message.success": "${message("shop.message.success")}",
	"shop.message.error": "${message("shop.message.error")}",
	"shop.dialog.ok": "${message("shop.dialog.ok")}",
	"shop.dialog.cancel": "${message("shop.dialog.cancel")}",
	"shop.dialog.deleteConfirm": "${message("shop.dialog.deleteConfirm")}",
	"shop.dialog.clearConfirm": "${message("shop.dialog.clearConfirm")}",
	"shop.validate.required": "${message("shop.validate.required")}",
	"shop.validate.email": "${message("shop.validate.email")}",
	"shop.validate.url": "${message("shop.validate.url")}",
	"shop.validate.date": "${message("shop.validate.date")}",
	"shop.validate.dateISO": "${message("shop.validate.dateISO")}",
	"shop.validate.pointcard": "${message("shop.validate.pointcard")}",
	"shop.validate.number": "${message("shop.validate.number")}",
	"shop.validate.digits": "${message("shop.validate.digits")}",
	"shop.validate.minlength": "${message("shop.validate.minlength")}",
	"shop.validate.maxlength": "${message("shop.validate.maxlength")}",
	"shop.validate.rangelength": "${message("shop.validate.rangelength")}",
	"shop.validate.min": "${message("shop.validate.min")}",
	"shop.validate.max": "${message("shop.validate.max")}",
	"shop.validate.range": "${message("shop.validate.range")}",
	"shop.validate.accept": "${message("shop.validate.accept")}",
	"shop.validate.equalTo": "${message("shop.validate.equalTo")}",
	"shop.validate.remote": "${message("shop.validate.remote")}",
	"shop.validate.integer": "${message("shop.validate.integer")}",
	"shop.validate.positive": "${message("shop.validate.positive")}",
	"shop.validate.negative": "${message("shop.validate.negative")}",
	"shop.validate.decimal": "${message("shop.validate.decimal")}",
	"shop.validate.pattern": "${message("shop.validate.pattern")}",
	"shop.validate.extension": "${message("shop.validate.extension")}"
};

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// 货币格式化
function currency(value, showSign, showUnit) {
	if (value != null) {
		var price;
		if (setting.priceRoundType == "roundHalfUp") {
			price = (Math.round(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else if (setting.priceRoundType == "roundUp") {
			price = (Math.ceil(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else {
			price = (Math.floor(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		}
		if (showSign) {
			price = setting.currencySign + price;
		}
		if (showUnit) {
			price += setting.currencyUnit;
		}
		return price;
	}
}

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			}
		}
	}
}

(function($) {

	var zIndex = 100;

	// 检测登录
	$.checkLogin = function() {
		var result = false;
		$.ajax({
			url: shopxx.base + "/login/check.jhtml",
			type: "GET",
			dataType: "json",
			cache: false,
			async: false,
			success: function(data) {
				result = data;
			}
		});
		return result;
	}

	// 跳转登录
	$.redirectLogin = function (redirectUrl, message) {
		var href = shopxx.base + "/login.jhtml";
		if (redirectUrl != null) {
			href += "?redirectUrl=" + encodeURIComponent(redirectUrl);
		}
		if (message != null) {
			$.message("warn", message);
			setTimeout(function() {
				location.href = href;
			}, 1000);
		} else {
			location.href = href;
		}
	}

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}

		if (message.type == null || message.content == null) {
			return false;
		}

		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}

		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();

		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}

	// 令牌
	$(document).ajaxSend(function(event, request, settings) {
		if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
			var token = getCookie("token");
			if (token != null) {
				request.setRequestHeader("token", token);
			}
		}
	});

	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");

		if (loginStatus == "accessDenied") {
			$.redirectLogin(window.location.href, "${message("shop.login.accessDenied")}");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global: false,
					headers: {token: token}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$().ready(function() {

	$("form").submit(function() {
		var $this = $(this);
		if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && $this.find("input[name='token']").size() == 0) {
			var token = getCookie("token");
			if (token != null) {
				$this.append('<input type="hidden" name="token" value="' + token + '" \/>');
			}
		}
	});

});

// 验证消息
if ($.validator != null) {
	$.extend($.validator.messages, {
		required: message("shop.validate.required"),
		email: message("shop.validate.email"),
		url: message("shop.validate.url"),
		date: message("shop.validate.date"),
		dateISO: message("shop.validate.dateISO"),
		pointcard: message("shop.validate.pointcard"),
		number: message("shop.validate.number"),
		digits: message("shop.validate.digits"),
		minlength: $.validator.format(message("shop.validate.minlength")),
		maxlength: $.validator.format(message("shop.validate.maxlength")),
		rangelength: $.validator.format(message("shop.validate.rangelength")),
		min: $.validator.format(message("shop.validate.min")),
		max: $.validator.format(message("shop.validate.max")),
		range: $.validator.format(message("shop.validate.range")),
		accept: message("shop.validate.accept"),
		equalTo: message("shop.validate.equalTo"),
		remote: message("shop.validate.remote"),
		integer: message("shop.validate.integer"),
		positive: message("shop.validate.positive"),
		negative: message("shop.validate.negative"),
		decimal: message("shop.validate.decimal"),
		pattern: message("shop.validate.pattern"),
		extension: message("shop.validate.extension")
	});

	$.validator.setDefaults({
		errorClass: "fieldError",
		ignore: ".ignore",
		ignoreTitle: true,
		errorPlacement: function(error, element) {
			var fieldSet = element.closest("span.fieldSet");
			if (fieldSet.size() > 0) {
				error.appendTo(fieldSet);
			} else {
				error.insertAfter(element);
			}
		},
		submitHandler: function(form) {
			$(form).find(":submit").prop("disabled", true);
			form.submit();
		}
	});
}

        var TB=TB||{};TB.Header=function(){var g=function(v){return typeof(v)!="string"?v:document.getElementById(v)},s=navigator.userAgent.toLowerCase(),o=/msie/.test(s)&&!/opera/.test(s),l=o&&!/msie 7/.test(s)&&!/msie 8/.test(s),m="http://list.taobao.com/browse/cat-0.htm";var i={getCookie:function(w){var v=document.cookie.match("(?:^|;)\\s*"+w+"=([^;]*)");return(v&&v[1])?decodeURIComponent(v[1]):""},parseQueryParams:function(B){var y={};var w=B.split("&");for(var z=0,A=w.length;z<A;++z){var x=w[z],C=x.search("=");var D=x.substring(0,C);var v=x.substring(C+1,x.length);y[decodeURIComponent(D)]=decodeURIComponent(v)}return y},trim:function(v){return v.replace(/^\s+|\s+$/g,"")},hasClass:function(w,v){w=g(w);if(!w||!v||!w.className){return false}return(" "+w.className+" ").indexOf(" "+v+" ")>-1},addClass:function(w,v){w=g(w);if(!w||!v){return}if(this.hasClass(w,v)){return}w.className+=" "+v},removeClass:function(w,v){w=g(w);if(!this.hasClass(w,v)){return}w.className=w.className.replace(new RegExp(v,"g"),"");if(!this.trim(w.className)){w.removeAttribute(o?"className":"class")}},addEvent:function(x,w,v){x=g(x);if(!x||!w||typeof(v)!="function"){return}if(x.addEventListener){x.addEventListener(w,v,false)}else{if(x.attachEvent){x.attachEvent("on"+w,v)}}},stopEvent:function(v){if(v.stopPropagation){v.stopPropagation()}else{v.cancelBubble=true}if(v.preventDefault){v.preventDefault()}else{v.returnValue=false}},getElementsByClassName:function(w,B,v,A){if(!g(v)){return}var x=[],z=g(v).getElementsByTagName(B),y=0;for(;y<z.length;y++){if(i.hasClass(z[y],w)){x[x.length]=z[y];arguments[3]&&arguments[3].call(z[y])}}return x},escapeHTML:function(w){var x=document.createElement("div");var v=document.createTextNode(w);x.appendChild(v);return x.innerHTML}};var e=i.getCookie("tracknick"),t=i.getCookie("_nk_")||e,j=i.getCookie("uc1"),d=i.parseQueryParams(j),q=i.getCookie("_l_g_")&&t||i.getCookie("ck1")&&e,p=parseInt(d._msg_)||0,k=new Date().getTime(),r=(document.location.href.indexOf("https://")===0);function a(x){if(!x){return}var w=i.getElementsByClassName("menu-bd","div",x)[0];if(!w){return}if(!r){var v=document.createElement("iframe");v.src="about: blank";v.className="menu-bd";x.insertBefore(v,w);x.iframe=v}x.menulist=w;x.onmouseenter=function(){i.addClass(this.parentNode,"hover");if(r){return}this.iframe.style.height=parseInt(this.menulist.offsetHeight)+25+"px";this.iframe.style.width=parseInt(this.menulist.offsetWidth)+1+"px"};x.onmouseleave=function(){i.removeClass(this.parentNode,"hover")}}function c(){var v=document.forms.topSearch;if(!v){return}i.addEvent(v,"submit",function(){if(v.q.value==""){v.action=m}})}function f(W,V){var z=g(W),K=z&&z.q,x=z&&z.search_type,E=z&&z.getElementsByTagName("label")[0],D=z&&z.cat,S=g("J_TSearchTabs").getElementsByTagName("li"),v=S.length,P={},I=false,F=false,y="tsearch-tabs-active",N=function(Y){for(var X=0;X<v;X++){i[X===Y?"addClass":"removeClass"](S[X],y)}},R=g("J_TSearchCat"),U=null,w=g("J_TSearchCatHd"),A=R&&R.getElementsByTagName("div")[0],C=A&&A.getElementsByTagName("a")||[],H=C.length,Q,G=function(X){for(Q=0;Q<H;Q++){if(C[Q].getAttribute("data-value")===X){return C[Q]}}return null},M=function(){i.removeClass(R,"tsearch-cat-active")},T=function(){i.addClass(R,"tsearch-cat-active")},L=function(X){for(Q=0;Q<H;Q++){i[C[Q]===X?"addClass":"removeClass"](C[Q],"tsearch-cat-selected")}M();w.innerHTML=X.innerHTML;D.value=X.getAttribute("data-value")},J=function(){K.focus();if(o){K.value=K.value}};if(!z){return}if(g("J_TSearchTabs")){var O=0,B={"\u5b9d\u8d1d":["item","\u8f93\u5165\u60a8\u60f3\u8981\u7684\u5b9d\u8d1d"],"\u6dd8\u5b9d\u5546\u57ce":["mall","\u8f93\u5165\u60a8\u60f3\u8981\u7684\u5546\u54c1"],"\u5e97\u94fa":["shop","\u8f93\u5165\u60a8\u60f3\u8981\u7684\u5e97\u94fa\u540d\u6216\u638c\u67dc\u540d"],"\u62cd\u5356":["auction","\u8f93\u5165\u60a8\u60f3\u8981\u7684\u5b9d\u8d1d"]};for(;O<v;O++){(function(){var Z=O,X=i.trim(S[Z].getElementsByTagName("a")[0].innerHTML),Y=B[X];P[Y[0]]={index:Z,hint:Y[1]};i.addEvent(S[Z],"click",function(aa){i.stopEvent(aa);N(Z);x.value=Y[0];E.innerHTML=Y[1];J()})})()}}i.addEvent(K,"focus",function(){E.innerHTML=""});i.addEvent(K,"blur",function(){if(i.trim(K.value)===""&&!I){E.innerHTML=P[x.value]["hint"]}});i.addEvent("J_TSearchTabs","mousedown",function(){I=true;F=true;setTimeout(function(){I=false})});i.addEvent("J_TSearchCat","click",function(X){i.stopEvent(X);var Y=X.target||X.srcElement;switch(true){case i.hasClass(Y.parentNode,"tsearch-cat-hd"):case i.hasClass(Y,"tsearch-cat-hd"):T();break;case Y.parentNode.nodeName.toLowerCase()==="div":L(Y);J();break}});i.addEvent(document,"click",M);i.addEvent(z,"submit",function(){switch(z.search_type.value){case"item":z.action=K.value===""?m:"http://search.taobao.com/search";break;case"mall":z.action="http://list.mall.taobao.com/search_dispatcher.htm";break;case"shop":z.action="http://shopsearch.taobao.com/browse/shop_search.htm";break;case"auction":z.atype.value="a";z.filterFineness.value="1,3";break}});E.innerHTML="";setTimeout(function(){if(!F){x.value=(V&&V.searchType)?V.searchType:"item";if(document.domain.indexOf("shopsearch.taobao.com")>-1){x.value="shop"}var X=P[x.value];E.innerHTML=X.hint;N(X.index)}if(R&&(U=G(D.value))){L(U)}if(i.trim(K.value)!==""){E.innerHTML=""}if(V&&V.autoFocus){J()}z.atype.value="";z.filterFineness.value=""})}function b(C){var z=g(C);if(!z){return}var A=z.q;if(!A){return}if(!(window.TB&&TB.Suggest)){return}var w=new TB.Suggest(A,"http://suggest.taobao.com/sug",{resultFormat:"\u7ea6%result%\u4e2a\u5b9d\u8d1d"});var B=z.ssid;if(B){setTimeout(function(){B.value="s5-e"},0);B.setAttribute("autocomplete","off");w.subscribe("onItemSelect",function(){if(B.value.indexOf("-p1")==-1){B.value+="-p1"}})}var x=z.elements.search_type;var v=function(){return x.value};var y=w._needUpdate;w._needUpdate=function(){var D=v();return(D==="item"||D==="mall")&&y.call(w)}}function n(v){var w=g(v);if(!w){return}i.addEvent(w,"click",function(y){i.stopEvent(y);var x=w.href;new Image().src="//taobao.alipay.com/user/logout.htm";setTimeout(function(){location.href=x},20)})}function h(){if(document.domain.indexOf(".taobao.net")===-1){return}var y=document.getElementById("header"),x=y?y.getElementsByTagName("a"):[],w=0,v=x.length,z=location.hostname.split(".");while(z.length>3){z.shift()}z=z.join(".");for(;w<v;w++){x[w].href=x[w].href.replace("taobao.com",z)}}function u(){if(document.location.href.indexOf("https://")===0){return}var v=document,y=v.getElementsByTagName("head")[0],x=v.createElement("script");x.src="http://a.tbcdn.cn/app/search/monitor.js?t=20100331.js";y.appendChild(x)}return{init:function(w){if(l){var v=i.getElementsByClassName("menu","div","site-nav",function(){a(this)})}h();c();u();n("J_Logout");if(g("J_TSearch")){f("J_TSearchForm",w);setTimeout(function x(){if(typeof x.count=="undefined"){x.count=0}x.count++;if(!(window.TB&&TB.Suggest)){setTimeout(arguments.callee,200)}else{b("J_TSearchForm")}},200)}},writeLoginInfo:function(z){z=z||{};var A=z.memberServer||"http://member1.taobao.com";var x=z.loginServer||A;var D=z.loginUrl||x+"/member/login.jhtml?f=top";var w=location.href;var F=/^http.*(\/member\/login\.jhtml)$/i;if(F.test(w)){w=""}var v=z.redirectUrl||w;if(v){D+="&redirectURL="+encodeURIComponent(v)}var C=z.logoutUrl||x+"/member/logout.jhtml?f=top";var B=A+"/member/newbie.htm";var E=A+"/message/list_private_msg.htm?t="+k;var G="http://jianghu.taobao.com/admin/home.htm?t="+k;var y="";if(q){y='\u60a8\u597d\uff0c<a class="user-nick" href="../images/'+G+'" target="_top">'+i.escapeHTML(unescape(t.replace(/\\u/g,"%u")))+"</a>\uff01";y+='<a id="J_Logout" href="../images/'+C+'" target="_top">\u9000\u51fa</a>';y+='<a href="../images/'+E+'" target="_top">\u7ad9\u5185\u4fe1';if(p){y+="("+p+")"}y+="</a>"}else{y='\u60a8\u597d\uff0c\u6b22\u8fce\u6765\u6dd8\u5b9d\uff01<a href="../images/'+D+'" target="_top">\u8bf7\u767b\u5f55</a>';y+='<a href="../images/'+B+'" target="_top">\u514d\u8d39\u6ce8\u518c</a>'}document.write(y)}}}();
