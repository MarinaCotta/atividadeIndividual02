package com.dac.marina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dac.marina.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
