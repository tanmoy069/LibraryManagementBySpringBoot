package com.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
