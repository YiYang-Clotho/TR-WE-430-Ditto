$('#btn').click(function(){
    $(this).toggleClass('active');
});

// search tag
function handleSearchBtn() {
    $(".d-none").removeClass("d-none")
    axios.post("/tags", {
        tag_name : $("#tag_name").val(),
    }).then(res => {
        console.log(res)
        window.location.replace("/tags/#tag_name");
    }).catch(err => {
        // eslint-disable-next-line no-undef
    bootbox.alert("Cannot find the corresponding recipe")
        console.log(err)
    })
}