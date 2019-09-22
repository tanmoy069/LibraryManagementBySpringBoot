package com.library.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.dao.BookRepository;
import com.library.dao.LibrarianRepository;
import com.library.dao.StudentRepository;
import com.library.model.Book;
import com.library.model.Librarian;
import com.library.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryManagementApplicationTests {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private LibrarianRepository librnRepo;
	
	@Autowired
	private StudentRepository stdRepo;
	
	@Test
	public void testBookSave() {
		bookRepo.save(getBook());
		System.out.println("Book added successfully");
	}
	
	private Book getBook() {
		Book ob = new Book();
		ob.setBookName("Teach Yourself C");
		ob.setAuthorName("SCHILDT");
		ob.setCategory("Programming");
		ob.setTotalNoOfBooks(10);
		ob.setAvailableBooks(10);
//		ob.setBookId(3l);
		return ob;
	}
	
	@Test
	public void testFindAllBooks() {
		for (Book ob : bookRepo.findAll()) {
			System.out.println(ob.toString());
		}
	}
	
	@Test
	public void testFindAllStudent() {
		for (Student ob : stdRepo.findAll()) {
			System.out.println(ob.toString());
		}
	}
	
	@Test
	public void testFindAllLibrarian() {
		for (Librarian ob : librnRepo.findAll()) {
			System.out.println(ob.toString());
		}
	}
	
	@Test
	public void testFindLibrarianByEmail() {
		System.out.println(librnRepo.findByEmail("rofg.abdul@gmail.com").toString());
	}

}
