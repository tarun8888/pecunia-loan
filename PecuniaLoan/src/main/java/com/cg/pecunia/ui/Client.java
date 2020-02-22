package com.cg.pecunia.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.pecunia.bean.Loan;
import com.cg.pecunia.exception.LoanException;
import com.cg.pecunia.service.LoanService;
import com.cg.pecunia.service.LoanServiceImpl;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
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
			
					String id = null;
					double amount = 0,roi=0;
					int tenure=0,score=0;
					try {
					System.out.println("Enter Exsisting Account Number");
					 id = scanner.next();
					 if(!loanService.validateNumber(id))
							 throw new LoanException("Invalid account Id : Id should be numeric 12 digit");

				
					System.out.println("Enter Loan amount");
					amount = scanner.nextDouble();
					if(!loanService.validateAmount(amount))
						throw new LoanException("Invalid Loan amount : Amount should be greater than 1000");
					
					System.out.println("Enter tenure in months (above 12 months)");
					tenure = scanner.nextInt();
					if(!loanService.validateTenure(tenure))
						 throw new LoanException("Invalid tenure: entered value is not valid");
					
					System.out.println("Enter rate of interest (above 7.0%)");
					roi = scanner.nextDouble();
					if(!loanService.validateRateOfInterest(roi))
						throw new LoanException("Invalid Interest rate : Rate of interest should be  in range of 4% to 15%");
					
					System.out.println("Enter credit score (above 100)");
					score = scanner.nextInt();
					if(!loanService.validateCreditScore(score))
						throw new LoanException("Invalid credit score : credit score should be in range of 100 to 999");
					
					
					System.out.println("proceed (Y/N)?");
					char proceed = scanner.next().charAt(0);
					if(proceed == 'Y' || proceed=='y') {
					loan = loanService.addLoanDetails(id, amount, tenure, roi, score);
					loan.setAccountBalance(10000);
					String sid = loanService.createLoanRequest(loan);
					System.out.println("(Account Number:"+sid+") Details added sucessfully for loan");	
					System.out.println("Requested for loan of "+ loan.getAmount()+" for "+loan.getTenure()+" months");
					}
					
					else {
						if(proceed == 'N' || proceed =='n')
							break;
						System.err.println("enter (Y/N)");
					}
							
	
				}
				catch (LoanException le) {
					System.err.println("\n"+le.getMessage()+"\n");
				}
				break;
				
			case 2:
				try {
					System.out.println("(Account Number:"+loan.getAccountId()+") Loan Status = "+loanService.loanApprovalStatus(loan,loan.getAccountId()));
					
				}
				catch(LoanException le) {
					System.err.println("\n"+le.getMessage()+"\n");
				}
				catch(NullPointerException ne) {
					System.err.println("\nLoan details not added\n");
				}
				break;
			
			case 3:
				try {
					System.out.println("(Account Number:"+loan.getAccountId()+") emi = "+loanService.calculateEmiForLoan(loan));
					
				}
				catch(LoanException le) {
					System.err.println("\n"+le.getMessage()+"\n");
				}
				catch(NullPointerException ne) {
					System.err.println("\nLoan details not added\n");
				}
				break;
				
			case 4:
				try {
					list = new ArrayList<>();
					list = loanService.loanRequestList();
					if(list.isEmpty())
						throw new LoanException("Loan Request List : Empty List");
					System.out.println("Account ID\t\tTenure\t\tLoan Amount\t\tRate Of Interest\t\tCredit Score\t\tLoan Status\t\tAccount Balance\t\tEMI\n");
					list.stream().forEach(p -> System.out.println(p));
					
					
					
				}
				catch(LoanException le) {
					System.err.println("\n"+le.getMessage()+"\n");
				}
				break;
				
			
			case 5:
				try {
					list = new ArrayList<Loan>();
					list = loanService.loanApprovalList(loan);
					if(list.isEmpty())
						throw new LoanException("Loan Approval List : Empty List");
					System.out.println("Account ID\t\tTenure\t\tLoan Amount\t\tRate Of Interest\t\tCredit Score\t\tLoan Status\t\tAccount Balance\t\tEMI\n");
					list.stream().forEach(p -> System.out.println(p));
					
				}
				catch(LoanException le) {
					System.err.println("\n"+le.getMessage()+"\n");
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
		catch(InputMismatchException e) {
			System.err.println("\nenter valid number (1-6)\n");
		}

	}

}
