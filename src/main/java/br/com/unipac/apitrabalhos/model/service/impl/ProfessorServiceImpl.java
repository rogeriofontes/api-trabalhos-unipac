package br.com.unipac.apitrabalhos.model.service.impl;

import br.com.unipac.apitrabalhos.model.domain.Professor;
import br.com.unipac.apitrabalhos.model.repository.ProfessorRepository;
import br.com.unipac.apitrabalhos.model.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    @Cacheable("ProfessorCache")
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    @Cacheable("ProfessorCache")
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor edit(Long id, Professor professor) {
        Optional<Professor> result = findById(id);

        if (result.isPresent()) {
            Professor professorParaUpdate = result.get();
            professorParaUpdate.setNome(professor.getNome());

            return professorRepository.save(professor);
        }

        return new Professor();
    }

    @Override
    public boolean remove(Long id) {
        try {
            professorRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
