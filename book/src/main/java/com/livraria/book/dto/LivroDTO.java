package com.livraria.book.dto;

import com.livraria.book.entities.Livro;

public class LivroDTO {

private int bookId;
	
	private String nomeLivro;
	private String nomeAutor;
	
	//Constructors
	public LivroDTO() {
		
	}
	public LivroDTO(int bookId, String nomeLivro, String nomeAutor) {
		this.bookId = bookId;
		this.nomeLivro = nomeLivro;
		this.nomeAutor = nomeAutor;
	}
	
	public LivroDTO(Livro livro) {
		bookId = livro.getBookId();
		nomeLivro = livro.getNomeLivro();
		nomeAutor = livro.getNomeAutor();
	}

	//Getters and Setters
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	
}
