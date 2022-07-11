package com.example.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Entities.Book;
import com.example.demo.domain.Entities.Magazine;
import com.example.demo.domain.Entities.PublicationCategoryEnums;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/publications")
public class BooksController {

	@Autowired
	private PublicationsService repo;

	@GetMapping("/categories")
	public Iterable<PublicationCategoryEnums> getCategories() {
		return repo.getCategories();
	}

	// /*@GetMapping("/book")
	// public Iterable<Book> getBook(){
	// return repo.getBooks();
	// }

	// @GetMapping("/magazine")
	// public Iterable<Magazine> getMagazine(){
	// return repo.getMagazines();
	// }*/

	@GetMapping("/type/{type}")
	public ResponseEntity<?> getPublication(@PathVariable String type) {
		if (repo.getPublication(type) != null)
			return new ResponseEntity<>(repo.getPublication(type), HttpStatus.OK);
		return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/book/{isbn}")
	public Optional<Book> getByIsbn(@PathVariable("isbn") Integer isbn) {
		return repo.getBookByIsbn(isbn);
	}

	@GetMapping("/book/availableBooksForRent")
	public Iterable<Book> getAvailableBooksForRent() {
		return repo.getAvailableBooksForRent();
	}

	@GetMapping("/magazine/availableMagazinesForRent")
	public Iterable<Magazine> getAvailableMagazinesForRent() {
		return repo.getAvailableMagazinesForRent();
	}

	@GetMapping("/book/outdatedRents")
	public Iterable<Book> getOutdatedBooks() {
		return repo.getOutdatedRentsBooks();
	}

	@GetMapping("/magazine/outdatedRents")
	public Iterable<Magazine> getOutdatedMagazines() {
		return repo.getOutdatedRentsMagazines();
	}

	@PostMapping("/book")
	public Book postBook(@RequestBody Book book) {
		Book b = repo.saveBook(book);
		return b;
	}

	@PostMapping("/magazine")
	public Magazine postMagazine(@RequestBody Magazine magazine) {
		Magazine m = repo.saveMagazine(magazine);
		return m;
	}

	@DeleteMapping("/book")
	public void deleteBook(@RequestParam Integer id) {
		repo.deleteBook(id);
	}

	@DeleteMapping("/magazine")
	public void deleteMagazine(@RequestParam Integer id) {
		repo.deleteMagazine(id);
	}

	@PatchMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return repo.updateBook(book);
	}

	@PatchMapping("/magazine")
	public Magazine updateMagazine(@RequestBody Magazine magazine) {
		return repo.saveMagazine(magazine);
	}

	@PatchMapping("/book/setRent")
	public Book setRentLimitBook(@RequestParam Integer isbn, @RequestParam String rentLimit) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rentLimitDate = sdf.parse(rentLimit);

		return repo.rentBook(isbn, rentLimitDate);
	}

	@PatchMapping("/magazine/setRent")
	public Magazine setRentLimitMagazine(@RequestParam Integer issn, @RequestParam String rentLimit) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rentLimitDate = sdf.parse(rentLimit);

		return repo.rentMagazine(issn, rentLimitDate);
	}

	@PatchMapping("/book/returnRentBook/{isbn}")
	public Book returnRentBook(@PathVariable Integer isbn) {
		return repo.returnRentBook(isbn);
	}

	@PatchMapping("/magazine/returnRentMagazine/{issn}")
	public Magazine returnRentMagazine(@PathVariable Integer issn) {
		return repo.returnRentMagazine(issn);
	}

}
