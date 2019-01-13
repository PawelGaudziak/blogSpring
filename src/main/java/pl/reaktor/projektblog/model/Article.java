package pl.reaktor.projektblog.model;


import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    @Length(max=50, message = "{article.title.max")
    @NotEmpty(message = "{article.title.notempty}")
    private String title;

    @Column(length = 4000)
    @Length(max=4000, message = "{article.content.max")
    @NotEmpty(message = "{article.title.notempty}")
    private  String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToMany
    @JoinTable(name="article_tag")
    private List<Tag> tags;

    public Article() {
    }

    public Article(String title, String content, Date date, List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.tags = tags;
    }

    public Article(long id, String title, String content, Date date, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                '}';
    }
}