package cn.skio.gateway.auth.web;

import cn.skio.gateway.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password){
        return authService.login(username, password);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password){
        return authService.register(username, password);
    }

}
