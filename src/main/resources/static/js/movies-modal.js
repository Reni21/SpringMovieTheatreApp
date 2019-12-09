// When the user clicks the button, open the modal
$('.openModalBtn').click(function () {
    $('#myModal').css('display', 'block');
});

// When the user clicks close button, close the modal
$('.close').click(function () {
    $('#myModal').css('display', 'none');
    $('.errors').html('');
    $('.field-title').css('display', 'block');
});

// When the user clicks anywhere remove errors
window.onclick = function () {
    var errorsDivs = $('p.errors');
    if (errorsDivs.text().length !== 0) {
        errorsDivs.html('');
        $('.field-title').css('display', 'block');
    }
};

// Create new movie for admin-movies with ajax, validation part 1
function submitMovieCreationFormHandler(event) {
    var $form = $(this);

    $($form).validate({ // initialize the plugin
        rules: {
            title: {
                required: true
            },
            directed: {
                required: true
            },
            duration: {
                required: true,
                digits: true,
                min: 0
            },
            cover_link: {
                required: true,
                url: true
            },
            bg_link: {
                required: true,
                url: true
            },
            trailer_link: {
                required: true,
                url: true
            }
        },
        messages: {
            title: {
                required: "| " + "Title field is required"
            },
            directed: {
                required: "| " + "Directed by field is required"
            },
            duration: {
                required: "| " + "Duration field is required",
                digits: "| " + "Use only digits for input",
                min: "| " + "Use only positive numbers for input"
            },
            cover_link: {
                required: "| " + "Cover link field is required",
                url: "| " + "Use only digits for input"
            },
            bg_link: {
                required: "| " + "Background link field is required",
                url: "| " + "Use valid url"
            },
            trailer_link: {
                required: "| " + "Trailer link field is required",
                url: "| " + "Use valid url"
            }
        },
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            $('#name_' + placement).css('display', 'none');
            $('#err_' + placement).html(error);
        }
    });

    if (!$form.valid()) {
        return false;
    }
    createAndDisplayNewMovie($form, event);
}

// Create new movie for admin-movies with ajax, post part 2
function createAndDisplayNewMovie(form, event) {
    let params = (new URL(document.location)).searchParams;
    let p = params.get("page");
    var page = parseInt(p);

    $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: form.serialize()
    }).done(function (resp) {
        var movie = resp;
        console.log(movie);
        $(':input').val('');
        var link = '<div class="movie-card">' +
            '<div class="movie-card__container">' +
            '<a href="' + movie.trailerUrl + '" target="_blank">' +
            '<div class="movie-cover">' +
            '<img class="play-icon" src="img/play.png" alt="cover"/>' +
            '<img class="cover-img" src="' + movie.coverImgUrl + '" alt="cover"/>\n' +
            '</div>' +
            '</a>' +
            '<div class="movie-card__background">' +
            '<img class="background-img"' +
            ' src="' + movie.backgroundImgUrl + '">' +
            '</div>' +
            '<div class="movie-description">' +
            '<div class="movie-title">' + movie.title + '</div>' +
            '<p class="movie-duration">' + 'Duration:' + movie.durationMinutes + 'min</p>' +
            '<a class="tag" href="movie?id=' + movie.movieId + '" onclick="deleteMovieHandler(' + movie.movieId + ')"' +
            ' style="border-radius: 25px;padding: 10px 25px;font-size: 18px;line-height: 28px;float: right;">Remove movie</a>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('main').append($(link));
        location.href = "/movie?page=" + page;
    }).fail(function (jqXHR) {
        var status = jqXHR.status;
        if (status === 400) {
            var json = JSON.parse(jqXHR.responseText);
            var arrayLength = json.length;
            for (var i = 0; i < arrayLength; i++) {
                var data = json[i].split("&");
                var msg = data[1];
                var placement = data[0];
                if ($('#err_' + placement).is(':empty')) {
                    $('#name_' + placement).css('display', 'none');
                    $('#err_' + placement).html("| " + msg);
                }
            }
        } else {
            alert("Something went wrong");
        }
        console.log(jqXHR.status + ' ' + msg);
    });
    //reject default handler for submit button
    event.preventDefault();
}

$(function () {
    $('form').submit(submitMovieCreationFormHandler);
});

function deleteMovieHandler(movieId) {
    let params = (new URL(document.location)).searchParams;
    let p = params.get("page");
    var page = parseInt(p);

    $.ajax({
        type: 'delete',
        url: 'movie?id=' + movieId
    }).done(function (resp) {
        $('#card_' + movieId).remove();
        var movie_card = parseInt($(".movie-card").length);
        if (movie_card === 0) {
            page = page === 1 ? 1 : page - 1;
            location.href = "/movie?page=" + page;
        } else {
            location.href = "/movie?page=" + page;
        }
    }).fail(function (jqXHR) {
        var status = jqXHR.status;
        var msg;
        if (status === 400) {
            msg = jqXHR.responseText;
        } else {
            msg = "Something went wrong";
        }
        alert(msg);
        console.log(jqXHR.status + ' ' + jqXHR.responseText);
    });

}
