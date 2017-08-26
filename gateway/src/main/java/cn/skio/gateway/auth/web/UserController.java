package cn.skio.gateway.auth.web;

import cn.skio.gateway.auth.entity.User;
import cn.skio.gateway.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> activeUsers(int pageNo, int pageSize){
        return authService.activeUsers(pageNo, pageSize);
    }

}
