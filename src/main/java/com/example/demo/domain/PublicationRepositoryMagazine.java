package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Entities.Book;
import com.example.demo.domain.Entities.Magazine;
import com.example.demo.domain.Entities.Publication;


public interface PublicationRepositoryMagazine extends CrudRepository<Magazine, Integer> {
	
	Magazine findMagazineByIssn(Integer issn);
	Iterable<Publication> findMagazineByTitle(String title);
	Iterable<Publication> findMagazineByCategory(String category);
	Iterable<Publication> findMagazineByPublicationDate(String publicationDate);
	Iterable<Publication> findMagazineByAuthor(String author);
	Iterable<Publication> findMagazineByIssn(String issn);

}
