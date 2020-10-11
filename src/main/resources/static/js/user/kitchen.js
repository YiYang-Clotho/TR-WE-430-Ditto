$("img.rounded-20").each(function (i) {
    $(this).jqthumb({
        width: '100%',//
        height: '200px',//
        zoom: '1',//
        method: 'auto',
        after: function (obj) {
            $(obj).css("border-radius", "20px")
        }
    });
})

function deleteRecipe(id) {
    bootbox.confirm("Are you sure you want to submit this recipe?", function (result) {
        if (result) {
            axios.delete('/api/recipe',{
                params: {
                    recipeId:id
                }
            }).then(res=>{
                console.log(res)
                window.location.reload()
            }).catch(err => {
                bootbox.alert("Delete Failed")
                console.log(err)
            })
        }
    })
}