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
    $.getJSON('/unconfirmedactivitiesforstudent/count', function (data) {
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

function loadData(pages) {
    $("#data > tbody").empty();
    $.getJSON('/unconfirmedactivitiesforstudent?page=' + pages, function (data) {
        var i;
        for (i = 0; i < data.length; i++) {
            $('#data > tbody:last-child').append(
                $('<tr>')
                    .append($('<td>').append(data[i].dateStart))
                    .append($('<td>').append(data[i].dataEnd))
                    .append($('<td>').append(data[i].money))
                    .append($('<td>').append(data[i].student))
                    .append($('<td>').append(data[i].teacher))
                    .append($('<td>').append(data[i].time))

            );
        }
    });
}

