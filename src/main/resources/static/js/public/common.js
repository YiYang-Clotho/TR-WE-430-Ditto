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
});

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