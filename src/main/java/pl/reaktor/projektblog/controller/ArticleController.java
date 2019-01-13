package pl.reaktor.projektblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reaktor.projektblog.model.Article;
import pl.reaktor.projektblog.model.Tag;
import pl.reaktor.projektblog.service.ArticleService;
import pl.reaktor.projektblog.service.TagService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {

    private TagService tagService;
    private ArticleService articleService;
    @Autowired
    public ArticleController(TagService tagService, ArticleService articleService) {
        this.tagService = tagService; //pobranie
        this.articleService = articleService; //zapisanie
    }



    @GetMapping("/article")
    public String article(Model model) {

        //przekazujemy do modelu pusty obiekt article
        model.addAttribute("article", new Article());

        //pobieramy z bazy wszystkie tagi i wrzucamy do modelu, żeby były dostępne na stronie html

        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("allTags", tags);


        //
        return "article/article";

    }

    @GetMapping("/article/{id}")
    public String articleById(@PathVariable long id, Model model){
        Article article = articleService.getById(id);
        model.addAttribute("article", article);
        return "article/single";
    }

    @PostMapping("/article")
    public String addArticle(@Valid @ModelAttribute Article article, BindingResult bindingResult, Model model) {

        //sprawdzamy, czy formularz jest prawidłowo wypełniony, jeśli ma błędzy to musimy wrócić do naszego widoku
        if (bindingResult.hasErrors()) {
            return "article/article";
        }

        //będziemy dodawali artykuł dobazy danych przez serwis i repozytory
        Article articleSaved = articleService.addArticle(article);
        //dodanie zapisanego artykułu do modelu w celu wyświetlenia go na srtonie single
        model.addAttribute("article", articleSaved);
        return "article/single";
    }

    @GetMapping("/single")
    public String singleArticle(Model model){

        //dodajemy do modelu najnowszy artykuł z bazy danych
        Article article = articleService.getLastArticle();
        model.addAttribute("article", article);
        return "article/single";
    }
}
