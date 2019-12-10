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
        <div id="js-particles" style="position: fixed;width: 100%;height: auto;z-index: 0;"></div>
        <header>
        </header>
        <main>
            <div style="z-index: 1;">
                <div style="margin: 0 auto;width: 960px;height: auto;position: relative;">
                    <div class="page-title" style="font-size: 125px;margin-bottom: 35px">500</div>
                    <div class="page-title">SORRY! NOT FOUND</div>
                    <div class="form__container">
                        <p class="error-msg" style="font-size: 18px;font-weight: 600;color: grey;text-align: center;">
                            Error message for 500 page is here
                        </p>
                        <a href="">
                            <button class="signinbutton">Home page</button>
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <script src='/js/particlesmin.js'></script>
        <script type="text/javascript" src="/js/particles.js"></script>
    </body>
</html>


