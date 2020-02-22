package com.cg.pecunia.service;

import java.util.List;

import com.cg.pecunia.bean.Loan;
import com.cg.pecunia.exception.LoanException;

public interface LoanService {
	public Loan addLoanDetails(String accountId, double amount, int tenure, double rateOfInterest, int creditScore ) throws LoanException;
	public String createLoanRequest(Loan loan) throws LoanException;
	public String loanApprovalStatus(Loan loan, String accountNumber) throws LoanException;
	public double calculateEmiForLoan(Loan loan) throws LoanException;
	public List<Loan> loanRequestList() throws LoanException;
	public List<Loan> loanApprovalList(Loan loan) throws LoanException;
	public boolean validateAmount(double amount)throws LoanException;
	public boolean validateTenure(int tenure) throws LoanException;
	public boolean validateCreditScore(int creditScore) throws LoanException;
	public boolean validateNumber(String number) throws LoanException;
	public boolean validateRateOfInterest(double rateOfInterest) throws LoanException;

}
