<#assign context=ctx.contextPath/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>${siteInfo.name}管理后台</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${context}/favicon.png">
    <link rel="stylesheet" href="${context}/css/vendor/bootstrap/dist/bootstrap.css">
    <link rel="stylesheet" href="${context}/css/vendor/Animate.css">
    <link rel="stylesheet" href="${context}/css/basic.css">
    <link rel="stylesheet" href="${context}/css/vendor/font_awesome/css/font-awesome.css">
</head>

<body>
<div class="container">
    <form class="form-signin" method="get" action="">
        <div class="logo animated bounceIn"><img src="${context}/favicon.png"/></div>
        <h1>${siteInfo.name}管理后台</h1>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="用户名" name="username" required="required"
                   autofocus="autofocus">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="密码" name="password" required="required">
        </div>
        <button class="btn btn-lg btn-primary btn-block subBtn" onclick="return login()">登录</button>
        <a class="btn btn-lg btn-default btn-block subBtn" href="register">去注册</a>
    </form>
</div>

<script src="${context}/js/vendor/jquery.min.js"></script>
<script src="${context}/js/refuel.js"></script>
<script src="${context}/js/vendor/bootstrap/dist/bootstrap.js"></script>
<script src="${context}/js/vendor/ie10-viewport-bug-workaround.js"></script>
<script>
    function login() {
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();

        $.ajax({
            url: "admin/user/login",
            method: "POST",
            data: {
                username: username,
                password: password
            },
            success: function (res) {
                // console.log(res);
                if (res.status === 0) {
                    addCookie(CommonStr.USER_INFO, "admin", 1);
                    var url = "${context}/";
                    console.log(url)
                    location.href = url;
                } else {
                    alert("登陆失败");
                }
            }
        });
        return false;
    }
</script>
</body>
</html>