function getKeywords() {
    let tags = $("#keywords").val().split(",")
    let newTags = []
    for (let tag of tags) {
        if (tag.trim().length !== 0) {
            newTags.push(tag.trim())
        }

    }
    return newTags
}

// search tag
async function handleSearchBtn() {
    let request
    let option = $("#search-option").val()
    console.log(option)
    switch (option) {
        case 'default':
            request = '/api/search/all'
            break
        case 'ingredients':
            request = '/api/search/ingredients'
            break
        case 'tags':
            request = '/api/search/tags'
            break
        case 'title':
            request = '/api/search/title'
            break
    }
    let keywords = getKeywords()
    if (keywords.length === 0) {
        return
    }
    loading()
    await axios.post(request, {
        keywords: getKeywords(),
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
        cutPic()
    }).catch(err => {
        completeLoading()
        // eslint-disable-next-line no-undef
        bootbox.alert("Cannot find the corresponding recipe")
        console.log(err)
    })
}

$('.table_block p span').each(function () {
    var words = $(this).text().length;
    if (words > 100) {
        $(this).text($(this).text().slice(0, 100) + "...");
    }
});
$("#keywords").keydown(function (e) {
    if (e.keyCode === 188 || e.key === "Enter") {
        handleSearchBtn()
    }

});


function cutPic() {
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

