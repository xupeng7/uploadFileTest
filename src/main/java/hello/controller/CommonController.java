package hello.controller;

import hello.model.Akafu;
import hello.model.Result;
import hello.storage.AkafuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class CommonController {


    @Autowired
    private AkafuService akafuService;

    //查询公司的证书之类的图片 type暂定1
    @GetMapping("/getAuthenticationPics/{id}/{type}")
    public List<Result> handleFileUpload(@PathVariable int id,@PathVariable int type) {

        List<Akafu> akafus = akafuService.findAuthenticationPicsByWorkerIdAndType(id,type);
        List<Result> results = new ArrayList<>();

        for (Akafu item : akafus
                ) {
            Result result = new Result();
            result.setUid(item.getId());
            result.setUrl(item.getImageUrl());
            results.add(result);
        }

        return results;
    }

    @DeleteMapping("/delete/{id}")
    public void deletePic(@PathVariable int id) {

        akafuService.deleteById(id);

    }

    @GetMapping("/getAvatarByWorkerId/{workerId}/{type}")
    public Result getAvatarById(@PathVariable int workerId,@PathVariable int type) throws RuntimeException {
        Result result = new Result();


        //
        try {
            Akafu akafu= akafuService.findAvatarByWorkerIdAndType(workerId,type);
            result.setUrl(akafu.getImageUrl());
            result.setUid(akafu.getId());
            return result;
        }catch (RuntimeException e){

        }

        return new Result();

    }
}
