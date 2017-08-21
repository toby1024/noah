package cn.skio.car_lease.service;

import cn.skio.car_lease.dao.LeaseInfoRepository;
import cn.skio.car_lease.entity.LeaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LeaseInfoService {

    @Autowired
    LeaseInfoRepository repository;

    public LeaseInfo find(Long id) {
        Optional<LeaseInfo> leaseInfo = repository.findOne(id);
        return leaseInfo.get();
    }

    @Transactional
    public List<LeaseInfo> findAll() {
        return repository.streamAllLeaseInfos().collect(Collectors.toList());
    }

}
