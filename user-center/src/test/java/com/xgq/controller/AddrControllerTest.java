package com.xgq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xgq.dto.UserDto;
import com.xgq.po.UserPo;
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
public class AddrControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }

    @Test
    public void selectUsers() throws Exception {
        UserDto userDto = new UserDto();
        mvc.perform(MockMvcRequestBuilders.get("/public/addr/selPageAddrs?pageNum=1&pageSize=5&userId=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void setDefaultAddr() throws Exception {
        UserDto userDto = new UserDto();
        mvc.perform(MockMvcRequestBuilders.get("/public/addr/setDefaultAddr?addrId=4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void delAddr() throws Exception {
        UserDto userDto = new UserDto();
        mvc.perform(MockMvcRequestBuilders.get("/public/addr/delAddr?addrId=5")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(userDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void updateAddr() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",4L);
        jsonObject.put("countryId","1");
        jsonObject.put("cityId","1");
        jsonObject.put("addr","天上");
        System.out.println(jsonObject.toJSONString());
        mvc.perform(MockMvcRequestBuilders.post("/public/addr/updateAddr")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
//            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

    }

    @Test
    public void addAddr() throws Exception {
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id",4L);
        jsonObject.put("countryId",1L);
        jsonObject.put("cityId",5L);
        jsonObject.put("addr","天上");
        System.out.println(jsonObject.toJSONString());
        mvc.perform(MockMvcRequestBuilders.post("/public/addr/addAddr")
                .header("sign","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTE4NTgwMTg4LCJzdWIiOiJzdWIiLCJpc3MiOiJpc3N1ZXIifQ.KPQyi3sy2Gwgac5-gUy8e0b-ySneif8w38Eh0hE654E")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toJSONString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
//            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

    }
//
//    @Test
//    public void getUserByUserCode() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/public/user/getUserByUserId?id=1")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//    }
//
//    @Test
//    public void login() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/public/user/login?userPhone=15611500999&password=123")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//    }
//
//
//    @Test
//    public void addUser() throws Exception {
//        UserPo userDto = new UserPo();
//        userDto.setUserName("张三");
//        userDto.setUserPhone("15611500029");
//        userDto.setUserPassword("123");
//        mvc.perform(MockMvcRequestBuilders.post("/public/user/addUser")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(JSONObject.toJSONString(userDto))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//    }
//
//    @Test
//    public void updateUserPhone() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/public/user/updateUserPhone?phone=15611500999&id=123")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//
//    }
//
////    @Test
////    public void updateUserMail() throws Exception {
////        mvc.perform(MockMvcRequestBuilders.get("/public/user/updateUserMail?mail=123@163.com&userCode=123484")
////                .contentType(MediaType.APPLICATION_JSON_UTF8)
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andDo(MockMvcResultHandlers.print()).andReturn();
////
////    }
//
//    @Test
//    public void updatePassword() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/public/user/updatePassword?password=123@163.com&id=123")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//
//    }
}
