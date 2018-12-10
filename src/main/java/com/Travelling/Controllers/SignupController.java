package com.Travelling.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/signup")
public class SignupController {
    @RequestMapping(value= "/signup", method= {RequestMethod.GET, RequestMethod.POST})
    public String Signup() {
        return "Signup";
    }
}
