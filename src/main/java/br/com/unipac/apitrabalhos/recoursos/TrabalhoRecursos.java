package br.com.unipac.apitrabalhos.recoursos;

import br.com.unipac.apitrabalhos.model.domain.Trabalho;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trabalhos")
public class TrabalhoRecursos {
    List<Trabalho> trabalhoList = new ArrayList<>();

    @GetMapping("/id")
    public Trabalho getTrabalho(){
        Trabalho trabalho = new Trabalho();
        trabalho.setId(1l);
        trabalho.setData(new Date());
        trabalho.setProfessor("Daniel");
        trabalho.setTituloDoTrabalho("Criar API web");

        return trabalho;
    }

    @GetMapping
    public List<Trabalho> getTrabalhoList(){
        return trabalhoList;
    }

    @PostMapping
    public void addTrabalho(@RequestBody Trabalho trabalho){
        System.out.println("Gravou o trabalho: " +trabalho.toString());
        trabalhoList.add(trabalho);
    }
}
