package hello.storage;

import hello.model.Akafu;
import hello.repository.AkafuDao;
import hello.storage.StorageException;
import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageProperties;
import hello.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    @Autowired
    private AkafuDao akafuDao;

    Akafu akafuAvatar=new Akafu();//更新头像用的实例对象

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }


    @Override
    @Transactional
    public String  storeImage(MultipartFile file) {
        Akafu akafu=new Akafu();
        /* String filename = StringUtils.cleanPath(file.getOriginalFilename());*/
        //拿到时间戳
        String oldName=StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(oldName);
        //拿到后缀名
        String suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
        System.out.println(suffix);
        String filename= Long.toString(Calendar.getInstance().getTimeInMillis())+"."+suffix;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve  (filename),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println("http://localhost:8082/image/"+filename);
                System.out.println("存认证信息");
                akafu.setImageUrl("http://localhost:8082/image/"+filename);
                akafu.setOriginame(file.getOriginalFilename());
                akafu.setWorkerId(3);
                akafu.setType(1);
                akafuDao.save(akafu);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return filename;
    }

    @Override
    public void store(MultipartFile file) {

    }


    @Transactional
    public void storeAvatar(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve  (filename),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println("http://localhost:8082/image/"+filename);

                akafuAvatar.setImageUrl("http://localhost:8082/image/"+filename);
                akafuAvatar.setOriginame(file.getOriginalFilename());
                akafuAvatar.setWorkerId(3);
                akafuAvatar.setType(0);
                akafuDao.save( akafuAvatar);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            System.out.println("loadAll()-service");
            return Files.walk(this.rootLocation, 1)
                .filter(path -> !path.equals(this.rootLocation))
                .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
