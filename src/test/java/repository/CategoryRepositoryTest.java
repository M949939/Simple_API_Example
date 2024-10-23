package repository;

import MSI.example.Framework_Module.FrameworkModuleApplication;
import MSI.example.Framework_Module.entity.Category;
import MSI.example.Framework_Module.repository.CategoryRepository;
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
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("Find category by name")
    @Test
    public void findByNameTest() {
        Category drama = new Category(null, "Drama", null);
        entityManager.persist(drama);
        entityManager.flush();

        List<Category> foundCategories = categoryRepository.findByName("Drama");

        assertThat(foundCategories).isNotNull();
        assertThat(foundCategories.size()).isEqualTo(1);
        assertThat(foundCategories.get(0).getName()).isEqualTo("Drama");
    }

    @DisplayName("Ensure no categories found if name does not match")
    @Test
    public void findByNameNoMatchTest() {
        Category comedy = new Category(null, "Comedy", null);
        entityManager.persist(comedy);
        entityManager.flush();

        List<Category> foundCategories = categoryRepository.findByName("Action");

        assertThat(foundCategories).isEmpty();
    }
}
