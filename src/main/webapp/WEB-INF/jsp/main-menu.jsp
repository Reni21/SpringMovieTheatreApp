<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:useBean id="activeTab" scope="request" type="java.lang.String"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd"/>

<html lang="en">
    <head>
    </head>
    <body>
        <nav class="nav__first-lvl">
            <div class="wrapper">
                <table class="first-lvl__bar-table">
                    <tr>
                        <td>
                            <ul class="first-lvl__main-menu">
                                <c:set var="name" value='${activeTab}'/>
                                <li <c:if test="${name == 'main'}">class="active"</c:if>><a href=""><spring:message
                                        code="main.menu.home"/></a>
                                </li>
                                <li <c:if test="${name == 'schedule'}">class="active"</c:if>><a
                                        href="schedule?date=${currentDate}"><spring:message code="main.menu.schedule"/></a>
                                </li>
                                <c:set var="name" value="${activeTab}"/>
                                <li <c:if test="${name == 'account'}">class="active"</c:if>>
                                    <c:set var="user" value="${sessionScope['user']}"/>
                                    <a href="${user == null ? 'login' : 'account/'.concat(user.getLogin())}">
                                <fmt:message key="main.menu.account"/></a>
                                </li>

                                <c:if test="${not empty user}">
                                    <li ><a href="logout">Log out</a>
                                    </li>
                                </c:if>
                            </ul>
                        </td>

                        <td class="lang__switcher">
                            <ul class="first-lvl__lang-menu">
                                <div>locale is ${pageContext.response.locale}</div>
                                <li <c:if test="${pageContext.response.locale == 'en'}">class="active"</c:if>>
                                    <a href="?locale=en">EN</a>
                                </li>
                                <li <c:if test="${pageContext.response.locale == 'ru'}">class="active"</c:if>>
                                    <a href="?locale=ru">RU</a>
                                </li>

                            </ul>
                        </td>
                    </tr>
                </table>
            </div>
        </nav>
    </body>
</html>