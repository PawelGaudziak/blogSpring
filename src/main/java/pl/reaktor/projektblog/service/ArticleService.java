package pl.reaktor.projektblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.reaktor.projektblog.model.Article;
import pl.reaktor.projektblog.repository.ArticleRepository;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    //metoda do zapisu dodanego artykułu do bazy danych przy użyciu articleRepository
    public Article addArticle(Article article){
        article.setDate(new Date());
        return articleRepository.save(article);
    }

    //korzystamy z metody z ArticleRepository, dodajemy ostatni artykuł z BD
    public Article getLastArticle(){
        return articleRepository.findFirstByOrderByIdDesc();
    }

    public List<Article> getAllArticle(){

        return articleRepository.findAll(Sort.by("date").descending());
    }

    public Article getById (long id){
        return articleRepository.getOne(id);
    }
}
