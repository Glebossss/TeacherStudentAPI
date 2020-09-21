$(document).ready(function () {
    $.getJSON('/myprofile', function (data) {
        $('#login').text(data.email);
        $('#name').text(data.name);
        $('#role').text(data.role);
        $("#avatar").attr("src", data.pictureURL);
    });
});
