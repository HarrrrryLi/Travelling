package com.Travelling.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class LoginController {
    @RequestMapping(value= "/login", method= {RequestMethod.GET, RequestMethod.POST})
    public String Login() {
        return "Login Page";
    }
}
