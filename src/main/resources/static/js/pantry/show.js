let index = 0

$("#input-ingredient").keydown(function (e) {

    let val = $("#input-ingredient").val().trim()
    let size = $('#div-ingredient').children().length
    size++

    if (e.key === "Enter" && val.length !== 0) {
        let html = ` <span class=" bg-success m-3 rounded-50  text-white" id="ingred-${size}" > <span class="ml-3 ingred" >${val}</span>  <button class="btn iconfont icon-x"" onclick="delIngredient(${size})" ></button></span>`
        $("#div-ingredient").append(html)
        $("#input-ingredient").val('')
        handleSearchBtn()
    }


});

function getIngredients() {
    let ingredients = new Set();

    $('.ingred').each(function () {
        ingredients.add($(this).text().trim())
    })
    return [...ingredients]
}

function clearIngredients() {
    let div = document.getElementById("div-ingredient");
    while (div.hasChildNodes()) {
        div.removeChild(div.firstChild);
    }

}

function delIngredient(index) {
    $(`#ingred-${index}`).remove()
    handleSearchBtn()
}

function handleSearchBtn() {
    console.log(getIngredients())
    loading();
    axios.post("/api/pantry", {
        keywords: getIngredients(),
    }).then(res => {
        completeLoading()
        console.log(res)
        let recipes = res['data']['data']
        const div = document.getElementById("cardDiv")
        let html = generateRecipeCard(recipes);
        if (recipes.length === 0) {
            html = `<h2>No result...</h2>`
        }
        $(div).html(html)
        //window.location.replace("/tags");
        //set img size
        cutPicture()
    }).catch(err => {
        completeLoading()
        // eslint-disable-next-line no-undef
        bootbox.alert("Cannot find the corresponding recipe")
        console.log(err)
    })
}

function cutPicture() {
    $('img.rounded-20').each(function (i) {
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
}

function clearAllResults() {
    const div = document.getElementById("cardDiv")
    $(div).html('');
}