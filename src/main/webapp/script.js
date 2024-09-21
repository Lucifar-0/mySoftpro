function sendMessage() {
    const userInput = document.getElementById('user-input').value;
    if (userInput.trim() === "") return;

    const chatWindow = document.getElementById('chat-window');

    // Append user's message
    const userMessage = document.createElement('div');
    userMessage.textContent = userInput;
    userMessage.className = 'user-message';
    chatWindow.appendChild(userMessage);

    // Clear input field
    document.getElementById('user-input').value = '';

    // Scroll to the bottom
    chatWindow.scrollTop = chatWindow.scrollHeight;

    // Simulate a response from Jarvice
    setTimeout(() => {
        const jarviceMessage = document.createElement('div');
        jarviceMessage.textContent = "Hello, how can I assist you today?";
        jarviceMessage.className = 'jarvice-message';
        chatWindow.appendChild(jarviceMessage);

        // Scroll to the bottom
        chatWindow.scrollTop = chatWindow.scrollHeight;
    }, 1000);
}
