<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html lang="en">
    <head>
        <title><spring:message code="tickets.label" /></title>
        <c:import url="head-data.jsp"/>
        <!-- Unique css -->
        <link rel="stylesheet" type="text/css" href="css/schedule-admin-styles.css">
    </head>
    <body>
        <header>
            <!-- Common menu -->
            <c:import url="main-menu.jsp"/>
        </header>
        <main>
            <c:set value="${requestScope['bookings']}" var="bookings"/>
            <c:if test="${bookings == null || bookings.isEmpty()}">
                <h2 style="text-align: center; color: gray; padding-top: 150px;">
                    <spring:message code="tickets.empty" /></h2>
            </c:if>
            <c:if test="${param.error != null}">
                <h2 style="text-align: center; color: red; padding-top: 50px;">
                    <spring:message code="tickets.error" /></h2>
            </c:if>

            <c:if test="${bookings!= null && !bookings.isEmpty()}">
                <c:forEach items="${bookings}" var="booking">
                    <div class="movie-card">
                        <div class="movie-card__container">
                            <div class="movie-description">
                                <div class="movie-cover"/>
                                <h2 class="booking_number">№${booking.bookingId}</h2>
                            </div>
                            <div class="movie-title">${booking.movieName}</div>
                            <p class="movie-duration"><spring:message code="tickets.duration" />: ${booking.movieDuration}min</p>
                            <h3>| <spring:message code="tickets.date" />: ${booking.getFormattedDate()} &nbsp;&nbsp;&nbsp;&nbsp;| <spring:message code="tickets.start" />: ${booking.getTimeView()} &nbsp;&nbsp;&nbsp;&nbsp;| <spring:message code="tickets.hall" />: ${booking.hallName} &nbsp;&nbsp;&nbsp;&nbsp;| <spring:message code="tickets.row" />: ${booking.row}&nbsp;&nbsp;&nbsp;&nbsp;| <spring:message code="tickets.seat" />: ${booking.place}</h3>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </main>
    </body>
</html>


