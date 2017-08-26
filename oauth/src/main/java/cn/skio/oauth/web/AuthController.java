package cn.skio.oauth.web;

import cn.skio.oauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public String login(String username, String password){
        return authService.login(username, password);
    }

}
