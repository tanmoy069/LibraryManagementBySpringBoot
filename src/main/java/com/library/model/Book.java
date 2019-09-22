package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "L_BOOK")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "book_id", updatable = false)
	private long bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "author_name")
	private String authorName;
	
	@Column(name = "book_category")
	private String category;
	
	@Column(name = "total_no_of_books")
	private int totalNoOfBooks;
	
	@Column(name = "available_books")
	private int availableBooks;
}
