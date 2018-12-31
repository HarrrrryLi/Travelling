package com.Travelling.Controllers;

import com.Travelling.Repositories.Entities.User;
import com.Travelling.Repositories.UserRepository;
import com.Travelling.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SignupController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/signup")
    public ModelAndView GetSignup() {
        ModelAndView view = new ModelAndView("signup");
        view.addObject("signup", new Signup());
        return view;
    }

    @PostMapping("/signup")
    public String PostSignup(@ModelAttribute("signup") Signup signup){
        String username = signup.getUsername();
        String email = signup.getEmail();
        String password = signup.getPassword();
        if(!password.equals(signup.getConfirm_password()))
            return "not the same";
        if(userRepository.existsByUsername(username) != 0 || userRepository.existsByEmail(email) != 0 )
            return "existed";
        else{
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            userRepository.save(user);
            return "saved";
        }

    }
}
