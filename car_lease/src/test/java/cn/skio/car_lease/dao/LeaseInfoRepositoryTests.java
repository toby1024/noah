package cn.skio.car_lease.dao;

import cn.skio.car_lease.entity.LeaseInfo;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Transactional
@Slf4j
@ActiveProfiles("test")
public class LeaseInfoRepositoryTests {

    @Autowired
    LeaseInfoRepository repository;

    @Test
    public void providesFindOneWithOptional() {

        LeaseInfo leaseInfo = repository.save(new LeaseInfo("test"));

        System.out.println(leaseInfo);
//        assertThat(repository.findOne(leaseInfo.getId()).isPresent(), is(true));
//        assertThat(repository.findOne(leaseInfo.id + 1), is(Optional.<LeaseInfo> empty()));
    }
}
