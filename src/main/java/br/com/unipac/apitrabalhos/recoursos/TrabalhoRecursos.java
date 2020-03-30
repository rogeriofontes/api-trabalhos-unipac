package br.com.unipac.apitrabalhos.recoursos;

import br.com.unipac.apitrabalhos.model.domain.Trabalho;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trabalhos")
public class TrabalhoRecursos {
    List<Trabalho> trabalhoList = new ArrayList<>();

    @GetMapping("/{id}")
    public ResponseEntity<Trabalho> getTrabalho(@PathVariable("id") int id){
        Trabalho trabalho = trabalhoList.get(id);

         if (trabalho == null) {
            return ResponseEntity.noContent().build();
        }
        //early return
        return ResponseEntity.ok(trabalho);
    }

    @GetMapping
    public ResponseEntity<List<Trabalho>> getTrabalhoList(){
        if (trabalhoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trabalhoList);
    }

    @PostMapping
    public ResponseEntity<Trabalho> addTrabalho(@RequestBody Trabalho trabalho){
        System.out.println("Gravou o trabalho: " +trabalho.toString());
        trabalhoList.add(trabalho);

        URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trabalho.getId()).toUri();
        return ResponseEntity.created(url).body(trabalho);
    }

    @PutMapping
    public ResponseEntity<List<Trabalho>> updateTrabalho(@PathVariable("id") int id, @RequestBody Trabalho novoTrabalho){
        Trabalho trabalho = trabalhoList.remove(id);
        trabalhoList.add(novoTrabalho);

        return ResponseEntity.ok(trabalhoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        try {
            trabalhoList.remove(id);
            return ResponseEntity.ok("Dados Deletados");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados nao pode ser removidos" + e.getMessage());
        }
    }

}
