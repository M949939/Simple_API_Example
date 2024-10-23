package repository;

import MSI.example.Framework_Module.FrameworkModuleApplication;
import MSI.example.Framework_Module.entity.Film;
import MSI.example.Framework_Module.repository.FilmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Date;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = FrameworkModuleApplication.class)
public class FilmRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FilmRepository filmRepository;

    @DisplayName("Find film by title")
    @Test
    public void findByTitleTest() {
        Film titanic = new Film(null, "Titanic", 1997, "A tragic love story on the ill-fated ship.", new Date(), 195, null, Collections.emptyList());

        entityManager.persist(titanic);
        entityManager.flush();

        List<Film> foundFilms = filmRepository.findByTitle("Titanic");

        assertThat(foundFilms).isNotNull();
        assertThat(foundFilms.size()).isEqualTo(1);
        assertThat(foundFilms.get(0).getTitle()).isEqualTo("Titanic");
    }


    @DisplayName("Ensure no films found if title does not match")
    @Test
    public void findByTitleNoMatchTest() {
        Film inception = new Film(null, "Inception", 2010, "A thief enters the dreams of others to steal ideas.", new Date(), 148, null, Collections.emptyList());
        entityManager.persist(inception);
        entityManager.flush();

        List<Film> foundFilms = filmRepository.findByTitle("Interstellar");

        assertThat(foundFilms).isEmpty();
    }
}
