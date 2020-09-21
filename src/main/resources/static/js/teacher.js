$(document).ready(function () {
    $.getJSON('/myprofile', function (data) {
        $('#login').text(data.email);
        $('#name').text(data.name);
        $('#role').text(data.role);
        $("#avatar").attr("src", data.pictureURL);
    });

    loadPages();
    loadData(0);
});


function loadPages() {
    $("#pages").empty();
    $.getJSON('/teacher/count', function (data) {
        var pageCount = (data.count / data.pageSize) +
            (data.count % data.pageSize > 0 ? 1 : 0);
        for (var i = 1; i <= pageCount; i++) {
            $('#pages').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i - 1)
                        .append('Page ' + i))
            );
        }
    });
    $("#pages").on("click", ".page-link", function (event) {
        loadData(event.target.id);
    });
}

function loadData(page) {
    $("#data > tbody").empty();
    $.getJSON('/teacher/allteacher?page=' + page, function (data) {
        var i;
        for (i = 0; i < data.length; i++) {
            $('#data > tbody:last-child').append(
                $('<tr>')
                    .append($('<td>').append(data[i].name))
                    .append($('<td>').append(data[i].email))
                    .append($('<button onclick="reply_click(this.value)" > ')
                        .attr('id', i)
                        .attr('value', data[i].email)
                        .append('Record'))
            );
        }
    });
}

