package br.com.unipac.apitrabalhos.recoursos;

import br.com.unipac.apitrabalhos.model.domain.Professor;
import br.com.unipac.apitrabalhos.model.repository.ProfessorRepository;
import br.com.unipac.apitrabalhos.model.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/professors")
public class ProfessorRecursos {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable("id") Long id){
        Optional<Professor> professor = professorService.findById(id);

        if (!professor.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professor.get());
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessorList(){
        List<Professor> professors = professorService.findAll();
        if (professors.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professors);
    }

    @PostMapping
    @CacheEvict(value = "ProfessorCache", allEntries = true)
    public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor){
        log.info("Gravou o professor: " +professor.toString());

        Professor saved = professorService.save(professor);

        URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(url).body(saved);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "ProfessorCache", allEntries = true)
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") Long id, @RequestBody Professor novoProfessor){
        Professor professorAlterado = professorService.edit(id, novoProfessor);

        if (professorAlterado != null) {
            return ResponseEntity.ok(professorAlterado);
        } else {
            log.info("Professor nao pode ser alterado: " + professorAlterado.toString());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "ProfessorCache", allEntries = true)
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        boolean remove = professorService.remove(id);
        if (remove) {
            return ResponseEntity.ok("Dados Deletados");
        } else{
            return ResponseEntity.noContent().build();
        }
    }

}
