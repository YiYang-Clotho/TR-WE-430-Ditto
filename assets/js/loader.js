$(document).ready(function () {    $("head").append(`


`)
  try {

    $("#headerDiv").load("../../../template/header.html");
    $("#footerDiv").load("../../../template/footer.html");
    $("a").attr("target", "_blank");
  } catch (error) {}
  // try {
  //   $("#headerDiv").load("./template/header.html");
  //   $("#footerDiv").load("./template/footer.html");
  // } catch (error) {}
});
// /template/header.html
