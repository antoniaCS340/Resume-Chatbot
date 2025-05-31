document.addEventListener("DOMContentLoaded", function () {
        createStars();

});

function setSampleQuestion(question) {
    document.getElementById('userInput').value = question;
    sendPrompt();
}

async function sendPrompt() {
    const userInput = document.getElementById('userInput').value.trim();
    if (!userInput) {
        document.getElementById('response').innerHTML = '<p class="text-red-500">Please enter a question.</p>';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/chat', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ prompt: userInput })
        });
        const data = await response.json();
        document.getElementById('response').innerHTML = `<p>${data.response}</p>`;
    } catch (error) {
        document.getElementById('response').innerHTML = '<p class="text-red-500">Error connecting to the server.</p>';
    }
}

function clearChat() {
    document.getElementById('userInput').value = '';
    document.getElementById('response').innerHTML = '';
}


function createStars() {
    let starContainer = document.querySelector(".stars");

    if (starContainer) {
        starContainer.innerHTML = "";
    } else {
        starContainer = document.createElement("div");
        starContainer.classList.add("stars");
        document.body.appendChild(starContainer);
    }

    starContainer.style.position = "fixed";
    starContainer.style.top = "0";
    starContainer.style.left = "0";
    starContainer.style.width = "100vw";
    starContainer.style.height = "100vh";
    starContainer.style.overflow = "hidden";
    starContainer.style.zIndex = "-1"; 

    const numStars = Math.floor(window.innerWidth / 15) + 250; 

    for (let i = 0; i < numStars; i++) {
        let star = document.createElement("div");
        star.classList.add("star");

        let x = Math.random() * window.innerWidth;
        let y = Math.random() * window.innerHeight;
        star.style.left = `${x}px`;
        star.style.top = `${y}px`;

        let delay = Math.random() * 5;
        star.style.animationDelay = `${delay}s`;

        starContainer.appendChild(star);
    }
}