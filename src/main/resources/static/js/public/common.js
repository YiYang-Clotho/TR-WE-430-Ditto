function getTokenHeader() {
    let token = getToken();

    // token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaWFuZyIsImV4cCI6MTU4NzIyNDM0NSwiaWF0IjoxNTg3MjE3MTQ1fQ.GvHmecWhnJSQp5pq9ccGLOok1DmYle55u3k7ZcJSvyJJAfb9BZushjvKa6vEdwLQvcmIBpKu0QrINUZIkvD_rQ"
    if (!isTokenNull()) {
        token = "Bearer-" + token;
        headers = {Authorization: token};
        return headers;
    }
    return headers;
}

function getToken() {
    let sto = window.sessionStorage;
    return sto.getItem("accessToken");
}

function setToken(token) {
    let sto = window.sessionStorage;
    sto.setItem("accessToken", token);
}

function isTokenNull() {
    let token = getToken();
    return token === null || token === "-1";
}

function setTokenNull() {
    let sto = window.sessionStorage;
    sto.setItem("accessToken", "-1");
}

function isLogged() {
    return !isTokenNull();
}

var headers = getTokenHeader();
var instance = axios.create({
    timeout: 10000,
    headers,
});
$('textarea').each(function () {
    this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
}).on('input', function () {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
}).on('change', function () {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
});
$('textarea').change(function () {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
})

function loading(obj) {
    let html = `
            <div class="loader" id="loading">
            <div class="loader-inner">
                <div class="loader-line-wrap">
                    <div class="loader-line"></div>
                </div>
                <div class="loader-line-wrap">
                    <div class="loader-line"></div>
                </div>
                <div class="loader-line-wrap">
                    <div class="loader-line"></div>
                </div>
                <div class="loader-line-wrap">
                    <div class="loader-line"></div>
                </div>
            </div>
        </div>
    `
    $("body").append(html)
}

function completeLoading() {
    $("#loading").remove();
}

function getTagsHtml(tagList) {
    let tagHtml = ''
    let ind = 0
    for (const tag of tagList) {
        if (ind > 1) {
            break
        }
        let tagName = tag['tagName'];
        tagHtml += `              
                  <span  class=" iconfont mx-1 "> &#xe612;${tagName}</span>   
           `
        ind++
    }
    return tagHtml
}

function generateRecipeCard(recipes) {
    let html = ''
    for (const i in recipes) {
        let recipe = recipes[i]
        let title = recipe["recipeName"]
        let description = recipe['recipeDescription']
        let coverPath = recipe['coverPath']
        let author = recipe['author']
        let link = recipe['url']
        let tagList = recipe['tagList']
        let tagHtml=getTagsHtml(tagList)
        let ind = 0
        html +=
            `
                    <div class="col-sm-3 mb-3" >
                        <a href="${link}">
                            <div class="p-3  d-flex flex-column shadow-sm rounded-10 shadow-0-2-4 ">
                                <img class="rounded-20" src="${coverPath != null ? coverPath : '/images/logo.png'}" alt="">
                                <span style="font-size: 20px">${title}</span>
                                <p style="font-size: 14px;color: #6b6668; max-width: 10em; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                   ${description} </p>
                                <span class="d-flex">
                                ${tagHtml}
                         <span
                                        class="ml-auto" style="font-size: 13px" >By ${author}</span>
                            </span>
                            </div>
                        </a>
                    </div>
                `
    }

    return html
}