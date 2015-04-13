<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.register.title")}[#if systemShowPowered] - Powered By shop++[/#if]</title>
<meta name="author" content="shop++ Team" />
<meta name="copyright" content="shop++" />
[#include "/shop/include/common.ftl" /]
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $registerForm = $("#registerForm");
	var $username = $("#username");
	var $password = $("#password");
	var $email = $("#email");
	var $areaId = $("#areaId");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $submit = $(":submit");
	var $agreement = $("#agreement");

	// 地区选择
	$areaId.lSelect({
		url: "${base}/common/area.jhtml"
	});

	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});

	// 注册协议
	$agreement.hover(function() {
		$(this).height(200);
	});

	// 表单验证
	$registerForm.validate({
		rules: {
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: ${setting.usernameMinLength},
				remote: {
					url: "${base}/register/check_username.jhtml",
					cache: false
				}
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: ${setting.passwordMinLength}
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "${base}/register/check_email.jhtml",
						cache: false
					}
				[/#if]
			},
			captcha: "required"
			[@member_attribute_list]
				[#list memberAttributes as memberAttribute]
					[#if memberAttribute.isRequired]
						,memberAttribute_${memberAttribute.id}: {
							required: true
						}
					[/#if]
				[/#list]
			[/@member_attribute_list]
		},
		messages: {
			username: {
				pattern: "${message("shop.register.usernameIllegal")}",
				remote: "${message("shop.register.disabledExist")}"
			},
			password: {
				pattern: "${message("shop.register.passwordIllegal")}"
			}
			[#if !setting.isDuplicateEmail]
				,email: {
					remote: "${message("shop.register.emailExist")}"
				}
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
						url: $registerForm.attr("action"),
						type: "POST",
						data: {
							username: $username.val(),
							enPassword: enPassword,
							email: $email.val()
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
								,captchaId: "${captchaId}",
								captcha: $captcha.val()
							[/#if]
							[@member_attribute_list]
								[#list memberAttributes as memberAttribute]
									,memberAttribute_${memberAttribute.id}: $(":input[name='memberAttribute_${memberAttribute.id}']").val()
								[/#list]
							[/@member_attribute_list]
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								window.setTimeout(function() {
									$submit.prop("disabled", false);
									location.href = "${base}/";
								}, 3000);
							} else {
								$submit.prop("disabled", false);
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
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
	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="am-panel am-panel-default">
					<div class="am-panel-hd am-cf">
						<strong>${message("shop.register.title")}</strong>USER REGISTER
					</div>
                    <div class="am-panel-bd am-collapse am-in" >
                    <form id="registerForm" class="am-form am-form-horizontal" action="${base}/register/submit.jhtml" method="post">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label">姓名 / Name</label>
                            <div class="am-u-sm-9">
                                <input type="password" id="user-name" placeholder="姓名 / Name">
                                <shop>输入你的名字，让我们记住你。</shop>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-email" class="am-u-sm-3 am-form-label"><span class="requiredField">*</span>${message("shop.register.username")}:</label>
                            <div class="am-u-sm-9">
                                <input type="email" id="user-email" placeholder="${message("shop.register.username")}" id="username" name="username"  maxlength="${setting.usernameMaxLength}">
                                <shop>邮箱你懂得...</shop>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-phone" class="am-u-sm-3 am-form-label">电话 / Telephone</label>
                            <div class="am-u-sm-9">
                                <input type="email" id="user-phone" placeholder="输入你的电话号码 / Telephone">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-QQ" class="am-u-sm-3 am-form-label">QQ</label>
                            <div class="am-u-sm-9">
                                <input type="email" id="user-QQ" placeholder="输入你的QQ号码">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-weibo" class="am-u-sm-3 am-form-label">微博 / Twitter</label>
                            <div class="am-u-sm-9">
                                <input type="email" id="user-weibo" placeholder="输入你的微博 / Twitter">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-intro" class="am-u-sm-3 am-form-label">简介 / Intro</label>
                            <div class="am-u-sm-9">
                                <textarea class="" rows="5" id="user-intro" placeholder="输入个人简介"></textarea>
                                <shop>250字以内写出你的一生...</shop>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <div class="am-u-sm-9 am-u-sm-push-3">
                                <button type="button" class="am-btn am-btn-primary">保存修改</button>
                            </div>
                        </div>
						<table>
							<tr>
								<th>

								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="${setting.usernameMaxLength}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("shop.register.password")}:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="${setting.passwordMaxLength}" autocomplete="off" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("shop.register.rePassword")}:
								</th>
								<td>
									<input type="password" name="rePassword" class="text" maxlength="${setting.passwordMaxLength}" autocomplete="off" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("shop.register.email")}:
								</th>
								<td>
									<input type="text" id="email" name="email" class="text" maxlength="200" />
								</td>
							</tr>
							[@member_attribute_list]
								[#list memberAttributes as memberAttribute]
									<tr>
										<th>
											[#if memberAttribute.isRequired]<span class="requiredField">*</span>[/#if]${memberAttribute.name}:
										</th>
										<td>
											[#if memberAttribute.type == "name"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "gender"]
												<span class="fieldSet">
													[#list genders as gender]
														<label>
															<input type="radio" name="memberAttribute_${memberAttribute.id}" value="${gender}" />${message("Member.Gender." + gender)}
														</label>
													[/#list]
												</span>
											[#elseif memberAttribute.type == "birth"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" onfocus="WdatePicker();" />
											[#elseif memberAttribute.type == "area"]
												<span class="fieldSet">
													<input type="hidden" id="areaId" name="memberAttribute_${memberAttribute.id}" />
												</span>
											[#elseif memberAttribute.type == "address"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "zipCode"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "phone"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "mobile"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "text"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "select"]
												<select name="memberAttribute_${memberAttribute.id}">
													<option value="">${message("shop.common.choose")}</option>
													[#list memberAttribute.options as option]
														<option value="${option}">
															${option}
														</option>
													[/#list]
												</select>
											[#elseif memberAttribute.type == "checkbox"]
												<span class="fieldSet">
													[#list memberAttribute.options as option]
														<label>
															<input type="checkbox" name="memberAttribute_${memberAttribute.id}" value="${option}" />${option}
														</label>
													[/#list]
												</span>
											[/#if]
										</td>
									</tr>
								[/#list]
							[/@member_attribute_list]
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
								<tr>
									<th>
										<span class="requiredField">*</span>${message("shop.captcha.name")}:
									</th>
									<td>
										<span class="fieldSet">
											<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" /><img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
										</span>
									</td>
								</tr>
							[/#if]
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submit" value="${message("shop.register.submit")}" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									${message("shop.register.agreement")}
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<div id="agreement" class="agreement">
										${setting.registerAgreement}
									</div>
								</td>
							</tr>
						</table>
						<div class="login">
							[@ad_position id = 8 /]
							<dl>
								<dt>${message("shop.register.hasAccount")}</dt>
								<dd>
									${message("shop.register.tips")}
									<a href="${base}/login.jhtml">${message("shop.register.login")}</a>
								</dd>
							</dl>
						</div>
					</form>
                    </div>
				</div>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>