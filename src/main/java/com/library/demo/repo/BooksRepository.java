package com.library.demo.repo;
import org.springframework.data.repository.CrudRepository;

import com.library.demo.model.Book;
public interface BooksRepository extends CrudRepository<Book, Integer>
{
}
