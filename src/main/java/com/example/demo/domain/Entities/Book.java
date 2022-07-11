package com.example.demo.domain.Entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="book")
public class Book extends Publication{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer isbn;
	@Column(name="publisher")
	protected String publisher;
	
	public Book(){
		super();
	}

	public Book(Integer nPages, String title, PublicationCategoryEnums category, Date publicationDate, String author, Integer isbn, String publisher) {
		super(nPages, title, category, publicationDate, author);
		this.isbn = isbn;
		this.publisher = publisher;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}	
	
	
	
	
}
