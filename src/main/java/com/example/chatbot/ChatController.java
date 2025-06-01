package com.example.chatbot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

@RestController
public class ChatController {

    private final Map<String, List<Pattern>> responseMap;

    public ChatController() {
        responseMap = new HashMap<>();

        responseMap.put(
            "I'm Antonia, an undergraduate Computer Science student at the University of Crete, passionate about software development and problem-solving.",
            List.of(Pattern.compile("(?i)\\b(who (are|r) you|introduce yourself|tell me about yourself|about you|yourself)\\b"))
        );

        responseMap.put(
            "Hi, My name is Antonia Charalampaki, and I'm happy to answer any questions you have about my education, experience, or skills.",
            List.of(Pattern.compile("(?i)\\b(name| what. *name)\\b"))
        );

        responseMap.put(
            "I have developed a fire service app for citizens to receive local incident alerts and register as volunteers, and an event venue booking app.",
            List.of(Pattern.compile("(?i)\\b(projects?|what (have|did) you (build|create|develop)|worked on|apps? you made)\\b"))
        );

        responseMap.put(
            "I'm skilled in C, C++, C#, Python, Java, HTML, CSS, JavaScript, and database management.",
            List.of(Pattern.compile("(?i)\\b(skills?|technical skills?|expertise|programming languages?|what.*languages.*you.*know)\\b"))
        );

        responseMap.put(
            "I am an undergraduate student in Computer Science at the University of Crete, starting in 2022. I graduated high school in 2022 with a grade of 19.8/20.",
            List.of(Pattern.compile("(?i)\\b(education|studies|study|school|university|academic background|where.*study|your degree)\\b"))
        );

        responseMap.put(
            "I enjoy learning foreign languages, building small projects or websites, listening to and playing music, and exercising.",
            List.of(Pattern.compile("(?i)\\b(hobbies|interests|free time|what.*you.*do.*for.*fun|outside work)\\b"))
        );

        responseMap.put(
            "I have worked as a car rental driver at Heraklion Airport, hostess and cold kitchen assistant at Palladium Event Venue, receptionist and cashier at Watercity Waterpark, and in administration at a law office.",
            List.of(Pattern.compile("(?i)\\b(work experience|jobs|professional experience|where.*worked|what.*jobs.*you.*done)\\b"))
        );

        responseMap.put(
            "I have participated in a programming hackathon, attended the 1st UOC Astrophysics School, and completed a First Aid (CPR) training seminar.",
            List.of(Pattern.compile("(?i)\\b(certifications?|certificates?|seminars?|training|courses|participated.*in.*training)\\b"))
        );

        responseMap.put(
            "I achieved 4th place in a National Astrophysics Competition and ranked 1st in admission to the Computer Science Department at the University of Crete.",
            List.of(Pattern.compile("(?i)\\b(awards?|achievements?|honors?|accomplishments|won.*competition)\\b"))
        );

        responseMap.put(
            "I am a native Greek speaker and proficient in English, holding a Michigan Proficiency (C2) certificate.",
            List.of(Pattern.compile("(?i)\\b(languages?|language skills|speak.*language|fluency|native speaker)\\b"))
        );

        responseMap.put(
            "I was born in Heraklion and live permanently in the city center.",
            List.of(Pattern.compile("(?i)\\b(where.*you.*from|where.*live|hometown|origin|based in|located.*where)\\b"))
        );

        responseMap.put(
            "I aim to apply my programming skills in real-world projects, grow through internships or entry-level roles, and contribute to dynamic software development teams.",
            List.of(Pattern.compile("(?i)\\b(career goals?|dream|dreams|accomplish|apply.*this.*job|future plans?|what.*want.*do|aspirations?|long term.*plans?)\\b"))
        );

        responseMap.put(
            "You can reach me at antocharal24@gmail.com.",
            List.of(Pattern.compile("(?i)\\b(contact|how.*reach you|email|how.*find you|get in touch)\\b"))
        );

        /*
            regex pattern:
            -begin with (?i) to ignore case and match both lowercase and uppercase input.
            -\b to ensure a match only occurs on whole words, preventing accidental partial matches.
            -the | operator to match any of multiple phrases or words.
            -.* to allow for variable words in between known phrases 
        */
    }

    @PostMapping("/chat")
    public ChatResponse handleChat(@RequestBody ChatRequest request) {
        String prompt = request.getPrompt().toLowerCase();
        String defaultResponse = "Sorry, I don't understand the question. Try asking about my skills, projects, education, or hobbies!";
        String response = defaultResponse;

        for (Map.Entry<String, List<Pattern>> entry : responseMap.entrySet()) {
            for (Pattern pattern : entry.getValue()) {
                if (pattern.matcher(prompt).find()) {
                    response = entry.getKey();
                    break;
                }
            }
            if (!response.equals(defaultResponse)) break;
        }

        return new ChatResponse(response);
    }
}