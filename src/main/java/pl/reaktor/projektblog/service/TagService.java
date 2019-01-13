package pl.reaktor.projektblog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.reaktor.projektblog.model.Tag;
import pl.reaktor.projektblog.repository.TagRepository;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    @Autowired

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    //metoda pobiera zawartość tabeli przy pomocy TagRepository
    public List<Tag> getAllTags(){

        return tagRepository.findAll();
}


}
