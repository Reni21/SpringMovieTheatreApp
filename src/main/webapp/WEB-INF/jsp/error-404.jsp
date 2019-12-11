<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isErrorPage="true" %>

<!doctype html>

<html lang="en">
    <head>
        <title><spring:message code="notfound.label" /></title>
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
                    <div class="page-title" style="font-size: 125px;margin-bottom: 35px">404</div>
                    <div class="page-title"><spring:message code="error.norfound.title" /></div>
                    <div class="form__container">
                        <p class="error-msg" style="font-size: 18px;font-weight: 600;color: grey;text-align: center;"><spring:message code="error.norfound.msg" /></p>
                        <a href="/">
                            <button class="signinbutton"><spring:message code="error.button" /></button>
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <script src='/js/particlesmin.js'></script>
        <script type="text/javascript" src="/js/error-particles.js"></script>
    </body>
</html>


