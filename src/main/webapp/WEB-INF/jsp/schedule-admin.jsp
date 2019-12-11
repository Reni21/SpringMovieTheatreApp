<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:useBean id="sessions" scope="request" type="java.util.List"/>

<html lang="en">
<head>
    <title><spring:message code="schedule.label" /></title>
    <c:import url="head-data.jsp"/>
    <!-- Unique css -->
    <link rel="stylesheet" type="text/css" href="css/schedule-admin-styles.css">
    <link rel="stylesheet" type="text/css" href="css/modal-styles.css">
</head>
<body>
<header>
    <!-- Common menu -->
    <c:import url="main-menu.jsp"/>

    <!-- Second level menu -->
    <ul class="nav__second-lvl">
        <c:forEach items="${menuDates}" var="menuDate">
            <li <c:if test="${menuDate.isActive()}">class="active"</c:if>>
                <a href="/schedule${menuDate.getIsoDate()}">
                    <spring:message code="week.day.${menuDate.getDayOfWeek()}"/>
                        ${menuDate.getFormattedDate()}</a></li>
        </c:forEach>
    </ul>
</header>
<main>
    <!-- Button for call modal -->
    <div class="wrapper btn">
        <button type="submit" class="openModalBtn add-movie"><spring:message code="admin.pin.movie"/></button>
    </div>

    <!-- - - - - - - - The Modal for movies - - - - - - - -->
    <div id="myModal" class="modal">
        <div class="content">
            <div class="page-title"><spring:message code="schedule.admin.modal.title" /></div>
            <!-- Modal content -->
            <div class="modal-content">
                <form id="selectedMovies" method="post">
                </form>
            </div>

            <!-- Modal buttons -->
            <button id="btnCheckout" name="confirm" class="signinbutton" type="submit" form="selectedMovies">
            <spring:message code="schedule.admin.modal.select" />
            </button>
            <button class="close"><spring:message code="schedule.admin.modal.close" /></button>
        </div>
    </div>
    <!-- - - - - - - - The Modal for movies - - - - - - - -->

    <c:forEach items="${sessions}" var="movie">
        <div id="${movie.movieId}">
            <div class="wrapper">
                <div id="errors_${movie.movieId}" class="errors"
                     style="font-size: 15px;color: red;margin: 0 auto;position: relative;"></div>
            </div>
            <div class="movie-card">
                <div class="movie-card__container" style="margin-bottom: 10px;">
                    <div class="movie-cover">
                        <!-- Delete pin movie button -->
                        <button class="btn delete" onclick="removePinHandler('${movie.movieId}')">
                            <spring:message code="schedule.admin.unpin.movie" />
                        </button>
                    </div>


                    <div id="movie_${movie.movieId}" class="movie-description">
                        <div class="movie-title">${movie.title}</div>
                        <p class="movie-duration"><spring:message code="schedule.duration"/>: ${movie.duration}
                            <fmt:message
                                    key="schedule.min"/></p>
                        <form action="movie-session?movieId=${movie.movieId}<c:if test="${param.get('date') != null}">&date=${param.get('date')}</c:if>"
                              class="session-form" name="session-form" id="session-form_${movie.movieId}"
                              method="post">
                            <input id="hours_${movie.movieId}" class="session-field" type="number" name="hours"
                                   placeholder="<spring:message code="admin.input.hh"/>" min="9" max="22"
                                   style="padding: 8px 5px">
                            <input id="minutes_${movie.movieId}" class="session-field" type="number" name="minutes"
                                   placeholder="<spring:message code="admin.input.mm"/>" min="0" max="59"
                                   style="padding: 8px 5px">
                            <input class="session-field" type="text" name="price"
                                   placeholder="<spring:message code="admin.input.price"/> 0.0" style="padding: 8px 5px">
                            <input type="submit" form="session-form_${movie.movieId}" value="<spring:message code="admin.add.session"/>" class="add">
                        </form>
                        <c:forEach items="${movie.movieSessionTimes}" var="time">
                            <a class="tag" href="movie-session/${time.getMovieSessionId()}"
                               id="${time.getMovieSessionId()}">${time.getTimeView()}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</main>
<script src="js/jquery/jquery-3.4.1.min.js"></script>
<script src="js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/pin-movie-schedule.js"></script>
<script type="text/javascript" src="js/movies-modal-session-creation.js"></script>
<script type="text/javascript">
    var errorsDictionary = new Map([
        ["hoursRequired", "<spring:message code='error.hours.required'/>"],
        ["hoursRange", "<spring:message code='error.hours.range'/>"],
        ["hoursDigits", "<spring:message code='error.hours.digits'/>"],
        ["hoursMaxlength", "<spring:message code='error.hours.maxlength'/>"],
        ["minutesRequired", "<spring:message code='error.minutes.required'/>"],
        ["minutesRange", "<spring:message code='error.minutes.range'/>"],
        ["minutesDigits", "<spring:message code='error.minutes.digits'/>"],
        ["minutesMaxlength", "<spring:message code='error.minutes.maxlength'/>"],
        ["priceRequired", "<spring:message code='error.price.required'/>"],
        ["priceNumber", "<spring:message code='error.price.number'/>"],
        ["priceMin", "<spring:message code='error.price.min'/>"]
    ]);
    var msgDictionary = new Map([
        ["duration", "<spring:message code="schedule.duration"/>"],
        ["min", "<spring:message code="schedule.min"/>"],
        ["mm", "<spring:message code="admin.input.mm"/>"],
        ["hh", "<spring:message code="admin.input.hh"/>"],
        ["price", "<spring:message code="admin.input.price"/>"],
        ["session", "<spring:message code="admin.add.session"/>"],
        ["empty", "<spring:message code="admin.movies.modal.empty"/>"],
        ["unpin", "<spring:message code="schedule.admin.unpin.movie"/>"]
    ]);
</script>
</body>
</html>


