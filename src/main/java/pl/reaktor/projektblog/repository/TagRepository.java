package pl.reaktor.projektblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.reaktor.projektblog.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
