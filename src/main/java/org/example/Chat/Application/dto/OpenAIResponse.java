package org.example.Chat.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIResponse {

    private List<Response> responses;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {

        private int index;
        private Message message;

    }

}
