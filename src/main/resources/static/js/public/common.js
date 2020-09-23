 function getTokenHeader() {
    let token = getToken();
     console.log("get header!")
    // token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaWFuZyIsImV4cCI6MTU4NzIyNDM0NSwiaWF0IjoxNTg3MjE3MTQ1fQ.GvHmecWhnJSQp5pq9ccGLOok1DmYle55u3k7ZcJSvyJJAfb9BZushjvKa6vEdwLQvcmIBpKu0QrINUZIkvD_rQ"
    if (!isTokenNull()) {
        token = "Bearer-" + token;
        headers = { Authorization: token };
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
 console.log({headers})
 var instance = axios.create({
     timeout: 10000,
     headers ,
 });