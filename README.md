# Resume Chatbot

A project to create a chatbot that answers questions about my resume, simulating an interview experience.

## Project Structure
- **Backend**: Spring Boot application with a `/chat` that responds to user input using a keyword- and regex-based matching system for natural language queries.

- **Frontend**: HTML interface with TailwindCSS, style the chatbox with action buttons and animation in background.
- **Directory**:
  - `src/main/java/com/example/chatbot/` - Contains Java source code, `ChatController.java, ChatRequest.java, ChatResponse.java`.
  - `src/main/resources/static/` - Contains the `index.html, script.js, styles.css`.

```bash
git clone https://github.com/antoniaCS340/Resume-Chatbot.git
cd Resume-Chatbot

You'll need Java 21 and Maven installed. 
To install dependencies, run:
 mvn clean install
 mvn spring-boot:run

Then, open your browser and visit: http://localhost:8080