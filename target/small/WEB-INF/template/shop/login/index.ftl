<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("shop.login.title")}[#if systemShowPowered] - Powered By shop++[/#if]</title>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
[#include "/shop/include/common.ftl" /]
    <!--AmazeUI end-->
<script type="text/javascript">
$().ready(function() {

	var $loginForm = $("#loginForm");
	var $username = $("#username");
	var $password = $("#password");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $isRememberUsername = $("#isRememberUsername");
	var $submit = $(":submit");
	
	// 记住用户名
	if (getCookie("memberUsername") != null) {
		$isRememberUsername.prop("checked", true);
		$username.val(getCookie("memberUsername"));
		$password.focus();
	} else {
		$isRememberUsername.prop("checked", false);
		$username.focus();
	}
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 表单验证、记住用户名
	$loginForm.validate({
		rules: {
			username: "required",
			password: "required"
			[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
				,captcha: "required"
			[/#if]
		},
		submitHandler: function(form) {
			$.ajax({
				url: "${base}/common/public_key.jhtml",
				type: "GET",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$submit.prop("disabled", true);
				},
				success: function(data) {
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($password.val()));
					$.ajax({
						url: $loginForm.attr("action"),
						type: "POST",
						data: {
							username: $username.val(),
							enPassword: enPassword
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
								,captchaId: "${captchaId}",
								captcha: $captcha.val()
							[/#if]
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							if ($isRememberUsername.prop("checked")) {
								addCookie("memberUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
							} else {
								removeCookie("memberUsername");
							}
							$submit.prop("disabled", false);
							if (message.type == "success") {
								[#if redirectUrl??]
									location.href = "${redirectUrl}";
								[#else]
									location.href = "${base}/";
								[/#if]
							} else {
								$.message(message);
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
									$captcha.val("");
									$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
								[/#if]
							}
						}
					});
				}
			});
		}
	});

});
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="container login">
		<div class="span12">
			[@ad_position id = 9 /]
		</div>
		<div class="span12 last">
				<div class="am-panel am-panel-default">
					<div class="am-panel-hd am-cf">
						<strong>${message("shop.login.title")}</strong>
					</div>
                    <div class="am-panel-bd am-collapse am-in" >
                    <form class="am-form am-form-horizontal"  data-am-validator id="loginForm"  action="${base}/login/submit.jhtml" method="post">
                        <!-- am-form-group 的基础上添加了 am-form-group-sm -->
                              <div class="am-form-group am-form-icon">
                                <i class="am-icon-user"></i>
                                <input required  type="text" id="username" name="username" maxlength="${setting.usernameMaxLength}" class="am-form-field width100" placeholder="[#if setting.isEmailLogin]${message("shop.login.usernameOrEmail")}[#else]${message("shop.login.username")}[/#if]">
                         </div>

                        <!-- am-form-group 的基础上添加了 am-form-group-lg -->

                            <div class="am-form-group am-form-icon">
                                <i class="am-icon-key"></i>
                                <input required type="password" id="password" name="password" maxlength="${setting.passwordMaxLength}" autocomplete="off"  class="am-form-field width200" placeholder="${message("shop.login.password")}">
                            </div>
                        [#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
                          <div class="am-form-group am-cf">
                              <div class="am-form-icon am-fl">
                                <i class="am-icon-keyboard-o"></i>
                              <input type="text" required  size="10" id="captcha" name="captcha"  maxlength="4" autocomplete="off"  class="am-form-field am-input-sm" placeholder=" ${message("shop.captcha.name")}">
                              </div>
                              <div class="am-form-icon am-fr">
                                  <img id="captchaImage"  src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
                              </div>
                          </div>
                        [/#if]

                        <div class="am-form-group am-cf">
                                <div class="am-checkbox am-fl">
                                    <label>
                                        <input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true" />${message("shop.login.isRememberUsername")}
                                    </label>
                                </div>
                                <div class="am-fr"><label>
                                    &nbsp;&nbsp;<a href="${base}/password/find.jhtml">${message("shop.login.findPassword")}</a>
                                </label></div>
                        </div>

                        <div class="am-form-group">
                                <input type="submit" name="" value="${message("shop.login.submit")}" class="am-btn am-btn-primary am-btn-block">
                           </div>
                    </form>
			</div>
            </div>
        </div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>