function removeMovieSessionHandler(movieSessionId) {
    var id = parseInt(movieSessionId, 10)
    var sessionsIds = [id];
    var path = $('#context').val();
    console.log(JSON.stringify(sessionsIds));
    $.ajax({
        type: 'post',
        url: '/delete-session',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(sessionsIds)
    }).done(function (resp) {
        window.location.replace(path + '/schedule');
    }).fail(function (jqXHR) {
        var msg = jqXHR.responseText;
        alert(msg);
        console.log(jqXHR.status + ' ' + jqXHR.responseText);
    });
}