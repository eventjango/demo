package com.example.demo;


import com.example.controller.RestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {RestController.class})*/
public class WebControllerTest {
/*

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void index() throws Exception {

        MvcResult result =
        mockMvc.perform(get("/rest/index"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
*/

}