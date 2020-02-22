package com.cg.pecunia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.pecunia.bean.Loan;
import com.cg.pecunia.dao.LoanDao;
import com.cg.pecunia.dao.LoanDaoImpl;
import com.cg.pecunia.exception.LoanException;
import com.cg.pecunia.service.LoanService;
import com.cg.pecunia.service.LoanServiceImpl;


class pecuniaLoanTest {

	LoanDao loanDao = null;
	LoanService serviceDao = null;
	
	Loan loan1 = new Loan("123456789012",24,240000,11.5,888);
	Loan loan2 = new Loan("123456789013",120,5000000,13.3,500);
	
	
	@BeforeEach
	public void setUp() {
		loanDao = new LoanDaoImpl();
		serviceDao = new LoanServiceImpl();
	}
	
	@Test
	void testLoanApprovalStatus() throws LoanException{
		String status1 = loanDao.loanApprovalStatus(loan1,"123456789012");
		String status2 = loanDao.loanApprovalStatus(loan2,"123456789021");
		assertEquals("approved", status1);
		assertEquals("rejected", status2);
	}
	
	@Test
	void testValidateCreditScore() {
		boolean status1 = serviceDao.validateCreditScore(900);
		boolean status2 = serviceDao.validateCreditScore(50);
		assertEquals(true, status1);
		assertEquals(false, status2);
		
	}
	
	@Test
	void testValidateAmount() {
		boolean status1 = serviceDao.validateAmount(1000000);
		boolean status2 = serviceDao.validateAmount(5);
		assertEquals(true, status1);
		assertEquals(false, status2);
	}
	
	@Test
	void testValidateRateOfInterest() {
		boolean status1 = serviceDao.validateRateOfInterest(11.11);
		boolean status2 = serviceDao.validateAmount(5.5);
		assertEquals(true, status1);
		assertEquals(false, status2);
	}
	
	@Test
	void testValidateTenure() {
		boolean status1 = serviceDao.validateTenure(120);
		boolean status2 = serviceDao.validateTenure(4);
		assertEquals(true, status1);
		assertEquals(false, status2);
		
	}
	

}
