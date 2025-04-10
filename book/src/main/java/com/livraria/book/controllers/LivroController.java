package com.livraria.book.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.book.dto.LivroDTO;
import com.livraria.book.entities.Livro;
import com.livraria.book.services.LivroService;

@RestController
@RequestMapping(value = "livro")
public class LivroController {

	@Autowired
	LivroService livroService;
	
	@GetMapping(value = "/{bookId}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable int bookId) {
        Livro livro = livroService.buscarLivroPorId(bookId);

        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable int bookId,@RequestBody LivroDTO livroDTO) {
        
        Optional<LivroDTO> livroAtualizado = livroService.atualizarLivro(bookId, livroDTO);
        
        if (livroAtualizado.isPresent()) {
            return ResponseEntity.ok(livroAtualizado.get());
        } 
        else {
            return ResponseEntity.notFound().build();
        }
    }
	
}
