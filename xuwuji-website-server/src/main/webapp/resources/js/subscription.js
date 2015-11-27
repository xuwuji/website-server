function subscript() {
    var email_address = $('#subscript_InputEmail').val();
    Date.now = function() {
        return new Date().getTime();
    }
    var subscript_time = Math.floor(Date.now() / 1000);
    var subscription_data = {
        "email_address": email_address,
        "subscript_time": subscript_time,
    };
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: subsript_url,
        data: subscription_data,
        dataType: "json"
    });
}
