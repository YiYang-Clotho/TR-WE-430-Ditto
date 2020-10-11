// var light = document.getElementById("light");
// var solid = document.getElementById("solid");
//
// //change it to solid
// light.onclick=function(){
// this.style.display = 'none';
// solid.style.display = 'inline';
//
// }
// //change it to light
// solid.onclick=function(){
// this.style.display = 'none';
// light.style.display = 'inline';
// }

$("img.rounded-20").each(function (i){
    $(this).jqthumb({
        width : '100%',//
        height : '142px',//
        zoom : '1',//
        method : 'auto',
        after:function (obj) {
            $(obj).css("border-radius","20px")
        }
    });
})

