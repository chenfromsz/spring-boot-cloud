$(function () {
    $('#searchBtn').click(function(){
        pageaction();
    });

    pageaction();

});

var pageaction = function(){
    $.get('/data/users/search/findByNameContaining?name='+$("#name").val(),
        function(data){
        var currentData = data["_embedded"].users;
        fillData(currentData);
    });

}

function fillData(data){
    var $list = $('#tbodyContent').empty();
    $.each(data,function(k,v){
        var html = "" ;
        html += '<tr> ' +
            '<td>'+ (v.name==null?'':v.name) +'</td>' +
            '<td>'+ (v.email==null?'':v.email) +'</td>' +
            '<td>'+ (v.create==null?'': getSmpFormatDateByLong(v.create,true)) +'</td>';
        html += '<td><a class="c-50a73f mlr-6" href="javascript:void(0)" onclick="showDetail(\''+ v.name+'\')">查看</a></td>';
        html +='</tr>' ;

        $list.append($(html));
    });
}

var artdialog ;
function showDetail(name){
    $.get("./"+name,{ts:new Date().getTime()},function(data){
        art.dialog({
            lock:true,
            opacity:0.3,
            title: "查看信息",
            width:'750px',
            height: 'auto',
            left: '50%',
            top: '50%',
            content:data,
            esc: true,
            init: function(){
                artdialog = this;
            },
            close: function(){
                artdialog = null;
            }
        });
    });
}

function closeDialog() {
    artdialog.close();
}
