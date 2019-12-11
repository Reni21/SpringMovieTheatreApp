<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <title><spring:message code="signup.label"/></title>
        <c:import url="head-data.jsp"/>
        <!-- Unique css -->
        <link rel="stylesheet" href="css/login-styles.css">
    <body>
        <header>
            <!-- Common menu -->
            <c:import url="main-menu.jsp"/>
            <!-- Second level menu -->
            <ul class="nav__second-lvl">
                <li><a href="login"><spring:message code="second.menu.login"/></a></li>
                <li class="active"><a href="sign-up"><spring:message code="second.menu.signup"/></a></li>
            </ul>
        </header>


        <main>
            <div class="content-card">
                <div class="content-card__container">
                    <div class="page-title"><spring:message code="signup.label"/></div>


                    <div class="form__container">
                        <form:form id="signUpForm" cssClass="form__http-properties" modelAttribute="signUpForm"
                                   action="sign-up" method="POST">

                            <!-- Fields -->

                            <p class="name_username field-title"><spring:message code="signup.username.title" />*
                                <i style="font-weight: normal; font-size: 13px">(<spring:message code="signup.username.rules" />)</i></p>
                            <form:input id="username" type="text" path="username" class="input" data-error="username"/>
                            <form:errors path="username" cssClass="errors" element="p"/>
                            <p id="err_username" class="errors" style="color: red;"></p>


                            <p class="password_username field-title"><spring:message code="signup.password.title" />* <i
                                    style="font-weight: normal; font-size: 13px">(<spring:message code="signup.password.rules" />)</i></p>
                            <form:input id="password" type="password" path="password" class="input"
                                        data-error="password"/>
                            <form:errors path="password" cssClass="errors" element="p"/>
                            <p id="err_password" class="errors" style="color: red;"></p>

                            <p class="email_username field-title"><spring:message code="signup.email.title" />*</p>
                            <form:input id="email" type="email" path="email" class="input" data-error="email"/>
                            <form:errors path="email" cssClass="errors" element="p"/>
                            <p id="err_email" class="errors" style="color: red;"></p>

                            <!-- Button -->
                            <button type="submit" class="signinbutton" value="sign-up">
                                <spring:message code="signup.label"/></button>
                        </form:form>
                    </div>
                </div>
            </div>
        </main>
        <script src="js/jquery/jquery-3.4.1.min.js"></script>
        <script src="js/jquery/jquery.validate.min.js"></script>
        <script type="text/javascript" src="js/validate-forms-script.js"></script>
    </body>
</html>


