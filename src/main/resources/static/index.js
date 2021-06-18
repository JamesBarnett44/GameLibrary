var myModal = new bootstrap.Modal(document.getElementById("gameModal"), {});
var focusedgame = 0;

//READ GAMES
const display = document.querySelector("#display");
const output = document.querySelector("#output");
const platforSelection = document.querySelector("#platformSelection");
const getGames = async () => {
    const response = await axios.get("/games/");
    output.innerHTML = "";
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

    const updateButton = document.createElement("a");
    updateButton.innerText = "Update";
    updateButton.className = "btn btn-primary";
    updateButton.addEventListener("click", function () {
        updateGame(id, name, genre, progress, platform);
    });
    cardBody.appendChild(updateButton);

    const cardFooter = document.createElement("div");
    cardFooter.className = "card-footer";
    card.appendChild(cardFooter);

    const deleteButton = document.createElement("a");
    deleteButton.innerText = "Delete";
    deleteButton.className = "btn btn-primary";
    deleteButton.addEventListener("click", function () {
        deleteGame(id);
    });
    cardFooter.appendChild(deleteButton);

    output.appendChild(column);
}
getGames();

document.querySelector("#viewAllButton").addEventListener("click", function (event) {
    getGames();
});

//CREATE GAME
document.querySelector("#gameForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        name: this.gameName.value,
        genre: this.genre.value,
        progress: this.progress.value,
        platform: {
            id: this.platform.value,
            name: this.platform.innerText
        }
    }

    axios.post("/games/create/", data)
        .then(res => {
            getGames();
            this.reset();
        }).catch(err => console.log(err));
});

//UPDATE GAME
const updateGame = async (id, name, genre, progress, platform) => {
    focusedgame = id;
    myModal.show();
    getGames();
}

document.querySelector("#gameUpdateForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
        name: this.gameName.value,
        genre: this.genre.value,
        progress: this.progress.value,
    }

    axios.put(`/games/update/${focusedgame}`, data)
        .then(res => {
            getGames();
            this.reset();
        }).catch(err => console.log(err));
});

//DELETE GAME
const deleteGame = async (id) => {
    const res = await axios.delete(`/games/delete/${id}`);
    getGames();
};

//==================================================================================
var platcount = 0;
var selectedPlatform = 0;

//READ PLATFORMS
const getPlatforms = async () => {
    const response = await axios.get("/platforms/");
    platforSelection.innerHTML = "";
    select = document.getElementById('platform');
    select.innerHTML = "";
    response.data.forEach(platform => createPlatformUI(platform));
}
getPlatforms();

//CREATE DROPDOWN OPTION
const createPlatformUI = async (platform) => {
    console.log(platform);
    select = document.getElementById('platform');
    var option = document.createElement('option');
    option.value = platform.id;
    option.innerHTML = platform.name;
    select.appendChild(option);

    platformSelection = document.getElementById('platformSelection')
    const newbutton = document.createElement('button')
    newbutton.classList.add("btn", "btn-primary");
    newbutton.id = platform.id;
    newbutton.textContent = platform.name;

    newbutton.onclick = function () {
        selectedPlatform = this.id;
        showPlatformGames(selectedPlatform);
    }
    platformSelection.appendChild(newbutton);
}

//CREATE PLATFORM
document.querySelector("#addplatformForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
        name: this.platformInput.value
    }
    console.log(data);
    axios.post("/platforms/create/", data)
        .then(res => {
            getGames();
            this.reset();
            getPlatforms();
        }).catch(err => console.log(err));
});

const showPlatformGames = async (id) => {
    const response = await axios.get(`/platforms/${selectedPlatform}`);
    output.innerHTML = "";
    response.data.games.forEach(game => {
        showGame(game);
    })
};


//DELETE PLATFORM
document.querySelector("#deletePlatformButton").addEventListener("click", function (event) {
    event.preventDefault();
    deletePlatform();
    //alert("This will delete a platform and all of its games");
    // var input = prompt("Please enter 'delete' to delete the selected platform and its' games");
    // if (input == 'hello') {
    //     const platform = await axios.get(`/platforms/${selectedPlatform}`);
    //     platform.data.games.forEach(game => {
    //         deleteGame(game.id);
    //     })
    //     const response = axios.delete(`/platforms/delete/${selectedPlatform}`);
    // } else {
    //     console.log("delete cancelled");
    // }
});

const deletePlatform = async () => {
    var input = prompt("Please enter 'delete' to delete the selected platform and its' games");
    if (input == 'delete') {
        const platform = await axios.get(`/platforms/${selectedPlatform}`);
        platform.data.games.forEach(game => {
            deleteGame(game.id);
        })
        const response = await axios.delete(`/platforms/delete/${selectedPlatform}`);
        getPlatforms();
        getGames();
    } else {
        console.log("delete cancelled");
    }
}