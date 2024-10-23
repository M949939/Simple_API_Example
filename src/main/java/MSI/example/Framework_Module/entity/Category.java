package MSI.example.Framework_Module.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Film> films;

    public Category() {}

    public Category(Long id, String name, List<Film> films) {
        this.id = id;
        this.name = name;
        this.films = films;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Film> getFilms() { return films; }
    public void setFilms(List<Film> films) { this.films = films; }
}
