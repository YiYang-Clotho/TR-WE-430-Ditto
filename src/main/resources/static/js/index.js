var light = document.getElementById("light");
var solid = document.getElementById("solid");

//change it to solid
light.onclick=function(){
this.style.display = 'none';
solid.style.display = 'inline';

}
//change it to light
solid.onclick=function(){
this.style.display = 'none';
light.style.display = 'inline';
}