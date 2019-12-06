<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">
    <head>
        <title>My account</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i,800,800i&display=swap"
              rel="stylesheet">
        <!-- Common css -->
        <link rel="stylesheet" type="text/css" href="/css/common-styles.css">
        <!-- Unique css -->
        <link rel="stylesheet" type="text/css" href="/css/login-styles.css">
    </head>
    <body>
        <header>
            <!-- Common menu -->
            <c:import url="main-menu.jsp"/>
        </header>
        <main>
            <!--
            ===============================================================================================
            Форма для заполения
            ===============================================================================================
        -->
            <div class="content-card">
                <div class="content-card__container">
                    <div class="page-title">Hello ${sessionScope['user'].username}</div>
                    <div class="form__container">
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>


