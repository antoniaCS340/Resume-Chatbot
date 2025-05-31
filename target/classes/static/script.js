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