package repository;

import MSI.example.Framework_Module.FrameworkModuleApplication;
import MSI.example.Framework_Module.entity.Actor;
import MSI.example.Framework_Module.repository.ActorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = FrameworkModuleApplication.class)
public class ActorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ActorRepository actorRepository;

    @DisplayName("Find actor by name containing a substring")
    @Test
    public void findByNameContainingTest() {
        Actor actorLeonardo = new Actor(null, "Leonardo DiCaprio", "A leading man in Hollywood...", null);
        entityManager.persist(actorLeonardo);
        entityManager.flush();

        Actor actorTom = new Actor(null, "Tom Hanks", "Known for his comedic and dramatic roles...", null);
        entityManager.persist(actorTom);
        entityManager.flush();

        List<Actor> foundActors = actorRepository.findByName("Leonardo DiCaprio");

        assertThat(foundActors).isNotNull();
        assertThat(foundActors.size()).isEqualTo(1);
        assertThat(foundActors.get(0).getName()).contains("Leonardo");
    }

    @DisplayName("Ensure no actors found if name does not match")
    @Test
    public void findByNameContainingNoMatchTest() {
        Actor actorTom = new Actor(null, "Tom Hanks", "Known for his comedic and dramatic roles...", null);
        entityManager.persist(actorTom);
        entityManager.flush();

        List<Actor> foundActors = actorRepository.findByName("Nicole");

        assertThat(foundActors).isEmpty();
    }
}
