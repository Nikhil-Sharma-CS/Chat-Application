package org.example.Chat.Application.controller;

import org.example.Chat.Application.dto.OpenAIRequest;
import org.example.Chat.Application.dto.OpenAIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/chat")
    public ResponseEntity<OpenAIResponse>  chat(@RequestParam String prompt){
        OpenAIRequest request=new OpenAIRequest(model, prompt);
        System.out.println(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(apiURL, request, OpenAIResponse.class));
    }


}
