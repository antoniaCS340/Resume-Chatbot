package com.example.chatbot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChatController {

    private final Map<String, String> responseMap;

    public ChatController() {
        responseMap = new HashMap<>();
        responseMap.put("about you", "Hi, I'm Antonia, an undergraduate Computer Science student at the University of Crete, passionate about software development and problem-solving.");        
        responseMap.put("projects", "I have developed a fire service app for citizens to receive local incident alerts and register as volunteers, and an event venue booking app.");
        responseMap.put("skills", "I have skills in C, C++, C#, Python, Java, HTML, CSS, JavaScript, and database management.");
        responseMap.put("education", "I am an undergraduate student in Computer Science at the University of Crete, starting in 2022. I graduated high school in 2022 with a grade of 19.8/20.");
        responseMap.put("hobbies", "I enjoy learning foreign languages, building small projects or websites, listening to or playing music, and exercising.");
        responseMap.put("work experience", "I have worked as a car rental driver at Heraklion Airport, hostess and cold kitchen assistant at Palladium Event Venue, receptionist and cashier at Watercity Waterpark, and in administration at a law office.");
        responseMap.put("certifications", "I have participated in a programming hackathon, attended the 1st UOC Astrophysics School, and completed a First Aid (CPR) training seminar.");
        responseMap.put("awards", "I achieved 4th place in a National Astrophysics Competition and ranked 1st in admission to the Computer Science Department at the University of Crete.");
        responseMap.put("languages", "I am a native Greek speaker and proficient in English, holding a Michigan Proficiency (C2) certificate.");
        responseMap.put("location", "I am based in Heraklion, Crete.");
        responseMap.put("goals", "I aim to apply my programming skills in real-world projects, grow through internships or entry-level roles, and contribute to dynamic software development teams.");
        responseMap.put("contact", "You can reach me at antocharal24@gmail.com.");    
    }

    @PostMapping("/chat")
    public ChatResponse handleChat(@RequestBody ChatRequest request) {
        String prompt = request.getPrompt().toLowerCase();
        String response = "Sorry, I don't understand the question. Try asking about my skills, projects, education, or hobbies!";

        for (Map.Entry<String, String> entry : responseMap.entrySet()) {
            if (prompt.contains(entry.getKey())) {
                response = entry.getValue();
                break;
            }
        }

        return new ChatResponse(response);
    }
}