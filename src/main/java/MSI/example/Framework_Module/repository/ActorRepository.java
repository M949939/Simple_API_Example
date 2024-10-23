package MSI.example.Framework_Module.repository;


import MSI.example.Framework_Module.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByName(String name);
}