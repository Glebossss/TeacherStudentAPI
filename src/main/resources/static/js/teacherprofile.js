$(document).ready(function () {
    $.getJSON('/teacher', function (data) {
        var defaults = 'default';
        var email = data.email;
        $('#login').text(data.email);
        $('#name').text(data.name);
        $('#price').attr("value", data.price);
        $("#avatar").attr("src", data.pictureURL);
        if (data.subjectEntity == null) {
            $('#default').text('default');
            $('#nameSubject').text('default');
        } else {
            $('#nameSubject').text(data.subjectEntity.name);
            $('#default').text(data.subjectEntity.name);
        }
        $('#test').append(
            $.getJSON('/subject/all', function (data) {
                var pageCount = data.length;
                for (var i = 0; i <= pageCount; i++) {
                    $('#test').append(
                        $('<option>')
                            .attr('class', 'page-item')
                            .text(data[i].name)
                    );
                }
            })).attr('name', data.subjectEntity.name);

        loadPages(email);
        loadData(email,0);
    });
});


function loadPages(email) {
    $("#pages").empty();
    $.getJSON('/calendar/count', function (data) {
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
        loadData(email, event.target.id);
    });
}

function loadData(email, page) {
    $("#data > tbody").empty();
    $.getJSON('/calendar/' + email + '?page=' + page, function (data) {
        var i;
        for (i = 0; i < data.length; i++) {
            $('#data > tbody:last-child').append(
                $('<tr>')
                    .append($('<td>').append(data[i].dataEnd))
                    .append($('<td>').append(data[i].dateStart))
                    .append($('<td>').append(data[i].money))
                    .append($('<td>').append(data[i].time))
            );
        }
    });
}


