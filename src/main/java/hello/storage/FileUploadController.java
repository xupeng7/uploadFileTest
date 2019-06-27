package hello.storage;

import java.io.IOException;
import java.util.stream.Collectors;

import hello.model.ResultVo;
import hello.service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    private ImageServiceImpl imageService;

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        System.out.println("loadAll-controller");
        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /*
    * 上传认证图片*/
    @PostMapping("/upload")
    @ResponseBody
    public ResultVo handleFileUpload(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) {
       try {
           //storageService.store(file);

           String fileName=storageService.storeImage(file);
           Long preTime=System.currentTimeMillis();
           imageService.imageHandler(fileName);
           Long didTime =System.currentTimeMillis();
           System.out.println("总耗时"+(didTime-preTime)+"ms");
           return new ResultVo(200,"上传成功");
       }catch (StorageException e){
           e.getMessage();
           return new ResultVo(500,"上传失败");
       } catch (Exception e) {
           e.printStackTrace();
           return new ResultVo(500,"上传失败");
       }

    }
     /*
     * 上传头像
     * */
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public ResultVo uploadAvatar(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        System.out.println(123);
        try {
            storageService.storeAvatar(file);
            return new ResultVo(200,"上传成功");
        }catch (StorageException e){
            e.getMessage();
            return new ResultVo(500,"上传失败");
        }

       /* redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";*/

    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
