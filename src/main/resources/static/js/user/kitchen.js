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
    bootbox.confirm("Are you sure you want to delete this recipe?", function (result) {
        if (result) {
            loading()
            axios.delete('/api/recipe',{
                params: {
                    recipeId:id
                }
            }).then(res=>{
                completeLoading()
                console.log(res)
                window.location.reload()
            }).catch(err => {
                completeLoading()
                bootbox.alert("Delete Failed")
                console.log(err)
            })
        }
    })
}