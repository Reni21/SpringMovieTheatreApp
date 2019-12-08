<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>

<!doctype html>

<html lang="en">
    <head>
        <title>Error</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i,800,800i&display=swap"
              rel="stylesheet">
        <!-- Common css -->
        <link rel="stylesheet" type="text/css" href="/css/common-styles.css">
        <!-- Unique css -->
        <link rel="stylesheet" href="/css/login-styles.css">
    </head>
    <body>
        <header>
        </header>
        <main>
            <div class="content-card">
                <div class="content-card__container">
                    <div class="page-title">500 SOMETHING WENT WRONG</div>
                    <div class="form__container">
                        <p class="error-msg" style="font-size: 18px;font-weight: 600;color: grey;text-align: center;">
                            Error message for 500 page is here
                        </p>
                        <a href="">
                            <button class="signinbutton">Go to home page</button>
                        </a>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>


