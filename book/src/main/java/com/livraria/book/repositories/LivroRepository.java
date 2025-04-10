package com.livraria.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.book.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
