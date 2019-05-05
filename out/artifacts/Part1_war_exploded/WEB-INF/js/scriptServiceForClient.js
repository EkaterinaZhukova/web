$(document).ready(function () {

    $('#clientsServices').validate({
        rules: {
            firstNameInput: {
                required: true,
                maxlength:15
            },
            secondNameInput: {
                required: true,
                maxlength: 15
            }
        },
        submitHandler: function (form) {
            if(confirm('Are you sure?')) {
                form.submit();
            }
        }
    });
});
