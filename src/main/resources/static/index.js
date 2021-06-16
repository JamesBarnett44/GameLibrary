var myModal = new bootstrap.Modal(document.getElementById("gameModal"), {});
var gamecount = 0;
var focusedgame = 0;

//READ GAMES
const display = document.querySelector("#display");
const output = document.getElementById("output");
const getGames = async () => {
    const response = await axios.get("/games/");
    // display.innerHTML = "";
    output.innerHTML = ""; //??
    // console.log(response);
    // response.data.forEach(game => console.log(game));
    // response.data.forEach(() => gamecount++);
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

    const updateButton = document.createElement("a");
    updateButton.innerText = "Update";
    updateButton.className = "btn btn-primary";
    updateButton.setAttribute("id", ++gamecount);
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

    /////////////////HIDDEN ID FOR UPDATING
    // var hiddenId = document.createElement("input");
    // hiddenId.setAttribute("style", "display: none");
    // hiddenId.setAttribute("name", "hiddenId");
    // hiddenId.setAttribute("id", "hiddenId");
    // hiddenId.setAttribute("value", id);
    // cardFooter.appendChild(hiddenId);

    output.appendChild(column);

}
getGames();

//CREATE GAME
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
//UPDATE GAME

//var myModal = new bootstrap.Modal(document.getElementById("gameModal"), {});
const updateGame = async (id, name, genre, progress, platform) => {
    console.log(id);
    // console.log(name);
    // console.log(genre);
    // console.log(progress);
    // console.log(platform);
    const data = {
        name: name,
        genre: genre,
        progress: progress,
        // platform: platform
        // platform: {
        //     id: this.platform.value,
        //     //name: this.platform.data
        //     name: "Steam"
        // }
    }
    focusedgame = id;
    console.log(data);
    myModal.show();
    // const response = await axios.put(`/games/update/${id}`, data);

    // axios.put(`/games/update/${id}`, data)
    //     .then(res => {
    //         getGames();
    //         // this.reset();
    //         // this.make.focus();
    //     }).catch(err => console.log(err));
    getGames();
}

document.querySelector("#gameUpdateForm").addEventListener("submit", function (event) {
    event.preventDefault();
    console.log(event);
    console.log("ID: " + this.id);
    var id = document.querySelector
    const data = {
        name: this.gameName.value,
        genre: this.genre.value,
        progress: this.progress.value,
    }
    console.log(data);



    axios.put(`/games/update/${focusedgame}`, data)
        .then(res => {
            getGames();
            this.reset();
            // this.make.focus();
        }).catch(err => console.log(err));

});


//DELETE GAME
const deleteGame = async (id) => {
    const res = await axios.delete(`/games/delete/${id}`);
    getGames();
};

//==================================================================================
var platcount = 0;

//READ PLATFORMS
const getPlatforms = async () => {
    const response = await axios.get("/platforms/");
    // console.log(response);
    // response.data.forEach(() => platcount++);
    // console.log("platform count: " + platcount);
    response.data.forEach(platform => console.log(platform));
    response.data.forEach(platform => createPlatform(platform));
}
getPlatforms();

//CREATE DROPDOWN OPTION
const createPlatform = async (data) => {
    select = document.getElementById('platform');
    var option = document.createElement('option');
    option.value = ++platcount;
    option.innerHTML = data.name;;
    select.appendChild(option);
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
        }).catch(err => console.log(err));
    createPlatform(data);

});

//const showPlatformGames = async(id) 

