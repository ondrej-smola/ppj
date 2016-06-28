package javappj.servis;

/**
 * Created by The CAT on 27.6.2016.
 */

import javappj.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;


@Controller
@RequestMapping("/")
public class indexControler {
    @Autowired
    private Serv serv;


    @Autowired
    public void setImageRepository(Serv serv) {

        this.serv = serv;
    }
    @Value("${path}")
    private String path;

  //  @RequestMapping("/projekt")
    @RequestMapping(value="/projekt", method = RequestMethod.GET)
    public String showIndex(Model model, @RequestParam(value = "id", required = false) String actual) {
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
        model.addAttribute("actual", image.getId().toString());

        if (!image.getLocation().startsWith("http")) {
            model.addAttribute("path", path);
        } else {
            model.addAttribute("path", "");
        }
       // System.out.println("MVC TADY");
        return "projekt";
    }



    @RequestMapping("/likeImg")
    public String likeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        serv.LikeImg(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/dislikeImg")
    public String dislikeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        serv.DislikeImg(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/likeComment")
    public String likeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                              @RequestParam(value = "commentId") String commentId) {
        serv.LikeComent(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/dislikeComment")
    public String dislikeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                                 @RequestParam(value = "commentId") String commentId) {
        serv.DislikeComent(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }


}