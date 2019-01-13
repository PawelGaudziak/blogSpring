package pl.reaktor.projektblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.reaktor.projektblog.service.ArticleService;

@Controller
public class BlogController {

    private ArticleService articleService;

    @Autowired
    public BlogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/") //mapujemy plik startowy - index.html
    public String index(Model model) {
        //pobranie listy artykułów do modelu i wyświetlenie ich na stronie
        model.addAttribute("articles", articleService.getAllArticle());
        return "index";
    }
@GetMapping("/about")
    public String about(){
        return "about";
}
}
