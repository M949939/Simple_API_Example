package MSI.example.Framework_Module.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private int year;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "Film_Actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    public Film() {}

    public Film(Long id, String title, int year, String description, Date releaseDate, int duration, Category category, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.category = category;
        this.actors = actors;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<Actor> getActors() { return actors; }
    public void setActors(List<Actor> actors) { this.actors = actors; }
}
