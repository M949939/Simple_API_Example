package MSI.example.Framework_Module.controller;

import MSI.example.Framework_Module.entity.Actor;
import MSI.example.Framework_Module.exception.ResourceNotFoundException;
import MSI.example.Framework_Module.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
        @Autowired
        private ActorRepository actorRepository;

        @GetMapping
        public List<Actor> getAllActors() {
            return actorRepository.findAll();
        }

        @PostMapping
        public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
            List<Actor> existingActors = actorRepository.findByName(actor.getName());
            if (!existingActors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            Actor newActor = actorRepository.save(actor);
            return ResponseEntity.ok(newActor);
        }

        @GetMapping("/{id:\\d+}")  // This regex ensures that the id must be digits only
        public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
            Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
            return ResponseEntity.ok(actor);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actorDetails) {
            Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
            actor.setName(actorDetails.getName());
            actor.setBio(actorDetails.getBio());
            Actor updatedActor = actorRepository.save(actor);
            return ResponseEntity.ok(updatedActor);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
            Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
            actorRepository.delete(actor);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/deleteAllActors")
        public ResponseEntity<Void> deleteAllActors() {
            actorRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }
    }
