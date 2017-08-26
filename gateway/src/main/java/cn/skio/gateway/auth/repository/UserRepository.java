package cn.skio.gateway.auth.repository;

import cn.skio.gateway.auth.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByUsername(String username);


    List<User> findAllByLockedAndAndEnabledAndAndExpired(boolean locked, boolean enabled, boolean expired,Pageable pageable);

}
