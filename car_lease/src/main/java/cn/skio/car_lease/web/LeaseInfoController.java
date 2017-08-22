package cn.skio.car_lease.web;

import cn.skio.car_lease.entity.LeaseInfo;
import cn.skio.car_lease.service.LeaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaseInfoController {

    @Autowired
    LeaseInfoService leaseInfoService;

    @RequestMapping("/lease_infos/{id}")
    LeaseInfo getLeaseInfo(@PathVariable Long id){
        return leaseInfoService.find(id);
    }

    @RequestMapping("/lease_infos")
    List<LeaseInfo> findAll(){
        System.out.println("==========>");
        return leaseInfoService.findAll();
    }
}
