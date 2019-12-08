var moviesToPin = new Map();
// Open modal to add movie card for admin-schedule
$('.openModalBtn').click(function () {
    // Get all movies from server with ajax
    $.get("movies-preview")
        .done(function (resp) {
            var movies = resp;
            var count = 0;
            $.each(movies, function (index, movie) {
                var mainHtmlId = 'movie_' + movie.movieId;
                if ($("#" + mainHtmlId).length === 0) {
                    // Get movie that can be pined
                    var link = '<label class="container">' + movie.title +
                        '<input class="remember" type="checkbox" name="movie_ids" value="' + movie.movieId + '" id="checkbox_' + movie.movieId + '"/>' +
                        '<span class="checkmark"></span></label>';
                    $('#selectedMovies').append($(link));
                    count++;
                    moviesToPin.set(movie.movieId, movie);
                }
            });
            if (count === 0) {
                var html = '<p style="text-align: center; margin-top: 150px; color: grey; font-weight: 600">All movies are already pined</p>';
                $('#selectedMovies').append($(html));
            }
        })
        .fail(function (jqXHR) {
            alert("error");
        });
    $('#myModal').css('display', 'block');
});
// When the user clicks close button, close the modal
$('.close').click(function () {
    $('#myModal').css('display', 'none');
    $("#selectedMovies").empty();
    moviesToPin.clear();
});

// When the user clicks anywhere outside of the modal delete all error msg
window.onclick = function () {
    var errorsDivs = $('div.errors');
    if (errorsDivs.text().length !== 0) {
        errorsDivs.html('');
    }
};

// Admin-schedule forms handler
function submitFormHandler(event) {
    var $form = $(this);
    if ($form.attr('id') === 'selectedMovies') {

        // Pin movie card to admin-schedule page without call server 1
        pinMovie($form, event, moviesToPin, submitFormHandler);
        $('#myModal').css('display', 'none');
        $("#selectedMovies").empty();
        moviesToPin.clear();
    } else {

        // Create new movie session for selected movie with ajax, validation part 1
        var movieId = $form.attr('id').split("_")[1];
        $($form).validate({ // initialize the plugin
            rules: {
                hours: {
                    required: true,
                    range: [9, 22],
                    digits: true
                },
                minutes: {
                    required: true,
                    range: [0, 59],
                    digits: true
                },
                price: {
                    required: true,
                    number: true,
                    min: 0
                }
            },
            messages: {
                hours: {
                    required: "| " + errorsDictionary.get('hoursRequired'),
                    range: jQuery.validator.format("| " + errorsDictionary.get('hoursRange')),
                    digits: "| " + errorsDictionary.get('hoursDigits'),
                    maxlength: jQuery.validator.format("| " + errorsDictionary.get('hoursMaxlength'))
                },
                minutes: {
                    required: "| " + errorsDictionary.get('minutesRequired'),
                    range: jQuery.validator.format("| " + errorsDictionary.get('minutesRange')),
                    digits: "| " + errorsDictionary.get('minutesDigits'),
                    maxlength: jQuery.validator.format("| " + errorsDictionary.get('minutesMaxlength'))
                },
                price: {
                    required: "| " + errorsDictionary.get('priceRequired'),
                    number: "| " + errorsDictionary.get('priceNumber'),
                    min: "| " + errorsDictionary.get('priceMin')
                }
            },
            errorLabelContainer: '#errors_' + movieId
        });

        if (!$form.valid()) {
            return false;
        }
        createAndDisplayNewMovieSession($form, event);
    }
}

// Create new movie session for selected movie with ajax 2
function createAndDisplayNewMovieSession(form, event) {
    var movieId = form.attr('id').split("_")[1];
    //reject default handler for submit button
    event.preventDefault();

    $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: form.serialize()
    }).done(function (resp) {
        var movieSessionTimeDto = resp;
        console.log(movieSessionTimeDto);
        form.find('input[name="hours"]').val('');
        form.find('input[name="price"]').val('');
        form.find('input[name="minutes"]').val('');
        var link = '<a class="tag" href="movie-session/' + movieSessionTimeDto.movieSessionId + '" id="' + movieSessionTimeDto.movieSessionId + '">' + movieSessionTimeDto.timeView + '</a>';
        $('#movie_' + movieId).append($(link));
    }).fail(function (jqXHR) {
        var status = jqXHR.status;
        console.log(status);
        if (status === 400) {
            var respText = jqXHR.responseText;
            var json = JSON.parse(respText);
            var arrayLength = json.length;
            for (var i = 0; i < arrayLength; i++) {
                var msg = json[i];
                var er = '<span class="error">' + '| ' + msg + '</span>';
                $('#errors_' + movieId).html(er);
            }
        } else {
            location.href = status + "-error";
        }
        $('.errors').css('display', 'block');
        console.log(jqXHR.responseText);
    });
    //reject default handler for submit button
    event.preventDefault();
}

$(function () {
    $('#selectedMovies').submit(submitFormHandler);
    $('form.session-form').submit(submitFormHandler);
});