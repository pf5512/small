<script type="text/javascript">
$().ready(function() {

	var $headerLogin = $("#headerLogin");
	var $headerRegister = $("#headerRegister");
	var $headerUsername = $("#headerUsername");
	var $headerLogout = $("#headerLogout");
	var $productSearchForm = $("#productSearchForm");
	var $keyword = $("#productSearchForm input");
	var defaultKeyword = "${message("shop.header.keyword")}";

	var username = getCookie("username");
	if (username != null) {
		$headerUsername.html("<span class='am-icon-user'></span>${message("shop.header.welcome")}, " + username).show();
		$headerLogout.show();
	} else {
		$headerLogin.show();
		$headerRegister.show();
	}

	$keyword.focus(function() {
		if ($keyword.val() == defaultKeyword) {
			$keyword.val("");
		}
	});

	$keyword.blur(function() {
		if ($keyword.val() == "") {
			$keyword.val(defaultKeyword);
		}
	});

	$productSearchForm.submit(function() {
		if ($.trim($keyword.val()) == "" || $keyword.val() == defaultKeyword) {
			return false;
		}
	});

});
</script>
<header class="am-topbar am-topbar-fixed-top">
    <div class="am-container">
        <h1 class="am-topbar-brand" id="headerUsername">
            <a href="#">Amaze UI</a>
        </h1>

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-secondary am-show-sm-only"
                data-am-collapse="{target: '#collapse-head'}"><span class="am-sr-only">导航切换</span> <span
                class="am-icon-bars"></span></button>

        <div class="am-collapse am-topbar-collapse" id="collapse-head">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="#">首页</a></li>
                <li id="headerLogin">
                    <a href="${base}/login.jhtml">${message("shop.header.login")}</a>
                </li>
                <li id="headerRegister" class="headerRegister">
                    <a href="${base}/register.jhtml">${message("shop.header.register")}</a>
                </li>
                <li id="headerLogout" class="headerLogout">
                    <a href="${base}/logout.jhtml">[${message("shop.header.logout")}]</a>
                </li>
                <li  class="am-hide-sm-only"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            [@navigation_list position = "top"]
                [#list navigations as navigation]
                    <li class="am-hide-sm-only"><a  href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if] href="javascript:;" id="admin-fullscreen"><span class="am-icon-${navigation.ioc}"></span>
                         <span class="admin-fullText">${navigation.name}
                   </span></a></li>

                [/#list]
            [/@navigation_list]
                <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-phone"></span> <span class="admin-fullText">
               [#if setting.phone??]
                   ${message("shop.header.phone")}:
                       <strong>${setting.phone}</strong>
               [/#if]
             </span></a></li>
            </ul>

        </div>
    </div>
</header>
 <div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${base}/">
				<img src="${setting.logo}" alt="${setting.siteName}" />
			</a>
		</div>
	</div>
	<div class="span9">

	</div>
	<div class="span10 last">
        <form id="productSearchForm" action="${base}/product/search.jhtml" method="get" class="am-form-inline" role="search">
            <div class="am-form-group">
                <input type="text"    x-webkit-speech  name="keyword"  maxlength="30" value="${productKeyword!message("shop.header.keyword")}" class="am-form-field am-input-sm" placeholder="输入关键字">
            </div>
            <button type="submit" class="am-btn am-btn-primary"> ${message("shop.header.search")} </button>
            <a class="am-btn am-btn-warning" href="${base}/cart/list.jhtml">
                <i class="am-icon-shoping-cart"></i>
                ${message("shop.header.cart")}
            </a>
        </form>
    [#if setting.hotSearches?has_content]

    ${message("shop.header.hotSearch")}:
        [#list setting.hotSearches as hotSearch]
            <a href="${base}/product/search.jhtml?keyword=${hotSearch?url}">${hotSearch}</a>
        [/#list]
    [/#if]
	</div>
	<div class="span24">
		<ul class="mainNav">
			[@navigation_list position = "middle"]
				[#list navigations as navigation]
					<li[#if navigation.url = url] class="current"[/#if]>
						<a href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if]>${navigation.name}</a>
						[#if navigation_has_next]|[/#if]
					</li>
				[/#list]
			[/@navigation_list]
		</ul>
	</div>
</div>