package hello.storage;

import hello.model.Akafu;
import hello.repository.AkafuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AkafuServiceImpl implements AkafuService {

    @Autowired
    private AkafuDao akafuDao;

    //查询用户的认证照片
    @Override
    public List<Akafu> findAuthenticationPicsByWorkerIdAndType(int workerId, int type) {
        return akafuDao.findAkafusByWorkerIdAndType(workerId,type);
    }

    @Override
    public void deleteById(int id) {
        akafuDao.deleteById(id);
    }

   //查询用户头像
    @Override
    public Akafu findAvatarByWorkerIdAndType(int workerId, int type) {
        return akafuDao.findAkafuByWorkerIdAndType(workerId,type);
    }
}
