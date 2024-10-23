package MSI.example.Framework_Module.controller;

import MSI.example.Framework_Module.entity.Film;
import MSI.example.Framework_Module.exception.ResourceNotFoundException;
import MSI.example.Framework_Module.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        List<Film> existingFilms = filmRepository.findByTitle(film.getTitle());
        if (!existingFilms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Film newFilm = filmRepository.save(film);
        return ResponseEntity.ok(newFilm);
    }

    @GetMapping("/{id:\\d+}")  // This regex ensures that the id must be digits only
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
        return ResponseEntity.ok(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film filmDetails) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
        film.setTitle(filmDetails.getTitle());
        film.setYear(filmDetails.getYear());
        film.setDescription(filmDetails.getDescription());
        film.setReleaseDate(filmDetails.getReleaseDate());
        film.setDuration(filmDetails.getDuration());
        Film updatedFilm = filmRepository.save(film);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
        filmRepository.delete(film);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAllFilms")
    public ResponseEntity<Void> deleteAllFilms() {
        filmRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
