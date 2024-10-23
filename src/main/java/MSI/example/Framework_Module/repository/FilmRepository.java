package MSI.example.Framework_Module.repository;

import MSI.example.Framework_Module.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByTitle(String title);
}