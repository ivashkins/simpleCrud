package library.Controller;

import library.Dao.VideoDao;
import library.Model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/video")
public class VideoController {
private static int counter=0;
    private final VideoDao videoDao;

    @Autowired
    public VideoController(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @GetMapping()
    public String index(Model model){
        counter = videoDao.index().stream().map(Video::getId).max(Integer::compare).get();
        model.addAttribute("videos",videoDao.index());
        return "video/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("video",videoDao.show(id));
        return "video/show";
    }
    @GetMapping("/new")
    public String newVideo(Model model){
        model.addAttribute("video", new Video());
        return "video/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("video") @Valid Video video, BindingResult bindingResult){
        if(bindingResult.hasErrors()){return "video/new";}
        video.setId(++counter);
        videoDao.save(video);
        return "redirect:/video";
    }
    @GetMapping("{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("video",videoDao.show(id));
        return "video/edit";
    }
    @PatchMapping("/{id}")
    public String update(Model model,@Valid Video video,BindingResult bindingResult,@PathVariable("id")int id){
        if(bindingResult.hasErrors())return "video/edit";
        videoDao.update(id,video);
        return "redirect:/video";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        videoDao.delete(id);
        return "redirect:/video";
    }
}
