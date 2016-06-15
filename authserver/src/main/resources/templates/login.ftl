<html>
<head>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<div class="login-block">
    <div class="logo"></div>
    <form role="form" action="login" method="post">
        <h1>Общий вход в систему</h1>
        <input type="text" class="form-control" placeholder="Логин" id="username" name="username"/>
        <input type="password" class="form-control" placeholder="Пароль" id="password" name="password"/>
        <button>Войти</button>
        <#if RequestParameters['error']??>
        <div class="alert alert-danger">
            Не верный логин или пароль. Пожалуйста повторите попытку.
        </div>
        </#if>
    </form>
</div>

</body>
</html>