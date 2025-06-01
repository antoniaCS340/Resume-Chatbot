# Resume Chatbot

A project to create a chatbot that answers questions about my resume, simulating an interview experience.

## Project Structure
- **Backend**: Spring Boot application with a `/chat` that responds to user input using a keyword- and regex-based matching system for natural language queries.

regex pattern:
-begin with (?i) to ignore case and match both lowercase and uppercase input.
-\b to ensure a match only occurs on whole words, preventing accidental partial matches.
-the | operator to match any of multiple phrases or words.
-.* to allow for variable words in between known phrases

- **Frontend**: HTML interface with TailwindCSS, style the chatbox with action buttons.
- **Directory**:
  - `src/main/java/com/example/chatbot/` - Contains Java source code.
  - `src/main/resources/static/` - Contains the `index.html, script.js, styles.css`.

git clone <https://github.com/antoniaCS340/Resume-Chatbot.git>
cd resume-chatbot

You'll need Java 21 and Maven
Install dependencies: `mvn clean install`
run it with: `mvn spring-boot:run`
and then visit http://localhost:8080