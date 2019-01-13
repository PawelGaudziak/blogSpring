package pl.reaktor.projektblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.reaktor.projektblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    //metoda zwracająca najnowszy artykuł
    public Article findFirstByOrderByIdDesc();


}
