package a7.zheye2.controller;

import a7.zheye2.dao.UserRepository;
import a7.zheye2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String registerForm(){
        return "login.html";
    }
    @PostMapping("/login.do")
    public String processRegistraion(@RequestBody User user){
        userRepository.save(new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),user.getPhone()));
        return "redirect:/login";
    }
}
