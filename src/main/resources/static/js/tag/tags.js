function getTags() {
    let tags = $("#tag_name").val().split(",")
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
    let tags = getTags()
    if (tags.length === 0) {
        return
    }
loading()
    await axios.post("/tags", {
        tag_name: getTags(),
    }).then(res => {
        completeLoading()
        console.log(res)
        let recipes = res['data']['data']
        const div = document.getElementById("cardDiv")
        let html = '';
        for (const i in recipes) {
            let recipe = recipes[i]
            let title = recipe["recipeName"]
            let description = recipe['recipeDescription']
            let coverPath = recipe['coverPath']
            let author = recipe['author']
            let link = recipe['url']
            html +=
                `
                    <div class="col-sm-3 mb-3" >
                        <a href="${link}">
                            <div class="p-3  d-flex flex-column shadow-sm rounded-10 shadow-0-2-4 ">
                                <img class="rounded-20" src="${coverPath != null ? coverPath : '/images/logo.png'}" alt="">
                                <span style="font-size: 20px">${title}</span>
                                <p style="font-size: 14px;color: #6b6668; max-width: 10em; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                   ${description} </p>
                                <span class="d-flex"><span
                                        class="ml-auto" style="font-size: 13px" >By ${author}</span>
                            </span>
                            </div>
                        </a>
                    </div>
                `
        }
        if (recipes.length === 0) {
            html = `<h2>No result...</h2>`
        }
        $(div).html(html)
        //window.location.replace("/tags");
        //set img size
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
$("#tag_name").keydown(function (e) {
    if (e.keyCode === 188||e.key==="Enter"){
        handleSearchBtn()
    }

});


