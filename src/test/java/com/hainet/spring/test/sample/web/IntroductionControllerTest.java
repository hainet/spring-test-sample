package com.hainet.spring.test.sample.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(IntroductionController.class)
public class IntroductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IntroductionService mockService;

    @Test
    public void statusTest() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void viewTest() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    public void mockBeanTest() throws Exception {
        when(mockService.introduce()).thenReturn("I'm mock!");

        this.mockMvc
                .perform(get("/introduce"))
                .andExpect(content().string(containsString("I'm mock!")));
    }
}
