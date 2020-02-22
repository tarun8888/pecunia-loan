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
	Loan loan3 = new Loan("123",1200,50,12,90);
	
	
	@BeforeEach
	public void setUp() {
		loanDao = new LoanDaoImpl();
		serviceDao = new LoanServiceImpl();
	}
	
	@Test
	void testUserWithException() throws LoanException{
		assertThrows(LoanException.class, ()-> loanDao.loanApprovalStatus(loan3,"1234"));
		
	}
	
	@Test
	void testLoanApprovalStatus() throws LoanException{
		String status1 = loanDao.loanApprovalStatus(loan1,"123456789012");
		assertEquals("approved", status1);
	
	}
		
	
	@Test
	
	void testValidateCreditScore() throws LoanException{
		
		boolean status2 = serviceDao.validateCreditScore(50);
		assertEquals(false, status2);
		
	}
	
	
	@Test
	void testValidateAmount() throws LoanException{
		boolean status1 = serviceDao.validateAmount(1000000);
		assertEquals(true, status1);
		
	}
	
	
	
	
	@Test
	void testValidateRateOfInterest2() throws LoanException{
		boolean status2 = serviceDao.validateAmount(5.5);
		assertEquals(false, status2);
		
	}
	
	@Test
	void testValidateTenure() throws LoanException {
		boolean status1 = serviceDao.validateTenure(120);
		assertEquals(true, status1);
		
	}
	
	
	
	

}
