
//console.log("Hello from the script");



//let f = document.getElementById("gameForm");


//READ
const display = document.querySelector("#display");
const output = document.getElementById("output");//??
const getGames = async () => {
    const response = await axios.get("/games/");
    // display.innerHTML = "";
    output.innerHTML = ""; //??
    console.log(response);
    response.data.forEach(game => console.log(game));
    response.data.forEach(game => showGame(game));
}

const showGame = ({ id, name, genre, progress, platform }) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const nameText = document.createElement("p");
    nameText.className = "card-text";
    nameText.innerText = `Name: ${name}`;
    cardBody.appendChild(nameText);

    const genreText = document.createElement("p");
    genreText.className = "card-text";
    genreText.innerText = `Genre: ${genre}`;
    cardBody.appendChild(genreText);

    const completedText = document.createElement("p");
    completedText.className = "card-text";
    completedText.innerText = `Status: ${progress}`;
    cardBody.appendChild(completedText);

    // const platformText = document.createElement("p");
    // platformText.className = "card-text";
    // platformText.innerText = `Platform: ${platform}`;
    // cardBody.appendChild(platformText);

    const cardFooter = document.createElement("div");
    cardFooter.className = "card-footer";
    card.appendChild(cardFooter);

    const updateButton = document.createElement("a");
    updateButton.innerText = "Update";
    updateButton.className = "card-link";
    updateButton.addEventListener("click", function () {
        updateGame(id);
    });
    cardFooter.appendChild(updateButton);

    const deleteButton = document.createElement("a");
    deleteButton.innerText = "Delete";
    deleteButton.className = "card-link";
    deleteButton.addEventListener("click", function () {
        deleteGame(id);
    });
    cardFooter.appendChild(deleteButton);

    output.appendChild(column);

}
getGames();

//CREATE
document.querySelector("#gameForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        name: this.gameName.value,
        genre: this.genre.value,
        progress: this.progress.value,
        platform: {
            id: this.platform.value,
            //name: this.platform.data
            name: "Steam"
        }
    }

    console.log(data);
    axios.post("/games/create/", data)
        .then(res => {
            getGames();
            this.reset();
            this.make.focus();
        }).catch(err => console.log(err));
});
//UPDATE

var myModal = new bootstrap.Modal(document.getElementById("gameModal"), {});
const updateGame = async (id, data) => {
    myModal.show();
    // const response = await axios.put(`/games/update/${id}`, data);

    getGames();
}

document.querySelector("#gameModal").addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
        name: this.gameName.value,
        genre: this.genre.value,
        progress: this.progress.value,
    }
    console.log(data);

});


//DELETE
const deleteGame = async (id) => {
    const res = await axios.delete(`/games/delete/${id}`);
    getGames();
};


//MODAL FOR CREATING A PLATFORM
// var myModal = new bootstrap.Modal(document.getElementById('myModal'), onclick)
// var myModalEl = document.getElementById('exampleModal')

// myModalEl.addEventListener('hidden.bs.modal', function (event) {
//     select = document.getElementById('platform');
//     var option = document.createElement('option');
//     option.value = event.value;
//     option.makeText = event.value;
//     // option.innerHTML = event.value;
//     select.appendChild(option);
// })
