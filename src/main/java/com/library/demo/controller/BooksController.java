package com.library.demo.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.demo.exception.BookNotFoundException;
import com.library.demo.model.Book;
import com.library.demo.service.BooksService;


@RestController("/api")
public class BooksController {
	
	@Autowired
	private BooksService booksService;

	@ApiOperation(value = "Gets all books data", notes="Gets all books data")
	@GetMapping("/book")
	private ResponseEntity<List<Book>> getAllBooks() {
		return new ResponseEntity<>(booksService.getAllBooks(),HttpStatus.OK);
	}

	@ApiOperation(value = "Gets Books by id", notes="Gets Books by id")
	@GetMapping("/book/{bookid}")
	private ResponseEntity<Book> getBooks(@PathVariable("bookid") int bookid) throws BookNotFoundException{
		Book book = booksService.getBooksById(bookid)
		        .orElseThrow(() -> new BookNotFoundException("Book not found for this id : " + bookid));
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	@ApiOperation(value = "Delete books", notes="Delete books")
	@DeleteMapping("/book/{bookid}")
	private void deleteBook(@PathVariable("bookid") int bookid) throws BookNotFoundException {
		Book book = booksService.getBooksById(bookid)
		        .orElseThrow(() -> new BookNotFoundException("Book not found for this id : " + bookid));
		booksService.delete(book);
	}
	@ApiOperation(value = "Save books data", notes="Save books data")
	@PostMapping("/books")
	private int saveBook(@RequestBody Book book) {
		booksService.saveOrUpdate(book);
		return book.getBookid();
	}
	@ApiOperation(value = "Update book data", notes="Update book data")
	@PutMapping("/books")
	private ResponseEntity<Book> update(@RequestBody Book book) throws BookNotFoundException {
		booksService.getBooksById(book.getBookid())
		        .orElseThrow(() -> new BookNotFoundException("Book not found for this id : " + book.getBookid()));
		booksService.saveOrUpdate(book);
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
}
