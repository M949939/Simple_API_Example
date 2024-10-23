package MSI.example.Framework_Module.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private List<Film> films;

    public Actor() {}

    public Actor(Long id, String name, String bio, List<Film> films) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.films = films;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public List<Film> getFilms() { return films; }
    public void setFilms(List<Film> films) { this.films = films; }
}
