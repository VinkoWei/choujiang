package com.vinko.choujiang.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @Author:Vinko
 * @Description:
 * @Date: Created in 15:09 2018-06-05
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private MockHttpServletRequest mockHttpServletRequest;
    private double[] list;

    @Before
    public void setupMockMvcAndList() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        list = new double[4];
        for (int i = 0; i < 4; i++) {
            list[i] = 0;
        }
    }

    /*
        测试该抽奖算法的概率正确性
     */
    @Test
    public void draw() throws Exception {
        String responseString;
        int index = 0;
        double count = 100000;   //测试抽奖次数
        for (int i = 0; i < count; i++) {
            //模拟请求controller获得抽奖结果，存入list.
            responseString = mockMvc.perform(MockMvcRequestBuilders.post("/draw")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn().getResponse().getContentAsString();
            //对应奖项次数+1
            index = Integer.parseInt(responseString);
            list[index] += 1;


        }
        double p;
        for (int i = 0; i < 4; i++) {
            //计算每个奖项的获奖频率
            p = list[i] / count;
            System.out.println((i + 1) + "等奖 :" + p);
        }
        /*
        当count等于100000，跑出的数据
            1等奖 :0.0093
            2等奖 :0.01001
            3等奖 :0.01058
            4等奖 :0.97011

            //预设的数值
            event[0] = 0.01;  // 1等奖
            event[1] = 0.01;  // 2等奖
            event[2] = 0.01;  // 3等奖
            event[3] = 0.97;  // 未中奖

            及其接近所设置的概率
         */
    }
}