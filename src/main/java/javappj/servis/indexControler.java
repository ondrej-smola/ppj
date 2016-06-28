package javappj.servis;

/**
 * Created by The CAT on 27.6.2016.
 */

import javappj.data.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Controller
public class indexControler {

    private Serv serv;


    @Autowired
    public void setImageRepository(Serv serv) {
        this.serv = serv;

    }

    @Value("${path}")
    private String path;

    @RequestMapping("/projekt")
    public String showHome(Model model, @RequestParam(value = "id", required = false) String actual) {
        Image image;

        if(actual == null) {
            image = serv.getFirstImage();
        } else {
            image = serv.getNextImageById(UUID.fromString(actual));
        }

        if (image == null) {
            image = serv.getFirstImage();
        }

        model.addAttribute("image", image);
        model.addAttribute("actual", image.getImg_id().toString());

        if (!image.getLocation().startsWith("http")) {
            model.addAttribute("path", path);
        } else {
            model.addAttribute("path", "");
        }

        return "projekt";
    }

    @RequestMapping("/likeImg")
    public String likeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        serv.LikeImg(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt?id="+id;
    }

    @RequestMapping("/dislikeImg")
    public String dislikeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        serv.DislikeImg(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt?id="+id;
    }

    @RequestMapping("/likeComment")
    public String likeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                              @RequestParam(value = "commentId") String commentId) {
        serv.LikeComent(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt?id="+id;
    }

    @RequestMapping("/dislikeComment")
    public String dislikeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                                 @RequestParam(value = "commentId") String commentId) {
        serv.DislikeComent(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt?id="+id;
    }
    private fileSys files;

    public static final String UPLOAD_PATH = "/upload/{name}";

    @RequestMapping(value = UPLOAD_PATH, method = RequestMethod.POST)
    public
    @ResponseBody
    void uploadImage(@PathVariable("name") String name,
                            @RequestParam("data") MultipartFile imageData,
                            HttpServletResponse response) {

       // ImageStatus state = new ImageStatus(ImageStatus.ImageState.READY);

        setFileManager();

        try {
            files.newImage(name, imageData.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setFileManager() {

        try {

           files = new fileSys();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}