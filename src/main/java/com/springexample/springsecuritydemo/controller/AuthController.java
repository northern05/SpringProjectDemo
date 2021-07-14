package com.springexample.springsecuritydemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Authentication/V1")
@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    @ApiOperation(value = "This method is used to login.")
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @ApiOperation(value = "Successfull authentication.")
    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }
}
