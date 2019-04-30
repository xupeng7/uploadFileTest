package hello.storage;

import hello.model.Akafu;

import java.util.List;

public interface AkafuService {
    List<Akafu> findAuthenticationPicsByWorkerIdAndType(int workerId,int type);

    void deleteById(int id);



    Akafu findAvatarByWorkerIdAndType(int workerId,int type);
}
