package br.com.alura.comex.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

public abstract class EntitiesController {

    @DeleteMapping("/{id}")
    @Transactional
    public abstract ResponseEntity<?> deletar(@PathVariable Long id, JpaRepository repository);

}
