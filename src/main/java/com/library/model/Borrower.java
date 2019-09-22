package com.library.model;

import java.time.LocalDate;

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
@Table(name = "L_BORROWER")
public class Borrower {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "borrower_id", updatable = false)
	private long borrowerId;
	
	@Column(name = "book_id")
	private long bookId;
	
	@Column(name = "std_id")
	private long stdId;
	
	@Column(name = "librn_id")
	private long librnId;
	
	@Column(name = "dtt_borrow")
	private LocalDate dateOfBorrow;
	
	@Column(name = "dtt_return")
	private LocalDate lastDateOfReturn;
	
	@Column(name = "status")
	private boolean status;	
}
