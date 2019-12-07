function validateLoginForm() {
    var username = document.forms["loginForm"]["username"].value;
    var password = document.forms["loginForm"]["password"].value;
    if (username == null || username == "", password == null || password == "") {
        alert("Please Fill All Required Field");
        return false;
    }
}

$(document).ready(function () {
    jQuery.validator.addMethod("match", function(value, element, param) {
        return value.match(new RegExp("." + param + "$"));
    });

    $('#signUpForm').validate({ // initialize the plugin
        rules: {
            username: {
                required: true,
                maxlength: 15,
                match: "[a-zA-Z0-9]+"
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 20
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            username: {
                required: "| " + "Username field is required",
                maxlength: jQuery.validator.format("| Max number of characters is 15"),
                match:  "| " + "Use only english alphabet and numbers"
            },
            password: {
                required: "| " + "Password by field is required",
                minlength: jQuery.validator.format("| Min number of characters for password is 5"),
                maxlength: jQuery.validator.format("| Max number of characters is 15")
            },
            email: {
                required: "| " + "Email field is required",
                email: jQuery.validator.format("| Input correct email")
            },
        },
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            $('#err_' + placement).html(error);
        }
    });

    if (!form.valid()) {
        return false;
    }
});
window.onclick = function () {
    var errorsP = $('p.errors');
    if (errorsP.text().length !== 0) {
        errorsP.html('');
    }
};
