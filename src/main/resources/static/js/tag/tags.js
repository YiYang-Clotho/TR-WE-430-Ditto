

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
            var author = recipe['author']
            var link = recipe['url']
            html +=
                `
                    <div class="col-sm-3" >
                        <a href="${link}">
                            <div class="p-3  d-flex flex-column shadow-sm rounded-10 shadow-0-2-4 ">
                                <img class="rounded-20" src="${coverPath}" alt="">
                                <span style="font-size: 20px">${title}</span>
                                <p style="font-size: 14px;color: #6b6668; max-width: 10em; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                   ${description} </p>
                                <span class="d-flex"><i class="far fa-heart" style="color: #C14A4A" aria-hidden="true"></i> <span
                                        class="ml-auto" style="font-size: 13px" >By ${author}</span>
                            </span>
                            </div>
                        </a>
                    </div>
                `

        }
        html +=
            `
                </div>

            `
        $(div).html(html)
        //window.location.replace("/tags");
         //set img size
        $('img.rounded-20').each(function (i){
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
    }).catch(err => {
        // eslint-disable-next-line no-undef
        bootbox.alert("Cannot find the corresponding recipe")
        console.log(err)
    })
}

 $('.table_block p span').each(function() {
        var words = $(this).text().length;
        if(words > 100){
            $(this).text($(this).text().slice(0,100)+"...");
        }
 });

