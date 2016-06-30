package javappj.servis;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by The CAT
 */
@RestController
public class RestControler {
    private FileSys file;

    public static final String UPLOAD_PATH = "/upload/{name}";

    @RequestMapping(value = UPLOAD_PATH, method = RequestMethod.POST)
    public
    @ResponseBody
    void uploadImage(@PathVariable("name") String name,@RequestParam("data") MultipartFile imageData, HttpServletResponse response) {

        System.out.println("UPLOAD");
        try {

            file =new FileSys();

        } catch (IOException e) {

            e.printStackTrace();

        }

        try {
            file.newImage(name, imageData.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}