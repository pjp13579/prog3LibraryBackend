package com.example.demo.domain.Entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;


//@Entity(name="publications") //table name

@MappedSuperclass
public abstract class Publication {
	
	@Column(name="nPages")
	protected Integer nPages;
	@Column(name="title")
	protected String title;
	@Column(name="author")
	protected String author;
	@Enumerated(EnumType.STRING)
	@Column(name="category")
	protected PublicationCategoryEnums category;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="publicationDate")
	protected Date publicationDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="rentLimit")
	protected Date rentLimit;
	
	public Publication() {
		super();
	}

	public Publication(Integer nPages, String title, PublicationCategoryEnums category, Date publicationDate, String author) {
		super();
		this.nPages = nPages;
		this.title = title;
		this.category = category;
		this.publicationDate = publicationDate;
		this.author = author;
		this.rentLimit = new Date();
	}

	public Integer getnPages() {
		return nPages;
	}

	public void setnPages(Integer nPages) {
		this.nPages = nPages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PublicationCategoryEnums getCategory() {
		return category;
	}

	public void setPublicationCategoryEnums(PublicationCategoryEnums category) {
		this.category = category;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public Date getRentLimit() {
		return rentLimit;
	}

	public void setRentLimit(Date rentLimit) {
		this.rentLimit = rentLimit;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
