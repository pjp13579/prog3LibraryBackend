package com.example.demo.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Entities.Book;
import com.example.demo.domain.Entities.Magazine;
import com.example.demo.domain.Entities.Publication;
import com.example.demo.domain.Entities.PublicationCategoryEnums;


@Service
public class PublicationsService {
//Optional<Book | Magazine> has 1 problem, method I made are incompatible with it
	
	@Autowired
	private PublicationRepositoryBook repBook;
	@Autowired
	private PublicationRepositoryMagazine repMagazine;
	
	
	public Iterable<Book> getBooks() {
		return repBook.findAll();
	}
	public Iterable<Magazine> getMagazines() {
		return repMagazine.findAll();
	}

	// public Iterable<Publication> getPublication(String type){
	// 	List<Publication> l = new ArrayList<>();
	// 	if(type.equalsIgnoreCase("book")){
	// 		for (Publication publication : repBook.findAll()) {
	// 			l.add(publication);
	// 		}
	// 		return l ;
	// 	}
	// 	for (Publication publication : repBook.findAll()) {
	// 		l.add(publication);
	// 	}
	// 	return l;
	// }

	// public List<Publication> getPublication(String type){
	// 	List<Publication> stuff = new ArrayList<>();
		
	// 	if(type.equalsIgnoreCase("book")){
	// 		repBook.findAll().forEach(str -> stuff.add(str));	
	// 		return stuff;
	// 	}
	// 	repMagazine.findAll().forEach(stuff::add);
	// 	return stuff;
	// }

	public Iterable<? extends Publication> getPublication(String type){
		if(type.equalsIgnoreCase("book"))
			return repBook.findAll();
		if(type.equalsIgnoreCase("magazine"))
			return repMagazine.findAll();
		return null;
	}

	public Book saveBook(Book book) {
		return repBook.save(book);
	}
	
	public Magazine saveMagazine(Magazine magazine) {
		return repMagazine.save(magazine);
	}
	
	
	public void deleteBook(Integer id) {
		repBook.deleteById(id);
	}
	
	public void deleteMagazine(Integer id) {
		repMagazine.deleteById(id);
	}
	
	
	public Book updateBook(Book book) {
		return repBook.save(book);
	}
	
	public Magazine updateMagazine(Magazine magazine) {
		return repMagazine.save(magazine);
	}

	
	public Optional<Book> getBookByIsbn(Integer isbn) {
		return repBook.findById(isbn);
	}
	
	public Iterable<Book> getAvailableBooksForRent(){
		Iterable<Book> book = repBook.findAll();
		List<Book> list = new ArrayList<Book>();
		//for each book, get those whose RentLimitDate is NULL, meaning those that aren't rented  
		for(Book b: book) {
			if(b.getRentLimit() == null)
				list.add(b);
		}
			Iterable<Book> iter = list;
		return iter;
	}
	
	public Iterable<Magazine> getAvailableMagazinesForRent(){
		Iterable<Magazine> magazine = repMagazine.findAll();
		List<Magazine> list = new ArrayList<Magazine>();
		//for each magazine, get those whose RentLimitDate is NULL, meaning those that aren't rented  
		for(Magazine m: magazine) {
			if(m.getRentLimit() == null)
				list.add(m);
		}
			Iterable<Magazine> iter = list;
		return iter;
	}
	
	//Rents a book, if it isn't currently rented
	public Book rentBook(Integer isbn, Date rentLimit) {
		Book book = repBook.findBookByIsbn(isbn);
		if(book != null && book.getRentLimit() != null) 
			return null;
		//change the value of the rent of the object
		book.setRentLimit(rentLimit);
		//updates the object on the database
		repBook.save(book);	
		return book;
	}
	
	//Rents a magazine, if it isn't currently rented
	public Magazine rentMagazine(Integer issn, Date rentLimit) {
		Magazine magazine= repMagazine.findMagazineByIssn(issn);
		if(magazine != null && magazine.getRentLimit() != null) 
			return null;
		//change the value of the rent of the object
		magazine.setRentLimit(rentLimit);
		//updates the object on the database
		repMagazine.save(magazine);		
		return magazine;
	}
	
	//This function is pure trash code.
	//the idea here if compare all the books isbn, to the available books isbn
	//for so 2 list, one with each info, completely unnecessary because we could compare directly on the Iterable<>
	//Then we remove the available books from the all books and whatever is left, is not available
	//super inefficient, i know
	public Iterable<Book> getOutdatedRentsBooks(){
		Iterable<Book> allBooks = getBooks();
		Iterable<Book> availableBooks = getAvailableBooksForRent();

		ArrayList<Integer> allBooksList = new ArrayList<>();
		ArrayList<Integer> avalBooksList = new ArrayList<>();
		
		for(Book book: allBooks) {
			allBooksList.add(book.getIsbn());
		}
		for(Book book: availableBooks) {
			avalBooksList.add(book.getIsbn());
		}
		for(Book book: availableBooks) {
			if(allBooksList.contains(book.getIsbn()))
				allBooksList.remove(book.getIsbn());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Book> rentedBooks = (List) repBook.findAllById(allBooksList);
		List<Book> outatedBooks = new ArrayList<>();
		
		for(Book rentedBook: rentedBooks) {
			Date limit =  rentedBook.getRentLimit();
			Date today = new Date();
			//if limit occours befora today
			if(limit.compareTo(today) < 0) {
				outatedBooks.add(rentedBook);
			}
		}
		Iterable<Book> iter = outatedBooks;
		return iter;
	}
	
	//This function is pure trash code.
	//the idea here if compare all the magazines issn, to the available magazines issn
	//for so 2 list, one with each info, completely unnecessary because we could compare directly on the Iterable<>
	//Then we remove the available magazines from the all magazines and whatever is left, is not available
	//super inefficient, i know. It works, though.
	public Iterable<Magazine> getOutdatedRentsMagazines(){
		Iterable<Magazine> allMagazines = getMagazines();
		Iterable<Magazine> availableMagazines = getAvailableMagazinesForRent();
		ArrayList<Integer> allMagazinesList = new ArrayList<>();
		ArrayList<Integer> avalMagazinesList = new ArrayList<>();
		
		for(Magazine magazine: allMagazines) {
			allMagazinesList.add(magazine.getIssn());
		}
		for(Magazine magazine: availableMagazines) {
			avalMagazinesList.add(magazine.getIssn());
		}
		for(Magazine magazine: availableMagazines) {
			if(allMagazinesList.contains(magazine.getIssn()))
				allMagazinesList.remove(magazine.getIssn());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Magazine> rentedMagazines = (List) repMagazine.findAllById(allMagazinesList);
		List<Magazine> outatedMagazines = new ArrayList<>();
		
		for(Magazine mag: rentedMagazines) {
			Date limit = mag.getRentLimit();
			Date today = new Date();
			
			if(limit.compareTo(today) < 0) {
				outatedMagazines.add(mag);
			}
		}
		
		Iterable<Magazine> iter = outatedMagazines;
		return iter;
	}
	
	public Iterable<PublicationCategoryEnums> getCategories(){
		List<PublicationCategoryEnums> enums = new ArrayList<>();
		
		for(PublicationCategoryEnums cat: PublicationCategoryEnums.values()) {
			enums.add(cat);
		}
		Iterable<PublicationCategoryEnums> iter = enums;
		return iter;
	}
	
	public Book returnRentBook(Integer isbn) {
		Book book = repBook.findBookByIsbn(isbn);
		book.setRentLimit(null);
		repBook.save(book);
		return book;
	}
	
	public Magazine returnRentMagazine(Integer issn) {
		Magazine magazine = repMagazine.findMagazineByIssn(issn);
		magazine.setRentLimit(null);
		repMagazine.save(magazine);
		return magazine;
	}
	
}






































