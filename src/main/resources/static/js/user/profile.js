let formData = new FormData()

function uploadAvatar(obj) {
    let file = document.getElementById("input-avatar")
    let reader = new FileReader()

    reader.readAsDataURL(file.files[0])
    reader.onload = function (e) {
        document.getElementById("img-avatar").src = this.result;
    }
    formData.append(`avatar`, file.files[0])
}

function saveProfile() {
    let username = $("#username").val().trim();
    let oldPassword = $("#oldPassword").val().trim();
    let newPassword = $("#newPassword").val().trim();

    if (username.length === 0) {
        bootbox.alert("Username cannot be blank")
        return
    }
    let data = {
        userId, username, newPassword, oldPassword
    }

    formData.append("data", JSON.stringify(data))
    bootbox.confirm("Are you sure you want to update the profile?", function (result) {

        if (result) {
            loading()
            axios.put('/api/profile', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(res => {
                completeLoading()
                console.log(res)
                bootbox.alert("Successfully!")
                window.location.replace("/user/kitchen");
            }).catch(err => {
                completeLoading()
                bootbox.alert("Failed")
                console.log(err)
            })
            formData = new FormData()
        }
    })
}