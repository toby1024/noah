package cn.skio.car_lease.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import cn.skio.car_lease.entity.LeaseInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

public interface LeaseInfoRepository extends Repository<LeaseInfo, Long> {

    /**
     * Special customization of {@link CrudRepository#findOne(java.io.Serializable)} to return a JDK 8 {@link Optional}.
     *
     * @param id
     * @return
     */
    Optional<LeaseInfo> findOne(Long id);

    /**
     * Saves the given {@link LeaseInfo}.
     *
     * @param leaseInfo
     * @return
     */
    <S extends LeaseInfo> S save(S leaseInfo);


    /**
     * Sample method to demonstrate support for {@link Stream} as a return type with a custom query. The query is executed
     * in a streaming fashion which means that the method returns as soon as the first results are ready.
     *
     * @return
     */
    @Query("select c from LeaseInfo c")
    Stream<LeaseInfo> streamAllLeaseInfos();

    @Async
    CompletableFuture<List<LeaseInfo>> readAllBy();
}
