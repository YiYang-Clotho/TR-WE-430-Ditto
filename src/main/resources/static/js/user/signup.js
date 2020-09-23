function signUp() {
    bootbox.dialog({
        message: '<div class="text-center"><i class="fa fa-spin fa-spinner"></i> Loading...</div>',
        closeButton: false
    })
    axios.post("/api/register", {
        username: $("#username").val(),
        password: $("#password").val()
    }).then(res => {
        console.log(res)
        window.location.replace("/login")
    }).catch(err => {

        bootbox.alert("Register Failed")
        console.log(err)
    })
}