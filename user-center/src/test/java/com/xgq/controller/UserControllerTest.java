package com.xgq.controller;

import com.alibaba.fastjson.JSONObject;
import com.xgq.dto.UserDto;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author xingguoqing
 * @date 2018/2/2 下午4:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.xgq.UserApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }

    @Test
    public void test1() throws Exception {
        UserDto userDto = new UserDto();

        mvc.perform(MockMvcRequestBuilders.post("/user/selectUsers?pageNum=2&pageSize=5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userDto))
//                .param("userCode", "")
//                .param("userMail", "")
//                .param("userPhone", "")
//                .param("userName", "")
//                .param("personalProfile", "")
//                .param("status", "")
//                .param("lastLogin", "")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
//            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

    }
}
