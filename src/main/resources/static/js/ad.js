$(document).ready(function(){
    $.getJSON('/admin', function(data) {
        $('#login').text("Email: " + data.email);
        $('#name').text("Name: " + data.name);
        $('#role').text("Role: " + data.role);
        $("#avatar").attr("src", data.pictureURL);
    });
});


