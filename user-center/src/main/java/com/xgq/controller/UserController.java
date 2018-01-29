package com.xgq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.CommonResponse;


/**
 * 用户相关controller
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/selectUsers",method = RequestMethod.GET)
    public CommonResponse selectUsers(){

        return null;
    }
}
