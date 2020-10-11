let index = 0

$("#input-ingredient").keydown(function (e) {

    let val = $("#input-ingredient").val().trim()
    let size = $('#div-ingredient').children.length
    size++
    if (e.key === "Enter" && val.length !== 0) {
        // $("#按钮id").click();
        let html = ` <span class=" bg-success m-3 rounded-50  text-white" id="ingred-${size}" > <span class="ml-3 ingred" >${val}</span>  <button class="btn iconfont icon-x"" onclick="delIngredient(${size})" ></button></span>`
        $('#div-ingredient').append(html)
    }


});
function getIngredients() {
    let ingredients=[]
    $('.ingred').each(function (){
        ingredients.push($(this).val().trim())
    })
    return ingredients
}
function clearIngredients() {
    let div = document.getElementById("div-ingredient");
    while(div.hasChildNodes())
    {
        div.removeChild(div.firstChild);
    }
}
function delIngredient(index) {
    $(`#ingred-${index}`).remove()
}
function search() {
//    save ingredient
    axios.post('/api/pantry',{
        ingredients:getIngredients()
    }).then(res => {
        console.log(res)

    }).catch(err => {

        // bootbox.alert("Register Failed")
        console.log(err)
    })

//    search
}