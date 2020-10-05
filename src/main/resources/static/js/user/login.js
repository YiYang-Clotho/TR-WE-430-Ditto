
function handleLoginBtn() {
    $(".d-none").removeClass("d-none")
    axios.post("/api/login", {

        username: $("#username").val(),
        password: $("#password").val(),

    }).then(res => {
        console.log(res)
        bootbox.alert("Login Successfully!")
        window.location.replace("/");
    }).catch(err => {
        // eslint-disable-next-line no-undef
        bootbox.alert("Login Failed!")
        console.log(err)
    })
}