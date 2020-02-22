package com.cg.pecunia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.pecunia.bean.Loan;
import com.cg.pecunia.dao.LoanDao;
import com.cg.pecunia.dao.LoanDaoImpl;
import com.cg.pecunia.exception.LoanException;


class pecuniaLoanTest {

	LoanDao loanDao = null;
	Loan loan1 = new Loan("123456789012",24,240000,11.5,888);
	Loan loan2 = new Loan("123456789013",120,5000000,13.3,500);
	
	
	@BeforeEach
	public void setUp() {
		loanDao = new LoanDaoImpl();
	}
	
	@Test
	void testLoanApprovalStatus() throws LoanException{
		String status1 = loanDao.loanApprovalStatus(loan1);
		String status2 = loanDao.loanApprovalStatus(loan2);
		assertEquals("approved", status1);
		assertEquals("rejected", status2);
	}
	
	

}
