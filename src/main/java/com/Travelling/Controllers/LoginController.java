package com.Travelling.Controllers;

import com.Travelling.Login;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@RestController("/login")
public class LoginController {
    @GetMapping("/login")
    public ModelAndView GetLogin() {
        ModelAndView view = new ModelAndView("login");
        view.addObject("login", new Login());
        return view;
    }

    @PostMapping("login")
    public String PostLogin(@ModelAttribute("user") Login login){
        return String.format("%s,%s", login.getUsername(), login.getPassword());


    }
}
