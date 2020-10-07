// search tag
function handleSearchBtn() {
    $(".d-none").removeClass("d-none")
    axios.post("/tags", {
        tag_name : $("#tag_name").val(),
    }).then(res => {
        console.log(res)
        let recipes = res['data']['data']
        const div = document.getElementById("cardDiv")
        var html = ''
        for(const i in recipes){
            var recipe = recipes[i]
            var title = recipe["recipeName"]
            var description = recipe['recipeDescription']
            var coverPath = recipe['coverPath']
            html +=
                `
                    <div class="container d-flex justify-content-center">
                        <div class="row w-85">
                            <div class="col-sm-3">
                            <div class="card" style="color: #C14A4A; width: 225px;  margin: center;">
                                <img src="${coverPath}" class="card-img-top" alt="">

                                <div class="card-body">
                                    <h5 class="card-title" id="title" >${title}</h5>
                                    <p class="card-text" id="description">${description}</p>
                                </div>

                                <div class="card-body">
                                    <a href="#" class="card-link"><i class="far fa-heart" style="color: #C14A4A" aria-hidden="true"></i> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                `

        }
        html +=
            `
                </div>

            `
        $(div).append(html)
        //window.location.replace("/tags");
    }).catch(err => {
        // eslint-disable-next-line no-undef
        bootbox.alert("Cannot find the corresponding recipe")
        console.log(err)
    })
}
