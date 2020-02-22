package com.cg.pecunia.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.pecunia.bean.Loan;
import com.cg.pecunia.exception.LoanException;
import com.cg.pecunia.service.LoanService;
import com.cg.pecunia.service.LoanServiceImpl;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		Loan loan = null;
		LoanService loanService = new LoanServiceImpl();
		List<Loan> list= null;
		while(true) {
			
			System.out.println("\n1. Add Details for Loan");
			System.out.println("2. Loan Approval Status");
			System.out.println("3. Calculate Loan EMI");
			System.out.println("4. Loan approval list");
			System.out.println("5. List Loan Requests");
			System.out.println("6. Exit");
			
			
			int choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				try {
					
					System.out.println("Enter Exsisting Account Number");
					String id = scanner.next();
					System.out.println("Enter Loan amount");
					double amount = scanner.nextDouble();
					System.out.println("Enter tenure in months");
					int tenure = scanner.nextInt();
					System.out.println("Enter rate of interest");
					double roi = scanner.nextDouble();
					System.out.println("Enter credit score");
					int score = scanner.nextInt();
					loan = loanService.addLoanDetails(id, amount, tenure, roi, score);
					loan.setAccountBalance(10000);
					System.out.println("(Account Number:"+loan.getAccountId()+") Details added sucessfully for loan");
					String sid = loanService.createLoanRequest(loan);
					System.out.println("(Account Number:"+sid+") requested for loan of "+ loan.getAmount()+" for "+loan.getTenure()+" months");
	
				}
				catch (LoanException le) {
					System.err.println(le.getMessage());
				}
				break;
				
			case 2:
				try {
					System.out.println("(Account Number:"+loan.getAccountId()+") Loan Status = "+loanService.loanApprovalStatus(loan,loan.getAccountId()));
					
				}
				catch(LoanException le) {
					System.err.println(le.getMessage());
				}
				break;
			
			case 3:
				try {
					System.out.println("(Account Number:"+loan.getAccountId()+") emi = "+loanService.calculateEmiForLoan(loan));
					
				}
				catch(LoanException le) {
					System.err.println(le.getMessage());
				}
				break;
				
			case 4:
				try {
					list = new ArrayList<>();
					list = loanService.loanRequestList();
					System.out.println("Account ID\t\tTenure\t\tLoan Amount\t\tRate Of Interest\t\tCredit Score\t\tLoan Status\t\tAccount Balance\t\tEMI\n");
					list.stream().forEach(p -> System.out.println(p));
					
					
					
				}
				catch(LoanException le) {
					System.err.println(le.getMessage());
				}
				break;
				
			
			case 5:
				try {
					list = new ArrayList<Loan>();
					list = loanService.loanApprovalList(loan);
					
					System.out.println("Account ID\t\tTenure\t\tLoan Amount\t\tRate Of Interest\t\tCredit Score\t\tLoan Status\t\tAccount Balance\t\tEMI\n");
					list.stream().forEach(p -> System.out.println(p));
					
				}
				catch(LoanException le) {
					System.err.println(le.getMessage());
				}
				break;
			
			case 6:
				System.out.println("Thank You! :) ");
				return;

			default:
				System.out.println("enter correct choice");
				break;
			}
		}

	}

}
