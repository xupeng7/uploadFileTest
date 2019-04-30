package hello.repository;

import hello.model.Akafu;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AkafuDao extends PagingAndSortingRepository<Akafu,Integer> {
    List<Akafu> findAkafusByWorkerIdAndType(int workerId,int type);

    Akafu findAkafuByWorkerIdAndType(int workerId,int type);


}
