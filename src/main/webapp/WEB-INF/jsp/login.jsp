<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
    <head>
        <title><spring:message code="login.label" /></title>
        <c:import url="head-data.jsp"/>
        <!-- Unique css -->
        <link rel="stylesheet" type="text/css" href="css/login-styles.css">
    </head>
    <body>
        <header>
            <!-- Common menu -->
            <c:import url="main-menu.jsp"/>
            <!-- Second level menu -->
            <ul class="nav__second-lvl">
                <li class="active"><a href="login"><spring:message code="second.menu.login" /></a></li>
                <li><a href="sign-up"><spring:message code="second.menu.signup" /></a></li>
            </ul>
        </header>
        <main>
            <div class="content-card">
                <div class="content-card__container">
                    <div class="page-title"><spring:message code="login.label" /></div>
                    <div class="form__container">
                        <form class="form__http-properties" name="loginForm" action="login" method="POST"
                              onsubmit="return validateLoginForm()">
                            <!-- Fields -->
                            <p class="field-title"><spring:message code="login.username.title" />*</p>
                            <input type="text" name="username" class="input" placeholder="" maxlength="15" required=""/>


                            <p class="field-title"><spring:message code="login.password.title" />*</p>
                            <input type="password" name="password" class="input" placeholder="" maxlength="15"
                                   required=""/>

                            <c:if test="${param.error != null}">
                                <p class="errors">
                                    <spring:message code="error.login.badcredentials" />
                                </p>
                            </c:if>

                            <c:if test="${param.msg != null}">
                                <h4 style="text-align: center; color: green"><spring:message code="login.success.msg" /></h4>
                            </c:if>

                            <!-- Button -->
                            <button type="submit" class="signinbutton" value="login"><spring:message code="login.label" /></button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <script src="js/jquery/jquery-3.4.1.min.js"></script>
        <script src="js/jquery/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/validate-forms-script.js"></script>
    </body>
</html>


