package com.livraria.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.book.dto.LivroDTO;
import com.livraria.book.entities.Livro;
import com.livraria.book.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;

    private List<Livro> cacheLivros = new ArrayList<>();

    //Adicionar um livro ao cache
    private void addLivroToCache(Livro livro) {
        //Verifica se já existe no cache
        for (int i = 0; i < cacheLivros.size(); i++) {
            if (cacheLivros.get(i).getBookId() == livro.getBookId()) {
            	//Atualiza o livro no cache
                cacheLivros.set(i, livro);
                return;
            }
        }
        //Se não existir adiciona no cache
        cacheLivros.add(livro);
    }
	
	//Buscar
    public Livro getLivroInfo(int bookId) {
        //Verifica se livro já está no cache
        for (Livro livro : cacheLivros) {
            if (livro.getBookId() == bookId) {
                System.out.println("Dados vindo do cache...");
                return livro;
            }
        }

        //Se o livro não estiver no cache busca no banco
        System.out.println("Dados vindo do banco de dados...");
        Optional<Livro> livroOpt = livroRepository.findById(bookId);

        //Se não existir adiciona ao cache e retorna
        if (livroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            addLivroToCache(livro);
            return livro;
        }

        //Se o livro não for encontrado retorna nulo
        return null;
    }

	
	//Atualizar
    public Optional<LivroDTO> updateLivro(int bookId, LivroDTO dto) {
        Optional<Livro> livroOpt = livroRepository.findById(bookId);
        
        if (livroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            livro.setNomeAutor(dto.getNomeAutor());
            livro.setNomeLivro(dto.getNomeLivro());

            //Atualiza o livro no banco de dados
            livroRepository.save(livro);

            // Atualiza o livro no cache
            addLivroToCache(livro);

            return Optional.of(new LivroDTO(livro.getBookId(), livro.getNomeAutor(), livro.getNomeLivro()));
        }
        
        return Optional.empty();
    }
	
	
}
