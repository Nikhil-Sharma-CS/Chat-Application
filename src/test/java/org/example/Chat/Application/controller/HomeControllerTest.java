package org.example.Chat.Application.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import org.example.Chat.Application.dto.OpenAIResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {HomeController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class HomeControllerTest {
    @Autowired
    private HomeController homeController;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link HomeController#chat(String)}
     */
    @Test
    void testChat() throws Exception {
        // Arrange
        when(restTemplate.postForObject(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<Class<OpenAIResponse>>any(),
                isA(Object[].class))).thenReturn(new OpenAIResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/chat").param("prompt", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"choices\":null}"));
    }
}
