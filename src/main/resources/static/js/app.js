let title = document.getElementById("title");

let typewriter = new Typewriter(title, {
    loop: true
});

typewriter.typeString("Welcome to <span class='text-color'>RIT Library</span> !")
    .pauseFor(4500)
    .deleteAll()
    .start();

let navigation = document.getElementById('nav');

console.log(window.innerWidth);

function changeNavbar(){
    if(window.innerWidth < 980) {
        navigation.classList.add("bg-dark");
        navigation.classList.remove("bg-transparent");
    }
    else{
        navigation.classList.add("bg-transparent");
        navigation.classList.remove("bg-dark");
    }
};