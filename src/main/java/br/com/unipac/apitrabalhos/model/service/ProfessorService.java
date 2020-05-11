package br.com.unipac.apitrabalhos.model.service;

import br.com.unipac.apitrabalhos.model.domain.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<Professor> findAll();
    Optional<Professor> findById(Long id);
    Professor save(Professor professor);
    Professor edit(Long id, Professor professor);
    boolean remove(Long id);
}
