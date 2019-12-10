function removeMovieSessionHandler(movieSessionId) {
    var sessionsIds = [movieSessionId];
    var path = $('#context').val();
    console.log(JSON.stringify(sessionsIds));
    $.ajax({
        type: 'delete',
        url: '/movie-session?param='+ sessionsIds,
    }).done(function (resp) {
        window.location.replace(path + '/schedule');
    }).fail(function (jqXHR) {
        var msg = jqXHR.responseText;
        alert(msg);
        console.log(jqXHR.status + ' ' + jqXHR.responseText);
    });
}