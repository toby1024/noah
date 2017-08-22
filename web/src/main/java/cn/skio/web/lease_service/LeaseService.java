package cn.skio.web.lease_service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lease")
public interface LeaseService {

    @RequestMapping("/lease_infos")
    String findAll();
}
