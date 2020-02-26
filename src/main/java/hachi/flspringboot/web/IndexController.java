package hachi.flspringboot.web;

import hachi.flspringboot.service.posts.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * index.mustache 의 컨트롤러
 */
@Controller
public class IndexController {

    @Autowired
    private PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; //경로 뒤 파일 확장자는 자동으로 지정
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
