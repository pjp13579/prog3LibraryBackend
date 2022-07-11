package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Entities.Book;
import com.example.demo.domain.Entities.PublicationCategoryEnums;


public interface PublicationRepositoryBook extends CrudRepository<Book, Integer> {
	
	Book findBookByIsbn(Integer isbn);
	Iterable<Book> findBookByTitle(String title);
	Iterable<Book> findBookByCategory(PublicationCategoryEnums category);
	Iterable<Book> findBookByPublicationDate(String publicationDate);
	Iterable<Book> findBookByAuthor(String author);
	Iterable<Book> findBookByPublisher(String publisher);
	

}
