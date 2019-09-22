package com.library.controller;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.dao.BookRepository;
import com.library.dao.BorrowerRepository;
import com.library.dao.LibrarianRepository;
import com.library.dao.StudentRepository;
import com.library.model.Book;
import com.library.model.Borrower;
import com.library.model.Librarian;
import com.library.model.Student;

@Controller
public class LibrarianLoginController {
	
	@Autowired
	private LibrarianRepository librnRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private BorrowerRepository borrowerRepo;
	
	@Autowired
	private StudentRepository stdRepo;
	
	@RequestMapping(value="/library", method=RequestMethod.GET)
	public String getLoginForm() {
		return "login";
	}
	
	@RequestMapping(value="/library", method=RequestMethod.POST)
	public String login(@ModelAttribute(name="libraryForm") Librarian librn, Model model) {
		
		String email = librn.getEmail();
		String password = librn.getPassword();
		
		Librarian ob = librnRepo.findByEmail(email);
		if (ob == null) System.out.println("Wrong Email");
		if (!ob.getPassword().equals(password)) System.out.println("Wrong Password");
		if (ob.getEmail().equals(email) && ob.getPassword().equals(password)) {
			model.addAttribute("librnObj", librnRepo.findByEmail(email));
			model.addAttribute("bookObj", bookRepo.findAll());
			model.addAttribute("borrowObj", borrowerRepo.findAll());
			return "home";
		}
		model.addAttribute("invalidCredentials", true);
		return "login";
	}
	
	@RequestMapping(value="/library/book-search", method=RequestMethod.GET)
	public String getBookSearch(Model model) {
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "book-search";
	}
	
	@RequestMapping(value="/library/book-search", method=RequestMethod.POST)
	public String getBookByName(@ModelAttribute(name="findBookForm") Book ob, Model model) {
		model.addAttribute("findBookByName", bookRepo.findByBookNameContains(ob.getBookName()));
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "book-search";
	}
	
	@RequestMapping(value="/library/std-search", method=RequestMethod.GET)
	public String getStudentSearch(Model model) {
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "std-search";
	}
	
	@RequestMapping(value="/library/std-search", method=RequestMethod.POST)
	public String getStudentById(@ModelAttribute(name="findStudentForm") Student ob, Model model) {
		model.addAttribute("findStudentById", stdRepo.findByStudentId(ob.getStudentId()));
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "std-search";
	}
	
	@RequestMapping(value="/library/add-book", method=RequestMethod.GET)
	public String getAddBook(Model model) {
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "add-book";
	}
	
	@RequestMapping(value="/library/add-book", method=RequestMethod.POST)
	public String addBook(@ModelAttribute(name="addBookForm") Book ob, Model model) {
		ob.setAvailableBooks(ob.getTotalNoOfBooks());
		bookRepo.save(ob);
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		model.addAttribute("validCredentials", true);
		return "add-book";
	}
	
	@RequestMapping(value="/library/add-student", method=RequestMethod.GET)
	public String getAddStudent(Model model) {
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "add-student";
	}
	
	@RequestMapping(value="/library/add-student", method=RequestMethod.POST)
	public String addStudent(@ModelAttribute(name="addStudentForm") Student ob, Model model) {
		try {
			stdRepo.findByStudentId(ob.getStudentId()).getStudentId();
			model.addAttribute("invalidCredentials", true);
			model.addAttribute("bookObj", bookRepo.findAll());
			model.addAttribute("borrowObj", borrowerRepo.findAll());
			return "add-student";
		} catch (NullPointerException e) {
			stdRepo.save(ob);
			model.addAttribute("validCredentials", true);
			model.addAttribute("bookObj", bookRepo.findAll());
			model.addAttribute("borrowObj", borrowerRepo.findAll());
			return "add-student";
		}
	}
	
	@RequestMapping(value="/library/add-borrower", method=RequestMethod.GET)
	public String getAddBorrower(Model model) {
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "add-borrower";
	}
	
	@RequestMapping(value="/library/add-borrower", method=RequestMethod.POST)
	public String addBorrower(@ModelAttribute(name="addBorrowerForm") Borrower ob, Model model) {
		try {
			stdRepo.findByStudentId(ob.getStdId()).getStudentId();
			Book book = bookRepo.findByBookId(ob.getBookId());
			if (book.getAvailableBooks() == 0) {
				model.addAttribute("notAvailable", true);
				model.addAttribute("bookObj", bookRepo.findAll());
				model.addAttribute("borrowObj", borrowerRepo.findAll());
				return "add-borrower";				
			}
			else {
				ob.setDateOfBorrow(LocalDate.now());
				ob.setStatus(true);
				borrowerRepo.save(ob);
				book.setAvailableBooks(book.getAvailableBooks() - 1);
				bookRepo.save(book);
				model.addAttribute("validCredentials", true);
				model.addAttribute("bookObj", bookRepo.findAll());
				model.addAttribute("borrowObj", borrowerRepo.findAll());
				return "add-borrower";
			}			
		} catch (NullPointerException e) {
			model.addAttribute("invalidCredentials", true);
			model.addAttribute("bookObj", bookRepo.findAll());
			model.addAttribute("borrowObj", borrowerRepo.findAll());
			return "add-borrower";
		}
		
	}
	
	@RequestMapping(value="/library/update-return", method=RequestMethod.GET)
	public String getUpdateReturn(Model model) {
		model.addAttribute("bookObj", bookRepo.findAll());
		model.addAttribute("borrowObj", borrowerRepo.findAll());
		return "update-return";
	}
	
	@RequestMapping(value="/library/update-return", method=RequestMethod.POST)
	public String updateReturn(@ModelAttribute(name="updateReturnForm") Borrower ob, Model model) {
		try {
			Borrower upObj = borrowerRepo.getOne(ob.getBorrowerId());
			upObj.setLastDateOfReturn(LocalDate.now());
			upObj.setStatus(false);
			borrowerRepo.save(upObj);
			Book book = bookRepo.getOne(upObj.getBookId());
			book.setAvailableBooks(book.getAvailableBooks() + 1);
			bookRepo.save(book);
			model.addAttribute("validCredentials", true);
			model.addAttribute("bookObj", bookRepo.findAll());
			model.addAttribute("borrowObj", borrowerRepo.findAll());
			return "update-return";			
		} catch (EntityNotFoundException e) {
			model.addAttribute("invalidCredentials", true);
			model.addAttribute("bookObj", bookRepo.findAll());
			model.addAttribute("borrowObj", borrowerRepo.findAll());
			return "update-return";
		}
	}
}
