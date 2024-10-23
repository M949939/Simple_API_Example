package MSI.example.Framework_Module.repository;

import MSI.example.Framework_Module.entity.Actor;
import MSI.example.Framework_Module.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
}
