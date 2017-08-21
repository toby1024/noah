package cn.skio.web.lease_web;

import cn.skio.web.lease_service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaseController {

    @Autowired
    LeaseService leaseService;

    @RequestMapping("/lease_infos")
    public String index(){
        return leaseService.findAll();
    }
}
