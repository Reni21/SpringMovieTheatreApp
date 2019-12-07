<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <title>Sign-up Cinema World</title>
        <c:import url="head-data.jsp"/>
        <!-- Unique css -->
        <link rel="stylesheet" href="css/login-styles.css">
    <body>
        <header>
            <!-- Common menu -->
            <c:import url="main-menu.jsp"/>
            <!-- Second level menu -->
            <ul class="nav__second-lvl">
                <li><a href="login">LOGIN</a></li>
                <li class="active"><a href="sign-up">SIGN UP</a></li>
            </ul>
        </header>


        <main>
            <!--
            ===============================================================================================
            Форма для заполения
            ===============================================================================================
        -->
            <div class="content-card">
                <div class="content-card__container">
                    <div class="page-title">Sign up</div>


                    <div class="form__container">
                        <form:form id="signUpForm" cssClass="form__http-properties" modelAttribute="signUpForm" action="sign-up" method="POST">

                            <!-- Fields -->

                            <p class="name_username field-title">Username*</p>
                            <form:input id="username" type="text" path="username" class="input" data-error="username"/>
                            <form:errors path="username" cssClass="errors" element="p"/>
                            <p id="err_username" class="errors" style="color: red;"></p>


                            <p class="password_username field-title">Password*</p>
                            <form:input id="password" type="password" path="password" class="input" data-error="password"/>
                            <form:errors path="password" cssClass="errors" element="p"/>
                            <p id="err_password" class="errors" style="color: red;"></p>

                            <p class="email_username field-title">Email*</p>
                            <form:input id="email" type="email" path="email" class="input" data-error="email"/>
                            <form:errors path="email" cssClass="errors" element="p"/>
                            <p id="err_email" class="errors" style="color: red;"></p>

                            <!-- Button -->
                            <button type="submit" class="signinbutton" value="sign-up">Sign up</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </main>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/validate-forms-script.js"></script>
    </body>
</html>


