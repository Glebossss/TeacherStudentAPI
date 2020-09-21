
$(document).ready(function(){
    $.getJSON('/myprofile', function(data) {
        $('#login').text(data.email);
        $('#name').text(data.name);
        $('#role').text(data.role);
        $("#avatar").attr("src", data.pictureURL);
    });
});




let emails = localStorage.getItem('email');
$("#data > tbody").empty();
$.getJSON('/calendar/' + emails + '/all/', function (data) {
    var i;
    for (i = 0; i < data.length; i++) {
        $('#data > tbody:last-child').append(
            $('<tr>')
                .append($('<td>').attr('id', 'de' + i).attr('value', data[i].dataEnd).append(data[i].dataEnd))
                .append($('<td>').attr('id', 'ds' + i).attr('value', data[i].dateStart).append(data[i].dateStart))
                .append($('<td>').attr('id', 'm' + i).attr('value', data[i].money).append(data[i].money))
                .append($('<td>').attr('id', 't' + i).attr('value', data[i].time).append(data[i].time))
                .append($('<button onclick="record(this.value)" > ')
                    .attr('id', i)
                    .attr('value', data[i].id)
                    .append('Record'))
        );
    }
});