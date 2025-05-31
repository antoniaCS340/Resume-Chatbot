package com.example.chatbot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    private final Map<String, List<String>> responseMap;

    public ChatController() {
        responseMap = new HashMap<>();
        responseMap.put("Hi, I'm Antonia, an undergraduate Computer Science student at the University of Crete, passionate about software development and problem-solving.", 
                        List.of("about yourself", "who are you", "introduce yourself"));
        responseMap.put("I have developed a fire service app for citizens to receive local incident alerts and register as volunteers, and an event venue booking app.", 
                        List.of("projects", "what have you built", "worked on"));
        responseMap.put("I'm skilled in C, C++, C#, Python, Java, HTML, CSS, JavaScript, and database management.", 
                        List.of("skills", "expertise", "know programming languages"));
        responseMap.put("I am an undergraduate student in Computer Science at the University of Crete, starting in 2022. I graduated high school in 2022 with a grade of 19.8/20.", 
                        List.of("education", "studies", "academic background"));
        responseMap.put("I enjoy learning foreign languages, building small projects or websites, listening to and playing music, and exercising.", 
                        List.of("hobbies", "interests", "free time"));
        responseMap.put("I have worked as a car rental driver at Heraklion Airport, hostess and cold kitchen assistant at Palladium Event Venue, receptionist and cashier at Watercity Waterpark, and in administration at a law office.", 
                        List.of("work experience", "jobs", "professional experience"));
        responseMap.put("I have participated in a programming hackathon, attended the 1st UOC Astrophysics School, and completed a First Aid (CPR) training seminar.", 
                        List.of("certifications", "certificates", "training"));
        responseMap.put("I achieved 4th place in a National Astrophysics Competition and ranked 1st in admission to the Computer Science Department at the University of Crete.", 
                        List.of("awards", "achievements", "honors"));
        responseMap.put("I am a native Greek speaker and proficient in English, holding a Michigan Proficiency (C2) certificate.", 
                        List.of("languages", "language skills", "speak"));
        responseMap.put("I was born in Heraklion and live permanently downtown.", 
                        List.of("from", "located", "origin", "hometown", "where are you"));
        responseMap.put("I aim to apply my programming skills in real-world projects, grow through internships or entry-level roles, and contribute to dynamic software development teams.", 
                        List.of("goals", "career aspirations", "future plans"));
        responseMap.put("You can reach me at antocharal24@gmail.com.", 
                        List.of("contact", "reach you", "email"));
    }

    @PostMapping("/chat")
    public ChatResponse handleChat(@RequestBody ChatRequest request) {
        String prompt = request.getPrompt().toLowerCase();
        String response = "Sorry, I don't understand the question. Try asking about my skills, projects, education, or hobbies!";

        for (Map.Entry<String, List<String>> entry : responseMap.entrySet()) {
            for (String keyword : entry.getValue()) {
                if (prompt.contains(keyword)) {
                    response = entry.getKey();
                    break;
                }
            }
            if (!response.equals("Sorry, I don't understand the question. Try asking about my skills, projects, education, or hobbies!")) {
                break;
            }
        }

        return new ChatResponse(response);
    }
}