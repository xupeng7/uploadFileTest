package hello.storage;

import hello.model.Akafu;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    void storeAvatar(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    List<Akafu> findAuthenticationPicsByWorkerIdAndType(int workerId, int type);

    void deleteById(int id);
    

    Akafu findAvatarByWorkerIdAndType(int workerId, int type);

}
