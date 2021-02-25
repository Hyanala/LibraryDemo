package com.library.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.demo.model.Book;
import com.library.demo.repo.BooksRepository;

@Service
public class BooksService {
	@Autowired
	private BooksRepository booksRepository;

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		booksRepository.findAll().forEach(books1 -> books.add(books1));
		return books;
	}

	public Optional<Book> getBooksById(int id) {
		return booksRepository.findById(id);
	}

	public void saveOrUpdate(Book books) {
		booksRepository.save(books);
	}

	public void delete(Book book) {
		booksRepository.delete(book);
	}

	public void update(Book books, int bookid) {
		booksRepository.save(books);
	}
}